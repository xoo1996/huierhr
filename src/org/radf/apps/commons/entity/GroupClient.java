package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//����ͻ���Ϣ��
public class GroupClient extends EntitySupport {
	private String gctid; // �ͻ�����
	private String gctnm; // �ͻ�����
	private String gctaddr; // �ͻ���ַ
	private String gctptcd; // �ͻ��ʱ�
	private String gctemail; // �ͻ�Email
	private String gctcnm; // ��ϵ������
	private String gcttel;// ��ϵ�绰
	private String gctnt; // ��ע
	private String gctoprcd;// ����Ա����
	private Date gctdate; // ����
	private Date gctstart; // ��ҵʱ��
	private String gctfax; // ����
	private String gctarea; // ��������
	private String gctprovince; // ʡ��
	private String gcttype;// ����
	private String gctvalid; // �Ƿ���Ч
	private String gctsname;//���
	
	private Integer gctexd;//�������

	public Date getGctstart() {
		return gctstart;
	}

	public void setGctstart(Date gctstart) {
		this.gctstart = gctstart;
	}

	public String getGctfax() {
		return gctfax;
	}

	public void setGctfax(String gctfax) {
		this.gctfax = gctfax;
	}

	public String getGctarea() {
		return gctarea;
	}

	public void setGctarea(String gctarea) {
		this.gctarea = gctarea;
	}

	public String getGctprovince() {
		return gctprovince;
	}

	public void setGctprovince(String gctprovince) {
		this.gctprovince = gctprovince;
	}

	public String getGcttype() {
		return gcttype;
	}

	public void setGcttype(String gcttype) {
		this.gcttype = gcttype;
	}

	public String getGctid() {
		return gctid;
	}

	public void setGctid(String gctid) {
		this.gctid = gctid;
	}

	public String getGctnm() {
		return gctnm;
	}

	public void setGctnm(String gctnm) {
		this.gctnm = gctnm;
	}

	public String getGctaddr() {
		return gctaddr;
	}

	public void setGctaddr(String gctaddr) {
		this.gctaddr = gctaddr;
	}

	public String getGctptcd() {
		return gctptcd;
	}

	public void setGctptcd(String gctptcd) {
		this.gctptcd = gctptcd;
	}

	public String getGctcnm() {
		return gctcnm;
	}

	public void setGctcnm(String gctcnm) {
		this.gctcnm = gctcnm;
	}

	public String getGcttel() {
		return gcttel;
	}

	public void setGcttel(String gcttel) {
		this.gcttel = gcttel;
	}

	public String getGctnt() {
		return gctnt;
	}

	public void setGctnt(String gctnt) {
		this.gctnt = gctnt;
	}

	public String getGctoprcd() {
		return gctoprcd;
	}

	public void setGctoprcd(String gctoprcd) {
		this.gctoprcd = gctoprcd;
	}

	public Date getGctdate() {
		return gctdate;
	}

	public void setGctdate(Date gctdate) {
		this.gctdate = gctdate;
	}

	public String getGctemail() {
		return gctemail;
	}

	public void setGctemail(String gctemail) {
		this.gctemail = gctemail;
	}

	public String getGctvalid() {
		return gctvalid;
	}

	public void setGctvalid(String gctvalid) {
		this.gctvalid = gctvalid;
	}

	public String getGctsname() {
		return gctsname;
	}

	public void setGctsname(String gctsname) {
		this.gctsname = gctsname;
	}

	public Integer getGctexd() {
		return gctexd;
	}

	public void setGctexd(Integer gctexd) {
		this.gctexd = gctexd;
	}
	
	

}
