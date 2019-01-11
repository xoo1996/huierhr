package org.radf.manage.department.form;

import org.apache.struts.action.ActionForm;

public class Sc02Form extends ActionForm{
	private String bsc001;//	  机构内码  
	private String bsc004;//	  机构别名  
	private String bsc005;//	  业务类别 
	public String getBsc001() {
		return bsc001;
	}
	public void setBsc001(String bsc001) {
		this.bsc001 = bsc001;
	}
	public String getBsc004() {
		return bsc004;
	}
	public void setBsc004(String bsc004) {
		this.bsc004 = bsc004;
	}
	public String getBsc005() {
		return bsc005;
	}
	public void setBsc005(String bsc005) {
		this.bsc005 = bsc005;
	}
}
