// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZSelectItem.java

package org.radf.plat.Zql;

// Referenced classes of package Zql:
//            ZAliasedName, ZConstant, ZUtils, ZExp

public class ZSelectItem extends ZAliasedName
{

    ZExp expression_;
    String aggregate_;

    public ZSelectItem()
    {
        expression_ = null;
        aggregate_ = null;
    }

    public ZSelectItem(String s)
    {
        super(s, ZAliasedName.FORM_COLUMN);
        expression_ = null;
        aggregate_ = null;
        setAggregate(ZUtils.getAggregateCall(s));
    }

    public ZExp getExpression()
    {
        if(isExpression())
            return expression_;
        if(isWildcard())
            return null;
        else
            return new ZConstant(getColumn(), 0);
    }

    public void setExpression(ZExp zexp)
    {
        expression_ = zexp;
        strform_ = expression_.toString();
    }

    public boolean isExpression()
    {
        return expression_ != null;
    }

    public void setAggregate(String s)
    {
        aggregate_ = s;
    }

    public String getAggregate()
    {
        return aggregate_;
    }
}
