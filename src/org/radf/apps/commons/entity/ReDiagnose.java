package org.radf.apps.commons.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

//�����
public class ReDiagnose extends EntitySupport {
	private String fctctid; // ���˴���
	private Date fctcdt; // ��������
	private String fctreason; // ����ԭ��
	private String fctdgn; // ������
	private String fcttmt; // �������
	private String fctdoc; // ҽ�����
	private String fctnt; // ��ע

	public String getFctctid() {
		return fctctid;
	}

	public void setFctctid(String fctctid) {
		this.fctctid = fctctid;
	}

	public Date getFctcdt() {
		return fctcdt;
	}

	public void setFctcdt(Date fctcdt) {
		this.fctcdt = fctcdt;
	}

	public String getFctreason() {
		return fctreason;
	}

	public void setFctreason(String fctreason) {
		this.fctreason = fctreason;
	}

	public String getFctdgn() {
		return fctdgn;
	}

	public void setFctdgn(String fctdgn) {
		this.fctdgn = fctdgn;
	}

	public String getFcttmt() {
		return fcttmt;
	}

	public void setFcttmt(String fcttmt) {
		this.fcttmt = fcttmt;
	}

	public String getFctdoc() {
		return fctdoc;
	}

	public void setFctdoc(String fctdoc) {
		this.fctdoc = fctdoc;
	}

	public String getFctnt() {
		return fctnt;
	}

	public void setFctnt(String fctnt) {
		this.fctnt = fctnt;
	}
}
