package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

//ģ�����
public class Tab_export extends EntitySupport {
    private String memo;//����

    private String table_name;//����

    private String type;//ģ�����

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTable_name() {
        return this.table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
