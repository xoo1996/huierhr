package org.radf.apps.task.action;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Task;
import org.radf.apps.customization.form.CustomizationForm;
import org.radf.apps.earmould.form.EarMouldForm;
import org.radf.apps.repair.facade.RepairFacade;
import org.radf.apps.task.facade.TaskFacade;
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

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 面板制作
 */
public class TaskAction extends ActionLeafSupport {

	/**
	 * 查询任务单基本信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String order = req.getParameter("order");
		String forward = null;
		String fileKey = null;
		ActionForward af = new ActionForward();
		TaskForm taskForm = (TaskForm)form;
		Task task = new Task();
		if("task".equals(order)){
			forward = "/task/taskquery.jsp";
			fileKey = "tsk01_001";
		}else if("confirm".equals(order)){
			forward = "/task/confirmquery.jsp";
			fileKey = "tsk02_001";
		}else if("produce".equals(order)){
			forward = "/task/producequery.jsp";
			fileKey = "tsk06_001";
		}else if("finish".equals(order)){
			forward = "/task/finishquery.jsp";
			fileKey = "tsk03_001";
		}else if("quality".equals(order)){
			forward = "/task/qualityquery.jsp";
			fileKey = "tsk04_001";
		}else if("in".equals(order)){
			forward = "/task/inquery.jsp";
			fileKey = "tsk05_001";
		}else if("inDetail".equals(order)){
			forward = "/task/inDetail.jsp";
			fileKey = "tsk05_002";
		}else if("qaf".equals(order)){
			forward = "/task/qafquery.jsp";
			fileKey = "tsk07_001";
		}else if ("qualityupdate".equals(order)) {
			forward = "/task/qualityupdatequery.jsp";
			fileKey = "tsk04_010";
			
		}
		try {
			ClassHelper.copyProperties(taskForm, task);
			task.setFileKey(fileKey);
			String hql = queryEnterprise(task);
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
	 * 面板零件配置
	 */
	public ActionForward configure(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		try {
			ClassHelper.copyProperties(taskForm, task);
			if (task.getTskpnlnm() == null || "".equals(task.getTskpnlnm())) {
				super.saveSuccessfulMsg(req, "请正确录入面板型号");
				return mapping.findForward("backspace");
			}
			if (task.getPnlproid() == null || "".equals(task.getPnlproid())) {
				super.saveSuccessfulMsg(req, "请正确录入面板代码（即商品代码）");
				return mapping.findForward("backspace");
			}
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			task.setTskbilopr(dto.getBsc010());//制单人信息(用户内码)
			task.setTskbgndt(DateUtil.getDate());//任务单下达日期
			task.setTskdfdt(task.getTskdfdt());//要求完成日期
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = taskFacade.configure(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// 获得新增任务单号
				String tskid = (String) ((HashMap) resEnv.getBody())
						.get("tskid");
				FindLog.insertLog(req, tskid, "新增任务单号成功！");

				super.saveSuccessfulMsg(req, "新增任务单成功,任务单号为" + tskid);

				/*Task task1 = (Task) ((HashMap) resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
				ClassHelper.copyProperties(task1, taskForm);*/
				// 下一个页面，填写订单详细信息
				return mapping.findForward("query");
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
	 * 面板工作量统计
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward worknum(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		TaskForm tf = (TaskForm) actionForm;
		Task task = new Task();
		Connection conn = null;
		try {

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String tskmkopr = null;
			tskmkopr = tf.getTskmkopr();
			
				
			File reportFile = null;
			if ("".equals(tskmkopr) || tskmkopr == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\pnlwork.jasper"));
			} else {
			
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\pnlwork1.jasper"));
				parameters.put("pid", tskmkopr);// 制作者编号
			}

			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", tf.getEnd());// 到日期

			parameters.put("sdata", tf.getStart());// 从日期

			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "work_report";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(),
					parameters, conn);

			JRXlsExporter exporter = new JRXlsExporter();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
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
	 * 生产部员工奖金报表
	 * @param actionMapping
	 * @param actionForm
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward bonus(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		TaskForm tf = (TaskForm) actionForm;
		Task task = new Task();
		Connection conn = null;
		try {
			//获得奖金单价
			TaskFacade facade = (TaskFacade) getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.getBonus(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			String earmodel = (String) ((HashMap) resEnv.getBody()).get("earmodel");
			String shell= (String) ((HashMap) resEnv.getBody()).get("shell");
			String shellsetupite= (String) ((HashMap) resEnv.getBody()).get("shellsetupite");
			String shellsetupcic= (String) ((HashMap) resEnv.getBody()).get("shellsetupcic") ;
			String repairmaster= (String) ((HashMap) resEnv.getBody()).get("repairmaster");
			String repairshell= (String) ((HashMap) resEnv.getBody()).get("repairshell");
			String shellsetup= (String) ((HashMap) resEnv.getBody()).get("shellsetup");
			String board= (String) ((HashMap) resEnv.getBody()).get("board");
			String ite = (String) ((HashMap) resEnv.getBody()).get("ite");
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// 报表编译之后生成的.jasper 文件的存放位置
			String tskmkopr = null;
			tskmkopr = tf.getTskmkopr();
			File reportFile = null;
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\repair_bonus.jasper"));
				String webRootPath = req.getSession().getServletContext()
						.getRealPath("/");
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("edata", tf.getEnd());// 到日期

			parameters.put("sdata", tf.getStart());// 从日期
			parameters.put("earmodel", new BigDecimal(earmodel));
			parameters.put("shell", new BigDecimal(shell));
			parameters.put("shellsetupite", new BigDecimal(shellsetupite));
			parameters.put("shellsetupcic", new BigDecimal(shellsetupcic));
			parameters.put("repairmaster", new BigDecimal(repairmaster));
			parameters.put("repairshell", new BigDecimal(repairshell));
			parameters.put("shellsetup", new BigDecimal(shellsetup));
			parameters.put("board",new BigDecimal(board));
			parameters.put("ite", new BigDecimal(ite));
			parameters.put("SUBREPORT_DIR", webRootPath + "WEB-INF\\report\\");
			// 连接到数据库
			conn = DBUtil.getConnection();

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());

			// res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			String reportclass = "bonus";
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("content-disposition", "attachment;filename="
					+ reportclass + ".xls");

			JasperPrint rptpnt = JasperFillManager.fillReport(reportFile.getPath(),
					parameters, conn);

			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rptpnt);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					ouputStream);
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
	 *查看任务单中的面板型号对应的零配件
	 */
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		String forward = "/task/checkDetail.jsp";
		try {
			ClassHelper.copyProperties(taskForm, task);
			task.setFileKey("tsk01_002");
			String hql = queryEnterprise(task);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	
	/**
	 * 保存修改任务单
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		String forward = "query";
		try{
			ClassHelper.copyProperties(tf, task);
			TaskFacade facade = (TaskFacade) getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modify(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改任务单成功");
				return mapping.findForward(forward);
	
			} else {
				String[] aa = StringUtil
						.getAsStringArray(returnValue.getMsg(), "|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	/**
	 * 
	 * 任务单修改显示
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		String tskid = req.getParameter("tskid");
				
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		
		if(!tf.getTsksta().equals("0")){
			super.saveSuccessfulMsg(req, "该任务单面板状态为\\n配件已确认，无法修改！");
			return mapping.findForward("backspace");
		}else{
			ClassHelper.copyProperties(tf, task);
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Task> map = new HashMap<String, Task>();
			map.put("beo", task);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(map);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			//处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				List listci = (ArrayList)((HashMap)resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(listci.get(0), tf);
				
			}else{
				String [] aa = StringUtil.getAsStringArray(returnValue.getMsg(), "|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		return mapping.findForward("alter");
	}
	
	/**
	 * 删除任务单
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		String tskid = req.getParameter("tskid");
		Task task = new Task();
		TaskForm tf = (TaskForm)form;
		try{
			if(!tf.getTsksta().equals("0")){
				super.saveSuccessfulMsg(req, "该任务单面板状态已不是\\n未确认状态，无法删除！");
				return mapping.findForward("backspace");
			}
			ClassHelper.copyProperties(tf, task);
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			//将application放入map对象中
			HashMap<String, Task> map = new HashMap<String, Task>();
			map.put("beo", task);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(map);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			//处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "删除任务单成功");
				return go2Page(req,mapping,"task");
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
	
	
	/**
	 *配件确认里的任务单详情
	 */
	public ActionForward confirmDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		String forward = "/task/confirmDetail.jsp";
		try {
			ClassHelper.copyProperties(taskForm, task);
			task.setFileKey("tsk01_002");
			String hql = queryEnterprise(task);
			af = super.init(req, forward, hql,0);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 配件确认
	 */
	public ActionForward confirm(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] idList = data.getParameterValues("pdtid");//零件代码
		String[] numList = data.getParameterValues("tcfnum");//零件货号
		String[] batList = data.getParameterValues("tcfbth");//零件批号
		String[] ntList = data.getParameterValues("tcfnt");//备注
		
		try {
			
			List<Task> taskList = new Vector<Task>();
			for(int i = 0; i < chk.length; i++){
				Task task = new Task();
				task.setTcfacy(idList[i]);
				task.setTcfnum(numList[i]);
				task.setTcfbth(batList[i]);
				task.setTcfnt(ntList[i]);
				taskList.add(task);
				
			}


			Task task = new Task();
			ClassHelper.copyProperties(taskForm, task);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			
			task.setTskadtopr(dto.getBsc010());//审核人信息(用户内码)
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = taskFacade.confirm(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "配件确认成功");
				return mapping.findForward("query1");
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
	 * 生产面板
	 */
	public ActionForward produce(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String tskid = req.getParameter("tskid");
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		try {
			ClassHelper.copyProperties(taskForm, task);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			
			task.setTskmkopr(dto.getBsc010());//生产人员信息(用户内码)
			task.setTskmkdt(DateUtil.getDate());//生产日期
			task.setTsksta("7");//7表示面板制作中
			
			TaskFacade facade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.produce(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "面板开始生产");
				return mapping.findForward("query2");
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
	 * 面板制作完成
	 *//*
	public ActionForward finish(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String tskid = req.getParameter("tskid");
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		try {
			ClassHelper.copyProperties(taskForm, task);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			
			task.setTskmkopr(dto.getBsc010());//生产人员信息(用户内码)
			task.setTskmkdt(DateUtil.getDate());//生产日期
			task.setTsksta("3");
			
			TaskFacade facade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.finish(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "面板生产完成");
				return mapping.findForward("query3");
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
	}	*/
	/**
	 * 进入面板完成详情页面
	 */
	public ActionForward finish(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		TaskForm taskForm = (TaskForm)form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		String forward = "/task/finishDetail.jsp";
		try {
			ClassHelper.copyProperties(taskForm, task);
			
			task.setFileKey("tsk03_002");
			
			String hql = queryEnterprise(task);
			af = super.init(req, forward, hql);
		}catch(AppException ae){
			this.saveErrors(req, ae);
		}catch(Exception e){
			this.saveErrors(req, e);
		}
		return af;
	}
	

	/**
	 * 批量保存生产信息
	 */
	public ActionForward batchSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		//String[] pnlList = data.getParameterValues("tsdpnlid");//面板编码
		String[] sidList = data.getParameterValues("tsdsid");//面板序号
		String[] staList = data.getParameterValues("tsksta");//面板状态
		
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			List<Task> taskList = new Vector<Task>();
			for(int i = 0; i < chk.length; i++){
				Task task = new Task();

				task.setTsdsid(sidList[Integer.parseInt(chk[i])-1]);
				task.setTsdsta(staList[Integer.parseInt(chk[i])-1]);
				taskList.add(task);
				
			}


			Task task = new Task();
			ClassHelper.copyProperties(taskForm, task);
			//task.setTskadtopr(dto.getBsc010());//审核人信息(用户内码)
			
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = taskFacade.batchSave(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "生产信息保存成功");
				return go2Page(req,mapping,"task");
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
	 * 跳入面板详情页面
	 */
	public ActionForward quality(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		String forward = null;
		String sql = null;
		String tp = req.getParameter("tp");
		try{
			if(null != tp && "check".equals(tp)){
				sql = "tsk01_003";
				forward = "/task/panelDetail1.jsp";
			}else if (null!=tp&&"update".equals(tp)) {
				sql = "tsk04_011";
				forward = "/task/panelDetailUpdate.jsp";
			}
			else{
				sql = "tsk04_002";
				forward = "/task/panelDetail.jsp";
			}
			ClassHelper.copyProperties(tf, task);
			
			
			task.setFileKey(sql);
			
						
			String hql = queryEnterprise(task);
			af = super.init(req, forward, hql);
		}catch(AppException ae){
			this.saveErrors(req, ae);
		}catch(Exception e){
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 跳入质检详情页面
	 */
	public ActionForward qualitya(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		//String tp = req.getParameter("tp");
		String forward = "/task/qualityDetail.jsp";
		try{
			ClassHelper.copyProperties(tf, task);
			
			if(null != task.getTsksta() && "5".equals(task.getTsksta()) && null != task.getQachka() && "yes".equals(task.getQachka())){
				super.saveSuccessfulMsg(req, "此面板已完成质检并且检验结果为合格！");
				return mapping.findForward("backspace");
			}
			
			task.setFileKey("tsk04_008");
			
			TaskFacade facade = (TaskFacade) getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", task);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), tf);
				tf.setTsdsid(task.getTsdsid());
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}

		} catch (AppException ae) {
			this.saveErrors(req, ae);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return mapping.findForward("quality");
	}
	
	
	/**
	 * 面板开始质检信息保存
	 */
	public ActionForward saveQA(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		try{
			ClassHelper.copyProperties(tf, task);
			
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			task.setPnlqatskid(task.getTskid());//任务单号
			task.setPnlqaopra(dto.getBsc010());//检验员1（品管人员）内码
			task.setPnlqarsta(task.getQachka());//检验结果1
			task.setPnlqadt(DateUtil.getDate());//检验日期
			
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveQA(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "质检信息保存成功");
				return go2Page(req,mapping,"task");
				//return mapping.findForward("query4");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	
	/**
	 * 跳入面板完成质检详情页面
	 */
	public ActionForward qaf(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		String forward = "/task/qafDetail.jsp";
		try{
			ClassHelper.copyProperties(tf, task);
			//task.setTskid("-1");
			/*task.setFileKey("tsk07_002");
			String hql = queryEnterprise(task);
			af = super.init(req, forward, hql);*/
			
			TaskFacade facade = (TaskFacade) getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", task);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");
				ClassHelper.copyProperties(listci.get(0), tf);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
			
		}catch(AppException ae){
			this.saveErrors(req, ae);
		}catch(Exception e){
			this.saveErrors(req, e);
		}
		return mapping.findForward("qaf");
	}
	
	
	/**
	 * 面板完成质检信息保存
	 */
	public ActionForward saveQAF(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		try{
			ClassHelper.copyProperties(tf, task);
			
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			task.setPnlqatskid(task.getTskid());//任务单号
			if(null != task.getQachkb()){
				task.setPnlqarstc(task.getQachkb());//检验结果2
			}
			if(null != task.getQachkc()){
				task.setPnlqarstc(task.getQachkc());//检验结果3
			}
				
			task.setPnlqadt(DateUtil.getDate());//检验日期
			
			
			
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveQAF(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "完成质检信息保存成功");
				return mapping.findForward("query5");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	/**
	 * 退机面板质检
	 */
	public ActionForward saveRecoilQA(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		try{
			ClassHelper.copyProperties(tf, task);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			
			task.setPnlqaopra(dto.getBsc010());//检验员（品管人员）内码
			task.setPnlqadt(DateUtil.getDate());//检验日期
			task.setPnlqarsta(task.getQachka());//质检结果
			
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.saveRecoilQA(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "退机质检信息保存成功");
				return mapping.findForward("query5");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	/**
	 *入库时任务单所对应的详情包括面板编号等
	 */
	public ActionForward inDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		String forward = "/task/inDetail.jsp";
		try {
			ClassHelper.copyProperties(taskForm, task);
			task.setFileKey("tsk05_002");
			String hql = queryEnterprise(task);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 *入库
	 */
	public ActionForward store(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//String tskid = (String)req.getParameter("tskid");
		TaskForm tf = (TaskForm) form;
		Task task = new Task();
		try{
			ClassHelper.copyProperties(tf, task);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			task.setStooprcd(dto.getBsc010());//操作员内码
			task.setStogrcliid("1501000000");//公司机构代码
			task.setStodate(DateUtil.getDate());//入库日期
			/*task.setStoprotype("3");//商品类型（3为面板）*/
			task.setStoproname(task.getTskpnlnm());//面板名称
			task.setStoamount(task.getTskpnlqnt());//面板数量
			task.setStoamountun("块");//数量单位
			task.setStoactype(1);//动作（1为入库）
			task.setStodisc("0");//是否报废
			task.setTsksta("6");//面板状态
			
			
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.store(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "入库信息保存成功");
				return mapping.findForward("query6");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	/**
	 * 批量入库
	 */
	public ActionForward batchStore(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pnlList = data.getParameterValues("tsdpnlid");//面板编码
		String[] sidList = data.getParameterValues("tsdsid");//面板序号
		
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			List<Task> taskList = new Vector<Task>();
			int length = chk.length;
			for(int i = 0; i < length; i++){
				Task task = new Task();

				task.setTsdpnlid(pnlList[Integer.parseInt(chk[i])-1]);
				task.setTsdsid(sidList[Integer.parseInt(chk[i])-1]);
				taskList.add(task);
				
			}
			Task task = new Task();
			ClassHelper.copyProperties(taskForm, task);
			
			task.setStooprcd(dto.getBsc010());//操作员内码
			task.setStogrcliid("1501000000");//公司机构代码
			task.setStodate(DateUtil.getDate());//入库日期
			task.setStoproname(task.getTskpnlnm());//面板名称
			task.setStoamount(length);//面板数量
			task.setStoamountun("块");//数量单位
			task.setStoactype(1);//动作（1为入库）
			task.setStodisc("0");//是否报废
			


			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			//mapRequest.put("length", length);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = taskFacade.batchStore(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "面板入库成功！");
				return go2Page(req,mapping,"task");
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
	 * 打印任务单
	 */
	public ActionForward print(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		TaskForm tf = (TaskForm) actionForm;
		Task task = new Task();
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\taskReport.jasper"));

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型

			// 连接到数据库
			conn = DBUtil.getConnection();
			parameters.put("tskid", tf.getTskid());

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			
			res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
			PdfReader reader = new PdfReader(bytes);
			StringBuffer script = new StringBuffer();
			script
					.append(
							"this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
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
	 * 批量保存质检信息
	 */
	public ActionForward batchQA(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pnlList = data.getParameterValues("tsdpnlid");//面板编码
		String[] qaList = data.getParameterValues("qachka");//质检结果
		String[] ntList = data.getParameterValues("pnlqant");//备注
		String[] sidList = data.getParameterValues("tsdsid");//面板序号
		
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			List<Task> taskList = new Vector<Task>();
			for(int i = 0; i < chk.length; i++){
				Task task = new Task();

				task.setTsdpnlid(pnlList[Integer.parseInt(chk[i])-1]);
				task.setPnlqarsta(qaList[Integer.parseInt(chk[i])-1]);
				task.setPnlqant(ntList[Integer.parseInt(chk[i])-1]);
				task.setTsdsid(sidList[Integer.parseInt(chk[i])-1]);
				task.setPnlqaopra(dto.getBsc010());
				task.setPnlqadt(DateUtil.getDate());
				
				taskList.add(task);
				
			}


			Task task = new Task();
			ClassHelper.copyProperties(taskForm, task);
			//LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			
			//task.setTskadtopr(dto.getBsc010());//审核人信息(用户内码)
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = taskFacade.batchQA(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "质检信息保存成功");
				return go2Page(req,mapping,"task");
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
	 * 批量修改质检信息
	 */
	public ActionForward batchQaUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pnlList = data.getParameterValues("tsdpnlid");//面板编码
/*		String[] qaList = data.getParameterValues("qachka");//质检结果
		String[] ntList = data.getParameterValues("pnlqant");//备注
*/		String[] sidList = data.getParameterValues("tsdsid");//面板序号
		
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			List<Task> taskList = new Vector<Task>();
			for(int i = 0; i < chk.length; i++){
				Task task = new Task();
				task.setTsdpnlid(pnlList[Integer.parseInt(chk[i])-1]);//面板编号
//				task.setPnlqarsta(qaList[Integer.parseInt(chk[i])-1]);
//				task.setPnlqant(ntList[Integer.parseInt(chk[i])-1]);
				task.setTsdsid(sidList[Integer.parseInt(chk[i])-1]);//面板序号
//				task.setPnlqaopra(dto.getBsc010());
//				task.setPnlqadt(DateUtil.getDate());
				
				taskList.add(task);
				
			}


			Task task = new Task();
			ClassHelper.copyProperties(taskForm, task);
			//LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			
			//task.setTskadtopr(dto.getBsc010());//审核人信息(用户内码)
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = taskFacade.batchQaUpdate(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "质检信息保存成功");
				return go2Page(req,mapping,"task");
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
	 * 翻新面板输入界面跳出
	 */
	public ActionForward inDetailre(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//InStoreForm storageForm = (InStoreForm ) form;
//		Store sto=new Store();
		Task task=new Task();
		ActionForward af = new ActionForward();
		String forward = "/task/panelre.jsp";
		try {
			//ClassHelper.copyProperties(storageForm, sto);
			task.setPnlid("-1");
			task.setFileKey("tsk05_005");
//			sto.setStoid(-1);
//			sto.setFileKey("ord11_022");
			String hql = queryEnterprise(task);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 批量入库
	 */
	public ActionForward batchStorere(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pnlList = data.getParameterValues("pnlqapnl");//面板编码
		String[] ntList = data.getParameterValues("pnlqant");//面板序号
		
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			List<Task> taskList = new Vector<Task>();
			
			int length=pnlList.length;
			//int length = chk.length;
			for(int i = 0; i < length; i++){
				Task task = new Task();

				task.setPnlqapnl(pnlList[i]);
				task.setPnlqant(ntList[i]);
				taskList.add(task);
				
			}
			Task task = new Task();
			ClassHelper.copyProperties(taskForm, task);
			
			task.setStooprcd(dto.getBsc010());//操作员内码
			task.setStogrcliid("1501000000");//公司机构代码
			task.setStodate(DateUtil.getDate());//入库日期
			//task.setStoproname(task.getTskpnlnm());//面板名称
			task.setStoproid(task.getPnlproid());
			task.setStoamount(length);//面板数量
			task.setStoamountun("块");//数量单位
			task.setStoactype(1);//动作（1为入库）
			task.setStodisc("0");//是否报废
			


			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			//mapRequest.put("length", length);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = taskFacade.batchStorere(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "翻新面板入库成功！");
				return go2Page(req,mapping,"task");
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
