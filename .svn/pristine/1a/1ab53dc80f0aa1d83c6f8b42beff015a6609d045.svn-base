// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.struts.util.ResponseUtils;

import org.radf.plat.commons.QueryInfo;
import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.cp.taglib:
//            ViewTableTag

public class TablePilotTag extends ViewTableTag
{

    public TablePilotTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        init();
        StringBuffer stringbuffer = new StringBuffer("");
        stringbuffer.append("</form></table></td></tr></table>");
        stringbuffer.append(renderPilot());
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
        action = null;
    }

    protected void init()
    {
        qi = (QueryInfo)pageContext.findAttribute(GlobalNames.QUERY_INFO);
        header = (List)pageContext.findAttribute(getHeaderMeta());
    }
}
