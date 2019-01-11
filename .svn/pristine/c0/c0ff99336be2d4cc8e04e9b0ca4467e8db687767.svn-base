<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="org.radf.apps.recommendation.consigninvite.dto.Rec0103DTO" %>
<%@ page import="java.util.TreeMap" %>
<%@ page import="java.util.Vector" %>
<%@ page import="org.radf.plat.commons.DateUtil" %>
<%@ page import="org.radf.apps.commons.ParaUtil" %>
<%@ page import="org.radf.manage.param.entity.Aa01" %>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
<title></title>
<style type="text/css" media="print">
<!--
.style5 {font-size: 16px}
.style8 {
	font-weight: bold;
	font-size: 16px;
}
.style11 {font-size: 16px; font-style: italic; font-weight: bold; }
.style25 {font-size: 18px; font-style: italic; font-weight: bold; }
.style26 {font-size: 18px}
.style28 {font-size: 22px}

.td {
	font-family: "宋体";
	font-size: 18px;
	color: #000000;
	border-top: 1px solid #DEE4F1;
	border-right: 1px solid #666666;
	border-bottom: 1px solid #666666;
	border-left: 1px solid #DEE4F1;
} 
.td1 {
	font-style: italic;
	font-weight: bold;
	font-size: 18px;
	color: #000000;
	border-top: 1px solid #DEE4F1;
	border-right: 1px solid #666666;
	border-bottom: 1px solid #666666;
	border-left: 1px solid #DEE4F1;
} 
.tablestyle {
	border-top: 1px solid #666666;
	border-right: 1px solid #ACACAC;
	border-bottom: 1px solid #ACACAC;
	border-left: 1px solid #666666;

      }
.tablestyle2 {
	border-top: 1px solid #ABB9E9;
	border-right: 1px solid #C8C8C8;
	border-bottom: 1.5px solid #C8C8C8;
	border-left: 1px solid #ABB9E9;
      } 
.style33 {font-size: 14}
.style35 {font-size: 22px; font-style: italic; font-weight: bold; }
.style37 {font-size: 26px; font-weight: bold; }
.style38 {
	font-size: 24px;
	font-weight: bold;
	font-style: italic;
}
.style39 {font-style: italic;  font-size: 22px; color: #000000; border-top: 1px solid #DEE4F1; border-right: 1px solid #666666; border-bottom: 1px solid #666666; border-left: 1px solid #DEE4F1; }
.style44 {font-size: 14px}

-->
</style>
<body onload=closewin()>
    <%
	ParaUtil para=new ParaUtil();
      String  acc221=null;     // 介绍信编号
	  String   aae043=null;//登记日期
	  //String  aac002=null;     //个人身份证
	  String  aae006=null;     //单位地址
      String  aab004=null;     // 单位名称
	  String  aab001=null;     //单位编号
      String  aac003=null;     // 姓名
      String  aac004=null;     // 性别
      String  aac002=null;     // 公民身份号码
      String  aca111=null;     // 专业工种
      String  acb216=null;     // 工种说明
      String  aae004=null;     // 联系人
      String  aae005=null;     // 单位联系电话
      String aae017=null;//经办结构
      String  aae011=null;     // 经办人
      String  aae036=null;     // 经办时间
      String  year = null;     // 经办时间的年
      String  month = null;    // 经办时间的月
      String  date = null;     // 经办时间的日
      Rec0103DTO dto = (Rec0103DTO)request.getSession().getAttribute("output");
      if (dto!=null){
        acc221 = dto.getAcc221();
        aab004 = dto.getAab004();
		aae006 = dto.getAae006();
		//aac002 = dto.getAac002();
        aab001 = dto.getAab001();
        aac003 = dto.getAac003();
        aac004 = dto.getAac004();
        aac002 = dto.getAac002();
		if(dto.getAae043()==null){
		aae043="";
		}
		else aae043=DateUtil.converToString(dto.getAae043(),"yyyy-MM-dd");
        aca111 = dto.getAca111();
        acb216 = dto.getAcb216();
        aae004 = dto.getAae004();
        aae005 = dto.getAae005();
        aae011 = dto.getAae011();
        aae017 =dto.getAae017();
        if(dto.getAae036()==null){
		aae036="";
		}
		else aae036 = DateUtil.converToString(dto.getAae036(),"yyyy-MM-dd");
        year = aae036.substring(0,4);
        month = aae036.substring(5,7);
        date = aae036.substring(8,10);
      }
    %>
    <table width="648" height="841" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td  colspan="2" align="center" ><span class="style37"><%=para.getParaV("tjxdqmz","tjxdqmz","rec") %>人力资源市场&nbsp; &nbsp; &nbsp;</span></td>
      </tr>
      <tr>
        <td height="54" ><span class="style11">&nbsp;</span> <span class="style23"> <span class="style38"><span class="">
          <%if (aab004== null){out.print("                              ");}else{out.print(aab004);}%>
        </span> <strong><em>： </em></strong></span></span></td>
      </tr>
      <tr>
        <td  colspan="2" >&nbsp;</td>
      </tr>
      <%
       TreeMap tt = (TreeMap) pageContext.findAttribute("ACA111");
       String gz = "";
       if(tt == null) {
          gz = "";
       }
       else if (null != tt.get(aca111)){
          gz = (String) tt.get(aca111);
       }else {
           gz = "";
       }
      %>
      <tr align="left" valign="top">
        <td height="50" align="left" ><blockquote>
            <p> &nbsp;&nbsp; &nbsp;&nbsp;<span class="style5">&nbsp;<span class="style28">兹介绍</span></span><span class="style25">&nbsp;<span class="style35"><strong><em><strong><em>
            <%if (aac003== null){out.print("             ");}else{out.print(aac003);}%>
            </em></strong> </em></strong></span></span><span class="style28">前来应聘你单位 <strong class="style35"><em>
          <% if (aca111==null){out.print("     ");}else{out.print(gz);}%>
              <%if (acb216!= null) out.print("（"+acb216+"）");%>
            </em></strong>职位</span><span class="style26">，</span><span class="style28">望予接。请将洽谈结果在下面“洽谈结果”的相应类别上打“√”并盖章后，由应聘者及时带回本中心（职业介绍所）。</span></p>
        </blockquote></td>
      </tr>
      <tr align="left" valign="top">
        <td height="127" ><p class="style5"><span class="style28">招工单位地址：</span><em class="style35">
            <%if (aae006== null){out.print("        ");}else{out.print(aae006);}%>
          </em> </p>
            <p align="left" class="style28">联系人：&nbsp;<strong class="style35"><em>
              <%if (aae004== null){out.print("        ");}else{out.print(aae004);}%>
&nbsp;</em></strong></p>
            <p align="left" class="style5"><span class="style28">联系电话：<strong></strong></span><strong class="style35">
              <%if (aae005== null){out.print("       ");}else{out.print(aae005);}%>
            </strong></p>
            <p class="style5"><em><strong> </strong></em></p></td>
      </tr>
      <!-- 
      <tr align="left" valign="top">
        <td ><p class="style28">备注：招工单位不得收取押金和报名费等</p></td>
      </tr>
      -->
      <tr align="left" valign="top">
        <td  width="76%" height="32"><p align="right" class="style5"> <span class="style26"><span class="style28"> 湖州市人力资源市场</span></span>&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</p></td>
      </tr>

      <tr align="left" valign="top">
        <td height="29" ><div align="right">
            <p><span class="style28">经办日期：</span> <span class="style35">
              <%if (year== null){out.print("      ");}else{out.print(year);}%>
              </span><span class="style5">&nbsp;</span><span class="style28">年</span> <span class="style35"><em><strong>
              <%if (month== null){out.print("   ");}else{out.print(month);}%>
              </strong></em></span> <span class="style28">月</span> 
              <span class="style35"><strong>
              <%if (date== null){out.print("   ");}else{out.print(date);}%>
              </strong></span><span class="style28">日</span>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
            <p>
            <hr color="#000000"  size="1">
        </div></td>
      </tr>
      <tr align="left" valign="top">
        <td height="38"  colspan="2" ><div align="center">
            <p class="style37">回&nbsp;&nbsp;&nbsp;&nbsp; 执&nbsp; &nbsp; &nbsp;</p>
        </div></td>
      </tr>
      <tr align="left" valign="top">
        <td height="205" ><table width="625" height="205" border="0"  bordercolor="#666666" class="tablestyle" >
            <tr align="left" valign="top"  >
              <td width="125" height="25" class="td">受理登记单位</td>
              <td colspan="2" class="td1"><%if (aae017== null){out.print("                ");}else{out.print(aae017);}%></td>
              <td width="105" class="td">登记日期</td>
             
              <td width="154" align="left" class="style39"><%if (aae043== null){out.print("                ");}else{out.print(aae043);}%></td>
            </tr>
            <tr align="left" valign="top" class="td">
              <td height="22" class="td">推荐人</td>
              <td colspan="2" align="left" class="style39"><%if (aae011== null){out.print("                ");}else{out.print(aae011);}%>
</td>
              <td class="td">推荐日期</td>
              <td align="left" class="style39"><%if (aae036== null){out.print("                ");}else{out.print(aae036);}%>
</td>
            </tr>
            <tr align="left" valign="top" class="td">
              <td height="23" class="td">应聘者姓名</td>
              <td colspan="2" align="left" class="style39"><%if (aac003== null){out.print("             ");}else{out.print(aac003);}%>
</td>
              <td class="td">身份证号</td>
              <td align="left" class="style39"><%if (aac002== null){out.print("                ");}else{out.print(aac002);}%>
</td>
            </tr>
            <tr align="left" valign="top" class="td">
              <td height="23" align="left" valign="top" class="td">应聘单位</td>
              <td colspan="4" align="left" valign="top" class="style39"><%if (aab004== null){out.print("                              ");}else{out.print(aab004);}%>
</td>
            </tr>
            <tr align="left" valign="top" class="td">
              <td height="20" class="td">应聘单位代码</td>
              <td width="116" align="left" class="style39"><%if (aab001== null){out.print("                ");}else{out.print(aab001);}%>
</td>
              <td width="101" align="left" class="td">应聘工种</td>
              <td colspan="2" class="style39"><% if (aca111==null){out.print("     ");}else{out.print(gz);}%>
              <%if (acb216!= null) out.print("（"+acb216+"）");%></td>
            </tr>
            <tr align="left" valign="top" class="td">
              <td rowspan="2" align="left" valign="top" class="td">洽谈结果</td>
              <td height="23" colspan="4" align="left" valign="top"><p class="td">1）录用（用工形式：a.合同制 b.劳务制 c.兼职 d.其他）</p></td>
            </tr>
            <tr>
              <td height="22" colspan="4" align="left" valign="top" class="td">2）不录用； 3）本人不愿意； 4）该工种已招满； 5）其他 </td>
            </tr>
            <tr align="left" valign="top" class="td">
              <td height="23" class="td">接待人</td>
              <td colspan="2" align="left" class="td1">&nbsp;</td>
              <td class="td">接待日期</td>
              <td align="left" class="td1">&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr align="left" valign="top">
        <td  width="76%" height="32"><p class="style8 style33"> * 注：<span class="style5">
        <%
        
        Vector vec=(Vector)para.getParaV("001","rec"); 
        for (int i=0;i<vec.size();i++)
		{   Aa01 aa01=new Aa01();
            aa01=(Aa01)vec.get(i);
            if(i==0)
			{
				 out.println((aa01.getAae013()==null?" ":aa01.getAae013())+"<br>");
			}
            else
			{
				 out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+(aa01.getAae013()==null?" ":aa01.getAae013())+"<br>");
			}
           
             
		}
        %>
      
        </span></p></td>
       
      </tr>
      <tr align="left" valign="top">
        <td height="45" ><div align="right">
            <p> <span class="style44">用工单位盖章</span>&nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;</p>
            <p> <span class="style44">年 &nbsp;&nbsp;月&nbsp;&nbsp; 日&nbsp; &nbsp;</span>&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; </p>
        </div></td>
      </tr>
    </table>
    <p>&nbsp;</p>
</body>
</html>
<script language="JavaScript"> 
var hkey_root,hkey_path,hkey_key 
hkey_root="HKEY_CURRENT_USER" 
hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\" 
//设置网页打印的页眉页脚为空 
function pagesetup_null(){ 
try{ 
var RegWsh = new ActiveXObject("WScript.Shell") 
hkey_key="header"  
RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"") 
hkey_key="footer" 
RegWsh.RegWrite(hkey_root+hkey_path+hkey_key,"") 
}catch(e){} 
}

function closewin() {
	//pagesetup_null();
	 window.print();
     //setTimeout("self.close()",8000) 
	window.close();
}
</script>
