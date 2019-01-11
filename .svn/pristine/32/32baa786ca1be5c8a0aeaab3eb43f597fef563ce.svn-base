// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZGroupBy.java

package org.radf.plat.Zql;
import java.io.Serializable;
import java.util.Vector;

// Referenced classes of package Zql:
//            ZExp

public class ZGroupBy
    implements Serializable
{

    Vector groupby_;
    ZExp having_;

    public ZGroupBy(Vector vector)
    {
        having_ = null;
        groupby_ = vector;
    }

    public void setHaving(ZExp zexp)
    {
        having_ = zexp;
    }

    public Vector getGroupBy()
    {
        return groupby_;
    }

    public ZExp getHaving()
    {
        return having_;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer("group by ");
        stringbuffer.append(groupby_.elementAt(0).toString());
        for(int i = 1; i < groupby_.size(); i++)
            stringbuffer.append(", " + groupby_.elementAt(i).toString());

        if(having_ != null)
            stringbuffer.append(" having " + having_.toString());
        return stringbuffer.toString();
    }
}
