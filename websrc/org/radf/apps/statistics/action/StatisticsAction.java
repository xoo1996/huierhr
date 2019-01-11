package org.radf.apps.statistics.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Statistics;
import org.radf.apps.statistics.form.StatisticsForm;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.ListUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class StatisticsAction extends ActionLeafSupport {
	/**
	 * 人事统计
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward stati(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		StatisticsForm sf = (StatisticsForm) form;
		Statistics sat = new Statistics();
		String status = req.getParameter("status");
		try {
			ClassHelper.copyProperties(sf, sat);
			String forward = "/statistics/stati.jsp";
			String queryhql = "";// 分页组件查询
			String chartsql = "";// 图表查询
			String statusCondition = "";
			String temp = "";
			Date stidate = sat.getStidate();
			Date stistartdate = sat.getStistartdate();
			if(status!=null&&status.equals("0")){
				statusCondition = "useremployeestatus='0' and userleavedate > " + "to_date('" + stistartdate + "','yyyy-mm-dd')" + " and userleavedate < " + "to_date('" + stidate + "','yyyy-mm-dd')";
			}else if(status!=null&&status.equals("1")){
				temp =  "userjoindate >" + "to_date('" + stistartdate + "','yyyy-mm-dd') and userjoindate < to_date('" + stidate + "','yyyy-mm-dd')";
				statusCondition = "((useremployeestatus='0' and userleavedate > " + "to_date('" + stidate + "','yyyy-mm-dd') and " + temp + ") or ( useremployeestatus<>'0' and " + temp + "))";
			}else{
				statusCondition = "1=1";
			}
			if (sat.getStiitem().equals("1")) {
				queryhql = "SELECT age AS temp2, temp1, ROUND(100*ratio_to_report(NVL(SUM(temp1),0)) over(),2) AS percent, row_number() over(order by temp1 DESC) rank FROM (SELECT COUNT(age) AS temp1, age from (select CASE WHEN age <=1 THEN '<=1' WHEN age>1 AND age<=2 THEN '1~2' WHEN age>2 AND age<=3 THEN '2~3' WHEN age>3 AND age<=5 THEN '3~5' WHEN age>5 AND age<=8 THEN '5~8' WHEN age>8 AND age<=10 THEN '8~10' WHEN age>10 AND age<=15 THEN '10~15' WHEN age>15 THEN '>15' END AS age FROM (SELECT TRUNC(months_between(to_date('" + stidate + "','yyyy-mm-dd'), userjoindate)/12) AS age FROM tbluser WHERE "+ statusCondition + " )) GROUP BY age ) GROUP BY temp1, age";

				chartsql = "SELECT age AS temp2, temp1 FROM (SELECT COUNT(age) AS temp1, age from (select CASE WHEN age <=1 THEN '<=1' WHEN age>1 AND age<=2 THEN '1~2' WHEN age>2 AND age<=3 THEN '2~3' WHEN age>3 AND age<=5 THEN '3~5' WHEN age>5 AND age<=8 THEN '5~8' WHEN age>8 AND age<=10 THEN '8~10' WHEN age>10 AND age<=15 THEN '10~15' WHEN age>15 THEN '>15' END AS age FROM (SELECT TRUNC(months_between(to_date('" + stidate + "','yyyy-mm-dd'), userjoindate)/12) AS age FROM tbluser WHERE "+ statusCondition + " )) GROUP BY age ) GROUP BY temp1, age ";
				req.getSession().setAttribute("chartname", "工龄分布统计表");
			} else if (sat.getStiitem().equals("2")) {
				queryhql = "select case usergender when '1' then '男' when '0' then '女' end as temp2, temp1, round(100*ratio_to_report(nvl(sum(temp1),0)) over(),2) as percent, row_number() over(order by temp1 desc) rank from (select usergender, count(usergender) as temp1 from tbluser where "+ statusCondition + " group by usergender ) group by temp1,usergender";
				chartsql = "select case usergender when '1' then '男' when '0' then '女' end as temp2, temp1 from (select usergender, count(usergender) as temp1 from tbluser where "+ statusCondition + " group by usergender ) group by temp1,usergender";
				req.getSession().setAttribute("chartname", "性别分布统计表");
			} else if (sat.getStiitem().equals("3")) {
				queryhql = "select case userheightestedu when '0' then '本科' when '1' then '本科在读' when '2' then '博士' when '3' then '初中' when '4' then '大专' when '5' then '高中' when '6' then '高中中技' when '7' then '硕士' when '8' then '职高' when '9' then '中专' when '10' then '专科' when '11' then '大专（在读）' else '其他' end as temp2, temp1, round(100*ratio_to_report(nvl(sum(temp1),0)) over(),2) as percent, row_number() over(order by temp1 desc) rank from (select userheightestedu, count(userheightestedu) as temp1 from tbluser where "+ statusCondition + " group by userheightestedu ) group by temp1,userheightestedu ";
				chartsql = "select case userheightestedu when '0' then '本科' when '1' then '本科在读' when '2' then '博士' when '3' then '初中' when '4' then '大专' when '5' then '高中' when '6' then '高中中技' when '7' then '硕士' when '8' then '职高' when '9' then '中专' when '10' then '专科' when '11' then '大专（在读）' else '其他' end as temp2, temp1 from (select userheightestedu, count(userheightestedu) as temp1 from tbluser where "+ statusCondition + " group by userheightestedu ) group by temp1,userheightestedu ";
				req.getSession().setAttribute("chartname", "学历分布统计表");
			} else if (sat.getStiitem().equals("4")) {
				queryhql = "SELECT aaa103 AS temp2, temp1, ROUND(100*ratio_to_report(NVL(SUM(temp1),0)) over(),2) AS percent, row_number() over(order by temp1 DESC) rank FROM (SELECT COUNT(*) AS temp1, aaa103 FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid left outer join aa10 a on a.aaa102 = c.gctarea WHERE u.userleavedate >=to_date('" +
							stistartdate +"','yyyy-mm-dd') AND u.userleavedate <=to_date(' "+
							stidate +"','yyyy-mm-dd') AND u.useremployeestatus='0'  GROUP BY aaa103 ) GROUP BY aaa103, temp1"; 
				chartsql = "SELECT aaa103 AS temp2, temp1 FROM (SELECT COUNT(*) AS temp1, aaa103 FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid left outer join aa10 a on a.aaa102 = c.gctarea WHERE u.userleavedate >=to_date('" +
							stistartdate +"','yyyy-mm-dd') AND u.userleavedate <=to_date(' "+
							stidate +"','yyyy-mm-dd') AND u.useremployeestatus='0'  GROUP BY aaa103 ) GROUP BY aaa103, temp1"; 
				req.getSession().setAttribute("chartname", "离职人数统计表");
			} else if (sat.getStiitem().equals("5")) {
				queryhql = "select temp1,temp2,row_number() over(order by temp1 desc) rank from ( select case (a.temp1+b.temp1) when 0 then 0 else trunc(100*a.temp1/(a.temp1+b.temp1),2) end as temp1, a.temp2 from (select distinct(a.aaa103) as temp2, nvl(b.temp1,0) as temp1 from aa10 a left outer join (select count(*) as temp1, c.gctarea as temp2 from tbluser u left outer join sc05 s on s.bsc008 = u.userdepartmentid left outer join tblgrpclient c on s.bsc011 =c.gctid where u.userleavedate >=to_date('"
						+ stistartdate
						+ "','yyyy-mm-dd') and u.userleavedate <=to_date('"
						+ stidate
						+ "','yyyy-mm-dd') and u.useremployeestatus='0'  group by c.gctarea ) b on a.aaa102 = b.temp2 where a.aaa100 = 'GCTAREA' ) a left outer join (select distinct(a.aaa103) as temp2, nvl(b.temp1,0) as temp1 from aa10 a left outer join (select count(*) as temp1, c.gctarea as temp2 from tbluser u left outer join sc05 s on s.bsc008 = u.userdepartmentid left outer join tblgrpclient c on s.bsc011 =c.gctid where (u.useremployeestatus='0' and u.userleavedate >=to_date('"
						+ stidate
						+ "','yyyy-mm-dd')) or (u.useremployeestatus <>'0' and u.userjoindate <=to_date('"
						+ stidate
						+ "','yyyy-mm-dd')) group by c.gctarea ) b on a.aaa102 = b.temp2 where a.aaa100 = 'GCTAREA' ) b on a.temp2=b.temp2 )";
				chartsql = "select temp2,temp1 from ( select case (a.temp1+b.temp1) when 0 then 0 else trunc(100*a.temp1/(a.temp1+b.temp1),2) end as temp1, a.temp2 from (select distinct(a.aaa103) as temp2, nvl(b.temp1,0) as temp1 from aa10 a left outer join (select count(*) as temp1, c.gctarea as temp2 from tbluser u left outer join sc05 s on s.bsc008 = u.userdepartmentid left outer join tblgrpclient c on s.bsc011 =c.gctid where u.userleavedate >=to_date('"
						+ stistartdate
						+ "','yyyy-mm-dd') and u.userleavedate <=to_date('"
						+ stidate
						+ "','yyyy-mm-dd') and u.useremployeestatus='0'  group by c.gctarea ) b on a.aaa102 = b.temp2 where a.aaa100 = 'GCTAREA' ) a left outer join (select distinct(a.aaa103) as temp2, nvl(b.temp1,0) as temp1 from aa10 a left outer join (select count(*) as temp1, c.gctarea as temp2 from tbluser u left outer join sc05 s on s.bsc008 = u.userdepartmentid left outer join tblgrpclient c on s.bsc011 =c.gctid where (u.useremployeestatus='0' and u.userleavedate >=to_date('"
						+ stidate
						+ "','yyyy-mm-dd')) or (u.useremployeestatus <>'0' and u.userjoindate <=to_date('"
						+ stidate
						+ "','yyyy-mm-dd')) group by c.gctarea ) b on a.aaa102 = b.temp2 where a.aaa100 = 'GCTAREA' ) b on a.temp2=b.temp2 )";
				req.getSession().setAttribute("chartname", "离职率统计表");
			} else if (sat.getStiitem().equals("6")) {
				queryhql ="select aaa103 as temp2, temp1, round(100*ratio_to_report(nvl(sum(temp1),0)) over(),2) as percent, row_number() over(order by temp1 desc) rank from (select count(distinct u.useremployid) as temp1, aaa103 from tbluser u left outer join sc05 s on s.bsc008 = u.userdepartmentid  LEFT OUTER JOIN tblgrpclient  c ON s.bsc011=c.gctid left outer join aa10 a on a.aaa102 = c.gctarea where u.userjoindate >=to_date('"
						+ stistartdate
						+ "','yyyy-mm-dd') and u.userjoindate <=to_date('"
						+ stidate
						+ "','yyyy-mm-dd')  group by aaa103 )group by aaa103,temp1";
						
				chartsql = "select aaa103 as temp2, temp1 from (select count(*) as temp1, aaa103 from tbluser u left outer join sc05 s on s.bsc008 = u.userdepartmentid  LEFT OUTER JOIN tblgrpclient  c ON s.bsc011=c.gctid left outer join aa10 a on a.aaa102 = c.gctarea where u.userjoindate >=to_date('"
						+ stistartdate
						+ "','yyyy-mm-dd') and u.userjoindate <=to_date('"
						+ stidate
						+ "','yyyy-mm-dd')  group by aaa103 )group by aaa103,temp1";
				req.getSession().setAttribute("chartname", "新入职人数统计表");
			}else if (sat.getStiitem().equals("7")) {
				queryhql = "SELECT age AS temp2, temp1, ROUND(100*ratio_to_report(NVL(SUM(temp1),0)) over(),2) AS percent, row_number() over(order by temp1 DESC) rank FROM (SELECT COUNT(age) AS temp1, age FROM ( select CASE WHEN age <=30 THEN '<=30' WHEN age>30 AND age<=35 THEN '30~35' WHEN age>35 AND age<=40 THEN '35~40' WHEN age>40 AND age<=50 THEN '40~50' WHEN age>50 THEN '>50' end as age from ( SELECT TRUNC(months_between(to_date('" + stidate + "','yyyy-mm-dd'), userbirthday)/12) AS age FROM tbluser WHERE "+ statusCondition + " ) ) GROUP BY age ) GROUP BY temp1, age";
				chartsql = "SELECT age AS temp2, temp1 FROM (SELECT COUNT(age) AS temp1, age FROM ( select CASE WHEN age <=30 THEN '<=30' WHEN age>30 AND age<=35 THEN '30~35' WHEN age>35 AND age<=40 THEN '35~40' WHEN age>40 AND age<=50 THEN '40~50' WHEN age>50 THEN '>50' end as age from ( SELECT TRUNC(months_between(to_date('" + stidate + "','yyyy-mm-dd'), userbirthday)/12) AS age FROM tbluser WHERE useremployeestatus<>'0' AND "+ statusCondition + " ) ) GROUP BY age ) GROUP BY temp1, age";
				req.getSession().setAttribute("chartname", "年龄分布统计表");
			}else if(sat.getStiitem().equals("8")) {
				queryhql = "SELECT case status when '0' then '离职' when '1' then '正式' when '2' then '试用' when '3' then '返聘' when '4' then '在职' end AS temp2, temp1, ROUND(100*ratio_to_report(NVL(SUM(temp1),0)) over(),2) AS percent, row_number() over(order by temp1 DESC) rank FROM (SELECT COUNT(status) AS temp1, status FROM (SELECT USEREMPLOYEESTATUS AS status FROM tbluser WHERE useremployeestatus<>'0' AND userjoindate <= to_date('" + stidate + "','yyyy-mm-dd') ) GROUP BY status ) GROUP BY temp1, status";
				chartsql = "SELECT case status when '0' then '离职' when '1' then '正式' when '2' then '试用' when '3' then '返聘' when '4' then '在职' end AS temp2, temp1 FROM (SELECT COUNT(status) AS temp1, status FROM (SELECT USEREMPLOYEESTATUS AS status FROM tbluser WHERE useremployeestatus<>'0' AND userjoindate <= to_date('" + stidate + "','yyyy-mm-dd') ) GROUP BY status ) GROUP BY temp1, status";
				req.getSession().setAttribute("chartname", "员工状态统计表");
			}
			af = init(req, forward, queryhql,13, "1");
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
	 * 薪酬统计
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward statiPay(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		StatisticsForm sf = (StatisticsForm) form;
		Statistics sat = new Statistics();
		try {
			ClassHelper.copyProperties(sf, sat);
			String forward = "/statistics/statipay.jsp";
			String queryhql = "";// 分页组件查询
			String chartsql = "";// 图表查询

			Date stidate = sat.getStidate();
			Date stistartdate = sat.getStistartdate();
			if (sat.getStipayitem().equals("shouldpay")) {
				queryhql = "select temp2, temp1,temp3,temp4, round(100*ratio_to_report(nvl(sum(temp1),0)) over(),2) as percent, row_number() over(order by temp1 desc) rank from (select aaa103 as temp2, sum(totalstore) as temp1, sum(num) as temp3,ROUND(sum(totalstore)/sum(num),2) as temp4 from (select sum(s.yingfa) as totalstore,count(*) as num, s.store, a.aaa103 from tblsalary s left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year||'-'||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year||'-'||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') group by s.store, a.aaa103 ) group by aaa103 ) group by temp2, temp1,temp3,temp4";

				chartsql =  "select temp2, temp1 from (select aaa103 as temp2, sum(totalstore) as temp1 from (select sum(s.yingfa) as totalstore, s.store, a.aaa103 from tblsalary s left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year||'-'||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year||'-'||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') group by s.store, a.aaa103 ) group by aaa103 ) group by temp2, temp1";

				req.getSession().setAttribute("chartname", "应发工资统计表");
			} else if (sat.getStipayitem().equals("finalpay")) {
				queryhql = "select temp2, temp1,temp3,temp4, round(100*ratio_to_report(nvl(sum(temp1),0)) over(),2) as percent, row_number() over(order by temp1 desc) rank from (select aaa103 as temp2, sum(totalstore) as temp1, sum(num) as temp3,ROUND(sum(totalstore)/sum(num),2) as temp4 from (select sum(s.shifa) as totalstore,count(*) as num, s.store, a.aaa103 from tblsalary s left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year||'-'||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year||'-'||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') group by s.store, a.aaa103 ) group by aaa103 ) group by temp2, temp1,temp3,temp4";

				chartsql =  "select temp2, temp1 from (select aaa103 as temp2, sum(totalstore) as temp1 from (select sum(s.shifa) as totalstore, s.store, a.aaa103 from tblsalary s left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year||'-'||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year||'-'||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') group by s.store, a.aaa103 ) group by aaa103 ) group by temp2, temp1";

				req.getSession().setAttribute("chartname", "实发工资统计表");
			} else if(sat.getStipayitem().equals("tryshouldpay")){
				queryhql = "SELECT temp2, temp1,temp3,temp4, ROUND(100*ratio_to_report(NVL(SUM(temp1),0)) over(),2) AS percent, row_number() over(order by temp1 DESC) rank FROM (SELECT aaa103 AS temp2, SUM(totalstore) AS temp1, sum(num) as temp3,ROUND(sum(totalstore)/sum(num),2) as temp4 FROM (SELECT SUM(s.yingfa) AS totalstore, count(*) as num,s.store, a.aaa103 FROM tblsalary s left outer join tbluser u on u.USEREMPLOYID = s.USEREMPLOYID LEFT OUTER JOIN tblgrpclient c ON c.gctnm =s.store LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea WHERE to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" + sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') AND to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" + sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') and u.USEREMPLOYEESTATUS = ',' GROUP BY s.store, a.aaa103 ) GROUP BY aaa103 ) GROUP BY temp2, temp1,temp3,temp4";
				chartsql = "SELECT temp2, temp1 FROM (SELECT aaa103 AS temp2, SUM(totalstore) AS temp1 FROM (SELECT SUM(s.yingfa) AS totalstore, s.store, a.aaa103 FROM tblsalary s left outer join tbluser u on u.USEREMPLOYID = s.USEREMPLOYID LEFT OUTER JOIN tblgrpclient c ON c.gctnm =s.store LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea WHERE to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" + sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') AND to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" + sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') and u.USEREMPLOYEESTATUS = ',' GROUP BY s.store, a.aaa103 ) GROUP BY aaa103 ) GROUP BY temp2, temp1 ";
				req.getSession().setAttribute("chartname", "试用期实发工资统计表");
			}else if(sat.getStipayitem().equals("tryfinalpay")){
				queryhql = "SELECT temp2, temp1,temp3,temp4, ROUND(100*ratio_to_report(NVL(SUM(temp1),0)) over(),2) AS percent, row_number() over(order by temp1 DESC) rank FROM (SELECT aaa103 AS temp2, SUM(totalstore) AS temp1, sum(num) as temp3,ROUND(sum(totalstore)/sum(num),2) as temp4 FROM (SELECT SUM(s.shifa) AS totalstore, count(*) as num,s.store, a.aaa103 FROM tblsalary s left outer join tbluser u on u.USEREMPLOYID = s.USEREMPLOYID LEFT OUTER JOIN tblgrpclient c ON c.gctnm =s.store LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea WHERE to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" + sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') AND to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" + sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') and u.USEREMPLOYEESTATUS = ',' GROUP BY s.store, a.aaa103 ) GROUP BY aaa103 ) GROUP BY temp2, temp1,temp3,temp4"; 
				chartsql = "SELECT temp2, temp1 FROM (SELECT aaa103 AS temp2, SUM(totalstore) AS temp1 FROM (SELECT SUM(s.shifa) AS totalstore, s.store, a.aaa103 FROM tblsalary s left outer join tbluser u on u.USEREMPLOYID = s.USEREMPLOYID LEFT OUTER JOIN tblgrpclient c ON c.gctnm =s.store LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea WHERE to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" + sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') AND to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" + sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') and u.USEREMPLOYEESTATUS = ',' GROUP BY s.store, a.aaa103 ) GROUP BY aaa103 ) GROUP BY temp2, temp1"; 
				req.getSession().setAttribute("chartname", "试用期应发工资统计表");
			}
			af = init(req, forward, queryhql,11, "1");
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
	 * 人事统计明细
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String[] chk = req.getParameterValues("chk");
		Collection<Statistics> collection = null;
		List<String> list = new ArrayList<String>();
		ActionForward af = new ActionForward();
		StatisticsForm sf = (StatisticsForm) form;
		Statistics sat = new Statistics();
		String statusCondition = "";
		String temp = "";
		String sql2 = "";
		
		
		try {
			SubmitDataMap data = new SubmitDataMap(req);
			String[] temp2List = data.getParameterValues("temp2");
			for(int i =0; i <chk.length;i++){
				int j = Integer.parseInt(chk[i])-1;
				String str = temp2List[j];
				list.add(str);
			}
			
			ClassHelper.copyProperties(sf, sat);
			String forward = "/statistics/detail.jsp";
			String hql = "";// 分页组件查询
			Date stidate = sat.getStidate();
			String status = sat.getStatus();
			Date stistartdate = sat.getStistartdate();
			if(status!=null&&status.equals("0")){
				statusCondition = "useremployeestatus='0' and userleavedate > " + "to_date('" + stistartdate + "','yyyy-mm-dd')" + " and userleavedate < " + "to_date('" + stidate + "','yyyy-mm-dd')";
			}else if(status!=null&&status.equals("1")){
				temp =  "userjoindate >" + "to_date('" + stistartdate + "','yyyy-mm-dd') and userjoindate < to_date('" + stidate + "','yyyy-mm-dd')";
				statusCondition = "((useremployeestatus='0' and userleavedate > " + "to_date('" + stidate + "','yyyy-mm-dd') and " + temp + ") or ( useremployeestatus<>'0' and " + temp + "))";
			}else{
				statusCondition = "1=1";
			}
			String temp2 = sat.getTemp2();
			if (sat.getStiitem().equals("1")) {
				sql2 = ListUtil.listToSql("age",list);
				hql = "SELECT distinct username,useremployid,danganid,bsc009,aaa103,userjoindate,userleavedate,userpositionnow,userheightestedu FROM (SELECT u.username, u.useremployid, u.danganid, bsc009, a.aaa103, userjoindate, userleavedate, userpositionnow, userheightestedu, TRUNC(months_between(to_date('" + stidate + "','yyyy-mm-dd'), userjoindate)/12) AS age FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea LEFT OUTER JOIN sc04 d ON d.bsc008 = s.bsc008 LEFT OUTER JOIN aa10 e ON e.aaa102 = u.userheightestedu WHERE "+ statusCondition +"  ) where " + sql2;
			} else if (sat.getStiitem().equals("2")) {
				sql2 = ListUtil.genderListToSql("usergender",list);
				hql = "SELECT distinct username,useremployid,danganid,bsc009,a.aaa103,userjoindate,userleavedate,userpositionnow,userheightestedu FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea LEFT OUTER JOIN sc04 d ON d.bsc008 = s.bsc008 left outer join aa10 e on e.aaa102 = u.userheightestedu WHERE "+ statusCondition +" and " + sql2;
			} else if (sat.getStiitem().equals("3")) {
				sql2 = ListUtil.eduListToSql("userheightestedu", list);
				hql = " SELECT distinct username,useremployid,danganid,bsc009,aaa103,userjoindate,userleavedate,userpositionnow,userheightestedu FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea LEFT OUTER JOIN sc04 d ON d.bsc008 = s.bsc008 where " +
						sql2 + " and "+ statusCondition + "','yyyy-mm-dd') ";
			} else if (sat.getStiitem().equals("4")) {
				sql2 = " (a.aaa103 = '" + list.get(0) + "'";
				for(int i = 1; i < list.size(); i++){
					sql2 += " or a.aaa103 = '" + list.get(i) + "'";
				}
				sql2 += ")";
				hql = "SELECT distinct username,useremployid,danganid,bsc009,aaa103,userjoindate,userleavedate,userpositionnow,userheightestedu FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea LEFT OUTER JOIN sc04 d ON d.bsc008 = s.bsc008 WHERE u.userleavedate >=to_date('" + stistartdate +"','yyyy-mm-dd') AND u.userleavedate <=to_date('"+ stidate +"','yyyy-mm-dd') AND u.useremployeestatus='0' AND "+sql2; 
			} else if (sat.getStiitem().equals("5")) {
				sql2 = " (a.aaa103 = '" + list.get(0) + "'";
				for(int i = 1; i < list.size(); i++){
					sql2 += " or a.aaa103 = '" + list.get(i) + "'";
				}
				sql2 += ")";
				hql = "SELECT distinct username,useremployid,danganid,bsc009,aaa103,userjoindate,userleavedate,userpositionnow,userheightestedu FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea LEFT OUTER JOIN sc04 d ON d.bsc008 = s.bsc008 WHERE u.userleavedate >=to_date('" + stistartdate +"','yyyy-mm-dd') AND u.userleavedate <=to_date('"+ stidate +"','yyyy-mm-dd') AND u.useremployeestatus='0' AND "+sql2; 
			} else if (sat.getStiitem().equals("6")) {
				sql2 = " (a.aaa103 = '" + list.get(0) + "'";
				for(int i = 1; i < list.size(); i++){
					sql2 += " or a.aaa103 = '" + list.get(i) + "'";
				}
				sql2 += ")";
				hql  =  "SELECT distinct username,useremployid,danganid,bsc009,aaa103,userjoindate,userleavedate,userpositionnow,userheightestedu FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid " +
						"LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea left outer join sc04 d on d.bsc008 = s.bsc008 " +
						"WHERE u.userjoindate >=to_date('"+stistartdate+"','yyyy-mm-dd') AND u.userjoindate <=to_date('"+stidate+"','yyyy-mm-dd') " +
						"AND "+ sql2;
			}else if(sat.getStiitem().equals("7")) {
				sql2 = ListUtil.listToSql("age",list);
				hql = "SELECT distinct username,useremployid,danganid,bsc009,aaa103,userjoindate,userleavedate,userpositionnow,userheightestedu FROM (SELECT u.username, u.useremployid, u.danganid, bsc009, a.aaa103, userjoindate, userleavedate, userpositionnow, userheightestedu, TRUNC(months_between(to_date('" + stidate + "','yyyy-mm-dd'), USERBIRTHDAY)/12) AS age FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea LEFT OUTER JOIN sc04 d ON d.bsc008 = s.bsc008 LEFT OUTER JOIN aa10 e ON e.aaa102 = u.userheightestedu WHERE "+ statusCondition +" ) where " + sql2;
			}else if(sat.getStiitem().equals("8")) {
				sql2 = ListUtil.statusListToSql("useremployeestatus",list);
				hql = "SELECT distinct username,useremployid,danganid,bsc009,aaa103,userjoindate,userleavedate,userpositionnow,userheightestedu FROM tbluser u LEFT OUTER JOIN sc05 s ON s.bsc008 = u.userdepartmentid LEFT OUTER JOIN tblgrpclient c ON s.bsc011 =c.gctid LEFT OUTER JOIN aa10 a ON a.aaa102 = c.gctarea LEFT OUTER JOIN sc04 d ON d.bsc008 = s.bsc008 WHERE " + sql2 + "  AND userjoindate <= to_date('" + stidate + "','yyyy-mm-dd') ";
			}

			af = init(req, forward, hql, "1");
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return af;
	}
	/**
	 * 工资明细
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailPay(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String[] chk = req.getParameterValues("chk");
		Collection<Statistics> collection = null;
		List<String> list = new ArrayList<String>();
		ActionForward af = new ActionForward();
		StatisticsForm sf = (StatisticsForm) form;
		Statistics sat = new Statistics();
		String sql2 = "";
		try {
			SubmitDataMap data = new SubmitDataMap(req);
			String[] temp2List = data.getParameterValues("temp2");
			for(int i =0; i <chk.length;i++){
				int j = Integer.parseInt(chk[i])-1;
				String str = temp2List[j];
				list.add(str);
			}
			
			ClassHelper.copyProperties(sf, sat);
			String forward = "/statistics/detailpay.jsp";
			String hql = "";// 分页组件查询
			String temp2 = sat.getTemp2();
			if (sat.getStipayitem().equals("shouldpay")) {
				sql2 = " (a.aaa103 = '" + list.get(0) + "'";
				for(int i = 1; i < list.size(); i++){
					sql2 += " or a.aaa103 = '" + list.get(i) + "'";
				}
				sql2 += ")";
				hql = "select sum(s.yingfa) as totalstore, s.store, a.aaa103 from tblsalary s left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') and " + sql2 + " group by s.store, a.aaa103";
			} else if (sat.getStipayitem().equals("finalpay")) {
				sql2 = " (a.aaa103 = '" + list.get(0) + "'";
				for(int i = 1; i < list.size(); i++){
					sql2 += " or a.aaa103 = '" + list.get(i) + "'";
				}
				sql2 += ")";
				hql = "select sum(s.shifa) as totalstore, s.store, a.aaa103 from tblsalary s left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') and " + sql2 + " group by s.store, a.aaa103";	
			} else if (sat.getStipayitem().equals("tryshouldpay")) {
				sql2 = " (a.aaa103 = '" + list.get(0) + "'";
				for(int i = 1; i < list.size(); i++){
					sql2 += " or a.aaa103 = '" + list.get(i) + "'";
				}
				sql2 += ")";
				hql = "select sum(s.yingfa) as totalstore, s.store, a.aaa103 from tblsalary s left outer join tbluser u  on u.USEREMPLOYID = s.USEREMPLOYID left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') and " + sql2 + " and u.USEREMPLOYEESTATUS = ',' group by s.store, a.aaa103";
			} else if (sat.getStipayitem().equals("tryfinalpay")) {
				sql2 = " (a.aaa103 = '" + list.get(0) + "'";
				for(int i = 1; i < list.size(); i++){
					sql2 += " or a.aaa103 = '" + list.get(i) + "'";
				}
				sql2 += ")";
				hql = "select sum(s.shifa) as totalstore, s.store, a.aaa103 from tblsalary s left outer join tbluser u  on u.USEREMPLOYID = s.USEREMPLOYID left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') and " + sql2 + " and u.USEREMPLOYEESTATUS = ',' group by s.store, a.aaa103";			} 
	
			req.setAttribute("sql", hql);
			af = init(req, forward, hql, "1");
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return af;
	}
	/**
	 * 工资明细查看页面，条件查询
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		StatisticsForm sf = (StatisticsForm) form;
		Statistics sat = new Statistics();
		String sql2 = "";
		try {
			ClassHelper.copyProperties(sf, sat);
			String forward = "/statistics/detailpay.jsp";
			String hql = "";// 分页组件查询
			String temp2 = sat.getTemp2();
			if(sat.getStore()!=null&&!sat.getStore().equals("")){
				sql2 = "and s.store='"+ sat.getStore() + "'";
			}
			if (sat.getStipayitem().equals("shouldpay")) {
				hql = "select sum(s.yingfa) as totalstore, s.store, a.aaa103 from tblsalary s left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') " + sql2 + " group by s.store, a.aaa103";
			} else if (sat.getStipayitem().equals("finalpay")) {
				hql = "select sum(s.shifa) as totalstore, s.store, a.aaa103 from tblsalary s left outer join tblgrpclient c on c.gctnm =s.store left outer join aa10 a on a.aaa102 = c.gctarea where to_date(s.year ||'-' ||s.month,'yyyy-mm')>=to_date('" +
						sat.getStartyear()+"-"+sat.getStartmonth()+"','yyyy-mm') and to_date(s.year ||'-' ||s.month,'yyyy-mm')<=to_date('" +
						sat.getEndyear()+"-"+sat.getEndmonth()+"','yyyy-mm') " + sql2 + " group by s.store, a.aaa103";			} 
			af = init(req, forward, hql, "1");
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return af;
	}
	/**
	 * 员工个人薪酬查询
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryByPerson(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = null;
		ActionForward af = new ActionForward();
		StatisticsForm sf = (StatisticsForm) form;
		Statistics sat = new Statistics();
		forward = "/statistics/querybyperson.jsp";
		try {
			ClassHelper.copyProperties(sf, sat);
			sat.setFileKey("sta_000001");
			String hql = queryEnterprise(sat);
			af = super.init(req, forward, hql);
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
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
	 * 休假情况查询
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryLeave(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = null;
		ActionForward af = new ActionForward();
		StatisticsForm sf = (StatisticsForm) form;
		Statistics sat = new Statistics();
		Connection conn = null;
		Statement statement = null;
		String count = "";
		forward = "/statistics/leaveinfo.jsp";
		try {
			conn = DBUtil.getConnection();
			statement = conn.createStatement();
			ClassHelper.copyProperties(sf, sat);
			sat.setFileKey("sta_000002");
			String hql = queryEnterprise(sat);
			String where = hql.split("\\bwhere\\b")[1];
			String sql = "select sum(nemtry) as total from tblnem where " + where;
			ResultSet result = statement.executeQuery(sql);
			while(result.next()){
				count = result.getString("total");
			}
			req.getSession().setAttribute("count", count==null?"0":count);
			af = super.init(req, forward, hql);
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
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
		
}
