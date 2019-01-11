/**
* <p>标题: 版本自动更新服务的底层通用服务ACTION</p>
* <p>说明: 需要自动更新表的ACTION继承本类，只需要重构三个方法即可：</p>
* <p>       String getTableName()       提供需要更新的表名，全表更新到CodeDetail时不需要</p>
* <p>       String getClassName()       提供实现类的类名</p>
* <p>       Vector getData(Iterator it) 提供获取数据返回到客户端的内容</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-8-2615:16:14</p>
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
     * 提供实现类的名称，此方法需要重构
     * @return
     */
    protected abstract String getClassName();
    
    /**
     * 提供返回客户端数据拼装后格式，此方法需要重构
     * @param it    IMP中传递过来的数据
     * @return
     */
    protected abstract Vector getData(Iterator it) throws ManageInputException;
    
    
    /**
     * 提供返回客户端删除SQL语句的拼装后格式，此方法无需重构
     * @param it    IMP中传递过来的数据
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
            // 获取接口实现类
            VersionFacade facade = (VersionFacade) getService("VersionFacade");
            // 入口参数转换
            value = processEvent(evt);

            // 调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.updateVersion(value);

            // 重组返回参数
            returnValue = processRevt(resEnv);

        } catch (ManageInputException me) {
            // 入口参数转换中出现的异常
            returnValue = ExceptionSupport(className, value, me, returnValue);
        } catch (AppException ae) {
            // 创建FacadeFactory出现的异常
            returnValue = ExceptionSupport(className, value, ae, returnValue);
        } catch (Exception ex) {
            // 其它异常
            returnValue = ExceptionSupport(className, value, ex, returnValue);
        }
        return returnValue;
    }
    /**
     * 系统入口参数封装方法 根据传入的HashMap，分解获取入口参数，并组装成所需要对象格式
     * 合法性判断主要判断长度、类型、校验等，一般通过客户端完成，本处只是防止客户端漏判，对重要字段重新校验
     * @see org.radf.plat.util.action.ActionSupport#processBody(HashMap)
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        // check param
        Vector versionList = (Vector) hashBody.get("versionList"); //获取版本信息列表
        List subList = new ArrayList();
        SysClientVersion dto = null;
        for (int i = 0; i < versionList.size(); i++) {
            HashMap row = (HashMap) versionList.get(i);
            if (row == null || row.isEmpty())
                continue;
            String tableName = TypeCast.stringToString((String) row
                    .get("tableName"), "数据表的名字", false);
            dto = new SysClientVersion();
            if(tableName.equalsIgnoreCase("ALL")){
                subList = new ArrayList();
                dto.setTableName(tableName);
                dto.setNowVersion(0);
                subList.add(dto);
                break;
            }
            
            String clientVersion = TypeCast.stringToString((String) row
                    .get("version"), "客户端数据版本号", true);
            int version = 0;
            if(clientVersion==null||clientVersion.equalsIgnoreCase("")){
                version = 0;
            }else{
                version = TypeCast.stringToInt(clientVersion, "客户端数据版本号", false);
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
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * @see org.radf.plat.util.action.ActionSupport#processMap(Object)
     * @param Objectbody ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object resBody) throws ManageInputException{
        try {
            HashMap bodyHM = (HashMap) resBody;
            HashMap hashMap = new HashMap();

            // ---------生成版本信息列表---------
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

            // -----------生成数据列表-----------
            Collection data = (Collection) bodyHM.get("data");
            if (data != null) { // clientVersion!=serverVersion
                hashMap.put("dataList", getData(data.iterator()));
            } else {
                hashMap.put("dataList", new Vector());
            }
            // -----------生成删除数据列表-----------
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
            throw new ManageInputException("返回参数数据错误");
        }
    }
}
