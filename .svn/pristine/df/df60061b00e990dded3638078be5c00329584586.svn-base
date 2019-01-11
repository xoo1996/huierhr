package org.radf.apps.client.single.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.client.single.form.DiagnoseForm;
import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.Diagnose;
import org.radf.apps.customization.facade.CustomizationFacade;
import org.radf.apps.customization.form.CustomizationForm;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.commons.chart.ChartParams;
import org.radf.commons.chart.dataproducer.CategoryDataProducer;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;

/**
 * 听力图管理
 */
public class DiagnoseAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public DiagnoseAction() {
	}

	public ActionForward showLeft(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = (String) req.getSession().getAttribute("ictid");
		// 定义图表参数对象
		ChartParams params = new ChartParams();
		params.setTitle("左耳听力图");
		// 定义获取数据需要的SQL、HQL 语句
		Map<String, String> hm = new HashMap<String, String>();
		// 折线图表
		params.setChartType("line");
		hm
				.put(
						"骨导",
						"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
								+ ictid
								+ " and t.adglre = 'L' and t.adgtp = 'G' and t.adgadt is not null order by t.adgfq");
		hm
				.put(
						"气导",
						"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
								+ ictid
								+ " and t.adglre = 'L' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
		// 选择适当的DataProducer 覆盖JSP 页面中定义的DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// 参数
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// 覆盖页面中定义的ChartParams 对象
		req.setAttribute("params", params);
		// 返回JSP
		return mapping.findForward("showchart");
	}

	public ActionForward showRight(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = (String) req.getSession().getAttribute("ictid");
		// 定义图表参数对象
		ChartParams params = new ChartParams();
		params.setTitle("右耳听力图");
		// 定义获取数据需要的SQL、HQL 语句
		Map<String, String> hm = new HashMap<String, String>();
		// 折线图表
		params.setChartType("line");
		hm
				.put(
						"骨导",
						"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
								+ ictid
								+ " and t.adglre = 'R' and t.adgtp = 'G' and t.adgadt is not null order by t.adgfq");
		hm
				.put(
						"气导",
						"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
								+ ictid
								+ " and t.adglre = 'R' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
		// 选择适当的DataProducer 覆盖JSP 页面中定义的DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// 参数
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// 覆盖页面中定义的ChartParams 对象
		req.setAttribute("params", params);
		// 返回JSP
		return mapping.findForward("showchart");
	}
	public ActionForward showLeft1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fcttlid = (String) req.getSession().getAttribute("fcttlid");
		// 定义图表参数对象
		ChartParams params = new ChartParams();
		params.setTitle("左耳听力图");
		// 定义获取数据需要的SQL、HQL 语句
		Map<String, String> hm = new HashMap<String, String>();
		// 折线图表
		params.setChartType("line");
		hm
				.put(
						"骨导",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'L' and t.adgtp = 'G' and t.adgadt is not null order by t.adgfq");
		hm
				.put(
						"气导",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'L' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
		hm
		.put(
				"声场",
				"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
						+fcttlid
						+ " and t.adglre = 'L' and t.adgtp = 'S' and t.adgadt is not null order by t.adgfq");
		// 选择适当的DataProducer 覆盖JSP 页面中定义的DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// 参数
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// 覆盖页面中定义的ChartParams 对象
		req.setAttribute("params", params);
		// 返回JSP
		return mapping.findForward("showchart");
	}

	public ActionForward showRight1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fcttlid = (String) req.getSession().getAttribute("fcttlid");
		// 定义图表参数对象
		ChartParams params = new ChartParams();
		params.setTitle("右耳听力图");
		// 定义获取数据需要的SQL、HQL 语句
		Map<String, String> hm = new HashMap<String, String>();
		// 折线图表
		params.setChartType("line");
		hm
				.put(
						"骨导",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'R' and t.adgtp = 'G' and t.adgadt is not null order by t.adgfq");
		hm
				.put(
						"气导",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'R' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
		hm
		.put(
				"声场",
				"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
						+ fcttlid
						+ " and t.adglre = 'R' and t.adgtp = 'S' and t.adgadt is not null order by t.adgfq");
		// 选择适当的DataProducer 覆盖JSP 页面中定义的DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// 参数
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// 覆盖页面中定义的ChartParams 对象
		req.setAttribute("params", params);
		// 返回JSP
		return mapping.findForward("showchart");
	}
	
	public ActionForward showLeft2(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fcttlid = (String) req.getSession().getAttribute("fcttlid");
		// 定义图表参数对象
		ChartParams params = new ChartParams();
		params.setTitle("左耳声场图");
		// 定义获取数据需要的SQL、HQL 语句
		Map<String, String> hm = new HashMap<String, String>();
		// 折线图表
		params.setChartType("line");
		hm
				.put(
						"声场",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+fcttlid
								+ " and t.adglre = 'L' and t.adgtp = 'S' and t.adgadt is not null order by t.adgfq");
	/*	hm
				.put(
						"气导",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'L' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
	*/	// 选择适当的DataProducer 覆盖JSP 页面中定义的DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// 参数
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// 覆盖页面中定义的ChartParams 对象
		req.setAttribute("params", params);
		// 返回JSP
		return mapping.findForward("showchart");
	}

	public ActionForward showRight2(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fcttlid = (String) req.getSession().getAttribute("fcttlid");
		// 定义图表参数对象
		ChartParams params = new ChartParams();
		params.setTitle("右耳声场图");
		// 定义获取数据需要的SQL、HQL 语句
		Map<String, String> hm = new HashMap<String, String>();
		// 折线图表
		params.setChartType("line");
		hm
				.put(
						"声场",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'R' and t.adgtp = 'S' and t.adgadt is not null order by t.adgfq");
	/*	hm
				.put(
						"气导",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'R' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
	*/	// 选择适当的DataProducer 覆盖JSP 页面中定义的DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// 参数
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// 覆盖页面中定义的ChartParams 对象
		req.setAttribute("params", params);
		// 返回JSP
		return mapping.findForward("showchart");
	}
	
	
	/**
	 * 寄厂定制（只生成订单）
	 */
	public ActionForward saveNew1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String gid = req.getParameter("gid");
		String cnm = req.getParameter("cnm");
		DiagnoseForm df = (DiagnoseForm) form;
		Diagnose dg = new Diagnose();
		try {
			ClassHelper.copyProperties(df, dg);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			// 获取服务接口
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", dg);
			mapRequest.put("gid", gid);
			mapRequest.put("cnm", cnm);
			mapRequest.put("opr", dto1.getBsc011());
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveNew(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String folno1 = (String) ((HashMap) resEnv.getBody())
						.get("folno1");
				String folno2 = (String) ((HashMap) resEnv.getBody())
						.get("folno2");
				if (folno2 == null || "".equals(folno2))
					super.saveSuccessfulMsg(req, "寄厂定制录单成功！订单号：" + folno1);
				else
					super.saveSuccessfulMsg(req, "寄厂定制录单成功！订单号：" + folno1 + ","
							+ folno2);
				return mapping.findForward("backspace");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
}
