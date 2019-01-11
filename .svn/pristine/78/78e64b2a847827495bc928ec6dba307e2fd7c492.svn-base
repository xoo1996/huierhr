<!-- /lemis/basicinfo/uniteperson.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List,org.radf.plat.taglib.Editor,java.util.ArrayList,java.util.Map,java.util.LinkedHashMap,com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants,org.radf.plat.taglib.Button" %>

<%//获取minuId
           
            
            
            List header = new ArrayList();
            header.add(new Formatter("ac01.aac002", "公民身份号码"));
            header.add(new Formatter("ac01.aac003", "姓名"));
            header.add(new Formatter("ac01.aac004", "性别"));
			header.add(new Formatter("ac01.aac006","出生日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
            header.add(new Formatter("ac01.aac005", "民族"));
            header.add(new Formatter("ac01.aac011", "文化程度"));
                       
           
            List editors = new ArrayList();
            editors.add(new Editor("card", "aac002", "公民身份号码"));
            editors.add(new Editor("text", "aac003", "姓名"));
			editors.add(new Editor("text","acc025","劳动手册号"));
            Map hidden = new LinkedHashMap();
            hidden.put("aac001", "个人编号");
			hidden.put("aac002", "身份证号");
			hidden.put("aac003", "姓名");
         
            pageContext.setAttribute("hidden", hidden);
            pageContext.setAttribute("editor", editors);
            pageContext.setAttribute("header", header);

			List buttons=new ArrayList();
			
			buttons.add(new Button("next","下一步","bas020601","next(document.all.tableform)"));
			
			//buttons.add(new Button("detail","查  看","bas020602","detail(document.all.tableform)"));
			buttons.add(new Button("closeWindow","关  闭","bas020603","closeWindow(\"UnitePersonForm\")"));
			
            pageContext.setAttribute("button", buttons);            
          
            
            session.setAttribute("tableheader","人员信息表");//表头
%>
<html>
	<lemis:base />
	<lemis:body>
		<lemis:title title="目标合并人员信息" />
		<lemis:query action="/queryUnitePerson.do?method=queryUnitePerson" editorMeta="editor" topic="目标合并个人信息查询" />
		<lemis:table action="queryUnitePerson.do" headerMeta="header" topic="目标合并个人信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">

	function next(obj){
	var t = editObj("chk");
		if(!t){
			return t;
		}
	
	var para = getAlldata(obj);
	obj.action = '<html:rewrite page="/queryUnitePerson.do?method=initFindOtherPerson"/>'
	+"&"+para+"&newaac001="+getSelectData(obj,'aac001');  

	obj.submit();
	}
	
	
	function detail(){
	var t = editObj("chk");
	if(!t){
	return t;
	}
	window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=view&menuId=alter&stringData="+ getAlldata(document.all.tableform);
	}

</script>

