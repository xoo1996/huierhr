// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart.generator;

import org.jfree.data.category.CategoryDataset;

import de.laures.cewolf.tooltips.CategoryToolTipGenerator;

public class CommonCategoryToolTipGenerator
    implements CategoryToolTipGenerator
{

    public CommonCategoryToolTipGenerator()
    {
    }

    public String generateToolTip(CategoryDataset categorydataset, int i, int j)
    {
        return String.valueOf(categorydataset.getValue(i, j));
    }
}
