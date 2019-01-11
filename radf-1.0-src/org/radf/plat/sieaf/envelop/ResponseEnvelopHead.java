package org.radf.plat.sieaf.envelop;

import java.io.Serializable;

/**
 * <p>Title: ResponseEnvelopHead</p>
 * <p>Description: This class encapsulate the information of return parater's head info of
 * EJB facade's method invocation</p>
 * <p>Project: Rapid Application Development Framework</p>
 * @author zqb
 * @version 0.1
 */

public class ResponseEnvelopHead implements Serializable{

    private int code = 0;
    /**
     * 错误信息，格式为：时间|错误码|详细信息|概述信息|
     */
    private String message=null;

    /* Added by KentKong for Transaction   */
//    private SysTransLog transLog = null;

//    public SysTransLog getTransLog() {
//        return transLog;
//    }
//    public void setTransLog(SysTransLog transLog) {
//        this.transLog = transLog;
//    }
    /* Added finish. */

    public ResponseEnvelopHead() {
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    /**
     * 设置错误信息，格式为时间|错误码|详细信息|概述信息|
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}