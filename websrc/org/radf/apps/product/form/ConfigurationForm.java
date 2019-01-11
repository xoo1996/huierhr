package org.radf.apps.product.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;

public class ConfigurationForm extends ActionForm {
	/**
	 * 面板配置
	 */
	private String cfgacyid;//零件代码
	private String cfgpnlnm;//面板型号
	private String cfgnt;//备注
	private String cfgacyidori;//修改前的零件代码
	
	/**
	 * 面板信息
	 */
	private String pnlid;//面板编号
	private String pnlnm;//面板型号
	private BigDecimal pnlstoid;//库存编号
	
	/**
	 * 面板零件字段
	 * @return
	 */
	private String acyid; //零件代码
	private String acystoid; //库存编号
	private String acypdtid; //零件货号
	private String acypdtnm; //零件名称
	private String acytyp; //零件型号
	private String acybthnum; //零件批号
	private String acynt; //备注
	private Integer cfgtemperature;//温度
	
	
	
	public Integer getCfgtemperature() {
		return cfgtemperature;
	}

	public void setCfgtemperature(Integer cfgtemperature) {
		this.cfgtemperature = cfgtemperature;
	}

	public String getCfgacyid() {
		return cfgacyid;
	}

	public void setCfgacyid(String cfgacyid) {
		this.cfgacyid = cfgacyid;
	}

	public String getCfgpnlnm() {
		return cfgpnlnm;
	}

	public void setCfgpnlnm(String cfgpnlnm) {
		this.cfgpnlnm = cfgpnlnm;
	}

	public String getCfgnt() {
		return cfgnt;
	}

	public void setCfgnt(String cfgnt) {
		this.cfgnt = cfgnt;
	}

	
	public String getCfgacyidori() {
		return cfgacyidori;
	}

	public void setCfgacyidori(String cfgacyidori) {
		this.cfgacyidori = cfgacyidori;
	}

	public String getPnlid() {
		return pnlid;
	}

	public void setPnlid(String pnlid) {
		this.pnlid = pnlid;
	}

	public String getPnlnm() {
		return pnlnm;
	}

	public void setPnlnm(String pnlnm) {
		this.pnlnm = pnlnm;
	}

	public BigDecimal getPnlstoid() {
		return pnlstoid;
	}

	public void setPnlstoid(BigDecimal pnlstoid) {
		this.pnlstoid = pnlstoid;
	}

	public String getAcyid() {
		return acyid;
	}

	public void setAcyid(String acyid) {
		this.acyid = acyid;
	}

	public String getAcystoid() {
		return acystoid;
	}

	public void setAcystoid(String acystoid) {
		this.acystoid = acystoid;
	}

	public String getAcypdtid() {
		return acypdtid;
	}

	public void setAcypdtid(String acypdtid) {
		this.acypdtid = acypdtid;
	}

	public String getAcypdtnm() {
		return acypdtnm;
	}

	public void setAcypdtnm(String acypdtnm) {
		this.acypdtnm = acypdtnm;
	}

	public String getAcytyp() {
		return acytyp;
	}

	public void setAcytyp(String acytyp) {
		this.acytyp = acytyp;
	}

	public String getAcybthnum() {
		return acybthnum;
	}

	public void setAcybthnum(String acybthnum) {
		this.acybthnum = acybthnum;
	}

	public String getAcynt() {
		return acynt;
	}

	public void setAcynt(String acynt) {
		this.acynt = acynt;
	}
	
	
}
