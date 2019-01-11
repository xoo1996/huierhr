package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//单位月缴纳表$lab
public class Cb52 extends EntitySupport {
    private String aab001;//单位编号

    private BigDecimal aac040;//应发工资

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办日期

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private BigDecimal acc520;//养老保险

    private BigDecimal acc521;//医疗保险

    private BigDecimal acc522;//工伤保险

    private BigDecimal acc526;//实际工资

    private BigDecimal acc52f;//生育保险

    private BigDecimal acc7g1;//缴费基数

    private Date bcb520;//交纳月份

    private BigDecimal bcb521;//应缴总额(工资+管理费+保险费)

    private BigDecimal bcb522;//保险应缴(五保单位部分)

    private BigDecimal bcb523;//社保个人总额 

    private BigDecimal bcb524;//差额

    private BigDecimal bcb525;//公积金合计

    private BigDecimal bcb526;//个税起征点

    private BigDecimal bcc614;//失业保险

    private BigDecimal bcc621;//管理费用

    private BigDecimal bcc624;//养老保险单位部分

    private BigDecimal bcc625;//养老保险个人部分

    private BigDecimal bcc626;//失业保险单位部分

    private BigDecimal bcc627;//失业保险个人部分

    private BigDecimal bcc628;//医疗保险单位部分

    private BigDecimal bcc629;//医疗保险个人部分

    private BigDecimal bcc62a;//工伤保险单位部分

    private BigDecimal bcc62b;//工伤保险个人部分

    private BigDecimal bcc62c;//生育保险单位部分

    private BigDecimal bcc62d;//生育保险个人部分

    private BigDecimal bcc62e;//扣个税

    private BigDecimal bcc62f;//附加费

    private BigDecimal bcc62l;//公积金个人

    private BigDecimal bcc62m;//公积金单位

    public String getAab001() {
        return this.aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
    }

    public BigDecimal getAac040() {
        return this.aac040;
    }

    public void setAac040(BigDecimal aac040) {
        this.aac040 = aac040;
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

    public BigDecimal getAcc520() {
        return this.acc520;
    }

    public void setAcc520(BigDecimal acc520) {
        this.acc520 = acc520;
    }

    public BigDecimal getAcc521() {
        return this.acc521;
    }

    public void setAcc521(BigDecimal acc521) {
        this.acc521 = acc521;
    }

    public BigDecimal getAcc522() {
        return this.acc522;
    }

    public void setAcc522(BigDecimal acc522) {
        this.acc522 = acc522;
    }

    public BigDecimal getAcc526() {
        return this.acc526;
    }

    public void setAcc526(BigDecimal acc526) {
        this.acc526 = acc526;
    }

    public BigDecimal getAcc52f() {
        return this.acc52f;
    }

    public void setAcc52f(BigDecimal acc52f) {
        this.acc52f = acc52f;
    }

    public BigDecimal getAcc7g1() {
        return this.acc7g1;
    }

    public void setAcc7g1(BigDecimal acc7g1) {
        this.acc7g1 = acc7g1;
    }

    public Date getBcb520() {
        return this.bcb520;
    }

    public void setBcb520(Date bcb520) {
        this.bcb520 = bcb520;
    }

    public BigDecimal getBcb521() {
        return this.bcb521;
    }

    public void setBcb521(BigDecimal bcb521) {
        this.bcb521 = bcb521;
    }

    public BigDecimal getBcb522() {
        return this.bcb522;
    }

    public void setBcb522(BigDecimal bcb522) {
        this.bcb522 = bcb522;
    }

    public BigDecimal getBcb523() {
        return this.bcb523;
    }

    public void setBcb523(BigDecimal bcb523) {
        this.bcb523 = bcb523;
    }

    public BigDecimal getBcb524() {
        return this.bcb524;
    }

    public void setBcb524(BigDecimal bcb524) {
        this.bcb524 = bcb524;
    }

    public BigDecimal getBcb525() {
        return this.bcb525;
    }

    public void setBcb525(BigDecimal bcb525) {
        this.bcb525 = bcb525;
    }

    public BigDecimal getBcb526() {
        return this.bcb526;
    }

    public void setBcb526(BigDecimal bcb526) {
        this.bcb526 = bcb526;
    }

    public BigDecimal getBcc614() {
        return this.bcc614;
    }

    public void setBcc614(BigDecimal bcc614) {
        this.bcc614 = bcc614;
    }

    public BigDecimal getBcc621() {
        return this.bcc621;
    }

    public void setBcc621(BigDecimal bcc621) {
        this.bcc621 = bcc621;
    }

    public BigDecimal getBcc624() {
        return this.bcc624;
    }

    public void setBcc624(BigDecimal bcc624) {
        this.bcc624 = bcc624;
    }

    public BigDecimal getBcc625() {
        return this.bcc625;
    }

    public void setBcc625(BigDecimal bcc625) {
        this.bcc625 = bcc625;
    }

    public BigDecimal getBcc626() {
        return this.bcc626;
    }

    public void setBcc626(BigDecimal bcc626) {
        this.bcc626 = bcc626;
    }

    public BigDecimal getBcc627() {
        return this.bcc627;
    }

    public void setBcc627(BigDecimal bcc627) {
        this.bcc627 = bcc627;
    }

    public BigDecimal getBcc628() {
        return this.bcc628;
    }

    public void setBcc628(BigDecimal bcc628) {
        this.bcc628 = bcc628;
    }

    public BigDecimal getBcc629() {
        return this.bcc629;
    }

    public void setBcc629(BigDecimal bcc629) {
        this.bcc629 = bcc629;
    }

    public BigDecimal getBcc62a() {
        return this.bcc62a;
    }

    public void setBcc62a(BigDecimal bcc62a) {
        this.bcc62a = bcc62a;
    }

    public BigDecimal getBcc62b() {
        return this.bcc62b;
    }

    public void setBcc62b(BigDecimal bcc62b) {
        this.bcc62b = bcc62b;
    }

    public BigDecimal getBcc62c() {
        return this.bcc62c;
    }

    public void setBcc62c(BigDecimal bcc62c) {
        this.bcc62c = bcc62c;
    }

    public BigDecimal getBcc62d() {
        return this.bcc62d;
    }

    public void setBcc62d(BigDecimal bcc62d) {
        this.bcc62d = bcc62d;
    }

    public BigDecimal getBcc62e() {
        return this.bcc62e;
    }

    public void setBcc62e(BigDecimal bcc62e) {
        this.bcc62e = bcc62e;
    }

    public BigDecimal getBcc62f() {
        return this.bcc62f;
    }

    public void setBcc62f(BigDecimal bcc62f) {
        this.bcc62f = bcc62f;
    }

    public BigDecimal getBcc62l() {
        return this.bcc62l;
    }

    public void setBcc62l(BigDecimal bcc62l) {
        this.bcc62l = bcc62l;
    }

    public BigDecimal getBcc62m() {
        return this.bcc62m;
    }

    public void setBcc62m(BigDecimal bcc62m) {
        this.bcc62m = bcc62m;
    }

}
