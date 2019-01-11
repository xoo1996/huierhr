package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//民办职业介绍结构日常检查投诉表$rec
public class Cb26 extends EntitySupport {
    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办时间

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private String acb240;//机构代码

    private String acb260;//内码 (主键)

    private String acb261;//类型

    private Short acb262;//分数

    private String acb263;//原因

    private String acb264;//处理结果

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

    public String getAcb240() {
        return this.acb240;
    }

    public void setAcb240(String acb240) {
        this.acb240 = acb240;
    }

    public String getAcb260() {
        return this.acb260;
    }

    public void setAcb260(String acb260) {
        this.acb260 = acb260;
    }

    public String getAcb261() {
        return this.acb261;
    }

    public void setAcb261(String acb261) {
        this.acb261 = acb261;
    }

    public Short getAcb262() {
        return this.acb262;
    }

    public void setAcb262(Short acb262) {
        this.acb262 = acb262;
    }

    public String getAcb263() {
        return this.acb263;
    }

    public void setAcb263(String acb263) {
        this.acb263 = acb263;
    }

    public String getAcb264() {
        return this.acb264;
    }

    public void setAcb264(String acb264) {
        this.acb264 = acb264;
    }

}
