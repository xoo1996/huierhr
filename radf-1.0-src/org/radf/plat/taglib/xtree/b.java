// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib.xtree;

import org.radf.plat.cp.tree.Trees;
import org.w3c.dom.*;

public class b
{

    public b()
    {
    }

    public String a(Trees trees)
    {
        StringBuffer stringbuffer = new StringBuffer(2048);
        try
        {
            NodeList nodelist = trees.getNode().getChildNodes();
            stringbuffer.append("var tree = new WebFXTree('").append(((Element)trees.getNode()).getAttribute("label")).append("');");
            for(int i = 0; i < nodelist.getLength(); i++)
            {
                Element element = (Element)nodelist.item(i);
                String s = "item_" + i;
                stringbuffer.append("var ").append(s).append(" = new WebFXTreeItem('").append(element.getAttribute("label")).append("'");
                if(element.getAttribute("link") != null && "" != element.getAttribute("link").trim())
                    stringbuffer.append(", '").append(element.getAttribute("link")).append("'").append(" );").append(s).append(".target = 'mainFrame';");
                else
                    stringbuffer.append(");");
                stringbuffer.append(a(element, s));
                stringbuffer.append("tree.add(").append(s).append(");");
            }

            stringbuffer.append(" document.write(tree);");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return stringbuffer.toString();
    }

    private String a(Element element, String s)
    {
        StringBuffer stringbuffer = new StringBuffer(1024);
        NodeList nodelist = element.getChildNodes();
        Object obj = null;
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            Element element1 = (Element)nodelist.item(i);
            String s1 = s + i;
            stringbuffer.append("var ").append(s1).append(" = new WebFXTreeItem('").append(element1.getAttribute("label")).append("'");
            if(element1.getAttribute("link") != null && "" != element1.getAttribute("link").trim())
                stringbuffer.append(", '").append(element1.getAttribute("link")).append("'").append(" );").append(s1).append(".target = 'mainFrame';");
            else
                stringbuffer.append(");");
            stringbuffer.append(a(element1, s1));
            stringbuffer.append(s).append(".add(").append(s1).append(");");
        }

        return stringbuffer.toString();
    }
}
