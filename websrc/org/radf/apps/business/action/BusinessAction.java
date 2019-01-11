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
 * �������
 */
public class BusinessAction extends ActionLeafSupport {

	/**
	 * ��ѯ��ת
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
		
		//ֱ�����Լ����ʣ��ܲ�ֻ�ܴ�ӡ����
		if("ZSxiaozhang".equals(menuId)){
			bf.setIvtgcltid(dto.getBsc011());
		}
		
		ClassHelper.copyProperties(bf, form);
		return mapping.findForward(forward);
	}

	/**
	 * �½�ǰУ��
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
	 * �½��ѯ
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
//				String msg = "������δ���㣡";
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
	 * ֱ���½��ѯ
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
				String msg = "û�в�ѯ��������������Ϣ��";
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
	 * �����½��ѯ
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
				//���˵������ݲ���֮ǰ��
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
	 * �����½����
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
	// ���ü��˵���½�洢����
	CallableStatement proc1 = null;
	try {
	LoginDTO dto1 = (LoginDTO) req.getSession()
	.getAttribute("LoginDTO");
	conn = DBUtil.getConnection();
	if (bf.getIvtyear() > 2013
	|| (bf.getIvtyear() == 2013 && bf.getIvtmonth() > 4)) {
	proc = conn
	.prepareCall("{ call PRC_MONTHLYSTATEMENT10(?, ?, ?, ?) }");// �����½�洢���̣���������Էֳ������֣���һ������ֱ����ģ��ڶ������Ǽ��˵��
	proc1 = conn.prepareCall("{ call PRC_JMDYJ(?, ?, ?, ?) }");// �����½�洢���̣���������Էֳ������֣���һ������ֱ����ģ��ڶ������Ǽ��˵��
	} else {
	proc = conn
	.prepareCall("{ call PRC_MONTHLYSTATEMENT(?, ?, ?, ?) }");// �����½�洢����
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
	// ���ö�Ӧ��Facadeҵ������
	ResponseEnvelop resEnv = null;
	// if(bf.getIvtyear()>=2013&&bf.getIvtmonth()>1)
	// {
	resEnv = facade.account(requestEnvelop);
	// }

	// �����ؽ��
	returnValue = processRevt(resEnv);
	if (returnValue.isSucessFlag()) {
	System.out.println("�¶Ƚ��˱���ɹ���");
	}

	if (retValue == 0&&retValue1==0) {
	String msg = "����������µĽ��㣡";
	super.saveSuccessfulMsg(req, msg);
	// return actionMapping.findForward("backspace");
	} else if (retValue == 1&&retValue1==1) {
	String time="�¶Ƚ���ɹ�����ʱ��"+String.valueOf((endTime - startTime) / 1000)+"s";
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("userId", dto1.getBsc011());
			mapRequest.put("collection", checked);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = businessFacade.modify(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "����������Ϣ�ɹ�!");
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
	 * Ӧ�տ��ѯ
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
				String msg = "����»�û���н��㣡";
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
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\company_detail_2.jasper"));

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��

			String gcltid = bf.getIvtgcltid();
			parameters.put("year", bf.getIvtyear());
			parameters.put("gcltid", gcltid);
			parameters.put("smonth", bf.getIvtmonth());// ��������
			parameters.put("emonth", bf.getIvtpqnt());

			String sql1 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gctarea=b.aaa102 where b.aaa100='GCTAREA' and g.gctid='"
					+ gcltid + "'";
			String sql2 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gctprovince=b.aaa102 where b.aaa100='GCTPROVINCE' and g.gctid='"
					+ gcltid + "'";
			String sql3 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gcttype=b.aaa102 where b.aaa100='GCTTYPE' and g.gctid='"
					+ gcltid + "'";
			boolean isSuccess = false;
			// ���ӵ����ݿ�

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

			// ���ӵ����ݿ�
			conn1 = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
					"�ͻ���Ϣ��.xls");
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
			// ����GridLine
			// ��С������䵥Ԫ��
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
	 * Ʒ����������
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
		String forward = "/business/saleDetail.jsp"; // �鿴������ϸ

		try {
			// ����ҳ������

			String queryhql = "select n.*, p.pdtnm, i.ictnm  from TBLINVENTORY  n LEFT OUTER JOIN tblproduct p ON n.ivtpdtid=p.pdtid LEFT OUTER JOIN tblindclient i ON i.ictid= n.ivtcltid where "
					+ " p.pdttype='"
					+ temp01
					+ "'"
					+ " and n.ivtyear="
					+ ivtyear; // ��ҳ�����ѯ
			if (!"".equals(ivtgcltid) && ivtgcltid != null)
				queryhql += " and n.ivtgcltid='" + ivtgcltid + "'";
			if (ivtmonth != null)
				queryhql += " and n.ivtmonth=" + ivtmonth;

			af = init(req, forward, queryhql);

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ��������������Ϣ��";
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

	// ������������
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

		String forward = "/business/qyxsDetail.jsp"; // �鿴������ϸ

		try {
			// ����ҳ������

			String queryhql = "select n.*, p.pdtnm, i.ictnm  from TBLINVENTORY  n LEFT OUTER JOIN tblproduct p ON n.ivtpdtid=p.pdtid LEFT OUTER JOIN tblindclient i ON i.ictid= n.ivtcltid left outer join tblgrpclient g on g.gctid=n.ivtgcltid where "
					+ " g.gctarea='"
					+ temp01
					+ "'"
					+ " and n.ivtyear="
					+ ivtyear; // ��ҳ�����ѯ

			if (ivtmonth != null)
				queryhql += " and n.ivtmonth=" + ivtmonth;

			af = init(req, forward, queryhql);

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ��������������Ϣ��";
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
	 * �����±���
	 */
	/*
	 * ֱ������ܱ��ӡ
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
//            "�ͻ���Ϣ��.xls");   
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, 
			Boolean.FALSE); 
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, 
			Boolean.FALSE); 
			//��ӵ����Կ���
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            //����GridLine
            //��С������䵥Ԫ��
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

			// ������
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
			// ����Ƿ��
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

			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
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
			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��
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

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			
			//2012/2/5�޸�
			//res.setHeader("content-disposition", "attachment;filename=yuebaobiao.pdf");	//PDF�����ֲ���Ϊ���ģ����򱨴�
			
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
	 * ��������ͳ��
	 */
	public ActionForward queryqyxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		BusinessForm bf = (BusinessForm) form;
		Business bi = new Business();
		try {
			ClassHelper.copyProperties(bf, bi);
			String forward = "/business/queryqyxs.jsp";
			String queryhql = "";// ��ҳ�����ѯ
			String chartsql = "";// ͼ���ѯ

			Integer month = bf.getIvtmonth();

			req.setAttribute("ivtyear", bf.getIvtyear());
			if (month != 0) {
				queryhql = "select nvl(g.gctarea,'����') as temp01,nvl(sum(i.ivtpamnt),0) as temp02,nvl(sum(i.ivtpqnt),0) as temp03,round(100*RATIO_TO_REPORT(nvl(sum(i.ivtpamnt),0)) over(),2) as percent,row_number() over(order by nvl(sum(i.ivtpamnt),0) desc) rank from tblinventory i left outer join tblgrpclient g on g.gctid=i.ivtgcltid where i.ivtyear="
						+ bf.getIvtyear()
						+ " and i.ivtmonth="
						+ bf.getIvtmonth() + " group by g.gctarea";

				req.setAttribute("ivtmonth", month);

				chartsql = "select nvl(g.gctarea,'����') as temp01,nvl(sum(i.ivtpamnt),0) as temp02 from tblinventory i left outer join tblgrpclient g on g.gctid=i.ivtgcltid where i.ivtyear="
						+ bf.getIvtyear()
						+ " and i.ivtmonth="
						+ bf.getIvtmonth()
						+ " group by g.gctarea order by temp02 desc";
			} else {
				bf.setIvtmonth(null);
				queryhql = "select nvl(g.gctarea,'����') as temp01,nvl(sum(i.ivtpamnt),0) as temp02,nvl(sum(i.ivtpqnt),0) as temp03,round(100*RATIO_TO_REPORT(nvl(sum(i.ivtpamnt),0)) over(),2) as percent,row_number() over(order by nvl(sum(i.ivtpamnt),0) desc) rank from tblinventory i left outer join tblgrpclient g on g.gctid=i.ivtgcltid where i.ivtyear="
						+ bf.getIvtyear() + " group by g.gctarea";

				chartsql = "select nvl(g.gctarea,'����') as temp01,nvl(sum(i.ivtpamnt),0) as temp02 from tblinventory i left outer join tblgrpclient g on g.gctid=i.ivtgcltid where i.ivtyear="
						+ bf.getIvtyear()
						+ " group by g.gctarea order by temp02 desc";
			}

			af = init(req, forward, queryhql, "1");
			req.getSession().setAttribute("chartname", "��������ͳ�Ʊ�");
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ��������������Ϣ��";
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
	 * Ʒ�� ��Ʒ����ͳ��
	 */
	public ActionForward querycpxs(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		BusinessForm bf = (BusinessForm) form;
		Business bi = new Business();
		try {
			ClassHelper.copyProperties(bf, bi);
			String forward = "/business/queryppxs.jsp";
			String queryhql = "";// ��ҳ�����ѯ
			String chartsql = "";// ͼ���ѯ

			Integer month = bf.getIvtmonth(); // �·�
			String gctid = bf.getIvtgcltid(); // ����ͻ�����
			queryhql = "select p.pdttype as temp01,nvl(sum(i.ivtpamnt),0) as temp02,nvl(sum(i.ivtpqnt),0) as temp03,round(100*RATIO_TO_REPORT(nvl(sum(i.ivtpamnt),0)) over(),2) as percent,row_number() over(order by nvl(sum(i.ivtpamnt),0) desc) rank from tblinventory i left outer join tblproduct p on p.pdtid=i.ivtpdtid where i.ivtyear="
					+ bf.getIvtyear();
			// �����·ݲ�ѯ����
			req.setAttribute("ivtyear", bf.getIvtyear());
			if (month != 0) {
				queryhql += " and i.ivtmonth=";
				queryhql += bf.getIvtmonth();
				req.setAttribute("ivtmonth", month);
			} else {
				bf.setIvtmonth(null);
			}
			// ��������ͻ���ѯ����
			if (gctid != null && gctid != "") {
				queryhql += " and i.ivtgcltid='";
				queryhql += bf.getIvtgcltid();
				queryhql += "'";
				req.setAttribute("gctid", gctid);
			}
			queryhql += " and p.pdttype is not null group by p.pdttype";

			chartsql = "select p.pdttype as temp01,nvl(sum(i.ivtpamnt),0) as temp02 from tblinventory i left outer join tblproduct p on p.pdtid=i.ivtpdtid where i.ivtyear="
					+ bf.getIvtyear();
			// �����·ݲ�ѯ����
			if (month != 0) {
				chartsql += " and i.ivtmonth=";
				chartsql += bf.getIvtmonth();
			}
			// ��������ͻ���ѯ����
			if (gctid != null && gctid != "") {
				chartsql += " and i.ivtgcltid='";
				chartsql += bf.getIvtgcltid();
				chartsql += "'";
			}
			chartsql += " and p.pdttype is not null group by p.pdttype order by temp02 desc";

			af = init(req, forward, queryhql, "1");
			req.getSession().setAttribute("chartname", "��Ʒ����ͳ�Ʊ�");
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ��������������Ϣ��";
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
	 * ������Ϣ��ѯ
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
		String ptype = bsForm.getPdttype();//��ƷƷ��
		String pnm = bsForm.getPdtnm();
		String rtype = bsForm.getRepfee();
		String ivttype = bsForm.getIvttype();//��Ʒ����
		
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
			// �ձ�����һ��ʵ��Ϊ""
			if (gtype != null && !"".equals(gtype)) {
				sql2 += " and g.gcttype='" + gtype + "'";
			}
			
			//��С����ͻ���Χ
			if (province != null && !"".equals(province)) {
				sql1 += ",sum(i.ivtpamnt)over(partition by g.gctprovince) as temp04";
				sql2 += " and g.gctprovince='" + province + "'";
			}//else{
				if (area != null && !"".equals(area)) {
					sql1 += ",sum(i.ivtpamnt)over(partition by g.gctarea) as temp06";
					sql2 += " and g.gctarea='" + area + "'";
				}
			//}
			//����ͻ�
			if (ivtgcltid != null && !"".equals(ivtgcltid)) {
				sql2 += " and g.gctid='" + ivtgcltid + "'";
			}
			//�������
			if(pdtcls !=null && !"".equals(pdtcls)){
				sql2 += "and p.pdtcls='" + pdtcls + "'";
			}
			//��Ʒ��Ϣ
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

			//�������ͣ�Ϊ�˲�ѯά�޷�
			/*if (rtype != null && !"".equals(rtype)) {
				sql1 += ",sum(i.ivtpamnt)over(partition by g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid,i.ivttype order by i.ivtgcltid) as temp12 ";
				sql2 += " and i.ivttype='repair' ";
			} else {
				sql1 += ",sum(i.ivtpamnt)over(partition by i.ivtpdtid,g.gcttype,g.gctarea,g.gctprovince,i.ivtgcltid order by i.ivtgcltid) as temp01,sum(i.ivtpqnt)over(partition by p.pdtnm,i.ivtgcltid order by p.pdtnm,i.ivtgcltid) as temp02 ";
				//sql2 += " and i.ivttype<>'repair'";
			}*/
			
			//��Ʒ����
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
						//sql2 += " and i.ivttype='normal' and p.pdtnm in (select pdtnm from tblproduct where pdtnm like'%��ʽ��%' or pdtnm like '%������%' and pdtcls not in('OTH')) ) where temp01 > 0 or temp02 >0" ;
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
				String msg = "û�в�ѯ������������������Ϣ��";
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
	 * �û���Ϣ��ѯ
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
				gender = "Ů";
			}else if(gender.equals("1")){
				gender = "��";
			}else{
				gender = "��";
			}
			
			String sql1 = "select i.ivtyear,g.gctid,g.gctnm, count(distinct i.ivtcltid) as amount,(select count(distinct i.ivtcltid) from  tblinventory i where i.ivtyear='"
				+ year + "' and i.ivtmonth >='" 
				+ months + "' and ivtmonth <='"
				+ montht + "') as total, (case c.ictgender when '��' then '��' when 'Ů' then 'Ů' else '��' end) as gender" +
				" from tblgrpclient g left outer join tblinventory i on g.gctid=i.ivtgcltid " +
				"left outer join tblindclient c on c.ictid = i.ivtcltid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//�������ͻ���ֵ
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and g.gctid='" + gctid + "'";
			}
			if(ages != 0 && !"".equals(ages)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12) >='" + ages + "' ";
			}
			if(aget != 0 && !"".equals(aget)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12)<='" + aget + "'";
			}
			if(!"��".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			sql2 += "group by  i.ivtyear,g.gctnm,g.gctid,c.ictgender order by g.gctid";
			String sql = sql1 + sql2;
			
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�������������û���Ϣ��";
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
	 * �۸�ͳ����Ϣ��ѯ
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
				gender = "Ů";
			}else if(gender.equals("1")){
				gender = "��";
			}else{
				gender = "��";
			}
			
			String sql1 = "select i.ivtyear,g.gctid,g.gctnm, count(distinct i.ivtcltid) as amount,(select count(distinct i.ivtcltid) from  tblinventory i where i.ivtyear='"
				+ year + "' and i.ivtmonth >='" 
				+ months + "' and ivtmonth <='"
				+ montht + "') as total, (case c.ictgender when '��' then '��' when 'Ů' then 'Ů' else '��' end) as gender" +
				" from tblgrpclient g left outer join tblinventory i on g.gctid=i.ivtgcltid " +
				"left outer join tblindclient c on c.ictid = i.ivtcltid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//�������ͻ���ֵ
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and g.gctid='" + gctid + "'";
			}
			if(prices != 0 && !"".equals(prices)){
				sql2 += " and i.ivtpamnt >='" + prices + "' ";
			}
			if(pricet != 0 && !"".equals(pricet)){
				sql2 += " and i.ivtpamnt <='" + pricet + "'";
			}
			if(!"��".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			sql2 += "group by  i.ivtyear,g.gctnm,g.gctid,c.ictgender order by g.gctid";
			String sql = sql1 + sql2;
			
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ۸�ͳ����Ϣ��";
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
	 * ���ͳ����Ϣ��ѯ
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
				gender = "Ů";
			}else if(gender.equals("1")){
				gender = "��";
			}else{
				gender = "��";
			}
			
			String sql1 = "select i.ivtyear,g.gctid,g.gctnm, count(distinct i.ivtcltid) as amount,(select count(distinct i.ivtcltid) from  tblinventory i where i.ivtyear='"
				+ year + "' and i.ivtmonth >='" 
				+ months + "' and ivtmonth <='"
				+ montht + "') as total, (case c.ictgender when '��' then '��' when 'Ů' then 'Ů' else '��' end) as gender" +
				" from tblgrpclient g left outer join tblinventory i on g.gctid=i.ivtgcltid " +
				"left outer join tblindclient c on c.ictid = i.ivtcltid " +
				"left outer join tbldiagnose g on g.dgnctid=c.ictid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//�������ͻ���ֵ
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and g.gctid='" + gctid + "'";
			}
			if(!"��".equals(gender)){
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
				String msg = "û�в�ѯ���������������ͳ����Ϣ��";
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
	 * �û���Ϣ���۸������������˫������ѯ
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
				gender = "Ů";
			}else if(gender.equals("1")){
				gender = "��";
			}else{
				gender = "��";
			}*/
			
			if(!gender.equals("0") && !gender.equals("1")){
				gender = "��";
			}
			
			String sql1 = "select i.ivtyear,p.gctid,p.gctnm, count(distinct i.ivtcltid) as amount,(select count(distinct i.ivtcltid) from  tblinventory i where i.ivtyear='"
				+ year + "' and i.ivtmonth >='" 
				+ months + "' and ivtmonth <='"
				+ montht + "') as total, (case c.ictgender when '1' then '��' when '0' then 'Ů' else '��' end) as gender" +
				" from tblgrpclient p left outer join tblinventory i on p.gctid=i.ivtgcltid " +
				"left outer join tblindclient c on c.ictid = i.ivtcltid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//�������ͻ���ֵ
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and p.gctid='" + gctid + "'";
			}
			
			//���ѡ����������
			if(ages != 0 && !"".equals(ages)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12) >='" + ages + "' ";
			}
			if(aget != 0 && !"".equals(aget)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12)<='" + aget + "'";
			}
			
			//���ѡ��۸�����
			if(prices != 0 && !"".equals(prices)){
				sql2 += " and i.ivtpamnt >='" + prices + "' ";
			}
			if(pricet != 0 && !"".equals(pricet)){
				sql2 += " and i.ivtpamnt <='" + pricet + "'";
			}
			
			//���ѡ������������
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
			
			
			
			if(!"��".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			
			
			sql2 += " group by  i.ivtyear,p.gctnm,p.gctid,c.ictgender order by p.gctid";
			String sql = sql1 + sql2;
			String sqlstart = "select * from (";
			String sqlend = ")x where x.amount>0";
			sql = sqlstart + sql + sqlend;
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�������������û���Ϣ��";
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
	 * �û���Ϣ���۸������������˫������ѯ��ϸ
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
				gender = "��";
			}
			
			String sql1 = "select distinct c.*,i.ivtyear,p.gctnm as ictgctnm" +
				" from tblindclient c left outer join tblinventory i on i.ivtcltid=c.ictid " +
				"left outer join tblgrpclient p on p.gctid = i.ivtgcltid ";
				//"left outer join tblindclient c on c.ictid = i.ivtcltid";
			String sql2 = " where i.ivtyear ='" + year + "' and i.ivtmonth >='"
					+ months + "' and ivtmonth <='"
					+ montht + "' ";
			
			//�������ͻ���ֵ
			if(gctid != null && !"".equals(gctid)){
				sql2 += " and p.gctid='" + gctid + "'";
			}
			
			//���ѡ����������
			if(ages != 0 && !"".equals(ages)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12) >='" + ages + "' ";
			}
			if(aget != 0 && !"".equals(aget)){
				sql2 += " and round(months_between(sysdate,c.ictbdt)/12)<='" + aget + "'";
			}
			
			//���ѡ��۸�����
			if(prices != 0 && !"".equals(prices)){
				sql2 += " and i.ivtpamnt >='" + prices + "' ";
			}
			if(pricet != 0 && !"".equals(pricet)){
				sql2 += " and i.ivtpamnt <='" + pricet + "'";
			}
			
			//���ѡ������������
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
			
			
			
			if(!"��".equals(gender)){
				sql2 += " and c.ictgender='" + gender + "' ";
			}
			
			
			//sql2 += " group by  i.ivtyear,g.gctnm,g.gctid,c.ictgender order by g.gctid";
			String sql = sql1 + sql2;
			
			af = init(req, forward, sql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�������������û���Ϣ��";
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
	 * ����� ����5λ ����
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

			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ�� PDF
//			File reportFile = new File(req.getSession().getServletContext()
//					.getRealPath("\\WEB-INF\\report\\profit_bean.jasper"));
//
//			// �ڿ���̨��ʾһ�±����ļ�������·��
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
			//��ӵ����Կ���
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            //����GridLine
            //��С������䵥Ԫ��
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
	 * ��ӳɱ���
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
			//�мӼ��ѵĲ�ѯ��� String hql = "SELECT DISTINCT NVL(SUM(i.ivtpqnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp01, NVL(SUM(i.ivtpamnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp02, p.pdtid, p.pdtnm, d.disprice AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, p.pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivtpqnt>0 AND i.ivttype <>'repair' and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"' union all SELECT DISTINCT 1 AS temp01, nvl(i.ivtpamnt,0) AS temp02, p.pdtid, p.pdtnm||' '||i.ivtnote as pdtnm, d.disprice AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, i.ivtfee as pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivttype <>'repair' and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"' AND ivtnote IN ('������','�Ӽ���') union all SELECT DISTINCT 1 AS temp01, i.ivtpamnt AS temp02, p.pdtid, p.pdtnm||' ά��', i.ivtpamnt AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, i.ivtfee as pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE  i.ivttype ='repair' and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"'";
			//String hql = "SELECT DISTINCT NVL(SUM(i.ivtpqnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp01, NVL(SUM(i.ivtpamnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp02, p.pdtid, p.pdtnm, d.disprice AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, p.pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivtpqnt>0 AND i.ivttype <>'repair' and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"'union all SELECT DISTINCT 1 AS temp01, i.ivtpamnt AS temp02, p.pdtid, p.pdtnm||' ά��', i.ivtpamnt AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, i.ivtfee as pdtprc, i.ivttype FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE  i.ivttype ='repair'  and i.ivtpamnt <> 0 and i.ivtgcltid='"+bf.getIvtgcltid() +"' and i.ivtyear='"+bf.getIvtyear()+"' and i.ivtmonth='"+bf.getIvtmonth()+"'";
			String hql = "SELECT DISTINCT NVL(SUM(i.ivtpqnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp01, NVL(SUM(i.ivtpamnt)over(partition BY p.pdtid order by i.ivttype, p.pdtid),0) AS temp02, p.pdtid, p.pdtnm, d.disprice AS ivtpamnt, DECODE(SIGN(d.discount),0,d.discount,d.discount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, p.pdtprc, i.ivttype, 1 as ivtid FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tbldiscount d ON (d.dispdtid =i.ivtpdtid AND d.disgctid =i.ivtgcltid) WHERE i.ivtpqnt>0 AND i.ivttype <>'repair' AND i.ivtgcltid='"+bf.getIvtgcltid() +"' AND i.ivtyear ='"+bf.getIvtyear()+"' AND i.ivtmonth ='"+bf.getIvtmonth()+"' UNION ALL SELECT DISTINCT 1 AS temp01, i.ivtpamnt AS temp02, p.pdtid, p.pdtnm ||' ά��', decode(d.rdisprice,null,i.ivtpamnt,d.rdisprice)  AS ivtpamnt, DECODE(SIGN(d.rdiscount),0,d.rdiscount,d.rdiscount) AS discount, g.gctnm, i.ivtpdtid, i.ivtgcltid, i.ivtfee AS pdtprc, i.ivttype, i.ivtid as ivtid FROM tblinventory i LEFT OUTER JOIN tblproduct p ON p.pdtid= i.ivtpdtid LEFT OUTER JOIN tblgrpclient g ON g.gctid=i.ivtgcltid LEFT OUTER JOIN tblrepdiscount d ON (d.rdispdtid =i.ivtpdtid AND d.rdisgctid =i.ivtgcltid and d.rivtid = i.ivtid) WHERE i.ivttype ='repair' AND i.ivtpqnt >0 AND i.ivtfee <> 0 AND i.ivtgcltid ='"+bf.getIvtgcltid() +"' AND i.ivtyear ='"+bf.getIvtyear()+"' AND i.ivtmonth ='"+bf.getIvtmonth()+"'"; 
			af = init(req, forward, hql, "1");

			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "����û�����ˣ�";
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
	 * ����ɱ���
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("collection", checked);
			mapRequest.put("ivtmonth",bf.getIvtmonth());
			mapRequest.put("ivtyear",bf.getIvtyear());
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = businessFacade.modifydis(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "����������Ϣ�ɹ�!");
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
	//��ѯ�����
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
				String msg = "�����û�н�����Ʒ��";
				super.saveSuccessfulMsg(req, msg);
			}
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		    return af;
	}
	//�����Ʒ��Ӧ�Ļݶ���
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
					String msg = "����Ʒû�ж�Ӧ�Ļݶ��㣡";
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
	 * �̶��ʲ���ѯ
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
	 * ����̶��ʲ���Ϣ
	 */
	public ActionForward saveAsset(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			//�����е����ݴ���ʵ���
			ClassHelper.copyProperties(bf, business);
			
			business.setAstdt(DateUtil.getDate());
			business.setAstopr(dto.getBsc011());
			business.setAstut(business.getPdtut());
			
			//��ȡ����ӿ�
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.saveAsset(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "����̶��ʲ���Ϣ�ɹ�");
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
	 * ��ʾ�޸Ĺ̶��ʲ���Ϣҳ��
	 */
	public ActionForward showAsset(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//��ȡ����ӿ�
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.showAsset(requestEnvelop);
			// �����ؽ��
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
	 * �޸Ĺ̶��ʲ���Ϣ
	 */
	public ActionForward modifyAsset(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//��ȡ����ӿ�
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modifyAsset(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "�޸Ĺ̶��ʲ���Ϣ�ɹ�!");
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
	 * ɾ���̶��ʲ���Ϣ
	 */
	public ActionForward deleteAsset(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//��ȡ����ӿ�
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.deleteAsset(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "ɾ���̶��ʲ���Ϣ�ɹ�!");
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
	 * ̯����Ϣ��ѯ
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
			proc = con.prepareCall("{call PRC_AMORTIZE()}");//���ô洢���̱Ƚϵ�ǰ�����Ƿ���̯����������֮��������򽫡��Ƿ��ڡ��ֶθ�Ϊ��1��
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
	 * ����̯��������Ϣ
	 */
	public ActionForward saveAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		try{
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			//�����е����ݴ���ʵ���
			ClassHelper.copyProperties(bf, business);
			
			business.setArzdt(DateUtil.getDate());
			business.setArzopr(dto.getBsc011());
			
			//��ȡ����ӿ�
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.saveAmortize(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "����̯��������Ϣ�ɹ�");
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
	 * ��ʾ�޸�̯��������Ϣҳ��
	 */
	public ActionForward showAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//��ȡ����ӿ�
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.showAmortize(requestEnvelop);
			// �����ؽ��
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
	 * �޸�̯����Ϣ
	 */
	public ActionForward modifyAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//��ȡ����ӿ�
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modifyAmortize(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "�޸�̯����Ϣ�ɹ�!");
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
	 * ɾ���̶��ʲ���Ϣ
	 */
	public ActionForward deleteAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		
		try{
			ClassHelper.copyProperties(bf, business);
			//��ȡ����ӿ�
			BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", business);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.deleteAmortize(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag())
			{
				super.saveSuccessfulMsg(req, "ɾ��̯��������Ϣ�ɹ�!");
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
	 * ��ӡ̯����Ϣ����
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
			
			date = day + "-" + month + "��" + " -" + year;
			
			
			
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			File reportFile = null;
			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\amortize.jasper"));
			
			parameters.put("date", date);
			
			
          
			// �ڿ���̨��ʾһ�±����ļ�������·��
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
//            "�ͻ���Ϣ��.xls");   
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, 
			Boolean.FALSE); 
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, 
			Boolean.FALSE); 
			//��ӵ����Կ���
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
            //����GridLine
            //��С������䵥Ԫ��
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
	 * ��泬�ڲ�ѯ
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
			//��ȡ����ӿ�
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
	
	//��ѯ�������
	public ActionForward queryExd(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		Business business = new Business();
		BusinessForm bf = (BusinessForm)form;
		try{
			ClassHelper.copyProperties(bf, business);
			
			
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Business> mapRequest = new HashMap<String, Business>();
			mapRequest.put("beo", business);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.print(requestEnvelop);
			// �����ؽ��
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
	 * �����޸ĵĿ������
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
			// ��Application�������HashMap
			HashMap<String, Business> mapRequest = new HashMap<String, Business>();
			mapRequest.put("beo", business);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.saveExd(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "�޸Ŀ�����޳ɹ���");
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
	 * ʵʱ���۲�ѯ
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
			String pdttype=bf.getPdttype();//Ʒ������
			String foltype=bf.getFoltype();//��������
			String hql="";
			String hql1="";
			String hql2="";
			if(null!=foltype && foltype!="" && !foltype.equals("normal")){
				// �ܲ���ʵʱ���ۣ��ͻ�����Ϊ��
				if (bi.getGctnm() == "") {
					bi.setFileKey("bus03_001");
				}
				// �ͻ����Ʋ�Ϊ�յ�ʱ�򣬼��˵ֱ꣬���꣬�ܲ���ѯ
				else {
					bi.setFileKey("bus03_004");
				}
				hql = queryEnterprise(bi);
			}else{
				if(foltype.equals("normal")){//��������Ϊ��ͨ
					// �ܲ���ֱ�����ѯ��ͨ����
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
				String msg = "û�в�ѯ�����������ļ�¼��";
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
