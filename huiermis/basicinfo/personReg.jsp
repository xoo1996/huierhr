<!-- basicinfo/personReg.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="org.radf.plat.taglib.Editor" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="com.lbs.cp.taglib.Formatter" %>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%//获取minuId
	String menuId = request.getParameter("menuId");
	String stringData = "";
    PersonForm fm = (PersonForm) session
            .getAttribute("QueryPersonForm");
	
    if (menuId == null || "".equals(menuId))
    {
        if (fm != null)
        {
            menuId = fm.getMenuId();
        }
    }
	//获取上一步的url, url2为备份在SESSION中的地址
    //当前地址中有url2参数,url2参数会被放到SESSION中,没有就把SESSION清空
	String url2=request.getParameter("url2");
	if(url2==null){
		session.removeAttribute("url2");
	}else{
		session.setAttribute("url2",url2);
	}
	//System.out.println("##sesurl22##"+session.getAttribute("url2"));
	String url1=request.getParameter("url1");
	if(url1==null){
		session.removeAttribute("url1");
	}else{
		session.setAttribute("url1",url1);
	}
	//System.out.println("##sesurl11##"+session.getAttribute("url1"));
	
 	List buttons=new ArrayList();
    QueryInfo qi = (QueryInfo) pageContext
            .findAttribute(GlobalNames.QUERY_INFO);
    buttons.add(new Button("printperson", "查看个人详细", "bas020306", "detail(document.all.tableform)"));
    buttons.add(new Button("add","增 加","bas020105","addData(document.forms[0])"));
    if (null != qi)
    {
        stringData = qi.getStringData();	
        buttons.add(new Button("mod","修 改","bas020104","editData(document.all.tableform)"));	
 		buttons.add(new Button("family","社会关系","bas020101","family(document.all.tableform)"));
 		buttons.add(new Button("resume","个人简历","bas020102","resume(document.all.tableform)"));
 		buttons.add(new Button("skill","专业技能","bas020103","skill(document.all.tableform)"));		      
    }
    
			
 	
 	buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"QueryPersonForm\")"));
    
    List header = new ArrayList();
    header.add(new Formatter("ac01.aac002", "公民身份号码"));
    header.add(new Formatter("ac01.aac003", "姓名"));
    header.add(new Formatter("ac01.aac004", "性别"));
 	header.add(new Formatter("ac01.aac006","出生日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
    header.add(new Formatter("ac01.aac005", "民族"));
    header.add(new Formatter("ac01.aac011", "文化程度"));

	Map qh = new LinkedHashMap();
    qh.put("menuId", null);
    qh.put("url", null);
    qh.put("url2", null);
    pageContext.setAttribute("queryHiddens", qh);
    String urlString = "";
    String action = null;//就失业管理“下一步”对应的Action
    if ("reg".equals(menuId))
    {//登记
        action = "/employs/queryPerson.do?method=enterPerson&menuId=reg";
    }
    List editors = new ArrayList();
    editors.add(new Editor("card", "aac002", "公民身份号码"));
    editors.add(new Editor("text", "aac003", "姓名"));
    Map hidden = new LinkedHashMap();
    hidden.put("aac001", "个人编号");
    hidden.put("aac002", "公民身份号码");
    hidden.put("aac003", "姓名");
    hidden.put("aac004", "性别");
    hidden.put("aac005", "民族");
    hidden.put("aac011", "文化程度");
    hidden.put("aac021", "失业证号码");
    hidden.put("aac026", "家庭住址");
 
    pageContext.setAttribute("hidden", hidden);
    pageContext.setAttribute("editor", editors);
    pageContext.setAttribute("header", header);
    pageContext.setAttribute("button", buttons);
    
    session.setAttribute("tableheader","人员信息表");//表头
%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="个人登记" />
		<lemis:query action="/queryPerson.do?method=findPerson" editorMeta="editor" hiddenMeta="queryHiddens" topic="个人信息查询" />
		<input name="stringData" value="<%=stringData%>" type="hidden" />
		<lemis:table action="queryPerson.do" headerMeta="header" topic="个人信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
	<lemis:clean names="QueryPersonForm" />
</html>
<script language="javascript">
	// 新增
	function addData(obj){
		obj.action = '<html:rewrite page="/queryPerson.do?method=findPerson&fg=add"/>'
		+"&stringData=" +document.all("stringData").value;
		obj.submit();
	}
	//修改
	function editData(obj){
		var t = editObj("chk");
		if(!t){
			return t;
		}
		var loc = location.href.split('&url1=');//拆分去掉URL参数,避免累加使得地址过长引起按钮无效
		obj.action = "/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=add&menuId=reg&buttonId=bas020121"+
		"&stringData=" +document.all("stringData").value +
		'&' +getAlldata(obj) +
		"&url1=&url2="+(loc[0]).replace(/&/g,";amp;");
        obj.submit();
	}
	// 专业技能
	function skill(obj){
		var para = getAlldata(obj);
		obj.action = '<html:rewrite page="/queryskillAction.do?method=findskill&"/>'+para+
		"stringData=" +document.all("stringData").value + 
		"&url1=" + (location.href).replace(/&/g,";amp;");
		obj.submit();
	}	
	// 个人简历
	function resume(obj){
		var para = getAlldata(obj);
		obj.action = '<html:rewrite page="/queryresumeAction.do?method=findresume&"/>'+para+
		"stringData=" +document.all("stringData").value+
		"&mod=basicinfo";
		obj.submit();
	}
	//新增加的社会关系，2005-5-25
	function family(obj){
		var para = getAlldata(obj);
		obj.action = '<html:rewrite page="/querysocietyAction.do?method=findsociety&"/>'+para+
		"stringData=" +document.all("stringData").value + 
		"&url1=" + (location.href).replace(/&/g,";amp;");
		obj.submit();
	}
	  function detail(obj){
			var t = editObj("chk");
			if(!t){
			return t;
			}
			obj.action = '<html:rewrite page="/personOperAction.do?method=printPerson&"/>'+getAlldata(obj);			
			obj.submit();
  }
	
</script>

