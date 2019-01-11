package org.radf.plat.sieaf.event;

import java.io.Serializable;

/**
 * <p>Title:EventResponse </p>
 * <p>Description: This class represent the return value of <code>EJBAction</code>'s method </p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co.,Ltd</p>
 * <p>Company: LBS</p>
 * @author LBS Architect Team
 * @version 0.1
 */

public class EventResponse implements Serializable{

    private java.util.HashMap body=null;
    private String msg="";
    private boolean sucessFlag=false;
    public EventResponse() {
    }
    public void setBody(java.util.HashMap body) {
        this.body = body;
    }
    public java.util.HashMap getBody() {
        return body;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
    public void setSucessFlag(boolean successFlag) {
        this.sucessFlag = successFlag;
    }
    public boolean isSucessFlag() {
        return sucessFlag;
    }
}
