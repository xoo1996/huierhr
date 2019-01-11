// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.io.IOException;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

public class ColorTag extends TagSupport
{

    private String _fldif;
    private String a;

    public ColorTag()
    {
        _fldif = "";
        a = "#FF0000";
    }

    public int doStartTag()
        throws JspException
    {
        JspWriter jspwriter = pageContext.getOut();
        try
        {
            jspwriter.print("<font color='" + a + "'>");
            jspwriter.print(_fldif);
            jspwriter.print("</font>");
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return 0;
    }

    public String getColor()
    {
        return a;
    }

    public String getValue()
    {
        return _fldif;
    }

    public void setColor(String s)
    {
        a = s;
    }

    public void setValue(String s)
    {
        _fldif = s;
    }
}
