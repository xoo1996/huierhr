package org.radf.apps.product.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class ProductForm extends ActionForm {
	private String pdtid; // ��Ʒ������������
	private String pdtnm; // ��Ʒ������������
	private String pdttype; // ��Ʒ����
	private String pdtcls; // ��Ʒ������������(����ʽ:ITE;����ʽ:ITC;��ȫ����ʽ:CIC;����ʽ:BTE)
	private String pdtut; // ��Ʒ����������λ
	private Double pdtprc;// //��Ʒ������������
	private Double pdtdiscount;//��Ʒ����
	private String pdtnt; // ��ע
	private String pdtisd;//�Ƿ���ӿ���
	private String pdtclid; //��Ʒ������
	private String pdtp;//��Ʒ���
	private String pdtclnm;//��Ʒ�������
	private String pdtmod;//����ͺ�
	private String pdtispnl;//�Ƿ�Ϊ������
	
	
	private String pclid;
	private String pcllarge;
	private String pclmid;
	private String pclsmall;
	private String pdtminsto;//��С�����
	private Integer pdtlmt;//�������ޣ��£�

	private String dispdtid; //��Ʒ����
	private String disgctid;//�ͻ�����
	private Double disprice;//���ۺ�ļ۸�
	private Double discount;//����
	private String disnote;//��ע
	private String disop;//������Ա
	private Date   disoptime;//����ʱ��
	
	private String disgctnm;//�ͻ�����
	private String gcttype;
	
	/**
	 * �������ֶ�
	 * @return
	 */
	private String acyid; //�������
	private String acystoid; //�����
	private String acypdtid; //�������
	private String acypdtnm; //�������
	private String acytyp; //����ͺ�
	private String acybthnum; //�������
	private String acynt; //��ע
	
	public Double getPdtdiscount() {
		return pdtdiscount;
	}

	public void setPdtdiscount(Double pdtdiscount) {
		this.pdtdiscount = pdtdiscount;
	}

	public String getPdtid() {
		return pdtid;
	}

	public void setPdtid(String pdtid) {
		this.pdtid = pdtid;
	}

	public String getPdtnm() {
		return pdtnm;
	}

	public void setPdtnm(String pdtnm) {
		this.pdtnm = pdtnm;
	}

	public String getPdtcls() {
		return pdtcls;
	}

	public void setPdtcls(String pdtcls) {
		this.pdtcls = pdtcls;
	}

	public String getPdtut() {
		return pdtut;
	}

	public void setPdtut(String pdtut) {
		this.pdtut = pdtut;
	}

	public Double getPdtprc() {
		return pdtprc;
	}

	public void setPdtprc(Double pdtprc) {
		this.pdtprc = pdtprc;
	}

	public String getPdtnt() {
		return pdtnt;
	}

	public void setPdtnt(String pdtnt) {
		this.pdtnt = pdtnt;
	}

	public String getPdtisd() {
		return pdtisd;
	}

	public void setPdtisd(String pdtisd) {
		this.pdtisd = pdtisd;
	}

	public String getPdttype() {
		return pdttype;
	}

	public void setPdttype(String pdttype) {
		this.pdttype = pdttype;
	}

	public String getPdtp() {
		return pdtp;
	}

	public void setPdtp(String pdtp) {
		this.pdtp = pdtp;
	}

	public String getDispdtid() {
		return dispdtid;
	}

	public void setDispdtid(String dispdtid) {
		this.dispdtid = dispdtid;
	}

	public String getDisgctid() {
		return disgctid;
	}

	public void setDisgctid(String disgctid) {
		this.disgctid = disgctid;
	}

	public Double getDisprice() {
		return disprice;
	}

	public void setDisprice(Double disprice) {
		this.disprice = disprice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDisnote() {
		return disnote;
	}

	public void setDisnote(String disnote) {
		this.disnote = disnote;
	}

	public String getDisop() {
		return disop;
	}

	public void setDisop(String disop) {
		this.disop = disop;
	}

	public Date getDisoptime() {
		return disoptime;
	}

	public void setDisoptime(Date disoptime) {
		this.disoptime = disoptime;
	}

	public String getDisgctnm() {
		return disgctnm;
	}

	public void setDisgctnm(String disgctnm) {
		this.disgctnm = disgctnm;
	}

	public String getGcttype() {
		return gcttype;
	}

	public void setGcttype(String gcttype) {
		this.gcttype = gcttype;
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

	public String getAcypdtid() {
		return acypdtid;
	}

	public void setAcypdtid(String acypdtid) {
		this.acypdtid = acypdtid;
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

	public String getAcybthnum() {
		return acybthnum;
	}

	public void setAcybthnum(String acybthnum) {
		this.acybthnum = acybthnum;
	}

	public String getAcynt() {
		return acynt;
	}

	public void setAcynt(String acynt) {
		this.acynt = acynt;
	}

	public String getPdtclid() {
		return pdtclid;
	}

	public void setPdtclid(String pdtclid) {
		this.pdtclid = pdtclid;
	}

	public String getPdtclnm() {
		return pdtclnm;
	}

	public void setPdtclnm(String pdtclnm) {
		this.pdtclnm = pdtclnm;
	}

	public String getPclid() {
		return pclid;
	}

	public void setPclid(String pclid) {
		this.pclid = pclid;
	}

	public String getPcllarge() {
		return pcllarge;
	}

	public void setPcllarge(String pcllarge) {
		this.pcllarge = pcllarge;
	}

	public String getPclmid() {
		return pclmid;
	}

	public void setPclmid(String pclmid) {
		this.pclmid = pclmid;
	}

	public String getPclsmall() {
		return pclsmall;
	}

	public void setPclsmall(String pclsmall) {
		this.pclsmall = pclsmall;
	}

	public String getPdtminsto() {
		return pdtminsto;
	}

	public void setPdtminsto(String pdtminsto) {
		this.pdtminsto = pdtminsto;
	}

	public Integer getPdtlmt() {
		return pdtlmt;
	}

	public void setPdtlmt(Integer pdtlmt) {
		this.pdtlmt = pdtlmt;
	}

	public String getPdtmod() {
		return pdtmod;
	}

	public void setPdtmod(String pdtmod) {
		this.pdtmod = pdtmod;
	}

	public String getPdtispnl() {
		return pdtispnl;
	}

	public void setPdtispnl(String pdtispnl) {
		this.pdtispnl = pdtispnl;
	}
	
	
	
}
