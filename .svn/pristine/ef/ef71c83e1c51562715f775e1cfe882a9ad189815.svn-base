package org.radf.plat.util.exception;

/**
 * <p>Title: WebException</p>
 * <p>Description: 所有在框架端异常类的父类</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co.,Ltd</p>
 * <p>Company: LBS</p>
 * @author LBS Architect Team
 * @version 0.1
 */

public class WebException extends Exception {

    Throwable myException;
    private int errorCode;
    protected String Msg="";//新加的属性

    public WebException() {
    }

    public WebException(String s ){
        super(s);
    }

    public WebException(Exception ex){
        super(ex.toString());
    }

    public WebException(String s, Throwable ex){
        super(s);
        this.myException = ex;
    }

    public void printStackTrace(){
        System.out.println ("WebException:"+this.toString());
        if(myException != null)
            myException.printStackTrace();
    }

    public WebException(int errorCode,String msg)
    {
      super(msg);
      this.errorCode = errorCode;
    }

    public WebException(int errorCode,String msg,Throwable e)
    {
      super(msg);
      this.errorCode = errorCode;
      this.myException = e;
    }

    public int getErrorCode()
    {
      return this.errorCode;
    }
    //新加的重载父类的方法

    public String getMessage(){
      if(myException != null){
        Msg=super.getMessage()+myException.getMessage();
      }
      else{
        Msg=super.getMessage();
      }
      return Msg;
    }

}
