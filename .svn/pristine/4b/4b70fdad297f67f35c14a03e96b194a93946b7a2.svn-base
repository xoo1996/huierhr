// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.ResponseUtils;

public class BottomTag extends TagSupport
{

    public BottomTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
		 StringBuffer stringbuffer = new StringBuffer("<hr color='#999999' size='1' align='center' width='90%'>");
	        stringbuffer.append("<TABLE width='100%'><TR><TD align='center'>");
	        stringbuffer.append("中国浙江杭州2015-2016");  
	        stringbuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎批评指正！");
	        stringbuffer.append("</TD></TR><TR><TD>&nbsp;</TD></TR></TABLE>");
	        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
    }
}
