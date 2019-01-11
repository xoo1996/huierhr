package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//资金收支表$ins
public class Cc7m extends EntitySupport {
    private BigDecimal aab180;//发生金额 

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae032;//发生时间

    private Date aae036;//经办时间

    private String aae041;//缴费开始年月

    private String aae042;//缴费结束年月 

    private String aae063;//资金编码

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private String acb700;//委托编号

    private String bab181;//发票号

    private String bac140;//费用类别

    public BigDecimal getAab180() {
        return this.aab180;
    }

    public void setAab180(BigDecimal aab180) {
        this.aab180 = aab180;
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

    public Date getAae032() {
        return this.aae032;
    }

    public void setAae032(Date aae032) {
        this.aae032 = aae032;
    }

    public Date getAae036() {
        return this.aae036;
    }

    public void setAae036(Date aae036) {
        this.aae036 = aae036;
    }

    public String getAae041() {
        return this.aae041;
    }

    public void setAae041(String aae041) {
        this.aae041 = aae041;
    }

    public String getAae042() {
        return this.aae042;
    }

    public void setAae042(String aae042) {
        this.aae042 = aae042;
    }

    public String getAae063() {
        return this.aae063;
    }

    public void setAae063(String aae063) {
        this.aae063 = aae063;
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

    public String getAcb700() {
        return this.acb700;
    }

    public void setAcb700(String acb700) {
        this.acb700 = acb700;
    }

    public String getBab181() {
        return this.bab181;
    }

    public void setBab181(String bab181) {
        this.bab181 = bab181;
    }

    public String getBac140() {
        return this.bac140;
    }

    public void setBac140(String bac140) {
        this.bac140 = bac140;
    }

}
