// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib.xtree;
import org.radf.plat.util.global.GlobalNames;
import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.lbs.commons.xtree:
//            a

public class TreeTag extends TagSupport
{

    private static Log log;
    private String _fldif;
    private String _flddo;


    public TreeTag()
    {
        _fldif = "";
        _flddo = null;
    }

    public int doStartTag()
        throws JspException
    {
        JspWriter jspwriter = pageContext.getOut();
        javax.servlet.ServletContext servletcontext = pageContext.getServletContext();
        try
        {
            a._mthif(jspwriter);
			jspwriter.print("var regionTree = new WebFXLoadTree(\"");
	         if(_fldif.equals("region"))  //判断JSP页面传递值并显示区域或工种或所属街道   by 李灵超 2006年4月26日
	             jspwriter.print("区域");
	         if(_fldif.equals("workType"))
	             jspwriter.print("工种");
             if(_fldif.equals("workTypeQuery"))
                 jspwriter.print("工种");             
	         if(_fldif.equals("ssjd"))
	             jspwriter.print("所属机构");
	         if(_fldif.equals("ssjdQuery"))
	             jspwriter.print("所属机构");
	         if(_fldif.equals("cylb"))
	             jspwriter.print("产业类别");
	         //jspwriter.print(_fldif.equals("region") ? "区域" : "工种");
	         jspwriter.print("\", \"");
	         jspwriter.print(GlobalNames.WEB_APP);
	         jspwriter.print("/treeServlet?key=root&dd="+new Date()+"&tree=");
	         if(_fldif.equals("region"))  //判断JSP页面传递值并向TreeServelt传值   by 李灵超 2006年4月26日
	             jspwriter.print("region");
	         if(_fldif.equals("workType"))
	             jspwriter.print("workType");
             if(_fldif.equals("workTypeQuery"))
                 jspwriter.print("workTypeQuery");
	         if(_fldif.equals("ssjd"))
	             jspwriter.print("ssjd");
	         if(_fldif.equals("ssjdQuery"))
	             jspwriter.print("ssjdQuery");
	         if(_fldif.equals("cylb"))
	             jspwriter.print("cylb");
	         //jspwriter.print(_fldif.equals("region") ? "region" : "workType");
	         if(_flddo != null && !"".equals(_flddo.trim()))
	             jspwriter.print("&condition=" + _flddo);
	         jspwriter.print("\");");
	         jspwriter.print("document.write(regionTree);");
            a.a(jspwriter);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return 0;
    }

    public String getProperty()
    {
        return _fldif;
    }

    public void setProperty(String s)
    {
        _fldif = s;
    }

    public String getCondition()
    {
        return _flddo;
    }

    public void setCondition(String s)
    {
        _flddo = s;
    }

    static 
    {
		log = LogFactory.getLog(org.radf.plat.taglib.xtree.TreeTag.class);
    }
}
