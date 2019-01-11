package org.radf.manage.role.actionSieaf;

import java.util.HashMap;

import org.radf.manage.role.entity.SysFunction;
import org.radf.manage.role.facade.FunctionFacade;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * ɾ��sysFunction��������sysFuncControlMap��¼
 * @author Kent Kong
 * @version 1.0
 */

public class RemoveFunctionAction extends ActionSupport{
    private String className = this.getClass().getName();


  /**
   *����FunctionFacade.removeFunction()
   */
    public EventResponse perform(Event evt){
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;
        
        try {
            //��ȡ�ӿ�ʵ����
            FunctionFacade facade = (FunctionFacade) getService("FunctionFacade");
            //��ڲ���ת��
            value = processEvent(evt);
            
            //���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.removeFunction(value);
            
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
        String functionId = (String)hashBody.get("functionId");
        return new SysFunction(functionId);
    }

    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }

}
