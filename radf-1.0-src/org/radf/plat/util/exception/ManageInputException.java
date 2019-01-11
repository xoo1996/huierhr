package org.radf.plat.util.exception;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Bea</p>
 * @author Kent Kong
 * @version 1.0
 */

public class ManageInputException extends AppException {

    public ManageInputException() {
        super();
    }

    public ManageInputException(String msg){
       super(msg);
    }
    
    public ManageInputException(Exception ex){
        super(ex.toString());
    }

    public ManageInputException(String s, Throwable ex){
        super(s,ex);
    }


    public ManageInputException(int errorCode,String msg)
    {
      super(errorCode,msg);
    }

    public ManageInputException(int errorCode,String msg,Throwable e)
    {
      super(errorCode,msg,e);
    }
}
