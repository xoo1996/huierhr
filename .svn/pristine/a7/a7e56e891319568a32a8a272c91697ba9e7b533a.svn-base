// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

import org.radf.plat.util.global.GlobalNames;

public class BizForwardTag extends TagSupport
{

    String bizForward;  

    public BizForwardTag()
    {
        bizForward = null;
    }

    public int doStartTag()
        throws JspException
    {
        Object obj = pageContext.findAttribute(GlobalNames.BIZ_FORWARD_MSG);
        bizForward = (String)pageContext.findAttribute(GlobalNames.BIZ_FORWARD);
        String as[] = (String[])null;
        if(obj == null)
            return 1;
        if(obj instanceof String[])
            as = (String[])obj;
        if(obj instanceof Exception[])
        {
            Exception aexception[] = (Exception[])obj;
            as = new String[aexception.length];
            for(int i = 0; i < aexception.length; i++)
                as[i] = aexception[i].getMessage();

        }
        if(as.length <= 0)
            return 1;
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("<script>\n");
        stringbuffer.append("if(confirm(\"");
        for(int j = 0; j < as.length; j++)
            stringbuffer.append(as[j]).append("\\n");

        stringbuffer.append("\")){window.location.href=");
        stringbuffer.append("\"").append(GlobalNames.WEB_APP).append(bizForward);
        stringbuffer.append("\";}");
        stringbuffer.append("</script>\n");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
    }
}
