package org.radf.manage.role.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
* 系统角色基本信息
* @author zqb
* @version 1.0
 */
public class SysRole extends EntitySupport {

	private String roleID;          //角色ID
	private String roleDesc;        //描述
    private String ParentID;        //上级角色ID
	private String createUserID;	//创建操作者ID
    
    public SysRole(){
    }
    public SysRole(String roleID){
        this.roleID = roleID;
    }
    
	/**
	 * @return String
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * @return String
	 */
	public String getRoleID() {
		return roleID;
	}

	/**
	 * Sets the roleDesc.
	 * @param roleDesc The roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/**
	 * Sets the roleID.
	 * @param roleID The roleID to set
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	/**
	 * @return Returns the createUserID.
	 */
	public String getCreateUserID() {
		return createUserID;
	}
	/**
	 * @param createUserID The createUserID to set.
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
    /**
     * @return Returns the parentID.
     */
    public String getParentID() {
        return ParentID;
    }
    /**
     * @param parentID The parentID to set.
     */
    public void setParentID(String parentID) {
        ParentID = parentID;
    }
    
}
