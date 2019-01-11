package org.radf.apps.product.form;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class NormalChargeForm extends ActionForm {
	
	private BigDecimal chgid;//收费流水号
	private String chgstoid; //库存编号
	private String chggcltid;//团体客户代码
	private String chgcltid;//个人客户代码
	private String chgpdtid;//商品代码
	private Integer chgqnt;//售出数量
	private Double chgdis;//扣率
	private Double chgmon;//实际收费
	private Date chgdt;//收费日期
	
	
	
	public BigDecimal getChgid() {
		return chgid;
	}
	public void setChgid(BigDecimal chgid) {
		this.chgid = chgid;
	}
	public String getChgstoid() {
		return chgstoid;
	}
	public void setChgstoid(String chgstoid) {
		this.chgstoid = chgstoid;
	}
	public String getChggcltid() {
		return chggcltid;
	}
	public void setChggcltid(String chggcltid) {
		this.chggcltid = chggcltid;
	}
	public String getChgcltid() {
		return chgcltid;
	}
	public void setChgcltid(String chgcltid) {
		this.chgcltid = chgcltid;
	}
	public String getChgpdtid() {
		return chgpdtid;
	}
	public void setChgpdtid(String chgpdtid) {
		this.chgpdtid = chgpdtid;
	}
	public Integer getChgqnt() {
		return chgqnt;
	}
	public void setChgqnt(Integer chgqnt) {
		this.chgqnt = chgqnt;
	}
	public Double getChgdis() {
		return chgdis;
	}
	public void setChgdis(Double chgdis) {
		this.chgdis = chgdis;
	}
	public Double getChgmon() {
		return chgmon;
	}
	public void setChgmon(Double chgmon) {
		this.chgmon = chgmon;
	}
	public Date getChgdt() {
		return chgdt;
	}
	public void setChgdt(Date chgdt) {
		this.chgdt = chgdt;
	}
	
	

	
}
