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
			// throw new AppException("����SQLΪ��,�����²�ѯ!");
			return mapping.findForward("backspace");
		}
		System.out.println("aaaaa=====" + sql);
		// af = super.init(req, forward, sql,"0");
		a.put(chartname, sql);

		ChartParams params = new ChartParams();// ��ʾͼ��Ĳ���
		params.setSqlmap(a);// ����SQL���()
		params.setTitle(fm.getTitle());// ����������
		//params.setTitle("ste");
		params.setExtraTitle(fm.getExtraTitle());// ���븱����
		
		// StringBuffer sbChartType = new StringBuffer();
		// DatasetProducer dataProducer = null;

		// dataProducer = new CategoryDataProducer();//��ʾ��״ͼ
		// dataProducer = new PieDataProducer();//��ʾ��״ͼ
		// dataProducer = new TimeDataProducer();//ʱ������ͼ

		// req.setAttribute("dataProducer", dataProducer);
		// params.setChartType("verticalbar3d");//��ʾ��״ͼ
		// params.setChartType("pie3d");//��ʾ��״ͼ
		// params.setChartType("timeseries");//ʱ������ͼ

		int chart3d = CHART_3D, chartDirection = CHART_VERTICAL;

		if (GlobalNames.CHART_2D.equals(fm.getChart3dType())) {
			chart3d = CHART_2D;
		}

		if (GlobalNames.CHART_HORIZONTAL.equals(fm.getDirection())) {
			chartDirection = CHART_HORIZONTAL;
		}

		StringBuffer sbChartType = new StringBuffer();
		DatasetProducer dataProducer = null;
		// ��ʾ��״ͼ
		if (GlobalNames.CHART_TYPE_BAR.equals(fm.getChartType())) {
			dataProducer = new CategoryDataProducer();
			// ������ʾ��״ͼ
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
		// ��ʾʱ������ͼ
		else if (GlobalNames.CHART_TYPE_TIME.equals(fm.getChartType())) {
			sbChartType.append("timeseries");
			dataProducer = new TimeDataProducer();
		}
		// ��ʾ��״ͼ
		else if (GlobalNames.CHART_TYPE_PIE.equals(fm.getChartType())) {
			sbChartType.append("pie");
			if (chart3d != CHART_2D) {
				sbChartType.append("3d");
			}
			dataProducer = new PieDataProducer();
		}
		// ��ʾXYʱ������ͼ
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
