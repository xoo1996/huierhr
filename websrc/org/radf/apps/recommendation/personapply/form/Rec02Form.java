/**
 * Rec02Form.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personapply.form;

import org.apache.struts.action.ActionForm;

/**
 * 求职登记查询
 */
public class Rec02Form extends ActionForm {
	private String aac002;// 公民身份号码

	private String aac003;// 姓名

	private String aac004;// 性别

	private String aac011;// 文化程度

	private String aac009;// 户口性质

	private String aac014;// 专业技术职务

	private String aac001;// 个人编号

	private String acc200;// 求职编号

	private String aac005; // 民族

	private String aac006; // 出生日期
	
	private String acc025;//劳动手册号

	public String getAac005() {
		return aac005;
	}

	public void setAac005(String aac005) {
		this.aac005 = aac005;
	}

	public String getAac006() {
		return aac006;
	}

	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}

	public String getAac001() {
		return aac001;
	}

	public void setAac001(String aac001) {
		this.aac001 = aac001;
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

	public String getAac009() {
		return aac009;
	}

	public void setAac009(String aac009) {
		this.aac009 = aac009;
	}

	public String getAac011() {
		return aac011;
	}

	public void setAac011(String aac011) {
		this.aac011 = aac011;
	}

	public String getAac014() {
		return aac014;
	}

	public void setAac014(String aac014) {
		this.aac014 = aac014;
	}

	public String getAcc200() {
		return acc200;
	}

	public void setAcc200(String acc200) {
		this.acc200 = acc200;
	}

	public String getAcc025() {
		return acc025;
	}

	public void setAcc025(String acc025) {
		this.acc025 = acc025;
	}

}