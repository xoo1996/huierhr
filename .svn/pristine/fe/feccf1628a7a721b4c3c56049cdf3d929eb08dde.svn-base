<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List,org.radf.plat.taglib.Editor,java.util.ArrayList,java.util.Map,java.util.LinkedHashMap,org.radf.apps.basicinfo.form.UnitePersonForm,org.radf.plat.commons.QueryInfo,com.lbs.cp.taglib.Formatter,org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants,org.radf.login.dto.LoginDTO,org.radf.plat.taglib.Button" %>
<!-- /lemis/basicinfo/uniteotherperson.jsp -->
<script type="text/javascript" src="/lemis/js/proxyRequest.js"></script>
<%//获取minuId

//初试化经办人、经办机构、经办时间
LoginDTO lf = (LoginDTO) request.getSession().getAttribute("LoginDTO");
String aae011 = lf.getBsc012();  //	  用户名称  
System.out.println("-----------------"+aae011);
         
			UnitePersonForm fm = (UnitePersonForm) request.getSession().getAttribute("UnitePersonForm");
            
            
           
            List header = new ArrayList();
            header.add(new Formatter("ac01.aac002", "公民身份号码"));
            header.add(new Formatter("ac01.aac003", "姓名"));
            header.add(new Formatter("ac01.aac004", "性别"));
			header.add(new Formatter("ac01.aac006","出生日期",TagConstants.DF_LEFT,TagConstants.DT_YEAR_MONTH_DATE));
            header.add(new Formatter("ac01.aac005", "民族"));
            header.add(new Formatter("ac01.aac011", "文化程度"));
			pageContext.setAttribute("header", header);
            
           
           
            List editors = new ArrayList();
            editors.add(new Editor("card", "a002ac", "公民身份号码"));
            editors.add(new Editor("text", "a003ac", "姓名"));
			editors.add(new Editor("text","acc025","劳动手册号"));
			pageContext.setAttribute("editor", editors);
			
            Map hidden = new LinkedHashMap();
            hidden.put("tac001", "个人编号");
			//hidden.put("aac002", "身份证号");
			//hidden.put("aac003", "姓名");
            pageContext.setAttribute("hidden", hidden);
            
            
			List buttons=new ArrayList();
			buttons.add(new Button("unite","合  并","bas020605","unite(document.all.tableform)"));
			buttons.add(new Button("toback","返  回","bas020604","go2Page(\"basicinfo\",\"1\")"));
			//buttons.add(new Button("detail","查  看","bas020606","detail(document.all.tableform)"));
			buttons.add(new Button("toback","关  闭","bas020607","closeWindow(\"UnitePersonForm\")"));
			
            pageContext.setAttribute("button", buttons);
            
            session.setAttribute("tableheader","人员信息表");//表头
%>
<html>
	<lemis:base />
	<lemis:body>
<lemis:title title="人员合并" />
<lemis:tabletitle title="目标合并人员基本信息"/>
<table class="TableInput">
<html:form action="/queryUnitePerson.do?method=queryUniteOtherPerson">
<lemis:editorlayout/>
  <tr>
  <html:hidden property="aac001" value="<%=fm.getNewaac001()%>"/>
    <td width="16%">身份证号</td>
    <td width="17%"><lemis:text property="aac002" disable="true"></lemis:text></td>
    <td width="16%">姓 名</td>
    <td width="50%"><lemis:text property="aac003" disable="true"></lemis:text></td>
  </tr>
</html:form>
</table>

		
		<lemis:query action="/queryUnitePerson.do?method=queryUniteOtherPerson" editorMeta="editor" topic="被合并人员信息查询" />
		<lemis:table action="/queryUnitePerson.do" headerMeta="header" topic="被合并人员信息" hiddenMeta="hidden" mode="radio" />
		<lemis:buttons buttonMeta="button" />
		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">
	// 合并
	function unite(obj){
	var t = editObj("chk");
		if(!t){
			return t;
		}
		var newaac001 = document.forms[0].aac001.value;
		var oldaac001 = getSelectData(obj,'tac001');
		//alert(newaac001+"__________"+oldaac001);
		if(newaac001==oldaac001){
		  alert('自己不能合并自己,请核对!');
		  return false;
		}
    if(window.confirm('您确定要合并这两个人员？')){
    
    var v = false;
    
	var para = getAlldata(obj);
	var url = "/basicinfo/queryUnitePerson.do?method=validateUnitePerson&newaac001=<%=fm.getNewaac001()%>&oldaac001="+getSelectData(obj,'tac001')+"&aae011=<%=aae011%>";
	var value = proxyRequest(url);
	var res = value.split("$");
	for(var i=0;i<res.length;i++){
	  var a = res[i];
	  eval(a);
	  if(!v){
	   return;
	  }
	}
    
	obj.action = '<html:rewrite page="/queryUnitePerson.do?method=saveUnitePerson"/>'
	+"&newaac001=<%=fm.getNewaac001()%>&oldaac001="+getSelectData(obj,'tac001')+"&aae011=<%=aae011%>";
	obj.submit();
	return true;
	
	} else {
	 return false;
	}
	}

   function detail(){
	var t = editObj("chk");
	if(!t){
	return t;
	}
	window.location.href="/" + lemis.WEB_APP_NAME + "/basicinfo/operatePerson.do?method=view&menuId=alter&"+ getAlldata(document.all.tableform) ;
	}

</script>

