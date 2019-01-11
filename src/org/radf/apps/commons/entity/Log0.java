package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//系统日志表$sys
public class Log0 extends EntitySupport {
    private String bze001;//流水号

    private String bze101;//操作员

    private String bze102;//经办机构

    private String bze103;//业务编号

    private Date bze104;//操作时间

    private String bze105;//操作类型

    public String getBze001() {
        return this.bze001;
    }

    public void setBze001(String bze001) {
        this.bze001 = bze001;
    }

    public String getBze101() {
        return this.bze101;
    }

    public void setBze101(String bze101) {
        this.bze101 = bze101;
    }

    public String getBze102() {
        return this.bze102;
    }

    public void setBze102(String bze102) {
        this.bze102 = bze102;
    }

    public String getBze103() {
        return this.bze103;
    }

    public void setBze103(String bze103) {
        this.bze103 = bze103;
    }

    public Date getBze104() {
        return this.bze104;
    }

    public void setBze104(Date bze104) {
        this.bze104 = bze104;
    }

    public String getBze105() {
        return this.bze105;
    }

    public void setBze105(String bze105) {
        this.bze105 = bze105;
    }

}
