// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.ResponseUtils;

public class HeadTitleTag extends TagSupport
{

    private String a;

    public void setTopic(String s)
    {
        a = s;
    }

    public String getTopic()
    {
        return a;
    }

    public HeadTitleTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        StringBuffer stringbuffer = new StringBuffer("<table width='95%' border='0' align='center' cellpadding='0' cellspacing='0' ><tr><td height='10' ></td></tr></table><table width='95%' border='0' align='center' cellpadding='0' cellspacing='0' ><tr><td><table width='100%' height='21' border='0' cellpadding='0' cellspacing='0'><tr>e<td width='59' align='center' valign='bottom' class='TabImages' style=\"word-break:keep-all\"><font class='TabImages'>");
        stringbuffer.append(a);
        stringbuffer.append("</font></td><td width='10' class='TabImages2'>&nbsp;</td><td>&nbsp;</td></tr></table></td></tr></table>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
        a = null;
    }
}
