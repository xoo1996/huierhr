package org.radf.manage.role.actionSieaf;

import java.util.HashMap;

import org.radf.manage.role.entity.SysRole;
import org.radf.manage.role.facade.RoleFacade;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * ��������ѯ��Ӧ��SYSROLE��¼
 */
public class FindRoleByPKAction extends ActionSupport{
    private String className = this.getClass().getName();
       /**
        * ����RoleFacade.findRoleByPK()
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
            ResponseEnvelop resEnv = facade.findRoleByPK(value);
            
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

		String PK = (String) hashBody.get("roleID");
		return new SysRole(PK);
	}

    /**
     * ���ݴ����ResponseEnvelop.getBody()���ݣ����ɷ��ز���EventResponse.setBody����
     * @param Object body ResponseEnvelop.getBody()����
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
        SysRole sysRole = (SysRole) resBody;
        HashMap body = new HashMap();

        String roleID=sysRole.getRoleID();
        String roleDesc=sysRole.getRoleDesc();

        body.put("roleID         ",roleID       );
        body.put("roleDesc       ",roleDesc     );

       return body;
    }
}
