package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

public class UserSup extends EntitySupport{
	private String superiorid;
	public String getSuperiorid() {
		return superiorid;
	}
	public void setSuperiorid(String superiorid) {
		this.superiorid = superiorid;
	}
	public String getJuniorid() {
		return juniorid;
	}
	public void setJuniorid(String juniorid) {
		this.juniorid = juniorid;
	}
	private String juniorid;
}
