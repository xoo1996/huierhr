/**
* <p>标题: 系统管理操作员处理</p>
* <p>说明: ACTION</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-1611:10:49</p>
*
* @author Administrator
* @version 1.0
*/
package org.radf.manage.role.actionSieaf;

import java.util.HashMap;

import org.radf.manage.role.entity.SysUser;
import org.radf.manage.role.facade.RoleFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
* 生成一条操作员SysUser记录
* @author zqb
* @version 1.0
 */
public class CreateUserAction extends ActionSupport{
    private String className = this.getClass().getName();
    public CreateUserAction() {
    }
    /**
     *调用RoleFacade.createUser()
     */
    public EventResponse perform(Event evt) {
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;
        
        try {
            //获取接口实现类
            RoleFacade facade = (RoleFacade) getService("RoleFacade");
            //入口参数转换
            value = processEvent(evt);
            
            //调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.createUser(value);
            
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
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(TypeCast.stringToString((String) hashBody.get("loginName"),"登录名",false));
        sysUser.setOperatorName(TypeCast.stringToString((String) hashBody.get("operatorName")));
        sysUser.setXQBM((String)hashBody.get("XQBM"));
        sysUser.setXZBM((String)hashBody.get("XZBM"));
        sysUser.setCBM((String)hashBody.get("CBM"));
        sysUser.setDeptID((String)hashBody.get("DEPTID"));
        sysUser.setType((String)hashBody.get("TYPE"));
        sysUser.setAab034((String)hashBody.get("AAB034"));
        //数据校验
        if (sysUser.getLoginName().length() > 16)
            throw new ManageInputException("登录名长度必须少于16位");
        if (sysUser.getOperatorName().length() > 32)
            throw new ManageInputException("姓名长度必须少于32位");
        return sysUser;
    }
    
    /**
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * @param Object body ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
        HashMap body = new HashMap();
        body.put("userID", resBody);
       return body;
    }
}
