// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.tree;

import org.w3c.dom.*;

// Referenced classes of package com.lbs.cp.tree:
//            BaseNode, TreeNode

public class Tree extends BaseNode
{

    public Tree(Node node)
    {
        super(node);
    }

    public TreeNode newTreeNode()
    {
        TreeNode treenode = new TreeNode(getNode().getOwnerDocument().createElement("TreeNode"));
        getNode().appendChild(treenode.getNode());
        String s = getID() + handleID(getNode().getChildNodes().getLength());
        treenode.addAttribute("id", s);
        return treenode;
    }

    public Tree newTree()
    {
        Tree tree = new Tree(getNode().getOwnerDocument().createElement("Tree"));
        getNode().appendChild(tree.getNode());
        String s = getID() + handleID(getNode().getChildNodes().getLength());
        tree.addAttribute("id", s);
        return tree;
    }

    public String getID(int i)
    {
        if(i > 9)
            return String.valueOf(i);
        else
            return "0" + i;
    }
}
