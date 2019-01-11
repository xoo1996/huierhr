package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//诊治信息表
public class Diagnose extends EntitySupport {
	private String dgnid;
	private String dgnctid; // 病人代码
	private String dgnlufa; // 左耳不适域
	private String dgnrufa; // 左耳不适域
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
	private String dgnpulwire;  
	private String dgnairvent; //通气孔
	private String dgnvoswitch; //开关
	private Double dgncrdprc;   //右耳现价
	private Double dgncldprc;   //左耳现价
	private String dgnlnm; //定制机型号（左）
	private String dgnrnm; //定制机型号（右）
	private String dgnlfdtfno;//左耳订单号
	private String dgnrfdtfno;//右耳订单号

	
	public String getDgnid() {
		return dgnid;
	}

	public void setDgnid(String dgnid) {
		this.dgnid = dgnid;
	}

	public String getDgnctid() {
		return dgnctid;
	}

	public void setDgnctid(String dgnctid) {
		this.dgnctid = dgnctid;
	}

	public String getDgnlufa() {
		return dgnlufa;
	}

	public void setDgnlufa(String dgnlufa) {
		this.dgnlufa = dgnlufa;
	}

	public String getDgnrufa() {
		return dgnrufa;
	}

	public void setDgnrufa(String dgnrufa) {
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

	

	public String getDgnpulwire() {
		return dgnpulwire;
	}

	public void setDgnpulwire(String dgnpulwire) {
		this.dgnpulwire = dgnpulwire;
	}

	public String getDgnairvent() {
		return dgnairvent;
	}

	public void setDgnairvent(String dgnairvent) {
		this.dgnairvent = dgnairvent;
	}

	public String getDgnvoswitch() {
		return dgnvoswitch;
	}

	public void setDgnvoswitch(String dgnvoswitch) {
		this.dgnvoswitch = dgnvoswitch;
	}

	public Double getDgncrdprc() {
		return dgncrdprc;
	}

	public void setDgncrdprc(Double dgncrdprc) {
		this.dgncrdprc = dgncrdprc;
	}

	public Double getDgncldprc() {
		return dgncldprc;
	}

	public void setDgncldprc(Double dgncldprc) {
		this.dgncldprc = dgncldprc;
	}

	public String getDgnlnm() {
		return dgnlnm;
	}

	public void setDgnlnm(String dgnlnm) {
		this.dgnlnm = dgnlnm;
	}

	public String getDgnrnm() {
		return dgnrnm;
	}

	public void setDgnrnm(String dgnrnm) {
		this.dgnrnm = dgnrnm;
	}

	public String getDgnlfdtfno() {
		return dgnlfdtfno;
	}

	public void setDgnlfdtfno(String dgnlfdtfno) {
		this.dgnlfdtfno = dgnlfdtfno;
	}

	public String getDgnrfdtfno() {
		return dgnrfdtfno;
	}

	public void setDgnrfdtfno(String dgnrfdtfno) {
		this.dgnrfdtfno = dgnrfdtfno;
	}
	
	
	
}
