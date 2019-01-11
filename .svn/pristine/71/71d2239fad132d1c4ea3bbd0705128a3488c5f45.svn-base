// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib.xtree;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.taglib.xtree.a;

// Referenced classes of package com.lbs.commons.xtree:
//            a

public class TreeMenuTag extends TagSupport
{

    private String b;

    public TreeMenuTag()
    {
        b = GlobalNames.MENU_XML;
    }

    public int doStartTag()
        throws JspException
    {
        JspWriter jspwriter = pageContext.getOut();
        javax.servlet.ServletContext servletcontext = pageContext.getServletContext();
        try
        {
            a._mthif(jspwriter);
            jspwriter.print(pageContext.findAttribute(getContentKey()));
            a.a(jspwriter);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return 0;
    }

    public String getContentKey()
    {
        return b;
    }

    public void setContentKey(String s)
    {
        b = s;
    }
}
