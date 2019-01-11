// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import org.radf.plat.commons.QueryInfo;
import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.cp.taglib:
//            FormBean

public class OrderTableTag extends TagSupport
{

    protected String action;
    protected String headerMeta;
    private String a;

    public OrderTableTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        FormBean formbean = new FormBean(pageContext, action);
        formbean.initFormBean();
        QueryInfo queryinfo = (QueryInfo)pageContext.findAttribute(GlobalNames.QUERY_INFO);
        HttpServletResponse httpservletresponse = (HttpServletResponse)pageContext.getResponse();
        String s = httpservletresponse.encodeURL(RequestUtils.getActionMappingURL(action, pageContext));
        StringBuffer stringbuffer = new StringBuffer("<table width='95%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td height='10'></td></tr></table><table width='95%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td><table width='100%' height='21' border='0' cellpadding='0' cellspacing='0'><tr>");
        stringbuffer.append("<form action='");
        stringbuffer.append(s);
        stringbuffer.append("?method=commonQuery");
        if(queryinfo != null)
            stringbuffer.append(queryinfo.getOrderSubmitData());
        stringbuffer.append("' method='post'><td width='10' class='TabImages1'>&nbsp;</td><td width='59' align='center' valign='bottom' class='TabImages'><font class='TabImages'>");
        stringbuffer.append(a);
        stringbuffer.append("</font></td><td width='10' class='TabImages2'>&nbsp;</td><td>&nbsp;</td><td width='40%'></td><td width='90' align='left' ><select style='font-size:12px' name='orderBy'>");
        HashMap hashmap = (HashMap)pageContext.findAttribute(getHeaderMeta());
        Iterator iterator = hashmap.keySet().iterator();
        Iterator iterator1 = hashmap.values().iterator();
        for(; iterator.hasNext(); stringbuffer.append("</option>"))
        {
            String s1 = (String)iterator1.next();
            String s2 = (String)iterator.next();
            stringbuffer.append("<option value='");
            stringbuffer.append(s2);
            stringbuffer.append("'>");
            stringbuffer.append(s1);
        }

        stringbuffer.append("</select>");
        stringbuffer.append("</td><td width='50' ><select style='font-size:12px' name='order' ><option value='asc'>ÉýÐò</option><option value='desc'>½µÐò</option></select></td><td width='50' ><input type='submit' name='sort' value='ÅÅÐò' class='button' />");
        stringbuffer.append("</td></form></tr></table></td></tr></table>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
        action = null;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String s)
    {
        action = s;
    }

    public String getHeaderMeta()
    {
        return headerMeta;
    }

    public String getTopic()
    {
        return a;
    }

    public void setHeaderMeta(String s)
    {
        headerMeta = s;
    }

    public void setTopic(String s)
    {
        a = s;
    }
}
