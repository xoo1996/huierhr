package org.radf.apps.charge.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.business.facade.BusinessFacade;
import org.radf.apps.charge.facade.ChargeFacade;
import org.radf.apps.charge.form.ChargeForm;
import org.radf.apps.client.single.form.SingleClientForm;
import org.radf.apps.commons.entity.Audiogram;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

public class ChargeAction extends ActionLeafSupport{

	/**
	 * 查询普通商品收费基本信息
	 */
	public ActionForward normalQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ChargeForm cForm = (ChargeForm) form;
		String forward = null;
		/*String fileKey = null;
		
		fileKey = "chg01_000";*/
		forward = "/charge/norchgquery.jsp";

		ActionForward af = new ActionForward();
		Charge charge = new Charge();
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			ClassHelper.copyProperties(cForm, charge);
			
			if(dto.getBsc001().equals("1501000000")){
				charge.setStogrcliid(dto.getBsc001());
			}else{
				charge.setStogrcliid(dto.getBsc011());
			}
			
			charge.setStoproid(cForm.getStoproid());
			charge.setPdtut(cForm.getPdtut());
			charge.setStart(cForm.getStart());
			charge.setEnd(cForm.getEnd());
			
			
			charge.setFileKey("chg01_000");
			String hql = queryEnterprise(charge);
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
	 * 普通商品收费时用户信息查询
	 */
	public ActionForward clientQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ChargeForm cForm = (ChargeForm) form;
		String forward=null;
		String tp=req.getParameter("tp");
		if(tp.equals("normal"))
		{ forward= "/charge/clientquery.jsp";}
		else {
			forward="/charge/clientqueryerbei.jsp";
		}

		ActionForward af = new ActionForward();
		
		
		Charge charge = new Charge();
		try {
			ClassHelper.copyProperties(cForm, charge);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			charge.setIctgctid(dto.getBsc011());
			charge.setFileKey("chg01_002");
			String hql = queryEnterprise(charge);
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
	 * 普通商品收费时新增用户信息
	 */
	public ActionForward addClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ChargeForm cForm = (ChargeForm) form;
		Charge charge = new Charge();
		String ictPro,ictCity,ictCounty;
		String ictPhone,ictLandLine;
		String tel = "";
		try {
			ClassHelper.copyProperties(cForm, charge);
			transToString(cForm,charge);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			charge.setIctoprcd(dto.getBsc011());
			charge.setIctgctid(dto.getBsc011());
			ictPro = charge.getIctpro()==null?"":charge.getIctpro().split(",")[1];//省份
			ictCity = charge.getIctcity()==null?"":charge.getIctcity().split(",")[1];//市
			ictCounty = charge.getIctcounty()==null?"":charge.getIctcounty().split(",")[1];//县
			ictPhone = charge.getIctphone()==null?"":charge.getIctphone();//手机
			ictLandLine = charge.getIctlandline()==null?"":charge.getIctlandline();//固定电话
/*			sc.setIctpro(sc.getIctpro()==null?"":sc.getIctpro().split(",")[0]);
			sc.setIctcity(sc.getIctcity()==null?"":sc.getIctcity().split(",")[0]);
			sc.setIctcounty(sc.getIctcounty()==null?"":sc.getIctcounty().split(",")[0]);*/
			if(ictPro!=null&&!"".equals(ictPro)){
				charge.setIctaddr(ictPro+ictCity+ictCounty+charge.getIctdetailaddr());
			}
			if(!"".equals(ictPhone)){
				tel = tel + ictPhone + "    ";
			}
			if(!"".equals(ictLandLine)){
				tel = tel + ictLandLine;
			}
			if(!"".equals(tel)&&tel!=null){
				charge.setIcttel(tel);
			}
			List<Audiogram> agList = new Vector<Audiogram>();
			Audiogram lg250 = new Audiogram();
			lg250.setAdglre("L");
			lg250.setAdgtp("G");
			lg250.setAdgfq(250);
			lg250.setAdgadt(cForm.getLg250());
			agList.add(lg250);
			Audiogram lg500 = new Audiogram();
			lg500.setAdglre("L");
			lg500.setAdgtp("G");
			lg500.setAdgfq(500);
			lg500.setAdgadt(cForm.getLg500());
			agList.add(lg500);
			Audiogram lg750 = new Audiogram();
			lg750.setAdglre("L");
			lg750.setAdgtp("G");
			lg750.setAdgfq(750);
			lg750.setAdgadt(cForm.getLg750());
			agList.add(lg750);
			Audiogram lg1000 = new Audiogram();
			lg1000.setAdglre("L");
			lg1000.setAdgtp("G");
			lg1000.setAdgfq(1000);
			lg1000.setAdgadt(cForm.getLg1000());
			agList.add(lg1000);
			Audiogram lg1500 = new Audiogram();
			lg1500.setAdglre("L");
			lg1500.setAdgtp("G");
			lg1500.setAdgfq(1500);
			lg1500.setAdgadt(cForm.getLg1500());
			agList.add(lg1500);
			Audiogram lg2000 = new Audiogram();
			lg2000.setAdglre("L");
			lg2000.setAdgtp("G");
			lg2000.setAdgfq(2000);
			lg2000.setAdgadt(cForm.getLg2000());
			agList.add(lg2000);
			Audiogram lg3000 = new Audiogram();
			lg3000.setAdglre("L");
			lg3000.setAdgtp("G");
			lg3000.setAdgfq(3000);
			lg3000.setAdgadt(cForm.getLg3000());
			agList.add(lg3000);
			Audiogram lg4000 = new Audiogram();
			lg4000.setAdglre("L");
			lg4000.setAdgtp("G");
			lg4000.setAdgfq(4000);
			lg4000.setAdgadt(cForm.getLg4000());
			agList.add(lg4000);
			Audiogram lg6000 = new Audiogram();
			lg6000.setAdglre("L");
			lg6000.setAdgtp("G");
			lg6000.setAdgfq(6000);
			lg6000.setAdgadt(cForm.getLg6000());
			agList.add(lg6000);

			Audiogram lq250 = new Audiogram();
			lq250.setAdglre("L");
			lq250.setAdgtp("Q");
			lq250.setAdgfq(250);
			lq250.setAdgadt(cForm.getLq250());
			agList.add(lq250);
			Audiogram lq500 = new Audiogram();
			lq500.setAdglre("L");
			lq500.setAdgtp("Q");
			lq500.setAdgfq(500);
			lq500.setAdgadt(cForm.getLq500());
			agList.add(lq500);
			Audiogram lq750 = new Audiogram();
			lq750.setAdglre("L");
			lq750.setAdgtp("Q");
			lq750.setAdgfq(750);
			lq750.setAdgadt(cForm.getLq750());
			agList.add(lq750);
			Audiogram lq1000 = new Audiogram();
			lq1000.setAdglre("L");
			lq1000.setAdgtp("Q");
			lq1000.setAdgfq(1000);
			lq1000.setAdgadt(cForm.getLq1000());
			agList.add(lq1000);
			Audiogram lq1500 = new Audiogram();
			lq1500.setAdglre("L");
			lq1500.setAdgtp("Q");
			lq1500.setAdgfq(1500);
			lq1500.setAdgadt(cForm.getLq1500());
			agList.add(lq1500);
			Audiogram lq2000 = new Audiogram();
			lq2000.setAdglre("L");
			lq2000.setAdgtp("Q");
			lq2000.setAdgfq(2000);
			lq2000.setAdgadt(cForm.getLq2000());
			agList.add(lq2000);
			Audiogram lq3000 = new Audiogram();
			lq3000.setAdglre("L");
			lq3000.setAdgtp("Q");
			lq3000.setAdgfq(3000);
			lq3000.setAdgadt(cForm.getLq3000());
			agList.add(lq3000);
			Audiogram lq4000 = new Audiogram();
			lq4000.setAdglre("L");
			lq4000.setAdgtp("Q");
			lq4000.setAdgfq(4000);
			lq4000.setAdgadt(cForm.getLq4000());
			agList.add(lq4000);
			Audiogram lq6000 = new Audiogram();
			lq6000.setAdglre("L");
			lq6000.setAdgtp("Q");
			lq6000.setAdgfq(6000);
			lq6000.setAdgadt(cForm.getLq6000());
			agList.add(lq6000);

			Audiogram rg250 = new Audiogram();
			rg250.setAdglre("R");
			rg250.setAdgtp("G");
			rg250.setAdgfq(250);
			rg250.setAdgadt(cForm.getRg250());
			agList.add(rg250);
			Audiogram rg500 = new Audiogram();
			rg500.setAdglre("R");
			rg500.setAdgtp("G");
			rg500.setAdgfq(500);
			rg500.setAdgadt(cForm.getRg500());
			agList.add(rg500);
			Audiogram rg750 = new Audiogram();
			rg750.setAdglre("R");
			rg750.setAdgtp("G");
			rg750.setAdgfq(750);
			rg750.setAdgadt(cForm.getRg750());
			agList.add(rg750);
			Audiogram rg1000 = new Audiogram();
			rg1000.setAdglre("R");
			rg1000.setAdgtp("G");
			rg1000.setAdgfq(1000);
			rg1000.setAdgadt(cForm.getRg1000());
			agList.add(rg1000);
			Audiogram rg1500 = new Audiogram();
			rg1500.setAdglre("R");
			rg1500.setAdgtp("G");
			rg1500.setAdgfq(1500);
			rg1500.setAdgadt(cForm.getRg1500());
			agList.add(rg1500);
			Audiogram rg2000 = new Audiogram();
			rg2000.setAdglre("R");
			rg2000.setAdgtp("G");
			rg2000.setAdgfq(2000);
			rg2000.setAdgadt(cForm.getRg2000());
			agList.add(rg2000);
			Audiogram rg3000 = new Audiogram();
			rg3000.setAdglre("R");
			rg3000.setAdgtp("G");
			rg3000.setAdgfq(3000);
			rg3000.setAdgadt(cForm.getRg3000());
			agList.add(rg3000);
			Audiogram rg4000 = new Audiogram();
			rg4000.setAdglre("R");
			rg4000.setAdgtp("G");
			rg4000.setAdgfq(4000);
			rg4000.setAdgadt(cForm.getRg4000());
			agList.add(rg4000);
			Audiogram rg6000 = new Audiogram();
			rg6000.setAdglre("R");
			rg6000.setAdgtp("G");
			rg6000.setAdgfq(6000);
			rg6000.setAdgadt(cForm.getRg6000());
			agList.add(rg6000);

			Audiogram rq250 = new Audiogram();
			rq250.setAdglre("R");
			rq250.setAdgtp("Q");
			rq250.setAdgfq(250);
			rq250.setAdgadt(cForm.getRq250());
			agList.add(rq250);
			Audiogram rq500 = new Audiogram();
			rq500.setAdglre("R");
			rq500.setAdgtp("Q");
			rq500.setAdgfq(500);
			rq500.setAdgadt(cForm.getRq500());
			agList.add(rq500);
			Audiogram rq750 = new Audiogram();
			rq750.setAdglre("R");
			rq750.setAdgtp("Q");
			rq750.setAdgfq(750);
			rq750.setAdgadt(cForm.getRq750());
			agList.add(rq750);
			Audiogram rq1000 = new Audiogram();
			rq1000.setAdglre("R");
			rq1000.setAdgtp("Q");
			rq1000.setAdgfq(1000);
			rq1000.setAdgadt(cForm.getRq1000());
			agList.add(rq1000);
			Audiogram rq1500 = new Audiogram();
			rq1500.setAdglre("R");
			rq1500.setAdgtp("Q");
			rq1500.setAdgfq(1500);
			rq1500.setAdgadt(cForm.getRq1500());
			agList.add(rq1500);
			Audiogram rq2000 = new Audiogram();
			rq2000.setAdglre("R");
			rq2000.setAdgtp("Q");
			rq2000.setAdgfq(2000);
			rq2000.setAdgadt(cForm.getRq2000());
			agList.add(rq2000);
			Audiogram rq3000 = new Audiogram();
			rq3000.setAdglre("R");
			rq3000.setAdgtp("Q");
			rq3000.setAdgfq(3000);
			rq3000.setAdgadt(cForm.getRq3000());
			agList.add(rq3000);
			Audiogram rq4000 = new Audiogram();
			rq4000.setAdglre("R");
			rq4000.setAdgtp("Q");
			rq4000.setAdgfq(4000);
			rq4000.setAdgadt(cForm.getRq4000());
			agList.add(rq4000);
			Audiogram rq6000 = new Audiogram();
			rq6000.setAdglre("R");
			rq6000.setAdgtp("Q");
			rq6000.setAdgfq(6000);
			rq6000.setAdgadt(cForm.getRq6000());
			agList.add(rq6000);
			
			
			ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", charge);
			mapRequest.put("beo1", agList);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.addClient(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String ictid = (String) ((HashMap) resEnv.getBody())
						.get("ictid");
				super.saveSuccessfulMsg(req, "保存个人客户信息成功!,客户代码为：" + ictid);
				
				//ClassHelper.copyProperties(cForm, charge);
				//req.getSession().setAttribute("ictid", ictid);
				return mapping.findForward("clientQuery");
			}else {
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
	 * 保存普通商品收费信息
	 */
	public ActionForward saveNormalCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		String gctid = req.getParameter("ictgctid");
		
		String ictaddr = req.getParameter("ictaddr");
		String ictgender = req.getParameter("ictgender");
		String ictnm = req.getParameter("ictnm");
		String folctnm = req.getParameter("folctnm");
		String icttel = req.getParameter("icttel");
		String ictpnm = req.getParameter("ictpnm");
		String ncdypname=req.getParameter("ncdypname");
		String tp=req.getParameter("tp");
		
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		//Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	

		try {
			con = DBUtil.getConnection();
//			DBUtil.beginTrans(con);
			
			String[] idList = data.getParameterValues("pdtid"); // 商品(耳机)代码
			String[] disList = data.getParameterValues("fdtdisc");// 商品扣率
			String[] qntList = data.getParameterValues("fdtqnt"); // 售出数量
			String[] prcList = data.getParameterValues("fdtrprc"); // 实际收费
			String[] ntList = data.getParameterValues("chgnt"); // 备注
			
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			int size = idList.length;
			List<Charge> chgList = new Vector<Charge>();
//			
			for (int i = 0; i < size; i++) {
				Integer amount=0;
				String pdtnm = null;
				String pdtun = null;
				String sql1 = "select distinct nvl(sum(stoamount*stoactype)over(partition by stoproid,stogrcliid),0) as temp,stoproname,stoamountun from tblsto  where stoproid = '" + idList[i] + "'" 
						+ " and stogrcliid='" + gctid + "'";//查询商品剩余量
				List result1 = (Vector)DBUtil.querySQL(con, sql1);
				if(result1.size() > 0){

					amount = Integer.parseInt(((HashMap)result1.get(0)).get("1").toString());//某商品代码所对应的商品剩余量
					pdtnm = ((HashMap)result1.get(0)).get("2").toString();//商品名称
					if(null == ((HashMap)result1.get(0)).get("3") || "".equals(((HashMap)result1.get(0)).get("3").toString())){//商品单位
						pdtun = "无";
					}else{
						pdtun = ((HashMap)result1.get(0)).get("3").toString() ;
					}
					
				}else{
					throw new AppException("商品代码为：" + idList[i] + "的商品无足够库存！");
				}
				
 				if(amount < Integer.parseInt(qntList[i])){
					String msg = pdtnm + "没有足够的库存量！";
					throw new AppException(msg);
//					return go2Page(req, mapping, "charge");
				}
				Charge charge = new Charge();
				//charge.setChgid(chgid);
				charge.setIctnm(ictnm);
				charge.setIctid(ictid);
				charge.setStoproname(pdtnm);
				charge.setStogrcliid(gctid);
				charge.setStodate(DateUtil.getDate());
				charge.setStoamountun(pdtun);
				charge.setStooprcd(dto.getBsc011());
				
//				charge.setNcdid(chgid);
				charge.setNcdpid(idList[i]);
				charge.setNcddis(Double.parseDouble(disList[i]));
				charge.setNcdqnt(Integer.parseInt(qntList[i]));
				charge.setNcdmon(Double.parseDouble(prcList[i]));
				charge.setNcdnt(ntList[i]);
				charge.setNcdsta("2");
				//charge.setNcdrecdt(DateUtil.getDate());
				charge.setNcdypname(ncdypname);
				chgList.add(charge);
				
			}
			
			
			ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", chgList);
			mapRequest.put("tp", tp);
			mapRequest.put("req", req);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save2(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				//super.saveSuccessfulMsg(req, "新增收费信息成功!");
				
				//Charge charge1 = (Charge)((HashMap)resEnv.getBody()).get("dto");
				List<Charge> list = (Vector<Charge>)((HashMap)resEnv.getBody()).get("dto");
				Charge charge1 = new Charge();
				charge1.setIctaddr(ictaddr);
				charge1.setIctgender(ictgender);
				charge1.setIctnm(ictnm);
				charge1.setFolctnm(folctnm);
				charge1.setIcttel(icttel);
				charge1.setIctpnm(ictpnm);
				charge1.setNcdypname(ncdypname);
				charge1.setChgid(list.get(0).getChgid());
				ClassHelper.copyProperties(charge1,form);
				ClassHelper.copyProperties(list.get(0), charge1);
				super.saveSuccessfulMsg(req, "新增收费信息成功!");
				return mapping.findForward("norChargeDetailView");
				//return go2Page(req, mapping, "charge");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}			
			
		} 
//		catch (AppException ex) {
//			this.saveErrors(req, ex);
//			//return mapping.findForward("backspace");
////			return mapping.findForward("norChargeDetail");
//			String forward = "/charge/clientquery.jsp";
//			return mapping.findForward(forward);
//		} 
		catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		
	}

	/**
	 * 保存耳背机收费信息
	 */
	public ActionForward saveErbeiCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		String gctid = req.getParameter("ictgctid");
		
		String ictaddr = req.getParameter("ictaddr");
		String ictgender = req.getParameter("ictgender");
		String ictnm = req.getParameter("ictnm");
		String folctnm = req.getParameter("folctnm");
		String icttel = req.getParameter("icttel");
		String ictpnm = req.getParameter("ictpnm");
		String ncdypname=req.getParameter("ncdypname");
		String tp=req.getParameter("tp");
		
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		//Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	

		try {
			con = DBUtil.getConnection();
//			DBUtil.beginTrans(con);
			
			String[] idList = data.getParameterValues("pdtid"); // 商品(耳机)代码
			String[] disList = data.getParameterValues("fdtdisc");// 商品扣率
			String[] qntList = data.getParameterValues("fdtqnt"); // 售出数量
			String[] prcList = data.getParameterValues("fdtrprc"); // 实际收费
			String[] ntList = data.getParameterValues("chgnt"); // 备注
			String[] jsIdList = data.getParameterValues("jsid"); // 机身编号
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			int size = idList.length;
			List<Charge> chgList = new Vector<Charge>();
//			
			for (int i = 0; i < size; i++) {
				Integer amount=0;
				String pdtnm = null;
				String pdtun = null;
				String sql1 = "select distinct nvl(sum(stoamount*stoactype)over(partition by stoproid,stogrcliid),0) as temp,stoproname,stoamountun from tblsto  where stoproid = '" + idList[i] + "'" 
						+ " and stogrcliid='" + gctid + "'";//查询商品剩余量
				List result1 = (Vector)DBUtil.querySQL(con, sql1);
				if(result1.size() > 0){

					amount = Integer.parseInt(((HashMap)result1.get(0)).get("1").toString());//某商品代码所对应的商品剩余量
					pdtnm = ((HashMap)result1.get(0)).get("2").toString();//商品名称
					if(null == ((HashMap)result1.get(0)).get("3") || "".equals(((HashMap)result1.get(0)).get("3").toString())){//商品单位
						pdtun = "无";
					}else{
						pdtun = ((HashMap)result1.get(0)).get("3").toString() ;
					}
					
				}else{
					throw new AppException("商品代码为：" + idList[i] + "的商品无足够库存！");
				}
				
 				if(amount < Integer.parseInt(qntList[i])){
					String msg = pdtnm + "没有足够的库存量！";
					throw new AppException(msg);
//					return go2Page(req, mapping, "charge");
				}
				Charge charge = new Charge();
				//charge.setChgid(chgid);
				charge.setIctnm(ictnm);
				charge.setIctid(ictid);
				charge.setStoproname(pdtnm);
				charge.setStogrcliid(gctid);
				charge.setStodate(DateUtil.getDate());
				charge.setStoamountun(pdtun);
				charge.setStooprcd(dto.getBsc011());
				
//				charge.setNcdid(chgid);
				charge.setNcdpid(idList[i]);
				charge.setNcddis(Double.parseDouble(disList[i]));
				charge.setNcdqnt(Integer.parseInt(qntList[i]));
				charge.setNcdmon(Double.parseDouble(prcList[i]));
				charge.setNcdnt(ntList[i]);
				charge.setJsid(jsIdList[i]);//机身编号
				charge.setNcdsta("2");
				//charge.setNcdrecdt(DateUtil.getDate());
				charge.setNcdypname(ncdypname);
				chgList.add(charge);
				
			}
			
			
			ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", chgList);
			mapRequest.put("tp", tp);
			mapRequest.put("req", req);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.save3(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				//super.saveSuccessfulMsg(req, "新增收费信息成功!");
				
				//Charge charge1 = (Charge)((HashMap)resEnv.getBody()).get("dto");
				List<Charge> list = (Vector<Charge>)((HashMap)resEnv.getBody()).get("dto");
				Charge charge1 = new Charge();
				charge1.setIctaddr(ictaddr);
				charge1.setIctgender(ictgender);
				charge1.setIctnm(ictnm);
				charge1.setFolctnm(folctnm);
				charge1.setIcttel(icttel);
				charge1.setIctpnm(ictpnm);
				charge1.setNcdypname(ncdypname);
				charge1.setChgid(list.get(0).getChgid());
				ClassHelper.copyProperties(charge1,form);
				ClassHelper.copyProperties(list.get(0), charge1);
				super.saveSuccessfulMsg(req, "新增收费信息成功!");
				return mapping.findForward("norChargeDetailView");
				//return go2Page(req, mapping, "charge");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}			
			
		} 
//		catch (AppException ex) {
//			this.saveErrors(req, ex);
//			//return mapping.findForward("backspace");
////			return mapping.findForward("norChargeDetail");
//			String forward = "/charge/clientquery.jsp";
//			return mapping.findForward(forward);
//		} 
		catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		
	}

	
	/**
	 * 保存普通商品收费信息
	 */
	public ActionForward checkNormalDisc(ActionMapping mapping, ActionForm form,
		HttpServletRequest req, HttpServletResponse res) throws Exception {
		String gctid = req.getParameter("ictgctid");		
		SubmitDataMap data = new SubmitDataMap(req);
		//Charge charge = new Charge();
		//判断是直属店还是加盟店
		String type = null;
		Connection con = null;
		try {

			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql = "select t.GCTTYPE from TBLGRPCLIENT t where t.GCTID='"
					+ gctid + "'";
			List result = (Vector) DBUtil.querySQL(con, sql);

			if (result.size() > 0) {
				// pamnt = Double.parseDouble(((HashMap)
				// result.get(0)).get("1").toString());
				type = ((HashMap) result.get(0)).get("1").toString();
			}
			System.out.println(type);
			DBUtil.commit(con);
		} catch (Exception ex) {

		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		ChargeForm cf = (ChargeForm) form;	
		try {
			String[] idList = data.getParameterValues("pdtid"); // 商品(耳机)代码
			String[] disList = data.getParameterValues("fdtdisc");// 商品扣率
			int size = idList.length;
			List<Charge> chgList = new Vector<Charge>();		
			for (int i = 0; i < size; i++) {
				Charge charge = new Charge();
				charge.setStogrcliid(gctid);
				charge.setNcdpid(idList[i]);
				charge.setNcddis(Double.parseDouble(disList[i]));
				chgList.add(charge);
			}
			ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", chgList);
			mapRequest.put("req", req);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.checkNormalDisc(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (!type.equals("A")) {
				String jsonStr;
				jsonStr="{tdspvo:''}";
				res.getWriter().write(jsonStr);
				
			} else {
				if (returnValue.isSucessFlag()) {
					res.setCharacterEncoding("GBK");
					String jsonStr="[";
					if(null!=(HashMap)resEnv.getBody())
					{
					List<Charge> list = (Vector<Charge>)((HashMap)resEnv.getBody()).get("dto");

//						boolean flag=false;
						if(null!=list)
						{
						for(Charge chg:list)
						{
							if(null!=chg.getMindisi())
							{
								jsonStr+="{i:"+chg.getMindisi()+",mindis:"+chg.getTdspvo()+"},";
//								flag=true;
							}
						}
//						if(flag)
//						{
							req.getSession().setAttribute("chgList",list);
							jsonStr=jsonStr.substring(0, jsonStr.length()-1);
							jsonStr+="]";
							res.getWriter().write(jsonStr);
							
//						}
						}
					}
						else
						{
							jsonStr="{tdspvo:''}";
							res.getWriter().write(jsonStr);
						}
				}

			}
			
		} 
		catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
//			return mapping.findForward("backspace");
		}
					return null;
		
	}
	
	/**
	 * 普通商品收费时进入普通商品收费详情页面
	 */
	public ActionForward saveChgid(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
         String tp=req.getParameter("tp");
		try {
			ClassHelper.copyProperties(cf, charge);

			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			charge.setIctgctid(dto.getBsc011());
			
			charge.setChgdt(DateUtil.getDate());
			charge.setChggcltid(dto.getBsc011());
			charge.setChgcltid(charge.getIctid());
			charge.setChgid("-1");

			ClassHelper.copyProperties(charge, cf);
			if(tp.equals("normal"))
			{return mapping.findForward("norChargeDetail");}
			else {
				return mapping.findForward("erbeijiChargeDetail");
			}
			
			/*ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Charge> mapRequest = new HashMap<String, Charge>();
			mapRequest.put("beo", charge);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print2(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "新增收费号成功!");
				Charge charge1 = (Charge) ((HashMap) resEnv.getBody()).get("beo");
				
				ClassHelper.copyProperties(charge1, cf);
				
				return mapping.findForward("norChargeDetail");

			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}*/
		} /*catch (AppException ex) {
			this.saveErrors(req, ex);
			return mapping.findForward("backspace");
		} */catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	/*
	 * 显示普通商品收费信息
	 */
			
	public ActionForward norChgRecDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
		try{
			ClassHelper.copyProperties(cf, charge);
			ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String,Object>();
			mapRequest.put("beo", charge);
//			mapRequest.put("grCli", grCli);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.norChgRecDetail(requestEnvelop);

		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			List<Charge> chglist = (ArrayList<Charge>)((HashMap)resEnv.getBody()).get("beo1");

				ClassHelper.copyProperties(chglist.get(0), form);
			
//				return mapping.findForward("norChgRecDetail");
				return new ActionForward("/ChargeAction.do?method=query&charge=detail");
		} 
        else {
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
	/**
	 * 显示批量录入普通商品页面
	 */
	public ActionForward normalChargeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
		ActionForward af = new ActionForward();
		
		
		
		String forward = null;
		forward = "/charge/norchgdetail.jsp";
		try {
			ClassHelper.copyProperties(cf, charge);
			charge.setFileKey("chg01_007");
			String hql = queryEnterprise(charge);
			af = super.init(req, forward, hql);
			ClassHelper.copyProperties(charge, form);
//			super.saveSuccessfulMsg(req, "新增收费信息成功!");
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	   

	/**
	 * 耳背机收费细节
	 */
	public ActionForward erbeiChargeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
		ActionForward af = new ActionForward();
		String forward = null;
		forward = "/charge/erbeichgdetail.jsp";
		try {
			ClassHelper.copyProperties(cf, charge);
			charge.setFileKey("chg01_007");
			String hql = queryEnterprise(charge);
			af = super.init(req, forward, hql);
			ClassHelper.copyProperties(charge, form);
//			super.saveSuccessfulMsg(req, "新增收费信息成功!");
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 显示批量录入普通商品页面
	 */
	public ActionForward normalChargeDetailView(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
		ActionForward af = new ActionForward();
		
		
		
		String forward = null;
		forward = "/charge/norchgdetailview.jsp";
		try {
			ClassHelper.copyProperties(cf, charge);
			charge.setFileKey("chg01_007");
			String hql = queryEnterprise(charge);
			af = super.init(req, forward, hql);
			ClassHelper.copyProperties(charge, form);
//			super.saveSuccessfulMsg(req, "新增收费信息成功!");
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	
	/**
	 * 普通商品收费打印
	 */
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String type = req.getParameter("chgid");
		ChargeForm chargeForm = (ChargeForm) form;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext().getRealPath("\\WEB-INF\\report\\report_norchg_new.jasper"));
			
		
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("chgid", chargeForm.getChgid());
			// 连接到数据库
			conn = DBUtil.getConnection();

			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			// res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
			// 自动打印
			PdfReader reader = new PdfReader(bytes);
			StringBuffer script = new StringBuffer();
			script.append("this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
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
	
	
	/**
	 * 定制商品收费打印
	 */
	public ActionForward print2(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ChargeForm chargeForm = (ChargeForm) form;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext().getRealPath("\\WEB-INF\\report\\report_cuschg_new.jasper"));
			
		
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("fdtfno", chargeForm.getFolno());
			parameters.put("pdtnm", chargeForm.getPdtnm());
			// 连接到数据库
			conn = DBUtil.getConnection();

			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			// res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
			// 自动打印
			PdfReader reader = new PdfReader(bytes);
			StringBuffer script = new StringBuffer();
			script.append("this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
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
	
	/**
	 * 打印维修收费单
	 */
	public ActionForward print3(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String type = req.getParameter("type");
		BigDecimal id = new BigDecimal(req.getParameter("id"));
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext().getRealPath("\\WEB-INF\\report\\report_repchg.jasper"));
			
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
	 * 定制机退机打印
	 */
	public ActionForward printCusRec(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		String type = req.getParameter("chgid");
		ChargeForm chargeForm = (ChargeForm) form;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext().getRealPath("\\WEB-INF\\report\\report_recchg.jasper"));
			
		
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("fdtfno", chargeForm.getFolno());
			parameters.put("pdtnm", chargeForm.getPdtnm());
			// 连接到数据库
			conn = DBUtil.getConnection();

			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			// res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
			// 自动打印
			PdfReader reader = new PdfReader(bytes);
			StringBuffer script = new StringBuffer();
			script.append("this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
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
	
	/**
	 * 普通商品退机打印
	 */
	public ActionForward printNorRec(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		String type = req.getParameter("chgid");
		ChargeForm chargeForm = (ChargeForm) form;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext().getRealPath("\\WEB-INF\\report\\report_norrec.jasper"));
			
		
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("chgid", chargeForm.getChgid());
//			parameters.put("pdtnm", chargeForm.getPdtnm());
			// 连接到数据库
			conn = DBUtil.getConnection();

			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			// res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
			// 自动打印
			PdfReader reader = new PdfReader(bytes);
			StringBuffer script = new StringBuffer();
			script.append("this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
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
	
	
	
	
	/**
	 * 查询定制和维修收费基本信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("charge");
		ChargeForm cForm = (ChargeForm) form;
		String forward = null;
		ActionForward af = new ActionForward();
		Charge charge = new Charge();
		try {
			ClassHelper.copyProperties(cForm, charge);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			String  grCli=dto.getBsc001();
			if(!"1501000000".equals(grCli)){
				charge.setChggcltid(dto.getBsc011());
			}
//			charge.setIctgctid(dto.getBsc011());
			if ("search".equals(order)) //search为定制商品收费查询
			{   
				if(null==charge.getFoltype()||charge.getFoltype().equals(""))
				{
					charge.setFileKey("chg02_007");
				    
				}
				else
				{
				 if(charge.getFoltype().equals("make"))
				 {
					charge.setFileKey("chg02_000");
				 }
				 else if(charge.getFoltype().equals("makeEar"))
				 {
					charge.setFileKey("chg02_006");
				 }
				 else if(charge.getFoltype().equals("makeRec"))
				 {
					 charge.setFileKey("chg02_010");
				 }
				 else
				 {
					 charge.setFileKey("chg02_011");
				 }
				}
				forward = "/charge/cuschgquery.jsp";
				
			} 
			else if("repair".equals(order)) //repair为维修商品收费查询
			{
				forward = "/charge/repchgquery.jsp";
				if("0".equals(charge.getFolischg())){
					charge.setFileKey("chg03_000");
				}else{
					if("1".equals(charge.getFolischg())){
						charge.setFileKey("chg03_005");
					}else {
						charge.setFileKey("chg03_006");
					}
				}
			}
			else if("recoil".equals(order))
			{
//				String reset = req.getParameter("reset");
//				if(null!=reset&&!reset.equals(" ")&&reset.equals("true"))
//				{
//					charge.setChggcltid(null);
//					charge.setNcdid(null);
//					charge.setNcdpid(null);
//					charge.setNcdsta(null);
//					ClassHelper.copyProperties(charge, form);
//				}
//				charge.setFileKey("chg04_003");
//				forward = "/charge/recoilquery.jsp";
				charge.setFileKey("chg04_009");
				forward = "/charge/recoilquery.jsp";
			}
			else if("recoilhead".equals(order))
			{
				charge.setFileKey("chg04_003");
			}
			else if("detail".equals(order))
			{
//				String tp=req.getParameter("tp");
//				String str="";
//				if(null!=tp&&tp.equals("s"))
//				{
//					str="已退回直属店";
//				}
//				super.saveSuccessfulMsg(req, str);
				charge.setFileKey("chg04_008");
				forward = "/charge/norchgrecdetail.jsp";
			}
			String hql = queryEnterprise(charge);
			if("recoilhead".equals(order))
			{
				if((null!=cForm.getNcddtty()&&!"".equals(cForm.getNcddtty()))&&(null!=cForm.getStart()&&!"".equals(cForm.getStart())))
				{
					hql+=" and "+cForm.getNcddtty()+">=to_date('"+cForm.getStart()+"','yyyy-MM-DD')";
				}
				if((null!=cForm.getNcddtty()&&!"".equals(cForm.getNcddtty()))&&(null!=cForm.getEnd()&&!"".equals(cForm.getEnd())))
				{
					hql+=" and "+cForm.getNcddtty()+"<=to_date('" +cForm.getEnd()+"','yyyy-MM-DD')";
				}
				forward = "/charge/recoilheadquery.jsp";
			}
			else if("search".equals(order))
			{
				
				if(null==charge.getFoltype()||charge.getFoltype().equals(""))
				{
					if((null!=cForm.getFoldtty()&&!"".equals(cForm.getFoldtty()))&&(null!=cForm.getStart()&&!"".equals(cForm.getStart())))
					{	
						hql+=" and "+cForm.getFoldtty()+">=to_date('"+cForm.getStart()+"','yyyy-MM-DD')";
					}
					if((null!=cForm.getFoldtty()&&!"".equals(cForm.getFoldtty()))&&(null!=cForm.getEnd()&&!"".equals(cForm.getEnd())))
					{
						hql+=" and "+cForm.getFoldtty()+"<=to_date('" +cForm.getEnd()+"','yyyy-MM-DD')";
					}
				}
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
	 * 查询加盟店定制和维修收费基本信息
	 */
	public ActionForward jmdquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("charge");
		ChargeForm cForm = (ChargeForm) form;
		String forward = null;
		ActionForward af = new ActionForward();
		Charge charge = new Charge();
		try {
			ClassHelper.copyProperties(cForm, charge);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			String  grCli=dto.getBsc001();
			if(!"1501000000".equals(grCli)){
				charge.setChggcltid(dto.getBsc011());
			}
//			charge.setIctgctid(dto.getBsc011());
			if ("search".equals(order)) //search为定制商品收费查询
			{   
				if(null==charge.getFoltype()||charge.getFoltype().equals(""))
				{
					charge.setFileKey("chg021_007");
				    
				}
				else
				{
				 if(charge.getFoltype().equals("make"))
				 {
					charge.setFileKey("chg021_000");
				 }
				 else if(charge.getFoltype().equals("makeEar"))
				 {
					charge.setFileKey("chg021_006");
				 }
				 else if(charge.getFoltype().equals("makeRec"))
				 {
					 charge.setFileKey("chg021_010");
				 }
				 else
				 {
					 charge.setFileKey("chg021_011");
				 }
				}
				forward = "/charge/cuschgqueryjmd.jsp";
				
			} 
			else if("repair".equals(order)) //repair为维修商品收费查询
			{
				forward = "/charge/repchgquery.jsp";
				if("0".equals(charge.getFolischg())){
					charge.setFileKey("chg03_000");
				}else{
					if("1".equals(charge.getFolischg())){
						charge.setFileKey("chg03_005");
					}else {
						charge.setFileKey("chg03_006");
					}
				}
			}
			else if("recoil".equals(order))
			{
//				String reset = req.getParameter("reset");
//				if(null!=reset&&!reset.equals(" ")&&reset.equals("true"))
//				{
//					charge.setChggcltid(null);
//					charge.setNcdid(null);
//					charge.setNcdpid(null);
//					charge.setNcdsta(null);
//					ClassHelper.copyProperties(charge, form);
//				}
//				charge.setFileKey("chg04_003");
//				forward = "/charge/recoilquery.jsp";
				charge.setFileKey("chg04_009");
				forward = "/charge/recoilquery.jsp";
			}
			else if("recoilhead".equals(order))
			{
				charge.setFileKey("chg04_003");
			}
			else if("detail".equals(order))
			{
//				String tp=req.getParameter("tp");
//				String str="";
//				if(null!=tp&&tp.equals("s"))
//				{
//					str="已退回直属店";
//				}
//				super.saveSuccessfulMsg(req, str);
				charge.setFileKey("chg04_008");
				forward = "/charge/norchgrecdetail.jsp";
			}
			String hql = queryEnterprise(charge);
			if("recoilhead".equals(order))
			{
				if((null!=cForm.getNcddtty()&&!"".equals(cForm.getNcddtty()))&&(null!=cForm.getStart()&&!"".equals(cForm.getStart())))
				{
					hql+=" and "+cForm.getNcddtty()+">=to_date('"+cForm.getStart()+"','yyyy-MM-DD')";
				}
				if((null!=cForm.getNcddtty()&&!"".equals(cForm.getNcddtty()))&&(null!=cForm.getEnd()&&!"".equals(cForm.getEnd())))
				{
					hql+=" and "+cForm.getNcddtty()+"<=to_date('" +cForm.getEnd()+"','yyyy-MM-DD')";
				}
				forward = "/charge/recoilheadquery.jsp";
			}
			else if("search".equals(order))
			{
				
				if(null==charge.getFoltype()||charge.getFoltype().equals(""))
				{
					if((null!=cForm.getFoldtty()&&!"".equals(cForm.getFoldtty()))&&(null!=cForm.getStart()&&!"".equals(cForm.getStart())))
					{	
						hql+=" and "+cForm.getFoldtty()+">=to_date('"+cForm.getStart()+"','yyyy-MM-DD')";
					}
					if((null!=cForm.getFoldtty()&&!"".equals(cForm.getFoldtty()))&&(null!=cForm.getEnd()&&!"".equals(cForm.getEnd())))
					{
						hql+=" and "+cForm.getFoldtty()+"<=to_date('" +cForm.getEnd()+"','yyyy-MM-DD')";
					}
				}
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
	 * 准备保存定制机收费信息的页面详情
	 */
	public ActionForward customizedChargeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("hello");
		String folno = req.getParameter("folno");
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
		
		if (null == folno || "".equalsIgnoreCase(folno)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		}
		if (!("finish".equals(cf.getFolsta()))&&!("recback".equals(cf.getFolsta())))
		{
			super.saveSuccessfulMsg(req, "此定制订单已收费或者已退机，不能再收费！");//修改的收费判断功能
			return mapping.findForward("backspace");
		}
		if (cf.getFolischg().equals("1")){
			super.saveSuccessfulMsg(req, "此定制订单已收费，不可重复收费！");
			return mapping.findForward("backspace");
		}
		else
		{
			try {
				ClassHelper.copyProperties(cf, charge); //
				ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
				
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap<String, Charge> mapRequest = new HashMap<String, Charge>();
				mapRequest.put("beo", charge);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.show(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);	
				if (returnValue.isSucessFlag()) {
					List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");// 定制商品收费信息
					
					
					ClassHelper.copyProperties(listci.get(0), cf);	//原来的 cf填charge时有值   ，现在改成cf
					
					
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
					super.saveErrors(req, new AppException(aa[3]));
				}
			} catch (AppException ex) {
				this.saveErrors(req, ex);
			} catch (Exception e) {
				this.saveErrors(req, e);
			}
		}
		return mapping.findForward("cusChargeDetail");
	}
	/**
	 * 准备保存加盟店定制机收费信息的页面详情
	 */
	public ActionForward jmdcustomizedChargeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("hello");
		String folno = req.getParameter("folno");
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
		
		if (null == folno || "".equalsIgnoreCase(folno)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		}
		if (!("finish".equals(cf.getFolsta()))&&!("recback".equals(cf.getFolsta())))
		{
			super.saveSuccessfulMsg(req, "此定制订单已收费或者已退机或者退机审核通过，不能再收费！");
			return mapping.findForward("backspace");
		}
		if (cf.getFolischg().equals("1")){
			super.saveSuccessfulMsg(req, "此定制订单已收费，不可重复收费！");
			return mapping.findForward("backspace");
		}
		else
		{
			try {
				ClassHelper.copyProperties(cf, charge); //
				ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
				
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap<String, Charge> mapRequest = new HashMap<String, Charge>();
				mapRequest.put("beo", charge);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.show(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);	
				if (returnValue.isSucessFlag()) {
					List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");// 定制商品收费信息
					
					
					ClassHelper.copyProperties(listci.get(0), cf);	//原来的 cf填charge时有值   ，现在改成cf
					
					
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
					super.saveErrors(req, new AppException(aa[3]));
				}
			} catch (AppException ex) {
				this.saveErrors(req, ex);
			} catch (Exception e) {
				this.saveErrors(req, e);
			}
		}
		return mapping.findForward("jmdcusChargeDetail");
	}
	
	/**
	 * 跳转至定制机退机退费信息的页面详情
	 */
	public ActionForward customizedRecoilDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String folno = req.getParameter("folno");
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
		
		if (null == folno || "".equalsIgnoreCase(folno)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		}
		
//		if (cf.getFolischg().equals("1")){
//			super.saveSuccessfulMsg(req, "此定制订单已收费，不可重复收费！");
//			return mapping.findForward("backspace");
//		}
//		else
//		{
			try {
				ClassHelper.copyProperties(cf, charge); //
				ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
				
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap<String, Charge> mapRequest = new HashMap<String, Charge>();
				mapRequest.put("beo", charge);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.show1(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);	
				if (returnValue.isSucessFlag()) {
					List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");// 定制商品收费信息
					
					
					ClassHelper.copyProperties(listci.get(0), cf);	//原来的 cf填charge时有值   ，现在改成cf
//					String tp=req.getParameter("tp");
//					String str="已退回";
//					if(null!=tp && !"".equals(tp))
//					{
//						if(tp.equals("s"))
//						{
//							str+="直属店";
//						}
//						else if(tp.equals("c"))
//						{
//							str+="总部";
//						}
//						super.saveSuccessfulMsg(req, str);
//						return mapping.findForward("backspace");
//					}
					return mapping.findForward("cusRecDetail");
					
					
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
					super.saveErrors(req, new AppException(aa[3]));
					return mapping.findForward("backspace");
				}
			} catch (AppException ex) {
				this.saveErrors(req, ex);
				return mapping.findForward("backspace");
			} catch (Exception e) {
				this.saveErrors(req, e);
				return mapping.findForward("backspace");
			}
//		}
		
	}
	
	/**
	 * 显示退机收费信息的页面详情
	 */
	public ActionForward recoilChargeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String folno = req.getParameter("fdtfno");
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm) form;	
		
		if (null == folno || "".equalsIgnoreCase(folno)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		}else if (cf.getFolischg().equals("1")){
			super.saveSuccessfulMsg(req, "此退机订单已收费，不可重复收费！");
			return mapping.findForward("backspace");
		}
		else
		{
			try {
				ClassHelper.copyProperties(cf, charge);
				charge.setFdtfno(folno);
				ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
				
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap<String, Charge> mapRequest = new HashMap<String, Charge>();
				mapRequest.put("beo", charge);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.print3(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);	
				if (returnValue.isSucessFlag()) {
					List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");// 定制商品收费信息
					
					
					ClassHelper.copyProperties(listci.get(0), cf);	//原来的 cf填charge时有值   ，现在改成cf
					
					
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
					super.saveErrors(req, new AppException(aa[3]));
				}
			} catch (AppException ex) {
				this.saveErrors(req, ex);
			} catch (Exception e) {
				this.saveErrors(req, e);
			}
		}
		return mapping.findForward("recChargeDetail");
	}
	
	
	
	/**
	 * 保存定制机收费信息并调用view方法
	 */
	public ActionForward saveCustomizedCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm)form;
		
		try{
			//将表单中的数据传到实体层
			ClassHelper.copyProperties(cf, charge);
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DATE);
			charge.setChgdt(DateUtil.getDate(year, month, day));
			/**/
			if("1".equals(charge.getFolurgischg()))
			{
				charge.setFolurgrfee(charge.getFolurgfee());
			}
			else
			{
				double rfee=0;
				charge.setFolurgrfee(rfee);
				charge.setFolurgischg("0");
				//double balance=charge.getBalance();
				//double urgfee=charge.getFolurgfee();
				//balance=balance-urgfee;
				//charge.setBalance(balance);
			}
			
			
			//获取服务接口
			ChargeFacade facade = (ChargeFacade)getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", charge);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.charge2(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "保存定制收费信息成功");
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("folno");	
				//return mapping.findForward("cusPrint");
				return new ActionForward(
						"/ChargeAction.do?method=view&folno=" + folno);
			}
			else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
		}
	}
	
	
	
	/**
	 * 保存定制机退机退费信息并调用view方法
	 */
	public ActionForward saveCustomizedRec(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm)form;
		String tp = req.getParameter("tp");
		try{
			//将表单中的数据传到实体层
			ClassHelper.copyProperties(cf, charge);
//			Calendar calendar = Calendar.getInstance();
//			int year = calendar.get(Calendar.YEAR);
//			int month = calendar.get(Calendar.MONTH) + 1;
//			int day = calendar.get(Calendar.DATE);
//			charge.setChgdt(DateUtil.getDate(year, month, day));
			if(tp.equals("c"))
			{
//				super.saveSuccessfulMsg(req, "在退回总部前必须先退回直属店");
//				return mapping.findForward("cusRecDetail");
				if("recoiledhead".equals(charge.getFolsta()))
				{
					throw new AppException("不能重复退回总部");
				}
				else if("finish".equals(charge.getFolsta())||"recback".equals(charge.getFolsta())||"charged".equals(charge.getFolsta()))
				{
					throw new AppException("在退回总部前必须先退回直属店");
				}
				
			}
			
			if(tp.equals("s"))
			{
//				super.saveSuccessfulMsg(req, "在退回总部前必须先退回直属店");
//				return mapping.findForward("cusRecDetail");
				if("recoiled".equals(charge.getFolsta()))
				{
					throw new AppException("不能重复退回直属店");
				}
				else if("recoiledhead".equals(charge.getFolsta()))
				{
					throw new AppException("已退回总部，不能再退回直属店");
				}
				
			}
//			charge.setFolrecdt(DateUtil.getDate());
			
			
			//获取服务接口
			ChargeFacade facade = (ChargeFacade)getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			ResponseEnvelop resEnv=null;
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", charge);
			requestEnvelop.setBody(mapRequest);
			if(tp.equals("s"))
			{
			// 调用对应的Facade业务处理方法
			    resEnv = facade.saveCustomizedRec(requestEnvelop);
			}
			else if(tp.equals("c"))
			{
				resEnv = facade.commitCustomizedRec(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				String str="已退回";
				if(tp.equals("s"))
				{
					str+="直属店";
				}
				else if(tp.equals("c"))
				{
					str+="总部";
				}
				super.saveSuccessfulMsg(req, str);
//				String folno = (String) ((HashMap) resEnv.getBody())
//						.get("folno");	
				//return mapping.findForward("cusPrint");

				
			}
			else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (AppException ex) {
			this.saveErrors(req, ex);
			//return mapping.findForward("cusRecDetail");
			return mapping.findForward("backspace");
		} 
		catch (Exception e) {
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
		}
		//return go2Page(req, mapping, "charge");
		return new ActionForward("/ChargeAction.do?method=customizedRecoilDetail&tp="+tp);
	}
	
	
	
	/**
	 * 雏形保存普通商品退机退费信息并调用view方法
	 */
	public ActionForward saveNomRec(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
//		Charge charge = new Charge();
		String tp = req.getParameter("tp");
		String chgid = req.getParameter("chgid");  //检查chgid是否为空值
		ChargeForm cf = (ChargeForm)form;
//		SubmitDataMap data = new SubmitDataMap(req);
		Collection<Charge> collection = null;
//		String[] ncdidList=data.getParameterValues("ncdid");
	
//		String[] ncdpidList=data.getParameterValues("pdtid");
////		String[] ncdstaList=data.getParameterValues("ncdsta");
//		String[] ncdrntList=data.getParameterValues("ncdrnt");
//		String[] ncdrecmonList=data.getParameterValues("ncdrecmon");
    
		collection = TypeCast.getEntities(new SubmitDataMap(req),Charge.class);
		
//		String tp = req.getParameter("tp");
//		if(null==ncdstaList)
//    	{
//    		throw new AppException("提交前要先进行保存");
//    	}
//		String[] tdspvooutList=data.getParameterValues("tdspvoout");
//		String[] tdsntList=data.getParameterValues("tdsnt");
		try{
//	    List<Charge> chgList=new ArrayList<Charge>();
//	    String str1="";
//	    String str2="";
//	    String str3="";
//	    String alt1="";
//	    String alt2="";
//	    String alt3="";
	    int i=1;
	    
	    for(Charge chg:collection)
	    {
//	    	chg.setNcdid(chgid);
	    	if("s".equals(tp))
	    	{
	    		if("nomrecoiled".equals(chg.getNcdsta()))
	    		{
	    			throw new AppException("商品代码为"+chg.getNcdpid()+"的商品不能重复退回到直属店");
	    		}
	    		if("commited".equals(chg.getNcdsta()))
	    		{
	    			throw new AppException("商品代码为"+chg.getNcdpid()+"的商品已退回到总部，不能重复退回到直属店");
	    		}
	    		if("pass".equals(chg.getNcdsta()))
	    		{
	    			throw new AppException("商品代码为"+chg.getNcdpid()+"的商品已审核通过，不能重复退回到直属店");
	    		}
	    	}
    	    if("c".equals(tp))
        	{
    	    	if("commited".equals(chg.getNcdsta()))
        		{
        			throw new AppException("商品代码为"+chg.getNcdpid()+"的商品不能重复退回到总部");
        		}
    	    	if(!"nomrecoiled".equals(chg.getNcdsta()))
    	    	{
    	    		throw new AppException("商品代码为"+chg.getNcdpid()+"的商品不能退回到总部，请先退回到直属店");
    	    	}
        		
        	}
    	    
    	    i++;
	    }
	    
	    //[start]
//	    for(int i=0;i<ncdpidList.length;i++)
//	    {
//	    for(Charge chg:collection)
//	    {
//	    	Charge chg=new Charge();
//	    	if(tp.equals("s"))
//	    	{
//	    		chg.setNcdid(cf.getChgid());
//	    		if(null!=ncdrntList[i] && !"".equals(ncdrntList[i]))
//		    	{
//		    		chg.setNcdrnt(Integer.parseInt(ncdrntList[i]));
//		    		chg.setNcdqnt(-Math.abs(Integer.parseInt(ncdrntList[i])));
//		    	}
//		    	
//		    	if(null!=ncdrecmonList[i] && !"".equals(ncdrecmonList[i]))
//		    	{
//		    		chg.setNcdrecmon(Double.parseDouble(ncdrecmonList[i]));
//		   
//		    	}
//	    	}
//	    	else if(tp.equals("c"))
//	    	{
//	    		String[] ncdoidList=data.getParameterValues("ncdoid");
//	    		chg.setNcdoid(ncdoidList[i]);
//	    	}
	    
	    //[end]
	    
//	    	if(!"".equals(ncdstaList[i])&&(ncdstaList[i].equals("commited") || ncdstaList[i].equals("pass")))
//	    	{
////	    		throw new AppException("第"+(i+1)+"行数据的状态不能保存或提交");
//	    		str1+=ncdpidList[i]+",";
//	    		
//	    	}
//	    	if(tp.equals("s"))
//	    	{
//	    		if(!"".equals(ncdstaList[i])&&(ncdstaList[i].equals("nomrecoiled")))
//	    		{
//	    			alt1=ncdpidList[i]+",";
//	    		}
//	    	}
//	    	if(tp.equals("m"))
//	    	{
//	    		if(!"".equals(ncdstaList[i])&&(ncdstaList[i].equals("modifyed")))
//	    		{
//	    			alt2=ncdpidList[i]+",";
//	    		}
//	    		if(!"".equals(ncdstaList[i])&&(!ncdstaList[i].equals("nomrecoiled")))
//	    		{
//	    			str2+=ncdpidList[i]+",";
//	    		}
//	    		
//	    	}
	    	
//	    	if(tp.equals("c"))
//	    	{
//	    		if(!"".equals(ncdstaList[i])&&(ncdstaList[i].equals("commited")))
//	    		{
//	    			alt3=ncdpidList[i]+",";
//	    		   
//	    		}
//	    		if(!"".equals(ncdstaList[i])&&(!ncdstaList[i].equals("modifyed")))
//	    		{
//	    			str3+=ncdpidList[i]+",";
//	    		}
//	    		
//	    	}
	    
//	    	chg.setNcdid(ncdpidList[i]);
	    //[start]
//	    	chg.setNcdpid(ncdpidList[i]);
//	    	chgList.add(chg);
	   
	    	
            
//	    }
	    //[end]
	
//	    if(!"".equals(str1))
//    	{
//    		str1=str1.substring(0, str1.length()-1);
//    		throw new AppException("商品代码为"+str1+"的商品状态不能退回到直属店或总部");
//    	}
	    
//	    if(!"".equals(str2))
//    	{
//    		str2=str2.substring(0, str2.length()-1);
//    		throw new AppException("商品代码为"+str2+"的商品在修改前必须先退回到直属店或\n已退回总部后就不能再修改");
//    	}
	    
//	    if(!"".equals(str3))
//    	{
//    		str3=str3.substring(0, str3.length()-1);
//    		throw new AppException("商品代码为"+str3+"的商品在退回总部前必须先修改退机费");
//    	}
//	    
//	    if(!"".equals(alt1))
//    	{
//	    	alt1=alt1.substring(0, alt1.length()-1);
//    		throw new AppException("商品代码为"+alt1+"的商品不能重复退回直属店");
//    	}
	
			//获取服务接口
			ChargeFacade facade = (ChargeFacade)getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			ResponseEnvelop resEnv=null;
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
//			mapRequest.put("beo", chgList);
			mapRequest.put("beo", collection);
			requestEnvelop.setBody(mapRequest);
			if(tp.equals("s"))
			{
			// 调用对应的Facade业务处理方法
			    resEnv = facade.saveNomRec(requestEnvelop);
			}
			else if(tp.equals("c"))
			{
				
				resEnv = facade.commitNomRec(requestEnvelop);
			}
//			else if(tp.equals("m"))
//			{
//				resEnv = facade.modifyNomRec(requestEnvelop);
//			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
//				String str="已";
//				if(tp.equals("s"))
//				{
//					str+="退回直属店";
//				}
//				else if(tp.equals("c"))
//				{
//					str+="退回总部";
//				}
//				else if(tp.equals("m"))
//				{
//					str+="修改";
//				}
//				super.saveSuccessfulMsg(req, str);
//				String folno = (String) ((HashMap) resEnv.getBody())
//						.get("folno");	
				if(tp.equals("s"))
				{
					super.saveSuccessfulMsg(req, "已退回直属店");
					//return new ActionForward("/ChargeAction.do?method=query&charge=detail&tp=s");
					return go2Page(req,mapping,"charge");
//					return mapping.findForward("backspace");
				}
				

				super.saveSuccessfulMsg(req, "已退回总部");
				return go2Page(req, mapping, "charge");

//				return go2Page(req, mapping, "charge");
			}
			else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}
		catch (AppException ex) {
			this.saveErrors(req, ex);
//			ActionForward actionForward = new ActionForward("/ChargeAction.do?method=query&charge=recoil");
//			actionForward.setRedirect(true);
//			return actionForward;
			//return new ActionForward("/ChargeAction.do?method=query&charge=recoil&reset=true");
			return mapping.findForward("backspace");
		}
		catch (Exception e) {
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 雏形保存普通商品退机退费信息并调用view方法
	 */
//	public ActionForward saveNomRec(ActionMapping mapping, ActionForm form,
//			HttpServletRequest req, HttpServletResponse res) throws Exception{
////		Charge charge = new Charge();
//		ChargeForm cf = (ChargeForm)form;
//		SubmitDataMap data = new SubmitDataMap(req);
//		String[] ncdrntList=data.getParameterValues("ncdrnt");
//		String[] ncdidList=data.getParameterValues("ncdid");
//		String[] ncdstaList=data.getParameterValues("ncdsta");
//		String[] ncdpidList=data.getParameterValues("ncdpid");
//		String tp = req.getParameter("tp");
////		if(null==ncdstaList)
////    	{
////    		throw new AppException("提交前要先进行保存");
////    	}
////		String[] tdspvooutList=data.getParameterValues("tdspvoout");
////		String[] tdsntList=data.getParameterValues("tdsnt");
//		try{
//	    List<Charge> chgList=new ArrayList<Charge>();
//	    String str1="";
//	    String str2="";
//	    for(int i=0;i<ncdrntList.length;i++)
//	    {
//	    	if(!"".equals(ncdstaList[i])&&(ncdstaList[i].equals("commited") || ncdstaList[i].equals("pass")))
//	    	{
////	    		throw new AppException("第"+(i+1)+"行数据的状态不能保存或提交");
//	    		str1+=ncdidList[i]+",";
//	    		
//	    	}
//	    	
//	    	
//	    	if(tp.equals("c"))
//	    	{
//	    		if(!"".equals(ncdstaList[i])&&(!ncdstaList[i].equals("nomrecoiled")))
//	    		{
//	    			str2+=ncdidList[i]+",";
////	    			throw new AppException("收费号为"+ncdidList[i]+"数据提交前要先进行保存");
//	    		}
//	    		
//	    	}
//	    	Charge chg=new Charge();
//	    	if(null!=ncdrntList[i] && !"".equals(ncdrntList[i]))
//	    	{
//	    		chg.setNcdrnt(Integer.parseInt(ncdrntList[i]));
//	    	}
//	    	chg.setNcdid(ncdidList[i]);
//	    	chg.setNcdpid(ncdpidList[i]);
//	    	chgList.add(chg);
//	    	
//            
//	    }
//	
//	    if(!"".equals(str1))
//    	{
//    		str1=str1.substring(0, str1.length()-1);
//    		throw new AppException("收费号为"+str1+"的数据状态不能保存或提交");
//    	}
//	    
//	    if(!"".equals(str2))
//    	{
//    		str2=str2.substring(0, str2.length()-1);
//    		throw new AppException("收费号为"+str2+"的数据状态不能保存或提交");
//    	}
//			//获取服务接口
//			ChargeFacade facade = (ChargeFacade)getService("ChargeFacade");
//			RequestEnvelop requestEnvelop = new RequestEnvelop();
//			EventResponse returnValue = new EventResponse();
//			ResponseEnvelop resEnv=null;
//			// 将Application对象放入HashMap
//			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
//			mapRequest.put("beo", chgList);
//			requestEnvelop.setBody(mapRequest);
//			if(tp.equals("s"))
//			{
//			// 调用对应的Facade业务处理方法
//			    resEnv = facade.saveNomRec(requestEnvelop);
//			}
//			else if(tp.equals("c"))
//			{
//				
//				resEnv = facade.commitNomRec(requestEnvelop);
//			}
//			// 处理返回结果
//			returnValue = processRevt(resEnv);
//			if(returnValue.isSucessFlag())
//			{
//				String str="";
//				if(tp.equals("s"))
//				{
//					str="保存";
//				}
//				else if(tp.equals("c"))
//				{
//					str="提交";
//				}
//				super.saveSuccessfulMsg(req, str+"定制机退机退费信息成功");
////				String folno = (String) ((HashMap) resEnv.getBody())
////						.get("folno");	
//				//return mapping.findForward("cusPrint");
//
//				return go2Page(req, mapping, "charge");
//			}
//			else {
//				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
//				super.saveSuccessfulMsg(req, aa[3]);
//				return mapping.findForward("backspace");
//			}
//		}
//		catch (AppException ex) {
//			this.saveErrors(req, ex);
////			ActionForward actionForward = new ActionForward("/ChargeAction.do?method=query&charge=recoil");
////			actionForward.setRedirect(true);
////			return actionForward;
//			return new ActionForward("/ChargeAction.do?method=query&charge=recoil&reset=true");
//		}
//		catch (Exception e) {
//				super.saveErrors(req, e);
//				return mapping.findForward("backspace");
//		}
//	}
	
	
	/**
	 * 保存普通商品退机退费信息并调用view方法
	 */
	public ActionForward exaNomRec(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
//		Charge charge = new Charge();
//		ChargeForm cf = (ChargeForm)form;
		SubmitDataMap data = new SubmitDataMap(req);
//		String[] ncdrntList=data.getParameterValues("ncdrnt");
		String[] ncdrntList=data.getParameterValues("ncdrnt");
		String[] ncdoidList=data.getParameterValues("ncdid");
		String[] ncdpidList=data.getParameterValues("ncdpid");
//		if(null==ncdstaList)
//    	{
//    		throw new AppException("提交前要先进行保存");
//    	}
//		String[] tdspvooutList=data.getParameterValues("tdspvoout");
//		String[] tdsntList=data.getParameterValues("tdsnt");
	    List<Charge> chgList=new ArrayList<Charge>();
	    for(int i=0;i<ncdrntList.length;i++)
	    {
	    	Charge chg=new Charge();
	    	if(null!=ncdrntList[i] && !"".equals(ncdrntList[i]))
	    	{
	    		chg.setNcdrnt(Math.abs(Integer.parseInt(ncdrntList[i])));
	    	}
	    	chg.setNcdid(ncdoidList[i]);
//	    	chg.setNcdid(folnoList[i]);
	    	chg.setNcdpid(ncdpidList[i]);
	    	chgList.add(chg);
//	    	if(!"".equals(ncdstaList[i])&&ncdstaList[i].equals("finish"))
//	    	{
//	    		throw new AppException("提交前要先进行保存");
//	    	}
            
	    }
		String tp = req.getParameter("tp");
		try{
			//获取服务接口
			ChargeFacade facade = (ChargeFacade)getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			ResponseEnvelop resEnv=null;
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", chgList);
			mapRequest.put("tp", tp);
			requestEnvelop.setBody(mapRequest);
//			if(tp.equals("s"))
//			{
			// 调用对应的Facade业务处理方法
			    resEnv = facade.exaNomRec(requestEnvelop);
//			}
//			else if(tp.equals("c"))
//			{
//				resEnv = facade.commitNomRec(requestEnvelop);
//			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				String str="";
				if(tp.equals("e"))
				{
					str="审核通过！";
				}
				else if(tp.equals("r"))
				{
					str="已回退！";
				}
				super.saveSuccessfulMsg(req, str);
//				String folno = (String) ((HashMap) resEnv.getBody())
//						.get("folno");	
//				return mapping.findForward("norChgRecDetail");

				return go2Page(req, mapping, "charge");
			}
			else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
			    e.printStackTrace();
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
		}
	}

	
	
	/**
	 * 保存定制机退机退费信息并调用view方法
	 */
	public ActionForward exaCusRec(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
//		Charge charge = new Charge();
//		ChargeForm cf = (ChargeForm)form;
		SubmitDataMap data = new SubmitDataMap(req);
//		String[] ncdrntList=data.getParameterValues("ncdrnt");
		String[] folnoList=data.getParameterValues("folno");
		String[] fdtpidList=data.getParameterValues("fdtpid");
//		Collection<Order> collection = null;
//		collection = TypeCast.getEntities(new SubmitDataMap(req),
//				Order.class);
//		for (Order od : collection) {
//			od.getFolno();
//		}
//		if(null==ncdstaList)
//    	{
//    		throw new AppException("提交前要先进行保存");
//    	}
//		String[] tdspvooutList=data.getParameterValues("tdspvoout");
//		String[] tdsntList=data.getParameterValues("tdsnt");
		String tp = req.getParameter("tp");
		List<Order> odList=new ArrayList<Order>();
//		if("e".equals(tp))
//		{
			
			for(int i=0;i<fdtpidList.length;i++)
			{
	    	Order ord=new Order();
//	    	if(null!=fdtpidList[i] && !"".equals(fdtpidList[i]))
//	    	{
//	    		chg.setNcdrnt(Math.abs(Integer.parseInt(fdtpidList[i])));
//	    	}
//	    	chg.setNcdid(folnoList[i]);
//	    	chg.setNcdpid(fdtpidList[i])
	    	ord.setFolno(folnoList[i]);
	    	ord.setFdtpid(fdtpidList[i]);
	    	odList.add(ord);
//	    	if(!"".equals(ncdstaList[i])&&ncdstaList[i].equals("finish"))
//	    	{
//	    		throw new AppException("提交前要先进行保存");
//	    	}
            
			}
//		}
		
		try{
			//获取服务接口
			//OrderFacade facade = (OrderFacade) getService("OrderFacade");
			ChargeFacade facade = (ChargeFacade)getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			ResponseEnvelop resEnv=null;
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
//			if("e".equals(tp))
//			{
			mapRequest.put("beo", odList);
//			}
			mapRequest.put("tp", tp);
			requestEnvelop.setBody(mapRequest);

			resEnv = facade.exaCusRec(requestEnvelop);

			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				String str="";
				if(tp.equals("e"))
				{
					str="审核通过！";
				}
				else if(tp.equals("r"))
				{
					str="已回退！";
				}
				super.saveSuccessfulMsg(req, str);
				return go2Page(req, mapping, "charge");
			}
			else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
			    e.printStackTrace();
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
		}
	}

	
	
	
	
	/**
	 * 保存退机收费信息
	 */
	public ActionForward saveRecoilCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm)form;
		
		try{
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			//将表单中的数据传到实体层
			ClassHelper.copyProperties(cf, charge);
			
			charge.setChgdt(DateUtil.getDate());
			charge.setDeposit(new Double(0));
			
			charge.setStogrcliid(dto.getBsc011());
			charge.setStoproid(cf.getPdtid());
			if(charge.getFoltype().equals("make")){
				charge.setStoprotype("4");
			}else if (charge.getFoltype().equals("normal")){
				charge.setStoprotype("2");
				
			}
			charge.setStoproname(cf.getPdtnm());
			charge.setStoamount(-1);
			charge.setStoamountun(cf.getPdtut());
			charge.setStoproprice(cf.getPdtprc());
			charge.setStoactype("-1");
			charge.setStoremark("退机");
			charge.setStooprcd(dto.getBsc011());
			charge.setStodisc("0");
			charge.setStodate(DateUtil.getDate());
			
			
			//获取服务接口
			ChargeFacade facade = (ChargeFacade)getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", charge);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.charge4(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "保存退机收费信息成功");
				return mapping.findForward("recoilquery");
			}
			else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
		}
	}
	
	
	/**
	 * 显示定制机收费详细信息
	 */
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String folno = req.getParameter("folno");
		if (folno == null) {
			folno = (String) req.getAttribute("folno");
		}
		req.getSession().setAttribute("folno", folno);
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm)form;
		if (null == folno || "".equalsIgnoreCase(folno)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(cf, charge);
			ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Charge> mapRequest = new HashMap<String, Charge>();
			mapRequest.put("beo", charge);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");
				ClassHelper.copyProperties(listci.get(0), cf);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		return mapping.findForward("view");
	}
	
	
	/**
	 * 显示维修收费详细信息
	 *//*
	public ActionForward repview(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String folno = req.getParameter("folno");
		if (folno == null) {
			folno = (String) req.getAttribute("folno");
		}
		req.getSession().setAttribute("folno", folno);
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm)form;
		if (null == folno || "".equalsIgnoreCase(folno)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(cf, charge);
			ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Charge> mapRequest = new HashMap<String, Charge>();
			mapRequest.put("beo", charge);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");
				ClassHelper.copyProperties(listci.get(0), cf);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		return mapping.findForward("repPrint");
	}*/
	
	
//	/**
//	 * 查询维修收费基本信息
//	 */
//	public ActionForward repairQuery(ActionMapping mapping, ActionForm form,
//			HttpServletRequest req, HttpServletResponse res) throws Exception {
//		ChargeForm cForm = (ChargeForm) form;
//		String forward = null;
//		String fileKey = null;
//		
//		fileKey = "chg03_000";
//		forward = "/charge/repchgquery.jsp";
//
//		ActionForward af = new ActionForward();
//		Charge charge = new Charge();
//		try {
//			ClassHelper.copyProperties(cForm, charge);
//			charge.setFileKey(fileKey);
//			String hql = queryEnterprise(charge);
//			af = super.init(req, forward, hql);
//			// 检查是否存在？
//			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
//				String msg = "没有查询到符合条件的记录！";
//				super.saveSuccessfulMsg(req, msg);
//			}
//		} catch (AppException ex) {
//			this.saveErrors(req, ex);
//		} catch (Exception e) {
//			this.saveErrors(req, e);
//		}
//		return af;
//	}
	
	
	/**
	 * 维修收费信息保存
	 */
	public ActionForward saveRepairCharge(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Charge charge = new Charge();
		ChargeForm cf = (ChargeForm)form;
		
		try{
			//将表单中的数据传到实体层
			ClassHelper.copyProperties(cf, charge);
			charge.setRepchgdt(DateUtil.getDate());
			//获取服务接口
			ChargeFacade facade = (ChargeFacade)getService("ChargeFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", charge);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.charge3(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "保存维修收费信息成功");
				String folno = (String) ((HashMap) resEnv.getBody()).get("folno");	
				return mapping.findForward("repPrint");
				//return new ActionForward("/ChargeAction.do?method=repview&folno=" + folno);
			}
			else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
		}
	}
	

	/**
	 * 显示维修机收费页面
	 */
	public ActionForward repairChargeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String repfolid = req.getParameter("repfolid");
		String repgctnm = req.getParameter("repgctnm");
		String repdate = req.getParameter("repdate");
		System.out.println(repgctnm);
		Charge charge = new Charge();
		System.out.println(repdate);
		ChargeForm cf = (ChargeForm) form;	
		ActionForward af = new ActionForward();

		String forward = "/business/realtimequery.jsp";
		if (null == repfolid || "".equalsIgnoreCase(repfolid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		}else if(cf.getFolischg().equals("1")){
			super.saveSuccessfulMsg(req, "此维修订单已收费，不可重复收费！");
			return mapping.findForward("backspace");
		}
		else{
			try {
				ClassHelper.copyProperties(cf, charge);
				ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
				BusinessFacade facade1 = (BusinessFacade) getService("BusinessFacade");
				if (!(facade1.queryStoreType(repgctnm)).equals("A")) {
                   if (repdate.compareTo("2015-04-25 23:59:59")<0){
                	   super.saveSuccessfulMsg(req, "加盟店以前的订单不能收费！");
           			   return mapping.findForward("backspace");
					
				   }  					
					
				}
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap<String, Charge> mapRequest = new HashMap<String, Charge>();
				mapRequest.put("beo", charge);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.print1(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					List listci = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
					ClassHelper.copyProperties(listci.get(0), cf);
	
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),"|");
					super.saveErrors(req, new AppException(aa[3]));
					return mapping.findForward("backspace");
				}
			} catch (Exception e) {
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
			}
		}
		return mapping.findForward("repChargeDetail");
	}
	public void transToString(ChargeForm form,Charge sc){
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
