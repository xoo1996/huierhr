package org.radf.manage.role.actionSieaf;

import java.util.HashMap;

import org.radf.manage.role.entity.SysDept;
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
 * 修改SYSDEPT记录
 */
public class ModifyDeptAction extends ActionSupport{
    private String className = this.getClass().getName();

	public ModifyDeptAction() {
	}
	/**
	 * 调用RoleFacade.modifyDept()
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
            ResponseEnvelop resEnv = facade.modifyDept(value);
            
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
		SysDept sysDept = new SysDept();
		sysDept.setDeptName(TypeCast.stringToString((String) hashBody.get("deptName"),"",false));
		sysDept.setDeptPrivilege(TypeCast.stringToString((String) hashBody.get("deptPrivilege"),"上级机构",true));
		sysDept.setComments(TypeCast.stringToString((String) hashBody.get("comments"),"机构描述",true));
		sysDept.setDeptID(TypeCast.stringToString((String) hashBody.get("deptID"),"",false));

		//validate data
		if (sysDept.getDeptName().length() > 64)
			throw new ManageInputException("deptName长度必须少于64位");
		if (sysDept.getDeptPrivilege().length() > 10)
			throw new ManageInputException("DeptPrivilege长度必须少于10位");
		if (sysDept.getComments().length() > 64)
			throw new ManageInputException("Comments长度必须少于64位");
		if (sysDept.getDeptID().length() > 16)
			throw new ManageInputException("deptID长度必须少于16位");

		return sysDept;
	}
    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }

}
