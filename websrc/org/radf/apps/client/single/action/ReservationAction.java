package org.radf.apps.client.single.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.client.single.facade.ReservationFacade;
import org.radf.apps.client.single.form.ReservationForm;
import org.radf.apps.client.single.form.SingleClientForm;
import org.radf.apps.commons.entity.Reservation;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class ReservationAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public ReservationAction() {
	}

	public ActionForward forwardreservate(ActionMapping mapping, ActionForm form,
		HttpServletRequest req, HttpServletResponse res) throws Exception {
		//String rsvnm = req.getParameter("rsvnm");

		//ReservationForm reservateForm = (ReservationForm) form;
	
		//if(null != rsvnm){
		//	reservateForm.setRsvnm(rsvnm);
		//}
		//String tp=req.getParameter("tp");
		String forward="addreservate";
		//ClassHelper.copyProperties(reservateForm, form);
		return mapping.findForward(forward);
	}
	
	public ActionForward yuyuequery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String forward = null;
		ActionForward af = new ActionForward();
		ReservationForm reservateForm = (ReservationForm) form;
		Reservation reservation = new Reservation();
		forward = "/client/reservate.jsp";
		//forward = "reservate";
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			ClassHelper.copyProperties(reservateForm, reservation);
			if (!"1501000000".equals(dto.getBsc001())) {
				reservation.setRsvgcltid(dto.getBsc011());// 直属店则插入直属店客户代码
			}
			if(reservation.getStart()==null&&reservation.getEnd()==null)
			{
				reservation.setStart(DateUtil.getDate());
				reservation.setEnd(DateUtil.getDate());
			}
			reservation.setFileKey("yuyue01_000");
			String hql = queryEnterprise(reservation);
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
	
	//门诊查询  搜到预约已签到的人
	public ActionForward menzhenquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = null;
		ActionForward af = new ActionForward();
		ReservationForm reservateForm = (ReservationForm) form;
		Reservation reservation = new Reservation();
		forward = "/client/menzhen.jsp";
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			ClassHelper.copyProperties(reservateForm, reservation);
			if (!"1501000000".equals(dto.getBsc001())) {
				reservation.setRsvgcltid(dto.getBsc011());// 直属店则插入直属店客户代码
			}
			reservation.setFileKey("yuyue01_001");
			String hql = queryEnterprise(reservation);
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
	
	public ActionForward yuyueSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ReservationForm reservationForm = (ReservationForm) form;
		Reservation reservation = new Reservation();
		try {
			ClassHelper.copyProperties(reservationForm, reservation);
			
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			if (!"1501000000".equals(dto.getBsc001())) {
				reservation.setRsvgcltid(dto.getBsc011());// 直属店则插入直属店客户代码
			}
			reservation.setRsvdate(reservation.getRsvdate());//要求完成日期
			
			ReservationFacade reservationFacade = (ReservationFacade) getService("ReservationFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", reservation);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = reservationFacade.yuyueSave(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// 获得新增任务单号
				String rsvid = (String) ((HashMap) resEnv.getBody())
						.get("rsvid");
				FindLog.insertLog(req, rsvid, "预约成功！");
				
				//super.saveSuccessfulMsg(req, "预约成功,预约号为" + rsvid);
				super.saveSuccessfulMsg(req, "预约成功!");
				reservationForm.setRsvgcltid(null);
				reservationForm.setRsvnm(null);
				reservationForm.setRsvphone(null);
				reservationForm.setYpnm(null);
				reservationForm.setRsvdate(null);
				reservationForm.setRsvgctnm(null);
				reservationForm.setRsvgczhuanid(null);
				reservationForm.setRsvgctzhuannm(null);
				/*Task task1 = (Task) ((HashMap) resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
				ClassHelper.copyProperties(task1, taskForm);*/
				// 下一个页面，填写订单详细信息
				
				return mapping.findForward("reservate");
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
	
	public ActionForward yuyueqiandao(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ReservationForm reservationForm = (ReservationForm) form;
		Reservation reservation = new Reservation();
		 String grCli="";
		//String forward = "/client/reservate.jsp";
		try {
			ClassHelper.copyProperties(reservationForm, reservation);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			grCli = dto.getBsc011();//客户代码
			if ("1501000000".equals(dto.getBsc001())) {
				super.saveSuccessfulMsg(req, "总部非门诊点,无法预约签到");
				reservationForm.setRsvgcltid(null);
				reservationForm.setRsvnm(null);
				reservationForm.setRsvphone(null);
				reservationForm.setYpnm(null);
				reservationForm.setRsvdate(null);
				reservationForm.setRsvgctnm(null);
				reservationForm.setRsvgczhuanid(null);
				reservationForm.setRsvsta(null);
				reservationForm.setRsvgctzhuannm(null);
				return mapping.findForward("reservate");
			}
			ReservationFacade facade = (ReservationFacade) getService("ReservationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", reservation);
			mapRequest.put("grCli", grCli);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.yuyueqiandao(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String rsvtodayid = (String) ((HashMap) resEnv.getBody())
						.get("rsvtodayid");
				super.saveSuccessfulMsg(req, "预约签到成功，号码为"+rsvtodayid);
				reservationForm.setRsvgcltid(null);
				reservationForm.setRsvnm(null);
				reservationForm.setRsvphone(null);
				reservationForm.setYpnm(null);
				reservationForm.setRsvdate(null);
				reservationForm.setRsvgctnm(null);
				reservationForm.setRsvgczhuanid(null);
				reservationForm.setRsvsta(null);
				reservationForm.setRsvgctzhuannm(null);
				return go2Page(req,mapping,"client");
				//return mapping.findForward("forward");
	
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
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		String rsvid = req.getParameter("rsvid");
		Reservation reservation = new Reservation();
		ReservationForm rf = (ReservationForm)form;
		try{
			
			ClassHelper.copyProperties(rf, reservation);
			ReservationFacade facade = (ReservationFacade)getService("ReservationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			//将application放入map对象中
			HashMap<String, Reservation> map = new HashMap<String, Reservation>();
			map.put("beo", reservation);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(map);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			//处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "预约删除成功");
				return go2Page(req,mapping,"client");
			}else{
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	public ActionForward statistics_query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String forward = null;
		
		ActionForward af = new ActionForward();
		
		forward = "/client/reservatestat.jsp";
		Reservation reservation = new Reservation();
		ReservationForm rf = (ReservationForm)form;
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			ClassHelper.copyProperties(rf, reservation);
			
			reservation.setFileKey("yuyue03_000");
			String hql = queryEnterprise(reservation);
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
	
	public ActionForward statistics_detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String forward = null;
		
		ActionForward af = new ActionForward();
		
		forward = "/client/statisticsdetail.jsp";
		Reservation reservation = new Reservation();
		ReservationForm rf = (ReservationForm)form;
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			ClassHelper.copyProperties(rf, reservation);
			
			reservation.setFileKey("yuyue03_001");
			String hql = queryEnterprise(reservation);
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
	
	
	public ActionForward newmenzhen(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//SingleClient sc=new SingleClient();
		ReservationForm reservationForm = (ReservationForm) form;
		Reservation reservation = new Reservation();
		String forward="addClient";
		
		try {
			ClassHelper.copyProperties(reservationForm, reservation);
			ReservationFacade facade = (ReservationFacade) getService("ReservationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", reservation);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.menzhen1(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				
				//ReservationFacade facade1 = (ReservationFacade) getService("ReservationFacade");
				//RequestEnvelop requestEnvelop1 = new RequestEnvelop();
				//EventResponse returnValue1 = new EventResponse();
				// 将Application对象放入HashMap
				//HashMap<String, Object> mapRequest1 = new HashMap<String,Object>();
				//mapRequest1.put("beo", reservation);
				
				// 将HashMap对象放入requestEnvelop
				//requestEnvelop1.setBody(mapRequest1);
				//ResponseEnvelop resEnv1 = facade1.chuanzhi(requestEnvelop1);
				
				//returnValue1 = processRevt(resEnv1);
				//if(returnValue1.isSucessFlag()){
					HashMap<String,Object> mapResponse=null;
					mapResponse = (HashMap) resEnv.getBody();
//					List ci = (ArrayList) mapResponse.get("beo1");// 个人客户信息
//					ClassHelper.copyProperties(ci.get(0), form);
					//List ci = (ArrayList) mapResponse.get("beo1");
					//ClassHelper.copyProperties(ci, sc);
					//ClassHelper.copyProperties(sc, form);
//					SingleClient ci = (SingleClient)mapResponse.get("beo1");
					for(Map.Entry<String, Object> entry:mapResponse.entrySet()){    
							ClassHelper.copyProperties(entry.getValue(),form);    
						    }
//					reservationForm.setIctnm(ci.getIctnm());
//					reservationForm.setIctgctid(ci.getIctgctid());
//					reservationForm.setIcttel(ci.getIcttel());
//					ClassHelper.copyProperties(reservationForm, form);
					
					return mapping.findForward(forward);
				
				
				//return mapping.findForward("forward");
	
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
	
	public ActionForward oldmenzhen(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ReservationForm reservationForm = (ReservationForm) form;
		Reservation reservation = new Reservation();
	
		String forward="modify";
		try {
			ClassHelper.copyProperties(reservationForm, reservation);
			ReservationFacade facade = (ReservationFacade) getService("ReservationFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", reservation);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.menzhen(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				ClassHelper.copyProperties(reservationForm, form);
				return mapping.findForward(forward);
				//super.saveSuccessfulMsg(req, "预约签到成功");
				//return go2Page(req,mapping,"client");
				
				
				//return mapping.findForward("forward");
	
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