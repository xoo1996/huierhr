package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//失业保险个人应缴实缴明细信息表$une
public class Jc01 extends EntitySupport {
    private String aab001;//单位编号

    private String aab033;//缴费类型

    private String aac001;//个人编号

    private String aac013;//用工形式

    private String aac028;//农民工身份标识

    private String aae002;//费款所属期

    private String aae003;//对应费款所属期

    private Date aae037;//个人缴费到帐日期

    private Date aae038;//单位缴费到帐日期

    private Short aae067;//个人缴费基金配置流水号

    private Short aae068;//单位缴费基金配置流水号

    private String aae101;//修改人

    private String aae114;//个人缴费部分的缴费标志

    private String aae115;//单位缴费部分的缴费标志

    private Date aae136;//修改时间

    private BigDecimal ajc020;//失业保险个人缴费基数？

    private BigDecimal ajc030;//个人缴纳失业保险费金额

    private BigDecimal ajc031;//单位缴纳失业保险费金额

    private String ajc302;//缴费流水号

    public String getAab001() {
        return this.aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
    }

    public String getAab033() {
        return this.aab033;
    }

    public void setAab033(String aab033) {
        this.aab033 = aab033;
    }

    public String getAac001() {
        return this.aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001;
    }

    public String getAac013() {
        return this.aac013;
    }

    public void setAac013(String aac013) {
        this.aac013 = aac013;
    }

    public String getAac028() {
        return this.aac028;
    }

    public void setAac028(String aac028) {
        this.aac028 = aac028;
    }

    public String getAae002() {
        return this.aae002;
    }

    public void setAae002(String aae002) {
        this.aae002 = aae002;
    }

    public String getAae003() {
        return this.aae003;
    }

    public void setAae003(String aae003) {
        this.aae003 = aae003;
    }

    public Date getAae037() {
        return this.aae037;
    }

    public void setAae037(Date aae037) {
        this.aae037 = aae037;
    }

    public Date getAae038() {
        return this.aae038;
    }

    public void setAae038(Date aae038) {
        this.aae038 = aae038;
    }

    public Short getAae067() {
        return this.aae067;
    }

    public void setAae067(Short aae067) {
        this.aae067 = aae067;
    }

    public Short getAae068() {
        return this.aae068;
    }

    public void setAae068(Short aae068) {
        this.aae068 = aae068;
    }

    public String getAae101() {
        return this.aae101;
    }

    public void setAae101(String aae101) {
        this.aae101 = aae101;
    }

    public String getAae114() {
        return this.aae114;
    }

    public void setAae114(String aae114) {
        this.aae114 = aae114;
    }

    public String getAae115() {
        return this.aae115;
    }

    public void setAae115(String aae115) {
        this.aae115 = aae115;
    }

    public Date getAae136() {
        return this.aae136;
    }

    public void setAae136(Date aae136) {
        this.aae136 = aae136;
    }

    public BigDecimal getAjc020() {
        return this.ajc020;
    }

    public void setAjc020(BigDecimal ajc020) {
        this.ajc020 = ajc020;
    }

    public BigDecimal getAjc030() {
        return this.ajc030;
    }

    public void setAjc030(BigDecimal ajc030) {
        this.ajc030 = ajc030;
    }

    public BigDecimal getAjc031() {
        return this.ajc031;
    }

    public void setAjc031(BigDecimal ajc031) {
        this.ajc031 = ajc031;
    }

    public String getAjc302() {
        return this.ajc302;
    }

    public void setAjc302(String ajc302) {
        this.ajc302 = ajc302;
    }

}
