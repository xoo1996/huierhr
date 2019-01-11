package org.radf.manage.role.actionSieaf;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

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
 * 获取指定交易定义的父交易
 * <p>Company: Bea</p>
 * @author zqb
 * @version 1.0
 */

public class GetChildPairsAction extends ActionSupport{
    private String className = this.getClass().getName();

  /**
   *调用FunctionFacade.getChildPairs()
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
            ResponseEnvelop resEnv = facade.getChildPairs(value);
            
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
        String functionId = TypeCast.stringToString((String)hashBody.get("functionId"),"功能id",false);
        //check param
        if(functionId.length() > 30)
            throw new ManageInputException("功能ID长度必须少于30位");
        return new SysFunction(functionId);
   }
    /**
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * @param Object body ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
        Collection results = (Collection)resBody;
        
        HashMap body = new HashMap();
        Iterator it = results.iterator();
        SysFunction sysFunction = null;
        HashMap tmp = null;
        Vector vector = new Vector();
        while(it.hasNext()){
            sysFunction = (SysFunction)it.next();

            tmp = new HashMap(11);
            tmp.put("functionId", sysFunction.getFunctionId());
            tmp.put("flag", sysFunction.getFlag());
            tmp.put("type", sysFunction.getType());
            tmp.put("transFlag", sysFunction.getTransFlag());
            tmp.put("functionDesc", sysFunction.getFunctionDesc());
            tmp.put("parentId", sysFunction.getParentId());
            tmp.put("signatureType",sysFunction.getSignatureType());
            tmp.put("title" ,sysFunction.getTitle() );
            tmp.put("location",sysFunction.getLocation() );
            tmp.put("orderno" ,TypeCast.intToString(sysFunction.getOrderno()));
            tmp.put("log",sysFunction.getLog());
            tmp.put("owner",sysFunction.getOwner());
            vector.add(tmp);
        }
        body.put("resultset",vector);
        return body;
    }
}
