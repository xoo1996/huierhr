// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZDelete.java

package org.radf.plat.Zql;


// Referenced classes of package Zql:
//            ZStatement, ZExp

public class ZDelete
    implements ZStatement
{

    String table_;
    ZExp where_;

    public ZDelete(String s)
    {
        where_ = null;
        table_ = new String(s);
    }

    public void addWhere(ZExp zexp)
    {
        where_ = zexp;
    }

    public String getTable()
    {
        return table_;
    }

    public ZExp getWhere()
    {
        return where_;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer("delete ");
        if(where_ != null)
            stringbuffer.append("from ");
        stringbuffer.append(table_);
        if(where_ != null)
            stringbuffer.append(" where " + where_.toString());
        return stringbuffer.toString();
    }
}
