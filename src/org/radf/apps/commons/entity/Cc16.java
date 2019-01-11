package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//小额贷款贴息表$ree
public class Cc16 extends EntitySupport {
    private String aac001;//人员内码

    private String aae011;//经办人

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办日期

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private String acc140;//贷款编号

    private BigDecimal acc141;//贷款金额(元)

    private String acc144;//是否微利项目

    private Date acc146;//小额贷款申请日期

    private Date bcc13x;//贷款时间

    private Date bcc142;//还款时间

    private BigDecimal bcc144;//还款金额

    private String bcc180;//贴息表内码编号

    private BigDecimal bcc181;//申请贴息金额

    private String bcc183;//劳动部门审核意见

    private String bcc184;//财政部门审批意见

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

    public String getAcc140() {
        return this.acc140;
    }

    public void setAcc140(String acc140) {
        this.acc140 = acc140;
    }

    public BigDecimal getAcc141() {
        return this.acc141;
    }

    public void setAcc141(BigDecimal acc141) {
        this.acc141 = acc141;
    }

    public String getAcc144() {
        return this.acc144;
    }

    public void setAcc144(String acc144) {
        this.acc144 = acc144;
    }

    public Date getAcc146() {
        return this.acc146;
    }

    public void setAcc146(Date acc146) {
        this.acc146 = acc146;
    }

    public Date getBcc13x() {
        return this.bcc13x;
    }

    public void setBcc13x(Date bcc13x) {
        this.bcc13x = bcc13x;
    }

    public Date getBcc142() {
        return this.bcc142;
    }

    public void setBcc142(Date bcc142) {
        this.bcc142 = bcc142;
    }

    public BigDecimal getBcc144() {
        return this.bcc144;
    }

    public void setBcc144(BigDecimal bcc144) {
        this.bcc144 = bcc144;
    }

    public String getBcc180() {
        return this.bcc180;
    }

    public void setBcc180(String bcc180) {
        this.bcc180 = bcc180;
    }

    public BigDecimal getBcc181() {
        return this.bcc181;
    }

    public void setBcc181(BigDecimal bcc181) {
        this.bcc181 = bcc181;
    }

    public String getBcc183() {
        return this.bcc183;
    }

    public void setBcc183(String bcc183) {
        this.bcc183 = bcc183;
    }

    public String getBcc184() {
        return this.bcc184;
    }

    public void setBcc184(String bcc184) {
        this.bcc184 = bcc184;
    }

}
