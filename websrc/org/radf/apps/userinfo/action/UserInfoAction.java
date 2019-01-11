package org.radf.apps.userinfo.action;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.radf.apps.business.facade.BusinessFacade;
import org.radf.apps.business.form.FeeForm;
import org.radf.apps.charge.facade.ChargeFacade;
import org.radf.apps.charge.form.ChargeForm;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.client.single.form.SingleClientForm;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Fee;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.commons.entity.Tblitsmci;
import org.radf.apps.commons.entity.UserEducate;
import org.radf.apps.commons.entity.UserFamily;
import org.radf.apps.commons.entity.UserInfo;
import org.radf.apps.commons.entity.UserSalary;
import org.radf.apps.process.facade.ApaFacade;
import org.radf.apps.process.form.ApaForm;
import org.radf.apps.userinfo.facade.UserFamilyFacade;
import org.radf.apps.userinfo.facade.UserInfoFacade;
import org.radf.apps.userinfo.facade.UserSalaryFacade;
import org.radf.apps.userinfo.form.AccUserForm;
import org.radf.apps.userinfo.form.ProcessResForm;
import org.radf.apps.userinfo.form.UserEducateForm;
import org.radf.apps.userinfo.form.UserFamilyForm;
import org.radf.apps.userinfo.form.UserInfoForm;
import org.radf.apps.userinfo.form.UserSupForm;
import org.radf.apps.userinfo.form.UserTrainForm;
import org.radf.apps.userinfo.form.UserWorkForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FileUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.global.GlobalNames;

public class UserInfoAction extends ActionLeafSupport {

	/**
	 * 添加人员信息
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;
		Connection con = DBUtil.getConnection();
		UserInfo userInfo = new UserInfo();
		List result8 = (Vector) DBUtil.querySQL(con,
				"select SEQ_USER.NEXTVAL from dual");
		BigDecimal aaa = (BigDecimal) ((HashMap) result8.get(0))
				.get("1");
		String employid = "HR"+aaa.toString();
		Date icdbt = userInfoForm.getUserjoindate();
		Calendar c = Calendar.getInstance();
		c.setTime(icdbt);
		int year1 = c.get(Calendar.YEAR);
		c.setTime(DateUtil.getSystemCurrentTime());
		int year2 = c.get(Calendar.YEAR);
		int age = year2 - year1;
		String workold=String.valueOf(age);
		userInfoForm.setUserworkold(workold);
		userInfoForm.setUseremployid(employid);
		String danganid=userInfoForm.getDanganid();
		List result7 = (Vector) DBUtil.querySQL(con,"select danganid from tbluser where danganid='"+ danganid + "'");
		String bsc008=userInfoForm.getUserdepartmentid();
		List result9 = (Vector) DBUtil.querySQL(con,"select bsc008 from sc04 where bsc008='"+ bsc008 + "'");
		if (userInfoForm.getUseremployid() == null
				|| userInfoForm.getUseremployid().equals("")) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
			return mapping.findForward("backspace");
		} else if(danganid!=null&&result7.size()>0){
			saveSuccessfulMsg(req, "档案id重复！");
			return mapping.findForward("backspace");
		} else if(bsc008!=null&&result9.size()==0){
			saveSuccessfulMsg(req, "未找到相关机构！");
			return mapping.findForward("backspace");
		} else {
			UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
			ClassHelper.copyProperties(userInfoForm, userInfo);

			// 添加基本信息
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap<String, UserInfo> mapRequest = new HashMap<String, UserInfo>();

			mapRequest.put("userInfo", userInfo);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.addUser(requestEnvelop);
			returnValue = processRevt(resEnv);

			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功");
				ActionForward actionForward = new ActionForward();
				actionForward.setPath("");
				actionForward.setRedirect(true);
				req.getSession().setAttribute("useremployid", userInfoForm.getUseremployid());
				req.getSession().setAttribute("username", userInfoForm.getUsername());
				return mapping.findForward("addMore");
				/* return go2Page(req, mapping, "business"); */
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}
	}
	
	/*
	 *解析导入的Excel表，讲新员工信息存入数据库 
	 */
	public ActionForward addUserByExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		String[] userInfoArray  = {"userdepartmentid","positionname","writedate",
				"danganid","useremployid","useremployeestatus",
				"username","usergender","userbirthday","userhometown",
				"usernation","userpolitical","useridno","userismarry",
				"userchildno","userheightestedu","usergraduatedate",
				"usermajor","usergraduatefrom","userrankname","userworktime",
				"userjoindate","userforeignlanglevel","usercomputerlevel",
				"userhousehold","userhukoutype","useremail","userresidence",
				"usermobilephone","usertelephone",
				"emepername","emeperrelation","emeperphone","emeperaddress",
				"investigate1","investigate2","investigate3","investigate4",
				"investigate5","contype","condateend"};
		try {
			Workbook workbook = Workbook.getWorkbook(userInfoForm.getFile(0).getInputStream());
			Sheet sheet = workbook.getSheet(0);
			int count = 0;
			EventResponse returnValue = null;
			Connection con = DBUtil.getConnection();
			
			for (int j = 1; j < sheet.getRows(); j++) {
				Map<String,String> rowData = new HashMap<String,String>();
				UserInfo userInfo = new UserInfo();
				for (int k = 0; k < sheet.getColumns(); k++) {
					Cell cell = sheet.getCell(k,j);
					rowData.put(userInfoArray[k],cell.getContents());
					
					System.out.print(cell.getContents() + "  ");
				}
				///////
				ClassHelper.copyProperties(rowData, userInfo);	
				List result8 = (Vector) DBUtil.querySQL(con,
						"select SEQ_USER.NEXTVAL from dual");
				BigDecimal aaa = (BigDecimal) ((HashMap) result8.get(0))
						.get("1");
				userInfo.setUseremployid("HR"+aaa.toString());
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				returnValue = new EventResponse();
				HashMap<String, UserInfo> mapRequest = new HashMap<String, UserInfo>();

				mapRequest.put("userInfo", userInfo);
				requestEnvelop.setBody(mapRequest);
				ResponseEnvelop resEnv = facade.addUser(requestEnvelop);
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					count++;
				}
			}
			if (count == sheet.getRows()-1) {
				super.saveSuccessfulMsg(req, "保存成功");
			}
		} catch (Exception e) {
			super.saveSuccessfulMsg(req, "系统出错！" + e.getMessage());
		}
		return mapping.findForward("toAddUserByExcel");
	}
	
	/*
	 *解析导入的Excel表，讲新员工信息存入数据库 
	 */
	public ActionForward addEducateByExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		String[] userInfoArray  = {"useremployid","userschoolname","userschoolstartdate",
				"userschoolenddate","userschoolmajor","userschooldegree",
				"userschooltype"};
		try {
			Workbook workbook = Workbook.getWorkbook(userInfoForm.getFile(0).getInputStream());
			Sheet sheet = workbook.getSheet(0);
			int count = 0;
			EventResponse returnValue = null;
			List<UserEducateForm> eduList = new ArrayList<UserEducateForm>();
			for (int j = 1; j < sheet.getRows(); j++) {
				Map<String,String> rowData = new HashMap<String,String>();
				UserEducateForm userEducate = new UserEducateForm();
				for (int k = 0; k < sheet.getColumns(); k++) {
					Cell cell = sheet.getCell(k,j);
					rowData.put(userInfoArray[k],cell.getContents());
					System.out.print(cell.getContents() + "  ");
				}
				ClassHelper.copyProperties(rowData, userEducate);
				if(userEducate.getUseremployid() == null || userEducate.getUseremployid().equals("")){
					throw new Exception("存在工号为空的记录！");
				}
				eduList.add(userEducate);
			}
				///////
				
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			returnValue = new EventResponse();
			HashMap<String, List<UserEducateForm>> mapRequest = new HashMap<String, List<UserEducateForm>>();
			mapRequest.put("eduList", eduList);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.addUserEducate(requestEnvelop);
			
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功");
				ActionForward actionForward = new ActionForward();
				actionForward.setPath("");
				actionForward.setRedirect(true);
			}
		} catch (Exception e) {
			super.saveSuccessfulMsg(req, "系统出错！" + e.getMessage());
			e.printStackTrace();
		}
		return mapping.findForward("toAddUserByExcel");
	}
	
	
	
	/*
	 *解析导入的Excel表，讲新员工信息存入数据库 
	 */
	public ActionForward addFamilyByExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		String[] userInfoArray  = {"useremployid","familycall","familyname",
				"familyworkplace","familyphoneno","familybirthday"};
		
		try {
			Workbook workbook = Workbook.getWorkbook(userInfoForm.getFile(0).getInputStream());
			Sheet sheet = workbook.getSheet(0);
			EventResponse returnValue = null;
			List<UserFamilyForm> famList = new ArrayList<UserFamilyForm>();
			for (int j = 1; j < sheet.getRows(); j++) {
				Map<String,String> rowData = new HashMap<String,String>();
				UserFamilyForm userFamily = new UserFamilyForm();
				for (int k = 0; k < sheet.getColumns(); k++) {
					Cell cell = sheet.getCell(k,j);
					rowData.put(userInfoArray[k],cell.getContents());
					System.out.print(cell.getContents() + "  ");
				}
				ClassHelper.copyProperties(rowData, userFamily);
				if(userFamily.getUseremployid() == null || userFamily.getUseremployid().equals("")){
					throw new Exception("存在工号为空的记录！");
				}
				famList.add(userFamily);
			}
				///////
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			returnValue = new EventResponse();
			HashMap<String, List<UserFamilyForm>> mapRequest = new HashMap<String, List<UserFamilyForm>>();
			mapRequest.put("famList", famList);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.saveUserFamily(requestEnvelop);
			
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功");
				ActionForward actionForward = new ActionForward();
				actionForward.setPath("");
				actionForward.setRedirect(true);
			}
		} catch (Exception e) {
			super.saveSuccessfulMsg(req, "系统出错" + e.getMessage());
		}
		return mapping.findForward("toAddUserByExcel");
	}
	
	
	
	/*
	 *解析导入的Excel表，讲新员工信息存入数据库 
	 */
	public ActionForward addTrainByExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		String[] userInfoArray  = {"useremployid","trainstartdate","trainenddate",
				"trainplace","traincontent","traincertificate"};
		
		try {
			Workbook workbook = Workbook.getWorkbook(userInfoForm.getFile(0).getInputStream());
			Sheet sheet = workbook.getSheet(0);
			EventResponse returnValue = null;
			List<UserTrainForm> trainList = new ArrayList<UserTrainForm>();
			for (int j = 1; j < sheet.getRows(); j++) {
				Map<String,String> rowData = new HashMap<String,String>();
				UserTrainForm train = new UserTrainForm();
				for (int k = 0; k < sheet.getColumns(); k++) {
					Cell cell = sheet.getCell(k,j);
					rowData.put(userInfoArray[k],cell.getContents());
					System.out.print(cell.getContents() + "  ");
				}
				ClassHelper.copyProperties(rowData, train);
				if(train.getUseremployid() == null || train.getUseremployid().equals("")){
					throw new Exception("存在工号为空的记录！");
				}
				trainList.add(train);
			}
				///////
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			returnValue = new EventResponse();
			HashMap<String, List<UserTrainForm>> mapRequest = new HashMap<String, List<UserTrainForm>>();
			mapRequest.put("trainList", trainList);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.saveUserTrain(requestEnvelop);
			
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功");
			}
		} catch (Exception e) {
			super.saveSuccessfulMsg(req, "系统出错!" + e.getMessage());
		}
		return mapping.findForward("toAddUserByExcel");
	}
	
	/*
	 *解析导入的Excel表，讲新员工信息存入数据库 
	 */
	public ActionForward addWorkByExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		String[] userInfoArray  = {"employid","workstartdate","workenddate",
				"workplace","workposition","worksalary","workleavereason","workprove"};
		
		try {
			Workbook workbook = Workbook.getWorkbook(userInfoForm.getFile(0).getInputStream());
			Sheet sheet = workbook.getSheet(0);
			EventResponse returnValue = null;
			List<UserWorkForm> workList = new ArrayList<UserWorkForm>();
			for (int j = 1; j < sheet.getRows(); j++) {
				Map<String,String> rowData = new HashMap<String,String>();
				UserWorkForm work = new UserWorkForm();
				for (int k = 0; k < sheet.getColumns(); k++) {
					Cell cell = sheet.getCell(k,j);
					rowData.put(userInfoArray[k],cell.getContents());
					System.out.print(cell.getContents() + "  ");
				}
				ClassHelper.copyProperties(rowData, work);
				if(work.getEmployid() == null || work.getEmployid().equals("")){
					
					throw new Exception("存在工号为空的记录！");
				}
				workList.add(work);
			}
				///////
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			returnValue = new EventResponse();
			HashMap<String, List<UserWorkForm>> mapRequest = new HashMap<String, List<UserWorkForm>>();
			mapRequest.put("workList", workList);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.addUserWork(requestEnvelop);
			
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功");
			}
		} catch (Exception e) {
			super.saveSuccessfulMsg(req, "系统出错！" + e.getMessage());
		}
		return mapping.findForward("toAddUserByExcel");
	}
	/*
	 *解析导入的Excel表，上下级信息存入数据库 
	 */
	public ActionForward addSupByExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		String[] userInfoArray  = {"superiorid","juniorid"};
		
		try {
			Workbook workbook = Workbook.getWorkbook(userInfoForm.getFile(0).getInputStream());
			Sheet sheet = workbook.getSheet(0);
			EventResponse returnValue = null;
			List<UserSupForm> workList = new ArrayList<UserSupForm>();
			for (int j = 1; j < sheet.getRows(); j++) {
				Map<String,String> rowData = new HashMap<String,String>();
				UserSupForm work = new UserSupForm();//
				for (int k = 0; k < sheet.getColumns(); k++) {
					Cell cell = sheet.getCell(k,j);
					rowData.put(userInfoArray[k],cell.getContents());
					System.out.print(cell.getContents() + "  ");
				}
				ClassHelper.copyProperties(rowData, work);
				if(work.getSuperiorid() == null || work.getSuperiorid().equals("")){
					
					throw new Exception("存在上级id为空的记录！");
				}
				workList.add(work);
			}
				///////
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			returnValue = new EventResponse();
			HashMap<String, List<UserSupForm>> mapRequest = new HashMap<String, List<UserSupForm>>();
			mapRequest.put("workList", workList);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.addUserSup(requestEnvelop);
			
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功");
			}
		} catch (Exception e) {
			super.saveSuccessfulMsg(req, "系统出错！" + e.getMessage());
		}
		return mapping.findForward("toAddUserByExcel");
	}
	/*
	 *解析导入的Excel表，账号-员工id对应信息存入数据库 
	 */
	public ActionForward addAccByExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		String[] userInfoArray  = {"account","useremployid"};
		
		try {
			Workbook workbook = Workbook.getWorkbook(userInfoForm.getFile(0).getInputStream());
			Sheet sheet = workbook.getSheet(0);
			EventResponse returnValue = null;
			List<AccUserForm> workList = new ArrayList<AccUserForm>();
			for (int j = 1; j < sheet.getRows(); j++) {
				Map<String,String> rowData = new HashMap<String,String>();
				AccUserForm work = new AccUserForm();//
				for (int k = 0; k < sheet.getColumns(); k++) {
					Cell cell = sheet.getCell(k,j);
					rowData.put(userInfoArray[k],cell.getContents());
					System.out.print(cell.getContents() + "  ");
				}
				ClassHelper.copyProperties(rowData, work);
				if(work.getAccount() == null || work.getAccount().equals("")){
					
					throw new Exception("存在账号为空的记录！");
				}
				workList.add(work);
			}
				///////
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			returnValue = new EventResponse();
			HashMap<String, List<AccUserForm>> mapRequest = new HashMap<String, List<AccUserForm>>();
			mapRequest.put("workList", workList);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.addAccUser(requestEnvelop);
			
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功");
			}
		} catch (Exception e) {
			super.saveSuccessfulMsg(req, "系统出错！" + e.getMessage());
		}
		return mapping.findForward("toAddUserByExcel");
	}
	/**
	 * 存储诸如教育信息，培训信息等
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward addMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String employid = req.getParameter("useremployid");
		if (employid == null || employid.equals("")) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
			return mapping.findForward("backspace");
		} else {
			UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		
		if(!verifyEduAndWork(req)){
			saveSuccessfulMsg(req, "教育经历和工作经历至少完整填写一行");
			return mapping.findForward("backspace");
		}

			// 添加家庭信息
			RequestEnvelop requestEnvelop1 = new RequestEnvelop();
			EventResponse returnValue1 = new EventResponse();
			returnValue1.setSucessFlag(true);
			HashMap<String, Object> mapRequest1 = new HashMap<String, Object>();
			List<UserFamilyForm> famList = getFamilyList(req);
			if (!famList.isEmpty()) {
				mapRequest1.put("useremployid", employid);
				mapRequest1.put("famList", famList);
				requestEnvelop1.setBody(mapRequest1);
				ResponseEnvelop resEnv1 = facade
						.saveUserFamily(requestEnvelop1);
				returnValue1 = processRevt(resEnv1);
			}
			req.setAttribute("familylist", famList);

			// 添加教育信息
			RequestEnvelop requestEnvelop2 = new RequestEnvelop();
			EventResponse returnValue2 = new EventResponse();
			returnValue2.setSucessFlag(true);
			HashMap<String, Object> mapRequest2 = new HashMap<String, Object>();
			List<UserEducateForm> eduList = getEducateList(req);
			if (!eduList.isEmpty()) {
				mapRequest2.put("useremployid", employid);
				mapRequest2.put("eduList", eduList);
				requestEnvelop2.setBody(mapRequest2);
				ResponseEnvelop resEnv2 = facade
						.addUserEducate(requestEnvelop2);
				returnValue2 = processRevt(resEnv2);
			}
			req.setAttribute("educatelist", eduList);

			// 添加教育信息
			RequestEnvelop requestEnvelop3 = new RequestEnvelop();
			EventResponse returnValue3 = new EventResponse();
			returnValue3.setSucessFlag(true);
			HashMap<String, Object> mapRequest3 = new HashMap<String, Object>();
			List<UserWorkForm> workList = getWorkList(req);
			if (!workList.isEmpty()) {
				mapRequest3.put("useremployid", employid);
				mapRequest3.put("workList", workList);
				requestEnvelop3.setBody(mapRequest3);
				ResponseEnvelop resEnv3 = facade.addUserWork(requestEnvelop3);
				returnValue3 = processRevt(resEnv3);
			}
			req.setAttribute("worklist", workList);
			
			// 添加培训信息
			RequestEnvelop requestEnvelop4 = new RequestEnvelop();
			EventResponse returnValue4 = new EventResponse();
			returnValue4.setSucessFlag(true);
			HashMap<String, Object> mapRequest4 = new HashMap<String, Object>();
			List<UserTrainForm> trainList = getTrainList(req);
			if (!trainList.isEmpty()) {
				mapRequest4.put("useremployid", employid);
				mapRequest4.put("trainList", trainList);
				requestEnvelop4.setBody(mapRequest4);
				ResponseEnvelop resEnv4 = facade.saveUserTrain(requestEnvelop4);
				returnValue4 = processRevt(resEnv4);
			}
			req.setAttribute("worklist", trainList);

			if (returnValue1.isSucessFlag() && returnValue2.isSucessFlag()
					&& returnValue3.isSucessFlag() && returnValue4.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "档案录入完成，接下来请完成：1、将本档案打印并由本人签字，随同毕业证、身份证复印件等寄回人事部；2、请上业务平台申请工作服");
				ActionForward actionForward = new ActionForward();
				actionForward.setPath("");
				actionForward.setRedirect(true);
				return mapping.findForward("showDetail");
				/* return go2Page(req, mapping, "business"); */
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue1.getMsg()
						+ returnValue2.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		String tp = req.getParameter("tp");
		String forward1 = "/userinfo/queryUser.jsp";
		String forward2 = "/userinfo/queryleave.jsp";
		String fileKey = "userquery_select";
		String fileKey2 = "userquery_leave";

		ActionForward af = new ActionForward();
		UserInfoForm userInfoForm = (UserInfoForm) form;
		UserInfo userInfo = new UserInfo();
		try {

			/*
			 * LoginDTO dto = (LoginDTO)
			 * req.getSession().getAttribute("LoginDTO");
			 */

			ClassHelper.copyProperties(userInfoForm, userInfo);
			if("leave".equals(tp)){
				userInfo.setFileKey(fileKey2);
				String hql = queryEnterprise(userInfo);
				af = super.init(req, forward2, hql);
			}else{
				userInfo.setFileKey(fileKey);
				String hql = queryEnterprise(userInfo);
				af = super.init(req, forward1, hql);
			}
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
	 * 根据session的获得的账号，得到该账号绑定的人员信息
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward querySingleInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		String taraccount = dto.getBsc011();
		req.setAttribute("taraccount", taraccount);
		String forward = "/userinfo/yuangong/querySingle.jsp";
		ActionForward af = new ActionForward();
		if(taraccount == null || taraccount.equals("")){
			super.saveSuccessfulMsg(req, "账号不能为空！");
			return mapping.findForward("toQuerySingle");
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from sc05 where bsc011 = ?";	
		try {
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,taraccount);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "该账号不存在！");
            	return mapping.findForward("toQuerySingle");
            }
            
            
			String hql_notnull = "select u.username,u.useremployid,au.account from tbluser u left join tbl_acc_user au on u.useremployid = au.userid where u.useremployeestatus <> '0' and au.account = '" + taraccount + "'";
			String hql = null;
			hql = hql_notnull;
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该账号未绑定人员信息！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}finally{
		 	DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
		}
		return af;
	}
	
	
	
	/**
	 * 根据session的获得的账号，得到该账号绑定的人员信息
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryJuniorInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		String taraccount = dto.getBsc011();
		req.setAttribute("taraccount", taraccount);
		String forward = "/userinfo/yuangong/queryJunior.jsp";
		ActionForward af = new ActionForward();
		if(taraccount == null || taraccount.equals("")){
			super.saveSuccessfulMsg(req, "账号不能为空！");
			return mapping.findForward("toQueryJunior");
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from sc05 where bsc011 = ?";	
		try {
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,taraccount);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "该账号不存在！");
            	return mapping.findForward("toQueryJunior");
            }
            
            
			String hql_notnull = "select u.username,u.useremployid from tbluser u where u.useremployid in (select sj.juniorid from tbl_sup_jun sj where sj.superiorid in(select au.userid from tbl_acc_user au where u.useremployeestatus <> '0' and au.account = '" + taraccount + "'))";
			String hql = null;
			hql = hql_notnull;
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "未查询到下级信息！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}finally{
		 	DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
		}
		return af;
	}
	
	
	/**
	 * 账号与员工绑定。账号先在页面上输入，人员经过该方法获得
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String taraccount = req.getParameter("taraccount");
		String useremployid = req.getParameter("useremployid");
		String username = req.getParameter("username");
		System.out.println(taraccount);
		String forward = "/userinfo/bindAccount.jsp";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		ActionForward af = new ActionForward();
		String sql = "select * from sc05 where bsc011 = ?";	

		try {
			
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            //查找账号是否存在
        	pstmt = con.prepareStatement(sql);
            pstmt.setString(1,taraccount);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "该账号不存在！");
            	return mapping.findForward("queryAccount");
            }
            
            //查询当前输入的工号是否有绑定账号
			String hql = "select u.username,u.useremployid,au.account from tbluser u left join tbl_acc_user au on u.useremployid = au.userid where au.account = '" + taraccount + "'";
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
		}finally{
		 	DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
		}
		return af;
	}

	/**
	 * 显示人员具体信息
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String employid = req.getParameter("useremployid");
		String method = req.getParameter("mtd");
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		try {
			con = DBUtil.getConnection();
			// DBUtil.beginTrans(con);

			UserInfo userInfo = new UserInfo();
			UserInfoForm userInfoForm = (UserInfoForm) form;
			if (employid == null || "".equalsIgnoreCase(employid)) {
				saveSuccessfulMsg(req, "主键为空，请重新查询");
			} else {
				userInfo.setUseremployid(employid);
				ClassHelper.copyProperties(userInfoForm, userInfo);

				UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
				
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap<String, Object> mapRequest = new HashMap<String, Object>();
				mapRequest.put("beo", userInfo);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.showDetail(requestEnvelop);
				// 处理返回结果p
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					HashMap mapResponse = (HashMap) resEnv.getBody();
					List userList = (ArrayList) mapResponse.get("beo");// 个人客户信息
					ClassHelper.copyProperties(userList.get(0), userInfoForm);
					/*
					 * req.getSession().setAttribute("jiancha",
					 * scForm.getJiancha());
					 * req.getSession().setAttribute("chuandao",
					 * scForm.getChuandao());
					 */

				} else {
					String[] aa = StringUtil.getAsStringArray(
							returnValue.getMsg(), "|");
					super.saveErrors(req, new AppException(aa[3]));
				}

				String sql = "select * from tblusereducate where useremployid = '"
						+ employid + "'";
				List result = (Vector) DBUtil.querySQL(con, sql);
				List<UserEducateForm> eduList = new ArrayList<UserEducateForm>();
				if (result.size() > 0) {
					for (int i = 0; i < result.size(); i++) {
						UserEducateForm educate = new UserEducateForm();
						Object a1 = ((HashMap) result.get(i)).get("1");
						Object a2 = ((HashMap) result.get(i)).get("2");
						Object a3 = ((HashMap) result.get(i)).get("3");
						Object a4 = ((HashMap) result.get(i)).get("4");
						Object a5 = ((HashMap) result.get(i)).get("5");
						Object a6 = ((HashMap) result.get(i)).get("6");
						Object a7 = ((HashMap) result.get(i)).get("7");
						if (a4 != null) {
							// educate.setUserschoolenddate(new
							// SimpleDateFormat("yyyy-MM-dd").parse(a4.toString()));
							educate.setUserschoolenddateStr(formatDateYYYYMMDD(a4.toString()));
						}
						educate.setUserschoolmajor((a5 == null ? "" : a5
								.toString()));
						educate.setUserschoolname((a2 == null ? "" : a2
								.toString()));
						if (a3 != null) {
							educate.setUserschoolstartdate(new SimpleDateFormat(
									"yyyy-MM-dd").parse(a3.toString()));
							educate.setUserschoolstartdateStr(formatDateYYYYMMDD(a3.toString()));
						}
						educate.setUserschooldegree((a6 == null ? "" : a6
								.toString()));
						
						educate.setUserschooltype((a7 == null ? "" : a7
								.toString()));
						eduList.add(educate);
					}
				}
				eduList.add(new UserEducateForm());
				eduList.add(new UserEducateForm());
				req.setAttribute("educateList", eduList);

				String sql1 = "select * from tbluserfamily where useremployid = '"
						+ employid + "'";
				List result1 = (Vector) DBUtil.querySQL(con, sql1);
				List<UserFamilyForm> famList = new ArrayList<UserFamilyForm>();
				if (result1.size() > 0) {
					for (int i = 0; i < result1.size(); i++) {
						UserFamilyForm family = new UserFamilyForm();
						Object a1 = ((HashMap) result1.get(i)).get("1");
						Object a2 = ((HashMap) result1.get(i)).get("2");
						Object a3 = ((HashMap) result1.get(i)).get("3");
						Object a4 = ((HashMap) result1.get(i)).get("4");
						Object a5 = ((HashMap) result1.get(i)).get("5");
						Object a6 = ((HashMap) result1.get(i)).get("6");
						family.setFamilycall(a1 == null ? "" : a1.toString());
						family.setFamilyname(a4 == null ? "" : a4.toString());
						family.setFamilyphoneno(a3 == null ? "" : a3.toString());
						family.setFamilyworkplace(a2 == null ? "" : a2
								.toString());
						if (a6 != null) {

							family.setFamilybirthdayStr(formatDateYYYYMMDD(a6.toString()));
						}
						famList.add(family);
					}
				}
				famList.add(new UserFamilyForm());
				famList.add(new UserFamilyForm());
				req.setAttribute("familyList", famList);

				String sql2 = "select * from tbluserwork where employid = '"
						+ employid + "'";
				List result2 = (Vector) DBUtil.querySQL(con, sql2);
				List<UserWorkForm> workList = new ArrayList<UserWorkForm>();
				if (result2.size() > 0) {
					for (int i = 0; i < result2.size(); i++) {
						UserWorkForm work = new UserWorkForm();
						Object a1 = ((HashMap) result2.get(i)).get("1");
						Object a2 = ((HashMap) result2.get(i)).get("2");
						Object a3 = ((HashMap) result2.get(i)).get("3");
						Object a4 = ((HashMap) result2.get(i)).get("4");
						Object a5 = ((HashMap) result2.get(i)).get("5");
						Object a6 = ((HashMap) result2.get(i)).get("6");
						Object a7 = ((HashMap) result2.get(i)).get("7");
						Object a8 = ((HashMap) result2.get(i)).get("8");
						if (a2 != null) {
							work.setWorkstartdateStr(formatDateYYYYMMDD(a2.toString()));
						}
						if (a3 != null) {
							work.setWorkenddateStr(formatDateYYYYMMDD(a3.toString()));
						}
						work.setWorkplace(a4 == null ? "" : a4.toString());
						work.setWorkposition(a5 == null ? "" : a5.toString());
						work.setWorksalary(a6 == null ? "" : a6.toString());
						work.setWorkleavereason(a7 == null ? "" : a7.toString());
						work.setWorkprove(a8 == null ? "" : a8.toString());

						workList.add(work);
					}
				}
				workList.add(new UserWorkForm());
				workList.add(new UserWorkForm());
				req.setAttribute("workList", workList);
				
				getTrainFromSQL(req);
			}

		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}finally{
			DBUtil.closeConnection(con);
		}
		if(method.equals("show"))
			return mapping.findForward("show");
		else if(method.equals("modifyUserInfo"))
			return mapping.findForward("showModifyUserInfo");
		else if(method.equals("geren")) //modifyMore
			return mapping.findForward("showSingle");
		else
			return mapping.findForward("showModifyMore");
	}

	/**
	 * 修改当前页面用户信息内容
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward showModifyUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String employid = req.getParameter("useremployid");
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		try {
			con = DBUtil.getConnection();
			// DBUtil.beginTrans(con);

			UserInfo userInfo = new UserInfo();
			UserInfoForm userInfoForm = (UserInfoForm) form;
			if (employid == null || "".equalsIgnoreCase(employid)) {
				saveSuccessfulMsg(req, "主键为空，请重新查询");
			} else {
				userInfo.setUseremployid(employid);
				ClassHelper.copyProperties(userInfoForm, userInfo);

				UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap<String, Object> mapRequest = new HashMap<String, Object>();
				mapRequest.put("beo", userInfo);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.showDetail(requestEnvelop);
				// 处理返回结果p
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					HashMap mapResponse = (HashMap) resEnv.getBody();
					List userList = (ArrayList) mapResponse.get("beo");// 个人客户信息
					ClassHelper.copyProperties(userList.get(0), userInfoForm);
					/*
					 * req.getSession().setAttribute("jiancha",
					 * scForm.getJiancha());
					 * req.getSession().setAttribute("chuandao",
					 * scForm.getChuandao());
					 */

				} else {
					String[] aa = StringUtil.getAsStringArray(
							returnValue.getMsg(), "|");
					super.saveErrors(req, new AppException(aa[3]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}finally{
			DBUtil.closeConnection(con);
		}
		return mapping.findForward("showModifyUserInfo");
	}
	
	/**
	 * 从数据库中获取需要显示的用户详情信息，比如教育信息等
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward showUserMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		getFamilyFromSQL(req);
		getWorkFromSQL(req);
		getEducateFromSQL(req);
		return mapping.findForward("showDetail");
	}
	
	/**
	 * 根据前端传过来的值更新人员基本信息
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward modifyUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;

		UserInfo userInfo = new UserInfo();
		if (userInfoForm.getUseremployid() == null
				|| userInfoForm.getUseremployid().equals("")) {
			saveSuccessfulMsg(req, "主键为空，无法修改该人员信息");
			return mapping.findForward("backspace");
		} else {
			UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
			ClassHelper.copyProperties(userInfoForm, userInfo);

			// 添加基本信息
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap<String, UserInfo> mapRequest = new HashMap<String, UserInfo>();

			mapRequest.put("userInfo", userInfo);
			requestEnvelop.setBody(mapRequest);
			ResponseEnvelop resEnv = facade.modifyUserInfo(requestEnvelop);
			returnValue = processRevt(resEnv);

			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改成功");
				ActionForward actionForward = new ActionForward();
				actionForward.setPath("");
				actionForward.setRedirect(true);
				return mapping.findForward("modifyMore");
				/* return go2Page(req, mapping, "business"); */
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}
	}
	
	public ActionForward modifyMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String employid = req.getParameter("useremployid");
		if (employid == null || employid.equals("")) {
			saveSuccessfulMsg(req, "工号丢失！");
			return mapping.findForward("backspace");
		} else {
			UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
			UserInfoForm userInfoForm = (UserInfoForm) form;
			UserInfo userInfo = new UserInfo();
			ClassHelper.copyProperties(userInfoForm, userInfo);
			// 添加家庭信息
			RequestEnvelop requestEnvelop1 = new RequestEnvelop();
			EventResponse returnValue1 = new EventResponse();
			returnValue1.setSucessFlag(true);
			HashMap<String, Object> mapRequest1 = new HashMap<String, Object>();
			List<UserFamilyForm> famList = getFamilyList(req);
			if (!famList.isEmpty()) {
				mapRequest1.put("useremployid", employid);
				mapRequest1.put("beo", userInfo);
				mapRequest1.put("famList", famList);
				requestEnvelop1.setBody(mapRequest1);
				facade.deleteUserFamily(requestEnvelop1);
				ResponseEnvelop resEnv1 = facade
						.saveUserFamily(requestEnvelop1);
				returnValue1 = processRevt(resEnv1);
			}
			req.setAttribute("familylist", famList);

			// 添加教育信息
			RequestEnvelop requestEnvelop2 = new RequestEnvelop();
			EventResponse returnValue2 = new EventResponse();
			returnValue2.setSucessFlag(true);
			HashMap<String, Object> mapRequest2 = new HashMap<String, Object>();
			List<UserEducateForm> eduList = getEducateList(req);
			if (!eduList.isEmpty()) {
				mapRequest2.put("useremployid", employid);
				mapRequest2.put("beo", userInfo);
				mapRequest2.put("eduList", eduList);
				requestEnvelop2.setBody(mapRequest2);
				facade.deleteUserEducate(requestEnvelop2);
				ResponseEnvelop resEnv2 = facade
						.addUserEducate(requestEnvelop2);
				returnValue2 = processRevt(resEnv2);
			}
			req.setAttribute("educatelist", eduList);

			// 添加教育信息
			RequestEnvelop requestEnvelop3 = new RequestEnvelop();
			EventResponse returnValue3 = new EventResponse();
			returnValue3.setSucessFlag(true);
			HashMap<String, Object> mapRequest3 = new HashMap<String, Object>();
			List<UserWorkForm> workList = getWorkList(req);
			if (!workList.isEmpty()) {
				mapRequest3.put("useremployid", employid);
				mapRequest3.put("beo", userInfo);
				mapRequest3.put("workList", workList);
				requestEnvelop3.setBody(mapRequest3);
				facade.deleteUserWork(requestEnvelop3);
				ResponseEnvelop resEnv3 = facade.addUserWork(requestEnvelop3);
				returnValue3 = processRevt(resEnv3);
			}
			req.setAttribute("worklist", workList);
			
			// 添加教育信息
			RequestEnvelop requestEnvelop4 = new RequestEnvelop();
			EventResponse returnValue4 = new EventResponse();
			returnValue4.setSucessFlag(true);
			HashMap<String, Object> mapRequest4 = new HashMap<String, Object>();
			List<UserTrainForm> trainList = getTrainList(req);
			if (!trainList.isEmpty()) {
				mapRequest4.put("useremployid", employid);
				mapRequest4.put("beo", userInfo);
				mapRequest4.put("trainList", trainList);
				requestEnvelop4.setBody(mapRequest4);
				facade.deleteUserTrain(requestEnvelop4);
				facade.deleteUserTrain(requestEnvelop4);
				ResponseEnvelop resEnv4 = facade.saveUserTrain(requestEnvelop4);
				returnValue4 = processRevt(resEnv4);
			}
			

			if (returnValue1.isSucessFlag() && returnValue2.isSucessFlag()
					&& returnValue3.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "保存成功");
				ActionForward actionForward = new ActionForward();
				actionForward.setPath("");
				actionForward.setRedirect(true);
				return mapping.findForward("showDetail");
				/* return go2Page(req, mapping, "business"); */
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue1.getMsg()
						+ returnValue2.getMsg(), "|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		}
	}

	/**
	 * 空请求，用于生成可填写复选框
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAddUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfoForm = (UserInfoForm) form;

		userInfoForm.setWritedate(DateUtil.getDate());
		return mapping.findForward("addUser");

		/*
		 * UserEducate userEducate = new UserEducate(); UserInfo userInfo = new
		 * UserInfo(); ActionForward af = new ActionForward(); String forward =
		 * "/userinfo/addUser.jsp"; try { userInfo.setFileKey("userinfo_null");
		 * userEducate.setFileKey(""); String hql = queryEnterprise(userInfo);
		 * af = init(req, forward, hql);
		 * 
		 * } catch (AppException ex) { this.saveErrors(req, ex); } catch
		 * (Exception e) { this.saveErrors(req, e); } return af;
		 */
	}

	/**
	 * 空请求，用于生成可填写复选框
	 * 
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward toAddMore(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserEducate userEducate = new UserEducate();
		UserInfo userInfo = new UserInfo();
		ActionForward af = new ActionForward();
		String forward = "/userinfo/addMore.jsp";
		try {
			userInfo.setFileKey("userinfo_null");
			userEducate.setFileKey("");
			String hql = queryEnterprise(userInfo);
			af = init(req, forward, hql);

		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	/**
	 * 跳转到上传页面
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward toUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserEducate userEducate = new UserEducate();
		UserInfo userInfo = new UserInfo();
		ActionForward af = new ActionForward();
		String forward = "/userinfo/upload.jsp";
		try {
			userInfo.setFileKey("userinfo_null");
			userEducate.setFileKey("");
			String hql = queryEnterprise(userInfo);
			af = init(req, forward, hql);

		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	/**
	 * 导入工资信息
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm userInfo = (UserInfoForm) form;
		String year = req.getParameter("year");
		String month = req.getParameter("month");
		UserSalaryFacade facade = (UserSalaryFacade) getService("UserSalaryFacade");
		String[] salaryArray = {"useremployid","phonenumber","store","indexx","month"
				,"name","jiben","gangwei","jixiao","xiaoshou"
				,"chanpin","teshu1","jieyue","cuxiao","xinhezuo"
				,"ewai","teshu2","queqin","qita","tiaozheng"
				,"yingfa","wxbt","gjj","jieri","wxkk"
				,"gkkjj","shifa","nongye","jianshe","jjxs"
				,"sjxse","hecpxse","kkfy"};
		try {
			int size = userInfo.getFileCount();

			for(int i = 0;i < size;i++){
				
				String contentType = userInfo.getFile(i).getContentType();
				String fileName = userInfo.getFile(i).getFileName();
				
				Connection con = DBUtil.getJDBCConnection();
				PreparedStatement pstmt = null;
				String sql = "insert into tblres(year,month,contenttype,filename,updatedate,attachment)values(?,?,?,?,?,EMPTY_BLOB())";
				String sql1 = "select count(*) from tblres where year = '" + year + "' and month = '" + month + "'";
				String sql_delete = "delete from tblres where year = '" + year + "' and month = '" + month + "'";
				DBUtil.beginTrans(con);
				
	            try{
	                DBUtil.beginTrans(con);
	                pstmt = con.prepareStatement(sql1);
	                ResultSet result = pstmt.executeQuery();
	                int recordNumber = 0;
	                if(result.next()){
	                	recordNumber = result.getInt(1);
	                }
	                if(recordNumber > 0){
	                	//删除前数据在插入
	                	pstmt = con.prepareStatement(sql_delete);
	                	pstmt.executeUpdate();
	                }
	                //插入数据excel表格
	                pstmt = con.prepareStatement(sql);
	                pstmt.setString(1,year);
	                pstmt.setString(2,month);
	                pstmt.setString(3,contentType);
	                pstmt.setString(4,fileName);
	                pstmt.setDate(5,DBUtil.getSysDate2(con));
	                pstmt.executeUpdate();
	                pstmt = null;
        
	                String key = "year='" + year + "' and month ='" + month +"'";
	                DBUtil.saveBlob(con,"tblres","attachment",key, userInfo.getFile(i).getFileData());
	                DBUtil.commit(con);
	                
	                
	                ////解析excel
	                Workbook workbook = Workbook.getWorkbook(userInfo.getFile(i).getInputStream());
					Sheet sheet = workbook.getSheet(0);
					for (int j = 1; j < sheet.getRows(); j++) {
						Map<String,String> rowData = new HashMap<String,String>();
						UserSalary userSalary = new UserSalary();
						for (int k = 0; k < sheet.getColumns(); k++) {
							Cell cell = sheet.getCell(k,j);
							if(cell.getContents() != null)
								rowData.put(salaryArray[k],cell.getContents());
							else
								rowData.put(salaryArray[k],"0.00");
							System.out.print(cell.getContents() + "  ");
						}
						rowData.put("year", year);
						ClassHelper.copyProperties(rowData, userSalary);	
						RequestEnvelop requestEnvelop = new RequestEnvelop();
						EventResponse returnValue = new EventResponse();
						HashMap<String, UserSalary> mapRequest = new HashMap<String, UserSalary>();

						mapRequest.put("userSalary", userSalary);
						requestEnvelop.setBody(mapRequest);
						ResponseEnvelop resEnv = facade.addUserSalary(requestEnvelop);
						returnValue = processRevt(resEnv);
						super.saveSuccessfulMsg(req, "上传成功");
					}
	            }catch(Exception e){
	                e.printStackTrace();
	            }finally{
	                DBUtil.closeStatement(pstmt);
	                DBUtil.rollback(con);
	                DBUtil.closeConnection(con);
	            }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*try {
			Workbook workbook = Workbook.getWorkbook(fileUpload.getFile().getInputStream());
			Sheet sheet = workbook.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j,i);
					System.out.print(cell.getContents() + "  ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		return mapping.findForward("toAddSalary");
	}
	
	/**
	 * 流程资源上传，支持多文件上传
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadProRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ProcessResForm prf = (ProcessResForm) form;
		String nemid = req.getParameter("nemid");
		Connection con = null;
		try {
			//获取文件数目
			int size = prf.getFileCount();
			for(int i = 0;i < size;i++){
				String name = prf.getFile(i).getFileName();
				con = DBUtil.getJDBCConnection();
				PreparedStatement pstmt = null;
				String sql = "insert into tblprores(nemid,name,id,content)values(?,?,?,EMPTY_BLOB())";		
	            try{
	                DBUtil.beginTrans(con);
	                //插入数据excel表格
	                //1.预插入，为blob预留空间
	                pstmt = con.prepareStatement(sql);
	                pstmt.setString(1,nemid);
	                pstmt.setString(2,name);
	                String id = String.valueOf(new Date().getTime());
	                pstmt.setString(3,id);
	                pstmt.executeUpdate();
	                pstmt = null;
	                
	                //2.将blob字段内容插入
	                String key = "id='" + id + "' and nemid ='" + nemid +"'";
	                DBUtil.saveBlob(con,"tblprores","content",key, prf.getFile(i).getFileData());
	                DBUtil.commit(con); 
	            }catch(Exception e){
	                e.printStackTrace();
	            }finally{
	                DBUtil.closeStatement(pstmt);
	                DBUtil.rollback(con);
	                DBUtil.closeConnection(con);
	            }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapping.findForward("getProResList");
	}
	
	/**
	 * 获取流程资源列表
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward getProResList(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ProcessResForm prf = (ProcessResForm) form;
		String nemid = prf.getNemid();
		
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from tblprores where nemid = '"
					+ nemid + "'";
			List result = (Vector) DBUtil.querySQL(con, sql);
			List<ProcessResForm> resList = new ArrayList<ProcessResForm>();
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					ProcessResForm proRes = new ProcessResForm();
					Object a1 = ((HashMap) result.get(i)).get("1");
					Object a2 = ((HashMap) result.get(i)).get("2");
					Object a3 = ((HashMap) result.get(i)).get("4");
					proRes.setNemid((a1 == null ? "" : a1.toString()));
					proRes.setName((a2 == null ? "" : a2.toString()));
					proRes.setId(a3 == null ? "" : a3.toString());
					resList.add(proRes);
				}
			}
			req.setAttribute("resList", resList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
		return mapping.findForward("download");
	}
	
	/**
	 * 流程资源下载
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward downloadRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String nemid = req.getParameter("nemid");
		String id = req.getParameter("id");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		//String sql = "insert into tblres(id,attachment)values(?,EMPTY_BLOB())";
		String sql = "select * from tblprores where nemid = ? and id = ?";	
       
        
        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,nemid);
            pstmt.setString(2,id);
            resultset = pstmt.executeQuery();
            if (resultset.next()) {
			   Blob blob = resultset.getBlob(3);
			   String fileName = resultset.getString(2);			   
			   InputStream ins = blob.getBinaryStream();
			   res.setContentType("application/unknown");
			   res.addHeader("Content-Disposition", "attachment; filename=\""+new String( fileName.getBytes("GBK"), "ISO8859_1" ) + "\"");
			   OutputStream outStream = res.getOutputStream();
			   byte[] bytes = new byte[1024];
			   int len = 0;
			   while ((len=ins.read(bytes))!=-1) {
			       outStream.write(bytes,0,len);
			   }
			   ins.close();
			   outStream.close();
			   outStream = null;
			   con.commit();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
		return  null;
	}
	
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String id = req.getParameter("id");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		//String sql = "insert into tblres(id,attachment)values(?,EMPTY_BLOB())";
		String sql = "select * from tblres where id = ?";	
       
        
        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,id);
            resultset = pstmt.executeQuery();
            if (resultset.next()) {
				String contentType = resultset.getString(3);
				String fileName = resultset.getString(4);
			   Blob blob = resultset.getBlob(2);
			   InputStream ins = blob.getBinaryStream();
			   res.setContentType("application/unknown");
			   res.addHeader("Content-Disposition", "attachment; filename="+fileName);
			   OutputStream outStream = res.getOutputStream();
			   byte[] bytes = new byte[1024];
			   int len = 0;
			   while ((len=ins.read(bytes))!=-1) {
			       outStream.write(bytes,0,len);
			   }
			   ins.close();
			   outStream.close();
			   outStream = null;
			   con.commit();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
		return  null;
	}
	
	
	public ActionForward toBindAccUserJSP(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String taraccount = req.getParameter("taraccount");
		String useremployid = req.getParameter("useremployid");
		System.out.println(taraccount);
		String forward = "/userinfo/bindAccount.jsp";

		ActionForward af = new ActionForward();
		

		try {

			String hql = "select u.username,u.useremployid,l.accountno from tbluser u left join tbllogin l   on u.useremployid = l.entityid where u.useremployid = '" + useremployid + "'";
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String mtd = req.getParameter("mtd");
				if(mtd != null && mtd.equals("jump")){
					
				}
				else{
					String msg = "没有查询到符合条件的记录！";
					super.saveSuccessfulMsg(req, msg);
				}
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	/**
	 * 账号人员绑定
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward bindAccUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String useremployid = req.getParameter("useremployid");
		String username = req.getParameter("username");
		String taraccount = req.getParameter("taraccount");
		req.setAttribute("useremployid", useremployid);
		req.setAttribute("taraccount", taraccount);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from tbllogin where entityid = ?";	
        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,useremployid);
            resultset = pstmt.executeQuery();
            
            if (resultset.next()) {
            	super.saveSuccessfulMsg(req, "该人员已绑定，请先解绑！");
            	return mapping.findForward("bindAccount");
            }else{
            	String sql_bind = "insert into tbllogin(accountno,entityid) values(?,?)";
            	pstmt = con.prepareStatement(sql_bind);
            	pstmt.setString(1,taraccount);
                pstmt.setString(2,useremployid);
                pstmt.executeUpdate();
                DBUtil.commit(con);
            }
        }catch(Exception e){
            e.printStackTrace();
            super.saveSuccessfulMsg(req, "系统出错！");
            return mapping.findForward("bindAccount");
        }finally{
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        super.saveSuccessfulMsg(req, "绑定成功！");
        ActionForward actionForward = new ActionForward();
		actionForward.setPath("");
		actionForward.setRedirect(true);
    	return mapping.findForward("bindAccount");
	}
	
	
	/**
	 * 解绑已有账号与员工关联
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward unWrapAccUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String useremployid = req.getParameter("useremployid");
		String accountno = req.getParameter("accountno");
		req.setAttribute("useremployid", useremployid);
		req.setAttribute("taraccount",accountno);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from tbllogin where entityid = ?";	

        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,useremployid);
            resultset = pstmt.executeQuery();
            
            if (resultset.next()) {
            	String sql_delete = "delete from tbllogin where entityid = ?";
            	pstmt = con.prepareStatement(sql_delete);
                pstmt.setString(1,useremployid);
                pstmt.executeUpdate();
                DBUtil.commit(con);
            }else{ 
                super.saveSuccessfulMsg(req, "该人员未绑定！");
            	return mapping.findForward("bindAccount");
            }
        }catch(Exception e){
            e.printStackTrace();
            super.saveSuccessfulMsg(req, "系统出错！");
            return mapping.findForward("bindAccount");
        }finally{
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        super.saveSuccessfulMsg(req, "解绑成功！");
    	return mapping.findForward("bindAccount");
	}
	
	
	public List<UserWorkForm> getWorkFromSQL(HttpServletRequest req) {
		String employid = req.getParameter("useremployid");
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		String sql2 = "select * from tbluserwork where employid = '"
				+ employid + "'";
		List result2;
		try {
			con = DBUtil.getConnection();
			result2 = (Vector) DBUtil.querySQL(con, sql2);
			List<UserWorkForm> workList = new ArrayList<UserWorkForm>();
			if (result2.size() > 0) {
				for (int i = 0; i < result2.size(); i++) {
					UserWorkForm work = new UserWorkForm();
					Object a1 = ((HashMap) result2.get(i)).get("1");
					Object a2 = ((HashMap) result2.get(i)).get("2");
					Object a3 = ((HashMap) result2.get(i)).get("3");
					Object a4 = ((HashMap) result2.get(i)).get("4");
					Object a5 = ((HashMap) result2.get(i)).get("5");
					Object a6 = ((HashMap) result2.get(i)).get("6");
					Object a7 = ((HashMap) result2.get(i)).get("7");
					Object a8 = ((HashMap) result2.get(i)).get("8");
					if (a2 != null) {
						work.setWorkstartdateStr(formatDateYYYYMMDD(a2.toString()));
					}
					if (a3 != null) {
						work.setWorkenddateStr(formatDateYYYYMMDD(a3.toString()));
					}
					work.setWorkplace(a4 == null ? "" : a4.toString());
					work.setWorkposition(a5 == null ? "" : a5.toString());
					work.setWorksalary(a6 == null ? "" : a6.toString());
					work.setWorkleavereason(a7 == null ? "" : a7.toString());
					work.setWorkprove(a8 == null ? "" : a8.toString());

					workList.add(work);
				}
			}
			req.setAttribute("workList", workList);
			return workList;
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
		return null;
		
	}

	public List<UserEducateForm> getEducateFromSQL(HttpServletRequest req) throws ParseException{
		String employid = req.getParameter("useremployid");
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		String sql = "select * from tblusereducate where useremployid = '"
				+ employid + "'";
		List result;
		try {
			con = DBUtil.getConnection();
			result = (Vector) DBUtil.querySQL(con, sql);
			List<UserEducateForm> eduList = new ArrayList<UserEducateForm>();
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					UserEducateForm educate = new UserEducateForm();
					Object a1 = ((HashMap) result.get(i)).get("1");
					Object a2 = ((HashMap) result.get(i)).get("2");
					Object a3 = ((HashMap) result.get(i)).get("3");
					Object a4 = ((HashMap) result.get(i)).get("4");
					Object a5 = ((HashMap) result.get(i)).get("5");
					Object a6 = ((HashMap) result.get(i)).get("6");
					Object a7 = ((HashMap) result.get(i)).get("7");
					if (a4 != null) {
						// educate.setUserschoolenddate(new
						// SimpleDateFormat("yyyy-MM-dd").parse(a4.toString()));
						educate.setUserschoolenddate((Date) a4);
						educate.setUserschoolenddateStr(formatDateYYYYMMDD(a4.toString()));
					}
					educate.setUserschoolmajor((a5 == null ? "" : a5
							.toString()));
					educate.setUserschoolname((a2 == null ? "" : a2
							.toString()));
					if (a3 != null) {
						educate.setUserschoolstartdate(new SimpleDateFormat(
								"yyyy-MM-dd").parse(a3.toString()));
						educate.setUserschoolstartdateStr(formatDateYYYYMMDD(a3.toString()));
					}
					educate.setUserschooldegree((a6 == null ? "" : a6
							.toString()));
					educate.setUserschooltype((a7 == null ? "" : a7
							.toString()));
					eduList.add(educate);
				}
			}
			req.setAttribute("educateList", eduList);
			return eduList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
		return null;
	}
	
	public List<UserFamilyForm> getFamilyFromSQL(HttpServletRequest req) {
		String employid = req.getParameter("useremployid");
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		String sql1 = "select * from tbluserfamily where useremployid = '"
				+ employid + "'";
		List result1;
		try {
			con = DBUtil.getConnection();
			result1 = (Vector) DBUtil.querySQL(con, sql1);
			List<UserFamilyForm> famList = new ArrayList<UserFamilyForm>();
			if (result1.size() > 0) {
				for (int i = 0; i < result1.size(); i++) {
					UserFamilyForm family = new UserFamilyForm();
					Object a1 = ((HashMap) result1.get(i)).get("1");
					Object a2 = ((HashMap) result1.get(i)).get("2");
					Object a3 = ((HashMap) result1.get(i)).get("3");
					Object a4 = ((HashMap) result1.get(i)).get("4");
					Object a5 = ((HashMap) result1.get(i)).get("5");
					Object a6 = ((HashMap) result1.get(i)).get("6");
					family.setFamilycall(a1 == null ? "" : a1.toString());
					family.setFamilyname(a4 == null ? "" : a4.toString());
					family.setFamilyphoneno(a3 == null ? "" : a3.toString());
					family.setFamilyworkplace(a2 == null ? "" : a2
							.toString());
					if (a6 != null) {

						family.setFamilybirthdayStr(formatDateYYYYMMDD(a6.toString()));
					}
					famList.add(family);
				}
			}
			
			req.setAttribute("familyList", famList);
			return famList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
		return null;
	}
	
	public List<UserTrainForm> getTrainFromSQL(HttpServletRequest req){
		String employid = req.getParameter("useremployid");
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		String sql = "select * from tblusertrain where useremployid = '"
				+ employid + "'";
		List result;
		try {
			con = DBUtil.getConnection();
			result = (Vector) DBUtil.querySQL(con, sql);
			List<UserTrainForm> trainList = new ArrayList<UserTrainForm>();
			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					UserTrainForm train = new UserTrainForm();
					Object a1 = ((HashMap) result.get(i)).get("1");
					Object a2 = ((HashMap) result.get(i)).get("2");
					Object a3 = ((HashMap) result.get(i)).get("3");
					Object a4 = ((HashMap) result.get(i)).get("4");
					Object a5 = ((HashMap) result.get(i)).get("5");
					Object a6 = ((HashMap) result.get(i)).get("6");
					if (a2 != null) {
						train.setTrainstartdate((Date) a2);
						train.setTrainstartdateStr(formatDateYYYYMMDD(a2.toString()));
						
					}
					if (a3 != null) {
						
						train.setTrainenddate((Date) a3);
						train.setTrainenddateStr(formatDateYYYYMMDD(a3.toString()));
					}
					train.setTrainplace(a4 == null?"":a4.toString());
					train.setTraincontent(a5 == null?"":a5.toString());
					train.setTraincertificate(a6 == null?"":a6.toString());
					trainList.add(train);
				}
			}
			trainList.add(new UserTrainForm());
			trainList.add(new UserTrainForm());
			req.setAttribute("trainList", trainList);
			return trainList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(con);
		}
		return null;
	}
	
	public ActionForward getUserInfoFromSQL(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res){
		String employid = req.getParameter("useremployid");
		SubmitDataMap data = new SubmitDataMap(req);
		Connection con = null;
		LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
		try {
			con = DBUtil.getConnection();
			// DBUtil.beginTrans(con);

			UserInfo userInfo = new UserInfo();
			UserInfoForm userInfoForm = (UserInfoForm) form;
			if (employid == null || "".equalsIgnoreCase(employid)) {
				saveSuccessfulMsg(req, "主键为空，请重新查询");
			} else {
				userInfo.setUseremployid(employid);
				ClassHelper.copyProperties(userInfoForm, userInfo);

				UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				// 将Application对象放入HashMap
				HashMap<String, Object> mapRequest = new HashMap<String, Object>();
				mapRequest.put("beo", userInfo);
				// 将HashMap对象放入requestEnvelop
				requestEnvelop.setBody(mapRequest);
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.showDetail(requestEnvelop);
				// 处理返回结果p
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					HashMap mapResponse = (HashMap) resEnv.getBody();
					List userList = (ArrayList) mapResponse.get("beo");// 个人客户信息
					ClassHelper.copyProperties(userList.get(0), userInfoForm);
					/*
					 * req.getSession().setAttribute("jiancha",
					 * scForm.getJiancha());
					 * req.getSession().setAttribute("chuandao",
					 * scForm.getChuandao());
					 */

				} else {
					String[] aa = StringUtil.getAsStringArray(
							returnValue.getMsg(), "|");
					super.saveErrors(req, new AppException(aa[3]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}finally{
			DBUtil.closeConnection(con);
		}

		return mapping.findForward("show");
	}

	/**
	 * 从req中获取家庭状况的list
	 * 
	 * @param req
	 * @return List集合
	 * @throws ParseException
	 */
	public List<UserFamilyForm> getFamilyList(HttpServletRequest req)
			throws ParseException {
		SubmitDataMap data = new SubmitDataMap(req);
		String[] familycallList = data.getParameterValues("familycall");
		String[] familyworkplaceList = data
				.getParameterValues("familyworkplace");
		String[] familyphonenoList = data.getParameterValues("familyphoneno");
		String[] familynameList = data.getParameterValues("familyname");
		String[] familybirthdayList = data.getParameterValues("familybirthday");
		List<UserFamilyForm> famList = new ArrayList<UserFamilyForm>();
		if (familycallList != null) {
			int size = familycallList.length;
			for (int i = 0; i < size; i++) {
				int j = 0;
				if (familycallList[i] == null || familycallList[i].equals("")) {
					j++;
				}
				if (familyworkplaceList[i] == null
						|| familyworkplaceList[i].equals("")) {
					j++;
				}
				if (familyphonenoList[i] == null
						|| familyphonenoList[i].equals("")) {
					j++;
				}
				if (familynameList[i] == null || familynameList[i].equals("")) {
					j++;
				}
				if (familynameList[i] == null || familynameList[i].equals("")) {
					j++;
				}
				if (j == 5)
					continue;
				UserFamilyForm userFamilyForm = new UserFamilyForm();
				userFamilyForm.setFamilycall(familycallList[i]);
				userFamilyForm.setFamilyname(familynameList[i]);
				userFamilyForm.setFamilyphoneno(familyphonenoList[i]);
				userFamilyForm.setFamilyworkplace(familyworkplaceList[i]);
				if (familybirthdayList[i] != "") {
					userFamilyForm.setFamilybirthday(new SimpleDateFormat(
							"yyyy-MM-dd").parse(familybirthdayList[i]));
				}
				famList.add(userFamilyForm);
			}
		}
		return famList;
	}

	public List<UserWorkForm> getWorkList(HttpServletRequest req) {
		SubmitDataMap data = new SubmitDataMap(req);
		String[] workstartdateList = data.getParameterValues("workstartdate");
		String[] workenddateList = data.getParameterValues("workenddate");
		String[] workplaceList = data.getParameterValues("workplace");
		String[] workpositionList = data.getParameterValues("workposition");
		String[] worksalaryList = data.getParameterValues("worksalary");
		String[] workleavereasonList = data
				.getParameterValues("workleavereason");
		String[] workproveList = data.getParameterValues("workprove");
		List<UserWorkForm> workList = new ArrayList<UserWorkForm>();
		if (workplaceList != null) {
			int size = workplaceList.length;
			for (int i = 0; i < size; i++) {
				try {
					int j = 0;
					if (workstartdateList[i] == null
							|| workstartdateList[i].equals("")) {
						j++;
					}
					if (workenddateList[i] == null
							|| workenddateList[i].equals("")) {
						j++;
					}
					if (workplaceList[i] == null || workplaceList[i].equals("")) {
						j++;
					}
					if (workpositionList[i] == null
							|| workpositionList[i].equals("")) {
						j++;
					}
					if (worksalaryList[i] == null
							|| worksalaryList[i].equals("")) {
						j++;
					}
					if (workleavereasonList[i] == null
							|| workleavereasonList[i].equals("")) {
						j++;
					}
					if (workproveList[i] == null || workproveList[i].equals("")) {
						j++;
					}
					if (j == 7)
						continue;
					UserWorkForm userWorkForm = new UserWorkForm();
					if (workstartdateList[i] != "") {
						userWorkForm.setWorkstartdate(new SimpleDateFormat(
								"yyyy-MM-dd").parse(workstartdateList[i]));
					}
					if (workenddateList[i] != "") {
						userWorkForm.setWorkenddate(new SimpleDateFormat(
								"yyyy-MM-dd").parse(workenddateList[i]));
					}

					userWorkForm.setWorkplace(workplaceList[i]);
					userWorkForm.setWorkposition(workpositionList[i]);
					userWorkForm.setWorksalary(worksalaryList[i]);
					userWorkForm.setWorkleavereason(workleavereasonList[i]);
					userWorkForm.setWorkprove(workproveList[i]);
					workList.add(userWorkForm);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return workList;
	}

	/**
	 * 获取教育经历列表
	 * 
	 * @param req
	 * @return
	 */
	public List<UserEducateForm> getEducateList(HttpServletRequest req) {
		SubmitDataMap data = new SubmitDataMap(req);
		String[] schoolnameList = data.getParameterValues("userschoolname");
		String[] schoolstartdateList = data
				.getParameterValues("userschoolstartdate");
		String[] schoolenddateList = data
				.getParameterValues("userschoolenddate");
		String[] schoolmajorList = data.getParameterValues("userschoolmajor");
		String[] schooldegreeList = data.getParameterValues("userschooldegree");
		String[] schooltypeList = data.getParameterValues("userschooltype");
		List<UserEducateForm> eduList = new ArrayList<UserEducateForm>();
		if (schoolnameList != null) {
			int size = schoolnameList.length;
			for (int i = 0; i < size; i++) {
				try {
					int j = 0;
					if (schoolnameList[i] == null
							|| schoolnameList[i].equals("")) {
						j++;
					}
					if (schoolstartdateList[i] == null
							|| schoolstartdateList[i].equals("")) {
						j++;
					}
					if (schoolenddateList[i] == null
							|| schoolenddateList[i].equals("")) {
						j++;
					}
					if (schoolmajorList[i] == null
							|| schoolmajorList[i].equals("")) {
						j++;
					}
					if (schooldegreeList[i] == null
							|| schooldegreeList[i].equals("")) {
						j++;
					}
					if (schooltypeList[i] == null
							|| schooltypeList[i].equals("")) {
						j++;
					}
					if (j == 6)
						continue;
					UserEducateForm userEducateForm = new UserEducateForm();
					userEducateForm.setUserschoolname(schoolnameList[i]);
					if (schoolstartdateList[i] != "") {
						userEducateForm
								.setUserschoolstartdate(new SimpleDateFormat(
										"yyyy-MM-dd")
										.parse(schoolstartdateList[i]));
					}
					if (schoolenddateList[i] != "") {
						userEducateForm
								.setUserschoolenddate(new SimpleDateFormat(
										"yyyy-MM-dd")
										.parse(schoolenddateList[i]));
					}

					userEducateForm.setUserschoolmajor(schoolmajorList[i]);
					userEducateForm.setUserschooldegree(schooldegreeList[i]);
					userEducateForm.setUserschooltype(schooltypeList[i]);
					eduList.add(userEducateForm);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return eduList;
	}
	
	public boolean verifyEduAndWork(HttpServletRequest req) {
		SubmitDataMap data = new SubmitDataMap(req);
		
		String[] schoolnameList = data.getParameterValues("userschoolname");
		String[] schoolstartdateList = data
				.getParameterValues("userschoolstartdate");
		String[] schoolenddateList = data
				.getParameterValues("userschoolenddate");
		String[] schoolmajorList = data.getParameterValues("userschoolmajor");
		String[] schooldegreeList = data.getParameterValues("userschooldegree");
		String[] schooltypeList = data.getParameterValues("userschooltype");
		
		String[] workstartdateList = data.getParameterValues("workstartdate");
		String[] workenddateList = data.getParameterValues("workenddate");
		String[] workplaceList = data.getParameterValues("workplace");
		String[] workpositionList = data.getParameterValues("workposition");
		String[] worksalaryList = data.getParameterValues("worksalary");
		String[] workleavereasonList = data
				.getParameterValues("workleavereason");
		String[] workproveList = data.getParameterValues("workprove");
		
		if (schoolnameList[0].equals("")
				|| schoolstartdateList[0].equals("")
				|| schoolenddateList[0].equals("")
				|| schoolmajorList[0].equals("")
				||schooldegreeList[0].equals("")
				||schooltypeList[0].equals("")
				||workstartdateList[0].equals("")
				||workenddateList[0].equals("")
				||workplaceList[0].equals("")
				||workpositionList[0].equals("")
				||worksalaryList[0].equals("")
				||workleavereasonList[0].equals("")
				||workproveList[0].equals("")){
			
				return false;
		}
		else 
			return true;
	}

	public List<UserTrainForm> getTrainList(HttpServletRequest req) {
		SubmitDataMap data = new SubmitDataMap(req);
		String[] trainstartdateList = data.getParameterValues("trainstartdate");
		String[] trainenddateList = data.getParameterValues("trainenddate");
		String[] trainplaceList = data.getParameterValues("trainplace");
		String[] traincontentList = data.getParameterValues("traincontent");
		String[] traincertificateList = data.getParameterValues("traincertificate");

		List<UserTrainForm> trainList = new ArrayList<UserTrainForm>();
		if (traincontentList != null) {
			int size = traincontentList.length;
			for (int i = 0; i < size; i++) {
				try {
					int j = 0;
					if (trainstartdateList[i] == null
							|| trainstartdateList[i].equals("")) {
						j++;
					}
					if (trainenddateList[i] == null
							|| trainenddateList[i].equals("")) {
						j++;
					}
					if (trainplaceList[i] == null || trainplaceList[i].equals("")) {
						j++;
					}
					if (traincontentList[i] == null
							|| traincontentList[i].equals("")) {
						j++;
					}
					if (traincertificateList[i] == null
							|| traincertificateList[i].equals("")) {
						j++;
					}
					
					if (j == 5)
						continue;
					UserTrainForm userTrainForm = new UserTrainForm();
					if (trainstartdateList[i] != "") {
						userTrainForm.setTrainstartdate(new SimpleDateFormat(
								"yyyy-MM-dd").parse(trainstartdateList[i]));
					}
					if (trainenddateList[i] != "") {
						userTrainForm.setTrainenddate(new SimpleDateFormat(
								"yyyy-MM-dd").parse(trainenddateList[i]));
					}

					userTrainForm.setTrainplace(trainplaceList[i]);
					userTrainForm.setTraincontent(traincontentList[i]);
					userTrainForm.setTraincertificate(traincertificateList[i]);

					trainList.add(userTrainForm);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return trainList;
	}
	

	
	
	//将数据库中取出的日期所生成的字符串修改成框架需要的形式
	public String formatDateYYYYMMDD(String dateStr){
		return dateStr.substring(0, 10);
	}

	/**
	 * 账号与上级员工绑定。账号先在页面上输入
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryAccSup(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String account = req.getParameter("taraccount");
		String tp = req.getParameter("tp");
		String superiorid = req.getParameter("useremployid");
		String username = req.getParameter("username");
		String forward1 = "/userinfo/accsup/queryAcc.jsp";
		String forward2 = "/userinfo/accsup/bindAccSup.jsp";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		ActionForward af = new ActionForward();
		String sql = "select * from sc05 where bsc011 = ?";	

		try {
			
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            //查找账号是否存在
        	pstmt = con.prepareStatement(sql);
            pstmt.setString(1,account);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "该账号不存在！");
            	return mapping.findForward("queryAccSup");
            }
            if(tp.equals("acc")){
            //查询当前输入的工号是否有绑定账号
				String hql = "select s.bsc011 as taraccount,s.bsc012,a.superiorid as useremployid,u.username from sc05 s left join tbl_acc_sup a on a.account=s.bsc011 left join tbluser u on u.useremployid = a.superiorid where s.bsc011 = '" +account + "'";
				if(!("".equals(superiorid))){
					hql+=" and u.useremployid = '"+superiorid +"'";
				}
				af = super.init(req, forward1, hql);
            }else{
            	//查询当前输入的工号信息
				String hql = "select u.useremployid,u.username,s.bsc009 from tbluser u left join sc04 s on s.bsc008=u.userdepartmentid where 1=1 ";
				if(!("".equals(superiorid))){
					hql+=" and u.useremployid = '"+superiorid +"'";
				}
				if(!("".equals(username))){
					hql+=" and u.username= '"+username+"'";
				}
				af = super.init(req, forward2, hql);
            }
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}finally{
		 	DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
		}
		return af;
	}
	
	public ActionForward tobindAccSup(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm uf=(UserInfoForm) form;
		Connection con = DBUtil.getConnection();
		List result5 = (Vector) DBUtil.querySQL(con,
				"select * from tbl_acc_sup where account ='" + uf.getTaraccount() + "'");
		if(result5.size()>0){
			saveSuccessfulMsg(req, "该账号已经配置上级工号！");
			return mapping.findForward("queryAccSup");
		}
		//账号信息，判断是否绑定
		return mapping.findForward("bindAccSup");
	}
	
	public ActionForward bindAccSup(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String account = req.getParameter("acc");
		UserInfoForm uf=(UserInfoForm) form;
		UserInfo ui=new UserInfo();
		Connection con = DBUtil.getConnection();
		ClassHelper.copyProperties(uf, ui);
		ui.setAccount(account);
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap<String, Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("beo", ui);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.addAccSup(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(req, "增加记录成功!");
		} else {
			String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveErrors(req, new AppException(aa[3]));
		}
		//账号信息，判断是否绑定
		System.out.print("ok");
		return mapping.findForward("bindAccSup");
	}
	
	public ActionForward disbindAccSup(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserInfoForm uf=(UserInfoForm) form;
		UserInfo ui=new UserInfo();
		Connection con = DBUtil.getConnection();
		ClassHelper.copyProperties(uf, ui);
		ui.setAccount(uf.getTaraccount());
		UserInfoFacade facade = (UserInfoFacade) getService("UserInfoFacade");
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = new EventResponse();
		// 将Application对象放入HashMap
		HashMap<String, Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("beo", ui);
		// 将HashMap对象放入requestEnvelop
		requestEnvelop.setBody(mapRequest);
		// 调用对应的Facade业务处理方法
		ResponseEnvelop resEnv = facade.delAccSup(requestEnvelop);
		// 处理返回结果
		returnValue = processRevt(resEnv);
		if (returnValue.isSucessFlag()) {
			super.saveSuccessfulMsg(req, "删除记录成功!");
		} else {
			String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
					"|");
			super.saveErrors(req, new AppException(aa[3]));
		}
		//账号信息，判断是否绑定
		System.out.print("ok");
		return mapping.findForward("bindAccSup");
	}
}
