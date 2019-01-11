package org.radf.plat.util.exception;

/**
 * <p>Title: WebException</p>
 * <p>Description: �����ڿ�ܶ��쳣��ĸ���</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co.,Ltd</p>
 * <p>Company: LBS</p>
 * @author LBS Architect Team
 * @version 0.1
 */

public class WebException extends Exception {

    Throwable myException;
    private int errorCode;
    protected String Msg="";//�¼ӵ�����

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
    //�¼ӵ����ظ���ķ���

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
