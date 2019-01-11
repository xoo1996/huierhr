<!--sysmanager/user/UserQuery.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<OBJECT id="ePass"  name="ePass" height="10" width="10" classid="clsid:C7672410-309E-4318-8B34-016EE77D6B58"  codebase="/ftd/cab/epass1000ND/install.cab" >
</OBJECT>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript" ></script> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">

	function find() {
		//打开设备
		ePass.OpenDevice(1, "");
		//读取设备序列号
		var snID = ePass.GetStrProperty(7, 0, 0);
		ePass.CloseDevice();
		resetForm(queryForm);
		$("input[name=ukeysn],[label=序列号]").val(snID);
		$("form[name=userForm]").submit();
		
	}
</script>
<lemis:base />
<lemis:body>
<%			String stringData = "";
			QueryInfo qi = (QueryInfo) pageContext
					.findAttribute(GlobalNames.QUERY_INFO);
			if (null != qi) {
				stringData = qi.getStringData();
			}
			List<Editor> editors = new ArrayList<Editor>();
			editors.add(new Editor("text", "ugctid", "用户代码"));
			editors.add(new Editor("text", "bsc012", "用户名称"));
			editors.add(new Editor("text", "ukeysn", "序列号"));
			editors.add(new Editor("text", "iswork", "状态"));
			List<Formatter> header = new ArrayList<Formatter>();
			header.add(new Formatter("sc05.ugctid", "用户代码"));
			header.add(new Formatter("sc05.bsc012", "用户名称"));
			header.add(new Formatter("sc05.ukeysn", "序列号"));
			header.add(new Formatter("sc05.iswork", "状态"));
			header.add(new Formatter("sc05.uopdate", "操作时间"));
			Map<String, String> hidden = new LinkedHashMap<String, String>();
			hidden.put("ugctid","用户代码");
			hidden.put("bsc012", "用户名称");
			hidden.put("ukeysn", "序列号");
			hidden.put("iswork", "状态");
			


			Map<String, String> buttons = new LinkedHashMap<String, String>();
			buttons.put("挂失/解除挂失","loss(document.all.tableform)");
			buttons.put("识别Ukey","find()");
			

			
			pageContext.setAttribute("header", header);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("button", buttons);
			session.setAttribute("tableheader", "人员信息表");
			

%>

	<html:hidden property="stringData" value="<%=stringData%>" />
	<lemis:title title="Ukey管理" />
	<lemis:query action="/userAction.do?method=ukeyQuery"
		editorMeta="editor" topic="Ukey信息查询" />
	<lemis:table topic="Ukey信息" action="/userAction.do" headerMeta="header"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:clean names="userForm" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/md5.js"></script>
<script language="javascript">
function loss(obj) {
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/userAction.do?method=loss&"/>'+getAlldata(obj);			
	obj.submit();
};

</script>



