/**
 * This file is creatd by POC Super Man Version 1.0 .
 * POC SuperMan is a tool for rapid POC developing (RPD).
 * It generates basic jdbc operating code, database sql, simple jsp code.
 * Buddy, I don't know who you are , but I deeply support you on spirits ^0^ .
 * You are the SuperMan!
 * Hope it can help you much! Achieve!!
 */
package org.radf.manage.trans.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
* ϵͳ������־ʵ����
* @author zqb
* @version 1.0
 */
public class SysTransLog extends EntitySupport {
   private long logId;              //��־��ʾ
   private String startTime;        //��ʼʱ��
   private String endTime;          //����ʱ��
   private String transId;          //���״���(ȡ����ģ�����FunctionID)
   private String transName;        //��������(�˵�����������)
   private long origLogId;          //ԭ������־����
   private String loginName;           //�û�����
   private String sessionId;        //�Ự����
   private int timeoutTime;         //��ʱʱ��
   private String status;           //����ִ��״̬
   private String ip;               //ִ����IP��ַ
   
   public SysTransLog(){
   }
   public SysTransLog(long logId){
       this.logId = logId;
   }
   public SysTransLog(long logID, String startTime, String endTime,
            String transID, String transName, long origLogID, String loginName,
            String sessionID, int timeoutTime, String status) {
        this.logId = logID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.transId = transID;
        this.transName = transName;
        this.origLogId = origLogID;
        this.loginName = loginName;
        this.sessionId = sessionID;
        this.timeoutTime = timeoutTime;
        this.status = status;
    }
   public SysTransLog(long logID, String startTime, String endTime,
           String transID, String transName, long origLogID, String loginName,
           String sessionID, int timeoutTime, String status,String ip) {
       this.logId = logID;
       this.startTime = startTime;
       this.endTime = endTime;
       this.transId = transID;
       this.transName = transName;
       this.origLogId = origLogID;
       this.loginName = loginName;
       this.sessionId = sessionID;
       this.timeoutTime = timeoutTime;
       this.status = status;
       this.ip = ip;
   }
   
   public long getLogId(){
        return logId ;
   }

   public void setLogId(long logId){
       this.logId = logId;
   }
   public String getStartTime(){
        return startTime ;
   }

   public void setStartTime(String startTime){
       this.startTime = startTime;
   }
   public String getEndTime(){
        return endTime ;
   }

   public void setEndTime(String endTime){
       this.endTime = endTime;
   }
   public String getTransId(){
        return transId ;
   }

   public void setTransId(String transId){
       this.transId = transId;
   }
   public String getTransName(){
        return transName ;
   }

   public void setTransName(String transName){
       this.transName = transName;
   }
   public long getOrigLogId(){
        return origLogId ;
   }

   public void setOrigLogId(long origLogId){
       this.origLogId = origLogId;
   }
   public String getLoginName(){
        return loginName ;
   }

   public void setLoginName(String loginName){
       this.loginName = loginName;
   }
   public String getSessionId(){
        return sessionId ;
   }

   public void setSessionId(String sessionId){
       this.sessionId = sessionId;
   }
   public int getTimeoutTime(){
        return timeoutTime ;
   }

   public void setTimeoutTime(int timeoutTime){
       this.timeoutTime = timeoutTime;
   }
   public String getStatus(){
        return status ;
   }

   public void setStatus(String status){
       this.status = status;
   }

/**
 * @return Returns the ip.
 */
public String getIp() {
    return ip;
}

/**
 * @param ip The ip to set.
 */
public void setIp(String ip) {
    this.ip = ip;
}



}



