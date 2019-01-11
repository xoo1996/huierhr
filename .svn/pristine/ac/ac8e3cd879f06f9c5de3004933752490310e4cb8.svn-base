// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.lbs.cp.taglib:
//            CodeListTag, TagUtil

public class QuickSelectTag extends CodeListTag
{

    public QuickSelectTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        java.util.TreeMap treemap = getDataSource();
        StringBuffer stringbuffer = new StringBuffer(" JSObjName='QuickSelect' ");
        stringbuffer.append(" onkeydown='eapObjsMgr.getEAPObj(this).getBaseObj().enterToTab();eapObjsMgr.getEAPObj(this).dealInputBackSpace()'");
        stringbuffer.append(" onkeypress='eapObjsMgr.getEAPObj(this).dealKeypress()' onblur='eapObjsMgr.getEAPObj(this).dealInputOnblur()' ");
        getSelectValue();
        ResponseUtils.write(pageContext, TagUtil.renderQuickSelect(treemap, type, label, required, key, type + "quickselect", prepareEventHandlers(), stringbuffer.toString()));
        return 1;
    }

    public void release()
    {
        super.release();
    }
}
