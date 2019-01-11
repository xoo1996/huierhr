package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

//听力图表
public class Audiogram extends EntitySupport {
	private String adgctid;
	private String adglre; // L:左耳，R:右耳
	private String adgtp; // 类型
	private Integer adgfq; // 频率
	private String adgadt; // 分贝
	private String adgshie;//是否遮蔽

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
