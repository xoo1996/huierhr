package org.radf.apps.repair.form;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class RepairForm extends ActionForm {
	private BigDecimal repidentity; // 维修流水号
	private String repid; // 机身编号
	private String reppnl; // 面板编号
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
	private String repactionzh;//维修措施综合
	private String repaction1;// 维修措施一
	private String repaction2; // 维修措施二
	private String repaction3; // 维修措施三
	private String repaction4; // 维修措施四
	private String repaction5; // 维修措施五
	private String repaction6; // 维修措施六 //自由输入维修措施
	
	private String repaction1prc;//措施一费用
	private String repaction2prc;//措施二费用
	private String repaction3prc;//措施三费用
	private String repaction4prc;//措施四费用
	private String repaction5prc;//措施五费用
	private String repaction6prc;//措施六费用
	
	private String reppreamt; // 维修费估计（超过100元、超过200元…）
	// private String repno; // 第几次送厂维修(not used!)
	private String repattention; // 注意事项(客户用)
	private String repreger; // 填表人代码
	private String reppid; // 助听器型号，同时也是零件批号
	private String repgctid; // 送修单位代码
	private String repregnames; // 其他维修员姓名
	private String reptip; // 温馨提示1
	private String reptip1; // 温馨提示2
	private String repsta; // 维修状态
	private String repcpy; // 送修厂商
	private Date reppdate;// 计划完成日期
	private String repiskp;//是否开票
	private Double repaprc;//费用预计
	private Date repkpdate;//开票日期
	private Date repshinstdt;//维修外壳安装日期
	
	private String repclear; // 
	private String repsymptom; // 
	private String repshellsyp; // 
	private String repotsyp; // 
	private String reppossyp; // 
	private String reptime; // 
//	private String repotsyp; // 
//	private String reppossyp; // 
	
	
	// 团体客户表
	private String repgctnm; // 团体客户名称
	// 商品表
	private String reppnm; // 耳机名称，同时也是零件名称
	private String pdtcls; //耳机类别
	private String folsta;
	
	private Date start;
	private Date end;

	//维修措施智能提示功能
	private String repaction;
	private String repactionPrc;
	private String repactionType;
	
	//零件查询
	private String component;//零件批号
	
	
/*	private String tmkcltid; // 病人代码
	private String tmkcltnm; // 病人姓名
	private String tmkgctnm;// 团体名称
	private String tmkfno; // 订单号
	private String tmkpnm; // 耳机名称
	private String tmksid; // 机身号
	private String tmkgctid;//客户代码
	private String tmkpid; // 耳机代码
*/	
	
	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public BigDecimal getRepidentity() {
		return repidentity;
	}

	public String getRepaction() {
		return repaction;
	}

	public void setRepaction(String repaction) {
		this.repaction = repaction;
	}

	public String getRepactionPrc() {
		return repactionPrc;
	}

	public void setRepactionPrc(String repactionPrc) {
		this.repactionPrc = repactionPrc;
	}

	public String getRepactionType() {
		return repactionType;
	}

	public void setRepactionType(String repactionType) {
		this.repactionType = repactionType;
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
	
	public String getFolsta() {
		return folsta;
	}

	public void setFolsta(String folsta) {
		this.folsta = folsta;
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

	public String getReppnm() {
		return reppnm;
	}

	public void setReppnm(String reppnm) {
		this.reppnm = reppnm;
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

	public String getRepgctnm() {
		return repgctnm;
	}

	public void setRepgctnm(String repgctnm) {
		this.repgctnm = repgctnm;
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

	public Date getReppdate() {
		return reppdate;
	}

	public void setReppdate(Date reppdate) {
		this.reppdate = reppdate;
	}

	public String getRepcltid() {
		return repcltid;
	}

	public void setRepcltid(String repcltid) {
		this.repcltid = repcltid;
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

	public String getRepclear() {
		return repclear;
	}

	public void setRepclear(String repclear) {
		this.repclear = repclear;
	}

	public String getRepsymptom() {
		return repsymptom;
	}

	public void setRepsymptom(String repsymptom) {
		this.repsymptom = repsymptom;
	}

	public String getRepshellsyp() {
		return repshellsyp;
	}

	public void setRepshellsyp(String repshellsyp) {
		this.repshellsyp = repshellsyp;
	}

	public String getRepotsyp() {
		return repotsyp;
	}

	public void setRepotsyp(String repotsyp) {
		this.repotsyp = repotsyp;
	}

	public String getReppossyp() {
		return reppossyp;
	}

	public void setReppossyp(String reppossyp) {
		this.reppossyp = reppossyp;
	}

	public String getReptime() {
		return reptime;
	}

	public void setReptime(String reptime) {
		this.reptime = reptime;
	}

	public String getRepaction1prc() {
		return repaction1prc;
	}

	public void setRepaction1prc(String repaction1prc) {
		this.repaction1prc = repaction1prc;
	}

	public String getRepaction2prc() {
		return repaction2prc;
	}

	public void setRepaction2prc(String repaction2prc) {
		this.repaction2prc = repaction2prc;
	}

	public String getRepaction3prc() {
		return repaction3prc;
	}

	public void setRepaction3prc(String repaction3prc) {
		this.repaction3prc = repaction3prc;
	}

	public String getRepaction4prc() {
		return repaction4prc;
	}

	public void setRepaction4prc(String repaction4prc) {
		this.repaction4prc = repaction4prc;
	}

	public String getRepaction5prc() {
		return repaction5prc;
	}

	public void setRepaction5prc(String repaction5prc) {
		this.repaction5prc = repaction5prc;
	}

	public String getRepaction6prc() {
		return repaction6prc;
	}

	public void setRepaction6prc(String repaction6prc) {
		this.repaction6prc = repaction6prc;
	}

	public String getPdtcls() {
		return pdtcls;
	}

	public void setPdtcls(String pdtcls) {
		this.pdtcls = pdtcls;
	}
	
	public Date getRepkpdate() {
		return repkpdate;	
	}
	
	public void setRepkpdate(Date repkpdate) {
		this.repkpdate = repkpdate;
	}

	public Date getRepshinstdt() {
		return repshinstdt;
	}

	public void setRepshinstdt(Date repshinstdt) {
		this.repshinstdt = repshinstdt;
	}

	public String getReppnl() {
		return reppnl;
	}

	public void setReppnl(String reppnl) {
		this.reppnl = reppnl;
	}

	public String getRepactionzh() {
		return repactionzh;
	}

	public void setRepactionzh(String repactionzh) {
		this.repactionzh = repactionzh;
	}
	
	/*public String getTmkcltid() {
		return tmkcltid;
	}

	public void setTmkcltid(String tmkcltid) {
		this.tmkcltid = tmkcltid;
	}

	public String getTmkcltnm() {
		return tmkcltnm;
	}

	public void setTmkcltnm(String tmkcltnm) {
		this.tmkcltnm = tmkcltnm;
	}

	public String getTmkgctnm() {
		return tmkgctnm;
	}

	public void setTmkgctnm(String tmkgctnm) {
		this.tmkgctnm = tmkgctnm;
	}

	public String getTmkfno() {
		return tmkfno;
	}

	public void setTmkfno(String tmkfno) {
		this.tmkfno = tmkfno;
	}

	public String getTmkpnm() {
		return tmkpnm;
	}

	public void setTmkpnm(String tmkpnm) {
		this.tmkpnm = tmkpnm;
	}

	public String getTmksid() {
		return tmksid;
	}

	public void setTmksid(String tmksid) {
		this.tmksid = tmksid;
	}

	public String getTmkgctid() {
		return tmkgctid;
	}

	public void setTmkgctid(String tmkgctid) {
		this.tmkgctid = tmkgctid;
	}

	public String getTmkpid() {
		return tmkpid;
	}

	public void setTmkpid(String tmkpid) {
		this.tmkpid = tmkpid;
	}*/
	
	
}
