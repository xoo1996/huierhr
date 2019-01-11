package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//配置项信息表
public class Contract extends EntitySupport {
	private String useremployid; // 员工id
	private String username;// 员工姓名
	private String conid;// 合同id
	private String conpartya;// 甲方
	private Date condatestart;// 合同开始日期
	private Date condateend;// 合同结束日期
	private Date condatesignstart;// 合同签约日期开始
	private Date condatesignend;// 合同签约日期结束
	private Date condatesign;// 合同签约日期
	private String conchargeperson;// 甲方负责人
	private String departmentid;// 员工部门id
	private String departmentname;// 员工部门名称
	private String contype;// 合同类型

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
