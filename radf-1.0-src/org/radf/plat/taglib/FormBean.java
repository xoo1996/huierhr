// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import org.apache.struts.action.*;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;

public class FormBean
{

    private PageContext a;
    private String _flddo;
    private String _fldif;
    protected static MessageResources messages = MessageResources.getMessageResources("org.apache.struts.taglib.html.LocalStrings");

    public String getFormBeanName()
    {
        return _fldif;
    }

    FormBean(PageContext pagecontext, String s)
    {
        a = null;
        _flddo = null;
        _fldif = null;
        a = pagecontext;
        _flddo = s;
    }

    protected void initFormBean()
        throws JspException
    {
        ModuleConfig moduleconfig = RequestUtils.getModuleConfig(a);
        if(moduleconfig == null)
        {
            JspException jspexception = new JspException(messages.getMessage("formTag.collections"));
            a.setAttribute("org.apache.struts.action.EXCEPTION", jspexception, 2);
            throw jspexception;
        }
        ActionServlet actionservlet = (ActionServlet)a.getServletContext().getAttribute("org.apache.struts.action.ACTION_SERVLET");
        String s = RequestUtils.getActionMappingName(_flddo);
        ActionMapping actionmapping = (ActionMapping)moduleconfig.findActionConfig(s);
        if(actionmapping == null)
        {
            JspException jspexception1 = new JspException(messages.getMessage("formTag.mapping", s));
            a.setAttribute("org.apache.struts.action.EXCEPTION", jspexception1, 2);
            throw jspexception1;
        }
        FormBeanConfig formbeanconfig = moduleconfig.findFormBeanConfig(actionmapping.getName());
        if(formbeanconfig == null)
        {
            JspException jspexception2 = new JspException(messages.getMessage("formTag.formBean", actionmapping.getName()));
            a.setAttribute("org.apache.struts.action.EXCEPTION", jspexception2, 2);
            throw jspexception2;
        }
        String s1 = actionmapping.getAttribute();
        _fldif = s1;
        String s2 = actionmapping.getScope();
        String s3 = formbeanconfig.getType();
        byte byte0 = 3;
        if("request".equalsIgnoreCase(s2))
            byte0 = 2;
        Object obj = a.getAttribute(s1, byte0);
        if(obj == null)
        {
            obj = RequestUtils.createActionForm((HttpServletRequest)a.getRequest(), actionmapping, moduleconfig, actionservlet);
            if(obj instanceof ActionForm)
                ((ActionForm)obj).reset(actionmapping, (HttpServletRequest)a.getRequest());
            if(obj == null)
                throw new JspException(messages.getMessage("formTag.create", s3));
            a.setAttribute(s1, obj, byte0);
        }
        a.setAttribute("org.apache.struts.taglib.html.BEAN", obj, 2);
    }

}
