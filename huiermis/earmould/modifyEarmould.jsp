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
	header.add(new Formatter("tmeno","订单号"));
	
	header.add(new Formatter("tmesid","耳模编号"));
	header.add(new Formatter("tmegctnm","客户名称"));
	header.add(new Formatter("tmecltnm","用户姓名"));
	header.add(new Formatter("tmesta","生产状态"));
	header.add(new Formatter("pdtnm","耳模名称"));
	header.add(new Formatter("tmemat","耳模类型"));
	header.add(new Formatter("tmefree","是否赠送"));
	header.add(new Formatter("tmecls","制作类别"));
	header.add(new Formatter("tmepdt","计划完成"));
	header.add(new Formatter("tmeurgent","是否加急"));
	header.add(new Formatter("tment","备注"));
	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("修改耳模信息","modify(document.all.tableform)");
	buttons.put("删除耳模记录","deleteci(document.all.tableform)");
	buttons.put("关 闭","closeWindow(\"\")");
	
	Map<String, String> hidden = new LinkedHashMap<String, String>();
	hidden.put("tmeno","订单号");
	hidden.put("tmepid","商品代码");
	hidden.put("tmecltnm", "用户姓名");
	hidden.put("pdtnm","耳模型号");
	hidden.put("tmesid","条形码");
	
	List<Editor> editors = new ArrayList<Editor>();
	editors.add(new Editor("text","tmeno","订单号"));
	editors.add(new Editor("text","tmecltnm","用户姓名"));
	editors.add(new Editor("text","tmesta","耳模状态"));
	editors.add(new Editor("text","tmesid","耳模编号"));
	editors.add(new Editor("date","tmeqst","计划完工日期  从"));
	editors.add(new Editor("date","tmeqen","到"));
	
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
             //查询时的校验
             $('#queryForm').submit(function(){
                    var startdate = $('#tmeqst').val();
                    var enddate = $('#tmeqen').val();
                    if(startdate != '')
                    {
                        if(enddate == '')
                        {alert("请输入计划完工截止日期!");return false;}
                    }
                    else if(enddate != '')
                    {
                    	if(startdate == '')
                        {alert("请输入计划完工开始日期!");return false;}
                    }
             });
         });

		//新增耳模订单信息
  		function addnew(obj){
  			var cNM = $("input[name=tmecltnm]").val();
  			if(cNM == "")
  			{
  			  alert("请输入用户姓名！");
  	          return false;
  	  		}
			obj.action = '<html:rewrite page="/EarMouldAction.do?method=addNew&clienNM="/>'+cNM;			
			obj.submit();
  		}
		//修改耳模信息
  		function modify(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			obj.action = '<html:rewrite page="/EarMouldAction.do?method=modify&"/>'+getAlldata(obj);		
			obj.submit();
  		}
		//删除耳模
  		function deleteci(obj){
			var t = editObj("chk");
			if(!t){
				return t;
			}
			if(confirm("确定要删除该定制机信息吗?")) {
				obj.action = '<html:rewrite page="/EarMouldAction.do?method=delete&"/>'+getAlldata(obj);		
				obj.submit();
			}
  		}
</script>
<lemis:base />
<lemis:body>
	<lemis:title title="耳模信息维护" />
	<lemis:query action="/EarMouldAction.do?method=query&order=modify"
		editorMeta="editor" topic="耳模信息查询" />
	<lemis:table action="EarMouldAction.do" headerMeta="header"
		topic="耳模信息" hiddenMeta="hidden" mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>