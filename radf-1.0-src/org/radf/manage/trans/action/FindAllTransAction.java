package org.radf.manage.trans.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

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
 * ��ѯ����SYSTRANSEDEF��¼
 * @author zqb
 * @version 1.0
 */

public class FindAllTransAction extends ActionSupport{
    private String className = this.getClass().getName();
  /**
   * ����TransFacade.findAllTrans()
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
            ResponseEnvelop resEnv = facade.findAllTrans(value);

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
     * ���ݴ����ResponseEnvelop.getBody()���ݣ����ɷ��ز���EventResponse.setBody����
     * 
     * @param Object
     *        body ResponseEnvelop.getBody()����
     * @return HashMap
     */
    protected HashMap processMap(Object resBody) {
        ArrayList results = (ArrayList)resBody;
        HashMap body = new HashMap();
        Vector vector = new Vector();
        for(int i = 0 , j = results.size() ; i < j ; i ++){
            SysTranseDef sysTranseDef = (SysTranseDef)results.get(i);
            String transId = sysTranseDef.getTransId();
            String transName = sysTranseDef.getTransName() == null ? "" : sysTranseDef.getTransName();
            String transDesc = sysTranseDef.getTransDesc() == null ? "" : sysTranseDef.getTransDesc();
            int timeOut = sysTranseDef.getTimeOut();
            String transType = sysTranseDef.getTransType();
            String undoTransId = sysTranseDef.getUndoTransId() == null ? "" : sysTranseDef.getUndoTransId();
            String redoTransId = sysTranseDef.getRedoTransId() == null ? "" : sysTranseDef.getRedoTransId();
            //String inputDataType = sysTranseDef.getInputDataType() == null ? "" : sysTranseDef.getInputDataType();
            //String outputDataType = sysTranseDef.getOutputDataType() == null ? "" : sysTranseDef.getOutputDataType();

            HashMap tmp = new HashMap();
            tmp.put("transId", transId);
            tmp.put("transName", transName);
            tmp.put("transDesc", transDesc);
            tmp.put("timeOut", String.valueOf(timeOut));
            tmp.put("transType", transType);
            tmp.put("undoTransId", undoTransId);
            tmp.put("redoTransId", redoTransId);
            //tmp.put("inputDataType", inputDataType);
            //tmp.put("outputDataType", outputDataType);
            vector.add(tmp);
        }
        body.put("results",vector);
        return body;
    }
    /**
     * @see ActionSupport#processBody(HashMap);
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        return null;
    }

}
