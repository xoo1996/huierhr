package org.radf.apps.commons.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

//�¶Ƚ����
public class Business extends EntitySupport {
	private BigDecimal ivtid;
	private Integer ivtyear; // ��
	private Integer ivtmonth; // ��
	private String ivtgcltid; // ����ͻ�����
	private String ivtcltid; // ���˴���
	private String ivtpdtid; // ��Ʒ(����)����
	private Integer ivtlmqnt; // ���½����
	private Integer ivtlsqnt; // ���²�����
	private Double ivtdiscount; // �ۿ���
	private Integer ivtpqnt; // ���»ؿ���
	private Double ivtpamnt; // �ؿ���
	private String ivtoprcd; // �½����Ա
	private Date ivtoprdt; // �½�����
	private String ivtnote; // ��ע
	private String ivttype;// ��������
	private String ivtcltnm; //��������
	
	private String ivtfree;//����
	private String ivttry;//�Ի�

	private Double discount;//����
	
	//���˵��¶Ƚ��˱�
	private String actstaid;//��ˮ��
	private Integer actstayear;//��
	private Integer actstamonth;//��
	private String actstagcltid;//����ͻ�����
	private Integer actmqnt;//�½����
	private Integer actsqnt;//�²�����
	private String actsta;//����״̬
	private String actstanote;//��ע
	
	private String gctid;//�ͻ�����
	private String gctnm;//�ͻ�����
	private String gctarea;//�ͻ�����
	private	String cltnm;//���˿ͻ�����
	private String gcttype;//�ͻ����
	
	private String gctprovince;//ʡ��
	private String pdtnm;//��Ʒ����
	private Double pdtprc;//ԭ��
	private Double fdtprc;//�ۼ�
	private Integer fdtqnt;//����
	private Date folchgdt;//��������
	private String ncdypname;//����ʦ
	
	private String ictgender;//�Ա�
	private Integer ages;//��ʼ����
	private Integer aget;//��������
	
	private Double prices;//��ͼ۸�
	private Double pricet;//��߼۸�
	
	private String earcase;//������
	
	//�̶��ʲ�
	private String astid;//��ˮ��
	private String astgctid;//�ͻ�����
	private String astgctnm;//�ͻ�����
	private String pdtut;//��λ
	private String astut;//�̶��ʲ���λ
	private String astpdtnm;//�̶��ʲ�����
	private String astopr;//�Ǽ�Ա����
	private Double astprc;//�̶��ʲ����
	private String astqnt;//�̶��ʲ�����
	private String astnote;//��ע
	private Date astdt;//�Ǽ�����
	private Date start;//��ʼ����
	private Date end;//��������
	private Date stui;//��ʼ����
	private Date etui;//��������
	
	//̯������
	private String arzid;// ��ˮ��
	private String arzgctid;// �ͻ�����
	private String arzgctnm;// �ͻ�����
	private String arzitem;// ̯����Ŀ
	private Double arzamount;// ̯���ܽ��
	private Double arzmon;// ̯�����
	private String arzmonth;// ̯���·�
	private Date arzdt;// �Ǽ�����
	private String arzopr;// �Ǽ�Ա����
	private String arzoprnm;// �Ǽ�Ա����
	private String arzisexp;//�Ƿ���
	private Date arzstdt;// ��ʼʱ��
	private Date arzexpdt;// ��������
	private String arznt;//��ע
	private String arzcontract;//��ͬ����

	// ����̯��
	private Double harzamount;// ̯���ܽ��
	private String harzmonth;// ̯���·�
	private Double harzmon;// ̯�����
	private Date harzstdt;// ̯����ʼ����
	private Date harzexpdt;// ̯����������
	private String harzisexp;// �Ƿ���
	private String harzcontract;// ��ͬ����
	private String harznt;// ��ע

	// װ�޷�̯��
	private Double farzamount;// ̯���ܽ��
	private String farzmonth;// ̯���·�
	private Double farzmon;// ̯�����
	private Date farzstdt;// ̯����ʼ����
	private Date farzexpdt;// ̯����������
	private String farzisexp;// �Ƿ���
	private String farzcontract;// ��ͬ����
	private String farznt;// ��ע

	// �豸̯��
	private Double darzamount;// ̯���ܽ��
	private String darzmonth;// ̯���·�
	private Double darzmon;// ̯�����
	private Date darzstdt;// ̯����ʼ����
	private Date darzexpdt;// ̯����������
	private String darzisexp;// �Ƿ���
	private String darzcontract;// ��ͬ����
	private String darznt;// ��ע

	// �̶��ʲ�̯��
	private Double aarzamount;// ̯���ܽ��
	private String aarzmonth;// ̯���·�
	private Double aarzmon;// ̯�����
	private Date aarzstdt;// ̯����ʼ����
	private Date aarzexpdt;// ̯����������
	private String aarzisexp;// �Ƿ���
	private String aarzcontract;// ��ͬ����
	private String aarznt;// ��ע

	// װ�÷�̯��
	private Double tarzamount;// ̯���ܽ��
	private String tarzmonth;// ̯���·�
	private Double tarzmon;// ̯�����
	private Date tarzstdt;// ̯����ʼ����
	private Date tarzexpdt;// ̯����������
	private String tarzisexp;// �Ƿ���
	private String tarzcontract;// ��ͬ����
	private String tarznt;// ��ע

	// �����̯��
	private Double oarzamount;// ̯���ܽ��
	private String oarzmonth;// ̯���·�
	private Double oarzmon;// ̯�����
	private Date oarzstdt;// ̯����ʼ����
	private Date oarzexpdt;// ̯����������
	private String oarzisexp;// �Ƿ���
	private String oarzcontract;// ��ͬ����
	private String oarznt;// ��ע
	
	//����
	private String stoid;//�����
	private String stogrcliid;//����ͻ�����
	private String stogrpnm;//�ͻ�����
	private String stoproid;//��Ʒ����
	private String stoprotype;//��Ʒ����
	private String stoproname;//��Ʒ����
	private Integer stoamount;//����(���/����)
	private Integer storqnt;//ʣ������
	private Integer stoexp;//�������
	private String stoisexp;//����Ƿ���
	private String stoamountun;//������λ
	private Double stoproprice;//����
	private Date stodate;//���������
	private Integer stoactype;//������1�����-1�����⣩
	private String storemark;//��ע
	private String stooprcd;//����Ա����
	private String stodisc;//�Ƿ��ѱ���
	private String days;//��������
	private String isrepair;//�Ƿ�ά�޶���
	
	private Double pricesbox;//��ʽ���۸����
	
	private Integer gctexd;//�������
	
	public String getIsrepair() {
		return isrepair;
	}

	public void setIsrepair(String isrepair) {
		this.isrepair = isrepair;
	}

	public Integer getIvtyear() {
		return ivtyear;
	}

	public void setIvtyear(Integer ivtyear) {
		this.ivtyear = ivtyear;
	}

	public Integer getIvtmonth() {
		return ivtmonth;
	}

	public void setIvtmonth(Integer ivtmonth) {
		this.ivtmonth = ivtmonth;
	}

	public String getIvtgcltid() {
		return ivtgcltid;
	}

	public void setIvtgcltid(String ivtgcltid) {
		this.ivtgcltid = ivtgcltid;
	}

	public String getIvtcltid() {
		return ivtcltid;
	}

	public void setIvtcltid(String ivtcltid) {
		this.ivtcltid = ivtcltid;
	}

	public String getIvtpdtid() {
		return ivtpdtid;
	}

	public void setIvtpdtid(String ivtpdtid) {
		this.ivtpdtid = ivtpdtid;
	}

	public Integer getIvtlmqnt() {
		return ivtlmqnt;
	}

	public void setIvtlmqnt(Integer ivtlmqnt) {
		this.ivtlmqnt = ivtlmqnt;
	}

	public Integer getIvtlsqnt() {
		return ivtlsqnt;
	}

	public void setIvtlsqnt(Integer ivtlsqnt) {
		this.ivtlsqnt = ivtlsqnt;
	}

	public Integer getIvtpqnt() {
		return ivtpqnt;
	}

	public void setIvtpqnt(Integer ivtpqnt) {
		this.ivtpqnt = ivtpqnt;
	}

	public Double getIvtpamnt() {
		return ivtpamnt;
	}

	public void setIvtpamnt(Double ivtpamnt) {
		this.ivtpamnt = ivtpamnt;
	}

	public String getIvtoprcd() {
		return ivtoprcd;
	}

	public void setIvtoprcd(String ivtoprcd) {
		this.ivtoprcd = ivtoprcd;
	}

	public Date getIvtoprdt() {
		return ivtoprdt;
	}

	public void setIvtoprdt(Date ivtoprdt) {
		this.ivtoprdt = ivtoprdt;
	}

	public String getIvtnote() {
		return ivtnote;
	}

	public void setIvtnote(String ivtnote) {
		this.ivtnote = ivtnote;
	}

	public Double getIvtdiscount() {
		return ivtdiscount;
	}

	public void setIvtdiscount(Double ivtdiscount) {
		this.ivtdiscount = ivtdiscount;
	}

	public BigDecimal getIvtid() {
		return ivtid;
	}

	public void setIvtid(BigDecimal ivtid) {
		this.ivtid = ivtid;
	}

	public String getIvtcltnm() {
		return ivtcltnm;
	}

	public void setIvtcltnm(String ivtcltnm) {
		this.ivtcltnm = ivtcltnm;
	}

	public String getIvttype() {
		return ivttype;
	}

	public void setIvttype(String ivttype) {
		this.ivttype = ivttype;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getIvtfree() {
		return ivtfree;
	}

	public void setIvtfree(String ivtfree) {
		this.ivtfree = ivtfree;
	}

	public String getIvttry() {
		return ivttry;
	}

	public void setIvttry(String ivttry) {
		this.ivttry = ivttry;
	}

	
	public String getActstaid() {
		return actstaid;
	}

	public void setActstaid(String actstaid) {
		this.actstaid = actstaid;
	}

	public Integer getActstayear() {
		return actstayear;
	}

	public void setActstayear(Integer actstayear) {
		this.actstayear = actstayear;
	}

	public Integer getActstamonth() {
		return actstamonth;
	}

	public void setActstamonth(Integer actstamonth) {
		this.actstamonth = actstamonth;
	}

	public String getActstagcltid() {
		return actstagcltid;
	}

	public void setActstagcltid(String actstagcltid) {
		this.actstagcltid = actstagcltid;
	}

	public Integer getActmqnt() {
		return actmqnt;
	}

	public void setActmqnt(Integer actmqnt) {
		this.actmqnt = actmqnt;
	}

	public Integer getActsqnt() {
		return actsqnt;
	}

	public void setActsqnt(Integer actsqnt) {
		this.actsqnt = actsqnt;
	}

	public String getActsta() {
		return actsta;
	}

	public void setActsta(String actsta) {
		this.actsta = actsta;
	}

	public String getActstanote() {
		return actstanote;
	}

	public void setActstanote(String actstanote) {
		this.actstanote = actstanote;
	}

	public String getGctnm() {
		return gctnm;
	}

	public void setGctnm(String gctnm) {
		this.gctnm = gctnm;
	}

	public String getGctarea() {
		return gctarea;
	}

	public void setGctarea(String gctarea) {
		this.gctarea = gctarea;
	}

	public String getIctgender() {
		return ictgender;
	}

	public void setIctgender(String ictgender) {
		this.ictgender = ictgender;
	}

	public Integer getAges() {
		return ages;
	}

	public void setAges(Integer ages) {
		this.ages = ages;
	}

	public Integer getAget() {
		return aget;
	}

	public void setAget(Integer aget) {
		this.aget = aget;
	}

	public Double getPrices() {
		return prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	public Double getPricet() {
		return pricet;
	}

	public void setPricet(Double pricet) {
		this.pricet = pricet;
	}

	public String getEarcase() {
		return earcase;
	}

	public void setEarcase(String earcase) {
		this.earcase = earcase;
	}

	public String getAstgctid() {
		return astgctid;
	}

	public void setAstgctid(String astgctid) {
		this.astgctid = astgctid;
	}


	public String getPdtut() {
		return pdtut;
	}

	public void setPdtut(String pdtut) {
		this.pdtut = pdtut;
	}

	
	public String getAstut() {
		return astut;
	}

	public void setAstut(String astut) {
		this.astut = astut;
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
	
	public Date getStui() {
		return stui;
	}

	public void setStui(Date stui) {
		this.stui = stui;
	}

	public Date getEtui() {
		return etui;
	}

	public void setEtui(Date etui) {
		this.etui = etui;
	}

	public String getAstpdtnm() {
		return astpdtnm;
	}

	public void setAstpdtnm(String astpdtnm) {
		this.astpdtnm = astpdtnm;
	}

	public Double getAstprc() {
		return astprc;
	}

	public void setAstprc(Double astprc) {
		this.astprc = astprc;
	}

	public String getAstqnt() {
		return astqnt;
	}

	public void setAstqnt(String astqnt) {
		this.astqnt = astqnt;
	}

	public String getAstnote() {
		return astnote;
	}

	public void setAstnote(String astnote) {
		this.astnote = astnote;
	}

	public Date getAstdt() {
		return astdt;
	}

	public void setAstdt(Date astdt) {
		this.astdt = astdt;
	}

	public String getAstid() {
		return astid;
	}

	public void setAstid(String astid) {
		this.astid = astid;
	}

	public String getAstopr() {
		return astopr;
	}

	public void setAstopr(String astopr) {
		this.astopr = astopr;
	}

	public String getAstgctnm() {
		return astgctnm;
	}

	public void setAstgctnm(String astgctnm) {
		this.astgctnm = astgctnm;
	}

	public String getArzid() {
		return arzid;
	}

	public void setArzid(String arzid) {
		this.arzid = arzid;
	}

	public String getArzgctid() {
		return arzgctid;
	}

	public void setArzgctid(String arzgctid) {
		this.arzgctid = arzgctid;
	}

	public String getArzgctnm() {
		return arzgctnm;
	}

	public void setArzgctnm(String arzgctnm) {
		this.arzgctnm = arzgctnm;
	}

	public String getArzitem() {
		return arzitem;
	}

	public void setArzitem(String arzitem) {
		this.arzitem = arzitem;
	}

	
	public Date getArzdt() {
		return arzdt;
	}

	public void setArzdt(Date arzdt) {
		this.arzdt = arzdt;
	}

	public String getArzopr() {
		return arzopr;
	}

	public void setArzopr(String arzopr) {
		this.arzopr = arzopr;
	}

	public Double getHarzamount() {
		return harzamount;
	}

	public void setHarzamount(Double harzamount) {
		this.harzamount = harzamount;
	}

	public String getHarzmonth() {
		return harzmonth;
	}

	public void setHarzmonth(String harzmonth) {
		this.harzmonth = harzmonth;
	}

	public Double getHarzmon() {
		return harzmon;
	}

	public void setHarzmon(Double harzmon) {
		this.harzmon = harzmon;
	}

	public Date getHarzstdt() {
		return harzstdt;
	}

	public void setHarzstdt(Date harzstdt) {
		this.harzstdt = harzstdt;
	}

	public Date getHarzexpdt() {
		return harzexpdt;
	}

	public void setHarzexpdt(Date harzexpdt) {
		this.harzexpdt = harzexpdt;
	}

	public String getHarzisexp() {
		return harzisexp;
	}

	public void setHarzisexp(String harzisexp) {
		this.harzisexp = harzisexp;
	}

	public String getHarzcontract() {
		return harzcontract;
	}

	public void setHarzcontract(String harzcontract) {
		this.harzcontract = harzcontract;
	}

	public String getHarznt() {
		return harznt;
	}

	public void setHarznt(String harznt) {
		this.harznt = harznt;
	}

	public Double getFarzamount() {
		return farzamount;
	}

	public void setFarzamount(Double farzamount) {
		this.farzamount = farzamount;
	}

	public String getFarzmonth() {
		return farzmonth;
	}

	public void setFarzmonth(String farzmonth) {
		this.farzmonth = farzmonth;
	}

	public Double getFarzmon() {
		return farzmon;
	}

	public void setFarzmon(Double farzmon) {
		this.farzmon = farzmon;
	}

	public Date getFarzstdt() {
		return farzstdt;
	}

	public void setFarzstdt(Date farzstdt) {
		this.farzstdt = farzstdt;
	}

	public Date getFarzexpdt() {
		return farzexpdt;
	}

	public void setFarzexpdt(Date farzexpdt) {
		this.farzexpdt = farzexpdt;
	}

	public String getFarzisexp() {
		return farzisexp;
	}

	public void setFarzisexp(String farzisexp) {
		this.farzisexp = farzisexp;
	}

	public String getFarzcontract() {
		return farzcontract;
	}

	public void setFarzcontract(String farzcontract) {
		this.farzcontract = farzcontract;
	}

	public String getFarznt() {
		return farznt;
	}

	public void setFarznt(String farznt) {
		this.farznt = farznt;
	}

	public Double getDarzamount() {
		return darzamount;
	}

	public void setDarzamount(Double darzamount) {
		this.darzamount = darzamount;
	}

	public String getDarzmonth() {
		return darzmonth;
	}

	public void setDarzmonth(String darzmonth) {
		this.darzmonth = darzmonth;
	}

	public Double getDarzmon() {
		return darzmon;
	}

	public void setDarzmon(Double darzmon) {
		this.darzmon = darzmon;
	}

	public Date getDarzstdt() {
		return darzstdt;
	}

	public void setDarzstdt(Date darzstdt) {
		this.darzstdt = darzstdt;
	}

	public Date getDarzexpdt() {
		return darzexpdt;
	}

	public void setDarzexpdt(Date darzexpdt) {
		this.darzexpdt = darzexpdt;
	}

	public String getDarzisexp() {
		return darzisexp;
	}

	public void setDarzisexp(String darzisexp) {
		this.darzisexp = darzisexp;
	}

	public String getDarzcontract() {
		return darzcontract;
	}

	public void setDarzcontract(String darzcontract) {
		this.darzcontract = darzcontract;
	}

	public String getDarznt() {
		return darznt;
	}

	public void setDarznt(String darznt) {
		this.darznt = darznt;
	}

	public Double getAarzamount() {
		return aarzamount;
	}

	public void setAarzamount(Double aarzamount) {
		this.aarzamount = aarzamount;
	}

	public String getAarzmonth() {
		return aarzmonth;
	}

	public void setAarzmonth(String aarzmonth) {
		this.aarzmonth = aarzmonth;
	}

	public Double getAarzmon() {
		return aarzmon;
	}

	public void setAarzmon(Double aarzmon) {
		this.aarzmon = aarzmon;
	}

	public Date getAarzstdt() {
		return aarzstdt;
	}

	public void setAarzstdt(Date aarzstdt) {
		this.aarzstdt = aarzstdt;
	}

	public Date getAarzexpdt() {
		return aarzexpdt;
	}

	public void setAarzexpdt(Date aarzexpdt) {
		this.aarzexpdt = aarzexpdt;
	}

	public String getAarzisexp() {
		return aarzisexp;
	}

	public void setAarzisexp(String aarzisexp) {
		this.aarzisexp = aarzisexp;
	}

	public String getAarzcontract() {
		return aarzcontract;
	}

	public void setAarzcontract(String aarzcontract) {
		this.aarzcontract = aarzcontract;
	}

	public String getAarznt() {
		return aarznt;
	}

	public void setAarznt(String aarznt) {
		this.aarznt = aarznt;
	}

	public Double getTarzamount() {
		return tarzamount;
	}

	public void setTarzamount(Double tarzamount) {
		this.tarzamount = tarzamount;
	}

	public String getTarzmonth() {
		return tarzmonth;
	}

	public void setTarzmonth(String tarzmonth) {
		this.tarzmonth = tarzmonth;
	}

	public Double getTarzmon() {
		return tarzmon;
	}

	public void setTarzmon(Double tarzmon) {
		this.tarzmon = tarzmon;
	}

	public Date getTarzstdt() {
		return tarzstdt;
	}

	public void setTarzstdt(Date tarzstdt) {
		this.tarzstdt = tarzstdt;
	}

	public Date getTarzexpdt() {
		return tarzexpdt;
	}

	public void setTarzexpdt(Date tarzexpdt) {
		this.tarzexpdt = tarzexpdt;
	}

	public String getTarzisexp() {
		return tarzisexp;
	}

	public void setTarzisexp(String tarzisexp) {
		this.tarzisexp = tarzisexp;
	}

	public String getTarzcontract() {
		return tarzcontract;
	}

	public void setTarzcontract(String tarzcontract) {
		this.tarzcontract = tarzcontract;
	}

	public String getTarznt() {
		return tarznt;
	}

	public void setTarznt(String tarznt) {
		this.tarznt = tarznt;
	}

	public Double getOarzamount() {
		return oarzamount;
	}

	public void setOarzamount(Double oarzamount) {
		this.oarzamount = oarzamount;
	}

	public String getOarzmonth() {
		return oarzmonth;
	}

	public void setOarzmonth(String oarzmonth) {
		this.oarzmonth = oarzmonth;
	}

	public Double getOarzmon() {
		return oarzmon;
	}

	public void setOarzmon(Double oarzmon) {
		this.oarzmon = oarzmon;
	}

	public Date getOarzstdt() {
		return oarzstdt;
	}

	public void setOarzstdt(Date oarzstdt) {
		this.oarzstdt = oarzstdt;
	}

	public Date getOarzexpdt() {
		return oarzexpdt;
	}

	public void setOarzexpdt(Date oarzexpdt) {
		this.oarzexpdt = oarzexpdt;
	}

	public String getOarzisexp() {
		return oarzisexp;
	}

	public void setOarzisexp(String oarzisexp) {
		this.oarzisexp = oarzisexp;
	}

	public String getOarzcontract() {
		return oarzcontract;
	}

	public void setOarzcontract(String oarzcontract) {
		this.oarzcontract = oarzcontract;
	}

	public String getOarznt() {
		return oarznt;
	}

	public void setOarznt(String oarznt) {
		this.oarznt = oarznt;
	}

	public String getArzmonth() {
		return arzmonth;
	}

	public void setArzmonth(String arzmonth) {
		this.arzmonth = arzmonth;
	}

	public String getArzisexp() {
		return arzisexp;
	}

	public void setArzisexp(String arzisexp) {
		this.arzisexp = arzisexp;
	}

	public String getArzoprnm() {
		return arzoprnm;
	}

	public void setArzoprnm(String arzoprnm) {
		this.arzoprnm = arzoprnm;
	}

	public Double getArzamount() {
		return arzamount;
	}

	public void setArzamount(Double arzamount) {
		this.arzamount = arzamount;
	}

	public Double getArzmon() {
		return arzmon;
	}

	public void setArzmon(Double arzmon) {
		this.arzmon = arzmon;
	}

	public Date getArzstdt() {
		return arzstdt;
	}

	public void setArzstdt(Date arzstdt) {
		this.arzstdt = arzstdt;
	}

	public Date getArzexpdt() {
		return arzexpdt;
	}

	public void setArzexpdt(Date arzexpdt) {
		this.arzexpdt = arzexpdt;
	}

	public String getArznt() {
		return arznt;
	}

	public void setArznt(String arznt) {
		this.arznt = arznt;
	}

	public String getArzcontract() {
		return arzcontract;
	}

	public void setArzcontract(String arzcontract) {
		this.arzcontract = arzcontract;
	}

	public String getStoid() {
		return stoid;
	}

	public void setStoid(String stoid) {
		this.stoid = stoid;
	}

	public String getStogrcliid() {
		return stogrcliid;
	}

	public void setStogrcliid(String stogrcliid) {
		this.stogrcliid = stogrcliid;
	}

	public String getStogrpnm() {
		return stogrpnm;
	}

	public void setStogrpnm(String stogrpnm) {
		this.stogrpnm = stogrpnm;
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

	public Integer getStorqnt() {
		return storqnt;
	}

	public void setStorqnt(Integer storqnt) {
		this.storqnt = storqnt;
	}

	public Integer getStoexp() {
		return stoexp;
	}

	public void setStoexp(Integer stoexp) {
		this.stoexp = stoexp;
	}

	public String getStoisexp() {
		return stoisexp;
	}

	public void setStoisexp(String stoisexp) {
		this.stoisexp = stoisexp;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Integer getGctexd() {
		return gctexd;
	}

	public void setGctexd(Integer gctexd) {
		this.gctexd = gctexd;
	}

	public String getGctid() {
		return gctid;
	}

	public void setGctid(String gctid) {
		this.gctid = gctid;
	}

	public String getGctprovince() {
		return gctprovince;
	}

	public void setGctprovince(String gctprovince) {
		this.gctprovince = gctprovince;
	}

	public String getPdtnm() {
		return pdtnm;
	}

	public void setPdtnm(String pdtnm) {
		this.pdtnm = pdtnm;
	}

	public Double getPdtprc() {
		return pdtprc;
	}

	public void setPdtprc(Double pdtprc) {
		this.pdtprc = pdtprc;
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

	public Date getFolchgdt() {
		return folchgdt;
	}

	public void setFolchgdt(Date folchgdt) {
		this.folchgdt = folchgdt;
	}

	public String getCltnm() {
		return	cltnm;
	}
	
	public void setCltnm(String cltnm) {
		this.cltnm =cltnm;
	}

	public String getNcdypname() {
		return ncdypname;
	}

	public void setNcdypname(String ncdypname) {
		this.ncdypname = ncdypname;
	}
	
	private String pdttype;//Ʒ��
	public String getPdttype() {
		return pdttype;
	}
	public void setPdttype(String pdttype) {
		this.pdttype = pdttype;
	}
	private String foltype;//��������
	public String getFoltype() {
		return foltype;
	}
	public void setFoltype(String foltype) {
		this.foltype = foltype;
	}

	public Double getPricesbox() {
		return pricesbox;
	}

	public void setPricesbox(Double pricesbox) {
		this.pricesbox = pricesbox;
	}

	public String getGcttype() {
		return gcttype;
	}

	public void setGcttype(String gcttype) {
		this.gcttype = gcttype;
	}
	
	
	
}
