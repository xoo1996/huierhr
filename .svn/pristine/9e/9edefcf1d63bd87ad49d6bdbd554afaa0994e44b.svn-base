package org.radf.manage.role.actionSieaf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.radf.manage.role.entity.SysChange;
import org.radf.manage.role.facade.FunctionFacade;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;


/**
 * 查询id大于指定值的SYSCHANGE记录
 * 更新function的变动情况
 * @author zqb
 * @version 1.0
 */

public class FindNewVersionAction extends ActionSupport{
    private String className = this.getClass().getName();

  /**
   *调用SysChangeFacade.findNewVersion()
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
            ResponseEnvelop resEnv = facade.findNewVersion(value);
            
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
        String stId = (String)hashBody.get("id");
        long id = -1;

        try {
            id = Long.parseLong(stId);
        }
        catch (Exception ex) {
            throw new ManageInputException("非法的ID");
        }
        return stId;
    }
    /**
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * @param Object body ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
        ArrayList results = (ArrayList)resBody;
        Vector vector = new Vector();
        for(int i = 0 , j = results.size() ; i < j ; i ++){
            SysChange sysChange = (SysChange)results.get(i);
            long id = sysChange.getId();
            String data = sysChange.getData();

            HashMap tmp = new HashMap();
            tmp.put("id", String.valueOf(id));
            tmp.put("data", data);
            vector.add(tmp);
        }
        HashMap body = new HashMap();
        body.put("results",vector);
        return body;
    }
}