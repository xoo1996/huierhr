// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart.generator;

import java.math.BigDecimal;

import org.jfree.data.general.PieDataset;

import de.laures.cewolf.tooltips.PieToolTipGenerator;

public class CommonPieToolTipGenerator
    implements PieToolTipGenerator
{

    public CommonPieToolTipGenerator()
    {
    }

    public String generateToolTip(PieDataset piedataset, Comparable comparable, int i)
    {
        int j = piedataset.getItemCount();
        int k = 0;
        for(int l = 0; l < j; l++)
            k += piedataset.getValue(l).intValue();

        int i1 = piedataset.getValue(comparable).intValue();
        float f = (new Float(i1)).floatValue() / (float)k;
        BigDecimal bigdecimal = new BigDecimal(i1 / k);
        return comparable.toString() + ": " + f * 100F + "%";
    }
}
