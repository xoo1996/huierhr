// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.tree;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.radf.plat.commons.DOMUtil;

// Referenced classes of package com.lbs.cp.tree:
//            BaseNode, Tree

public class Trees extends BaseNode
    implements Cloneable
{

    private String _fldif;
    private String a;

    public Trees(String s)
    {
        super(null);
        _fldif = null;
        a = "CommonTree";
        Document document = DOMUtil.createNewDocument();
        node = DOMUtil.createAndAppendElement(document, "Trees", null);
        _fldif = s;
        id = "";
    }

    public void setNode(Node node)
    {
        this.node = node;
    }

    public Tree newTree()
        throws Exception
    {
        if(_fldif == null)
        {
            throw new Exception(" you must define trees's id firstly");
        } else
        {
            Tree tree = new Tree(getNode().getOwnerDocument().createElement("Tree"));
            getNode().appendChild(tree.getNode());
            String s = getID() + handleID(getNode().getChildNodes().getLength());
            tree.addAttribute("id", s);
            return tree;
        }
    }

    public void setTreeType(String s)
    {
        a = s;
    }

    public StringBuffer print()
        throws Exception
    {
        if(_fldif == null)
            throw new Exception("trees's id excepted");
        StringBuffer stringbuffer = new StringBuffer();
        String s = DOMUtil.domToString(getNode());
        s = s.substring(s.indexOf(">") + 2);
        stringbuffer.append("<xml id =\"");
        stringbuffer.append(_fldif);
        stringbuffer.append("\" ondatasetcomplete=\"");
        if(a.equalsIgnoreCase("SelectTree"))
            stringbuffer.append("new SelectTree().init('");
        else
            stringbuffer.append("new CommonTree().init('");
        stringbuffer.append(_fldif);
        stringbuffer.append("');\"");
        stringbuffer.append(">\n");
        stringbuffer.append(s);
        stringbuffer.append("\n");
        stringbuffer.append("</xml>");
        return stringbuffer;
    }

    public Object clone()
    {
        Trees trees = null;
        try
        {
            trees = (Trees)super.clone();
        }
        catch(CloneNotSupportedException clonenotsupportedexception)
        {
            clonenotsupportedexception.printStackTrace();
        }
        return trees;
    }
}
