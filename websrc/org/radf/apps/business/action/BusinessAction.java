package org.radf.apps.business.action;

import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.business.facade.BusinessFacade;
import org.radf.apps.business.form.BusinessForm;
import org.radf.apps.business.form.FeeForm;
import org.radf.apps.charge.facade.ChargeFacade;
import org.radf.apps.charge.form.ChargeForm;
import org.radf.apps.commons.entity.Business;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Fee;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.Report;
import org.radf.apps.commons.entity.ReportFactory;
import org.radf.apps.product.facade.ProductFacade;
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
import org.syntax.jedit.InputHandler.select_all;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;
import com.sun.org.apache.xpath.internal.operations.And;

/**
 * 商务管理
 */
public class BusinessAction extends ActionLeafSupport {

	/**
	 * 查询跳转
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
		String menuId = req.getParameter("menuId");
		// req.getSession().setAttribute("menuId", menuId);
		String forward = menuId;
		BusinessForm bf = new BusinessForm();
		bf.setIvtyear(DateUtil.getYear());
		bf.setActstayear(DateUtil.getYear());
		Calendar cl = Calendar.getInstance();
		cl.setTime(new Date());
		bf.setIvtmonth(cl.get(Calendar.MONTH) + 1);
		bf.setActstamonth(cl.get(Calendar.MONTH) + 1);
		
		//直属店自己销帐，总部只能打印报表
		if("ZSxiaozhang".equals(menuId)){
			bf.setIvtgcltid(dto.getBsc011());
		}
		
		ClassHelper.copyProperties(bf, form);
		return mapping.findForward(forward);
	}

	/**
	 * 月结前校验
	 */
	public ActionForward preyuejie(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String mark = (String)req.getParameter("mark");
		String time = (String)req.getParameter("time");
		Business bi = new Business();
		ActionForward af = new ActionForward();
		String forward = "";
		try {
			bi.setIvtyear(Integer.parseInt(req.getParameter("year")));
			bi.setIvtmonth(Integer.parseInt(req.getParameter("month")));
			LoginDTO dto1 =(LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			// System.out.println(bi);
			if("zhishu".equals(mark))
			{
				bi.setIvtgcltid(dto1.getBsc011());
				bi.setFileKey("bus01_003");
			}else{
				bi.setFileKey("bus01_000");
			}
			String hql = queryEnterprise(bi);
			af = super.init(req, forward, hql);

			if (null != req.getAttribute(GlobalNames.QUERY_DATA)) {
				res.setCharacterEncoding("GBK");
				res.getWriter().write("[{flag:'1'}]");
			} else {
				res.setCharacterEncoding("GBK");
				res.getWriter().write("[{flag:'0'}]");
			}
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return null;
	}

	/**
	 * 月结查询
	 */
	public ActionForward yuejiequery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String mark = req.getParameter("mark");
		Business bi = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward1 = "/business/xiaozhang.jsp";
		String forward2 = "/business/ZSxiaozhang.jsp";
		Connection conn = null;
		String totalYSK = "0";
		try {
			ClassHelper.copyProperties(bf, bi);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			if("jiameng".equals(mark)){
				bi.setIvtyear(bi.getActstayear());
				bi.setIvtmonth(bi.getActstamonth());
				bi.setIvtgcltid(bi.getActstagcltid());
				//bi.setFileKey("bus01_010");
				
				String hql="";
				if("".equals(bi.getIvttype()) || null==bi.getIvttype())
				{
					if("".equals(bi.getPdtnm()) || null==bi.getPdtnm())
					{
						bi.setFileKey("bus01_010");
						hql = queryEnterprise(bi);
				    }
					else
					{
						bi.setFileKey("bus01_015");
						String hql1 = queryEnterprise(bi);
						hql1=hql1+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
						bi.setFileKey("bus01_016");
						String hql2 = queryEnterprise(bi);
						hql2=hql2+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
						hql=hql1+" union all "+hql2;
					}
				}
				else
				{
					if("normal".equals(bi.getIvttype()))
					{
						if("".equals(bi.getPdtnm()) || null==bi.getPdtnm())
						{
							bi.setFileKey("bus01_016");
							hql = queryEnterprise(bi);
						}
						else 
						{
							bi.setFileKey("bus01_016");
							hql = queryEnterprise(bi);
							hql=hql+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
						}	
					}
					else 
					{
						if("makenormal".equals(bi.getIvttype()))
						{
							bi.setFileKey("bus01_015");
							String hql1 = queryEnterprise(bi);
							hql1=hql1+" and y.ivttype='make'";
							bi.setFileKey("bus01_016");
							String hql2 = queryEnterprise(bi);
							if("".equals(bi.getPdtnm()) || null==bi.getPdtnm())
							{
							}
							else{
								hql1=hql1+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
								hql2=hql2+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
							}
							hql=hql1+" union all "+hql2;	
						}
						else 
						{
							bi.setFileKey("bus01_015");
							hql = queryEnterprise(bi);
							
							if("makeEar".equals(bi.getIvttype())){
								hql=hql+" and y.ivttype in('"+bi.getIvttype()+"','makeEarYN') ";
							}else{
								hql=hql+" and y.ivttype='"+bi.getIvttype()+"'";
							}
							//hql=hql+" and y.ivttype='"+bi.getIvttype()+"'";
							if("".equals(bi.getPdtnm()) || null==bi.getPdtnm())
							{
							}
							else 
							{
								hql=hql+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
							}
						}
					}
				}
				
				//String hql = queryEnterprise(bi);
				hql=hql+" order by ivttype,ivtgcltid,ivtpdtid,ivtcltnm";
				af = super.init(req, forward1, hql, "1");
				
			}
			else if("zhishu".equals(mark)){
				String hql="";
				if("".equals(bi.getIvttype()) || null==bi.getIvttype())
				{
					if("".equals(bi.getPdtnm()) || null==bi.getPdtnm())
					{
						bi.setFileKey("bus01_001");
						hql = queryEnterprise(bi);
				    }
					else
					{
						bi.setFileKey("bus01_013");
						String hql1 = queryEnterprise(bi);
						hql1=hql1+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
						bi.setFileKey("bus01_014");
						String hql2 = queryEnterprise(bi);
						hql2=hql2+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
						hql=hql1+" union all "+hql2;
					}
				}
				else
				{
					if("normal".equals(bi.getIvttype()))
					{
						if("".equals(bi.getPdtnm()) || null==bi.getPdtnm())
						{
							bi.setFileKey("bus01_014");
							hql = queryEnterprise(bi);
						}
						else 
						{
							bi.setFileKey("bus01_014");
							hql = queryEnterprise(bi);
							hql=hql+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
						}	
					}
					else 
					{
						if("makenormal".equals(bi.getIvttype()))
						{
							bi.setFileKey("bus01_013");
							String hql1 = queryEnterprise(bi);
							hql1=hql1+" and y.ivttype='make'";
							bi.setFileKey("bus01_014");
							String hql2 = queryEnterprise(bi);
							if("".equals(bi.getPdtnm()) || null==bi.getPdtnm())
							{
							}
							else{
								hql1=hql1+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
								hql2=hql2+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
							}
							hql=hql1+" union all "+hql2;	
						}
						else 
						{
							bi.setFileKey("bus01_013");
							hql = queryEnterprise(bi);
							
							if("makeEar".equals(bi.getIvttype())){
								hql=hql+" and y.ivttype in('"+bi.getIvttype()+"','makeEarYN') ";
							}else{
								hql=hql+" and y.ivttype='"+bi.getIvttype()+"'";
							}
							//hql=hql+" and y.ivttype='"+bi.getIvttype()+"'";
							if("".equals(bi.getPdtnm()) || null==bi.getPdtnm())
							{
							}
							else 
							{
								hql=hql+" and p.pdtnm like '%"+bi.getPdtnm()+"%'";
							}
						}
					}
				}
				
				//String hql = queryEnterprise(bi);
				hql=hql+" order by ivttype,ivtgcltid,ivtpdtid,ivtcltnm";
				af = super.init(req, forward2, hql, "1");
			}
			
			

			ClassHelper.copyProperties(bi, actionForm);
			
			conn = DBUtil.getConnection();
			Statement pstmt = conn.createStatement();
			String sql = "select sum(ivtpamnt) as totalYSK from tblinventory where ivtyear='"
					+ bf.getIvtyear()
					+ "' and ivtmonth='"
					+ bf.getIvtmonth()
					+ "' and ivtgcltid='" + bf.getIvtgcltid() + "'";
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				totalYSK = rs.getString("totalYSK");
			}
			if (totalYSK == null) {
				totalYSK = "0";
			}
			req.getSession().setAttribute("totalYSK", totalYSK);
			rs.close();
			pstmt.close();


			
			
//			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
//				String msg = "该月尚未结算！";
//				super.saveSuccessfulMsg(req, msg);
//			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		} finally {
			DBUtil.closeConnection(conn);
		}
		return af;
	}
	
	
	/**
	 * 直属月结查询
	 */
	public ActionForward ZSyuejiequery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Business bi = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/business/ZSpreview.jsp";
		try {
			ClassHelper.copyProperties(bf, bi);
			bi.setFileKey("bus01_008");
			String hql = queryEnterprise(bi);

			af = super.init(req, forward, hql);
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(req, msg);

			}
			

		} catch(AppException ae){
			this.saveErrors(req, ae);
		}catch(Exception e){
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	
	/**
	 * 加盟月结查询
	 */
	public ActionForward JMyuejiequery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String mark = req.getParameter("mark");
		Business bi = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/business/JMxiaozhang.jsp";
		try {
			ClassHelper.copyProperties(bf, bi);
			//if( bi.getActstayear() < 2013 || (bi.getActstayear() >= 2013 && bi.getActstamonth() <= 4))
			if( bi.getActstayear() < 2013 || (bi.getActstayear() == 2013 && bi.getActstamonth() <= 4))
			{
				//加盟店老数据查找之前的
				bi.setFileKey("bus01_011");
				String hql = queryEnterprise(bi);
				af = super.init(req, forward, hql);
			}
			else
			{
				bi.setFileKey("bus01_009");//bus01005
				String hql = queryEnterprise(bi);
	
				if(null != mark && "back".equals(mark)){
					bi.setActstagcltid(null);
				}
				
				if(null!=bi.getActstagcltid()&&!"".equals(bi.getActstagcltid()))
				{
						hql+=" and y.ivtgcltid='"+bi.getActstagcltid()+"'";
				}
				
				hql+=" group by y.ivtgcltid,y.ivtyear,y.ivtmonth) x,tblgrpclient g left outer join tblactsta t on g.gctid = t.actstagcltid where x.ivtgcltid=t.actstagcltid and exists (select gctid from tblgrpclient p where (gcttype!='A' or gcttype='A') and gctvalid!='0'and p.gctid=t.actstagcltid)";//and not exists (select gctid from tblgrpclient p where gcttype='A' and gctvalid='0'and p.gctid=t.actstagcltid
				
			    if(null!=bi.getActstayear()&&!"".equals(bi.getActstayear()))
				{
					hql+=" and t.actstayear='" + bi.getActstayear()+" '";
				}
				if(null!=bi.getActstamonth()&&!"".equals(bi.getActstamonth()))
				{
					hql+=" and t.actstamonth='"+bi.getActstamonth()+"'";
				}
				if(null!=bi.getGctarea()&&!"".equals(bi.getGctarea()))
				{
					hql+=" and g.gctarea='"+bi.getGctarea()+"'";
				}
				if(null!=bi.getActsta()&&!"".equals(bi.getActsta()))
				{
					hql+=" and t.actsta='"+bi.getActsta()+"'";
				}
				if(null!=bi.getActstagcltid()&&!"".equals(bi.getActstagcltid()))
				{
					hql+=" and t.actstagcltid='"+bi.getActstagcltid()+"'";
				}
				if(null!=bi.getGcttype()&&!"".equals(bi.getGcttype()))
				{
					hql+=" and g.gcttype='"+bi.getGcttype()+"'";
				}
				hql+=" order by actstagcltid";
				af = super.init(req, forward, hql);
			}
			

		} catch(AppException ae){
			this.saveErrors(req, ae);
		}catch(Exception e){
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 进入月结界面
	 */
	public ActionForward yuejie(ActionMapping actionMapping,
	ActionForm actionForm, HttpServletRequest req,
	HttpServletResponse res) throws Exception {
	String mark = (String) req.getParameter("mark");
	Business bi = new Business();
	BusinessForm bf = (BusinessForm) actionForm;
	ActionForward af = new ActionForward();
	String forward = "/business/yuejie.jsp";
	Connection conn = null;
	CallableStatement proc = null;
	// 调用加盟店的月结存储过程
	CallableStatement proc1 = null;
	try {
	LoginDTO dto1 = (LoginDTO) req.getSession()
	.getAttribute("LoginDTO");
	conn = DBUtil.getConnection();
	if (bf.getIvtyear() > 2013
	|| (bf.getIvtyear() == 2013 && bf.getIvtmonth() > 4)) {
	proc = conn
	.prepareCall("{ call PRC_MONTHLYSTATEMENT10(?, ?, ?, ?) }");// 调用月结存储过程，从这儿可以分成两部分，第一部分是直属店的，第二部分是加盟店的
	proc1 = conn.prepareCall("{ call PRC_JMDYJ(?, ?, ?, ?) }");// 调用月结存储过程，从这儿可以分成两部分，第一部分是直属店的，第二部分是加盟店的
	} else {
	proc = conn
	.prepareCall("{ call PRC_MONTHLYSTATEMENT(?, ?, ?, ?) }");// 调用月结存储过程
	}
	proc.setInt(1, bf.getIvtyear());
	proc.setInt(2, bf.getIvtmonth());
	proc.setString(3, dto1.getBsc011());
	proc.registerOutParameter(4, Types.INTEGER);
	proc1.setInt(1, bf.getIvtyear());
	proc1.setInt(2, bf.getIvtmonth());
	proc1.setString(3, dto1.getBsc011());
	proc1.registerOutParameter(4, Types.INTEGER);
	// }
	long startTime = System.currentTimeMillis(); 

	proc.execute();
	int retValue = proc.getInt(4);
	proc1.execute();
	int retValue1 = proc1.getInt(4);
	long endTime = System.currentTimeMillis(); 
	System.out.println("time: " + (endTime - startTime) / 1000 + " s"); 
	
	bi.setIvtyear(bf.getIvtyear());
	bi.setIvtmonth(bf.getIvtmonth());
	BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
	RequestEnvelop requestEnvelop = new RequestEnvelop();
	EventResponse returnValue = new EventResponse();
	HashMap<String, Object> mapRequest = new HashMap<String, Object>();
	mapRequest.put("beo", bi);
	requestEnvelop.setBody(mapRequest);
	// 调用对应的Facade业务处理方法
	ResponseEnvelop resEnv = null;
	// if(bf.getIvtyear()>=2013&&bf.getIvtmonth()>1)
	// {
	resEnv = facade.account(requestEnvelop);
	// }

	// 处理返回结果
	returnValue = processRevt(resEnv);
	if (returnValue.isSucessFlag()) {
	System.out.println("月度结账表保存成功！");
	}

	if (retValue == 0&&retValue1==0) {
	String msg = "请先完成上月的结算！";
	super.saveSuccessfulMsg(req, msg);
	// return actionMapping.findForward("backspace");
	} else if (retValue == 1&&retValue1==1) {
	String time="月度结算成功，耗时："+String.valueOf((endTime - startTime) / 1000)+"s";
	super.saveSuccessfulMsg(req, time);
	}

	} catch (Exception e) {
	this.saveErrors(req, e);
	return actionMapping.findForward("backspace");
	} finally {
	proc.close();
	DBUtil.closeConnection(conn);
	}
	return actionMapping.findForward("ydjs");
	}
	public ActionForward batchSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String[] chk = req.getParameterValues("chk");
		Collection<Business> collection = null;
		Collection<Business> checked = new LinkedList();
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),Business.class);
			for (int j = 0; j < chk.length; j++) {
				int i = 1;
				for (Business dto : collection) {
					if (i == Integer.parseInt(chk[j])) {
						checked.add(dto);
					}
					i++;
				}
			}
			LoginDTO dto1 = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			BusinessFacade businessFacade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("userId", dto1.getBsc011());
			mapRequest.put("collection", checked);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = businessFacade.modify(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "批量保存信息成功!");
				//return mapping.findForward("JMxiaozhang");
				return go2Page(req, mapping, "business");
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
	 * 
	 * 应收款查询
	 */
	public ActionForward queryAccount(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Business bi = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/business/queryAccount.jsp";
		try {
			ClassHelper.copyProperties(bf, bi);

			String hql = "";
			hql = "select nvl(sum(i.ivtpamnt),'0') as temp01,i.ivtgcltid,g.gctnm from tblinventory i left OUTER JOIN tblgrpclient g ON g.gctid= i.ivtgcltid where i.ivtyear='"
					+ bf.getIvtyear()
					+ "' and i.ivtmonth='"
					+ bf.getIvtmonth()
					+ "'";
			if (!("".equals(bf.getIvtgcltid())) && bf.getIvtgcltid() != null)
				hql += " and i.ivtgcltid='" + bf.getIvtgcltid() + "'";
			hql += " GROUP BY i.ivtgcltid,g.gctnm";
			af = init(req, forward, hql, "1");
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "这个月还没进行结算！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	public ActionForward huikuang(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		BusinessForm bf = (BusinessForm) actionForm;
		Connection conn1 = null;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\company_detail_2.jasper"));

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型

			String gcltid = bf.getIvtgcltid();
			parameters.put("year", bf.getIvtyear());
			parameters.put("gcltid", gcltid);
			parameters.put("smonth", bf.getIvtmonth());// 包括该月
			parameters.put("emonth", bf.getIvtpqnt());

			String sql1 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gctarea=b.aaa102 where b.aaa100='GCTAREA' and g.gctid='"
					+ gcltid + "'";
			String sql2 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gctprovince=b.aaa102 where b.aaa100='GCTPROVINCE' and g.gctid='"
					+ gcltid + "'";
			String sql3 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gcttype=b.aaa102 where b.aaa100='GCTTYPE' and g.gctid='"
					+ gcltid + "'";
			boolean isSuccess = false;
			// 连接到数据库

			conn = DBUtil.getConnection();

			Statement pstmt1 = conn.createStatement(); // Statment
			ResultSet rs1 = pstmt1.executeQuery(sql1);
			String area = null;
			String province = null;
			String type = null;
			if (rs1.next()) {
				isSuccess = true;
				area = rs1.getString("aaa103");
			}
			rs1.close();
			pstmt1.close();

			Statement pstmt2 = conn.createStatement();
			ResultSet rs2 = pstmt2.executeQuery(sql2);
			if (rs2.next()) {
				isSuccess = true;
				province = rs2.getString("aaa103");
			}
			rs2.close();
			pstmt2.close();

			Statement pstmt3 = conn.createStatement();
			ResultSet rs3 = pstmt3.executeQuery(sql3);
			if (rs3.next()) {
				isSuccess = true;
				type = rs3.getString("aaa103");
			}
			rs3.close();
			pstmt3.close();

			DBUtil.closeConnection(conn);

			parameters.put("area", area);
			parameters.put("province", province);
			parameters.put("type", type);

			System.out.println("area" + area);
			System.out.println("province" + province);
			System.out.println("type" + type);

			// 连接到数据库
			conn1 = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = gcltid + "_huikuang_report";

			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(),
					parameters, conn1);

			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					"客户信息表.xls");
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn1);
		}
		return null;
	}

	/**
	 * 
	 * 品牌销售详情
	 * 
	 */

	public ActionForward saleDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		// String ivtgcltid = req.getParameter("ivtgcltid");
		// Integer ivtyear = Integer.valueOf(req.getParameter("ivtyear"));
		// Integer ivtmonth = Integer.valueOf(req.getParameter("ivtmonth"));

		String temp01 = new String(req.getParameter("temp01"));
		// .getBytes("ISO8859-1"),"GBK"
		ActionForward af = new ActionForward();

		BusinessForm bf = (BusinessForm) form;

		String ivtgcltid = (String) req.getSession().getAttribute("ivtgcltid");
		Integer ivtyear = (Integer) req.getSession().getAttribute("ivtyear");
		Integer ivtmonth = (Integer) req.getSession().getAttribute("ivtmonth");

		bf.setIvtgcltid(ivtgcltid);
		bf.setIvtyear(ivtyear);
		bf.setIvtmonth(ivtmonth);
		bf.setIvtnote(temp01);
		String forward = "/business/saleDetail.jsp"; // 查看销售明细

		try {
			// 处理页面数据

			String queryhql = "select n.*, p.pdtnm, i.ictnm  from TBLINVENTORY  n LEFT OUTER JOIN tblproduct p ON n.ivtpdtid=p.pdtid LEFT OUTER JOIN tblindclient i ON i.ictid= n.ivtcltid where "
					+ " p.pdttype='"
					+ temp01
					+ "'"
					+ " and n.ivtyear="
					+ ivtyear; // 分页组件查询
			if (!"".equals(ivtgcltid) && ivtgcltid != null)
				queryhql += " and n.ivtgcltid='" + ivtgcltid + "'";
			if (ivtmonth != null)
				queryhql += " and n.ivtmonth=" + ivtmonth;

			af = init(req, forward, queryhql);

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(req, msg);

			}

		} catch (AppException e) {
			saveErrors(req, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	// 区域销售详情
	public ActionForward qyxsDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		String temp01 = new String(req.getParameter("temp01"));

		ActionForward af = new ActionForward();

		BusinessForm bf = (BusinessForm) form;

		Integer ivtyear = (Integer) req.getSession().getAttribute("ivtyear");
		Integer ivtmonth = (Integer) req.getSession().getAttribute("ivtmonth");

		bf.setIvtyear(ivtyear);
		bf.setIvtmonth(ivtmonth);
		bf.setIvtnote(temp01);

		String forward = "/business/qyxsDetail.jsp"; // 查看销售明细

		try {
			// 处理页面数据

			String queryhql = "select n.*, p.pdtnm, i.ictnm  from TBLINVENTORY  n LEFT OUTER JOIN tblproduct p ON n.ivtpdtid=p.pdtid LEFT OUTER JOIN tblindclient i ON i.ictid= n.ivtcltid left outer join tblgrpclient g on g.gctid=n.ivtgcltid where "
					+ " g.gctarea='"
					+ temp01
					+ "'"
					+ " and n.ivtyear="
					+ ivtyear; // 分页组件查询

			if (ivtmonth != null)
				queryhql += " and n.ivtmonth=" + ivtmonth;

			af = init(req, forward, queryhql);

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(req, msg);
			}

		} catch (AppException e) {
			saveErrors(req, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 生成月报表
	 */
	/*
	 * 直属店汇总表打印
	 */
	public ActionForward salesum(ActionMapping actionMapping,ActionForm actionForm,HttpServletRequest req,HttpServletResponse res)throws Exception{
		BusinessForm bf = (BusinessForm) actionForm;
		Connection conn = null;
		try{
			File reportFile = null;
			Map<String, Object> parameters = new HashMap<String, Object>();
			String gctarea = bf.getGctarea();
			int ivtyear = bf.getIvtyear();
			int ivtyearEnd = bf.getIvtyearEnd();
			int ivtmonths = bf.getIvtmonths();
			int ivtmontht = bf.getIvtmontht();
			String ivtgcltid = bf.getIvtgcltid();
			if(gctarea.equals("")){
				gctarea = "%%";
			}
			if(ivtgcltid.equals("")){
				ivtgcltid = "%%";
			}
			
			parameters.put("gctarea",gctarea);
			parameters.put("ivtyear",ivtyear);
			parameters.put("ivtyearEnd",ivtyearEnd);
			parameters.put("ivtmonths",ivtmonths);
			parameters.put("ivtmontht",ivtmontht);
			parameters.put("ivtgcltid",ivtgcltid);
			reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\salesum.jasper"));
			System.out.println(reportFile.getPath());
			
			conn = DBUtil.getConnection();
			
			ServletOutputStream ouputStream = res.getOutputStream();
            String reportclass = "salesum";
			res.setContentType("application/vnd.ms-excel"); 
			res.setHeader("content-disposition", 
			"attachment;filename=" + reportclass + ".xls"); 

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(), parameters, conn); 

			JRXlsExporter exporter = new JRXlsExporter(); 
         
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt); 
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream); 
//			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,   
//            "客户信息表.xls");   
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, 
			Boolean.FALSE); 
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, 
			Boolean.FALSE); 
			//添加的属性控制
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            //保留GridLine
            //缩小字体填充单元格
            exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,true);

			
            
			exporter.exportReport(); 

			ouputStream.flush();
			ouputStream.close();	
		}catch (Exception e) {
//			res.setContentType("text/html;charset=GB2312");   
//          PrintWriter   out   =   res.getWriter();   
          e.printStackTrace();   
		} finally {
			
			DBUtil.closeConnection(conn);
		}
		return null;
	}
	public ActionForward report(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		String gctcom = req.getParameter("type");
		BusinessForm bf = (BusinessForm) actionForm;
		Connection conn = null;
		Connection con = null;
		Statement pstmt = null;
		Statement pstmt2 = null;
		ResultSet rs = null;
		try {

			LoginDTO dto = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			String gctid = bf.getIvtgcltid();
			Integer year = bf.getIvtyear();
			Integer month = bf.getIvtmonth();
			//String gctcom = bf.getGctcom();

			Double dmad = new Double(0);
			Double dmsales = new Double(0);
			Double dmpamnt = new Double(0);
			Double dmback = new Double(0);
			Double dmothers = new Double(0);
			Double dmnsales = new Double(0);
			String mnote = null;
			Double lmarrears = new Double(0);

			// 输入金额
			String s = "select count(*) from tblmonth m where m.mgctid='"
					+ gctid + "' and m.myear='" + year + "' and m.mmonth='"
					+ month + "'";
			String sql3 = "select actstanote from tblactsta where actstayear='"+year+"' and actstamonth='"+month+"' and actstagcltid= '"+gctid+"'";
			con = DBUtil.getConnection();
			pstmt = con.createStatement();
			if (DBUtil.getRowCount(con, s) > 0) {
				String sql1 = "select m.* from tblmonth m where m.mgctid='"
						+ gctid + "' and m.myear='" + year + "' and m.mmonth='"
						+ month + "'";
				rs = pstmt.executeQuery(sql1);
				while (rs.next()) {
					dmad = rs.getDouble("mad");
					dmback = rs.getDouble("mback");
					dmsales = rs.getDouble("msales");
					dmpamnt = rs.getDouble("mpamnt");
					dmothers = rs.getDouble("mothers");
					dmnsales = rs.getDouble("mnsales");
				//	mnote = rs.getString("mnote");
				}
			}
			pstmt2 = con.createStatement();
			ResultSet rs2 = pstmt2.executeQuery(sql3);
			while(rs2.next()){
				mnote = rs2.getString("actstanote");
			}
			// 上月欠款
			Integer lmonth = null;
			Integer lyear = null;

			if (month == 1) {
				lyear = year - 1;
				lmonth = 12;
			} else {
				lmonth = month - 1;
				lyear = year;
			}
			String sql2 = "select m.* from tblmonth m where m.mgctid='" + gctid
					+ "' and m.myear='" + lyear + "' and m.mmonth='" + lmonth
					+ "'";
			rs = pstmt.executeQuery(sql2);
			while (rs.next()) {
				lmarrears = rs.getDouble("marrears");
			}
			if (null == lmarrears || "".equals(lmarrears)) {
				lmarrears = new Double(0);
			}
			rs.close();
			pstmt.close();
			DBUtil.closeConnection(con);

			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;
			if ("huier".equals(gctcom)) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\report_1.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\report_2.jasper"));
			}

			/*if ("0".equals(gctcom)) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\report_1.jasper"));
			} else {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\report_2.jasper"));
			}*/
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("pid", dto.getBsc011());
			parameters.put("code", gctid);
			parameters.put("year", year);
			parameters.put("month", month);
			parameters.put("lmarrears", lmarrears);
			parameters.put("mad", dmad);
			parameters.put("mback", dmback);
			parameters.put("msales", dmsales);
			parameters.put("mpamnt", dmpamnt);
			parameters.put("mnsales", dmnsales);
			parameters.put("mothers", dmothers);
			parameters.put("mnote", mnote);

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			
			//2012/2/5修改
			//res.setHeader("content-disposition", "attachment;filename=yuebaobiao.pdf");	//PDF的名字不能为中文，否则报错
			
			res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
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
	

	/**
	 * 区域销售统计
	 */
	public ActionForward queryqyxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		BusinessForm bf = (BusinessForm) form;
		Business bi = new Business();
		try {
			ClassHelper.copyProperties(bf, bi);
			String forward = "/business/queryqyxs.jsp";
			String queryhql = "";// 分页组件查询
			String chartsql = "";// 图表查询

			Integer month = bf.getIvtmonth();

			req.setAttribute("ivtyear", bf.getIvtyear());
			if (month != 0) {
				queryhql = "select nvl(g.gctarea,'其他') as temp01,nvl(sum(i.ivtpamnt),0) as temp02,nvl(sum(i.ivtpqnt),0) as temp03,round(100*RATIO_TO_REPORT(nvl(sum(i.ivtpamnt),0)) over(),2) as percent,row_number() over(order by nvl(sum(i.ivtpamnt),0) desc) rank from tblinventory i left outer join tblgrpclient g on g.gctid=i.ivtgcltid where i.ivtyear="
						+ bf.getIvtyear()
						+ " and i.ivtmonth="
						+ bf.getIvtmonth() + " group by g.gctarea";

				req.setAttribute("ivtmonth", month);

				chartsql = "select nvl(g.gctarea,'其他') as temp01,nvl(sum(i.ivtpamnt),0) as temp02 from tblinventory i left outer join tblgrpclient g on g.gctid=i.ivtgcltid where i.ivtyear="
						+ bf.getIvtyear()
						+ " and i.ivtmonth="
						+ bf.getIvtmonth()
						+ " group by g.gctarea order by temp02 desc";
			} else {
				bf.setIvtmonth(null);
				queryhql = "select nvl(g.gctarea,'其他') as temp01,nvl(sum(i.ivtpamnt),0) as temp02,nvl(sum(i.ivtpqnt),0) as temp03,round(100*RATIO_TO_REPORT(nvl(sum(i.ivtpamnt),0)) over(),2) as percent,row_number() over(order by nvl(sum(i.ivtpamnt),0) desc) rank from tblinventory i left outer join tblgrpclient g on g.gctid=i.ivtgcltid where i.ivtyear="
						+ bf.getIvtyear() + " group by g.gctarea";

				chartsql = "select nvl(g.gctarea,'其他') as temp01,nvl(sum(i.ivtpamnt),0) as temp02 from tblinventory i left outer join tblgrpclient g on g.gctid=i.ivtgcltid where i.ivtyear="
						+ bf.getIvtyear()
						+ " group by g.gctarea order by temp02 desc";
			}

			af = init(req, forward, queryhql, "1");
			req.getSession().setAttribute("chartname", "区域销售统计表");
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(req, msg);
				req.getSession().setAttribute("chartsql", null);
			} else {
				req.getSession().setAttribute("chartsql", chartsql);
			}
		} catch (AppException e) {
			saveErrors(req, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 品牌 产品销售统计
	 */
	public ActionForward querycpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		BusinessForm bf = (BusinessForm) form;
		Business bi = new Business();
		try {
			ClassHelper.copyProperties(bf, bi);
			String forward = "/business/queryppxs.jsp";
			String queryhql = "";// 分页组件查询
			String chartsql = "";// 图表查询

			Integer month = bf.getIvtmonth(); // 月份
			String gctid = bf.getIvtgcltid(); // 团体客户代码
			queryhql = "select p.pdttype as temp01,nvl(sum(i.ivtpamnt),0) as temp02,nvl(sum(i.ivtpqnt),0) as temp03,round(100*RATIO_TO_REPORT(nvl(sum(i.ivtpamnt),0)) over(),2) as percent,row_number() over(order by nvl(sum(i.ivtpamnt),0) desc) rank from tblinventory i left outer join tblproduct p on p.pdtid=i.ivtpdtid where i.ivtyear="
					+ bf.getIvtyear();
			// 增加月份查询条件
			req.setAttribute("ivtyear", bf.getIvtyear());
			if (month != 0) {
				queryhql += " and i.ivtmonth=";
				queryhql += bf.getIvtmonth();
				req.setAttribute("ivtmonth", month);
			} else {
				bf.setIvtmonth(null);
			}
			// 增加团体客户查询条件
			if (gctid != null && gctid != "") {
				queryhql += " and i.ivtgcltid='";
				queryhql += bf.getIvtgcltid();
				queryhql += "'";
				req.setAttribute("gctid", gctid);
			}
			queryhql += " and p.pdttype is not null group by p.pdttype";

			chartsql = "select p.pdttype as temp01,nvl(sum(i.ivtpamnt),0) as temp02 from tblinventory i left outer join tblproduct p on p.pdtid=i.ivtpdtid where i.ivtyear="
					+ bf.getIvtyear();
			// 增加月份查询条件
			if (month != 0) {
				chartsql += " and i.ivtmonth=";
				chartsql += bf.getIvtmonth();
			}
			// 增加团体客户查询条件
			if (gctid != null && gctid != "") {
				chartsql += " and i.ivtgcltid='";
				chartsql += bf.getIvtgcltid();
				chartsql += "'";
			}
			chartsql += " and p.pdttype is not null group by p.pdttype order by temp02 desc";

			af = init(req, forward, queryhql, "1");
			req.getSession().setAttribute("chartname", "产品销售统计表");
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(req, msg);
				req.getSession().setAttribute("chartsql", null);
			} else {
				req.getSession().setAttribute("chartsql", chartsql);
			}
		} catch (AppException e) {
			saveErrors(req, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 
	 * 销售信息查询
	 */
	public ActionForward salequery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String forward = "/business/salequery.jsp";
		ActionForward af = new ActionForward();
		Business bs = new Business();
		BusinessForm bsForm = (BusinessForm) actionForm;
		Integer yearEnd = bsForm.getIvtyearEnd();
		Integer months = bsForm.getIvtmonths();
		Integer montht = bsForm.getIvtmontht();
		String area = bsForm.getGctarea();
		String province = bsForm.getGctprovince();
		String gtype = bsForm.getGcttype();
		String ptype = bsForm.getPdttype();//产品品牌
		String pnm = bsForm.getPdtnm();
		String rtype = bsForm.getRepfee();
		String ivttype = bsForm.getIvttype();//商品类型
		
		try {
			ClassHelper.copyProperties(bsForm, bs);
			Integer year1 = bsForm.getIvtyear();
			String ivtgcltid = bsForm.getIvtgcltid();
			String pdtcls = bsForm.getPdtcls();
			String start ="to_date('" +year1 + "-" + months+"','YYYY-mm')";
			String end = "to_date('"+yearEnd + "-" + montht+"','YYYY-mm')";
			String sql1 = "select * from (select distinct i.ivtgcltid,i.ivtyear, g.gctnm,g.gcttype,g.gctprovince,g.gctarea,p.pdtprc,p.pdtnm,p.pdttype ";
			String sql2 = "from tblinventory i left outer join tblproduct p on i.ivtpdtid= p.pdtid left outer join tblgrpclient g on g.gctid=i.ivtgcltid where to_date(to_char(ivtyear,'9999')||to_char(ivtmonth,'99'),'YYYY-mm' )>="
					+ start
					+ "and to_date(to_char(ivtyear,'9999')||to_char(ivtmonth,'99'),'YYYY-mm' )<="
					+ end;
			// 空变量是一个实例为""
			if (gtype != null && !"".equals(gtype)) {
				sql2 += " and g.gcttype='" + gtype + "'";
			}
			
			//缩小团体客户范围
			if (province != null && !"".equals(province)) {
				sql1 += ",sum(i.ivtpamnt)over(partition by g.gctprovince) as temp04";
				sql2 += " and g.gctprovince='" + province + "'";
			}//else{
				if (area != null && !"".equals(area)) {
					sql1 += ",sum(i.ivtpamnt)over(partition by g.gctarea) as temp06";
					sql2 += " and g.gctarea='" + area + "'";
				}
			//}
			//团体客户
			if (ivtgcltid != null && !"".equals(ivtgcltid)) {
				sql2 += " and g.gctid='" + ivtgcltid + "'";
			}
			//耳机类别
			if(pdtcls !=null && !"".equals(pdtcls)){
				sql2 += "and p.pdtcls='" + pdtcls + "'";
			}
			//产品信息
			if (pnm != null && !"".equals(pnm)) {
				sql1 += ",sum(i.ivtpamnt)over(partition by g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid, p.pdtnm order by p.pdtnm ) as temp10 ";
				sql2 += " and p.pdtnm like '%" + pnm + "%'";
			}//else{
				if (ptype != null && !"".equals(ptype)) { 
					sql1 += ",sum(i.ivtpamnt)over(partition by  p.pdttype,g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid) as temp08";
					sql2 += " and p.pdttype='" + ptype + "'";
				}
		    
			//}
			sql1 += ",sum(i.ivtpamnt)over(partition by i.ivtpdtid,g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid order by i.ivtgcltid) as temp01,sum(i.ivtpqnt)over(partition by p.pdtnm,i.ivtgcltid order by p.pdtnm,i.ivtgcltid) as temp02 ";

			//订单类型，为了查询维修费
			/*if (rtype != null && !"".equals(rtype)) {
				sql1 += ",sum(i.ivtpamnt)over(partition by g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid,i.ivttype order by i.ivtgcltid) as temp12 ";
				sql2 += " and i.ivttype='repair' ";
			} else {
				sql1 += ",sum(i.ivtpamnt)over(partition by i.ivtpdtid,g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid order by i.ivtgcltid) as temp01,sum(i.ivtpqnt)over(partition by p.pdtnm,i.ivtgcltid order by p.pdtnm,i.ivtgcltid) as temp02 ";
				//sql2 += " and i.ivttype<>'repair'";
			}*/
			
			//商品类型
			if(ivttype != null && !"".equals(ivttype)){
				if("make".equals(ivttype)){
					sql1 += ",sum(i.ivtpamnt)over(partition by i.ivtpdtid,g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid order by i.ivtgcltid) as temp14,sum(i.ivtpqnt)over(partition by p.pdtnm,i.ivtgcltid order by p.pdtnm,i.ivtgcltid) as temp13 ";
					sql2 += " and i.ivttype='make' and p.pdtnm in (select pdtnm from tblproduct ) ) where not (temp01 = 0 and temp02 = 0)";
				}
				else 
				{
					if("normal".equals(ivttype))
					{
						sql1 += ",sum(i.ivtpamnt)over(partition by i.ivtpdtid,g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid order by i.ivtgcltid) as temp16,sum(i.ivtpqnt)over(partition by p.pdtnm,i.ivtgcltid order by p.pdtnm,i.ivtgcltid) as temp15 ";
						//sql2 += " and i.ivttype='normal' and p.pdtnm in (select pdtnm from tblproduct where pdtnm like'%盒式机%' or pdtnm like '%耳背机%' and pdtcls not in('OTH')) ) where temp01 > 0 or temp02 >0" ;
						sql2 += " and i.ivttype='normal' and p.pdtcls in ('BTE','BOX')) where not (temp01 = 0 and temp02 = 0)" ;
					}
				    else
				    { 
				    	if("repair".equals(ivttype))
				    	{
							sql1 += ",sum(i.ivtpamnt)over(partition by g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid,i.ivttype order by i.ivtgcltid) as temp12 ";
							sql2 += " and i.ivttype='repair' and p.pdtnm in (select pdtnm from tblproduct ) ) where not (temp01 = 0 and temp02 = 0) ";
				    	}
				    	else 
				    	{
				    		if (province == null || "".equals(province)) 
				    		{
				    			sql1 += ",sum(i.ivtpamnt)over(partition by g.gctprovince order by g.gctprovince ) as temp04 ";
				    		}
							sql2 += "and i.ivttype in('make','normal') and p.pdtnm in (select pdtnm from tblproduct ) ) where not (temp01 = 0 and temp02 = 0)";
						}
				    }
			     }
			}
			else{
				if (province == null || "".equals(province)) 
	    		{
					sql1 += ",sum(i.ivtpamnt)over(partition by g.gctprovince order by g.gctprovince ) as temp04 ";
	    		}
				sql2 += "and p.pdtnm in (select pdtnm from tblproduct ) ) where not (temp01 = 0 and temp02 = 0)";
			}
			
			

			String sql = sql1 + sql2;
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的销售信息！";
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
	 * 
	 * 用户信息查询
	 */
	public ActionForward clientquery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String forward = "/business/queryclient.jsp";
		ActionForward af = new ActionForward();
		Business bs = new Business();
		BusinessForm bsForm = (BusinessForm) actionForm;

		Integer year = bsForm.getIvtyear();
		Integer months = bsForm.getIvtmonths();
		Integer montht = bsForm.getIvtmontht();
		
		String gctid = bsForm.getIvtgcltid();
		String gender = bsForm.getIctgender();
		Integer ages = bsForm.getAges();
		Integer aget = bsForm.getAget();
		try {
			ClassHelper.copyProperties(bsForm, bs);

			if(gender.equals("0")){
				gender = "女";
			}else if(gender.equals("1")){
				gender = "男";
			}else{
				gender = "无";
			}
			
			String sql1 = "select i.ivtyear,g.gctid,g.gctnm, count(distinct i.ivtcltid) as amount,(select count(distinct i.ivtcltid) from  tblinventory i where i.ivtyear='"
				+ year + "' and i.ivtmonth >='" 
				+ months + "' and ivtmonth <='"
				+ montht + "') as total, (case c.ictgender when '男' then '男' when '女' then '女' else '无' end) as gender" +
				" from tblgrpclient g left outer join tblinventory i on g.gctid=i.ivtgcltid " +
				"left outer join tblindclient c on c.ictid = i.ivtcltid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//如果团体客户有值
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and g.gctid='" + gctid + "'";
			}
			if(ages != 0 && !"".equals(ages)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12) >='" + ages + "' ";
			}
			if(aget != 0 && !"".equals(aget)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12)<='" + aget + "'";
			}
			if(!"无".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			sql2 += "group by  i.ivtyear,g.gctnm,g.gctid,c.ictgender order by g.gctid";
			String sql = sql1 + sql2;
			
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的用户信息！";
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
	 * 
	 * 价格统计信息查询
	 */
	public ActionForward pricequery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String forward = "/business/queryprice.jsp";
		ActionForward af = new ActionForward();
		Business bs = new Business();
		BusinessForm bsForm = (BusinessForm) actionForm;

		Integer year = bsForm.getIvtyear();
		Integer months = bsForm.getIvtmonths();
		Integer montht = bsForm.getIvtmontht();
		
		String gctid = bsForm.getIvtgcltid();
		String gender = bsForm.getIctgender();
		Double prices = bsForm.getPrices();
		Double pricet = bsForm.getPricet();
		try {
			ClassHelper.copyProperties(bsForm, bs);

			if(gender.equals("0")){
				gender = "女";
			}else if(gender.equals("1")){
				gender = "男";
			}else{
				gender = "无";
			}
			
			String sql1 = "select i.ivtyear,g.gctid,g.gctnm, count(distinct i.ivtcltid) as amount,(select count(distinct i.ivtcltid) from  tblinventory i where i.ivtyear='"
				+ year + "' and i.ivtmonth >='" 
				+ months + "' and ivtmonth <='"
				+ montht + "') as total, (case c.ictgender when '男' then '男' when '女' then '女' else '无' end) as gender" +
				" from tblgrpclient g left outer join tblinventory i on g.gctid=i.ivtgcltid " +
				"left outer join tblindclient c on c.ictid = i.ivtcltid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//如果团体客户有值
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and g.gctid='" + gctid + "'";
			}
			if(prices != 0 && !"".equals(prices)){
				sql2 += " and i.ivtpamnt >='" + prices + "' ";
			}
			if(pricet != 0 && !"".equals(pricet)){
				sql2 += " and i.ivtpamnt <='" + pricet + "'";
			}
			if(!"无".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			sql2 += "group by  i.ivtyear,g.gctnm,g.gctid,c.ictgender order by g.gctid";
			String sql = sql1 + sql2;
			
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的价格统计信息！";
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
	 * 
	 * 配机统计信息查询
	 */
	public ActionForward audioquery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String forward = "/business/queryaudio.jsp";
		ActionForward af = new ActionForward();
		Business bs = new Business();
		BusinessForm bsForm = (BusinessForm) actionForm;

		Integer year = bsForm.getIvtyear();
		Integer months = bsForm.getIvtmonths();
		Integer montht = bsForm.getIvtmontht();
		
		String gctid = bsForm.getIvtgcltid();
		String gender = bsForm.getIctgender();
		String earcase = bsForm.getEarcase();
		
		try {
			ClassHelper.copyProperties(bsForm, bs);

			if(gender.equals("0")){
				gender = "女";
			}else if(gender.equals("1")){
				gender = "男";
			}else{
				gender = "无";
			}
			
			String sql1 = "select i.ivtyear,g.gctid,g.gctnm, count(distinct i.ivtcltid) as amount,(select count(distinct i.ivtcltid) from  tblinventory i where i.ivtyear='"
				+ year + "' and i.ivtmonth >='" 
				+ months + "' and ivtmonth <='"
				+ montht + "') as total, (case c.ictgender when '男' then '男' when '女' then '女' else '无' end) as gender" +
				" from tblgrpclient g left outer join tblinventory i on g.gctid=i.ivtgcltid " +
				"left outer join tblindclient c on c.ictid = i.ivtcltid " +
				"left outer join tbldiagnose g on g.dgnctid=c.ictid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//如果团体客户有值
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and g.gctid='" + gctid + "'";
			}
			if(!"无".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			if("0".equals(earcase)){
				sql2 += " and dgnlid is not null ";
			}
			if("1".equals(earcase)){
				sql2 += " and dgnlid is not null and dgnrid is not null";
			}
			sql2 += " group by  i.ivtyear,g.gctnm,g.gctid,c.ictgender order by g.gctid";
			String sql = sql1 + sql2;
			
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的配机统计信息！";
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
	 * 
	 * 用户信息、价格、配机（单耳、双耳）查询
	 */
	public ActionForward compositequery(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String forward = "/client/single//querycomposite.jsp";
		ActionForward af = new ActionForward();
		Business bs = new Business();
		BusinessForm bsForm = (BusinessForm) actionForm;

		Integer year = bsForm.getIvtyear();
		Integer months = bsForm.getIvtmonths();
		Integer montht = bsForm.getIvtmontht();
		
		String gctid = bsForm.getIvtgcltid();
		String gender = bsForm.getIctgender();
		Integer ages = bsForm.getAges();
		Integer aget = bsForm.getAget();
		
		Double prices = bsForm.getPrices();
		Double pricet = bsForm.getPricet();
		
		String earcase = bsForm.getEarcase();
		
		try {
			ClassHelper.copyProperties(bsForm, bs);

			/*if(gender.equals("0")){
				gender = "女";
			}else if(gender.equals("1")){
				gender = "男";
			}else{
				gender = "无";
			}*/
			
			if(!gender.equals("0") && !gender.equals("1")){
				gender = "无";
			}
			
			String sql1 = "select i.ivtyear,p.gctid,p.gctnm, count(distinct i.ivtcltid) as amount,(select count(distinct i.ivtcltid) from  tblinventory i where i.ivtyear='"
				+ year + "' and i.ivtmonth >='" 
				+ months + "' and ivtmonth <='"
				+ montht + "') as total, (case c.ictgender when '1' then '男' when '0' then '女' else '无' end) as gender" +
				" from tblgrpclient p left outer join tblinventory i on p.gctid=i.ivtgcltid " +
				"left outer join tblindclient c on c.ictid = i.ivtcltid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//如果团体客户有值
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and p.gctid='" + gctid + "'";
			}
			
			//如果选择年龄条件
			if(ages != 0 && !"".equals(ages)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12) >='" + ages + "' ";
			}
			if(aget != 0 && !"".equals(aget)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12)<='" + aget + "'";
			}
			
			//如果选择价格条件
			if(prices != 0 && !"".equals(prices)){
				sql2 += " and i.ivtpamnt >='" + prices + "' ";
			}
			if(pricet != 0 && !"".equals(pricet)){
				sql2 += " and i.ivtpamnt <='" + pricet + "'";
			}
			
			//如果选择配件情况条件
			if("0".equals(earcase)){
				sql1 += " left outer join tbldiagnose g on g.dgnctid=c.ictid ";
				//sql2 += " and ((dgnlid is  null and dgnrid is not null) or (dgnlid is  not null and dgnrid is null))";
			    sql2+="and ((g.dgnlid is null and g.dgnrid is not null and (g.dgnctid not in (select n.dgnctid from tbldiagnose n where n.dgnid!=g.dgnid) or g.dgnlid= all(select n.dgnlid from tbldiagnose n where n.dgnctid=g.dgnctid))) or (dgnlid is  not null and dgnrid is null and(g.dgnctid not in (select n.dgnctid from tbldiagnose n where n.dgnid!=g.dgnid) or g.dgnrid= all(select n.dgnrid from tbldiagnose n where n.dgnctid=g.dgnctid))))";
			}
			if("1".equals(earcase)){
				sql1 += " left outer join tbldiagnose g on g.dgnctid=c.ictid ";
				//sql2 += " and dgnlid is not null and dgnrid is not null";
				sql2+=" and ((dgnlid is not null and dgnrid is not null) or((g.dgnlid is null and g.dgnrid is not null and 0<(select count(*) from tbldiagnose n where n.dgnctid=g.dgnctid and n.dgnlid is not null)) or (  dgnlid is not null and dgnrid is null and 0<(select count(*) from tbldiagnose n where n.dgnctid=g.dgnctid and n.dgnrid is not null))))";
			}
			
			
			
			if(!"无".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			
			
			sql2 += " group by  i.ivtyear,p.gctnm,p.gctid,c.ictgender order by p.gctid";
			String sql = sql1 + sql2;
			String sqlstart = "select * from (";
			String sqlend = ")x where x.amount>0";
			sql = sqlstart + sql + sqlend;
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的用户信息！";
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
	 * 
	 * 用户信息、价格、配机（单耳、双耳）查询明细
	 */
	public ActionForward detail(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String forward = "/client/single/detail.jsp";
		ActionForward af = new ActionForward();
		Business bs = new Business();
		BusinessForm bsForm = (BusinessForm) actionForm;

		Integer year = bsForm.getIvtyear();
		Integer months = bsForm.getIvtmonths();
		Integer montht = bsForm.getIvtmontht();
		
		String gctid = bsForm.getGctid();
		String gender = bsForm.getIctgender();
		Integer ages = bsForm.getAges();
		Integer aget = bsForm.getAget();
		
		Double prices = bsForm.getPrices();
		Double pricet = bsForm.getPricet();
		
		String earcase = bsForm.getEarcase();
		
		try {
			ClassHelper.copyProperties(bsForm, bs);

			
			
			if(!gender.equals("0") && !gender.equals("1")){
				gender = "无";
			}
			
			String sql1 = "select distinct c.*,i.ivtyear,p.gctnm as ictgctnm" +
				" from tblindclient c left outer join tblinventory i on i.ivtcltid=c.ictid " +
				"left outer join tblgrpclient p on p.gctid = i.ivtgcltid ";
				//"left outer join tblindclient c on c.ictid = i.ivtcltid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//如果团体客户有值
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and p.gctid='" + gctid + "'";
			}
			
			//如果选择年龄条件
			if(ages != 0 && !"".equals(ages)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12) >='" + ages + "' ";
			}
			if(aget != 0 && !"".equals(aget)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12)<='" + aget + "'";
			}
			
			//如果选择价格条件
			if(prices != 0 && !"".equals(prices)){
				sql2 += " and i.ivtpamnt >='" + prices + "' ";
			}
			if(pricet != 0 && !"".equals(pricet)){
				sql2 += " and i.ivtpamnt <='" + pricet + "'";
			}
			
			//如果选择配件情况条件
			if("0".equals(earcase)){
				sql1 += " left outer join tbldiagnose g on g.dgnctid=c.ictid ";
				//sql2 += " and ((dgnlid is  null and dgnrid is not null) or (dgnlid is  not null and dgnrid is null)) ";
			    sql2+="and ((g.dgnlid is null and g.dgnrid is not null and (g.dgnctid not in (select n.dgnctid from tbldiagnose n where n.dgnid!=g.dgnid) or g.dgnlid= all(select n.dgnlid from tbldiagnose n where n.dgnctid=g.dgnctid))) or (dgnlid is  not null and dgnrid is null and(g.dgnctid not in (select n.dgnctid from tbldiagnose n where n.dgnid!=g.dgnid) or g.dgnrid= all(select n.dgnrid from tbldiagnose n where n.dgnctid=g.dgnctid))))";
			}
			if("1".equals(earcase)){
				sql1 += " left outer join tbldiagnose g on g.dgnctid=c.ictid ";
				//sql2 += " and dgnlid is not null and dgnrid is not null";
				sql2+=" and ((dgnlid is not null and dgnrid is not null) or((g.dgnlid is null and g.dgnrid is not null and 0<(select count(*) from tbldiagnose n where n.dgnctid=g.dgnctid and n.dgnlid is not null)) or (  dgnlid is not null and dgnrid is null and 0<(select count(*) from tbldiagnose n where n.dgnctid=g.dgnctid and n.dgnrid is not null))))";
			}
			
			
			
			if(!"无".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			
			
			//sql2 += " group by  i.ivtyear,g.gctnm,g.gctid,c.ictgender order by g.gctid";
			String sql = sql1 + sql2;
			
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的用户信息！";
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
	 * 利润表 数量5位 单价
	 */
	public ActionForward profit(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		BusinessForm bf = (BusinessForm) actionForm;
		Integer year = bf.getIvtyear();
		Integer month = bf.getIvtmonth();
		String ivtgcltid = bf.getIvtgcltid();
		Map<String, Object> parameters = new HashMap<String, Object>();
		try {
			ReportFactory pf = new ReportFactory();
			List<Report> data = pf.setCollection(ivtgcltid, year, month);
			// for(int i = 0;i < data.size();i ++){
			// Report report = data.get(i);;
			// System.out.println("pname"+report.getPname()+"  number"+report.getNumber()+"  price"+report.getPrice()+"  sales"+report.getSales()
			// +"  discount"+report.getDiscount()+"  fname"+report.getFname()+" feeout"+report.getOutfee());
			// }
			JRDataSource dataSource = new JRBeanCollectionDataSource(data);

			Connection con = DBUtil.getConnection();

			Statement pstmt = con.createStatement(); // Statment
			ResultSet rs = pstmt
					.executeQuery("select gctnm from tblgrpclient where gctid='"
							+ ivtgcltid + "'");
			String gctnm = "";
			if (rs.next()) {
				gctnm = rs.getString("gctnm");
			}
			rs.close();
			pstmt.close();
			DBUtil.closeConnection(con);

			parameters.put("gctnm", gctnm);
			parameters.put("year", year);
			parameters.put("month", month);

			// 报表编译之后生成的.jasper 文件的存放位置 PDF
//			File reportFile = new File(req.getSession().getServletContext()
//					.getRealPath("\\WEB-INF\\report\\profit_bean.jasper"));
//
//			// 在控制台显示一下报表文件的物理路径
//			System.out.println(reportFile.getPath());
//			byte[] bytes = JasperRunManager.runReportToPdf(
//					reportFile.getPath(), parameters, dataSource);
//
//			res.setContentType("application/pdf");
//			res.setContentLength(bytes.length);
//			ServletOutputStream ouputStream = res.getOutputStream();
//			ouputStream.write(bytes, 0, bytes.length);
//			ouputStream.flush();
//			ouputStream.close();
			
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\profit_bean.jasper"));
			ServletOutputStream ouputStream = res.getOutputStream();
            String reportclass = "profit";
			res.setContentType("application/vnd.ms-excel"); 
			res.setHeader("content-disposition", 
			"attachment;filename=" + reportclass + ".xls"); 

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(), parameters, dataSource); 

			JRXlsExporter exporter = new JRXlsExporter(); 
         
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt); 
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream); 
  
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, 
			Boolean.FALSE); 
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, 
			Boolean.FALSE); 
			//添加的属性控制
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            //保留GridLine
            //缩小字体填充单元格
            exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,true);

			
            
			exporter.exportReport(); 

			ouputStream.flush();
			ouputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加成本价
	 */
	public ActionForward yuedisquery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Business bi = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/business/editdis.jsp";
		try {
			ClassHelper.copyProperties(bf, bi);
		
			/*String hql = "select distinct nvl(sum(i.ivtpqnt)over(partition by p.pdtid order by i.ivttype, p.pdtid),0) as temp01,nvl(sum(i.ivtpamnt)over(partition by p.pdtid order by i.ivttype, p.pdtid),0) as temp02,p.pdtid,p.pdtnm,d.disprice as ivtpamnt, decode(sign(d.discount),0,d.discount,d.discount) as discount,g.gctnm,i.ivtpdtid,i.ivtgcltid,p.pdtprc,i.ivttype from tblinventory i left outer join tblproduct p on p.pdtid= i.ivtpdtid left outer join tblgrpclient g on g.gctid=i.ivtgcltid left outer join tbldiscount d on (d.dispdtid=i.ivtpdtid and d.disgctid=i.ivtgcltid)  where i.ivtpqnt>0 and i.ivttype<>'repair' and i.ivtgcltid='"+bf.getIvtgcltid()
			+"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"' ";*/
			//有加急费的查询语句 String hql = "SELECT DISTINCT NVL(SUM(i.ivtpqnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp01, NVL(SUM(i.ivtpamnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp02, p.pdtid, p.pdtnm, d.disprice AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, p.pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivtpqnt>0 AND i.ivttype <>'repair' and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"' union all SELECT DISTINCT 1 AS temp01, nvl(i.ivtpamnt,0) AS temp02, p.pdtid, p.pdtnm||' '||i.ivtnote as pdtnm, d.disprice AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, i.ivtfee as pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivttype <>'repair' and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"' AND ivtnote IN ('续保费','加急费') union all SELECT DISTINCT 1 AS temp01, i.ivtpamnt AS temp02, p.pdtid, p.pdtnm||' 维修', i.ivtpamnt AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, i.ivtfee as pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE  i.ivttype ='repair' and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"'";
			//String hql = "SELECT DISTINCT NVL(SUM(i.ivtpqnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp01, NVL(SUM(i.ivtpamnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp02, p.pdtid, p.pdtnm, d.disprice AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, p.pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivtpqnt>0 AND i.ivttype <>'repair' and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"'union all SELECT DISTINCT 1 AS temp01, i.ivtpamnt AS temp02, p.pdtid, p.pdtnm||' 维修', i.ivtpamnt AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, i.ivtfee as pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE  i.ivttype ='repair'  and i.ivtpamnt <> 0 and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"'";
			String hql = "SELECT DISTINCT NVL(SUM(i.ivtpqnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp01, NVL(SUM(i.ivtpamnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp02, p.pdtid, p.pdtnm, d.disprice AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, p.pdtprc, i.ivttype, 1 as ivtid FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivtpqnt>0 AND i.ivttype <>'repair' AND i.ivtgcltid='"+bf.getIvtgcltid() +"' AND i.ivtyear ='"+bf.getIvtyear()+"' AND i.ivtmonth ='"+bf.getIvtmonth()+"' UNION ALL SELECT DISTINCT 1 AS temp01, i.ivtpamnt AS temp02, p.pdtid, p.pdtnm ||' 维修', decode(d.rdisprice,null,i.ivtpamnt,d.rdisprice)  AS ivtpamnt, DECODE(SIGN(d.rdiscount),0,d.rdiscount,d.rdiscount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, i.ivtfee AS pdtprc, i.ivttype, i.ivtid as ivtid FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tblrepdiscount d ON (d.rdispdtid =i.ivtpdtid AND d.rdisgctid =i.ivtgcltid and d.rivtid = i.ivtid) WHERE i.ivttype ='repair' AND i.ivtpqnt >0 AND i.ivtfee <> 0 AND i.ivtgcltid ='"+bf.getIvtgcltid() +"' AND i.ivtyear ='"+bf.getIvtyear()+"' AND i.ivtmonth ='"+bf.getIvtmonth()+"'"; 
			af = init(req, forward, hql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该月没有销账！";
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
	 * 保存成本价
	 */
	@SuppressWarnings("unchecked")
	public ActionForward batchdis(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		BusinessForm bf = (BusinessForm) form;
		String[] chk = req.getParameterValues("chk");
		Collection<Business> collection = null;
		Collection<Business> checked = new LinkedList();
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),
					Business.class);
			for (int j = 0; j < chk.length; j++) {
				int i = 1;
				for (Business dto : collection) {
					if (i == Integer.parseInt(chk[j])) {
						checked.add(dto);
					}
					i++;
				}
			}
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			BusinessFacade businessFacade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("collection", checked);
			mapRequest.put("ivtmonth",bf.getIvtmonth());
			mapRequest.put("ivtyear",bf.getIvtyear());
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = businessFacade.modifydis(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "批量保存信息成功!");
				return go2Page(req, mapping, "business");
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
	//查询结存数
	public ActionForward jcquery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Business bus = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/business/jcquery.jsp";
		try {
			
			ClassHelper.copyProperties(bf, bus);
			String hql1 = "select distinct nvl(sum(i.ivtlsqnt+i.ivtlmqnt-nvl(i.ivtpqnt,0))over(partition by i.ivtpdtid order by i.ivtpdtid),0) as temp01,i.ivtyear,i.ivtmonth,p.pdtnm,i.ivtpdtid,p.pdttype,p.pdtprc";
			String hql2 = " from tblinventory i left outer join tblproduct p on i.ivtpdtid=p.pdtid left outer join tblgrpclient g on g.gctid=i.ivtgcltid  where i.ivttype<>'repair' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"' ";
			if(bf.getGcttype() != null && !"".equals(bf.getGcttype())) {
				hql1 += ",g.gcttype";
				hql2 += "and g.gcttype='" + bf.getGcttype() + "' ";
			}
			if(bf.getGctarea() != null && !"".equals(bf.getGctarea())) {
				hql1 += ",g.gctarea";
				hql2 += "and g.gctarea='" + bf.getGctarea() + "' ";
			}
			if(bf.getPdtnm() != null && !"".equals(bf.getPdtnm())) {
				hql2 += "and p.pdtnm='" + bf.getPdtnm() + "' ";
			}
			if(bf.getPdttype() != null && !"".equals(bf.getPdttype())) {
				hql2 += "and p.pdttype='" + bf.getPdttype() + "' ";
			}
			String hqlstart="select * from(";
			String hqlend=") a where a.temp01<>0";
			String hql = hqlstart + hql1 + hql2 + hqlend;
			af = init(req, forward, hql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "这个月没有结存的商品！";
				super.saveSuccessfulMsg(req, msg);
			}
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		    return af;
	}
	//结存商品对应的惠耳点
	public ActionForward jcdetail(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Business bus = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/business/jcdetail.jsp";
		try {
				ClassHelper.copyProperties(bf, bus);
				String hql1 = "select distinct nvl(sum(i.ivtlsqnt+i.ivtlmqnt-nvl(i.ivtpqnt,0))over(partition by i.ivtgcltid,i.ivtfee order by i.ivtgcltid),0) as temp01,g.gctnm,i.ivtpdtid,p.pdtnm,i.ivtfee,i.ivttype";
				String hql2 = " from tblinventory i left outer join tblproduct p on i.ivtpdtid=p.pdtid left outer join tblgrpclient g on g.gctid=i.ivtgcltid  where i.ivttype<>'repair' and i.ivtpdtid='" + bf.getIvtpdtid() +"'";
				if(bf.getIvtyear() != null && bf.getIvtyear() != 0) {
					hql1 += ",i.ivtyear";
					hql2 += " and i.ivtyear='" + bf.getIvtyear() + "'";
				}
				if(bf.getIvtmonth() != null && bf.getIvtmonth() != 0) {
					hql1 += ",i.ivtmonth";
					hql2 += " and i.ivtmonth='" + bf.getIvtmonth() + "'";
				}
				if(bf.getIvtgcltid() != null && !"".equals(bf.getIvtgcltid())) {
					hql2 += " and i.ivtgcltid='" + bf.getIvtgcltid() + "'";
				}
				if(bf.getGcttype() != null && !"".equals(bf.getGcttype())) {
					hql1 += ",g.gcttype";
					hql2 += "and g.gcttype='" + bf.getGcttype() + "' ";
				}
				if(bf.getGctarea() != null && !"".equals(bf.getGctarea())) {
					hql1 += ",g.gctarea";
					hql2 += "and g.gctarea='" + bf.getGctarea() + "' ";
				}
				if(bf.getPdtnm() != null && !"".equals(bf.getPdtnm())) {
					hql2 += "and p.pdtnm='" + bf.getPdtnm() + "' ";
				}
				if(bf.getPdttype() != null && !"".equals(bf.getPdttype())) {
					hql2 += "and p.pdttype='" + bf.getPdttype() + "' ";
				}
				String hql = hql1 + hql2;
				af = init(req, forward, hql, "1");
				
				if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
					String msg = "该商品没有对应的惠耳点！";
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
	 * 固定资产查询
	 */
	public ActionForward assetQuery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Business bi = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/business/asset/assetquery.jsp";
		try {
			ClassHelper.copyProperties(bf, bi);
			bi.setFileKey("bus04_000");
			String hql = queryEnterprise(bi);
			af = super.init(req, forward, hql);
		} catch(AppException ae){
			this.saveErrors(req, ae);
		}catch(Exception e){
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 保存固定资产信息
	 */
	public ActionForward saveAsset(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			//将表单中的数据传到实体层
			ClassHelper.copyProperties(bf, business);
			
			business.setAstdt(DateUtil.getDate());
			business.setAstopr(dto.getBsc011());
			business.setAstut(business.getPdtut());
			
			//获取服务接口
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveAsset(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "保存固定资产信息成功");
				return mapping.findForward("assetquery");
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
	 * 显示修改固定资产信息页面
	 */
	public ActionForward showAsset(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//获取服务接口
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.showAsset(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), bf);
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
		return mapping.findForward("showAsset");
	}
	
	/**
	 * 修改固定资产信息
	 */
	public ActionForward modifyAsset(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//获取服务接口
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modifyAsset(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "修改固定资产信息成功!");
				return mapping.findForward("assetquery");
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
	 * 删除固定资产信息
	 */
	public ActionForward deleteAsset(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//获取服务接口
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deleteAsset(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "删除固定资产信息成功!");
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
		return go2Page(req, mapping, "business");
	}
	
	/**
	 * 摊销信息查询
	 */
	public ActionForward amortizeQuery(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Business bi = new Business();
		BusinessForm bf = (BusinessForm) actionForm;
		ActionForward af = new ActionForward();
		String forward = "/business/amortize/amortizequery.jsp";
		Connection con = null;
		CallableStatement proc = null;
				
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			proc = con.prepareCall("{call PRC_AMORTIZE()}");//调用存储过程比较当前日期是否在摊销到期日期之后，如果是则将“是否到期”字段改为‘1’
			proc.execute();
			
			DBUtil.commit(con);
			
			ClassHelper.copyProperties(bf, bi);
			bi.setFileKey("bus05_000");
			String hql = queryEnterprise(bi);
			af = super.init(req, forward, hql);
		} catch(AppException ae){
			this.saveErrors(req, ae);
		}catch(Exception e){
			this.saveErrors(req, e);
		}finally {
			proc.close();
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return af;
	}
	
	/**
	 * 保存摊销费用信息
	 */
	public ActionForward saveAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		try{
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			//将表单中的数据传到实体层
			ClassHelper.copyProperties(bf, business);
			
			business.setArzdt(DateUtil.getDate());
			business.setArzopr(dto.getBsc011());
			
			//获取服务接口
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveAmortize(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "保存摊销费用信息成功");
				return mapping.findForward("amortizequery");
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
	 * 显示修改摊销管理信息页面
	 */
	public ActionForward showAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//获取服务接口
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.showAmortize(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), bf);
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
		return mapping.findForward("showAmortize");
	}
	
	/**
	 * 修改摊销信息
	 */
	public ActionForward modifyAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//获取服务接口
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modifyAmortize(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "修改摊销信息成功!");
				return mapping.findForward("amortizequery");
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
	 * 删除固定资产信息
	 */
	public ActionForward deleteAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//获取服务接口
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deleteAmortize(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "删除摊销费用信息成功!");
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
		return go2Page(req, mapping, "business");
	}
	
	
	/**
	 * 打印摊销信息报表
	 */
	public ActionForward amortizeReport(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		BusinessForm bf = (BusinessForm) actionForm;
		Connection conn1 = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = null;
		String year = null;
		String month = null;
		String day = null;
		try {
			
			date = sdf.format(bf.getArzstdt());
			year = date.substring(2, 4);
			month = date.substring(5, 7);
			if(Integer.parseInt(month) < 9)
			{
				int i = Integer.parseInt(month);
				month = String.valueOf(i);
			}
			day = date.substring(8, 10);
			
			date = day + "-" + month + "月" + " -" + year;
			
			
			
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\amortize.jasper"));
			
			parameters.put("date", date);
			
			
          
			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			
			conn1 = DBUtil.getConnection();
			
			ServletOutputStream ouputStream = res.getOutputStream();
            String reportclass = "amortize_report";
			res.setContentType("application/vnd.ms-excel"); 
			res.setHeader("content-disposition", 
			"attachment;filename=" + reportclass + ".xls"); 

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(), parameters, conn1); 

			JRXlsExporter exporter = new JRXlsExporter(); 
         
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt); 
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream); 
//			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,   
//            "客户信息表.xls");   
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, 
			Boolean.FALSE); 
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, 
			Boolean.FALSE); 
			//添加的属性控制
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            //保留GridLine
            //缩小字体填充单元格
            exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN,false);

            
			exporter.exportReport(); 

			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
            e.printStackTrace();   
		} finally {
			
			DBUtil.closeConnection(conn1);
		}
		return null;
		
	}
	
	/**
	 * 库存超期查询
	 */
	public ActionForward stoexpquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		Connection con = null;
		CallableStatement proc = null;
		String forward = "/business/querystoexp.jsp";
		ActionForward af = new ActionForward();
		try{
			ClassHelper.copyProperties(bf, business);
			//获取服务接口
			con = DBUtil.getConnection();
			proc = con.prepareCall("{call PRC_STOEXP}");
			proc.execute();
			
			String str1_start = "select * from (";
			String str1_end = " and h.folsdt>=to_date('2013-04-26 00:00:00','yyyy-MM-dd HH24:mi:ss') )x1" +
					" where x1.days>0 union ";
			business.setFileKey("bus01_002");
			String hql1 = queryEnterprise(business);
			
			String str2_start = "select * from (";
			String str2_end =")x2 where x2.days>0";
			business.setFileKey("bus01_012");
			String hql2 = queryEnterprise(business);
			
			String hql=str1_start + hql1 + str1_end + str2_start + hql2 + str2_end;
			af = super.init(req, forward, hql);
			

		}catch (Exception e) {
				super.saveErrors(req, e);
				return mapping.findForward("backspace");
		}
		return af;
	}
	
	//查询库存期限
	public ActionForward queryExd(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		try{
			ClassHelper.copyProperties(bf, business);
			
			
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Business> mapRequest = new HashMap<String, Business>();
			mapRequest.put("beo", business);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), bf);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return mapping.findForward("expireday");
	}
	
	
	
	/**
	 * 保存修改的库存期限
	 */
	public ActionForward saveExd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		try{
			ClassHelper.copyProperties(bf, business);
			
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Business> mapRequest = new HashMap<String, Business>();
			mapRequest.put("beo", business);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveExd(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "修改库存期限成功！");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
			}
		}catch (AppException ex) {
			this.saveErrors(request, ex);
		} catch (Exception e) {
			this.saveErrors(request, e);
		}

		return mapping.findForward("stoexp");
	}
	
	/**
	 * 实时销售查询
	 */
	public ActionForward realtimequery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		Business bi = new Business();
		BusinessForm bf = (BusinessForm)form;
		BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
		ActionForward af = new ActionForward();
		String forward = "/business/realtimequery.jsp";
		try {

			ClassHelper.copyProperties(bf, bi);
			System.out.println(bi.getGctnm());
			String pdttype=bf.getPdttype();//品牌类型
			String foltype=bf.getFoltype();//订单类型
			String hql="";
			String hql1="";
			String hql2="";
			if(null!=foltype && foltype!="" && !foltype.equals("normal")){
				// 总部查实时销售，客户名称为空
				if (bi.getGctnm() == "") {
					bi.setFileKey("bus03_001");
				}
				// 客户名称不为空的时候，加盟店，直属店，总部查询
				else {
					bi.setFileKey("bus03_004");
				}
				hql = queryEnterprise(bi);
			}else{
				if(foltype.equals("normal")){//订单类型为普通
					// 总部，直属店查询普通订单
					if (bi.getGctnm() == "") {
						bi.setFileKey("bus03_006");
					} else {
						String gctnm = bi.getGctnm();
						System.out.println(facade.queryStoreType(gctnm));
						if ((facade.queryStoreType(gctnm)).equals("A")) {
							bi.setFileKey("bus03_006");

						} else {
							bi.setFileKey("bus03_005");
						}
					}
					hql = queryEnterprise(bi);
				} else {
					// bi.setFileKey("bus03_003");
					if (bi.getGctnm() == "") {
						bi.setFileKey("bus03_001");
						hql1 = queryEnterprise(bi);
						bi.setFileKey("bus03_006");
						hql2 = queryEnterprise(bi);
					} else {
						bi.setFileKey("bus03_004");
						hql1 = queryEnterprise(bi);
						if ((facade.queryStoreType(bi.getGctnm())).equals("A")) {
							bi.setFileKey("bus03_006");
						} else {
							bi.setFileKey("bus03_005");
						}
						hql2 = queryEnterprise(bi);
					}
					hql = "select * from(" + hql1
							+ ") union all select * from (" + hql2 + ")";
				}
			}
			//bi.setFileKey("bus03_001");
			
			//String hql = queryEnterprise(bi);
			af = super.init(req, forward, hql);
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
