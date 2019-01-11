package org.radf.manage.logMessage.actionSieaf;

import java.util.HashMap;

import org.radf.manage.logMessage.entity.LogMessage;
import org.radf.manage.logMessage.facade.LogFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;

/**
 * <p>Title: ��־����</p>
 * <p>Description:EjbAction </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: LBS</p>
 * @author <a href=mailto:qin_yalan@sina.com>Qin Yalan</a>
 * @version 1.0
 */

public class RemoveLogMessageAction extends ActionSupport {
    private String className = this.getClass().getName();

    /**
     * ����LogFacade.removeLogMessage
     * @param evt
     * @return
     */
    public EventResponse perform(Event evt) {
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;

        try {
            // ��ȡ�ӿ�ʵ����
            LogFacade facade = (LogFacade) getService("LogFacade");
            // ��ڲ���ת��
            value = processEvent(evt);

            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.removeLogMessage(value);

            // ���鷵�ز���
            returnValue = processRevt(resEnv);

        } catch (ManageInputException me) {
            // ��ڲ���ת���г��ֵ��쳣
            returnValue = ExceptionSupport(className, value, me, returnValue);
        } catch (AppException ae) {
            // ����FacadeFactory���ֵ��쳣
            returnValue = ExceptionSupport(className, value, ae, returnValue);
        } catch (Exception ex) {
            // �����쳣
            returnValue = ExceptionSupport(className, value, ex, returnValue);
        }
        return returnValue;
    }

    /**
     * ϵͳ��ڲ�����װ���� ���ݴ����HashMap���ֽ��ȡ��ڲ���������װ������Ҫ�����ʽ
     * �Ϸ����ж���Ҫ�жϳ��ȡ����͡�У��ȣ�һ��ͨ���ͻ�����ɣ�����ֻ�Ƿ�ֹ�ͻ���©�У�����Ҫ�ֶ�����У��
     * 
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        // check param
        String id = TypeCast.stringToString((String) hashBody.get("id"),
                "��־ID", false);

        HashMap reqbody = new HashMap();
        reqbody.put("dto", new LogMessage(id));

        return reqbody;
    }
    /**
     * @see ActionSupport#processMap(Object);
     */
    protected HashMap processMap(Object body)throws ManageInputException{
        return null;
    }

}
