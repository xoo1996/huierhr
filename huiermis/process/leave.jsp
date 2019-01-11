<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("提 交","saveData(document.forms[0])");
buttons.put("返 回","history.back()");
pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
		function saveData(obj){
			var childnum = document.getElementById("files").getElementsByTagName(
			"input").length-1;
			if(childnum<2){
				alert('请上传附件：离职交接表、固定资产盘存表');
				return false;
				}
			if(!checkValue(obj)){
				return false;
			}
			obj.submit();
		}
		function add(){
			$("form").submit();
		}
		
		 // 在DOM中插入一个上传文件列表项（div元素)和一个<input type="file"/>元素  
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
	</script>
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="员工离职交接子流程" />
		<lemis:tabletitle title="基本信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form enctype="multipart/form-data" action="/ApaOperAction.do?method=saveNewApa&type=leave" method="POST">
				<tr>
					<lemis:texteditor property="nemid" label="流程id" required="false" disable="true" />
					<lemis:texteditor property="nemname" label="姓名" required="false" disable="true" />
					<lemis:texteditor property="nemapplyid" label="员工id" required="false" disable="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="nemappdt" required="false" label="申请时间" disable="true" format="true" />
					<lemis:texteditor property="bsc009" label="门店或部门" required="false" disable="true" />
					<lemis:codelisteditor type="nemtype" isSelect="false" label="员工类型" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="leasdt" required="false" label="入职时间" disable="true" format="true"/>
					<lemis:formateditor mask="date" property="leaedt" required="false" label="拟离职时间" disable="true" format="true"/>
					<lemis:codelisteditor type="leatype" isSelect="false" label="离职类别" redisplay="true" required="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemsoc" label="社保办停情况" required="false" disable="true" colspan="3"/>
				</tr>
				
				<lemis:tabletitle title="离职原因 " />
				<table class="tableinput">
				<lemis:editorlayout />
				<tr>
					<html:textarea property="leareason" rows="5" disabled="true"></html:textarea>
				</tr>
				</table>
				<lemis:tabletitle title="附件:上传离职交接表" />
				<table class="tableinput">
				<lemis:editorlayout />
				</table>
				<!-- 上传组件 -->
			<table class="tableinput">
            <span id="files">  
            <COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='40%'></COLGROUP>
			<tr>
			<!-- <input type="text"  name="nemid"/> -->
               <input type="file" id="file_0" name="file[0]" onchange="insertNextFile(this)" /> </span>  
               
            </tr>
           </table> 

        <div id="files_preview" style="width: 500px;  margin: 5px 5px; overflow: auto"></div>  
          <div id="status" style="visibility: hidden; color: Red">  
            正在上传文件  
        </div>  
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>

