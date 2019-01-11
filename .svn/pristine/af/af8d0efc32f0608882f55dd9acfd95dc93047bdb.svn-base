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
 * 接收客户端请求，处理请求，并将处理结果返回客户端
 * 在整个Web Application中共享使用，由MainServlet调用
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
     * 接收请求，返回客户端结果信息
     * @param request
     * @return          返回给客户端的XML字符串
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
            
            //出错处理
            if (request.getAttribute(RequestNames.ERROR) != null) {
                return doError(request,transformer);
            }
            
            //第一次登录
            if (request.getAttribute(RequestNames.NEW_LOGON) != null) {
                return doLogin(request,transformer);
            }
            
            //退出系统
            if (request.getAttribute(RequestNames.IS_LOG_OFF) != null) {
                return doLogout(request,transformer);
            }
            
            //解析XML，生成返回参数
            return doProcessor(request,head,transformer);
        }
	}
    /**
     * 交易出错处理
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
     * 第一次登录处理
     * @param request
     * @param transformer
     * @return
     */
    private String doLogin(HttpServletRequest request,Transformer transformer){
        HttpSession httpSession = request.getSession(false);
        //获取服务端得到的用户权限
        Collection cl = (Collection) httpSession
                .getAttribute(SessionNames.FUNCTION_LIST);
        //获得服务端得到的数据版本信息
        Collection ver = (Collection) httpSession
                .getAttribute(SessionNames.VERSION_LIST);
        request.removeAttribute(SessionNames.VERSION_LIST);
        request.removeAttribute(RequestNames.NEW_LOGON);
        
        //格式化权限为规范的客户端格式
        return transformer.doLogin(cl,ver);
    }
    /**
     * 退出系统（登出）处理
     * @param request
     * @param transformer
     * @return
     */
    private String doLogout(HttpServletRequest request,Transformer transformer){
        HttpSession session = request.getSession(false);
        session.invalidate();
        EventResponse eventResponse = new EventResponse();
        HashMap body = new HashMap();
        body.put("message", "成功退出系统,欢迎下次再来");
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
     * 解析XML，生成返回参数
     * @param request
     * @param head
     * @param transformer
     * @return
     */
    private String doProcessor(HttpServletRequest request,RequestEnvelopHead head,Transformer transformer){
        String xmlString =
            (String) request.getAttribute(RequestNames.XML_STRING);
        //安全处理,验证数字签名
        String type = null;
        if (GlobalNames.ENCRYPT_FLAG) {
            FunctionCache cache =
                (FunctionCache) (context
                    .getAttribute(GlobalNames.FUNCTION_CACHE));
            if(cache!=null){
                type = cache.getSignatureType(head.getFunctionID());
            }
            head.setSignatureType(type);
            //需要服务端验证客户端提供的数字签名
            if (type != null
                && (type.equals(GlobalNames.CLIENT_CERT_FUNCTION) //10
                    || type.equals(GlobalNames.BOTH_CERT_FUNCTION))) { //11
                //获得客户端提供的签名:取出会话属性BodySign的值(即传入xml数据中signature的值)
                String sign = head.getSignature();
                //获得客户的证书
                HttpSession httpSession = request.getSession(false);
                String cert =
                    (String) httpSession.getAttribute(SessionNames.CERT);
                boolean isValid = false;
                try {
                    SecurityUtil util = new SecurityUtil();
                    //获得传入的业务数据
                    String body =
                        xmlString.substring(
                            xmlString.indexOf("<in:business"),
                            xmlString.indexOf("</soap:Body>"));
                    //验证签名，验证失败则抛出SafeException
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
        
        //进行soap消息的解码
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
        //执行业务处理，并返回业务处理结果
        return doAction(request,transformer,head.getFunctionID(),event);
    }
    
    /**
     * 执行业务ACTION
     * 通过调用action.perform方法执行
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
        //获取action
        actionName = actionMappings.getAction(functionId);
        if (actionName == null) {
            actionName = actionMappings.getTransAction(functionId);
            if (actionName != null)
                isTxAction = true;
        }

        //找不到和functionID匹配的Action
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
                //交易支持action
                transAction =
                    (TransAction) Class.forName(actionName).newInstance();
                transAction.init(context, request);
                transAction.preExec();
                eventResponse = transAction.exec(event);
            } else {
                //不支持交易的action
                Action action = null;
                action = (Action) Class.forName(actionName).newInstance();
                action.init(context, httpSession);
                eventResponse = action.perform(event);
            }
            //业务处理失败
            if (!eventResponse.isSucessFlag()) {
                EventError error = new EventError();
                error.setErrorCode(2003);
                error.setErrorMsg(eventResponse.getMsg());
                return transformer.doError(error);
            }
            //业务处理成功
            String result = transformer.doResponse(eventResponse);
            
            //交易支持action的后处理
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
