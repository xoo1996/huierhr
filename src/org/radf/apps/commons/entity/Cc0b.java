package org.radf.apps.commons.entity;

import java.util.Date;
import java.math.BigDecimal;
import org.radf.plat.util.entity.EntitySupport;

//个人工作履历信息表$bas
public class Cc0b extends EntitySupport {
    private String aac001;//	个人编号	

    private String aac045;//	毕业学校或工作单位	   

    private String aae011;//	经办人	

    private String aae013;//	备注	

    private String aae017;//	经办机构	

    private String aae019;//经办科室

    private Date aae030;//	开始日期	

    private Date aae031;//	终止日期	

    private Date aae036;//	经办日期	

    private String acc0b0;//	简历编号	

    private String acc0b4;//	任职或工作	

    private BigDecimal acc217;//	从业年数	

    public String getAac001() {
        return this.aac001;
    }

    public void setAac001(String aac001) {
        this.aac001 = aac001;
    }

    public String getAac045() {
        return this.aac045;
    }

    public void setAac045(String aac045) {
        this.aac045 = aac045;
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

    public Date getAae030() {
        return this.aae030;
    }

    public void setAae030(Date aae030) {
        this.aae030 = aae030;
    }

    public Date getAae031() {
        return this.aae031;
    }

    public void setAae031(Date aae031) {
        this.aae031 = aae031;
    }

    public Date getAae036() {
        return this.aae036;
    }

    public void setAae036(Date aae036) {
        this.aae036 = aae036;
    }

    public String getAcc0b0() {
        return this.acc0b0;
    }

    public void setAcc0b0(String acc0b0) {
        this.acc0b0 = acc0b0;
    }

    public String getAcc0b4() {
        return this.acc0b4;
    }

    public void setAcc0b4(String acc0b4) {
        this.acc0b4 = acc0b4;
    }

    public BigDecimal getAcc217() {
        return this.acc217;
    }

    public void setAcc217(BigDecimal acc217) {
        this.acc217 = acc217;
    }

}
