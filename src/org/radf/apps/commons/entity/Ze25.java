package org.radf.apps.commons.entity;

import java.util.Date;
import org.radf.plat.util.entity.EntitySupport;

//大屏幕外部上传文件列表
public class Ze25 extends EntitySupport {
    private String aae011;//经办人

    private String aae017;//经办机构

    private Date aae036;//经办日期

    private String bze21y;//上传文件名

    private String bze21z;//序列号

    private String bze250;//是否为有效插入广告0为无效，1为招聘有效 ，2为求职有效 0724bylwd

    public String getAae011() {
        return this.aae011;
    }

    public void setAae011(String aae011) {
        this.aae011 = aae011;
    }

    public String getAae017() {
        return this.aae017;
    }

    public void setAae017(String aae017) {
        this.aae017 = aae017;
    }

    public Date getAae036() {
        return this.aae036;
    }

    public void setAae036(Date aae036) {
        this.aae036 = aae036;
    }

    public String getBze21y() {
        return this.bze21y;
    }

    public void setBze21y(String bze21y) {
        this.bze21y = bze21y;
    }

    public String getBze21z() {
        return this.bze21z;
    }

    public void setBze21z(String bze21z) {
        this.bze21z = bze21z;
    }

    public String getBze250() {
        return this.bze250;
    }

    public void setBze250(String bze250) {
        this.bze250 = bze250;
    }

}
