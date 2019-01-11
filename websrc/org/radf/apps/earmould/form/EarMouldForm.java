package org.radf.apps.earmould.form;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class EarMouldForm extends ActionForm {
	private String tmesid; // 耳模条形码
	private String tmeno;//  订单号
	private String tmecltid;//个人客户代码
	private String tmecltnm;//个人客户姓名
	
	private String tmectid;//团体客户代码
	private String tmemat;//耳模类型   0 左耳 1 右耳 2 双耳
	private String tmecls;//类别   new 新做  redo 重做   repair 维修
	private String tmefree;//是否赠送  Y 赠送,N 不赠送
	private String tmepid;//耳模代码
	private String tmesta;//耳模状态
	private Date tmemdt;//开始制作日期
	private Date tmepdt;//计划完成日期
	private Date tmefmdt;//完成日期
	private String tmemaker;//制作者代码
	private String tmeurgent;//是否加急
	
	private String tmectnm;//客户名称
	private String fdtqnt;//数量
	//质检信息
	private String tmeckt;//质检结果
	private String tmechkoprcd;//质检员代码
	private Date tmechkdt;//质检日期
	private String tmeappear;//外观
	private String tmeden;//气密性
	private Date tmefoldt;//录单日期
	private String tmeprc;//耳模售价
	private String tment;//备注
	//按计划完工日期查询
	private Date tmeqst;//开始日期
	private Date tmeqen;//结束日期
	private String tmepnm;//耳模名称
	
	private String tmegctnm;//客户名称
	
	
	/*
	 * 
	 * 维修信息
	 * 
	 * */
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

	// 商品表
	private String reppnm; // 耳机名称

	private Date start;
	private Date end;
	
	public String getTmepnm() {
		return tmepnm;
	}
	public void setTmepnm(String tmepnm) {
		this.tmepnm = tmepnm;
	}
	public String getTmesid() {
		return tmesid;
	}
	public void setTmesid(String tmesid) {
		this.tmesid = tmesid;
	}
	public String getTmeno() {
		return tmeno;
	}
	public void setTmeno(String tmeno) {
		this.tmeno = tmeno;
	}
	public String getTmecltid() {
		return tmecltid;
	}
	public void setTmecltid(String tmecltid) {
		this.tmecltid = tmecltid;
	}
	public String getTmectid() {
		return tmectid;
	}
	public void setTmectid(String tmectid) {
		this.tmectid = tmectid;
	}
	public String getTmemat() {
		return tmemat;
	}
	public void setTmemat(String tmemat) {
		this.tmemat = tmemat;
	}
	public String getTmecls() {
		return tmecls;
	}
	public void setTmecls(String tmecls) {
		this.tmecls = tmecls;
	}
	public String getTmefree() {
		return tmefree;
	}
	public void setTmefree(String tmefree) {
		this.tmefree = tmefree;
	}
	public String getTmepid() {
		return tmepid;
	}
	public void setTmepid(String tmepid) {
		this.tmepid = tmepid;
	}
	public String getTmesta() {
		return tmesta;
	}
	public void setTmesta(String tmesta) {
		this.tmesta = tmesta;
	}
	public Date getTmemdt() {
		return tmemdt;
	}
	public void setTmemdt(Date tmemdt) {
		this.tmemdt = tmemdt;
	}
	public Date getTmepdt() {
		return tmepdt;
	}
	public void setTmepdt(Date tmepdt) {
		this.tmepdt = tmepdt;
	}
	public Date getTmefmdt() {
		return tmefmdt;
	}
	public void setTmefmdt(Date tmefmdt) {
		this.tmefmdt = tmefmdt;
	}
	public String getTmemaker() {
		return tmemaker;
	}
	public void setTmemaker(String tmemaker) {
		this.tmemaker = tmemaker;
	}
	public String getTmeckt() {
		return tmeckt;
	}
	public void setTmeckt(String tmeckt) {
		this.tmeckt = tmeckt;
	}
	public String getTmechkoprcd() {
		return tmechkoprcd;
	}
	public void setTmechkoprcd(String tmechkoprcd) {
		this.tmechkoprcd = tmechkoprcd;
	}
	public Date getTmechkdt() {
		return tmechkdt;
	}
	public void setTmechkdt(Date tmechkdt) {
		this.tmechkdt = tmechkdt;
	}
	public String getTmeappear() {
		return tmeappear;
	}
	public void setTmeappear(String tmeappear) {
		this.tmeappear = tmeappear;
	}
	public String getTmeden() {
		return tmeden;
	}
	public void setTmeden(String tmeden) {
		this.tmeden = tmeden;
	}
	public Date getTmefoldt() {
		return tmefoldt;
	}
	public void setTmefoldt(Date tmefoldt) {
		this.tmefoldt = tmefoldt;
	}
	public String getTmeprc() {
		return tmeprc;
	}
	public void setTmeprc(String tmeprc) {
		this.tmeprc = tmeprc;
	}
	public String getTment() {
		return tment;
	}
	public void setTment(String tment) {
		this.tment = tment;
	}
	public Date getTmeqst() {
		return tmeqst;
	}
	public void setTmeqst(Date tmeqst) {
		this.tmeqst = tmeqst;
	}
	public Date getTmeqen() {
		return tmeqen;
	}
	public void setTmeqen(Date tmeqen) {
		this.tmeqen = tmeqen;
	}
	public String getTmecltnm() {
		return tmecltnm;
	}
	public void setTmecltnm(String tmecltnm) {
		this.tmecltnm = tmecltnm;
	}
	public String getTmegctnm() {
		return tmegctnm;
	}
	public void setTmegctnm(String tmegctnm) {
		this.tmegctnm = tmegctnm;
	}

	
	/*
	 * 
	 * 2012/2/20新增
	 * 
	 */
	
	
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
	
	public String getFdtqnt() {
		return fdtqnt;
	}
	public void setFdtqnt(String fdtqnt) {
		this.fdtqnt = fdtqnt;
	}
	public String getTmectnm() {
		return tmectnm;
	}
	public void setTmectnm(String tmectnm) {
		this.tmectnm = tmectnm;
	}
	public String getTmeurgent() {
		return tmeurgent;
	}
	public void setTmeurgent(String tmeurgent) {
		this.tmeurgent = tmeurgent;
	}
	
} 
