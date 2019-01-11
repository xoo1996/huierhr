package org.radf.apps.commons.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//����������Ϣ��
public class Order extends EntitySupport {
	private String folno; // ��������
	private String folctid;// �ͻ�����
	private String folindctid;  //���˿ͻ����
	private Date foldt; // ����
	private String folopr; // ����Ա����
	private String folway; // ������ʽ
	private String folsopr; // ����Ա����
	private Date folsdt; // ��������
	private Date folpdt; // �ƻ���������
	private String folsno; // ��ݺ�
	private String folsta; // ����״̬
	private String folnt; // ��ע
	private String foltype;// �������
	private String foldes; // �����ݶ���
	private String folstoid;//�����
	private String folischg;//�Ƿ����շ�
	private String tp2;  //�ж϶����Ƿ�ɱ��޸ģ����ͨ���Ĳ��ܱ��޸ģ�
	private String fdtpid;
	private String ictpro;      //ʡ��
	private String ictcity;     //��
	private String ictcounty;    //��
	private String ictphone; //�ֻ�
	private String ictlandline; //�̶��绰
	private String ictdetailaddr;//��ϸ��ַ
	//���ƶ���

	private String folurgent; //�Ƿ�Ӽ�����
	private Double folurgfee; //�Ӽ���
	private String foldelay;   //�ӳ����޵�����
	private String delayright;//�Ҷ��ӳ�����ʱ��
	private String delayleft;//����ӳ�����ʱ��
	private Integer feeright;//�Ҷ����޷���
	private Integer feeleft;//������޷���
	private String isright;//�Ҷ��Ƿ�����
	private String isleft;//����Ƿ�����
	private Integer leftpay;//���ʵ��������
	private Integer rightpay;//�Ҷ�ʵ��������
	private Double foldps;//����
	private Double folldps;//�������
	private Double folrdps;//�Ҷ�����
	private String fdtcltnm;//������ϸ���˿ͻ�����
	private String folctnm;
	private Date fdtexadt;//�˻��������
	private String fdtfno;//������
	private String fdtono;//ԭ��������
	private Date folexdt;//�������
	
    //���� ��ʾ��ģ��Ϣ
	private String fdtfree;
	private String fdtprc;
	
	private String ictid;  //�ͻ����
	private String ictnm;  //�ͻ�����

	private String ictgctid; // �����������
	private String  ictgender;
	private Date ictbdt; // ��������
	private String ictaddr; // ��ϵ��ַ
	private String icttel; // ��ϵ�绰
	private String ictphis; // ʹ�ù���������������ʽ������ʽ������ʽ��δ������
	private String ictnt; // ��ע
	private String ictsrc; // ��Դ
	private String ictfrom;     //��Դ2
	private String dgndoc;//����ʦ
	
	// �������Լ�¼��

	// ����ǵ�
	private String lg250;
	private String lg500;
	private String lg1000;
	private String lg2000;
	private String lg4000;
	private String lgavg;

	// �������
	private String lq250;
	private String lq500;
	private String lq1000;
	private String lq2000;
	private String lq4000;
	private String lqavg;

	// �Ҷ��ǵ�
	private String rg250;
	private String rg500;
	private String rg1000;
	private String rg2000;
	private String rg4000;
	private String rgavg;

	// �Ҷ�����
	private String rq250;
	private String rq500;
	private String rq1000;
	private String rq2000;
	private String rq4000;
	private String rqavg;


	//�¼ӹǵ�����
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
	
	

	// ��ʱ
	private Date start;
	private Date end;
	private Date exstart;
	private Date exend;
	private String pdtid;// ��Ʒ����

	private String cltnm;//���˿ͻ�
	private String sid;//�������
	private String gctnm;  //��������
	private String tdeisexa;
	
	  //�ͻ���ϸ��Ϣ
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

	
	
	public String[] getChuli() {
		return chuli;
	}

	public void setChuli(String[] chuli) {
		this.chuli = chuli;
	}

	public String getclnote() {
		return clnote;
	}

	public void setclnote(String clnote) {
		this.clnote = clnote;
	}

	public String gethhxnote() {
		return hhxnote;
	}

	public void sethhxnote(String hhxnote) {
		this.hhxnote = hhxnote;
	}

	public String getcdxing() {
		return cdxing;
	}

	public void setcdxing(String cdxing) {
		this.cdxing = cdxing;
	}

	public String getgyxing() {
		return gyxing;
	}

	public void setgyxing(String gyxing) {
		this.gyxing = gyxing;
	}

	public String getHunhexing() {
		return hunhexing;
	}

	public void setHunhexing(String hunhexing) {
		this.hunhexing = hunhexing;
	}

	public String[] getChuandao() {
		return chuandao;
	}

	public void setChuandao(String[] chuandao) {
		this.chuandao = chuandao;
	}

	public String getcdnote() {
		return cdnote;
	}

	public void setcdnote(String cdnote) {
		this.cdnote = cdnote;
	}

	public String[] getGanyin() {
		return ganyin;
	}

	public void setGanyin(String[] ganyin) {
		this.ganyin = ganyin;
	}

	public String getgynote() {
		return gynote;
	}

	public void setgynote(String gynote) {
		this.gynote = gynote;
	}

	public String[] getJiancha() {
		return jiancha;
	}

	public void setJiancha(String[] jiancha) {
		this.jiancha = jiancha;
	}

	public String getjcnote() {
		return jcnote;
	}

	public void setjcnote(String jcnote) {
		this.jcnote = jcnote;
	}

	public String[] getGumo() {
		return gumo;
	}

	public void setGumo(String[] gumo) {
		this.gumo = gumo;
	}

	public String getgmnote() {
		return gmnote;
	}

	public void setgmnote(String gmnote) {
		this.gmnote = gmnote;
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

	public String getyznote() {
		return yznote;
	}

	public void setyznote(String yznote) {
		this.yznote = yznote;
	}

	public String getShoushushi() {
		return shoushushi;
	}

	public void setShoushushi(String shoushushi) {
		this.shoushushi = shoushushi;
	}

	public String getsssnote() {
		return sssnote;
	}

	public void setsssnote(String sssnote) {
		this.sssnote = sssnote;
	}

	public String getYichuanshi() {
		return yichuanshi;
	}

	public void setYichuanshi(String yichuanshi) {
		this.yichuanshi = yichuanshi;
	}

	public String getycsnote() {
		return ycsnote;
	}

	public void setycsnote(String ycsnote) {
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

	public String gettnbnote() {
		return tnbnote;
	}

	public void settnbnote(String tnbnote) {
		this.tnbnote = tnbnote;
	}

	public String getZhutingqi() {
		return zhutingqi;
	}

	public void setZhutingqi(String zhutingqi) {
		this.zhutingqi = zhutingqi;
	}

	public String getztqtime() {
		return ztqtime;
	}

	public void setztqtime(String ztqtime) {
		this.ztqtime = ztqtime;
	}

	public String getztqtype() {
		return ztqtype;
	}

	public void setztqtype(String ztqtype) {
		this.ztqtype = ztqtype;
	}

	public String getztqxg() {
		return ztqxg;
	}

	public void setztqxg(String ztqxg) {
		this.ztqxg = ztqxg;
	}

	public String getLosetime() {
		return losetime;
	}

	public void setLosetime(String losetime) {
		this.losetime = losetime;
	}

	public String getlstnote() {
		return lstnote;
	}

	public void setlstnote(String lstnote) {
		this.lstnote = lstnote;
	}

	/**
     * ��ѯά�޼�¼�����ֶ�
     * 
     */
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
	private String reppid; // �������ͺ�
	private String repgctid; // ���޵�λ����
	private String repregnames; // ����ά��Ա����
	private String reptip; // ��ܰ��ʾ1
	private String reptip1; // ��ܰ��ʾ2
	private String repsta; // ά��״̬
	private String repcpy; // ���޳���
	private Date reppdate;// �ƻ��������
	private String repiskp;//�Ƿ�Ʊ
	private Double repaprc;//����Ԥ��
	// ����ͻ���
	private String repgctnm; // ����ͻ�����
	// ��Ʒ��
	private String pdtnm; // ��������
	
	
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
	private String isrepair;//�Ƿ�ά�޶���
	private String isbill; //�Ƿ�Ʊ
	
	//�ͻ���Ϣ��ϸ
	
	public String getIsbill() {
		return isbill;
	}

	public String getIsrepair() {
		return isrepair;
	}

	public void setIsrepair(String isrepair) {
		this.isrepair = isrepair;
	}

	public void setIsbill(String isbill) {
		this.isbill = isbill;
	}

	public String getFolsopr() {
		return folsopr;
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

	public void setFolsopr(String folsopr) {
		this.folsopr = folsopr;
	}

	public Date getFolsdt() {
		return folsdt;
	}

	public void setFolsdt(Date folsdt) {
		this.folsdt = folsdt;
	}

	public String getFolsno() {
		return folsno;
	}

	public void setFolsno(String folsno) {
		this.folsno = folsno;
	}

	public String getFolway() {
		return folway;
	}

	public void setFolway(String folway) {
		this.folway = folway;
	}

	public String getFolsta() {
		return folsta;
	}

	public void setFolsta(String folsta) {
		this.folsta = folsta;
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

	public Date getFoldt() {
		return foldt;
	}

	public void setFoldt(Date foldt) {
		this.foldt = foldt;
	}

	public String getFolopr() {
		return folopr;
	}

	public void setFolopr(String folopr) {
		this.folopr = folopr;
	}

	public String getFolnt() {
		return folnt;
	}

	public void setFolnt(String folnt) {
		this.folnt = folnt;
	}

	public Date getFolpdt() {
		return folpdt;
	}

	public void setFolpdt(Date folpdt) {
		this.folpdt = folpdt;
	}

	public String getFoltype() {
		return foltype;
	}

	public void setFoltype(String foltype) {
		this.foltype = foltype;
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
	
	public Date getExstart() {
		return exstart;
	}

	public void setExstart(Date exstart) {
		this.exstart = exstart;
	}

	public Date getExend() {
		return exend;
	}

	public void setExend(Date exend) {
		this.exend = exend;
	}

	public String getPdtid() {
		return pdtid;
	}

	public void setPdtid(String pdtid) {
		this.pdtid = pdtid;
	}

	public String getCltnm() {
		return cltnm;
	}

	public void setCltnm(String cltnm) {
		this.cltnm = cltnm;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public String getFolstoid() {
		return folstoid;
	}

	public void setFolstoid(String folstoid) {
		this.folstoid = folstoid;
	}

	public String getFolindctid() {
		return folindctid;
	}

	public void setFolindctid(String folindctid) {
		this.folindctid = folindctid;
	}

	public String getFolurgent() {
		return folurgent;
	}

	public void setFolurgent(String folurgent) {
		this.folurgent = folurgent;
	}

	public String getFoldelay() {
		return foldelay;
	}

	public void setFoldelay(String foldelay) {
		this.foldelay = foldelay;
	}

	

	public Double getFolldps() {
		return folldps;
	}

	public void setFolldps(Double folldps) {
		this.folldps = folldps;
	}

	public Double getFolrdps() {
		return folrdps;
	}

	public void setFolrdps(Double folrdps) {
		this.folrdps = folrdps;
	}

	public String getIctnm() {
		return ictnm;
	}

	public void setIctnm(String ictnm) {
		this.ictnm = ictnm;
	}

	public String getIctgender() {
		return ictgender;
	}

	public void setIctgender(String ictgender) {
		this.ictgender = ictgender;
	}

	public String getIctaddr() {
		return ictaddr;
	}

	public void setIctaddr(String ictaddr) {
		this.ictaddr = ictaddr;
	}

	public String getIctid() {
		return ictid;
	}

	public void setIctid(String ictid) {
		this.ictid = ictid;
	}

	public String getIctgctid() {
		return ictgctid;
	}

	public void setIctgctid(String ictgctid) {
		this.ictgctid = ictgctid;
	}

	public Date getIctbdt() {
		return ictbdt;
	}

	public void setIctbdt(Date ictbdt) {
		this.ictbdt = ictbdt;
	}

	public String getIcttel() {
		return icttel;
	}

	public void setIcttel(String icttel) {
		this.icttel = icttel;
	}

	public String getIctphis() {
		return ictphis;
	}

	public void setIctphis(String ictphis) {
		this.ictphis = ictphis;
	}

	public String getIctnt() {
		return ictnt;
	}

	public void setIctnt(String ictnt) {
		this.ictnt = ictnt;
	}

	public String getIctsrc() {
		return ictsrc;
	}

	public void setIctsrc(String ictsrc) {
		this.ictsrc = ictsrc;
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

	public String getGctnm() {
		return gctnm;
	}

	public void setGctnm(String gctnm) {
		this.gctnm = gctnm;
	}

	public String getFolischg() {
		return folischg;
	}

	public void setFolischg(String folischg) {
		this.folischg = folischg;
	}

	public String getTp2() {
		return tp2;
	}

	public void setTp2(String tp2) {
		this.tp2 = tp2;
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

	public Double getFoldps() {
		return foldps;
	}

	public void setFoldps(Double foldps) {
		this.foldps = foldps;
	}

	public String getFdtcltnm() {
		return fdtcltnm;
	}

	public void setFdtcltnm(String fdtcltnm) {
		this.fdtcltnm = fdtcltnm;
	}

	public String getFolctnm() {
		return folctnm;
	}

	public void setFolctnm(String folctnm) {
		this.folctnm = folctnm;
	}

	public String getFdtfree() {
		return fdtfree;
	}

	public void setFdtfree(String fdtfree) {
		this.fdtfree = fdtfree;
	}

	public String getFdtprc() {
		return fdtprc;
	}

	public void setFdtprc(String fdtprc) {
		this.fdtprc = fdtprc;
	}

	public String getFdtpid() {
		return fdtpid;
	}

	public void setFdtpid(String fdtpid) {
		this.fdtpid = fdtpid;
	}

	public String getDgndoc() {
		return dgndoc;
	}

	public void setDgndoc(String dgndoc) {
		this.dgndoc = dgndoc;
	}

	public Double getFolurgfee() {
		return folurgfee;
	}

	public void setFolurgfee(Double folurgfee) {
		this.folurgfee = folurgfee;
	}

	public String getTdeisexa() {
		return tdeisexa;
	}

	public void setTdeisexa(String tdeisexa) {
		this.tdeisexa = tdeisexa;
	}

	public Date getFdtexadt() {
		return fdtexadt;
	}

	public void setFdtexadt(Date fdtexadt) {
		this.fdtexadt = fdtexadt;
	}
	
	public Date getFolexdt() {
		return folexdt;
	}

	public void setFolexdt(Date folexdt) {
		this.folexdt = folexdt;
	}

	public String getFdtfno() {
		return fdtfno;
	}

	public void setFdtfno(String fdtfno) {
		this.fdtfno = fdtfno;
	}
	
	public String getFdtono() {
		return fdtono;
	}

	public void setFdtono(String fdtono) {
		this.fdtono = fdtono;
	}

	public String getdelayright() {
		return delayright;
	}

	public void setdelayright(String delayright) {
		this.delayright = delayright;
	}

	public String getdelayleft() {
		return delayleft;
	}

	public void setdelayleft(String delayleft) {
		this.delayleft = delayleft;
	}

	public Integer getfeeright() {
		return feeright;
	}

	public void setfeeright(Integer feeright) {
		this.feeright = feeright;
	}

	public Integer getfeeleft() {
		return feeleft;
	}

	public void setfeeleft(Integer feeleft) {
		this.feeleft = feeleft;
	}

	public String getisright() {
		return isright;
	}

	public void setisright(String isright) {
		this.isright = isright;
	}

	public String getisleft() {
		return isleft;
	}

	public void setisleft(String isleft) {
		this.isleft = isleft;
	}

	public Integer getLeftpay() {
		return leftpay;
	}

	public void setLeftpay(Integer leftpay) {
		this.leftpay = leftpay;
	}

	public Integer getRightpay() {
		return rightpay;
	}

	public void setRightpay(Integer rightpay) {
		this.rightpay = rightpay;
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