// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.commons;


import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;
import org.bouncycastle.util.encoders.Base64;

import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.commons.actions:
//            ActionUtil

public class PageQueryInfo implements Serializable{

    private static final long _flddo = 0x2d37383130313935L;
    private int _fldtry;
    private int _fldfor;
    private int _fldbyte;
    private int _fldnew;
    private String _fldif;
    private boolean _fldint;
    private String a[];
    private String _fldcase[];

    public PageQueryInfo()
    {
        _fldtry = -1;
        _fldfor = -1;
        _fldbyte = 0;
        _fldnew = GlobalNames.PAGE_SIZE;
        _fldint = false;
    }

    public int getCurPageNo()
    {
        return _fldbyte;
    }

    public void setCurPageNo(int i)
    {
        _fldbyte = i;
    }

    public int getPageCount()
    {
        return _fldfor;
    }

    public void setPageCount(int i)
    {
        _fldfor = i;
    }

    public int getPageSize()
    {
        return _fldnew;
    }

    public void setPageSize(int i)
    {
        _fldnew = i;
    }

    public int getRowCount()
    {
        return _fldtry;
    }

    public void setRowCount(int i)
    {
        _fldtry = i;
    }

    public String getPageQueryAction()
    {
        return _fldif;
    }

    public void setPageQueryAction(String s)
    {
        _fldif = s;
    }

    public boolean isRetriveAllDataFlag()
    {
        return _fldint;
    }

    public void setRetriveAllDataFlag(boolean flag)
    {
        _fldint = flag;
    }

    public String serial2text()
    {
        return new String(Base64.encode(SerializationUtils.serialize(this)));
    }

    public static PageQueryInfo deserialFromText(String s)
    {
        return (PageQueryInfo)SerializationUtils.deserialize(Base64.decode(s.getBytes()));
    }

    public String[] getQueryFields()
    {
        return a;
    }

    public void setQueryFields(String as[])
    {
        a = as;
    }

    public String[] getRudeFields()
    {
        return _fldcase;
    }

    public void setRudeFields(String as[])
    {
        _fldcase = as;
    }

    public int getFieldIndex(String s)
    {
       return getIndex(a, s);
    }

    public StringBuffer getStateUrl()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("&totalNum=");
        stringbuffer.append(getRowCount());
        stringbuffer.append("&totalPageNo=");
        stringbuffer.append(getPageCount());
        stringbuffer.append("&curPageNo=");
        stringbuffer.append(getCurPageNo());
        stringbuffer.append("&pageSize=");
        stringbuffer.append(getPageSize());
        return stringbuffer;
    }
	 public static int getIndex(String as[], String s)
	    {
	        if(as != null)
	        {
	            for(int i = 0; i < as.length; i++)
	                if(s.equalsIgnoreCase(as[i]))
	                    return i;

	            return -1;
	        } else
	        {
	            return -1;
	        }
	    }
}
