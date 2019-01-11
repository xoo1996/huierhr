package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

public class TblLogin extends EntitySupport{
	
	//上级账号
	private String qentityid;
	//下级账号
		private String xentityid;
	//账号
	private String accountno;
	//账号类型
	private String accounttype;
	//实体id
	private String enityid;
	//上级id
	private String superiorid;
	//角色
	private String role;
	//角色id
	private String roleid;
	
	
	public String getXentityid() {
		return xentityid;
	}
	public void setXentityid(String xentityid) {
		this.xentityid = xentityid;
	}
	public String getQentityid() {
		return qentityid;
	}
	public void setQentityid(String qentityid) {
		this.qentityid = qentityid;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getEnityid() {
		return enityid;
	}
	public void setEnityid(String enityid) {
		this.enityid = enityid;
	}
	public String getSuperiorid() {
		return superiorid;
	}
	public void setSuperiorid(String superiorid) {
		this.superiorid = superiorid;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	
}
