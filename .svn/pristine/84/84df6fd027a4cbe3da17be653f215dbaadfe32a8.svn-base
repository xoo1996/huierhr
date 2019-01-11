// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.ResponseUtils;

public class NewLineTag extends TagSupport
{

    private String a;

    public String getHeight()
    {
        return a;
    }

    public void setHeight(String s)
    {
        a = s;
    }

    public NewLineTag()
    {
        a = "5";
    }

    public int doStartTag()
        throws JspException
    {
        StringBuffer stringbuffer = new StringBuffer("<table width='95%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td height='");
        stringbuffer.append(getHeight());
        stringbuffer.append("'></td></tr></table>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
    }
}
