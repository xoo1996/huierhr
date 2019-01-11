// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.radf.login.dto.LoginDTO;

public class OperatorTag extends TagSupport
{

    private String a;

    public OperatorTag()
    {
    }

    public String getProperty()
    {
        return a;
    }

    public void setProperty(String s)
    {
        a = s;
    }

    public int doStartTag()
        throws JspException
    {
        try
        {
			LoginDTO currentuser = (LoginDTO)pageContext.findAttribute("LoginDTO");
           
            JspWriter jspwriter = pageContext.getOut();
            jspwriter.print("<label>");
            String s = currentuser.getBsc012();
            if(s == null)
                s = "&nbsp;";
            jspwriter.print(s);
            jspwriter.print("</label>");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return 0;
    }
}
