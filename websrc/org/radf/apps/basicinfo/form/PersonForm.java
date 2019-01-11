/**
 * PersonForm.java 2008/03/24
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.basicinfo.form;

import org.apache.struts.action.ActionForm;
/**
 * 人员信息
 */
public class PersonForm extends ActionForm {
	
    private String cce001;//所属街道代码
    private String ssjqnm;//街道名称
    private String menuId;//菜单ID
    private String aae100;//是否有效
	 private String url;

	private String url2;

    /**
     * @return Returns the ssjqnm.
     */
    public String getSsjqnm() {
        return ssjqnm;
    }
    /**
     * @param ssjqnm The ssjqnm to set.
     */
    public void setSsjqnm(String ssjqnm) {
        this.ssjqnm = ssjqnm;
    }
	/**
	 * @return Returns the url2.
	 */
	public String getUrl2() {
		return url2;
	}

	/**
	 * @param url2
	 *            The url2 to set.
	 */
	public void setUrl2(String url2) {
		this.url2 = url2;
	}

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

	//private String id; //ID

	private String aab301;//	所在地行政区划代码

	private String aab001;//	单位编号

	private String aac001;//	个人编号

	private String aac002;//	公民身份号码

	private String aac003;//	姓名

	private String aac004;//	性别

	private String aac005;//	民族

	private String aac006;//	出生日期

	private String aac007;//	参加工作日期

	private String aac008;//	社会保险状态

	private String aac009;//	户口性质

	private String aac010;//	户口所在地保障中心

	private String aac011;//	文化程度

	private String aac012;//	个人身份

	private String aac013;//	用工形式

	private String aac014;//	专业技术职务

	private String aac015;//	专业技术等级

	private String aac016;//	就业状态

	private String aac017;//	婚姻状况

	private String aac019;//	特殊工种标识

	private String aac020;//	行政职务

	private String aac021;//	就(失)业证号码

	private String aac022;//	个人拼音码

	private String aac023;//	英文名称

	private String aac024;//	政治面貌

	private String aac025;//	出生地

	private String aac026;//	家庭住址

	private String bac298;//	基本人员类别

	private String aac028;//	人员小类

	private String aac032;//	血型

	private String aac033;//	健康状况

	private String aac034;//	身高(CM)

	private String aac035;//	体重(KG)

	private String aac036;//	视力
	

	private String aae004;//	联系人

	private String aae005;//	联系电话

	private String aac030;//	手机
	
	private String aae006;//	地址

	private String aae007;//	邮政编码

	private String aae011;//	经办人

	private String aae013;//	备注

	private String aae015;//	个人电子信箱

	private String aae017;//	经办机构

	private String aae036;//	经办日期

	private String aae043;//	登记日期

	private String acc02i;//	国家职业资格证书号码

	private String acc101;//	再就业优惠证编号

	private String aic001;//	基本养老保险视同缴费月数

	private String ajc001;//	失业保险视同缴费月数

	private String ace119;//  注销日期

	private String ace120;//  注销原因
    
    private String bac297;//是否验证
	
	private String acc025;//劳动手册号

    public String getAcc025() {
		return acc025;
	}
	public void setAcc025(String acc025) {
		this.acc025 = acc025;
	}
	public String getBac297()
    {
        return bac297;
    }
    public void setBac297(String bac297)
    {
        this.bac297 = bac297;
    }
    public String getAab001()
    {
        return aab001;
    }
    public void setAab001(String aab001)
    {
        this.aab001 = aab001;
    }
    public String getAab301()
    {
        return aab301;
    }
    public void setAab301(String aab301)
    {
        this.aab301 = aab301;
    }
    public String getAac001()
    {
        return aac001;
    }
    public void setAac001(String aac001)
    {
        this.aac001 = aac001;
    }
    public String getAac002()
    {
        return aac002;
    }
    public void setAac002(String aac002)
    {
        this.aac002 = aac002;
    }
    public String getAac003()
    {
        return aac003;
    }
    public void setAac003(String aac003)
    {
        this.aac003 = aac003;
    }
    public String getAac004()
    {
        return aac004;
    }
    public void setAac004(String aac004)
    {
        this.aac004 = aac004;
    }
    public String getAac005()
    {
        return aac005;
    }
    public void setAac005(String aac005)
    {
        this.aac005 = aac005;
    }
    public String getAac006()
    {
        return aac006;
    }
    public void setAac006(String aac006)
    {
        this.aac006 = aac006;
    }
    public String getAac007()
    {
        return aac007;
    }
    public void setAac007(String aac007)
    {
        this.aac007 = aac007;
    }
    public String getAac008()
    {
        return aac008;
    }
    public void setAac008(String aac008)
    {
        this.aac008 = aac008;
    }
    public String getAac009()
    {
        return aac009;
    }
    public void setAac009(String aac009)
    {
        this.aac009 = aac009;
    }
    public String getAac010()
    {
        return aac010;
    }
    public void setAac010(String aac010)
    {
        this.aac010 = aac010;
    }
    public String getAac011()
    {
        return aac011;
    }
    public void setAac011(String aac011)
    {
        this.aac011 = aac011;
    }
    public String getAac012()
    {
        return aac012;
    }
    public void setAac012(String aac012)
    {
        this.aac012 = aac012;
    }
    public String getAac013()
    {
        return aac013;
    }
    public void setAac013(String aac013)
    {
        this.aac013 = aac013;
    }
    public String getAac014()
    {
        return aac014;
    }
    public void setAac014(String aac014)
    {
        this.aac014 = aac014;
    }
    public String getAac015()
    {
        return aac015;
    }
    public void setAac015(String aac015)
    {
        this.aac015 = aac015;
    }
    public String getAac016()
    {
        return aac016;
    }
    public void setAac016(String aac016)
    {
        this.aac016 = aac016;
    }
    public String getAac017()
    {
        return aac017;
    }
    public void setAac017(String aac017)
    {
        this.aac017 = aac017;
    }
    public String getAac019()
    {
        return aac019;
    }
    public void setAac019(String aac019)
    {
        this.aac019 = aac019;
    }
    public String getAac020()
    {
        return aac020;
    }
    public void setAac020(String aac020)
    {
        this.aac020 = aac020;
    }
    public String getAac021()
    {
        return aac021;
    }
    public void setAac021(String aac021)
    {
        this.aac021 = aac021;
    }
    public String getAac022()
    {
        return aac022;
    }
    public void setAac022(String aac022)
    {
        this.aac022 = aac022;
    }
    public String getAac023()
    {
        return aac023;
    }
    public void setAac023(String aac023)
    {
        this.aac023 = aac023;
    }
    public String getAac024()
    {
        return aac024;
    }
    public void setAac024(String aac024)
    {
        this.aac024 = aac024;
    }
    public String getAac025()
    {
        return aac025;
    }
    public void setAac025(String aac025)
    {
        this.aac025 = aac025;
    }
    public String getAac026()
    {
        return aac026;
    }
    public void setAac026(String aac026)
    {
        this.aac026 = aac026;
    }

    public String getBac298()
    {
        return bac298;
    }
    public void setBac298(String bac298)
    {
        this.bac298 = bac298;
    }
    public String getAac028()
    {
        return aac028;
    }
    public void setAac028(String aac028)
    {
        this.aac028 = aac028;
    }
    public String getAac030()
    {
        return aac030;
    }
    public void setAac030(String aac030)
    {
        this.aac030 = aac030;
    }
    public String getAac032()
    {
        return aac032;
    }
    public void setAac032(String aac032)
    {
        this.aac032 = aac032;
    }
    public String getAac033()
    {
        return aac033;
    }
    public void setAac033(String aac033)
    {
        this.aac033 = aac033;
    }
    public String getAac034()
    {
        return aac034;
    }
    public void setAac034(String aac034)
    {
        this.aac034 = aac034;
    }
    public String getAac035()
    {
        return aac035;
    }
    public void setAac035(String aac035)
    {
        this.aac035 = aac035;
    }
    public String getAac036()
    {
        return aac036;
    }
    public void setAac036(String aac036)
    {
        this.aac036 = aac036;
    }
    public String getAae004()
    {
        return aae004;
    }
    public void setAae004(String aae004)
    {
        this.aae004 = aae004;
    }
    public String getAae005()
    {
        return aae005;
    }
    public void setAae005(String aae005)
    {
        this.aae005 = aae005;
    }
    public String getAae006()
    {
        return aae006;
    }
    public void setAae006(String aae006)
    {
        this.aae006 = aae006;
    }
    public String getAae007()
    {
        return aae007;
    }
    public void setAae007(String aae007)
    {
        this.aae007 = aae007;
    }
    public String getAae011()
    {
        return aae011;
    }
    public void setAae011(String aae011)
    {
        this.aae011 = aae011;
    }
    public String getAae013()
    {
        return aae013;
    }
    public void setAae013(String aae013)
    {
        this.aae013 = aae013;
    }
    public String getAae015()
    {
        return aae015;
    }
    public void setAae015(String aae015)
    {
        this.aae015 = aae015;
    }
    public String getAae017()
    {
        return aae017;
    }
    public void setAae017(String aae017)
    {
        this.aae017 = aae017;
    }
    public String getAae036()
    {
        return aae036;
    }
    public void setAae036(String aae036)
    {
        this.aae036 = aae036;
    }
    public String getAae043()
    {
        return aae043;
    }
    public void setAae043(String aae043)
    {
        this.aae043 = aae043;
    }
    public String getAcc02i()
    {
        return acc02i;
    }
    public void setAcc02i(String acc02i)
    {
        this.acc02i = acc02i;
    }
    public String getAcc101()
    {
        return acc101;
    }
    public void setAcc101(String acc101)
    {
        this.acc101 = acc101;
    }
    public String getAce119()
    {
        return ace119;
    }
    public void setAce119(String ace119)
    {
        this.ace119 = ace119;
    }
    public String getAce120()
    {
        return ace120;
    }
    public void setAce120(String ace120)
    {
        this.ace120 = ace120;
    }
    public String getAic001()
    {
        return aic001;
    }
    public void setAic001(String aic001)
    {
        this.aic001 = aic001;
    }
    public String getAjc001()
    {
        return ajc001;
    }
    public void setAjc001(String ajc001)
    {
        this.ajc001 = ajc001;
    }
    public String getCce001()
    {
        return cce001;
    }
    public void setCce001(String cce001)
    {
        this.cce001 = cce001;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public String getAae100()
    {
        return aae100;
    }
    public void setAae100(String aae100)
    {
        this.aae100 = aae100;
    }
	
	
}