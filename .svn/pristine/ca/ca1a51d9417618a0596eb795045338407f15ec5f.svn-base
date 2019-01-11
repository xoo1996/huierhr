package org.radf.manage.trans.action;

import java.util.HashMap;

import org.radf.manage.trans.entity.SysTranseDef;
import org.radf.manage.trans.facade.TransFacade;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * 查询SYSTRANSEDEF记录
 * @author zqb
 * @version 1.0
 */

public class FindTransByPKAction extends ActionSupport{
    private String className = this.getClass().getName();
  /**
   * 调用TransFacade.findTransByPK()
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
            ResponseEnvelop resEnv = facade.findTransByPK(value);

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
        String transId =  (String)hashBody.get("transId");
        return new SysTranseDef(transId);
    }
    /**
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * 
     * @param Object
     *        body ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object resBody) {
        SysTranseDef sysTranseDef = (SysTranseDef)resBody;
        String transId = sysTranseDef.getTransId();
        String transName = sysTranseDef.getTransName() == null ? "" : sysTranseDef.getTransName();
        String transDesc = sysTranseDef.getTransDesc() == null ? "" : sysTranseDef.getTransDesc();
        int timeOut = sysTranseDef.getTimeOut();
        String transType = sysTranseDef.getTransType();
        String undoTransId = sysTranseDef.getUndoTransId() == null ? "" : sysTranseDef.getUndoTransId();
        String redoTransId = sysTranseDef.getRedoTransId() == null ? "" : sysTranseDef.getRedoTransId();
        //String inputDataType = sysTranseDef.getInputDataType() == null ? "" : sysTranseDef.getInputDataType();
        //String outputDataType = sysTranseDef.getOutputDataType() == null ? "" : sysTranseDef.getOutputDataType();


        HashMap body = new HashMap();
        body.put("transId", transId);
        body.put("transName", transName);
        body.put("transDesc", transDesc);
        body.put("timeOut", String.valueOf(timeOut));
        body.put("transType", transType);
        body.put("undoTransId", undoTransId);
        body.put("redoTransId", redoTransId);
        //body.put("inputDataType", inputDataType);
        //body.put("outputDataType", outputDataType);

        return body;
    }

}
