<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("提 交","saveData(document.forms[0])");
buttons.put("重 置","document.forms[0].reset();");//这个模块下公用的按钮
buttons.put("返 回","history.back()");
pageContext.setAttribute("button", buttons);

%>

<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
		function saveData(obj){
			var ob=document.getElementsByName("nempay");
			if(ob[0].checked){
					var n1=$("input[name=nemmon1]").val().length;
					var n2=$("input[name=nemmon2]").val().length;
					if(n1<1||n2<1){
						alert("请填写试用及转正后的工资"); 
						return false;}
			 } 
			if(ob[1].checked){
				var n1=$("input[name=nemyear1]").val().length;
				var n2=$("input[name=nemyear2]").val().length;
				if(n1<1||n2<1){
					alert("请填写年薪及每月发放的工资"); 
					return false;}
			 }
			var childnum = document.getElementById("files").getElementsByTagName(
			"input").length-1;
			
			if(!checkValue(obj)){
				return false;
			}
/* 			var checkSubmitFlg = false; 
 			if(!checkSubmitFlg){ 
				checkSubmitFlg=true;
				 obj.submit();
			}
			else{
				var li=$(".buttonGray");
				for(var i=0;i<li.length;i++){
					if(li[i].value=="提 交") {
						li[i].disabled=true;
						$("form").submit();
					}
				}
			} */ 
			var li=$(".buttonGray");
			for(var i=0;i<li.length;i++){
				if(li[i].value=="提 交") {
					li[i].disabled=true;
				}
			}
			obj.submit();
		}
		function add(){
			$("form").submit();
		}
		
		$(document).ready(function(){
			
		/* 智能输入 */
			var shops = "<%=session.getServletContext().getAttribute("zongbuList")%>".replace("{","").replace("}","").split(", ");
			
			$("input[name=nempart]").autocomplete(shops,{
				max : 10,
				matchContains : true
			});
			$("input[name=nempart]").result(function(event, data, formatted) {
				if (data){
					var gnm = data[0].substring(data[0].indexOf("=")+1);
					var gid = data[0].substring(0,data[0].indexOf("="));
					$("input[name=nempart]").val(gid);
					$("input[name=bsc009]").val(gnm);
				}
			});
		});

		 // 在DOM中插入一个上传文件列表项（div元素)和一个<input type="file"/>元素  
		 <!-- 
	    function insertNextFile(obj)   
	    {   
	  // 获取上传控制个数  
	        var childnum = document.getElementById("files").getElementsByTagName("input").length;         
	        var id = childnum - 1;  
	        var fullName = obj.value;  
	        // 插入<div>元素及其子元素  
	        var fileHtml = '';  
	        fileHtml += '<div  id = "file_preview' + id + '" style ="border-bottom: 1px solid #CCC;">';  

	        fileHtml += fullName.substr(fullName.lastIndexOf('\\')+1) ;  
	        fileHtml += '<a href="javascript:;" onclick="removeFile(' + id + ');">删除</a> </div>  ';  
	      
	        var fileElement = document.getElementById("files_preview");  
	        fileElement.innerHTML = fileElement.innerHTML + fileHtml;      
	        obj.style.display = 'none';   // 隐藏当前的<input type=”file”/>元素  
	        addUploadFile(childnum);  // 插入新的<input type=”file”/>元素  
	    }  
	    //  插入新的<input type=”file”/>元素，适合于不同的浏览器（包括IE、FireFox等）  
	    function addUploadFile(index)  
	    {  
	        try  // 用于IE浏览器  
	        {     
	            var uploadHTML = document.createElement( "<input type='file' id='file_" + index +   
	                                    "' name='file[" + index + "]' onchange='insertNextFile(this)'/>");  
	            document.getElementById("files").appendChild(uploadHTML);  
	        }  
	        catch(e)  // 用于其他浏览器  
	        {   
	            var uploadObj = document.createElement("input");  
	            uploadObj.setAttribute("name", "file[" + index + "]");  
	            uploadObj.setAttribute("onchange", "insertNextFile(this)");  
	            uploadObj.setAttribute("type", "file");  
	            uploadObj.setAttribute("id", "file_" + index);  
	            document.getElementById("files").appendChild(uploadObj);  
	        }  
	    }  
	    function removeFile(index)  // 删除当前文件的<div>和<input type=”file”/>元素  
	    {  
	        document.getElementById("files_preview").removeChild(document.getElementById("file_preview" + index));   
	        document.getElementById("files").removeChild(document.getElementById("file_" + index));      
	    }  
	    function showStatus(obj)  // 显示“正在上传文件”提示信息  
	    {  
	      document.getElementById("status").style.visibility="visible";  
	    }  
	    -->
	</script>

	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="新员工录用审批流程新增" />
		<lemis:tabletitle title="新员工信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form enctype="multipart/form-data" action="/ApaOperAction.do?method=saveNewApa&type=nem" method="POST">
				<tr>
					<lemis:texteditor property="nemname" label="姓名" required="true" disable="false"  />
					<lemis:codelisteditor type="nemsex" isSelect="true" label="性别" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nembirthpl" label="籍贯" required="false" disable="false"  />
					<lemis:formateditor mask="date" property="nembirthdt" required="true" label="出生年月" disable="false" format="true" />
					<lemis:codelisteditor type="userheightestedu" isSelect="true" label="最高学历" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemmajor" label="所学专业" required="false" disable="false"  />
					<lemis:texteditor property="nemschool" label="最后毕业学校" required="false" disable="false"  />
					<lemis:formateditor mask="date" property="nemedudt" required="false" label="毕业时间" disable="false" format="true" />
				</tr>
				<lemis:tabletitle title="岗位信息" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nempart" label="拟录用部门id" required="true" disable="false" />
					<lemis:texteditor property="bsc009" label="拟录用部门" required="true" disable="false" />
					<lemis:texteditor property="nemjob" label="拟录用岗位" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemworkdt" required="false" label="拟入职时间" disable="false" format="true" />
					<lemis:codelisteditor type="nemtype" isSelect="true" label="员工类型" redisplay="true" required="true" />
				</tr>
				</table>
				<lemis:tabletitle title="薪资情况" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>薪资情况，销售经理必填</tr>
				<tr>
					<td><input type="radio" name="nempay" value="0"/>月薪制</td>
					<lemis:texteditor  property="nemmon1" label="试用工资每月税前（元）" required="false" disable="false" />
					<lemis:texteditor  property="nemmon2" label="转正工资每月税前（元）" required="false" disable="false" />					
				</tr>
				<tr>  
                    <td><input type="radio" name="nempay" value="1"/>年薪制</td>
					<lemis:texteditor property="nemyear1" label="年薪标准为税前（元）" required="false" disable="false" />
					<lemis:texteditor property="nemyear2" label="每月发放税前（元）" required="false" disable="false" />	
				</tr>
				<tr>
					<lemis:texteditor property="nemwelfare" label="福利情况" required="false" disable="false" colspan="5"/>
				</tr>
				</table>
				<lemis:tabletitle title="劳动合同签订" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<lemis:texteditor property="nemlimit" label="合同期限（年）" required="false" disable="false" />	
					<lemis:texteditor property="nemtry" label="试用期（月）" required="false" disable="false" />	
					<td>注：入职一个月内完成签订</td>
				</tr>
				</table>
				<lemis:tabletitle title="备注" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="nemnote" rows="5" disabled="false"></html:textarea>
				</tr>
				</table>
				
			    <!--
				<lemis:tabletitle title="流程表格模板:" /> 
				<table class="tableinput">
				<lemis:editorlayout />
					<a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=录用：面试评估表.xls&localname=录用：面试评估表.xls&type=luyong">录用：面试评估表.xls</a></br>
					<a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=录用：应聘登记表.xls&localname=录用：应聘登记表.xls&type=luyong">录用：应聘登记表.xls</a>
				</table>
				 
				<lemis:tabletitle title="附件" />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
				请上传附件：面试评估表和总部新员工录用审批表或门店人员应聘登记表！
				</tr>
				</table>
				 -->
			
				<!-- 上传组件 -->
		    
			
			<!-- <table class="tableinput">  -->
			
            <span id="files">  
             <!--
            <COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='40%'></COLGROUP>
			<tr>
        
			<input type="text"  name="nemid"/> 
              <input type="file" id="file_0" name="file[0]" onchange="insertNextFile(this)" required="true" /> </span>    
               
            </tr>
           </table> 
        
           
           
          

       
        <!--
        <div id="files_preview" style="width: 500px;  margin: 5px 5px; overflow: auto"></div>  
          <div id="status" style="visibility: hidden; color: Red">  
                             正在上传文件  
          </div> 
			</html:form>
		</table>
	     -->


		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

