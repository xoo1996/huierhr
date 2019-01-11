<!--/recommendation/employinvite/ModEmptyPost.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
	<lemis:base />
	<script src="/lemis/js/lemisTree.js"></script>
	<lemis:body>
		<%

			
			List buttons = new ArrayList();//定义按钮属性
			
			buttons.add(new Button("Btn_save","保 存", "rec040210", "save()"));
			
			buttons.add(new Button("Btn_back", "返 回", "rec999997", "go2Page(\"recommendation\",\"2\")"));
			buttons.add(new Button("Btn_close", "关 闭", "rec999999", "closeWindow(\"modP,modEmptyPostForm\")"));
			pageContext.setAttribute("buttons", buttons);
%>
		<%//标题部分%>
		<lemis:title title="修改招聘空岗信息" />
		<%//录入部分%>
		<lemis:tabletitle title="招聘空岗信息" />
		<table class="tableInput">
			<lemis:editorlayout />
			<html:form action="/Rec0402Action.do" method="POST">
				<tr>
					<lemis:texteditor property="aab004" label="单位名称" maxlength="100" disable="true" colspan="3" />
					<lemis:codelisteditor type="acb201" label="招聘方式" isSelect="false" redisplay="true" />
				</tr>
				<tr>
					<lemis:texteditor property="aca112" required="true" label="工种名称" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112,document.all.aca111)" disable="false" />
					<html:hidden property="aca111" />
					<lemis:texteditor property="acb216" label="工种说明" maxlength="50" disable="false" required="true" />
					<lemis:texteditor property="acb21o" label="推荐比例" disable="true"  />
				</tr>
			    <tr>
					<lemis:formateditor mask="date" property="aae030"  label="招聘开始日期" disable="false" format="true" required="true" />
					<lemis:formateditor mask="date" property="aae031" label="招聘终止时间"  disable="false" format="true" onclick="addDateTime()" required="true"/>
					<html:hidden property="aaeyxq" />
					<lemis:formateditor mask="nnnnnnnn" property="acb21d" label="人数" disable="false" required="true"/>
               </tr>
				<tr>
					
					<lemis:formateditor mask="nnnnnnnn" property="acb218" label="已推荐人数" disable="true" required="false" />
					<lemis:formateditor mask="nnnnnnnn" property="acb21a" label="已成功人数" disable="true" required="false" />
				    <lemis:texteditor property="acb217" label="招收区域" maxlength="30" disable="false" />
				</tr>
				
				<tr>
					
					<lemis:texteditor property="acb215" label="招收区域限制" maxlength="100" disable="false" />
					<lemis:codelisteditor type="aac048" label="用工形式" isSelect="true" redisplay="true" required="true" />
				    <lemis:formateditor mask="nn" property="acb214" label="合同期限(月)" disable="false" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="nnnnnn"  property="acb21h" label="劳动报酬（元）" disable="false" required="false" onblur="zdgz()"/>
   					<html:hidden property="ac21hb" />
					<lemis:codelisteditor type="acb21c" label="是否招收外来人员" isSelect="true" redisplay="true" />
					<lemis:codelisteditor type="acb035" label="是否招收大龄人员" isSelect="true" redisplay="true" />
					
				</tr>
				
				<tr>
					
					
					<lemis:texteditor property="acb21i" label="工资待遇说明" maxlength="100" disable="false" colspan="5" />
				</tr>
				

				<tr>
					<html:hidden property="aab001" />
					<html:hidden property="acb200" />
					<html:hidden property="acb208" />
					<html:hidden property="acb210" />
				</tr>
				<lemis:tabletitle title="空岗条件信息" />
				<table class="tableInput">
					<lemis:editorlayout />
					<tr>



						<lemis:codelisteditor type="aac011" label="文化程度" isSelect="true" redisplay="true" required="true" />
						<lemis:codelisteditor type="aac004" label="性别" isSelect="true" redisplay="true" required="false" />
						<lemis:codelisteditor type="aac015" label="职业资格等级" isSelect="true" required="false" />
					</tr>

					<tr>
						<lemis:formateditor mask="nnn" property="acb221" label="最低年龄" disable="false" required="true" />
						<lemis:formateditor mask="nnn" property="acb222" label="最高年龄" disable="false" required="true" />
						<lemis:formateditor mask="nnn.n" property="aac034" label="身高(cm)" disable="false" required="false" />
					</tr>

					<tr>
						<lemis:formateditor mask="n.n" property="aac036" label="视力" disable="false" required="false" />
						<lemis:codelisteditor type="aac009" label="户口性质" isSelect="true" redisplay="true" />
						<lemis:codelisteditor type="bac299" label="人员类别" isSelect="true" redisplay="true" />
					</tr>
					<tr>
						
						<lemis:codelisteditor type="aac017" label="婚姻状况" isSelect="true" redisplay="true" />
						<lemis:codelisteditor type="aac024" label="政治面貌" isSelect="true" redisplay="true" />
						<lemis:codelisteditor type="acb228" label="是否提供住宿" isSelect="true" redisplay="true" />
					</tr>


					<tr>
						
						
						
					</tr>

					<tr>
						<lemis:codelisteditor type="aac014" label="专业技术职务" isSelect="true" required="false" />
						<lemis:texteditor property="aae013" label="备注" maxlength="100" disable="false" colspan="5" />

					</tr>
					<tr>
						
					</tr>
					
					
	<tr>
      <lemis:codelisteditor type="aae011" label="经办人" isSelect="false" />
        <lemis:codelisteditor type="aae017" label="经办机构" isSelect="false" />
      <lemis:formateditor mask="date" property="aae036" label="经办日期" disable="true" required="false" />
    </tr>

				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="buttons" />
		<%//界面模型规定的其他标配部分%>
		<%//隐藏域，用于状态保持%>
	

		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">
  //工种说明的改变
function change(obj){
	obj.acb216.value=obj.aca112.value;
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
    var ok = checkValue(obj);
    if (!ok){
      return ok;
    }
     var aca112= document.all("aca112").value;
     var aca111= document.all("aca111").value;
     var acb221=parseInt(document.all("acb221").value);
	 var acb222=parseInt(document.all("acb222").value);
	if (acb221>acb222)
	  {
		alert("最低年龄不能大于最大年龄");
		return false;
	}
    if (aca112=="")
    {
    alert ("工种名称不能为空。");
    return false;
    }
    var acb21d = document.all("acb21d").value;
    if ((acb21d==""))
    {
    alert("人数错误！");
    return false;
    }
    var acb218 = document.all("acb218").value;
    var acb21a = document.all("acb21a").value;
    var acb21o = document.all("acb21o").value;
    if(acb21d=="")acb21d="0";
 
    if (parseInt(acb21a)>parseInt(acb21d)*parseInt(acb21o)){alert("已成功人数应小于最大推荐人数，请重新确定数据！");document.all("acb21a").focus();return;}
    if (parseInt(acb218)>parseInt(acb21d)*parseInt(acb21o)){alert("已推荐人数应小于最大推荐人数，请重新确定数据！");document.all("acb218").focus();return;}
    var beginDate = document.all("aae030").value;
    var endDate = document.all("aae031").value;
   ok = compareDate(endDate,beginDate);
   if (!ok){
     alert("“招聘开始日期“不能晚于“招聘终止时间”，请重新确认招聘时间段。");
     document.all("aae031").focus();
      return ok;
   }
    obj.action= '<html:rewrite page="/Rec0402Action.do?method=modsaveEmptyPost"/>';
    obj.submit();
  }
  function addDateTime(){
  var str =  document.all.aae030.value;
  //var addt = 20;
  var addt =  document.all.aaeyxq.value;
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

