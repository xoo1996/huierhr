// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

import org.radf.plat.util.global.GlobalNames;

public class TitleTag extends TagSupport
{

    protected String title;

    public TitleTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        String s = (String)pageContext.findAttribute(GlobalNames.NAVIGATION);
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("<TABLE class=navigation>\n\t\t<TR>\n\t\t\t<TD>");
        if(s == null || "".equals(s))
            stringbuffer.append(getTitle());
        else
            stringbuffer.append(s).append("£º   ").append(getTitle());
        stringbuffer.append("</TD>\n\t\t</TR>\n</TABLE><br>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
        title = null;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
