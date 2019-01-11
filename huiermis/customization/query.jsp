<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.login.dto.LoginDTO"%>
<%@ page import="org.radf.manage.entity.Sc08" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("tmkfno", "������"));
	header.add(new Formatter("tmkgctnm", "�ͻ�����"));
	header.add(new Formatter("tmkcltnm", "�û�����"));
	header.add(new Formatter("tmksid", "���ƻ�������"));
	header.add(new Formatter("tmkpnl","�����"));
	header.add(new Formatter("tmkplr", "��������"));
	header.add(new Formatter("tmkpnm", "�������ͺ�"));
	header.add(new Formatter("tmkshmknm", "���������Ա"));
	header.add(new Formatter("tmkpdt", "�ƻ��깤"));
	header.add(new Formatter("tmkshinstnm", "��ǰ�װ��Ա"));
	header.add(new Formatter("tmkpst", "���ƻ�״̬"));
	header.add(new Formatter("tmknt", "��ע"));

	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�鿴����","detailci(document.all.tableform)");
	buttons.put("��ӡ������","print(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmkfno", "������");
	hidden.put("tmkcltid", "�û�����");
	hidden.put("tmkpid", "��������");
	hidden.put("tmkcltnm", "�û�����");
	hidden.put("tmkpnm", "��������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "tmkfno", "������"));
	editors.add(new Editor("text","tmksid","���ƻ�������"));
	editors.add(new Editor("text","tmkpst", "���ƻ�״̬"));
	editors.add(new Editor("text","tmkcltnm", "�û�����"));
	editors.add(new Editor("date","start", "�깤���ڴ�"));
	editors.add(new Editor("date","end", "��"));
	editors.add(new Editor("date","tmkpdt", "�ƻ��깤����"));
	editors.add(new Editor("text","tmkpid", "��Ʒ����"));
	editors.add(new Editor("text","tmkpnm", "�������ͺ�"));
	editors.add(new Editor("text","tmkurg", "�Ƿ�Ӽ�"));
	
	//2012-1-18������ѯ����
	editors.add(new Editor("text","tmkgctnm", "�ͻ�����"));
	editors.add(new Editor("text","tmkshmknm", "���������Ա"));
	editors.add(new Editor("text","tmkshinstnm", "��ǰ�װ��Ա"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
	
	LoginDTO dto = (LoginDTO)session.getAttribute("LoginDTO");
%>
<html>
<script language="javascript">
		//��ʾ��ϸ��Ϣ
  		function detailci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/CustomizationAction.do?method=print&"/>'+getAlldata(obj);			
			obj.submit();
  		};
  		function print(obj) {
  			var t = editObj("chk");
  			if (!t) {
  				return t;
  			}
  			else {
  				obj.action = '<html:rewrite page="/CustomizationAction.do?method=barcode&"/>' + getAlldata(obj);
  				obj.submit();
  			} 
  		};
	</script>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>	
<script language="javascript">
$(document).ready(function(){
	var grCli="";
	grCli=<%=dto.getBsc001()%>;
	if(grCli=="1501000000")
	{
		var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
		$("input[name=tmkgctnm]").autocomplete(shops,{
			max:10,
			matchContains:true,
			formatItem:function(data,i,max){
			return data[0].substring(0);
			}
		});
	
		$("input[name=tmkgctnm]").result(function(event,data,formatted){
			if(data){
				var gid = data[0].substring(0,data[0].indexOf("="));
				var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
				$("input[name=tmkgctnm]").val(gnm);
				/* 	$("input[@type=hidden][name=folctid]").val(gid);
				$("input[@type=hidden][name=folctid]").val(); */
			
				}
			});
	}
	else
	{
		$("input[name=tmkgctnm]").val("<%=dto.getBsc012()%>");
		$("input[name=tmkgctnm]").attr("readonly","true");
		$("input[value=����[R]]").bind("click",function(e){
		$("input[name=tmkgctnm]").val("<%=dto.getBsc012()%>");
				
		}); 
 	}
	
	
});
	
</script>

<lemis:base />
<lemis:body>
	<lemis:title title="���Ƽ�¼��ѯ" />
	<lemis:query action="/CustomizationAction.do?method=query"
		editorMeta="editor" topic="��ѯ����" />
	<lemis:table action="CustomizationAction.do" headerMeta="header"
		topic="���Ƽ�¼" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


