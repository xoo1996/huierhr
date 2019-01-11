<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String part=(String)request.getSession().getAttribute("part");
String nemname=(String)request.getSession().getAttribute("nemname");
String nemapplyid=(String)request.getSession().getAttribute("nemapplyid");
Map<String,String> buttons=new LinkedHashMap<String,String>();
buttons.put("提 交","saveData(document.forms[0])");
buttons.put("重 置","document.forms[0].reset();");//这个模块下公用的按钮
buttons.put("返 回","history.back()");
pageContext.setAttribute("button", buttons);

%>

<html>
	<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
	<script language="javascript">
		function saveData(obj){
			var count1=$("textarea[name=cvtintro]").val().length; 
			var childnum = document.getElementById("files").getElementsByTagName(
			"input").length-1;
			if(<%=part %>=="1501000000"){
				if(childnum<1){
					alert('请上传附件：总部转正申请表');
					return false;
					}
			}else{
				if(childnum<3){
					alert('请上传附件：新员工第一阶段学习小结、新员工第二阶段总结(转正申请)表、考核表');
					return false;
					}
			}
			if(count1<100){
				alert('自我鉴定不得少于100个字');
				return false;
				}
			if(!checkValue(obj)){
				return false;
			}
			
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
		<lemis:title title="新员工转正申请流程新增" />
		<lemis:tabletitle title="基本信息" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form enctype="multipart/form-data" action="/ApaOperAction.do?method=saveNewApa&type=cvt" method="POST">
				<tr>
					<lemis:texteditor property="cvtname" label="姓名" required="true" disable="true" value="<%=nemname %>"/>
					<lemis:formateditor mask="date" property="cvtbdt" required="false" label="出生年月" disable="false" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cvtedu" label="学历" required="false" disable="false" />
					<lemis:texteditor property="cvtsch" label="毕业学校" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemapplyid" label="员工id" required="true" disable="true" value="<%=nemapplyid %>"/>
					<lemis:formateditor mask="date" property="cvtsdt" required="false" label="入职时间" disable="false" format="true" />
				</tr>
				<lemis:tabletitle title="自我鉴定" />
				<tr>
					<textarea name="cvtintro" rows="4" cols="120"></textarea>			
				</tr>
				<lemis:tabletitle title="流程表格模板:" />
				<table class="tableinput">
				<lemis:editorlayout />
					<a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=转正：新员工第一阶段学习总结表 (1).doc&localname=转正：新员工第一阶段学习总结表 (1).doc&type=zhuanzheng">转正：新员工第一阶段学习总结表 (1).doc</a></br>
					<a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=转正：新员工第二阶段总结（转正申请）表 (1).doc&localname=转正：新员工第二阶段总结（转正申请）表 (1).doc&type=zhuanzheng">转正：新员工第二阶段总结（转正申请）表 (1).doc</a></br>
					<a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=转正：试用期阶段性学习（转正）考核表.doc&localname=转正：试用期阶段性学习（转正）考核表.doc&type=zhuanzheng">转正：试用期阶段性学习（转正）考核表.doc</a>
				</table>
				<lemis:tabletitle title="附件" />
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

