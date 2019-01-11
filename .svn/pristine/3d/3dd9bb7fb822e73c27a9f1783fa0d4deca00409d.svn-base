// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.ResponseUtils;

public class LayoutTag extends TagSupport
{

    public LayoutTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        ResponseUtils.write(pageContext, "<COLGROUP><COL width='10%'><COL width='23%'><COL width='10%'><COL width='23%'><COL width='10%'><COL width='23%'></COLGROUP>");
        return 1;
    }

    public void release()
    {
        super.release();
    }
}
