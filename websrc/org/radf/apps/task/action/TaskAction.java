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
 * �������
 */
public class TaskAction extends ActionLeafSupport {

	/**
	 * ��ѯ���񵥻�����Ϣ
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
			// ����Ƿ���ڣ�
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
	
	/**
	 * ����������
	 */
	public ActionForward configure(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		try {
			ClassHelper.copyProperties(taskForm, task);
			if (task.getTskpnlnm() == null || "".equals(task.getTskpnlnm())) {
				super.saveSuccessfulMsg(req, "����ȷ¼������ͺ�");
				return mapping.findForward("backspace");
			}
			if (task.getPnlproid() == null || "".equals(task.getPnlproid())) {
				super.saveSuccessfulMsg(req, "����ȷ¼�������루����Ʒ���룩");
				return mapping.findForward("backspace");
			}
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			task.setTskbilopr(dto.getBsc010());//�Ƶ�����Ϣ(�û�����)
			task.setTskbgndt(DateUtil.getDate());//�����´�����
			task.setTskdfdt(task.getTskdfdt());//Ҫ���������
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = taskFacade.configure(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// ����������񵥺�
				String tskid = (String) ((HashMap) resEnv.getBody())
						.get("tskid");
				FindLog.insertLog(req, tskid, "�������񵥺ųɹ���");

				super.saveSuccessfulMsg(req, "�������񵥳ɹ�,���񵥺�Ϊ" + tskid);

				/*Task task1 = (Task) ((HashMap) resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
				ClassHelper.copyProperties(task1, taskForm);*/
				// ��һ��ҳ�棬��д������ϸ��Ϣ
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
	 * ��幤����ͳ��
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

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			String tskmkopr = null;
			tskmkopr = tf.getTskmkopr();
			
				
			File reportFile = null;
			if ("".equals(tskmkopr) || tskmkopr == null) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\pnlwork.jasper"));
			} else {
			
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\pnlwork1.jasper"));
				parameters.put("pid", tskmkopr);// �����߱��
			}

			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("edata", tf.getEnd());// ������

			parameters.put("sdata", tf.getStart());// ������

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
			// ����GridLine
			// ��С������䵥Ԫ��
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
	 * ������Ա�����𱨱�
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
			//��ý��𵥼�
			TaskFacade facade = (TaskFacade) getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.getBonus(requestEnvelop);
			// �����ؽ��
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
			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			String tskmkopr = null;
			tskmkopr = tf.getTskmkopr();
			File reportFile = null;
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath("\\WEB-INF\\report\\repair_bonus.jasper"));
				String webRootPath = req.getSession().getServletContext()
						.getRealPath("/");
			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("edata", tf.getEnd());// ������

			parameters.put("sdata", tf.getStart());// ������
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
			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
			// ����GridLine
			// ��С������䵥Ԫ��
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
	 *�鿴�����е�����ͺŶ�Ӧ�������
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
	 * �����޸�����
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modify(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "�޸����񵥳ɹ�");
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
	 * �����޸���ʾ
	 */
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		String tskid = req.getParameter("tskid");
				
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		ActionForward af = new ActionForward();
		
		if(!tf.getTsksta().equals("0")){
			super.saveSuccessfulMsg(req, "���������״̬Ϊ\\n�����ȷ�ϣ��޷��޸ģ�");
			return mapping.findForward("backspace");
		}else{
			ClassHelper.copyProperties(tf, task);
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Task> map = new HashMap<String, Task>();
			map.put("beo", task);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(map);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			//�����ؽ��
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
	 * ɾ������
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		String tskid = req.getParameter("tskid");
		Task task = new Task();
		TaskForm tf = (TaskForm)form;
		try{
			if(!tf.getTsksta().equals("0")){
				super.saveSuccessfulMsg(req, "���������״̬�Ѳ���\\nδȷ��״̬���޷�ɾ����");
				return mapping.findForward("backspace");
			}
			ClassHelper.copyProperties(tf, task);
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			//��application����map������
			HashMap<String, Task> map = new HashMap<String, Task>();
			map.put("beo", task);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(map);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.delete(requestEnvelop);
			//�����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "ɾ�����񵥳ɹ�");
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
	 *���ȷ�������������
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
	 * ���ȷ��
	 */
	public ActionForward confirm(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] idList = data.getParameterValues("pdtid");//�������
		String[] numList = data.getParameterValues("tcfnum");//�������
		String[] batList = data.getParameterValues("tcfbth");//�������
		String[] ntList = data.getParameterValues("tcfnt");//��ע
		
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
			
			task.setTskadtopr(dto.getBsc010());//�������Ϣ(�û�����)
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = taskFacade.confirm(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "���ȷ�ϳɹ�");
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
	 * �������
	 */
	public ActionForward produce(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String tskid = req.getParameter("tskid");
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		try {
			ClassHelper.copyProperties(taskForm, task);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			
			task.setTskmkopr(dto.getBsc010());//������Ա��Ϣ(�û�����)
			task.setTskmkdt(DateUtil.getDate());//��������
			task.setTsksta("7");//7��ʾ���������
			
			TaskFacade facade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.produce(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "��忪ʼ����");
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
	 * ����������
	 *//*
	public ActionForward finish(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String tskid = req.getParameter("tskid");
		TaskForm taskForm = (TaskForm) form;
		Task task = new Task();
		try {
			ClassHelper.copyProperties(taskForm, task);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			
			task.setTskmkopr(dto.getBsc010());//������Ա��Ϣ(�û�����)
			task.setTskmkdt(DateUtil.getDate());//��������
			task.setTsksta("3");
			
			TaskFacade facade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.finish(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "����������");
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
	 * ��������������ҳ��
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
	 * ��������������Ϣ
	 */
	public ActionForward batchSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		//String[] pnlList = data.getParameterValues("tsdpnlid");//������
		String[] sidList = data.getParameterValues("tsdsid");//������
		String[] staList = data.getParameterValues("tsksta");//���״̬
		
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
			//task.setTskadtopr(dto.getBsc010());//�������Ϣ(�û�����)
			
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = taskFacade.batchSave(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "������Ϣ����ɹ�");
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
	 * �����������ҳ��
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
	 * �����ʼ�����ҳ��
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
				super.saveSuccessfulMsg(req, "�����������ʼ첢�Ҽ�����Ϊ�ϸ�");
				return mapping.findForward("backspace");
			}
			
			task.setFileKey("tsk04_008");
			
			TaskFacade facade = (TaskFacade) getService("TaskFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", task);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			
			// �����ؽ��
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
	 * ��忪ʼ�ʼ���Ϣ����
	 */
	public ActionForward saveQA(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		try{
			ClassHelper.copyProperties(tf, task);
			
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			task.setPnlqatskid(task.getTskid());//���񵥺�
			task.setPnlqaopra(dto.getBsc010());//����Ա1��Ʒ����Ա������
			task.setPnlqarsta(task.getQachka());//������1
			task.setPnlqadt(DateUtil.getDate());//��������
			
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.saveQA(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "�ʼ���Ϣ����ɹ�");
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
	 * �����������ʼ�����ҳ��
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
			// ��Application�������HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", task);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.find(requestEnvelop);
			
			// �����ؽ��
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
	 * �������ʼ���Ϣ����
	 */
	public ActionForward saveQAF(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res)throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		try{
			ClassHelper.copyProperties(tf, task);
			
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			task.setPnlqatskid(task.getTskid());//���񵥺�
			if(null != task.getQachkb()){
				task.setPnlqarstc(task.getQachkb());//������2
			}
			if(null != task.getQachkc()){
				task.setPnlqarstc(task.getQachkc());//������3
			}
				
			task.setPnlqadt(DateUtil.getDate());//��������
			
			
			
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.saveQAF(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "����ʼ���Ϣ����ɹ�");
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
	 * �˻�����ʼ�
	 */
	public ActionForward saveRecoilQA(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		TaskForm tf = (TaskForm)form;
		Task task = new Task();
		try{
			ClassHelper.copyProperties(tf, task);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			
			task.setPnlqaopra(dto.getBsc010());//����Ա��Ʒ����Ա������
			task.setPnlqadt(DateUtil.getDate());//��������
			task.setPnlqarsta(task.getQachka());//�ʼ���
			
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.saveRecoilQA(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "�˻��ʼ���Ϣ����ɹ�");
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
	 *���ʱ��������Ӧ�������������ŵ�
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
	 *���
	 */
	public ActionForward store(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//String tskid = (String)req.getParameter("tskid");
		TaskForm tf = (TaskForm) form;
		Task task = new Task();
		try{
			ClassHelper.copyProperties(tf, task);
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			task.setStooprcd(dto.getBsc010());//����Ա����
			task.setStogrcliid("1501000000");//��˾��������
			task.setStodate(DateUtil.getDate());//�������
			/*task.setStoprotype("3");//��Ʒ���ͣ�3Ϊ��壩*/
			task.setStoproname(task.getTskpnlnm());//�������
			task.setStoamount(task.getTskpnlqnt());//�������
			task.setStoamountun("��");//������λ
			task.setStoactype(1);//������1Ϊ��⣩
			task.setStodisc("0");//�Ƿ񱨷�
			task.setTsksta("6");//���״̬
			
			
			TaskFacade facade = (TaskFacade)getService("TaskFacade");
			
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Task> mapRequest = new HashMap<String, Task>();
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.store(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if(returnValue.isSucessFlag()){
				super.saveSuccessfulMsg(req, "�����Ϣ����ɹ�");
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
	 * �������
	 */
	public ActionForward batchStore(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pnlList = data.getParameterValues("tsdpnlid");//������
		String[] sidList = data.getParameterValues("tsdsid");//������
		
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
			
			task.setStooprcd(dto.getBsc010());//����Ա����
			task.setStogrcliid("1501000000");//��˾��������
			task.setStodate(DateUtil.getDate());//�������
			task.setStoproname(task.getTskpnlnm());//�������
			task.setStoamount(length);//�������
			task.setStoamountun("��");//������λ
			task.setStoactype(1);//������1Ϊ��⣩
			task.setStodisc("0");//�Ƿ񱨷�
			


			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			//mapRequest.put("length", length);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = taskFacade.batchStore(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "������ɹ���");
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
	 * ��ӡ����
	 */
	public ActionForward print(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		TaskForm tf = (TaskForm) actionForm;
		Task task = new Task();
		Connection conn = null;
		try {
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\taskReport.jasper"));

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();
			parameters.put("tskid", tf.getTskid());

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
	 * ���������ʼ���Ϣ
	 */
	public ActionForward batchQA(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pnlList = data.getParameterValues("tsdpnlid");//������
		String[] qaList = data.getParameterValues("qachka");//�ʼ���
		String[] ntList = data.getParameterValues("pnlqant");//��ע
		String[] sidList = data.getParameterValues("tsdsid");//������
		
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
			
			//task.setTskadtopr(dto.getBsc010());//�������Ϣ(�û�����)
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = taskFacade.batchQA(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "�ʼ���Ϣ����ɹ�");
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
	 * �����޸��ʼ���Ϣ
	 */
	public ActionForward batchQaUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pnlList = data.getParameterValues("tsdpnlid");//������
/*		String[] qaList = data.getParameterValues("qachka");//�ʼ���
		String[] ntList = data.getParameterValues("pnlqant");//��ע
*/		String[] sidList = data.getParameterValues("tsdsid");//������
		
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			
			List<Task> taskList = new Vector<Task>();
			for(int i = 0; i < chk.length; i++){
				Task task = new Task();
				task.setTsdpnlid(pnlList[Integer.parseInt(chk[i])-1]);//�����
//				task.setPnlqarsta(qaList[Integer.parseInt(chk[i])-1]);
//				task.setPnlqant(ntList[Integer.parseInt(chk[i])-1]);
				task.setTsdsid(sidList[Integer.parseInt(chk[i])-1]);//������
//				task.setPnlqaopra(dto.getBsc010());
//				task.setPnlqadt(DateUtil.getDate());
				
				taskList.add(task);
				
			}


			Task task = new Task();
			ClassHelper.copyProperties(taskForm, task);
			//LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			
			//task.setTskadtopr(dto.getBsc010());//�������Ϣ(�û�����)
			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = taskFacade.batchQaUpdate(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "�ʼ���Ϣ����ɹ�");
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
	 * ������������������
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
	 * �������
	 */
	public ActionForward batchStorere(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		TaskForm taskForm = (TaskForm) form;
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pnlList = data.getParameterValues("pnlqapnl");//������
		String[] ntList = data.getParameterValues("pnlqant");//������
		
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
			
			task.setStooprcd(dto.getBsc010());//����Ա����
			task.setStogrcliid("1501000000");//��˾��������
			task.setStodate(DateUtil.getDate());//�������
			//task.setStoproname(task.getTskpnlnm());//�������
			task.setStoproid(task.getPnlproid());
			task.setStoamount(length);//�������
			task.setStoamountun("��");//������λ
			task.setStoactype(1);//������1Ϊ��⣩
			task.setStodisc("0");//�Ƿ񱨷�
			


			
			TaskFacade taskFacade = (TaskFacade) getService("TaskFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("taskList", taskList);
			mapRequest.put("beo", task);
			//mapRequest.put("length", length);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = taskFacade.batchStorere(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "����������ɹ���");
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
