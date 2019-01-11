package org.radf.apps.commons;

import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.radf.commons.chart.ExtraChartEnhancer;

public class AudiogramExtraChartEnhancer extends ExtraChartEnhancer {
	public void processChart(Object chart, Map map) {
		String s = (String) map.get("title");
		if (s != null && s.trim().length() > 0)
			((JFreeChart) chart).addSubtitle(new TextTitle(s));
		CategoryPlot plot = (CategoryPlot) ((JFreeChart) chart).getPlot();
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) plot
				.getRenderer();
		lineandshaperenderer.setShapesVisible(true);// 数据点可见
		lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		lineandshaperenderer.setBaseItemLabelsVisible(true);// 显示折点数据
		yAxis.setTickUnit(new NumberTickUnit(10));// y轴间隔为10
		yAxis.setUpperBound(120);
		yAxis.setLowerBound(-10);
		yAxis.setAutoRangeIncludesZero(false);
		yAxis.setInverted(true); // y轴数值递增
		plot.setRangeGridlinesVisible(true);
		plot.setDomainGridlinesVisible(true);
	}
}
