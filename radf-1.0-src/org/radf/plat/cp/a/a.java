// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.a;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.radf.manage.entity.Sc08;
import org.radf.plat.commons.DOMUtil;
import org.radf.plat.cp.tree.CommonTree;
import org.radf.plat.cp.tree.DeptTree;
import org.radf.plat.cp.tree.Trees;
import org.radf.plat.taglib.xtree.b;
import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.cp.a:
//            c

public class a
{

    public a()
    {
    }

    public static void a(HttpServletRequest httpservletrequest, Collection collection)
        throws Exception
    {
        HttpSession httpsession = httpservletrequest.getSession(false);
        CommonTree commontree = new CommonTree(collection);
        commontree.BuildTree();
        Collection collection1 = commontree.getMainMenu();
        Trees trees = (Trees)commontree.getTree();
        Iterator iterator = collection1.iterator();
        Object obj = null;
        if(iterator.hasNext())
        {
			Sc08 functiondto = (Sc08)iterator.next();
            httpsession.setAttribute(GlobalNames.MENU_XML, a(trees, functiondto.getBsc016()));
        }
        httpsession.setAttribute(GlobalNames.MENU_TREE, trees);
        httpsession.setAttribute(GlobalNames.MAIN_MENU, collection1);
    }

	   public static void _mthif(HttpServletRequest httpservletrequest, Collection collection)
       throws Exception
   {
       HttpSession httpsession = httpservletrequest.getSession(false);
	   DeptTree depttree = new DeptTree(collection);
	   depttree.BuildTree();
       Trees trees = (Trees)depttree.getTree();
       httpsession.setAttribute("deptTree", trees);
   }

    public static void a(HttpServletRequest httpservletrequest, Collection collection, Collection collection1)
        throws Exception
    {
        org.radf.plat.cp.tree.SelectTree selecttree = new org.radf.plat.cp.tree.SelectTree(collection, collection1);
        selecttree.BuildTree();
        String s = (String)selecttree.getTree();
        httpservletrequest.setAttribute(GlobalNames.RIGHT_TREE_XML, s);
    }

    public static String a(Trees trees, String s)
    {
        Trees trees1 = (Trees)trees.clone();
        if(s != null && trees1 != null)
        {
            NodeList nodelist = DOMUtil.getMultiNodes(trees1.getNode(), "Tree");
            for(int i = 0; i < nodelist.getLength(); i++)
            {
                String s1 = DOMUtil.getAttributeValue((Element)nodelist.item(i), "title");
                if(!s1.equals(s))
                    continue;
                trees1.setNode(nodelist.item(i));
                break;
            }

        }
        return (new b()).a(trees1);
    }

    public static String a(HttpServletRequest httpservletrequest, String s)
    {
        List list = (List)httpservletrequest.getSession().getAttribute(GlobalNames.FUNCTION_LIST);
        Stack stack = new Stack();
        a(((Collection) (list)), s, stack);
        return a(stack);
    }

    private static void a(Collection collection, String s, Stack stack)
    {
		Sc08 functiondto = c._mthif(collection, s);
        if(functiondto != null)
        {
            stack.push(functiondto);
            if(functiondto.getBsc019() != null)
                a(collection, functiondto.getBsc019(), stack);
        }
    }

    private static String a(Stack stack)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Object obj = null;
        boolean flag = true;
		Sc08 functiondto;
        for(; !stack.isEmpty(); stringbuffer.append(functiondto.getBsc018()))
        {
            functiondto = (Sc08)stack.pop();
            if(flag)
                flag = false;
            else
                stringbuffer.append(" -> ");
        }

        return stringbuffer.toString();
    }
}
