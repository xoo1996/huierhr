package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

public class Department extends EntitySupport{

	//����id
	private String departmentid;
	//��������
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
