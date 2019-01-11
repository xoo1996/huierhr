package org.radf.apps.product.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.business.facade.BusinessFacade;
import org.radf.apps.business.form.BusinessForm;
import org.radf.apps.client.group.facade.GroupClientFacade;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.client.single.form.SingleClientForm;
import org.radf.apps.commons.entity.Business;
import org.radf.apps.commons.entity.Configuration;
import org.radf.apps.commons.entity.Discount;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.ProClass;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.Task;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.commons.entity.TypDiscount;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderDetailForm;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.apps.product.form.ConfigurationForm;
import org.radf.apps.product.form.ProductForm;
import org.radf.login.dto.LoginDTO;
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

/**
 * 商品信息管理
 */
public class ProductAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public ProductAction() {
	}

	/**
	 * 新增商品代码
	 */
	public ActionForward saveNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		ProductForm pdtForm = (ProductForm) form;
		String mark = req.getParameter("mark");
		String forward = null;
		if (mark.equals("make")) {
			forward = "query";
		} else if (mark.equals("accessory")) {
			forward = "queryac";
		}
		
		try {
			ClassHelper.copyProperties(pdtForm, pdt);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pdt);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "新增商品代码成功!");
				// 获得商品代码
				String pdtid = (String) ((HashMap) resEnv.getBody())
						.get("pdtid");
				String workString = (String) ((HashMap) resEnv.getBody())
						.get("workString");
				FindLog.insertLog(req, pdtid, workString);
				return mapping.findForward(forward);
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
	 * 新增面板零件
	 */
	public ActionForward saveNew1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		ProductForm pdtForm = (ProductForm) form;
		String forward = "addpart";
		
		try {
			ClassHelper.copyProperties(pdtForm, pdt);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pdt);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save1(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "新增面板零件成功!");
				// 获得商品代码
				String pdtid = (String) ((HashMap) resEnv.getBody())
						.get("pdtid");
				String workString = (String) ((HashMap) resEnv.getBody())
						.get("workString");
				FindLog.insertLog(req, pdtid, workString);
				return mapping.findForward(forward);
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
	
	public ActionForward savePcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		Product pdt = new Product();
		ProClass pcl=new ProClass();
		ProductForm pdtForm = (ProductForm) form;
//		String mark = req.getParameter("mark");
		String forward = null;
//		if (mark.equals("make")) {
//			forward = "query";
//		} else if (mark.equals("accessory")) {
//			forward = "queryac";
//		}
		
		try {
//			ClassHelper.copyProperties(pdtForm, pdt);
			ClassHelper.copyProperties(pdtForm, pcl);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pcl);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.savePcl(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "新增商品类别代码成功!");
				// 获得商品代码
				String pclid = (String) ((HashMap) resEnv.getBody())
						.get("pclid");
				String workString = (String) ((HashMap) resEnv.getBody())
						.get("workString");
				FindLog.insertLog(req, pclid, workString);
				forward = "queryPcl";
				return mapping.findForward(forward);
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
	 * 进入查询商品信息页面
	 */
	public ActionForward enterQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String cltID = req.getParameter("cltID");
		req.getSession().setAttribute("cltID", cltID);
		System.out.println(cltID);
		return mapping.findForward("query");
	}

	/**
	 * 查询商品信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		ProductForm pf = (ProductForm) form;
		ActionForward af = new ActionForward();
		String forward = null;
		String mark = req.getParameter("mark");
		try {
			ClassHelper.copyProperties(pf, pdt);
			if (mark.equals("make")) {
				pdt.setFileKey("pdt01_000");
				forward = "/product/query.jsp";
			} else if (mark.equals("accessory")) {
				pdt.setFileKey("pdt01_001");
				forward = "/product/accessory/query.jsp";
			}else if(mark.equals("part")){
				pdt.setFileKey("pdt05_000");
				forward = "/product/part/query.jsp";
			}else if(mark.equals("typDis")){
				pdt.setFileKey("pdt06_001");
				forward="/product/cusDiscount.jsp";
			}else if(mark.equals("proDis")){
				String pdttype=req.getSession().getAttribute("pdttype").toString();
				pdt.setPdttype(pdttype);
				pdt.setFileKey("pdt02_001");
				forward="/product/cusDisDetail.jsp";
			}
			String hql = queryEnterprise(pdt);
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
	 * 查询商品信息
	 */
	public ActionForward queryPcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		Product pdt = new Product();
		ProClass pcl=new ProClass();
		ProductForm pf = (ProductForm) form;
		ActionForward af = new ActionForward();
		String forward = null;
//		String mark = req.getParameter("mark");
		try {
			ClassHelper.copyProperties(pf, pcl);
//			if (mark.equals("make")) {
//				pdt.setFileKey("pdt01_000");
//				forward = "/product/query.jsp";
//			} else if (mark.equals("accessory")) {
//				pdt.setFileKey("pdt01_001");
//				forward = "/product/accessory/query.jsp";
//			}else if(mark.equals("part")){
//				pdt.setFileKey("pdt05_000");
//				forward = "/product/part/query.jsp";
//			}
			pcl.setFileKey("pdt06_000");
			forward="/product/queryPcl.jsp";
			String hql = queryEnterprise(pcl);
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
	 * 获得商品代码 jquery 提示框
	 */
	public ActionForward queryEMPro(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {
//			pdt.setPdtclid(req.getParameter("PclId"));pdtcls
//			pdt.setPdtcls(req.getParameter("pdtcls"));
			String proType=req.getParameter("proType");
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("pdt", pdt);
			mapRequest.put("proType", proType);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.queryProidByClass(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询商品价格成功!");
//				Collection<Product> collection = (Collection<Product>) map
//						.get("collection");
				HashMap<String,Object> mapResponse = (HashMap) resEnv.getBody();
				List<Product> proList=null;
				 for(Map.Entry<String, Object> entry:mapResponse.entrySet()){  
					  proList=(List<Product>)entry.getValue();
					    }
//				List proList=(ArrayList)mapResponse.get("pdt");
				res.setCharacterEncoding("GBK");
				String jsonStr="[";
				for(Product pro:proList)
				{
					jsonStr+="{proid:'"+pro.getPdtid()+"',proname:'" + pro.getPdtnm() + "',proprc:'"+pro.getPdtprc()+"'},";
				}
				jsonStr=jsonStr.substring(0, jsonStr.length()-1);
				jsonStr+="]";
				res.getWriter().write(jsonStr);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}
	/**
	 * 获得面板型号 jquery 提示框
	 */
	public ActionForward queryEMPro1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Task pdt = new Task();
		try {
			String proType=req.getParameter("proType");
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("pdt", pdt);
			mapRequest.put("proType", proType);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.queryProidByClass1(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询面板型号成功!");
				HashMap<String,Object> mapResponse = (HashMap) resEnv.getBody();
				List<Task> proList=null;
				 for(Map.Entry<String, Object> entry:mapResponse.entrySet()){  
					  proList=(List<Task>)entry.getValue();
					    }
				res.setCharacterEncoding("GBK");
				String jsonStr="[";
				for(Task pro:proList)
				{
					jsonStr+="{proid:'"+pro.getPnlproid()+"',cfgpnlnm:'" + pro.getCfgpnlnm() + "'},";
				}
				jsonStr=jsonStr.substring(0, jsonStr.length()-1);
				jsonStr+="]";
				res.getWriter().write(jsonStr);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}
	/*$.getJSON("IndicatorManager.jsp", //服务器页面地址
			   {
			   action: "getIndicatorList" //action参数
			   },
			   function(json) { //回调函数
			 $(json).each(function(i){ //遍历结果数组
			alert(json[i].indicatorCode);
			 alert(json[i].indicatorName);  
			});*/
	
	/*
	 * 根据商品品牌查询商品
	 */
	public ActionForward queryProByTp(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		TypDiscount tpd=new TypDiscount();
		Product pdt=new Product();
		String tdsnm=req.getParameter("tdsnm");
		
		pdt.setPdttype(tdsnm);
//		ProductForm pf = (ProductForm) form;
		ActionForward af = new ActionForward();
		String forward = null;
//		String mark = req.getParameter("mark");
		try {
//			ClassHelper.copyProperties(pf, pdt);
			pdt.setFileKey("pdt06_002");
			forward = "/product/cusDisDetail.jsp";
			String hql = queryEnterprise(pdt);
			af = super.init(req, forward, hql);
//			ClassHelper.copyProperties(pdt, form);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
			req.getSession().setAttribute("pdttype", pdt.getPdttype());
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 根据耳模型号获得耳模售价
	 */
	public ActionForward queryPanels(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		Product pdt = new Product();
		Configuration cfg = new Configuration();
		try {
//			pdt.setPdtclid(req.getParameter("PclId"));pdtcls
//			pdt.setPdtcls(req.getParameter("pdtcls"));
//			String proType=req.getParameter("proType");
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("cfg", cfg);
//			mapRequest.put("proType", proType);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.queryPanels(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询商品价格成功!");
//				Collection<Product> collection = (Collection<Product>) map
//						.get("collection");
				HashMap<String,Object> mapResponse = (HashMap) resEnv.getBody();
				List<Configuration> cfgList=null;
				 for(Map.Entry<String, Object> entry:mapResponse.entrySet()){  
					  cfgList=(List<Configuration>)entry.getValue();
					    }
//				List proList=(ArrayList)mapResponse.get("pdt");
				res.setCharacterEncoding("GBK");
				String jsonStr="[";
				for(Configuration cfg1:cfgList)                      //getCfgpnlnm()
				{
//					jsonStr+="{proid:'"+pro.getPdtid()+"',proname:'" + pro.getPdtnm() + "',proprc:'"+pro.getPdtprc()+"'},";
					jsonStr+="{pnlnm:'"+cfg1.getCfgpnlnm()+"'},";
				}
				jsonStr=jsonStr.substring(0, jsonStr.length()-1);
				jsonStr+="]";
				res.getWriter().write(jsonStr);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}
	
	public ActionForward queryParts(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {
//			pdt.setPdtclid(req.getParameter("PclId"));pdtcls
//			pdt.setPdtcls(req.getParameter("pdtcls"));
//			String proType=req.getParameter("proType");
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("pdt", pdt);
//			mapRequest.put("proType", proType);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.queryParts(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询商品价格成功!");
//				Collection<Product> collection = (Collection<Product>) map
//						.get("collection");
				HashMap<String,Object> mapResponse = (HashMap) resEnv.getBody();
				List<Product> proList=null;
				 for(Map.Entry<String, Object> entry:mapResponse.entrySet()){  
					  proList=(List<Product>)entry.getValue();
					    }
//				List<Product> proList=(ArrayList)mapResponse.get("pdt");
				res.setCharacterEncoding("GBK");
				String jsonStr="[";
				for(Product pro:proList)
				{
					jsonStr+="{proid:'"+pro.getPdtid()+"',proname:'" + pro.getPdtnm() + "',promod:'"+pro.getPdtmod()+"'},";
				}
				jsonStr=jsonStr.substring(0, jsonStr.length()-1);
				jsonStr+="]";
				res.getWriter().write(jsonStr);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}
	
	
	public ActionForward queryClasses(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		Product pdt = new Product();
		ProClass pcl=new ProClass(); 
		try {
//			pdt.setPdtclid(req.getParameter("PclId"));pdtcls
//			pdt.setPdtcls(req.getParameter("pdtcls"));
//			String proType=req.getParameter("proType");
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("pcl", pcl);
//			mapRequest.put("proType", proType);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.queryClasses(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询商品价格成功!");
//				Collection<Product> collection = (Collection<Product>) map
//						.get("collection");
				HashMap<String,Object> mapResponse = (HashMap) resEnv.getBody();
				 List<ProClass> pclList=null;
				for(Map.Entry<String, Object> entry:mapResponse.entrySet()){  
					  pclList=(List<ProClass>)entry.getValue();
					    }
//				List proList=(ArrayList)mapResponse.get("pdt");
				res.setCharacterEncoding("GBK");
				String jsonStr="[";
				for(ProClass pcl1:pclList)
				{
//					jsonStr+="{proid:'"+pro.getPdtid()+"',proname:'" + pro.getPdtnm() + "',promod:'"+pro.getPdtmod()+"'},";
					
					jsonStr+="{pclid:'"+pcl1.getPclid()+"',pcllarge:'" + pcl1.getPcllarge() + "',pclmid:'"+pcl1.getPclmid()+"',pclsmall:'"+pcl1.getPclsmall()+"'},";
				}
				jsonStr=jsonStr.substring(0, jsonStr.length()-1);
				jsonStr+="]";
				res.getWriter().write(jsonStr);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}
	
	
	
	
	
	/**
	 * 修改商品信息
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pdtid = req.getParameter("pdtid");
		String mark = req.getParameter("mark");
		String forward = null;
		if (mark.equals("make")) {
			forward = "alter";
		} else if (mark.equals("accessory")) {
			forward = "alterac";
		}
		Product pdt = new Product();
		ProductForm pdtForm = (ProductForm) form;
		if (null == pdtid || "".equalsIgnoreCase(pdtid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(pdtForm, pdt);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Product> mapRequest = new HashMap<String, Product>();
			mapRequest.put("beo", pdt);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), pdtForm);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		return mapping.findForward(forward);
	}
	
	/**
	 * 修改面板零件信息
	 */
	public ActionForward modify1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//String acyid = req.getParameter("acyid");
		//String acypdtnm = req.getParameter("acypdtnm");
		String forward = "alterpart";
		Product pdt = new Product();
		ProductForm pdtForm = (ProductForm) form;
		if (null == pdtForm.getPdtid() || "".equalsIgnoreCase(pdtForm.getPdtid())) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(pdtForm, pdt);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Product> mapRequest = new HashMap<String, Product>();
			mapRequest.put("beo", pdt);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print1(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), pdtForm);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		return mapping.findForward(forward);
	}

    
	/**
	 * 修改商品信息
	 */
	public ActionForward modifyPcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pdtid = req.getParameter("pclid");
//		String mark = req.getParameter("mark");
		String forward = null;
//		if (mark.equals("make")) {
//			forward = "alter";
//		} else if (mark.equals("accessory")) {
//			forward = "alterac";
//		}
//		forward="/product/alterPcl.jsp";
		forward="alterPcl";
//		Product pdt = new Product();
		ProClass pcl=new ProClass();
		ProductForm pdtForm = (ProductForm) form;
		if (null == pdtid || "".equalsIgnoreCase(pdtid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(pdtForm, pcl);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, ProClass> mapRequest = new HashMap<String, ProClass>();
			mapRequest.put("beo", pcl);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.printPcl(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), pdtForm);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		return mapping.findForward(forward);
	}
	
	
	
	
	/**
	 * 保存修改后的商品代码
	 */
	public ActionForward saveModified(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		ProductForm pdtForm = (ProductForm) form;
		String mark = req.getParameter("mark");
		String forward = null;
		if (mark.equals("make")) {
			forward = "query";
		} else if (mark.equals("accessory")) {
			forward = "queryac";
		}
		try {
			ClassHelper.copyProperties(pdtForm, pdt);
			// 获取服务接口
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pdt);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modify(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改商品代码成功!");
				return mapping.findForward(forward);
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
	 * 保存修改后的面板零件
	 */
	public ActionForward saveModified1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		ProductForm pdtForm = (ProductForm) form;
		String forward = "addpart";
		try {
			ClassHelper.copyProperties(pdtForm, pdt);
			// 获取服务接口
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pdt);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modify1(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改面板零件信息成功!");
				return mapping.findForward(forward);
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
	 * 保存修改后的商品代码
	 */
	public ActionForward saveModifiedPcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		Product pdt = new Product();
		ProClass pcl=new ProClass();
		ProductForm pdtForm = (ProductForm) form;
//		String mark = req.getParameter("mark");
		String forward = null;
//		if (mark.equals("make")) {
//			forward = "query";
//		} else if (mark.equals("accessory")) {
//			forward = "queryac";
//		}
		forward="queryPcl";
		try {
			ClassHelper.copyProperties(pdtForm, pcl);
			// 获取服务接口
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pcl);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modifyPcl(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改商品代码成功!");
				return mapping.findForward(forward);
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
	 * 删除商品代码
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pdtid = req.getParameter("pdtid");
		Product pdt = new Product();
		ProductForm pdtForm = (ProductForm) form;
		if (null == pdtid || "".equalsIgnoreCase(pdtid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(pdtForm, pdt);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Product> mapRequest = new HashMap<String, Product>();
			mapRequest.put("beo", pdt);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除商品代码成功!");
				FindLog.insertLog(req, pdtid, "删除商品代码");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		return go2Page(req, mapping, "product");
	}

	
	/**
	 * 删除商品类别
	 */
	public ActionForward deletePcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pclid = req.getParameter("pclid");
//		Product pdt = new Product();
		ProClass pcl=new ProClass();
		ProductForm pdtForm = (ProductForm) form;
		if (null == pclid || "".equalsIgnoreCase(pclid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(pdtForm, pcl);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, ProClass> mapRequest = new HashMap<String, ProClass>();
			mapRequest.put("beo", pcl);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deletePcl(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除商品类别代码成功!");
				FindLog.insertLog(req, pclid, "删除商品类别代码");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		return go2Page(req, mapping, "product");
	}
	
	
	/**
	 * 删除面板零件
	 *//*
	public ActionForward delete1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String acyid = req.getParameter("acyid");
		Product pdt = new Product();
		ProductForm pdtForm = (ProductForm) form;
		if (null == acyid || "".equalsIgnoreCase(acyid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(pdtForm, pdt);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Product> mapRequest = new HashMap<String, Product>();
			mapRequest.put("beo", pdt);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete1(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除零件代码成功!");
				FindLog.insertLog(req, acyid, "删除零件代码");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		return go2Page(req, mapping, "product");
	}*/
	
	
	/**
	 * 联动下拉框选择助听器型号
	 */
	public ActionForward queryAudiphone(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {
			String type = req.getParameter("type");
			type = java.net.URLDecoder.decode(type, "UTF-8");
			pdt.setPdttype(type);
			pdt.setPdtcls(req.getParameter("cls"));

			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pdt);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.findBySQL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List<HashMap> list = (Vector) ((HashMap) resEnv.getBody())
						.get("result");
				StringBuilder json = new StringBuilder("[");
				for (HashMap i : list) {
					json.append("{value:'" + i.get("1") + "',caption:'"
							+ i.get("2") + "'},");
				}
				if (list.size() > 0) {
					json.deleteCharAt(json.length() - 1);
				}
				json.append("]");
				System.out.println(json);
				res.setCharacterEncoding("GBK");
				res.getWriter().print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			// res.setCharacterEncoding("GBK");
			// res.getWriter().write("商品代码输入有误");
		}
		return null;
	}

	/**
	 * 查找修改扣率
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward disquery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Product pt = new Product();
		ProductForm pf = (ProductForm) actionForm;
		ActionForward af = new ActionForward();
		String id = req.getParameter("id");
		String forward = "";
		if ("updis".equals(id))
			forward = "/product/updiscount.jsp";
		else
			forward = "/product/disquery.jsp";
		String pdtp = pf.getPdtp();
		try {
			ClassHelper.copyProperties(pf, pt);
			if (pdtp == null || "".equals(pdtp))
				pt.setFileKey("pdt02_001");
			else {
				if ("1".equals(pdtp))
					pt.setFileKey("pdt01_001");
				else
					pt.setFileKey("pdt01_000");
			}
			String hql = queryEnterprise(pt);
			af = init(req, forward, hql);
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的商品！";
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
	 * 批量保存商品信息
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward batchSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Collection<Product> collection = null;
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),
					Product.class);
			ProductFacade productFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("collection", collection);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = productFacade.update(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "产品扣率信息批量保存成功!");
				return go2Page(req, mapping, "product");
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
	 * 扣率添加页面
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	// public ActionForward add(ActionMapping actionMapping,
	// ActionForm actionForm, HttpServletRequest req,
	// HttpServletResponse res) throws Exception {
	// Collection<Product> collection = null;
	// collection = TypeCast.getEntities(new SubmitDataMap(req),Product.class);
	//		
	// Product pt = new Product();
	// ProductForm pf = (ProductForm) actionForm;
	// ActionForward af = new ActionForward();
	// String forward = "/product/disalter.jsp";
	// Connection conn = null;
	// String type = req.getParameter("type");
	// CallableStatement proc = null;
	// int retValue = 1;
	// try {
	// LoginDTO dto1 = (LoginDTO) req.getSession()
	// .getAttribute("LoginDTO");
	// for(int i =0 ;i < collection.size();i ++){
	// pt = (Product)collection.iterator();
	// ClassHelper.copyProperties(pf, pt);
	// conn = DBUtil.getConnection();
	// proc = conn
	// .prepareCall("{ call PRC_DISCOUNT(?, ?, ?, ?, ?) }");
	// proc.setString(1, pf.getPdtid());
	// proc.setDouble(2, pf.getDiscount());
	// proc.setDouble(3, pf.getDisprice());
	// proc.setString(4, type);
	// proc.registerOutParameter(5, Types.INTEGER);
	// proc.execute();
	// int n = proc.getInt(5);
	// if(n != 1)
	// retValue = n;
	// }
	// // conn = DBUtil.getConnection();
	// // proc = conn
	// // .prepareCall("{ call PRC_DISCOUNT(?, ?, ?, ?, ?) }");
	// // proc.setString(1, pf.getPdtid());
	// // proc.setDouble(2, pf.getDiscount());
	// // proc.setDouble(3, pf.getDisprice());
	// // proc.setString(4, type);
	// // proc.registerOutParameter(5, Types.INTEGER);
	// // proc.execute();
	// // int retValue = proc.getInt(5);
	// if (retValue == 1) {
	// String msg = "该商品已完成添加，进入修改！";
	// super.saveSuccessfulMsg(req, msg);
	// return actionMapping.findForward("backspace");
	// } else {
	// ClassHelper.copyProperties(pf,pt);
	// //req.getSession().setAttribute("pdtid", pf.getPdtid());
	// // pt.setFileKey("pdt03_000");
	// // String hql = queryEnterprise(pt);
	// // af = super.init(req, forward, hql);
	// return go2Page(req, actionMapping, "product");
	//				
	// }
	// } catch (Exception e) {
	// this.saveErrors(req, e);
	// return actionMapping.findForward("backspace");
	// } finally {
	// proc.close();
	// DBUtil.closeConnection(conn);
	// }
	// //return af;
	// }

	public ActionForward add(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Collection<Product> collection = null;
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),
					Product.class);
			ProductFacade productFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("collection", collection);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = productFacade.disupdate(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "产品扣率信息批量保存成功!");
				return go2Page(req, actionMapping, "product");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return actionMapping.findForward("backspace");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return actionMapping.findForward("backspace");
		}
	}

	/**
	 * 修改扣率
	 */
	public ActionForward editdis(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Product pt = new Product();
		ProductForm pf = (ProductForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/product/disalter.jsp";
		Connection conn = null;
		try {
			ClassHelper.copyProperties(pf, pt);
			String s = "select count(*) from tbldiscount where dispdtid='"
					+ pf.getPdtid() + "'";
			conn = DBUtil.getConnection();
			if (DBUtil.getRowCount(conn, s) > 0) {
				pt.setFileKey("pdt03_000");
				String hql = queryEnterprise(pt);
				af = super.init(req, forward, hql);
			} else {
				String msg = "该商品的扣率还没有添加！";
				super.saveSuccessfulMsg(req, msg);
				return actionMapping.findForward("backspace");
			}
		} catch (Exception e) {
			this.saveErrors(req, e);
			return actionMapping.findForward("backspace");
		} finally {
			DBUtil.closeConnection(conn);
		}
		return af;
	}

	/**
	 * 批量保存
	 */
	public ActionForward disbatchSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Collection<Product> collection = null;
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),
					Product.class);
			ProductFacade businessFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("collection", collection);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = businessFacade.savedis(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "批量保存扣率信息成功!");
				return go2Page(req, mapping, "product");
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
	 * 查询条件筛选
	 */
	public ActionForward alterquery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Product pt = new Product();
		ProductForm pf = (ProductForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/product/disalter.jsp";
		try {
			ClassHelper.copyProperties(pf, pt);
			pt.setFileKey("pdt03_002");
			String hql = queryEnterprise(pt);
			af = super.init(req, forward, hql);
		} catch (Exception e) {
			this.saveErrors(req, e);
			return actionMapping.findForward("backspace");
		}
		return af;
	}
	
	
	/**
	 * 批量修改商品品牌最小扣率
	 */
	public ActionForward batchModTypDis(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		SubmitDataMap data = new SubmitDataMap(req);
		String[] tdsnmList=data.getParameterValues("tdsnm");
		String[] tdspvoinList=data.getParameterValues("tdspvoin");
		String[] tdspvooutList=data.getParameterValues("tdspvoout");
		String[] tdsntList=data.getParameterValues("tdsnt");
	    List<TypDiscount> tpdList=new ArrayList<TypDiscount>();
	    for(int i=0;i<tdspvoinList.length;i++)
	    {
	    	TypDiscount tpd=new TypDiscount();
	    	tpd.setTdsnm(tdsnmList[i]);
	    	if(null!=tdspvoinList[i]&&!"".equals(tdspvoinList[i]))
	    	{
	    	  tpd.setTdspvoin(Double.parseDouble(tdspvoinList[i]));
	    	}
	    	if(null!=tdspvooutList[i]&&!"".equals(tdspvooutList[i]))
	    	{
	    	  tpd.setTdspvoout(Double.parseDouble(tdspvooutList[i]));
	    	}
	    	tpd.setTdsnt(tdsntList[i]);
	    	tpd.setFileKey("pdt06_004");
	    	tpdList.add(tpd);
	    }
	    try{
	    	ResponseEnvelop resEnv=null;
	    	ProductFacade pdtsFacade = (ProductFacade) getService("ProductFacade");
	    	RequestEnvelop requestEnvelop = new RequestEnvelop();
	    	EventResponse returnValue = new EventResponse();
	    	// 将Application对象放入HashMap
	    	HashMap<String, Object> mapRequest = new HashMap<String, Object>();
	    	mapRequest.put("tpdList", tpdList);
	    	requestEnvelop.setBody(mapRequest);
	    	// 调用对应的Facade业务处理方法
	    	resEnv = pdtsFacade.modTypDis(requestEnvelop);
	    	// 处理返回结果
	    	returnValue = processRevt(resEnv);
	    	if (returnValue.isSucessFlag()) {
	    		super.saveSuccessfulMsg(req, "批量修改成功！" );
	    		return go2Page(req, mapping, "product");
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
	 * 批量修改商品最小扣率
	 */
	public ActionForward batchModProDis(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pdtdiscoinList=data.getParameterValues("pdtdiscoin");
		String[] pdtdiscooutList=data.getParameterValues("pdtdiscoout");
		String[] pdtidList=data.getParameterValues("pdtid");
//		String[] tdspvooutList=data.getParameterValues("tdspvoout");
//		String[] tdsntList=data.getParameterValues("tdsnt");
	    List<Product> pdtList=new ArrayList<Product>();
	    for(int i=0;i<pdtdiscoinList.length;i++)
	    {
             Product pdt=new Product();
             pdt.setPdtid(pdtidList[i]);
             if(null!=pdtdiscoinList[i]&&!"".equals(pdtdiscoinList[i]))
             {
                pdt.setPdtdiscoin(Double.parseDouble(pdtdiscoinList[i]));
             }
             if(null!=pdtdiscooutList[i]&&!"".equals(pdtdiscooutList[i]))
             {
               pdt.setPdtdiscoout(Double.parseDouble(pdtdiscooutList[i]));
             }
             pdtList.add(pdt);
	    }
	    try{
	    	ResponseEnvelop resEnv=null;
	    	ProductFacade pdtsFacade = (ProductFacade) getService("ProductFacade");
	    	RequestEnvelop requestEnvelop = new RequestEnvelop();
	    	EventResponse returnValue = new EventResponse();
	    	// 将Application对象放入HashMap
	    	HashMap<String, Object> mapRequest = new HashMap<String, Object>();
	    	mapRequest.put("pdtList", pdtList);
	    	requestEnvelop.setBody(mapRequest);
	    	// 调用对应的Facade业务处理方法
	    	resEnv = pdtsFacade.modProDis(requestEnvelop);
	    	// 处理返回结果
	    	returnValue = processRevt(resEnv);
	    	if (returnValue.isSucessFlag()) {
	    		super.saveSuccessfulMsg(req, "批量修改成功！" );
	    		return go2Page(req, mapping, "product");
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
