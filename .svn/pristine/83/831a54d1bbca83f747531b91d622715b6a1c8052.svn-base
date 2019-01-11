<%@ page contentType="text/html; charset=GBK" language="java" 
import="java.util.*,com.jspsmart.upload.*" errorPage="" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>

<%
	//新建一个SmartUpload对象
	SmartUpload su = new SmartUpload();
	// 上传初始化
	su.initialize(pageContext);
	// 将上传文件全部保存到指定目录
	su.upload();

	File1 file = su.getFiles().getFile(0);
	String fileType = file.getFileExt();//获取文件的扩展名
	/* file.saveAs("D:/contrast." + fileType); */
	

	int count = su.save("/qa");
	out.println(count +"个文件上传成功！<br>");
	
 	out.print ("<script>alert('" + count + "个文件上传成功');this.history.go(-1);</script>");

	
%>


<html>
<head>
<title>文件上传处理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
</head>

<body>


</body>
</html>