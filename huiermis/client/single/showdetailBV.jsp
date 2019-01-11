<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返 回","history.back()");
	pageContext.setAttribute("buttons",buttons);
 %>
<html>
	<script src="/lemis/js/lemisTree.js"></script>
<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="惠耳售后服务调查表详情" />
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
						disable="true" />
					<lemis:texteditor property="bvleftnum" label="左耳助听器机身编号" required="false"
						disable="true" />
				</tr>
				<tr>
					<lemis:texteditor property="bvrighttype" label="右耳助听器型号" required="false"
						disable="true" />
					<lemis:texteditor property="bvrightnum" label="右耳助听器机身编号" required="false"
						disable="true" />
				</tr>
				<th colspan="5">回访记录</th>
				<lemis:tabletitle title="第一次回访" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase1" label="调查情况与处理对策"
						required="true" disable="true" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod1" isSelect="false" label="回访方法" redisplay="true" required="true" />
					<lemis:codelisteditor type="bveffect1" isSelect="false" label="效果评定" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor1" label="回访者" required="true" disable="true" />
					<lemis:formateditor mask="date" property="bvdate1" required="true" label="回访日期" disable="true" format="true"/>				
				</tr>	
				</table>
				<lemis:tabletitle title="第二次回访" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase2" label="调查情况与处理对策"
						required="false" disable="true" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod2" isSelect="false" label="回访方法" redisplay="true" required="false" />
					<lemis:codelisteditor type="bveffect2" isSelect="false" label="效果评定" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor2" label="回访者" required="false" disable="true" />
					<lemis:formateditor mask="date" property="bvdate2" required="false" label="回访日期" disable="true" format="true"/>			
				</tr>	
				</table>
				<lemis:tabletitle title="第三次回访" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="bvcase3" label="调查情况与处理对策"
						required="false" disable="true" colspan="20"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="bvmethod3" isSelect="false" label="回访方法" redisplay="true" required="false" />
					<lemis:codelisteditor type="bveffect3" isSelect="false" label="效果评定" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="bvvisitor3" label="回访者" required="false" disable="true" />
					<lemis:formateditor mask="date" property="bvdate3" required="false" label="回访日期" disable="true" format="true"/>			
				</tr>	
				<tr>
					<lemis:codelisteditor type="bvassess" isSelect="false" label="最终效果评定" redisplay="true" required="false" />
				</tr>
				</table>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="buttons" />
		<lemis:bottom />
	</lemis:body>
</html>
