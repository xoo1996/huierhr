<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>

<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script src="/huiermis/js/proxyRequest.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
	<script src="/lemis/js/BaseObj.js"></script>
	<script src="/lemis/js/EAPObjsMgr.js"></script>
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
<!-- <script src="/huiermis/js/inputDetail.js"> -->

<script type="text/javascript">
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
 
        fileHtml += '<a href="javascript:;" onclick="removeFile(' + id + ');">删除</a>   ';  
        fileHtml += fullName.substr(fullName.lastIndexOf('\\')+1) +'  </div>';  
      
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
</head>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="用户新增" />
		<%-- <html:form enctype="multipart/form-data" action="/UserInfoAction.do?method=upload" method="post">  
          <html:file property="file"></html:file>  
          <html:submit>Upload File</html:submit>  
	</html:form>   --%>
	
	
	 <html:form enctype="multipart/form-data" action="/UserInfoAction.do?method=upload">  
	 <table class="tableinput">
            <span id="files">  
            <COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='40%'></COLGROUP>
            <tr>  
            	<lemis:texteditor property="year" label="年份" disable="false" 
						required="true" />
				<lemis:texteditor property="month" label="月份" disable="false" 
						required="true" />
			</tr>
			<tr>
			
               <input type="file" id="file_0" name="file[0]" onchange="insertNextFile(this)" /> </span>  
               <html:submit value=" 上传 " onclick="showStatus(this);" />  
            </tr>
           </table> 
        </html:form>  
    
      <!--   <a href="/huiermis/userinfo/UserInfoAction.do?method=download&id=1">download</a> -->
        <p>  
        <div id="status" style="visibility: hidden; color: Red">  
            正在上传文件  
        </div>  
        <p>
        <div id="files_preview" style="width: 500px; height: 500px; overflow: auto"></div>  
          
            
       
       <%-- 
	        <html:form enctype="multipart/form-data" action="/UserInfoAction.do?method=upload">  
	        	<tr>
					<lemis:texteditor property="year" label="年份" disable="false" 
						required="true" />
					<lemis:texteditor property="month" label="月份" disable="false" 
						required="true" />
					<input type="file" name="file"/>
					<html:submit value=" 上传 " /> 
				</tr>
	        </html:form>     --%>
          
           
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>