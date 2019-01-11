package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//��������Ϣ��
public class SingleClient extends EntitySupport {
	private String ictid; 		// �ͻ�����
	private String ictnm; 		// ����
	private String ictgctid; 	// �����������
	private String ictpnm; 		// �ҳ�����
	private String ictgender; 	// �Ա�
	private Date ictbdt; 		// ��������
	private String ictaddr; 	// ��ϵ��ַ
	private String ictpcd; 		// ��������
	private String icttel; 		// ��ϵ�绰
	private String ictphis; 	// ʹ�ù���������������ʽ������ʽ������ʽ��δ������
	private String ictnt; 		// ��ע
	private String ictsrc; 		// ��Դ
	private String ictfrom;     //��Դ2
	private String ictoprcd; 	// ����Ա����
	private Date ictdate; 		// ����
	private String ictpro;      //ʡ��
	private String ictcity;     //��
	private String ictcounty;    //��
	private String ictphone; //�ֻ�
	private String ictdetailaddr;//��ϸ��ַ
	private String ictlandline; //�̶��绰
	private String ictgctnm; // ������������
	private Integer ivtyear;//ͳ�����
	private String repid;//������
	private String ictsid;//������
	private String gctnm;
	

	
	private String rsvid;  //ԤԼ��
	private String rsvnm;  //ԤԼ�ͻ�����
	private String rsvphone;  //ԤԼ�ֻ���
	private String ypnm;  //����ʦ����
	private Date rsvdate;  //ԤԼ����
	private String rsvsta;  //����״̬
	private String rsvgcltid; //ԤԼ����ͻ�����

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
	private String gumo;
	private String gmnote;
	private String jiancha;
	private String jcnote;
	private String chuandao;
	private String cdnote;
	private String ganyin;
	private String gynote;
	private String cdxing;
	private String gyxing;
	private String hunhexing;
	private String hhxnote;
	private String chuli;
	private String clnote;
	
	//��������ͼ
	private String fcttlid;//��������ͼ���
	private Date fctcdt; // ��������
	private String fctnt; // ��ע
	
	//����������������
	private String fcypid;//���������������
	private String fcypgl;//����ǿ����
	private String fcypgr;//����ǿ����
	private String fcypwzt;//δ����
	private String fcypdzl;//����������
	private String fcypdzr;//����������
	private String fcypsz;//˫������
	
	//�ط�-�ۺ��������
	private String bvid;//�ۺ��������id
	private String bvassess;//��Ч������
	private String bvcase1;//��һ�λطü�¼
	private String bveffect1;//Ч������
	private String bvmethod1;//�ط÷���
	private String bvvisitor1;//�ط���
	private Date bvdate1;//�ط�����
	private String bvcase2;//�ڶ��λطü�¼
	private String bveffect2;//Ч������
	private String bvmethod2;//�ط÷���
	private String bvvisitor2;//�ط���
	private Date bvdate2;//�ط�����
	private String bvcase3;//�����λطü�¼
	private String bveffect3;//Ч������
	private String bvmethod3;//�ط÷���
	private String bvvisitor3;//�ط���
	private Date bvdate3;//�ط�����
	private String ictage;//age
	private Date start; // ��������
	private Date end; // ��������
	private String bvlefttype;//����������ͺ�
	private String bvleftnum;//���������������
	private String bvrighttype;//�Ҷ��������ͺ�
	private String bvrightnum;//�Ҷ�������������
	
	public String getBvlefttype() {
		return bvlefttype;
	}
	public void setBvlefttype(String bvlefttype) {
		this.bvlefttype = bvlefttype;
	}
	public String getBvleftnum() {
		return bvleftnum;
	}
	public void setBvleftnum(String bvleftnum) {
		this.bvleftnum = bvleftnum;
	}
	public String getBvrighttype() {
		return bvrighttype;
	}
	public void setBvrighttype(String bvrighttype) {
		this.bvrighttype = bvrighttype;
	}
	public String getBvrightnum() {
		return bvrightnum;
	}
	public void setBvrightnum(String bvrightnum) {
		this.bvrightnum = bvrightnum;
	}
	public String getIctage() {
		return ictage;
	}
	public void setIctage(String ictage) {
		this.ictage = ictage;
	}
	public String getBvid() {
		return bvid;
	}
	public void setBvid(String bvid) {
		this.bvid = bvid;
	}
	public String getBvassess() {
		return bvassess;
	}
	public void setBvassess(String bvassess) {
		this.bvassess = bvassess;
	}
	public String getBvcase1() {
		return bvcase1;
	}
	public void setBvcase1(String bvcase1) {
		this.bvcase1 = bvcase1;
	}
	public String getBveffect1() {
		return bveffect1;
	}
	public void setBveffect1(String bveffect1) {
		this.bveffect1 = bveffect1;
	}
	public String getBvmethod1() {
		return bvmethod1;
	}
	public void setBvmethod1(String bvmethod1) {
		this.bvmethod1 = bvmethod1;
	}
	public String getBvvisitor1() {
		return bvvisitor1;
	}
	public void setBvvisitor1(String bvvisitor1) {
		this.bvvisitor1 = bvvisitor1;
	}
	public Date getBvdate1() {
		return bvdate1;
	}
	public void setBvdate1(Date bvdate1) {
		this.bvdate1 = bvdate1;
	}
	public String getBvcase2() {
		return bvcase2;
	}
	public void setBvcase2(String bvcase2) {
		this.bvcase2 = bvcase2;
	}
	public String getBveffect2() {
		return bveffect2;
	}
	public void setBveffect2(String bveffect2) {
		this.bveffect2 = bveffect2;
	}
	public String getBvmethod2() {
		return bvmethod2;
	}
	public void setBvmethod2(String bvmethod2) {
		this.bvmethod2 = bvmethod2;
	}
	public String getBvvisitor2() {
		return bvvisitor2;
	}
	public void setBvvisitor2(String bvvisitor2) {
		this.bvvisitor2 = bvvisitor2;
	}
	public Date getBvdate2() {
		return bvdate2;
	}
	public void setBvdate2(Date bvdate2) {
		this.bvdate2 = bvdate2;
	}
	public String getBvcase3() {
		return bvcase3;
	}
	public void setBvcase3(String bvcase3) {
		this.bvcase3 = bvcase3;
	}
	public String getBveffect3() {
		return bveffect3;
	}
	public void setBveffect3(String bveffect3) {
		this.bveffect3 = bveffect3;
	}
	public String getBvmethod3() {
		return bvmethod3;
	}
	public void setBvmethod3(String bvmethod3) {
		this.bvmethod3 = bvmethod3;
	}
	public String getBvvisitor3() {
		return bvvisitor3;
	}
	public void setBvvisitor3(String bvvisitor3) {
		this.bvvisitor3 = bvvisitor3;
	}
	public Date getBvdate3() {
		return bvdate3;
	}
	public void setBvdate3(Date bvdate3) {
		this.bvdate3 = bvdate3;
	}
	
	//������������
		private String fcscid;//���������������
		public String getFcypid() {
			return fcypid;
		}
		public void setFcypid(String fcypid) {
			this.fcypid = fcypid;
		}
		public String getFcypgl() {
			return fcypgl;
		}
		public void setFcypgl(String fcypgl) {
			this.fcypgl = fcypgl;
		}
		public String getFcypgr() {
			return fcypgr;
		}
		public void setFcypgr(String fcypgr) {
			this.fcypgr = fcypgr;
		}
		public String getFcypwzt() {
			return fcypwzt;
		}
		public void setFcypwzt(String fcypwzt) {
			this.fcypwzt = fcypwzt;
		}
		public String getFcypdzl() {
			return fcypdzl;
		}
		public void setFcypdzl(String fcypdzl) {
			this.fcypdzl = fcypdzl;
		}
		public String getFcypdzr() {
			return fcypdzr;
		}
		public void setFcypdzr(String fcypdzr) {
			this.fcypdzr = fcypdzr;
		}
		public String getFcypsz() {
			return fcypsz;
		}
		public void setFcypsz(String fcypsz) {
			this.fcypsz = fcypsz;
		}
		

	public String getGumo() {
		return gumo;
	}
	public void setGumo(String gumo) {
		this.gumo = gumo;
	}
	public String getJiancha() {
		return jiancha;
	}
	public void setJiancha(String jiancha) {
		this.jiancha = jiancha;
	}
	public String getChuandao() {
		return chuandao;
	}
	public void setChuandao(String chuandao) {
		this.chuandao = chuandao;
	}
	public String getGanyin() {
		return ganyin;
	}
	public void setGanyin(String ganyin) {
		this.ganyin = ganyin;
	}
	public String getChuli() {
		return chuli;
	}
	public void setChuli(String chuli) {
		this.chuli = chuli;
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
	
	public String getgmnote() {
		return gmnote;
	}
	public void setgmnote(String gmnote) {
		this.gmnote = gmnote;
	}
	
	public String getjcnote() {
		return jcnote;
	}
	public void setjcnote(String jcnote) {
		this.jcnote = jcnote;
	}
	
	public String getcdnote() {
		return cdnote;
	}
	public void setcdnote(String cdnote) {
		this.cdnote = cdnote;
	}
	
	public String getgynote() {
		return gynote;
	}
	public void setgynote(String gynote) {
		this.gynote = gynote;
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
	public String gethhxnote() {
		return hhxnote;
	}
	public void sethhxnote(String hhxnote) {
		this.hhxnote = hhxnote;
	}
	
	public String getclnote() {
		return clnote;
	}
	public void setclnote(String clnote) {
		this.clnote = clnote;
	}
	public String getRsvid() {
		return rsvid;
	}
	public void setRsvid(String rsvid) {
		this.rsvid = rsvid;
	}
	public String getRsvnm() {
		return rsvnm;
	}
	public void setRsvnm(String rsvnm) {
		this.rsvnm = rsvnm;
	}
	public String getRsvphone() {
		return rsvphone;
	}
	public void setRsvphone(String rsvphone) {
		this.rsvphone = rsvphone;
	}
	public String getYpnm() {
		return ypnm;
	}
	public void setYpnm(String ypnm) {
		this.ypnm = ypnm;
	}
	public Date getRsvdate() {
		return rsvdate;
	}
	public void setRsvdate(Date rsvdate) {
		this.rsvdate = rsvdate;
	}
	public String getRsvsta() {
		return rsvsta;
	}
	public void setRsvsta(String rsvsta) {
		this.rsvsta = rsvsta;
	}
	public String getRsvgcltid() {
		return rsvgcltid;
	}
	public void setRsvgcltid(String rsvgcltid) {
		this.rsvgcltid = rsvgcltid;
	}
	public String getIctid() {
		return ictid;
	}
	public void setIctid(String ictid) {
		this.ictid = ictid;
	}
	public String getIctnm() {
		return ictnm;
	}
	public void setIctnm(String ictnm) {
		this.ictnm = ictnm;
	}
	public String getIctgctid() {
		return ictgctid;
	}
	public void setIctgctid(String ictgctid) {
		this.ictgctid = ictgctid;
	}
	public String getIctpnm() {
		return ictpnm;
	}
	public void setIctpnm(String ictpnm) {
		this.ictpnm = ictpnm;
	}
	public String getIctgender() {
		return ictgender;
	}
	public void setIctgender(String ictgender) {
		this.ictgender = ictgender;
	}
	public Date getIctbdt() {
		return ictbdt;
	}
	public void setIctbdt(Date ictbdt) {
		this.ictbdt = ictbdt;
	}
	public String getIctaddr() {
		return ictaddr;
	}
	public void setIctaddr(String ictaddr) {
		this.ictaddr = ictaddr;
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
	public String getIctoprcd() {
		return ictoprcd;
	}
	public void setIctoprcd(String ictoprcd) {
		this.ictoprcd = ictoprcd;
	}
	public Date getIctdate() {
		return ictdate;
	}
	public void setIctdate(Date ictdate) {
		this.ictdate = ictdate;
	}
	public String getIctsid() {
		return ictsid;
	}
	public void setIctsid(String ictsid) {
		this.ictsid = ictsid;
	}
	public String getIctgctnm() {
		return ictgctnm;
	}
	public void setIctgctnm(String ictgctnm) {
		this.ictgctnm = ictgctnm;
	}
	public Integer getIvtyear() {
		return ivtyear;
	}
	public void setIvtyear(Integer ivtyear) {
		this.ivtyear = ivtyear;
	}
	public String getGctnm() {
		return gctnm;
	}
	public void setGctnm(String gctnm) {
		this.gctnm = gctnm;
	}
	public String getRepid() {
		return repid;
	}
	public void setRepid(String repid) {
		this.repid = repid;
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
	public String getIctdetailaddr() {
		return ictdetailaddr;
	}
	public void setIctdetailaddr(String ictdetailaddr) {
		this.ictdetailaddr = ictdetailaddr;
	}
	public String getFcttlid() {
		return fcttlid;
	}
	public void setFcttlid(String fcttlid) {
		this.fcttlid = fcttlid;
	}
	public Date getFctcdt() {
		return fctcdt;
	}
	public void setFctcdt(Date fctcdt) {
		this.fctcdt = fctcdt;
	}
	public String getFctnt() {
		return fctnt;
	}
	public void setFctnt(String fctnt) {
		this.fctnt = fctnt;
	}
	public String getFcscid() {
		return fcscid;
	}
	public void setFcscid(String fcscid) {
		this.fcscid = fcscid;
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
	public String getIctfrom() {
		return ictfrom;
	}
	public void setIctfrom(String ictfrom) {
		this.ictfrom = ictfrom;
	}
	

	
}
