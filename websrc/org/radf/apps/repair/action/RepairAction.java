package org.radf.apps.repair.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.client.single.form.SingleClientForm;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.commons.entity.Task;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.apps.repair.facade.RepairFacade;
import org.radf.apps.repair.form.RepairForm;
import org.radf.apps.task.form.TaskForm;
import org.radf.login.dto.LoginDTO;
import org.radf.manage.param.dto.AA01DTO;
import org.radf.manage.param.facade.ParamFacade;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 * 维修管理
 */
public class RepairAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public RepairAction() {
	}

	/**
	 * 查询跳转
	 */
	public ActionForward enterQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.getSession().removeAttribute("SqlForRepair");
		String menuId = req.getParameter("menuId");
		String forward = menuId;
		RepairForm rf = new RepairForm();
		rf.setRepid(null);
		ClassHelper.copyProperties(rf, form);
		return mapping.findForward(forward);
	}

	/**
	 * 获取维修措施
	 */
	public ActionForward getActions(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String q = req.getParameter("q");// 参数名q是提交查询关键字的
		q = java.net.URLDecoder.decode(q, "UTF-8");
		AA01DTO aa01 = new AA01DTO();
		try {
			ParamFacade facade = (ParamFacade) getService("ParamFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo",
					"select AAA004 from AA01 where AAA001 like 'REPACTION%'");
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List<BasicDynaBean> list = (ArrayList) ((HashMap) resEnv
						.getBody()).get("beo");
				res.setCharacterEncoding("GBK");
				for (BasicDynaBean i : list) {
					ClassHelper.copyProperties(i, aa01);
					if (aa01.getAaa004().indexOf(q) != -1) {
						res.getWriter().println(aa01.getAaa004());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
		}
		return null;
	}
	
	public ActionForward getOnAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String q = req.getParameter("actionID");// 参数名q是提交查询关键字的
		String ac = req.getParameter("ac");
		q = java.net.URLDecoder.decode(q, "UTF-8");
		AA01DTO aa01 = new AA01DTO();
		try {
			ParamFacade facade = (ParamFacade) getService("ParamFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo",
					"select AAA004,AAA006 from AA01 where AAA001='" + ac + "' and AAA003='" + q + "'");
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List<BasicDynaBean> list = (ArrayList) ((HashMap) resEnv
						.getBody()).get("beo");
				res.setCharacterEncoding("GBK");
				if(null!=list)
				{
					BasicDynaBean i = list.get(0);
				    ClassHelper.copyProperties(i, aa01);
				    res.getWriter().println("[{aaa004:'" + aa01.getAaa004() + "',aaa006:'" + aa01.getAaa006() + "'}]");
			    }
				else
				{
				    res.getWriter().println("[{aaa004:'',aaa006:''}]");
				}
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
		}
		return null;
	}

	/**
	 * 进入维修登记页面
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {
		String gid = req.getParameter("gid");
		String cnm = req.getParameter("cnm");
		String pid = req.getParameter("pid");
		RepairForm rf = (RepairForm) form;
		rf.setRepgctid(gid);
		rf.setRepcltnm(cnm);
		rf.setRepdate(DateUtil.getDate());
		rf.setReppid(pid);
		ClassHelper.copyProperties(rf, form);
		return mapping.findForward("new");
	}

	/**
	 * 进入维修登记页面2
	 */
	public ActionForward enter2(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) {
		RepairForm rf = (RepairForm) form;
		rf.setRepcltid(req.getParameter("ictid"));
		rf.setRepcltnm(req.getParameter("ictnm"));
		rf.setRepgctid(req.getParameter("gctid"));
		rf.setRepgctnm(req.getParameter("gctnm"));
		rf.setReppid(req.getParameter("pid"));
		rf.setRepid(req.getParameter("sid"));
		rf.setRepdate(DateUtil.getDate());
		rf.setRepcpy("惠耳");
		ClassHelper.copyProperties(rf, form);
		return mapping.findForward("new");
	}

	/**
	 * 进入输维修措施界面(包括通知费用界面)
	 */
	public ActionForward repair(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String require = request.getParameter("require");
		String repidentity = request.getParameter("repidentity");
		String forward = null;
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		if (null == repidentity || "".equalsIgnoreCase(repidentity)) {
			saveSuccessfulMsg(request, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(ref, rep);
			if ("repair".equals(require)) {
				forward = "repair";
			} else if ("input1".equals(require)) {
				forward = "input1";
			} else if ("input2".equals(require)) {
				if ("惠耳".equals(rep.getRepcpy()))
					forward = "input3";
				else
					forward = "input2";
			}
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", rep);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), ref);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		return mapping.findForward(forward);
	}

	/**
	 * 更改维修状态
	 */
	public ActionForward change(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String require = request.getParameter("require");
		String repidentity = request.getParameter("repidentity");
		Repair rep = new Repair();
		String tp = request.getParameter("tp");
		RepairForm ref = (RepairForm) form;
		if (null == repidentity || "".equalsIgnoreCase(repidentity)) {
			saveSuccessfulMsg(request, "主键为空，请重新查询");
			return mapping.findForward("backspace");
		} else {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			ClassHelper.copyProperties(ref, rep);
			rep.setRepoprcd(dto1.getBsc011());
			if ("start".equals(require)) {
				rep.setRepsta("process");
			} else if ("finish".equals(require)) {
				rep.setRepsta("finish");
			} else if ("stop".equals(require)) {
				rep.setRepsta("stop");
			} else if ("baoxiu".equals(require)) {
				rep.setRepsta("out");
				rep.setRepamt(0.0);
			}
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", rep);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.changeStatus(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if ("start".equals(require)) {
					super.saveSuccessfulMsg(request, "确认开始维修！");
				} else if ("finish".equals(require)) {
					super.saveSuccessfulMsg(request, "确认维修完成！");
				} else if ("stop".equals(require)) {
					super.saveSuccessfulMsg(request, "订单已取消！");
				} else if ("baoxiu".equals(require)) {
					super.saveSuccessfulMsg(request, "确认保修完成！");
				}
				if(null != tp && tp.equals("ear")){
					return go2Page(request, mapping, "earmould");
				}else{
					return go2Page(request, mapping, "repair");
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		}
	}

	/**
	 * 批量更改维修状态
	 */
	public ActionForward batchChange(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.removeAttribute("SqlForRepair");
		Collection<Repair> collection = null;
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),
					Repair.class);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			for (Repair rep : collection) {
				rep.setRepoprcd(dto1.getBsc011());
				rep.setRepsta("process");
			}
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("collection", collection);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.batchChange(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "确认开始维修！");
				return go2Page(req, mapping, "repair");
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
	/*外壳制作
	 * 
	 */
	
	public ActionForward batchMakeshell(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.removeAttribute("SqlForRepair");
		Collection<Repair> collection = null;
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),
					Repair.class);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			for (Repair rep : collection) {
				rep.setRepregnames(dto1.getBsc011());
				rep.setRepsta("process");
				rep.setRepshinstdt(DateUtil.getDate());
			}
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("collection", collection);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.batchMakeshell(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "确认开始制作外壳！");
				return go2Page(req, mapping, "repair");
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
	 * 新增维修机
	 */
	public ActionForward saveNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		try {
			ClassHelper.copyProperties(ref, rep);
			if (rep.getRepgctid() == null || "".equals(rep.getRepgctid())) {
				super.saveSuccessfulMsg(req, "请正确录入送修单位");
				return mapping.findForward("backspace");
			}
			if (rep.getReppid() == null || "".equals(rep.getReppid())) {
				super.saveSuccessfulMsg(req, "请正确录入产品型号");
				return mapping.findForward("backspace");
			}
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			rep.setRepreger(dto1.getBsc011());
			if ("惠耳".equals(rep.getRepcpy()))
				rep.setRepsta("wait");// 新增维修机的状态为"等待维修"
			else
				rep.setRepsta("out");// 送厂维修状态"厂修"
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", rep);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "维修录单成功！订单号：" + rep.getRepfolid());
				rep = (Repair) ((HashMap) resEnv.getBody()).get("beo");
				FindLog.insertLog(req, rep.getRepfolid(), "新增维修记录");
				// ClassHelper.copyProperties(list.get(0), sc);
				return mapping.findForward("query");
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
	 * 查询维修机简略信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("order");
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		ActionForward af = new ActionForward();
		String forward = null;
		String filekey = null;
		if ("modify".equals(order)) {
			forward = "/repair/modify.jsp";
			filekey = "rep01_000";
		} else if ("repair".equals(order)) {
			forward = "/repair/querywx.jsp";
			filekey = "rep01_001";
		} else if ("out".equals(order)) {
			forward = "/repair/querycs.jsp";
			filekey = "rep01_002";
		} else if ("notify".equals(order)) {
			forward = "/repair/notify.jsp";
			filekey = "rep01_004";
		} else if ("confirm".equals(order)) {
			forward = "/repair/confirm.jsp";
			filekey = "rep01_003";
		} else if ("kaip".equals(order)) {
			forward = "/repair/querykp.jsp";
			filekey = "rep01_006";
		}else if("earRepModify".equals(order)) {
			forward = "/earmould/modify.jsp";
			filekey = "rep01_007";
		}else if("querywx".equals(order)){
			forward = "/earmould/querywx.jsp";
			filekey = "rep01_010";
		}else if("repconfirm".equals(order)){
			forward = "/earmould/repairconfirm.jsp";
			filekey = "rep01_008";
		}else if ("pgquery".equals(order)) {
			forward ="/repair/querypg.jsp";
			if(ref.getRepactionzh().equals(""))
			{
				filekey="rep01_013";
			}
			
			else {
				filekey="rep01_012";
			}
		}
		else if ("pgljquery".equals(order)) {
			forward ="/repair/querypglj.jsp";
			filekey="rep01_014";
		}
		else if("ermorepairquery".equals(order)){
			forward="/earmould/ermorepairquery.jsp";
			filekey="rep01_015";
			
			
		}
		else {
			forward = "/repair/query.jsp";
			filekey = "rep01_000";
		}
		try {
			ClassHelper.copyProperties(ref, rep);
			rep.setFileKey(filekey);
			
			String hql = null;
			if ("repair".equals(order)) {
				hql = (String) req.getSession().getAttribute("SqlForRepair");
				if (rep.getRepid() == null || "".equals(rep.getRepid())) {
					req.getSession().removeAttribute("SqlForRepair");
					hql = queryEnterprise(rep);
				} else {
					if (hql == null || "".equals(hql)) { // hql为空
						hql = queryEnterprise(rep);
						hql += " and (repid='" + rep.getRepid() + "')";
						req.getSession().setAttribute("SqlForRepair", hql);
					} else if (hql.indexOf(rep.getRepid()) == -1) { // 不重复
						if (hql.indexOf("and") == -1) {
							hql += " and (repid='" + rep.getRepid() + "')";
						} else {
							hql = hql.substring(0, hql.length() - 1);
							hql += " or repid='" + rep.getRepid() + "')";
							req.getSession().setAttribute("SqlForRepair", hql);
						}
					}
				}
			} else {
				hql = queryEnterprise(rep);
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
	 * 查询定制机简略信息
	 */
	public ActionForward queryCus(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		ActionForward af = new ActionForward();
		String forward = "/repair/queryCus.jsp";
		String filekey = "cus01_014";
		try {
			ClassHelper.copyProperties(ref, rep);
			rep.setFileKey(filekey);
			String hql = queryEnterprise(rep);
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
	 * 显示维修机信息
	 */
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		try {
			ClassHelper.copyProperties(ref, rep);
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", rep);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), ref);

			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward("view");
	}

	/**
	 * 显示维修机信息
	 */
	public ActionForward seeDevRep(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//InStoreForm storageForm = (InStoreForm ) form;
//		Store sto=new Store();
		Order ord=new Order();
		ActionForward af = new ActionForward();
		String forward = "/repair/devRepReg.jsp";
		try {
			//ClassHelper.copyProperties(storageForm, sto);
			ord.setFolno("-1");
			ord.setFileKey("ord11_022");
//			sto.setStoid(-1);
//			sto.setFileKey("ord11_022");
			String hql = queryEnterprise(ord);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 修改维修机信息
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String repidentity = req.getParameter("repidentity");
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		if (null == repidentity || "".equalsIgnoreCase(repidentity)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(ref, rep);
			if(null!=rep.getFolsta()&& (rep.getFolsta().equals("finish")||rep.getFolsta().equals("charged")||rep.getFolsta().equals("recback")||rep.getFolsta().equals("recoiled")||rep.getFolsta().equals("recoiledhead")||rep.getFolsta().equals("recpass")))	
			{
				super.saveSuccessfulMsg(req, "发货后维修信息不可修改！");
				return mapping.findForward("backspace");
			}
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", rep);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), ref);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		return mapping.findForward("alter");
	}

	
	/**
	 * 保存修改后维修记录
	 */
	public ActionForward saveModified(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String repidentity = req.getParameter("repidentity");
		String require = req.getParameter("require");
		String tp = req.getParameter("tp");
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		if ("".equals(ref.getRepoprcd()) || ref.getRepoprcd() == null) {
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			ref.setRepoprcd(dto1.getBsc011());
		}
		System.out.println(ref.getRepoprcd());
		if (null == repidentity || "".equalsIgnoreCase(repidentity)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
			return mapping.findForward("backspace");
		} else {
			ClassHelper.copyProperties(ref, rep);
			if ("start".equals(require)) {
				rep.setRepsta("process");
			} else if ("sleep".equals(require)) {
				rep.setRepsta("sleep");
			} else if ("notified".equals(require)) {
				rep.setRepsta("notified");
			} else if ("out".equals(require)) {
				rep.setRepsta("out");
			} else if ("finish".equals(require)) {
				rep.setRepsta("finish");
			}
			// 获取服务接口
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", rep);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modify(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存维修记录成功!");
				if(null != tp && tp.equals("ear")){
					return go2Page(req, mapping, "earmould");
					
				}else{

					return go2Page(req, mapping, "repair");
				}
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}
	}

	/**
	 * 删除维修机
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String repidentity = req.getParameter("repidentity");
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		if (null == repidentity || "".equalsIgnoreCase(repidentity)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
			return mapping.findForward("backspace");
		} else {
			ClassHelper.copyProperties(ref, rep);
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", rep);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.remove(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除维修记录成功!");
				FindLog.insertLog(req, repidentity, "删除维修记录");
				return go2Page(req, mapping, "repair");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
	}

	/**
	 * 保存修改后维修记录并确认维修完成
	 */
	public ActionForward finish(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String repidentity = req.getParameter("repidentity");
		Repair rep = new Repair();
		RepairForm ref = (RepairForm) form;
		if (null == repidentity || "".equalsIgnoreCase(repidentity)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
			return mapping.findForward("backspace");
		} else {
			if ("".equals(ref.getRepoprcd()) || ref.getRepoprcd() == null) {
				LoginDTO dto1 = (LoginDTO) req.getSession().getAttribute(
						"LoginDTO");
				ref.setRepoprcd(dto1.getBsc011());
			}
			System.out.println(ref.getRepoprcd());
			ClassHelper.copyProperties(ref, rep);
			rep.setRepsta("finish");
			// 获取服务接口
			RepairFacade facade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", rep);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.finish(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存维修记录成功!");
				return go2Page(req, mapping, "repair");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}
	}

	/**
	 * 打印维修单
	 */
	public ActionForward print1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String type = req.getParameter("type");
		BigDecimal id = new BigDecimal(req.getParameter("id"));
		Connection conn = null;
		try {
			File reportFile = null;
			// 报表编译之后生成的.jasper 文件的存放位置
			if ("jiewen".equals(type)) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\jiw_repair_list.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\hui_repair_list.jasper"));
			}
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("repid", id);
			// 连接到数据库
			conn = DBUtil.getConnection();

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
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
	 * 维修主要工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward worknum(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm rf = (RepairForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String oprcd = null;
			oprcd = rf.getRepoprcd();
			File reportFile = null;
			if ("".equals(oprcd) || oprcd == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\repair_1.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\repair_2.jasper"));
				parameters.put("pid", rf.getRepoprcd());// 维修编号
			}

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getRepfdate());// 到日期

			parameters.put("sdata", rf.getRepdate());// 从日期

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
			
			JasperPrint rptpnt = extracted(conn, parameters, reportFile);
			 ByteArrayOutputStream oStream = new ByteArrayOutputStream();

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
		    byte[] bytes = oStream.toByteArray(); 
            res.setContentLength(bytes.length);
            ouputStream.write(bytes, 0, bytes.length);

			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	private JasperPrint extracted(Connection conn,
			Map<String, Object> parameters, File reportFile) throws JRException {
		return  JasperFillManager.fillReport(reportFile.getPath(),
				parameters, conn);
	}

	/**
	 * 维修辅修工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward worknum2(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm rf = (RepairForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String oprcd = null;
			oprcd = rf.getRepregnames();
			File reportFile = null;
			if ("".equals(oprcd) || oprcd == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\repair_3.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\repair_4.jasper"));
				parameters.put("pid", rf.getRepregnames());// 辅修维修编号
			}

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getRepfdate());// 到日期

			parameters.put("sdata", rf.getRepdate());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "work_report2";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = extracted(conn, parameters, reportFile);

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
	 * 维修辅修工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward worknum0(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm rf = (RepairForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String oprcd = null;
			oprcd = rf.getRepoprcd();
			File reportFile = null;
			if ("".equals(oprcd) || oprcd == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\repair_1.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\repair_2.jasper"));
				parameters.put("pid", rf.getRepoprcd());// 辅修维修编号
			}

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getRepfdate());// 到日期

			parameters.put("sdata", rf.getRepdate());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "work_report0";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = extracted(conn, parameters, reportFile);

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
	 * 维修每日工作量统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward schedule(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm cf = (RepairForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置

			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\repair_Schedule.jasper"));

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("mdata", cf.getRepfdate());// 从日期

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

			JasperPrint rptpnt = extracted(conn, parameters, reportFile);

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
			// res.setContentType("text/html;charset=GB2312");
			// PrintWriter out = res.getWriter();
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 维修工作的统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward workcount(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm rf = (RepairForm) actionForm;
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;

			reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\repair_5.jasper"));

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getRepfdate());// 到日期

			parameters.put("sdata", rf.getRepdate());// 从日期


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

			
			JasperPrint rptpnt = extracted(conn, parameters, reportFile);

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
	 * 一月返修统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward onemonthback(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm rf = (RepairForm) actionForm;
		Connection conn = null;
		try {
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;

			reportFile = new File(req.getSession().getServletContext()
								.getRealPath("\\WEB-INF\\report\\repair_onemonthback.jasper"));

			
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getRepfdate());// 到日期

			parameters.put("sdata", rf.getRepdate());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "repair_onemonthback";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = extracted(conn, parameters, reportFile);

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
	 * 维修人员换零件统计
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward persta(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm rf = (RepairForm) actionForm;
		Connection conn = null;
		try {
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;

			reportFile = new File(req.getSession().getServletContext()
								.getRealPath("\\WEB-INF\\report\\personsta.jasper"));

			
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getRepfdate());// 到日期

			parameters.put("sdata", rf.getRepdate());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "repair_onemonthback";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = extracted(conn, parameters, reportFile);

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
	 * 三月返修统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward threemonthback(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm rf = (RepairForm) actionForm;
		Connection conn = null;
		try {
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;

			reportFile = new File(req.getSession().getServletContext()
								.getRealPath("\\WEB-INF\\report\\repair_threemonthback.jasper"));

			
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getRepfdate());// 到日期

			parameters.put("sdata", rf.getRepdate());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "repair_threemonthback";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = extracted(conn, parameters, reportFile);

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
	 * 三月重修统计
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward threemonthre(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm rf = (RepairForm) actionForm;
		Connection conn = null;
		try {
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;

			reportFile = new File(req.getSession().getServletContext()
								.getRealPath("\\WEB-INF\\report\\repair_threemonthre.jasper"));

			
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", rf.getRepfdate());// 到日期

			parameters.put("sdata", rf.getRepdate());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "repair_threemonthre";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = extracted(conn, parameters, reportFile);

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
	 * 生成维修条形码
	 */
	public ActionForward barcode(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RepairForm cf = (RepairForm) actionForm;
		Connection conn = null;
		try {

			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\repaircode.jasper"));

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			conn = DBUtil.getConnection();
			// 连接到数据库
			// System.out.println(cf.getRepfolid());
			parameters.put("repid", cf.getRepidentity());

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

//			res.setContentType("application/pdf");
//			res.setContentLength(bytes.length);
//			ServletOutputStream ouputStream = res.getOutputStream();
//			ouputStream.write(bytes, 0, bytes.length);
//			ouputStream.flush();
//			ouputStream.close();
			
			res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			PdfReader reader = new PdfReader(bytes);
			StringBuffer script = new StringBuffer();
			script
					.append(
							"this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
					.append("\r\nthis.closeDoc();");
			ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
			PdfStamper stamp = new PdfStamper(reader, bos);
			stamp.setViewerPreferences(PdfWriter.HideMenubar
					| PdfWriter.HideToolbar | PdfWriter.HideWindowUI);
			stamp.addJavaScript(script.toString());
			stamp.close();
			ouputStream.write(bos.toByteArray());
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}
	
	//寄厂维修的开票
	public ActionForward batchkp(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Collection<Repair> collection = new Vector<Repair>();
		
		SubmitDataMap data = new SubmitDataMap(req);
		String[] repList = data.getParameterValues("repidentity");
		try {
			for(int i = 0;i < repList.length;i ++){
				Repair repair = new Repair();
				repair.setRepidentity(new BigDecimal(repList[i]));
				repair.setRepkpdate(DateUtil.getDate());
				collection.add(repair);
			}
			RepairFacade repFacade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("collection", collection);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = repFacade.kaip(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "批量保存信息成功!");
				return go2Page(req, mapping, "repair");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	/**
	 * 查询个人客户维修记录
	 */
	public ActionForward queryRep(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Repair rep = new Repair();
		RepairForm cf = (RepairForm) form;
		ActionForward af = new ActionForward();
		String forward = "/repair/singlehistory.jsp";
		try {
			rep.setRepcltid(cf.getRepcltid());
			rep.setFileKey("rep01_005");
			String hql = queryEnterprise(rep);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无维修记录！";
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
	 * 维修历史
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward history(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		RepairForm cf = (RepairForm) form;
		Repair repair = new Repair();
		
		
		ActionForward af = new ActionForward();
		String forward = "/repair/history.jsp";
		try{
			
			ClassHelper.copyProperties(cf, repair);

			repair.setFileKey("rep01_009");
			String hql = queryEnterprise(repair);
			af = super.init(req, forward, hql);
		}catch(AppException ae){
			this.saveErrors(req, ae);
		}catch(Exception e){
			this.saveErrors(req, e);
		}
		return af;
	}
	/**
	 * 
	 * 获得维修措施列表
	 */
	public ActionForward getRepair(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Repair repair = new Repair();
		try {
			RepairFacade repFacade = (RepairFacade) getService("RepairFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("rep", repair);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = repFacade.getRepair(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询维修措施成功!");
				HashMap<String,Object> mapResponse = (HashMap) resEnv.getBody();
				List<Repair> repList=null;
				 for(Map.Entry<String, Object> entry:mapResponse.entrySet()){  
					 repList=(List<Repair>)entry.getValue();
					    }
				res.setCharacterEncoding("GBK");
				String[] repaction = {"[","[","[","[","[","["};
				
				for(Repair rep:repList)
				{	
					String str = rep.getRepactionType().substring(9, 10);
					int i = Integer.parseInt(str)-1;
					repaction[i] +="{repaction:'"+rep.getRepaction()+"',repactionPrc:'"+rep.getRepactionPrc()+"',repactionType:'"+rep.getRepactionType()+"',repactionCode:'"+rep.getRepactionCode()+"'},";

				}
				for(int i = 0;i<6;i++){
					repaction[i] = repaction[i].substring(0,repaction[i].length()-1)+"]";
				}
				String jsonStr = "["+repaction[0]+","+repaction[1]+","+repaction[2]+","+repaction[3]+","+repaction[4]+","+repaction[5]+"]";
				res.getWriter().write(jsonStr);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}
}
