// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.a;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

public class b
{

    public b()
    {
    }

    public static boolean a(ServletContext servletcontext, String s)
    {
        return s != null && servletcontext.getAttribute(s) != null;
    }



    public static String a(ServletContext servletcontext, String s, String s1)
    {
        if(s1 != null)
        {
            s1 = s1.trim();
            Map map = (Map)servletcontext.getAttribute(s);
            if(map != null)
            {
                Set set = map.entrySet();
                for(Iterator iterator = set.iterator(); iterator.hasNext();)
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                    if(s1.equals(entry.getValue()))
                        return (String)entry.getKey();
                }

            }
        }
        return null;
    }
}
