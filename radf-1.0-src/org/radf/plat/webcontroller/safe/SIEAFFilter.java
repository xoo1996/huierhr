/**
 * <p>����: SIEAF SaftyFilter</p>
 * <p>˵��: SIEAF����°�ȫ������������</p>
 * <p>��Ŀ: Rapid Application Development Framework</p>
 * <p>��Ȩ: Copyright 2008 - 2017</p>
 * <p>ʱ��: 2005-11-1110:24:17</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.plat.webcontroller.safe;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radf.manage.role.entity.SysUser;
import org.radf.manage.role.facade.RoleFacade;
import org.radf.plat.commons.CommonVerify;
import org.radf.plat.commons.safe.SecurityUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.event.EventError;
import org.radf.plat.util.FacadeFactory;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.util.global.RequestNames;
import org.radf.plat.util.global.SessionNames;

public class SIEAFFilter implements SaftyFacade {

    public boolean filter(FilterConfig config,HttpServletRequest hreq, HttpServletResponse hres,
            RequestEnvelopHead head) throws IOException, ServletException{
        HttpSession httpSession = hreq.getSession(false);
        if (httpSession == null) {
            if (head.getFunctionID().equals("F00.00.00.00")) {
                // �µ�¼
                hreq.setAttribute(RequestNames.ERROR, processNewLogon(hreq,
                        head));
            } else {
                // û�д�sessionID����Ϊ��¼��ʱ
                hreq.setAttribute(RequestNames.ERROR, dealException(1500,
                        GlobalErrorMsg.SYS_FILTER_LOGIN, head));
            }
        } else {
            // �Ѿ���½
            head.setSessionID(httpSession.getId());
            RequestEnvelopHead old = (RequestEnvelopHead)httpSession.getAttribute(SessionNames.HEAD);

            //��ԭ����¼����Ϣ���ݲ���
            head.setUserId(old.getUserId());
            head.setUsrName(old.getUsrName());
            head.setDEPTID(old.getDEPTID());
            head.setIp(old.getIp());
            head.setXQBM(old.getXQBM());
            head.setXZBM(old.getXZBM());
            head.setCBM(old.getCBM());
            head.setAab034(old.getAab034());
            
            hreq.setAttribute(RequestNames.ENV_HEAD, head);
            hreq.setAttribute(RequestNames.ERROR, processLogon(hreq, head));
        }
        return true;
    }

    /**
     * SIEAFģʽ ���û���¼��������
     * 
     * @param HttpServletRequest
     * @param RequestEnvelopHead
     * @return EventError
     */
    private EventError processNewLogon(HttpServletRequest hreq,
            RequestEnvelopHead head) {
        SysUser user = null;
        HashMap returnMap = null;
        // �û�У��
        try {
            returnMap = loginAction(head.getLoginName(), head.getPassword());
        } catch (AppException ae) {
            ae.printStackTrace();
            return dealException(ae.getErrorCode(), ae.getMessage(), head);
        }
        try {
            // ��ȡ��¼�û���Ϣ
            user = (SysUser) returnMap.get("SysUser");
            head.setUserId(user.getUserID());
            head.setLoginName(user.getLoginName());
            head.setUsrName(user.getOperatorName());
            head.setAab034(user.getAab034());
            head.setType(user.getType());
            head.setDEPTID(user.getDeptID());
            head.setIp(hreq.getRemoteAddr());
            head.setXQBM(user.getXQBM());
            head.setXZBM(user.getXZBM());
            head.setCBM(user.getCBM());
            System.out.println("��ǰ��¼��IP = " + head.getIp());
            // ��ȡ�û���Ȩʹ�õĹ����б�
            Collection functionList = (Collection) returnMap
                    .get(SessionNames.FUNCTION_LIST);
            Collection versionList = (Collection) returnMap
                    .get(SessionNames.VERSION_LIST);
            // �����û�session��Ϣ
            doWhenSessionCreated(hreq, head, user, functionList, versionList);
        } catch (Exception ae) {
            ae.printStackTrace();
            return dealException(1406, ae.getMessage(), head);
        }
        return null;
    }

    /**
     * �Ѿ���¼�û�(����session������)�ĺϷ�����֤, �������������null�����򷵻ش�����Ϣ
     * ������SIEAF���ʱ��headΪ����ͷ��Ϣ�����ΪLEAF��ܣ�head=null
     * 
     * @param HttpServletRequest
     * @param RequestEnvelopHead
     *            sieaf����½���ͷ��Ϣ��webģʽΪ��
     * @return EventError
     */
    private EventError processLogon(HttpServletRequest hreq,
            RequestEnvelopHead head) {
        String loginName = head.getLoginName();
        if (loginName == null)
            loginName = "";
        String functionID = head.getFunctionID();
        if (functionID == null)
            functionID = "";
        String pwd = head.getPassword();
        if (pwd == null)
            pwd = "";
        HttpSession httpSession = hreq.getSession(false);
        String strSessionUserID = ((String) httpSession
                .getAttribute(SessionNames.USER_ID));

        String strPWD = ((String) httpSession.getAttribute(SessionNames.PWD));
        // У���û�ID
        if (!(loginName.trim().equals(strSessionUserID.trim()))) {
            return dealException(1407, GlobalErrorMsg.SYS_ROLE_LOGIN, head);
        }
        // У���û�����
        if (!(pwd.trim().equals(strPWD.trim()))) {
            return dealException(1405, GlobalErrorMsg.SYS_ROLE_LOGIN, head);
        }
        // У�鹦��ID�Ƿ�Ϊ���˳���¼��
        if (functionID.equals("F0")) {
            hreq.setAttribute(RequestNames.IS_LOG_OFF, "true");
            LogHelper log = new LogHelper(this.getClass().getName());
            log.log(head, 1408, "�˳�ϵͳ");
            log = null;
            return null;
        }
        // У�鹦��ID�Ƿ���Ȩ�ޣ�
        // (1)���ж��Ƿ�Ϊ������ϵȨ��verifyFunctionID
        // (2)�ж��Ƿ�Ϊ���˲���Ȩ��helper.postValidate(functionList, functionID)
        // (3)�ж��Ƿ�Ϊϵͳ���еĹ���Ȩ��GlobalNames.ROLE_PUBLIC_ALL
        if (!verifyFunctionID(functionID)) {
            Collection functionList = null;
            functionList = (Collection) httpSession
                    .getAttribute(SessionNames.FUNCTION_LIST);

            CommonVerify helper = new CommonVerify();
            if ((!helper.postValidate(functionList, functionID))
                    && (!helper.postValidate(GlobalNames.ROLE_PUBLIC_ALL,
                            functionID))) {

                return dealException(1409, GlobalErrorMsg.SYS_ROLE_LACK, head);
            }
        }
        return null;
    }

    /**
     * SIEAFģʽ �жϸ�����functionID�Ƿ��ڿ��Ա����÷��ʵĹ��ܶ��� �������У���Ƿ����û��Ĺ����б��� �������ʵ�ҵ�������
     * "F00" ϵͳ�˳� "F12.XX" ����ҵ�� "F00.01.XX" ����ҵ�� "F00.02.XX" ����ҵ�� "F00.00.00.XX"
     * ϵͳ����
     * 
     * @param functionID
     * @return
     */
    private boolean verifyFunctionID(String functionID) {
        if (functionID.equalsIgnoreCase("F00") || functionID.startsWith("F12")) {
            return true; // F00����F12.XX
        }
        if (functionID.startsWith("F00.01") || functionID.startsWith("F00.02")) {
            return true;
        }
        if (functionID.startsWith("F00.00.00")) {
            return true;
        }
        if (functionID.equalsIgnoreCase("F09.01.02")) { // �޸�����
            return true;
        }
        return false;
    }

    /**
     * ������¼��Session��Ϣ
     * 
     * @param hreq
     * @param head
     * @param user
     * @param functionList
     */
    private void doWhenSessionCreated(HttpServletRequest hreq,
            RequestEnvelopHead head, SysUser user, Collection functionList,
            Collection versionList) {
        // ����session����
        HttpSession httpSession = hreq.getSession(true);
        head.setSessionID(httpSession.getId());
        hreq.setAttribute(RequestNames.NEW_LOGON, "login");
        
        //�����¼����Ϣ
        httpSession.setAttribute(SessionNames.FUNCTION_LIST, functionList);
        httpSession.setAttribute(SessionNames.VERSION_LIST, versionList);
        httpSession.setAttribute(SessionNames.HEAD,head);
        httpSession.setAttribute(SessionNames.USER_ID, head.getLoginName());
        httpSession.setAttribute(SessionNames.PWD, head.getPassword());
//        httpSession.setAttribute(SessionNames.IP, head.getIp());

        LogHelper log = new LogHelper(this.getClass().toString());
        log.log(head, 1400, "login");
        log = null;
        // ��ȫ����
        if (GlobalNames.ENCRYPT_FLAG) {
            try {
                // get user's cert ��ô���û���Ӧ��֤����ļ�������
                String certName = user.getCertName();
                // new SecurityUtil()�������Ի��˰�ȫ���棬��ȡ���˷�������֤��
                SecurityUtil util = new SecurityUtil();
                // ���ļ���ȡ���û�֤��(��У��Ϸ���)
                String cert = util.getCert(certName);
                // put it in session ���û�֤�����Ự������ClientCert��
                httpSession.setAttribute(SessionNames.CERT, cert);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * �û���¼Facade���ýӿ�
     * 
     * @param loginName
     * @param password
     * @return
     * @throws AppException
     */
    private HashMap loginAction(String loginName, String password)
            throws AppException {
        // ��ȡ�ӿ�ʵ����
        RoleFacade facade = null;
        try {
            //FacadeFactory ff = new FacadeFactory();
            facade = (RoleFacade) FacadeFactory.getService("RoleFacade");
        } catch (AppException ae) {
            ae.printStackTrace();
            throw new AppException(1406, "ϵͳ��¼�ӿ�RoleFacade��������:"
                    + ae.getMessage());
        }
        // ��ڲ�����װ
        SysUser dto = new SysUser();
        dto.setLoginName(loginName);
        dto.setPassWD(password);
        // ��¼
        return facade.userLogin(dto);
    }

    /**
     * ��¼�쳣������Ϣ����
     * 
     * @param code
     *            ������
     * @param msg
     *            ������Ϣ
     * @param head
     *            ����ͷ��Ϣ
     * @return
     */
    private EventError dealException(int code, String msg,
            RequestEnvelopHead head) {
        EventError eventError = new EventError();
        eventError.setErrorCode(code);
        eventError.setErrorMsg(msg);
        LogHelper log = new LogHelper(this.getClass().getName());
        log.log(head, code, msg);
        log = null;
        return eventError;
    }

}
