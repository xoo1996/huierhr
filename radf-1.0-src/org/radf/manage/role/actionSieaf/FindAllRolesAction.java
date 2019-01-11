package org.radf.manage.role.actionSieaf;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

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
 * ��������SYSROLE��¼
 */
public class FindAllRolesAction extends ActionSupport{
    private String className = this.getClass().getName();
	/**
	 *����AclFacade.findAllRoles()
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
            ResponseEnvelop resEnv = facade.findAllRoles(value);
            
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
     * ���ݴ����ResponseEnvelop.getBody()���ݣ����ɷ��ز���EventResponse.setBody����
     * @param Object body ResponseEnvelop.getBody()����
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
		Collection collection = (Collection) resBody;
		Collection list = new Vector();
		HashMap body = new HashMap();
		for (Iterator iter = collection.iterator(); iter.hasNext();) {
			SysRole sysRole = (SysRole) iter.next();

			HashMap row = new HashMap();

			//FILL MAP WITH DTO
			row.put("roleID"     ,sysRole.getRoleID());
			row.put("roleDesc"   ,sysRole.getRoleDesc());
            row.put("parentID"   ,sysRole.getParentID());
            row.put("createUser" ,sysRole.getCreateUserID());
			list.add(row);
		}
		body.put("allRoles", list);
        return body;
	}
    /**
     * @see ActionSupport#processBody(HashMap);
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        return null;
    }

}
