/**
 * <p>����: ��վģʽ��ȫ������</p>
 * <p>˵��: �˹���������ʹ��TZPF���ʱ��ͨ���û����webservice���������֤����վ����Facade�м���</p>
 * <p>��Ŀ: Rapid Application Development Framework</p>
 * <p>��Ȩ: Copyright 2008 - 2017</p>
 * <p>ʱ��: 2005-12-11 15:55:59</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.plat.webcontroller.safe;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radf.login.common.LoginUtil;
import org.radf.login.dto.LoginDTO;
import org.radf.manage.entity.Sc08;
import org.radf.plat.cp.a.a;
import org.radf.plat.cp.tree.Trees;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.taglib.WizardURL;
import org.radf.plat.util.global.GlobalNames;

public class LoginFilter implements SaftyFacade {
	private static String[] FILTER_PAGES = null;

	public LoginFilter() {
		String s = GlobalNames.get_INITCONFIG("FILTER_PAGES");
		init(s);
	}

	public LoginFilter(String s) {
		init(s);
	}

	private void init(String fileterPath) {
		if (FILTER_PAGES == null) {
			FILTER_PAGES = getAsStringArray(fileterPath, ",");
			if (FILTER_PAGES.length == 1 && FILTER_PAGES[0] == null) {
				FILTER_PAGES[0] = "/";
			} else {
				for (int i = 0; FILTER_PAGES.length > i; i++) {
					if (!FILTER_PAGES[i].startsWith("/")) {
						FILTER_PAGES[i] = "/" + FILTER_PAGES[i];
					}
					if (!FILTER_PAGES[i].endsWith("/")) {
						FILTER_PAGES[i] = FILTER_PAGES[i] + "/";
					}
					if (FILTER_PAGES[i].equals("/")) {
						FILTER_PAGES[0] = "/";
						break;
					}
				}
			}
		}
	}

	public boolean filter(FilterConfig config, HttpServletRequest hreq,
			HttpServletResponse hres, RequestEnvelopHead head)
			throws IOException, ServletException {
		return filter(config, hreq, hres);
	}

	private boolean filter(FilterConfig config, HttpServletRequest hreq,
			HttpServletResponse hres) throws IOException, ServletException {

		String path = hreq.getServletPath();
		String pathtouch = "";
		String pathbigscreen = "";
		if(path!=null)
		{
			if(path.length()>17)
			{
				pathtouch=path.substring(0,17);
			}
			if(path.length()>10)
			{
				pathbigscreen=path.substring(0,10);
			}
		}
		if("/touchscreen/show".equalsIgnoreCase(pathtouch)||"/bigscreen".equalsIgnoreCase(pathbigscreen))
		{
			return true;
		}
		String ip = hreq.getRemoteAddr();
		if (GlobalNames.DEBUG_OUTPUT_FLAG) {
			System.out.println("ClinetIP��" + ip + " ServletPath��" + path);
		}
		if (path.equals("/webservice.jsp")) {
			return true;
		}
		HttpSession session = hreq.getSession(false);

		if (session == null) {
			config.getServletContext().setAttribute(ip, path);
			if (GlobalNames.LOGON_PAGE.equals(path)) {
				return true;
			} else {
				hres.sendRedirect(hres
				.encodeRedirectURL(GlobalNames.WEB_APP
						+ GlobalNames.LOGON_PAGE));
				return false;
				// ����ǵ�¼Action���򽻸�Action���к�������
//				if (GlobalNames.LOGON_ACTION.equals(path)) {
//					hres.sendRedirect(hres
//							.encodeRedirectURL(GlobalNames.WEB_APP
//									+ GlobalNames.LOGON_PAGE));
//					return false;
//				} else if (GlobalNames.MAIN_PAGE.equals(path)) {
//					return true;
//				} else {
//					hres.sendRedirect(hres
//							.encodeRedirectURL(GlobalNames.WEB_APP
//									+ GlobalNames.LOGON_PAGE));
//					return false;
//				}
			}

		} else {			
			
			if (GlobalNames.LOGON_PAGE.equals(path)
					|| GlobalNames.LOGON_ACTION.equals(path)) {
				return true;
			} else if (GlobalNames.MENU_ACTION.equals(path)) {
				java.lang.String s4 = hreq.getParameter(GlobalNames.MENU_NAME);
				Trees trees = (Trees) hreq.getSession().getAttribute(
						GlobalNames.MENU_TREE);
				hreq.getSession().setAttribute(GlobalNames.MENU_XML,
						a.a(trees, s4));
				return true;
			} else if (GlobalNames.TOP_PAGE.equals(path)
					|| GlobalNames.MENU_PAGE.equals(path)
					|| GlobalNames.MAIN_PAGE.equals(path)) {
				return true;
			} else {
				LoginDTO dto = (LoginDTO) hreq.getSession().getAttribute(
				"LoginDTO");
				if (dto != null) {
					java.util.List list = (java.util.List) session
							.getAttribute(GlobalNames.FUNCTION_LIST);
					if (list == null) {
						config.getServletContext().setAttribute(ip, path);
						return true;
					} else {
						if (a(hreq, list)) {
							return true;
						} else {
							hres.sendRedirect(hres
									.encodeRedirectURL(GlobalNames.WEB_APP
											+ GlobalNames.LOGON_PAGE));
							return false;
						}
					}
				} else {
					hres.sendRedirect(hres
							.encodeRedirectURL(GlobalNames.WEB_APP
									+ GlobalNames.LOGON_PAGE));
					return false;
				}
			}

		}

	}

	/**
	 * û�е�¼��Ϣ�����ߵ�¼��Ϣ�����ʱ�� ���Ƿ��ʲ����Ƶ�ҳ���Ŀ¼�б������ض���ҵ���¼ҳ��
	 * 
	 * @param hres
	 * @throws IOException
	 */
	private boolean sendRedirect(HttpServletResponse hres, String path)
			throws IOException {
		for (int i = 0; FILTER_PAGES.length > i; i++) {
			if (!path.equals("/errorpage.jsp")
					&& path.startsWith(FILTER_PAGES[i])) {
				hres.sendRedirect(hres.encodeRedirectURL(GlobalNames.WEB_APP
						+ GlobalNames.MAIN_PAGE));
				return false;
			}
		}
		return true;
	}

	/**
	 * �˷�����StringUtil�еķ�����Ϊ�˱��ֽӿڶ����Բŷŵ������� ���ݷָ��delim�����ַ���s_valueת��Ϊ��������
	 * 
	 * @param s_value
	 *            �ַ���
	 * @param delim
	 *            �ָ�����Ϊ��Ĭ��ʹ�á�,���ָ�
	 * @return Collection �ַ����б�
	 */
	private String[] getAsStringArray(String s_value, String delim) {
		if (delim == null || delim.equalsIgnoreCase("")) {
			delim = ",";
		}
		String[] s = new String[getSubtringCount(s_value, delim) + 1];

		int firstindex = s_value.indexOf(delim); // ��һ��λ��
		if (firstindex == -1) {
			// ������û��ָ���ķָ����
			s[0] = s_value;
		} else {
			int i = 0;
			while (firstindex != -1) {
				s[i] = s_value.substring(0, firstindex);
				s_value = s_value.substring(firstindex + delim.length(),
						s_value.length());
				firstindex = s_value.indexOf(delim);
				i++;
			}
			s[i] = s_value;
		}
		return s;
	}

	/**
	 * �˷�����StringUtil�еķ�����Ϊ�˱��ֽӿڶ����Բŷŵ������� �õ��ַ����е��Ӵ��ĸ�����
	 * 
	 * @param source
	 *            �ַ���
	 * @param sub
	 *            �Ӵ�
	 * @return �ַ����е��Ӵ��ĸ���
	 */
	private int getSubtringCount(String source, String sub) {
		if (source == null || source.length() == 0) {
			return 0;
		}
		int count = 0;
		for (int index = source.indexOf(sub); index >= 0; index = source
				.indexOf(sub, index + 1))
			count++;
		// int index = source.indexOf(sub);
		// while (index >= 0) {
		// count++;
		// index = source.indexOf(sub, index + 1);
		// }
		return count;
	}

	private boolean a(javax.servlet.http.HttpServletRequest httpservletrequest,
			java.util.List list) {
		java.lang.String s = httpservletrequest
				.getParameter(GlobalNames.METHOD);
		java.lang.String s1 = httpservletrequest.getServletPath();
		java.lang.StringBuffer stringbuffer = new StringBuffer(s1);
		if (s == null) {
			java.lang.String s2 = httpservletrequest.getParameter("prefix");
			java.lang.String s4 = httpservletrequest.getParameter("page");
			if (s2 != null && s4 != null)
				stringbuffer.append("?prefix=").append(s2).append("&page=")
						.append(s4);
		} else {
			if ("commonQuery".equals(s) || "goBack".equals(s))
				return true;
			stringbuffer.append("?method=").append(s);
		}
		java.lang.String s3 = httpservletrequest.getParameter("menuId");
		if (s3 != null)
			stringbuffer.append("&menuId=").append(s3);
		java.lang.String s5 = stringbuffer.toString();

		for (java.util.Iterator iterator = list.iterator(); iterator.hasNext();) {
			Sc08 functiondto = (Sc08) iterator.next();
			if (functiondto.getBsc017() == null)
				functiondto.setBsc017("");
			if (s5.toLowerCase().equals(functiondto.getBsc017().toLowerCase())) {
				httpservletrequest.getSession().setAttribute("userfunctiondto",
						functiondto);
				java.lang.String s6 = functiondto.getBsc021();
				if (!GlobalNames.MENU_BUTTON.equals(s6))
					httpservletrequest.getSession().setAttribute(
							GlobalNames.NAVIGATION,
							a.a(httpservletrequest, functiondto.getBsc016()));
				doWizard(httpservletrequest);
				if (GlobalNames.MENU_LEAF.equals(s6))
					httpservletrequest.getSession().setAttribute(
							GlobalNames.CAN_CLEAR_FORM, "true");
				else
					httpservletrequest.getSession().setAttribute(
							GlobalNames.CAN_CLEAR_FORM, "false");
				break;
			}
		}
		if(GlobalNames.DEBUG_OUTPUT_FLAG){
		    LoginUtil.Sc01logAdd(httpservletrequest, s5);
		}
		return true;
	}

	public void doWizard(
			javax.servlet.http.HttpServletRequest httpservletrequest) {
		java.lang.String s = httpservletrequest
				.getParameter(GlobalNames.NEXT_WIZARD);
		if (s != null) {
			WizardURL wizardurl = (WizardURL) httpservletrequest.getSession()
					.getAttribute(GlobalNames.WIZARD_URL);
			if (wizardurl != null) {
				wizardurl.addURL(s);
			} else {
				WizardURL wizardurl1 = new WizardURL(s);
				httpservletrequest.getSession().setAttribute(
						GlobalNames.WIZARD_URL, wizardurl1);
			}
		}
	}
}
