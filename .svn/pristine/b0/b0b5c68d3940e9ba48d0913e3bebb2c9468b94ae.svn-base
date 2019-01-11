package org.radf.manage.trans.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.radf.manage.trans.entity.SysTransLog;
import org.radf.manage.trans.facade.TransFacade;
import org.radf.manage.util.Constants;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * 查询logid大于指定值的SYSTRANSLOG记录
 * @author Kent Kong
 * @version 1.0
 */

public class FindTransLogForwardAction extends ActionSupport{
    private String className = this.getClass().getName();
  /**
   * 调用TransFacade.findTransLogForward()
   */
    public EventResponse perform(Event evt){
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;

        try {
            // 获取接口实现类
            TransFacade facade = (TransFacade) getService("TransFacade");
            // 入口参数转换
            value = processEvent(evt);

            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.findTransLogForward(value);

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
        String logId =  (String)hashBody.get("logId");
        try {
            Long.parseLong(logId);
        }
        catch (Exception ex) {
            throw new ManageInputException("非法的交易ID");
        }
        return logId;
    }
    /**
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * 
     * @param Object
     *        body ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object resBody) {
        ArrayList results = (ArrayList)resBody;
        HashMap body = new HashMap();
        Vector vector = new Vector();
        int j = results.size();
        boolean hasMore = false;
        if(j == Constants.PAGE_ROWS + 1){
            hasMore = true;
            j = Constants.PAGE_ROWS;
        }
        for(int i = 0  ; i < j ; i ++){
            SysTransLog sysTransLog = (SysTransLog)results.get(i);
            long logId = sysTransLog.getLogId();
            String startTime = sysTransLog.getStartTime();
            String endTime = sysTransLog.getEndTime();
            String transId = sysTransLog.getTransId();
            String transName = sysTransLog.getTransName();
            long origLogId = sysTransLog.getOrigLogId();
            String loginName = sysTransLog.getLoginName();
            String sessionId = sysTransLog.getSessionId();
            int timeoutTime = sysTransLog.getTimeoutTime();
            String status = sysTransLog.getStatus();

            HashMap tmp = new HashMap();
            tmp.put("logId", String.valueOf(logId));
            tmp.put("startTime", startTime);
            tmp.put("endTime", endTime);
            tmp.put("transId", transId);
            tmp.put("transName", transName);
            tmp.put("origLogId", String.valueOf(origLogId));
            tmp.put("userId", loginName);
            tmp.put("sessionId", sessionId);
            tmp.put("timeoutTime", String.valueOf(timeoutTime));
            tmp.put("status", status);

            vector.add(tmp);
        }
        if(hasMore)
            body.put("hasMore","true");
        else
            body.put("hasMore","false");
        body.put("results",vector);
        return body;
    }

}
