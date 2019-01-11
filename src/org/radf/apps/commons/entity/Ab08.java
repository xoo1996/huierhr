package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//实收处理$une
public class Ab08 extends EntitySupport {
    private String aab001;//单位编号

    private String aab033;//实缴类型

    private Date aab191;//到账日期

    private BigDecimal aab210;//单位实收

    private BigDecimal aab211;//个人实收

    private BigDecimal aac131;//滞纳金

    private String aae041;//费款开始年月

    private String aae042;//费款结束年月

    private String aae065;//回单单据号

    private String aae066;//基金配置流水号

    private String ajc305;//征缴机构

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

    public Date getAab191() {
        return this.aab191;
    }

    public void setAab191(Date aab191) {
        this.aab191 = aab191;
    }

    public BigDecimal getAab210() {
        return this.aab210;
    }

    public void setAab210(BigDecimal aab210) {
        this.aab210 = aab210;
    }

    public BigDecimal getAab211() {
        return this.aab211;
    }

    public void setAab211(BigDecimal aab211) {
        this.aab211 = aab211;
    }

    public BigDecimal getAac131() {
        return this.aac131;
    }

    public void setAac131(BigDecimal aac131) {
        this.aac131 = aac131;
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

    public String getAae065() {
        return this.aae065;
    }

    public void setAae065(String aae065) {
        this.aae065 = aae065;
    }

    public String getAae066() {
        return this.aae066;
    }

    public void setAae066(String aae066) {
        this.aae066 = aae066;
    }

    public String getAjc305() {
        return this.ajc305;
    }

    public void setAjc305(String ajc305) {
        this.ajc305 = ajc305;
    }

}
