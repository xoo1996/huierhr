package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

public class Department extends EntitySupport{

	//部门id
	private String departmentid;
	//部门名称
	private String departmentname;
	
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
	
	
}
