// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZInsert.java

package org.radf.plat.Zql;
import java.util.Vector;

// Referenced classes of package Zql:
//            ZExpression, ZQuery, ZStatement, ZExp

public class ZInsert
    implements ZStatement
{

    String table_;
    Vector columns_;
    ZExp valueSpec_;

    public ZInsert(String s)
    {
        columns_ = null;
        valueSpec_ = null;
        table_ = new String(s);
    }

    public String getTable()
    {
        return table_;
    }

    public Vector getColumns()
    {
        return columns_;
    }

    public void addColumns(Vector vector)
    {
        columns_ = vector;
    }

    public void addValueSpec(ZExp zexp)
    {
        valueSpec_ = zexp;
    }

    public Vector getValues()
    {
        if(!(valueSpec_ instanceof ZExpression))
            return null;
        else
            return ((ZExpression)valueSpec_).getOperands();
    }

    public ZQuery getQuery()
    {
        if(!(valueSpec_ instanceof ZQuery))
            return null;
        else
            return (ZQuery)valueSpec_;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer("insert into " + table_);
        if(columns_ != null && columns_.size() > 0)
        {
            stringbuffer.append("(" + columns_.elementAt(0));
            for(int i = 1; i < columns_.size(); i++)
                stringbuffer.append("," + columns_.elementAt(i));

            stringbuffer.append(")");
        }
        String s = valueSpec_.toString();
        stringbuffer.append(" ");
        if(getValues() != null)
            stringbuffer.append("values ");
        if(s.startsWith("("))
            stringbuffer.append(s);
        else
            stringbuffer.append(" (" + s + ")");
        return stringbuffer.toString();
    }
}
