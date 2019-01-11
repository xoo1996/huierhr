// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.lbs.cp.taglib:
//            StringInputTag, TagUtil

public class StringInputELTag extends StringInputTag
{

    public String getStringInputValue()
    {
        if(value != null)
            if(value.startsWith("${"))
                return TagUtil.getELPropertyValue(pageContext, this, value);
            else
                return ResponseUtils.filter(value);
        if(redisplay || !"password".equals(type))
            return TagUtil.getPropertyValue(pageContext, property);
        else
            return "";
    }

    public int doStartTag()
        throws JspException
    {
        ResponseUtils.write(pageContext, renderStringInput());
        return 1;
    }

    public StringInputELTag()
    {
    }

    public void release()
    {
        super.release();
    }
}
