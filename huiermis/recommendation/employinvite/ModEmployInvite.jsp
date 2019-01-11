<!--/recommendation/employinvite/ModEmployInvite.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<lemis:body>
<% 
    String menuId=request.getSession().getAttribute("menuId").toString();
  String modE = (String)request.getSession().getAttribute("modE");
  String date = DateUtil.getSystemCurrentTime().toString();
  //定义按钮
  List buttons=new ArrayList();//定义按钮属性
  buttons.add(new Button("Btn_save", "保 存", "rec040204", "save()"));
  buttons.add(new Button("Btn_back", "返 回", "rec999997", "go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close", "关 闭", "rec999999", "closeWindow(\"modE,Rec0402Form\")"));
  pageContext.setAttribute("buttons",buttons);
%>
  <%//标题部分%>
  <lemis:title title="修改单位招聘信息"/>

  <%//录入部分%>
  <lemis:tabletitle title="单位招聘信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  <html:form action="/Rec0402Action.do?method=modEmployInvite" method="POST">
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
  <lemis:tabletitle title="单位招聘信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
     <tr>      
      <lemis:texteditor  property="acb206" label="发布名称" maxlength="20" disable="false" required="true" colspan="3" />
      <lemis:formateditor mask="money"  property="acb209" label="收费金额" disable="false" required="false"/>
    </tr>
    <tr>
      <lemis:codelisteditor type="acb201" label="招聘方式"  isSelect="false"  key="1" redisplay="true" required="false" />
	     <lemis:texteditor property="aae004" label="联系人" maxlength="20" disable="false" required="true" />
		  <lemis:texteditor property="aae005" label="联系电话" maxlength="20"  disable="false" required="true" />
    
    </tr>

	<tr>
     <lemis:formateditor mask="date" property="aae043" label="登记日期"  disable="false" required="true" format="true"/>
      <lemis:texteditor property="acb204" label="工作地点" maxlength="80" disable="false" colspan="3" />
    </tr>
    <tr>
      
      <lemis:texteditor property="acb205" label="乘车路线" maxlength="200" disable="false"/>
      <lemis:texteditor property="acb202" label="报名地点" maxlength="30" disable="false" colspan="3"/>
    </tr>
    <tr>
      
     
    </tr>
    <tr>
      <lemis:texteditor property="acb203" label="招聘地区" maxlength="30" disable="false" colspan="3"/>
      <lemis:formateditor mask="card" property="aac002" label="联系人身份号码" disable="false" required="false"/>
     
    </tr>
    
    <tr>
      <lemis:formateditor mask="date" property="acb207" label="面试日期" disable="false" required="false" format="true"/>
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
       <html:hidden property="aae011"/>
       <html:hidden property="aae017"/>
       <html:hidden property="aae036"/>
    </tr> 
  </html:form>
  </table>
  <lemis:buttons buttonMeta="buttons"/>

  <%//界面模型规定的其他标配部分%>
  
  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script language="javascript" >
  //上一步
  function back(url){
    location.href=url;
  }
  //保存
  function save(){
	
    var obj = document.forms[0];
	//addDateTime()
	var ok = checkValue(obj);
    if (!ok){
      return ok;
    }
   
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=modsaveEmployInvite"/>';
    obj.submit();
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
  // 检查日前的先后顺序 form的字符串和日期的先后顺序字符串，从大到小
  function checkDates(obj,obj2) {
  	if (obj2==null) {
  		alert("需要比较的日期不足，不能比较！");
	  	return 	false;
  	}
  	var cDate = obj2.split(";");
  	if (obj2.length < 2) {
  		alert("需要比较的日期不足，不能比较！");
	  	return 	false;
  	}
  	var oDate	=	new Array();
  	for (var i=0;i<cDate.length;i++) {
  		oDate[i]	=	eval(obj+"."+cDate[i]);
  	}
  	for (var i=1;i<oDate.length;i++) {
  		if (checkDate(oDate[i-1],oDate[i])) {
  		} else {
  			return false;
  		}
  	}
  	return true;
  }
 
</script>

