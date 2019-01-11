/**
 * Rec06DTO.java 2008/04/03
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.recommendation.statistic.dto;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

/**
 * 职业工作情况年报
 */
public class Rec06DTO extends EntitySupport {
	private String brt996;// 统计时间
	private String aae011; //经办人                      
	private String aae017; //经办机构                    
	private String aae019; //经办科室                    
	private Date   aae036; //经办日期  
	private String cce001;//统计机构
	private String bsc006;//虚拟机构
	
	public String getBsc006() {
		return bsc006;
	}

	public void setBsc006(String bsc006) {
		this.bsc006 = bsc006;
	}

	public String getCce001() {
		return cce001;
	}

	public void setCce001(String cce001) {
		this.cce001 = cce001;
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

	public String getAae019() {
		return aae019;
	}

	public void setAae019(String aae019) {
		this.aae019 = aae019;
	}

	public Date getAae036() {
		return aae036;
	}

	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}

	public String getBrt996() {
		return brt996;
	}

	public void setBrt996(String brt996) {
		this.brt996 = brt996;
	}



}
