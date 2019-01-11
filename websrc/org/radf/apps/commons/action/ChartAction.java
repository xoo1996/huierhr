package org.radf.apps.commons.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.commons.form.ChartParamForm;
import org.radf.commons.chart.ChartParams;
import org.radf.commons.chart.dataproducer.CategoryDataProducer;
import org.radf.commons.chart.dataproducer.PieDataProducer;
import org.radf.commons.chart.dataproducer.TimeDataProducer;
import org.radf.commons.chart.dataproducer.XYDataProducer;
import org.radf.plat.log.LogHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

import de.laures.cewolf.DatasetProducer;

public class ChartAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());
	private static int CHART_3D = 0;
	private static int CHART_2D = 1;
	private static int CHART_HORIZONTAL = 1;
	private static int CHART_VERTICAL = 0;

	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		String chartsql = (String) req.getSession().getAttribute("chartsql");
		System.out.println("chartSql: ===" + chartsql);
		if (chartsql == null || "".equals(chartsql)) {
			ActionForward af = new ActionForward();
			af = mapping.findForward("showchart");
			return af;
		}
		ChartParamForm fm = (ChartParamForm) form;
		Map<String, String> a = new HashMap<String, String>();
		// ActionForward af = new ActionForward();

		String sql = (String) req.getSession().getAttribute("chartsql");
		String chartname = (String) req.getSession().getAttribute("chartname");
		if (sql == null || sql == "") {
			// throw new AppException("传入SQL为空,请重新查询!");
			return mapping.findForward("backspace");
		}
		System.out.println("aaaaa=====" + sql);
		// af = super.init(req, forward, sql,"0");
		a.put(chartname, sql);

		ChartParams params = new ChartParams();// 显示图表的参数
		params.setSqlmap(a);// 传入SQL语句()
		params.setTitle(fm.getTitle());// 传入主标题
		//params.setTitle("ste");
		params.setExtraTitle(fm.getExtraTitle());// 传入副标题
		
		// StringBuffer sbChartType = new StringBuffer();
		// DatasetProducer dataProducer = null;

		// dataProducer = new CategoryDataProducer();//显示柱状图
		// dataProducer = new PieDataProducer();//显示饼状图
		// dataProducer = new TimeDataProducer();//时间折线图

		// req.setAttribute("dataProducer", dataProducer);
		// params.setChartType("verticalbar3d");//显示柱状图
		// params.setChartType("pie3d");//显示饼状图
		// params.setChartType("timeseries");//时间折线图

		int chart3d = CHART_3D, chartDirection = CHART_VERTICAL;

		if (GlobalNames.CHART_2D.equals(fm.getChart3dType())) {
			chart3d = CHART_2D;
		}

		if (GlobalNames.CHART_HORIZONTAL.equals(fm.getDirection())) {
			chartDirection = CHART_HORIZONTAL;
		}

		StringBuffer sbChartType = new StringBuffer();
		DatasetProducer dataProducer = null;
		// 显示柱状图
		if (GlobalNames.CHART_TYPE_BAR.equals(fm.getChartType())) {
			dataProducer = new CategoryDataProducer();
			// 横向显示柱状图
			if (chartDirection == CHART_HORIZONTAL) {
				sbChartType.append("horizontal");
			}
			//
			else {
				sbChartType.append("vertical");
			}
			sbChartType.append("bar");
			if (chart3d != CHART_2D) {
				sbChartType.append("3d");
			}
		}
		// 显示时间折线图
		else if (GlobalNames.CHART_TYPE_TIME.equals(fm.getChartType())) {
			sbChartType.append("timeseries");
			dataProducer = new TimeDataProducer();
		}
		// 显示饼状图
		else if (GlobalNames.CHART_TYPE_PIE.equals(fm.getChartType())) {
			sbChartType.append("pie");
			if (chart3d != CHART_2D) {
				sbChartType.append("3d");
			}
			dataProducer = new PieDataProducer();
		}
		// 显示XY时间折线图
		else if (GlobalNames.CHART_TYPE_XY.equals(fm.getChartType())) {
			sbChartType.append("xy");
			dataProducer = new XYDataProducer();
		}

		req.setAttribute("dataProducer", dataProducer);
		params.setChartType(sbChartType.toString());
		params.setHeight(fm.getHeight()==0 ? 260 : fm.getHeight());
		params.setWidth(fm.getLength()==0 ? 360 : fm.getLength());
		req.setAttribute("params", params);

		return mapping.findForward("showchart");

	}

}
