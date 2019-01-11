 /**
  * Rec0703Form.java 2008/03/27
  * 
  * Copyright (c) 2008 - 2017
  * All rights reserved.<br>
  * @author lwd
  * @version 1.0
  */
package org.radf.apps.recommendation.employassembly.form;


import org.apache.struts.action.ActionForm;
/**
 * 大会招聘期数信息管理
 */
public class Rec0703Form extends ActionForm{

	private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private String aae036;//经办时间

    private String aae101;//修改人员

    private String aae136;//修改时间
    
    private String acb230;//期数内码
    
    private String cce001;//所属机构
    
    private String acb231;//期数
    
    private String aae100;//有效标记
    
    private String acb232;//招聘日期
    
    private String acb233;//招聘地点
    
    private String ssjqnm;

	public String getSsjqnm() {
		return ssjqnm;
	}

	public void setSsjqnm(String ssjqnm) {
		this.ssjqnm = ssjqnm;
	}

	public String getAae011() {
		return aae011;
	}

	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public String getAae017() {
		return aae017;
	}

	public void setAae017(String aae017) {
		this.aae017 = aae017;
	}

	public String getAae019() {
		return aae019;
	}

	public void setAae019(String aae019) {
		this.aae019 = aae019;
	}

	public String getAae036() {
		return aae036;
	}

	public void setAae036(String aae036) {
		this.aae036 = aae036;
	}

	public String getAae101() {
		return aae101;
	}

	public void setAae101(String aae101) {
		this.aae101 = aae101;
	}

	public String getAae136() {
		return aae136;
	}

	public void setAae136(String aae136) {
		this.aae136 = aae136;
	}

	public String getAcb230() {
		return acb230;
	}

	public void setAcb230(String acb230) {
		this.acb230 = acb230;
	}

	public String getAcb231() {
		return acb231;
	}

	public void setAcb231(String acb231) {
		this.acb231 = acb231;
	}

	public String getAcb232() {
		return acb232;
	}

	public void setAcb232(String acb232) {
		this.acb232 = acb232;
	}

	public String getAcb233() {
		return acb233;
	}

	public void setAcb233(String acb233) {
		this.acb233 = acb233;
	}


	public String getCce001() {
		return cce001;
	}

	public void setCce001(String cce001) {
		this.cce001 = cce001;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
}
