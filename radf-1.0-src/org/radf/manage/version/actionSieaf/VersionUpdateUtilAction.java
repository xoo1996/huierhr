/**
* <p>����: �汾�Զ����·���ĵײ�ͨ�÷���ACTION</p>
* <p>˵��: ��Ҫ�Զ����±��ACTION�̳б��ֻ࣬��Ҫ�ع������������ɣ�</p>
* <p>       String getTableName()       �ṩ��Ҫ���µı�����ȫ����µ�CodeDetailʱ����Ҫ</p>
* <p>       String getClassName()       �ṩʵ���������</p>
* <p>       Vector getData(Iterator it) �ṩ��ȡ���ݷ��ص��ͻ��˵�����</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-8-2615:16:14</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.actionSieaf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.radf.manage.version.entity.SysClientVersion;
import org.radf.manage.version.entity.SysRemoveVersion;
import org.radf.manage.version.facade.VersionFacade;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;

public abstract class VersionUpdateUtilAction extends ActionSupport{
    /**
     * �ṩʵ��������ƣ��˷�����Ҫ�ع�
     * @return
     */
    protected abstract String getClassName();
    
    /**
     * �ṩ���ؿͻ�������ƴװ���ʽ���˷�����Ҫ�ع�
     * @param it    IMP�д��ݹ���������
     * @return
     */
    protected abstract Vector getData(Iterator it) throws ManageInputException;
    
    
    /**
     * �ṩ���ؿͻ���ɾ��SQL����ƴװ���ʽ���˷��������ع�
     * @param it    IMP�д��ݹ���������
     * @return
     */
    private Vector getRemove(Iterator it) throws ManageInputException{
        Vector resultv = new Vector();
        while (it.hasNext()) {
            SysRemoveVersion beo = (SysRemoveVersion) it.next();
            HashMap body = new HashMap(2);
            body.put("version", TypeCast.intToString(beo.getVersion()));
            body.put("sql", TypeCast.stringToString(beo.getSql()));
            resultv.add(body);
        }
        return resultv;
    }
    /**
     * @see org.radf.plat.util.action.ActionSupport#perform(Event)
     */
    public EventResponse perform(Event evt) {
        String className = getClassName();
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;

        try {
            // ��ȡ�ӿ�ʵ����
            VersionFacade facade = (VersionFacade) getService("VersionFacade");
            // ��ڲ���ת��
            value = processEvent(evt);

            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.updateVersion(value);

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
     * @see org.radf.plat.util.action.ActionSupport#processBody(HashMap)
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        // check param
        Vector versionList = (Vector) hashBody.get("versionList"); //��ȡ�汾��Ϣ�б�
        List subList = new ArrayList();
        SysClientVersion dto = null;
        for (int i = 0; i < versionList.size(); i++) {
            HashMap row = (HashMap) versionList.get(i);
            if (row == null || row.isEmpty())
                continue;
            String tableName = TypeCast.stringToString((String) row
                    .get("tableName"), "���ݱ������", false);
            dto = new SysClientVersion();
            if(tableName.equalsIgnoreCase("ALL")){
                subList = new ArrayList();
                dto.setTableName(tableName);
                dto.setNowVersion(0);
                subList.add(dto);
                break;
            }
            
            String clientVersion = TypeCast.stringToString((String) row
                    .get("version"), "�ͻ������ݰ汾��", true);
            int version = 0;
            if(clientVersion==null||clientVersion.equalsIgnoreCase("")){
                version = 0;
            }else{
                version = TypeCast.stringToInt(clientVersion, "�ͻ������ݰ汾��", false);
            }
            dto.setTableName(tableName);
            dto.setNowVersion(version);
            subList.add(dto);
        }
        HashMap reqbody = new HashMap();
        reqbody.put("versionList", subList);
        return reqbody;
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
            HashMap hashMap = new HashMap();

            // ---------���ɰ汾��Ϣ�б�---------
            Collection versionList = (ArrayList) bodyHM.get("serverVersion");
            Iterator it = versionList.iterator();
            Vector resultv = new Vector();
            while (it.hasNext()) {
                SysClientVersion beo = (SysClientVersion) it.next();
                HashMap body = new HashMap(2);
                body.put("tableName", TypeCast.stringToString(beo
                        .getTableName()));
                body.put("Version", TypeCast
                        .intToString(beo.getUpdateVersion()));
                resultv.add(body);
            }
            hashMap.put("versionList", resultv);

            // -----------���������б�-----------
            Collection data = (Collection) bodyHM.get("data");
            if (data != null) { // clientVersion!=serverVersion
                hashMap.put("dataList", getData(data.iterator()));
            } else {
                hashMap.put("dataList", new Vector());
            }
            // -----------����ɾ�������б�-----------
            Collection remove = (Collection) bodyHM.get("remove");
            if (remove != null) { // clientVersion!=serverVersion
                hashMap.put("removeList", getRemove(remove.iterator()));
            } else {
                hashMap.put("removeList", new Vector());
            }
            
            return hashMap;
        }catch (ManageInputException me) {
            throw me;
        }catch(Exception ex){
            throw new ManageInputException("���ز������ݴ���");
        }
    }
}
