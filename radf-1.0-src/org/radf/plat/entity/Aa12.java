package org.radf.plat.entity;

import org.radf.plat.util.entity.EntitySupport;

//全国行政区划表
public class Aa12 extends EntitySupport {
    private String aaa020;//所在地行政区划代码

    private String aaa021;//行政区划名称

    private String baa022;//辅助排序字段

    public String getAaa020() {
        return this.aaa020;
    }

    public void setAaa020(String aaa020) {
        this.aaa020 = aaa020;
    }

    public String getAaa021() {
        return this.aaa021;
    }

    public void setAaa021(String aaa021) {
        this.aaa021 = aaa021;
    }

    public String getBaa022() {
        return this.baa022;
    }

    public void setBaa022(String baa022) {
        this.baa022 = baa022;
    }

}
