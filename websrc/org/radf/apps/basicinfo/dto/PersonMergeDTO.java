/**
 * PersonMergeDTO.java 2008/03/24
 * 
 * Copyright (c) 2008 项目: Rapid Application Development Framework
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.basicinfo.dto;

import org.radf.plat.util.entity.EntitySupport;
/**
 * 人员合并
 */
public class PersonMergeDTO extends EntitySupport{
	
	private String result;
	private String aac001;
	private String oldaac001;
	private String newaac001;
	private String aae011; //经办人
	
	private String a002ac;  //目标合并人员页面的身份证号
	private String a003ac;  //....姓名
	private String acc025;//劳动手册号
	
	
	
	public String getAcc025() {
		return acc025;
	}
	public void setAcc025(String acc025) {
		this.acc025 = acc025;
	}
	public String getA002ac() {
		return a002ac;
	}
	public void setA002ac(String a002ac) {
		this.a002ac = a002ac;
	}
	public String getA003ac() {
		return a003ac;
	}
	public void setA003ac(String a003ac) {
		this.a003ac = a003ac;
	}
	public String getAac001() {
		return aac001;
	}
	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}
	public String getAae011() {
		return aae011;
	}
	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}
	public String getNewaac001() {
		return newaac001;
	}
	public void setNewaac001(String newaac001) {
		this.newaac001 = newaac001;
	}
	public String getOldaac001() {
		return oldaac001;
	}
	public void setOldaac001(String oldaac001) {
		this.oldaac001 = oldaac001;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
