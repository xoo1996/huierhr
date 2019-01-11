// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import org.radf.manage.entity.Sc08;
import org.radf.plat.util.global.GlobalNames;

public class TagUtil
{


    public TagUtil()
    {
    }

    public static String getELPropertyValue(PageContext pagecontext, Tag tag, String s)
    {
        String s1 = "";
        try
        {
            s1 = (String)ExpressionEvaluatorManager.evaluate("value", s, java.lang.String.class, tag, pagecontext);
        }
        catch(JspException jspexception)
        {
            jspexception.printStackTrace();
            s1 = "";
        }
        if(s1 == null)
            s1 = "";
        return ResponseUtils.filter(s1);
    }

    public static String getPropertyValue(PageContext pagecontext, String s)
    {
        Object obj = null;
        try
        {
            obj = RequestUtils.lookup(pagecontext, "org.apache.struts.taglib.html.BEAN", s, null);
        }
        catch(JspException jspexception)
        {
            obj = "";
			System.out.println("没有该字段=="+s);
        }
        if(obj == null)
            obj = "";
        if("-2147483648".equals(obj)||"1900-01-01".equals(obj))
        {
            obj = "";
        }
        return ResponseUtils.filter(obj.toString());
    }

    public static String renderQuickSelect(TreeMap treemap, String s, String s1, String s2, String s3, String s4, String s5, String s6)
    {
        return a(treemap, s, s1, s2, s3, s4, s5, s6);
    }

    public static String renderSelect(TreeMap treemap, String s, String s1, String s2, String s3, String s4, String s5)
    {
        return a(treemap, s, s1, s2, s3, s4, s5, null);
    }

    private static String a(TreeMap treemap, String s, String s1, String s2, String s3, String s4, String s5, String s6)
    {
        StringBuffer stringbuffer = new StringBuffer("<select style='font-size:12px' name='");
        stringbuffer.append(s).append("'");
        a(s4, stringbuffer);
        stringbuffer.append(" class='select' label='");
        stringbuffer.append(s1).append("' ");
        if(s2 != null)
            stringbuffer.append(" required='").append(s2).append("'");
        if(s6 != null)
            stringbuffer.append(" ").append(s6);
        if(s5 != null)
            stringbuffer.append(" ").append(s5);
        stringbuffer.append(" >");
        if(s3 == null || "".equals(s3.trim()) || "null".equals(s3.trim()))
            stringbuffer.append("<option value='' selected> 请选择</option>");
        else
            stringbuffer.append("<option value='' > 请选择</option>");
        if(treemap != null)
        {
            Iterator iterator = treemap.keySet().iterator();
            Iterator iterator1 = treemap.values().iterator();
            for(; iterator.hasNext(); stringbuffer.append("</option>"))
            {
                String s7 = (String)iterator.next();
                String s8 = (String)iterator1.next();
                stringbuffer.append("<option value='");
                stringbuffer.append(s7);
                if(s7.equals(s3))
                    stringbuffer.append("' selected>");
                else
                    stringbuffer.append("' >");
                stringbuffer.append(s8);
            }

        }
        stringbuffer.append("</select>");
        return stringbuffer.toString();
    }

    public static String renderText(String s, String s1, String s2, String s3, String s4, int i)
    {
        StringBuffer stringbuffer = new StringBuffer("<input name='");
        stringbuffer.append(s).append("'");
        stringbuffer.append(" maxlength='").append(i).append("'");
        a(s4, stringbuffer);
        stringbuffer.append(" value='");
        stringbuffer.append(s3);
        stringbuffer.append("' required='");
        stringbuffer.append(s2);
        stringbuffer.append("' label='").append(s1).append("' ");
        stringbuffer.append(" type='text'  class='text'/> ");
        return stringbuffer.toString();
    }

    public static String renderFormatInput(String s, String s1, String s2, String s3, String s4, int i, String s5, boolean flag, 
            String s6)
    {
        StringBuffer stringbuffer = new StringBuffer("<textarea style='width:100%' class='mask' required='");
        stringbuffer.append(s2);
        stringbuffer.append("' label='").append(s1).append("' ");
        stringbuffer.append(" rows=1 cols=20 mask='");
        stringbuffer.append(s5);
        stringbuffer.append("' name='");
        stringbuffer.append(s).append("'");
        stringbuffer.append(" maxlength='").append(i).append("'");
        //添加 20070227 为身份证输入框添加ID属性 用来返回 lwd
        if (s4==null)
        {
            s4=s;
        
        }
        a(s4, stringbuffer);
        if(flag && s6 != null && s5.equalsIgnoreCase("date"))
        {
            stringbuffer.append(" onfocus=\"setday(this,document.all('");
            stringbuffer.append(s6);
            stringbuffer.append("').all('");
            stringbuffer.append(s);
            stringbuffer.append("'))\"");
        }
        stringbuffer.append(">");
        if(flag && s5.equalsIgnoreCase("money") && !"".equals(s3.trim()))
        {
            BigDecimal bigdecimal = (new BigDecimal(s3)).setScale(2, 4);
            s3 = bigdecimal.toString();
        }
        stringbuffer.append(s3);
        stringbuffer.append("</textarea>");
        return stringbuffer.toString();
    }

    private static void a(String s, StringBuffer stringbuffer)
    {
        if(s != null && !"".equals(s.trim()))
            stringbuffer.append(" id='").append(s).append("'");
    }

    public static boolean hasRight(PageContext pagecontext, String s)
    {
        List list = (List)pagecontext.findAttribute(GlobalNames.FUNCTION_LIST);
        if(s == null)
            return true;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
			Sc08 functiondto = (Sc08)iterator.next();
            if(s.equals(functiondto.getBsc016()))
                return true;
        }

        return false;
    }
}
