/**
 * Rec0105DTO.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.consigninvite.dto;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

/**
 * 人员推荐反馈dto
 */
public class Rec0105DTO extends EntitySupport {

	private String aac003; // 姓名

	private String aac004; // 性别

	private String aac002; // 公民身份号码

	private String aab004; // 单位名称

	private String aca111; // 专业工种

	private Date ace014; // 推荐有效日期

	private Date a014ce; // 推荐有效日期

	private String aae017; // 经办机构

	private String acc220; // 推荐编号

	private String aae011; // 经办人

	private Date aae036; // 经办时间

	private String acc22e;// 失败情况

	private String acb200;//

	private String acb210;//

	private String acc200;//

	private String acc210;//
	
	private String acb216;//求职工种	

	private String acc229;// 反馈有效期

	private String acc226;// 反馈操作员

	private String acc223;// 反馈状态

	private String flag;

	public String getAcb216() {
		return acb216;
	}

	public void setAcb216(String acb216) {
		this.acb216 = acb216;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getA014ce() {
		return a014ce;
	}

	public void setA014ce(Date a014ce) {
		this.a014ce = a014ce;
	}

	public String getAab004() {
		return aab004;
	}

	public void setAab004(String aab004) {
		this.aab004 = aab004;
	}

	public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getAac004() {
		return aac004;
	}

	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}

	public String getAae011() {
		return aae011;
	}

	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	public String getAae017() {
		return aae017;
	}

	public void setAae017(String aae017) {
		this.aae017 = aae017;
	}

	public Date getAae036() {
		return aae036;
	}

	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}

	public String getAca111() {
		return aca111;
	}

	public void setAca111(String aca111) {
		this.aca111 = aca111;
	}

	public String getAcb200() {
		return acb200;
	}

	public void setAcb200(String acb200) {
		this.acb200 = acb200;
	}

	public String getAcb210() {
		return acb210;
	}

	public void setAcb210(String acb210) {
		this.acb210 = acb210;
	}

	public String getAcc200() {
		return acc200;
	}

	public void setAcc200(String acc200) {
		this.acc200 = acc200;
	}

	public String getAcc210() {
		return acc210;
	}

	public void setAcc210(String acc210) {
		this.acc210 = acc210;
	}

	public String getAcc220() {
		return acc220;
	}

	public void setAcc220(String acc220) {
		this.acc220 = acc220;
	}

	public String getAcc226() {
		return acc226;
	}

	public void setAcc226(String acc226) {
		this.acc226 = acc226;
	}

	public String getAcc22e() {
		return acc22e;
	}

	public void setAcc22e(String acc22e) {
		this.acc22e = acc22e;
	}

	public Date getAce014() {
		return ace014;
	}

	public void setAce014(Date ace014) {
		this.ace014 = ace014;
	}

	public String getAcc223() {
		return acc223;
	}

	public void setAcc223(String acc223) {
		this.acc223 = acc223;
	}

	public String getAcc229() {
		return acc229;
	}

	public void setAcc229(String acc229) {
		this.acc229 = acc229;
	}

}