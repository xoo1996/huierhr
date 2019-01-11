package org.radf.manage.trans.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

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
 * 查询所有SYSTRANSEDEF记录
 * @author zqb
 * @version 1.0
 */

public class FindAllTransAction extends ActionSupport{
    private String className = this.getClass().getName();
  /**
   * 调用TransFacade.findAllTrans()
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
            ResponseEnvelop resEnv = facade.findAllTrans(value);

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
        for(int i = 0 , j = results.size() ; i < j ; i ++){
            SysTranseDef sysTranseDef = (SysTranseDef)results.get(i);
            String transId = sysTranseDef.getTransId();
            String transName = sysTranseDef.getTransName() == null ? "" : sysTranseDef.getTransName();
            String transDesc = sysTranseDef.getTransDesc() == null ? "" : sysTranseDef.getTransDesc();
            int timeOut = sysTranseDef.getTimeOut();
            String transType = sysTranseDef.getTransType();
            String undoTransId = sysTranseDef.getUndoTransId() == null ? "" : sysTranseDef.getUndoTransId();
            String redoTransId = sysTranseDef.getRedoTransId() == null ? "" : sysTranseDef.getRedoTransId();
            //String inputDataType = sysTranseDef.getInputDataType() == null ? "" : sysTranseDef.getInputDataType();
            //String outputDataType = sysTranseDef.getOutputDataType() == null ? "" : sysTranseDef.getOutputDataType();

            HashMap tmp = new HashMap();
            tmp.put("transId", transId);
            tmp.put("transName", transName);
            tmp.put("transDesc", transDesc);
            tmp.put("timeOut", String.valueOf(timeOut));
            tmp.put("transType", transType);
            tmp.put("undoTransId", undoTransId);
            tmp.put("redoTransId", redoTransId);
            //tmp.put("inputDataType", inputDataType);
            //tmp.put("outputDataType", outputDataType);
            vector.add(tmp);
        }
        body.put("results",vector);
        return body;
    }
    /**
     * @see ActionSupport#processBody(HashMap);
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        return null;
    }

}
