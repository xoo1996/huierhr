/*
 * Created on 2003-2-25
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.radf.manage.role.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
 * 用户－角色关系对照表
 * @author zqb
 */
public class SysAct extends EntitySupport{
	private String actID;      //用户角色关联代码,seq生成
	private String userID;     //用户代码
	private String roleID;     //角色代码

    public SysAct(){
    }
    public SysAct(String actid){
        this.setActID(actid);
    }
    
	/**
	 * @return String
	 */
	public String getActID() {
		return actID;
	}

	/**
	 * @return String
	 */
	public String getRoleID() {
		return roleID;
	}

	/**
	 * @return String
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Sets the actID.
	 * @param actID The actID to set
	 */
	public void setActID(String actID) {
		this.actID = actID;
	}

	/**
	 * Sets the roleID.
	 * @param roleID The roleID to set
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	/**
	 * Sets the userID.
	 * @param userID The userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

}
