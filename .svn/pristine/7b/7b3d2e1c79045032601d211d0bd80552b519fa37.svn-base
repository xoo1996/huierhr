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
public class ModifyUserAction extends ActionSupport{
    private String className = this.getClass().getName();

	public ModifyUserAction() {
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
            ResponseEnvelop resEnv = facade.modifyUser(value);
            
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
		sysUser.setUserID(TypeCast.stringToString((String) hashBody.get("userID"),"用户id",false));
		sysUser.setLoginName(TypeCast.stringToString((String) hashBody.get("loginName"),"登录名",false));
		sysUser.setOperatorName(TypeCast.stringToString((String) hashBody.get("operatorName"),"姓名",true));
        sysUser.setDeptID(TypeCast.stringToString((String) hashBody.get("DeptID"),"",true));
        sysUser.setCBM(TypeCast.stringToString((String) hashBody.get("CBM"),"",true));
        sysUser.setXQBM(TypeCast.stringToString((String) hashBody.get("XQBM"),"辖区",true));
        sysUser.setXZBM(TypeCast.stringToString((String) hashBody.get("XZBM"),"县区",true));
        sysUser.setAab034(TypeCast.stringToString((String) hashBody.get("AAB034"),"所属社保机构",true));
        sysUser.setType(TypeCast.stringToString((String) hashBody.get("TYPE"),"用户类型",true));
        sysUser.setDESCRIPTION(TypeCast.stringToString((String) hashBody.get("loginName"),"备注",true));
		if (sysUser.getLoginName().length() > 16)
			throw new ManageInputException("登录名长度必须少于16位");
		if (sysUser.getOperatorName().length() > 32)
			throw new ManageInputException("姓名长度必须少于32位");
		return sysUser;
	}
    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }
 

}
