// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZUtils.java

package org.radf.plat.Zql;

import java.util.Hashtable;

public class ZUtils
{

    private static Hashtable fcts_ = null;
    public static final int VARIABLE_PLIST = 10000;

    public ZUtils()
    {
    }

    public static void addCustomFunction(String s, int i)
    {
        if(fcts_ == null)
            fcts_ = new Hashtable();
        if(i <= 0)
            i = 1;
        fcts_.put(s.toUpperCase(), new Integer(i));
    }

    public static int isCustomFunction(String s)
    {
        Integer integer;
        if(s == null || s.length() < 1 || fcts_ == null || (integer = (Integer)fcts_.get(s.toUpperCase())) == null)
            return -1;
        else
            return integer.intValue();
    }

    public static boolean isAggregate(String s)
    {
        s = s.toUpperCase().trim();
        return s.equals("SUM") || s.equals("AVG") || s.equals("MAX") || s.equals("MIN") || s.equals("COUNT") || fcts_ != null && fcts_.get(s) != null;
    }

    public static String getAggregateCall(String s)
    {
        int i = s.indexOf(40);
        if(i <= 0)
            return null;
        String s1 = s.substring(0, i);
        if(isAggregate(s1))
            return s1.trim();
        else
            return null;
    }

}
