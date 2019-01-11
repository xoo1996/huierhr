package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//月代缴费用表$ins
public class Ccd1 extends EntitySupport {
    private String aab001;//单位编号

    private String aac001;//人员编码 

    private String aae011;//经办人    

    private String aae013;//备注    

    private String aae017;//经办机构  

    private String aae019;//经办科室

    private Date aae036;//经办日期   

    private String aae063;//资金编码

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private String acb700;//委托编号

    private BigDecimal ajc020;//缴费工资基数 

    private String bccd10;//应缴年月   

    private BigDecimal bccd11;//养老保险金额  

    private BigDecimal bccd12;//失业保险金额   

    private BigDecimal bccd13;//工伤保险金额    

    private BigDecimal bccd14;//女工生育保险金额  

    private BigDecimal bccd15;//医疗保险金额      

    private BigDecimal bccd16;//住房公积金金额   

    private Short bccd17;//补缴月份数     

    private String bccd18;//拨付标志   

    private BigDecimal bccd19;//预留字段(其他)档案费

    private BigDecimal bccd20;//预留字段(其他)

    private BigDecimal bccd21;//预留字段(其他)

    private String bcce01;//代理类型(类型编码)

    private String boe010;//序列号

    private Date boe011;//发生时间

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

    public BigDecimal getAjc020() {
        return this.ajc020;
    }

    public void setAjc020(BigDecimal ajc020) {
        this.ajc020 = ajc020;
    }

    public String getBccd10() {
        return this.bccd10;
    }

    public void setBccd10(String bccd10) {
        this.bccd10 = bccd10;
    }

    public BigDecimal getBccd11() {
        return this.bccd11;
    }

    public void setBccd11(BigDecimal bccd11) {
        this.bccd11 = bccd11;
    }

    public BigDecimal getBccd12() {
        return this.bccd12;
    }

    public void setBccd12(BigDecimal bccd12) {
        this.bccd12 = bccd12;
    }

    public BigDecimal getBccd13() {
        return this.bccd13;
    }

    public void setBccd13(BigDecimal bccd13) {
        this.bccd13 = bccd13;
    }

    public BigDecimal getBccd14() {
        return this.bccd14;
    }

    public void setBccd14(BigDecimal bccd14) {
        this.bccd14 = bccd14;
    }

    public BigDecimal getBccd15() {
        return this.bccd15;
    }

    public void setBccd15(BigDecimal bccd15) {
        this.bccd15 = bccd15;
    }

    public BigDecimal getBccd16() {
        return this.bccd16;
    }

    public void setBccd16(BigDecimal bccd16) {
        this.bccd16 = bccd16;
    }

    public Short getBccd17() {
        return this.bccd17;
    }

    public void setBccd17(Short bccd17) {
        this.bccd17 = bccd17;
    }

    public String getBccd18() {
        return this.bccd18;
    }

    public void setBccd18(String bccd18) {
        this.bccd18 = bccd18;
    }

    public BigDecimal getBccd19() {
        return this.bccd19;
    }

    public void setBccd19(BigDecimal bccd19) {
        this.bccd19 = bccd19;
    }

    public BigDecimal getBccd20() {
        return this.bccd20;
    }

    public void setBccd20(BigDecimal bccd20) {
        this.bccd20 = bccd20;
    }

    public BigDecimal getBccd21() {
        return this.bccd21;
    }

    public void setBccd21(BigDecimal bccd21) {
        this.bccd21 = bccd21;
    }

    public String getBcce01() {
        return this.bcce01;
    }

    public void setBcce01(String bcce01) {
        this.bcce01 = bcce01;
    }

    public String getBoe010() {
        return this.boe010;
    }

    public void setBoe010(String boe010) {
        this.boe010 = boe010;
    }

    public Date getBoe011() {
        return this.boe011;
    }

    public void setBoe011(Date boe011) {
        this.boe011 = boe011;
    }

}
