package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//新增参保人员$une
public class Ac02 extends EntitySupport {
    private String aab001;//单位编号

    private String aac001;//个人编码

    private String aac013;//用工形式

    private String aac047;//参保开始年月

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办日期

    private String aae041;//缴费开始年月

    private String aae042;//缴费截止年月

    private String aae062;//人员转移流水号

    private String aae101;//修改人

    private Date aae136;//修改时间

    private BigDecimal acc7g1;//缴费基数

    private String ajc301;//参保原因

    private String ajc303;//停保操作员

    private Date ajc304;//停保操作时间

    private String ajc306;//是否特殊参保人员

    private String bcc901;//退休类型

    private String bjc102;//停保计算年月

    private String bjc104;//停保原因

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

    public String getAac013() {
        return this.aac013;
    }

    public void setAac013(String aac013) {
        this.aac013 = aac013;
    }

    public String getAac047() {
        return this.aac047;
    }

    public void setAac047(String aac047) {
        this.aac047 = aac047;
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

    public String getAae062() {
        return this.aae062;
    }

    public void setAae062(String aae062) {
        this.aae062 = aae062;
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

    public BigDecimal getAcc7g1() {
        return this.acc7g1;
    }

    public void setAcc7g1(BigDecimal acc7g1) {
        this.acc7g1 = acc7g1;
    }

    public String getAjc301() {
        return this.ajc301;
    }

    public void setAjc301(String ajc301) {
        this.ajc301 = ajc301;
    }

    public String getAjc303() {
        return this.ajc303;
    }

    public void setAjc303(String ajc303) {
        this.ajc303 = ajc303;
    }

    public Date getAjc304() {
        return this.ajc304;
    }

    public void setAjc304(Date ajc304) {
        this.ajc304 = ajc304;
    }

    public String getAjc306() {
        return this.ajc306;
    }

    public void setAjc306(String ajc306) {
        this.ajc306 = ajc306;
    }

    public String getBcc901() {
        return this.bcc901;
    }

    public void setBcc901(String bcc901) {
        this.bcc901 = bcc901;
    }

    public String getBjc102() {
        return this.bjc102;
    }

    public void setBjc102(String bjc102) {
        this.bjc102 = bjc102;
    }

    public String getBjc104() {
        return this.bjc104;
    }

    public void setBjc104(String bjc104) {
        this.bjc104 = bjc104;
    }

}
