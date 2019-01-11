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
	<lemis:title title="excel表导入员工数据" />
		<html:form enctype="multipart/form-data" action="/UserInfoAction.do?method=addEducateByExcel" method="post">  
          <input type="file" id="file_0" name="file[0]" /> </span>  
          <html:submit>&nbsp;&nbsp;上传&nbsp;&nbsp;</html:submit>  
		</html:form>  
	
	
	
           
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>