package org.radf.apps.client.single.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class DiagnoseForm extends ActionForm {
	private String dgnctid; // 病人代码
	private Double dgnlufa; // 左耳不适域
	private Double dgnrufa; // 左耳不适域
	private String dgnlid; // 左耳耳机产品编号
	private Double dgnldprc; // 左耳零售价格
	private Double dgnldct; // 左耳折扣
	private String dgnrid; // 右耳耳机产品编号
	private Double dgnrdprc; // 右耳零售价格
	private Double dgnrdct;// 右耳折扣
	private String dgnmmk; // 耳印制作者
	private Date dgnpfdt; // 要求取货日期
	private String dgnsdmd; // 特殊要求
	private Date dgnafdt;// 实际取货日期
	private String dgnctimp; // 病人反映
	private String dgndoc; // 医生代码
	private Date dgndt; // 门诊日期
	private String company;

	public String getDgnctid() {
		return dgnctid;
	}

	public void setDgnctid(String dgnctid) {
		this.dgnctid = dgnctid;
	}

	public Double getDgnlufa() {
		return dgnlufa;
	}

	public void setDgnlufa(Double dgnlufa) {
		this.dgnlufa = dgnlufa;
	}

	public Double getDgnrufa() {
		return dgnrufa;
	}

	public void setDgnrufa(Double dgnrufa) {
		this.dgnrufa = dgnrufa;
	}

	public String getDgnlid() {
		return dgnlid;
	}

	public void setDgnlid(String dgnlid) {
		this.dgnlid = dgnlid;
	}

	public Double getDgnldprc() {
		return dgnldprc;
	}

	public void setDgnldprc(Double dgnldprc) {
		this.dgnldprc = dgnldprc;
	}

	public Double getDgnldct() {
		return dgnldct;
	}

	public void setDgnldct(Double dgnldct) {
		this.dgnldct = dgnldct;
	}

	public String getDgnrid() {
		return dgnrid;
	}

	public void setDgnrid(String dgnrid) {
		this.dgnrid = dgnrid;
	}

	public Double getDgnrdprc() {
		return dgnrdprc;
	}

	public void setDgnrdprc(Double dgnrdprc) {
		this.dgnrdprc = dgnrdprc;
	}

	public Double getDgnrdct() {
		return dgnrdct;
	}

	public void setDgnrdct(Double dgnrdct) {
		this.dgnrdct = dgnrdct;
	}

	public String getDgnmmk() {
		return dgnmmk;
	}

	public void setDgnmmk(String dgnmmk) {
		this.dgnmmk = dgnmmk;
	}

	public Date getDgnpfdt() {
		return dgnpfdt;
	}

	public void setDgnpfdt(Date dgnpfdt) {
		this.dgnpfdt = dgnpfdt;
	}

	public String getDgnsdmd() {
		return dgnsdmd;
	}

	public void setDgnsdmd(String dgnsdmd) {
		this.dgnsdmd = dgnsdmd;
	}

	public Date getDgnafdt() {
		return dgnafdt;
	}

	public void setDgnafdt(Date dgnafdt) {
		this.dgnafdt = dgnafdt;
	}

	public String getDgnctimp() {
		return dgnctimp;
	}

	public void setDgnctimp(String dgnctimp) {
		this.dgnctimp = dgnctimp;
	}

	public String getDgndoc() {
		return dgndoc;
	}

	public void setDgndoc(String dgndoc) {
		this.dgndoc = dgndoc;
	}

	public Date getDgndt() {
		return dgndt;
	}

	public void setDgndt(Date dgndt) {
		this.dgndt = dgndt;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
