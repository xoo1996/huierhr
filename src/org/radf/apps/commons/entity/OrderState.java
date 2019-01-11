package org.radf.apps.commons.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

//收费基本信息表
public class OrderState extends EntitySupport {

	private String folno; // 订单号码
	private String fdtfno; // 订单号

	// 团体客户表
	private String folctid;// 客户代码
	private String folctnm; // 客户名称
	private String cltnm;//用户姓名
	private String ictgender; 	// 性别
	private String ictpcd; 		// 邮政编码
	private String icttel; 		// 联系电话
	private String ictaddr; 	// 联系地址
	private String ictpro;      //省份
	private String ictcity;     //市
	private String ictcounty;    //县
	private String ictphone; //手机
	private String ictdetailaddr;//详细地址
	private String ictlandline; //固定电话
	private String pdtnm; // 商品名称
	private String pdtid;// 商品代码
	private Integer fdtqnt;//数量
	private Integer fdtrqnt;//剩余数量
	
	private Integer fdtmqnt;//卖出数量
	private String fdtsnt; // 商品售出备注
	private Double fdtdisc;//普通商品扣率
	private Double fdtrprc; //普通商品实际价格
	private Date fdtcdt; //普通商品收费日期
	
	private Double fdtprc; // 售价
	private Double pdtprc; // 价格
	private Double discount;//扣率
	private Double deposit;//定金
	private Double balance;//应付余额
	private String folischg;//是否已收费
	private Date chargedate; //收费日期
	
	
	//维修商品用到的字段
	private BigDecimal repidentity; // 维修流水号
	private String repid; // 机身编号
	private Date repdate;// 送修日期
	// repwcode char(30) null, //修理原因（代码：REP）：废弃
	// represult char(30) null, //修理结果（代码：WCS）：废弃
	private Date repfdate; // 完工日期
	private String repoprcd; // 维修员代码
	private String repnote; // 备注
	private String repcltid; // 病人代码
	private String repcltnm; // 病人姓名
	private String repcls; // 修理类别(一月返修,二月返修,三月返修,其他)
	private Double repamt; // 维修金额
	private String repfolid; // 该维修记录生成的对应帐单号
	// private String repoldid; // 新机身编号
	// private String repbarcd; // 条码编号
	private String repfree; // 保修期(是:Y,否:N)
	private String repdeclare; // 故障详情(客户提出)
	private String repconfirmed; // 故障确认情况
	private String repaction1;// 维修措施一
	private String repaction2; // 维修措施二
	private String repaction3; // 维修措施三
	private String repaction4; // 维修措施四
	private String repaction5; // 维修措施五
	private String repaction6; // 维修措施六 //自由输入维修措施
	private String reppreamt; // 维修费估计（超过100元、超过200元…）
	// private String repno; // 第几次送厂维修(not used!)
	private String repattention; // 注意事项(客户用)
	private String repreger; // 填表人代码
	private String reppid;// 助听器型号
	private String repgctid; // 送修单位代码
	private String repregnames; // 其他维修员姓名
	private String reptip; // 温馨提示1
	private String reptip1; // 温馨提示2
	private String repsta;// 维修状态
	private String repcpy;// 送修厂家
	private Date reppdate; // 计划完工日期
	private String repiskp;//是否开票
	private Double repaprc;//费用预计
	
	private Date repischarged; // 维修费是否已收
	private Date repchargedate; // 维修费收费日期

	// 商品表
	private String reppnm; // 耳机名称（助听器名称）
	private Date foldt; // 订货日期
	private Date folsdt; // 发货日期
	private String foltype;// 订单类别
	
	private String folsno; // 快递号
	private String folsta; // 订单状态
	private String folnt; // 备注
	private String foldes; // 发往惠耳点
	// 临时
	private Date start; //开始日期
	private Date end;	//结束日期
	public String getFolno() {
		return folno;
	}
	public void setFolno(String folno) {
		this.folno = folno;
	}
	public String getFolctid() {
		return folctid;
	}
	public void setFolctid(String folctid) {
		this.folctid = folctid;
	}
	public String getFolctnm() {
		return folctnm;
	}
	public void setFolctnm(String folctnm) {
		this.folctnm = folctnm;
	}
	public String getCltnm() {
		return cltnm;
	}
	public void setCltnm(String cltnm) {
		this.cltnm = cltnm;
	}
	
	public String getIctgender() {
		return ictgender;
	}
	public void setIctgender(String ictgender) {
		this.ictgender = ictgender;
	}
	
	public String getIctpcd() {
		return ictpcd;
	}
	public void setIctpcd(String ictpcd) {
		this.ictpcd = ictpcd;
	}
	
	public String getIcttel() {
		return icttel;
	}
	public void setIcttel(String icttel) {
		this.icttel = icttel;
	}
	public String getIctaddr() {
		return ictaddr;
	}
	public void setIctaddr(String ictaddr) {
		this.ictaddr = ictaddr;
	}
	
	
	
	public String getPdtnm() {
		return pdtnm;
	}
	public void setPdtnm(String pdtnm) {
		this.pdtnm = pdtnm;
	}
	public String getPdtid() {
		return pdtid;
	}
	public void setPdtid(String pdtid) {
		this.pdtid = pdtid;
	}
	public Integer getFdtqnt() {
		return fdtqnt;
	}
	public void setFdtqnt(Integer fdtqnt) {
		this.fdtqnt = fdtqnt;
	}
	public Double getFdtprc() {
		return fdtprc;
	}
	public void setFdtprc(Double fdtprc) {
		this.fdtprc = fdtprc;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getDeposit() {
		return deposit;
	}
	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Date getFoldt() {
		return foldt;
	}
	public void setFoldt(Date foldt) {
		this.foldt = foldt;
	}
	public Date getFolsdt() {
		return folsdt;
	}
	public void setFolsdt(Date folsdt) {
		this.folsdt = folsdt;
	}
	public String getFoltype() {
		return foltype;
	}
	public void setFoltype(String foltype) {
		this.foltype = foltype;
	}
	public String getFolsno() {
		return folsno;
	}
	public void setFolsno(String folsno) {
		this.folsno = folsno;
	}
	public String getFolsta() {
		return folsta;
	}
	public void setFolsta(String folsta) {
		this.folsta = folsta;
	}
	public String getFolnt() {
		return folnt;
	}
	public void setFolnt(String folnt) {
		this.folnt = folnt;
	}
	public String getFoldes() {
		return foldes;
	}
	public void setFoldes(String foldes) {
		this.foldes = foldes;
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
	public String getFdtfno() {
		return fdtfno;
	}
	public void setFdtfno(String fdtfno) {
		this.fdtfno = fdtfno;
	}
	public String getFolischg() {
		return folischg;
	}
	public void setFolischg(String folischg) {
		this.folischg = folischg;
	}
	public Double getPdtprc() {
		return pdtprc;
	}
	public void setPdtprc(Double pdtprc) {
		this.pdtprc = pdtprc;
	}
	public BigDecimal getRepidentity() {
		return repidentity;
	}
	public void setRepidentity(BigDecimal repidentity) {
		this.repidentity = repidentity;
	}
	public String getRepid() {
		return repid;
	}
	public void setRepid(String repid) {
		this.repid = repid;
	}
	public Date getRepdate() {
		return repdate;
	}
	public void setRepdate(Date repdate) {
		this.repdate = repdate;
	}
	public Date getRepfdate() {
		return repfdate;
	}
	public void setRepfdate(Date repfdate) {
		this.repfdate = repfdate;
	}
	public String getRepoprcd() {
		return repoprcd;
	}
	public void setRepoprcd(String repoprcd) {
		this.repoprcd = repoprcd;
	}
	public String getRepnote() {
		return repnote;
	}
	public void setRepnote(String repnote) {
		this.repnote = repnote;
	}
	public String getRepcltid() {
		return repcltid;
	}
	public void setRepcltid(String repcltid) {
		this.repcltid = repcltid;
	}
	public String getRepcltnm() {
		return repcltnm;
	}
	public void setRepcltnm(String repcltnm) {
		this.repcltnm = repcltnm;
	}
	public String getRepcls() {
		return repcls;
	}
	public void setRepcls(String repcls) {
		this.repcls = repcls;
	}
	public Double getRepamt() {
		return repamt;
	}
	public void setRepamt(Double repamt) {
		this.repamt = repamt;
	}
	public String getRepfolid() {
		return repfolid;
	}
	public void setRepfolid(String repfolid) {
		this.repfolid = repfolid;
	}
	public String getRepfree() {
		return repfree;
	}
	public void setRepfree(String repfree) {
		this.repfree = repfree;
	}
	public String getRepdeclare() {
		return repdeclare;
	}
	public void setRepdeclare(String repdeclare) {
		this.repdeclare = repdeclare;
	}
	public String getRepconfirmed() {
		return repconfirmed;
	}
	public void setRepconfirmed(String repconfirmed) {
		this.repconfirmed = repconfirmed;
	}
	public String getRepaction1() {
		return repaction1;
	}
	public void setRepaction1(String repaction1) {
		this.repaction1 = repaction1;
	}
	public String getRepaction2() {
		return repaction2;
	}
	public void setRepaction2(String repaction2) {
		this.repaction2 = repaction2;
	}
	public String getRepaction3() {
		return repaction3;
	}
	public void setRepaction3(String repaction3) {
		this.repaction3 = repaction3;
	}
	public String getRepaction4() {
		return repaction4;
	}
	public void setRepaction4(String repaction4) {
		this.repaction4 = repaction4;
	}
	public String getRepaction5() {
		return repaction5;
	}
	public void setRepaction5(String repaction5) {
		this.repaction5 = repaction5;
	}
	public String getRepaction6() {
		return repaction6;
	}
	public void setRepaction6(String repaction6) {
		this.repaction6 = repaction6;
	}
	public String getReppreamt() {
		return reppreamt;
	}
	public void setReppreamt(String reppreamt) {
		this.reppreamt = reppreamt;
	}
	public String getRepattention() {
		return repattention;
	}
	public void setRepattention(String repattention) {
		this.repattention = repattention;
	}
	public String getRepreger() {
		return repreger;
	}
	public void setRepreger(String repreger) {
		this.repreger = repreger;
	}
	public String getReppid() {
		return reppid;
	}
	public void setReppid(String reppid) {
		this.reppid = reppid;
	}
	public String getRepgctid() {
		return repgctid;
	}
	public void setRepgctid(String repgctid) {
		this.repgctid = repgctid;
	}
	public String getRepregnames() {
		return repregnames;
	}
	public void setRepregnames(String repregnames) {
		this.repregnames = repregnames;
	}
	public String getReptip() {
		return reptip;
	}
	public void setReptip(String reptip) {
		this.reptip = reptip;
	}
	public String getReptip1() {
		return reptip1;
	}
	public void setReptip1(String reptip1) {
		this.reptip1 = reptip1;
	}
	public String getRepsta() {
		return repsta;
	}
	public void setRepsta(String repsta) {
		this.repsta = repsta;
	}
	public String getRepcpy() {
		return repcpy;
	}
	public void setRepcpy(String repcpy) {
		this.repcpy = repcpy;
	}
	public Date getReppdate() {
		return reppdate;
	}
	public void setReppdate(Date reppdate) {
		this.reppdate = reppdate;
	}
	public String getRepiskp() {
		return repiskp;
	}
	public void setRepiskp(String repiskp) {
		this.repiskp = repiskp;
	}
	public Double getRepaprc() {
		return repaprc;
	}
	public void setRepaprc(Double repaprc) {
		this.repaprc = repaprc;
	}
	public String getReppnm() {
		return reppnm;
	}
	public void setReppnm(String reppnm) {
		this.reppnm = reppnm;
	}
	public Date getRepischarged() {
		return repischarged;
	}
	public void setRepischarged(Date repischarged) {
		this.repischarged = repischarged;
	}
	public Date getRepchargedate() {
		return repchargedate;
	}
	public void setRepchargedate(Date repchargedate) {
		this.repchargedate = repchargedate;
	}
	public Integer getFdtrqnt() {
		return fdtrqnt;
	}
	public void setFdtrqnt(Integer fdtrqnt) {
		this.fdtrqnt = fdtrqnt;
	}
	public Integer getFdtmqnt() {
		return fdtmqnt;
	}
	public void setFdtmqnt(Integer fdtmqnt) {
		this.fdtmqnt = fdtmqnt;
	}
	public String getFdtsnt() {
		return fdtsnt;
	}
	public void setFdtsnt(String fdtsnt) {
		this.fdtsnt = fdtsnt;
	}
	public Double getFdtdisc() {
		return fdtdisc;
	}
	public void setFdtdisc(Double fdtdisc) {
		this.fdtdisc = fdtdisc;
	}
	public Double getFdtrprc() {
		return fdtrprc;
	}
	public void setFdtrprc(Double fdtrprc) {
		this.fdtrprc = fdtrprc;
	}
	public Date getFdtcdt() {
		return fdtcdt;
	}
	public void setFdtcdt(Date fdtcdt) {
		this.fdtcdt = fdtcdt;
	}
	
	public Date getChargedate() {
		return chargedate;
	}
	public void setChargedate(Date chargedate) {
		this.chargedate = chargedate;
	}
	public String getIctpro() {
		return ictpro;
	}
	public void setIctpro(String ictpro) {
		this.ictpro = ictpro;
	}
	public String getIctcity() {
		return ictcity;
	}
	public void setIctcity(String ictcity) {
		this.ictcity = ictcity;
	}
	public String getIctcounty() {
		return ictcounty;
	}
	public void setIctcounty(String ictcounty) {
		this.ictcounty = ictcounty;
	}
	
	
	public String getIctdetailaddr() {
		return ictdetailaddr;
	}
	public void setIctdetailaddr(String ictdetailaddr) {
		this.ictdetailaddr = ictdetailaddr;
	}
	public String getIctphone() {
		return ictphone;
	}
	public void setIctphone(String ictphone) {
		this.ictphone = ictphone;
	}
	public String getIctlandline() {
		return ictlandline;
	}
	public void setIctlandline(String ictlandline) {
		this.ictlandline = ictlandline;
	}
	
}
