// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.commons;

import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.collections.FastHashMap;

public class PatternFactory
{

    static Map patternMap = new FastHashMap();

    public PatternFactory()
    {
    }

    public static Pattern getPattern(String s)
    {
        Pattern pattern = null;
        if(patternMap.containsKey(s))
        {
            pattern = (Pattern)patternMap.get(s);
        } else
        {
            pattern = pattern = Pattern.compile(s);
            patternMap.put(s, pattern);
        }
        return pattern;
    }

}
