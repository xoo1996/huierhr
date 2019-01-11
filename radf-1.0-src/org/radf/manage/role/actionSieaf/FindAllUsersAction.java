package org.radf.manage.role.actionSieaf;

import java.util.Collection;
import java.util.HashMap;
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
 *查找所有的SYSUSER记录和相关单位名称
 */
public class FindAllUsersAction extends ActionSupport{
    private String className = this.getClass().getName();
	/**
	 *调用AclFacade.findAllUsers()
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
            ResponseEnvelop resEnv = facade.findAllUsers(value);
            
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
     * 根据传入的ResponseEnvelop.getBody()内容，生成返回参数EventResponse.setBody内容
     * @param Object body ResponseEnvelop.getBody()部分
     * @return HashMap
     */
    protected HashMap processMap(Object resBody){
        
      
        Collection list = new Vector();
        HashMap body = new HashMap();
        
        Vector vct1= (Vector)resBody;
       
        String userID =null;
        String loginName =null;
        String operatorName =null;
        String org=null;
        String name =null;
        String aab034 = null;
        
        if (vct1 !=null){
            for(int i=0;i<vct1.size();i++){
                HashMap row = new HashMap();
                SysUser sysUser =(SysUser)vct1.get(i);
                if(sysUser!=null){
                 userID = sysUser.getUserID();
                 loginName =sysUser.getLoginName();
                 operatorName = sysUser.getOperatorName();
                 org =sysUser.getDeptID();
                 aab034 = sysUser.getAab034();
                }
                row.put("userID", userID);//用户ID
                row.put("loginName", loginName);//登陆姓名
                row.put("operatorName", operatorName);//用户姓名
                row.put("org", org);//单位编号
                row.put("AAB034",aab034);//所属社保机构
                row.put("TYPE",sysUser.getType());
                list.add(row);
            }
        }
		body.put("allUsers", list);
        return body;
	}
    /**
     * @see ActionSupport#processBody(HashMap);
     */
    protected Object processBody(HashMap hashBody) throws ManageInputException {
        return null;
    }

}
