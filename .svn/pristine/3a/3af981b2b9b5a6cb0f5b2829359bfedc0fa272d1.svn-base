package org.radf.manage.trans.action;

import java.util.HashMap;

import org.radf.manage.trans.entity.SysTransVastInput;
import org.radf.manage.trans.entity.SysTransVastOutput;
import org.radf.manage.trans.facade.TransFacade;
import org.radf.plat.commons.safe.BASE64Encoder;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * 查询SYSTRANSVASTINPUT、SYSTRANSVASTOUT记录
 * @author Kent Kong
 * @version 1.0
 */

public class FindInOutputByPKAction extends ActionSupport{
    private String className = this.getClass().getName();
  /**
   * 调用TransFacade.findInOutputByPK()
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
            ResponseEnvelop resEnv = facade.findInOutputByPK(value);

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
        HashMap value = (HashMap)resBody;
        SysTransVastInput input = (SysTransVastInput)value.get("inputData");
        BASE64Encoder encoder = new BASE64Encoder();
        String tmp = encoder.encode(input.getData());

        HashMap body = new HashMap();
        body.put("inputData",tmp);
        if(value.get("outputData") != null){
            tmp = null;
            SysTransVastOutput output = (SysTransVastOutput)value.get("outputData");
            tmp = encoder.encode(output.getData());
            body.put("outputData",tmp);
        }
       return body;
    }

}
