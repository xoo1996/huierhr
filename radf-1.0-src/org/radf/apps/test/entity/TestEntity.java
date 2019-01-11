/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-8-3117:12:09</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.apps.test.entity;

import java.sql.Date;

import org.radf.plat.util.entity.EntitySupport;

public class TestEntity extends EntitySupport{
    private String userId;
    private String loginName;
    private String password;
    private String clientIP;
    private String userName;
    private Date loginTime;
    
    /**
     * 
     * @return Returns the loginTime.
     */
    public Date getLoginTime() {
        return loginTime;
    }
    /**
     * @param loginTime The loginTime to set.
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    /**
     * @return Returns the userName.
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return Returns the loginName.
     */
    public String getLoginName() {
        return loginName;
    }
    /**
     * @param loginName The loginName to set.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return Returns the userID.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userID The userID to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return Returns the clientIP.
     */
    public String getClientIP() {
        return clientIP;
    }
    /**
     * @param clientIP The clientIP to set.
     */
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
}
