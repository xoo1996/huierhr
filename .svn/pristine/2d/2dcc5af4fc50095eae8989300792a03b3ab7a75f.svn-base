package org.radf.apps.store.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Store;
import org.radf.apps.store.facade.StoreFacade;
import org.radf.apps.store.form.InStoreForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class ProDiscardAction extends ActionLeafSupport{
	public ActionForward query(ActionMapping mapping, ActionForm form,
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
//			fileKey = "ord02_004";
//			forward = "/order/query.jsp";
//		}
		fileKey="sto02_001";
		forward="/store/ProDiscard.jsp";
		ActionForward af = new ActionForward();
		InStoreForm inStoreForm = (InStoreForm ) form;
//		Order or = new Order();
		Store sto=new Store();
		try {
			ClassHelper.copyProperties(inStoreForm, sto);
			sto.setFileKey(fileKey);
//			String hql="";
//			String filter="";
////			String hql = queryEnterprise(sto);
//			if(sto.getGctnm()!=null && !"".equals(sto.getGctnm()))
//			{
//			   filter+="and gctnm like '%"+sto.getGctnm()+"%'";
//			   
//			}
//			if(sto.getStopid()!=null && !"".equals(sto.getStopid()))
//			{
//				filter+="and stoproid like '%"+sto.getStoproid()+"%'";
//			}
//			if(sto.getStoproname()!=null && !"".equals(sto.getStoproname()))
//			{
//				filter+="and stoproname like '%"+sto.getStoproname()+"%'";
//			}
//			if(sto.getStodate()!=null && !"".equals(sto.getStodate()))
//			{
//				filter+="and stoproname like '%"+sto.getStoproname()+"%'";
//			}
//			if(sto.getStodisc()!=null && !"".equals(sto.getStodisc()))
//			{
//				filter+="and stodisc='"+sto.getStodisc()+"'";
//			}
//			if(sto.getStart()!=null && !"".equals(sto.getEnd()))
//			{
//				filter+="and stodate>=to_date('"+sto.getStart()+"','yyyy-MM-dd')";
//			}
//			if(sto.getEnd()!=null && !"".equals(sto.getEnd()))
//			{
//				filter+="and stodate<=to_date('"+sto.getEnd()+"','yyyy-MM-dd')";
//			}
////			if(filter.indexOf("and")==-1)
//			if(!"".equals(filter))
//			{
//			 hql="select a.stoid,(case when s.aab300='惠耳听力总部' then s.aab300 else t.gctnm end) as gctnm,a.stoproid,a.stoproname,a.stoamount,a.stoproprice,"+
//"a.stoamountun,a.stoactype,a.stodate,a.stodisc,a.storemark from (select distinct p.stoid,p.stogrcliid,p.stoproid,p.stoproname,p.stoproprice,"+
//"p.stoamountun,p.stoactype,p.stodate,p.stodisc,p.storemark,(p.stoamount-(select sum(c.stoamount) as amount "+
//"from tblsto c join tblsto p on c.stopid=p.stoid))as stoamount from tblsto c join tblsto p on c.stopid=p.stoid)a "+
//"left join tblgrpclient t on t.gctid=a.stogrcliid left join sc01 s on a.stogrcliid=s.bsc001 where 1=1 "+filter+
//"union select b.stoid, (case when s.aab300='惠耳听力总部' then s.aab300 else t.gctnm end) as gctnm,"+
//"b.stoproid,b.stoproname,b.stoamount,b.stoproprice,b.stoamountun,b.stoactype,b.stodate,b.stodisc,b.storemark "+
//"from (select * from tblsto where stoid not in (select stoid from tblsto where stoid in (select stopid from tblsto where stopid is not null)))b left join tblgrpclient t on t.gctid=b.stogrcliid "+
//"left join sc01 s on b.stogrcliid=s.bsc001 where 1=1 "+filter+ "order by stoid ";
//			}
//			else
//			{
//				 hql="select a.stoid,(case when s.aab300='惠耳听力总部' then s.aab300 else t.gctnm end) as gctnm,a.stoproid,a.stoproname,a.stoamount,a.stoproprice,"+
//				 "a.stoamountun,a.stoactype,a.stodate,a.stodisc,a.storemark from (select distinct p.stoid,p.stogrcliid,p.stoproid,p.stoproname,p.stoproprice,"+
//				 "p.stoamountun,p.stoactype,p.stodate,p.stodisc,p.storemark,(p.stoamount-(select sum(c.stoamount) as amount "+
//				 "from tblsto c join tblsto p on c.stopid=p.stoid))as stoamount from tblsto c join tblsto p on c.stopid=p.stoid)a "+
//				 "left join tblgrpclient t on t.gctid=a.stogrcliid left join sc01 s on a.stogrcliid=s.bsc001 "+
//				 "union select b.stoid, (case when s.aab300='惠耳听力总部' then s.aab300 else t.gctnm end) as gctnm,"+
//				 "b.stoproid,b.stoproname,b.stoamount,b.stoproprice,b.stoamountun,b.stoactype,b.stodate,b.stodisc,b.storemark "+
//				 "from (select * from tblsto where stoid not in (select stoid from tblsto where stoid in (select stopid from tblsto where stopid is not null)))b left join tblgrpclient t on t.gctid=b.stogrcliid "+
//				 "left join sc01 s on b.stogrcliid=s.bsc001 order by stoid ";
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
	 * 修改报废状态
	 */
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
		Store sto=new Store();
		InStoreForm inStoForm=(InStoreForm)form;
		try
		{
		ClassHelper.copyProperties(inStoForm, sto);
//		LoginDTO currentuser=(LoginDTO)req.getSession().getAttribute("LoginDTO");
//		String grCli = currentuser.getBsc001();
//	    String operCd=currentuser.getBsc010();
//	    sto.setStogrcliid(grCli);
//	    sto.setStooprcd(operCd);
		StoreFacade facade = (StoreFacade) getService("StoreFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap mapRequest = new HashMap();
		mapRequest.put("beo", sto);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.modifyState(requestEnvelop,1);
		// 处理返回结果
	    returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			
				super.saveSuccessfulMsg(req, "报废成功!");
//				FindLog.insertLog(req, s, "删除个人客户信息");
				return go2Page(req, mapping, "store");
//				return mapping.findForward("proDisc");
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

}
