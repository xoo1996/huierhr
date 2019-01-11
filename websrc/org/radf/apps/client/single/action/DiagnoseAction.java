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
 * ����ͼ����
 */
public class DiagnoseAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public DiagnoseAction() {
	}

	public ActionForward showLeft(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = (String) req.getSession().getAttribute("ictid");
		// ����ͼ���������
		ChartParams params = new ChartParams();
		params.setTitle("�������ͼ");
		// �����ȡ������Ҫ��SQL��HQL ���
		Map<String, String> hm = new HashMap<String, String>();
		// ����ͼ��
		params.setChartType("line");
		hm
				.put(
						"�ǵ�",
						"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
								+ ictid
								+ " and t.adglre = 'L' and t.adgtp = 'G' and t.adgadt is not null order by t.adgfq");
		hm
				.put(
						"����",
						"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
								+ ictid
								+ " and t.adglre = 'L' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
		// ѡ���ʵ���DataProducer ����JSP ҳ���ж����DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// ����
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// ����ҳ���ж����ChartParams ����
		req.setAttribute("params", params);
		// ����JSP
		return mapping.findForward("showchart");
	}

	public ActionForward showRight(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = (String) req.getSession().getAttribute("ictid");
		// ����ͼ���������
		ChartParams params = new ChartParams();
		params.setTitle("�Ҷ�����ͼ");
		// �����ȡ������Ҫ��SQL��HQL ���
		Map<String, String> hm = new HashMap<String, String>();
		// ����ͼ��
		params.setChartType("line");
		hm
				.put(
						"�ǵ�",
						"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
								+ ictid
								+ " and t.adglre = 'R' and t.adgtp = 'G' and t.adgadt is not null order by t.adgfq");
		hm
				.put(
						"����",
						"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
								+ ictid
								+ " and t.adglre = 'R' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
		// ѡ���ʵ���DataProducer ����JSP ҳ���ж����DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// ����
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// ����ҳ���ж����ChartParams ����
		req.setAttribute("params", params);
		// ����JSP
		return mapping.findForward("showchart");
	}
	public ActionForward showLeft1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fcttlid = (String) req.getSession().getAttribute("fcttlid");
		// ����ͼ���������
		ChartParams params = new ChartParams();
		params.setTitle("�������ͼ");
		// �����ȡ������Ҫ��SQL��HQL ���
		Map<String, String> hm = new HashMap<String, String>();
		// ����ͼ��
		params.setChartType("line");
		hm
				.put(
						"�ǵ�",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'L' and t.adgtp = 'G' and t.adgadt is not null order by t.adgfq");
		hm
				.put(
						"����",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'L' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
		hm
		.put(
				"����",
				"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
						+fcttlid
						+ " and t.adglre = 'L' and t.adgtp = 'S' and t.adgadt is not null order by t.adgfq");
		// ѡ���ʵ���DataProducer ����JSP ҳ���ж����DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// ����
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// ����ҳ���ж����ChartParams ����
		req.setAttribute("params", params);
		// ����JSP
		return mapping.findForward("showchart");
	}

	public ActionForward showRight1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fcttlid = (String) req.getSession().getAttribute("fcttlid");
		// ����ͼ���������
		ChartParams params = new ChartParams();
		params.setTitle("�Ҷ�����ͼ");
		// �����ȡ������Ҫ��SQL��HQL ���
		Map<String, String> hm = new HashMap<String, String>();
		// ����ͼ��
		params.setChartType("line");
		hm
				.put(
						"�ǵ�",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'R' and t.adgtp = 'G' and t.adgadt is not null order by t.adgfq");
		hm
				.put(
						"����",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'R' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
		hm
		.put(
				"����",
				"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
						+ fcttlid
						+ " and t.adglre = 'R' and t.adgtp = 'S' and t.adgadt is not null order by t.adgfq");
		// ѡ���ʵ���DataProducer ����JSP ҳ���ж����DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// ����
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// ����ҳ���ж����ChartParams ����
		req.setAttribute("params", params);
		// ����JSP
		return mapping.findForward("showchart");
	}
	
	public ActionForward showLeft2(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fcttlid = (String) req.getSession().getAttribute("fcttlid");
		// ����ͼ���������
		ChartParams params = new ChartParams();
		params.setTitle("�������ͼ");
		// �����ȡ������Ҫ��SQL��HQL ���
		Map<String, String> hm = new HashMap<String, String>();
		// ����ͼ��
		params.setChartType("line");
		hm
				.put(
						"����",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+fcttlid
								+ " and t.adglre = 'L' and t.adgtp = 'S' and t.adgadt is not null order by t.adgfq");
	/*	hm
				.put(
						"����",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'L' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
	*/	// ѡ���ʵ���DataProducer ����JSP ҳ���ж����DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// ����
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// ����ҳ���ж����ChartParams ����
		req.setAttribute("params", params);
		// ����JSP
		return mapping.findForward("showchart");
	}

	public ActionForward showRight2(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fcttlid = (String) req.getSession().getAttribute("fcttlid");
		// ����ͼ���������
		ChartParams params = new ChartParams();
		params.setTitle("�Ҷ�����ͼ");
		// �����ȡ������Ҫ��SQL��HQL ���
		Map<String, String> hm = new HashMap<String, String>();
		// ����ͼ��
		params.setChartType("line");
		hm
				.put(
						"����",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'R' and t.adgtp = 'S' and t.adgadt is not null order by t.adgfq");
	/*	hm
				.put(
						"����",
						"select t.adgfq,t.adgadt from tblfctlgraph t where t.fctlid = "
								+ fcttlid
								+ " and t.adglre = 'R' and t.adgtp = 'Q' and t.adgadt is not null order by t.adgfq");
	*/	// ѡ���ʵ���DataProducer ����JSP ҳ���ж����DataProducer
		req.setAttribute("dataProducer", new CategoryDataProducer());
		// ����
		params.setMaxVal(120);
		params.setMinVal(-10);
		params.setSqlmap(hm);
		params.setWidth(380);
		params.setHeight(280);
		// ����ҳ���ж����ChartParams ����
		req.setAttribute("params", params);
		// ����JSP
		return mapping.findForward("showchart");
	}
	
	
	/**
	 * �ĳ����ƣ�ֻ���ɶ�����
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
			// ��ȡ����ӿ�
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", dg);
			mapRequest.put("gid", gid);
			mapRequest.put("cnm", cnm);
			mapRequest.put("opr", dto1.getBsc011());
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.saveNew(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String folno1 = (String) ((HashMap) resEnv.getBody())
						.get("folno1");
				String folno2 = (String) ((HashMap) resEnv.getBody())
						.get("folno2");
				if (folno2 == null || "".equals(folno2))
					super.saveSuccessfulMsg(req, "�ĳ�����¼���ɹ��������ţ�" + folno1);
				else
					super.saveSuccessfulMsg(req, "�ĳ�����¼���ɹ��������ţ�" + folno1 + ","
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
