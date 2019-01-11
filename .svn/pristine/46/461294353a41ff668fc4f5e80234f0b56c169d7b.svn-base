package org.radf.apps.order.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.client.group.facade.GroupClientFacade;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.commons.entity.Audiogram;
import org.radf.apps.commons.entity.Diagnose;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.commons.chart.ChartParams;
import org.radf.commons.chart.dataproducer.CategoryDataProducer;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
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

public class CustomOrderAction extends ActionLeafSupport {

	public ActionForward forwardClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictnm = req.getParameter("ictnm");
		OrderHeaderForm ohf = (OrderHeaderForm) form;
		HttpSession session = req.getSession();
		if (null != ictnm) {
			ohf.setIctnm(ictnm);
		}
		String tp = req.getParameter("tp");
		String forward = "";
		if (tp.equals("cus")) {
			forward = "addClientCus";
		} else if (tp.equals("ear")) {
			forward = "addClientEar";
		} else if (tp.equals("cusRep")) {
			forward = "addClientCusRep";
		} else if (tp.equals("earRep")) {
			forward = "addClientEarRep";
		} else if (tp.equals("add")) {
			forward = "addClient";
		}
		ClassHelper.copyProperties(ohf, form);
		return mapping.findForward(forward);
	}

	/**
	 * 查询订单输入页面
	 */
	public ActionForward queryClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String tp = req.getParameter("tp");
		String forward = null;
		String fileKey = null;
		// if ("del".equals(order)) {
		// fileKey = "ord02_000";
		// forward = "/order/del.jsp";
		// } else if ("delivery".equals(order)) { // 为了发货而查询
		// forward = "/order/query1.jsp";
		// fileKey = "ord02_001";
		// } else {
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		SingleClient sc=new SingleClient();
		fileKey = "clt05_001";
		if (tp.equals("cus")) {
//			fileKey = "clt05_001";
			forward = "/order/customSelect.jsp";
		} else if (tp.equals("rep")) {
			fileKey = "clt05_000";
			forward = "/order/customRepairSelect.jsp";
		} else if (tp.equals("ear")) {
			forward = "/order/earCustomSelect.jsp";
		} else if (tp.equals("earRep")) {
			sc.setIctsid(orderForm.getFdtsid());
			fileKey = "clt05_002";
			forward = "/order/earRepSelect.jsp";
		} else if (tp.equals("add")) {
			forward = "/client/single/queryClient.jsp";
		}
		// }


//		Order or = new Order();
		try {
			ClassHelper.copyProperties(orderForm, sc);
//			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
//			String gctid = dto.getBsc011();
//			if (!"1501000000".equals(dto.getBsc001())) {
//				orderForm.setIctgctid(gctid);// 如果不是总部，则设置客户代码为登录名
//			} else {
//				if (null != orderForm.getIctgctid()
//						&& !"".equals(orderForm.getIctgctid())
//						&& "A".equals(orderForm.getIctgctid().substring(0, 1))) {
//					if ("A0065".equals(orderForm.getIctgctid())) {// A0065为听力总部所代表的客户代码
//						orderForm.setIctgctid("A0065");
//					} else {
//						orderForm.setIctgctid("直属店不能在总部下订单");// 如果是总部，并且输入了以A开头的客户代码，则过滤掉。
//						super.saveSuccessfulMsg(req, "直属店不能在总部下订单");
//						return mapping.findForward("backspace");
//					}
//				}
//			}
//			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
//			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			/*
			 * zyf
			 */
			if(null!=orderForm.getGctnm()&&!"".equals(orderForm.getGctnm()))
			{
				GroupClient gr=new GroupClient();
				gr.setGctnm(orderForm.getGctnm());
			GroupClientFacade gcFacade = (GroupClientFacade) getService("GroupClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			gr.setFileKey("clt02_000");
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", gr);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = gcFacade.query(requestEnvelop);  //默认为order_select
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
//				super.saveSuccessfulMsg(req, "查询商品价格成功!");
				if(((HashMap) resEnv.getBody()).get("beo")==null){
					
				}else{
					List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
					if (list.size() > 1)
						throw new Exception();
					ClassHelper.copyProperties(list.get(0), gr);
				}

				sc.setIctgctid(gr.getGctid());
			}
		}
				sc.setFileKey(fileKey);
				String hql = queryEnterprise(sc);
				af = super.init(req, forward, hql);
				// 检查是否存在？
				if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
					String msg = "没有查询到符合条件的记录！";
					super.saveSuccessfulMsg(req, msg);
				}
//				double price = pdt.getPdtprc();
//				String folctid=or.getFolctid();
	
//				res.setCharacterEncoding("GBK");
//				res.getWriter().write("[{price:" + price + "}]");
//			ClassHelper.copyProperties(orderForm, or);	
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 保存订单基本信息
	 */
	public ActionForward saveCustomOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		try {
			ClassHelper.copyProperties(orderForm, order);
			// if (order.getFolctid() == null || "".equals(order.getFolctid()))
			// {
			// super.saveSuccessfulMsg(req, "请正确录入团体客户");
			// return mapping.findForward("backspace");
			// }
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			order.setFolopr(dto.getBsc010());
			order.setFoldt(DateUtil.getDate());
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", order);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = orderFacade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("folno");

				FindLog.insertLog(req, folno, "新增订单基本信息成功！");

				Order order1 = (Order) ((HashMap) resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
				ClassHelper.copyProperties(order1, orderForm);
				// 下一个页面，填写订单详细信息
				return mapping.findForward("inputOrderDetail");
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

	/*
	 * 定制订单输入
	 */
	public ActionForward inputOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		String tp = req.getParameter("tp");
		String repid=req.getParameter("repid");
		String forward = "";
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		SingleClient sc = new SingleClient();
		sc.setIctid(ictid);
		if (null == ictid || "".equalsIgnoreCase(ictid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			// ClassHelper.copyProperties(scForm, sc);
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			mapRequest.put("repid", repid);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
				// List listci1 = (ArrayList) mapResponse.get("beo1");// 门诊信息
				List listci2 = (ArrayList) mapResponse.get("beo2");// 听力图信息
				String foldt = (String) mapResponse.get("foldt");// 听力图信息
				String reppid = (String) mapResponse.get("reppid");
				String reppnm = (String) mapResponse.get("pdtnm");
				ClassHelper.copyProperties(listci.get(0), orderForm);
				ClassHelper.copyProperties(listci2.get(0), orderForm);
				orderForm.setRepid(repid);
				orderForm.setReppid(reppid);
				orderForm.setReppnm(reppnm);
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//				sdf.parse(foldt);
				if(!"".equals(foldt)&& null!=foldt)
				{
					foldt=foldt.substring(0, 10);
					orderForm.setRepcusdt(foldt);
				}
				req.getSession().setAttribute("ictid", ictid);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		if (tp.equals("rep")) {
//			forward = "inputCusRepOrder";
			return new ActionForward("/CustomOrderAction.do?method=addClient&tp=cusRep&type=firm");
		} else if (tp.equals("cus")) {
//			forward = "inputCustomOrder";
			return new ActionForward("/CustomOrderAction.do?method=addClient&tp=cus&type=firm");
		}
		
		return mapping.findForward(forward);
	}

	/*
	 * 耳模订单输入
	 */
	public ActionForward inputEarOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		String fdtsid = req.getParameter("fdtsid");
		String tp = req.getParameter("tp");
//		String repid=req.getParameter("repid");
//		String tmepid=req.getParameter("tmepid");
		String forward = "";
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		SingleClient sc = new SingleClient();
		sc.setIctid(ictid);
		if (null == ictid || "".equalsIgnoreCase(ictid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			// ClassHelper.copyProperties(scForm, sc);
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, SingleClient> mapRequest = new HashMap<String, SingleClient>();
			mapRequest.put("beo", sc);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
				// List listci1 = (ArrayList) mapResponse.get("beo1");// 门诊信息
				List listci2 = (ArrayList) mapResponse.get("beo2");// 听力图信息
//				String fdtsid = (String ) mapResponse.get("fdtsid");//
				ClassHelper.copyProperties(listci.get(0), orderForm);
				ClassHelper.copyProperties(listci2.get(0), orderForm);
				orderForm.setFdtsid(fdtsid);
				req.getSession().setAttribute("ictid", ictid);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		if (tp.equals("ear")) {
//			forward = "inputEarRepOrder";
			return new ActionForward("/CustomOrderAction.do?method=addClient&tp=ear&type=firm");
		} else if (tp.equals("earRep")) {
//			forward = "inputEarOrder";
			return new ActionForward("/CustomOrderAction.do?method=addClient&tp=earRep&type=firm");
		}

		return mapping.findForward(forward);
	}

	/**
	 * 新增订单
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		Diagnose dg = new Diagnose();
		ClassHelper.copyProperties(orderForm, dg);
		ClassHelper.copyProperties(orderForm, order);
		// CustomizationForm cf = new CustomizationForm();
		// String company = req.getParameter("company");
		String num = req.getParameter("num");
		// String gid = req.getParameter("gid");
		String cid = req.getParameter("cid"); // 个人客户编号
		String cnm = req.getParameter("cnm");
		String isExa = req.getParameter("isExa");
//		SubmitDataMap data=new SubmitDataMap(req);
		String mds=req.getParameter("md");
		String lr=req.getParameter("lr");
		// String rsheltp=req.getParameter("rsheltp");
		String tp = req.getParameter("tp");
		String grCli = "";
		if(order.getisleft().equals("1")){
			order.setLeftpay(0);
		}else{
			order.setLeftpay(order.getfeeleft());
		}
		if(order.getisright().equals("1")){
			order.setRightpay(0);
		}else{
			order.setRightpay(order.getfeeright());
		}
		LoginDTO currentuser = (LoginDTO) req.getSession().getAttribute(
				"LoginDTO");
		if (!"1501000000".equals(currentuser.getBsc001())) {
			grCli = currentuser.getBsc011();// 客户代码
		} else {
			grCli = order.getIctgctid();
		}
		String operCd = currentuser.getBsc010();
		order.setFolindctid(cid);
		dg.setDgnctid(cid);
		List<OrderDetail> odList = new Vector<OrderDetail>();
		if (num.equals("1")) {
			String plr = req.getParameter("plr");
			if (plr.equals("l")) {
				String lpid = req.getParameter("pid"); // 商品代码 （耳机代码）
				OrderDetail od = new OrderDetail();
				od.setFdtcltid(cid);
				od.setFdtpid(lpid);
				od.setFdtmklr("0");
				od.setFdtsheltp(orderForm.getFdtsheltpl());
				od.setFdtcltnm(cnm);
				od.setFdtdisc(dg.getDgnldct());// 左耳扣率
				od.setDgndoc(order.getDgndoc());
				odList.add(od);

			} else if (plr.equals("r")) {
				String rpid = req.getParameter("pid");
				OrderDetail od = new OrderDetail();
				od.setFdtcltid(cid);
				od.setFdtpid(rpid);
				od.setFdtmklr("1");
				od.setFdtsheltp(orderForm.getFdtsheltpr());
				od.setFdtcltnm(cnm);
				od.setFdtdisc(dg.getDgnrdct());// 右耳扣率
				od.setDgndoc(order.getDgndoc());
				odList.add(od);
			}
		} else if (num.equals("2")) {
			String lpid = req.getParameter("lpid");
			String rpid = req.getParameter("rpid");
			OrderDetail odl = new OrderDetail();
			odl.setFdtcltid(cid);
			odl.setFdtpid(lpid);
			odl.setFdtmklr("0");
			odl.setFdtsheltp(orderForm.getFdtsheltpl());
			odl.setFdtcltnm(cnm);
			odl.setFdtdisc(dg.getDgnldct());// 左耳扣率
			odl.setDgndoc(order.getDgndoc());
			odList.add(odl);
			OrderDetail odr = new OrderDetail();
			odr.setFdtcltid(cid);
			odr.setFdtpid(rpid);
			odr.setFdtmklr("1");
			odr.setFdtsheltp(orderForm.getFdtsheltpr());
			odr.setFdtcltnm(cnm);
			odr.setFdtdisc(dg.getDgnrdct());// 右耳扣率
			odr.setDgndoc(order.getDgndoc());
			odList.add(odr);//odList存放左右耳的订单详细情况。
		}
		try {
			// LoginDTO dto1 = (LoginDTO) req.getSession()
			// .getAttribute("LoginDTO");
			// 获取服务接口
			ResponseEnvelop resEnv = null;
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("gid", grCli);
			// mapRequest.put("cid", cid);
			 mapRequest.put("lr", lr);
			mapRequest.put("md", mds);
			mapRequest.put("opr", operCd);
			mapRequest.put("isExa", isExa);
			mapRequest.put("diagnose", dg);
			mapRequest.put("order", order);//将订单信息放入mapRequest
			// mapRequest.put("rdiscount", rdiscount);
			requestEnvelop.setBody(mapRequest);
			if (tp.equals("c")) {
				resEnv = facade.commit(requestEnvelop);
			} else if (tp.equals("b")) {
				// 调用对应的Facade业务处理方法
				resEnv = facade.saveCustomOrder(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String tmkfno1 = (String) ((HashMap) resEnv.getBody())
						.get("tmkfno1");
				String tmkfno2 = (String) ((HashMap) resEnv.getBody())
						.get("tmkfno2");
				EventResponse Env=null;
				HashMap<String,Object> mapResponse=(HashMap) resEnv.getBody();
				/*List alertList = (ArrayList) mapResponse.get("beo1");
				if(null!=alertList)
				{
					super.saveSuccessfulAndFailureMsg(req,Env, "是", "否");
					super.saveSuccessfulMsg(req, "该订单已提交且没有被回退,无法修改、删除！");
					return mapping.findForward("query");
				}*/
					
				if (tmkfno2 == null || "".equals(tmkfno2)){
						// FindLog.insertLog(req, folno, "新增助听器维修订单成功！");
						if (tp.equals("c")) {
							super.saveSuccessfulMsg(req, "定制录单并提交成功！订单号："+ tmkfno1);	
						} 
						else if (tp.equals("b")) {
							super.saveSuccessfulMsg(req, "定制录单成功！订单号："	+ tmkfno1);	
						} 
				}
				else {
					if (tp.equals("c")) {
						super.saveSuccessfulMsg(req, "定制录单并提交成功！订单号："+ tmkfno1 + "," + tmkfno2);		
					} else if (tp.equals("b")) {
						super.saveSuccessfulMsg(req, "定制录单成功！订单号："+ tmkfno1 + "," + tmkfno2);			
					}
				}

				return mapping.findForward("customSelect");
				// return go2Page(req, mapping, "order"); //返回前一页 ，导致js
				// alert框点不掉

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

	public ActionForward addClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SingleClient sc = new SingleClient();
//		Diagnose dg = new Diagnose();
		OrderHeaderForm scForm = (OrderHeaderForm) form;
		String tp = req.getParameter("tp");
		String type = req.getParameter("type");
		String ictid2 = req.getParameter("ictid");
//		String ictid = req.getParameter("ictid");
		String forward = "";
		String ictPro,ictCity,ictCounty;
		String ictPhone,ictLandLine;
		String tel = "";
		try {
			/*
			 * if (scForm.getIctgctid() == null ||
			 * "".equals(scForm.getIctgctid())) { super.saveSuccessfulMsg(req,
			 * "请正确录入所属团体"); return mapping.findForward("backspace"); }
			 */
			ClassHelper.copyProperties(scForm, sc);
			transToString(scForm,sc);
//			ClassHelper.copyProperties(scForm, dg);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			sc.setIctoprcd(dto1.getBsc011());
			sc.setIctdate(DateUtil.getDate());
			if (!"1501000000".equals(dto1.getBsc001())) {
				sc.setIctgctid(dto1.getBsc011());// 直属店则插入直属店客户代码
			}

//			if (dg.getDgnlid() == null || "".equals(dg.getDgnlid())) {
//				dg.setDgnldprc(null);
//			}
//			if (dg.getDgnrid() == null || "".equals(dg.getDgnrid())) {
//				dg.setDgnrdprc(null);
//			}
			
			ictPro = sc.getIctpro()==null?"":sc.getIctpro().split(",")[1];//省份
			ictCity = sc.getIctcity()==null?"":sc.getIctcity().split(",")[1];//市
			ictCounty = sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[1];//县
			ictPhone = sc.getIctphone()==null?"":sc.getIctphone();//手机
			ictLandLine = sc.getIctlandline()==null?"":sc.getIctlandline();//固定电话
/*			sc.setIctpro(sc.getIctpro()==null?"":sc.getIctpro().split(",")[0]);
			sc.setIctcity(sc.getIctcity()==null?"":sc.getIctcity().split(",")[0]);
			sc.setIctcounty(sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[0]);*/
			if(ictPro!=null&&!"".equals(ictPro)){
				sc.setIctaddr(ictPro+ictCity+ictCounty+sc.getIctdetailaddr());
			}
			if(!"".equals(ictPhone)){
				tel = tel + ictPhone + "    ";
			}
			if(!"".equals(ictLandLine)){
				tel = tel + ictLandLine;
			}
			if(!"".equals(tel)&&tel!=null){
				sc.setIcttel(tel);
			}
			List<Audiogram> agList = new Vector<Audiogram>();
			Audiogram lg250 = new Audiogram();
			lg250.setAdglre("L");
			lg250.setAdgtp("G");
			lg250.setAdgfq(250);
			lg250.setAdgadt(scForm.getLg250());
			agList.add(lg250);
			Audiogram lg500 = new Audiogram();
			lg500.setAdglre("L");
			lg500.setAdgtp("G");
			lg500.setAdgfq(500);
			lg500.setAdgadt(scForm.getLg500());
			agList.add(lg500);
			Audiogram lg750 = new Audiogram();
			lg750.setAdglre("L");
			lg750.setAdgtp("G");
			lg750.setAdgfq(750);
			lg750.setAdgadt(scForm.getLg750());
			agList.add(lg750);
			Audiogram lg1000 = new Audiogram();
			lg1000.setAdglre("L");
			lg1000.setAdgtp("G");
			lg1000.setAdgfq(1000);
			lg1000.setAdgadt(scForm.getLg1000());
			agList.add(lg1000);
			Audiogram lg1500 = new Audiogram();
			lg1500.setAdglre("L");
			lg1500.setAdgtp("G");
			lg1500.setAdgfq(1500);
			lg1500.setAdgadt(scForm.getLg1500());
			agList.add(lg1500);
			Audiogram lg2000 = new Audiogram();
			lg2000.setAdglre("L");
			lg2000.setAdgtp("G");
			lg2000.setAdgfq(2000);
			lg2000.setAdgadt(scForm.getLg2000());
			agList.add(lg2000);
			Audiogram lg3000 = new Audiogram();
			lg3000.setAdglre("L");
			lg3000.setAdgtp("G");
			lg3000.setAdgfq(3000);
			lg3000.setAdgadt(scForm.getLg3000());
			agList.add(lg3000);
			Audiogram lg4000 = new Audiogram();
			lg4000.setAdglre("L");
			lg4000.setAdgtp("G");
			lg4000.setAdgfq(4000);
			lg4000.setAdgadt(scForm.getLg4000());
			agList.add(lg4000);
			Audiogram lg6000 = new Audiogram();
			lg6000.setAdglre("L");
			lg6000.setAdgtp("G");
			lg6000.setAdgfq(6000);
			lg6000.setAdgadt(scForm.getLg6000());
			agList.add(lg6000);

			Audiogram lq250 = new Audiogram();
			lq250.setAdglre("L");
			lq250.setAdgtp("Q");
			lq250.setAdgfq(250);
			lq250.setAdgadt(scForm.getLq250());
			agList.add(lq250);
			Audiogram lq500 = new Audiogram();
			lq500.setAdglre("L");
			lq500.setAdgtp("Q");
			lq500.setAdgfq(500);
			lq500.setAdgadt(scForm.getLq500());
			agList.add(lq500);
			Audiogram lq750 = new Audiogram();
			lq750.setAdglre("L");
			lq750.setAdgtp("Q");
			lq750.setAdgfq(750);
			lq750.setAdgadt(scForm.getLq750());
			agList.add(lq750);
			Audiogram lq1000 = new Audiogram();
			lq1000.setAdglre("L");
			lq1000.setAdgtp("Q");
			lq1000.setAdgfq(1000);
			lq1000.setAdgadt(scForm.getLq1000());
			agList.add(lq1000);	
			Audiogram lq1500 = new Audiogram();
			lq1500.setAdglre("L");
			lq1500.setAdgtp("Q");
			lq1500.setAdgfq(1500);
			lq1500.setAdgadt(scForm.getLq1500());
			agList.add(lq1500);
			Audiogram lq2000 = new Audiogram();
			lq2000.setAdglre("L");
			lq2000.setAdgtp("Q");
			lq2000.setAdgfq(2000);
			lq2000.setAdgadt(scForm.getLq2000());
			agList.add(lq2000);
			Audiogram lq3000 = new Audiogram();
			lq3000.setAdglre("L");
			lq3000.setAdgtp("Q");
			lq3000.setAdgfq(3000);
			lq3000.setAdgadt(scForm.getLq3000());
			agList.add(lq3000);
			Audiogram lq4000 = new Audiogram();
			lq4000.setAdglre("L");
			lq4000.setAdgtp("Q");
			lq4000.setAdgfq(4000);
			lq4000.setAdgadt(scForm.getLq4000());
			agList.add(lq4000);
			Audiogram lq6000 = new Audiogram();
			lq6000.setAdglre("L");
			lq6000.setAdgtp("Q");
			lq6000.setAdgfq(6000);
			lq6000.setAdgadt(scForm.getLq6000());
			agList.add(lq6000);

			Audiogram rg250 = new Audiogram();
			rg250.setAdglre("R");
			rg250.setAdgtp("G");
			rg250.setAdgfq(250);
			rg250.setAdgadt(scForm.getRg250());
			agList.add(rg250);
			Audiogram rg500 = new Audiogram();
			rg500.setAdglre("R");
			rg500.setAdgtp("G");
			rg500.setAdgfq(500);
			rg500.setAdgadt(scForm.getRg500());
			agList.add(rg500);
			Audiogram rg750 = new Audiogram();
			rg750.setAdglre("R");
			rg750.setAdgtp("G");
			rg750.setAdgfq(750);
			rg750.setAdgadt(scForm.getRg750());
			agList.add(rg750);
			Audiogram rg1000 = new Audiogram();
			rg1000.setAdglre("R");
			rg1000.setAdgtp("G");
			rg1000.setAdgfq(1000);
			rg1000.setAdgadt(scForm.getRg1000());
			agList.add(rg1000);
			Audiogram rg1500 = new Audiogram();
			rg1500.setAdglre("R");
			rg1500.setAdgtp("G");
			rg1500.setAdgfq(1500);
			rg1500.setAdgadt(scForm.getRg1500());
			agList.add(rg1500);
			Audiogram rg2000 = new Audiogram();
			rg2000.setAdglre("R");
			rg2000.setAdgtp("G");
			rg2000.setAdgfq(2000);
			rg2000.setAdgadt(scForm.getRg2000());
			agList.add(rg2000);
			Audiogram rg3000 = new Audiogram();
			rg3000.setAdglre("R");
			rg3000.setAdgtp("G");
			rg3000.setAdgfq(3000);
			rg3000.setAdgadt(scForm.getRg3000());
			agList.add(rg3000);
			Audiogram rg4000 = new Audiogram();
			rg4000.setAdglre("R");
			rg4000.setAdgtp("G");
			rg4000.setAdgfq(4000);
			rg4000.setAdgadt(scForm.getRg4000());
			agList.add(rg4000);
			Audiogram rg6000 = new Audiogram();
			rg6000.setAdglre("R");
			rg6000.setAdgtp("G");
			rg6000.setAdgfq(6000);
			rg6000.setAdgadt(scForm.getRg6000());
			agList.add(rg6000);

			Audiogram rq250 = new Audiogram();
			rq250.setAdglre("R");
			rq250.setAdgtp("Q");
			rq250.setAdgfq(250);
			rq250.setAdgadt(scForm.getRq250());
			agList.add(rq250);
			Audiogram rq500 = new Audiogram();
			rq500.setAdglre("R");
			rq500.setAdgtp("Q");
			rq500.setAdgfq(500);
			rq500.setAdgadt(scForm.getRq500());
			agList.add(rq500);
			Audiogram rq750 = new Audiogram();
			rq750.setAdglre("R");
			rq750.setAdgtp("Q");
			rq750.setAdgfq(750);
			rq750.setAdgadt(scForm.getRq750());
			agList.add(rq750);
			Audiogram rq1000 = new Audiogram();
			rq1000.setAdglre("R");
			rq1000.setAdgtp("Q");
			rq1000.setAdgfq(1000);
			rq1000.setAdgadt(scForm.getRq1000());
			agList.add(rq1000);
			Audiogram rq1500 = new Audiogram();
			rq1500.setAdglre("R");
			rq1500.setAdgtp("Q");
			rq1500.setAdgfq(1500);
			rq1500.setAdgadt(scForm.getRq1500());
			agList.add(rq1500);
			Audiogram rq2000 = new Audiogram();
			rq2000.setAdglre("R");
			rq2000.setAdgtp("Q");
			rq2000.setAdgfq(2000);
			rq2000.setAdgadt(scForm.getRq2000());
			agList.add(rq2000);
			Audiogram rq3000 = new Audiogram();
			rq3000.setAdglre("R");
			rq3000.setAdgtp("Q");
			rq3000.setAdgfq(3000);
			rq3000.setAdgadt(scForm.getRq3000());
			agList.add(rq3000);
			Audiogram rq4000 = new Audiogram();
			rq4000.setAdglre("R");
			rq4000.setAdgtp("Q");
			rq4000.setAdgfq(4000);
			rq4000.setAdgadt(scForm.getRq4000());
			agList.add(rq4000);
			Audiogram rq6000 = new Audiogram();
			rq6000.setAdglre("R");
			rq6000.setAdgtp("Q");
			rq6000.setAdgfq(6000);
			rq6000.setAdgadt(scForm.getRq6000());
			agList.add(rq6000);


			SingleClientFacade scFacade = (SingleClientFacade) getService("SingleClientFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
//			mapRequest.put("beo1", dg);
			mapRequest.put("beo2", agList);
			mapRequest.put("type", type);
			mapRequest.put("ictid", ictid2);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = scFacade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if(type==null)
				{
					super.saveSuccessfulMsg(req, "保存个人客户信息成功!");
				}
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List<SingleClient> listci = (ArrayList) mapResponse.get("sc");
				String ictid = (String) mapResponse.get("ictid");
				// SingleClient scli = (SingleClient) mapResponse.get("sc");//
				// 个人客户信息
				ClassHelper.copyProperties(listci.get(0), scForm);
				req.getSession().setAttribute("ictid", scForm.getIctid()); // 生产听力图时获取客户id
				// if(tp.equals("cus"))
				// {
				// forward="inputCustomOrder";
				// }
				// else if(tp.equals("rep"))
				// {
				// forward="inputCusRepOrder";
				// }
				// String
				// cliOrdTy=(String)req.getSession().getAttribute("cliOrdTy");
				if (tp.equals("ear")) {
					forward = "inputEarOrder";
				} else if (tp.equals("cus")) {
					forward = "inputCustomOrder";
				} else if (tp.equals("cusRep")) {
					forward = "inputCusRepOrder";
				} else if (tp.equals("earRep")) {
					forward = "inputEarRepOrder";
				} 
//			    if(tp.equals("confirm"))
//				{
//					
//				}
//			    else
//			    {
//			    	
//			    }
				req.getSession().setAttribute("ictid", ictid);
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

	/*
	 * 生成听力图
	 */
	public ActionForward showLeft(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = (String) req.getSession().getAttribute("ictid");
		// 定义图表参数对象
		ChartParams params = new ChartParams();
		params.setTitle("左耳听力图");
		// params.setTitle("zuoertinglitu");
		// 定义获取数据需要的SQL、HQL 语句
		Map<String, String> hm = new HashMap<String, String>();
		// 折线图表
		params.setChartType("line");
		hm.put("骨导",
				"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
						+ ictid
						+ " and t.adglre = 'L' and t.adgtp = 'G' order by t.adgfq");
		hm.put("气导",
				"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
						+ ictid
						+ " and t.adglre = 'L' and t.adgtp = 'Q' order by t.adgfq");
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
		hm.put("骨导",
				"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
						+ ictid
						+ " and t.adglre = 'R' and t.adgtp = 'G' order by t.adgfq");
		hm.put("气导",
				"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = "
						+ ictid
						+ " and t.adglre = 'R' and t.adgtp = 'Q' order by t.adgfq");
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

	/*
	 * 修改订单
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		String tp = req.getParameter("tp");
		String isExa = req.getParameter("isExa");
//		SubmitDataMap data=new SubmitDataMap(req);
		String mds=req.getParameter("md");
		String aga=req.getParameter("aga");
		ResponseEnvelop resEnv = null;
		Order order = new Order();
		Diagnose dg = new Diagnose();
		List<OrderDetail> odList = new ArrayList<OrderDetail>();
		try {
			ClassHelper.copyProperties(orderForm, order);
			ClassHelper.copyProperties(orderForm, dg);
			if (dg.getDgnlid() != null && !"".equals(dg.getDgnlid())
					&& dg.getDgnrid() != null && !"".equals(dg.getDgnrid()))// 左右耳都定制
			{
				OrderDetail od1 = new OrderDetail();
				od1.setFdtpid(dg.getDgnlid());
				od1.setFdtdprc(dg.getDgnldprc());
				od1.setFdtprc(dg.getDgncldprc());
				od1.setFdtsheltp(orderForm.getFdtsheltpl());
				od1.setFdtmklr("0");
				od1.setFdtfno(order.getFolno());
				od1.setFdtdisc(dg.getDgnldct());// 左耳扣率
				od1.setFdtcltid(orderForm.getIctid());
				order.setFoldps(order.getFolldps());// 左耳定金
				odList.add(od1);
				OrderDetail od2 = new OrderDetail();
				od2.setFdtpid(dg.getDgnrid());
				od2.setFdtdprc(dg.getDgnrdprc());
				od2.setFdtprc(dg.getDgncrdprc());
				od2.setFdtsheltp(orderForm.getFdtsheltpr());
				od2.setFdtmklr("1");
				od2.setFdtfno(order.getFolno());
				od2.setFdtdisc(dg.getDgnrdct());// 右耳扣率
				od2.setFdtcltid(orderForm.getIctid());
				order.setFoldps(order.getFolrdps());// 右耳定金
				odList.add(od2);

			} else if ((dg.getDgnlid() != null && !"".equals(dg.getDgnlid()))
					&& (dg.getDgnrid() == null || "".equals(dg.getDgnrid())))// 左耳定制
			{
				OrderDetail od1 = new OrderDetail();
				od1.setFdtpid(dg.getDgnlid());
				od1.setFdtdprc(dg.getDgnldprc());
				od1.setFdtprc(dg.getDgncldprc());
				od1.setFdtsheltp(orderForm.getFdtsheltpl());
				od1.setFdtmklr("0");
				od1.setFdtfno(order.getFolno());
				od1.setFdtdisc(dg.getDgnldct());// 左耳扣率
				od1.setFdtcltid(orderForm.getIctid());
				order.setFoldps(order.getFolldps());// 左耳定金
				odList.add(od1);
				dg.setDgnrid(null);
				dg.setDgnrdct(null);
				dg.setDgnrdprc(null);
			} else if ((dg.getDgnlid() == null || "".equals(dg.getDgnlid()))
					&& (dg.getDgnrid() != null && !"".equals(dg.getDgnrid())))// 右耳定制
			{
				OrderDetail od2 = new OrderDetail();
				od2.setFdtpid(dg.getDgnrid());
				od2.setFdtdprc(dg.getDgnrdprc());
				od2.setFdtprc(dg.getDgncrdprc());
				od2.setFdtsheltp(orderForm.getFdtsheltpr());
				od2.setFdtmklr("1");
				od2.setFdtfno(order.getFolno());
				od2.setFdtdisc(dg.getDgnrdct());// 右耳扣率
				od2.setFdtcltid(orderForm.getIctid());
				order.setFoldps(order.getFolrdps());// 右耳定金
				odList.add(od2);
				dg.setDgnlid(null);
				dg.setDgnldct(null);
				dg.setDgnldprc(null);
			}
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("order", order);
			mapRequest.put("dg", dg);
			mapRequest.put("odList", odList);
			mapRequest.put("md", mds);
			mapRequest.put("aga", aga);
			mapRequest.put("isExa", isExa);
			mapRequest.put("tp", tp);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("b")) {
				resEnv = orderFacade.update(requestEnvelop);
			} else if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			}

			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if (tp.equals("b")) {
					super.saveSuccessfulMsg(req, "修改订单成功!");
				} else if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "提交订单成功!");
					return go2Page(req, mapping, "order");
				}

				// FindLog.insertLog(req, orderForm.getFolctid(), "修改订单");
				// return go2Page(req, mapping, "order");
//				return mapping.findForward("modifyCustom");
				return mapping.findForward("query");
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
	}

	/*
	 * 修改定制机维修订单
	 */
	public ActionForward modifyCusRep(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Repair rep = new Repair();
		String tp = req.getParameter("tp");
		try {
			ClassHelper.copyProperties(orderForm, rep);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			// rep.setRepgctid(dto.getBsc001());
			rep.setRepreger(dto.getBsc011());
			// rep.setRepcltnm(orderForm.getIctnm());
			// rep.setRepcltid(orderForm.getIctid());
			// order.setFoldt(DateUtil.getDate());
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("rep", rep);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.updateCusRep(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");

				// FindLog.insertLog(req, folno, "修改助听器维修订单成功！");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "提交助听器维修订单成功！");
				} else {
					super.saveSuccessfulMsg(req, "修改助听器维修订单成功！");
				}
				return go2Page(req, mapping, "order");
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
	public void transToString(OrderHeaderForm form,SingleClient sc){
		sc.setGumo(trans(form.getGumo()));
		sc.setJiancha(trans(form.getJiancha()));
		sc.setChuandao(trans(form.getChuandao()));
		sc.setGanyin(trans(form.getGanyin()));
		sc.setChuli(trans(form.getChuli()));
	}
	public String trans(String[] str){
		if(str!=null&&str.length>0){
			String result="";
			for(int i =0; i <str.length;i++){
				result += str[i]+",";
			}
			return result;
		}else return "";
	}
}
