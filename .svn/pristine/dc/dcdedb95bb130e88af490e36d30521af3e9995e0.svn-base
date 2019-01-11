<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List,org.radf.plat.taglib.Editor,java.util.ArrayList,java.util.Map,java.util.LinkedHashMap,org.radf.apps.basicinfo.form.EmployerForm,com.lbs.cp.taglib.Formatter,org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants,org.radf.login.dto.LoginDTO,org.radf.plat.taglib.Button" %>
<script type="text/javascript" src="/lemis/js/proxyRequest.js"></script>

<!-- basicinfo/initqueryemploy.jsp -->
<%//获取minuId

      
//初试化经办人、经办机构、经办时间
LoginDTO lf = (LoginDTO) request.getSession().getAttribute("LoginDTO");
String aae011 = lf.getBsc012();  //	  用户名称  
            
            
            EmployerForm fm = (EmployerForm) request.getSession().getAttribute("UniteEmployForm");
            
           
			
			Map hidden = new LinkedHashMap();
			hidden.put("tab001","单位编号");
			//hidden.put("aae119","单位状态");
			pageContext.setAttribute("hidden",hidden);

			List header = new ArrayList();
			header.add(new Formatter("ab01.aab002","社保编号"));
			header.add(new Formatter("ab01.aab003","组织机构代码"));
			header.add(new Formatter("ab01.aab004","单位名称"));
			header.add(new Formatter("ab01.aab020","经济类型"));
			header.add(new Formatter("ab01.aab019","单位类型",TagConstants.DF_CENTER));
			header.add(new Formatter("ab01.aae004","联系人"));
			header.add(new Formatter("ab01.aae005","联系电话"));
			pageContext.setAttribute("header",header);
			
     
            List editors = new ArrayList();
            editors.add(new Editor("text", "a002ab", "社保编号"));
            editors.add(new Editor("text", "a003ab", "组织机构代码"));
            editors.add(new Editor("text", "a004ab", "单位名称"));
			pageContext.setAttribute("editor", editors);
			
			List buttons=new ArrayList();
			
			buttons.add(new Button("unite","合  并","bas010604","unite(document.all.tableform)"));
			buttons.add(new Button("toback","返  回","bas010603","go2Page(\"basicinfo\",\"1\")"));
			//buttons.add(new Button("toback","查  看","bas010603","viewEmp(document.all.tableform)"));
			buttons.add(new Button("toback","关  闭","bas010603","closeWindow(\"UniteEmployForm\")"));
			
            pageContext.setAttribute("button", buttons);
            
            session.setAttribute("tableheader","单位信息表");//表头
%>
<html>
	<lemis:base />
	<lemis:body>
<lemis:title title="单位合并" />
<lemis:tabletitle title="目标合并单位基本信息"/>
<table class="TableInput">
<html:form action="/UniteEmployAction.do?method=queryUniteOtherEmploy">
<lemis:editorlayout/>
  <tr>
  <td width="16%">社保编号</td>
  <html:hidden property="aab001" value="<%=fm.getNewaab001()%>"/>
    <td width="17%"><lemis:text property="aab002" disable="true"></lemis:text></td>
    <td width="16%">组织机构代码</td>
    <td width="20%"><lemis:text property="aab003" disable="true"></lemis:text></td>
    <td width="16%">单位名称</td>
    <td width="30%"><lemis:text property="aab004" disable="true"></lemis:text></td>
  </tr>
</html:form>
</table>

		
		<lemis:query action="/UniteEmployAction.do?method=queryUniteOtherEmploy" editorMeta="editor" topic="被合并单位信息查询" />
		<lemis:table action="/UniteEmployAction.do" headerMeta="header" topic="被合并单位信息" hiddenMeta="hidden" mode="radio" />
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
		var newaab001 = document.forms[0].aab001.value;
		var oldaab001 = getSelectData(obj,'tab001');
		//alert(newaab001+"______"+oldaab001);
		if(newaab001==oldaab001){
		 alert('单位不能合并自己,请核对!');
		 return false;
		}
		
		

    if(window.confirm('您确定要合并这两个单位？')){
    
    var v = false;
    
	var para = getAlldata(obj);
	var url = "/basicinfo/UniteEmployAction.do?method=validateUniteEmploy&"+ "&newaab001=<%=fm.getNewaab001()%>&oldaab001="+getSelectData(obj,'tab001')+"&aae011=<%=aae011%>";
	var value = proxyRequest(url);
	var res = value.split("$");
	for(var i=0;i<res.length;i++){
	  var a = res[i];
	  eval(a);
	  if(!v){
	   return;
	  }
	}
	
	obj.action = '<html:rewrite page="/UniteEmployAction.do?method=saveUniteEmploy"/>'
	+"&newaab001=<%=fm.getNewaab001()%>&oldaab001="+getSelectData(obj,'tab001')+"&aae011=<%=aae011%>";
	obj.submit();
	return true;
	
	
	} else {
	 return false;
	}
	}

          function viewEmp (obj){
          var t = editObj("chk");
		if(!t){
			return t;
		}
			var para = getAlldata(obj);
			//alert(para);
			var test = para.split('&');
			var test2 = test[0].split('=');
			var aab001 = test2[1];
			//alert(aab001);
			
			//return false;
			obj.action = '<html:rewrite page="/employerAction.do?method=viewEmployer&"/>'+"aab001="+aab001;
			// 增加URL地址，并将URL地址的&符号转化成";amp;"。
			location.href=obj.action;
 }
</script>

