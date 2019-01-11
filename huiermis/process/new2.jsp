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
buttons.put("�� ��","saveData(document.forms[0])");
buttons.put("�� ��","document.forms[0].reset();");//���ģ���¹��õİ�ť
buttons.put("�� ��","history.back()");
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
					alert('���ϴ��������ܲ�ת�������');
					return false;
					}
			}else{
				if(childnum<3){
					alert('���ϴ���������Ա����һ�׶�ѧϰС�ᡢ��Ա���ڶ��׶��ܽ�(ת������)�����˱�');
					return false;
					}
			}
			if(count1<100){
				alert('���Ҽ�����������100����');
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
		<lemis:title title="��Ա��ת��������������" />
		<lemis:tabletitle title="������Ϣ" />
		<table class="tableinput">
			<lemis:editorlayout />
			<html:form enctype="multipart/form-data" action="/ApaOperAction.do?method=saveNewApa&type=cvt" method="POST">
				<tr>
					<lemis:texteditor property="cvtname" label="����" required="true" disable="true" value="<%=nemname %>"/>
					<lemis:formateditor mask="date" property="cvtbdt" required="false" label="��������" disable="false" format="true" />
				</tr>
				<tr>
					<lemis:texteditor property="cvtedu" label="ѧ��" required="false" disable="false" />
					<lemis:texteditor property="cvtsch" label="��ҵѧУ" required="false" disable="false" />
				</tr>
				<tr>
					<lemis:texteditor property="nemapplyid" label="Ա��id" required="true" disable="true" value="<%=nemapplyid %>"/>
					<lemis:formateditor mask="date" property="cvtsdt" required="false" label="��ְʱ��" disable="false" format="true" />
				</tr>
				<lemis:tabletitle title="���Ҽ���" />
				<tr>
					<textarea name="cvtintro" rows="4" cols="120"></textarea>			
				</tr>
				<lemis:tabletitle title="���̱��ģ��:" />
				<table class="tableinput">
				<lemis:editorlayout />
					<a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=ת������Ա����һ�׶�ѧϰ�ܽ�� (1).doc&localname=ת������Ա����һ�׶�ѧϰ�ܽ�� (1).doc&type=zhuanzheng">ת������Ա����һ�׶�ѧϰ�ܽ�� (1).doc</a></br>
					<a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=ת������Ա���ڶ��׶��ܽᣨת�����룩�� (1).doc&localname=ת������Ա���ڶ��׶��ܽᣨת�����룩�� (1).doc&type=zhuanzheng">ת������Ա���ڶ��׶��ܽᣨת�����룩�� (1).doc</a></br>
					<a href="/huiermis/process/ApaOperAction.do?method=localFileDownload&requiredname=ת���������ڽ׶���ѧϰ��ת�������˱�.doc&localname=ת���������ڽ׶���ѧϰ��ת�������˱�.doc&type=zhuanzheng">ת���������ڽ׶���ѧϰ��ת�������˱�.doc</a>
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

