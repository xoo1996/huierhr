package org.radf.apps.repair.form;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class RepairForm extends ActionForm {
	private BigDecimal repidentity; // ά����ˮ��
	private String repid; // ������
	private String reppnl; // �����
	private Date repdate;// ��������
	// repwcode char(30) null, //����ԭ�򣨴��룺REP��������
	// represult char(30) null, //�����������룺WCS��������
	private Date repfdate; // �깤����
	private String repoprcd; // ά��Ա����
	private String repnote; // ��ע
	private String repcltid; // ���˴���
	private String repcltnm; // ��������
	private String repcls; // �������(һ�·���,���·���,���·���,����)
	private Double repamt; // ά�޽��
	private String repfolid; // ��ά�޼�¼���ɵĶ�Ӧ�ʵ���
	// private String repoldid; // �»�����
	// private String repbarcd; // ������
	private String repfree; // ������(��:Y,��:N)
	private String repdeclare; // ��������(�ͻ����)
	private String repconfirmed; // ����ȷ�����
	private String repactionzh;//ά�޴�ʩ�ۺ�
	private String repaction1;// ά�޴�ʩһ
	private String repaction2; // ά�޴�ʩ��
	private String repaction3; // ά�޴�ʩ��
	private String repaction4; // ά�޴�ʩ��
	private String repaction5; // ά�޴�ʩ��
	private String repaction6; // ά�޴�ʩ�� //��������ά�޴�ʩ
	
	private String repaction1prc;//��ʩһ����
	private String repaction2prc;//��ʩ������
	private String repaction3prc;//��ʩ������
	private String repaction4prc;//��ʩ�ķ���
	private String repaction5prc;//��ʩ�����
	private String repaction6prc;//��ʩ������
	
	private String reppreamt; // ά�޷ѹ��ƣ�����100Ԫ������200Ԫ����
	// private String repno; // �ڼ����ͳ�ά��(not used!)
	private String repattention; // ע������(�ͻ���)
	private String repreger; // ����˴���
	private String reppid; // �������ͺţ�ͬʱҲ���������
	private String repgctid; // ���޵�λ����
	private String repregnames; // ����ά��Ա����
	private String reptip; // ��ܰ��ʾ1
	private String reptip1; // ��ܰ��ʾ2
	private String repsta; // ά��״̬
	private String repcpy; // ���޳���
	private Date reppdate;// �ƻ��������
	private String repiskp;//�Ƿ�Ʊ
	private Double repaprc;//����Ԥ��
	private Date repkpdate;//��Ʊ����
	private Date repshinstdt;//ά����ǰ�װ����
	
	private String repclear; // 
	private String repsymptom; // 
	private String repshellsyp; // 
	private String repotsyp; // 
	private String reppossyp; // 
	private String reptime; // 
//	private String repotsyp; // 
//	private String reppossyp; // 
	
	
	// ����ͻ���
	private String repgctnm; // ����ͻ�����
	// ��Ʒ��
	private String reppnm; // �������ƣ�ͬʱҲ���������
	private String pdtcls; //�������
	private String folsta;
	
	private Date start;
	private Date end;

	//ά�޴�ʩ������ʾ����
	private String repaction;
	private String repactionPrc;
	private String repactionType;
	
	//�����ѯ
	private String component;//�������
	
	
/*	private String tmkcltid; // ���˴���
	private String tmkcltnm; // ��������
	private String tmkgctnm;// ��������
	private String tmkfno; // ������
	private String tmkpnm; // ��������
	private String tmksid; // �����
	private String tmkgctid;//�ͻ�����
	private String tmkpid; // ��������
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
