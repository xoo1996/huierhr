package org.radf.apps.commons.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

import com.google.gson.annotations.Expose;

//配置项信息表
public class OrderDetail extends EntitySupport {
	private String fdtfno; // 帐单号
	private String fdtcltid; // 用户代码
	private String foldes;//客户代码
	private String folctid;//客户代码
	private String foltype;//订单类型
	private String fdtpid; // 商品(耳机)名称代码
	private Double fdtprc; // 售价
	private Integer fdtqnt; // 数量
	private Integer fdtrqnt; // 剩余数量
	private Integer fdtsqnt; // 发货数量
	private Double fdtpqnt;
	private Date fdtedt;
	private Double fdtdprc; // 原价
	private String fdtnt;// 备注
	private String fdtcltnm; // 用户姓名
	private Date fdtodt;//退机日期
	private String fdtono;//退机原定单号
	private Double fdtdisc; //扣率
	private String fdtsheltp;  //外壳类型
	private String fdtmklr;  //判断左右耳
	private String fdtsheltpl;  //外壳类型(左) 用于显示订单详细信息
	private String fdtsheltpr;  //外壳类型(右) 用于显示订单详细信息
	private String fdtmat;
	private String fdtcls;
	private String fdtfree;
	private String fdtsid;
	private Date fdtcusdt;
	private Double fdtrecmon;
	private String fdtisspdisc;
	private String fdtsta;
	private String oldpid;//用于保存原订单号（修改订单明细时）
	
	private String fdtinnt;
	private String dgnlid; // 左耳耳机产品编号
	private String dgnrid; // 右耳耳机产品编号
	private String minstoeq0;
	private String minstoles0;
	@Expose
	private String minstoi; //第i行商品的最小库存报警
	@Expose
	private Integer minsto;
	private Integer stoAmo; //现在的库存余量
	
	
	/**
     * 查询维修记录所需字段
     * 
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
	private String pdtnm; // 耳机名称
	
	private Double tdspvo;//省内外最小扣率
	
	private String dgndoc;//验配师
	public String getFdtfno() {
		return fdtfno;
	}

	public void setFdtfno(String fdtfno) {
		this.fdtfno = fdtfno;
	}

	public String getFdtcltid() {
		return fdtcltid;
	}

	public void setFdtcltid(String fdtcltid) {
		this.fdtcltid = fdtcltid;
	}

	public String getFdtpid() {
		return fdtpid;
	}

	public void setFdtpid(String fdtpid) {
		this.fdtpid = fdtpid;
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

	public Integer getFdtsqnt() {
		return fdtsqnt;
	}

	public void setFdtsqnt(Integer fdtsqnt) {
		this.fdtsqnt = fdtsqnt;
	}

	public Double getFdtpqnt() {
		return fdtpqnt;
	}

	public void setFdtpqnt(Double fdtpqnt) {
		this.fdtpqnt = fdtpqnt;
	}

	public Date getFdtedt() {
		return fdtedt;
	}

	public void setFdtedt(Date fdtedt) {
		this.fdtedt = fdtedt;
	}

	public Double getFdtdprc() {
		return fdtdprc;
	}

	public void setFdtdprc(Double fdtdprc) {
		this.fdtdprc = fdtdprc;
	}

	public String getFdtnt() {
		return fdtnt;
	}

	public void setFdtnt(String fdtnt) {
		this.fdtnt = fdtnt;
	}

	public String getFdtcltnm() {
		return fdtcltnm;
	}

	public void setFdtcltnm(String fdtcltnm) {
		this.fdtcltnm = fdtcltnm;
	}

	public Date getFdtodt() {
		return fdtodt;
	}

	public void setFdtodt(Date fdtodt) {
		this.fdtodt = fdtodt;
	}

	public String getFdtono() {
		return fdtono;
	}

	public void setFdtono(String fdtono) {
		this.fdtono = fdtono;
	}

	public Integer getFdtrqnt() {
		return fdtrqnt;
	}

	public void setFdtrqnt(Integer fdtrqnt) {
		this.fdtrqnt = fdtrqnt;
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

	public String getPdtnm() {
		return pdtnm;
	}

	public void setPdtnm(String pdtnm) {
		this.pdtnm = pdtnm;
	}

	public Double getFdtdisc() {
		return fdtdisc;
	}

	public void setFdtdisc(Double fdtdisc) {
		this.fdtdisc = fdtdisc;
	}

	public String getFdtsheltp() {
		return fdtsheltp;
	}

	public void setFdtsheltp(String fdtsheltp) {
		this.fdtsheltp = fdtsheltp;
	}

	public String getFdtmklr() {
		return fdtmklr;
	}

	public void setFdtmklr(String fdtmklr) {
		this.fdtmklr = fdtmklr;
	}

	public String getFdtsheltpl() {
		return fdtsheltpl;
	}

	public void setFdtsheltpl(String fdtsheltpl) {
		this.fdtsheltpl = fdtsheltpl;
	}

	public String getFdtsheltpr() {
		return fdtsheltpr;
	}

	public void setFdtsheltpr(String fdtsheltpr) {
		this.fdtsheltpr = fdtsheltpr;
	}

	public String getDgnlid() {
		return dgnlid;
	}

	public void setDgnlid(String dgnlid) {
		this.dgnlid = dgnlid;
	}

	public String getDgnrid() {
		return dgnrid;
	}

	public void setDgnrid(String dgnrid) {
		this.dgnrid = dgnrid;
	}

	public String getFoldes() {
		return foldes;
	}

	public void setFoldes(String foldes) {
		this.foldes = foldes;
	}

	public String getFolctid() {
		return folctid;
	}

	public void setFolctid(String folctid) {
		this.folctid = folctid;
	}

	public String getFoltype() {
		return foltype;
	}

	public void setFoltype(String foltype) {
		this.foltype = foltype;
	}

	public String getFdtinnt() {
		return fdtinnt;
	}

	public void setFdtinnt(String fdtinnt) {
		this.fdtinnt = fdtinnt;
	}

	public String getOldpid() {
		return oldpid;
	}

	public void setOldpid(String oldpid) {
		this.oldpid = oldpid;
	}

	public String getFdtmat() {
		return fdtmat;
	}

	public void setFdtmat(String fdtmat) {
		this.fdtmat = fdtmat;
	}

	public String getFdtcls() {
		return fdtcls;
	}

	public void setFdtcls(String fdtcls) {
		this.fdtcls = fdtcls;
	}

	public String getFdtfree() {
		return fdtfree;
	}

	public void setFdtfree(String fdtfree) {
		this.fdtfree = fdtfree;
	}

	public String getMinstoi() {
		return minstoi;
	}

	public void setMinstoi(String minstoi) {
		this.minstoi = minstoi;
	}

	public Integer getMinsto() {
		return minsto;
	}

	public void setMinsto(Integer minsto) {
		this.minsto = minsto;
	}

	public String getFdtsid() {
		return fdtsid;
	}

	public void setFdtsid(String fdtsid) {
		this.fdtsid = fdtsid;
	}

	public Date getFdtcusdt() {
		return fdtcusdt;
	}

	public void setFdtcusdt(Date fdtcusdt) {
		this.fdtcusdt = fdtcusdt;
	}

	public Integer getStoAmo() {
		return stoAmo;
	}

	public void setStoAmo(Integer stoAmo) {
		this.stoAmo = stoAmo;
	}

	public Double getTdspvo() {
		return tdspvo;
	}

	public void setTdspvo(Double tdspvo) {
		this.tdspvo = tdspvo;
	}

	public String getMinstoeq0() {
		return minstoeq0;
	}

	public void setMinstoeq0(String minstoeq0) {
		this.minstoeq0 = minstoeq0;
	}

	public String getMinstoles0() {
		return minstoles0;
	}

	public void setMinstoles0(String minstoles0) {
		this.minstoles0 = minstoles0;
	}

	public Double getFdtrecmon() {
		return fdtrecmon;
	}

	public void setFdtrecmon(Double fdtrecmon) {
		this.fdtrecmon = fdtrecmon;
	}


	public String getDgndoc() {
		return dgndoc;
	}

	public void setDgndoc(String dgndoc) {
		this.dgndoc = dgndoc;
	}
	
	public String getFdtisspdisc() {
		return fdtisspdisc;
	}

	public void setFdtisspdisc(String fdtisspdisc) {
		this.fdtisspdisc = fdtisspdisc;
	}

	public String getFdtsta() {
		return fdtsta;
	}

	public void setFdtsta(String fdtsta) {
		this.fdtsta = fdtsta;
	}

    
    
    
	
	
	
}