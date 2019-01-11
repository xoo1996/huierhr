package org.radf.manage.entity;
import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;
public class Sc05 extends EntitySupport {
	private String bsc010;//	  用户内码  
	private String bsc011;//	  用户代码  
	private String bsc012;//	  用户名称  
	private String bsc013;//	  密码  
	private String aae005;//	  联系电话  
	private String bsc001;//	  机构内码  
	private String bsc008;//	  科室内码  
	private String aae100;//	  有效标记  
	private Date   aae036;//  操作时间  
	private String aae011;//	  操作人  
	private String gctarea;//	所属区域
	
	public String getGctarea() {
		return gctarea;
	}
	public void setGctarea(String gctarea) {
		this.gctarea = gctarea;
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
}
