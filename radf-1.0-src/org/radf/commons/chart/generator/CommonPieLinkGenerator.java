// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart.generator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.laures.cewolf.links.PieSectionLinkGenerator;

public class CommonPieLinkGenerator
    implements PieSectionLinkGenerator
{

    private static Log a;
    //static Class class$0; /* synthetic field */

    public CommonPieLinkGenerator()
    {
    }

    public String generateLink(Object obj, Object obj1)
    {
        a.info("xxx");
        return "#";
    }

    static 
    {
        a = LogFactory.getLog(org.radf.commons.chart.generator.CommonPieLinkGenerator.class);
    }
}
