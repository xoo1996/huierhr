/**
 * Rec0305DTO.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.query.dto;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

/**
 * ����ͳ��
 */
public class Rec0305DTO extends EntitySupport {
	private String stat01;// ͳ�Ʒ���

	private Date date01;// �Ǽ�ʱ��

	private Date date02;// �Ǽ�ʱ��

	private String cce001;// ��������

	private String ssjqnm;// ��������

	private String total;// ����

	private String bsc006;// �������
	private String acb201;// ��Ƹ��ʽ
	private String acb231;// ��Ƹ���� 

	public String getAcb201() {
		return acb201;
	}

	public void setAcb201(String acb201) {
		this.acb201 = acb201;
	}



	public String getAcb231() {
		return acb231;
	}

	public void setAcb231(String acb231) {
		this.acb231 = acb231;
	}

	public String getBsc006() {
		return bsc006;
	}

	public void setBsc006(String bsc006) {
		this.bsc006 = bsc006;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCce001() {
		return cce001;
	}

	public void setCce001(String cce001) {
		this.cce001 = cce001;
	}

	public Date getDate01() {
		return date01;
	}

	public void setDate01(Date date01) {
		this.date01 = date01;
	}

	public Date getDate02() {
		return date02;
	}

	public void setDate02(Date date02) {
		this.date02 = date02;
	}

	public String getSsjqnm() {
		return ssjqnm;
	}

	public void setSsjqnm(String ssjqnm) {
		this.ssjqnm = ssjqnm;
	}

	public String getStat01() {
		return stat01;
	}

	public void setStat01(String stat01) {
		this.stat01 = stat01;
	}

}
