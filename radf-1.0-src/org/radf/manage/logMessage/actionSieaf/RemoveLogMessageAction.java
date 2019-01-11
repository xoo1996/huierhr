package org.radf.manage.logMessage.actionSieaf;

import java.util.HashMap;

import org.radf.manage.logMessage.entity.LogMessage;
import org.radf.manage.logMessage.facade.LogFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;

/**
 * <p>Title: 日志管理</p>
 * <p>Description:EjbAction </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: LBS</p>
 * @author <a href=mailto:qin_yalan@sina.com>Qin Yalan</a>
 * @version 1.0
 */

public class RemoveLogMessageAction extends ActionSupport {
    private String className = this.getClass().getName();

    /**
     * 调用LogFacade.removeLogMessage
     * @param evt
     * @return
     */
    public EventResponse perform(Event evt) {
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;

        try {
            // 获取接口实现类
            LogFacade facade = (LogFacade) getService("LogFacade");
            // 入口参数转换
            value = processEvent(evt);

            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.removeLogMessage(value);

            // 重组返回参数
            returnValue = processRevt(resEnv);

        } catch (ManageInputException me) {
            // 入口参数转换中出现的异常
            returnValue = ExceptionSupport(className, value, me, returnValue);
        } catch (AppException ae) {
            // 创建FacadeFactory出现的异常
            returnValue = ExceptionSupport(className, value, ae, returnValue);
        } catch (Exception ex) {
            // 其它异常
            returnValue = ExceptionSupport(className, value, ex, returnValue);
        }
        return returnValue;
    }

    /**
     * 系统入口参数封装方法 根据传入的HashMap，分解获取入口参数，并组装成所需要对象格式
     * 合法性判断主要判断长度、类型、校验等，一般通过客户端完成，本处只是防止客户端漏判，对重要字段重新校验
     * 
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        // check param
        String id = TypeCast.stringToString((String) hashBody.get("id"),
                "日志ID", false);

        HashMap reqbody = new HashMap();
        reqbody.put("dto", new LogMessage(id));

        return reqbody;
    }
    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }

}
