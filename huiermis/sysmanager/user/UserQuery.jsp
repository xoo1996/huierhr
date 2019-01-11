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
* ����Ŀ¼
* pName: ��������Ŀ����
*/
function CreateDir(pName)
{
			try
			{
					ePass.OpenDevice(1,"");	
			}
			catch(e)
			{
					alert("����key�Ƿ���룡");
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
					//0x00000300�����ļ����ƽ���ɾ��
					ePass.VerifyPIN(0,"1234");
					ePass.DeleteDir(0x00000300,pGUID,pName);
					ePass.CreateDir(lFlags,pName,pGUID,dirInfoID);
			}
		ePass.CloseDevice();
}	

/**
* �����ļ� 
* fileID  �ļ���ţ�ʹ��ʮ�����ƣ����磺0x01
*/
function CreateFile(pName,epsFileID,pBuffer)
{
			ePass.OpenDevice(1,"");	
			ePass.ChangeDir(0x0300,0,pName);//�ı�Ŀ¼  
			//�ļ�����
			var epsFileSize=16;
			/*
			*   EPAS_FILETYPE_UNUSED 0x00 
			*	EPAS_FILETYPE_DIR 0x01 
			*	EPAS_FILETYPE_DATA 0x02 
			*	EPAS_FILETYPE_MD5 0x04 
			*	EPAS_FILETYPE_TEA 0x08 
			*	EPAS_FILETYPE_UNKNOWN 0xFF 
			* Ȩ�ޣ�
			*   EPAS_ACCESS_ANYONE 0x00 
			*	EPAS_ACCESS_USER 0x01 
			*	EPAS_ACCESS_OFFICER 0x02 
			*	EPAS_ACCESS_NONE 0x07 
			*
			*/
			var epsFileType=0x04;
			//��ȥȨ��
			var epsFileReadAccess=1;
			//д��Ȩ��
			var epsFileWriteAccess=1;
			//����Ȩ��
			var epsFileCryptAccess=1;
			//�����ļ�
			ePass.CreateFile(0,epsFileID,epsFileSize,epsFileType,epsFileReadAccess,epsFileWriteAccess,epsFileCryptAccess,2);
			
			
		/*
			0 ���ļ�ʹ�õ�ǰ��Ȩ���� 
			0x00000010  EPAS_FILE_READ  Open the file for reading.   
			0x00000020  EPAS_FILE_WRITE Open the file for writing. 
			0x00000040  EPAS_FILE_CRYPT Open the file for cryptographic operations. 
		*/
		ePass.OpenFile(2,epsFileID);
		
		//0 ASCII����   1  ����������
		var lType=1;
		//��������δ������չ�����ұ�������Ϊ�㡣
		var lFlags=0;
		//���ĸ��ֽڿ�ʼ
		var lOffset=0;
		//��д����ֽ���
		var lBytesToWrite=16;
		
		ePass.write(lType,lFlags,lOffset,pBuffer,lBytesToWrite);
		ePass.CloseDevice();
}	

/**
*��ʼ��key
*1 ����Ŀ¼
*2 �����ļ�
*3 д����Կ
*/

function iniKey()
{
		var arr = new Array();
		var str = getAlldata(document.all.tableform);
		arr = str.split("&")[0].split("=");
/** ********����Ŀ¼******* */
		var pName="huier";
		CreateDir(pName);
/** ********�����ļ�������******* */
		var key=arr[1];
	   //��һ���ļ�
		 var epsFileID=0x01;
		 var kIpad=MD5HMAC(0,key);
//		 alert("kIpad:==="+kIpad);
		 CreateFile(pName,epsFileID,kIpad);
		 //�ڶ����ļ�	
		 epsFileID=0x02;
		 var kOpad=MD5HMAC(1,key);
//		 alert("kOpad:==="+kOpad);
		 CreateFile(pName,epsFileID,kOpad);	
		 //���豸
		 ePass.OpenDevice(1,"");
		 //��ȡ�豸���к�
		 var snID = ePass.GetStrProperty(7,0,0);
		 ePass.CloseDevice();

		 var s="snID="+snID;
		 $.ajax({
			  type: "POST",
			  url: '<%=basePath%>sysmanager/userAction.do?method=initKey&snID='+snID+'&ugctid='+key,
			  dataType:"text",
			  data:s,
			  async: true, //true�첽ִ��
			  success:function(result){  
				  alert("��ʼ���ɹ���");
		         },
		      error:function(response, textStatus, errorThrown)
		      {
					alert("��ʼ��ʧ�ܣ�"+response.status);
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
	if(confirm("�Ƿ�ȫ�����ã�")){
	  window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=ukeyOperate&flag=openAll";
	}else{
    	return false;
  	}
}
function closeAll(){
	if(confirm("�Ƿ�ȫ��ͣ�ã�")){
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
			editors.add(new Editor("text", "bsc010", "�û�����"));
			editors.add(new Editor("text", "bsc011", "�û�����"));
			editors.add(new Editor("text", "bsc012", "�û�����"));
			editors.add(new Editor("text", "aae005", "��ϵ�绰"));
			editors.add(new Editor("text", "bsc001", "��������"));
			editors.add(new Editor("text", "bsc008", "��������"));
			editors.add(new Editor("text", "aae100", "��Ч���"));
			editors.add(new Editor("text", "aab300", "��������"));
			editors.add(new Editor("text", "bsc009", "��������"));
			
			List header = new ArrayList();
			header.add(new Formatter("sc05.bsc010", "�û�����"));
			header.add(new Formatter("sc05.bsc011", "�û�����"));
			header.add(new Formatter("sc05.bsc012", "�û�����"));
			header.add(new Formatter("sc05.aae005", "��ϵ�绰"));
			header.add(new Formatter("sc05.bsc001", "��������"));
			header.add(new Formatter("sc01.aab300", "��������"));
			header.add(new Formatter("sc05.bsc008", "��������"));			
			header.add(new Formatter("sc04.bsc009", "��������"));
			header.add(new Formatter("sc05.aae100", "��Ч���"));
			header.add(new Formatter("sc05.aae101","ukey����״̬"));
			header.add(new Formatter("sc05.aae036", "����ʱ��", null, null, true));
			header.add(new Formatter("sc05.aae011", "������", null, null, true));
			Map hidden = new LinkedHashMap();
			hidden.put("bsc011","�û�����");
			hidden.put("bsc010", "�û�����");
			hidden.put("bsc012", "�û�����");
			

			Map<String, String> buttons = new LinkedHashMap<String, String>();
			buttons.put("�� ��","addData()");
			buttons.put("�� ��","editData()");
			buttons.put("ע ��","delData()");
			buttons.put("��������","passwdReset()"); 
			buttons.put("�޸�����","passwdChange()"); 
			buttons.put("�� ��","closeWindow(\"\")");
		
			
			

			pageContext.setAttribute("header", header);
			pageContext.setAttribute("editor", editors);
			pageContext.setAttribute("hidden", hidden);
			pageContext.setAttribute("button", buttons);
			session.setAttribute("tableheader", "��Ա��Ϣ��");
			

%>

	<html:hidden property="stringData" value="<%=stringData%>" />
	<lemis:title title="��Ա����" />
	<lemis:query action="/userAction.do?method=userQuery"
		editorMeta="editor" topic="��Ա��Ϣ��ѯ" />
	<lemis:table topic="��Ա��Ϣ" action="/userAction.do" headerMeta="header"
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
	if(confirm("�˲������ܻ��ˣ�ȷ��Ҫ������ѡ�е��û�������")){
	//���봦��
	window.location.href ="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=passwdReset&function=reset&" + 
	"newpasswd=" + hex_md5("888888") + "&" + getAlldata(document.all.tableform);
	
  	}else{
    	return false;
  	}
}
function passwdChange(){
	//���봦��
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
    if(confirm("�˲������ܻ��ˣ�ȷ��Ҫɾ����ѡ�еĴ�����")){
      window.location.href="/" + lemis.WEB_APP_NAME + "/sysmanager/userAction.do?method=userDel&stringData=" +
      document.all("stringData").value + "&" + getAlldata(document.all.tableform);
    }else{
      return false;
    }
}
</script>



