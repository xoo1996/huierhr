package org.radf.apps.commons.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

//�շѻ�����Ϣ��
public class OrderState extends EntitySupport {

	private String folno; // ��������
	private String fdtfno; // ������

	// ����ͻ���
	private String folctid;// �ͻ�����
	private String folctnm; // �ͻ�����
	private String cltnm;//�û�����
	private String ictgender; 	// �Ա�
	private String ictpcd; 		// ��������
	private String icttel; 		// ��ϵ�绰
	private String ictaddr; 	// ��ϵ��ַ
	private String ictpro;      //ʡ��
	private String ictcity;     //��
	private String ictcounty;    //��
	private String ictphone; //�ֻ�
	private String ictdetailaddr;//��ϸ��ַ
	private String ictlandline; //�̶��绰
	private String pdtnm; // ��Ʒ����
	private String pdtid;// ��Ʒ����
	private Integer fdtqnt;//����
	private Integer fdtrqnt;//ʣ������
	
	private Integer fdtmqnt;//��������
	private String fdtsnt; // ��Ʒ�۳���ע
	private Double fdtdisc;//��ͨ��Ʒ����
	private Double fdtrprc; //��ͨ��Ʒʵ�ʼ۸�
	private Date fdtcdt; //��ͨ��Ʒ�շ�����
	
	private Double fdtprc; // �ۼ�
	private Double pdtprc; // �۸�
	private Double discount;//����
	private Double deposit;//����
	private Double balance;//Ӧ�����
	private String folischg;//�Ƿ����շ�
	private Date chargedate; //�շ�����
	
	
	//ά����Ʒ�õ����ֶ�
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
	
	private Date repischarged; // ά�޷��Ƿ�����
	private Date repchargedate; // ά�޷��շ�����

	// ��Ʒ��
	private String reppnm; // �������ƣ����������ƣ�
	private Date foldt; // ��������
	private Date folsdt; // ��������
	private String foltype;// �������
	
	private String folsno; // ��ݺ�
	private String folsta; // ����״̬
	private String folnt; // ��ע
	private String foldes; // �����ݶ���
	// ��ʱ
	private Date start; //��ʼ����
	private Date end;	//��������
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
