// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.lbs.cp.taglib:
//            Editor, TagUtil, FormBean

public class QueryEditorTag extends TagSupport
{

    private String _fldif;
    private String _fldfor;
    private String a;
    private String _flddo;
    private String _fldint;

    public void setTopic(String s)
    {
        _fldif = s;
    }

    public void setEditorMeta(String s)
    {
        a = s;
    }

    public void setAction(String s)
    {
        _fldfor = s;
    }

    public void setHiddenMeta(String s)
    {
        _fldint = s;
    }

    public String getTopic()
    {
        return _fldif;
    }

    public String getEditorMeta()
    {
        return a;
    }

    public String getAction()
    {
        return _fldfor;
    }

    public String getHiddenMeta()
    {
        return _fldint;
    }

    public QueryEditorTag()
    {
        _flddo = "";
        _fldint = "";
    }

    private String a(Editor editor)
        throws JspException
    {
        StringBuffer stringbuffer = new StringBuffer("<td  width='12%' height='0' align='right' nowrap>");
        if(editor.getRequired().equalsIgnoreCase("true"))
            stringbuffer.append("<font color='#FF0000'>*</font>");
        stringbuffer.append(editor.getLabel());
        stringbuffer.append("<br></td><td width='21%' height='0' align='center' colspan='");
        stringbuffer.append(Integer.parseInt(editor.getWidth()) * 2 - 1);
        stringbuffer.append("'>");
        if(editor.getDataMeta() == null)
        {
            if("aaa020".equalsIgnoreCase(editor.getCode()))
            {
                String s = TagUtil.getPropertyValue(pageContext, "aaa020");
                String s2 = TagUtil.getPropertyValue(pageContext, "aaa021");
                stringbuffer.append("<input type='text' name='aaa021' style='cursor: hand' class='text' required='");
                stringbuffer.append(editor.getRequired());
                stringbuffer.append("' label='");
                stringbuffer.append(editor.getLabel());
                stringbuffer.append("' value='");
                stringbuffer.append(s2);
                stringbuffer.append("' onclick=\"setRegionTree(this,eval(document.all.queryForm.aaa021),");
                stringbuffer.append("eval(document.all.queryForm.all.aaa020))\" readonly='true'/>");
                stringbuffer.append("<input type='hidden' name='aaa020' value='");
                stringbuffer.append(s);
                stringbuffer.append("'/></td>");
                return stringbuffer.toString();
            }
            if("aca111".equalsIgnoreCase(editor.getCode()))
            {
                String s1 = TagUtil.getPropertyValue(pageContext, "aca111");
                String s3 = TagUtil.getPropertyValue(pageContext, "aca112");
                stringbuffer.append("<input type='text' name='aca112' style='cursor: hand' class='text' required='");
                stringbuffer.append(editor.getRequired());
                stringbuffer.append("' label='");
                stringbuffer.append(editor.getLabel());
                stringbuffer.append("' value='");
                stringbuffer.append(s3);
                stringbuffer.append("' onclick=\"setWorkTypeTree(this,eval(document.all.queryForm.aca112),");
                stringbuffer.append("eval(document.all.queryForm.aca111))\" readonly='true'/>");
                stringbuffer.append("<input type='hidden' name='aca111' value='");
                stringbuffer.append(s1);
                stringbuffer.append("'/></td>");
                return stringbuffer.toString();
            }
			if("bcc9b7".equalsIgnoreCase(editor.getCode())){
                String s1 = TagUtil.getPropertyValue(pageContext, "bcc9b7");
                String s3 = TagUtil.getPropertyValue(pageContext, "bcc9b4");
                stringbuffer.append("<input type='text' name='bcc9b4' style='cursor: hand' class='text' required='");
                stringbuffer.append(editor.getRequired());
                stringbuffer.append("' label='");
                stringbuffer.append(editor.getLabel());
                stringbuffer.append("' value='");
                stringbuffer.append(s3);
                stringbuffer.append("' onclick=\"setWorkTypeTree(this,eval(document.all.queryForm.bcc9b4),");
                stringbuffer.append("eval(document.all.queryForm.bcc9b7))\" readonly='true'/>");
                stringbuffer.append("<input type='hidden' name='bcc9b7' value='");
                stringbuffer.append(s1);
                stringbuffer.append("'/></td>");
                return stringbuffer.toString();
			}
			if("bab055".equalsIgnoreCase(editor.getCode())){
                String s1 = TagUtil.getPropertyValue(pageContext, "bab055");
                String s3 = TagUtil.getPropertyValue(pageContext, "aab054");
                stringbuffer.append("<input type='text' name='aab054' style='cursor: hand' class='text' required='");
                stringbuffer.append(editor.getRequired());
                stringbuffer.append("' label='");
                stringbuffer.append(editor.getLabel());
                stringbuffer.append("' value='");
                stringbuffer.append(s3);
                stringbuffer.append("' onclick=\"setCYLBTree(this,eval(document.all.queryForm.aab054),");
                stringbuffer.append("eval(document.all.queryForm.bab055))\" readonly='true'/>");
                stringbuffer.append("<input type='hidden' name='bab055' value='");
                stringbuffer.append(s1);
                stringbuffer.append("'/></td>");
                return stringbuffer.toString();
			}
            //添加BY李灵超 0313 为查询条件中能显示树形结构街区代码
            if("cce001".equalsIgnoreCase(editor.getCode()))
            {
                java.lang.String s = TagUtil.getPropertyValue(pageContext, "cce001");
                java.lang.String s2 = TagUtil.getPropertyValue(pageContext, "ssjqnm");
                stringbuffer.append("<input type='text' name='ssjqnm' style='cursor: hand' class='text' required='");
                stringbuffer.append(editor.getRequired());
                stringbuffer.append("' label='");
                stringbuffer.append(editor.getLabel());
                stringbuffer.append("' value='");
                stringbuffer.append(s2);
                stringbuffer.append("' onclick=\"setSSJDTree(this,eval(document.all.queryForm.ssjqnm),");
                stringbuffer.append("eval(document.all.queryForm.all.cce001))\" readonly='true'/>");
                stringbuffer.append("<input type='hidden' name='cce001' value='");
                stringbuffer.append(s);
                stringbuffer.append("'/></td>");
                return stringbuffer.toString();
            }
        }
        stringbuffer.append(_mthif(editor));
        stringbuffer.append("</td>");
        return stringbuffer.toString();
    }

    private String _mthif(Editor editor)
        throws JspException
    {
        String s = editor.getType();
        String s1 = editor.getCode();
        String s2 = editor.getRequired();
        String s3 = editor.getLabel();
        String s4 = TagUtil.getPropertyValue(pageContext, s1);
        int i = editor.getMaxlength();
        TreeMap treemap = null;
        if(editor.getDataMeta() != null)
            treemap = (TreeMap)pageContext.findAttribute(editor.getDataMeta());
        else
            treemap = (TreeMap)pageContext.findAttribute(s1.toUpperCase());
        if(editor.isDisable())
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("<input type = 'hidden'  name='");
            stringbuffer.append(s1);
            stringbuffer.append("' value=\"");
            stringbuffer.append(s4);
            stringbuffer.append("\" />");
            stringbuffer.append("<label>");
            if(treemap != null)
                stringbuffer.append(treemap.get(s4));
            else
                stringbuffer.append(s4);
            stringbuffer.append("</label>");
            return stringbuffer.toString();
        }
        if(treemap != null)
            return TagUtil.renderSelect(treemap, s1, s3, s2, s4, null, "");
        if(s.equals("text"))
            return TagUtil.renderText(s1, s3, s2, s4, null, i);
        else
            return TagUtil.renderFormatInput(s1, s3, s2, s4, null, i, s, true, _flddo);
    }

    public int doStartTag()
        throws JspException
    {
        FormBean formbean = new FormBean(pageContext, _fldfor);
        formbean.initFormBean();
        _flddo = formbean.getFormBeanName();
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("<TABLE class='tableTitle'>\n\t<TR>\n\t\t<TD>");
        stringbuffer.append(_fldif);
        stringbuffer.append("</TD>\n\t\t</TR>\n</TABLE>");
        stringbuffer.append("<TABLE class='tableInput'>");
        HttpServletResponse httpservletresponse = (HttpServletResponse)pageContext.getResponse();
        String s = httpservletresponse.encodeURL(RequestUtils.getActionMappingURL(_fldfor, pageContext));
        stringbuffer.append("<form name ='");
        stringbuffer.append(_flddo);
        stringbuffer.append("' action='");
        stringbuffer.append(s);
        stringbuffer.append("' method='post' id='queryForm' onsubmit=\"return checkValue(this)\">");
        Collection collection = (Collection)pageContext.findAttribute(getEditorMeta());
        int i = 0;
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            if(i == 0)
                stringbuffer.append("<tr >");
            Editor editor = (Editor)iterator.next();
            stringbuffer.append(a(editor));
            i += Integer.parseInt(editor.getWidth());
            if(i == 3)
            {
                stringbuffer.append("</tr>");
                i = 0;
            }
        }

        if(i != 0)
        {
            for(; i != 3; i++)
                stringbuffer.append("<td  height='0' align='right' nowrap><br></td><td width='16%' height='0' align='center' >&nbsp;&nbsp;</td>");

            stringbuffer.append("</tr>");
        }
        stringbuffer.append("\n<tr><td class=tdRight colSpan=8 >\n");
        if(!"".equals(_fldint.trim()))
        {
            Map map = (Map)pageContext.findAttribute(getHiddenMeta());
            for(Iterator iterator1 = map.keySet().iterator(); iterator1.hasNext(); stringbuffer.append("'/>"))
            {
                String s1 = (String)iterator1.next();
                String s2 = (String)map.get(s1);
                s2 = s2 != null ? s2 : "";
                if("".equals(s2))
                    s2 = TagUtil.getPropertyValue(pageContext, s1);
                stringbuffer.append("\n<input type='hidden' value=\"");
                stringbuffer.append(s2);
                stringbuffer.append("\" name='");
                stringbuffer.append(s1);
            }

        }
        stringbuffer.append("<input type='submit' name='method' value='查询[Q]' class='buttonGray' />&nbsp;&nbsp;&nbsp;&nbsp;\n<input type='button' name='method' value='重置[R]' class='buttonGray' onclick='resetForm(queryForm)'/>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr></form></table>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
        _fldif = null;
    }
}
