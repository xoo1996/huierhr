package org.radf.manage.role.actionSieaf;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.radf.manage.role.entity.SysAct;
import org.radf.manage.role.entity.SysUser;
import org.radf.manage.role.facade.AclFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 *����userID��Ӧ��Act��¼��
 */
public class FindRolesByUserAction extends ActionSupport{
    private String className = this.getClass().getName();
	/**
	 *����AclFacade.findAllActByUser()
	 */
	public EventResponse perform(Event evt) {
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;
        
        try {
            //��ȡ�ӿ�ʵ����
            AclFacade facade = (AclFacade) getService("AclFacade");
            //��ڲ���ת��
            value = processEvent(evt);
            
            //���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.findAllActByUser(value);
            
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
        String PK = TypeCast.stringToString((String) hashBody.get("userID"),"userID",false);
		return new SysUser(PK);
	}
    /**
     * ���ݴ����ResponseEnvelop.getBody()���ݣ����ɷ��ز���EventResponse.setBody����
     * @param Object body ResponseEnvelop.getBody()����
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
		Collection collection = (Collection) resBody;
		Collection list = new Vector();
		HashMap body = new HashMap();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			//SysUser sysUser = (SysUser) iter.next();

			HashMap row = new HashMap();

			//FILL MAP WITH DTO
			SysAct dto = (SysAct) iter.next();
			row.put("actID", (String) dto.getActID());
			row.put("userID", (String) dto.getUserID());
			row.put("roleID", (String) dto.getRoleID());
			list.add(row);
		}
		body.put("allRoles", list);
        return body;
	}

}
