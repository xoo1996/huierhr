<!--/emis2005hz/lemis/basicinfo/modiperson.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.QueryInfo" %>
<%@ page import="org.radf.plat.util.global.GlobalNames" %>
<%@ page import="org.radf.apps.basicinfo.form.PersonForm" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--个人新增模块-->
<%
    String menuid = "reg";
    String title = "";
    menuid = request.getParameter("menuId");
	String buttonId = request.getParameter("buttonId");
    String stringData = request.getParameter("stringData");
    if ("alter".equals(menuid))
    {
        title = "个人基本信息变更";
    }
    else
    {
        title = "个人基本信息新增";
    }
    if ((null == stringData) || ("".equals(stringData)))
    {
        QueryInfo qi = (QueryInfo) pageContext
                .findAttribute(GlobalNames.QUERY_INFO);
        if (null != qi)
        {
            stringData = qi.getStringData();
        }
    }
    //获取上一步的url
    PersonForm fm = (PersonForm) request.getAttribute("OperPersonForm");
    String url = request.getParameter("url2");
    if (url == null || "".equals(url))
    {
        if (fm != null)
        {
            url = fm.getUrl2();
        }
    }
    if (url == null)
    {
        url = "";
    }
    String rurl = null;
    if ("".equals(url))
    {
        url = "queryPerson.do?method=findPerson;amp;menuId=" + menuid;
        rurl = "queryPerson.do?method=enterPerson;amp;menuId=" + menuid;
    }
    List buttons=new ArrayList();
    if ("alter".equals(menuid))
    {
    	buttons.add(new Button("alterSave","保 存",buttonId,"saveData(document.forms[0])"));
    }
    else
    {
    	buttons.add(new Button("save","保 存","bas020122","saveData(document.forms[0])"));
    }
    
    buttons.add(new Button("reset","重 置","bas999998","document.forms[0].reset();"));
    buttons.add(new Button("goBack","返 回","bas999997","go2Page(\"basicinfo\",\"1\")"));
    buttons.add(new Button("close","关 闭","bas999999","closeWindow(\"OperPersonForm\")"));
   
   
    String s = rurl;
    if (s == null)
    {
        s = url;
    }
    
    pageContext.setAttribute("button", buttons);

	System.out.println("##sesurl11##"+request.getParameter("url1"));
	System.out.println("##sesurl22222##"+request.getParameter("url2"));
%>
<html>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="<%=title%>" />
		<lemis:tabletitle title="个人基本信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form action="/operatePerson.do" method="POST">
				<tr>
					<html:hidden property="aac001" />
					<lemis:formateditor mask="card" property="aac002" label="公民身份号码" required="true" disable="false" />
					<lemis:texteditor property="aac003" label="姓名" required="true" disable="false" maxlength="20" />
					<lemis:codelisteditor type="aac004" isSelect="true" label="性别" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac005" isSelect="true" required="true" label="民族" redisplay="true"/>
					<lemis:formateditor mask="date" property="aac006" required="true" disable="ture" label="出生日期" format="true"/>
					<lemis:codelisteditor type="aac009" label="户口性质" isSelect="true" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:codelisteditor type="bac298" label="人员类别" redisplay="true" required="true" />
					<lemis:texteditor property="ssjqnm" required="true" label="人员辖地" disable="false" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
					
					<lemis:texteditor property="acc025"  label="劳动手册号" disable="false"  maxlength="18"/>
					
					<html:hidden property="cce001" />
				</tr>
				<tr>
					<lemis:texteditor property="aac010" required="false" label="户口所在地区" disable="false" style="CURSOR:hand" onclick="setRegionTree(this,document.all.aac010,document.all.aab301)" />
					<html:hidden property="aab301" />
					<lemis:codelisteditor type="aac024" isSelect="true" label="政治面貌" redisplay="true" />
					<lemis:formateditor property="aac034" label='身高(CM)' disable="false" mask="nnn.n" required="false" />
				</tr>
				<tr>
					<lemis:formateditor property="aac035" label='体重(KG)' disable="false" mask="nnn.nn" required="false" />
					<lemis:formateditor property="aac036" label="视力" disable="false" mask="n.n" required="false" />
					<lemis:codelisteditor type="aac032" isSelect="true" redisplay="true" label="血型" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac033" isSelect="true" label="健康状况" redisplay="true" />
					<lemis:codelisteditor type="aac017" label="婚姻状况" redisplay="true"/>
					<lemis:formateditor mask="date" property="aac007" required="false" label="参加工作日期" disable="false" format="true"/>
				</tr>
				<tr>
					<lemis:codelisteditor type="aac011" label="文化程度" redisplay="true" required="false" />
					<lemis:formateditor property="aae007" label="邮政编码" required="false" mask="######" disable="false" />
					<lemis:texteditor property="aae005" label="联系电话" disable="false" maxlength="20" />
				</tr>
				<tr>
					<lemis:texteditor property="aae015" label="个人电子信箱" disable="false" maxlength="50" />
					<lemis:codelisteditor type="aac015" label="专业技术等级" redisplay="true" />
					<lemis:texteditor property="acc02i" label="国家职业资格证书号码" disable="false" maxlength="20" />
				</tr>
				<tr>
					<lemis:codelisteditor type="aac014" label="专业技术职务" redisplay="true" />
					<lemis:texteditor property="aac021" label="失业证号码" disable="false" maxlength="14"/>
					<lemis:texteditor property="aac025" label="出生地" required="false" disable="false" maxlength="100" />
				</tr>
				<tr>
					<lemis:texteditor property="aae006" label="地址" disable="false" maxlength="50" />
					<lemis:texteditor property="aac022" label="个人拼音码" required="false" disable="false" maxlength="100" />
					<lemis:codelisteditor type="bac297" label="是否验证" redisplay="true" />
				</tr>
				<tr>
					<lemis:texteditor property="aae013" label="备注" disable="false" maxlength="100" colspan="5" />
				</tr>
				<tr>
					<td>经办人</td>
					<td><lemis:operator/></td>
					<td>经办机构</td>
					<td ><lemis:operorg/></td>
					<td>经办日期</td>
					<td><lemis:operdate/></td>
				</tr>
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script language="javascript">
	function page_init(){ //方法签名不能改变
  	 	eapObjsMgr.onReady('OperPersonForm');//codeListForm为快速录入的标签所属的form
	}
	function saveData(obj){
		if(!checkValue(obj)){
			return false;
		}
		obj.action ="/" + lemis.WEB_APP_NAME + "/basicinfo/personOperAction.do?method=savePerson&menuId=<%=menuid%>&url2=<%=url%>;amp;aac003=;amp;aac021=;amp;aac002="+document.all("aac002").value+"&";
		obj.submit();
	}
	function toBack(){
		window.location.href="<%=url.replaceAll(";amp;","&")%>";
	}
</script>

