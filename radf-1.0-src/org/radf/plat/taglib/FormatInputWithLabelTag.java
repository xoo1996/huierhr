// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;
import javax.servlet.jsp.JspException;

// Referenced classes of package com.lbs.cp.taglib:
//            FormatInputTag

public class FormatInputWithLabelTag extends FormatInputTag
{

    protected String colspan;

    public FormatInputWithLabelTag()
    {
        colspan = "1";
    }

    protected String renderTextareaElement()
        throws JspException
    {
        super.getFormatInputValue();
        if("true".equalsIgnoreCase(disable))
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("<td>");
            if("true".equalsIgnoreCase(required))
                stringbuffer.append("<font color='#FF0000'>*</font>");
            stringbuffer.append(label);
            stringbuffer.append("</td><td colspan=\"");
            stringbuffer.append(colspan);
            stringbuffer.append("\">");
            stringbuffer.append(super.renderDisableProperties());
            return stringbuffer.toString();
        }
        StringBuffer stringbuffer1 = new StringBuffer("<td>");
        if("true".equalsIgnoreCase(required))
            stringbuffer1.append("<font color='#FF0000'>*</font>");
        stringbuffer1.append(label);
        stringbuffer1.append("</td><td colspan=\"");
        stringbuffer1.append(colspan);
        stringbuffer1.append("\">");
        stringbuffer1.append(super.renderProperties());
		
        return stringbuffer1.toString();
    }

    public String getColspan()
    {
        return colspan;
    }

    public void setColspan(String s)
    {
        colspan = s;
    }
}
