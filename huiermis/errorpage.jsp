<%@ page contentType="text/html;charset=GBK" language="java"
	isErrorPage="true"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.radf.plat.commons.StringUtil"%>
<%@ page import="org.radf.plat.log.LogHelper"%>
<%@ page import="org.radf.plat.util.global.GlobalNames"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����ҳ��</title>
</head>
<body bgcolor="#FFFFFF" text="#000000">
<%HashMap map = (HashMap) request.getAttribute("message");
			if (map != null) {
				LogHelper log ;
				//��ȡ������Դ
				String Originates = (String) map.get("Originates");
				//��ȡ��ʾ����
				String type = (String) map.get("type");

				//�����0��Դ��imp��
				if (Originates.equals("0")) {
					//��ȡ������Ϣ
					String msg = (String) map.get("msg");
					String bb = msg;
					String[] aa;
					String code = "";
					String cc = "";
					String date = "";
					if (msg.indexOf("|") > 0) {
						aa = StringUtil.getAsStringArray(msg, "|");
						bb = aa[3];
						code = aa[1];
						cc = aa[2];
						date = aa[4];
					}
					//���������ʾ������
					if (type.equals("1")) {
						bb=StringUtil.StringReplace("\"","",bb);
						System.out.println("bb"+bb);
						out.println("<script language=javascript>\n alert("
								+ "'" + bb.trim() + "'"
								+ ");;history.back();</script>");
					} else {//�����Ǵ���ҳ��
						StringBuffer stringbuffer = new StringBuffer();
						stringbuffer.append("<table width='632' height='32' border='0' align='center' cellpadding='0' cellspacing='0'>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='32' align='center' valign='top'><img src='"+GlobalNames.WEB_APP+"/error_top.jpg'' width='632' height='50'></td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("</table>");
						stringbuffer.append("<table width='632' height='396' border='0' align='center' cellpadding='0' cellspacing='0' background='"+GlobalNames.WEB_APP+"/error_center.jpg'>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='396' align='center' valign='top'><table width='98%' border='0' align='center' cellpadding='0' cellspacing='0'>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td width='24%' height='30' align='center' valign='top'>�������:</td>");
						stringbuffer.append("<td width='76%' align='center' valign='top'>"+bb+"</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='30' align='center' valign='top'>�������:</td>");
						stringbuffer.append("<td align='center' valign='top'>"+code+"</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='30' align='center' valign='top'>������Դ:</td>");
						stringbuffer.append("<td align='center' valign='top'>"+cc+"</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='30' align='center' valign='top'>����ʱ��</td>");
						stringbuffer.append("<td align='center' valign='top'>"+date+"</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='30' align='center' valign='top'>��������</td>");
						stringbuffer.append("<td align='center' valign='top'>"+msg+"</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("</table></td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("</table>");
						stringbuffer.append("<table width='632' height='13' border='0' align='center' cellpadding='0' cellspacing='0'>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='4' align='center' valign='top'><img src='"+GlobalNames.WEB_APP+"/error_bottom.jpg' width='632' height='4'></td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("</table>");
						out.println(stringbuffer.toString());
					}

				} else {//���߾���action��
					Exception msgexp = (Exception) map.get("msg");
					//���������ʾ������
					if (type.equals("0")) {
						out.println("<script language=javascript>\n alert("
								+ "'" + "��ָ�����" + "'"
								+ ");;history.back();</script>");
					} else {//�����Ǵ���ҳ��
						StringBuffer stringbuffer = new StringBuffer();
						stringbuffer.append("<table width='632' height='32' border='0' align='center' cellpadding='0' cellspacing='0'>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='32' align='center' valign='top'><img src='"+GlobalNames.WEB_APP+"/error_top.jpg'' width='632' height='50'></td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("</table>");
						stringbuffer.append("<table width='632' height='396' border='0' align='center' cellpadding='0' cellspacing='0' background='"+GlobalNames.WEB_APP+"/error_center.jpg'>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='396' align='center' valign='top'><table width='98%' border='0' align='center' cellpadding='0' cellspacing='0'>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td width='24%' height='30' align='center' valign='top'>�������:</td>");
						stringbuffer.append("<td width='76%' align='center' valign='top'>&nbsp;</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='30' align='center' valign='top'>�������:</td>");
						stringbuffer.append("<td align='center' valign='top'>&nbsp;</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='30' align='center' valign='top'>������Դ:</td>");
						stringbuffer.append("<td align='center' valign='top'>"+ request.getAttribute("servletpath")+ "�еķ�����"+ request.getAttribute("method")+"</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='30' align='center' valign='top'>����ʱ��</td>");
						stringbuffer.append("<td align='center' valign='top'>&nbsp;</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("<tr>");
						stringbuffer.append("<td height='30' align='center' valign='top'>��������</td>");
						stringbuffer.append("<td align='center' valign='top'>"+msgexp+"</td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("</table></td>");
						stringbuffer.append("</tr>");
						stringbuffer.append("</table>");
						out.println(stringbuffer.toString());
					}
				}
			}
		%>
</body>
</html>
