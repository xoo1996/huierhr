package org.radf.apps.earmould.form;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class EarMouldForm extends ActionForm {
	private String tmesid; // ��ģ������
	private String tmeno;//  ������
	private String tmecltid;//���˿ͻ�����
	private String tmecltnm;//���˿ͻ�����
	
	private String tmectid;//����ͻ�����
	private String tmemat;//��ģ����   0 ��� 1 �Ҷ� 2 ˫��
	private String tmecls;//���   new ����  redo ����   repair ά��
	private String tmefree;//�Ƿ�����  Y ����,N ������
	private String tmepid;//��ģ����
	private String tmesta;//��ģ״̬
	private Date tmemdt;//��ʼ��������
	private Date tmepdt;//�ƻ��������
	private Date tmefmdt;//�������
	private String tmemaker;//�����ߴ���
	private String tmeurgent;//�Ƿ�Ӽ�
	
	private String tmectnm;//�ͻ�����
	private String fdtqnt;//����
	//�ʼ���Ϣ
	private String tmeckt;//�ʼ���
	private String tmechkoprcd;//�ʼ�Ա����
	private Date tmechkdt;//�ʼ�����
	private String tmeappear;//���
	private String tmeden;//������
	private Date tmefoldt;//¼������
	private String tmeprc;//��ģ�ۼ�
	private String tment;//��ע
	//���ƻ��깤���ڲ�ѯ
	private Date tmeqst;//��ʼ����
	private Date tmeqen;//��������
	private String tmepnm;//��ģ����
	
	private String tmegctnm;//�ͻ�����
	
	
	/*
	 * 
	 * ά����Ϣ
	 * 
	 * */
	private BigDecimal repidentity; // ά����ˮ��
	private String repid; // ������
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
	private String repaction1;// ά�޴�ʩһ
	private String repaction2; // ά�޴�ʩ��
	private String repaction3; // ά�޴�ʩ��
	private String repaction4; // ά�޴�ʩ��
	private String repaction5; // ά�޴�ʩ��
	private String repaction6; // ά�޴�ʩ�� //��������ά�޴�ʩ
	private String reppreamt; // ά�޷ѹ��ƣ�����100Ԫ������200Ԫ����
	// private String repno; // �ڼ����ͳ�ά��(not used!)
	private String repattention; // ע������(�ͻ���)
	private String repreger; // ����˴���
	private String reppid;// �������ͺ�
	private String repgctid; // ���޵�λ����
	private String repregnames; // ����ά��Ա����
	private String reptip; // ��ܰ��ʾ1
	private String reptip1; // ��ܰ��ʾ2
	private String repsta;// ά��״̬
	private String repcpy;// ���޳���
	private Date reppdate; // �ƻ��깤����
	private String repiskp;//�Ƿ�Ʊ
	private Double repaprc;//����Ԥ��

	// ��Ʒ��
	private String reppnm; // ��������

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
	 * 2012/2/20����
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
