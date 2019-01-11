package org.radf.manage.param.dto;


import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class AA01DTO extends EntitySupport{
	
	private String aae140;//险种类型
	private String aaa001;//参数类别代码
	private String aaa003;//参数代码
	private Date aae030;//开始日期
	private String aaa002;//参数类别名称
	private String aaa004;//参数名称
	private String aaa005;//参数值
	private Double aaa006;//维修费
	private Date aae031;//终止日期
	private String aae013;//备注
	private String aae002;//实际登记年月
	private String aae100;//是否有效
	private String aab301;//所在区（林科增加）
	private String aac010;
	public String getAaa001() {
		return aaa001;
	}
	public void setAaa001(String aaa001) {
		this.aaa001 = aaa001;
	}
	public String getAaa002() {
		return aaa002;
	}
	public void setAaa002(String aaa002) {
		this.aaa002 = aaa002;
	}
	public String getAaa003() {
		return aaa003;
	}
	public void setAaa003(String aaa003) {
		this.aaa003 = aaa003;
	}
	public String getAaa004() {
		return aaa004;
	}
	public void setAaa004(String aaa004) {
		this.aaa004 = aaa004;
	}
	public String getAaa005() {
		return aaa005;
	}
	public void setAaa005(String aaa005) {
		this.aaa005 = aaa005;
	}
	public Double getAaa006() {
		return aaa006;
	}
	public void setAaa006(Double aaa006) {
		this.aaa006 = aaa006;
	}
	public String getAab301() {
		return aab301;
	}
	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}
	public String getAae002() {
		return aae002;
	}
	public void setAae002(String aae002) {
		this.aae002 = aae002;
	}
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	public Date getAae030() {
		return aae030;
	}
	public void setAae030(Date aae030) {
		this.aae030 = aae030;
	}
	public Date getAae031() {
		return aae031;
	}
	public void setAae031(Date aae031) {
		this.aae031 = aae031;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	public String getAae140() {
		return aae140;
	}
	public void setAae140(String aae140) {
		this.aae140 = aae140;
	}
	public String getAac010() {
		return aac010;
	}
	public void setAac010(String aac010) {
		this.aac010 = aac010;
	}
	
	
}
