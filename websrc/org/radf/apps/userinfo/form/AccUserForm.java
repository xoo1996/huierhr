package org.radf.apps.userinfo.form;

import org.apache.struts.action.ActionForm;

public class AccUserForm extends ActionForm{

	//��ѯ������Ŀ���˺�
	private String taraccount;
	private String qusername;
	private String quseremployid;
	
//��ҳ������
	//�¼�id
	private String useremployid;
	//userid���󶨵��˺�
	private String account;
	//�û���
	
	
	
	public String getTaraccount() {
		return taraccount;
	}
	public String getQuseremployid() {
		return quseremployid;
	}
	public void setQuseremployid(String quseremployid) {
		this.quseremployid = quseremployid;
	}
	public void setTaraccount(String taraccount) {
		this.taraccount = taraccount;
	}


	public String getUseremployid() {
		return useremployid;
	}
	public void setUseremployid(String useremployid) {
		this.useremployid = useremployid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getQusername() {
		return qusername;
	}
	public void setQusername(String qusername) {
		this.qusername = qusername;
	}
	
}
