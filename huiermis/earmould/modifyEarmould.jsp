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
	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("tmeno","������"));
	
	header.add(new Formatter("tmesid","��ģ���"));
	header.add(new Formatter("tmegctnm","�ͻ�����"));
	header.add(new Formatter("tmecltnm","�û�����"));
	header.add(new Formatter("tmesta","����״̬"));
	header.add(new Formatter("pdtnm","��ģ����"));
	header.add(new Formatter("tmemat","��ģ����"));
	header.add(new Formatter("tmefree","�Ƿ�����"));
	header.add(new Formatter("tmecls","�������"));
	header.add(new Formatter("tmepdt","�ƻ����"));
	header.add(new Formatter("tmeurgent","�Ƿ�Ӽ�"));
	header.add(new Formatter("tment","��ע"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("�޸Ķ�ģ��Ϣ","modify(document.all.tableform)");
	buttons.put("ɾ����ģ��¼","deleteci(document.all.tableform)");
	buttons.put("�� ��","closeWindow(\"\")");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmeno","������");
	hidden.put("tmepid","��Ʒ����");
	hidden.put("tmecltnm", "�û�����");
	hidden.put("pdtnm","��ģ�ͺ�");
	hidden.put("tmesid","������");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","tmeno","������"));
	editors.add(new Editor("text","tmecltnm","�û�����"));
	editors.add(new Editor("text","tmesta","��ģ״̬"));
	editors.add(new Editor("text","tmesid","��ģ���"));
	editors.add(new Editor("date","tmeqst","�ƻ��깤����  ��"));
	editors.add(new Editor("date","tmeqen","��"));
	
	pageContext.setAttribute("hidden", hidden);
	pageContext.setAttribute("editor", editors);
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>
<script src="/huiermis/js/jquery-1.3.2.min.js"></script>
<script language="javascript">
         $(document).ready(function(){
            // $('input[name=tmecltnm]').val('');
             //$('input[name=tmeno]').val('');  
             $('#tmeqst').val('');
             $('#tmeqen').val('');
             //��ѯʱ��У��
             $('#queryForm').submit(function(){
                    var startdate = $('#tmeqst').val();
                    var enddate = $('#tmeqen').val();
                    if(startdate != '')
                    {
                        if(enddate == '')
                        {alert("������ƻ��깤��ֹ����!");return false;}
                    }
                    else if(enddate != '')
                    {
                    	if(startdate == '')
                        {alert("������ƻ��깤��ʼ����!");return false;}
                    }
             });
         });

		//������ģ������Ϣ
  		function addnew(obj){
  			var cNM = $("input[name=tmecltnm]").val();
  			if(cNM == "")
  			{
  			  alert("�������û�������");
  	          return false;
  	  		}
			obj.action = '<html:rewrite page="/EarMouldAction.do?method=addNew&clienNM="/>'+cNM;			
			obj.submit();
  		}
		//�޸Ķ�ģ��Ϣ
  		function modify(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/EarMouldAction.do?method=modify&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//ɾ����ģ
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("ȷ��Ҫɾ���ö��ƻ���Ϣ��?")) {
				obj.action = '<html:rewrite page="/EarMouldAction.do?method=delete&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="��ģ��Ϣά��" />
	<lemis:query action="/EarMouldAction.do?method=query&order=modify"
		editorMeta="editor" topic="��ģ��Ϣ��ѯ" />
	<lemis:table action="EarMouldAction.do" headerMeta="header"
		topic="��ģ��Ϣ" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>