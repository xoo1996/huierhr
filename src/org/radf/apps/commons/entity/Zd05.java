package org.radf.apps.commons.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//工种介绍$tou
public class Zd05 extends EntitySupport {
    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办时间

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private String aca111;//工种名称

    private String bzd501;//概要介绍

    private String bzd502;//详细介绍

    private String bzd503;//播放文件地址

    private BigDecimal bzd504;//播放时长
    
    private String Bzd505;//id
    
    private String log017;//登陆进来人的经办机构

    public String getLog017() {
		return log017;
	}

	public void setLog017(String log017) {
		this.log017 = log017;
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

    public Date getAae136() {
        return this.aae136;
    }

    public void setAae136(Date aae136) {
        this.aae136 = aae136;
    }

    public String getAca111() {
        return this.aca111;
    }

    public void setAca111(String aca111) {
        this.aca111 = aca111;
    }

    public String getBzd501() {
        return this.bzd501;
    }

    public void setBzd501(String bzd501) {
        this.bzd501 = bzd501;
    }

    public String getBzd502() {
        return this.bzd502;
    }

    public void setBzd502(String bzd502) {
        this.bzd502 = bzd502;
    }

    public String getBzd503() {
        return this.bzd503;
    }

    public void setBzd503(String bzd503) {
        this.bzd503 = bzd503;
    }

    public BigDecimal getBzd504() {
        return this.bzd504;
    }

    public void setBzd504(BigDecimal bzd504) {
        this.bzd504 = bzd504;
    }

	public String getBzd505() {
		return Bzd505;
	}

	public void setBzd505(String bzd505) {
		Bzd505 = bzd505;
	}

}
