package org.radf.manage.role.actionSieaf;

import java.util.HashMap;

import org.radf.manage.role.entity.SysRole;
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
 * 生成一条SYSROLE记录
 */
public class CreateRoleAction extends ActionSupport {
    private String className = this.getClass().getName();

	public CreateRoleAction() {
	}
	/**
	 *调用RoleManagerFacade.createRole()
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
            ResponseEnvelop resEnv = facade.createRole(value);
            
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
		SysRole sysRole = new SysRole();
		sysRole.setRoleDesc(TypeCast.stringToString((String) hashBody.get("roleDesc")));

		//validate data
		if (sysRole.getRoleDesc().length() > 30)
			throw new ManageInputException("RoleDesc长度必须少于30位");

		return sysRole;
	}
    /**
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * @param Object body ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object body){
        HashMap map = new HashMap();
        map.put("roleID", body);
       return map;
    }
}
