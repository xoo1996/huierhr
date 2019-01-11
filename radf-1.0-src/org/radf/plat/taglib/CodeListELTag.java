// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.lbs.cp.taglib:
//            CodeListTag, TagUtil

public class CodeListELTag extends CodeListTag
{

    public CodeListELTag()
    {
    }

    protected String getSelectValue()
    {
        if(key == null || "".equals(key.trim()) || "null".equals(key.trim()))
            return TagUtil.getPropertyValue(pageContext, type);
        if(key != null && key.startsWith("${") && key.endsWith("}"))
            return TagUtil.getELPropertyValue(pageContext, this, key);
        else
            return key;
    }

    public int doStartTag()
        throws JspException
    {
        ResponseUtils.write(pageContext, super.renderSelect());
        return 1;
    }
}
