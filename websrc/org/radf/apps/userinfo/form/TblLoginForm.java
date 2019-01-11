package org.radf.apps.userinfo.form;

import org.apache.struts.action.ActionForm;

public class TblLoginForm extends ActionForm{

	//查询条件，目标账号
	private String taraccount;
//分页中数据
	//下级id
	private String userid;
	//userid所绑定的账号
	private String uaccount;
	//用户名
	private String username;
	public String getTaraccount() {
		return taraccount;
	}
	public void setTaraccount(String taraccount) {
		this.taraccount = taraccount;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUaccount() {
		return uaccount;
	}
	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
