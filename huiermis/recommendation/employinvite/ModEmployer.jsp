<!--/recommendation/employinvite/ModEmployer.jsp-->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ taglib uri=/WEB-INF/plat.tld prefix="lemis" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<lemis:base/>
<script src="/lemis/js/lemisTree.js"></script>
<lemis:body>
<% 
  String modE = (String)request.getSession().getAttribute("modE");
  //定义按钮
  Map buttons=new LinkedHashMap();//定义按钮属性
  buttons.put("保存","save()");
  buttons.put("返回","back()");
  buttons.put("关闭","closeWindow(\"modE,modEmployerForm\")");
  pageContext.setAttribute("buttons",buttons);
%>
  <%//标题部分%>
  <lemis:title title="修改单位信息"/>

  <%//录入部分%>
  <lemis:tabletitle title="单位信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  <html:form action="/modEmployerAction.do" method="POST">
  <tr>
  <lemis:texteditor  property="aab003" label="组织机构代码" maxlength="15" disable="false" required="true" />
      <lemis:texteditor property="aab004" label="单位名称" maxlength="100" disable="false" required="true" />
     <lemis:codelisteditor type="aab019" label="单位类型"  redisplay="true" required="true"/>
     
      </tr>
	  <tr>
      <lemis:texteditor property="aae004" label="联系人" maxlength="20"  disable="false"  required="true"/>
      <lemis:texteditor property="aae005" label="联系电话" maxlength="20"  disable="false"  required="true"/>
	  <lemis:texteditor property="aae006" label="地址" maxlength="80" disable="false"  colspan="1"  required="true"/>
	 
    </tr>
	    <tr>
      <lemis:codelisteditor type="aab020" label="经济类型"  isSelect="true" redisplay="true"   required="true" />
	        <lemis:codelisteditor type="aae119" label="单位状态"  isSelect="true"  redisplay="true" required="true" />

<!--       <lemis:texteditor property="aab022" label="行业代码" maxlength="6"  disable="false"   required="true"/> -->
		<lemis:codelisteditor type="aab022" label="行业类型" isSelect="true" redisplay="true" required="true"/>
     </tr>
     <tr>
     
      <lemis:texteditor property="aab038" label="单位IC卡号" maxlength="20" disable="false"/>
      <lemis:texteditor  property="aab002" label="社会保险登记证编码" maxlength="20" disable="false"  />
       <lemis:texteditor property="aab041" label="单位英文名称" maxlength="50" disable="false"/>
      </tr>

    <tr>
      <lemis:texteditor property="aab039" label="用工登记证号" maxlength="20" disable="false"  />
      <lemis:texteditor property="aab040" label="工资基金使用手册编号" maxlength="50" disable="false"/>
       <lemis:texteditor property="aab042" label="单位简称" maxlength="20" disable="false"  />
      </tr>

    <tr>
      <lemis:texteditor property="aaa130" label="国别代码" maxlength="4" disable="false"  />
     <lemis:texteditor readonly="true" disable="false" label="单位所在区" required="true" property="aaa021" style="cursor: hand" styleClass="text"
				onclick="setRegionTree(this,document.all.aaa021,document.all.aab301)"/>
				<html:hidden property="aab301"/>
            <lemis:texteditor property="aab014" label="法定代表人公民身份号码" maxlength="18" disable="false"/>
      </tr>
    <tr>
	      <lemis:codelisteditor type="aab021" label="隶属关系"  isSelect="true" redisplay="true"/>

      <lemis:texteditor property="aab013" label="法定代表人" maxlength="20"  disable="false" required="false"/>
	  <lemis:codelisteditor type="aab057" label="事业单位类型"  isSelect="true" redisplay="true"/>

    </tr>
    
    
     <tr>
      <lemis:texteditor property="aae015" label="个人电子信箱" maxlength="30"  disable="false"/>
      <lemis:texteditor property="aae016" label="网址" maxlength="30"  disable="false"/>
      <lemis:texteditor property="aab301" label="所在地行政区划代码" maxlength="12" disable="false"  />
    </tr>
    <tr>
		<lemis:codelisteditor type="aab056" label="人员规模"  isSelect="true" redisplay="true" />
		 <lemis:codelisteditor type="aab023" label="主管部门"  isSelect="true" redisplay="true"/>
        <lemis:texteditor property="aae007" label="邮政编码" maxlength="6"  disable="false" />
       </tr>
      <tr>
<!--       <lemis:texteditor property="aaa021" label="行政区划名称" maxlength="60"  disable="false"/>
 -->    <!--   <lemis:texteditor property="aab023" label="主管部门" maxlength="50"  disable="false"/> -->
	      <lemis:texteditor property="aae014" label="传真" maxlength="15" disable="false" />

      <lemis:texteditor property="aab045" label="主办部门" maxlength="50"  disable="false" colspan="3"/>
    </tr>



    <tr>
      <lemis:codelisteditor type="aab048" label="经营方式"  isSelect="true" redisplay="true"/>
      <lemis:codelisteditor type="aab054" label="产业类别"  isSelect="true" redisplay="true"/>
      <!-- <lemis:texteditor property="aab049" label="注册资金" maxlength="50"  disable="false"  required="true"/> -->
	  <td>
					注册资金
				</td>
				<td colspan="1" >
					<table class="TableInput">
						<td>
							<lemis:text property="aab049" label="注册资金"  disable="false"/>
						</td>
						<td width="30%" >万元</td>
					</table>
				</td>
    </tr>

    <tr>
      <lemis:texteditor property="aab052" label="主营范围" maxlength="400" disable="false" />
      <lemis:texteditor property="aab053" label="兼营范围" maxlength="400"  disable="false"/>
      <lemis:codelisteditor type="aab006" label="工商登记执照种类"  isSelect="true" redisplay="true"/>
    </tr>

     <tr>
      <lemis:texteditor property="aab052" label="工商登记执照号码" maxlength="50" disable="false"  />
      <lemis:formateditor mask="date" property="aab008" label="工商登记发照日期" disable="false" required="false"/>
      <lemis:formateditor mask="nnn" property="aab009" label="工商登记有效期限(年)" disable="false" required="false"/>
    </tr>

     <tr>
     <lemis:texteditor property="aab010" label="批准成立单位" disable="false" />
      <lemis:formateditor mask="date" property="aab011" label="批准日期" disable="false"  required="false"/>
      <lemis:texteditor property="aae008" label="银行行号" maxlength="40"  disable="false"/>
    </tr>

    <tr>
      <lemis:texteditor property="aae009" label="银行户名" maxlength="50" disable="false"  />
      <lemis:texteditor property="aae010" label="银行帐号" maxlength="40" disable="false"/>
      <lemis:texteditor property="aab027" label="开户银行" maxlength="40" disable="false"/>
    </tr>
    <tr>
      <lemis:texteditor property="aab029" label="支付银行基本帐号" maxlength="40" disable="false" />
      <lemis:texteditor property="aab030" label="税号" maxlength="40"  disable="false"/>
      <lemis:texteditor property="aab031" label="税务机构编号" maxlength="14"  disable="false"/>
    </tr>

    <tr>
      <lemis:texteditor property="aab032" label="税务机构名称" maxlength="50" disable="false"  />
      <lemis:formateditor mask="date" property="aab037" label="税务证批准日期" disable="false"  required="false"/>
      <lemis:texteditor property="aab046" label="地税税号" maxlength="40"  disable="false"/>
    </tr>

    <tr>
      <lemis:texteditor property="aab047" label="地税税务机构名称" maxlength="50" disable="false"  />
      <lemis:texteditor property="aab046" label="批准单位" maxlength="50" disable="false"/>
    
      <lemis:texteditor property="aab012" label="批准文号" maxlength="20" disable="false"  />
      </tr>
      <tr>
      <lemis:texteditor property="aab015" label="法定代表人电话" maxlength="20" disable="false"  />
      <lemis:texteditor property="aab016" label="缴费单位专管员姓名" maxlength="20"  disable="false"/>
      <lemis:texteditor property="aab017" label="缴费单位专管员所在部门" maxlength="50" disable="false"/>
      </tr>

       <tr>
      <lemis:texteditor property="aab018" label="缴费单位专管员电话" maxlength="20" disable="false"  />
      <lemis:texteditor property="aab024" label="缴费开户银行代码" maxlength="40"  disable="false"/>
      <lemis:texteditor property="aab025" label="缴费开户户名" maxlength="50"  disable="false"/>
      </tr>

       <tr>
      <lemis:texteditor property="aab026" label="缴费银行基本帐号" maxlength="40" disable="false" />
      <lemis:texteditor property="aab028" label="支付银行户名" maxlength="50"  disable="false"/>
      <lemis:codelisteditor type="aab033" label="缴费方式" isSelect="true" redisplay="true"/>
      </tr>

       <tr>
      <lemis:texteditor property="aab034" label="社会保险经办机构编码" maxlength="8" disable="false" />
      <lemis:codelisteditor type="aab341" label="单位年检情况" isSelect="true" redisplay="true"/>
      <lemis:codelisteditor type="aab342" label="单位级别" isSelect="true" redisplay="true"/>
      </tr>
      <tr>
      <lemis:texteditor property="aab343" label="一级单位编号" maxlength="8" disable="false" />
      <lemis:codelisteditor type="aab500" label="缴费周期"  isSelect="true" redisplay="true"/> 
      </tr>
<tr>
      <lemis:texteditor property="aae013" label="备注" maxlength="100" disable="false" colspan="5" />
      </tr>
<tr>
				<lemis:texteditor property="aae011" label="经办人" disable="false"/>
				<lemis:texteditor property="aae017" label="经办机构" disable="false"/>
				<lemis:texteditor property="aae036" label="经办日期" disable="false"/>
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
  function back(){
	  window.history.back(-1);
    }
  //保存
  function save(){
    var obj = document.forms[0];
    var ok = checkValue(obj);
    if (!ok){
      return ok;
    }
    obj.action= '<html:rewrite page="/modEmployerAction.do?method=modEmployer"/>';
    obj.submit();
  }

</script>

