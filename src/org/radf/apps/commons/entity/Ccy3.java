package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//小企业贷款$ree
public class Ccy3 extends EntitySupport {
    private String aab001;//单位编号

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办时间

    private String aae101;//修改人员

    private String aae136;//修改时间

    private String acc144;//是否微利项目

    private String bcc3y1;//贷款编号

    private Date bcc3y2;//申请日期

    private BigDecimal bcc3y3;//贷款金额（元）

    private String bcc3y4;//经营项目

    private String bcc3y5;//审核人

    private Short bcc3y6;//登记扶持就业数

    private Short bcc3y7;//劳动数

    public String getAab001() {
        return this.aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
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

    public String getAae136() {
        return this.aae136;
    }

    public void setAae136(String aae136) {
        this.aae136 = aae136;
    }

    public String getAcc144() {
        return this.acc144;
    }

    public void setAcc144(String acc144) {
        this.acc144 = acc144;
    }

    public String getBcc3y1() {
        return this.bcc3y1;
    }

    public void setBcc3y1(String bcc3y1) {
        this.bcc3y1 = bcc3y1;
    }

    public Date getBcc3y2() {
        return this.bcc3y2;
    }

    public void setBcc3y2(Date bcc3y2) {
        this.bcc3y2 = bcc3y2;
    }

    public BigDecimal getBcc3y3() {
        return this.bcc3y3;
    }

    public void setBcc3y3(BigDecimal bcc3y3) {
        this.bcc3y3 = bcc3y3;
    }

    public String getBcc3y4() {
        return this.bcc3y4;
    }

    public void setBcc3y4(String bcc3y4) {
        this.bcc3y4 = bcc3y4;
    }

    public String getBcc3y5() {
        return this.bcc3y5;
    }

    public void setBcc3y5(String bcc3y5) {
        this.bcc3y5 = bcc3y5;
    }

    public Short getBcc3y6() {
        return this.bcc3y6;
    }

    public void setBcc3y6(Short bcc3y6) {
        this.bcc3y6 = bcc3y6;
    }

    public Short getBcc3y7() {
        return this.bcc3y7;
    }

    public void setBcc3y7(Short bcc3y7) {
        this.bcc3y7 = bcc3y7;
    }

}
