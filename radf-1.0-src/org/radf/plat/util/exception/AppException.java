package org.radf.plat.util.exception;

/**
* <p>标题: AppException</p>
* <p>说明: 所有在框架端异常类的父类：</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-9-1 13:30:54</p>
*
* @author zqb
* @version 1.0
*/

public class AppException extends Exception {

    protected Throwable myException;

    protected int errorCode = 0;

    protected String Msg = "";// 新加的属性

    public AppException() {
    }

    public AppException(String s) {
        super(s);
        this.errorCode = 100000000;
    }

    public AppException(Exception ex) {
        super(ex.toString());
    }

    public AppException(int errorCode, Exception ex) {
        super(ex.toString());
        this.errorCode = errorCode;
    }

    public AppException(String s, Throwable ex) {
        super(s);
        this.myException = ex;
    }

    public AppException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public AppException(int errorCode, String msg, Throwable e) {
        super(msg);
        if(errorCode==0){
            this.errorCode = 100000000;
        }else{
            this.errorCode = errorCode;
        }
        this.myException = e;
    }
    
    public void setErrorCode(int code){
        this.errorCode = code;
    }
    public int getErrorCode() {
        return this.errorCode;
    }

    // 新加的重载父类的方法

    public String getMessage() {
        if (myException != null) {
            String s = super.getMessage().trim();
            if(super.getMessage().endsWith("|")){
                Msg = s.substring(0,s.length()-1) + "：" + myException.getMessage()+"|";
            }else{
                Msg = s + "：" + myException.getMessage();
            }
        } else {
            Msg = super.getMessage();
        }
        return Msg;
    }

    public void printStackTrace() {
        System.out.println("AppException:" + this.toString());
        try {
            if (myException != null)
                myException.printStackTrace();
        } catch (Exception ex) {

        }
    }
}