package org.radf.plat.util.exception;
/**
 * 数据库连接抛出的异常
 * @author zqb
 * @version 1.0
 */
public class NoConnectionException extends Exception {
    private int errCode = 0;
    
    public NoConnectionException(){
        
    }
    public NoConnectionException(String msg){
        super(msg);
    }
    public NoConnectionException(int errCode,String msg){
        super(msg);
        this.errCode = errCode;
    }
    /**
     * @return Returns the errCode.
     */
    public int getErrCode() {
        return errCode;
    }
    /**
     * @param errCode The errCode to set.
     */
    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }    
}
