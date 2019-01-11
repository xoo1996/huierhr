package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//��������Ϣ��
public class Contract extends EntitySupport {
	private String useremployid; // Ա��id
	private String username;// Ա������
	private String conid;// ��ͬid
	private String conpartya;// �׷�
	private Date condatestart;// ��ͬ��ʼ����
	private Date condateend;// ��ͬ��������
	private Date condatesignstart;// ��ͬǩԼ���ڿ�ʼ
	private Date condatesignend;// ��ͬǩԼ���ڽ���
	private Date condatesign;// ��ͬǩԼ����
	private String conchargeperson;// �׷�������
	private String departmentid;// Ա������id
	private String departmentname;// Ա����������
	private String contype;// ��ͬ����

	public String getContype() {
		return contype;
	}

	public void setContype(String contype) {
		this.contype = contype;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public Date getCondatesignstart() {
		return condatesignstart;
	}

	public void setCondatesignstart(Date condatesignstart) {
		this.condatesignstart = condatesignstart;
	}

	public Date getCondatesignend() {
		return condatesignend;
	}

	public void setCondatesignend(Date condatesignend) {
		this.condatesignend = condatesignend;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremployid() {
		return useremployid;
	}

	public void setUseremployid(String useremployid) {
		this.useremployid = useremployid;
	}

	public String getConid() {
		return conid;
	}

	public void setConid(String conid) {
		this.conid = conid;
	}

	public String getConpartya() {
		return conpartya;
	}

	public void setConpartya(String conpartya) {
		this.conpartya = conpartya;
	}

	public Date getCondatestart() {
		return condatestart;
	}

	public void setCondatestart(Date condatestart) {
		this.condatestart = condatestart;
	}

	public Date getCondateend() {
		return condateend;
	}

	public void setCondateend(Date condateend) {
		this.condateend = condateend;
	}

	public Date getCondatesign() {
		return condatesign;
	}

	public void setCondatesign(Date condatesign) {
		this.condatesign = condatesign;
	}

	public String getConchargeperson() {
		return conchargeperson;
	}

	public void setConchargeperson(String conchargeperson) {
		this.conchargeperson = conchargeperson;
	}

}
