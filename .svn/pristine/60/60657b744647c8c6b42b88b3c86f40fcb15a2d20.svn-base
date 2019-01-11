package org.radf.apps.task.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class TaskForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//面板基本信息
	private String pnlid;//面板编号
	private String pnlnm;//面板型号
	private String pnlstoid;//库存编号
	private String pnlnt;//备注
	private String pnlproid;//面板代码（即商品代码）
	
	/**
	 * 面板配置
	 */
	private String cfgacyid;//零件代码
	private String cfgpnlnm;//面板型号
	private String cfgnt;//备注
	private String cfgacyidori;//修改前的零件代码
	
	
	/**
	 * 面板零件字段
	 */
	private String acyid; //零件代码
	private String acystoid; //库存编号
	private String acypdtnm; //零件名称
	private String acytyp; //零件型号
	private String acynt; //备注
	
	
	//任务单信息
	private String tskid;//任务单号
	private String tskpnlnm;//面板型号
	private Integer tskpnlqnt;//面板数量
	private String tskdmd;//面板要求
	private String tsksta;//面板状态
	private Date tskdfdt;//要求完成日期
	private Date tskbgndt;//任务下达日期
	private String tskbilopr;//制单人
	private String tskadtopr;//审核人
	private String tskmkopr;//生产人员
	private Date tskmkdt;//生产日期
	private String tskpdopr;//生产部主管
	private String tsknt;//备注
	
	private Date start;//开始日期
	private Date end;//结束日期
	
	//任务单详情表字段
	private String tsdtskid;//任务单号
	private String tsdsid;// 任务单中面板序号
	private String tsdpnlid;// 面板编号
	private String tsdsta;// 面板状态
	private String tsdnt;// 备注
	
	//任务单配置表字段
	private String tcfid;//  任务单号
	private String tcfsid;//  任务单中面板序号
	private String tcfacy;//  零件代码
	private String tcfbth;//  零件批号
	private String tcfnum;//  零件货号
	private String tcfnt;//  备注
	
	// 库存表
	private String stoid;// 库存编号
	private String stogrcliid;// 团体客户代码
	private String stoproid;// 商品代码
	private String stoprotype;// 商品类型
	private String stoproname;// 商品名称
	private Integer stoamount;// 数量(入库/出库)
	private String stoamountun;// 数量单位
	private Double stoproprice;// 单价
	private Date stodate;// 出入库日期
	private Integer stoactype;// 动作（1、入库-1、出库）
	private String storemark;// 备注
	private String stooprcd;// 操作员代码
	private String stodisc;// 是否已报废
	private String stoprodsc;//商品描述
	private String stocllar;//大类
	private String stoclmid;//中类
	private String stoclsam;//小类
	private String isrepair;//是否维修订单
	//面板质检表
	private String pnlqaid;//质检编号
	private String pnlqatskid;//任务单号
	private String pnlqapnl;//任务单中所有面板编号范围（如JB112020370-JB112020380）
	private String pnlqarsta;//检验结果1
	private String pnlqarstb;//检验结果2
	private String pnlqarstc;//检验结果3
	private String pnlqaopra;//检验员1（品管人员）
	private String pnlqaoprb;//检验员2（品管人员）
	private String pnlqaoprc;//检验员3（品管人员）
	private String pnlqapnla;//检验员1所检面板编号范围
	private String pnlqapnlb;//检验员2所检面板编号范围
	private String pnlqapnlc;//检验员3所检面板编号范围

	private String qachka;//检验结果1(下拉列表)
	private String qachkb;//检验结果2(下拉列表)
	private String qachkc;//检验结果3(下拉列表)
	
	private Date pnlqadt;//检验日期
	private String pnlqant;//备注
	private String pnlorgnm;//面板的原来定制机型号
		
	public String getIsrepair() {
		return isrepair;
	}
	public void setIsrepair(String isrepair) {
		this.isrepair = isrepair;
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
	public String getPnlstoid() {
		return pnlstoid;
	}
	public void setPnlstoid(String pnlstoid) {
		this.pnlstoid = pnlstoid;
	}
	
	public String getPnlnt() {
		return pnlnt;
	}
	public void setPnlnt(String pnlnt) {
		this.pnlnt = pnlnt;
	}
	public String getTskid() {
		return tskid;
	}
	public void setTskid(String tskid) {
		this.tskid = tskid;
	}
	public String getTskpnlnm() {
		return tskpnlnm;
	}
	public void setTskpnlnm(String tskpnlnm) {
		this.tskpnlnm = tskpnlnm;
	}
	public Integer getTskpnlqnt() {
		return tskpnlqnt;
	}
	public void setTskpnlqnt(Integer tskpnlqnt) {
		this.tskpnlqnt = tskpnlqnt;
	}
	public String getTskdmd() {
		return tskdmd;
	}
	public void setTskdmd(String tskdmd) {
		this.tskdmd = tskdmd;
	}
	public String getTsksta() {
		return tsksta;
	}
	public void setTsksta(String tsksta) {
		this.tsksta = tsksta;
	}
	public Date getTskdfdt() {
		return tskdfdt;
	}
	public void setTskdfdt(Date tskdfdt) {
		this.tskdfdt = tskdfdt;
	}
	public Date getTskbgndt() {
		return tskbgndt;
	}
	public void setTskbgndt(Date tskbgndt) {
		this.tskbgndt = tskbgndt;
	}
	public String getTskbilopr() {
		return tskbilopr;
	}
	public void setTskbilopr(String tskbilopr) {
		this.tskbilopr = tskbilopr;
	}
	public String getTskadtopr() {
		return tskadtopr;
	}
	public void setTskadtopr(String tskadtopr) {
		this.tskadtopr = tskadtopr;
	}
	public String getTskmkopr() {
		return tskmkopr;
	}
	public void setTskmkopr(String tskmkopr) {
		this.tskmkopr = tskmkopr;
	}
	public Date getTskmkdt() {
		return tskmkdt;
	}
	public void setTskmkdt(Date tskmkdt) {
		this.tskmkdt = tskmkdt;
	}
	public String getTskpdopr() {
		return tskpdopr;
	}
	public void setTskpdopr(String tskpdopr) {
		this.tskpdopr = tskpdopr;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
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
	
	public String getTcfnum() {
		return tcfnum;
	}
	public void setTcfnum(String tcfnum) {
		this.tcfnum = tcfnum;
	}
	public String getTcfbth() {
		return tcfbth;
	}
	public void setTcfbth(String tcfbth) {
		this.tcfbth = tcfbth;
	}
	public String getAcynt() {
		return acynt;
	}
	public void setAcynt(String acynt) {
		this.acynt = acynt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStoid() {
		return stoid;
	}
	public String getStogrcliid() {
		return stogrcliid;
	}
	public void setStogrcliid(String stogrcliid) {
		this.stogrcliid = stogrcliid;
	}
	public String getStoproid() {
		return stoproid;
	}
	public void setStoproid(String stoproid) {
		this.stoproid = stoproid;
	}
	public String getStoprotype() {
		return stoprotype;
	}
	public void setStoprotype(String stoprotype) {
		this.stoprotype = stoprotype;
	}
	public String getStoproname() {
		return stoproname;
	}
	public void setStoproname(String stoproname) {
		this.stoproname = stoproname;
	}
	public Integer getStoamount() {
		return stoamount;
	}
	public void setStoamount(Integer stoamount) {
		this.stoamount = stoamount;
	}
	public String getStoamountun() {
		return stoamountun;
	}
	public void setStoamountun(String stoamountun) {
		this.stoamountun = stoamountun;
	}
	public Double getStoproprice() {
		return stoproprice;
	}
	public void setStoproprice(Double stoproprice) {
		this.stoproprice = stoproprice;
	}
	public Date getStodate() {
		return stodate;
	}
	public void setStodate(Date stodate) {
		this.stodate = stodate;
	}
	public Integer getStoactype() {
		return stoactype;
	}
	public void setStoactype(Integer stoactype) {
		this.stoactype = stoactype;
	}
	public String getStoremark() {
		return storemark;
	}
	public void setStoremark(String storemark) {
		this.storemark = storemark;
	}
	public String getStooprcd() {
		return stooprcd;
	}
	public void setStooprcd(String stooprcd) {
		this.stooprcd = stooprcd;
	}
	public String getStodisc() {
		return stodisc;
	}
	public void setStodisc(String stodisc) {
		this.stodisc = stodisc;
	}
	public void setStoid(String stoid) {
		this.stoid = stoid;
	}
	public String getPnlqaid() {
		return pnlqaid;
	}
	public void setPnlqaid(String pnlqaid) {
		this.pnlqaid = pnlqaid;
	}
	public String getPnlqatskid() {
		return pnlqatskid;
	}
	public void setPnlqatskid(String pnlqatskid) {
		this.pnlqatskid = pnlqatskid;
	}
	public String getPnlqapnl() {
		return pnlqapnl;
	}
	public void setPnlqapnl(String pnlqapnl) {
		this.pnlqapnl = pnlqapnl;
	}
	public Date getPnlqadt() {
		return pnlqadt;
	}
	public void setPnlqadt(Date pnlqadt) {
		this.pnlqadt = pnlqadt;
	}
	public String getPnlqant() {
		return pnlqant;
	}
	public void setPnlqant(String pnlqant) {
		this.pnlqant = pnlqant;
	}
	public String getPnlorgnm() {
		return pnlorgnm;
	}
	public void setPnlorgnm(String pnlorgnm) {
		this.pnlorgnm = pnlorgnm;
	}
	public String getTsknt() {
		return tsknt;
	}
	public void setTsknt(String tsknt) {
		this.tsknt = tsknt;
	}
	
	
	public String getQachka() {
		return qachka;
	}
	public void setQachka(String qachka) {
		this.qachka = qachka;
	}
	public String getQachkb() {
		return qachkb;
	}
	public void setQachkb(String qachkb) {
		this.qachkb = qachkb;
	}
	public String getQachkc() {
		return qachkc;
	}
	public void setQachkc(String qachkc) {
		this.qachkc = qachkc;
	}
	public String getPnlqarsta() {
		return pnlqarsta;
	}
	public void setPnlqarsta(String pnlqarsta) {
		this.pnlqarsta = pnlqarsta;
	}
	public String getPnlqarstb() {
		return pnlqarstb;
	}
	public void setPnlqarstb(String pnlqarstb) {
		this.pnlqarstb = pnlqarstb;
	}
	public String getPnlqarstc() {
		return pnlqarstc;
	}
	public void setPnlqarstc(String pnlqarstc) {
		this.pnlqarstc = pnlqarstc;
	}
	public String getPnlqaopra() {
		return pnlqaopra;
	}
	public void setPnlqaopra(String pnlqaopra) {
		this.pnlqaopra = pnlqaopra;
	}
	public String getPnlqaoprb() {
		return pnlqaoprb;
	}
	public void setPnlqaoprb(String pnlqaoprb) {
		this.pnlqaoprb = pnlqaoprb;
	}
	public String getPnlqaoprc() {
		return pnlqaoprc;
	}
	public void setPnlqaoprc(String pnlqaoprc) {
		this.pnlqaoprc = pnlqaoprc;
	}
	public String getPnlqapnla() {
		return pnlqapnla;
	}
	public void setPnlqapnla(String pnlqapnla) {
		this.pnlqapnla = pnlqapnla;
	}
	public String getPnlqapnlb() {
		return pnlqapnlb;
	}
	public void setPnlqapnlb(String pnlqapnlb) {
		this.pnlqapnlb = pnlqapnlb;
	}
	public String getPnlqapnlc() {
		return pnlqapnlc;
	}
	public void setPnlqapnlc(String pnlqapnlc) {
		this.pnlqapnlc = pnlqapnlc;
	}
	public String getTsdtskid() {
		return tsdtskid;
	}
	public void setTsdtskid(String tsdtskid) {
		this.tsdtskid = tsdtskid;
	}
	public String getTsdsid() {
		return tsdsid;
	}
	public void setTsdsid(String tsdsid) {
		this.tsdsid = tsdsid;
	}
	public String getTsdpnlid() {
		return tsdpnlid;
	}
	public void setTsdpnlid(String tsdpnlid) {
		this.tsdpnlid = tsdpnlid;
	}
	public String getTsdsta() {
		return tsdsta;
	}
	public void setTsdsta(String tsdsta) {
		this.tsdsta = tsdsta;
	}
	public String getTsdnt() {
		return tsdnt;
	}
	public void setTsdnt(String tsdnt) {
		this.tsdnt = tsdnt;
	}
	public String getTcfid() {
		return tcfid;
	}
	public void setTcfid(String tcfid) {
		this.tcfid = tcfid;
	}
	public String getTcfsid() {
		return tcfsid;
	}
	public void setTcfsid(String tcfsid) {
		this.tcfsid = tcfsid;
	}
	public String getTcfacy() {
		return tcfacy;
	}
	public void setTcfacy(String tcfacy) {
		this.tcfacy = tcfacy;
	}
	public String getTcfnt() {
		return tcfnt;
	}
	public void setTcfnt(String tcfnt) {
		this.tcfnt = tcfnt;
	}
	public String getStocllar() {
		return stocllar;
	}
	public void setStocllar(String stocllar) {
		this.stocllar = stocllar;
	}
	public String getStoclmid() {
		return stoclmid;
	}
	public void setStoclmid(String stoclmid) {
		this.stoclmid = stoclmid;
	}
	public String getStoclsam() {
		return stoclsam;
	}
	public void setStoclsam(String stoclsam) {
		this.stoclsam = stoclsam;
	}
	public String getStoprodsc() {
		return stoprodsc;
	}
	public void setStoprodsc(String stoprodsc) {
		this.stoprodsc = stoprodsc;
	}
	public String getPnlproid() {
		return pnlproid;
	}
	public void setPnlproid(String pnlproid) {
		this.pnlproid = pnlproid;
	}
	
	
	
}
