/**
 * QueryEmployerForm.java 2008/03/24
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.basicinfo.form;

import org.apache.struts.action.ActionForm;

/**
 * ��λ��Ϣ
 */
public class QueryEmployerForm extends ActionForm {
	private String aab003; // ��֯��������

	private String aab004; // ��λ����

	private String aab043; // ��λƴ����

	private String aab002; // ��ᱣ�յǼ�֤����

	private String aae119; //��λ״̬

	private String menuId;
    private String aab007;

	/**
	 * @return Returns the menuId.
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            The menuId to set.
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return Returns the aab002.
	 */
	public String getAab002() {
		return aab002;
	}

	/**
	 * @param aab002
	 *            The aab002 to set.
	 */
	public void setAab002(String aab002) {
		this.aab002 = aab002;
	}

	/**
	 * @return Returns the aab003.
	 */
	public String getAab003() {
		return aab003;
	}

	/**
	 * @param aab003
	 *            The aab003 to set.
	 */
	public void setAab003(String aab003) {
		this.aab003 = aab003;
	}

	/**
	 * @return Returns the aab004.
	 */
	public String getAab004() {
		return aab004;
	}

	/**
	 * @param aab004
	 *            The aab004 to set.
	 */
	public void setAab004(String aab004) {
		this.aab004 = aab004;
	}

	/**
	 * @return Returns the aab043.
	 */
	public String getAab043() {
		return aab043;
	}

	/**
	 * @param aab043
	 *            The aab043 to set.
	 */
	public void setAab043(String aab043) {
		this.aab043 = aab043;
	}

	/**
	 * @return Returns the aae119.
	 */
	public String getAae119() {
		return aae119;
	}

	/**
	 * @param aae119
	 *            The aae119 to set.
	 */
	public void setAae119(String aae119) {
		this.aae119 = aae119;
	}

    public String getAab007()
    {
        return aab007;
    }

    public void setAab007(String aab007)
    {
        this.aab007 = aab007;
    }
}