// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class WizardURL
    implements Serializable
{

    private int a;
    Map urls;

    public WizardURL()
    {
        a = 0;
        urls = new LinkedHashMap();
    }

    public WizardURL(String s)
    {
        a = 0;
        urls = new LinkedHashMap();
        addURL(s);
    }

    public String previous()
    {
        String s = (String)urls.get((new StringBuffer(String.valueOf(--a))).toString());
        return s != null ? s : "";
    }

    public void addURL(String s)
    {
        urls.put((new StringBuffer(String.valueOf(a))).toString(), s);
        a++;
    }
}
