package org.radf.manage.logMessage.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
 * ��־����ʵ����
 * @author zqb</a>
 * @version 1.0
 */

public class LogMessage extends EntitySupport {

    public LogMessage() {
    }
    public LogMessage(String id){
        this.id = id;
    }
    
    private java.lang.String id;

    private int code;

    private java.lang.String message;

    private java.lang.String msgdate;

    private java.lang.String sessionid;

    private java.lang.String loginName;

    private java.lang.String functionid;

    private java.lang.String ip;
    
    
    private String paixu;//����
    private String dianji;//����

    /**
     * @return ���� dianji��
     */
    public String getDianji() {
        return dianji;
    }
    /**
     * @param dianji Ҫ���õ� dianji��
     */
    public void setDianji(String dianji) {
        this.dianji = dianji;
    }
    /**
     * @return ���� paixu��
     */
    public String getPaixu() {
        return paixu;
    }
    /**
     * @param paixu Ҫ���õ� paixu��
     */
    public void setPaixu(String paixu) {
        this.paixu = paixu;
    }
    public void setId(java.lang.String id) {
        this.id = id;
    }

    public java.lang.String getId() {
        return this.id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    public java.lang.String getMessage() {
        return this.message;
    }

    public void setMsgdate(java.lang.String msgdate) {
        this.msgdate = msgdate;
    }

    public java.lang.String getMsgdate() {
        return this.msgdate;
    }

    public void setSessionid(java.lang.String sessionid) {
        this.sessionid = sessionid;
    }

    public java.lang.String getSessionid() {
        return this.sessionid;
    }

    public void setLoginName(java.lang.String loginName) {
        this.loginName = loginName;
    }

    public java.lang.String getLoginName() {
        return this.loginName;
    }

    public void setFunctionid(java.lang.String functionid) {
        this.functionid = functionid;
    }

    public java.lang.String getFunctionid() {
        return this.functionid;
    }

    public java.lang.String getIp() {
        return ip;
    }

    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }

}
