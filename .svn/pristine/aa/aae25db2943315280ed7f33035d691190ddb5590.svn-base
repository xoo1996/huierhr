<!--/recommendation/personapply/modiperson2.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!--个人新增页面-->
<lemis:base />
<script src="/lemis/js/lemisTree.js"></script>
<%
			
            List buttons=new ArrayList();		
            buttons.add(new Button("Btn_save","保 存","rec020105", "saveData(document.forms[0])"));
            buttons.add(new Button("Btn_back","返 回", "rec999997","go2Page(\"recommendation\",\"1\")"));
            buttons.add(new Button("Btn_close","关 闭", "rec999999","closeWindow(\"Rec0201Form\")"));
            pageContext.setAttribute("button", buttons);

            %>
<html>
	<lemis:body>
		<lemis:errors />
		<lemis:title title="个人信息修改" />
		<html:form action="/Rec0201Action.do" method="POST">
			<lemis:tabletitle title="个人求职意向" />
			<table class="tableInput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="aca112" label="第一意向" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112,document.all.aca111)" onkeypress="setWorkTypeTreequery(this,document.all.aca112,document.all.aca111)" disable="false" required="true" />
					<html:hidden property="aca111" />
					
						<lemis:texteditor property="aca112a" label="第二意向" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112a,document.all.aca111a)" onkeypress="setWorkTypeTreequery(this,document.all.aca112a,document.all.aca111a)" disable="false" />
						<html:hidden property="aca111a" />
					
						<lemis:texteditor property="aca112b" label="第三意向" style="cursor: hand" styleClass="text" onclick="setWorkTypeTree(this,document.all.aca112b,document.all.aca111b)" onkeypress="setWorkTypeTreequery(this,document.all.aca112b,document.all.aca111b)" disable="false" />
						<html:hidden property="aca111b" />
					
				</tr>
				<tr>
					<lemis:codelisteditor type="aab019" label="单位类型" isSelect="true" redisplay="true" />
					<lemis:codelisteditor type="aac048" label="用工形式" isSelect="true" redisplay="true" />
					<lemis:codelisteditor type="aab020" label="经济类型" isSelect="true" redisplay="true" />
					
				</tr>
				<tr>
				    <lemis:formateditor mask="nnnnnn"  property="acc034" label="工资待遇（元）" disable="false" required="false"/>
					<lemis:texteditor property="acc213" label="求职地区要求" required="false" disable="false" maxlength="20" />
					
				</tr>
			</table>
			<lemis:tabletitle title="个人基本信息" />
			<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:hidden property="aac001" />
					<lemis:formateditor mask="card" property="aac002" label="公民身份号码" required="false" disable="true" />
					<lemis:texteditor property="aac003" label="姓名" required="false" disable="true" maxlength="20" />
					<lemis:codelisteditor type="aac004" isSelect="false" label="性别" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" isSelect="false" required="false" label="民族" redisplay="false" />
					<lemis:formateditor mask="date" property="aac006" disable="true" label="出生日期" required="false" format="true"/>
					<lemis:codelisteditor type="aac017" label="婚姻状况" isSelect="false" redisplay="true" required="false" />

				</tr>
				<tr>
					<html:hidden property="bac298" />
					<lemis:codelisteditor type="aac009" label="户口性质" isSelect="true" redisplay="true" />
					<lemis:codelisteditor type="aac011" label="文化程度" redisplay="true" required="true" />

					<lemis:codelisteditor type="bac299" label="求职人员类别" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="aac010" required="false" label="户口所在地" disable="false" style="CURSOR:hand" onclick="setRegionTree(this,document.all.aac010,document.all.aab301)" />
					<html:hidden property="aab301" />

					<lemis:formateditor mask="date" property="aae043" label="登记时间" disable="false" required="true" format="true" />
					<lemis:texteditor property="aae005" label=" 联系电话" disable="false" maxlength="20" required="true" />
					<input name="aae005" type="hidden" />
				</tr>
				<tr>
					<lemis:texteditor property="aac025" label="出生地" required="false" disable="false" maxlength="100" />
					<lemis:codelisteditor type="aac024" isSelect="true" label="政治面貌" redisplay="true" />
					<lemis:formateditor property="aac036" label="视力" disable="false" mask="n.n" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="aac007" required="false" label="参加工作日期" disable="false" format="true" />
					<lemis:formateditor property="aac034" label='身高(CM)' disable="false" mask="nnn.n" required="false" />
					<lemis:formateditor property="aac035" label='体重(KG)' disable="false" mask="nnn.nn" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="aae006" label="地址" disable="false" maxlength="50" colspan="3" />
				   <lemis:formateditor property="aae007" label="邮政编码" required="false" mask="######" disable="false" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac033" isSelect="true" label="健康状况" redisplay="true" />
					<lemis:texteditor property="aae015" label="个人电子信箱" disable="false" maxlength="30" />
					<lemis:texteditor property="aac021" disable="true" label="失业证号码" required="false" maxlength="14" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac015" label="专业技术等级" redisplay="true" required="false" />
					<lemis:texteditor property="acc02i" label="国家职业资格证书号码" disable="false" maxlength="20" />
					<lemis:codelisteditor type="aac014" label="专业技术职务" redisplay="true" />
				</tr>
				
			
				<tr>
				    <lemis:codelisteditor type="aac032" isSelect="true" redisplay="true" label="血型" />
					<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="100" colspan="3" />

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
		
			<html:hidden property="acc200"  />
			<html:hidden property="acc210"  />
			<html:hidden property="acc210a" />
			<html:hidden property="acc210b" />

		</html:form>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>
<script language="javascript">
  

	function saveData(obj){
	
	if(!checkValue(obj)){
	return false;
	}
	obj.action ="/" + lemis.WEB_APP_NAME + "/recommendation/Rec0201Action.do?method=modPerson&aac002="+document.all("aac002").value+"&acc200="+document.all("acc200").value+"&acc210="+document.all("acc210").value+"&acc210a="+document.all("acc210a").value+"&acc210b="+document.all("acc210b").value+"&";
	obj.submit();
	}
	

</script>

