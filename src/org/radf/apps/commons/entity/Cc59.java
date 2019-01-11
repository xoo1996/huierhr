package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//劳务业务个人劳务合同表$lab
public class Cc59 extends EntitySupport {
    private String aab001;//服务单位代码

    private String aac001;//个人代码

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae030;//合同起始日期

    private Date aae031;//合同终止日期

    private Date aae036;//经办日期

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private String bcb112;//合同编号

    private Date bcb541;//签订日期

    private String bcc591;//签订状态

    private String bcc592;//是否续签

    private String bze216;//岗位描述

    private String bze235;//岗位代码
    
    //新增
    private String acc11e;//是否参加公积金
    
    private String acc99a;//参加险种

    public String getAcc11e() {
		return acc11e;
	}

	public void setAcc11e(String acc11e) {
		this.acc11e = acc11e;
	}

	public String getAcc99a() {
		return acc99a;
	}

	public void setAcc99a(String acc99a) {
		this.acc99a = acc99a;
	}

	public String getAab001() {
        return this.aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
    }

    public String getAac001() {
        return this.aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001;
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

    public Date getAae030() {
        return this.aae030;
    }

    public void setAae030(Date aae030) {
        this.aae030 = aae030;
    }

    public Date getAae031() {
        return this.aae031;
    }

    public void setAae031(Date aae031) {
        this.aae031 = aae031;
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

    public String getBcb112() {
        return this.bcb112;
    }

    public void setBcb112(String bcb112) {
        this.bcb112 = bcb112;
    }

    public Date getBcb541() {
        return this.bcb541;
    }

    public void setBcb541(Date bcb541) {
        this.bcb541 = bcb541;
    }

    public String getBcc591() {
        return this.bcc591;
    }

    public void setBcc591(String bcc591) {
        this.bcc591 = bcc591;
    }

    public String getBcc592() {
        return this.bcc592;
    }

    public void setBcc592(String bcc592) {
        this.bcc592 = bcc592;
    }

    public String getBze216() {
        return this.bze216;
    }

    public void setBze216(String bze216) {
        this.bze216 = bze216;
    }

    public String getBze235() {
        return this.bze235;
    }

    public void setBze235(String bze235) {
        this.bze235 = bze235;
    }

}
