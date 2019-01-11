<!--/recommendation/employassembly/AddEmptyPost.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.apps.recommendation.employassembly.dto.Rec0702DTO" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<script  src="/lemis/js/lemisTree.js" ></script>
<lemis:body>
<% 
    String menuId=request.getSession().getAttribute("menuId").toString();

  //获取信息
  Rec0702DTO dto = null;
  String aab001 = "";    //单位编号
  String aab004 = "";    //单位名称
  String acb200 = "";    //招聘编号
  String acb201 = "";    //招聘方式
  
  dto = (Rec0702DTO)request.getSession().getAttribute("Rec0702DTO");
  if (dto.getAab001()!=null) aab001 = dto.getAab001().trim();
  if (dto.getAab004()!=null) aab004 = dto.getAab004().trim();
  if (dto.getAcb200()!=null) acb200 = dto.getAcb200().trim();
  if (dto.getAcb201()!=null) acb201 = dto.getAcb201().trim();

  List buttons=new ArrayList();//定义按钮属性
  buttons.add(new Button("Btn_save","保 存","rec070209","save()"));
 
  buttons.add(new Button("Btn_back","返 回","rec999997","go2Page(\"recommendation\",\"2\")"));
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"Rec0702DTO,Rec0702Form,addP\")"));
  pageContext.setAttribute("buttons",buttons);
%>
  <%//标题部分%>
  <lemis:title title="增加招聘空岗信息"/>

  <%//录入部分%>
  <lemis:tabletitle title="空岗信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  <html:form action="/Rec0702Action.do?method=addEmptyPost" method="POST">
   <tr>
      <lemis:texteditor property="aab004" label="单位名称" maxlength="100"  value="<%=aab004%>"  disable="true" colspan="3" />
      <lemis:codelisteditor type="acb201" label="招聘方式"   isSelect="false" key="<%=acb201%>"  redisplay="true"/>
   </tr>

    <tr>
   		 <lemis:texteditor property="aca112" required="true" label="工种名称" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112,document.all.aca111)" onkeypress="setWorkTypeTreequery(this,document.all.aca112,document.all.aca111)" disable="false" />
	  	<html:hidden property="aca111" />
      	<lemis:texteditor property="acb216" label="工种说明" maxlength="50"  disable="false" required="true" required="true" onfocus="change()"/>
	    <lemis:codelisteditor type="aac048" label="用工形式"  isSelect="true"   redisplay="true" required="true"  />

	</tr>
	   <tr>
		<lemis:formateditor mask="date" property="aae030"  label="招聘开始日期" disable="false" required="true" format="true" />
		<lemis:formateditor mask="date" property="aae031" label="招聘终止时间"  disable="false" onclick="addDateTime()" required="true"/>
		<html:hidden property="aaeyxq" />
		<lemis:formateditor mask="nnnnnnnn" property="acb21d" label="人数" disable="false" required="true"/>
    </tr>
    <tr>
           

      <lemis:texteditor property="acb217" label="招收区域" maxlength="30"  disable="false"/>
      <lemis:texteditor property="acb215" label="招收区域限制" maxlength="100"  disable="false"/>
      <lemis:formateditor mask="nn" property="acb214" label="合同期限（月）"   disable="false" required="false"/>
      </tr>
       
    <tr>
     
     <lemis:texteditor property="acb21i" label="工资待遇说明" maxlength="100"  disable="false" colspan="5"/>
    </tr>
   
    <tr>
     <lemis:formateditor mask="nnnnnn"  property="acb21h" label="劳动报酬（元）" disable="false" required="false" onblur="zdgz()"/>
     <html:hidden property="ac21hb" />
     <lemis:codelisteditor type="acb21c" label="是否招收外来人员"  isSelect="true"   redisplay="true"/>
     <lemis:codelisteditor type="acb035" label="是否招收大龄人员"  isSelect="true"   redisplay="true"/>
    
    </tr>
	
     <tr>
     <html:hidden property="acb210" />
	  <html:hidden property="acb200" value="<%=acb200%>" />
	 <html:hidden property="aab001" value="<%=aab001%>" />
    </tr>

  </table>
   <lemis:tabletitle title="空岗条件信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
    <tr>

   

      <lemis:codelisteditor type="aac011" label="文化程度"  isSelect="true"   redisplay="true" required="true"/>
      <lemis:codelisteditor type="aac004" label="性别" isSelect="true"   redisplay="true" required="false"/>
      <lemis:codelisteditor type="aac015" label="职业资格等级" isSelect="true" required="false" />
      </tr>

    <tr>
      <lemis:formateditor mask="nnn" property="acb221" label="最低年龄" disable="false" required="true"/>
      <lemis:formateditor mask="nnn" property="acb222" label="最高年龄" disable="false" required="true"/>
	   <lemis:formateditor mask="nnn.n" property="aac034" label="身高(cm)" disable="false" required="false"/>
      </tr>

    <tr>
      <lemis:formateditor mask="n.n" property="aac036" label="视力" disable="false" required="false"/>
      <lemis:codelisteditor type="aac009" label="户口性质" isSelect="true"   redisplay="true"/>
      <lemis:codelisteditor type="bac299" label="求职人员类别" isSelect="true"   redisplay="true"/>
      </tr>
      <tr>
       <lemis:codelisteditor type="aac017" label="婚姻状况" isSelect="true"   redisplay="true"/>
      <lemis:codelisteditor type="aac024" label="政治面貌" isSelect="true"   redisplay="true"/>
      <lemis:codelisteditor type="acb228" label="食宿情况" isSelect="true"   redisplay="true"/>
    </tr>
    <tr>
       <lemis:codelisteditor type="aac014" label="专业技术职务" isSelect="true" required="false" />
       <lemis:texteditor property="aae013" label="备注" maxlength="100"  disable="false" colspan="5" />
       
    </tr>
    
    <tr>
						<td>
							经办人
						</td>
						<td>
							<lemis:operator />
						</td>
						<td>
							经办机构
						</td>
						<td>
							<lemis:operorg />
						</td>
						<td>
							经办日期
						</td>
						<td>
							<lemis:operdate />
						</td>
					</tr>
  </table>
    </html:form>
  <lemis:buttons buttonMeta="buttons"/>
  <%//界面模型规定的其他标配部分%>
 <%//隐藏域，用于状态保持%>

  <lemis:errors/>
  <lemis:bottom/></lemis:body>
  </html>
  <script language="javascript" >
//工种说明的改变
function change(){
	document.all("acb216").value=document.all("aca112").value;
}

function zdgz(){
	if(parseInt(document.all("ac21hb").value)>parseInt(format(document.all("acb21h").value)))
	{
	   alert("不能低于当地最低工资要求"+document.all("ac21hb").value+"元");
	   return false;
	}
}
  //保存
  function save(){

    var obj = document.forms[0];
	var acb221=parseInt(document.all("acb221").value);
	var acb222=parseInt(document.all("acb222").value);
    var aca112 = document.all("aca112").value;
    var acb210 = document.all("acb210").value;
	if (acb221>acb222)
	  {
		alert("最低年龄不能大于最高年龄");
		return false;
	}
    if (acb210!=null&&acb210!="")
    {
    return false;
    }
    if (aca112 =="")
    {
      alert("工种名称不能为空！");
      return false;
    }
    var ok = checkValue(obj);
    if (!ok){
      return ok;
    }
    
    var acb21d = document.all("acb21d").value;//人数
    if ((acb21d==""))
    {
    alert("人数错误！");
    return false;
    }
    var beginDate = document.all("aae030").value;
    var endDate = document.all("aae031").value;
   ok = compareDate(endDate,beginDate);
   if (!ok){
     alert("“招聘开始日期“不能晚于“招聘终止时间”，请重新确认招聘时间段。");
     document.all("aae031").focus();
      return ok;
   }
    obj.action= '<html:rewrite page="/Rec0702Action.do?method=saveEmptyPost&"/>';
	 obj.action = obj.action+"aab001="+ document.all("aab001").value;
	 obj.action = obj.action+"&acb200="+ document.all("acb200").value;
    obj.submit();
  }
   //时间的改变,招聘终止时间比开始时间晚15天

function addDateTime(){
  var str =  document.all.aae030.value;
 // var addt = 20;
  var addt =  1*document.all.aaeyxq.value;
  
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
  
</script>

