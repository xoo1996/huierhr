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
 * 修改SYSUSER记录
 */
public class ChangePWDAction extends ActionSupport {
    private String className = this.getClass().getName();
	public ChangePWDAction() {
	}
	/**
	 * 调用RoleFacade.modifyUser()
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
			ResponseEnvelop resEnv = facade.changePWD(value);
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
        //新的密码占用deptID字段存放
		SysUser sysUser = new SysUser();
		sysUser.setLoginName(TypeCast.stringToString((String) hashBody.get("loginName"),"登录名",false));
		sysUser.setPassWD(TypeCast.stringToString((String) hashBody.get("oldPWD"),"原密码",true));
		sysUser.setDeptID(TypeCast.stringToString((String) hashBody.get("newPWD"),"新密码",true));
        
		//validate data
		if (sysUser.getLoginName().length() > 16)
			throw new ManageInputException("LoginName长度必须少于16位");
		if (sysUser.getPassWD()!=null&&sysUser.getPassWD().length() > 16)
			throw new ManageInputException("PassWD长度必须少于16位");
		if (sysUser.getDeptID()!=null&&sysUser.getDeptID().length() > 16)
			throw new ManageInputException("PassWD长度必须少于16位");

		return sysUser;
	}
    
    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }

}
