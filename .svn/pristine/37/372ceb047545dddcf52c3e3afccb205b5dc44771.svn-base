
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<!-- recommendation/personapply/ModPersonApply.jsp -->
<html>
<lemis:base/>
<lemis:body>
<% 
String menuId = (String)request.getSession().getAttribute("menuId");
  String today = DateUtil.getSystemCurrentTime().toString();
  String modPA = (String)request.getSession().getAttribute("modPA");
  //定义按钮
  List buttons=new ArrayList();//定义按钮属性
	  buttons.add(new Button("Btn_save","保 存","rec020107","save()"));
  
  buttons.add(new Button("Btn_back","返 回","rec999997","go2Page(\"recommendation\",\"1\")"));
  buttons.add(new Button("Btn_close","关 闭","rec999999","closeWindow(\"modPA,Rec0201Form\")"));
  pageContext.setAttribute("buttons",buttons);
%>
  <%//标题部分%>
  <lemis:title title="修改个人求职信息"/>

  <%//录入部分%>
  <lemis:tabletitle title="个人求职信息"/>
  <table class="tableInput">
  <lemis:editorlayout/>
  <html:form action="/Rec0201Action.do?method=modPersonApply" method="POST">
   
   <tr>
        <html:hidden property="acc200"/>
        <html:hidden property="aac001"/>
        <html:hidden property="today" value="<%=today%>"/>
       <lemis:texteditor property="aac003" label="姓名" maxlength="20"  required="false"  />
       <lemis:codelisteditor type="aac004" label="性别"  isSelect="false" redisplay="true" required="false" />
       <lemis:formateditor mask="card" property="aac002" label="公民身份号码"  required="false"/>
       
     </tr>

    <tr>
       <lemis:formateditor mask="date" property="aac006" label="出生日期"  required="false" format="true" />
       <lemis:codelisteditor type="aac011" label="文化程度"  isSelect="false" redisplay="true" required="false" />
       <lemis:codelisteditor type="aac005" label="民族"  isSelect="false" redisplay="true" required="false" />
    </tr>

    <tr>
     <html:hidden property="bac298"/>
     <lemis:codelisteditor type="aac024" label="政治面貌"  isSelect="false" redisplay="true"/>
      <lemis:codelisteditor type="aac009" label="户口性质"  isSelect="false" redisplay="true"/>
      <lemis:texteditor property="aac025" label="籍贯" maxlength="60"  />

     </tr>
   
    <tr>     
     <lemis:codelisteditor type="bac299" label="求职人员类别"  isSelect="true" redisplay="true" required="true" />    
     <lemis:texteditor property="aae006" label="通讯地址" maxlength="80"  disable="false" colspan="3"/>  
    </tr>
      
      <tr>
       <lemis:texteditor property="aae004" label="联系人" maxlength="20"  disable="false" />
       <lemis:texteditor property="aae005" label="联系电话" maxlength="20"  disable="false" required="false" />
       <lemis:formateditor mask="nnnnnn" property="aae007" label="邮政编码" disable="false" required="false"/>
       
      </tr>
    <tr>
      <lemis:codelisteditor type="aac017" label="婚姻状况"  isSelect="false" redisplay="true"/>
      <lemis:codelisteditor type="aac033" label="健康状况"  isSelect="false" redisplay="true"/> 
      <lemis:formateditor mask="nnn.n" property="aac034" label="身高(CM)" disable="true" required="false"/>
    </tr>

     <tr>          
     <lemis:formateditor mask="nnn.nn" property="aac035" label="体重(KG)" disable="true" required="false"/>
      <lemis:codelisteditor type="aac032" label="血型"  isSelect="false" redisplay="true"/>
      <lemis:formateditor mask="n.n" property="aac036" label="视力" disable="true" required="false"/>
   
    </tr>
    
    <tr>
      
       <lemis:codelisteditor type="aac038" label="第一外语"  isSelect="true" redisplay="true"/>
       <lemis:codelisteditor type="aac039" label="第一外语熟练程度"  isSelect="true" redisplay="true"/>
       <lemis:codelisteditor type="aac041" label="第二外语"  isSelect="true" redisplay="true"/>
    </tr>

     <tr>
       <lemis:codelisteditor type="aac042" label="第二外语熟练程度"  isSelect="true" redisplay="true"/>
        <lemis:texteditor property="acc20e" label="其他外语" maxlength="30"  disable="false" />
        <lemis:codelisteditor type="aac043" label="计算机水平"  isSelect="true" redisplay="true"/>
	</tr>
    <tr>
    	<lemis:codelisteditor type="aac014" label="专业技术职务" isSelect="true" required="false" />
        <lemis:formateditor mask="date" property="aac007" label="参加工作日期" disable="true" required="false" format="true"/>
       <lemis:codelisteditor type="acc201" label="登记类别"  isSelect="true" redisplay="true"/>
     </tr>
     <tr>
    
     <lemis:texteditor property="aac021" label="失业证号码" maxlength="20" disable="false"/>
     <lemis:codelisteditor type="acc207" label="是否境外求职"  isSelect="true" redisplay="true"/>
     <lemis:formateditor mask="date" property="aae043" label="登记日期" disable="false" required="false"  onclick="addDateTime()" onblur="addDateTime()" format="true"/>
     </tr>
      <tr>
      
     <lemis:formateditor mask="date" property="ace014" label="有效日期" disable="false" required="false" format="true"
	   onclick="addDateTime()" onblur="addDateTime()"/>
        <lemis:texteditor property="acc20b" label="信息采集员" maxlength="30"  disable="false" />
 	   <lemis:texteditor property="aae015" label="个人电子信箱" maxlength="30"  disable="false"/>
 	</tr>
    <tr> 
      
      <lemis:texteditor property="aae013" label="备注" maxlength="100"  disable="false" colspan="5"  style="word-break:break-all"/>
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
  </html:form>
  </table>
  <lemis:buttons buttonMeta="buttons"/>

  <%//界面模型规定的其他标配部分%>
  
  <lemis:errors/>
<lemis:bottom/></lemis:body>
</html>
<script language="javascript" >
  //上一步
  function back(url){
  window.history.back();
    //location.href=url;
  }

function addDateTime(){
  var str =  document.all.aae043.value;
  var addt = 90;
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
  document.all.ace014.value = FullDate;
//  return FullDate;
}

   //保存
  function save(){
	//addDateTime();
    var obj = document.forms[0];
    var ok = checkValue(obj);
    if (!ok){
      return ok;
    }
    if(!compareDate(obj.today.value,obj.aac006.value)){
     alert("出生日期不能晚于当前日期，请确认！");
     obj.aac006.focus();
     return false;
     }
     if(!compareDate(obj.today.value,obj.aae043.value)){
     alert("登记日期不能晚于当前日期，请确认！");
     obj.aae043.focus();
     return false;
     }
    obj.action= '<html:rewrite page="/Rec0201Action.do?method=savePersonApply"/>';

    obj.submit();
  }
  
  function next(){

   var ok = document.all("acc200").value;
    if (ok=="")
   {
    alert("资料未保存，请保存后再进行下一步操作！");
    return false;
    }
    obj = document.forms[0];
    //var url = obj.url.value;

    obj.action= '<html:rewrite page="/nextPage2Action.do?method=nextPage2&"/>'+"acc200="+ok;
    
    obj.action=obj.action+"&nextPA="+location.href.replace(/\&/g,";nextPA;");
    //alert(obj.action);
    obj.submit();
  }
 </script>

