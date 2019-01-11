/**
 * Rec07Form.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employassembly.form;

import org.apache.struts.action.ActionForm;

/**
 * 大会招聘信息管理
 */
public class Rec07Form extends ActionForm {

	private String aab007;// 营业执照号

	private String aab002;// 社会保险登记证编码

	private String aab043;// 单位拼音码

	private String aab001;// 单位编号

	private String aab004;// 单位名称

	private String aab003;// 组织机构代码

	private String aae006;// 单位地点

	private String aae004;// 联系人

	private String aae005;// 联系电话

	public String getAab001() {
		return aab001;
	}

	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}

	public String getAab002() {
		return aab002;
	}

	public void setAab002(String aab002) {
		this.aab002 = aab002;
	}

	public String getAab003() {
		return aab003;
	}

	public void setAab003(String aab003) {
		this.aab003 = aab003;
	}

	public String getAab004() {
		return aab004;
	}

	public void setAab004(String aab004) {
		this.aab004 = aab004;
	}

	public String getAab007() {
		return aab007;
	}

	public void setAab007(String aab007) {
		this.aab007 = aab007;
	}

	public String getAab043() {
		return aab043;
	}

	public void setAab043(String aab043) {
		this.aab043 = aab043;
	}

	public String getAae004() {
		return aae004;
	}

	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}

	public String getAae005() {
		return aae005;
	}

	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}

	public String getAae006() {
		return aae006;
	}

	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

}