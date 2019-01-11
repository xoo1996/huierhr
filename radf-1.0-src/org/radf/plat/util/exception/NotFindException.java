package org.radf.plat.util.exception;

import org.radf.plat.util.global.GlobalErrorCode;
/**
 * <p>Title: rs为空时抛出的异常类</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author zqb
 * @version 1.0
 */

public class NotFindException extends AppException {
  //int errorcode=GlobalErrorCode.NOTFINDEXCEPTIONCODE;
  public NotFindException(String msg)
  { super(GlobalErrorCode.NOTFINDEXCEPTIONCODE,msg);


  }

  public NotFindException(String msg,Throwable myException)
  {
    super(GlobalErrorCode.NOTFINDEXCEPTIONCODE,msg,myException);
  }

}
