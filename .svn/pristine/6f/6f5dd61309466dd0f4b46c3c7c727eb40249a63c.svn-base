// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

public class MaintainPanelTag extends TagSupport
{

    public MaintainPanelTag()
    {
    }

    public int doStartTag()
        throws JspException
    {
        StringBuffer stringbuffer = new StringBuffer("<table width='95%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td height='35' valign='middle'><table width='300' height='21' border='0' align='right' cellpadding='0' cellspacing='0'><tr><td width='12%' align='center' valign='bottom'  ><input type='submit' name='method'  class='button' value='查看' onclick=\"return editObj('chk')\"/></td><td width='1%' align='center' valign='bottom' id='eee'></td><td width='12%' align='center' valign='bottom'  ><input type='submit' name='method'  class='button' value='增加' /></td><td width='1%' align='center' valign='bottom' id='eee'></td><td width='12%' align='center' valign='bottom'  ><input type='submit' name='method'  class='button' value='修改' onclick=\"return editObj('chk')\"/></td><td width='1%'></td><td width='12%' align='center' valign='bottom' ><input type='submit' name='method'  class='button' value='删除' onclick=\"return delObj('chk')\"/></td><td width='2%'></td></tr></table></td></tr></table><table width='95%' height='1' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td height='1' class='BottomlineColor'></td></tr></table><table width='95%' border='0' align='center' cellpadding='0' cellspacing='0'><tr><td height='10'></td></tr></table>");
        stringbuffer.append("</form>");
        ResponseUtils.write(pageContext, stringbuffer.toString());
        return 1;
    }

    public void release()
    {
        super.release();
    }
}
