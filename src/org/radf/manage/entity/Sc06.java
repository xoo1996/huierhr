package org.radf.manage.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;
public class Sc06 extends EntitySupport {
	private String bsc014;//	  角色内码  
	private String bsc015;//	  角色名称  
	private Date aae036;//  操作时间  
	private String aae011;//	  操作人  
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
	public String getBsc014() {
		return bsc014;
	}
	public void setBsc014(String bsc014) {
		this.bsc014 = bsc014;
	}
	public String getBsc015() {
		return bsc015;
	}
	public void setBsc015(String bsc015) {
		this.bsc015 = bsc015;
	}
}
