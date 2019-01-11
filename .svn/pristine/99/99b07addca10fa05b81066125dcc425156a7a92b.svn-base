// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZOrderBy.java

package org.radf.plat.Zql;

import java.io.Serializable;

// Referenced classes of package Zql:
//            ZExp

public class ZOrderBy
    implements Serializable
{

    ZExp exp_;
    boolean asc_;

    public ZOrderBy(ZExp zexp)
    {
        asc_ = true;
        exp_ = zexp;
    }

    public void setAscOrder(boolean flag)
    {
        asc_ = flag;
    }

    public boolean getAscOrder()
    {
        return asc_;
    }

    public ZExp getExpression()
    {
        return exp_;
    }

    public String toString()
    {
        return exp_.toString() + " " + (asc_ ? "ASC" : "DESC");
    }
}
