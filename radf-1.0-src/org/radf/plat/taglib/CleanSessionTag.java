// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;

import org.radf.plat.util.global.GlobalNames;

public class CleanSessionTag extends TagSupport
{

    private String _fldif;
    private String a;

    public CleanSessionTag()
    {
        _fldif = "";
        a = "false";
    }

    public int doStartTag()
        throws JspException
    {
        String s = (String)pageContext.findAttribute(GlobalNames.CAN_CLEAR_FORM);
        if("true".equalsIgnoreCase(s))
        {
            ModuleConfig moduleconfig = RequestUtils.getModuleConfig(pageContext);
            FormBeanConfig aformbeanconfig[] = moduleconfig.findFormBeanConfigs();
            for(int j = 0; j < aformbeanconfig.length; j++)
            {
                String s1 = aformbeanconfig[j].getName();
                pageContext.removeAttribute(s1, 3);
            }

        }
        if("true".equalsIgnoreCase(a))
        {
            String as[] = _fldif.split(",");
            for(int i = 0; i < as.length; i++)
                pageContext.removeAttribute(as[i], 3);

        }
        pageContext.removeAttribute("org.apache.struts.taglib.html.BEAN", 3);
        return 1;
    }

    public void release()
    {
        super.release();
        _fldif = null;
        a = null;
    }

    public String getForce()
    {
        return a;
    }

    public void setForce(String s)
    {
        a = s;
    }

    public String getNames()
    {
        return _fldif;
    }

    public void setNames(String s)
    {
        _fldif = s;
    }
}
