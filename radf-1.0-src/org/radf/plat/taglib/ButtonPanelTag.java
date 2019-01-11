// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.cp.b.d;
import java.io.IOException;
import java.util.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.lbs.cp.taglib:
//            WizardURL, Button, TagUtil

public class ButtonPanelTag extends TagSupport
{

    private String _fldif;
    private boolean _flddo;
    private String a;

    public String getPosition()
    {
        return a;
    }

    public void setPosition(String s)
    {
        a = s;
    }

    public boolean isWizard()
    {
        return _flddo;
    }

    public void setWizard(boolean flag)
    {
        _flddo = flag;
    }

    public ButtonPanelTag()
    {
        _flddo = false;
        a = "mid";
    }

    public int doStartTag()
        throws JspException
    {
        StringBuffer stringbuffer = new StringBuffer("<table class='tableInput' width='95%' height='35' border='0' align='center' cellspacing='0' ><tr><td><table width='140' height='31' border='0' align='right' cellpadding='0' cellspacing='0'><tr><td>");
        StringBuffer stringbuffer1 = new StringBuffer("<script language=\"javascript\">\nfunction shortcut()\n{");
        stringbuffer1.append("\nif(window.event.altKey){\n");
        stringbuffer1.append("\nif(window.event.keyCode ==").append(81).append("){");
        stringbuffer1.append("\n\tvar qf = eval(\"document.all.queryForm\");");
        stringbuffer1.append("\n\tif(qf && undefined != qf)");
        stringbuffer1.append("\n\t\tqf.submit();\n}");
        stringbuffer1.append("\nif(window.event.keyCode == ").append(82).append("){");
        stringbuffer1.append("\n\tvar qf = eval(\"document.all.queryForm\");");
        stringbuffer1.append("\n\tif(qf && undefined != qf)");
        stringbuffer1.append("\n\t\tresetForm(queryForm);\n}\n");
        Object obj = null;
        Object obj1 = null;
        Object obj2 = pageContext.findAttribute(getButtonMeta());
        if(isWizard())
        {
            WizardURL wizardurl = (WizardURL)pageContext.findAttribute(GlobalNames.WIZARD_URL);
            String s = "";
            try
            {
                if(wizardurl != null)
                {
                    d d1 = new d();
                    s = "location.href=\"" + new String(d1.a(wizardurl.previous())) + "\"";
                }
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            if("first".equalsIgnoreCase(getPosition()))
            {
                stringbuffer.append(renderButton("next", "下一步[N]", "nextWizard()"));
                stringbuffer1.append(generateShortCut("下一步[N]", "nextWizard()"));
                pageContext.removeAttribute(GlobalNames.WIZARD_URL, 3);
            } else
            if("last".equalsIgnoreCase(getPosition()))
            {
                stringbuffer.append(renderButton("previous", "上一步[P]", s));
                stringbuffer1.append(generateShortCut("上一步[P]", s));
            } else
            {
                stringbuffer.append(renderButton("previous", "上一步[P]", s));
                stringbuffer1.append(generateShortCut("上一步[P]", s));
                stringbuffer.append(renderButton("next", "下一步[N]", "nextWizard()"));
                stringbuffer1.append(generateShortCut("下一步[N]", "nextWizard()"));
            }
        }
        if(obj2 instanceof Map)
        {
            Map map = (Map)obj2;
            Iterator iterator = map.values().iterator();
            String s1;
            String s3;
            for(Iterator iterator1 = map.keySet().iterator(); iterator1.hasNext(); stringbuffer1.append(generateShortCut(s1, s3)))
            {
                s1 = (String)iterator1.next();
                s3 = (String)iterator.next();
                stringbuffer.append(renderButton("", s1, s3));
            }

        } else
        if(obj2 instanceof List)
        {
            List list = (List)obj2;
            for(int i = 0; i < list.size(); i++)
            {
                Button button = (Button)list.get(i);
                String s2 = button.getName();
                String s4 = button.getLabel();
                String s5 = button.getClick();
                String s6 = button.getFunctionid();
                if(TagUtil.hasRight(pageContext, s6))
                {
                    stringbuffer.append(renderButton(s2, s4, s5));
                    stringbuffer1.append(generateShortCut(s4, s5));
                }
            }

        }
        stringbuffer1.append("}\n}\ndocument.onkeydown=shortcut;\n</script>");
        stringbuffer.append("</td></tr></table></td></tr></table>");
        stringbuffer.append(stringbuffer1.toString());
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    protected String renderButton(String s, String s1, String s2)
    {
        StringBuffer stringbuffer = new StringBuffer("<input class='buttonGray' type='button' onclick='");
        stringbuffer.append(s2);
        stringbuffer.append("' name='");
        stringbuffer.append(s);
        stringbuffer.append("' value='");
        stringbuffer.append(s1);
        stringbuffer.append("'>&nbsp;&nbsp;&nbsp;&nbsp;");
        return stringbuffer.toString();
    }

    protected String generateShortCut(String s, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(s != null && -1 != s.indexOf("["))
        {
            stringbuffer.append("if(window.event.keyCode == ");
            stringbuffer.append(s.charAt(s.indexOf("[") + 1) + 0);
            stringbuffer.append(")");
            stringbuffer.append(s1);
            stringbuffer.append(";\n");
        }
        return stringbuffer.toString();
    }

    public void release()
    {
        super.release();
    }

    public String getButtonMeta()
    {
        return _fldif;
    }

    public void setButtonMeta(String s)
    {
        _fldif = s;
    }
}
