package org.radf.apps.business.action;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.naming.java.javaURLContextFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.business.facade.BusinessFacade;
import org.radf.apps.business.form.BusinessForm;
import org.radf.apps.business.form.FeeForm;
import org.radf.apps.client.group.facade.GroupClientFacade;
import org.radf.apps.client.group.form.GroupClientForm;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.client.single.form.SingleClientForm;
import org.radf.apps.commons.entity.Business;
import org.radf.apps.commons.entity.Fee;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

import antlr.collections.impl.Vector;

public class FeeAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());
	
	/**
	 * 跳转
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
		String menuId = req.getParameter("menuId");
		String forward = menuId;
		FeeForm bf = new FeeForm();
		bf.setFeeyear(DateUtil.getYear());
		Calendar calendar = Calendar.getInstance();
		bf.setFeemonth(new Integer(calendar.get(2) + 1));
		if("fee".equals(menuId)){
			bf.setFeegctid(dto.getBsc011());
		}
		ClassHelper.copyProperties(bf, form);
		return mapping.findForward(forward);
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 * 录入客户费用信息
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Fee fee = new Fee();
		FeeForm fef = (FeeForm) form;
		try {
			ClassHelper.copyProperties(fef, fee);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			fee.setFeeop(dto1.getBsc011());
			//fee.setFeegctid(dto1.getBsc011());
			fee.setFeeopdata(DateUtil.getSystemCurrentTime());
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", fee);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.insert(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "录入客户费用信息成功!");
				String gctid = (String) ((HashMap) resEnv.getBody())
						.get("feegctid");
				// 获得从业务层返回的日志信息
				String workString = (String) ((HashMap) resEnv.getBody())
						.get("workString");
				FindLog.insertLog(req, gctid, workString);
				return mapping.findForward("insertfee");
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
	 * 输入摊销费用
	 */
	public ActionForward insertAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Fee fee = new Fee();
		FeeForm fef = (FeeForm) form;
		try {
			ClassHelper.copyProperties(fef, fee);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			fee.setFeeop(dto1.getBsc011());
			//fee.setFeegctid(dto1.getBsc011());
			fee.setFeeopdata(DateUtil.getSystemCurrentTime());
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", fee);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.insertAmortize(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "录入客户费用信息成功!");
				String gctid = (String) ((HashMap) resEnv.getBody())
						.get("feegctid");
				// 获得从业务层返回的日志信息
				String workString = (String) ((HashMap) resEnv.getBody())
						.get("workString");
				FindLog.insertLog(req, gctid, workString);
				return mapping.findForward("insertAmortize");
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
	 * 打印客户费用报表
	 */
	public ActionForward report(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		FeeForm bf = (FeeForm) actionForm;
		Connection conn1 = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\feereport.jasper"));
			
			parameters.put("year", bf.getFeeyear());
			parameters.put("month", bf.getFeemonth());
			parameters.put("gctid", bf.getFeegctid());
			
          
			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			
			conn1 = DBUtil.getConnection();
			
			ServletOutputStream ouputStream = res.getOutputStream();
            String reportclass = "fee_report";
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
	
	
	@SuppressWarnings("deprecation")
	public ActionForward companyfee(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		FeeForm bf = (FeeForm) actionForm;
		Connection conn1 = null;
//		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = null;
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			String gctarea = bf.getGctarea();
			String gctid = bf.getFeegctid();
			if(gctid == null || "".equals(gctid)){
				if(gctarea == null || "".equals(gctarea)){
					reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\companyfee_4.jasper"));//客户名称为空，区域为空
				}else{
					reportFile = new File(req.getSession().getServletContext()
							.getRealPath("\\WEB-INF\\report\\companyfee_3.jasper"));//客户名称为空，区域不为空
					  parameters.put("gctarea",gctarea );
				}
			}else{
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\companyfee_5.jasper"));//客户名称不为空
				  parameters.put("gctid",gctid );
			}
			String webRootPath = req.getSession().getServletContext()
					.getRealPath("/");
			parameters.put("ccyear", bf.getFeeyear());
			parameters.put("endYear", bf.getEndYear());
			parameters.put("startMonth", bf.getStartMonth());
			parameters.put("endMonth", bf.getEndMonth());
			parameters.put("SUBREPORT_DIR", webRootPath + "WEB-INF\\report\\");
			
//			String sql1 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gctarea=b.aaa102 where b.aaa100='GCTAREA' and g.gctid='" + feegctid + "'";
//			String sql2 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gctprovince=b.aaa102 where b.aaa100='GCTPROVINCE' and g.gctid='" + feegctid + "'";
//			String sql3 = "select b.aaa103 from aa10 b left outer join tblgrpclient g on g.gcttype=b.aaa102 where b.aaa100='GCTTYPE' and g.gctid='" + feegctid + "'";
//			boolean isSuccess = false;
//			// 连接到数据库
//			
//			conn = DBUtil.getConnection();
//			
//			Statement pstmt1 =  conn.createStatement(); //Statment
//			ResultSet rs1 = pstmt1.executeQuery(sql1);
//			String area = null;
//			String province = null;
//			String type = null;
//			if(rs1.next()){ 
//				isSuccess = true;
//			    area = rs1.getString("aaa103");
//			}
//			rs1.close();
//			pstmt1.close();
//			
//			Statement pstmt2 =  conn.createStatement();
//			ResultSet rs2 = pstmt2.executeQuery(sql2);
//			if(rs2.next()){ 
//				isSuccess = true;
//			    province = rs2.getString("aaa103");
//			}
//			rs2.close();
//			pstmt2.close();
//			
//			Statement pstmt3 =  conn.createStatement();
//			ResultSet rs3 = pstmt3.executeQuery(sql3);
//			if(rs3.next()){ 
//				isSuccess = true;
//			    type = rs3.getString("aaa103");
//			}
//			rs3.close();
//			pstmt3.close();
			
//			DBUtil.closeConnection(conn);
	
//			parameters.put("area", area);
//			parameters.put("province",province );
//			parameters.put("type", type);
	
			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			
			conn1 = DBUtil.getConnection();
			
			ServletOutputStream ouputStream = res.getOutputStream();
            String reportclass = "company_fee";
			res.setContentType("application/vnd.ms-excel"); 
			res.setHeader("content-disposition", 
			"attachment;filename=" + reportclass + ".xls"); 

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(), parameters, conn1); 

			JRXlsExporter exporter = new JRXlsExporter(); 
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);//设置检测单元格格式，解决“excel以文本显示数字”
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
//			}
		} catch (Exception e) {
//			res.setContentType("text/html;charset=GB2312");   
//            PrintWriter   out   =   res.getWriter();   
            e.printStackTrace();   
		} finally {
			
			DBUtil.closeConnection(conn1);
		}
		return null;
		
	}
	
	public ActionForward query(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Fee fee = new Fee();
		FeeForm ff = (FeeForm) actionForm;
		ActionForward af = new ActionForward();
		//String forward = "/business/jasper/costquery.jsp";
		String forward = "/business/jasper/query.jsp";
		try {
				ClassHelper.copyProperties(ff, fee);
				if(fee.getFeemonth()<10){
					fee.setFeemonths("0"+fee.getFeemonth());
				}else {
					fee.setFeemonths(fee.getFeemonth()+"");
				}
				fee.setFileKey("fe3_select");
				String hql = queryEnterprise(fee);
				af = init(req, forward, hql);
				if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
					String msg = "这个月费用信息还没录入！";
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
	 * 修改费用信息
	 */
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		    Fee fee = new Fee();
		    FeeForm ff = (FeeForm) form;

			ClassHelper.copyProperties(ff, fee);
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Fee> mapRequest = new HashMap<String, Fee>();
			mapRequest.put("beo", fee);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.query(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// 客户费用信息
			
				ClassHelper.copyProperties(listci.get(0), ff);
				return mapping.findForward("savefee");
				
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
	
	}
	
	/**
	 * 保存修改信息
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveModified(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Fee fee = new Fee();
		FeeForm ff = (FeeForm) form;
		try {
			// 设定经办信息
			ClassHelper.copyProperties(ff, fee);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			fee.setFeeop(dto1.getBsc011());
			fee.setFeeopdata(DateUtil.getSystemCurrentTime());
			// 获取服务接口
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Fee> mapRequest = new HashMap<String, Fee>();
			mapRequest.put("beo", fee);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.update(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改客户费用信息成功!");
				//return mapping.findForward("backspace");
				return go2Page(req,mapping,"business");
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
	
	public ActionForward getLastMonth(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Fee fee = new Fee();
		try {
			fee.setFeegctid(req.getParameter("gctId"));
			Integer year = Integer.valueOf(req.getParameter("year"));
			Integer month = Integer.valueOf(req.getParameter("month"));
			if(month != 1)
				month = month -1;
			else{
				month = 12;
				year = year - 1;
			}
			fee.setFeeyear(year);
			fee.setFeemonth(month);
			
			BusinessFacade facade = (BusinessFacade) getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", fee);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.lastMonth(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询上月数据!");
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				System.out.println("LLLLLLLLL" + list.size());
				if (list.size() > 1)
					throw new Exception();
				ClassHelper.copyProperties(list.get(0), fee);
			
				res.setCharacterEncoding("GBK");
				res.getWriter().write("[{feedevice:" + fee.getFeedevice() +",feepension:" + fee.getFeepension() +",feeassets:" + fee.getFeeassets() +",feebuilt:" + fee.getFeebuilt()+",feeopen:" + fee.getFeeopen()+",feerent:"+ fee.getFeerent()+",feetrans:" + fee.getFeetrans()+"}]");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}
	public ActionForward toAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		Fee fee = new Fee();
		ActionForward af = new ActionForward();
		String forward = "/business/jasper/insertAmortize.jsp";
		try {
				fee.setFileKey("to_amotize");
				String hql = queryEnterprise(fee);
				af = init(req, forward, hql);
				
			} catch (AppException ex) {
				this.saveErrors(req, ex);
			} catch (Exception e) {
				this.saveErrors(req, e);
			}
			return af;
	}
	public ActionForward setAmortize(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		FeeForm feeForm = (FeeForm) form;
		SubmitDataMap data = new SubmitDataMap(req);
		String[] startList = data.getParameterValues("feestart"); //开始时间
		String[] endList = data.getParameterValues("feeend");// 结束时间
		String[] typeList = data.getParameterValues("amotype"); // 费用类型
		String[] moneyList = data.getParameterValues("money"); // 总额
		String[] noteList = data.getParameterValues("note");//备注
		String[] amoMonList = data.getParameterValues("amomoney");//月摊销额
		String[] longList = data.getParameterValues("feelong");//摊销月份
		try{ 
			int size = startList.length;
			List<Fee> feeList = new java.util.Vector<Fee>();
			for(int i = 0; i<size; i++){
				Fee fee = new Fee();
				fee.setFeestart(DateUtil.converToDate(startList[i]+"-01"));
				fee.setFeeend(DateUtil.converToDate(endList[i]+"-01"));
				fee.setAmotype(typeList[i]);
				fee.setMoney(Double.parseDouble(moneyList[i]));
				fee.setNote(noteList[i]);
				fee.setOperDate(DateUtil.getDate());
				fee.setAmomoney(Double.parseDouble(amoMonList[i]));
				fee.setFeelong(Integer.parseInt(longList[i]));
				feeList.add(fee);
			}
			ResponseEnvelop resEnv = null;
			BusinessFacade busFacade = (BusinessFacade)getService("BusinessFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("feeList", feeList);
			mapRequest.put("feegctid", feeForm.getFeegctid());
			mapRequest.put("feegctname", feeForm.getFeegctname());
			requestEnvelop.setBody(mapRequest);
			resEnv = busFacade.insertAmortize(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				
					super.saveSuccessfulMsg(req, "摊销费用保存成功");
				
					ActionForward actionForward = new ActionForward();
					actionForward.setPath("/business/FeeAction.do?method=toAmortize");
					actionForward.setRedirect(true);
					return go2Page(req, mapping, "business");
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
	public ActionForward queryamo(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		FeeForm feeform = (FeeForm)form;
		Fee fee = new Fee();
		ActionForward af = new ActionForward();
		String forward = "/business/jasper/queryamo.jsp";
		try {
				ClassHelper.copyProperties(feeform, fee);
				fee.setFileKey("amo_select");
				String hql = queryEnterprise(fee);
				af = init(req, forward, hql);
				if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
					String msg = "信息还没录入！";
					super.saveSuccessfulMsg(req, msg);
				}
			} catch (AppException ex) {
				this.saveErrors(req, ex);
			} catch (Exception e) {
				this.saveErrors(req, e);
			}
			return af;
		
	}
	//双耳率查询
			public ActionForward doubleratio(ActionMapping mapping,
					ActionForm actionForm, HttpServletRequest req,
					HttpServletResponse res) throws Exception {
				Fee fee = new Fee();
				FeeForm ff = (FeeForm) actionForm;
				ActionForward af = new ActionForward();
				String forward = "/business/jasper/doubleratio.jsp";
				try {
						ClassHelper.copyProperties(ff, fee);
						Connection conn = null;
						CallableStatement proc = null;
						Date sdate = DateUtil.converToDate(ff.getFeeyear()+"-"+ff.getStartMonth()+"-1");
						Date edate = DateUtil.converToDate(ff.getEndYear()+"-"+ff.getEndMonth()+"-1");
						conn = DBUtil.getConnection();
						proc = conn.prepareCall("{ call DOUBLERATIO2(?, ?, ?) }");
						proc.setDate(2, (java.sql.Date) sdate);
						proc.setDate(3, (java.sql.Date) edate);
						proc.registerOutParameter(1, Types.DOUBLE);
						proc.execute();
						double retValue = 100*proc.getDouble(1);	
						DecimalFormat df = new DecimalFormat("######0.00");   
						String result = df.format(retValue);
							String msg ="从"+sdate+"到"+edate+"的双耳率为 "+result+"%";
							super.saveSuccessfulMsg(req, msg);
						
					} catch (Exception e) {
						this.saveErrors(req, e);
					}
					return mapping.findForward("backspace");
			}
			//单耳单价
			public ActionForward price(ActionMapping mapping,
					ActionForm actionForm, HttpServletRequest req,
					HttpServletResponse res) throws Exception {
				Fee fee = new Fee();
				FeeForm ff = (FeeForm) actionForm;
				try {
						ClassHelper.copyProperties(ff, fee);
						Connection conn = null;
						CallableStatement proc = null;
						Date sdate = DateUtil.converToDate(ff.getFeeyear()+"-"+ff.getStartMonth()+"-1");
						Date edate = DateUtil.converToDate(ff.getEndYear()+"-"+ff.getEndMonth()+"-1");
						conn = DBUtil.getConnection();
						proc = conn.prepareCall("{ call PRICE(?, ?, ?) }");
						proc.setDate(2, (java.sql.Date) sdate);
						proc.setDate(3, (java.sql.Date) edate);
						proc.registerOutParameter(1, Types.DOUBLE);
						proc.execute();
						double retValue = proc.getDouble(1);	
						DecimalFormat df = new DecimalFormat("######0.00");   
						String result = df.format(retValue);
							String msg ="从"+sdate+"到"+edate+"的单耳单价为 "+result+"元";
							super.saveSuccessfulMsg(req, msg);
						
					} catch (Exception e) {
						this.saveErrors(req, e);
					}
					return mapping.findForward("backspace");
			}
			//删除摊销费用
			public ActionForward deleteAmortize(ActionMapping mapping, ActionForm form,
					HttpServletRequest req, HttpServletResponse res) throws Exception{
				Fee fee = new Fee();
				FeeForm ff = (FeeForm)form;
				try{
					ClassHelper.copyProperties(ff, fee);
					//获取服务接口
					BusinessFacade facade = (BusinessFacade)getService("BusinessFacade");
					RequestEnvelop requestEnvelop = new RequestEnvelop();
					EventResponse returnValue = new EventResponse();
					// 将Application对象放入HashMap
					HashMap<String, Object> mapRequest = new HashMap<String, Object>();
					mapRequest.put("beo", fee); 
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
}
