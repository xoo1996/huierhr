package org.radf.apps.commons.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

//月度结算表
public class Business extends EntitySupport {
	private BigDecimal ivtid;
	private Integer ivtyear; // 年
	private Integer ivtmonth; // 月
	private String ivtgcltid; // 团体客户代码
	private String ivtcltid; // 病人代码
	private String ivtpdtid; // 商品(耳机)代码
	private Integer ivtlmqnt; // 上月结存数
	private Integer ivtlsqnt; // 本月补入数
	private Double ivtdiscount; // 折扣率
	private Integer ivtpqnt; // 本月回款数
	private Double ivtpamnt; // 回款金额
	private String ivtoprcd; // 月结操作员
	private Date ivtoprdt; // 月结日期
	private String ivtnote; // 备注
	private String ivttype;// 订单类型
	private String ivtcltnm; //病人姓名
	
	private String ivtfree;//赠送
	private String ivttry;//试机

	private Double discount;//扣率
	
	//加盟店月度结账表
	private String actstaid;//流水号
	private Integer actstayear;//年
	private Integer actstamonth;//月
	private String actstagcltid;//团体客户代码
	private Integer actmqnt;//月结存数
	private Integer actsqnt;//月补入数
	private String actsta;//结账状态
	private String actstanote;//备注
	
	private String gctid;//客户代码
	private String gctnm;//客户名称
	private String gctarea;//客户区域
	private	String cltnm;//个人客户姓名
	private String gcttype;//客户类别
	
	private String gctprovince;//省份
	private String pdtnm;//商品名称
	private Double pdtprc;//原价
	private Double fdtprc;//售价
	private Integer fdtqnt;//数量
	private Date folchgdt;//销售日期
	private String ncdypname;//验配师
	
	private String ictgender;//性别
	private Integer ages;//开始年龄
	private Integer aget;//结束年龄
	
	private Double prices;//最低价格
	private Double pricet;//最高价格
	
	private String earcase;//配机情况
	
	//固定资产
	private String astid;//流水号
	private String astgctid;//客户代码
	private String astgctnm;//客户名称
	private String pdtut;//单位
	private String astut;//固定资产单位
	private String astpdtnm;//固定资产名称
	private String astopr;//登记员代码
	private Double astprc;//固定资产金额
	private String astqnt;//固定资产数量
	private String astnote;//备注
	private Date astdt;//登记日期
	private Date start;//开始日期
	private Date end;//结束日期
	private Date stui;//开始日期
	private Date etui;//结束日期
	
	//摊销管理
	private String arzid;// 流水号
	private String arzgctid;// 客户代码
	private String arzgctnm;// 客户名称
	private String arzitem;// 摊销项目
	private Double arzamount;// 摊销总金额
	private Double arzmon;// 摊销金额
	private String arzmonth;// 摊销月份
	private Date arzdt;// 登记日期
	private String arzopr;// 登记员代码
	private String arzoprnm;// 登记员姓名
	private String arzisexp;//是否到期
	private Date arzstdt;// 起始时间
	private Date arzexpdt;// 到期日期
	private String arznt;//备注
	private String arzcontract;//合同期限

	// 房租摊销
	private Double harzamount;// 摊销总金额
	private String harzmonth;// 摊销月份
	private Double harzmon;// 摊销金额
	private Date harzstdt;// 摊销开始年月
	private Date harzexpdt;// 摊销到期年月
	private String harzisexp;// 是否到期
	private String harzcontract;// 合同期限
	private String harznt;// 备注

	// 装修费摊销
	private Double farzamount;// 摊销总金额
	private String farzmonth;// 摊销月份
	private Double farzmon;// 摊销金额
	private Date farzstdt;// 摊销开始年月
	private Date farzexpdt;// 摊销到期年月
	private String farzisexp;// 是否到期
	private String farzcontract;// 合同期限
	private String farznt;// 备注

	// 设备摊销
	private Double darzamount;// 摊销总金额
	private String darzmonth;// 摊销月份
	private Double darzmon;// 摊销金额
	private Date darzstdt;// 摊销开始年月
	private Date darzexpdt;// 摊销到期年月
	private String darzisexp;// 是否到期
	private String darzcontract;// 合同期限
	private String darznt;// 备注

	// 固定资产摊销
	private Double aarzamount;// 摊销总金额
	private String aarzmonth;// 摊销月份
	private Double aarzmon;// 摊销金额
	private Date aarzstdt;// 摊销开始年月
	private Date aarzexpdt;// 摊销到期年月
	private String aarzisexp;// 是否到期
	private String aarzcontract;// 合同期限
	private String aarznt;// 备注

	// 装让费摊销
	private Double tarzamount;// 摊销总金额
	private String tarzmonth;// 摊销月份
	private Double tarzmon;// 摊销金额
	private Date tarzstdt;// 摊销开始年月
	private Date tarzexpdt;// 摊销到期年月
	private String tarzisexp;// 是否到期
	private String tarzcontract;// 合同期限
	private String tarznt;// 备注

	// 开办费摊销
	private Double oarzamount;// 摊销总金额
	private String oarzmonth;// 摊销月份
	private Double oarzmon;// 摊销金额
	private Date oarzstdt;// 摊销开始年月
	private Date oarzexpdt;// 摊销到期年月
	private String oarzisexp;// 是否到期
	private String oarzcontract;// 合同期限
	private String oarznt;// 备注
	
	//库存表
	private String stoid;//库存编号
	private String stogrcliid;//团体客户代码
	private String stogrpnm;//客户名称
	private String stoproid;//商品代码
	private String stoprotype;//商品类型
	private String stoproname;//商品名称
	private Integer stoamount;//数量(入库/出库)
	private Integer storqnt;//剩余数量
	private Integer stoexp;//库存期限
	private String stoisexp;//库存是否超期
	private String stoamountun;//数量单位
	private Double stoproprice;//单价
	private Date stodate;//出入库日期
	private Integer stoactype;//动作（1、入库-1、出库）
	private String storemark;//备注
	private String stooprcd;//操作员代码
	private String stodisc;//是否已报废
	private String days;//超期天数
	private String isrepair;//是否维修订单
	
	private Double pricesbox;//盒式机价格高于
	
	private Integer gctexd;//库存期限
	
	public String getIsrepair() {
		return isrepair;
	}

	public void setIsrepair(String isrepair) {
		this.isrepair = isrepair;
	}

	public Integer getIvtyear() {
		return ivtyear;
	}

	public void setIvtyear(Integer ivtyear) {
		this.ivtyear = ivtyear;
	}

	public Integer getIvtmonth() {
		return ivtmonth;
	}

	public void setIvtmonth(Integer ivtmonth) {
		this.ivtmonth = ivtmonth;
	}

	public String getIvtgcltid() {
		return ivtgcltid;
	}

	public void setIvtgcltid(String ivtgcltid) {
		this.ivtgcltid = ivtgcltid;
	}

	public String getIvtcltid() {
		return ivtcltid;
	}

	public void setIvtcltid(String ivtcltid) {
		this.ivtcltid = ivtcltid;
	}

	public String getIvtpdtid() {
		return ivtpdtid;
	}

	public void setIvtpdtid(String ivtpdtid) {
		this.ivtpdtid = ivtpdtid;
	}

	public Integer getIvtlmqnt() {
		return ivtlmqnt;
	}

	public void setIvtlmqnt(Integer ivtlmqnt) {
		this.ivtlmqnt = ivtlmqnt;
	}

	public Integer getIvtlsqnt() {
		return ivtlsqnt;
	}

	public void setIvtlsqnt(Integer ivtlsqnt) {
		this.ivtlsqnt = ivtlsqnt;
	}

	public Integer getIvtpqnt() {
		return ivtpqnt;
	}

	public void setIvtpqnt(Integer ivtpqnt) {
		this.ivtpqnt = ivtpqnt;
	}

	public Double getIvtpamnt() {
		return ivtpamnt;
	}

	public void setIvtpamnt(Double ivtpamnt) {
		this.ivtpamnt = ivtpamnt;
	}

	public String getIvtoprcd() {
		return ivtoprcd;
	}

	public void setIvtoprcd(String ivtoprcd) {
		this.ivtoprcd = ivtoprcd;
	}

	public Date getIvtoprdt() {
		return ivtoprdt;
	}

	public void setIvtoprdt(Date ivtoprdt) {
		this.ivtoprdt = ivtoprdt;
	}

	public String getIvtnote() {
		return ivtnote;
	}

	public void setIvtnote(String ivtnote) {
		this.ivtnote = ivtnote;
	}

	public Double getIvtdiscount() {
		return ivtdiscount;
	}

	public void setIvtdiscount(Double ivtdiscount) {
		this.ivtdiscount = ivtdiscount;
	}

	public BigDecimal getIvtid() {
		return ivtid;
	}

	public void setIvtid(BigDecimal ivtid) {
		this.ivtid = ivtid;
	}

	public String getIvtcltnm() {
		return ivtcltnm;
	}

	public void setIvtcltnm(String ivtcltnm) {
		this.ivtcltnm = ivtcltnm;
	}

	public String getIvttype() {
		return ivttype;
	}

	public void setIvttype(String ivttype) {
		this.ivttype = ivttype;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getIvtfree() {
		return ivtfree;
	}

	public void setIvtfree(String ivtfree) {
		this.ivtfree = ivtfree;
	}

	public String getIvttry() {
		return ivttry;
	}

	public void setIvttry(String ivttry) {
		this.ivttry = ivttry;
	}

	
	public String getActstaid() {
		return actstaid;
	}

	public void setActstaid(String actstaid) {
		this.actstaid = actstaid;
	}

	public Integer getActstayear() {
		return actstayear;
	}

	public void setActstayear(Integer actstayear) {
		this.actstayear = actstayear;
	}

	public Integer getActstamonth() {
		return actstamonth;
	}

	public void setActstamonth(Integer actstamonth) {
		this.actstamonth = actstamonth;
	}

	public String getActstagcltid() {
		return actstagcltid;
	}

	public void setActstagcltid(String actstagcltid) {
		this.actstagcltid = actstagcltid;
	}

	public Integer getActmqnt() {
		return actmqnt;
	}

	public void setActmqnt(Integer actmqnt) {
		this.actmqnt = actmqnt;
	}

	public Integer getActsqnt() {
		return actsqnt;
	}

	public void setActsqnt(Integer actsqnt) {
		this.actsqnt = actsqnt;
	}

	public String getActsta() {
		return actsta;
	}

	public void setActsta(String actsta) {
		this.actsta = actsta;
	}

	public String getActstanote() {
		return actstanote;
	}

	public void setActstanote(String actstanote) {
		this.actstanote = actstanote;
	}

	public String getGctnm() {
		return gctnm;
	}

	public void setGctnm(String gctnm) {
		this.gctnm = gctnm;
	}

	public String getGctarea() {
		return gctarea;
	}

	public void setGctarea(String gctarea) {
		this.gctarea = gctarea;
	}

	public String getIctgender() {
		return ictgender;
	}

	public void setIctgender(String ictgender) {
		this.ictgender = ictgender;
	}

	public Integer getAges() {
		return ages;
	}

	public void setAges(Integer ages) {
		this.ages = ages;
	}

	public Integer getAget() {
		return aget;
	}

	public void setAget(Integer aget) {
		this.aget = aget;
	}

	public Double getPrices() {
		return prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	public Double getPricet() {
		return pricet;
	}

	public void setPricet(Double pricet) {
		this.pricet = pricet;
	}

	public String getEarcase() {
		return earcase;
	}

	public void setEarcase(String earcase) {
		this.earcase = earcase;
	}

	public String getAstgctid() {
		return astgctid;
	}

	public void setAstgctid(String astgctid) {
		this.astgctid = astgctid;
	}


	public String getPdtut() {
		return pdtut;
	}

	public void setPdtut(String pdtut) {
		this.pdtut = pdtut;
	}

	
	public String getAstut() {
		return astut;
	}

	public void setAstut(String astut) {
		this.astut = astut;
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
	
	public Date getStui() {
		return stui;
	}

	public void setStui(Date stui) {
		this.stui = stui;
	}

	public Date getEtui() {
		return etui;
	}

	public void setEtui(Date etui) {
		this.etui = etui;
	}

	public String getAstpdtnm() {
		return astpdtnm;
	}

	public void setAstpdtnm(String astpdtnm) {
		this.astpdtnm = astpdtnm;
	}

	public Double getAstprc() {
		return astprc;
	}

	public void setAstprc(Double astprc) {
		this.astprc = astprc;
	}

	public String getAstqnt() {
		return astqnt;
	}

	public void setAstqnt(String astqnt) {
		this.astqnt = astqnt;
	}

	public String getAstnote() {
		return astnote;
	}

	public void setAstnote(String astnote) {
		this.astnote = astnote;
	}

	public Date getAstdt() {
		return astdt;
	}

	public void setAstdt(Date astdt) {
		this.astdt = astdt;
	}

	public String getAstid() {
		return astid;
	}

	public void setAstid(String astid) {
		this.astid = astid;
	}

	public String getAstopr() {
		return astopr;
	}

	public void setAstopr(String astopr) {
		this.astopr = astopr;
	}

	public String getAstgctnm() {
		return astgctnm;
	}

	public void setAstgctnm(String astgctnm) {
		this.astgctnm = astgctnm;
	}

	public String getArzid() {
		return arzid;
	}

	public void setArzid(String arzid) {
		this.arzid = arzid;
	}

	public String getArzgctid() {
		return arzgctid;
	}

	public void setArzgctid(String arzgctid) {
		this.arzgctid = arzgctid;
	}

	public String getArzgctnm() {
		return arzgctnm;
	}

	public void setArzgctnm(String arzgctnm) {
		this.arzgctnm = arzgctnm;
	}

	public String getArzitem() {
		return arzitem;
	}

	public void setArzitem(String arzitem) {
		this.arzitem = arzitem;
	}

	
	public Date getArzdt() {
		return arzdt;
	}

	public void setArzdt(Date arzdt) {
		this.arzdt = arzdt;
	}

	public String getArzopr() {
		return arzopr;
	}

	public void setArzopr(String arzopr) {
		this.arzopr = arzopr;
	}

	public Double getHarzamount() {
		return harzamount;
	}

	public void setHarzamount(Double harzamount) {
		this.harzamount = harzamount;
	}

	public String getHarzmonth() {
		return harzmonth;
	}

	public void setHarzmonth(String harzmonth) {
		this.harzmonth = harzmonth;
	}

	public Double getHarzmon() {
		return harzmon;
	}

	public void setHarzmon(Double harzmon) {
		this.harzmon = harzmon;
	}

	public Date getHarzstdt() {
		return harzstdt;
	}

	public void setHarzstdt(Date harzstdt) {
		this.harzstdt = harzstdt;
	}

	public Date getHarzexpdt() {
		return harzexpdt;
	}

	public void setHarzexpdt(Date harzexpdt) {
		this.harzexpdt = harzexpdt;
	}

	public String getHarzisexp() {
		return harzisexp;
	}

	public void setHarzisexp(String harzisexp) {
		this.harzisexp = harzisexp;
	}

	public String getHarzcontract() {
		return harzcontract;
	}

	public void setHarzcontract(String harzcontract) {
		this.harzcontract = harzcontract;
	}

	public String getHarznt() {
		return harznt;
	}

	public void setHarznt(String harznt) {
		this.harznt = harznt;
	}

	public Double getFarzamount() {
		return farzamount;
	}

	public void setFarzamount(Double farzamount) {
		this.farzamount = farzamount;
	}

	public String getFarzmonth() {
		return farzmonth;
	}

	public void setFarzmonth(String farzmonth) {
		this.farzmonth = farzmonth;
	}

	public Double getFarzmon() {
		return farzmon;
	}

	public void setFarzmon(Double farzmon) {
		this.farzmon = farzmon;
	}

	public Date getFarzstdt() {
		return farzstdt;
	}

	public void setFarzstdt(Date farzstdt) {
		this.farzstdt = farzstdt;
	}

	public Date getFarzexpdt() {
		return farzexpdt;
	}

	public void setFarzexpdt(Date farzexpdt) {
		this.farzexpdt = farzexpdt;
	}

	public String getFarzisexp() {
		return farzisexp;
	}

	public void setFarzisexp(String farzisexp) {
		this.farzisexp = farzisexp;
	}

	public String getFarzcontract() {
		return farzcontract;
	}

	public void setFarzcontract(String farzcontract) {
		this.farzcontract = farzcontract;
	}

	public String getFarznt() {
		return farznt;
	}

	public void setFarznt(String farznt) {
		this.farznt = farznt;
	}

	public Double getDarzamount() {
		return darzamount;
	}

	public void setDarzamount(Double darzamount) {
		this.darzamount = darzamount;
	}

	public String getDarzmonth() {
		return darzmonth;
	}

	public void setDarzmonth(String darzmonth) {
		this.darzmonth = darzmonth;
	}

	public Double getDarzmon() {
		return darzmon;
	}

	public void setDarzmon(Double darzmon) {
		this.darzmon = darzmon;
	}

	public Date getDarzstdt() {
		return darzstdt;
	}

	public void setDarzstdt(Date darzstdt) {
		this.darzstdt = darzstdt;
	}

	public Date getDarzexpdt() {
		return darzexpdt;
	}

	public void setDarzexpdt(Date darzexpdt) {
		this.darzexpdt = darzexpdt;
	}

	public String getDarzisexp() {
		return darzisexp;
	}

	public void setDarzisexp(String darzisexp) {
		this.darzisexp = darzisexp;
	}

	public String getDarzcontract() {
		return darzcontract;
	}

	public void setDarzcontract(String darzcontract) {
		this.darzcontract = darzcontract;
	}

	public String getDarznt() {
		return darznt;
	}

	public void setDarznt(String darznt) {
		this.darznt = darznt;
	}

	public Double getAarzamount() {
		return aarzamount;
	}

	public void setAarzamount(Double aarzamount) {
		this.aarzamount = aarzamount;
	}

	public String getAarzmonth() {
		return aarzmonth;
	}

	public void setAarzmonth(String aarzmonth) {
		this.aarzmonth = aarzmonth;
	}

	public Double getAarzmon() {
		return aarzmon;
	}

	public void setAarzmon(Double aarzmon) {
		this.aarzmon = aarzmon;
	}

	public Date getAarzstdt() {
		return aarzstdt;
	}

	public void setAarzstdt(Date aarzstdt) {
		this.aarzstdt = aarzstdt;
	}

	public Date getAarzexpdt() {
		return aarzexpdt;
	}

	public void setAarzexpdt(Date aarzexpdt) {
		this.aarzexpdt = aarzexpdt;
	}

	public String getAarzisexp() {
		return aarzisexp;
	}

	public void setAarzisexp(String aarzisexp) {
		this.aarzisexp = aarzisexp;
	}

	public String getAarzcontract() {
		return aarzcontract;
	}

	public void setAarzcontract(String aarzcontract) {
		this.aarzcontract = aarzcontract;
	}

	public String getAarznt() {
		return aarznt;
	}

	public void setAarznt(String aarznt) {
		this.aarznt = aarznt;
	}

	public Double getTarzamount() {
		return tarzamount;
	}

	public void setTarzamount(Double tarzamount) {
		this.tarzamount = tarzamount;
	}

	public String getTarzmonth() {
		return tarzmonth;
	}

	public void setTarzmonth(String tarzmonth) {
		this.tarzmonth = tarzmonth;
	}

	public Double getTarzmon() {
		return tarzmon;
	}

	public void setTarzmon(Double tarzmon) {
		this.tarzmon = tarzmon;
	}

	public Date getTarzstdt() {
		return tarzstdt;
	}

	public void setTarzstdt(Date tarzstdt) {
		this.tarzstdt = tarzstdt;
	}

	public Date getTarzexpdt() {
		return tarzexpdt;
	}

	public void setTarzexpdt(Date tarzexpdt) {
		this.tarzexpdt = tarzexpdt;
	}

	public String getTarzisexp() {
		return tarzisexp;
	}

	public void setTarzisexp(String tarzisexp) {
		this.tarzisexp = tarzisexp;
	}

	public String getTarzcontract() {
		return tarzcontract;
	}

	public void setTarzcontract(String tarzcontract) {
		this.tarzcontract = tarzcontract;
	}

	public String getTarznt() {
		return tarznt;
	}

	public void setTarznt(String tarznt) {
		this.tarznt = tarznt;
	}

	public Double getOarzamount() {
		return oarzamount;
	}

	public void setOarzamount(Double oarzamount) {
		this.oarzamount = oarzamount;
	}

	public String getOarzmonth() {
		return oarzmonth;
	}

	public void setOarzmonth(String oarzmonth) {
		this.oarzmonth = oarzmonth;
	}

	public Double getOarzmon() {
		return oarzmon;
	}

	public void setOarzmon(Double oarzmon) {
		this.oarzmon = oarzmon;
	}

	public Date getOarzstdt() {
		return oarzstdt;
	}

	public void setOarzstdt(Date oarzstdt) {
		this.oarzstdt = oarzstdt;
	}

	public Date getOarzexpdt() {
		return oarzexpdt;
	}

	public void setOarzexpdt(Date oarzexpdt) {
		this.oarzexpdt = oarzexpdt;
	}

	public String getOarzisexp() {
		return oarzisexp;
	}

	public void setOarzisexp(String oarzisexp) {
		this.oarzisexp = oarzisexp;
	}

	public String getOarzcontract() {
		return oarzcontract;
	}

	public void setOarzcontract(String oarzcontract) {
		this.oarzcontract = oarzcontract;
	}

	public String getOarznt() {
		return oarznt;
	}

	public void setOarznt(String oarznt) {
		this.oarznt = oarznt;
	}

	public String getArzmonth() {
		return arzmonth;
	}

	public void setArzmonth(String arzmonth) {
		this.arzmonth = arzmonth;
	}

	public String getArzisexp() {
		return arzisexp;
	}

	public void setArzisexp(String arzisexp) {
		this.arzisexp = arzisexp;
	}

	public String getArzoprnm() {
		return arzoprnm;
	}

	public void setArzoprnm(String arzoprnm) {
		this.arzoprnm = arzoprnm;
	}

	public Double getArzamount() {
		return arzamount;
	}

	public void setArzamount(Double arzamount) {
		this.arzamount = arzamount;
	}

	public Double getArzmon() {
		return arzmon;
	}

	public void setArzmon(Double arzmon) {
		this.arzmon = arzmon;
	}

	public Date getArzstdt() {
		return arzstdt;
	}

	public void setArzstdt(Date arzstdt) {
		this.arzstdt = arzstdt;
	}

	public Date getArzexpdt() {
		return arzexpdt;
	}

	public void setArzexpdt(Date arzexpdt) {
		this.arzexpdt = arzexpdt;
	}

	public String getArznt() {
		return arznt;
	}

	public void setArznt(String arznt) {
		this.arznt = arznt;
	}

	public String getArzcontract() {
		return arzcontract;
	}

	public void setArzcontract(String arzcontract) {
		this.arzcontract = arzcontract;
	}

	public String getStoid() {
		return stoid;
	}

	public void setStoid(String stoid) {
		this.stoid = stoid;
	}

	public String getStogrcliid() {
		return stogrcliid;
	}

	public void setStogrcliid(String stogrcliid) {
		this.stogrcliid = stogrcliid;
	}

	public String getStogrpnm() {
		return stogrpnm;
	}

	public void setStogrpnm(String stogrpnm) {
		this.stogrpnm = stogrpnm;
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

	public Integer getStorqnt() {
		return storqnt;
	}

	public void setStorqnt(Integer storqnt) {
		this.storqnt = storqnt;
	}

	public Integer getStoexp() {
		return stoexp;
	}

	public void setStoexp(Integer stoexp) {
		this.stoexp = stoexp;
	}

	public String getStoisexp() {
		return stoisexp;
	}

	public void setStoisexp(String stoisexp) {
		this.stoisexp = stoisexp;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Integer getGctexd() {
		return gctexd;
	}

	public void setGctexd(Integer gctexd) {
		this.gctexd = gctexd;
	}

	public String getGctid() {
		return gctid;
	}

	public void setGctid(String gctid) {
		this.gctid = gctid;
	}

	public String getGctprovince() {
		return gctprovince;
	}

	public void setGctprovince(String gctprovince) {
		this.gctprovince = gctprovince;
	}

	public String getPdtnm() {
		return pdtnm;
	}

	public void setPdtnm(String pdtnm) {
		this.pdtnm = pdtnm;
	}

	public Double getPdtprc() {
		return pdtprc;
	}

	public void setPdtprc(Double pdtprc) {
		this.pdtprc = pdtprc;
	}

	public Double getFdtprc() {
		return fdtprc;
	}

	public void setFdtprc(Double fdtprc) {
		this.fdtprc = fdtprc;
	}

	public Integer getFdtqnt() {
		return fdtqnt;
	}

	public void setFdtqnt(Integer fdtqnt) {
		this.fdtqnt = fdtqnt;
	}

	public Date getFolchgdt() {
		return folchgdt;
	}

	public void setFolchgdt(Date folchgdt) {
		this.folchgdt = folchgdt;
	}

	public String getCltnm() {
		return	cltnm;
	}
	
	public void setCltnm(String cltnm) {
		this.cltnm =cltnm;
	}

	public String getNcdypname() {
		return ncdypname;
	}

	public void setNcdypname(String ncdypname) {
		this.ncdypname = ncdypname;
	}
	
	private String pdttype;//品牌
	public String getPdttype() {
		return pdttype;
	}
	public void setPdttype(String pdttype) {
		this.pdttype = pdttype;
	}
	private String foltype;//订单类型
	public String getFoltype() {
		return foltype;
	}
	public void setFoltype(String foltype) {
		this.foltype = foltype;
	}

	public Double getPricesbox() {
		return pricesbox;
	}

	public void setPricesbox(Double pricesbox) {
		this.pricesbox = pricesbox;
	}

	public String getGcttype() {
		return gcttype;
	}

	public void setGcttype(String gcttype) {
		this.gcttype = gcttype;
	}
	
	
	
}
