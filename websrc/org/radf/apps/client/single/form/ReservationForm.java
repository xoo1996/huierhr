package org.radf.apps.client.single.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class ReservationForm extends ActionForm {
	private String rsvid;  //ԤԼ��
	private String rsvnm;  //ԤԼ�ͻ�����
	private String rsvphone;  //ԤԼ�ֻ���
	private String ypnm;  //����ʦ����
	private Date rsvdate;  //ԤԼ����
	private String rsvsta;  //����״̬
	private String rsvgcltid; //ԤԼ����ͻ�����
	private String rsvgctnm;//ԤԼ����ͻ�����
	private String rsvgczhuanid;//ת�ﵥλ����
	private String rsvgctzhuannm;//ת�ﵥλ����
	private String rsvtodayid;//��ȻԤԼ��
	private Date rsvqddate;//ǩ������
	private String rsvnote;//ԤԼ��ע
	private Date rsvdjdate;//ԤԼ�Ǽ�����
	
	private Date start;//ԤԼ���ڴ�
	private Date end;//ԤԼ���ڵ�
	//���ݸ�singelclient�Ĳ���
	private String ictnm;
	private String icttel;
	private String ictgctid;
	
	public String getIctnm() {
		return ictnm;
	}
	public void setIctnm(String ictnm) {
		this.ictnm = ictnm;
	}
	public String getIcttel() {
		return icttel;
	}
	public void setIcttel(String icttel) {
		this.icttel = icttel;
	}
	public String getIctgctid() {
		return ictgctid;
	}
	public void setIctgctid(String ictgctid) {
		this.ictgctid = ictgctid;
	}
	
	public String getRsvgctnm() {
		return rsvgctnm;
	}
	public void setRsvgctnm(String rsvgctnm) {
		this.rsvgctnm = rsvgctnm;
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
	public String getRsvgczhuanid() {
		return rsvgczhuanid;
	}
	public void setRsvgczhuanid(String rsvgczhuanid) {
		this.rsvgczhuanid = rsvgczhuanid;
	}
	public String getRsvgctzhuannm() {
		return rsvgctzhuannm;
	}
	public void setRsvgctzhuannm(String rsvgctzhuannm) {
		this.rsvgctzhuannm = rsvgctzhuannm;
	}
	public String getRsvtodayid() {
		return rsvtodayid;
	}
	public void setRsvtodayid(String rsvtodayid) {
		this.rsvtodayid = rsvtodayid;
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
	public Date getRsvqddate() {
		return rsvqddate;
	}
	public void setRsvqddate(Date rsvqddate) {
		this.rsvqddate = rsvqddate;
	}
	public String getRsvnote() {
		return rsvnote;
	}
	public void setRsvnote(String rsvnote) {
		this.rsvnote = rsvnote;
	}
	public Date getRsvdjdate() {
		return rsvdjdate;
	}
	public void setRsvdjdate(Date rsvdjdate) {
		this.rsvdjdate = rsvdjdate;
	}
	
	
	
}