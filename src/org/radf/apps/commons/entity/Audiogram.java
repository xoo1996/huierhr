package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

//����ͼ��
public class Audiogram extends EntitySupport {
	private String adgctid;
	private String adglre; // L:�����R:�Ҷ�
	private String adgtp; // ����
	private Integer adgfq; // Ƶ��
	private String adgadt; // �ֱ�
	private String adgshie;//�Ƿ��ڱ�

	public String getAdgctid() {
		return adgctid;
	}

	public void setAdgctid(String adgctid) {
		this.adgctid = adgctid;
	}

	public String getAdglre() {
		return adglre;
	}

	public void setAdglre(String adglre) {
		this.adglre = adglre;
	}

	public String getAdgtp() {
		return adgtp;
	}

	public void setAdgtp(String adgtp) {
		this.adgtp = adgtp;
	}

	public Integer getAdgfq() {
		return adgfq;
	}

	public void setAdgfq(Integer adgfq) {
		this.adgfq = adgfq;
	}

	public String getAdgadt() {
		return adgadt;
	}

	public void setAdgadt(String adgadt) {
		this.adgadt = adgadt;
	}

	public String getAdgshie() {
		return adgshie;
	}

	public void setAdgshie(String adgshie) {
		this.adgshie = adgshie;
	}
	
	
}
