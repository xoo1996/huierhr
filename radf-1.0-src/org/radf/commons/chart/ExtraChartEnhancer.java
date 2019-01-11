// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart;

import java.io.Serializable;
import java.util.Map;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import de.laures.cewolf.ChartPostProcessor;

public class ExtraChartEnhancer implements ChartPostProcessor, Serializable {

	public ExtraChartEnhancer() {
	}

	public void processChart(Object chart, Map map) {
		String s = (String) map.get("title");
		if (s != null && s.trim().length() > 0)
			((JFreeChart) chart).addSubtitle(new TextTitle(s));
	}
}
