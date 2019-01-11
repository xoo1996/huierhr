package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

//指标比对表$sys
public class Zbbd extends EntitySupport {
    private String memo;//无

    private String mkname;//无

    private String name;//无

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMkname() {
        return this.mkname;
    }

    public void setMkname(String mkname) {
        this.mkname = mkname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
