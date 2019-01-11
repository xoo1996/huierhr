/**
 * Ins01Form.java 2008/03/24
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author fcl
 * @version 1.0
 */
package org.radf.apps.insuranceagent.companyagency.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class Ins01Form extends ActionForm {

	private String aac004;

	private String aac006;//

	private String aac011;//

	private String aac017;//

	private String aac026;//

	private FormFile uploadfile;

	private String bdk002;// 身份证

	private String bdk003;// 姓名

	private String bdk013;// 单位内码

	private String bdk998;// 分类

	private String bdk999;// 流水号

	private String aae100;// 是否有效
	
	private String aac015;
	
	private String aac009;


	// cb90 表
	private String menuId;

	private String aab001;// 单位编号

	private String aac001;// 个人编号

	private String aac002;// 经办人身份证号

	private String aae004;// 单位联系人

	private String aae005;// 联系电话

	private String aae010;// 银行帐号

	private String aae011;// 经办人

	private String aae013;// 备注

	private String aae017;// 经办机构

	private String aae019;// 经办科室

	private String aae030;// 委托开始日期

	private String aae031;// 委托终止日期

	private String aae036;// 经办日期

	private String aae101;// 修改人员

	private String aae136;// 修改时间

	private String acb700;// 委托编号

	private String acb704;// 存档人数

	private String acb707;// 是否签订协议

	private String acb708;// 协议书号码

	private String acb709;// 协议主要内容

	private String acb70a;// 协议签订日期

	private String acb70b;// 协议实际终止日期

	private String acb70c;// 代理机构业务经办人

	private String acb7c0;// 代理费收费标准

	private String acc7e2;// 联系电话

	private String acc7e3;// 代理费收费标准

	private String aab002;// 社保编号

	private String aab003;// 组织机构代码

	private String aab004;// 单位名称

	private String aab019;// 单位类型

	private String aab020;// 经济类型

	private String aae006;// 单位地址

	private String aac003;// 姓名

	// cce2 表

	private String bcce10;// 序号 （后加）

	private String bcce39;// 参保开始年月 （后加）

	private String bcce40;// 终止年月 （后加）

	private String bcceh3;// 预留字段 （后加）

	private String bccei3;// 预留字段 （后加）

	private String bccej3;// 预留字段 （后加）
	
	private String bcce41;// 中断日期

	private String ajc020;// 社保缴费工资

	private String bcb997;// 个人社保编号

	private String bcce01;// 类型编码

	private String bcce31;// 挂靠编号

	private String bcce32;// 退休类型

	private String bcce33;// 公积金编号

	private String bcce34;// 医保缴费工资

	private String bcce35;// 个人缴费比例

	private String bcce36;// 自动修正

	private String bcce37;// 委托状态

	private String bcce38;// 停缴原因

	private String bccea3;// 养老保险基数

	private String bcceb3;// 失业保险基数

	private String bccec3;// 工伤保险基数

	private String bcced3;// 女工生育保险基数

	private String bccee3;// 医疗保险基数

	private String bccef3;// 住房公积金基数

	private String bcceg3;// 档案管理费基数
	
	private String txd001;//提醒天数
	
	private String txd002;//提醒天数

	public String getTxd001() {
		return txd001;
	}

	public void setTxd001(String txd001) {
		this.txd001 = txd001;
	}

	public String getAab001() {
		return aab001;
	}

	public void setAab001(String aab001) {
		this.aab001 = aab001;
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

	public String getAab004() {
		return aab004;
	}

	public void setAab004(String aab004) {
		this.aab004 = aab004;
	}

	public String getAab019() {
		return aab019;
	}

	public void setAab019(String aab019) {
		this.aab019 = aab019;
	}

	public String getAab020() {
		return aab020;
	}

	public void setAab020(String aab020) {
		this.aab020 = aab020;
	}

	public String getAac001() {
		return aac001;
	}

	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}

	public String getAac002() {
		return aac002;
	}

	public void setAac002(String aac002) {
		this.aac002 = aac002;
	}

	public String getAac003() {
		return aac003;
	}

	public void setAac003(String aac003) {
		this.aac003 = aac003;
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

	public String getAae010() {
		return aae010;
	}

	public void setAae010(String aae010) {
		this.aae010 = aae010;
	}

	public String getAae011() {
		return aae011;
	}

	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}

	public String getAae013() {
		return aae013;
	}

	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}

	public String getAae017() {
		return aae017;
	}

	public void setAae017(String aae017) {
		this.aae017 = aae017;
	}

	public String getAae019() {
		return aae019;
	}

	public void setAae019(String aae019) {
		this.aae019 = aae019;
	}

	public String getAae030() {
		return aae030;
	}

	public void setAae030(String aae030) {
		this.aae030 = aae030;
	}

	public String getAae031() {
		return aae031;
	}

	public void setAae031(String aae031) {
		this.aae031 = aae031;
	}

	public String getAae036() {
		return aae036;
	}

	public void setAae036(String aae036) {
		this.aae036 = aae036;
	}

	public String getAae101() {
		return aae101;
	}

	public void setAae101(String aae101) {
		this.aae101 = aae101;
	}

	public String getAae136() {
		return aae136;
	}

	public void setAae136(String aae136) {
		this.aae136 = aae136;
	}

	public String getAcb700() {
		return acb700;
	}

	public void setAcb700(String acb700) {
		this.acb700 = acb700;
	}

	public String getAcb704() {
		return acb704;
	}

	public void setAcb704(String acb704) {
		this.acb704 = acb704;
	}

	public String getAcb707() {
		return acb707;
	}

	public void setAcb707(String acb707) {
		this.acb707 = acb707;
	}

	public String getAcb708() {
		return acb708;
	}

	public void setAcb708(String acb708) {
		this.acb708 = acb708;
	}

	public String getAcb709() {
		return acb709;
	}

	public void setAcb709(String acb709) {
		this.acb709 = acb709;
	}

	public String getAcb70a() {
		return acb70a;
	}

	public void setAcb70a(String acb70a) {
		this.acb70a = acb70a;
	}

	public String getAcb70b() {
		return acb70b;
	}

	public void setAcb70b(String acb70b) {
		this.acb70b = acb70b;
	}

	public String getAcb70c() {
		return acb70c;
	}

	public void setAcb70c(String acb70c) {
		this.acb70c = acb70c;
	}

	public String getAcb7c0() {
		return acb7c0;
	}

	public void setAcb7c0(String acb7c0) {
		this.acb7c0 = acb7c0;
	}

	public String getAcc7e2() {
		return acc7e2;
	}

	public void setAcc7e2(String acc7e2) {
		this.acc7e2 = acc7e2;
	}

	public String getAcc7e3() {
		return acc7e3;
	}

	public void setAcc7e3(String acc7e3) {
		this.acc7e3 = acc7e3;
	}

	public String getAjc020() {
		return ajc020;
	}

	public void setAjc020(String ajc020) {
		this.ajc020 = ajc020;
	}

	public String getBcb997() {
		return bcb997;
	}

	public void setBcb997(String bcb997) {
		this.bcb997 = bcb997;
	}

	public String getBcce01() {
		return bcce01;
	}

	public void setBcce01(String bcce01) {
		this.bcce01 = bcce01;
	}

	public String getBcce31() {
		return bcce31;
	}

	public void setBcce31(String bcce31) {
		this.bcce31 = bcce31;
	}

	public String getBcce32() {
		return bcce32;
	}

	public void setBcce32(String bcce32) {
		this.bcce32 = bcce32;
	}

	public String getBcce33() {
		return bcce33;
	}

	public void setBcce33(String bcce33) {
		this.bcce33 = bcce33;
	}

	public String getBcce34() {
		return bcce34;
	}

	public void setBcce34(String bcce34) {
		this.bcce34 = bcce34;
	}

	public String getBcce35() {
		return bcce35;
	}

	public void setBcce35(String bcce35) {
		this.bcce35 = bcce35;
	}

	public String getBcce36() {
		return bcce36;
	}

	public void setBcce36(String bcce36) {
		this.bcce36 = bcce36;
	}

	public String getBcce37() {
		return bcce37;
	}

	public void setBcce37(String bcce37) {
		this.bcce37 = bcce37;
	}

	public String getBcce38() {
		return bcce38;
	}

	public void setBcce38(String bcce38) {
		this.bcce38 = bcce38;
	}



	public String getBccef3() {
		return bccef3;
	}

	public void setBccef3(String bccef3) {
		this.bccef3 = bccef3;
	}

	public String getBcceg3() {
		return bcceg3;
	}

	public void setBcceg3(String bcceg3) {
		this.bcceg3 = bcceg3;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getAac004() {
		return aac004;
	}

	public void setAac004(String aac004) {
		this.aac004 = aac004;
	}

	public String getAac006() {
		return aac006;
	}

	public void setAac006(String aac006) {
		this.aac006 = aac006;
	}

	public String getAac011() {
		return aac011;
	}

	public void setAac011(String aac011) {
		this.aac011 = aac011;
	}

	public String getAac017() {
		return aac017;
	}

	public void setAac017(String aac017) {
		this.aac017 = aac017;
	}

	public String getAac026() {
		return aac026;
	}

	public void setAac026(String aac026) {
		this.aac026 = aac026;
	}

	public FormFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(FormFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getBdk002() {
		return bdk002;
	}

	public void setBdk002(String bdk002) {
		this.bdk002 = bdk002;
	}

	public String getBdk003() {
		return bdk003;
	}

	public void setBdk003(String bdk003) {
		this.bdk003 = bdk003;
	}

	public String getBdk999() {
		return bdk999;
	}

	public void setBdk999(String bdk999) {
		this.bdk999 = bdk999;
	}

	public String getBdk998() {
		return bdk998;
	}

	public void setBdk998(String bdk998) {
		this.bdk998 = bdk998;
	}

	public String getBdk013() {
		return bdk013;
	}

	public void setBdk013(String bdk013) {
		this.bdk013 = bdk013;
	}

	public String getAae100() {
		return aae100;
	}

	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}

	public String getBcce10() {
		return bcce10;
	}

	public void setBcce10(String bcce10) {
		this.bcce10 = bcce10;
	}

	public String getBcce39() {
		return bcce39;
	}

	public void setBcce39(String bcce39) {
		this.bcce39 = bcce39;
	}

	public String getBcce40() {
		return bcce40;
	}

	public void setBcce40(String bcce40) {
		this.bcce40 = bcce40;
	}

	public String getBcceh3() {
		return bcceh3;
	}

	public void setBcceh3(String bcceh3) {
		this.bcceh3 = bcceh3;
	}

	public String getBccei3() {
		return bccei3;
	}

	public void setBccei3(String bccei3) {
		this.bccei3 = bccei3;
	}

	public String getBccej3() {
		return bccej3;
	}

	public void setBccej3(String bccej3) {
		this.bccej3 = bccej3;
	}

	public String getBcce41() {
		return bcce41;
	}

	public void setBcce41(String bcce41) {
		this.bcce41 = bcce41;
	}

	public String getAac009() {
		return aac009;
	}

	public void setAac009(String aac009) {
		this.aac009 = aac009;
	}

	public String getAac015() {
		return aac015;
	}

	public void setAac015(String aac015) {
		this.aac015 = aac015;
	}

	public String getBccea3() {
		return bccea3;
	}

	public void setBccea3(String bccea3) {
		this.bccea3 = bccea3;
	}

	public String getBcceb3() {
		return bcceb3;
	}

	public void setBcceb3(String bcceb3) {
		this.bcceb3 = bcceb3;
	}

	public String getBccec3() {
		return bccec3;
	}

	public void setBccec3(String bccec3) {
		this.bccec3 = bccec3;
	}

	public String getBcced3() {
		return bcced3;
	}

	public void setBcced3(String bcced3) {
		this.bcced3 = bcced3;
	}

	public String getBccee3() {
		return bccee3;
	}

	public void setBccee3(String bccee3) {
		this.bccee3 = bccee3;
	}

	public String getTxd002() {
		return txd002;
	}

	public void setTxd002(String txd002) {
		this.txd002 = txd002;
	}



}
