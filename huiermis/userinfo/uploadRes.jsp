<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
	
	/* $(document).ready(function(){	
		
			var href = $("#href");
			 url = "/ResAction.do?method=downloadRes&nemid="
			+ href.attr("nemid") + '&name=' + href.attr("name");
			 $(this).attr("href",url);
			 href.attr("href",url); 
			
			var div = document.getElementById('href'); 
			div.setAttribute("href", url); 
	}); */


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
	<lemis:title title="���Ӹ���" />
	
	
	
	 <html:form enctype="multipart/form-data" action="/ResAction.do?method=uploadProRes">  
	 <table class="tableinput">
            <span id="files">  
            <COLGROUP><COL width='10%'><COL width='15%'><COL width='10%'><COL width='14%'><COL width='10%'><COL width='40%'></COLGROUP>
			<tr>
			<%-- <lemis:texteditor property="nemid" label="���̺�" disable="false" 
					required="true" disable="true"/> --%>
			<input type="text"  name="nemid"/>
               <input type="file" id="file_0" name="file[0]" onchange="insertNextFile(this)" /> </span>  
               <html:submit value=" �ϴ� " onclick="showStatus(this);" />  
            </tr>
           </table> 
        </html:form>  
    
        <p>  
        <div id="status" style="visibility: hidden; color: Red">  
            �����ϴ��ļ�  
        </div>  
        <p>
        <div id="files_preview" style="width: 500px; height: 200px; overflow: auto"></div>  
         
        
        
        <TABLE class=tableList cellSpacing=0 cellPadding=0 style="width:400px" text-align="center" border=0>
				<TBODY>
					<TR>
						<TD>
							<TABLE id=resultset cellSpacing=1 width="100px" align=center border=0>
								<TBODY>				
									<tr >
										<c:forEach items="${requestScope.resList}" var="res" varStatus="obj">
											<c:if test="${obj.count%2 == '0'}">
												<TR class=listColorA text-align=center>
											</c:if>
											<c:if test="${obj.count%2 != '0'}">
												<TR class=listColorB >
											</c:if>
										
											<TD  style="text-align:center" style="WORD-BREAK:break-all" >
											
											<a id="href" href='/huiermis/userinfo/ResAction.do?method=downloadRes&nemid=${res.nemid }&id=${res.id }' >${res.name }</a>
											</TD>
											
										</c:forEach>
									</tr>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
            
       
    
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>