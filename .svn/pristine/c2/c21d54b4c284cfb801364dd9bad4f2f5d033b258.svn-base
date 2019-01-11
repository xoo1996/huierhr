package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

public class PlatErrorsTag extends TagSupport{
	private String a;

    public PlatErrorsTag()
    {
        a = org.radf.plat.util.global.GlobalErrorMsg.EXCEPTIONMESSAGE;
    }

    public String getName()
    {
        return a;
    }

    public void setName(String s)
    {
        a = s;
    }

    public int doStartTag()
        throws JspException
    {
        Object obj = pageContext.findAttribute(a);
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
        if("true".equalsIgnoreCase((String)pageContext.findAttribute("alertmsgoff")))
        {
            pageContext.removeAttribute("alertmsgoff", 3);
            return 1;
        }
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("<script>\n");
        stringbuffer.append("alert(\"");
        for(int j = 0; j < as.length; j++)
            stringbuffer.append(as[j].replaceAll("\n","")).append("\\n");

        stringbuffer.append("\");");
        stringbuffer.append("</script>\n");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        pageContext.removeAttribute(a, 3);
        return 1;
    }

    public void release()
    {
        super.release();
    }

}
