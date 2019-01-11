// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.commons;

import java.io.Serializable;
import java.math.BigDecimal;

public class Money
    implements Comparable, Serializable
{

    public static final Money ZERO = new Money("0");
    private BigDecimal _fldif;
    private int a;
    private int _flddo;

    public Money(String s)
    {
        if(s != null && !"".equals(s.trim()))
            _fldif = init(new BigDecimal(s));
        else
            _fldif = ZERO.getAmount();
    }

    public Money(BigDecimal bigdecimal)
    {
        _fldif = init(bigdecimal);
    }

    public Money(BigDecimal bigdecimal, int i, int j)
    {
        _fldif = init(bigdecimal, i, j);
    }

    private BigDecimal init(BigDecimal bigdecimal)
    {
        return init(bigdecimal, 2, 4);
    }

    private BigDecimal init(BigDecimal bigdecimal, int i, int j)
    {
        if(bigdecimal != null)
        {
            a = i;
            _flddo = j;
            return bigdecimal.setScale(i, j);
        } else
        {
            return null;
        }
    }

    public BigDecimal getAmount()
    {
        return _fldif;
    }

    public int compareTo(Object obj)
    {
        Money money = (Money)obj;
        if(money == null || _fldif == null)
            return -1;
        else
            return _fldif.compareTo(money._fldif);
    }

    public boolean isLessThanZero()
    {
        return compareTo(ZERO) == -1;
    }

    public Money add(Money money)
    {
        if(money != null && _fldif != null)
            return new Money(_fldif.add(money._fldif), a, _flddo);
        else
            return this;
    }

    public Money subtract(Money money)
    {
        if(money != null && _fldif != null)
            return new Money(_fldif.subtract(money._fldif), a, _flddo);
        else
            return this;
    }

    public Money multiply(int i)
    {
        if(_fldif == null)
            return this;
        else
            return new Money(_fldif.multiply(new BigDecimal(String.valueOf(i))), a, _flddo);
    }

    public Money multiply(BigDecimal bigdecimal, int i)
    {
        if(_fldif == null || bigdecimal == null)
            return this;
        else
            return new Money(_fldif.multiply(bigdecimal).setScale(_fldif.scale(), i), a, _flddo);
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Money))
            return false;
        Money money = (Money)obj;
        if(_fldif != null)
            return money._fldif.equals(_fldif);
        else
            return false;
    }

    public int hashCode()
    {
        if(_fldif == null)
            return -1;
        else
            return _fldif.hashCode();
    }

    public String toString()
    {
        if(_fldif == null)
        {
            return null;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer("гд");
            stringbuffer.append(_fldif.toString());
            return stringbuffer.toString();
        }
    }

}
