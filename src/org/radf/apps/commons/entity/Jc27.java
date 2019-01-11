package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//失业人员住院补助表$pay
public class Jc27 extends EntitySupport {
    private String aac001;//个人编号

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办日期

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private String acc020;//失业登记编号

    private String ajc273;//医院名称

    private Date ajc277;//住院日期

    private BigDecimal ajc312;//一次性医疗待遇金额

    private BigDecimal akc227;//发生医疗费用

    private String bjc270;//住院补助编号

    private Date bjc302;//登记日期

    private String bjc413;//是否转院

    private String bjc414;//转入院名称

    private String bjc415;//住院原因

    private String bjc416;//一次性医疗待遇信息

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

    public String getAcc020() {
        return this.acc020;
    }

    public void setAcc020(String acc020) {
        this.acc020 = acc020;
    }

    public String getAjc273() {
        return this.ajc273;
    }

    public void setAjc273(String ajc273) {
        this.ajc273 = ajc273;
    }

    public Date getAjc277() {
        return this.ajc277;
    }

    public void setAjc277(Date ajc277) {
        this.ajc277 = ajc277;
    }

    public BigDecimal getAjc312() {
        return this.ajc312;
    }

    public void setAjc312(BigDecimal ajc312) {
        this.ajc312 = ajc312;
    }

    public BigDecimal getAkc227() {
        return this.akc227;
    }

    public void setAkc227(BigDecimal akc227) {
        this.akc227 = akc227;
    }

    public String getBjc270() {
        return this.bjc270;
    }

    public void setBjc270(String bjc270) {
        this.bjc270 = bjc270;
    }

    public Date getBjc302() {
        return this.bjc302;
    }

    public void setBjc302(Date bjc302) {
        this.bjc302 = bjc302;
    }

    public String getBjc413() {
        return this.bjc413;
    }

    public void setBjc413(String bjc413) {
        this.bjc413 = bjc413;
    }

    public String getBjc414() {
        return this.bjc414;
    }

    public void setBjc414(String bjc414) {
        this.bjc414 = bjc414;
    }

    public String getBjc415() {
        return this.bjc415;
    }

    public void setBjc415(String bjc415) {
        this.bjc415 = bjc415;
    }

    public String getBjc416() {
        return this.bjc416;
    }

    public void setBjc416(String bjc416) {
        this.bjc416 = bjc416;
    }

}
