package org.radf.apps.order.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class OrderDetailForm extends ActionForm {
	private String fdtfno; // �ʵ���
	private String fdtcltid; // �ͻ�����
	private String fdtpid; // ��Ʒ(����)���ƴ���
	private Double fdtprc; // �ۼ�
	private Integer fdtqnt; // ����
	private Integer fdtrqnt; // ʣ������
	private Integer fdtsqnt; // ��������
	private Double fdtpqnt;
	private Date fdtedt;
	private Double fdtdprc; // ԭ��
	private String fdtnt;// ��ע
	private String fdtcltnm; // �û�����

	private String foldesnm;// �����ص�����
	private String foldes;// �����ص�
	private String folway;// ������ʽ
	private String folsno;// �����
	private String dgndoc;//����ʦ
	private String folstoid; //�����
	
	private String folsta;//����״̬
	
	public String getFdtfno() {
		return fdtfno;
	}

	public void setFdtfno(String fdtfno) {
		this.fdtfno = fdtfno;
	}

	public String getFdtcltid() {
		return fdtcltid;
	}

	public void setFdtcltid(String fdtcltid) {
		this.fdtcltid = fdtcltid;
	}

	public String getFdtpid() {
		return fdtpid;
	}

	public void setFdtpid(String fdtpid) {
		this.fdtpid = fdtpid;
	}

	public double getFdtprc() {
		return fdtprc;
	}

	public void setFdtprc(Double fdtprc) {
		this.fdtprc = fdtprc;
	}

	public int getFdtqnt() {
		return fdtqnt;
	}

	public void setFdtqnt(Integer fdtqnt) {
		this.fdtqnt = fdtqnt;
	}

	public int getFdtsqnt() {
		return fdtsqnt;
	}

	public void setFdtsqnt(Integer fdtsqnt) {
		this.fdtsqnt = fdtsqnt;
	}

	public double getFdtpqnt() {
		return fdtpqnt;
	}

	public void setFdtpqnt(Double fdtpqnt) {
		this.fdtpqnt = fdtpqnt;
	}

	public Date getFdtedt() {
		return fdtedt;
	}

	public void setFdtedt(Date fdtedt) {
		this.fdtedt = fdtedt;
	}

	public double getFdtdprc() {
		return fdtdprc;
	}

	public void setFdtdprc(Double fdtdprc) {
		this.fdtdprc = fdtdprc;
	}

	public String getFdtnt() {
		return fdtnt;
	}

	public void setFdtnt(String fdtnt) {
		this.fdtnt = fdtnt;
	}

	public String getFdtcltnm() {
		return fdtcltnm;
	}

	public void setFdtcltnm(String fdtcltnm) {
		this.fdtcltnm = fdtcltnm;
	}

	public String getFoldesnm() {
		return foldesnm;
	}

	public void setFoldesnm(String foldesnm) {
		this.foldesnm = foldesnm;
	}

	public String getFoldes() {
		return foldes;
	}

	public void setFoldes(String foldes) {
		this.foldes = foldes;
	}

	public String getFolway() {
		return folway;
	}

	public void setFolway(String folway) {
		this.folway = folway;
	}

	public String getFolsno() {
		return folsno;
	}

	public void setFolsno(String folsno) {
		this.folsno = folsno;
	}

	public Integer getFdtrqnt() {
		return fdtrqnt;
	}

	public void setFdtrqnt(Integer fdtrqnt) {
		this.fdtrqnt = fdtrqnt;
	}

	public String getFolstoid() {
		return folstoid;
	}

	public void setFolstoid(String folstoid) {
		this.folstoid = folstoid;
	}
	
	public String getFolsta() {
		return folsta;
	}
	
	public void setFolsta(String folsta) {
		this.folsta = folsta;
	}

	public String getDgndoc() {
		return dgndoc;
	}

	public void setDgndoc(String dgndoc) {
		this.dgndoc = dgndoc;
	}
	
}
