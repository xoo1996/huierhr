package org.radf.apps.userinfo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class UserTrainForm extends ActionForm{
	private String useremployid;
	private Date trainstartdate;
	private String trainstartdateStr;
	private Date trainenddate;
	private String trainenddateStr;
	private String trainplace;
	private String traincontent;
	private String traincertificate;
	
	
	public String getTrainstartdateStr() {
		return trainstartdateStr;
	}
	public void setTrainstartdateStr(String trainstartdateStr) {
		this.trainstartdateStr = trainstartdateStr;
	}
	public String getTrainenddateStr() {
		return trainenddateStr;
	}
	public void setTrainenddateStr(String trainenddateStr) {
		this.trainenddateStr = trainenddateStr;
	}
	public String getUseremployid() {
		return useremployid;
	}
	public void setUseremployid(String useremployid) {
		this.useremployid = useremployid;
	}
	public Date getTrainstartdate() {
		return trainstartdate;
	}
	public void setTrainstartdate(Date trainstartdate) {
		this.trainstartdate = trainstartdate;
	}
	public Date getTrainenddate() {
		return trainenddate;
	}
	public void setTrainenddate(Date trainenddate) {
		this.trainenddate = trainenddate;
	}
	public String getTrainplace() {
		return trainplace;
	}
	public void setTrainplace(String trainplace) {
		this.trainplace = trainplace;
	}
	public String getTraincontent() {
		return traincontent;
	}
	public void setTraincontent(String traincontent) {
		this.traincontent = traincontent;
	}
	public String getTraincertificate() {
		return traincertificate;
	}
	public void setTraincertificate(String traincertificate) {
		this.traincertificate = traincertificate;
	}
	
}
