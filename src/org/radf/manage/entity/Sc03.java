package org.radf.manage.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class Sc03 extends EntitySupport {
	private String bsc006;//	  ����  
	private String bsc007;//	  λ��  
	private Date   aae036;//  ����ʱ��  
	private String aae011;//	  ������  
	private String aae017;//���첿��  
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
	public String getBsc006() {
		return bsc006;
	}
	public void setBsc006(String bsc006) {
		this.bsc006 = bsc006;
	}
	public String getBsc007() {
		return bsc007;
	}
	public void setBsc007(String bsc007) {
		this.bsc007 = bsc007;
	}
}
