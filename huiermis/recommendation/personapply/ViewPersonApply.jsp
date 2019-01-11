<!-- recommendation/personapply/ViewPersonApply.jsp -->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
	<lemis:base />
	<lemis:body>
		<%
            //定义按钮
            List buttons = new ArrayList();//定义按钮属性
            buttons.add(new Button("Btn_back","返回", "rec999997","go2Page(\"recommendation\",\"1\")"));
            buttons.add(new Button("Btn_close","关闭", "rec999999", "closeWindow(\"viewPA,Rec0201Form\")"));
            pageContext.setAttribute("buttons", buttons);
%>
		<!--//标题部分-->
		<lemis:title title="查看个人求职信息" />
		<lemis:tabletitle title="个人求职信息" />
		<table class="tableInput">
			<lemis:editorlayout />
			<html:form action="/Rec0201Action.do" method="POST">

				<tr>
					<html:hidden property="acc200" />
					<lemis:texteditor property="aac003" label="姓名" maxlength="20" disable="true" />
					<lemis:codelisteditor type="aac004" label="性别" isSelect="false" redisplay="true" />
					<lemis:texteditor property="aac002" label="公民身份号码" disable="true" />

				</tr>

				<tr>
					<lemis:formateditor mask="date" property="aac006" label="出生日期" disable="true" format="true" required="false"/>
					<lemis:codelisteditor type="aac011" label="文化程度" isSelect="false" redisplay="true" />
					<lemis:codelisteditor type="aac005" label="民族" isSelect="false" redisplay="true" />
				</tr>

				<tr>

					<lemis:codelisteditor type="aac024" label="政治面貌" isSelect="false" redisplay="true" />
					<lemis:codelisteditor type="bac299" label="求职人员类别" redisplay="true" required="false" isSelect="false" />
					<html:hidden property="bac298" value="" />
					<lemis:codelisteditor type="aac009" label="户口性质" isSelect="false" redisplay="true" />
				</tr>

				<tr>
					<lemis:texteditor property="aae015" label="个人电子信箱" maxlength="30" disable="true" />
					<lemis:texteditor property="aae006" label="通讯地址" maxlength="80" disable="true" colspan="3" />


				</tr>

				<tr>
					<lemis:texteditor property="aae004" label="联系人" maxlength="20" disable="true" />
					<lemis:texteditor property="aae005" label="联系电话" maxlength="20" disable="true" />
					<lemis:texteditor property="aae007" label="邮政编码" maxlength="6" disable="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac017" label="婚姻状况" isSelect="false" redisplay="true" />
					<lemis:codelisteditor type="aac033" label="健康状况" isSelect="false" redisplay="true" />
					<lemis:texteditor property="aac034" label="身高(CM)" disable="true" required="false" />
				</tr>

				<tr>

					<lemis:texteditor property="aac035" label="体重(KG)" disable="true" required="false" />
					<lemis:codelisteditor type="aac032" label="血型" isSelect="false" redisplay="true" />
					<lemis:texteditor property="aac036" label="视力" maxlength="20" disable="true" />
				</tr>		

				<tr>
				    <lemis:codelisteditor type="aac038" label="第一外语" isSelect="false" redisplay="true" />
					<lemis:codelisteditor type="aac039" label="第一外语熟练程度" isSelect="false" redisplay="true" />
					<lemis:codelisteditor type="aac041" label="第二外语" isSelect="false" redisplay="true" />
				</tr>

				<tr>
					<lemis:codelisteditor type="aac042" label="第二外语熟练程度" isSelect="false" redisplay="true" />
					<lemis:texteditor property="acc20e" label="其他外语" maxlength="30" disable="true" />
					<lemis:codelisteditor type="aac043" label="计算机水平" isSelect="false" redisplay="true" />

				</tr>

				<tr>
			        <lemis:texteditor property="aac025" label="籍贯" maxlength="100" disable="true" />
			        <lemis:codelisteditor type="aac014" label="专业技术职务" isSelect="false" required="false" />
			        <lemis:formateditor mask="date" property="aac007" label="参加工作日期" disable="true" required="false" format="true" />

				</tr>
				
				<tr>					
				<lemis:codelisteditor type="acc201" label="登记类别" isSelect="false" redisplay="true" />
				<lemis:texteditor property="aac021" label="失业证号码" maxlength="20" disable="true" />
				<lemis:codelisteditor type="acc207" label="是否境外求职" isSelect="false" redisplay="true" />
				
				</tr>
				<tr>
				<lemis:formateditor mask="date" property="aae043" label="登记日期" disable="true" format="true" required="false"/>
				<lemis:formateditor mask="date" property="ace014" label="有效日期" disable="true" required="false" format="true"/>
				<lemis:texteditor property="acc20b" label="信息采集员" maxlength="30" disable="true" />
				</tr>				
				<tr>
					<lemis:texteditor property="aae013" label="备注" maxlength="100" disable="true" colspan="3" />
				    <lemis:codelisteditor type="acb208" label="求职状态" isSelect="false" redisplay="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aae011" label="经办人" isSelect="false" />
					<lemis:codelisteditor type="aae017" label="经办机构" isSelect="false" />
					<lemis:formateditor mask="date" property="aae036" label="经办日期" disable="true" required="false" format="true"/>
				</tr>

			</html:form>
		</table>
		<lemis:buttons buttonMeta="buttons" />

		<%//界面模型规定的其他标配部分%>

		<lemis:errors />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">
  //上一步
  function back(url){
    //location.href=url;
    window.history.back();
  }
 </script>

