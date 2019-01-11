package org.radf.apps.store.action;

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
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.store.facade.StoreFacade;
import org.radf.apps.store.form.InStoreForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class AllocateAction extends ActionLeafSupport{

	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("order");	//"order"来源？
		String forward = null;
		String fileKey = null;
//		if ("del".equals(order)) {
//			fileKey = "ord02_000";
//			forward = "/order/del.jsp";
//		} else if ("delivery".equals(order)) { // 为了发货而查询
//			forward = "/order/query1.jsp";
//			fileKey = "ord02_001";
//		} else {
			fileKey = "ord02_007";
			forward = "/store/Allocate.jsp";
//		}
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order or = new Order();
		try {
			ClassHelper.copyProperties(orderForm, or);
			or.setFileKey(fileKey);
			String hql = queryEnterprise(or);
//			hql+=
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
		
	
	public ActionForward modifyState(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		Collection<Store> collection = null;
//		collection = TypeCast.getEntities(new SubmitDataMap(req),
//				Store.class);
//		String[] ids=req.getParameterValues("stoid");
//		List<Store> stoList=new ArrayList<Store>();
//		for(int i=0;i<ids.length;i++)
//		{
//			Store sto=new Store();
//			sto.setStoid(Integer.parseInt(ids[i]));
//		    stoList.add(sto);
//		}	
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order=new Order();
		Store sto=new Store();
		try
		{
		ClassHelper.copyProperties(orderForm, order);
//		LoginDTO currentuser = (LoginDTO)pageContext.findAttribute("LoginDTO");
		LoginDTO currentuser=(LoginDTO)req.getSession().getAttribute("LoginDTO");
        String grCli = currentuser.getBsc001();
        String operCd=currentuser.getBsc010();
        sto.setStogrcliid(grCli);
        sto.setStooprcd(operCd);
		StoreFacade facade = (StoreFacade) getService("StoreFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("order", order);
		mapRequest.put("store", sto);
		
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.modifyState(requestEnvelop,2);
		// 处理返回结果
	    returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			
				super.saveSuccessfulMsg(req, "报废成功!");
				return go2Page(req, mapping, "store");

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
	 * 显示订单详细信息
	 */
	public ActionForward allocateDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ActionForward af = new ActionForward();
		String forward = "/store/AllocateDetail.jsp";
		try{
			ClassHelper.copyProperties(orderForm, order);
			order.setFileKey("ord11_025");
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		}catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;  
	}
	
	/**
	 * 配货
	 */
	public ActionForward allocate(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		OrderHeaderForm orderForm = (OrderHeaderForm)form;
		Order order = new Order();
		
		try{
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			String opr = dto.getBsc011();
			ClassHelper.copyProperties(orderForm, order);
			StoreFacade facade = (StoreFacade)getService("StoreFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse response = new EventResponse();
			
			HashMap<String,Object> mapRequest = new HashMap<String,Object>();
			mapRequest.put("beo", order);
			mapRequest.put("opr",opr);
			requestEnvelop.setBody(mapRequest);
			
			ResponseEnvelop resEnv = facade.allocate(requestEnvelop);
			response = processRevt(resEnv);
			
			if(response.isSucessFlag()){
				super.saveSuccessfulMsg(req, "配货成功！");
				return go2Page(req,mapping,"store");
			}else {
				String[] aa = StringUtil.getAsStringArray(response.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	
	/**
	 * 查询订单详细信息
	 */
	public ActionForward queryDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ActionForward af = new ActionForward();
		String forward = "/order/queryDetail.jsp"; // 查看订单明细
		try {
			ClassHelper.copyProperties(orderForm, order);
			order.setFileKey("ord01_001");
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	
}
