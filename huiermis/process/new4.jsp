<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*" %>
<%@ page import="org.radf.plat.taglib.Button" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

	String nemname=(String)request.getSession().getAttribute("nemname");
	String bsc009=(String)request.getSession().getAttribute("bsc009");
	String nempart=(String)request.getSession().getAttribute("nempart");
	String nemapplyid=(String)request.getSession().getAttribute("nemapplyid");
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�� ��","saveData(document.forms[0])");
	buttons.put("�� ��","document.forms[0].reset();");//���ģ���¹��õİ�ť
	buttons.put("�� ��","history.back()");
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
	<script src="/lemis/js/SelectObj.js"></script>
	<script src="/lemis/js/QuickSelectObj.js"></script>
	<script language="javascript">
		function saveData(obj){
			var childnum = document.getElementById("files").getElementsByTagName(
			"input").length-1;
			
			var obj = document.getElementById("files").getElementsByTagName(
			"input");
			var name = obj.files.name;
			alert(name);
			if(childnum<1){
				alert('���ϴ�������Ա����ְ�����');
				return false;
				}
			if(!checkValue(obj)){
				return false;
			}
			
			var li=$(".buttonGray");
			for(var i=0;i<li.length;i++){
				if(li[i].value=="�� ��") {
					li[i].disabled=true;
				}
			}
			
			obj.submit();
		}
		$(document).ready(function(){
			/* �������� */
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

	        fileHtml += fullName.substr(fullName.lastIndexOf('\\')+1) ;  
	        fileHtml += '<a href="javascript:;" onclick="removeFile(' + id + ');">ɾ��</a> </div>  ';  
	      
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
	
	<lemis:body>
		<lemis:base />
		<lemis:errors />
		<lemis:title title="Ա����ְ������������" />
		<lemis:tabletitle title="������Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form enctype="multipart/form-data" action="/ApaOperAction.do?method=saveNewApa&type=lea" method="POST">
				<tr>
					<lemis:texteditor property="nemname" label="����" required="false" disable="true" value="<%=nemname %>"/>
					<lemis:texteditor property="nemapplyid" label="Ա��id" required="false" disable="true" value="<%=nemapplyid %>" />
				</tr>
				<tr>
					<lemis:texteditor property="nempart" label="��������id" required="true" disable="false" value="<%=nempart%>"/>
					<lemis:texteditor property="bsc009" label="���ڲ���" required="true" disable="false" value="<%=bsc009%>"/>
					<lemis:codelisteditor type="nemtype" isSelect="true" label="Ա������" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:formateditor mask="date" property="leasdt" required="true" label="��ְʱ��" disable="false" format="true" />
					<lemis:formateditor mask="date" property="leaedt" required="true" label="����ְʱ��" disable="false" format="true" />
					<lemis:codelisteditor type="leatype" isSelect="true" label="��ְ���" redisplay="true" required="true" />
				</tr>
				<tr>
					<lemis:texteditor property="nemsoc" label="�籣��ͣ���" required="true" disable="false"/>
					<lemis:texteditor property="leareason" label="��ְԭ��" required="true" disable="false" colspan="5"/>
				</tr>
				<lemis:tabletitle title="���̱���ģ��:" />
				<table class="tableinput">
				<lemis:editorlayout />
					 <a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=��ְ����ְ�±���201507.xlsx&localname=��ְ����ְ�±���201507.xlsx&type=lizhi">��ְ����ְ�±���201507.xlsx</a>
				</table>
				<lemis:tabletitle title="����" />
				<table class="tableinput">
				<lemis:editorlayout />
				</table>
				<!-- �ϴ���� -->
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
            �����ϴ��ļ�  
        </div>  
			</html:form>
		</table>
		<lemis:buttons buttonMeta="button" />
		<lemis:bottom />
	</lemis:body>
</html>
