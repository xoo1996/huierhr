package org.radf.manage.trans.action;

import java.util.HashMap;

import org.radf.manage.trans.entity.SysTranseDef;
import org.radf.manage.trans.facade.TransFacade;
import org.radf.manage.util.Constants;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * 生成SYSTRANSEDEF记录
 * @author zqb
 * @version 1.0
 */
public class CreateTransAction extends ActionSupport{
    private String className = this.getClass().getName();

  /**
   * 调用TransFacade.createTrans()
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
            ResponseEnvelop resEnv = facade.createTrans(value);

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
        SysTranseDef sysTranseDef = new SysTranseDef();
        String transId =  TypeCast.stringToString
            ((String)hashBody.get("transId"),"交易ID",false);
        String transName = TypeCast.stringToString
            ((String)hashBody.get("transName"),"交易名称",true);
        String transDesc =  TypeCast.stringToString
            ((String)hashBody.get("transDesc"),"交易说明",true);
        int iTimeOut = TypeCast.stringToInt
            ((String)hashBody.get("timeOut"),"超时时间",false);
        String transType =  TypeCast.stringToString
            ((String)hashBody.get("transType"),"交易类型",false);
        String undoTransId =  (String)hashBody.get("undoTransId");
        String redoTransId =  (String)hashBody.get("redoTransId");
        //String inputDataType =  (String)hashBody.get("inputDataType");
        //String outputDataType =  (String)hashBody.get("outputDataType");

        if(transId!=null&&transId.length() > 30 )
            throw new ManageInputException("交易ID长度必须小于30");
        if(transName!=null&&transName.length() > 20)
            throw new ManageInputException("交易名称长度必须小于20");
        if(transDesc!=null&&transDesc.length() > 30)
            throw new ManageInputException("交易描述长度必须小于30");
        
        if(iTimeOut < 0)
            throw new ManageInputException("超时时间不能小于零");
        if( !transType.equals(Constants.TRANS_FORWARD) &&
            !transType.equals(Constants.TRANS_BACKWARD) &&
            !transType.equals(Constants.TRANS_REDO)){
            throw new ManageInputException("交易类型非法");
        }
        if(undoTransId != null && undoTransId.length() > 30)
            throw new ManageInputException("反向交易ID长度必须小于30");

        if(redoTransId != null && redoTransId.length() > 30)
            throw new ManageInputException("重做交易ID长度必须小于30");


        sysTranseDef.setTransId(transId);
        sysTranseDef.setTransName(transName);
        sysTranseDef.setTransDesc(transDesc);
        sysTranseDef.setTimeOut(iTimeOut);
        sysTranseDef.setTransType(transType);
        sysTranseDef.setUndoTransId(undoTransId);
        sysTranseDef.setRedoTransId(redoTransId);
        //sysTranseDef.setInputDataType(inputDataType);
        //sysTranseDef.setOutputDataType(outputDataType);

        return sysTranseDef;
    }
    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }

}
