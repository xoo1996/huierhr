<!--/lemis/recommendation/employassembly/AddEmployInvite.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.recommendation.employassembly.form.Rec0701Form" %>
<%@ page import="org.radf.login.dto.LoginDTO" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<% 
    String menuId=request.getSession().getAttribute("menuId").toString();
Rec0701Form form = (Rec0701Form) request.getSession().getAttribute("Rec0701Form");
LoginDTO userform = (LoginDTO) request.getSession().getAttribute("LoginDTO");
  String url = "";
 if((String)request.getParameter("addE") != null)
   {url = request.getParameter("addE");
     request.getSession().setAttribute("addE",url);
   }
   
  List buttons=new ArrayList();//定义按钮属性
  
  buttons.add(new Button("Btn_save","保 存","rec070102","save()"));
  buttons.add(new Button("Btn_next","下一步","rec070103","next()"));
  
  buttons.add(new Button("Btn_back","返 回","rec999997","go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"addE,initAddEmployInviteForm\")"));
  pageContext.setAttribute("buttons",buttons);
  %>
<html>
<lemis:base/>
<lemis:body>
<script src="/lemis/js/lemisTree.js"></script>

  <%//标题部分%>
  <lemis:title title="增加单位招聘信息"/>
  <%//录入部分%>
  <html:form action="/Rec0701Action.do?method=addEmployInvite" method="POST">
  <lemis:tabletitle title="单位基本信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
     <tr>
      <html:hidden property="aab001"/>
      <html:hidden  property="acb200" />
      <lemis:texteditor property="aab004" label="单位名称" maxlength="100" disable="true" colspan="3" />
      <lemis:codelisteditor type="aab019" label="单位类型"  isSelect="false"  redisplay="true"/>
      </tr>
    <tr>
     <lemis:codelisteditor type="aab020" label="经济类型"  isSelect="false"  redisplay="true"/>
      <lemis:codelisteditor type="aab022" label="行业类型"   isSelect="false"  redisplay="true"/>
      <lemis:texteditor property="aab003" label="组织机构代码" maxlength="15" disable="true"/>
      </tr> 
  </table>
  <lemis:tabletitle title="本次招聘联系信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
    <tr>
       <html:hidden property="acb230"/>
       <lemis:texteditor property="acb231" label="大会招聘期数" maxlength="20"  required="true" disable="true"/>
	   <lemis:formateditor mask="date" property="acb232" label="招聘日期" disable="true" required="false" format="true" />
	   <lemis:texteditor property="acb233" label="招聘地点" maxlength="20"  disable="true" required="false" />
    
    </tr>
   <tr>
      <lemis:texteditor  property="acb206" label="发布名称" disable="false" required="true" colspan="2"/>
      <td>
      <select name=sel onchange="bao(this.options[this.options.selectedIndex].value)">
           <option value="">请选择
           <option value="1">职介所名称
           <option value="2">招聘单位名称
      </select>
      </td>
      <lemis:formateditor mask="money"  property="acb209" label="收费金额" disable="false" required="false"/>
    </tr>
    <tr>
      <lemis:codelisteditor type="acb201" label="招聘方式"  isSelect="false" redisplay="true" required="false" key="2"/>
	     <lemis:texteditor property="aae004" label="联系人" maxlength="20" disable="false" required="true" />
		  <lemis:texteditor property="aae005" label="联系电话" maxlength="20"  disable="false" required="true" />
    
    </tr>

	<tr>
     <lemis:formateditor mask="date" property="aae043" label="登记日期" disable="false" required="true" />
     <lemis:texteditor property="acb202" label="报名地点" maxlength="30" disable="false" colspan="3"/>
   	  
    </tr>
    <tr>
      <lemis:texteditor property="acb204" label="工作地点" maxlength="80" disable="false" colspan="3" />
      <lemis:texteditor property="acb205" label="乘车路线" maxlength="200" disable="false"/>
    </tr>  
    <tr>
      <lemis:texteditor property="acb203" label="招聘地区" maxlength="30" disable="false" colspan="3"/>
      <lemis:formateditor mask="card" property="aac002" label="联系人身份号码" disable="false" required="false"/>
     
    </tr>
    <tr>
      <lemis:formateditor mask="date" property="acb207" label="面试日期" disable="false" required="false"/>
      <lemis:texteditor property="acb20c" label="面试地点" maxlength="50"  disable="false" colspan="3" />
    </tr>
    <tr>    
      <lemis:texteditor property="acb20f" label="面试详细说明" maxlength="200" disable="false" colspan="3" />
      <lemis:texteditor property="acb20b" label="分配职介员" maxlength="30"  disable="false"/>
    </tr> 
    <tr>
      <lemis:texteditor property="aae013" label="备注" maxlength="100"  disable="false" colspan="5"/>
    
    </tr>
    			<tr>
				<!--<lemis:texteditor property="aae011" label="经办人" disable="false"  />
				<lemis:texteditor property="aae017" label="经办机构" disable="false"  />
				<lemis:texteditor property="aae036" label="经办日期"/>-->

					<td>经办人</td>
					<td><lemis:operator /></td>
					<td>经办机构</td>
					<td><lemis:operorg /></td>
					<td>经办日期</td>
					<td><lemis:operdate /></td> 
				
			</tr>
    <tr>
       <html:hidden property="aae011"/>
       <html:hidden property="aae017"/>
       <html:hidden property="aae036"/>
    </tr>  
     </html:form>
  </table>
  <lemis:buttons buttonMeta="buttons"/>
  <%//界面模型规定的其他标配部分%>
 <%//隐藏域，用于状态保持%> 
    <INPUT type="hidden" name="url" value="<%=url%>" /> 
  <lemis:errors/>
  <lemis:bottom/></lemis:body>
  </html>
  
  <script language="javascript" >
  //上一步
  function back(url){
	  //window.history.back();
	  location.href=url;
  }
  // 判断日前的先后顺序
  function checkDate(obj) {
  	if (!compareDate(obj.aae030.value,obj.aae043.value)) {
  		alert("招聘开始日期应该晚于登记日期！");
  		obj.aae030.focus();
  		return false;
  	}
  	if(obj.acb207.value!="")
  	{
  	if (!compareDate(obj.acb207.value,obj.aae030.value)) {
  		alert("面试日期应该晚于招聘开始日期！");
  		obj.acb207.focus();
  		return false;
  	}
  	if (!compareDate(obj.aae031.value,obj.acb207.value)) {
  		alert("招聘终止日期应该晚于面试日期！");
  		obj.aae031.focus();
  		return false;
  	}
}
  	return true;
  }
  //保存
  function save(){
   
    var obj = document.forms[0];
    
      var ok = checkValue(obj);
   if (!ok){
      return ok;
  }

		var url = document.all("url").value;
		
		obj.action= '<html:rewrite page="/Rec0701Action.do?method=addEmployInvite"/>';
		obj.action = obj.action+"&addE="+url;
    obj.submit();
  }
  //时间的改变,招聘终止时间比开始时间晚15天

function addDateTime(){
  var str =  document.all.aae030.value;
  var addt = 20;
  var reg1 = /^(\d{4,4})-(\d{2,2})-(\d{2,2})$/; 
  var r = str.match(reg1); 
  d= new Date(r[1], --r[2],r[3]*1+addt); 
  var iFullYear=d.getFullYear()
  var iMonth=parseInt(d.getMonth())+1;
  var iDate=parseInt(d.getDate());
  if(iMonth<10){
  	iMonth="0"+iMonth;
  }
  if(iDate<10){
  	iDate="0"+iDate;
  }
  var FullDate=iFullYear+"-"+iMonth+"-"+iDate;
  document.all.aae031.value = FullDate;
//  return FullDate;
}

  //下一步

function next (obj){
 var aab001= document.forms[0].aab001.value;
 var ok = document.all("acb200").value;
    if (ok=="")
   {
    alert("资料未保存，请保存后再进行下一步操作！");
    return false;
    }
    obj = document.forms[0];
  
    obj.action= '<html:rewrite page="/Rec0702Action.do?method=toNext&"/>'+"acb200="+ok+"&aab001="+aab001;
    obj.action=obj.action+"&nextP="+location.href.replace(/\&/g,";nextP;");
   
    obj.submit();
  }
  
  function bao(s)
{
    var acb206,aae004,aae005,acb202;
    
    if(s==1)
    {
    acb206="<%=userform.getAab300()%>";
    aae004="<%=userform.getBsc012()%>";
    aae005="<%=userform.getAae005()%>";
    acb202="<%=userform.getAae006()%>";
    if(acb206=="null"){
       document.forms[0].acb206.value="";
    }else{
      document.forms[0].acb206.value=acb206;
    }
    if(aae004=="null"){
       document.forms[0].aae004.value="";
    }else{
       document.forms[0].aae004.value=aae004;
    }
    if(aae005=="null"){
       document.forms[0].aae005.value="";
    }else{
       document.forms[0].aae005.value=aae005;
    }
    }
    else if(s==2) 
    {
    var aab004=document.forms[0].aab004.value;    
    aae004="<%=form.getAae004()%>";
    aae005="<%=form.getAae005()%>";
    if(aab004=="null"){
       document.forms[0].acb206.value="";
    }else{
      document.forms[0].acb206.value=aab004;
    }
    if(aae004=="null"){
       document.forms[0].aae004.value="";
    }else{
       document.forms[0].aae004.value=aae004;
    }
    if(aae005=="null"){
       document.forms[0].aae005.value="";
    }else{
       document.forms[0].aae005.value=aae005;
    }
    }
    
    document.all.sel.options[0].selected=true;
}
</script>

