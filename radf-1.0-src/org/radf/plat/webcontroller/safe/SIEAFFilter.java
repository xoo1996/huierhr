/**
 * <p>标题: SIEAF SaftyFilter</p>
 * <p>说明: SIEAF框架下安全过滤器处理类</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-11-1110:24:17</p>
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
                // 新登录
                hreq.setAttribute(RequestNames.ERROR, processNewLogon(hreq,
                        head));
            } else {
                // 没有传sessionID，认为登录超时
                hreq.setAttribute(RequestNames.ERROR, dealException(1500,
                        GlobalErrorMsg.SYS_FILTER_LOGIN, head));
            }
        } else {
            // 已经登陆
            head.setSessionID(httpSession.getId());
            RequestEnvelopHead old = (RequestEnvelopHead)httpSession.getAttribute(SessionNames.HEAD);

            //从原来登录的信息传递参数
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
     * SIEAF模式 新用户登录处理流程
     * 
     * @param HttpServletRequest
     * @param RequestEnvelopHead
     * @return EventError
     */
    private EventError processNewLogon(HttpServletRequest hreq,
            RequestEnvelopHead head) {
        SysUser user = null;
        HashMap returnMap = null;
        // 用户校验
        try {
            returnMap = loginAction(head.getLoginName(), head.getPassword());
        } catch (AppException ae) {
            ae.printStackTrace();
            return dealException(ae.getErrorCode(), ae.getMessage(), head);
        }
        try {
            // 获取登录用户信息
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
            System.out.println("当前登录的IP = " + head.getIp());
            // 获取用户有权使用的功能列表
            Collection functionList = (Collection) returnMap
                    .get(SessionNames.FUNCTION_LIST);
            Collection versionList = (Collection) returnMap
                    .get(SessionNames.VERSION_LIST);
            // 创建用户session信息
            doWhenSessionCreated(hreq, head, user, functionList, versionList);
        } catch (Exception ae) {
            ae.printStackTrace();
            return dealException(1406, ae.getMessage(), head);
        }
        return null;
    }

    /**
     * 已经登录用户(已有session的连接)的合法性验证, 如果正常，返回null，否则返回错误信息
     * 当采用SIEAF框架时，head为交易头信息，如果为LEAF框架，head=null
     * 
     * @param HttpServletRequest
     * @param RequestEnvelopHead
     *            sieaf框架下交易头信息，web模式为空
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
        // 校验用户ID
        if (!(loginName.trim().equals(strSessionUserID.trim()))) {
            return dealException(1407, GlobalErrorMsg.SYS_ROLE_LOGIN, head);
        }
        // 校验用户口令
        if (!(pwd.trim().equals(strPWD.trim()))) {
            return dealException(1405, GlobalErrorMsg.SYS_ROLE_LOGIN, head);
        }
        // 校验功能ID是否为“退出登录”
        if (functionID.equals("F0")) {
            hreq.setAttribute(RequestNames.IS_LOG_OFF, "true");
            LogHelper log = new LogHelper(this.getClass().getName());
            log.log(head, 1408, "退出系统");
            log = null;
            return null;
        }
        // 校验功能ID是否有权限：
        // (1)先判断是否为公用体系权限verifyFunctionID
        // (2)判断是否为此人操作权限helper.postValidate(functionList, functionID)
        // (3)判断是否为系统特有的公共权限GlobalNames.ROLE_PUBLIC_ALL
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
     * SIEAF模式 判断给定的functionID是否在可以被共用访问的功能定义 如果是则不校验是否在用户的功能列表中 公共访问的业务包括：
     * "F00" 系统退出 "F12.XX" 公共业务 "F00.01.XX" 公共业务 "F00.02.XX" 公共业务 "F00.00.00.XX"
     * 系统管理
     * 
     * @param functionID
     * @return
     */
    private boolean verifyFunctionID(String functionID) {
        if (functionID.equalsIgnoreCase("F00") || functionID.startsWith("F12")) {
            return true; // F00，或F12.XX
        }
        if (functionID.startsWith("F00.01") || functionID.startsWith("F00.02")) {
            return true;
        }
        if (functionID.startsWith("F00.00.00")) {
            return true;
        }
        if (functionID.equalsIgnoreCase("F09.01.02")) { // 修改密码
            return true;
        }
        return false;
    }

    /**
     * 创建登录者Session信息
     * 
     * @param hreq
     * @param head
     * @param user
     * @param functionList
     */
    private void doWhenSessionCreated(HttpServletRequest hreq,
            RequestEnvelopHead head, SysUser user, Collection functionList,
            Collection versionList) {
        // 创建session对象
        HttpSession httpSession = hreq.getSession(true);
        head.setSessionID(httpSession.getId());
        hreq.setAttribute(RequestNames.NEW_LOGON, "login");
        
        //保存登录人信息
        httpSession.setAttribute(SessionNames.FUNCTION_LIST, functionList);
        httpSession.setAttribute(SessionNames.VERSION_LIST, versionList);
        httpSession.setAttribute(SessionNames.HEAD,head);
        httpSession.setAttribute(SessionNames.USER_ID, head.getLoginName());
        httpSession.setAttribute(SessionNames.PWD, head.getPassword());
//        httpSession.setAttribute(SessionNames.IP, head.getIp());

        LogHelper log = new LogHelper(this.getClass().toString());
        log.log(head, 1400, "login");
        log = null;
        // 安全处理
        if (GlobalNames.ENCRYPT_FLAG) {
            try {
                // get user's cert 获得存放用户对应的证书的文件的名字
                String certName = user.getCertName();
                // new SecurityUtil()隐含初试化了安全引擎，并取出了服务器端证书
                SecurityUtil util = new SecurityUtil();
                // 从文件中取出用户证书(并校验合法性)
                String cert = util.getCert(certName);
                // put it in session 将用户证书存入会话的属性ClientCert中
                httpSession.setAttribute(SessionNames.CERT, cert);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用户登录Facade调用接口
     * 
     * @param loginName
     * @param password
     * @return
     * @throws AppException
     */
    private HashMap loginAction(String loginName, String password)
            throws AppException {
        // 获取接口实现类
        RoleFacade facade = null;
        try {
            //FacadeFactory ff = new FacadeFactory();
            facade = (RoleFacade) FacadeFactory.getService("RoleFacade");
        } catch (AppException ae) {
            ae.printStackTrace();
            throw new AppException(1406, "系统登录接口RoleFacade创建错误:"
                    + ae.getMessage());
        }
        // 入口参数组装
        SysUser dto = new SysUser();
        dto.setLoginName(loginName);
        dto.setPassWD(password);
        // 登录
        return facade.userLogin(dto);
    }

    /**
     * 登录异常错误信息生成
     * 
     * @param code
     *            错误码
     * @param msg
     *            错误信息
     * @param head
     *            交易头信息
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
