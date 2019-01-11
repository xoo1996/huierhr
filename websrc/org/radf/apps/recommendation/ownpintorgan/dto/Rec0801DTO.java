/**
 * Rec0801DTO.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.ownpintorgan.dto;

import org.radf.plat.util.entity.EntitySupport;

/**
 * 民办职业介绍机构年检
 */
public class Rec0801DTO extends EntitySupport {

	private String acb240;// 编号

	private String acb241;// 机构名称

	private String aab019;// 企业性质

	private String aae001;// 年检年份

	public String getAae001() {
		return aae001;
	}

	public void setAae001(String aae001) {
		this.aae001 = aae001;
	}

	public String getAab019() {
		return aab019;
	}

	public void setAab019(String aab019) {
		this.aab019 = aab019;
	}

	public String getAcb240() {
		return acb240;
	}

	public void setAcb240(String acb240) {
		this.acb240 = acb240;
	}

	public String getAcb241() {
		return acb241;
	}

	public void setAcb241(String acb241) {
		this.acb241 = acb241;
	}

}
