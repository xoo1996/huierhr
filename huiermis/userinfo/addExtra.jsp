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


	 // ��DOM�в���һ���ϴ��ļ��б��divԪ��)��һ��<input type="file"/>Ԫ��  
    function insertNextFile(obj)   
    {   
  // ��ȡ�ϴ����Ƹ���  
        var childnum = document.getElementById("files").getElementsByTagName("input").length;         
        var id = childnum - 1;  
        var fullName = obj.value;  
        // ����<div>Ԫ�ؼ�����Ԫ��  
        var fileHtml = '';  
        fileHtml += '<div  id = "file_preview' + id + '" style ="border-bottom: 1px solid #CCC;">';  
 
        fileHtml += '<a href="javascript:;" onclick="removeFile(' + id + ');">ɾ��</a>   ';  
        fileHtml += fullName.substr(fullName.lastIndexOf('\\')+1) +'  </div>';  
      
        var fileElement = document.getElementById("files_preview");  
        fileElement.innerHTML = fileElement.innerHTML + fileHtml;      
        obj.style.display = 'none';   // ���ص�ǰ��<input type=��file��/>Ԫ��  
        addUploadFile(childnum);  // �����µ�<input type=��file��/>Ԫ��  
    }  
    //  �����µ�<input type=��file��/>Ԫ�أ��ʺ��ڲ�ͬ�������������IE��FireFox�ȣ�  
    function addUploadFile(index)  
    {  
        try  // ����IE�����  
        {     
            var uploadHTML = document.createElement( "<input type='file' id='file_" + index +   
                                    "' name='file[" + index + "]' onchange='insertNextFile(this)'/>");  
            document.getElementById("files").appendChild(uploadHTML);  
        }  
        catch(e)  // �������������  
        {   
            var uploadObj = document.createElement("input");  
            uploadObj.setAttribute("name", "file[" + index + "]");  
            uploadObj.setAttribute("onchange", "insertNextFile(this)");  
            uploadObj.setAttribute("type", "file");  
            uploadObj.setAttribute("id", "file_" + index);  
            document.getElementById("files").appendChild(uploadObj);  
        }  
    }  
    function removeFile(index)  // ɾ����ǰ�ļ���<div>��<input type=��file��/>Ԫ��  
    {  
        document.getElementById("files_preview").removeChild(document.getElementById("file_preview" + index));   
        document.getElementById("files").removeChild(document.getElementById("file_" + index));      
    }  
    function showStatus(obj)  // ��ʾ�������ϴ��ļ�����ʾ��Ϣ  
    {  
      document.getElementById("status").style.visibility="visible";  
    }  

</script>
</head>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="excel����Ա������" />
		<html:form enctype="multipart/form-data" action="/UserInfoAction.do?method=addEducateByExcel" method="post">  
          <input type="file" id="file_0" name="file[0]" /> </span>  
          <html:submit>&nbsp;&nbsp;�ϴ�&nbsp;&nbsp;</html:submit>  
		</html:form>  
	
	
	
           
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>