package org.radf.manage.trans.action;

import java.util.HashMap;

import org.radf.manage.trans.entity.SysTranseDef;
import org.radf.manage.trans.facade.TransFacade;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
/**
 * ��ѯSYSTRANSEDEF��¼
 * @author zqb
 * @version 1.0
 */

public class FindTransByPKAction extends ActionSupport{
    private String className = this.getClass().getName();
  /**
   * ����TransFacade.findTransByPK()
   */
    public EventResponse perform(Event evt){
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;

        try {
            // ��ȡ�ӿ�ʵ����
            TransFacade facade = (TransFacade) getService("TransFacade");
            // ��ڲ���ת��
            value = processEvent(evt);

            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.findTransByPK(value);

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
        String transId =  (String)hashBody.get("transId");
        return new SysTranseDef(transId);
    }
    /**
     * ���ݴ����ResponseEnvelop.getBody()���ݣ����ɷ��ز���EventResponse.setBody����
     * 
     * @param Object
     *        body ResponseEnvelop.getBody()����
     * @return HashMap
     */
    protected HashMap processMap(Object resBody) {
        SysTranseDef sysTranseDef = (SysTranseDef)resBody;
        String transId = sysTranseDef.getTransId();
        String transName = sysTranseDef.getTransName() == null ? "" : sysTranseDef.getTransName();
        String transDesc = sysTranseDef.getTransDesc() == null ? "" : sysTranseDef.getTransDesc();
        int timeOut = sysTranseDef.getTimeOut();
        String transType = sysTranseDef.getTransType();
        String undoTransId = sysTranseDef.getUndoTransId() == null ? "" : sysTranseDef.getUndoTransId();
        String redoTransId = sysTranseDef.getRedoTransId() == null ? "" : sysTranseDef.getRedoTransId();
        //String inputDataType = sysTranseDef.getInputDataType() == null ? "" : sysTranseDef.getInputDataType();
        //String outputDataType = sysTranseDef.getOutputDataType() == null ? "" : sysTranseDef.getOutputDataType();


        HashMap body = new HashMap();
        body.put("transId", transId);
        body.put("transName", transName);
        body.put("transDesc", transDesc);
        body.put("timeOut", String.valueOf(timeOut));
        body.put("transType", transType);
        body.put("undoTransId", undoTransId);
        body.put("redoTransId", redoTransId);
        //body.put("inputDataType", inputDataType);
        //body.put("outputDataType", outputDataType);

        return body;
    }

}
