package org.radf.manage.role.actionSieaf;

import java.util.HashMap;

import org.radf.manage.role.entity.SysFunction;
import org.radf.manage.role.facade.FunctionFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * ��������ѯsysfunction��¼
 * @author zqb
 * @version 1.0
 */

public class FindFunctionByPKAction extends ActionSupport{
    private String className = this.getClass().getName();


  /**
   *����FunctionFacade.findFunctionByPK()
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
            ResponseEnvelop resEnv = facade.findFunctionByPK(value);
            
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
        String functionId = TypeCast.stringToString((String)hashBody.get("functionId"),"����id",false);
        return new SysFunction(functionId);
    }
    
    /**
     * ���ݴ����ResponseEnvelop.getBody()���ݣ����ɷ��ز���EventResponse.setBody����
     * @param Object body ResponseEnvelop.getBody()����
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
        SysFunction sysFunction = (SysFunction) resBody;
        
        HashMap body = new HashMap();
        body.put("functionId", sysFunction.getFunctionId());
        body.put("flag", sysFunction.getFlag());
        body.put("type", sysFunction.getType());
        body.put("transFlag", sysFunction.getTransFlag());
        body.put("functionDesc", sysFunction.getFunctionDesc());
        body.put("parentId", sysFunction.getParentId());
        body.put("signatureType",sysFunction.getSignatureType());
        body.put("title" ,sysFunction.getTitle() );
        body.put("location",sysFunction.getLocation() );
        body.put("orderno" ,TypeCast.intToString(sysFunction.getOrderno()));
        body.put("log",sysFunction.getLog() );
        body.put("owner",sysFunction.getOwner() );
        body.put("imageId",TypeCast.intToString(sysFunction.getImageID()));
        body.put("helpId",TypeCast.intToString(sysFunction.getHelpID()));
        return body;
    }
}
