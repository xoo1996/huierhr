/**
* <p>标题:GetApplicationVersion </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-11-1711:08:26</p>
*
* @author 楼贤斌
* @version 1.0
*/
package org.radf.manage.version.actionSieaf;

import java.util.HashMap;

import org.radf.manage.version.facade.VersionFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;

/**
 * @author 楼贤斌
 *
 * TODO 用来查询当前客户端程序版本GetApplicationVersion对应的org.radf.manage.version.actionsieaf
 */
public class GetApplicationVersionAction extends ActionSupport {
    private String className = this.getClass().getName();
    /**
     * @see AddRetiredAction#perform(Event)
     * @param Event evt
     * @return EventResponse
     */
    public EventResponse perform(Event evt) {
        EventResponse returnValue = new EventResponse();
        int exceptionLogCode = 0;
        String detailCusString = null;
        String LogString = null;
        RequestEnvelop value = null;
        
        try {
            //创建factory
            VersionFacade facade = 
                (VersionFacade) getService("VersionFacade");
            
            //入口参数转换
            value = processEvent(evt);
            
            //调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.getApplicationVersion(value);
            
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
     * 系统入口参数封装方法 根据传入的HashMap，分解获取入口参数，并组装成所需要对象格式
     * 合法性判断主要判断长度、类型、校验等，一般通过客户端完成，本处只是防止客户端漏判，对重要字段重新校验
     * @see org.radf.plat.util.action.ActionSupport#processBody(HashMap)
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        String appName=TypeCast.stringToString((String)hashBody.get("appName"), "客户端程序名字", false);
        HashMap returnMap=new HashMap();
        returnMap.put("appName",appName);
        return returnMap;
    }
    
    /**
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * @see org.radf.plat.util.action.ActionSupport#processMap(Object)
     * @param Objectbody ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object resBody) throws ManageInputException{
        try {
            HashMap bodyHM = (HashMap) resBody;
            Integer appVersion=(Integer)bodyHM.get("appVersion");
            HashMap hashMap = new HashMap();
            hashMap.put("appVersion",TypeCast.intToString(appVersion.intValue()));
            return hashMap;
        }
        catch(Exception ex){
            throw new ManageInputException("返回参数数据错误");
        }
    }    
}
