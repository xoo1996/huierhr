// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

import org.radf.plat.util.global.GlobalNames;

public class NavigationTag extends TagSupport
{

    public NavigationTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        Object obj = null;
        String s = (String)pageContext.findAttribute(GlobalNames.NAVIGATION);
        StringBuffer stringbuffer = new StringBuffer("<TABLE class=\"navigation\">\n\t\t<TR>\n\t\t\t<TD>");
        stringbuffer.append(s);
        stringbuffer.append("</TD>\n\t\t</TR>\n</TABLE><br>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
    }
}
