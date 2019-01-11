package org.radf.manage.role.actionSieaf;

import java.util.HashMap;

import org.radf.manage.role.entity.SysFunction;
import org.radf.manage.role.facade.FunctionFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * 生成一条sysfunction记录
 * @author Kent Kong
 * @version 1.0
 */

public class CreateFunctionAction extends ActionSupport{
    private String className = this.getClass().getName();
    
  /**
   *调用FunctionFacade.createFunction()
   */
    public EventResponse perform(Event evt){
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;
        
        try {
            //获取接口实现类
            FunctionFacade facade = (FunctionFacade) getService("FunctionFacade");
            //入口参数转换
            value = processEvent(evt);
            
            //调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.createFunction(value);
            
            //重组返回参数
            returnValue = processRevt(resEnv);
            
        } catch (ManageInputException me) {
            //入口参数转换中出现的异常
            returnValue = ExceptionSupport(className,value,me,returnValue);
        } catch (AppException ae) {
            //创建FacadeFactory出现的异常
            returnValue = ExceptionSupport(className,value,ae,returnValue);
        } catch (Exception ex) {
            //其它异常
            returnValue = ExceptionSupport(className,value,ex,returnValue);
        }
        
        return returnValue;
    }
    /**
     * 系统入口参数封装方法
     * 根据传入的HashMap，分解获取入口参数，并组装成所需要对象格式
     * 合法性判断主要判断长度、类型、校验等，一般通过客户端完成，本处只是防止客户端漏判，对重要字段重新校验
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody)throws ManageInputException{

        SysFunction sysFunction = new SysFunction();
        sysFunction.setFunctionId(TypeCast.stringToString((String)hashBody.get("functionId"),"功能ID",false));
        sysFunction.setFlag(TypeCast.stringToString((String)hashBody.get("flag"),"功能节点类型",false));
        sysFunction.setType(TypeCast.stringToString((String)hashBody.get("type"),"功能类型",false));
        sysFunction.setTransFlag(TypeCast.stringToString((String)hashBody.get("transFlag"),"交易类型",false));
        sysFunction.setFunctionDesc(TypeCast.stringToString((String)hashBody.get("functionDesc"),"功能描述",true));
        sysFunction.setParentId(TypeCast.stringToString((String)hashBody.get("parentId"),"父节点ID",true));
        sysFunction.setSignatureType(TypeCast.stringToString((String)hashBody.get("signatureType"),"签名类型",true));
        sysFunction.setLocation((String)hashBody.get("Location"));
        sysFunction.setLog((String)hashBody.get("Log"));
        sysFunction.setOrderno(TypeCast.stringToInt((String)hashBody.get("Orderno"),"Orderno",true));
        sysFunction.setOwner((String)hashBody.get("Owner"));
        sysFunction.setTitle((String)hashBody.get("Title"));

        String parentFlag = TypeCast.stringToString((String)hashBody.get("parentFlag"),"父节点功能类型",true);

        //check param
        if(sysFunction.getFunctionId().length() > 30)
            throw new ManageInputException("功能ID长度必须少于30位");
        if(!sysFunction.getFlag().equals("0") && !sysFunction.getFlag().equals("1"))
            throw new ManageInputException("功能节点类型错误");
        if(!sysFunction.getType().equals("0") && !sysFunction.getType().equals("1"))
            throw new ManageInputException("功能类型错误");
        if(!sysFunction.getTransFlag().equals("0") && !sysFunction.getTransFlag().equals("1"))
            throw new ManageInputException("功能的交易类型错误");
        if(sysFunction.getFunctionDesc().length() > 30)
            throw new ManageInputException("功能描述长度必须少于30位");
        if(sysFunction.getParentId().length() > 30)
            throw new ManageInputException("父节点功能的ID长度必须少于30位");

        if(sysFunction.getSignatureType().length() > 2)
            throw new ManageInputException("非法的签名类型");
        if(!parentFlag.equals("0") && !parentFlag.equals("1"))
            throw new ManageInputException("父节点功能类型错误");
        HashMap body = new HashMap();
        body.put("SysFunction",sysFunction);
        body.put("parentFlag",parentFlag);
        return body;
    }
    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }

}
