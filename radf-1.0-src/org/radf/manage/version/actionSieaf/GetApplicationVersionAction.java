/**
* <p>����:GetApplicationVersion </p>
* <p>˵��: </p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-11-1711:08:26</p>
*
* @author ¥�ͱ�
* @version 1.0
*/
package org.radf.manage.version.actionSieaf;

import java.util.HashMap;

import org.radf.manage.version.facade.VersionFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;

/**
 * @author ¥�ͱ�
 *
 * TODO ������ѯ��ǰ�ͻ��˳���汾GetApplicationVersion��Ӧ��org.radf.manage.version.actionsieaf
 */
public class GetApplicationVersionAction extends ActionSupport {
    private String className = this.getClass().getName();
    /**
     * @see AddRetiredAction#perform(Event)
     * @param Event evt
     * @return EventResponse
     */
    public EventResponse perform(Event evt) {
        EventResponse returnValue = new EventResponse();
        int exceptionLogCode = 0;
        String detailCusString = null;
        String LogString = null;
        RequestEnvelop value = null;
        
        try {
            //����factory
            VersionFacade facade = 
                (VersionFacade) getService("VersionFacade");
            
            //��ڲ���ת��
            value = processEvent(evt);
            
            //���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.getApplicationVersion(value);
            
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
     * ϵͳ��ڲ�����װ���� ���ݴ����HashMap���ֽ��ȡ��ڲ���������װ������Ҫ�����ʽ
     * �Ϸ����ж���Ҫ�жϳ��ȡ����͡�У��ȣ�һ��ͨ���ͻ�����ɣ�����ֻ�Ƿ�ֹ�ͻ���©�У�����Ҫ�ֶ�����У��
     * @see org.radf.plat.util.action.ActionSupport#processBody(HashMap)
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        String appName=TypeCast.stringToString((String)hashBody.get("appName"), "�ͻ��˳�������", false);
        HashMap returnMap=new HashMap();
        returnMap.put("appName",appName);
        return returnMap;
    }
    
    /**
     * ���ݴ����ResponseEnvelop.getBody()���ݣ����ɷ��ز���EventResponse.setBody����
     * @see org.radf.plat.util.action.ActionSupport#processMap(Object)
     * @param Objectbody ResponseEnvelop.getBody()����
     * @return HashMap
     */
    protected HashMap processMap(Object resBody) throws ManageInputException{
        try {
            HashMap bodyHM = (HashMap) resBody;
            Integer appVersion=(Integer)bodyHM.get("appVersion");
            HashMap hashMap = new HashMap();
            hashMap.put("appVersion",TypeCast.intToString(appVersion.intValue()));
            return hashMap;
        }
        catch(Exception ex){
            throw new ManageInputException("���ز������ݴ���");
        }
    }    
}
