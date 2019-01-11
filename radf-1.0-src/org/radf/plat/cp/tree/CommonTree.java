// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.radf.manage.entity.Sc08;
import org.radf.plat.cp.a.c;
import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.cp.tree:
//            TreeBuilder, Trees, Tree, TreeNode

public class CommonTree extends TreeBuilder
{

    private ArrayList _fldif;
    private Trees a;
    private Collection _flddo;

    public CommonTree(Collection collection)
    {
        _fldif = new ArrayList();
        a = null;
        _flddo = null;
        _flddo = collection;
    }

    public void BuildTree()
    {
        a = new Trees(GlobalNames.MENU_TREE);
        a.setTreeType(GlobalNames.COMMON_TREE);
        try
        {
            for(Iterator iterator = _flddo.iterator(); iterator.hasNext();)
            {
				Sc08 functiondto = (Sc08)iterator.next();
                if(functiondto.getBsc019() == null)
                {
                    _fldif.add(functiondto);
                    String s = functiondto.getBsc016();
                    Tree tree = a.newTree();
                    tree.addLabel(functiondto.getBsc018());
                    tree.addTitle(s);
                    a(tree, s);
                }
            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void a(Tree tree, String s)
    {
        List list = c.a(_flddo, s);
        if(list != null)
        {
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
				Sc08 functiondto = (Sc08)iterator.next();
                String s1 = functiondto.getBsc016();
                String s2 = functiondto.getBsc021();
                if(GlobalNames.MENU_NODE.equals(s2))
                {
                    Tree tree1 = tree.newTree();
                    tree1.addLabel(functiondto.getBsc018());
                    tree1.addTitle(s1);
                    a(tree1, s1);
                }
                if(GlobalNames.MENU_LEAF.equals(s2))
                {
                    TreeNode treenode = tree.newTreeNode();
                    treenode.addLabel(functiondto.getBsc018());
                    treenode.addTitle(s1);
                    treenode.addLink(GlobalNames.WEB_APP + functiondto.getBsc017());
                }
            }

        }
    }

    public Object getTree()
    {
        return a;
    }

    public Collection getMainMenu()
    {
        return _fldif;
    }
}
