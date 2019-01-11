/**
* <p>标题: SIEAF模式ActionSupport的基础框架</p>
* <p>说明: 主要处理Action中的流程以及异常的处理</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-1611:08:40</p>
*
* @author Administrator
* @version 1.0
*/
package org.radf.plat.util.action;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.FacadeFactory;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ExceptionUtil;
import org.radf.plat.util.exception.ManageInputException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.SessionNames;

public abstract class ActionSupport implements Action{
    protected ServletContext ctx;
    protected HttpSession session;
    
    public void init(ServletContext context, HttpSession session) {
        this.ctx = context;
        this.session = session;
    }
    
    public Object getService(String facade) throws AppException{
        //FacadeFactory ff = new FacadeFactory();
        return FacadeFactory.getService(facade);
    }
    /**
     * 主要业务处理过程，系统会自动执行此方法
     * @param event
     * @return
     */
    public abstract EventResponse perform(Event evt);

    /**
     * 系统入口参数转换，异常时抛出ManageInputException由上层处理
     * 此方法为基础模型，一般不需要overrides，
     * 但内部调用的processBody方法需要根据是否传入参数在上层overrides
     * @param Event
     * @return RequestEnvelop
     * @exception ManageInputException
     */
    protected  RequestEnvelop processEvent(Event evt)throws ManageInputException{
        RequestEnvelop returnValue = new RequestEnvelop();
        
        //--------生成RequestEnvelopHead----------
        RequestEnvelopHead requestHead = null;
        if (session != null){
            requestHead = (RequestEnvelopHead)session.getAttribute(SessionNames.HEAD);
            requestHead.setSessionID(session.getId());
        }else{
            requestHead = new RequestEnvelopHead();
        }
        requestHead.setLoginName(evt.getLoginName());
        requestHead.setFunctionID(evt.getFunctionID());


        returnValue.setHead(requestHead);
        
        //----------------生成body----------------
        try{
            returnValue.setBody(processBody(evt.getBody()));
        }catch(ManageInputException me){
            throw me;
        }catch(Exception ex){
            throw new ManageInputException(GlobalErrorCode.INPUTPARAMTYPEERRORCODE,"入口参数组装异常",ex);
        }
        
        return returnValue;
    }
    /**
     * 系统出口参数转换，异常时抛出ManageInputException由上层处理
     * 此方法为基础模型，一般不需要overrides，
     * 但内部调用的processMap方法需要根据是否返回参数在上层overrides
     * 本层处理调用IMP时产生的异常的处理
     * @param ResponseEnvelop
     * @return EventResponse
     * @exception ManageInputException
     */
    protected EventResponse processRevt(ResponseEnvelop resEnv)throws ManageInputException{
        //返回结果判断，并生成返回参数
        EventResponse retValue = new EventResponse();
        if (resEnv==null){
            //成功
            retValue.setSucessFlag(true);
            //返回的retValue.setBody
            retValue.setBody(null);
        }else if (resEnv.getHead() != null){
            if(resEnv.getHead().getCode()!=0) {
                //失败
                retValue.setSucessFlag(false);
                retValue.setMsg(resEnv.getHead().getMessage());
                //保存错误日志
                ExceptionUtil.saveLog( (RequestEnvelopHead)null, "ActionSupport",resEnv.getHead().getCode(),resEnv.getHead().getMessage());
            }else{
                //成功
                retValue.setSucessFlag(true);
                retValue.setMsg(resEnv.getHead().getMessage());
                retValue.setBody(processMap(resEnv.getBody()));
            }
        } else { // resEnv.getHead=null
            // resEnv.getHead=null
            retValue.setSucessFlag(true);
            Object obj = resEnv.getBody();
            // if(obj != null){
            // retValue.setBody(processMap(obj));
            // }
            try{
                retValue.setBody(processMap(obj));
            }catch(ManageInputException me){
                retValue.setSucessFlag(false);
                retValue.setMsg(me.getMessage());
            }catch(Exception e){
                retValue.setSucessFlag(false);
                retValue.setMsg(GlobalErrorMsg.SYS_DATE_NOTFOUNT);
                //retValue.setBody(null);
            }
        }
        return retValue;
    }

  /**
   * 系统入口参数RequestEnvelop.body方法
   * 根据传入的HashMap，分解获取入口参数，并组装成所需要对象格式，
   * 供RequestEnvelop.body.setBody()使用
   * 如果外部交易入口没有参数，可以不overrides
   * @param hashBody
   * @return Object
   * @throws ManageInputException
   */
    protected  abstract Object processBody(HashMap hashBody)throws ManageInputException;
//  protected Object processBody(HashMap hashBody)throws ManageInputException{
//      throw new ManageInputException("没有具体的实现类，请与开发人员联系");
//  }
  /**
   * 系统出口参数ResponseEnvelop.body方法
   * 根据传入的ResponseEnvelop.getBody()内容，
   * 生成返回参数EventResponse.setBody内容
   * 如果外部交易出口没有返回数据，可以不overrides
   * @param body ResponseEnvelop.getBody()部分
   * @return HashMap
   */
    protected abstract HashMap processMap(Object body)throws ManageInputException;
//    protected HashMap processMap(Object body)throws ManageInputException{
//        throw new ManageInputException("没有具体的实现类，请与开发人员联系");
//    }
  
  /**
   * 异常处理
   * @see org.radf.plat.util.exception.ExceptionUtil#ExceptionSupport(String,RequestEnvelop,ManageInputException,EventResponse)
   * @param className
   * @param value
   * @param thw
   * @param returnValue
   * @return
   */
  public EventResponse ExceptionSupport(String className,RequestEnvelop value,ManageInputException me,EventResponse returnValue){
      return ExceptionUtil.ExceptionSupport(className,value,me,returnValue);
  }
  /**
   * 异常处理
   * @see org.radf.plat.util.exception.ExceptionUtil#ExceptionSupport(String,RequestEnvelop,AppException,EventResponse)
   * @param className
   * @param value
   * @param thw
   * @param returnValue
   * @return
   */
  public EventResponse ExceptionSupport(String className,RequestEnvelop value,AppException ae,EventResponse returnValue){
      return ExceptionUtil.ExceptionSupport(className,value,ae,returnValue);
  }
  /**
   * 异常处理
   * @see org.radf.plat.util.exception.ExceptionUtil#ExceptionSupport(String,RequestEnvelop,Exception,EventResponse)
   * @param className
   * @param value
   * @param thw
   * @param returnValue
   * @return
   */
  public EventResponse ExceptionSupport(String className,RequestEnvelop value,Exception ex,EventResponse returnValue){
      return ExceptionUtil.ExceptionSupport(className,value,ex,returnValue);
  }
}
