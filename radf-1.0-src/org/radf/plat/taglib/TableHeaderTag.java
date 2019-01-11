// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;



import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.apache.struts.util.ResponseUtils;

import com.lbs.cp.taglib.Formatter;
import org.radf.plat.commons.QueryInfo;
import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.cp.taglib:
//            ViewTableTag, Formatter

public class TableHeaderTag extends ViewTableTag
{

    public TableHeaderTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        init();
        StringBuffer stringbuffer = new StringBuffer("");
        stringbuffer.append(renderHeader());
        stringbuffer.append("<table  width='95%' border='0' align='center' cellpadding='0' cellspacing='0' class='tableList'><tr><td class=''>");
        stringbuffer.append(" <table id='resultset' width='100%' border='0' align='center' cellspacing='1' > ");
        stringbuffer.append("<tr align='center'>");
        if("checkbox".equalsIgnoreCase(mode))
        {
            stringbuffer.append("<td width='3%' height='0' class='tableHead' >");
            stringbuffer.append("<input type='checkbox' name='checkall' class='check' onclick=\"selectall(document.all('chk'))\">");
            stringbuffer.append("</td> ");
        } else
        {
            stringbuffer.append("<td width='3%' height='0' class='tableHead' >&nbsp;</td>");
        }
        for(Iterator iterator = header.iterator(); iterator.hasNext(); stringbuffer.append("</td>"))
        {
            Formatter formatter = (Formatter)iterator.next();
            stringbuffer.append("<td height='0' nowrap class='tableHead' >");
            stringbuffer.append(formatter.getLabel());
        }

        stringbuffer.append("</tr><form id='tableform' name='tableform' method='post'>");
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
