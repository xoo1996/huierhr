// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.radf.manage.entity.Sc08;
import org.radf.plat.cp.a.c;

// Referenced classes of package com.lbs.cp.tree:
//            TreeBuilder

public class SelectTree extends TreeBuilder
{

    private Collection _fldfor;
    private Collection _fldnew;
    private String _fldint;

    public SelectTree(Collection collection, Collection collection1)
    {
        _fldfor = null;
        _fldnew = null;
        _fldint = null;
        _fldfor = collection;
        _fldnew = collection1;
    }

    public void BuildTree()
    {
        StringBuffer stringbuffer = new StringBuffer("var tree = new Array;\n");
        int i = 0;
        for(Iterator iterator = _fldfor.iterator(); iterator.hasNext();)
        {
            String s = "";
			Sc08 functiondto = (Sc08)iterator.next();
            String s2 = functiondto.getBsc021();
            if(functiondto.getBsc019() == null)
            {
                String s3 = functiondto.getBsc016();
                String s1 = " tree[" + i + "]";
                stringbuffer.append(s1).append(" = new Array;\n");
                stringbuffer.append(s1).append("['caption'] = \"").append(functiondto.getBsc018()).append("\";\n");
                stringbuffer.append(s1).append("['functionid'] = \"").append(s3).append(";").append(s2).append("\";\n");
                stringbuffer.append(s1).append("['isOpen'] = true;\n");
                String s4 = c._mthfor(_fldnew, s3);
                if(s4 != null)
                    stringbuffer.append(s1).append("['isChecked'] = ").append(s4).append(";\n");
                stringbuffer.append(a(s1, s3));
                i++;
            }
        }

        _fldint = stringbuffer.toString();
    }

    private String a(String s, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        List list = c.a(_fldfor, s1);
        if(list != null)
        {
            int i = 0;
            String s2 = "";
            Object obj = null;
            s = s + "['children']";
            stringbuffer.append(s).append(" = new Array;\n");
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
				Sc08 functiondto = (Sc08)iterator.next();
                String s5 = functiondto.getBsc016();
                String s6 = functiondto.getBsc021();
                String s3 = s + "[" + i + "]";
                stringbuffer.append(s3).append(" = new Array;\n");
                stringbuffer.append(s3).append("['caption'] = \"").append(functiondto.getBsc018()).append("\";\n");
                stringbuffer.append(s3).append("['functionid'] = \"").append(s5).append(";").append(s6).append("\";\n");
                String s4 = c._mthfor(_fldnew, s5);
                if(s4 != null)
                    stringbuffer.append(s3).append("['isChecked'] = ").append(s4).append(";\n");
                stringbuffer.append(a(s3, s5));
                i++;
            }

        }
        return stringbuffer.toString();
    }

    public Object getTree()
    {
        return _fldint;
    }
}
