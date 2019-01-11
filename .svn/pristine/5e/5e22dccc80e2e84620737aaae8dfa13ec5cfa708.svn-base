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
 * 用DEPT_ID查询所有满足条件的SYSUSER记录
 */
public class FindUsersByDeptIDAction extends ActionSupport{
    private String className = this.getClass().getName();
       /**
        * 调用RoleFacade.findAllMembers()
        */
	public EventResponse perform(Event evt) {
        EventResponse returnValue = new EventResponse();
        RequestEnvelop value = null;
        
        try {
            //获取接口实现类
            RoleFacade facade = (RoleFacade) getService("RoleFacade");
            //入口参数转换
            value = processEvent(evt);
            
            //调用对应的Facade业务处理方法
            ResponseEnvelop resEnv = facade.findAllMembers(value);
            
            //重组返回参数
            returnValue = processRevt(resEnv);
            
        } catch (ManageInputException me) {
            //入口参数转换中出现的异常
            returnValue = ExceptionSupport(className,value,me,returnValue);
        } catch (AppException ae) {
            //创建FacadeFactory出现的异常
            returnValue = ExceptionSupport(className,value,ae,returnValue);
        } catch (Exception ex) {
            //其它异常
            returnValue = ExceptionSupport(className,value,ex,returnValue);
        }
        
		return returnValue;

	}
    /**
     * 系统入口参数封装方法
     * 根据传入的HashMap，分解获取入口参数，并组装成所需要对象格式
     * 合法性判断主要判断长度、类型、校验等，一般通过客户端完成，本处只是防止客户端漏判，对重要字段重新校验
     * @param hashBody
     * @return Object
     * @throws ManageInputException
     */
    protected Object processBody(HashMap hashBody)throws ManageInputException{
		//String PK = (String) hashBody.get("deptID");
        String PK = (String) hashBody.get("roleID");
        
		//check param
		if (PK.length() > 16)
			throw new ManageInputException("deptID长度必须少于16位");
		return PK;
	}

    /**
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * @param Object body ResponseEnvelop.getBody()部分
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
            row.put("AAB034",sysUser.getAab034());//所属社保机构
            

            list.add(row);
        }
        //map.put("allDeptMembers", list);
        map.put("allRoles", list);
       return map;
    }
}
