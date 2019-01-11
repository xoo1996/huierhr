package org.radf.manage.user.dto;
import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;
public class Sc05DTO extends EntitySupport {
	private String bsc010;//	  用户内码 
	private String bsc011;//	  用户代码  
	private String bsc012;//	  用户名称  
	private String bsc013;//	  密码
	private String oldpwd;//	  密码 
	private String aae005;//	  联系电话  
	private String bsc001;//	  机构内码  
	private String bsc008;//	  科室内码  
	private String aae100;//	  有效标记  
	private Date   aae036;//  操作时间  
	private String aae011;//	  操作人
	private String bsc014;//	    角色内码    
	private String bsc015;//	   角色名称 
	private String bsc009;//	     科室名称
	private String aab300;//	     机构名称
	private String aab003;//	     机构编码
	private String ukeysn;//			ukey序列号
	private String ugctid;//      客户id
	private String iswork; //     状态
	private String aae101;//       启用状态
	private Date uopdate;//    ukey操作时间
	
	
	public Date getUopdate() {
		return uopdate;
	}
	public void setUopdate(Date uopdate) {
		this.uopdate = uopdate;
	}
	public String getAae101() {
		return aae101;
	}
	public void setAae101(String aae101) {
		this.aae101 = aae101;
	}
	public String getUkeysn() {
		return ukeysn;
	}
	public void setUkeysn(String ukeysn) {
		this.ukeysn = ukeysn;
	}
	public String getUgctid() {
		return ugctid;
	}
	public void setUgctid(String ugctid) {
		this.ugctid = ugctid;
	}
	public String getIswork() {
		return iswork;
	}
	public void setIswork(String iswork) {
		this.iswork = iswork;
	}
	public String getAab003() {
		return aab003;
	}
	public void setAab003(String aab003) {
		this.aab003 = aab003;
	}
	public String getAae005() {
		return aae005;
	}
	public void setAae005(String aae005) {
		this.aae005 = aae005;
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
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	public String getBsc001() {
		return bsc001;
	}
	public void setBsc001(String bsc001) {
		this.bsc001 = bsc001;
	}
	public String getBsc008() {
		return bsc008;
	}
	public void setBsc008(String bsc008) {
		this.bsc008 = bsc008;
	}
	public String getBsc010() {
		return bsc010;
	}
	public void setBsc010(String bsc010) {
		this.bsc010 = bsc010;
	}
	public String getBsc011() {
		return bsc011;
	}
	public void setBsc011(String bsc011) {
		this.bsc011 = bsc011;
	}
	public String getBsc012() {
		return bsc012;
	}
	public void setBsc012(String bsc012) {
		this.bsc012 = bsc012;
	}
	public String getBsc013() {
		return bsc013;
	}
	public void setBsc013(String bsc013) {
		this.bsc013 = bsc013;
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
	public String getBsc009() {
		return bsc009;
	}
	public void setBsc009(String bsc009) {
		this.bsc009 = bsc009;
	}
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getAab300() {
		return aab300;
	}
	public void setAab300(String aab300) {
		this.aab300 = aab300;
	}

}
