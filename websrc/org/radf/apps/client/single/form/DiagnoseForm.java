package org.radf.apps.client.single.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class DiagnoseForm extends ActionForm {
	private String dgnctid; // ���˴���
	private Double dgnlufa; // ���������
	private Double dgnrufa; // ���������
	private String dgnlid; // ���������Ʒ���
	private Double dgnldprc; // ������ۼ۸�
	private Double dgnldct; // ����ۿ�
	private String dgnrid; // �Ҷ�������Ʒ���
	private Double dgnrdprc; // �Ҷ����ۼ۸�
	private Double dgnrdct;// �Ҷ��ۿ�
	private String dgnmmk; // ��ӡ������
	private Date dgnpfdt; // Ҫ��ȡ������
	private String dgnsdmd; // ����Ҫ��
	private Date dgnafdt;// ʵ��ȡ������
	private String dgnctimp; // ���˷�ӳ
	private String dgndoc; // ҽ������
	private Date dgndt; // ��������
	private String company;

	public String getDgnctid() {
		return dgnctid;
	}

	public void setDgnctid(String dgnctid) {
		this.dgnctid = dgnctid;
	}

	public Double getDgnlufa() {
		return dgnlufa;
	}

	public void setDgnlufa(Double dgnlufa) {
		this.dgnlufa = dgnlufa;
	}

	public Double getDgnrufa() {
		return dgnrufa;
	}

	public void setDgnrufa(Double dgnrufa) {
		this.dgnrufa = dgnrufa;
	}

	public String getDgnlid() {
		return dgnlid;
	}

	public void setDgnlid(String dgnlid) {
		this.dgnlid = dgnlid;
	}

	public Double getDgnldprc() {
		return dgnldprc;
	}

	public void setDgnldprc(Double dgnldprc) {
		this.dgnldprc = dgnldprc;
	}

	public Double getDgnldct() {
		return dgnldct;
	}

	public void setDgnldct(Double dgnldct) {
		this.dgnldct = dgnldct;
	}

	public String getDgnrid() {
		return dgnrid;
	}

	public void setDgnrid(String dgnrid) {
		this.dgnrid = dgnrid;
	}

	public Double getDgnrdprc() {
		return dgnrdprc;
	}

	public void setDgnrdprc(Double dgnrdprc) {
		this.dgnrdprc = dgnrdprc;
	}

	public Double getDgnrdct() {
		return dgnrdct;
	}

	public void setDgnrdct(Double dgnrdct) {
		this.dgnrdct = dgnrdct;
	}

	public String getDgnmmk() {
		return dgnmmk;
	}

	public void setDgnmmk(String dgnmmk) {
		this.dgnmmk = dgnmmk;
	}

	public Date getDgnpfdt() {
		return dgnpfdt;
	}

	public void setDgnpfdt(Date dgnpfdt) {
		this.dgnpfdt = dgnpfdt;
	}

	public String getDgnsdmd() {
		return dgnsdmd;
	}

	public void setDgnsdmd(String dgnsdmd) {
		this.dgnsdmd = dgnsdmd;
	}

	public Date getDgnafdt() {
		return dgnafdt;
	}

	public void setDgnafdt(Date dgnafdt) {
		this.dgnafdt = dgnafdt;
	}

	public String getDgnctimp() {
		return dgnctimp;
	}

	public void setDgnctimp(String dgnctimp) {
		this.dgnctimp = dgnctimp;
	}

	public String getDgndoc() {
		return dgndoc;
	}

	public void setDgndoc(String dgndoc) {
		this.dgndoc = dgndoc;
	}

	public Date getDgndt() {
		return dgndt;
	}

	public void setDgndt(Date dgndt) {
		this.dgndt = dgndt;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
