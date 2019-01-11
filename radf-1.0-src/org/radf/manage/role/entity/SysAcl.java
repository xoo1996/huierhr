/*
 * Created on 2003-2-25
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.radf.manage.role.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
 * 角色－模块关系对照表
 * @author zqb
 */
public class SysAcl extends EntitySupport{
	private String aclID;          //角色模块关联代码,seq生成
    private String roleID;         //角色代码
	private String functionID;     //功能代码
    private String checkType;      //含义未知

    public SysAcl(){
        
    }
    public SysAcl(String aclid){
        this.setAclID(aclid);
    }

	/**
	 * @return String
	 */
	public String getAclID() {
		return aclID;
	}

	/**
	 * @return String
	 */
	public String getFunctionID() {
		return functionID;
	}

	/**
	 * @return String
	 */
	public String getRoleID() {
		return roleID;
	}

	/**
	 * Sets the aclID.
	 * @param aclID The aclID to set
	 */
	public void setAclID(String aclID) {
		this.aclID = aclID;
	}

	/**
	 * Sets the functionID.
	 * @param functionID The functionID to set
	 */
	public void setFunctionID(String functionID) {
		this.functionID = functionID;
	}

	/**
	 * Sets the roleID.
	 * @param roleID The roleID to set
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

    /**
     * @return Returns the checkType.
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * @param checkType The checkType to set.
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

}
