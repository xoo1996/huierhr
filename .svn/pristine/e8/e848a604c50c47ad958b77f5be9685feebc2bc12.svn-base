// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 


package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.ResponseUtils;

public class TableTitleTag extends TagSupport
{

    private String a;

    public TableTitleTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("<TABLE class=tableTitle>\n\t\t<TR>\n\t\t\t<TD style=\"word-break:keep-all\">");
        stringbuffer.append(getTitle());
        stringbuffer.append("</TD>\n\t\t</TR>\n</TABLE>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
        a = null;
    }

    public String getTitle()
    {
        return a;
    }

    public void setTitle(String s)
    {
        a = s;
    }
}
