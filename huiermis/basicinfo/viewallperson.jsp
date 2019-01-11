<!-- basicinfo/121viewallperson.jsp -->
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.apps.commons.entity.Cc02" %>
<%@ page import="org.radf.apps.commons.entity.Cc03" %>
<%@ page import="org.radf.apps.commons.entity.Jc10" %>
<%@ page import="org.radf.apps.commons.entity.Jc22" %>
<%@ page import="org.radf.apps.commons.entity.Cc9a" %>
<%@ page import="org.radf.apps.commons.entity.Cc9d" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="org.radf.apps.commons.entity.Jc40" %>
<%@ page import="org.radf.apps.commons.entity.Ac02" %>
<%@ page import="org.radf.apps.commons.entity.Jc01" %>
<%@ page import="org.radf.plat.commons.ClassHelper" %>
<%@ page import="org.radf.plat.commons.*"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
//获取上个页面的勾选情况
  //基本信息jbxx
  String jbxx=null;
if(request.getParameter("jbxx")!=""){
	jbxx=request.getParameter("jbxx");
}

  //失业证信息syzxx
  String syzxx=null;
if(request.getParameter("syzxx")!=""){
	syzxx=request.getParameter("syzxx");
}

//就业信息jydjxx
String jydjxx=null;
if(request.getParameter("jydjxx")!=""){
	jydjxx=request.getParameter("jydjxx");
}
  //失业金审核syjsh
  String syjsh=null;
if(request.getParameter("syjsh")!=""){
	syjsh=request.getParameter("syjsh");
}

  //失业金发放syjff
  String syjff=null;
if(request.getParameter("syjff")!=""){
	syjff=request.getParameter("syjff");
}

  //农合工补助nhgbz
  String nhgbz=null;
if(request.getParameter("nhgbz")!=""){
	nhgbz=request.getParameter("nhgbz");
}

  //个人参保grcb
  String grcb=null;
if(request.getParameter("grcb")!=""){
	grcb=request.getParameter("grcb");
}

  //失业保险个人应缴实缴明细sybxgrmx
  String sybxgrmx=null;
if(request.getParameter("sybxgrmx")!=""){
	sybxgrmx=request.getParameter("sybxgrmx");
}
  //灵活就业情况
String lhjyqk=null;
if(request.getParameter("lhjyqk")!=""){
	lhjyqk=request.getParameter("lhjyqk");
}
  //一次性再就业优惠补助
String ycxzjyyhbz=null;
if(request.getParameter("ycxzjyyhbz")!=""){
	ycxzjyyhbz=request.getParameter("ycxzjyyhbz");
}



 %>

<html>

<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0
	id=WB width=0></object> <OBJECT id=factory style="DISPLAY:  none"
		codeBase=http: //<%=request.getServerName()%> :
		<%=request.getServerPort()+request.getContextPath()%>/scriptx/ScriptX.cab#Version=5,60,0,360
		classid=clsid:1663ed61-23eb-11d2-b92f-008048fdd814 viewastext> </OBJECT>
	<body onload='print();'>
<table width="900" border="0" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
		<%if(jbxx!=null&&jbxx.equals("1")) {%>
		<html:form action="/personOperAction.do" onsubmit="return checkValue(this)" method="POST" >
						
						<tr >
			<td align="center">
			<font size="5"><strong>人员档案信息</strong></font>
			</td>
			</tr>
			<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
			<tr height="35">
			<td>
			个人基本信息
			</td>
			</tr>
			<tr height="35">
			<td>
			<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
		<COLGROUP><COL width='16%' align="center"><COL width='17%' align="center">
		<COL width='16%' align="center"><COL width='17%' align="center">
		<COL width='16%' align="center"><COL width='17%' align="center">
		</COLGROUP>	
				
				<tr height="20">
					<html:hidden property="aac001"/>
					<lemis:formateditor mask="card" property="aac002" label=" 公民身份号码" required="false" disable="true"/>
					<lemis:texteditor property="aac003" label="姓名" required="false" disable="true" maxlength="20" />
					<lemis:codelisteditor type="aac004" isSelect="false" label="性别" redisplay="false" required="false" />
				</tr>
				<tr height="35">
					<lemis:codelisteditor type="aac005" isSelect="false" required="false" label="民族"  redisplay="true" />
					<lemis:formateditor mask="date" property="aac006" required="fasle" disable="true" format="true" label="出生日期"/>
					<lemis:codelisteditor type="aac009" label="户口性质" isSelect="false" redisplay="false" required="false"/>
				</tr>
				<tr height="35">
					
					<lemis:codelisteditor type="bac298" label="人员类别" redisplay="false" required="false" isSelect="false"/>
					<lemis:texteditor property="ssjqnm" required="false" label="所属街道" disable="true" style="CURSOR:hand" onclick="setSSJDTree(this,document.all.ssjqnm,document.all.cce001)" />
					<html:hidden property="cce001" />
					<lemis:texteditor  property="aac010" required="false" label="户口所在地区" disable="true" style="CURSOR:hand" onclick="setRegionTree(this,document.all.aac010,document.all.aab301)"/>
					<html:hidden property="aab301"/>
				</tr>
			<tr height="35">
					<lemis:texteditor property="acc025" label='劳动手册号' disable="true" required="false" maxlength="18"/>
					<lemis:codelisteditor type="aac024" isSelect="false" label="政治面貌"  redisplay="true"/>
					<lemis:formateditor property="aac034" label='身高(CM)' disable="true" mask="nnn.n" required="false"/>
				</tr>
				<tr height="35">
					<lemis:formateditor property="aac035" label='体重(KG)' disable="true" mask="nnn.nn" required="false"/>
					<lemis:formateditor property="aac036" label="视力" disable="true" mask="n.n" required="false"/>
					<lemis:codelisteditor type="aac032" isSelect="false" redisplay="true" label="血型"/>
				</tr>
				<tr height="35">
					<lemis:codelisteditor type="aac033" isSelect="false" label="健康状况" redisplay="true"/>
					<lemis:codelisteditor type="aac017" label="婚姻状况" isSelect="false" redisplay="true" required="false"/>
					<lemis:formateditor mask="date" property="aac007" required="false" label="参加工作日期" disable="true" format="true"/>
				</tr>
				<tr height="35">
					<lemis:codelisteditor type="aac011" label="文化程度" redisplay="true" required="false" isSelect="false" />	
					<lemis:formateditor property="aae007" label="邮政编码" required="false" mask="######"  disable="true"/>
					<lemis:texteditor property="aae005" label=" 联系电话" disable="true" maxlength="20"/>
				</tr>
				<tr height="35">
					<lemis:texteditor property="aae015" label="个人电子信箱" disable="true" maxlength="50"/>
					<lemis:codelisteditor type="aac015" label="专业技术等级" redisplay="true" isSelect="false"/>
					<lemis:texteditor property="acc02i"  label="国家职业资格证书" disable="true" maxlength="20"/>
				</tr>
				<tr height="35">		
					<lemis:codelisteditor type="aac014" label="专业技术职务" redisplay="true" isSelect="false"/>
				<lemis:texteditor property="aac025" label="出生地" required="false" disable="true" maxlength="100" colspan="7"/>
				
				</tr>
				<tr height="35">
				<lemis:texteditor property="aae006" label="地址" disable="true" maxlength="50" colspan="7"/>
				
				</tr>

			</table>
			</td>
			</tr>
		</html:form>
		<%} %>

<%List listcc02 = (List)session.getAttribute("listcc02");
if  (listcc02!=null&&listcc02.size()>0&&(syzxx.equals("1"))){
%>			<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
         <tr height="35">
			<td>
			失业证信息
			</td>
		</tr>
		<tr height="35">
			<td>
		<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
				
		  <tr height="35">   
    <td ><div align="center">失业证号</div></td>
    <td ><div align="center">失业日期</td>
    <td ><div align="center">失业类别</div></td>
    <td ><div align="center">原工作单位</div></td>
    <td ><div align="center">失业原因</div></td>
    <td ><div align="center">注销日期</div></td>
    <td ><div align="center">注销原因</div></td>
    
  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listcc02.size();i++){
			Cc02 cc02=new Cc02();
			ClassHelper.copyProperties(listcc02.get(i), cc02);

			String aac021=cc02.getAac021();//失业证号
			String ajc090=DateUtil.converToString(cc02.getAjc090(),"YYYY-MM-DD");//失业日期
			String bcc025=OptionDictSupport.getCodeName(request,"BCC025",cc02.getBcc025());//失业类别
			String aab004=cc02.getAab004();//原工作单位
			String ajc093=OptionDictSupport.getCodeName(request,"AJC093",cc02.getAjc093());//失业原因
			String bae043=DateUtil.converToString(cc02.getBae043(),"YYYY-MM-DD");//注销日期
			String bae045=OptionDictSupport.getCodeName(request,"BAE045",cc02.getBae045());//注销原因
		%>	
				
  <tr height="35">
    <td ><div align="center"><%=aac021==null?"&nbsp;":aac021%></div></td>
    <td width="100"><div align="center"><%=ajc090==null?"&nbsp;":ajc090%></div></td>
    <td width="100"><div align="center"><%=bcc025==null?"&nbsp;":bcc025%></div></td>
  	<td ><div align="center"><%=aab004==null?"&nbsp;":aab004%></div></td>
  	<td ><div align="center"><%=ajc093==null?"&nbsp;":ajc093%></div></td>
  	<td width="100"><div align="center"><%=bae043==null?"&nbsp;":bae043%></div></td>
  	<td ><div align="center"><%=bae045==null?"&nbsp;":bae045%></div></td>
  	
 </tr>
		<%
		} 
		%>

			</table>
			</td>
			</tr>
			<%}%>

<%List listcc03 = (List)session.getAttribute("listcc03");

if  (listcc03!=null&&listcc03.size()>0&&(jydjxx.equals("1"))){
%>			<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
         <tr height="35">
			<td>
			就业登记信息
			</td>
		</tr>
		<tr height="35">
			<td>
		<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
				
		  <tr height="35">   
    <td ><div align="center">单位名称</div></td>
    <td ><div align="center">备案日期</td>
    <td ><div align="center">合同开始日期</div></td>
    <td ><div align="center">证件类型</div></td>
    <td ><div align="center">证件号码</div></td>
    <td ><div align="center">经办人</div></td>
    <td ><div align="center">经办日期</div></td>
    
  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listcc03.size();i++){
			Cc03 cc03=new Cc03();
			ClassHelper.copyProperties(listcc03.get(i), cc03);

			String aab001=cc03.getAab001();//单位名称
			String acc031=DateUtil.converToString(cc03.getAcc031(),"YYYY-MM-DD");//备案日期
			String aae030=DateUtil.converToString(cc03.getAae030(),"YYYY-MM-DD");//合同开始日期
			String acc03g=OptionDictSupport.getCodeName(request,"ACC03G",cc03.getAcc03g());//证件类型
			String acc03f=cc03.getAcc03f();//证件号码
			String aae011=OptionDictSupport.getCodeName(request,"AAE011",cc03.getAae011());//经办人
			String aae036=DateUtil.converToString(cc03.getAae036(),"YYYY-MM-DD");//经办日期
		%>	
				
  <tr height="35">
    <td ><div align="center"><%=aab001==null?"&nbsp;":aab001%></div></td>
    <td width="100"><div align="center"><%=acc031==null?"&nbsp;":acc031%></div></td>
    <td width="100"><div align="center"><%=aae030==null?"&nbsp;":aae030%></div></td>
  	<td ><div align="center"><%=acc03g==null?"&nbsp;":acc03g%></div></td>
  	<td ><div align="center"><%=acc03f==null?"&nbsp;":acc03f%></div></td>
  	<td width="100"><div align="center"><%=aae011==null?"&nbsp;":aae011%></div></td>
  	<td ><div align="center"><%=aae036==null?"&nbsp;":aae036%></div></td>
  	
 </tr>
		<%
		} 
		%>

			</table>
			</td>
			</tr>
			<%}%>
			
<%List listjc10 = (List)session.getAttribute("listjc10");
if  (listjc10!=null&&listjc10.size()>0&&(syjsh.equals("1"))){
%>			<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
					<tr height="35">
			<td>
			失业金审核信息
			</td>
			</tr>
				<tr height="35">
			<td>
					<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
				
		  <tr height="35">   
    <td ><div align="center">可领月数</div></td>
    <td ><div align="center">已领月数</td>
    <td ><div align="center">享受开始</div></td>
    <td ><div align="center">享受结束</div></td>
    <td ><div align="center">本次审核缴费截止</div></td>
    <td ><div align="center">发放方式</div></td>
    <td ><div align="center">失业金标准</div></td>
    <td ><div align="center">医疗金标准</div></td>
    
  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listjc10.size();i++){
			Jc10 jc10=new Jc10();
			ClassHelper.copyProperties(listjc10.get(i), jc10);


			String ajc097=TypeCast.objToString(jc10.getAjc097());//可领月数
			String ajc098=TypeCast.objToString(jc10.getAjc098());//已领月数
			String ajc100=jc10.getAjc100();//享受开始时间
			String ajc105=OptionDictSupport.getCodeName(request,"AJC105",jc10.getAjc105());//发放方式
			String ajc106=jc10.getAjc106();//享受结束时间
			String bjc406=jc10.getBjc406();//缴费截止时间
			String ajc140=TypeCast.objToString(jc10.getAjc140());//失业金标准
			String ajc152=TypeCast.objToString(jc10.getAjc152());//医疗金标准

		%>	
				
  <tr height="35">
    <td ><div align="center"><%=ajc097==null?"&nbsp;":ajc097%></div></td>
    <td ><div align="center"><%=ajc098==null?"&nbsp;":ajc098%></div></td>
    <td ><div align="center"><%=ajc100==null?"&nbsp;":ajc100%></div></td>
  	<td ><div align="center"><%=ajc106==null?"&nbsp;":ajc106%></div></td>
  	<td ><div align="center"><%=bjc406==null?"&nbsp;":bjc406%></div></td>
  	<td ><div align="center"><%=ajc105==null?"&nbsp;":ajc105%></div></td>
  	<td ><div align="center"><%=ajc140==null?"&nbsp;":ajc140%></div></td>
  	<td ><div align="center"><%=ajc152==null?"&nbsp;":ajc152%></div></td>
  	
 </tr>
		<%
		} 
		%>

			</table>
			</td>
			</tr>
			<%}%>
			
	<%List listjc22 = (List)session.getAttribute("listjc22");
if  (listjc22!=null&&listjc22.size()>0&&(syjff.equals("1"))){
%>			
				<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
						<tr height="35">
			<td>
			失业金发放信息
			</td>
			</tr>
			<tr height="35">
			<td>
					<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
		  <tr height="35">   
    <td ><div align="center">发放年月</div></td>
    <td ><div align="center">发放月数</td>
    <td ><div align="center">失业金</div></td>
    <td ><div align="center">医疗金</div></td>
    <td ><div align="center">大病医疗补助</div></td>
    <td ><div align="center">其他金额</div></td>
    <td ><div align="center">发放类别 </div></td>
    <td ><div align="center">签到标记 </div></td>
    
  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listjc22.size();i++){
			Jc22 jc22=new Jc22();

			
			ClassHelper.copyProperties(listjc22.get(i), jc22);
			
			String ajc150=jc22.getAjc150();//      发放年月                       
			String ajc303=TypeCast.objToString(jc22.getAjc303());//  发放月数     
			String ajc140=TypeCast.objToString(jc22.getAjc140());//  失业金       
			String ajc152=TypeCast.objToString(jc22.getAjc152());//	医疗金   
			String ajc190=TypeCast.objToString(jc22.getAjc190());//大病医疗补助      
			String bjc417=TypeCast.objToString(jc22.getBjc417());//	其他金额      
			String bjc418=OptionDictSupport.getCodeName(request,"BJC418",jc22.getBjc418());	//发放类别                              
			String acb35c=OptionDictSupport.getCodeName(request,"ACB35C",jc22.getAcb35c());//	签到标记                              


		%>	
				
  <tr height="35">
    <td ><div align="center"><%=ajc150==null?"&nbsp;":ajc150%></div></td>
    <td ><div align="center"><%=ajc303==null?"&nbsp;":ajc303%></div></td>
    <td ><div align="center"><%=ajc140==null?"&nbsp;":ajc140%></div></td>
  	<td ><div align="center"><%=ajc152==null?"&nbsp;":ajc152%></div></td>
  	<td ><div align="center"><%=ajc190==null?"&nbsp;":ajc190%></div></td>
  	<td ><div align="center"><%=bjc417==null?"&nbsp;":bjc417%></div></td>
  	<td ><div align="center"><%=bjc418==null?"&nbsp;":bjc418%></div></td>
  	<td><div align="center"><%=acb35c==null?"&nbsp;":acb35c%></div></td>
  	
 </tr>
		<%
		} 
		%>

			</table>
			</td>
			</tr>
			<%}%>
			
			<%List listjc40 = (List)session.getAttribute("listjc40");
if  (listjc40!=null&&listjc40.size()>0&&(nhgbz.equals("1"))){
%>			
					<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
					<tr height="35">
			<td>
			农合工补助信息
			</td>
			</tr>
			<tr height="35">
			<td>
					<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
				
		  <tr height="35">   
    <td ><div align="center">连续工作月数</div></td>
    <td ><div align="center">领取生活补助金月）</td>
    <td ><div align="center">合计金额</div></td>
    <td ><div align="center">领取标准</div></td>
    <td ><div align="center">应领取日期</div></td>
    <td ><div align="center">领取标记 </div></td>
    <td ><div align="center">实际领取日期</div></td>
    
  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listjc40.size();i++){
			Jc40 jc40=new Jc40();

			
			ClassHelper.copyProperties(listjc40.get(i), jc40);
			String ajc403=TypeCast.objToString(jc40.getAjc403());//	连续工作月数                       
			String ajc404=TypeCast.objToString(jc40.getAjc404());//	领取生活补助金（月）               
			String ajc405=TypeCast.objToString(jc40.getAjc405());//	合计金额                           
			String ajc409=TypeCast.objToString(jc40.getAjc409());//	领取标准                           
			String ajc410=DateUtil.converToString(jc40.getAjc410(),"YYYY-MM-DD");//	应领取日期         
			String ajc411=OptionDictSupport.getCodeName(request,"AJC411",jc40.getAjc411());//领取标记  
			String ajc412=DateUtil.converToString(jc40.getAjc412(),"YYYY-MM-DD");//	实际领取日期 %>	
				
  <tr height="35">
    <td ><div align="center"><%=ajc403==null?"&nbsp;":ajc403%></div></td>
    <td ><div align="center"><%=ajc404==null?"&nbsp;":ajc404%></div></td>
    <td ><div align="center"><%=ajc405==null?"&nbsp;":ajc405%></div></td>
  	<td ><div align="center"><%=ajc409==null?"&nbsp;":ajc409%></div></td>
  	<td ><div align="center"><%=ajc410==null?"&nbsp;":ajc410%></div></td>
  	<td ><div align="center"><%=ajc411==null?"&nbsp;":ajc411%></div></td>
  	<td ><div align="center"><%=ajc412==null?"&nbsp;":ajc412%></div></td>
  	
 </tr>
		<%
		} 
		%>

			</table>
			</td>
			</tr>
			<%}%>
			
			
		<%List listac02 = (List)session.getAttribute("listac02");
		System.out.print("grcb====="+request.getParameter("grcb"));
if  (listac02!=null&&listac02.size()>0&&grcb.equals("1")){
%>			
					<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
			<tr height="35">
			<td>
			个人参保信息
			</td>
			</tr>
			<tr height="35">
			<td>
					<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
				
		  <tr height="35">   
    <td ><div align="center">参保单位</div></td>
    <td ><div align="center">参保开始</td>
    <td ><div align="center">缴费开始</div></td>
    <td><div align="center">用工形式</div></td>
    <td ><div align="center">参保原因</div></td>
    <td ><div align="center">缴费基数</div></td>
    <td ><div align="center">特殊参保</div></td>
    <td ><div align="center">缴费截止 </div></td>
    <td ><div align="center">停保原因</div></td>
    <td ><div align="center">停保年月</div></td>
  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listac02.size();i++){
		Ac02 ac02=new Ac02();
		ClassHelper.copyProperties(listac02.get(i), ac02);
			
String aab001=ac02.getAab001();// 单位编号          
String aac047=ac02.getAac047();//参保开始年月       
String aae041=ac02.getAae041();//缴费开始年月       
String aac013=OptionDictSupport.getCodeName(request,"AAC013",ac02.getAac013());// 用工形式          
String ajc301=OptionDictSupport.getCodeName(request,"AJC301",ac02.getAjc301());//  参保原因         
String acc7g1=TypeCast.objToString(ac02.getAcc7g1());//缴费基数           
String ajc306=OptionDictSupport.getCodeName(request,"AJC306",ac02.getAjc306());// 是否特殊参保人员  
String aae042=ac02.getAae042();//缴费截止年月       
String bjc104=OptionDictSupport.getCodeName(request,"BCJ104",ac02.getBjc104());//  停保原因         
String bjc102=ac02.getBjc102();//停保计算年月       


	%>			
  <tr height="35">
    <td ><div align="center"><%=aab001==null?"&nbsp;":aab001%></div></td>
    <td ><div align="center"><%=aac047==null?"&nbsp;":aac047%></div></td>
    <td ><div align="center"><%=aae041==null?"&nbsp;":aae041%></div></td>
  	<td ><div align="center"><%=aac013==null?"&nbsp;":aac013%></div></td>
  	<td ><div align="center"><%=ajc301==null?"&nbsp;":ajc301%></div></td>
  	<td ><div align="center"><%=acc7g1==null?"&nbsp;":acc7g1%></div></td>
  	<td><div align="center"><%=ajc306==null?"&nbsp;":ajc306%></div></td>
  	<td ><div align="center"><%=aae042==null?"&nbsp;":aae042%></div></td>
  	<td ><div align="center"><%=bjc104==null?"&nbsp;":bjc104%></div></td>
  	<td ><div align="center"><%=bjc102==null?"&nbsp;":bjc102%></div></td>
 </tr>
		<%
		} 
		%>

			</table>
			</td>
			</tr>
			<%}%>
			
			<%List listjc01 = (List)session.getAttribute("listjc01");
if  (listjc01!=null&&listjc01.size()>0&&(sybxgrmx.equals("1"))){
%>			
					<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
			<tr height="35">
			<td>
			失业保险个人应缴实缴明细
			</td>
			</tr>
			<tr height="35">
			<td>
					<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
				
		  <tr height="35">   
    <td ><div align="center">参保单位</div></td>
    <td ><div align="center">缴费年月</td>
    <td ><div align="center">费款所属期</div></td>
    <td ><div align="center">个人缴费基数</div></td>
    <td ><div align="center">单位缴纳金额</div></td>
    <td ><div align="center">个人缴纳金额</div></td>


  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listjc01.size();i++){
		Jc01 jc01=new Jc01();
		ClassHelper.copyProperties(listjc01.get(i), jc01);
		
		String aab001=jc01.getAab001();// 单位编号                                   
                           
String aae002=jc01.getAae002();// 费款所属期                              
String aae003=jc01.getAae003();//对应费款所属期                          
                     
String ajc020=TypeCast.objToString(jc01.getAjc020());//失业保险个人缴费基数		
String ajc031=TypeCast.objToString(jc01.getAjc031());//单位缴纳失业保险费金额		
String ajc030=TypeCast.objToString(jc01.getAjc030());//个人缴纳失业保险费金额		
  



	%>			
  <tr height="35">
    <td ><div align="center"><%=aab001==null?"&nbsp;":aab001%></div></td>
    <td ><div align="center"><%=aae002==null?"&nbsp;":aae002%></div></td>
  	<td ><div align="center"><%=aae003==null?"&nbsp;":aae003%></div></td>
  	<td ><div align="center"><%=ajc020==null?"&nbsp;":ajc020%></div></td>
  	<td ><div align="center"><%=ajc031==null?"&nbsp;":ajc031%></div></td>
  	<td><div align="center"><%=ajc030==null?"&nbsp;":ajc030%></div></td>

  	
 </tr>
		<%
		} 
		%>

			</table></td>
			</tr>
			<%}%>	
			
			
			<%List listcc9a = (List)session.getAttribute("listcc9a");
if  (listcc9a!=null&&listcc9a.size()>0&&lhjyqk.equals("1")){
%>			<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
			<tr height="35">
			<td>
			灵活就业补贴信息
			</td>
			</tr>
			<tr height="35">
			<td>
					<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
		
				
		  <tr height="35">   
    <td><div align="center">申请年份</div></td>
    <td><div align="center">申请批次</div></td>
    <td><div align="center">申请编号</div></td>
    <td><div align="center">医疗补贴月数</div></td>
    <td><div align="center">医疗补贴金额</div></td>
    <td><div align="center">养老补贴月数</div></td>
    <td><div align="center">养老补贴金额</div></td>
    <td><div align="center">总补贴金额</div></td>
    <td><div align="center">证件编号</div></td>
    <td><div align="center">联系地址</div></td>
    <td><div align="center">联系电话</div></td>
    <td><div align="center">备注</div></td>
  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listcc9a.size();i++){
			Cc9a cc9a=new Cc9a();
			ClassHelper.copyProperties(listcc9a.get(i), cc9a);

			String bccl02=DateUtil.converToString(cc9a.getBccl02(),"YYYY-MM-DD");//申请年份
			String bccy78=cc9a.getBccy78();//申请批次
			String bccy05=cc9a.getBccy05();//申请编号
			BigDecimal bccy56=cc9a.getBccy56();//医疗补贴月数
			BigDecimal bccy57=cc9a.getBccy57();//医疗补贴金额
			BigDecimal bccy53=cc9a.getBccy53();//养老补贴月数
			BigDecimal bccy54=cc9a.getBccy54();//养老补贴金额
			BigDecimal bccl04=cc9a.getBccl04();
			String bccf54=cc9a.getBccf54();//证件编号
			String bcc9af=cc9a.getBcc9af();//经营地址
			String aae005=cc9a.getAae005();//联系电话
			String aae013=cc9a.getAae013();//备注
			
		%>	
				
  <tr height="35">
    <td><div align="center"><%=bccl02==null?"&nbsp;":bccl02%></div></td>
    <td><div align="center"><%=bccy78==null?"&nbsp;":bccy78%></div></td>
    <td><div align="center"><%=bccy05==null?"&nbsp;":bccy05%></div></td>
  	<td><div align="center"><%=bccy56==null?"&nbsp;":bccy56.toString()%></div></td>
  	<td><div align="center"><%=bccy57==null?"&nbsp;":bccy57.toString()%></div></td>
  	<td><div align="center"><%=bccy53==null?"&nbsp;":bccy53.toString()%></div></td>
  	<td><div align="center"><%=bccy54==null?"&nbsp;":bccy54.toString()%></div></td>
  	<td><div align="center"><%=bccl04==null?"&nbsp;":bccl04.toString()%></div></td>
  	<td><div align="center"><%=bccf54==null?"&nbsp;":bccf54.toString()%></div></td>
  	<td><div align="center"><%=bcc9af==null?"&nbsp;":bcc9af.toString()%></div></td>
  	<td><div align="center"><%=aae005==null?"&nbsp;":aae005.toString()%></div></td>
  	<td><div align="center"><%=aae013==null?"&nbsp;":aae013.toString()%></div></td>
  	
 </tr>
		<%
		} 
		%>

			</table>
			</div>
			</td>
			</tr>
			<%}%>
			
			<%List listcc9d = (List)session.getAttribute("listcc9d");
if  (listcc9d!=null&&listcc9d.size()>0&&ycxzjyyhbz.equals("1")){
%>			<tr height="35">
			<td>
			&nbsp;
			</td>
			</tr>
			<tr height="35">
			<td>
			一次性再就业优惠补助
			</td>
			</tr>
			<tr height="35">
			<td>
					<table width="100%" border="1" align="center" cellpadding="0"
		cellspacing="0" style='border-collapse: collapse'
		bordercolor='#000000'>
				
		  <tr height="35">   
    <td><div align="center">申请年份</div></td>
    <td><div align="center">申请批次</div></td>
    <td><div align="center">补贴金额</div></td>
    <td><div align="center">符合条件</div></td>
    <td ><div align="center">优惠证号</div></td>
    <td ><div align="center">营业执照号</div></td>
    <td ><div align="center">开业时间</div></td>
    <td ><div align="center">经营范围</div></td>
    <td ><div align="center">经营地址</div></td>
    <td ><div align="center">联系电话</div></td>
    <td ><div align="center">备注</div></td>
    
  </tr>
		<!--信息列表（只读）-->
		<% 
		for(int i=0;i<listcc9d.size();i++){
			Cc9d cc9d=new Cc9d();
			ClassHelper.copyProperties(listcc9d.get(i), cc9d);

			String bccl02=DateUtil.converToString(cc9d.getBccl02(),"YYYY-MM-DD");//申请年份
			String bccy78=cc9d.getBccy78();//申请批次
			java.math.BigDecimal bccl04=cc9d.getBccl04();//补贴金额
			String bccy76=cc9d.getBccy76();
			String acc101=cc9d.getAcc101();//再就业优惠证号
			String acc03g=cc9d.getAcc03g();//营业执照号码
			String bccy79=cc9d.getBccy79();//开店时间
			String bcc9aj=cc9d.getBcc9aj();//经营范围
			String bcc9af=cc9d.getBcc9af();//经营地址
			String aae005=cc9d.getAae005();//联系电话
			String aae013=cc9d.getAae013();//备注			
		%>	
				
  <tr height="35">
    <td ><div align="center"><%=bccl02==null?"&nbsp;":bccl02%></div></td>
    <td><div align="center"><%=bccy78==null?"&nbsp;":bccy78%></div></td>
    <td><div align="center"><%=bccl04==null?"&nbsp;":bccl04.toString()%></div></td>
  	<td><div align="center"><%=bccy76==null?"&nbsp;":bccy76%></div></td>
  	<td><div align="center"><%=acc101==null?"&nbsp;":acc101%></div></td>
  	<td><div align="center"><%=acc03g==null?"&nbsp;":acc03g%></div></td>
  	<td><div align="center"><%=bcc9aj==null?"&nbsp;":bcc9aj%></div></td>
  	<td><div align="center"><%=bcc9af==null?"&nbsp;":bcc9af%></div></td>
  	<td><div align="center"><%=aae005==null?"&nbsp;":aae005%></div></td>
  	<td><div align="center"><%=aae013==null?"&nbsp;":aae013%></div></td>
  	
 </tr>
		<%
		} 
		%>

			</table>
			</div>
			</td>
			</tr>
			<%}%>
			
			
			
			
			
			
			
			
			
			
</table>
	</body>
</html>
<script>
	function print()
	{ 
	SetPrintSettings();
	try{WB.ExecWB(6,6);}catch(e){}
	}
 
   function SetPrintSettings() {  
          
          factory.printing.portrait = false     //方向，true
          factory.printing.leftMargin = 2.0  
          factory.printing.topMargin = 2.0  
          factory.printing.rightMargin = 2.0  
          factory.printing.bottomMargin = 1.0 
          factory.printing.header = ""
          factory.printing.footer = ""
 
       
   }  
</script>
