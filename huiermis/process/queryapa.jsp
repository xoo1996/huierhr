<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.util.*"%>
<%@ page import="org.radf.plat.taglib.Editor"%>
<%@ page import="org.radf.plat.taglib.Button"%>
<%@ page import="com.lbs.cp.taglib.Formatter"%>
<%@ page import="org.radf.plat.taglib.TagConstants"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="/WEB-INF/plat.tld" prefix="lemis"%>
<%@ page import="org.radf.plat.taglib.TagConstants" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%

	List<Formatter> header = new ArrayList<Formatter>();
	header.add(new Formatter("apaproid", "流程id",TagConstants.DF_CENTER));
	header.add(new Formatter("aparole", "审核角色",TagConstants.DF_CENTER));
	header.add(new Formatter("apaname", "审核人姓名",TagConstants.DF_CENTER));
	header.add(new Formatter("apaverifydt", "审核时间",TagConstants.DF_CENTER));
	header.add(new Formatter("apaadv", "审核意见",TagConstants.DF_CENTER));
	header.add(new Formatter("aparesult", "审核结果",TagConstants.DF_CENTER));
  	
	Map<String,String> buttons=new LinkedHashMap<String,String>();
	buttons.put("返回","history.back()");
	
	pageContext.setAttribute("header", header);
	pageContext.setAttribute("button", buttons);
%>
<html>

<lemis:base />
<lemis:body>
	<lemis:title title="流程审核记录查询" />
	<lemis:table action="ApaOperAction.do" headerMeta="header" topic="流程信息"  mode="radio" />
	<lemis:buttons buttonMeta="button" />
	<lemis:errors />
	<lemis:bottom />
</lemis:body>
</html>


