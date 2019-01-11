// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.tree;

import org.w3c.dom.Node;

// Referenced classes of package com.lbs.cp.tree:
//            BaseNode

public class TreeNode extends BaseNode
{

    public TreeNode(Node node)
    {
        super(node);
    }

    public void addLink(String s)
    {
        addAttribute("link", s);
    }
}
