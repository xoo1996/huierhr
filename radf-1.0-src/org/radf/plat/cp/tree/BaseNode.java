// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.tree;

import java.io.Serializable;
import org.w3c.dom.*;

public class BaseNode
    implements Serializable
{

    protected Node node;
    protected String id;

    public BaseNode(Node node1)
    {
        id = null;
        node = node1;
    }

    public Node getNode()
    {
        return node;
    }

    protected Node addAttribute(String s, String s1)
    {
        if(s1 == null)
            s1 = "";
        if(s.equalsIgnoreCase("id"))
            id = s1;
        Attr attr = node.getOwnerDocument().createAttribute(s);
        attr.setNodeValue(s1);
        return node.getAttributes().setNamedItem(attr);
    }

    public void addLabel(String s)
    {
        addAttribute("label", s);
    }

    public void addTitle(String s)
    {
        addAttribute("title", s);
    }

    public void addChecked(boolean flag)
    {
        addAttribute("isChecked", String.valueOf(flag));
    }

    public String getID()
    {
        return id;
    }

    public String handleID(int i)
    {
        if(i > 9)
            return String.valueOf(i);
        else
            return "0" + i;
    }
}
