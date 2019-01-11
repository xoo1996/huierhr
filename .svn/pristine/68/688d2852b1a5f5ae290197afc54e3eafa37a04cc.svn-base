// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.ResponseUtils;

public class BodyTag extends TagSupport
{

    public BodyTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        StringBuffer stringbuffer = new StringBuffer("<body leftmargin=\"0\" topmargin=\"0\" onload=\"page_init() \">");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public int doEndTag()
        throws JspException
    {
        ResponseUtils.write(pageContext, "</body>");
        return 6;
    }

    public void release()
    {
        super.release();
    }
}
