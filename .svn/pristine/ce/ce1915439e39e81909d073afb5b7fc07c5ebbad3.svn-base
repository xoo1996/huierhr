<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("提 交","saveData(document.forms[0])");
	buttons.put("重 置","document.forms[0].reset();");//这个模块下公用的按钮
	buttons.put("返 回","history.back()");
    pageContext.setAttribute("button", buttons);
    
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
	<script language="javascript">
		function saveData(obj){
			var count1=$("input[name=bvcase1]").val().length; 
			var count2=$("input[name=bvcase2]").val().length; 
			var count3=$("input[name=bvcase3]").val().length; 
			
			if(count1<20||(count2>0&&count2<20)||(count3>0&&count3<20)){
			alert('调查情况与处理对策不得少于20个字');return false;}
			if(!checkValue(obj)){
				return false;
			}
			
			obj.submit();
		}
		$(document).ready(function(){
			//$("input[name=pdtid]").bind("click",function(){
				$.ajax({
					 type:'post',
					 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
					 dataType:'json',
					 error:function(){
					   alert('获取数据错误！');
					 },
					 success:function(data){
								$("input[name=bvrighttype]").autocomplete(data,{
									max:10,
									matchContains:true,
									formatItem:function(data,i,max){
										return (data.proid + "=" + data.proname);
									}
								});	
								$("input[name=bvrighttype]").result(function(event, data, formatted) {
									if (data){
										var proname=data.proname;
										$("input[name=bvrighttype]").val(proname);
										}
								});
							}
					});
			});	
			$(document).ready(function(){
				//$("input[name=pdtid]").bind("click",function(){
					$.ajax({
						 type:'post',
						 url:'<%=basePath%>ProductAction.do?method=queryEMPro',
						 dataType:'json',
						 error:function(){
						   alert('获取数据错误！');
						 },
						 success:function(data){
									$("input[name=bvlefttype]").autocomplete(data,{
										max:10,
										matchContains:true,
										formatItem:function(data,i,max){
											return (data.proid + "=" + data.proname);
										}
									});	
									$("input[name=bvlefttype]").result(function(event, data, formatted) {
										if (data){
											var proname=data.proname;
											$("input[name=bvlefttype]").val(proname);
											}
									});
								}
						});
				});	
				
	</script>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="惠耳售后服务调查表新增" />
		<lemis:tabletitle title="用户基本信息信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/SingleClientAction.do?method=addBV" method="POST">
				<tr>
			    	 <lemis:texteditor property="ictid" label="用户编号" required="false"
						disable="true" />
					<lemis:texteditor property="ictnm" label="用户姓名" required="false"
						disable="true" />
					<lemis:texteditor property="gctnm" label="所属团体" required="false"
						disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="ictage" label="年龄" required="false"
						disable="true" />
					<lemis:texteditor property="ictgender" label="性别" required="false"
						disable="true" />
					<lemis:texteditor property="icttel" label="电话" required="false"
						disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="ictaddr" label="地址" required="false"
						disable="true" colspan="20"/>
				</tr>
				<tr>
					<lemis:texteditor property="bvlefttype" label="左耳助听器型号" required="false"
						disable="false" />
					<lemis:texteditor property="bvleftnum" label="左耳助听器机身编号" required="false"
						disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="bvrighttype" label="右耳助听器型号" required="false"
						disable="false" />
					<lemis:texteditor property="bvrightnum" label="右耳助听器机身编号" required="false"
						disable="false" />
				</tr>
				<th colspan="5">回访记录</th>
				<lemis:tabletitle title="第一次回访" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase1" label="调查情况与处理对策"
						required="true" disable="false" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod1" isSelect="true" label="回访方法" redisplay="true" required="true" />
					<lemis:codelisteditor type="bveffect1" isSelect="true" label="效果评定" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor1" label="回访者" required="true" disable="false" />
					<lemis:formateditor mask="date" property="bvdate1" required="true" label="回访日期" disable="false" format="true"/>				
				</tr>	
				</table>
				<lemis:tabletitle title="第二次回访" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase2" label="调查情况与处理对策"
						required="false" disable="false" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod2" isSelect="true" label="回访方法" redisplay="true" required="false" />
					<lemis:codelisteditor type="bveffect2" isSelect="true" label="效果评定" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor2" label="回访者" required="false" disable="false" />
					<lemis:formateditor mask="date" property="bvdate2" required="false" label="回访日期" disable="false" format="true"/>			
				</tr>	
				</table>
				<lemis:tabletitle title="第三次回访" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase3" label="调查情况与处理对策"
						required="false" disable="false" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod3" isSelect="true" label="回访方法" redisplay="true" required="false" />
					<lemis:codelisteditor type="bveffect3" isSelect="true" label="效果评定" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor3" label="回访者" required="false" disable="false" />
					<lemis:formateditor mask="date" property="bvdate3" required="false" label="回访日期" disable="false" format="true"/>			
				</tr>	
				<tr>
					<lemis:codelisteditor type="bvassess" isSelect="true" label="最终效果评定" redisplay="true" required="false" />
				</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

