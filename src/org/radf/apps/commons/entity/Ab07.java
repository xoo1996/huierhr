package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//单位缴费核定$une
public class Ab07 extends EntitySupport {
    private String aab001;//单位编号

    private Short aab083;//从业人员数

    private BigDecimal aab085;//缴费合计金额

    private String aab087;//缴费基数核定标志

    private Short aab509;//农民工人数

    private BigDecimal aac131;//滞纳金

    private String aae002;//费款所属期

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办日期

    private String aae101;//修改人

    private Date aae136;//修改时间

    private BigDecimal aja010;//失业保险单位缴费比例

    private BigDecimal aja011;//失业保险个人缴费比例

    private BigDecimal ajb001;//失业保险单位缴纳基数

    private BigDecimal ajb002;//失业保险单位缴费金额

    private BigDecimal ajb003;//失业保险个人缴费金额

    private BigDecimal ajb004;//失业保险单位补缴

    private BigDecimal ajb005;//失业保险个人补缴

    private BigDecimal ajb006;//失业保险个人缴费基数

    private String ajc305;//征缴机构

    public String getAab001() {
        return this.aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
    }

    public Short getAab083() {
        return this.aab083;
    }

    public void setAab083(Short aab083) {
        this.aab083 = aab083;
    }

    public BigDecimal getAab085() {
        return this.aab085;
    }

    public void setAab085(BigDecimal aab085) {
        this.aab085 = aab085;
    }

    public String getAab087() {
        return this.aab087;
    }

    public void setAab087(String aab087) {
        this.aab087 = aab087;
    }

    public Short getAab509() {
        return this.aab509;
    }

    public void setAab509(Short aab509) {
        this.aab509 = aab509;
    }

    public BigDecimal getAac131() {
        return this.aac131;
    }

    public void setAac131(BigDecimal aac131) {
        this.aac131 = aac131;
    }

    public String getAae002() {
        return this.aae002;
    }

    public void setAae002(String aae002) {
        this.aae002 = aae002;
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

    public BigDecimal getAja010() {
        return this.aja010;
    }

    public void setAja010(BigDecimal aja010) {
        this.aja010 = aja010;
    }

    public BigDecimal getAja011() {
        return this.aja011;
    }

    public void setAja011(BigDecimal aja011) {
        this.aja011 = aja011;
    }

    public BigDecimal getAjb001() {
        return this.ajb001;
    }

    public void setAjb001(BigDecimal ajb001) {
        this.ajb001 = ajb001;
    }

    public BigDecimal getAjb002() {
        return this.ajb002;
    }

    public void setAjb002(BigDecimal ajb002) {
        this.ajb002 = ajb002;
    }

    public BigDecimal getAjb003() {
        return this.ajb003;
    }

    public void setAjb003(BigDecimal ajb003) {
        this.ajb003 = ajb003;
    }

    public BigDecimal getAjb004() {
        return this.ajb004;
    }

    public void setAjb004(BigDecimal ajb004) {
        this.ajb004 = ajb004;
    }

    public BigDecimal getAjb005() {
        return this.ajb005;
    }

    public void setAjb005(BigDecimal ajb005) {
        this.ajb005 = ajb005;
    }

    public BigDecimal getAjb006() {
        return this.ajb006;
    }

    public void setAjb006(BigDecimal ajb006) {
        this.ajb006 = ajb006;
    }

    public String getAjc305() {
        return this.ajc305;
    }

    public void setAjc305(String ajc305) {
        this.ajc305 = ajc305;
    }

}
