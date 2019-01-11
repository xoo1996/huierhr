package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//合同备案内容变更表$emp
public class Cc9x extends EntitySupport {
    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室  

    private Date aae035;//变更日期

    private Date aae036;//经办日期

    private String aae101;//修改操作员 

    private Date aae136;//修改日期

    private String bcc9b1;//合同内码

    private String bcc9x0;//变更内码

    private String bcc9x1;//合同内容变更类型

    private String bcc9x2;//变更前的值 

    private String bcc9x3;//变更后的值 

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

    public Date getAae035() {
        return this.aae035;
    }

    public void setAae035(Date aae035) {
        this.aae035 = aae035;
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

    public String getBcc9b1() {
        return this.bcc9b1;
    }

    public void setBcc9b1(String bcc9b1) {
        this.bcc9b1 = bcc9b1;
    }

    public String getBcc9x0() {
        return this.bcc9x0;
    }

    public void setBcc9x0(String bcc9x0) {
        this.bcc9x0 = bcc9x0;
    }

    public String getBcc9x1() {
        return this.bcc9x1;
    }

    public void setBcc9x1(String bcc9x1) {
        this.bcc9x1 = bcc9x1;
    }

    public String getBcc9x2() {
        return this.bcc9x2;
    }

    public void setBcc9x2(String bcc9x2) {
        this.bcc9x2 = bcc9x2;
    }

    public String getBcc9x3() {
        return this.bcc9x3;
    }

    public void setBcc9x3(String bcc9x3) {
        this.bcc9x3 = bcc9x3;
    }

}
