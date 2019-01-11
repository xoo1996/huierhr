// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZLockTable.java

package org.radf.plat.Zql;

import java.util.Vector;

// Referenced classes of package Zql:
//            ZStatement

public class ZLockTable
    implements ZStatement
{

    boolean nowait_;
    String lockMode_;
    Vector tables_;

    public ZLockTable()
    {
        nowait_ = false;
        lockMode_ = null;
        tables_ = null;
    }

    public void addTables(Vector vector)
    {
        tables_ = vector;
    }

    public Vector getTables()
    {
        return tables_;
    }

    public void setLockMode(String s)
    {
        lockMode_ = new String(s);
    }

    public String getLockMode()
    {
        return lockMode_;
    }

    public boolean isNowait()
    {
        return nowait_;
    }
}
