/*
 * Created on 2003-2-25
 *
 * To change this generated comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.radf.manage.role.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
 * ��ɫ��ģ���ϵ���ձ�
 * @author zqb
 */
public class SysAcl extends EntitySupport{
	private String aclID;          //��ɫģ���������,seq����
    private String roleID;         //��ɫ����
	private String functionID;     //���ܴ���
    private String checkType;      //����δ֪

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
