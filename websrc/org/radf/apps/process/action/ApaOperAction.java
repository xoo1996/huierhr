/**
 * CIOperAction.java 2008/03/27
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
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
 * ��Ա��Ϣ����
 */
public class ApaOperAction extends ActionLeafSupport {

	/**
	 * ���<br>
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
			// �趨������Ϣ
			ClassHelper.copyProperties(cf, ap);
			// ��ȡ����ӿ�
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", ap);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// ��˴���
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			String id = dto1.getBsc011();
			Connection con = DBUtil.getConnection();
			//���� ��ǰ�˺� ��ȡ ���ţ���ɫ���¼������ˣ���ɫid
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
				saveSuccessfulMsg(request, "�¼�������Ϊ�գ�");
				return mapping.findForward("backspace");
			} else {
				ap.setAparesult("ͨ��");
				// �����ж� ��������
				List result2 = (Vector) DBUtil.querySQL(con,
						"select nemcondition from tblnem where nemid='" + nemid
								+ "'");
				String condition = (String) ((HashMap) result2.get(0)).get("1");
				//������һ�������˵��˺�
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
						//�ܲ������
						if (checkrole(con,id,"924")) {
							ap.setNemsupid("920");
							ap.setNemcondition("4");
						}
					}else {
						if(condition.equals("2")){
							//�ŵ귢��ľ�����ѵ��,
							List result4 = (Vector) DBUtil.querySQL(con,
								"select superiorid from tbl_sup_jun where juniorid='" + ap.getNemapplyid()+ "'");
							if (result4.size()==0) {
								saveSuccessfulMsg(request, "�¼�������Ϊ�գ�");
								return mapping.findForward("backspace");
							} else {
								ap.setNemsupid((String) ((HashMap) result4.get(0)).get("1"));
							}
						}else if (checkrole(con,id,"919")) {
							//��������������ܾ�����,����ѵ������ͨ��
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
					//�����������ǲ����ܲ�Ա�� �ж���ְ����
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
					//�ж���ְ�Ƿ����
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
						//���ݲ���ѡ���������ʱ��
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
					if ("1".equals(condition)) {// Ա��������ǩentityid.equals(cf.getNememployid())
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
				// ���ö�Ӧ��Facadeҵ������
				ResponseEnvelop resEnv = facade.verifyApa(requestEnvelop);
				// �����ؽ��
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					//sendMail(ap.getNemsupid(),con,ap.getHqz());
					super.saveSuccessfulMsg(request, "���������Ϣ�ɹ�!");
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
			saveSuccessfulMsg(request, "��������ˣ������ظ����!");
			return mapping.findForward("backspace");
		}
	}

	/**
	 * ����<br>
	 */
	public ActionForward backApa(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse res)
			throws Exception {
		ApaForm cf = (ApaForm) form;
		Applyprocess Applyprocess = new Applyprocess();
		String nemstate = cf.getNemstate();
		String type = request.getParameter("type");
		if (nemstate.equals("dsp") || nemstate.equals("spz")) {
			// �趨������Ϣ
			ClassHelper.copyProperties(cf, Applyprocess);
			// ��ȡ����ӿ�
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// ��˴���

			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			String id = dto1.getBsc011();
			Connection con = DBUtil.getConnection();
			//���ݵ�ǰ�˺�ȡ�����źͽ�ɫ
			List result = (Vector) DBUtil
					.querySQL(
							con,
							"select a.userid,d.bsc015,d.bsc014 from tbl_acc_user a left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010 left join sc06 d on d.bsc014=c.bsc014 where a.account='"
									+ id + "'");
			String entityid = (String) ((HashMap) result.get(0)).get("1");
			Applyprocess.setNemverifyid(entityid);
			Applyprocess.setNemstate("ht");
			Applyprocess.setNemsupid("");
			Applyprocess.setAparesult("��ͨ��");
			Applyprocess
					.setAparole((String) ((HashMap) result.get(0)).get("2"));
			if (type.equals("con") && entityid.equals(cf.getNememployid())) {// Ա��������ǩ
				Applyprocess.setNemyn("no");
			}
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.verifyApa(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "�������̳ɹ�!");
				cf.setNemname(null);
				return mapping.findForward("wap");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(request, aa[3]);
				return mapping.findForward("backspace");
			}
		} else {
			saveSuccessfulMsg(request, "��������ˣ������ظ����!");
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
			//���ݹ���ȡ�����֤���벿�ű��
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
	 * ������ɾ��<br>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param actionForm
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * 
	 * @return ��Ϣ�б�
	 * @throws ɾ������
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", apatype);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.deleteApa(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "ɾ����¼�ɹ�!");
				FindLog.insertLog(request, nemid, "ɾ��������Ϣ");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
			}
			return mapping.findForward("pap");
		} else {
			saveSuccessfulMsg(request, "��������ˣ�����ɾ��!");
			return mapping.findForward("backspace");
		}

	}

	/**
	 * �����Ϣ<br>
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
			// ����Ƿ���ڣ�
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "���û����޼�¼��";
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
	 * ��ʾĳ������������Ϣ<br>
	 * 
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param actionForm
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * 
	 * @return ������������Ϣ�б�
	 * @throws ����
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
			//��ǰ�˺�����Ǿ���ȡ�����Ĳ���id
			List result5 = (Vector) DBUtil.querySQL(con,
					"select a.bsc008 from sc05 a left join sc07 b on b.bsc010=a.bsc010 where b.bsc014 in ('923','920') and a.bsc011='" + account + "'");
			if(result5.size()>0)
				part = (String) ((HashMap) result5.get(0)).get("1");
		}
		//��������ȡ�������ֶΣ����ţ��¼�������
		List result = (Vector) DBUtil.querySQL(con,
				"select nemcondition,nememployid,nemsupid from tblnem where nemid='" + nemid + "'");
		String condition = (String) ((HashMap) result.get(0)).get("1");
		String employid = (String) ((HashMap) result.get(0)).get("2");
		if (type != null && type.equals("lea") && !apatype.equals("lea")) {
			saveSuccessfulMsg(request, "����ְ���̣����ܽ�����ְ���ӣ�");
			return mapping.findForward("pap");
		} else if (type != null && type.equals("lea") && apatype.equals("lea")
				&& (!condition.equals("4"))) {
			saveSuccessfulMsg(request, "�ϼ�������δ���������ܽ�����ְ���ӣ�");
			return mapping.findForward("pap");
		} else {
			ClassHelper.copyProperties(fm, Applyprocess);
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", apatype);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.printApa(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");// ��������Ϣ
				request.getSession().setAttribute("listci", listci);
				ClassHelper.copyProperties(listci.get(0), fm);
				request.getSession().setAttribute("nempay",fm.getNempay());
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
			}
			if ("lea".equals(type)) {
				
				return mapping.findForward("leave");// �ϴ���ְ����

			}  else if ("rzsx".equals(type)) {
				
				return mapping.findForward("look2");// ��ְ������������ѯ

			}  else if ("lzqk".equals(type)) {
				
				return mapping.findForward("detail4");// ��ְ�����ѯ

			}  else if (apatype.equals("user")) {
				//���ݹ���ȡ��Ա��������Ϣ
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
				//���ݹ���ȡ����ͥ��Ϣ
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
				//���ݹ���ȡ��������Ϣ
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
				//���ݹ���ȡ����ѵ��Ϣ
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
						//���������Ϊ�����ܾ���������ʱ����н�����
						return mapping.findForward("verify7");
					}else return mapping.findForward("verify1");
				} else if (apatype.equals("cvt")) {
					if(!(condition.equals("2"))){
						//���������Ϊ�����ܾ���������ʱ����ת������,ת��ʱ��
						//������ѵ����������
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
	 * ������Դ����
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
	 * �޸�ĳ������������Ϣ<br>
	 * 
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param actionForm
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * 
	 * @return �޸�������������Ϣ�б�
	 * @throws ����
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", apatype);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.printApa(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List listci = (ArrayList) ((HashMap) resEnv.getBody())
						.get("beo");// ��������Ϣ
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
			saveSuccessfulMsg(request, "�����������������޸ģ�");
			return mapping.findForward("backspace");
		}
	}

	
	/**
	 * 
	 * �������������������Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param actionForm
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * 
	 * @return ��������������ҳ��
	 * @throws ��ѯ����
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
		//�鿴���������Ƿ���ȷ
		List result9 = (Vector) DBUtil.querySQL(con,"select bsc008 from sc04 where bsc008='"+ bsc008 + "'");
		//�鿴��Աid�Ƿ���ȷ
		List result7 = (Vector) DBUtil.querySQL(con,"select useremployid from tbluser where useremployid='"+ applyid + "'");
		try {
			if(bsc008!=null&&result9.size()==0){
				saveSuccessfulMsg(request, "δ�ҵ���ػ�����");
				return mapping.findForward("backspace");
			}else if(applyid!=null&&result7.size()==0){
				saveSuccessfulMsg(request, "��Աid�������");
				return mapping.findForward("backspace");
			}else {
			// �趨������Ϣ
			ClassHelper.copyProperties(cf, Applyprocess);
			Applyprocess.setHqz("00000");
			// ��ȡ����ӿ�
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// ��������id
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			String id = dto1.getBsc011();
			String nemid = cf.getNemid();
			//�����˺Ų�����ţ��¼������ˣ���ɫid
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
				saveSuccessfulMsg(request, "�¼�������Ϊ�գ�");
				return mapping.findForward("backspace");
			} else {
				if ((type.equals("leave"))) {
					if(nemid== null || nemid.isEmpty()){
						saveSuccessfulMsg(request, "����Ϊ�գ�");
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
					//�����˺Ų���Ƿ����ܲ�
					List result3 = (Vector) DBUtil.querySQL(con,
									"select bsc001 from sc05 where bsc011='"
											+ id + "'");
					String part = (String) ((HashMap) result3.get(0))
							.get("1");
					String nempart = Applyprocess.getNempart();
					if (type.equals("nem")) {
						if (checkrole(con,id,"920")) {// rsbm ����
							Applyprocess.setNemcondition("2");
							if (Applyprocess.getNemtype().equals("pt")) {
								//�����Ա����Ӧ�Ĳ��ž���
								List result4 = (Vector) DBUtil.querySQL(con, 
												"select c.userid from sc05 a left join sc07 b on b.bsc010=a.bsc010 left join tbl_acc_user c on c.account=a.bsc011 where b.bsc014='923' and a.bsc008='"+ nempart + "'");
								if (result4.size()<1) {
									saveSuccessfulMsg(request, "���벿�ŵĲ��ž������ڣ�");
									return mapping.findForward("backspace");
								} else {Applyprocess.setNemsupid((String) ((HashMap) result4.get(0)).get("1"));}
							} else {
								// �����Ӧ�ķֹܸ���
								Applyprocess.setNemsupid(fenguan(nempart));
							}
						} else if (checkrole(con,id,"919")) {
							if(cf.getNemmon1().length()<1&&cf.getNemyear1().length()<1){
								saveSuccessfulMsg(request, "�����ܾ����½�¼�����̱�������н�������");
								return mapping.findForward("backspace");
							}
							Applyprocess.setNemsupid("920");
							//�����ܾ����½�¼�����̱�������н�����
						} else {
							if(entityid==""){
								//�����ŵ����������ѯ�����ܾ���
								while(!(checkrole(con,id,"919"))){
									List result6 = (Vector) DBUtil.querySQL(con,
											"select u.userid,u.account from tbl_acc_sup a left join tbl_acc_user u on u.userid=a.superiorid where a.account='"+id+"'");
									if(result6.size()<1){
										saveSuccessfulMsg(request, "���ŵ���������������ܾ������ڣ�");
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
									//ѭ���õ���ǰ���Ŷ�Ӧ�������ܾ���
									result6 = (Vector) DBUtil.querySQL(con,
											"select a.account,b.superiorid from tbl_acc_user a left join tbl_sup_jun b on b.juniorid=a.userid where a.userid='" + supid + "'");
									id=(String) ((HashMap) result6.get(0)).get("1");
									supid=(String) ((HashMap) result6.get(0)).get("2");
									if (supid==null) {
										saveSuccessfulMsg(request, "�¼�������Ϊ�գ�");
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
						// ������Ϊ���ܻ���
						Applyprocess.setNemcondition("3");
					}else if (type.equals("cvt")) {					
						if ((!part.equals("1501000000"))){
							//�޸�   ȡ����ѵ�����
							/*Applyprocess.setNemsupid("925");
							Applyprocess.setNemcondition("2");*/
							List result4 = (Vector) DBUtil.querySQL(con,
									"select superiorid from tbl_sup_jun where juniorid='" + Applyprocess.getNemapplyid()+ "'");
								if (result4.size()==0) {
									saveSuccessfulMsg(request, "�¼�������Ϊ�գ�");
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
								saveSuccessfulMsg(request, "��Ա����ְʱ��Ϊ�գ���������Ϣ��");
								return mapping.findForward("backspace");
							}
							Date icdbt = (Date) ((HashMap) result4.get(0)).get("1");
							Calendar c = Calendar.getInstance();
							c.setTime(icdbt);
							int year1 = c.get(Calendar.YEAR)+1;
							SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
							Date day1 = dateFormat1.parse(year1+"-12-25");
							Date day2=DateUtil.getSystemCurrentTime();
							//������㣬����Ա����ְ���꣬��12.25������Ϊ0������һ���12.25������Ϊ1
							if(day1.after(day2)){
								saveSuccessfulMsg(request, "����Ϊ0�������ݼ٣�");
								return mapping.findForward("backspace");
							}
						}
						if (!part.equals("1501000000")){
							Applyprocess.setNemcondition("2");
						}
					} else if (type.equals("con")) {// ��ǩ����
						entityid = cf.getNememployid();
						List result4 = (Vector) DBUtil.querySQL(con,
								"select a.bsc001,b.positionname from sc04 a left join tbluser b on b.userdepartmentid=a.bsc008 where b.useremployid='"
										+ entityid + "'");
						part = (String) ((HashMap) result4.get(0)).get("1");
						String position=(String) ((HashMap) result4.get(0)).get("2");
						if(part.equals("1501000000")){
							if("8".equals(position)){
									//���Ա����Ӧ���ϼ�
								List result5 = (Vector) DBUtil.querySQL(con, 
												"select c.userid from sc05 a left join sc07 b on b.bsc010=a.bsc010 left join tbl_acc_user c on c.account=a.bsc011 where b.bsc014='923' and a.bsc008='"+ nempart + "'");
								if (result5.size()<1) {
									if("1188".equals(nempart))Applyprocess.setNemsupid("HR10002");
									else{
										saveSuccessfulMsg(request, "���벿�ŵĲ��ž������ڣ�");
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
								//ѭ���õ���ǰ���Ŷ�Ӧ�������ܾ���
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
					if (type.equals("user")) {// ��ְ����
						
						//���ȸ������֤�Ų�ѯһ��ϵͳ���Ƿ���������
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
				 //���ļ��ϴ�
				Connection conn = null;
				try {
					// ��ȡ�ļ���Ŀ
					int size = cf.getFileCount();
					for (int i = 0; i < size; i++) {
						String name = cf.getFile(i).getFileName();
						conn = DBUtil.getJDBCConnection();
						PreparedStatement pstmt = null;
						String sql = "insert into tblprores(nemid,name,id,content)values(?,?,?,EMPTY_BLOB())";
						try {
							DBUtil.beginTrans(conn);
							// ��������excel���
							// 1.Ԥ���룬ΪblobԤ���ռ�
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, nemid);
							pstmt.setString(2, name);
							String timeid = String
									.valueOf(new Date().getTime());
							pstmt.setString(3, timeid);
							pstmt.executeUpdate();
							pstmt = null;

							// 2.��blob�ֶ����ݲ���
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
				// ���ö�Ӧ��Facadeҵ������
				ResponseEnvelop resEnv = facade.saveApa(requestEnvelop);
				// �����ؽ��
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					//sendMail(Applyprocess.getNemsupid(),con,Applyprocess.getHqz());
					if ((type.equals("leave"))) {
						super.saveSuccessfulMsg(request, "�ϴ���ְ�����̸����ɹ�!");
					} else if ((type.equals("user"))) {
						super.saveSuccessfulMsg(request, "��ͬ�»�ӭ��!���²������콫�Ͷ���ͬ��������ְ���ϼĸ������ڻݶ��㣬�յ�������д�������Ļ����²���1���Ļ����ϣ��Ͷ���ͬ��ѧϰ������֤��ӡ����ѧ��֤�鸴ӡ������������ְ���Ļ����²���2������ҵ��ƽ̨���빤����");
					} else if ((type.equals("nem"))) {
						super.saveSuccessfulMsg(request, "1.����Ա���ڱ��������½�ŵ��˺Ű�����ְ������2.׼������ְ���ϣ����֤��ѧ��֤��ӡ�������˽��п�������������챨��һ�ݣ�");
					} else {
						super.saveSuccessfulMsg(request, "��������������Ϣ�ɹ�!");
					}
					// ������������������
					String apaid = (String) ((HashMap) resEnv.getBody())
							.get("nemid");
					// ��ô�ҵ��㷵�ص���־��Ϣ
					String workString = (String) ((HashMap) resEnv.getBody())
							.get("workString");
					FindLog.insertLog(request, apaid, workString);
					// ��һ��ҳ�棬����������Ǽ�ҳ��
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
	 * �����޸ĺ����������Ϣ<br>
	 * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param actionForm
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * 
	 * @return ��������������ҳ��
	 * @throws ��ѯ����
	 */
	public ActionForward saveModifiedApa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ApaForm cf = (ApaForm) form;
		Applyprocess Applyprocess = new Applyprocess();
		String type = request.getParameter("type");
		try {
			// �趨������Ϣ
			ClassHelper.copyProperties(cf, Applyprocess);
			// ��ȡ����ӿ�
			ApaFacade facade = (ApaFacade) getService("ApaFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", Applyprocess);
			mapRequest.put("beo3", type);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.modifyApa(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "�޸�������Ϣ�ɹ�!");
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
	 * ��ѯ�����������Ϣ(֧��ģ����ѯ)
	 * 
	 * @param mapping
	 *            ��ӳ����
	 * @param actionForm
	 *            ������ʵ��
	 * @param request
	 *            �ͻ��˵�HTTP��������
	 * @param response
	 *            �ͻ��˵�HTTP��������
	 * 
	 * @return ��������Ϣ�б�ҳ��
	 * @throws ��ѯ����
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
				//���ݵ�ǰ�˺Ż�ȡ��ɫ��Ϣ
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
				//��������²��ţ�����Ҫ���л�ǩ������
				if("920".equals(roleid)){
					ci.setNemverifyid(entityid);
					ci.setFileKey("ver_search3");
					//�������ѵ�����ܾ����ɫ������Ҫ���н�ɫ����
				}else if (condition==1) {
					ci.setNemapplyid(roleid);
					ci.setNemverifyid(entityid);
					ci.setFileKey("ver_search2");
					//����ǻ�ǩ�飬����Ҫ���л�ǩ������
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
					//������ŵ���ǩ����ְ��nemsupid in (select userid from tbl_acc_user where account=)
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
	//�����ݿ���ȡ�������������ɵ��ַ����޸ĳɿ����Ҫ����ʽ
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
		
		//��ǩ���ֶΣ����ݲ�ͬ���Ÿı��ַ�����ֵ
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

			  
	        //1.�����ļ�ContentType���ͣ��������ã����Զ��ж������ļ�����  
	        res.setContentType("multipart/form-data");  
	        //2.�����ļ�ͷ�����һ�����������������ļ���(�������ǽ�a.pdf)  
	        try {
				res.setHeader("Content-Disposition", "attachment;fileName="+ new String( requiredname.getBytes("gb2312"), "ISO8859-1" ));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
	        ServletOutputStream out;  
	        //ͨ���ļ�·�����File����(�����·������һ��download.pdf�ļ�)  
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