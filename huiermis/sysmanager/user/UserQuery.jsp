<!--sysmanager/user/UserQuery.jsp-->
<%@ page contentType="text/html; charset=GBK" language="java"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="org.radf.plat.commons.QueryInfo"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"	prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<OBJECT id="ePass"  name="ePass" height="10" width="10" classid="clsid:C7672410-309E-4318-8B34-016EE77D6B58"  codebase="/ftd/cab/epass1000ND/install.cab" >
</OBJECT>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript" type="text/javascript" ></script> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
/**
* 创建目录
* pName: 创建的项目名称
*/
function CreateDir(pName)
{
			try
			{
					ePass.OpenDevice(1,"");	
			}
			catch(e)
			{
					alert("请检查key是否插入！");
			}
			var lFlags=0x00010000;
			var pGUID=0;
			var dirInfoID=100;
			
			try
			{
					ePass.CreateDir(lFlags,pName,pGUID,dirInfoID);
			}			
			catch(e)
			{
					//0x00000300根据文件名称进行删除
					ePass.VerifyPIN(0,"1234");
					ePass.DeleteDir(0x00000300,pGUID,pName);
					ePass.CreateDir(lFlags,pName,pGUID,dirInfoID);
			}
		ePass.CloseDevice();
}	

/**
* 创建文件 
* fileID  文件编号，使用十六进制，例如：0x01
*/
function CreateFile(pName,epsFileID,pBuffer)
{
			ePass.OpenDevice(1,"");	
			ePass.ChangeDir(0x0300,0,pName);//改变目录  
			//文件长度
			var epsFileSize=16;
			/*
			*   EPAS_FILETYPE_UNUSED 0x00 
			*	EPAS_FILETYPE_DIR 0x01 
			*	EPAS_FILETYPE_DATA 0x02 
			*	EPAS_FILETYPE_MD5 0x04 
			*	EPAS_FILETYPE_TEA 0x08 
			*	EPAS_FILETYPE_UNKNOWN 0xFF 
			* 权限：
			*   EPAS_ACCESS_ANYONE 0x00 
			*	EPAS_ACCESS_USER 0x01 
			*	EPAS_ACCESS_OFFICER 0x02 
			*	EPAS_ACCESS_NONE 0x07 
			*
			*/
			var epsFileType=0x04;
			//读去权限
			var epsFileReadAccess=1;
			//写入权限
			var epsFileWriteAccess=1;
			//加密权限
			var epsFileCryptAccess=1;
			//创建文件
			ePass.CreateFile(0,epsFileID,epsFileSize,epsFileType,epsFileReadAccess,epsFileWriteAccess,epsFileCryptAccess,2);
			
			
		/*
			0 打开文件使用当前授权访问 
			0x00000010  EPAS_FILE_READ  Open the file for reading.   
			0x00000020  EPAS_FILE_WRITE Open the file for writing. 
			0x00000040  EPAS_FILE_CRYPT Open the file for cryptographic operations. 
		*/
		ePass.OpenFile(2,epsFileID);
		
		//0 ASCII数据   1  二进制数据
		var lType=1;
		//保留用于未来的扩展，并且必须设置为零。
		var lFlags=0;
		//从哪个字节开始
		var lOffset=0;
		//待写入的字节数
		var lBytesToWrite=16;
		
		ePass.write(lType,lFlags,lOffset,pBuffer,lBytesToWrite);
		ePass.CloseDevice();
}	

/**
*初始化key
*1 创建目录
*2 创建文件
*3 写入秘钥
*/

function iniKey()
{
		var arr = new Array();
		var str = getAlldata(document.all.tableform);
		arr = str.split("&")[0].split("=");
/** ********创建目录******* */
		var pName="huier";
		CreateDir(pName);
/** ********创建文件和数据******* */
		var key=arr[1];
	   //第一个文件
		 var epsFileID=0x01;
		 var kIpad=MD5HMAC(0,key);
//		 alert("kIpad:==="+kIpad);
		 CreateFile(pName,epsFileID,kIpad);
		 //第二个文件	
		 epsFileID=0x02;
		 var kOpad=MD5HMAC(1,key);
//		 alert("kOpad:==="+kOpad);
		 CreateFile(pName,epsFileID,kOpad);	
		 //打开设备
		 ePass.OpenDevice(1,"");
		 //读取设备序列号
		 var snID = ePass.GetStrProperty(7,0,0);
		 ePass.CloseDevice();

		 var s="snID="+snID;
		 $.ajax({
			  type: "POST",
			  url: '<%=basePath%>sysmanager/userAction.do?method=initKey&snID='+snID+'&ugctid='+key,
			  dataType:"text",
			  data:s,
			  async: true, //true异步执行
			  success:function(result){  
				  alert("初始化成功！");
		         },
		      error:function(response, textStatus, errorThrown)
		      {
					alert("初始化失败："+response.status);
		      }
			});
		 
			
}
function MD5HMAC(lFlags,pAuthKey)
{		 
		ePass.OpenDevice(1,"");    
   /*
   	SOFT_MD5HMAC_IPAD_KEY 0x00 return an array of 16 bytes that receives MD5 (SECRET XOR iPad).  
		SOFT_MD5HMAC_OPAD_KEY 0x01 return an array of 16 bytes that receives MD5(SECRET XOR oPad). 
		SOFT_MD5HMAC_DIGEST   0x02 return 16 bits digests of the input message. 
		*/
   	 var  pRetVal=ePass.Soft_MD5HMAC_Ex(lFlags,"mytest",pAuthKey);
   	 ePass.CloseDevice();
		
   	 return pRetVal;
}

	</script>
<script type="text/javascript">
function openAll(){
	if(confirm("是否全部启用？")){
	  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=ukeyOperate&flag=openAll";
	}else{
    	return false;
  	}
}
function closeAll(){
	if(confirm("是否全部停用？")){
	 window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=ukeyOperate&flag=closeAll";
	}else{
	    	return false;
  	}
}
function openKey(){
	  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=ukeyOperate&flag=open&"+ getAlldata(document.all.tableform);
}
function closeKey(){
	  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=ukeyOperate&flag=close&"+ getAlldata(document.all.tableform);
}
</script>
<lemis:base />
<lemis:body>
<%			String stringData = "";
			QueryInfo qi = (QueryInfo) pageContext
					.findAttribute(GlobalNames.QUERY_INFO);
			if (null != qi) {
				stringData = qi.getStringData();
			}

			
			List editors = new ArrayList();
			editors.add(new Editor("text", "bsc010", "用户内码"));
			editors.add(new Editor("text", "bsc011", "用户代码"));
			editors.add(new Editor("text", "bsc012", "用户名称"));
			editors.add(new Editor("text", "aae005", "联系电话"));
			editors.add(new Editor("text", "bsc001", "机构内码"));
			editors.add(new Editor("text", "bsc008", "科室内码"));
			editors.add(new Editor("text", "aae100", "有效标记"));
			editors.add(new Editor("text", "aab300", "机构名称"));
			editors.add(new Editor("text", "bsc009", "科室名称"));
			
			List header = new ArrayList();
			header.add(new Formatter("sc05.bsc010", "用户内码"));
			header.add(new Formatter("sc05.bsc011", "用户代码"));
			header.add(new Formatter("sc05.bsc012", "用户名称"));
			header.add(new Formatter("sc05.aae005", "联系电话"));
			header.add(new Formatter("sc05.bsc001", "机构内码"));
			header.add(new Formatter("sc01.aab300", "机构名称"));
			header.add(new Formatter("sc05.bsc008", "科室内码"));			
			header.add(new Formatter("sc04.bsc009", "科室名称"));
			header.add(new Formatter("sc05.aae100", "有效标记"));
			header.add(new Formatter("sc05.aae101","ukey启用状态"));
			header.add(new Formatter("sc05.aae036", "操作时间", null, null, true));
			header.add(new Formatter("sc05.aae011", "操作人", null, null, true));
			Map hidden = new LinkedHashMap();
			hidden.put("bsc011","用户代码");
			hidden.put("bsc010", "用户内码");
			hidden.put("bsc012", "用户名称");
			

			Map<String, String> buttons = new LinkedHashMap<String, String>();
			buttons.put("增 加","addData()");
			buttons.put("修 改","editData()");
			buttons.put("注 销","delData()");
			buttons.put("密码重置","passwdReset()"); 
			buttons.put("修改密码","passwdChange()"); 
			buttons.put("关 闭","closeWindow(\"\")");
		
			
			

			pageContext.setAttribute("header", header);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("button", buttons);
			session.setAttribute("tableheader", "人员信息表");
			

%>

	<html:hidden property="stringData" value="<%=stringData%>" />
	<lemis:title title="人员管理" />
	<lemis:query action="/userAction.do?method=userQuery"
		editorMeta="editor" topic="人员信息查询" />
	<lemis:table topic="人员信息" action="/userAction.do" headerMeta="header"
		hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:clean names="userForm" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/md5.js"></script>
<script language="javascript">
function passwdReset(){
	if(confirm("此操作不能回退，确信要重置您选中的用户密码吗？")){
	//密码处理
	window.location.href ="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=passwdReset&function=reset&" + 
	"newpasswd=" + hex_md5("888888") + "&" + getAlldata(document.all.tableform);
	
  	}else{
    	return false;
  	}
}
function passwdChange(){
	//密码处理
	window.location.href ="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=adminAlterPassword&" + getAlldata(document.all.tableform);
	//window.location.href ="/" + lemis.WEB_APP_NAME + "/switchAction.do?prefix=/sysmanager&page=/user/AdminAlterPassword.jsp";	
}
function addData(){
  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=userSav&function=" + "add&" +
  document.all("stringData").value + "&" + getAlldata(document.all.tableform);
}
function editData(){
   var t = editObj("chk");
  if(!t){
    return t;
  }
  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=userSav&function=" + "mod&" +
  document.all("stringData").value + "&" + getAlldata(document.all.tableform); 
}
function delData(){
  var t = delObj("chk");
  if(!t){
    return t;
  }
    if(confirm("此操作不能回退，确信要删除您选中的代码吗？")){
      window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=userDel&stringData=" +
      document.all("stringData").value + "&" + getAlldata(document.all.tableform);
    }else{
      return false;
    }
}
</script>



