// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import java.io.Serializable;

public class Editor
    implements Serializable
{

    private String _fldchar;
    private String _fldtry;
    private String _fldint;
    private String _fldbyte;
    private String _flddo;
    private String _fldfor;
    private boolean _fldnew;
    private String _fldcase;
    private String a;
    private int _fldif;

    public String getCode()
    {
        return _flddo;
    }

    public String getType()
    {
        return _fldbyte;
    }

    public void setLabel(String s)
    {
        _fldchar = s;
    }

    public void setCode(String s)
    {
        _flddo = s;
    }

    public void setType(String s)
    {
        _fldbyte = s;
    }

    public String getLabel()
    {
        return _fldchar;
    }

    public Editor(String s, String s1, String s2, String s3)
    {
        _fldtry = "false";
        _fldint = "false";
        _fldfor = "1";
        _fldnew = false;
        _fldcase = null;
        a = null;
        _fldif = 50;
        _fldbyte = s;//类型
        _flddo = s1;//字段名字
        _fldchar = s2;//标签名字
        _fldtry = s3;//是否为必输入项
    }

    public Editor(String s, String s1, String s2, String s3, String s4)
    {
        _fldtry = "false";
        _fldint = "false";
        _fldfor = "1";
        _fldnew = false;
        _fldcase = null;
        a = null;
        _fldif = 30;
        _fldbyte = s;//类型
        _flddo = s1;//字段名字
        _fldchar = s2;//标签名字
        _fldtry = s3;//是否为必输入项
        _fldfor = s4;//宽度
    }

    public String getRequired()
    {
        return _fldtry;
    }

    public void setRequired(String s)
    {
        _fldtry = s;
    }

    public Editor(String s, String s1, String s2)
    {
        _fldtry = "false";
        _fldint = "false";
        _fldfor = "1";
        _fldnew = false;
        _fldcase = null;
        a = null;
        _fldif = 30;
        _fldbyte = s;
        _flddo = s1;
        _fldchar = s2;
    }

    public Editor(String s, String s1, String s2, boolean flag)
    {
        _fldtry = "false";
        _fldint = "false";
        _fldfor = "1";
        _fldnew = true;
        _fldcase = null;
        a = null;
        _fldif = 30;
        _fldbyte = s;
        _flddo = s1;
        _fldchar = s2;
        _fldnew = flag;//是否可输入，true表示输入框为灰色不能输入20070918添加注释bylwd
    }

    public String getWidth()
    {
        return _fldfor;
    }

    public String getIsHidden()
    {
        return _fldint;
    }

    public boolean isDisable()
    {
        return _fldnew;
    }

    public String getDefaultValue()
    {
        return _fldcase;
    }

    public String getDataMeta()
    {
        return a;
    }

    public void setWidth(String s)
    {
        _fldfor = s;
    }

    public void setIsHidden(String s)
    {
        _fldint = s;
    }

    public void setDisable(boolean flag)
    {
        _fldnew = flag;
    }

    public void setDefaultValue(String s)
    {
        _fldcase = s;
    }

    public void setDataMeta(String s)
    {
        a = s;
    }

    public int getMaxlength()
    {
        return _fldif;
    }

    public void setMaxlength(int i)
    {
        _fldif = i;
    }
}
