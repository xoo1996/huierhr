<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	Map<String,String> buttons=new LinkedHashMap<String,String>();
    buttons.put("�޸�","edit(document.all.tableform)");
    buttons.put("�� ��","closeWindow(\"\")");
    
    List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text", "feeyear", "��","true"));
	editors.add(new Editor("text", "feemonth", "��","true"));
	 editors.add(new Editor("text","feegctid","�ͻ�����"));
	 editors.add(new Editor("text","gctarea","��������"));
	
	List<Formatter> header = new ArrayList<Formatter>();
	//header.add(new Formatter("feegctid","�ͻ�����"));
	
	header.add(new Formatter("feegctname","�ͻ�����"));
	header.add(new Formatter("feesales","���۶�"));
	header.add(new Formatter("rent","����"));
	/* header.add(new Formatter("feedevice","�豸̯��")); */
	header.add(new Formatter("feemanage","�����"));
	header.add(new Formatter("feetax","˰��"));
	header.add(new Formatter("feepension","�籣��"));
	header.add(new Formatter("feepay","����"));
	header.add(new Formatter("built","װ�޷�"));
	header.add(new Formatter("open","�����"));
	header.add(new Formatter("feeinvoice","��Ʊ˰"));
	header.add(new Formatter("trans","ת�÷�"));
	header.add(new Formatter("feead","����"));
	header.add(new Formatter("feephone","�绰��"));
	header.add(new Formatter("feewater","ˮ���"));
	header.add(new Formatter("feetrip","���÷�"));
	header.add(new Formatter("feepostage","�ʷ�"));
	header.add(new Formatter("feeprocess","������"));
	header.add(new Formatter("feepromotion","����"));
	header.add(new Formatter("feeback","�˻���"));
	header.add(new Formatter("feeoffice","�칫��",true));
	header.add(new Formatter("feesocial","���ط�",true));
	header.add(new Formatter("feemedical","ת���",true));
	header.add(new Formatter("feeaccount","��ƹ���",true));
	header.add(new Formatter("feothersa","��������",true));
	header.add(new Formatter("assetscom","�̶��ʲ�(����)",true));
	header.add(new Formatter("assetscon","�̶��ʲ�(�յ�)",true));
	header.add(new Formatter("assetsmach","�豸",true));	
	header.add(new Formatter("other","�̶��ʲ�",true));
	header.add(new Formatter("bsc012","¼��Ա",true));
	header.add(new Formatter("feeopdata","¼��ʱ��","",TagConstants.DT_YEAR_MONTH_DATE,true));
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("feegctid", "�û����");
	hidden.put("feeyear", "��");
	hidden.put("feemonth", "��");

	
	pageContext.setAttribute("editor",editors);
    pageContext.setAttribute("button", buttons);
    pageContext.setAttribute("header", header);
    pageContext.setAttribute("hidden",hidden);
%>


<%@page import="org.radf.plat.taglib.Editor"%>
<%@page import="org.radf.plat.taglib.TagConstants"%><html>
<link rel="stylesheet" type="text/css"
	href="/huiermis/css/jquery.autocomplete.css" />
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script src="/lemis/js/lemisTree.js"></script>
<script src="/lemis/js/BaseObj.js"></script>
<script src="/lemis/js/EAPObjsMgr.js"></script>
<script src="/lemis/js/SelectObj.js"></script>
<script src="/lemis/js/QuickSelectObj.js"></script>
<script src='/huiermis/js/jquery.autocomplete.js'></script>
<script language="javascript">
	function saveData(obj) {
		if (!checkValue(obj)) {
			return false;
		}
		obj.submit();
	};
	function edit(obj){
	var t = editObj("chk");
	if(!t){
		return t;
	}
	obj.action = '<html:rewrite page="/FeeAction.do?method=edit&"/>'+getAlldata(obj);			
	obj.submit();
	};
		$(document).ready( function() {
		       var shops = "<%=session.getServletContext().getAttribute("shopList")%>".replace("{","").replace("}","").split(", ");
				
				$("input[name=feegctid]").autocomplete(shops,{
					max : 10,
					matchContains : true
				});
				$("input[name=feegctid]").result(function(event, data, formatted) {
					if (data){
						var gnm = data[0].substring(data[0].indexOf("=")+1);
						var gid = data[0].substring(0,data[0].indexOf("="));
						$("input[name=feegctid]").val(gid);
						$(this).parent().next().find("input").val(gid);
					}
				});
			});
</script>

<lemis:body>
	<lemis:base />
	<lemis:errors />
	<lemis:title title="����ͻ�������Ϣ" />
	<lemis:query action="/FeeAction.do?method=query" editorMeta="editor" topic="�ͻ�������Ϣ��ѯ" />
	<lemis:table action="/FeeAction.do" topic="��ѯ������Ϣ" headerMeta="header" hiddenMeta="hidden" 
	   mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:bottom />
</lemis:body>
</html>


		
		
