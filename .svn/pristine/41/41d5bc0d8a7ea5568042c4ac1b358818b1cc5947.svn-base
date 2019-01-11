package org.radf.manage.logMessage.actionSieaf;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

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
 * <p>Title: 根据日志信息内容模糊查询日志</p>
 * <p>Description:Action </p>
 * @author zqb
 * @version 1.0
 */

public class FindByMessageAction extends ActionSupport{
    private String className = this.getClass().getName();
    /**
     * 调用LogFacade.findByMessage
     */
  public EventResponse perform(Event evt){
      EventResponse returnValue = new EventResponse();
      RequestEnvelop value = null;
      
      try {
          //获取接口实现类
          LogFacade facade = (LogFacade) getService("LogFacade");
          //入口参数转换
          value = processEvent(evt);
          
          //调用对应的Facade业务处理方法
          ResponseEnvelop resEnv = facade.findByMessage(value);
          
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
      //check param
      String message=TypeCast.stringToString(
          (String)hashBody.get("message"),"日志信息",false);

      //分页标记
      String count=TypeCast.intToString(
          TypeCast.stringToInt((String)hashBody.get("count"),"个数",false));
      String offset=TypeCast.intToString(
          TypeCast.stringToInt((String)hashBody.get("offset"),"偏移量",false));

      HashMap reqbody = new HashMap();
      reqbody.put("message",message);
      reqbody.put("count",count);
      reqbody.put("offset",offset);

      return reqbody;
  }

  protected HashMap processMap(Object resBody){
      HashMap bodyHM = (HashMap)resBody;
      Collection collect=(Collection)bodyHM.get("collection");

      Vector resultv = new Vector();
      Iterator it = collect.iterator();
      while(it.hasNext()){
        LogMessage logmessage=(LogMessage)it.next();

        HashMap body = new HashMap(8);

        body.put("id", logmessage.getId());
        body.put("code", TypeCast.intToString(logmessage.getCode()));
        body.put("message", logmessage.getMessage());
        body.put("msgdate", logmessage.getMsgdate());
        body.put("sessionid", logmessage.getSessionid());
        body.put("userid", logmessage.getLoginName());
        body.put("functionid", logmessage.getFunctionid());
        body.put("ip",logmessage.getIp());
        resultv.add(body);
      }
      HashMap map = new HashMap();
      map.put("resultset",resultv);
    return map;
  }
}
