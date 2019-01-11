package org.radf.manage.role.actionSieaf;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.radf.manage.role.entity.SysUser;
import org.radf.manage.role.facade.RoleFacade;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * ��DEPT_ID��ѯ��������������SYSUSER��¼
 */
public class FindUsersByDeptIDAction extends ActionSupport{
    private String className = this.getClass().getName();
       /**
        * ����RoleFacade.findAllMembers()
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
            ResponseEnvelop resEnv = facade.findAllMembers(value);
            
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
		//String PK = (String) hashBody.get("deptID");
        String PK = (String) hashBody.get("roleID");
        
		//check param
		if (PK.length() > 16)
			throw new ManageInputException("deptID���ȱ�������16λ");
		return PK;
	}

    /**
     * ���ݴ����ResponseEnvelop.getBody()���ݣ����ɷ��ز���EventResponse.setBody����
     * @param Object body ResponseEnvelop.getBody()����
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
        HashMap map = new HashMap();
        Collection collection = (Collection) resBody;
        Collection list = new Vector();
        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            SysUser sysUser = (SysUser) iter.next();
            HashMap row = new HashMap();

            String userID = sysUser.getUserID();
            String passWD = sysUser.getPassWD();
            String loginName = sysUser.getLoginName();
            String operatorName = sysUser.getOperatorName();
            String deptID = sysUser.getDeptID();
            String XQBM = sysUser.getXQBM();
            String XZBM = sysUser.getXZBM();
            String CBM = sysUser.getCBM();
            String DEPTID = sysUser.getDeptID();

            row.put("userID",userID);
//            row.put("passWD        ", passWD);
            row.put("loginName",loginName);
            row.put("operatorName",operatorName);
            row.put("deptID",deptID);
            row.put("XQBM",XQBM);
            row.put("XZBM",XZBM);
            row.put("TYPE",sysUser.getType());
            row.put("CBM" ,CBM);
            row.put("itemID" ,userID);
            row.put("roleDesc" ,loginName);
            row.put("roleID" ,userID);
            row.put("AAB034",sysUser.getAab034());//�����籣����
            

            list.add(row);
        }
        //map.put("allDeptMembers", list);
        map.put("allRoles", list);
       return map;
    }
}
