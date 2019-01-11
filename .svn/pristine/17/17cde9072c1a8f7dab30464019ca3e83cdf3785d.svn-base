<!--/recommendation/consigninvite/ppqk.jsp-->
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.apps.commons.entity.Cb21"%>
<%@ page import="org.radf.apps.commons.entity.Cc20"%>
<%@ page import="org.radf.apps.commons.entity.Cc21"%>
<%@ page import="org.radf.apps.commons.entity.Ac01"%>
<%@ page import="org.radf.apps.commons.entity.Ab01"%>
<%@ page import="org.radf.plat.commons.DateUtil"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.util.Vector"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<html>
<lemis:base />
<lemis:body>
	<%      Cc21 cc21 = new Cc21();
			Cc21 cc21_1 = new Cc21();
			Cc21 cc21_2 = new Cc21();
			Cb21 cb21 = (Cb21) request.getSession().getAttribute("cb21");
			Cc20 cc20 = (Cc20) request.getSession().getAttribute("cc20");
			Ab01 ab01 = (Ab01) request.getSession().getAttribute("ab01");
			Ac01 ac01 = (Ac01) request.getSession().getAttribute("ac01");
			Vector vec = (Vector) request.getSession().getAttribute("cc21vec");
			cc21 = (Cc21) vec.get(0);
			cc21_1 = (Cc21) vec.get(1);
			cc21_2 = (Cc21) vec.get(2);
			//定义按钮
			List buttons = new ArrayList();//定义按钮属性
			buttons.add(new Button("Btn_back", "返 回", "rec999997",
					"go2Page(\"recommendation\",\"2\")"));
			buttons.add(new Button("Btn_close", "关 闭", "rec999999",
					"closeWindow(\"viewP,Rec0103Form\")"));
			pageContext.setAttribute("buttons", buttons);
			String ppstr = "";
%>
	<%//标题部分%>
	<lemis:title title="查看求职信息和岗位信息的匹配情况" />

	<%//查看部分%>
	<lemis:tabletitle title="查看求职信息和岗位信息的匹配情况" />
	<table class="tableInput">
		<COLGROUP>
			<COL width='10%'>
			<COL width='30%' align=left>
			<COL width='30%' align=left>
			<COL width='30%' align=left>
		</COLGROUP>
		<html:form action="/Rec0103Action.do" method="POST">
			<tr>
				<td>类别</td>
				<td>用人单位信息</td>
				<td>求职人员情况</td>
				<td>匹配情况</td>
			</tr>
			<tr>
	      <%
	        String aca111=cc21.getAca111();
	        if (cb21.getAca111().equals(cc21.getAca111())) {
				ppstr = "匹配";
				aca111=cc21.getAca111();
			} else if (cb21.getAca111().equals(cc21_1.getAca111())) {
				ppstr = "匹配";
				aca111=cc21_1.getAca111();
			} else if (cb21.getAca111().equals(cc21_2.getAca111())) {
				ppstr = "匹配";
				aca111=cc21_2.getAca111();
			} else {
				ppstr = "不匹配";
			}

			%>
			<td>工种</td>
			<td><lemis:codelist type="aca111"  isSelect="false" redisplay="true" key="<%=cb21.getAca111()%>" /></td>
			<td><lemis:codelist type="aca111"  isSelect="false" redisplay="true" key="<%=aca111%>" /></td>
			<td><lemis:text property="ppqz"   maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>


			<tr>
	      <%
	        String acb221=cb21.getAcb221()+"到"+cb21.getAcb222();
		    int aac006=DateUtil.getAge(ac01.getAac002());
			if(cb21.getAcb222().intValue()>=aac006 && cb21.getAcb221().intValue()<=aac006)
			{
				ppstr = "匹配";
			}
			else
			{
				ppstr = "不匹配";
			}
			%>
			<td>年龄</td>			
			<td><lemis:text property="acb221"  maxlength="100" disable="true" value="<%=acb221%>" /></td>
			<td><lemis:text property="aac006"  maxlength="100" disable="true" value="<%=aac006+""%>" /></td>
			<td><lemis:text property="ppqz"  maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>
			<tr>
	      <%
	        String aac004cb21=cb21.getAac004();
		    String aac004ac01=ac01.getAac004();
			if(aac004cb21==null ||"".equals(aac004cb21)||"9".equals(aac004cb21))
			{
				aac004cb21="9";
				ppstr = "匹配";
			}
			else if(aac004cb21.equals(aac004ac01))
			{
				ppstr = "匹配";
			}else
			{
				ppstr = "不匹配";
			}
			%>
			<td>性别</td>
			<td><lemis:codelist type="aac004"  isSelect="false" redisplay="true" key="<%=aac004cb21%>" /></td>
			<td><lemis:codelist type="aac004"  isSelect="false" redisplay="true" key="<%=aac004ac01%>" /></td>
			<td><lemis:text property="ppqz"  maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>
          <tr>
	      <%
		  BigDecimal acc034=cc21.getAcc034();
		  BigDecimal acb21h=cb21.getAcb21h();
		  if(acc034==null)
			  acc034=new BigDecimal("0");
		  if(acb21h==null)
			  acb21h=new BigDecimal("0");
			if(acb21h.compareTo(acc034)>=0)
			{
				
				ppstr = "匹配";
			}else
			{
				ppstr = "不匹配";
			}
			%>
			<td>待遇</td>
			<td><lemis:text property="acb21h"  maxlength="100" disable="true" value="<%=acb21h.toString()%>" /></td>
			<td><lemis:text property="acc034"  maxlength="100" disable="true" value="<%=acc034.toString()%>" /></td>
			<td><lemis:text property="ppqz"  maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>
			<%
			
					
			String aac011cb21=cb21.getAac011();
			String aac011ac01=ac01.getAac011();
			if(aac011cb21==null || "".equals(aac011cb21))
					{
				      aac011cb21="10";
					}
			if(aac011ac01==null || "".equals(aac011ac01))
					{
				aac011ac01="10";
					}
			if(aac011cb21==null ||"".equals(aac011cb21)||"10".equals(aac011cb21))
			{
				aac004cb21="10";
				ppstr = "匹配";
			}
			else if(Integer.parseInt(aac011cb21)>=Integer.parseInt(aac011ac01))
			{
				ppstr = "匹配";
			}else
			{
				ppstr = "不匹配";
			}
			%>
			<td>学历</td>
			<td><lemis:codelist type="aac011"  isSelect="false" redisplay="true" key="<%=aac011cb21%>" /></td>
			<td><lemis:codelist type="aac011"  isSelect="false" redisplay="true" key="<%=aac011ac01%>" /></td>
			<td><lemis:text property="ppqz"  maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>
			<tr>
			<%
			
					
			String aac048cb21=cb21.getAac048();
			String aac048cc21=cc21.getAac048();
			if(aac048cb21==null || "".equals(aac048cb21))
					{
				     
					 aac048cb21="900";
					}
			if(aac048cc21==null || "".equals(aac048cc21))
					{
				     aac048cc21="900";
					}
			if(aac048cb21.equals(aac048cc21))
			{
				ppstr = "匹配";
			}else
			{
				ppstr = "不匹配";
			}
			
			%>
			<td>用工形式</td>
			<td><lemis:codelist type="aac048"  isSelect="false" redisplay="true" key="<%=aac048cb21%>" /></td>
			<td><lemis:codelist type="aac048"  isSelect="false" redisplay="true" key="<%=aac048cc21%>" /></td>
			<td><lemis:text property="ppqz"  maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>
			<tr>
			<%
			
			ppstr = "不匹配";
			String acb217=cb21.getAcb217();
			String acc213=cc21.getAcc213();
			if(acb217==null || "".equals(acb217))
					{				     
				      ppstr = "匹配";
					  acb217="无要求";
					}
			if(acc213==null || "".equals(acc213))
					{
				   ppstr = "匹配";
				   acc213="无要求";
					}
			if(acb217.equals(acc213)||"匹配".equals(ppstr))
			{
				ppstr = "匹配";
			}else
			{
				ppstr = "不匹配";
			}
			
			%>
			<td>工作地区</td>
			<td><lemis:text property="acb217"  maxlength="100" disable="true" value="<%=acb217%>" /></td>
			<td><lemis:text property="acc213"  maxlength="100" disable="true" value="<%=acc213%>" /></td>
			<td><lemis:text property="ppqz"  maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>
			
			
			<tr>
			<%
			
			ppstr = "不匹配";
			String aab019ab01=ab01.getAab019();
			String aab019cc21=cc21.getAab019();
			if(aab019ab01==null || "".equals(aab019ab01))
					{
					 ppstr = "匹配";
					}
			if(aab019cc21==null || "".equals(aab019cc21))
					{				     
					 ppstr = "匹配";
					}
			if(!"匹配".equals(ppstr))
			{
				if(aab019ab01.equals(aab019cc21))
				{
				    ppstr = "匹配";
			    }else
			     {
				ppstr = "不匹配";
			   }
			}
			
			%>
			<td>单位性质</td>
			<td><lemis:codelist type="aab019"  isSelect="false" redisplay="true" key="<%=aab019ab01%>" /></td>
			<td><lemis:codelist type="aab019"  isSelect="false" redisplay="true" key="<%=aab019cc21%>" /></td>
			<td><lemis:text property="ppqz"  maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>
			
			<tr>
			<%
			
					

			ppstr = "不匹配";
			String aab020ab01=ab01.getAab020();
			String aab020cc21=cc21.getAab020();
			if(aab020ab01==null || "".equals(aab020ab01))
					{
					 ppstr = "匹配";
					}
			if(aab020cc21==null || "".equals(aab020cc21))
					{				     
					 ppstr = "匹配";
					}
			if(!"匹配".equals(ppstr))
			{
				if(aab020ab01.equals(aab020cc21))
				{
				    ppstr = "匹配";
			    }else
			     {
				ppstr = "不匹配";
			   }
			}
			
			%>
			<td>经济类型</td>
			<td><lemis:codelist type="aab020"  isSelect="false" redisplay="true" key="<%=aab020ab01%>" /></td>
			<td><lemis:codelist type="aab020"  isSelect="false" redisplay="true" key="<%=aab020cc21%>" /></td>
			<td><lemis:text property="ppqz"  maxlength="100" disable="true" value="<%=ppstr%>" /></td>
			</tr>
		</html:form>
	</table>
	<lemis:buttons buttonMeta="buttons" />
	<lemis:bottom />
</lemis:body>
</html>
<script language="javascript">
  //上一步
  function back(url){
    location.href=url;
  }


</script>

