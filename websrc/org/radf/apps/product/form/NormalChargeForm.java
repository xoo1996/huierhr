package org.radf.apps.product.form;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts.action.ActionForm;

public class NormalChargeForm extends ActionForm {
	
	private BigDecimal chgid;//�շ���ˮ��
	private String chgstoid; //�����
	private String chggcltid;//����ͻ�����
	private String chgcltid;//���˿ͻ�����
	private String chgpdtid;//��Ʒ����
	private Integer chgqnt;//�۳�����
	private Double chgdis;//����
	private Double chgmon;//ʵ���շ�
	private Date chgdt;//�շ�����
	
	
	
	public BigDecimal getChgid() {
		return chgid;
	}
	public void setChgid(BigDecimal chgid) {
		this.chgid = chgid;
	}
	public String getChgstoid() {
		return chgstoid;
	}
	public void setChgstoid(String chgstoid) {
		this.chgstoid = chgstoid;
	}
	public String getChggcltid() {
		return chggcltid;
	}
	public void setChggcltid(String chggcltid) {
		this.chggcltid = chggcltid;
	}
	public String getChgcltid() {
		return chgcltid;
	}
	public void setChgcltid(String chgcltid) {
		this.chgcltid = chgcltid;
	}
	public String getChgpdtid() {
		return chgpdtid;
	}
	public void setChgpdtid(String chgpdtid) {
		this.chgpdtid = chgpdtid;
	}
	public Integer getChgqnt() {
		return chgqnt;
	}
	public void setChgqnt(Integer chgqnt) {
		this.chgqnt = chgqnt;
	}
	public Double getChgdis() {
		return chgdis;
	}
	public void setChgdis(Double chgdis) {
		this.chgdis = chgdis;
	}
	public Double getChgmon() {
		return chgmon;
	}
	public void setChgmon(Double chgmon) {
		this.chgmon = chgmon;
	}
	public Date getChgdt() {
		return chgdt;
	}
	public void setChgdt(Date chgdt) {
		this.chgdt = chgdt;
	}
	
	

	
}
