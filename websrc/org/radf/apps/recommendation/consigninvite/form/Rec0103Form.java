/**
 * Rec0103Form.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.consigninvite.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * ��Ա�Ƽ�form
 */
public class Rec0103Form extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String aac001; // ���˱��

	private String aab001; // ��λ���

	private String aac002; // ������ݺ���

	private String acc220; // �Ƽ����

	private String aac003; // ����

	private String aac004; // �Ա�

	private String aac009; // ��������

	private String aac011; // �Ļ��̶�

	private String bac298; // ��Ա���

	private String aca111; // ԭרҵ����

	private String aae043; // �Ǽ����ڣ���ʱδ�ã�

	private String a043ae; // �Ǽ����ڣ���ʱδ�ã�

	private String aae036; // �Ǽ�����

	private String a036ae; // �Ǽ�����

	private String acc201; // �Ǽ����

	private String aca112;

	private String aac014;

	private String acc200; // ��ְ���

	private String acc210; // ��ҵ���ֱ��

	private String acb200; // ��Ƹ���

	private String acb210; // ��λ���

	private String acc221; // �����ű��

	private String aab004; // ��λ����

	private String aae006; // ��ַ

	private String acb216; // ����˵��

	private String aae004; // ��ϵ��

	private String aae005; // ��ϵ�绰

	private String aac048;// �ù���ʽ

	private String acb21h;// ���ʴ�����

	private String a21hcb;// ��

	private String acb21d;// ��������

	private String acb21a;// �ɹ�����

	private String acb218;// ���Ƽ�����

	private String acb211;// ���Ƽ�����

	private String aab007;// Ӫҵִ�պ�

	private String aab003;// ��֯��������

	private String aab002;// ��ᱣ�յǼ�֤����

	private String acb206;// ��������

	private String acc034;// ���ʴ���

	private String a034cc;// ���ʴ���

	private String aac017;// ����״��

	private String nnn001;// �����

	private String nnn002;// ���䵽

	public String getA034cc() {
		return a034cc;
	}

	public void setA034cc(String a034cc) {
		this.a034cc = a034cc;
	}

	public String getAac017() {
		return aac017;
	}

	public void setAac017(String aac017) {
		this.aac017 = aac017;
	}

	public String getAcc034() {
		return acc034;
	}

	public void setAcc034(String acc034) {
		this.acc034 = acc034;
	}

	public String getNnn001() {
		return nnn001;
	}

	public void setNnn001(String nnn001) {
		this.nnn001 = nnn001;
	}

	public String getNnn002() {
		return nnn002;
	}

	public void setNnn002(String nnn002) {
		this.nnn002 = nnn002;
	}

	public String getAab004() {
		return aab004;
	}

	public void setAab004(String aab004) {
		this.aab004 = aab004;
	}

	public String getAae004() {
		return aae004;
	}

	public void setAae004(String aae004) {
		this.aae004 = aae004;
	}

	public String getAae005() {
		return aae005;
	}

	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}

	public String getAae006() {
		return aae006;
	}

	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}

	public String getAcb216() {
		return acb216;
	}

	public void setAcb216(String acb216) {
		this.acb216 = acb216;
	}

	public String getAcc221() {
		return acc221;
	}

	public void setAcc221(String acc221) {
		this.acc221 = acc221;
	}

	public void setAca112(String aca112) {
		this.aca112 = aca112;
	}

	public String getAca112() {
		return aca112;
	}

	// ���˱��
	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}

	public String getAac001() {
		return aac001;
	}

	// ������ݺ���
	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public String getAac002() {
		return aac002;
	}

	// ����
	public void setAac003(String aac003) {
		this.aac003 = aac003;
	}

	public String getAac003() {
		return aac003;
	}

	// �Ա�
	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}

	public String getAac004() {
		return aac004;
	}

	// ��������
	public void setAac009(String aac009) {
		this.aac009 = aac009;
	}

	public String getAac009() {
		return aac009;
	}

	// �Ļ��̶�
	public void setAac011(String aac011) {
		this.aac011 = aac011;
	}

	public String getAac011() {
		return aac011;
	}

	// ԭרҵ����
	public void setAca111(String aca111) {
		this.aca111 = aca111;
	}

	public String getAca111() {
		return aca111;
	}

	// �Ǽ����ڣ���ʱδ�ã�
	public void setAae043(String aae043) {
		this.aae043 = aae043;
	}

	public String getAae043() {
		return aae043;
	}

	// �Ǽ����
	public void setAcc201(String acc201) {
		this.acc201 = acc201;
	}

	public String getAcc201() {
		return acc201;
	}

	public String getAac014() {
		return aac014;
	}

	public void setAac014(String aac014) {
		this.aac014 = aac014;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// this.aac002 = null;
		// this.aac003 = null;
		// this.aac004 = null;
		// this.aac009 = null;
		// this.aac011 = null;
		// this.aac027 = null;
		// this.aca111 = null;
		// this.aae043 = null;
		// this.acc201 = null;
		// this.aac001 = null;
	}

	public String getBac298() {
		return bac298;
	}

	public void setBac298(String bac298) {
		this.bac298 = bac298;
	}

	/**
	 * @return Returns the a043ae.
	 */
	public String getA043ae() {
		return a043ae;
	}

	/**
	 * @param a043ae
	 *            The a043ae to set.
	 */
	public void setA043ae(String a043ae) {
		this.a043ae = a043ae;
	}

	/**
	 * @return Returns the a036ae.
	 */
	public String getA036ae() {
		return a036ae;
	}

	/**
	 * @param a036ae
	 *            The a036ae to set.
	 */
	public void setA036ae(String a036ae) {
		this.a036ae = a036ae;
	}

	/**
	 * @return Returns the aae036.
	 */
	public String getAae036() {
		return aae036;
	}

	/**
	 * @param aae036
	 *            The aae036 to set.
	 */
	public void setAae036(String aae036) {
		this.aae036 = aae036;
	}

	public String getAcb200() {
		return acb200;
	}

	public void setAcb200(String acb200) {
		this.acb200 = acb200;
	}

	public String getAcb210() {
		return acb210;
	}

	public void setAcb210(String acb210) {
		this.acb210 = acb210;
	}

	public String getAcc200() {
		return acc200;
	}

	public void setAcc200(String acc200) {
		this.acc200 = acc200;
	}

	public String getAcc210() {
		return acc210;
	}

	public void setAcc210(String acc210) {
		this.acc210 = acc210;
	}

	public String getA21hcb() {
		return a21hcb;
	}

	public void setA21hcb(String a21hcb) {
		this.a21hcb = a21hcb;
	}

	public String getAac048() {
		return aac048;
	}

	public void setAac048(String aac048) {
		this.aac048 = aac048;
	}

	public String getAcb211() {
		return acb211;
	}

	public void setAcb211(String acb211) {
		this.acb211 = acb211;
	}

	public String getAcb218() {
		return acb218;
	}

	public void setAcb218(String acb218) {
		this.acb218 = acb218;
	}

	public String getAcb21a() {
		return acb21a;
	}

	public void setAcb21a(String acb21a) {
		this.acb21a = acb21a;
	}

	public String getAcb21d() {
		return acb21d;
	}

	public void setAcb21d(String acb21d) {
		this.acb21d = acb21d;
	}

	public String getAcb21h() {
		return acb21h;
	}

	public void setAcb21h(String acb21h) {
		this.acb21h = acb21h;
	}

	public String getAab002() {
		return aab002;
	}

	public void setAab002(String aab002) {
		this.aab002 = aab002;
	}

	public String getAab003() {
		return aab003;
	}

	public void setAab003(String aab003) {
		this.aab003 = aab003;
	}

	public String getAab007() {
		return aab007;
	}

	public void setAab007(String aab007) {
		this.aab007 = aab007;
	}

	public String getAcb206() {
		return acb206;
	}

	public void setAcb206(String acb206) {
		this.acb206 = acb206;
	}

	public String getAab001() {
		return aab001;
	}

	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAcc220() {
		return acc220;
	}

	public void setAcc220(String acc220) {
		this.acc220 = acc220;
	}

}