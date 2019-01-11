// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZTransactStmt.java

package org.radf.plat.Zql;

// Referenced classes of package Zql:
//            ZStatement

public class ZTransactStmt
    implements ZStatement
{

    String statement_;
    String comment_;
    boolean readOnly_;

    public ZTransactStmt(String s)
    {
        comment_ = null;
        readOnly_ = false;
        statement_ = new String(s);
    }

    public void setComment(String s)
    {
        comment_ = new String(s);
    }

    public String getComment()
    {
        return comment_;
    }

    public boolean isReadOnly()
    {
        return readOnly_;
    }
}
