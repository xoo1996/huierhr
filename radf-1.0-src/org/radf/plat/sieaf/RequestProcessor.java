package org.radf.plat.sieaf;

import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.radf.plat.commons.safe.SecurityUtil;
import org.radf.plat.sieaf.actionmapping.ActionMapping;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventError;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.sieaf.trans.TransAction;
import org.radf.plat.sieaf.transformer.Transformer;
import org.radf.plat.sieaf.transformer.TransformerFactory;
import org.radf.plat.util.action.Action;
import org.radf.plat.util.exception.SafeException;
import org.radf.plat.util.exception.TransException;
import org.radf.plat.util.exception.WebException;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.util.global.RequestNames;
import org.radf.plat.util.global.SessionNames;

/**
 * ���տͻ������󣬴������󣬲������������ؿͻ���
 * ������Web Application�й���ʹ�ã���MainServlet����
 * @author zqb
 * @version 1.0
 */
public class RequestProcessor implements java.io.Serializable {

	private ServletContext context;

	public RequestProcessor() {

	}

	public void init(ServletContext context) {
		this.context = context;

	}
    
    /**
     * �������󣬷��ؿͻ��˽����Ϣ
     * @param request
     * @return          ���ظ��ͻ��˵�XML�ַ���
     * @throws ServletException
     */
	public String processRequest(HttpServletRequest request)
		throws ServletException {

		RequestEnvelopHead head =
			(RequestEnvelopHead) request.getAttribute(RequestNames.ENV_HEAD);
        
        if(head==null){
            return "HTTP";
        }else{
            Transformer transformer = TransformerFactory.getTransformer();
            transformer.init(head);
            
            //������
            if (request.getAttribute(RequestNames.ERROR) != null) {
                return doError(request,transformer);
            }
            
            //��һ�ε�¼
            if (request.getAttribute(RequestNames.NEW_LOGON) != null) {
                return doLogin(request,transformer);
            }
            
            //�˳�ϵͳ
            if (request.getAttribute(RequestNames.IS_LOG_OFF) != null) {
                return doLogout(request,transformer);
            }
            
            //����XML�����ɷ��ز���
            return doProcessor(request,head,transformer);
        }
	}
    /**
     * ���׳�����
     * @param request
     * @param transformer
     * @return
     */
    private String doError(HttpServletRequest request,Transformer transformer){
        EventError error =
            (EventError) request.getAttribute(RequestNames.ERROR);
        request.removeAttribute(RequestNames.ERROR);
        return transformer.doError(error);
    }
    /**
     * ��һ�ε�¼����
     * @param request
     * @param transformer
     * @return
     */
    private String doLogin(HttpServletRequest request,Transformer transformer){
        HttpSession httpSession = request.getSession(false);
        //��ȡ����˵õ����û�Ȩ��
        Collection cl = (Collection) httpSession
                .getAttribute(SessionNames.FUNCTION_LIST);
        //��÷���˵õ������ݰ汾��Ϣ
        Collection ver = (Collection) httpSession
                .getAttribute(SessionNames.VERSION_LIST);
        request.removeAttribute(SessionNames.VERSION_LIST);
        request.removeAttribute(RequestNames.NEW_LOGON);
        
        //��ʽ��Ȩ��Ϊ�淶�Ŀͻ��˸�ʽ
        return transformer.doLogin(cl,ver);
    }
    /**
     * �˳�ϵͳ���ǳ�������
     * @param request
     * @param transformer
     * @return
     */
    private String doLogout(HttpServletRequest request,Transformer transformer){
        HttpSession session = request.getSession(false);
        session.invalidate();
        EventResponse eventResponse = new EventResponse();
        HashMap body = new HashMap();
        body.put("message", "�ɹ��˳�ϵͳ,��ӭ�´�����");
        eventResponse.setBody(body);
        eventResponse.setSucessFlag(true);
        String result;
        try {
            return transformer.doResponse(eventResponse);
        } catch (WebException we) {
            we.printStackTrace();
            EventError error = new EventError();
            error.setErrorCode(6000);
            error.setErrorMsg(we.getMessage());
            return transformer.doError(error);
        }
    }
    /**
     * ����XML�����ɷ��ز���
     * @param request
     * @param head
     * @param transformer
     * @return
     */
    private String doProcessor(HttpServletRequest request,RequestEnvelopHead head,Transformer transformer){
        String xmlString =
            (String) request.getAttribute(RequestNames.XML_STRING);
        //��ȫ����,��֤����ǩ��
        String type = null;
        if (GlobalNames.ENCRYPT_FLAG) {
            FunctionCache cache =
                (FunctionCache) (context
                    .getAttribute(GlobalNames.FUNCTION_CACHE));
            if(cache!=null){
                type = cache.getSignatureType(head.getFunctionID());
            }
            head.setSignatureType(type);
            //��Ҫ�������֤�ͻ����ṩ������ǩ��
            if (type != null
                && (type.equals(GlobalNames.CLIENT_CERT_FUNCTION) //10
                    || type.equals(GlobalNames.BOTH_CERT_FUNCTION))) { //11
                //��ÿͻ����ṩ��ǩ��:ȡ���Ự����BodySign��ֵ(������xml������signature��ֵ)
                String sign = head.getSignature();
                //��ÿͻ���֤��
                HttpSession httpSession = request.getSession(false);
                String cert =
                    (String) httpSession.getAttribute(SessionNames.CERT);
                boolean isValid = false;
                try {
                    SecurityUtil util = new SecurityUtil();
                    //��ô����ҵ������
                    String body =
                        xmlString.substring(
                            xmlString.indexOf("<in:business"),
                            xmlString.indexOf("</soap:Body>"));
                    //��֤ǩ������֤ʧ�����׳�SafeException
                    util.doValidate(body, sign, cert);

                } catch (SafeException se) {
                    se.printStackTrace();
                    EventError error = new EventError();
                    error.setErrorCode(6000);
                    error.setErrorMsg(se.getMessage());
                    return transformer.doError(error);
                }
            }
        }
        
        //����soap��Ϣ�Ľ���
        Event event = null;
        try {
            event = transformer.doRequest(xmlString);
            //HttpSession httpSession = request.getSession(false);
            //event.setXQBM("");
        } catch (TransException te) {
            te.printStackTrace();
            EventError error = new EventError();
            error.setErrorCode(5000);
            error.setErrorMsg(te.getMessage());
            return transformer.doError(error);
        }
        //ִ��ҵ����������ҵ������
        return doAction(request,transformer,head.getFunctionID(),event);
    }
    
    /**
     * ִ��ҵ��ACTION
     * ͨ������action.perform����ִ��
     * @param request
     * @param transformer
     * @param functionId
     * @param event
     * @return
     */
    private String doAction(HttpServletRequest request,Transformer transformer,String functionId,Event event){
        String actionName = null;
        boolean isTxAction = false;
        ActionMapping actionMappings =
            (ActionMapping) context.getAttribute(GlobalNames.ACTION_MAPPINGS);
        //��ȡaction
        actionName = actionMappings.getAction(functionId);
        if (actionName == null) {
            actionName = actionMappings.getTransAction(functionId);
            if (actionName != null)
                isTxAction = true;
        }

        //�Ҳ�����functionIDƥ���Action
        if (actionName == null) {
            EventError error = new EventError();
            error.setErrorCode(1004);
            error.setErrorMsg(GlobalErrorMsg.SYS_MAPPINGS_FUNCTIONID);
            System.out.println(GlobalErrorMsg.SYS_MAPPINGS_FUNCTIONID+":"+functionId);
            return transformer.doError(error);
        }
        try {
            EventResponse eventResponse = null;
            HttpSession httpSession = request.getSession(false);
            TransAction transAction = null;
            if (isTxAction) {
                //����֧��action
                transAction =
                    (TransAction) Class.forName(actionName).newInstance();
                transAction.init(context, request);
                transAction.preExec();
                eventResponse = transAction.exec(event);
            } else {
                //��֧�ֽ��׵�action
                Action action = null;
                action = (Action) Class.forName(actionName).newInstance();
                action.init(context, httpSession);
                eventResponse = action.perform(event);
            }
            //ҵ����ʧ��
            if (!eventResponse.isSucessFlag()) {
                EventError error = new EventError();
                error.setErrorCode(2003);
                error.setErrorMsg(eventResponse.getMsg());
                return transformer.doError(error);
            }
            //ҵ����ɹ�
            String result = transformer.doResponse(eventResponse);
            
            //����֧��action�ĺ���
            if (isTxAction) {
                httpSession.setAttribute(RequestNames.XML_STRING, result);
                transAction.postExec();
            }
            return result;
        } catch (WebException we) {
            we.printStackTrace();
            EventError error = new EventError();
            error.setErrorCode(5000);
            error.setErrorMsg(we.getMessage());
            return transformer.doError(error);
        } catch (Exception e) {
            e.printStackTrace();
            EventError error = new EventError();
            error.setErrorCode(0);
            error.setErrorMsg(e.getMessage());
            return transformer.doError(error);
        }
    }
}
