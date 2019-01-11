/**
 * Rec0305Action.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.query.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.recommendation.query.dto.Rec0305DTO;
import org.radf.apps.recommendation.query.form.Rec0305Form;
import org.radf.apps.recommendation.query.form.Rec03Form;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.facade.FACADESupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * 图形化分类统计
 */
public class Rec0305Action extends ActionLeafSupport {

	public Rec0305Action() {
		super();

	}

	/**
	 * 查询跳转
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 查询页面
	 * @throws 查询出错
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String menuid = request.getParameter("menuId");
		request.getSession().setAttribute("menuId", menuid);
	//	String type = request.getParameter("type");
	//	request.getSession().setAttribute("type", type);
		String forward = menuid;
		Date date1 = DateUtil.getDate(DateUtil.getYear(), 1, 1);
		Date date2 = DateUtil.getDate();
		Rec0305Form fm = new Rec0305Form();
		fm.setDate01(date1.toString());
		fm.setDate02(date2.toString());
		ClassHelper.copyProperties(fm, actionForm);
		return mapping.findForward(forward);
	}

	/**
	 * 求职分类统计
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 分类统计
	 * @throws 分类统计出错
	 */
	public ActionForward queryStat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取页面查询条件
		ActionForward af = new ActionForward();
		Rec0305Form fm = (Rec0305Form) actionForm;
		if (fm.getCce001() == null || "".equals(fm.getCce001())) {
			fm.setCce001("-1");
		}
		if (fm.getBsc006() == null || "".equals(fm.getBsc006())) {
			fm.setBsc006("-1");
		}
		Rec0305DTO dto = new Rec0305DTO();
		Rec0305DTO ndto = new Rec0305DTO();
		Rec0305DTO lsdto = null;
		String tmp001 = "AAC011";
		// ArrayList statls = new ArrayList();
		try {

			// 处理页面数据
			ClassHelper.copyProperties(fm, dto);
			if (fm.getDate02() != null && !"".equals(fm.getDate02())) {
				dto.setDate02(DateUtil.getStepDay(dto.getDate02(), 1));
			}

			String forward = "/recommendation/query/querychart.jsp";
			//String type = (String) request.getSession().getAttribute("type");
			String countsql = "";// 分页组件查询
			if ("CC20.AAC011".equalsIgnoreCase(dto.getStat01())
					|| "CC20.BAC299".equalsIgnoreCase(dto.getStat01())
					|| "CC21.AAC048".equalsIgnoreCase(dto.getStat01())
					|| "CC21.ACA111".equalsIgnoreCase(dto.getStat01())
					|| "CC21.AAB019".equalsIgnoreCase(dto.getStat01())
					|| "CC21.AAB020".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC009".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC033".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC004".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC005".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC024".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC017".equalsIgnoreCase(dto.getStat01())) {
				countsql = "select count(*) as total from cc20,cc21,ac01 where ac01.aac001=cc20.aac001 and cc20.acc200=cc21.acc200 "
						+ "and cc21.aca111 is not null and f_getdata_area('"
						+ fm.getCce001()
						+ "',cc21.aae017,1)='1'"
						+ " and f_get_bsc003(cc20.aae017,'"
						+ fm.getBsc006()
						+ "')='1'"
						+ " and cc20.aae043>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cc20.aae043<to_date('"
						+ fm.getDate02() + "','yyyy-MM-dd')+1 ";
			} else if ("AB01.AAB019".equalsIgnoreCase(dto.getStat01())
					|| "AB01.AAB020".equalsIgnoreCase(dto.getStat01())
					|| "AB01.AAB048".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC048".equalsIgnoreCase(dto.getStat01())
					|| "CB21.BAC299".equalsIgnoreCase(dto.getStat01())
					|| "CB21.ACA111".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC004".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC009".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC014".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC015".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC017".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC024".equalsIgnoreCase(dto.getStat01())) {
				countsql = "select sum(cb21.acb21d) as total from cb20,ab01,cb21 where ab01.aab001=cb20.aab001 "
						+ "and cb20.acb200=cb21.acb200 and f_getdata_area('"
						+ fm.getCce001()
						+ "',cb20.aae017,1)='1'"
						+ " and f_get_bsc003(cb20.aae017,'"
						+ fm.getBsc006()
						+ "')='1'"
						+ " and cb20.aae043>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cb20.aae043<to_date('"
						+ fm.getDate02() + "','yyyy-MM-dd')+1 ";
			}
			String total = "";// 总备案数

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", countsql);
			mapRequest.put("count", "10");
			mapRequest.put("offset", "0");
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			FACADESupport facade = (FACADESupport) getService("FACADESupport");
			ResponseEnvelop resEnv = facade.findBySQL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody())
						.get("collection");
				ClassHelper.copyProperties(list.get(0), ndto);
				total = ndto.getTotal();

			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				super.saveErrors(request, new AppException(errorMsg[3]));
				return mapping.findForward("backspace");
			}

			String queryhql = "";// 分页组件查询
			String chartsql = "";// 图表查询
			String strtype = "";
			int num = 0;
			String strtable = "AA10";
			if ("CC20.AAC011".equalsIgnoreCase(dto.getStat01())) {
				strtype = "10";
			} else if ("CC20.BAC299".equalsIgnoreCase(dto.getStat01())) {
				strtype = "4";
			} else if ("CC21.AAC048".equalsIgnoreCase(dto.getStat01())) {
				strtype = "900";
			} else if ("CC21.ACA111".equalsIgnoreCase(dto.getStat01())) {
				strtype = "061";
				strtable = "CA11";
				num = 10;
			} else if ("CC21.AAB019".equalsIgnoreCase(dto.getStat01())) {
				strtype = "90";
			} else if ("CC21.AAB020".equalsIgnoreCase(dto.getStat01())) {
				strtype = "900";
			} else if ("AC01.AAC009".equalsIgnoreCase(dto.getStat01())) {
				strtype = "90";
			} else if ("AC01.AAC033".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("AC01.AAC004".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("AC01.AAC005".equalsIgnoreCase(dto.getStat01())) {
				num = 10;
				strtype = "99";
			} else if ("AC01.AAC024".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("AC01.AAC017".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("AB01.AAB019".equalsIgnoreCase(dto.getStat01())) {
				strtype = "90";
			} else if ("AB01.AAB020".equalsIgnoreCase(dto.getStat01())) {
				strtype = "900";
			} else if ("AB01.AAB048".equalsIgnoreCase(dto.getStat01())) {
				strtype = "99";
			} else if ("CB21.AAC048".equalsIgnoreCase(dto.getStat01())) {
				strtype = "900";
			} else if ("CB21.BAC299".equalsIgnoreCase(dto.getStat01())) {
				strtype = "4";
			} else if ("CB21.ACA111".equalsIgnoreCase(dto.getStat01())) {
				num = 10;
				strtable = "CA11";
				strtype = "061";
			} else if ("CB21.AAC004".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("CB21.AAC009".equalsIgnoreCase(dto.getStat01())) {
				strtype = "90";
			} else if ("CB21.AAC014".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("CB21.AAC015".equalsIgnoreCase(dto.getStat01())) {
				strtype = "A";
			} else if ("CB21.AAC017".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("CB21.AAC024".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			}

				queryhql = "select decode("
						+ dto.getStat01()
						+ ",null,'"
						+ strtype
						+ "',"
						+ dto.getStat01()
						+ ") as "
						+ dto.getStat01().substring(5)
						+ ",count(*) as tmp002, trunc(count(*)/"
						+ total
						+ "*100,2)||'%' as tmp003,"
						+ "trunc(sum(decode(cc21.acc034,null,'0',cc21.acc034))/"
						+ "decode(sum(decode(cc21.acc034, null, '0', '1')),'0','1',sum(decode(cc21.acc034, null, '0', '1'))),2) as tmp004"
						+ " from cc20,cc21,ac01 where cc20.acc200=cc21.acc200 and ac01.aac001=cc20.aac001 and cc21.aca111 is not null and f_getdata_area('"
						+ fm.getCce001() + "',cc20.aae017,1)='1'"
						+ " and f_get_bsc003(cc20.aae017,'" + fm.getBsc006()
						+ "')='1'" + " and cc20.aae043>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cc20.aae043<to_date('"
						+ fm.getDate02()
						+ "','yyyy-MM-dd')+1  group by decode("
						+ dto.getStat01() + ",null,'" + strtype + "',"
						+ dto.getStat01() + ") order by count(*) desc ";
				chartsql = "select f_chart_chinese('"
						+ fm.getStat01()
						+ "',decode("
						+ fm.getStat01()
						+ ",null,'"
						+ strtype
						+ "',"
						+ dto.getStat01()
						+ "),'"
						+ strtable
						+ "')||trunc(count(*)/"
						+ total
						+ "*100,2)||'%',count(*) as tmp002  from cc20,cc21,ac01 where "
						+ "cc20.acc200=cc21.acc200 and ac01.aac001=cc20.aac001 "
						+ "and cc21.aca111 is not null and f_getdata_area('"
						+ fm.getCce001() + "',cc20.aae017,1)='1'"
						+ " and f_get_bsc003(cc20.aae017,'" + fm.getBsc006()
						+ "')='1'" + " and cc20.aae043>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cc20.aae043<to_date('"
						+ fm.getDate02() + "','yyyy-MM-dd')+1 group by decode("
						+ dto.getStat01() + ",null,'" + strtype + "',"
						+ dto.getStat01() + ")  order by count(*) desc ";

			if (num > 0) {
				queryhql = "select * from (" + queryhql + ") where rownum<="
						+ num;
				chartsql = "select * from (" + chartsql + ") where rownum<="
						+ num;
			}

			af = init(request, forward, queryhql, "1");
			request.getSession().setAttribute("chartname", "职业介绍统计图表");
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(request, msg);
				request.getSession().setAttribute("chartsql", null);
			} else {
				request.getSession().setAttribute("chartsql", chartsql);

			}
			request.getSession().setAttribute("tmp001", dto.getStat01());

		} catch (AppException e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}
	/**
	 * 招工分类统计
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 分类统计
	 * @throws 分类统计出错
	 */
	public ActionForward queryStat2(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取页面查询条件
		ActionForward af = new ActionForward();
		Rec0305Form fm = (Rec0305Form) actionForm;
		if (fm.getCce001() == null || "".equals(fm.getCce001())) {
			fm.setCce001("-1");
		}
		if (fm.getBsc006() == null || "".equals(fm.getBsc006())) {
			fm.setBsc006("-1");
		}
		Rec0305DTO dto = new Rec0305DTO();
		Rec0305DTO ndto = new Rec0305DTO();
		Rec0305DTO lsdto = null;
		String tmp001 = "AAC011";
		// ArrayList statls = new ArrayList();
		try {

			// 处理页面数据
			ClassHelper.copyProperties(fm, dto);
			if (fm.getDate02() != null && !"".equals(fm.getDate02())) {
				dto.setDate02(DateUtil.getStepDay(dto.getDate02(), 1));
			}

			String forward = "/recommendation/query/querychart.jsp";
			String countsql = "";// 分页组件查询
			if ("CC20.AAC011".equalsIgnoreCase(dto.getStat01())
					|| "CC20.BAC299".equalsIgnoreCase(dto.getStat01())
					|| "CC21.AAC048".equalsIgnoreCase(dto.getStat01())
					|| "CC21.ACA111".equalsIgnoreCase(dto.getStat01())
					|| "CC21.AAB019".equalsIgnoreCase(dto.getStat01())
					|| "CC21.AAB020".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC009".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC033".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC004".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC005".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC024".equalsIgnoreCase(dto.getStat01())
					|| "AC01.AAC017".equalsIgnoreCase(dto.getStat01())) {
				countsql = "select count(*) as total from cc20,cc21,ac01 where ac01.aac001=cc20.aac001 and cc20.acc200=cc21.acc200 "
						+ "and cc21.aca111 is not null and f_getdata_area('"
						+ fm.getCce001()
						+ "',cc21.aae017,1)='1'"
						+ " and f_get_bsc003(cc20.aae017,'"
						+ fm.getBsc006()
						+ "')='1'"
						+ " and cc20.aae043>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cc20.aae043<to_date('"
						+ fm.getDate02() + "','yyyy-MM-dd')+1 ";
			} else if ("AB01.AAB019".equalsIgnoreCase(dto.getStat01())
					|| "AB01.AAB020".equalsIgnoreCase(dto.getStat01())
					|| "AB01.AAB048".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC048".equalsIgnoreCase(dto.getStat01())
					|| "CB21.BAC299".equalsIgnoreCase(dto.getStat01())
					|| "CB21.ACA111".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC004".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC009".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC014".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC015".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC017".equalsIgnoreCase(dto.getStat01())
					|| "CB21.AAC024".equalsIgnoreCase(dto.getStat01())) {
				countsql = "select sum(cb21.acb21d) as total from cb20,ab01,cb21 where ab01.aab001=cb20.aab001 "
						+ "and cb20.acb200=cb21.acb200 and f_getdata_area('"
						+ fm.getCce001()
						+ "',cb20.aae017,1)='1'"
						+ " and f_get_bsc003(cb20.aae017,'"
						+ fm.getBsc006()
						+ "')='1'"
						+ " and cb20.aae043>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cb20.aae043<to_date('"
						+ fm.getDate02() + "','yyyy-MM-dd')+1 ";
			}
			String total = "";// 总备案数

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap mapRequest = new HashMap();
			mapRequest.put("beo", countsql);
			mapRequest.put("count", "10");
			mapRequest.put("offset", "0");
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			FACADESupport facade = (FACADESupport) getService("FACADESupport");
			ResponseEnvelop resEnv = facade.findBySQL(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				List list = (ArrayList) ((HashMap) resEnv.getBody())
						.get("collection");
				ClassHelper.copyProperties(list.get(0), ndto);
				total = ndto.getTotal();

			} else {
				String[] errorMsg = StringUtil.getAsStringArray(returnValue
						.getMsg(), "|");
				super.saveErrors(request, new AppException(errorMsg[3]));
				return mapping.findForward("backspace");
			}

			String queryhql = "";// 分页组件查询
			String chartsql = "";// 图表查询
			String strtype = "";
			int num = 0;
			String strtable = "AA10";
			if ("CC20.AAC011".equalsIgnoreCase(dto.getStat01())) {
				strtype = "10";
			} else if ("CC20.BAC299".equalsIgnoreCase(dto.getStat01())) {
				strtype = "4";
			} else if ("CC21.AAC048".equalsIgnoreCase(dto.getStat01())) {
				strtype = "900";
			} else if ("CC21.ACA111".equalsIgnoreCase(dto.getStat01())) {
				strtype = "061";
				strtable = "CA11";
				num = 10;
			} else if ("CC21.AAB019".equalsIgnoreCase(dto.getStat01())) {
				strtype = "90";
			} else if ("CC21.AAB020".equalsIgnoreCase(dto.getStat01())) {
				strtype = "900";
			} else if ("AC01.AAC009".equalsIgnoreCase(dto.getStat01())) {
				strtype = "90";
			} else if ("AC01.AAC033".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("AC01.AAC004".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("AC01.AAC005".equalsIgnoreCase(dto.getStat01())) {
				num = 10;
				strtype = "99";
			} else if ("AC01.AAC024".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("AC01.AAC017".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("AB01.AAB019".equalsIgnoreCase(dto.getStat01())) {
				strtype = "90";
			} else if ("AB01.AAB020".equalsIgnoreCase(dto.getStat01())) {
				strtype = "900";
			} else if ("AB01.AAB048".equalsIgnoreCase(dto.getStat01())) {
				strtype = "99";
			} else if ("CB21.AAC048".equalsIgnoreCase(dto.getStat01())) {
				strtype = "900";
			} else if ("CB21.BAC299".equalsIgnoreCase(dto.getStat01())) {
				strtype = "4";
			} else if ("CB21.ACA111".equalsIgnoreCase(dto.getStat01())) {
				num = 10;
				strtable = "CA11";
				strtype = "061";
			} else if ("CB21.AAC004".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("CB21.AAC009".equalsIgnoreCase(dto.getStat01())) {
				strtype = "90";
			} else if ("CB21.AAC014".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("CB21.AAC015".equalsIgnoreCase(dto.getStat01())) {
				strtype = "A";
			} else if ("CB21.AAC017".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			} else if ("CB21.AAC024".equalsIgnoreCase(dto.getStat01())) {
				strtype = "9";
			}
				queryhql = "select decode("
						+ dto.getStat01()
						+ ",null,'"
						+ strtype
						+ "',"
						+ dto.getStat01()
						+ ") as "
						+ dto.getStat01().substring(5)
						+ ",sum(cb21.acb21d) as tmp002, trunc(sum(cb21.acb21d)/"
						+ total
						+ "*100,2)||'%' as tmp003,"
						+ "trunc(sum(decode(cb21.acb21h,null,'0',cb21.acb21h))/"
						+ "decode(sum(decode(cb21.acb21h,null,'0','1')),'0','1',sum(decode(cb21.acb21h,null,'0','1'))),2) as tmp004 "
						+ "from CB20,AB01,cb21 where ab01.aab001=cb20.aab001 and cb20.acb200=cb21.acb200 AND f_getdata_area('"
						+ fm.getCce001() + "',cb20.aae017,1)='1'"
						+ " and f_get_bsc003(cb20.aae017,'" + fm.getBsc006()
						+ "')='1'" + " and cb20.aae043>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cb20.aae043<to_date('"
						+ fm.getDate02() + "','yyyy-MM-dd')+1 group by decode("
						+ dto.getStat01() + ",null,'" + strtype + "',"
						+ dto.getStat01()
						+ ")  order by sum(cb21.acb21d) desc ";
				chartsql = "select f_chart_chinese('"
						+ fm.getStat01()
						+ "',decode("
						+ fm.getStat01()
						+ ",null,'"
						+ strtype
						+ "',"
						+ dto.getStat01()
						+ "),'"
						+ strtable
						+ "')||trunc(sum(cb21.acb21d)/"
						+ total
						+ "*100,2)||'%',sum(cb21.acb21d) as tmp002 from CB20,AB01,cb21 where ab01.aab001=cb20.aab001"
						+ " and cb20.acb200=cb21.acb200 AND f_getdata_area('"
						+ fm.getCce001() + "',cb20.aae017,1)='1'"
						+ " and f_get_bsc003(cb20.aae017,'" + fm.getBsc006()
						+ "')='1'" + " and cb20.aae043>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cb20.aae043<to_date('"
						+ fm.getDate02() + "','yyyy-MM-dd')+1 group by decode("
						+ dto.getStat01() + ",null,'" + strtype + "',"
						+ dto.getStat01()
						+ ")  order by sum(cb21.acb21d) desc ";

			if (num > 0) {
				queryhql = "select * from (" + queryhql + ") where rownum<="
						+ num;
				chartsql = "select * from (" + chartsql + ") where rownum<="
						+ num;
			}

			af = init(request, forward, queryhql, "1");
			request.getSession().setAttribute("chartname", "职业介绍统计图表");
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(request, msg);
				request.getSession().setAttribute("chartsql", null);
			} else {
				request.getSession().setAttribute("chartsql", chartsql);

			}
			request.getSession().setAttribute("tmp001", dto.getStat01());

		} catch (AppException e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 按操作人员统计
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 按操作人员统计
	 * @throws 按操作人员统计出错
	 */
	public ActionForward queryczy(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获取页面查询条件
		ActionForward af = new ActionForward();
		Rec0305Form fm = (Rec0305Form) actionForm;
		if (fm.getCce001() == null || "".equals(fm.getCce001())) {
			fm.setCce001("-1");
		}
		if (fm.getBsc006() == null || "".equals(fm.getBsc006())) {
			fm.setBsc006("-1");
		}
		Rec0305DTO dto = new Rec0305DTO();
		Rec0305DTO ndto = new Rec0305DTO();
		Rec0305DTO lsdto = null;

		// ArrayList statls = new ArrayList();
		try {

			// 处理页面数据
			ClassHelper.copyProperties(fm, dto);
			if (fm.getDate02() != null && !"".equals(fm.getDate02())) {
				dto.setDate02(DateUtil.getStepDay(dto.getDate02(), 1));
			}

			String forward = "/recommendation/query/queryczy.jsp";

			String queryhql = "";// 分页组件查询

			queryhql = "select t.aae011 aae011,"
					+ "(sum(case acc223 when '0' then '1' else '0' end)) temp02,"
					+ "(sum(case acc223 when '0' then '1' else case acc223 when '1' " 
					+ "then '1' else case acc223 when '2' then '1' else '0' end end end)) temp03,"
					+ "(sum(case acc223 when '1' then '1' else '0' end)) temp04,"
					+ "(sum(case acc223 when '2' then '1' else '0' end)) temp05,"
					+ "trunc((sum(case acc223 when '1' then '1' else '0' end))*100/"
					+ "(sum(case acc223 when '0' then '1' else case acc223 when '1' "
					+ "then '1' else case acc223 when '2' then '1' else '0' "
					+ "end end end)),2)||'%' temp06"
					+ " from cc22 t where f_getdata_area('"
					+ fm.getCce001()
					+ "',t.aae017,1)='1'"
					+ " and f_get_bsc003(t.aae017,'"
					+ fm.getBsc006()
					+ "')='1'"
					+ " and t.aae036>=to_date('"
					+ fm.getDate01()
					+ "','yyyy-MM-dd') and t.aae036<to_date('"
					+ fm.getDate02()
					+ "','yyyy-MM-dd')+1  group by t.aae011 order by count(*) desc ";

			af = init(request, forward, queryhql, "1");
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(request, msg);
			}

		} catch (AppException e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}
	
	/**
	 *按招聘信息统计
	 * 
	 * @param mapping
	 *            表单映射器
	 * @param form
	 *            表单数据实体
	 * @param request
	 *            客户端的HTTP请求连接
	 * @param response
	 *            客户端的HTTP返回连接
	 * @return 按操作人员统计
	 * @throws 按操作人员统计出错
	 */
	public ActionForward zpStat(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获取页面查询条件
		ActionForward af = new ActionForward();
		Rec0305Form fm = (Rec0305Form) actionForm;
		if (fm.getCce001() == null || "".equals(fm.getCce001())) {
			fm.setCce001("-1");
		}
		if (fm.getBsc006() == null || "".equals(fm.getBsc006())) {
			fm.setBsc006("-1");
		}
		Rec0305DTO dto = new Rec0305DTO();
		Rec0305DTO ndto = new Rec0305DTO();
		Rec0305DTO lsdto = null;

		// ArrayList statls = new ArrayList();
		try {

			// 处理页面数据
			ClassHelper.copyProperties(fm, dto);
			if (fm.getDate02() != null && !"".equals(fm.getDate02())) {
				dto.setDate02(DateUtil.getStepDay(dto.getDate02(), 1));
			}
			if("1".equals(dto.getAcb201())){//委托招聘
				if("".equals(dto.getDate01())||"".equals(dto.getDate02())||dto.getDate01()==null||dto.getDate02()==null){
					throw new AppException("招聘方式为‘委托招聘’，起止时间不能为空！");
				}					
			}else{
				if("".equals(dto.getAcb231())||dto.getAcb231()==null){
					throw new AppException("招聘方式为‘大会招聘’，招聘期数不能为空！");
				}
			}
			String forward = "/recommendation/query/statzp.jsp";

			String queryhql = "";// 分页组件查询

			if ("CB21.ACA111".equalsIgnoreCase(dto.getStat01())){//工种
				if("1".equals(dto.getAcb201())){//委托招聘
					queryhql = queryhql = "select CB21.ACA111,"
						+ " sum(cb21.acb21d) as tmp002 "
						+ "from cb21,cb20 where cb21.ACB200=cb20.ACB200 and cb20.Acb201='1' and f_getdata_area('"
						+ fm.getCce001() + "',cb21.aae017,1)='1'"
						+ " and f_get_bsc003(cb21.aae017,'" + fm.getBsc006()
						+ "')='1'" + " and cb21.aae036>=to_date('"
						+ fm.getDate01()
						+ "','yyyy-MM-dd') and cb21.aae036<to_date('"
						+ fm.getDate02() + "','yyyy-MM-dd')+1 group by CB21.ACA111 "
						+ "  order by sum(cb21.acb21d) desc ";
				}else{
					queryhql = queryhql = "select CB21.ACA111,"
						+ " sum(cb21.acb21d) as tmp002 "
						+ "from cb21,cb20,cb23 where cb21.ACB200=cb20.ACB200 and cb20.Acb201='2' and f_getdata_area('"
						+ fm.getCce001() + "',cb21.aae017,1)='1'"
						+ " and f_get_bsc003(cb21.aae017,'" + fm.getBsc006()
						+ "')='1'" + " and cb20.ACB230=cb23.acb230 and cb23.acb231='"
						+ fm.getAcb231()
						+ "' group by CB21.ACA111 "
						+ "  order by sum(cb21.acb21d) desc ";
					}	
				}else if("ZPDWS".equalsIgnoreCase(dto.getStat01())){//招聘单位数
					if("1".equals(dto.getAcb201())){//委托招聘
						queryhql = queryhql = "select '招聘单位数' ZPDWS,"
							+ " count(*) as tmp002 "
							+ "from cb20 where cb20.Acb201='1' and f_getdata_area('"
							+ fm.getCce001() + "',cb20.aae017,1)='1'"
							+ " and f_get_bsc003(cb20.aae017,'" + fm.getBsc006()
							+ "')='1'" + " and cb20.aae036>=to_date('"
							+ fm.getDate01()
							+ "','yyyy-MM-dd') and cb20.aae036<to_date('"
							+ fm.getDate02() + "','yyyy-MM-dd')+1  ";
					}else{
						queryhql = queryhql = "select '招聘单位数' ZPDWS,"
							+ " count(*) as tmp002 "
							+ "from cb20,cb23 where cb20.Acb201='2' and f_getdata_area('"
							+ fm.getCce001() + "',cb20.aae017,1)='1'"
							+ " and f_get_bsc003(cb20.aae017,'" + fm.getBsc006()
							+ "')='1'" + " and cb20.ACB230=cb23.acb230 and cb23.acb231='"
							+ fm.getAcb231()+"'";
						}	

				}else if("ZPRYS".equalsIgnoreCase(dto.getStat01())){//招聘总人数
					if("1".equals(dto.getAcb201())){//委托招聘
						queryhql = queryhql = "select '招聘总人数' ZPRYS,"
							+ " sum(cb21.acb21d) as tmp002 "
							+ "from cb21,cb20 where cb21.ACB200=cb20.ACB200 and cb20.Acb201='1' and f_getdata_area('"
							+ fm.getCce001() + "',cb21.aae017,1)='1'"
							+ " and f_get_bsc003(cb21.aae017,'" + fm.getBsc006()
							+ "')='1'" + " and cb21.aae036>=to_date('"
							+ fm.getDate01()
							+ "','yyyy-MM-dd') and cb21.aae036<to_date('"
							+ fm.getDate02() + "','yyyy-MM-dd')+1 ";
					}else{
						queryhql = queryhql = "select '招聘总人数' ZPRYS,"
							+ " sum(cb21.acb21d) as tmp002 "
							+ "from cb21,cb20,cb23 where cb21.ACB200=cb20.ACB200 and cb20.Acb201='2' and f_getdata_area('"
							+ fm.getCce001() + "',cb21.aae017,1)='1'"
							+ " and f_get_bsc003(cb21.aae017,'" + fm.getBsc006()
							+ "')='1'" + " and cb20.ACB230=cb23.acb230 and cb23.acb231='"
							+ fm.getAcb231()+"'";;
						}	

				}else if("ZPGZS".equalsIgnoreCase(dto.getStat01())){//招聘工种数
					if("1".equals(dto.getAcb201())){//委托招聘
						queryhql = queryhql = "select '招聘工种数' ZPGZS,"
							+ " count(*) as tmp002 from (select CB21.ACB216 "
							+ "from cb21,cb20 where cb21.ACB200=cb20.ACB200 and cb20.Acb201='1' and f_getdata_area('"
							+ fm.getCce001() + "',cb21.aae017,1)='1'"
							+ " and f_get_bsc003(cb21.aae017,'" + fm.getBsc006()
							+ "')='1'" + " and cb21.aae036>=to_date('"
							+ fm.getDate01()
							+ "','yyyy-MM-dd') and cb21.aae036<to_date('"
							+ fm.getDate02() + "','yyyy-MM-dd')+1 group by CB21.ACB216) ";
					}else{
						queryhql = queryhql = "select '招聘工种数' ZPGZS,"
							+ " count(*) as tmp002 from (select CB21.ACB216 "
							+ "from cb21,cb20,cb23 where cb21.ACB200=cb20.ACB200 and cb20.Acb201='2' and f_getdata_area('"
							+ fm.getCce001() + "',cb21.aae017,1)='1'"
							+ " and f_get_bsc003(cb21.aae017,'" + fm.getBsc006()
							+ "')='1'" + " and cb20.ACB230=cb23.acb230 and cb23.acb231='"
							+ fm.getAcb231()
							+ "' group by CB21.ACB216) ";
						}	
					}
			request.getSession().setAttribute("tmp001", dto.getStat01());
			af = init(request, forward, queryhql, "1");
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(request, msg);
			}

		} catch (AppException e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}
}
