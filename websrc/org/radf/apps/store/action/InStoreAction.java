package org.radf.apps.store.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Store;
import org.radf.apps.commons.entity.Task;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.apps.store.facade.StoreFacade;
import org.radf.apps.store.form.InStoreForm;
import org.radf.apps.task.form.TaskForm;
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

public class InStoreAction extends ActionLeafSupport{
	/**
	 * 批量录入
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		try {
			SubmitDataMap data = new SubmitDataMap(req);
			
//			String[] typeList = data.getParameterValues("stoprotype");
			String[] idList = data.getParameterValues("stoproid"); // 商品(耳机)代码
			String[] nameList = data.getParameterValues("stoproname"); // 商品(耳机)名称
//			String[] dscList = data.getParameterValues("stoprodsc");
//			String[] prcList = data.getParameterValues("stoproprice");// 价格(售价)
			String[] numList = data.getParameterValues("stoamount"); // 数量
//			String[] numUnList = data.getParameterValues("stoamountun"); // 数量单位
			String[] remarkList = data.getParameterValues("storemark");
			String[] isrepair = data.getParameterValues("isrepair");
			String grCli ="";
			Integer amount=0;
			List<Store> storeList=new Vector<Store>();
			for (int i = 0; i < idList.length; i++) {
				Store sto=new Store();
				LoginDTO currentuser=(LoginDTO)req.getSession().getAttribute("LoginDTO");
				if(!"1501000000".equals(currentuser.getBsc001())){
			        grCli = currentuser.getBsc011();//客户代码
				}else{
					grCli = "1501000000";
				}
				String operCd=currentuser.getBsc010(); 
				sto.setStogrcliid(grCli);
				sto.setStoproid(idList[i]);
//				sto.setStoprotype(typeList[i]);
				sto.setStoproname(nameList[i].trim());//删除商品名字中的空格
//				sto.setStoprodsc(dscList[i]);
				sto.setStoamount(Integer.parseInt(numList[i]));
				amount=sto.getStoamount();
				if(amount<0)
				{
					Integer mount=-amount;
					sto.setStoamount(mount);
					sto.setStoactype("-1");
				}
				else
				{
					sto.setStoactype("1");
				}
//				sto.setStoamountun(numUnList[i]);	
			
//				sto.setStoproprice(Double.parseDouble(prcList[i]));
				sto.setStodate(DateUtil.getDate());
				sto.setStoremark(remarkList[i]);
				sto.setIsrepair(isrepair[i]);
				sto.setStooprcd(operCd);
				sto.setStodisc("0");
				storeList.add(sto);
			}
			
//			order.setFolopr(dto.getBsc011());
//			order.setFoldt(DateUtil.getDate());
			StoreFacade storeFacade = (StoreFacade) getService("StoreFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", storeList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = storeFacade.saveStore(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if(amount<0)
				{
					super.saveSuccessfulMsg(req, "商品出库成功!");
				}
				else
				{
					super.saveSuccessfulMsg(req, "新增入库商品信息成功!");
				}
				// 获得新增的订单ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("stoid");
				FindLog.insertLog(req, folno, "新增入库商品信息成功！");

				Store store1 = (Store) ((HashMap) resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
//				ClassHelper.copyProperties(store1, storeForm);
				// 下一个页面，填写订单详细信息
//				return mapping.findForward("inputOrderDetail");
				return go2Page(req, mapping, "store");
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
	
	public ActionForward add1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		InStoreForm inStoreForm = (InStoreForm ) form;
		req.getSession().setAttribute("gctnm", inStoreForm.getGctnm());
		Connection con = null;//加
		try {
			con = DBUtil.getConnection();//加
			DBUtil.beginTrans(con);//加
			
			SubmitDataMap data = new SubmitDataMap(req);
			
//			String[] typeList = data.getParameterValues("stoprotype");
			String[] idList = data.getParameterValues("stoproid"); // 商品(耳机)代码
			String[] nameList = data.getParameterValues("stoproname"); // 商品(耳机)名称
//			String[] dscList = data.getParameterValues("stoprodsc");
//			String[] prcList = data.getParameterValues("stoproprice");// 价格(售价)
			String[] numList = data.getParameterValues("stoamount"); // 数量
//			String[] numUnList = data.getParameterValues("stoamountun"); // 数量单位
			String[] remarkList = data.getParameterValues("storemark");
			String[] isrepair = data.getParameterValues("isrepair");
			String grCli ="";
			Integer amount=0;
			List<Store> storeList=new Vector<Store>();
			for (int i = 0; i < idList.length; i++) {
				Store sto=new Store();
				LoginDTO currentuser=(LoginDTO)req.getSession().getAttribute("LoginDTO");
				if(!"1501000000".equals(currentuser.getBsc001())){
			        grCli = currentuser.getBsc011();//客户代码
				}else{//加
					//grCli = "1501000000";
					List result = (Vector)DBUtil.querySQL(con, "select distinct gctid from tblgrpclient where gctvalid='1' and gctnm='" + inStoreForm.getGctnm()+ "'");
					if(1 == result.size()){
							String gctid = ((HashMap)result.get(0)).get("1").toString();
							grCli=gctid;
					}else {
						//grCli = inStoreForm.getStogrcliid();
						if("惠耳听力总部".equals(inStoreForm.getGctnm())){
							grCli = "1501000000";
						}
					}
					if("".equals(grCli)||null==grCli){
						throw new AppException("所属团体输入有误！(请再输入一遍)"); 
					}
				}
				String operCd=currentuser.getBsc010(); 
				sto.setStogrcliid(grCli);
				sto.setStoproid(idList[i]);
//				sto.setStoprotype(typeList[i]);
				sto.setStoproname(nameList[i].trim());//删除商品名字中的空格
//				sto.setStoprodsc(dscList[i]);
				sto.setStoamount(Integer.parseInt(numList[i]));
				amount=sto.getStoamount();
				if(amount<0)
				{
					Integer mount=-amount;
					sto.setStoamount(mount);
					sto.setStoactype("-1");
				}
				else
				{
					sto.setStoactype("1");
				}
//				sto.setStoamountun(numUnList[i]);	
			
//				sto.setStoproprice(Double.parseDouble(prcList[i]));
				sto.setStodate(DateUtil.getDate());
				sto.setStoremark(remarkList[i]);
				sto.setIsrepair(isrepair[i]);
				sto.setStooprcd(operCd);
				sto.setStodisc("0");
				storeList.add(sto);
			}
			DBUtil.commit(con);//加
			
//			order.setFolopr(dto.getBsc011());
//			order.setFoldt(DateUtil.getDate());
			StoreFacade storeFacade = (StoreFacade) getService("StoreFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", storeList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = storeFacade.saveStore(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if(amount<0)
				{
					super.saveSuccessfulMsg(req, "商品出库成功!");
				}
				else
				{
					super.saveSuccessfulMsg(req, "新增入库商品信息成功!");
				}
				// 获得新增的订单ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("stoid");
				FindLog.insertLog(req, folno, "新增入库商品信息成功！");

				Store store1 = (Store) ((HashMap) resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
//				ClassHelper.copyProperties(store1, storeForm);
				// 下一个页面，填写订单详细信息
//				return mapping.findForward("inputOrderDetail");
				return go2Page(req, mapping, "store");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}finally {//加
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
	}
	
	/**
	 * 批量新增账单明细
	 */
	public ActionForward batchSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fno = req.getParameter("fdtfno");
		SubmitDataMap data = new SubmitDataMap(req);
		try {
			// String[] cltList = data.getParameterValues("fdtcltid"); // 病人代码
			String[] idList = data.getParameterValues("fdtpid"); // 商品(耳机)代码
			String[] prcList = data.getParameterValues("fdtprc");// 价格(售价)
			String[] numList = data.getParameterValues("fdtqnt"); // 数量

			int size = idList.length;
			List<OrderDetail> odList = new Vector<OrderDetail>();
			for (int i = 0; i < size; i++) {
				OrderDetail od = new OrderDetail();
				od.setFdtfno(fno);
				od.setFdtpid(idList[i]);
				od.setFdtprc(Double.parseDouble(prcList[i]));
				od.setFdtqnt(Integer.parseInt(numList[i]));
				
				od.setFdtrqnt(Integer.parseInt(numList[i]));	//初始化商品的剩余数量为订货时的数量
				
				if(Integer.parseInt(numList[i])<0){
					od.setFdtodt(DateUtil.getSystemCurrentTime());
				}
				od.setFdtsqnt(Integer.parseInt(numList[i]));
				odList.add(od);
			}
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", odList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = orderFacade.saveDetail(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "新增订单明细成功!");
				// 获得从业务层返回的日志信息
				return go2Page(req, mapping, "order");
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
 * 查询
 */
	/*
	public ActionForward addQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String forward = "/store/InStore.jsp";
		String fileKey = "sto01_000";
		
		ActionForward af = new ActionForward();
		InStoreForm storeForm = (InStoreForm) form;
		Store store = new Store();
		try {
			ClassHelper.copyProperties(storeForm, store);
			store.setFileKey(fileKey);
			String hql = queryEnterprise(store);
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
	}*/


	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = null;
		String fileKey = null;
		fileKey="sto04_001";
		forward="/store/Query.jsp";
		ActionForward af = new ActionForward();
		InStoreForm inStoreForm = (InStoreForm ) form;
//		Order or = new Order();
		Store sto=new Store();
		try {
			ClassHelper.copyProperties(inStoreForm, sto);
			sto.setFileKey(fileKey);
//			if(null==sto.getStogrcliid()&&"".equals(sto.getStogrcliid()))
//			{
//				ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
//				RequestEnvelop requestEnvelop = new RequestEnvelop();
//				EventResponse returnValue = new EventResponse();
//				// 将Application对象放入HashMap
//				HashMap<String, Object> mapRequest = new HashMap<String, Object>();
//				mapRequest.put("beo", sto);
//				requestEnvelop.setBody(mapRequest);
//				// 调用对应的Facade业务处理方法
//				ResponseEnvelop resEnv = pdtFacade.query(requestEnvelop);
//				// 处理返回结果
//				returnValue = processRevt(resEnv);
//				if (returnValue.isSucessFlag()) {
//					super.saveSuccessfulMsg(req, "查询商品价格成功!");
//					List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
//					if (list.size() > 1)
//						throw new Exception();
//					ClassHelper.copyProperties(list.get(0), pdt);
//			}
			String hql = queryEnterprise(sto);
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
	
	/*
	 * 直属店库存量查询
	 */
	public ActionForward queryStorage(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		String order = req.getParameter("order");	//"order"来源？
		String forward = null;
		String fileKey = null;
//		if ("del".equals(order)) {
//			fileKey = "ord02_000";
//			forward = "/order/del.jsp";
//		} else if ("delivery".equals(order)) { // 为了发货而查询
//			forward = "/order/query1.jsp";
//			fileKey = "ord02_001";
//		} else {
		
			fileKey = "sto05_001";
			forward = "/store/StorageQuery.jsp";
//		}
			
		ActionForward af = new ActionForward();
		InStoreForm storageForm = (InStoreForm ) form;
//		Order or = new Order();
		Store sto=new Store();
		try {
			ClassHelper.copyProperties(storageForm,sto);
			
			String grCli="";
			LoginDTO currentuser=(LoginDTO)req.getSession().getAttribute("LoginDTO");
			if(!"1501000000".equals(currentuser.getBsc001())){
				grCli = currentuser.getBsc012();//客户名称
				sto.setGctnm(grCli);
			}else{
				
			}
			
			sto.setFileKey(fileKey);
			String hql = queryEnterprise(sto);
//			hql+=" where 1=1";
//			if(null!=sto.getGctnm()&&!"".equals(sto.getGctnm()))
//			{
//				hql+=" and(case when c.aab300='惠耳听力总部' then c.aab300 else g.gctnm end) like '%"+sto.getGctnm()+"%'";
//			}
//			if(null!=sto.getStocllar()&&!"".equals(sto.getStocllar()))
//			{
//				hql+=" and s.stocllar='"+sto.getStocllar()+"'";
//			}
//			if(null!=sto.getStoclmid()&&!"".equals(sto.getStoclmid()))
//			{
//				hql+="and s.stoclmid='"+sto.getStoclmid()+"'";
//			}
//			if(null!=sto.getStoclsam()&&!"".equals(sto.getStoclsam()))
//			{
//				hql+="and stoclsam='"+sto.getStoclsam()+"'";
//			}
//			if(null!=sto.getStoproname()&&!"".equals(sto.getStoproname()))
//			{
//				hql+=" and s.stoproname like '%"+sto.getStoproname()+"%'";
//			}
//			if(null!=storageForm.getStart()&&!"".equals(storageForm.getStart()))
//			{
//				hql+=" and s.stodate>=to_date('" +storageForm.getStart()+"','yyyy-MM-DD')";
//			}
//			if(null!=storageForm.getEnd()&&!"".equals(storageForm.getEnd()))
//			{
//				hql+=" and s.stodate<=to_date('" +storageForm.getEnd()+"','yyyy-MM-DD')";;
//			}
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
	

	/*
	 * 加盟店库存量查询
	 * 
	 * 
	 */
	public ActionForward queryStorageJmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		String order = req.getParameter("order");	//"order"来源？
		String forward = null;
		String fileKey = null;
//		if ("del".equals(order)) {
//			fileKey = "ord02_000";
//			forward = "/order/del.jsp";
//		} else if ("delivery".equals(order)) { // 为了发货而查询
//			forward = "/order/query1.jsp";
//			fileKey = "ord02_001";
//		} else {
		
			fileKey = "sto05_001";
			forward = "/store/JmdStorageQuery.jsp";
//		}
			
		ActionForward af = new ActionForward();
		InStoreForm storageForm = (InStoreForm ) form;
//		Order or = new Order();
		Store sto=new Store();
		try {
			ClassHelper.copyProperties(storageForm,sto);
			
			String grCli="";
			LoginDTO currentuser=(LoginDTO)req.getSession().getAttribute("LoginDTO");
			if(!"1501000000".equals(currentuser.getBsc001())){
				grCli = currentuser.getBsc012();//客户名称
				sto.setGctnm(grCli);
			}else{
				
			}
			
			sto.setFileKey(fileKey);
			String hql = queryEnterprise(sto);
//			hql+=" where 1=1";
//			if(null!=sto.getGctnm()&&!"".equals(sto.getGctnm()))
//			{
//				hql+=" and(case when c.aab300='惠耳听力总部' then c.aab300 else g.gctnm end) like '%"+sto.getGctnm()+"%'";
//			}
//			if(null!=sto.getStocllar()&&!"".equals(sto.getStocllar()))
//			{
//				hql+=" and s.stocllar='"+sto.getStocllar()+"'";
//			}
//			if(null!=sto.getStoclmid()&&!"".equals(sto.getStoclmid()))
//			{
//				hql+="and s.stoclmid='"+sto.getStoclmid()+"'";
//			}
//			if(null!=sto.getStoclsam()&&!"".equals(sto.getStoclsam()))
//			{
//				hql+="and stoclsam='"+sto.getStoclsam()+"'";
//			}
//			if(null!=sto.getStoproname()&&!"".equals(sto.getStoproname()))
//			{
//				hql+=" and s.stoproname like '%"+sto.getStoproname()+"%'";
//			}
//			if(null!=storageForm.getStart()&&!"".equals(storageForm.getStart()))
//			{
//				hql+=" and s.stodate>=to_date('" +storageForm.getStart()+"','yyyy-MM-DD')";
//			}
//			if(null!=storageForm.getEnd()&&!"".equals(storageForm.getEnd()))
//			{
//				hql+=" and s.stodate<=to_date('" +storageForm.getEnd()+"','yyyy-MM-DD')";;
//			}
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
	 * 入库
	 */
	public ActionForward inDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//InStoreForm storageForm = (InStoreForm ) form;
		Store sto=new Store();
		ActionForward af = new ActionForward();
		String forward = "/store/InStoreDetail.jsp";
		try {
			//ClassHelper.copyProperties(storageForm, sto);
			sto.setStoid(-1);
			sto.setFileKey("sto06_000");
			String hql = queryEnterprise(sto);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/** 
	 * 库存量查询时入库
	 */
	public ActionForward inDetail1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//InStoreForm storageForm = (InStoreForm ) form;
		Store sto=new Store();
		ActionForward af = new ActionForward();
		String forward = "/store/InStoreDetailadd.jsp";
		InStoreForm inStoreForm = (InStoreForm ) form;
		try {
			ClassHelper.copyProperties(inStoreForm, sto);
			String hql="";
			if("".equals(sto.getStoproid()) || null==sto.getStoproid())
			{
				sto.setStoid(-1);
				sto.setFileKey("sto06_000");
				hql = queryEnterprise(sto);
			}
			else
			{
				hql="select distinct stoproid,stoproname,isrepair from tblsto where stoproid='"+sto.getStoproid()+"'";
				if(sto.getIsrepair().equals("1")){
					hql += " and isrepair = '1'";
				}else{
					hql += " and isrepair is null";
				}
			}
			//String hql = queryEnterprise(sto);
			sto.setStoproid("");
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	public ActionForward storageDetail(ActionMapping mapping,ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Store sto=new Store();
		InStoreForm inStoreForm = (InStoreForm ) form;
		ActionForward af = new ActionForward();
		String forward = "/store/StorageDetail.jsp";
		String fileKey = null;
		fileKey="sto04_002";
		//forward="/store/Query.jsp";
		try {
			ClassHelper.copyProperties(inStoreForm, sto);
			sto.setFileKey(fileKey);
			String hql = queryEnterprise(sto);
			af = super.init(req, forward, hql);
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
			req.getSession().setAttribute("gctnm", inStoreForm.getGctnm());
			req.getSession().setAttribute("stoproname", inStoreForm.getStoproname());

		}  catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		
		return	af;
	}
	
	public ActionForward queryDetail(ActionMapping mapping,ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Store sto=new Store();
		InStoreForm inStoreForm = (InStoreForm ) form;
		String gctnm=req.getSession().getAttribute("gctnm").toString();
		String stoproname=req.getSession().getAttribute("stoproname").toString();
		ActionForward af = new ActionForward();
		String forward = "/store/StorageDetail.jsp";
		String fileKey = null;
		fileKey="sto04_003";
		//forward="/store/Query.jsp";
		try {
			ClassHelper.copyProperties(inStoreForm, sto);
			sto.setFileKey(fileKey);
			sto.setGctnm(gctnm);
			sto.setStoproname(stoproname);
			String hql = queryEnterprise(sto);
			af = super.init(req, forward, hql);
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}

		}  catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		
		return	af;
	}
	public ActionForward panku(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//InStoreForm storageForm = (InStoreForm ) form;
		Store sto=new Store();
		ActionForward af = new ActionForward();
		String forward = "/store/InStoreDetailadd.jsp";
		InStoreForm inStoreForm = (InStoreForm ) form;
		Connection con = null;
		try {
			ClassHelper.copyProperties(inStoreForm, sto);
			
			StoreFacade storeFacade = (StoreFacade) getService("StoreFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sto);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = storeFacade.panku(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(req, "盘库成功！");
					return mapping.findForward("backspace");
				}
				else
				{
					super.saveSuccessfulMsg(req, "盘库失败！");
					return mapping.findForward("backspace");
				}
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			this.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	public ActionForward queryHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = null;
		String fileKey = null;
		fileKey="sto04_001_his";
		forward="/store/QueryHis.jsp";
		ActionForward af = new ActionForward();
		InStoreForm inStoreForm = (InStoreForm ) form;
//		Order or = new Order();
		Store sto=new Store();
		try {
			ClassHelper.copyProperties(inStoreForm, sto);
			sto.setFileKey(fileKey);
//			if(null==sto.getStogrcliid()&&"".equals(sto.getStogrcliid()))
//			{
//				ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
//				RequestEnvelop requestEnvelop = new RequestEnvelop();
//				EventResponse returnValue = new EventResponse();
//				// 将Application对象放入HashMap
//				HashMap<String, Object> mapRequest = new HashMap<String, Object>();
//				mapRequest.put("beo", sto);
//				requestEnvelop.setBody(mapRequest);
//				// 调用对应的Facade业务处理方法
//				ResponseEnvelop resEnv = pdtFacade.query(requestEnvelop);
//				// 处理返回结果
//				returnValue = processRevt(resEnv);
//				if (returnValue.isSucessFlag()) {
//					super.saveSuccessfulMsg(req, "查询商品价格成功!");
//					List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
//					if (list.size() > 1)
//						throw new Exception();
//					ClassHelper.copyProperties(list.get(0), pdt);
//			}
			String hql = queryEnterprise(sto);
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

	