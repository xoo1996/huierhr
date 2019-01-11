// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.util.ResponseUtils;

public class ViewTag extends TagSupport
{

    protected String beanMeta;
    private String a;
    private String _fldif;

    public ViewTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        Object obj = pageContext.findAttribute(_fldif);
        StringBuffer stringbuffer = new StringBuffer("<table width='95%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td height='10'></td></tr></table><table width='95%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td><table width='100%' height='21' border='0' cellpadding='0' cellspacing='0'><tr><td width='10' class='TabImages1'>&nbsp;</td><td width='59' align='center' valign='bottom' class='TabImages'><font class='TabImages'>");
        stringbuffer.append(a);
        stringbuffer.append("</font></td><td width='10' class='TabImages2'>&nbsp;</td><td>&nbsp;</td></tr></table></td></tr></table>");
        stringbuffer.append("<table width='95%' border='0' align='center' cellpadding='3' cellspacing='0' class='TableStyle'>");
        Map map = (Map)pageContext.findAttribute(getBeanMeta());
        int i = 0;
        Iterator iterator = map.keySet().iterator();
        Iterator iterator1 = map.values().iterator();
        while(iterator.hasNext()) 
        {
            String s = (String)iterator1.next();
            String s1 = (String)iterator.next();
            Object obj1 = null;
            try
            {
                obj1 = PropertyUtils.getSimpleProperty(obj, s1);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                illegalaccessexception.printStackTrace();
                throw new JspException("访问方法出错(反射)！", illegalaccessexception);
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                invocationtargetexception.printStackTrace();
                throw new JspException("访问target出错(反射)！", invocationtargetexception);
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                nosuchmethodexception.printStackTrace();
                throw new JspException("访问的方法不存在(反射)！", nosuchmethodexception);
            }
            stringbuffer.append(a(s));
            stringbuffer.append(a(obj1));
            if(++i == 3)
            {
                stringbuffer.append("</tr>");
                i = 0;
            }
        }
        if(i != 3)
        {
            for(; i != 3; i++)
                stringbuffer.append("<td width='17%' height='0' align='right' class='TableTdStyle' nowrap><br></td><td width='16%' height='0' align='center' class='TableTdStyle'>&nbsp;&nbsp;</td>");

            stringbuffer.append("</tr>");
        }
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    private String a(Object obj)
    {
        StringBuffer stringbuffer = new StringBuffer("<td width='16%' height='0' align='right' class='TableTdStyle' nowrap>");
        stringbuffer.append(obj);
        stringbuffer.append("<br></td>");
        return stringbuffer.toString();
    }

    private String a(String s)
    {
        StringBuffer stringbuffer = new StringBuffer("<td width='17%' height='0' align='right' class='TableTdStyle' nowrap>");
        stringbuffer.append(s);
        stringbuffer.append("<br></td>");
        return stringbuffer.toString();
    }

    public void release()
    {
        super.release();
        a = null;
        _fldif = null;
        beanMeta = null;
    }

    public String getBeanMeta()
    {
        return beanMeta;
    }

    public void setBeanMeta(String s)
    {
        beanMeta = s;
    }

    public String getForm()
    {
        return _fldif;
    }

    public void setForm(String s)
    {
        _fldif = s;
    }

    public String getTopic()
    {
        return a;
    }

    public void setTopic(String s)
    {
        a = s;
    }
}
