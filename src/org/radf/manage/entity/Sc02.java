package org.radf.manage.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class Sc02 extends EntitySupport {
	private String bsc001;//	  机构内码  
	private String bsc004;//	  机构别名  
	private String bsc005;//	  业务类别  
	private Date   aae036;//  操作时间   
	private String aae011;//	  操作人  
	private String aae017;//经办部门  
	public String getAae017() {
		return aae017;
	}
	public void setAae017(String aae017) {
		this.aae017 = aae017;
	}
	public String getAae011() {
		return aae011;
	}
	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}
	public Date getAae036() {
		return aae036;
	}
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}
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
