package org.radf.apps.earmould.action;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.EarMould;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.QA;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.earmould.facade.EarmouldFacade;
import org.radf.apps.earmould.form.EarMouldForm;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.apps.repair.form.RepairForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class EarMouldAction extends ActionLeafSupport {

	/**
	 * 查询跳转
	 */
	public ActionForward enterQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.getSession().removeAttribute("SqlForEM");
		req.getSession().removeAttribute("SqlForEX");
		String menuId = req.getParameter("menuId");
		String forward = menuId;
		EarMouldForm emf = new EarMouldForm();
		emf.setTmesid(null);
		ClassHelper.copyProperties(emf, form);
		return mapping.findForward(forward);
	}

	/**
	 * 根据耳模型号获得耳模售价
	 */
	public ActionForward queryEMPro(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {
			pdt.setPdtid(req.getParameter("EarId"));
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pdt);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.query(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询商品价格成功!");
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list.size() > 1)
					throw new Exception();
				ClassHelper.copyProperties(list.get(0), pdt);
				double price = pdt.getPdtprc();
				res.setCharacterEncoding("GBK");
				res.getWriter().write("[{price:" + price + "}]");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}

	/**
	 * 保存新增后的耳模信息
	 */
	public ActionForward saveNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		EarMouldForm eform = (EarMouldForm) form;
		EarMould emould;
		String num = req.getParameter("fdtqnt");
		Integer earnum = Integer.valueOf(num);
		List<EarMould> emList = new Vector<EarMould>();
		try {
			if (eform.getTmectid() == null || "".equals(eform.getTmectid())) {
				super.saveSuccessfulMsg(req, "请正确录入团体客户");
				return mapping.findForward("backspace");
			}
			String emtype = eform.getTmemat();
			// 判断耳模类别
			if (emtype.equals("0") || emtype.equals("1")) {
				for (int i = 0; i < earnum; i++) {
					emould = new EarMould();
					ClassHelper.copyProperties(eform, emould);
					emList.add(emould);
				}
			} else if (emtype.equals("2")) {
				for (int i = 0; i < earnum; i++) {
					// 生成双耳中的左
					emould = new EarMould();
					ClassHelper.copyProperties(eform, emould);
					emould.setTmemat("0");
					emList.add(emould);
					// 生成双耳中的右
					emould = new EarMould();
					ClassHelper.copyProperties(eform, emould);
					emould.setTmemat("1");
					emList.add(emould);
				}
				
			}
			earnum = 1;
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			EarmouldFacade facade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", emList);
			mapRequest.put("opr", dto1.getBsc011());
			mapRequest.put("num", earnum.toString());
			mapRequest.put("price", eform.getTmeprc());
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String tmeno = (String) ((HashMap) resEnv.getBody())
						.get("tmeno");
				// 获得从业务层返回的日志信息
				String earworkString = (String) ((HashMap) resEnv.getBody())
						.get("earworkString");
				FindLog.insertLog(req, tmeno, earworkString);
				super.saveSuccessfulMsg(req, "耳模录单成功！订单号：" + tmeno);
				return mapping.findForward("success");
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

	/**
	 * 根据不同的需求查询耳模信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("order");
		String forward = null;
		String fileKey = null;
		ActionForward af = new ActionForward();
		EarMouldForm emform = (EarMouldForm) form;
		EarMould em = new EarMould();
		if ("execute".equals(order)) {
			forward = "/earmould/execute.jsp";
			fileKey = "ear01_000";
		} else if ("confirm".equals(order)) {
			forward = "/earmould/confirm.jsp";
			fileKey = "ear01_001";
		} else if ("modify".equals(order)) {
			forward = "/earmould/modifyEarmould.jsp";
			if (emform.getTmeqst() == null || emform.getTmeqst().equals("")) {
				fileKey = "ear01_002";
			} else {
				fileKey = "ear01_006";
			}
		} else if ("checkup".equals(order)) {
			forward = "/earmould/checkup.jsp";
			fileKey = "ear01_004";
		} else if ("completeall".equals(order)) {
			forward = "/earmould/confirmcheck.jsp";
			fileKey = "ear01_005";
		}else if ("earrepzj".equals(order)) {
			forward = "/qa/earrepzj.jsp";
			fileKey = "ear01_007";
		}

		try {
			ClassHelper.copyProperties(emform, em);
			em.setFileKey(fileKey);
			String hql = null;
			//if ("execute".equals(order) || "confirm".equals(order)) {
			if ("execute".equals(order) || "confirm".equals(order)) {
				if ("confirm".equals(order))
					hql = (String) req.getSession().getAttribute("SqlForEM");
				else if ("execute".equals(order))
					hql = (String) req.getSession().getAttribute("SqlForEX");
				if (em.getTmesid() == null || "".equals(em.getTmesid())) {
					req.getSession().removeAttribute("SqlForEM");
					req.getSession().removeAttribute("SqlForEX");
					hql = queryEnterprise(em);
				}else {
					if (hql == null || "".equals(hql)) { // hql为空
						hql = queryEnterprise(em);
						hql += " and (tmesid='" + em.getTmesid() + "')";
						if ("confirm".equals(order))
							req.getSession().setAttribute("SqlForEM", hql);
						else if ("execute".equals(order))
							req.getSession().setAttribute("SqlForEX", hql);
					} else if (hql.indexOf(em.getTmesid()) == -1) { // 不重复
						if (hql.indexOf("and") == -1) {
							hql += " and (tmesid='" + em.getTmesid() + "')";
						} else {
							hql = hql.substring(0, hql.length() - 1);
							hql += " or tmesid='" + em.getTmesid() + "')";
						}
						if ("confirm".equals(order))
							req.getSession().setAttribute("SqlForEM", hql);
						else if ("execute".equals(order))
							req.getSession().setAttribute("SqlForEX", hql);
					}
			    }
			/*
			if ("confirm".equals(order)) {	
				hql = (String) req.getSession().getAttribute("SqlForEM");
				if (em.getTmesid() == null || "".equals(em.getTmesid())) {
					req.getSession().removeAttribute("SqlForEM");
					hql = queryEnterprise(em);
				} else {
					if (hql == null || "".equals(hql)) { // hql为空
						hql = queryEnterprise(em);
						hql += " and (tmesid='" + em.getTmesid() + "')";
						req.getSession().setAttribute("SqlForEM", hql);
					} else if (hql.indexOf(em.getTmesid()) == -1) { // 不重复
						if (hql.indexOf("and") == -1) {
							hql += " and (tmesid='" + em.getTmesid() + "')";
						} else {
							hql = hql.substring(0, hql.length() - 1);
							hql += " or tmesid='" + em.getTmesid() + "')";
							req.getSession().setAttribute("SqlForEM", hql);
						}
					}
				}*/
			}else {
				hql = queryEnterprise(em);
			}
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 进入耳模制作界面
	 */
	public ActionForward produce(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ictnm = request.getParameter("ictnm");
		String pdtnm = request.getParameter("pdtnm");
		EarMouldForm emForm = (EarMouldForm) form;
		emForm.setTmecltnm(ictnm);
		// emForm.setTmepnm(pdtnm);
		return mapping.findForward("produce");
	}

	/**
	 * 进入耳模订单录入界面
	 */
	public ActionForward addNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
		EarMouldForm emform = (EarMouldForm) form;
		EarMould em = new EarMould();
		String clientid = (String) req.getSession().getAttribute("tmecltid");
		try {
			EarmouldFacade emFacade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", em);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = emFacade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "查询团体客户成功!");
				// 获得团体客户名称
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list.size() > 1)
					throw new Exception();
				ClassHelper.copyProperties(list.get(0), sc);
				String gclientID = sc.getIctgctid();
				String clientID = sc.getIctid();
				String clientNM = sc.getIctnm();
				emform.setTmectid(gclientID);
				emform.setTmecltid(clientID);
				emform.setTmecltnm(clientNM);
			} else {
				String msg = "档案中没有该用户记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return mapping.findForward("register");
	}

	/**
	 * 批量耳模制作开始
	 */
	public ActionForward updateProduce(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SubmitDataMap data = new SubmitDataMap(request);
		String[] fnoList = data.getParameterValues("tmeno"); // 订单号
		String[] sidList = data.getParameterValues("tmesid");// 条形码
		List<EarMould> emList = new Vector<EarMould>();
		try {
			int size = fnoList.length;
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			for (int i = 0; i < size; i++) {
				EarMould em = new EarMould();
				em.setTmeno(fnoList[i]);
				em.setTmesid(sidList[i]);
				em.setTmesta("1");// 状态为制作中
				em.setTmemaker(dto1.getBsc011());
				emList.add(em);
			}
			// 获取服务接口
			EarmouldFacade facade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", emList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.updateProduce(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "耳模开始制作！");
				return go2Page(request, mapping, "earmould");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 确认完成耳模制作(批量获取信息)
	 */
	public ActionForward complete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SubmitDataMap data = new SubmitDataMap(request); //
		String[] fnoList = data.getParameterValues("tmeno"); // 订单号
		String[] sidList = data.getParameterValues("tmesid");// 条形码
		try {
			int size = fnoList.length;
			List<EarMould> emList = new Vector<EarMould>();
			List<QA> qaList = new Vector<QA>();
			LoginDTO dto = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			for (int i = 0; i < size; i++) {
				EarMould em = new EarMould();
				em.setTmeno(fnoList[i]);
				em.setTmesid(sidList[i]);
				em.setTmesta("2");// 状态为等待质检
				em.setTmefmdt(DateUtil.getDate());
				emList.add(em);
			}
			EarmouldFacade facade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", emList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.complete(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "批量生产完成确认成功！");
				return go2Page(request, mapping, "earmould");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 修改耳模信息
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EarMouldForm emform = (EarMouldForm) form;
		EarMould em = new EarMould();
		try {
			ClassHelper.copyProperties(emform, em);
			EarmouldFacade emFacade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", em);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = emFacade.printCI(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listem = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				String price = (String) ((HashMap) resEnv.getBody())
						.get("price");
				if (listem.size() > 1) {
					super.saveErrors(request, new AppException("查询所得记录不唯一"));
				}
				ClassHelper.copyProperties(listem.get(0), emform);
				emform.setTmeprc(price);
				return mapping.findForward("alterem");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 删除耳模信息
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		EarMouldForm emform = (EarMouldForm) form;
		EarMould em = new EarMould();
		String tmeno = emform.getTmeno();
		try {
			ClassHelper.copyProperties(emform, em);
			EarmouldFacade facade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, EarMould> mapRequest = new HashMap<String, EarMould>();
			mapRequest.put("beo", em);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "删除耳模信息成功!");
				FindLog.insertLog(request, tmeno, "删除耳模");
				return go2Page(request, mapping, "earmould");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 质检
	 */
	public ActionForward checkup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pdtnm = request.getParameter("pdtnm");
		EarMouldForm emForm = (EarMouldForm) form;
		emForm.setTmepnm(pdtnm);
		return mapping.findForward("checkupdetail");
	}

	/**
	 * 质检－更新耳模质检信息
	 */
	public ActionForward updateCheckup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EarMouldForm emForm = (EarMouldForm) form;
		EarMould em = new EarMould();
		QA qa = new QA();
		try {
			// 设定经办信息
			ClassHelper.copyProperties(emForm, em);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			em.setTmechkdt(DateUtil.getSystemCurrentTime());//质检日期
			em.setTmechkoprcd(dto1.getBsc011());//质检员代码
			
			qa.setQafno(em.getTmeno());//获得订单号

			// 获取服务接口
			EarmouldFacade emFacade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", em);
			mapRequest.put("qa", qa);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = emFacade.updateCheckup(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "耳模质检信息保存成功!");
				return go2Page(request, mapping, "earmould");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}

	}

	/**
	 * 耳模信息修改——耳模信息维护
	 */
	public ActionForward saveModifiedEM(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EarMouldForm emForm = (EarMouldForm) form;
		EarMould em = new EarMould();
		try {
			ClassHelper.copyProperties(emForm, em);
			EarmouldFacade facade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", em);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.savemodify(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "耳模信息修改成功!");
				return go2Page(request, mapping, "earmould");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}

	}

	/**
	 * 保存新增的耳模维修信息
	 */
	public ActionForward saveRepair(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EarMouldForm eform = (EarMouldForm) form;
		EarMould emould = new EarMould();
		Repair rep = new Repair();
		String num = request.getParameter("fdtqnt");
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			
			//插入tblearmaking表用到的数据
			ClassHelper.copyProperties(eform, emould);
			emould.setTmecls("repair");//制作类别
			emould.setTmesta("2");//耳模状态，2表示等待质检
			
			
			
			rep.setRepdate(DateUtil.getDate());// 送修日期
			rep.setRepoprcd(dto1.getBsc011());
			rep.setRepnote(eform.getTment());// 备注
			rep.setRepcltnm(eform.getTmecltnm());// 客户姓名
			rep.setRepid(eform.getTmesid());// 耳模条形码
			rep.setReppid(eform.getTmepid());// 耳模型号
			rep.setRepgctid(eform.getTmectid());// 团体客户
			rep.setReppdate(eform.getTmepdt());//计划完工日期
			rep.setRepsta("wait");
			rep.setRepcpy("惠耳");
			
			
			// repList.add(rep);

			EarmouldFacade facade = (EarmouldFacade) getService("EarmouldFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", rep);
			mapRequest.put("ear", emould);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveRepair(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "添加耳模维修记录成功!");
				return mapping.findForward("backspace");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}

		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 耳膜工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward worknum(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		EarMouldForm rf = (EarMouldForm) actionForm;
		String qe=req.getParameter("qe");
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String tmemaker = null;
			tmemaker = rf.getTmemaker();
			File reportFile = null;
//			增加了耳膜重做的部分
			if (qe.equals("earredo")) {
				if ("".equals(tmemaker) || tmemaker == null) {
					reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\earmoderedo.jasper"));
				} else {
					reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\earmode1redo.jasper"));
					parameters.put("pid", rf.getTmemaker());// 制作者编号
				}
			} else {
				if ("".equals(tmemaker) || tmemaker == null) {
					reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\earmode.jasper"));
				} else {
					reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\earmode1.jasper"));
					parameters.put("pid", rf.getTmemaker());// 制作者编号
				}
			}
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getTmefmdt());// 到日期

			parameters.put("sdata", rf.getTmepdt());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "work_report";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(),
					parameters, conn);

			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);

			exporter
					.setParameter(
							JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
							Boolean.TRUE);
			// 保留GridLine
			// 缩小字体填充单元格
			exporter.setParameter(
					JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					true);
			exporter.exportReport();

			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 耳膜每日工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward schedule(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		EarMouldForm cf = (EarMouldForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置

			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\earmode_Schedule.jasper"));

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("mdata", cf.getTmefmdt());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "schedule_report";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(),
					parameters, conn);

			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);

			exporter
					.setParameter(
							JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
							Boolean.TRUE);
			// 保留GridLine
			// 缩小字体填充单元格
			exporter.setParameter(
					JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,
					Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,
					true);
			exporter.exportReport();

			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}
	
	
	/**
	 * 查询耳模简略信息
	 */
	public ActionForward queryRepair(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		EarMould em = new EarMould();
		EarMouldForm emf = (EarMouldForm) form;
		ActionForward af = new ActionForward();
		String forward = "/earmould/earrep.jsp";
		String filekey = "ear06_000";
		try {
			ClassHelper.copyProperties(emf, em);
			em.setFileKey(filekey);
			String hql = queryEnterprise(em);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

}
