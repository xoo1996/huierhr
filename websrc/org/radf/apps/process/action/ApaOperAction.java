/**
 * CIOperAction.java 2008/03/27
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */

package org.radf.apps.process.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Applyprocess;///
import org.radf.apps.process.facade.ApaFacade;///
import org.radf.apps.process.form.ApaForm;///
import org.radf.apps.userinfo.form.UserEducateForm;
import org.radf.apps.userinfo.form.UserFamilyForm;
import org.radf.apps.userinfo.form.UserTrainForm;
import org.radf.apps.userinfo.form.UserWorkForm;
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

import com.cm.service.MailBean;

/**
 * 人员信息管理
 */
public class ApaOperAction extends ActionLeafSupport {

	/**
	 * 审核<br>
	 */
	public ActionForward verifyApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		ApaForm cf = (ApaForm) form;
		Applyprocess ap = new Applyprocess();
		String nemid = cf.getNemid();
		String nemstate = cf.getNemstate();
		String type = request.getParameter("type");
		if (nemstate.equals("dsp") || nemstate.equals("spz")) {
			// 设定经办信息
			ClassHelper.copyProperties(cf, ap);
			// 获取服务接口
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", ap);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// 审核处理
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			String id = dto1.getBsc011();
			Connection con = DBUtil.getConnection();
			//根据 当前账号 获取 工号，角色，下级审批人，角色id
			List result = (Vector) DBUtil
					.querySQL(
							con,
							"select a.userid,d.bsc015,e.superiorid,b.bsc008 from tbl_acc_user a left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010 left join sc06 d on d.bsc014=c.bsc014 left join tbl_sup_jun e on e.juniorid=a.userid where a.account='"
									+ id + "'");
			String entityid = (String) ((HashMap) result.get(0)).get("1");
			ap.setNemverifyid(entityid);
			ap.setAparole((String) ((HashMap) result.get(0)).get("2"));
			String nemsupid = (String) ((HashMap) result.get(0)).get("3");
			ap.setNemsupid(nemsupid);
			ap.setApaverifydt(DateUtil.getSystemCurrentTime());
			if (nemsupid == null) {
				saveSuccessfulMsg(request, "下级审批人为空！");
				return mapping.findForward("backspace");
			} else {
				ap.setAparesult("通过");
				// 流程判断 流程条件
				List result2 = (Vector) DBUtil.querySQL(con,
						"select nemcondition from tblnem where nemid='" + nemid
								+ "'");
				String condition = (String) ((HashMap) result2.get(0)).get("1");
				//查找下一级审批人的账号
				List result3 = (Vector) DBUtil.querySQL(con,
								"select account from tbl_acc_user where userid='" + nemsupid + "'");
				String acc = (String) ((HashMap) result3.get(0)).get("1");
				if (type.equals("nem")) {
					if (condition.equals("2")) {// rsbm
						if (checkrole(con,id,"924")
								&& ap.getNemtype().equals("zg")) {
							ap.setNemsupid("921");
						}
						if ((checkrole(con,id,"924") && ap.getNemtype().equals(
								"pt"))
								|| checkrole(con,id,"921")) {
							ap.setNemstate("tg");
							ap.setNemsupid(" ");
						} else
							ap.setNemstate("spz");
					} else {
						if (checkrole(con,id,"919")) {
							ap.setNemsupid("920");
						} else if (checkrole(con,id,"920")) {
							if(ap.getNemtype().equals("zg"))
								ap.setNemsupid("921");	
							else condition="1";
						}
						if ((checkrole(con,id,"920") && condition.equals("1"))
								|| checkrole(con,id,"921")) {
							ap.setNemstate("tg");
							ap.setNemsupid(" ");
						} else
							ap.setNemstate("spz");
					}
				} else if (type.equals("cvt")) { 
					if(condition.equals("0")){
						//总部发起的
						if (checkrole(con,id,"924")) {
							ap.setNemsupid("920");
							ap.setNemcondition("4");
						}
					}else {
						if(condition.equals("2")){
							//门店发起的经过培训部,
							List result4 = (Vector) DBUtil.querySQL(con,
								"select superiorid from tbl_sup_jun where juniorid='" + ap.getNemapplyid()+ "'");
							if (result4.size()==0) {
								saveSuccessfulMsg(request, "下级审批人为空！");
								return mapping.findForward("backspace");
							} else {
								ap.setNemsupid((String) ((HashMap) result4.get(0)).get("1"));
							}
						}else if (checkrole(con,id,"919")) {
							//区域经理发起或销售总经理发起,或被培训部审批通过
							ap.setNemsupid("920");
						}
						ap.setNemcondition("4");
					}
					if (checkrole(con,id,"920")&&condition.equals("4")) {// rsbm
						ap.setNemstate("tg");
						ap.setNemsupid(" ");
						ap.setUseremployeestatus("1");
						ap.setNememployid(ap.getNemapplyid());
					} else
						ap.setNemstate("spz");
				} else if (type.equals("rest")) {
					Date dt1 = cf.getRestsdt();
					Date dt2 = cf.getRestedt();
					long dt = dt2.getTime() - dt1.getTime();
					long day = dt / (1000 * 3600 * 24)+1;
					if ((condition.equals("0") && (day < 4) && checkrole(con,acc,"924"))||(condition.equals("2") && (day < 4) && checkrole(con,acc,"919"))){				
						ap.setNemsupid("920");
						ap.setNemcondition("4");
						condition="4";
					}
					if (checkrole(con,id,"919") || checkrole(con,id,"924")) {
						ap.setNemsupid("920");
						ap.setNemcondition("4");
					}
					if (checkrole(con,id,"920")&&condition.equals("4")) {// rsbm
						ap.setNemstate("tg");
						ap.setNemsupid(" ");
					} else
						ap.setNemstate("spz");
				} else if (type.equals("lea") || type.equals("leave")) {
					//根据申请人是不是总部员工 判断离职流程
					List result6 = (Vector) DBUtil
					
							.querySQL(
									con,
									"select a.bsc001 from sc04 a left join tbluser b on b.userdepartmentid=a.bsc008 where b.useremployid='"
											+ cf.getNemapplyid() + "'");
					String part = (String) ((HashMap) result6.get(0)).get("1");
					if (condition.equals("0")
							&& (checkrole(con,id,"919") || checkrole(con,id,"924"))) {
						if (ap.getNemtype().equals("zg")) {
							ap.setNemsupid("921");
						} else {
							ap.setNemsupid("0");
							ap.setNemstate("tg");
							ap.setNemcondition("4");
						}
					}
					if (condition.equals("0") && checkrole(con,id,"921")) {
						ap.setNemsupid("0");
						ap.setNemstate("tg");
						ap.setNemcondition("4");
					}
					//判断离职是否完成
					boolean a=false;
					if (condition.equals("4") && part != null
							&& part.equals("1501000000")) {
						a=true;
					} else if (condition.equals("4")) {
//						ap.setNemsupid("930");// swb
//						ap.setNemcondition("5");
						ap.setNemcondition("9");
//					} else if (condition.equals("5")) {
//						ap.setNemsupid("930");
//						ap.setNemcondition("6");// rsb
//					} else if (condition.equals("6")) {
//						ap.setNemsupid("930");
//						ap.setNemcondition("7");// wljsb
//					} else if (condition.equals("7")) {
//						ap.setNemsupid("930");
//						ap.setNemcondition("8");// tzb
//					} else if (condition.equals("8")) {
//						ap.setNemsupid("930");
//						ap.setNemcondition("9");// gyb
					} else if (condition.equals("9")) {
						a=true;
					}
					if(Integer.parseInt(condition)<10&&Integer.parseInt(condition)>4){
						String pn = (String) ((HashMap) result.get(0)).get("4");
						//根据部门选择更新审批时间
						List result4 = (Vector) DBUtil.querySQL(con,
								"select hqz from tblnem where nemid='" + ap.getNemid()+ "'");
						String hqz=(String) ((HashMap) result4.get(0)).get("1");
						if("582".equals(pn)){
							ap.setHqz(hqz(hqz,0));
							ap.setNemren1(DateUtil.getSystemCurrentTime());
						}else if("1149".equals(pn)){
							ap.setHqz(hqz(hqz,1));
							ap.setNemren2(DateUtil.getSystemCurrentTime());
						}else if("1161".equals(pn)){
							ap.setHqz(hqz(hqz,2));
							ap.setNemren3(DateUtil.getSystemCurrentTime());
						}else if("1151".equals(pn)){
							ap.setHqz(hqz(hqz,3));
							ap.setNemren4(DateUtil.getSystemCurrentTime());
						}else if("620".equals(pn)){
							ap.setHqz(hqz(hqz,4));
							ap.setNemren5(DateUtil.getSystemCurrentTime());
						}
					}else ap.setHqz("00000");
					if (a) {// rsbm
						ap.setNemstate("tg");
						ap.setNemsupid(" ");
						ap.setUseremployeestatus("0");
						ap.setNememployid(ap.getNemapplyid());
					} else
						ap.setNemstate("spz");
				} else if (type.equals("con")) {//
					if (checkrole(con,id,"919") || checkrole(con,id,"924")) {
						List result4 = (Vector) DBUtil.querySQL(con,
								"select c.bsc014,a.account from tbl_acc_user a left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010 where a.userid='" + ap.getNememployid()+ "'");
						String roleid=(String) ((HashMap) result4.get(0)).get("1");
							if("900".equals(roleid)){
								ap.setNemsupid((String) ((HashMap) result4.get(0)).get("2"));
								}else ap.setNemsupid(cf.getNememployid());//
						ap.setNemcondition("1");
					}
					if ("1".equals(condition)) {// 员工决定续签entityid.equals(cf.getNememployid())
						ap.setNemstate("tg");
						ap.setNemsupid(" ");
						ap.setUseremployeestatus("1");
						ap.setNemyn("yes");
					} else
						ap.setNemstate("spz");
				}else if (type.equals("user")) {//
					ap.setNemstate("tg");
					ap.setNemsupid(" ");
					ap.setUseremployeestatus(",");
				}
				if(type.equals("lea") || type.equals("leave")){;}else ap.setHqz("00000");
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.verifyApa(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					//sendMail(ap.getNemsupid(),con,ap.getHqz());
					super.saveSuccessfulMsg(request, "审核流程信息成功!");
					cf.setNemname(null);
					return mapping.findForward("wap");
				} else {
					String[] aa = StringUtil.getAsStringArray(
							returnValue.getMsg(), "|");
					super.saveSuccessfulMsg(request, aa[3]);
					return mapping.findForward("backspace");
				}
			}
		} else {
			saveSuccessfulMsg(request, "流程已审核，不能重复审核!");
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 回退<br>
	 */
	public ActionForward backApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		ApaForm cf = (ApaForm) form;
		Applyprocess Applyprocess = new Applyprocess();
		String nemstate = cf.getNemstate();
		String type = request.getParameter("type");
		if (nemstate.equals("dsp") || nemstate.equals("spz")) {
			// 设定经办信息
			ClassHelper.copyProperties(cf, Applyprocess);
			// 获取服务接口
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// 审核处理

			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			String id = dto1.getBsc011();
			Connection con = DBUtil.getConnection();
			//根据当前账号取出工号和角色
			List result = (Vector) DBUtil
					.querySQL(
							con,
							"select a.userid,d.bsc015,d.bsc014 from tbl_acc_user a left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010 left join sc06 d on d.bsc014=c.bsc014 where a.account='"
									+ id + "'");
			String entityid = (String) ((HashMap) result.get(0)).get("1");
			Applyprocess.setNemverifyid(entityid);
			Applyprocess.setNemstate("ht");
			Applyprocess.setNemsupid("");
			Applyprocess.setAparesult("不通过");
			Applyprocess
					.setAparole((String) ((HashMap) result.get(0)).get("2"));
			if (type.equals("con") && entityid.equals(cf.getNememployid())) {// 员工决定续签
				Applyprocess.setNemyn("no");
			}
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.verifyApa(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "回退流程成功!");
				cf.setNemname(null);
				return mapping.findForward("wap");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} else {
			saveSuccessfulMsg(request, "流程已审核，不能重复审核!");
			return mapping.findForward("backspace");
		}
	}

	/*
	 * new
	 */
	public ActionForward newApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ApaForm cf = (ApaForm) form;
		String type = req.getParameter("type");
		if (type.equals("contract")) {
			req.getSession().setAttribute("bsc009", cf.getBsc009());
			String useremployid = req.getParameter("useremployid");
			String username = req.getParameter("username");
			Connection con = DBUtil.getConnection();
			//根据工号取出身份证号与部门编号
			List result = (Vector) DBUtil
					.querySQL(
							con,
							"select useridno,userdepartmentid,userjoindate from tbluser where useremployid='"
									+ useremployid + "'");
			cf.setNemcard((String) ((HashMap) result.get(0)).get("1"));
			cf.setNempart((String) ((HashMap) result.get(0)).get("2"));
			cf.setNemworkdt((Date) ((HashMap) result.get(0)).get("3"));
			cf.setNemname(username);
			cf.setNememployid(useremployid);
			return mapping.findForward("new5");
		} else {
			req.getSession().setAttribute("nemname", cf.getNemname());
			req.getSession().setAttribute("nempart", cf.getNempart());
			req.getSession().setAttribute("bsc009", cf.getBsc009());
			req.getSession().setAttribute("nememployid", cf.getNememployid());
			req.getSession().setAttribute("nemapplyid", cf.getNemapplyid());
			if (type.equals("con")) {
				return mapping.findForward("new5");
			}else if (type.equals("lea")) {
				return mapping.findForward("new4");
			}else if (type.equals("cvt")) {
				Connection con = DBUtil.getConnection();
				List result = (Vector) DBUtil.querySQL(con,"select a.bsc001,u.userjoindate from sc04 a left join tbluser u on u.userdepartmentid=a.bsc008 where u.useremployid='"+ cf.getNemapplyid() + "'");
				req.getSession().setAttribute("part", (String) ((HashMap) result.get(0)).get("1"));
				cf.setCvtsdt((Date) ((HashMap) result.get(0)).get("2"));
				return mapping.findForward("new2");
			}else
				return mapping.findForward("new3");
			}
	}

	/**
	 * 配置项删除<br>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param actionForm
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * 
	 * @return 信息列表
	 * @throws 删除出错
	 */
	public ActionForward deleteApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		ApaForm fm = (ApaForm) form;
		String nemid = request.getParameter("nemid");
		String nemstate = request.getParameter("nemstate");
		String apatype = request.getParameter("apatype");
		Applyprocess Applyprocess = new Applyprocess();
		if (nemstate.equals("dsp") || nemstate.equals("spz")) {
			ClassHelper.copyProperties(fm, Applyprocess);
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", apatype);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.deleteApa(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "删除记录成功!");
				FindLog.insertLog(request, nemid, "删除流程信息");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
			}
			return mapping.findForward("pap");
		} else {
			saveSuccessfulMsg(request, "流程已审核，不能删除!");
			return mapping.findForward("backspace");
		}

	}

	/**
	 * 审核信息<br>
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		// String type = request.getParameter("type");
		ApaForm fm = (ApaForm) form;
		// String nemid = request.getParameter("nemid");
		Applyprocess ap = new Applyprocess();
		ActionForward af = new ActionForward();

		String forward = "/process/queryapa.jsp";
		try {
			ClassHelper.copyProperties(fm, ap);

			ap.setFileKey("ver_select");
			String hql = queryEnterprise(ap);
			af = super.init(request, forward, hql);
			// 检查是否存在？
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无记录！";
				super.saveSuccessfulMsg(request, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(request, ex);
		} catch (Exception e) {
			this.saveErrors(request, e);
		}
		return af;

	}

	/**
	 * 显示某配置项所有信息<br>
	 * 
	 * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param actionForm
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * 
	 * @return 配置项完整信息列表
	 * @throws 出错
	 */
	public ActionForward printApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ApaForm fm = (ApaForm) form;
		String nemid = request.getParameter("nemid");
		String apatype = request.getParameter("apatype");
		String type = request.getParameter("type");
		Applyprocess Applyprocess = new Applyprocess();
		Connection con = DBUtil.getConnection();
		String part=null;
		LoginDTO dto1 = (LoginDTO) request.getSession()
				.getAttribute("LoginDTO");
		String account = dto1.getBsc011();
		if("verify".equals(type)&&"lea".equals(apatype)){
			//当前账号如果是经理，取出他的部门id
			List result5 = (Vector) DBUtil.querySQL(con,
					"select a.bsc008 from sc05 a left join sc07 b on b.bsc010=a.bsc010 where b.bsc014 in ('923','920') and a.bsc011='" + account + "'");
			if(result5.size()>0)
				part = (String) ((HashMap) result5.get(0)).get("1");
		}
		//根据流程取出条件字段，工号，下级审批人
		List result = (Vector) DBUtil.querySQL(con,
				"select nemcondition,nememployid,nemsupid from tblnem where nemid='" + nemid + "'");
		String condition = (String) ((HashMap) result.get(0)).get("1");
		String employid = (String) ((HashMap) result.get(0)).get("2");
		if (type != null && type.equals("lea") && !apatype.equals("lea")) {
			saveSuccessfulMsg(request, "非离职流程，不能进行离职交接！");
			return mapping.findForward("pap");
		} else if (type != null && type.equals("lea") && apatype.equals("lea")
				&& (!condition.equals("4"))) {
			saveSuccessfulMsg(request, "上级审批尚未结束，不能进行离职交接！");
			return mapping.findForward("pap");
		} else {
			ClassHelper.copyProperties(fm, Applyprocess);
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", apatype);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.printApa(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");// 配置项信息
				request.getSession().setAttribute("listci", listci);
				ClassHelper.copyProperties(listci.get(0), fm);
				request.getSession().setAttribute("nempay",fm.getNempay());
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
			}
			if ("lea".equals(type)) {
				
				return mapping.findForward("leave");// 上传离职交接

			}  else if ("rzsx".equals(type)) {
				
				return mapping.findForward("look2");// 入职手续完成情况查询

			}  else if ("lzqk".equals(type)) {
				
				return mapping.findForward("detail4");// 离职情况查询

			}  else if (apatype.equals("user")) {
				//根据工号取出员工教育信息
				String sql = "select * from tblusereducate where useremployid = '"
						+ employid + "'";
				List result0 = (Vector) DBUtil.querySQL(con, sql);
				List<UserEducateForm> eduList = new ArrayList<UserEducateForm>();
				if (result0.size() > 0) {
					for (int i = 0; i < result0.size(); i++) {
						UserEducateForm educate = new UserEducateForm();
						Object a1 = ((HashMap) result0.get(i)).get("1");
						Object a2 = ((HashMap) result0.get(i)).get("2");
						Object a3 = ((HashMap) result0.get(i)).get("3");
						Object a4 = ((HashMap) result0.get(i)).get("4");
						Object a5 = ((HashMap) result0.get(i)).get("5");
						Object a6 = ((HashMap) result0.get(i)).get("6");
						Object a7 = ((HashMap) result0.get(i)).get("7");
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
				request.setAttribute("educateList", eduList);
				//根据工号取出家庭信息
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
				request.setAttribute("familyList", famList);
				//根据工号取出工作信息
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
				request.setAttribute("workList", workList);
				//根据工号取出培训信息
				String sql3 = "select * from tblusertrain where useremployid = '"
						+ employid + "'";
				List result3 = (Vector) DBUtil.querySQL(con, sql3);
				List<UserTrainForm> trainList = new ArrayList<UserTrainForm>();
				if (result3.size() > 0) {
					for (int i = 0; i < result3.size(); i++) {
						UserTrainForm train = new UserTrainForm();
						Object a1 = ((HashMap) result3.get(i)).get("1");
						Object a2 = ((HashMap) result3.get(i)).get("2");
						Object a3 = ((HashMap) result3.get(i)).get("3");
						Object a4 = ((HashMap) result3.get(i)).get("4");
						Object a5 = ((HashMap) result3.get(i)).get("5");
						Object a6 = ((HashMap) result3.get(i)).get("6");
						if (a2 != null) {
							train.setTrainstartdate((Date)a2);
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
				request.setAttribute("trainList", trainList);
				
				if ("verify".equals(type)){
					String supid = (String) ((HashMap) result.get(0)).get("3");
					if(supid.equals("925")){
						return mapping.findForward("look2");
					}else
						return mapping.findForward("verify6");
				}else  {
					return mapping.findForward("detail6");
				} 
			} else if ("verify".equals(type)) {
				List<ApaForm> resList = getResList(nemid);
				request.setAttribute("resList", resList);
				if (apatype.equals("nem")) {
					if(checkrole(con,account,"919")||checkrole(con,account,"918")||checkrole(con,account,"923")||checkrole(con,account,"924")){
						//如果审批人为销售总经理，则审批时填入薪资情况
						return mapping.findForward("verify7");
					}else return mapping.findForward("verify1");
				} else if (apatype.equals("cvt")) {
					if(!(condition.equals("2"))){
						//如果审批人为销售总经理，则审批时填入转正方案,转正时间
						//考虑培训部经理！！！
						return mapping.findForward("verify8");
					}else return mapping.findForward("verify2");
				} else if (apatype.equals("rest")) {
					return mapping.findForward("verify3");
				} else if (apatype.equals("lea")) {
					if (condition.equals("0") || condition.equals("1")
							|| condition.equals("3"))
						return mapping.findForward("verify4");
					else if(condition.equals("10")){
						return mapping.findForward("leave8");
					}else if((!condition.equals("4"))&&"582".equals(part)){
						return mapping.findForward("leave7");
					}else if((!condition.equals("4"))&&"1149".equals(part)){
						return mapping.findForward("leave3");
					}else if((!condition.equals("4"))&&"1161".equals(part)){
						return mapping.findForward("leave4");
					}else if((!condition.equals("4"))&&"1151".equals(part)){
						return mapping.findForward("leave5");
					}else if((!condition.equals("4"))&&"620".equals(part)){
						return mapping.findForward("leave6");
					}else
						return mapping.findForward("leave2");
				} 
				else {
					if (condition.equals("0"))
						return mapping.findForward("verify5");
					else
						return mapping.findForward("xq");
				}
			} else {
				List<ApaForm> resList = getResList(nemid);
				request.setAttribute("resList", resList);
				if (apatype.equals("nem")) {
					
					return mapping.findForward("detail1");
				} else if (apatype.equals("cvt")) {
					return mapping.findForward("detail2");
				} else if (apatype.equals("rest")) {
					return mapping.findForward("detail3");
				} else if (apatype.equals("lea")) {
					if (condition.equals("0") || condition.equals("4"))
						return mapping.findForward("detail4");
					else
						return mapping.findForward("look");
				} else {
					return mapping.findForward("detail5");
				}
			}
		}
	}

	/**
	 * 流程资源下载
	 * 
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
		// String sql =
		// "insert into tblres(id,attachment)values(?,EMPTY_BLOB())";
		String sql = "select * from tblprores where nemid = ? and id = ?";

		try {
			con = DBUtil.getJDBCConnection();
			DBUtil.beginTrans(con);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nemid);
			pstmt.setString(2, id);
			resultset = pstmt.executeQuery();
			if (resultset.next()) {
				Blob blob = resultset.getBlob(3);
				String fileName = resultset.getString(2);
				InputStream ins = blob.getBinaryStream();
				res.setContentType("application/unknown");
				res.addHeader("Content-Disposition", "attachment; filename=\""
						+ new String(fileName.getBytes("GBK"), "ISO8859_1")
						+ "\"");
				OutputStream outStream = res.getOutputStream();
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = ins.read(bytes)) != -1) {
					outStream.write(bytes, 0, len);
				}
				ins.close();
				outStream.close();
				outStream = null;
				con.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeStatement(pstmt);
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return null;
	}

	private ArrayList<ApaForm> getResList(String nemid) {
		Connection con = null;
		ArrayList<ApaForm> resList = new ArrayList<ApaForm>();
		try {
			con = DBUtil.getConnection();
			String sql = "select * from tblprores where nemid = '" + nemid
					+ "'";
			List result = (Vector) DBUtil.querySQL(con, sql);

			if (result.size() > 0) {
				for (int i = 0; i < result.size(); i++) {
					ApaForm proRes = new ApaForm();
					Object a1 = ((HashMap) result.get(i)).get("1");
					Object a2 = ((HashMap) result.get(i)).get("2");
					Object a3 = ((HashMap) result.get(i)).get("4");
					proRes.setNemid((a1 == null ? "" : a1.toString()));
					proRes.setName((a2 == null ? "" : a2.toString()));
					proRes.setId(a3 == null ? "" : a3.toString());
					resList.add(proRes);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return resList;
	}

	/**
	 * 修改某配置项所有信息<br>
	 * 
	 * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param actionForm
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * 
	 * @return 修改配置项完整信息列表
	 * @throws 出错
	 */
	public ActionForward modifyApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ApaForm fm = (ApaForm) form;
		String nemid = request.getParameter("nemid");
		String apatype = request.getParameter("apatype");
		String nemstate = fm.getNemstate();
		Applyprocess Applyprocess = new Applyprocess();
		if (nemstate.equals("dsp")||nemstate.equals("ht")) {
			ClassHelper.copyProperties(fm, Applyprocess);
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", apatype);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.printApa(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");// 配置项信息
				request.getSession().setAttribute("listci", listci);
				ClassHelper.copyProperties(listci.get(0), fm);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
			}
			if (apatype.equals("nem")) {
				return mapping.findForward("alter1");
			} else if (apatype.equals("cvt")) {
				return mapping.findForward("alter2");
			} else if (apatype.equals("rest")) {
				return mapping.findForward("alter3");
			} else if (apatype.equals("lea")) {
				return mapping.findForward("alter4");
			} else if (apatype.equals("con")) {
				return mapping.findForward("alter5");
			} else if (apatype.equals("user")) {
				return mapping.findForward("alter6");
			} else {
				return mapping.findForward("alter5");
			}
		} else {
			saveSuccessfulMsg(request, "流程已审批，不能修改！");
			return mapping.findForward("backspace");
		}
	}

	
	/**
	 * 
	 * 保存新增后的配置项信息<br>
	 * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param actionForm
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * 
	 * @return 新增配置项输入页面
	 * @throws 查询出错
	 */
	public ActionForward saveNewApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		ApaForm cf = (ApaForm) form;
		Applyprocess Applyprocess = new Applyprocess();
		String type = request.getParameter("type");
		String bsc008=cf.getNempart();
		String applyid=cf.getNememployid();
		Connection con = DBUtil.getConnection();
		//查看机构内码是否正确
		List result9 = (Vector) DBUtil.querySQL(con,"select bsc008 from sc04 where bsc008='"+ bsc008 + "'");
		//查看人员id是否正确
		List result7 = (Vector) DBUtil.querySQL(con,"select useremployid from tbluser where useremployid='"+ applyid + "'");
		try {
			if(bsc008!=null&&result9.size()==0){
				saveSuccessfulMsg(request, "未找到相关机构！");
				return mapping.findForward("backspace");
			}else if(applyid!=null&&result7.size()==0){
				saveSuccessfulMsg(request, "人员id输入错误！");
				return mapping.findForward("backspace");
			}else {
			// 设定经办信息
			ClassHelper.copyProperties(cf, Applyprocess);
			Applyprocess.setHqz("00000");
			// 获取服务接口
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// 存申请人id
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			String id = dto1.getBsc011();
			String nemid = cf.getNemid();
			//根据账号查出工号，下级审批人，角色id
			List result = (Vector) DBUtil
					.querySQL(
							con,
							"select a.userid,b.superiorid from tbl_acc_user a left join tbl_sup_jun b on b.juniorid=a.userid where a.account='"
									+ id + "'");
			String supid="",entityid="";
			if(result.size()>0){
				supid = (String) ((HashMap) result.get(0)).get("2");
				Applyprocess.setNemsupid(supid);
				entityid = (String) ((HashMap) result.get(0))
						.get("1");
			}
			if ((supid == ""||supid==null)&&(!(type.equals("user")||type.equals("nem")))) {
				saveSuccessfulMsg(request, "下级审批人为空！");
				return mapping.findForward("backspace");
			} else {
				if ((type.equals("leave"))) {
					if(nemid== null || nemid.isEmpty()){
						saveSuccessfulMsg(request, "主键为空！");
						return mapping.findForward("backspace");
					}
				}else{
					if(Applyprocess.getNemapplyid()==null){Applyprocess.setNemapplyid(entityid);}
					if(type.equals("con")){Applyprocess.setNemapplyid(cf.getNememployid());}
					Applyprocess.setNemverifyid(entityid);
					Applyprocess.setNemstate("dsp");
					Applyprocess.setNemappdt(DateUtil.getSystemCurrentTime());
					// nemid
					List result2 = (Vector) DBUtil.querySQL(con,
							"select SEQ_NEM.NEXTVAL from dual");
					BigDecimal aa = (BigDecimal) ((HashMap) result2.get(0))
							.get("1");
					nemid = aa.toString();
					Applyprocess.setNemid(nemid);
					Applyprocess.setNemcondition("0");
					//根据账号查出是否是总部
					List result3 = (Vector) DBUtil.querySQL(con,
									"select bsc001 from sc05 where bsc011='"
											+ id + "'");
					String part = (String) ((HashMap) result3.get(0))
							.get("1");
					String nempart = Applyprocess.getNempart();
					if (type.equals("nem")) {
						if (checkrole(con,id,"920")) {// rsbm 申请
							Applyprocess.setNemcondition("2");
							if (Applyprocess.getNemtype().equals("pt")) {
								//查出新员工对应的部门经理
								List result4 = (Vector) DBUtil.querySQL(con, 
												"select c.userid from sc05 a left join sc07 b on b.bsc010=a.bsc010 left join tbl_acc_user c on c.account=a.bsc011 where b.bsc014='923' and a.bsc008='"+ nempart + "'");
								if (result4.size()<1) {
									saveSuccessfulMsg(request, "输入部门的部门经理不存在！");
									return mapping.findForward("backspace");
								} else {Applyprocess.setNemsupid((String) ((HashMap) result4.get(0)).get("1"));}
							} else {
								// 查出对应的分管副总
								Applyprocess.setNemsupid(fenguan(nempart));
							}
						} else if (checkrole(con,id,"919")) {
							if(cf.getNemmon1().length()<1&&cf.getNemyear1().length()<1){
								saveSuccessfulMsg(request, "销售总经理新建录用流程必须填入薪资情况！");
								return mapping.findForward("backspace");
							}
							Applyprocess.setNemsupid("920");
							//销售总经理新建录用流程必须填入薪资情况
						} else {
							if(entityid==""){
								//根据门店所属区域查询销售总经理
								while(!(checkrole(con,id,"919"))){
									List result6 = (Vector) DBUtil.querySQL(con,
											"select u.userid,u.account from tbl_acc_sup a left join tbl_acc_user u on u.userid=a.superiorid where a.account='"+id+"'");
									if(result6.size()<1){
										saveSuccessfulMsg(request, "该门店所属区域的销售总经理不存在！");
										return mapping.findForward("backspace");
									}
									entityid=(String) ((HashMap) result6.get(0)).get("1");
									id=(String) ((HashMap) result6.get(0)).get("2");
								}
								Applyprocess.setNemapplyid(entityid);
								Applyprocess.setNemverifyid(entityid);
							}else{
								supid=entityid;
								List result6;
								while(!(checkrole(con,id,"919"))){
									//循环得到当前工号对应的销售总经理
									result6 = (Vector) DBUtil.querySQL(con,
											"select a.account,b.superiorid from tbl_acc_user a left join tbl_sup_jun b on b.juniorid=a.userid where a.userid='" + supid + "'");
									id=(String) ((HashMap) result6.get(0)).get("1");
									supid=(String) ((HashMap) result6.get(0)).get("2");
									if (supid==null) {
										saveSuccessfulMsg(request, "下级审批人为空！");
										return mapping.findForward("backspace");
									}
									if(checkrole(con,id,"919")) break;
									entityid=supid;
								}
							}
							Applyprocess.setNemsupid(entityid);
							Applyprocess.setNemtype("pt");
							// age
							String sex=cf.getNemsex();
							Date icdbt = cf.getNembirthdt();
							Calendar c = Calendar.getInstance();
							c.setTime(icdbt);
							int year1 = c.get(Calendar.YEAR);
							c.setTime(DateUtil.getSystemCurrentTime());
							int year2 = c.get(Calendar.YEAR);
							int age = year2 - year1;
							if ((age <= 38&&sex.equals("0"))) {//
								Applyprocess.setNemcondition("1");
							}
						}
					} else if (checkrole(con,id,"917") || checkrole(con,id,"918")
							|| checkrole(con,id,"923")) {
						// 申请人为主管或经理
						Applyprocess.setNemcondition("3");
					}else if (type.equals("cvt")) {					
						if ((!part.equals("1501000000"))){
							//修改   取消培训部审核
							/*Applyprocess.setNemsupid("925");
							Applyprocess.setNemcondition("2");*/
							List result4 = (Vector) DBUtil.querySQL(con,
									"select superiorid from tbl_sup_jun where juniorid='" + Applyprocess.getNemapplyid()+ "'");
								if (result4.size()==0) {
									saveSuccessfulMsg(request, "下级审批人为空！");
									return mapping.findForward("backspace");
								} else {
									Applyprocess.setNemsupid((String) ((HashMap) result4.get(0)).get("1"));
								}
							Applyprocess.setNemcondition("4");
							Applyprocess.setNemstate("spz");
						}
						
						
					} else if (type.equals("rest")) {
						List result4 = (Vector) DBUtil.querySQL(con,
								"select userjoindate from tbluser where useremployid='"
										+ Applyprocess.getNemapplyid() + "'");
						if("nx".equals(cf.getResttype())){
							if (result4.size()<1) {
								saveSuccessfulMsg(request, "该员工入职时间为空，请完善信息！");
								return mapping.findForward("backspace");
							}
							Date icdbt = (Date) ((HashMap) result4.get(0)).get("1");
							Calendar c = Calendar.getInstance();
							c.setTime(icdbt);
							int year1 = c.get(Calendar.YEAR)+1;
							SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
							Date day1 = dateFormat1.parse(year1+"-12-25");
							Date day2=DateUtil.getSystemCurrentTime();
							//工龄计算，所有员工入职当年，到12.25，工龄为0，到下一年的12.25，工龄为1
							if(day1.after(day2)){
								saveSuccessfulMsg(request, "工龄为0，无年休假！");
								return mapping.findForward("backspace");
							}
						}
						if (!part.equals("1501000000")){
							Applyprocess.setNemcondition("2");
						}
					} else if (type.equals("con")) {// 续签流程
						entityid = cf.getNememployid();
						List result4 = (Vector) DBUtil.querySQL(con,
								"select a.bsc001,b.positionname from sc04 a left join tbluser b on b.userdepartmentid=a.bsc008 where b.useremployid='"
										+ entityid + "'");
						part = (String) ((HashMap) result4.get(0)).get("1");
						String position=(String) ((HashMap) result4.get(0)).get("2");
						if(part.equals("1501000000")){
							if("8".equals(position)){
									//查出员工对应的上级
								List result5 = (Vector) DBUtil.querySQL(con, 
												"select c.userid from sc05 a left join sc07 b on b.bsc010=a.bsc010 left join tbl_acc_user c on c.account=a.bsc011 where b.bsc014='923' and a.bsc008='"+ nempart + "'");
								if (result5.size()<1) {
									if("1188".equals(nempart))Applyprocess.setNemsupid("HR10002");
									else{
										saveSuccessfulMsg(request, "输入部门的部门经理不存在！");
										return mapping.findForward("backspace");
									}
								} else {
									Applyprocess.setNemsupid((String) ((HashMap) result5.get(0)).get("1"));
								}
							}else{
								Applyprocess.setNemsupid(fenguan(nempart));
							}
						}else{
							supid=entityid;
							List result6;
							while(!(checkrole(con,id,"919"))){
								//循环得到当前工号对应的销售总经理
							result6 = (Vector) DBUtil.querySQL(con,
									"select a.account,b.superiorid from tbl_acc_user a left join tbl_sup_jun b on b.juniorid=a.userid where a.userid='" + supid + "'");
							id=(String) ((HashMap) result6.get(0)).get("1");
							supid=(String) ((HashMap) result6.get(0)).get("2");
							if(checkrole(con,id,"919")) break;
							entityid=supid;
							}
							Applyprocess.setNemsupid(entityid);
						}
					} 
					if (type.equals("user")) {// 入职流程
						
						//首先根据神份证号查询一下系统中是否已有申请
						String sql_verify_same = "SELECT u.useremployid FROM tbluser u LEFT JOIN tblnem m ON u.useremployid = m.nemapplyid WHERE  u.useridno = '"+cf.getUseridno()+"' AND m.nemstate <> 'ht'";						
						List verifySame = (Vector) DBUtil.querySQL(con,sql_verify_same);
						
						
						if(verifySame.size()!=0){
							String userEmployId=(String) ((HashMap) verifySame.get(0)).get("1");
							request.getSession().setAttribute("useremployid",userEmployId);
							request.getSession().setAttribute("username", cf.getUsername());
							res.sendRedirect("/huiermis/switchAction.do?prefix=/userinfo&page=/addMore.jsp");

						   return null;
						}
						
						List result8 = (Vector) DBUtil.querySQL(con,
								"select SEQ_USER.NEXTVAL from dual");
						BigDecimal aaa = (BigDecimal) ((HashMap) result8.get(0))
								.get("1");
						String employid = "HR"+aaa.toString();
						Date icdbt = cf.getUserjoindate();
						Calendar c = Calendar.getInstance();
						c.setTime(icdbt);
						int year1 = c.get(Calendar.YEAR);
						c.setTime(DateUtil.getSystemCurrentTime());
						int year2 = c.get(Calendar.YEAR);
						int age = year2 - year1;
						String workold=String.valueOf(age);
						Applyprocess.setUserworkold(workold);
						Applyprocess.setNemsupid("920");
						Applyprocess.setNemapplyid(employid);
						Applyprocess.setNemverifyid(employid);
						Applyprocess.setUseremployid(employid);
						Applyprocess.setNememployid(employid);
						Applyprocess.setAccount(id);
					} 
					if (type.equals("lea")) {
						Applyprocess.setNemcondition("0");
					}
				}
				 //多文件上传
				Connection conn = null;
				try {
					// 获取文件数目
					int size = cf.getFileCount();
					for (int i = 0; i < size; i++) {
						String name = cf.getFile(i).getFileName();
						conn = DBUtil.getJDBCConnection();
						PreparedStatement pstmt = null;
						String sql = "insert into tblprores(nemid,name,id,content)values(?,?,?,EMPTY_BLOB())";
						try {
							DBUtil.beginTrans(conn);
							// 插入数据excel表格
							// 1.预插入，为blob预留空间
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, nemid);
							pstmt.setString(2, name);
							String timeid = String
									.valueOf(new Date().getTime());
							pstmt.setString(3, timeid);
							pstmt.executeUpdate();
							pstmt = null;

							// 2.将blob字段内容插入
							String key = "id='" + timeid + "' and nemid ='"
									+ nemid + "'";
							DBUtil.saveBlob(conn, "tblprores", "content", key,
									cf.getFile(i).getFileData());
							DBUtil.commit(conn);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							DBUtil.closeStatement(pstmt);
							DBUtil.rollback(conn);
							DBUtil.closeConnection(conn);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 调用对应的Facade业务处理方法
				ResponseEnvelop resEnv = facade.saveApa(requestEnvelop);
				// 处理返回结果
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					//sendMail(Applyprocess.getNemsupid(),con,Applyprocess.getHqz());
					if ((type.equals("leave"))) {
						super.saveSuccessfulMsg(request, "上传离职子流程附件成功!");
					} else if ((type.equals("user"))) {
						super.saveSuccessfulMsg(request, "新同事欢迎您!人事部将尽快将劳动合同等其他入职资料寄给您所在惠耳点，收到后请填写完整并寄还人事部：1、寄还材料：劳动合同、学习答卷、身份证复印件、学历证书复印件、近半年入职体检寄回人事部；2、请上业务平台申请工作服");
					} else if ((type.equals("nem"))) {
						super.saveSuccessfulMsg(request, "1.请新员工在报到当天登陆门店账号办理入职手续；2.准备好入职资料（身份证、学历证复印件、个人建行卡、近半年内体检报告一份）");
					} else {
						super.saveSuccessfulMsg(request, "新增流程审批信息成功!");
					}
					// 获得新增的配置项代码
					String apaid = (String) ((HashMap) resEnv.getBody())
							.get("nemid");
					// 获得从业务层返回的日志信息
					String workString = (String) ((HashMap) resEnv.getBody())
							.get("workString");
					FindLog.insertLog(request, apaid, workString);
					// 下一个页面，还是配置项登记页面
					if(type.equals("user")){
						/*ActionForward af = new ActionForward();
						af.setPath("/SingleClientAction.do?method=queryDebug&ictid="
								+ umForm.getIctid());
						af.setPath("/switchAction.do?prefix=/client&page=/single/query.jsp");
					//	return */
						request.getSession().setAttribute("useremployid", Applyprocess.getUseremployid());
						request.getSession().setAttribute("username", Applyprocess.getUsername());
						res.sendRedirect("/huiermis/switchAction.do?prefix=/userinfo&page=/addMore.jsp");
//						res.sendRedirect("/huiermis/userinfo/UserInfoAction.do?method=toAddUser");
					   return null;

					}else
						return mapping.findForward("pap");
				} else {
					String[] aaa = StringUtil.getAsStringArray(
							returnValue.getMsg(), "|");
					super.saveSuccessfulMsg(request, aaa[3]);
					return mapping.findForward("backspace");
				}
			}
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}

	}

	/**
	 * 
	 * 保存修改后的配置项信息<br>
	 * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param actionForm
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * 
	 * @return 新增配置项输入页面
	 * @throws 查询出错
	 */
	public ActionForward saveModifiedApa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ApaForm cf = (ApaForm) form;
		Applyprocess Applyprocess = new Applyprocess();
		String type = request.getParameter("type");
		try {
			// 设定经办信息
			ClassHelper.copyProperties(cf, Applyprocess);
			// 获取服务接口
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modifyApa(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "修改流程信息成功!");
				cf.setNemid(null);
				cf.setNemname(null);
				return mapping.findForward("pap");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}

	}

	/**
	 * 查询配置项简略信息(支持模糊查询)
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param actionForm
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * 
	 * @return 配置项信息列表页面
	 * @throws 查询出错
	 */
	public ActionForward queryApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		ApaForm ApaForm = (ApaForm) form;
		Applyprocess ci = new Applyprocess();
		String type = req.getParameter("type");
		String forward1 = "/process/pap.jsp";
		String forward2 = "/process/wap.jsp";
		String forward3 = "/process/search.jsp";
		String forward4 = "/process/check.jsp";
		String forward5 = "/process/search2.jsp";
		String forward6 = "/process/search3.jsp";
		String forward7 = "/process/check2.jsp";
		String forward8 = "/process/search4.jsp";
		String forward9 = "/process/searchsalary.jsp";
		String forward10 = "/process/check3.jsp";
		String forward11 = "/process/check4.jsp";
		ActionForward af = new ActionForward();
		try {
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			String id = dto1.getBsc011();
			Connection con = DBUtil.getConnection();
			ClassHelper.copyProperties(ApaForm, ci);
			if ("verify".equals(type)) {
				String entityid = null;
				String roleid = null;
				int condition=0;//
				//根据当前账号获取角色信息
				List result = (Vector) DBUtil.querySQL(con,
								"select a.userid,c.bsc014 from tbl_acc_user a left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010 where a.account='"
										+ id + "'");
				if (result.size() > 0) {
					entityid = (String) ((HashMap) result.get(0)).get("1");
				}
				for(int i=0;i<result.size();i++){
					roleid = (String) ((HashMap) result.get(i)).get("2");
					if (roleid != null&& (roleid.equals("921")|| roleid.equals("925"))){
						condition=1;break;
					}else if(roleid.equals("920")){
						break;
					}else if(roleid.equals("930")){
						condition=2;break;
					}
				}
				//如果是人事部门，则还需要进行会签组审批
				if("920".equals(roleid)){
					ci.setNemverifyid(entityid);
					ci.setFileKey("ver_search3");
					//如果是培训部和总经理角色，则还需要进行角色审批
				}else if (condition==1) {
					ci.setNemapplyid(roleid);
					ci.setNemverifyid(entityid);
					ci.setFileKey("ver_search2");
					//如果是会签组，则还需要进行会签组审批
				} else if (condition==2) {
					ci.setNemapplyid("930");
					ci.setNemverifyid(entityid);
					List result2 = (Vector) DBUtil.querySQL(con,
							"select a.bsc008 from sc05 a where a.bsc011='"+id+"'");
					String bsc008=(String) ((HashMap) result2.get(0)).get("1");
					if("582".equals(bsc008)){
						ci.setFileKey("ver_search4");
					}else if("1161".equals(bsc008)){
						ci.setFileKey("ver_search5");
					}else if("1151".equals(bsc008)){
						ci.setFileKey("ver_search6");
					}else if("620".equals(bsc008)){
						ci.setFileKey("ver_search7");
					} 
				} else {
					//如果是门店续签或离职，nemsupid in (select userid from tbl_acc_user where account=)
					if (roleid == null||roleid.equals("900")) {
						ci.setNemverifyid(id);
					} else
						ci.setNemverifyid(entityid);
					ci.setFileKey("ver_search");
				}
				String hql = queryEnterprise(ci);
				af = super.init(req, forward2, hql);
			} else if ("rest".equals(type)) {
				ci.setNemverifyid(id);
				ci.setFileKey("rest_search");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward3, hql);
			} else if ("con".equals(type)) {
				ci.setFileKey("con_search");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward5, hql);
			} else if ("cvt".equals(type)) {
				ci.setNemverifyid(id);
				ci.setFileKey("cvt_search");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward8, hql);
			} else if ("salary".equals(type)) {
				ci.setFileKey("salary_search");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward9, hql);
			} else if ("lea".equals(type)) {
				ci.setNemverifyid(id);
				ci.setFileKey("lea_search");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward6, hql);
			} else if ("check".equals(type)) {
				ci.setNemstate("tg");
				ci.setFileKey("nem_check");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward4, hql);
			}else if ("seak".equals(type)) {
				ci.setFileKey("nem_seak");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward7, hql);
			} else if ("rzsx".equals(type)) {
				ci.setFileKey("nem_rzsx");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward10, hql);
			} else if ("lzqk".equals(type)) {
				ci.setFileKey("nem_lzqk");
				String hql = queryEnterprise(ci);
				af = super.init(req, forward11, hql);
			} else {
				ci.setNemverifyid(id);
				ci.setFileKey("nem_search");
				String hql = queryEnterprise(ci);
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
	//将数据库中取出的日期所生成的字符串修改成框架需要的形式
		public String formatDateYYYYMMDD(String dateStr){
			return dateStr.substring(0, 10);
		}		
		
		public String fenguan(String a){
			if(a.equals("1168")||a.equals("430")||a.equals("1151")||a.equals("1160")||a.equals("1161")||a.equals("1162")||
					a.equals("436")||a.equals("1163")||a.equals("1188")){
				return "HR10002";
			}else if(a.equals("582")||a.equals("1153")||a.equals("620")||a.equals("1139")||a.equals("1149")){
				return "HR10003";
			}else if(a.equals("1170")||a.equals("1292")){
				return "HR10007";
			}else if(a.equals("1165")||a.equals("1167")){
				return "HR10005";
			}else if(a.equals("1171")){
				return "HR10001";
			}else return "HR10002";
		}
		
		public boolean checkrole(Connection con,String acc,String role) throws Exception{
			String roleid = null;
			List result = (Vector) DBUtil.querySQL(con,
					"select c.bsc014 from sc05 b left join sc07 c on c.bsc010=b.bsc010 where b.bsc011='"
							+ acc + "'");
			for(int i=0;i<result.size();i++){
				roleid = (String) ((HashMap) result.get(i)).get("1");
				if (roleid != null&& roleid.equals(role)){
					return true;
				}
			}
			return false;
		}
		
		//会签组字段，根据不同部门改变字符串的值
		public String hqz(String hqz,int i){
			char[] h=hqz.toCharArray();
			h[i]='1';
			String s=new String(h);
			return s;
		}
		
		public void sendMail(String nemsupid,Connection con,String hqz){
			try{
				if("920".equals(nemsupid)||"921".equals(nemsupid)||"925".equals(nemsupid)){
					List result4 = (Vector) DBUtil.querySQL(con,"select u.useremail from tbluser u left join tbl_acc_user a on u.useremployid=a.userid left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010  where c.bsc014='"+nemsupid+"'");
					String email;
					for(int i=0;i<result4.size();i++){
						email=(String) ((HashMap) result4.get(i)).get("1");
						if(!("".equals(email))) MailBean.SendMail(email);
					}
				}else if("930".equals(nemsupid)){
					List result4 = (Vector) DBUtil.querySQL(con,"select u.useremail,u.userdepartmentid from tbluser u left join tbl_acc_user a on u.useremployid=a.userid left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010  where c.bsc014='"+nemsupid+"'");
					String email,part;
					char[] h=hqz.toCharArray();
					for(int i=0;i<result4.size();i++){
						email=(String) ((HashMap) result4.get(i)).get("1");
						part=(String) ((HashMap) result4.get(i)).get("2");
						if("582".equals(part)&h[0]=='1'){
							continue;
						}else if("1161".equals(part)&h[2]=='1'){
							continue;
						}else if("1151".equals(part)&h[3]=='1'){
							continue;
						}else if("620".equals(part)&h[4]=='1'){
							continue;
						}
						if(!(email==null||email.isEmpty())) MailBean.SendMail(email);
					}
				}else{
					List result4 = (Vector) DBUtil.querySQL(con,"select useremail from tbluser where useremployid='" + nemsupid+ "'");
					if(result4.size()>0){
						String email=(String) ((HashMap) result4.get(0)).get("1");
						MailBean.SendMail(email);
					}
				}
			}catch(Exception e){
				System.out.print("connect database error");
			}
		}
		
		public void localFileDownload(ActionMapping mapping, ActionForm form,
				HttpServletRequest req, HttpServletResponse res){
			BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
			
			String requiredname = req.getParameter("requiredname");
			String localname = req.getParameter("localname");
			String type = req.getParameter("type");
			String path = req.getSession().getServletContext().getRealPath("");

			  
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
	        res.setContentType("multipart/form-data");  
	        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
	        try {
				res.setHeader("Content-Disposition", "attachment;fileName="+ new String( requiredname.getBytes("gb2312"), "ISO8859-1" ));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
	        ServletOutputStream out;  
	        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
	        File file = new File(path + "/WEB-INF/download/" + type + "/" + localname);  
	        try {
				bis = new BufferedInputStream(new FileInputStream(file));
		        bos = new BufferedOutputStream(res.getOutputStream());
		        byte[] buff = new byte[2048];
		        while (true) {
		          int bytesRead;
		          if (-1 == (bytesRead = bis.read(buff, 0, buff.length))) break;
		          bos.write(buff, 0, bytesRead);
		        }
		        bis.close();
		        bos.close();
	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}