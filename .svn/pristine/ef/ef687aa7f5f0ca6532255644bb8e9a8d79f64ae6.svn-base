// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZConstant.java

package org.radf.plat.Zql;

// Referenced classes of package Zql:
//            ZExp

public class ZConstant
    implements ZExp
{

    public static final int UNKNOWN = -1;
    public static final int COLUMNNAME = 0;
    public static final int NULL = 1;
    public static final int NUMBER = 2;
    public static final int STRING = 3;
    int type_;
    String val_;

    public ZConstant(String s, int i)
    {
        type_ = -1;
        val_ = null;
        val_ = new String(s);
        type_ = i;
    }

    public String getValue()
    {
        return val_;
    }

    public int getType()
    {
        return type_;
    }

    public String toString()
    {
        if(type_ == 3)
            return '\'' + val_ + '\'';
        else
            return val_;
    }
}
