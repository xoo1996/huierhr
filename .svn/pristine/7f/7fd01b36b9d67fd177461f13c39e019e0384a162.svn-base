// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.util.TreeMap;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.apache.struts.taglib.html.SelectTag;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.lbs.cp.taglib:
//            TagUtil

public class CodeListTag extends SelectTag
{

    protected String required;
    protected String type;
    protected String key;
    protected boolean isSelect;
    protected String redisplay;
    protected String label;
    protected String isValue;
    protected String dataMeta;

    public CodeListTag()
    {
        redisplay = "false";
        isValue = "false";
        dataMeta = null;
        required = null;
        type = null;
        key = null;
        isSelect = true;
    }

    public int doStartTag()
        throws JspException
    {
        ResponseUtils.write(pageContext, renderSelect());
        return 1;
    }

    protected String renderSelect()
        throws JspException
    {
        TreeMap treemap = getDataSource();
        String s = getSelectValue();
        if(treemap != null)
        {
            if(isSelect)
                return TagUtil.renderSelect(treemap, type, label, required, s, type, prepareEventHandlers());
            StringBuffer stringbuffer = new StringBuffer();
            String s1 = "";
            stringbuffer.append("<input type='hidden' value=\"");
            stringbuffer.append(s);
            stringbuffer.append("\" name='");
            stringbuffer.append(type);
            stringbuffer.append("'>");
            if(treemap.get(s) != null)
                s1 = (String)treemap.get(s);
            else
                s1 = "&nbsp;";
            if(!"true".equalsIgnoreCase(isValue))
                s1 = "<label>" + s1 + "</label>";
            stringbuffer.append(s1);
            return stringbuffer.toString();
        } else
        {
            return "";
        }
    }

    protected String getSelectValue()
    {
        if(key == null || "".equals(key.trim()) || "null".equals(key.trim()))
            return TagUtil.getPropertyValue(pageContext, type);
        else
            return key;
    }

    protected TreeMap getDataSource()
    {
        TreeMap treemap = null;
        if(getDataMeta() != null)
            treemap = (TreeMap)pageContext.findAttribute(getDataMeta());
        if(treemap == null)
            treemap = (TreeMap)pageContext.findAttribute(type.toUpperCase());
        return treemap;
    }

    public void release()
    {
        super.release();
        required = null;
        type = null;
        key = null;
    }

    public String getKey()
    {
        return key;
    }

    public String getRequired()
    {
        return required;
    }

    public String getType()
    {
        return type;
    }

    public void setKey(String s)
    {
        key = s;
    }

    public void setRequired(String s)
    {
        required = s;
    }

    public void setType(String s)
    {
        type = s;
    }

    public boolean isIsSelect()
    {
        return isSelect;
    }

    public String getLabel()
    {
        return label;
    }

    public String getRedisplay()
    {
        return redisplay;
    }

    public String getIsValue()
    {
        return isValue;
    }

    public String getDataMeta()
    {
        return dataMeta;
    }

    public void setIsSelect(boolean flag)
    {
        isSelect = flag;
    }

    public void setLabel(String s)
    {
        label = s;
    }

    public void setRedisplay(String s)
    {
        redisplay = s;
    }

    public void setIsValue(String s)
    {
        isValue = s;
    }

    public void setDataMeta(String s)
    {
        dataMeta = s;
    }
}
