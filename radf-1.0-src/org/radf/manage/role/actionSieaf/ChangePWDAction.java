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
 * �޸�SYSUSER��¼
 */
public class ChangePWDAction extends ActionSupport {
    private String className = this.getClass().getName();
	public ChangePWDAction() {
	}
	/**
	 * ����RoleFacade.modifyUser()
	 */
	public EventResponse perform(Event evt) {
		EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;

		try {
            //��ȡ�ӿ�ʵ����
            RoleFacade facade = (RoleFacade) getService("RoleFacade");
            //��ڲ���ת��
            value = processEvent(evt);
            //���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.changePWD(value);
            //���鷵�ز���
            returnValue = processRevt(resEnv);
        } catch (ManageInputException me) {
            //��ڲ���ת���г��ֵ��쳣
            returnValue = ExceptionSupport(className,value,me,returnValue);
        } catch (AppException ae) {
            //����FacadeFactory���ֵ��쳣
            returnValue = ExceptionSupport(className,value,ae,returnValue);
        } catch (Exception ex) {
            //�����쳣
            returnValue = ExceptionSupport(className,value,ex,returnValue);
        }
 		return returnValue;
	}
    /**
     * ϵͳ��ڲ�����װ����
     * ���ݴ����HashMap���ֽ��ȡ��ڲ���������װ������Ҫ�����ʽ
     * �Ϸ����ж���Ҫ�жϳ��ȡ����͡�У��ȣ�һ��ͨ���ͻ�����ɣ�����ֻ�Ƿ�ֹ�ͻ���©�У�����Ҫ�ֶ�����У��
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody)throws ManageInputException{
        //�µ�����ռ��deptID�ֶδ��
		SysUser sysUser = new SysUser();
		sysUser.setLoginName(TypeCast.stringToString((String) hashBody.get("loginName"),"��¼��",false));
		sysUser.setPassWD(TypeCast.stringToString((String) hashBody.get("oldPWD"),"ԭ����",true));
		sysUser.setDeptID(TypeCast.stringToString((String) hashBody.get("newPWD"),"������",true));
        
		//validate data
		if (sysUser.getLoginName().length() > 16)
			throw new ManageInputException("LoginName���ȱ�������16λ");
		if (sysUser.getPassWD()!=null&&sysUser.getPassWD().length() > 16)
			throw new ManageInputException("PassWD���ȱ�������16λ");
		if (sysUser.getDeptID()!=null&&sysUser.getDeptID().length() > 16)
			throw new ManageInputException("PassWD���ȱ�������16λ");

		return sysUser;
	}
    
    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }

}
