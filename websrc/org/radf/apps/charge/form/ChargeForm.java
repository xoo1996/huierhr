package org.radf.apps.charge.form;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.struts.action.ActionForm;

/*定制商品收费详细数据项*/

public class ChargeForm extends ActionForm{
	private String folno; // 订单号码
	private String folctid;// 客户代码	
	private String folctnm; // 客户名称
	private Date foldt; // 订货日期
	private Date folsdt; // 发货日期
	private String foltype;// 订单类别
	private String folsno; // 快递号
	private String folsta; // 订单状态
	private String folnt; // 备注
	private String foldes; // 发往惠耳点
	private String foladdr;//联系地址
	private Double deposit;//定金
	private Double balance;//应付余额
	private String folischg;//是否已收费
	private Double fdtrecmon;
	private Date fdtrecheaddt;
	private Date chgrecdt;
	private Date ncdrecdt;
	private Date ncdrecheaddt;
	private String foldtty;
	private String ncddtty;
	private String ictid; 		// 客户代码
	private String ictnm; 		// 姓名
	private String ictgctid; 	// 所属团体代码
	private String ictpnm; 		// 家长姓名
	private String ictgender; 	// 性别
	private Date ictbdt; 		// 出生日期
	private String ictaddr; 	// 联系地址
	private String ictpcd; 		// 邮政编码
	private String icttel; 		// 联系电话
	private String ictphis; 	// 使用过何种助听器（盒式、耳道式、耳背式、未戴过）
	private String ictnt; 		// 备注
	private String ictsrc; 		// 来源
	private String ictfrom;     //来源2
	private String ictoprcd; 	// 操作员代码
	private Date ictdate; 		// 日期
    private String fdtmklr;//左右耳
	
	private String pdtnm; //助听器型号（商品名称）
	private String pdtid;// 商品代码
	private Double pdtprc; // 售价
	
	private Double folurgfee;//加急费
	private Double xubaofee;//加急费
	private String folurgischg;//是否收加急费
	private Double folurgrfee;//实收加急费
	private Double fdtprc; //售价
	private Double fdtdprc;//原价
	private Integer fdtqnt;//数量
	private Integer fdtrqnt;//剩余数量
	private Integer fdtmqnt;//卖出数量
	private String ictpro;      //省份
	private String ictcity;     //市
	private String ictcounty;    //县
	private String ictphone; //手机
	private String ictlandline; //固定电话
	private String ictdetailaddr;//详细地址
	private String fdtsnt; // 商品售出备注
	private Double fdtdisc;//普通商品扣率
	private Double fdtrprc; //普通商品实际价格
	private Date fdtcdt; //普通商品收费日期
	
	//普通商品收费所用字段
	private String chgid;//收费流水号
	private String chggcltid;//团体客户代码
	private String chgcltid;//个人客户代码
	private Date chgdt;//收费日期
	
	private String ncdid;//收费流水号
	private String ncdoid;
	private String ncdpid;//商品代码
	private Integer ncdqnt;//售出数量
	private Double ncddis;//扣率
	private Double ncdmon;//实际收费
	private String ncdnt;//收费备注
	private String ncdsta;
	private String pdtut;//商品单位
	private String ncdypname;//验配师姓名
	
	//库存字段
	private Integer stoid;//库存编号
	private String stogrcliid;//团体客户代码
	private String stoproid;//商品代码
	private String stoprotype;//商品类别
	private String stoproname;//商品名称
	private Integer stoamount;//数量
	private String stoamountun;//数量单位
	private Double stoproprice;//单价
	private String  stoactype;//动作
	private Date stodate;//日期
	private String stooprcd;//操作员代码
	private String storemark;//备注
	private String stodisc;//是否报废
	private String stocllar;
	private String stoclmid;
	private String stoclsam;
	private String stoprodsc;
	private String isrepair;//是否维修订单
	private String fdtnt; // 备注
	private Double discount;//扣率

	private String cltnm;//用户姓名
	private String gctnm;
	private Date start; //开始日期
	private Date end;	//结束日期
	private Date chargedate; //收费日期
	private Date folrecdt;
	private String ncdrnt;
	
	/*
	 * 维修用到的字段
	 */
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
	private String reppid; // 助听器型号
	private String repgctid; // 送修单位代码
	private String repregnames; // 其他维修员姓名
	private String reptip; // 温馨提示1
	private String reptip1; // 温馨提示2
	private String repsta; // 维修状态
	private String repcpy; // 送修厂商
	private Date reppdate;// 计划完成日期
	private String repiskp;//是否开票
	private Double repaprc;//费用预计
	// 团体客户表
	private String repgctnm; // 团体客户名称
	// 商品表
	private String reppnm; // 耳机名称
	
	private Date repischarged; // 维修费是否已收
	private Date repchgdt; // 维修费收费日期
	
	
	// 听力测试记录表

	// 左耳骨导
	private String lg250;
	private String lg500;
	private String lg1000;
	private String lg2000;
	private String lg4000;
	private String lgavg;

	// 左耳气导
	private String lq250;
	private String lq500;
	private String lq1000;
	private String lq2000;
	private String lq4000;
	private String lqavg;

	// 右耳骨导
	private String rg250;
	private String rg500;
	private String rg1000;
	private String rg2000;
	private String rg4000;
	private String rgavg;

	// 右耳气导
	private String rq250;
	private String rq500;
	private String rq1000;
	private String rq2000;
	private String rq4000;
	private String rqavg;
   
	//新加骨导气导
		private String lg750;
		private String lg1500;
		private String lg3000;
		private String lg6000;
		
		private String lq750;
		private String lq1500;
		private String lq3000;
		private String lq6000;
		
		private String rg750;
		private String rg1500;
		private String rg3000;
		private String rg6000;
		
		private String rq750;
		private String rq1500;
		private String rq3000;
		private String rq6000;
	//机身编号
	private String jsid;
	private Date baoxiudate;//保修日期
	
	
	 //客户详细信息
    private String losetime;
	private String lstnote;
	private String shoushushi;
	private String sssnote;
	private String yichuanshi;
	private String ycsnote;
	private String erming;
	private String xuanyun;
	private String gaoxueya;
	private String gaoxuezhi;
	private String tangniaobing;
	private String tnbnote;
	private String zhutingqi;
	private String ztqtime;
	private String ztqtype;
	private String ztqxg;
	
	private String jixing;
	private String yanzheng;
	private String yznote;
	private String[] gumo;
	private String gmnote;
	private String[] jiancha;
	private String jcnote;
	private String[] chuandao;
	private String cdnote;
	private String[] ganyin;
	private String gynote;
	private String cdxing;
	private String gyxing;
	private String hunhexing;
	private String hhxnote;
	private String[] chuli;
	private String clnote;
	
	
	
	public String getIsrepair() {
		return isrepair;
	}
	public void setIsrepair(String isrepair) {
		this.isrepair = isrepair;
	}
	public String getLosetime() {
		return losetime;
	}
	public void setLosetime(String losetime) {
		this.losetime = losetime;
	}
	public String getLstnote() {
		return lstnote;
	}
	public void setLstnote(String lstnote) {
		this.lstnote = lstnote;
	}
	public String getShoushushi() {
		return shoushushi;
	}
	public void setShoushushi(String shoushushi) {
		this.shoushushi = shoushushi;
	}
	public String getSssnote() {
		return sssnote;
	}
	public void setSssnote(String sssnote) {
		this.sssnote = sssnote;
	}
	public String getYichuanshi() {
		return yichuanshi;
	}
	public void setYichuanshi(String yichuanshi) {
		this.yichuanshi = yichuanshi;
	}
	public String getYcsnote() {
		return ycsnote;
	}
	public void setYcsnote(String ycsnote) {
		this.ycsnote = ycsnote;
	}
	public String getErming() {
		return erming;
	}
	public void setErming(String erming) {
		this.erming = erming;
	}
	public String getXuanyun() {
		return xuanyun;
	}
	public void setXuanyun(String xuanyun) {
		this.xuanyun = xuanyun;
	}
	public String getGaoxueya() {
		return gaoxueya;
	}
	public void setGaoxueya(String gaoxueya) {
		this.gaoxueya = gaoxueya;
	}
	public String getGaoxuezhi() {
		return gaoxuezhi;
	}
	public void setGaoxuezhi(String gaoxuezhi) {
		this.gaoxuezhi = gaoxuezhi;
	}
	public String getTangniaobing() {
		return tangniaobing;
	}
	public void setTangniaobing(String tangniaobing) {
		this.tangniaobing = tangniaobing;
	}
	public String getTnbnote() {
		return tnbnote;
	}
	public void setTnbnote(String tnbnote) {
		this.tnbnote = tnbnote;
	}
	public String getZhutingqi() {
		return zhutingqi;
	}
	public void setZhutingqi(String zhutingqi) {
		this.zhutingqi = zhutingqi;
	}
	public String getZtqtime() {
		return ztqtime;
	}
	public void setZtqtime(String ztqtime) {
		this.ztqtime = ztqtime;
	}
	public String getZtqtype() {
		return ztqtype;
	}
	public void setZtqtype(String ztqtype) {
		this.ztqtype = ztqtype;
	}
	public String getZtqxg() {
		return ztqxg;
	}
	public void setZtqxg(String ztqxg) {
		this.ztqxg = ztqxg;
	}
	public String getJixing() {
		return jixing;
	}
	public void setJixing(String jixing) {
		this.jixing = jixing;
	}
	public String getYanzheng() {
		return yanzheng;
	}
	public void setYanzheng(String yanzheng) {
		this.yanzheng = yanzheng;
	}
	public String getYznote() {
		return yznote;
	}
	public void setYznote(String yznote) {
		this.yznote = yznote;
	}
	public String[] getGumo() {
		return gumo;
	}
	public void setGumo(String[] gumo) {
		this.gumo = gumo;
	}
	public String getGmnote() {
		return gmnote;
	}
	public void setGmnote(String gmnote) {
		this.gmnote = gmnote;
	}
	public String[] getJiancha() {
		return jiancha;
	}
	public void setJiancha(String[] jiancha) {
		this.jiancha = jiancha;
	}
	public String getJcnote() {
		return jcnote;
	}
	public void setJcnote(String jcnote) {
		this.jcnote = jcnote;
	}
	public String[] getChuandao() {
		return chuandao;
	}
	public void setChuandao(String[] chuandao) {
		this.chuandao = chuandao;
	}
	public String getCdnote() {
		return cdnote;
	}
	public void setCdnote(String cdnote) {
		this.cdnote = cdnote;
	}
	public String[] getGanyin() {
		return ganyin;
	}
	public void setGanyin(String[] ganyin) {
		this.ganyin = ganyin;
	}
	public String getGynote() {
		return gynote;
	}
	public void setGynote(String gynote) {
		this.gynote = gynote;
	}
	public String getCdxing() {
		return cdxing;
	}
	public void setCdxing(String cdxing) {
		this.cdxing = cdxing;
	}
	public String getGyxing() {
		return gyxing;
	}
	public void setGyxing(String gyxing) {
		this.gyxing = gyxing;
	}
	public String getHunhexing() {
		return hunhexing;
	}
	public void setHunhexing(String hunhexing) {
		this.hunhexing = hunhexing;
	}
	public String getHhxnote() {
		return hhxnote;
	}
	public void setHhxnote(String hhxnote) {
		this.hhxnote = hhxnote;
	}
	public String[] getChuli() {
		return chuli;
	}
	public void setChuli(String[] chuli) {
		this.chuli = chuli;
	}
	public String getClnote() {
		return clnote;
	}
	public void setClnote(String clnote) {
		this.clnote = clnote;
	}
	public Date getBaoxiudate() {
		return baoxiudate;
	}
	public void setBaoxiudate(Date baoxiudate) {
		this.baoxiudate = baoxiudate;
	}
	public String getJsid() {
		return jsid;
	}
	public void setJsid(String jsid) {
		this.jsid = jsid;
	}
	public String getFdtmklr() {
		return fdtmklr;
	}
	public void setFdtmklr(String fdtmklr) {
		this.fdtmklr = fdtmklr;
	}
	
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
	public String getFoladdr() {
		return foladdr;
	}
	public void setFoladdr(String foladdr) {
		this.foladdr = foladdr;
	}
	public String getIctgender() {
		return ictgender;
	}
	public void setIctgender(String ictgender) {
		this.ictgender = ictgender;
	}
	public String getIcttel() {
		return icttel;
	}
	public void setIcttel(String icttel) {
		this.icttel = icttel;
	}
	public String getIctpcd() {
		return ictpcd;
	}
	public void setIctpcd(String ictpcd) {
		this.ictpcd = ictpcd;
	}
	public String getPdtnm() {
		return pdtnm;
	}
	public void setPdtnm(String pdtnm) {
		this.pdtnm = pdtnm;
	}
	public Double getFdtprc() {
		return fdtprc;
	}
	public void setFdtprc(Double fdtprc) {
		this.fdtprc = fdtprc;
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
	public String getFolischg() {
		return folischg;
	}
	public void setFolischg(String folischg) {
		this.folischg = folischg;
	}
	public Date getChargedate() {
		return chargedate;
	}
	public void setChargedate(Date chargedate) {
		this.chargedate = chargedate;
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
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
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
	public Double getPdtprc() {
		return pdtprc;
	}
	public void setPdtprc(Double pdtprc) {
		this.pdtprc = pdtprc;
	}
	public String getIctaddr() {
		return ictaddr;
	}
	public void setIctaddr(String ictaddr) {
		this.ictaddr = ictaddr;
	}
	public String getFdtnt() {
		return fdtnt;
	}
	public void setFdtnt(String fdtnt) {
		this.fdtnt = fdtnt;
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
	public String getRepgctnm() {
		return repgctnm;
	}
	public void setRepgctnm(String repgctnm) {
		this.repgctnm = repgctnm;
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
	public Date getRepchgdt() {
		return repchgdt;
	}
	public void setRepchgdt(Date repchgdt) {
		this.repchgdt = repchgdt;
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
	public String getChgid() {
		return chgid;
	}
	public void setChgid(String chgid) {
		this.chgid = chgid;
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
	
	public Date getChgdt() {
		return chgdt;
	}
	public void setChgdt(Date chgdt) {
		this.chgdt = chgdt;
	}

	public Date getFdtcdt() {
		return fdtcdt;
	}
	public void setFdtcdt(Date fdtcdt) {
		this.fdtcdt = fdtcdt;
	}
	public String getPdtut() {
		return pdtut;
	}
	public void setPdtut(String pdtut) {
		this.pdtut = pdtut;
	}
	public String getStoactype() {
		return stoactype;
	}
	public void setStoactype(String stoactype) {
		this.stoactype = stoactype;
	}
	public Integer getStoid() {
		return stoid;
	}
	public void setStoid(Integer stoid) {
		this.stoid = stoid;
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
	public String getIctpnm() {
		return ictpnm;
	}
	public void setIctpnm(String ictpnm) {
		this.ictpnm = ictpnm;
	}

	public String getIctid() {
		return ictid;
	}
	public void setIctid(String ictid) {
		this.ictid = ictid;
	}
	public String getIctnm() {
		return ictnm;
	}
	public void setIctnm(String ictnm) {
		this.ictnm = ictnm;
	}
	public Date getIctbdt() {
		return ictbdt;
	}
	public void setIctbdt(Date ictbdt) {
		this.ictbdt = ictbdt;
	}
	public String getIctoprcd() {
		return ictoprcd;
	}
	public void setIctoprcd(String ictoprcd) {
		this.ictoprcd = ictoprcd;
	}
	public Date getIctdate() {
		return ictdate;
	}
	public void setIctdate(Date ictdate) {
		this.ictdate = ictdate;
	}
	public String getIctgctid() {
		return ictgctid;
	}
	public void setIctgctid(String ictgctid) {
		this.ictgctid = ictgctid;
	}
	public String getIctphis() {
		return ictphis;
	}
	public void setIctphis(String ictphis) {
		this.ictphis = ictphis;
	}
	public String getIctsrc() {
		return ictsrc;
	}
	public void setIctsrc(String ictsrc) {
		this.ictsrc = ictsrc;
	}

	public String getIctnt() {
		return ictnt;
	}
	public void setIctnt(String ictnt) {
		this.ictnt = ictnt;
	}
	public String getNcdpid() {
		return ncdpid;
	}
	public void setNcdpid(String ncdpid) {
		this.ncdpid = ncdpid;
	}
	public Integer getNcdqnt() {
		return ncdqnt;
	}
	public void setNcdqnt(Integer ncdqnt) {
		this.ncdqnt = ncdqnt;
	}
	public Double getNcddis() {
		return ncddis;
	}
	public void setNcddis(Double ncddis) {
		this.ncddis = ncddis;
	}
	public Double getNcdmon() {
		return ncdmon;
	}
	public void setNcdmon(Double ncdmon) {
		this.ncdmon = ncdmon;
	}
	public String getNcdnt() {
		return ncdnt;
	}
	public void setNcdnt(String ncdnt) {
		this.ncdnt = ncdnt;
	}
	public String getStooprcd() {
		return stooprcd;
	}
	public void setStooprcd(String stooprcd) {
		this.stooprcd = stooprcd;
	}
	public String getNcdid() {
		return ncdid;
	}
	public void setNcdid(String ncdid) {
		this.ncdid = ncdid;
	}
	public String getStoremark() {
		return storemark;
	}
	public void setStoremark(String storemark) {
		this.storemark = storemark;
	}
	public String getStodisc() {
		return stodisc;
	}
	public void setStodisc(String stodisc) {
		this.stodisc = stodisc;
	}
	public String getLg250() {
		return lg250;
	}
	public void setLg250(String lg250) {
		this.lg250 = lg250;
	}
	public String getLg500() {
		return lg500;
	}
	public void setLg500(String lg500) {
		this.lg500 = lg500;
	}
	public String getLg1000() {
		return lg1000;
	}
	public void setLg1000(String lg1000) {
		this.lg1000 = lg1000;
	}
	public String getLg2000() {
		return lg2000;
	}
	public void setLg2000(String lg2000) {
		this.lg2000 = lg2000;
	}
	public String getLg4000() {
		return lg4000;
	}
	public void setLg4000(String lg4000) {
		this.lg4000 = lg4000;
	}
	public String getLgavg() {
		return lgavg;
	}
	public void setLgavg(String lgavg) {
		this.lgavg = lgavg;
	}
	public String getLq250() {
		return lq250;
	}
	public void setLq250(String lq250) {
		this.lq250 = lq250;
	}
	public String getLq500() {
		return lq500;
	}
	public void setLq500(String lq500) {
		this.lq500 = lq500;
	}
	public String getLq1000() {
		return lq1000;
	}
	public void setLq1000(String lq1000) {
		this.lq1000 = lq1000;
	}
	public String getLq2000() {
		return lq2000;
	}
	public void setLq2000(String lq2000) {
		this.lq2000 = lq2000;
	}
	public String getLq4000() {
		return lq4000;
	}
	public void setLq4000(String lq4000) {
		this.lq4000 = lq4000;
	}
	public String getLqavg() {
		return lqavg;
	}
	public void setLqavg(String lqavg) {
		this.lqavg = lqavg;
	}
	public String getRg250() {
		return rg250;
	}
	public void setRg250(String rg250) {
		this.rg250 = rg250;
	}
	public String getRg500() {
		return rg500;
	}
	public void setRg500(String rg500) {
		this.rg500 = rg500;
	}
	public String getRg1000() {
		return rg1000;
	}
	public void setRg1000(String rg1000) {
		this.rg1000 = rg1000;
	}
	public String getRg2000() {
		return rg2000;
	}
	public void setRg2000(String rg2000) {
		this.rg2000 = rg2000;
	}
	public String getRg4000() {
		return rg4000;
	}
	public void setRg4000(String rg4000) {
		this.rg4000 = rg4000;
	}
	public String getRgavg() {
		return rgavg;
	}
	public void setRgavg(String rgavg) {
		this.rgavg = rgavg;
	}
	public String getRq250() {
		return rq250;
	}
	public void setRq250(String rq250) {
		this.rq250 = rq250;
	}
	public String getRq500() {
		return rq500;
	}
	public void setRq500(String rq500) {
		this.rq500 = rq500;
	}
	public String getRq1000() {
		return rq1000;
	}
	public void setRq1000(String rq1000) {
		this.rq1000 = rq1000;
	}
	public String getRq2000() {
		return rq2000;
	}
	public void setRq2000(String rq2000) {
		this.rq2000 = rq2000;
	}
	public String getRq4000() {
		return rq4000;
	}
	public void setRq4000(String rq4000) {
		this.rq4000 = rq4000;
	}
	public String getRqavg() {
		return rqavg;
	}
	public void setRqavg(String rqavg) {
		this.rqavg = rqavg;
	}
	public Double getFdtdprc() {
		return fdtdprc;
	}
	public void setFdtdprc(Double fdtdprc) {
		this.fdtdprc = fdtdprc;
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
	public Date getFolrecdt() {
		return folrecdt;
	}
	public void setFolrecdt(Date folrecdt) {
		this.folrecdt = folrecdt;
	}
	public Date getFdtrecheaddt() {
		return fdtrecheaddt;
	}
	public void setFdtrecheaddt(Date fdtrecheaddt) {
		this.fdtrecheaddt = fdtrecheaddt;
	}
	public String getNcdsta() {
		return ncdsta;
	}
	public void setNcdsta(String ncdsta) {
		this.ncdsta = ncdsta;
	}
	public String getGctnm() {
		return gctnm;
	}
	public void setGctnm(String gctnm) {
		this.gctnm = gctnm;
	}
	public String getNcdrnt() {
		return ncdrnt;
	}
	public void setNcdrnt(String ncdrnt) {
		this.ncdrnt = ncdrnt;
	}
	public Double getFdtrecmon() {
		return fdtrecmon;
	}
	public void setFdtrecmon(Double fdtrecmon) {
		this.fdtrecmon = fdtrecmon;
	}
	public Date getChgrecdt() {
		return chgrecdt;
	}
	public void setChgrecdt(Date chgrecdt) {
		this.chgrecdt = chgrecdt;
	}
	public Date getNcdrecdt() {
		return ncdrecdt;
	}
	public void setNcdrecdt(Date ncdrecdt) {
		this.ncdrecdt = ncdrecdt;
	}
	public Date getNcdrecheaddt() {
		return ncdrecheaddt;
	}
	public void setNcdrecheaddt(Date ncdrecheaddt) {
		this.ncdrecheaddt = ncdrecheaddt;
	}
	public String getNcddtty() {
		return ncddtty;
	}
	public void setNcddtty(String ncddtty) {
		this.ncddtty = ncddtty;
	}
	public String getFoldtty() {
		return foldtty;
	}
	public void setFoldtty(String foldtty) {
		this.foldtty = foldtty;
	}
	public String getNcdoid() {
		return ncdoid;
	}
	public void setNcdoid(String ncdoid) {
		this.ncdoid = ncdoid;
	}
	public String getNcdypname() {
		return ncdypname;
	}
	public void setNcdypname(String ncdypname) {
		this.ncdypname = ncdypname;
	}
	public Double getFolurgfee() {
		return folurgfee;
	}
	public void setFolurgfee(Double folurgfee) {
		this.folurgfee = folurgfee;
	}
	public String getFolurgischg() {
		return folurgischg;
	}
	public void setFolurgischg(String folurgischg) {
		this.folurgischg = folurgischg;
	}
	public Double getFolurgrfee() {
		return folurgrfee;
	}
	public void setFolurgrfee(Double folurgrfee) {
		this.folurgrfee = folurgrfee;
	}
	public Double getXubaofee() {
		return xubaofee;
	}
	public void setXubaofee(Double xubaofee) {
		this.xubaofee = xubaofee;
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
	public String getIctdetailaddr() {
		return ictdetailaddr;
	}
	public void setIctdetailaddr(String ictdetailaddr) {
		this.ictdetailaddr = ictdetailaddr;
	}
	public String getLg750() {
		return lg750;
	}
	public void setLg750(String lg750) {
		this.lg750 = lg750;
	}
	public String getLg1500() {
		return lg1500;
	}
	public void setLg1500(String lg1500) {
		this.lg1500 = lg1500;
	}
	public String getLg3000() {
		return lg3000;
	}
	public void setLg3000(String lg3000) {
		this.lg3000 = lg3000;
	}
	public String getLg6000() {
		return lg6000;
	}
	public void setLg6000(String lg6000) {
		this.lg6000 = lg6000;
	}
	public String getLq750() {
		return lq750;
	}
	public void setLq750(String lq750) {
		this.lq750 = lq750;
	}
	public String getLq1500() {
		return lq1500;
	}
	public void setLq1500(String lq1500) {
		this.lq1500 = lq1500;
	}
	public String getLq3000() {
		return lq3000;
	}
	public void setLq3000(String lq3000) {
		this.lq3000 = lq3000;
	}
	public String getLq6000() {
		return lq6000;
	}
	public void setLq6000(String lq6000) {
		this.lq6000 = lq6000;
	}
	public String getRg750() {
		return rg750;
	}
	public void setRg750(String rg750) {
		this.rg750 = rg750;
	}
	public String getRg1500() {
		return rg1500;
	}
	public void setRg1500(String rg1500) {
		this.rg1500 = rg1500;
	}
	public String getRg3000() {
		return rg3000;
	}
	public void setRg3000(String rg3000) {
		this.rg3000 = rg3000;
	}
	public String getRg6000() {
		return rg6000;
	}
	public void setRg6000(String rg6000) {
		this.rg6000 = rg6000;
	}
	public String getRq750() {
		return rq750;
	}
	public void setRq750(String rq750) {
		this.rq750 = rq750;
	}
	public String getRq1500() {
		return rq1500;
	}
	public void setRq1500(String rq1500) {
		this.rq1500 = rq1500;
	}
	public String getRq3000() {
		return rq3000;
	}
	public void setRq3000(String rq3000) {
		this.rq3000 = rq3000;
	}
	public String getRq6000() {
		return rq6000;
	}
	public void setRq6000(String rq6000) {
		this.rq6000 = rq6000;
	}
	public String getIctfrom() {
		return ictfrom;
	}
	public void setIctfrom(String ictfrom) {
		this.ictfrom = ictfrom;
	}
	
	
	
}
