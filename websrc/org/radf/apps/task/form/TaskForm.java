package org.radf.apps.task.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class TaskForm extends ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//��������Ϣ
	private String pnlid;//�����
	private String pnlnm;//����ͺ�
	private String pnlstoid;//�����
	private String pnlnt;//��ע
	private String pnlproid;//�����루����Ʒ���룩
	
	/**
	 * �������
	 */
	private String cfgacyid;//�������
	private String cfgpnlnm;//����ͺ�
	private String cfgnt;//��ע
	private String cfgacyidori;//�޸�ǰ���������
	
	
	/**
	 * �������ֶ�
	 */
	private String acyid; //�������
	private String acystoid; //�����
	private String acypdtnm; //�������
	private String acytyp; //����ͺ�
	private String acynt; //��ע
	
	
	//������Ϣ
	private String tskid;//���񵥺�
	private String tskpnlnm;//����ͺ�
	private Integer tskpnlqnt;//�������
	private String tskdmd;//���Ҫ��
	private String tsksta;//���״̬
	private Date tskdfdt;//Ҫ���������
	private Date tskbgndt;//�����´�����
	private String tskbilopr;//�Ƶ���
	private String tskadtopr;//�����
	private String tskmkopr;//������Ա
	private Date tskmkdt;//��������
	private String tskpdopr;//����������
	private String tsknt;//��ע
	
	private Date start;//��ʼ����
	private Date end;//��������
	
	//����������ֶ�
	private String tsdtskid;//���񵥺�
	private String tsdsid;// ������������
	private String tsdpnlid;// �����
	private String tsdsta;// ���״̬
	private String tsdnt;// ��ע
	
	//�������ñ��ֶ�
	private String tcfid;//  ���񵥺�
	private String tcfsid;//  ������������
	private String tcfacy;//  �������
	private String tcfbth;//  �������
	private String tcfnum;//  �������
	private String tcfnt;//  ��ע
	
	// ����
	private String stoid;// �����
	private String stogrcliid;// ����ͻ�����
	private String stoproid;// ��Ʒ����
	private String stoprotype;// ��Ʒ����
	private String stoproname;// ��Ʒ����
	private Integer stoamount;// ����(���/����)
	private String stoamountun;// ������λ
	private Double stoproprice;// ����
	private Date stodate;// ���������
	private Integer stoactype;// ������1�����-1�����⣩
	private String storemark;// ��ע
	private String stooprcd;// ����Ա����
	private String stodisc;// �Ƿ��ѱ���
	private String stoprodsc;//��Ʒ����
	private String stocllar;//����
	private String stoclmid;//����
	private String stoclsam;//С��
	private String isrepair;//�Ƿ�ά�޶���
	//����ʼ��
	private String pnlqaid;//�ʼ���
	private String pnlqatskid;//���񵥺�
	private String pnlqapnl;//��������������ŷ�Χ����JB112020370-JB112020380��
	private String pnlqarsta;//������1
	private String pnlqarstb;//������2
	private String pnlqarstc;//������3
	private String pnlqaopra;//����Ա1��Ʒ����Ա��
	private String pnlqaoprb;//����Ա2��Ʒ����Ա��
	private String pnlqaoprc;//����Ա3��Ʒ����Ա��
	private String pnlqapnla;//����Ա1��������ŷ�Χ
	private String pnlqapnlb;//����Ա2��������ŷ�Χ
	private String pnlqapnlc;//����Ա3��������ŷ�Χ

	private String qachka;//������1(�����б�)
	private String qachkb;//������2(�����б�)
	private String qachkc;//������3(�����б�)
	
	private Date pnlqadt;//��������
	private String pnlqant;//��ע
	private String pnlorgnm;//����ԭ�����ƻ��ͺ�
		
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
