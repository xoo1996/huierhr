/**
 * Rec0305DTO.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.query.dto;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

/**
 * 分类统计
 */
public class Rec0305DTO extends EntitySupport {
	private String stat01;// 统计分类

	private Date date01;// 登记时间

	private Date date02;// 登记时间

	private String cce001;// 所属机构

	private String ssjqnm;// 机构名称

	private String total;// 总数

	private String bsc006;// 虚拟机构
	private String acb201;// 招聘方式
	private String acb231;// 招聘期数 

	public String getAcb201() {
		return acb201;
	}

	public void setAcb201(String acb201) {
		this.acb201 = acb201;
	}



	public String getAcb231() {
		return acb231;
	}

	public void setAcb231(String acb231) {
		this.acb231 = acb231;
	}

	public String getBsc006() {
		return bsc006;
	}

	public void setBsc006(String bsc006) {
		this.bsc006 = bsc006;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCce001() {
		return cce001;
	}

	public void setCce001(String cce001) {
		this.cce001 = cce001;
	}

	public Date getDate01() {
		return date01;
	}

	public void setDate01(Date date01) {
		this.date01 = date01;
	}

	public Date getDate02() {
		return date02;
	}

	public void setDate02(Date date02) {
		this.date02 = date02;
	}

	public String getSsjqnm() {
		return ssjqnm;
	}

	public void setSsjqnm(String ssjqnm) {
		this.ssjqnm = ssjqnm;
	}

	public String getStat01() {
		return stat01;
	}

	public void setStat01(String stat01) {
		this.stat01 = stat01;
	}

}
