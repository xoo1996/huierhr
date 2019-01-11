package org.radf.apps.commons.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class DisExamine extends EntitySupport{
	private String tdefolno;
	private String tdepdtid;
	private String tdegctid;
	private String tdeictid;
	private Double tdedis;
	private String tdeisexa;  
	private Date tdedt;
	private Double tdedmindis;
	private String gctnm;  //所属团体
	private String ictnm;  //所属团体
	private Date start;
	private Date end; 
	
	public String getTdefolno() {
		return tdefolno;
	}
	public void setTdefolno(String tdefolno) {
		this.tdefolno = tdefolno;
	}
	public String getTdepdtid() {
		return tdepdtid;
	}
	public void setTdepdtid(String tdepdtid) {
		this.tdepdtid = tdepdtid;
	}
	public String getTdegctid() {
		return tdegctid;
	}
	public void setTdegctid(String tdegctid) {
		this.tdegctid = tdegctid;
	}
	public String getTdeictid() {
		return tdeictid;
	}
	public void setTdeictid(String tdeictid) {
		this.tdeictid = tdeictid;
	}
	public Double getTdedis() {
		return tdedis;
	}
	public void setTdedis(Double tdedis) {
		this.tdedis = tdedis;
	}
	public String getTdeisexa() {
		return tdeisexa;
	}
	public void setTdeisexa(String tdeisexa) {
		this.tdeisexa = tdeisexa;
	}
	public Date getTdedt() {
		return tdedt;
	}
	public void setTdedt(Date tdedt) {
		this.tdedt = tdedt;
	}
	public Double getTdedmindis() {
		return tdedmindis;
	}
	public void setTdedmindis(Double tdedmindis) {
		this.tdedmindis = tdedmindis;
	}
	public String getGctnm() {
		return gctnm;
	}
	public void setGctnm(String gctnm) {
		this.gctnm = gctnm;
	}
	public String getIctnm() {
		return ictnm;
	}
	public void setIctnm(String ictnm) {
		this.ictnm = ictnm;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	
	

}
