// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.net.MalformedURLException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

public class BaseTag extends TagSupport
{

    public BaseTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        String s = null;
        String s1 = null;
		String s2 = null;//20070918bylwd
        try
        {
            s = RequestUtils.computeURL(pageContext, "css", null, null, null, null, null, false);
            s1 = RequestUtils.computeURL(pageContext, "globals", null, null, null, null, null, false);
			s2 = RequestUtils.computeURL(pageContext, "tableshm", null, null, null, null, null, false);//20070918bylwd
        }
        catch(MalformedURLException malformedurlexception)
        {
            RequestUtils.saveException(pageContext, malformedurlexception);
            throw new JspException("URL计算异常");
        }
        StringBuffer stringbuffer = new StringBuffer("<head><title>惠耳企业信息管理系统</title></head>");
        stringbuffer.append("<meta http-equiv=\"cache-control\" content=\"no-cache\">");
        stringbuffer.append("<link href='");
        stringbuffer.append(s);
        stringbuffer.append("' rel='stylesheet' type='text/css'><script src='");
        stringbuffer.append(s1);
		stringbuffer.append("'></script>");
		////20070918bylwd
		stringbuffer.append("<script src='");
        stringbuffer.append(s2);
        stringbuffer.append("'></script>");
		////20070918bylwd
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
    }
}
