// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.lbs.cp.taglib:
//            StringInputELTag

public class StringInputWithLabelELTag extends StringInputELTag
{

    protected String colspan;

    public String getColspan()
    {
        return colspan;
    }

    public void setColspan(String s)
    {
        colspan = s;
    }

    public int doStartTag()
        throws JspException
    {
        StringBuffer stringbuffer = new StringBuffer("<td>");
        if("true".equalsIgnoreCase(required))
            stringbuffer.append("<font color='#FF0000'>*</font>");
        stringbuffer.append(label);
        stringbuffer.append("</td><td colspan=\"");
        stringbuffer.append(colspan);
        stringbuffer.append("\">");
        stringbuffer.append(super.renderStringInput());
        stringbuffer.append("</td>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public StringInputWithLabelELTag()
    {
        colspan = "1";
    }

    public void release()
    {
        super.release();
    }
}
