package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//劳务用工协议信息表$lab
public class Cb54 extends EntitySupport {
    private String aab001;//单位代码

    private String aae011;//经办人

    private String aae013;//备注

    private String aae017;//经办机构

    private String aae019;//经办科室

    private Date aae036;//经办日期

    private String aae101;//修改人员

    private Date aae136;//修改时间

    private Date bcb541;//签定年月

    private Date bcb542;//开始日期

    private Date bcb543;//截止日期

    private String bcb544;//协议状态

    private String bcc900;//劳务用工协议编号

    public String getAab001() {
        return this.aab001;
    }

    public void setAab001(String aab001) {
        this.aab001 = aab001;
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

    public Date getBcb541() {
        return this.bcb541;
    }

    public void setBcb541(Date bcb541) {
        this.bcb541 = bcb541;
    }

    public Date getBcb542() {
        return this.bcb542;
    }

    public void setBcb542(Date bcb542) {
        this.bcb542 = bcb542;
    }

    public Date getBcb543() {
        return this.bcb543;
    }

    public void setBcb543(Date bcb543) {
        this.bcb543 = bcb543;
    }

    public String getBcb544() {
        return this.bcb544;
    }

    public void setBcb544(String bcb544) {
        this.bcb544 = bcb544;
    }

    public String getBcc900() {
        return this.bcc900;
    }

    public void setBcc900(String bcc900) {
        this.bcc900 = bcc900;
    }

}
