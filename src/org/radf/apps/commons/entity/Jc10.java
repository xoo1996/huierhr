package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//失业保险金待遇审核表$pay
public class Jc10 extends EntitySupport {
    private String aac001;//个人编号

    private String aae005;//联系电话

    private String aae010;//银行帐号

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办日期

    private String aae101;//修改人员

    private String aae116;//享受状态

    private Date aae136;//修改时间

    private String acc020;//失业编号

    private String acc02j;//是否参加医疗

    private Short ajc097;//可领月数

    private Short ajc098;//已领月数

    private String ajc100;//享受开始

    private String ajc105;//发放方式

    private String ajc106;//享受结束

    private BigDecimal ajc140;//失业金标准

    private BigDecimal ajc152;//医疗金标准

    private String bjc100;//审核编号

    private String bjc405;//连续缴费起

    private String bjc406;//连续缴费止
	
	private Short bjc403;//缴费年数
	 
	private Short bjc404;//缴费月数


    private Short bjc407;//上次审核未领取月数

    private String bjc408;//停止年月

    private String bjc409;//城镇工年月
	
    private Short bjc410;//转移月数

    private String bjc411;//转移方式

    private String bjc412;//转移去向
	
	private String bjc160;// 停发原因

	private String aab027;// 银行类型
    public String getBjc160() {
		return bjc160;
	}

	public void setBjc160(String bjc160) {
		this.bjc160 = bjc160;
	}

	public String getAac001() {
        return this.aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001;
    }

    public String getAae005() {
        return this.aae005;
    }

    public void setAae005(String aae005) {
        this.aae005 = aae005;
    }

    public String getAae010() {
        return this.aae010;
    }

    public void setAae010(String aae010) {
        this.aae010 = aae010;
    }

    public String getAae011() {
        return this.aae011;
    }

    public void setAae011(String aae011) {
        this.aae011 = aae011;
    }

    public String getAae013() {
        return this.aae013;
    }

    public void setAae013(String aae013) {
        this.aae013 = aae013;
    }

    public String getAae017() {
        return this.aae017;
    }

    public void setAae017(String aae017) {
        this.aae017 = aae017;
    }

    public String getAae019() {
        return this.aae019;
    }

    public void setAae019(String aae019) {
        this.aae019 = aae019;
    }

    public Date getAae036() {
        return this.aae036;
    }

    public void setAae036(Date aae036) {
        this.aae036 = aae036;
    }

    public String getAae101() {
        return this.aae101;
    }

    public void setAae101(String aae101) {
        this.aae101 = aae101;
    }

    public String getAae116() {
        return this.aae116;
    }

    public void setAae116(String aae116) {
        this.aae116 = aae116;
    }

    public Date getAae136() {
        return this.aae136;
    }

    public void setAae136(Date aae136) {
        this.aae136 = aae136;
    }

    public String getAcc020() {
        return this.acc020;
    }

    public void setAcc020(String acc020) {
        this.acc020 = acc020;
    }

    public String getAcc02j() {
        return this.acc02j;
    }

    public void setAcc02j(String acc02j) {
        this.acc02j = acc02j;
    }

    public Short getAjc097() {
        return this.ajc097;
    }

    public void setAjc097(Short ajc097) {
        this.ajc097 = ajc097;
    }

    public Short getAjc098() {
        return this.ajc098;
    }

    public void setAjc098(Short ajc098) {
        this.ajc098 = ajc098;
    }

    public String getAjc100() {
        return this.ajc100;
    }

    public void setAjc100(String ajc100) {
        this.ajc100 = ajc100;
    }

    public String getAjc105() {
        return this.ajc105;
    }

    public void setAjc105(String ajc105) {
        this.ajc105 = ajc105;
    }

    public String getAjc106() {
        return this.ajc106;
    }

    public void setAjc106(String ajc106) {
        this.ajc106 = ajc106;
    }

    public BigDecimal getAjc140() {
        return this.ajc140;
    }

    public void setAjc140(BigDecimal ajc140) {
        this.ajc140 = ajc140;
    }

    public BigDecimal getAjc152() {
        return this.ajc152;
    }

    public void setAjc152(BigDecimal ajc152) {
        this.ajc152 = ajc152;
    }

    public String getBjc100() {
        return this.bjc100;
    }

    public void setBjc100(String bjc100) {
        this.bjc100 = bjc100;
    }

    public String getBjc405() {
        return this.bjc405;
    }

    public void setBjc405(String bjc405) {
        this.bjc405 = bjc405;
    }

    public String getBjc406() {
        return this.bjc406;
    }

    public void setBjc406(String bjc406) {
        this.bjc406 = bjc406;
    }

    public Short getBjc407() {
        return this.bjc407;
    }

    public void setBjc407(Short bjc407) {
        this.bjc407 = bjc407;
    }

    public String getBjc408() {
        return this.bjc408;
    }

    public void setBjc408(String bjc408) {
        this.bjc408 = bjc408;
    }

    public String getBjc409() {
        return this.bjc409;
    }

    public void setBjc409(String bjc409) {
        this.bjc409 = bjc409;
    }

    public Short getBjc410() {
        return this.bjc410;
    }

    public void setBjc410(Short bjc410) {
        this.bjc410 = bjc410;
    }

    public String getBjc411() {
        return this.bjc411;
    }

    public void setBjc411(String bjc411) {
        this.bjc411 = bjc411;
    }

    public String getBjc412() {
        return this.bjc412;
    }

    public void setBjc412(String bjc412) {
        this.bjc412 = bjc412;
    }

	public Short getBjc403() {
		return bjc403;
	}

	public void setBjc403(Short bjc403) {
		this.bjc403 = bjc403;
	}

	public Short getBjc404() {
		return bjc404;
	}

	public void setBjc404(Short bjc404) {
		this.bjc404 = bjc404;
	}

	public String getAab027() {
		return aab027;
	}

	public void setAab027(String aab027) {
		this.aab027 = aab027;
	}

}
