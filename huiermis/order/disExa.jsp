<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("tdefolno", "������"));
	header.add(new Formatter("tdepdtid", "��Ʒ����"));
	header.add(new Formatter("pdtnm", "��Ʒ����"));
	header.add(new Formatter("gctnm", "��������")); 
	header.add(new Formatter("ictnm", "���˿ͻ�"));
	header.add(new Formatter("tdedis", "�ֿ���"));
	header.add(new Formatter("tdedmindis", "ԭ��С����"));
	header.add(new Formatter("tdeisexa", "�Ƿ������"));
	header.add(new Formatter("tdedt", "��������"));
	
/* 	List<Button> buttons=new ArrayList<Button>();
	buttons.add(new Button("back","����","ord020003","history.back()")); */
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("���","examine(document.all.tableform)");
	buttons.put("����","Disback(document.all.tableform)");
	/* buttons.put("����","history.back()"); */

	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tdefolno", "������"); 
	hidden.put("tdepdtid", "��Ʒ����"); 
	hidden.put("tdeisexa","�Ƿ������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "tdefolno", "������"));
	editors.add(new Editor("text", "tdepdtid", "��Ʒ����"));
	editors.add(new Editor("text","gctnm","��������"));
	editors.add(new Editor("text", "tdeisexa", "�Ƿ������"));
	editors.add(new Editor("date","start","�������ڴ�"));
	editors.add(new Editor("date","end","��"));
	
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/huiermis/js/jquery.bgiframe.min.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	$(document).ready(function(){
		 var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
	     
			if(shops.length==1)
			{
				$("input[name=gctnm]").val(shops[0].substring(shops[0].indexOf("=")+1,shops[0].length));
				$("input[name=gctnm]").attr("readonly","true");
			}
			
			$("input[name=gctnm]").autocomplete(shops,{
				max:10,
				matchContains:true,
				formatItem:function(data,i,max){
					return data[0].substring(0);
				}
			});
			
			$("input[name=gctnm]").result(function(event,data,formatted){
				if(data){
					var gid = data[0].substring(0,data[0].indexOf("="));
					var gnm=data[0].substring(data[0].indexOf("=")+1,data[0].length);
					$("input[name=gctnm]").val(gnm);
				/* 	$("input[@type=hidden][name=folctid]").val(gid);
					$("input[@type=hidden][name=folctid]").val(); */
					
				}
			});
	});
	function examine(obj) {
		var t = delObj("chk");//У����û�п��ύ��
		if (!t) {
			return t;
		}
		t = preCheckForBatch();//�Ա�¼��У��
		if (!t) {
			return t;
		}
		if (confirm("ȷʵҪ���ͨ��������������")) {
		obj.action = '<html:rewrite page="/OrderAction.do?method=examineDis"/>';			
		obj.submit();
		}
		//�������ύ����̨ ע�⣺getAlldata(document.all.tableform)�ǵõ�����Ҫ�ύ�����ݡ�
		else
			return t;
	};
	
	function Disback(obj) {
		var t = editObj("chk");
		if(!t){
			return	t;
		}
		if (confirm("ȷʵҪ���˸ÿ���������")) {
		obj.action='<html:rewrite page="/OrderAction.do?method=Disback&"/>'+getAlldata(obj);
		obj.submit();
		}
		//�������ύ����̨ ע�⣺getAlldata(document.all.tableform)�ǵõ�����Ҫ�ύ�����ݡ�
		else
			return t;
	};
</script>
<script language="javascript">
//�鿴������ϸ
	function print(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/OrderAction.do?method=orderDetail&tp2=s&"/>'+getAlldata(obj);	
	obj.submit();
	};
	</script>

<lemis:base />
<lemis:body>
	<lemis:title title="������ѯ" />
	<lemis:query action="/OrderAction.do?method=queryDisExa" editorMeta="editor"
		topic="��ѯ����" />
	<lemis:table action="OrderAction.do" headerMeta="header" topic="������Ϣ"
		hiddenMeta="hidden" mode="checkbox" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


