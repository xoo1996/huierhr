package org.radf.apps.client.single.form;

import java.util.Date;
import org.apache.struts.action.ActionForm;
import org.radf.plat.util.entity.EntitySupport;

//配置项信息表
public class TjForm extends ActionForm {
	private String ictnm;  //用户姓名
	private String icttel;  //用户电话
	private String ictaddr;  //用户地址
	private String fdtpid;  //商品代码
	private String pdtnm;  //商品名称
	private int fdtqnt;  //数量
	private int pdtprc;  //商品单价
	private Date foldt;  //选配时间
	
	private Date start;
	private Date end;
	
	
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
	public String getIctnm() {
		return ictnm;
	}
	public void setIctnm(String ictnm) {
		this.ictnm = ictnm;
	}
	public String getIcttel() {
		return icttel;
	}
	public void setIcttel(String icttel) {
		this.icttel = icttel;
	}
	public String getIctaddr() {
		return ictaddr;
	}
	public void setIctaddr(String ictaddr) {
		this.ictaddr = ictaddr;
	}
	public String getFdtpid() {
		return fdtpid;
	}
	public void setFdtpid(String fdtpid) {
		this.fdtpid = fdtpid;
	}
	public String getPdtnm() {
		return pdtnm;
	}
	public void setPdtnm(String pdtnm) {
		this.pdtnm = pdtnm;
	}
	public int getFdtqnt() {
		return fdtqnt;
	}
	public void setFdtqnt(int fdtqnt) {
		this.fdtqnt = fdtqnt;
	}
	public int getPdtprc() {
		return pdtprc;
	}
	public void setPdtprc(int pdtprc) {
		this.pdtprc = pdtprc;
	}
	public Date getFoldt() {
		return foldt;
	}
	public void setFoldt(Date foldt) {
		this.foldt = foldt;
	}
}