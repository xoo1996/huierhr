package org.radf.login.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Configuration;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.commons.entity.ProClass;
import org.radf.apps.commons.entity.Product;
import org.radf.login.dto.LoginDTO;
import org.radf.login.facade.LoginFacade;
import org.radf.login.form.LoginForm;
import org.radf.manage.entity.Sc03;
import org.radf.manage.entity.Sc04;
import org.radf.manage.entity.Sc05;
import org.radf.manage.entity.Sc10;
import org.radf.manage.param.entity.Aa01;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.cp.a.a;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.global.GlobalNames;

/**
 * ������Ա�ĵ�½���˳�
 * 
 * @author Administrator
 */
public class LogonAction extends ActionLeafSupport {
	/**
	 * ������Ա��½ �ɹ���תSUCCESS ʧ����תFAILURE ������Ա��Ϣ����LoginDTO�Լ���Ա��ӵ�еĲ˵�Ȩ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward userLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ActionForward forward = new ActionForward();
		// ��ȡҳ������
		LoginForm loginform = (LoginForm) form;
		if (loginform.getBsc011() == null || "".equals(loginform.getBsc011())) {
			response.sendRedirect(response
					.encodeRedirectURL(GlobalNames.WEB_APP
							+ GlobalNames.LOGON_PAGE));
			return null;
		}
		if (loginform.getBsc013() == null || "".equals(loginform.getBsc013())) {
			response.sendRedirect(response
					.encodeRedirectURL(GlobalNames.WEB_APP
							+ GlobalNames.LOGON_PAGE));
			return null;
		}
		// ������Ա����
		LoginDTO dto = new LoginDTO();
		Sc10 sc10 = new Sc10();
		sc10.setBsc026(request.getRemoteAddr());
		sc10.setBsc029(request.getSession().getId());
		// ��ҳ�����ݴ��� ��ֵ����Ա����
		ClassHelper.copyProperties(loginform, dto);
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		HashMap<String,Object> mapRequest = new HashMap<String,Object>();
		mapRequest.put("dto", dto);
		mapRequest.put("sc10", sc10);
		requestEnvelop.setBody(mapRequest);
		// ��ȡ�ӿ�
		LoginFacade facade = (LoginFacade) getService("LoginFacade");
		// ��֤��Ա��Ϣ�Լ���ȡ��Ա�˵�
		ResponseEnvelop resEnv = facade.userLogin(requestEnvelop);
		EventResponse returnValue = processRevt(resEnv);
		List grCli=null;
		if (returnValue.isSucessFlag()) {
			HashMap map = (HashMap) returnValue.getBody();
			dto = (LoginDTO) map.get("dto");
			ArrayList list = (ArrayList) map.get("list");
			Vector vecsc03 = (Vector) map.get("vecsc03");
			List products = (ArrayList) map.get("products");// ������Ʒ����
//			List nomProducts = (ArrayList) map.get("nomProducts");// ��ͨ��Ʒ����
//			List cusProducts = (ArrayList) map.get("cusProducts");// ����������
//			List classes = (ArrayList) map.get("classes");// ��Ʒ���
			HttpSession httpsession = request.getSession(true);
			httpsession.removeAttribute("LoginDTO");
			
			if(dto.getBsc001().equals("1501000000")){
				List shops = (ArrayList) map.get("shops"); // ����������
				List zongbu = (ArrayList) map.get("zongbu");
//				grCli = (ArrayList) map.get("grCli"); // ����������
				List users = (ArrayList) map.get("users");// ���в���Ա����
				List action1 = (ArrayList) map.get("action1");// ά�޴�ʩһ
				List action2 = (ArrayList) map.get("action2");// ά�޴�ʩ��
				List action3 = (ArrayList) map.get("action3");// ά�޴�ʩ��
				List action4 = (ArrayList) map.get("action4");// ά�޴�ʩ��
				List action5 = (ArrayList) map.get("action5");// ά�޴�ʩ��
				List action6 = (ArrayList) map.get("action6");// ά�޴�ʩ��
				List folway = (ArrayList) map.get("folway");//������ʽ
				
				//List panels = (ArrayList) map.get("panels");//������
//				List parts = (ArrayList) map.get("parts");
				
//				List pnlnm = (ArrayList)map.get("pnlnm");
				
				
				
				GroupClient gc = new GroupClient();
				TreeMap<String, String> shoplist = new TreeMap<String, String>();
				for (Object g : shops) {
					ClassHelper.copyProperties(g, gc);
					shoplist.put(gc.getGctid(), gc.getGctnm());
				}
				shoplist.put("1501000000", "�ݶ������ܲ�");
				httpsession.getServletContext().setAttribute("shopList", shoplist);
				
				Sc04 sc04 = new Sc04();
				TreeMap<String, String> zongbuList = new TreeMap<String, String>();
				for (Object g : zongbu) {
					ClassHelper.copyProperties(g, sc04);
					zongbuList.put(sc04.getBsc008(),sc04.getBsc009());
				}
				
				httpsession.getServletContext().setAttribute("zongbuList", zongbuList);
				
				Sc05 sc05 = new Sc05();
				TreeMap<String, String> userlist = new TreeMap<String, String>();
				for (Object u : users) {
					ClassHelper.copyProperties(u, sc05);
					userlist.put(sc05.getBsc011(), sc05.getBsc012() + ":" + sc05.getBsc010());
				}
				httpsession.getServletContext().setAttribute("userList", userlist);
				
//				Configuration part = new Configuration();
//				TreeMap<String, String> partList = new TreeMap<String, String>();
//				for(Object p : parts){
//					ClassHelper.copyProperties(p, part);
//					partList.put(part.getPdtid(),  part.getPdtnm() + ":" + part.getPdtmod());				
//				}
//				httpsession.getServletContext().setAttribute("partList",partList);
				
//				Set<String> pnlnmList = new HashSet<String>();
//				for(Object n : pnlnm){
//					ClassHelper.copyProperties(n, part);
//					pnlnmList.add(part.getCfgpnlnm());
//				}
//				httpsession.getServletContext().setAttribute("pnlnmList", pnlnmList);
				
				
				//����ά�޼۸�ĵ�			
				Aa01 aa01 = new Aa01();
				TreeMap<String, String> action1List = new TreeMap<String, String>();
				for (Object a : action1) {
					ClassHelper.copyProperties(a, aa01);
					action1List.put(aa01.getAaa003(), aa01.getAaa004());
				}
				httpsession.getServletContext().setAttribute("action1List",
						action1List);

				TreeMap<String, String> action2List = new TreeMap<String, String>();
				for (Object a : action2) {
					ClassHelper.copyProperties(a, aa01);
					action2List.put(aa01.getAaa003(), aa01.getAaa004());
				}
				httpsession.getServletContext().setAttribute("action2List",
						action2List);

				TreeMap<String, String> action3List = new TreeMap<String, String>();
				for (Object a : action3) {
					ClassHelper.copyProperties(a, aa01);
					action3List.put(aa01.getAaa003(), aa01.getAaa004());
				}
				httpsession.getServletContext().setAttribute("action3List",
						action3List);

				TreeMap<String, String> action4List = new TreeMap<String, String>();
				for (Object a : action4) {
					ClassHelper.copyProperties(a, aa01);
					action4List.put(aa01.getAaa003(), aa01.getAaa004());
				}
				httpsession.getServletContext().setAttribute("action4List",
						action4List);

				TreeMap<String, String> action5List = new TreeMap<String, String>();
				for (Object a : action5) {
					ClassHelper.copyProperties(a, aa01);
					action5List.put(aa01.getAaa003(), aa01.getAaa004());
				}
				httpsession.getServletContext().setAttribute("action5List",
						action5List);

				TreeMap<String, String> action6List = new TreeMap<String, String>();
				for (Object a : action6) {
					ClassHelper.copyProperties(a, aa01);
					action6List.put(aa01.getAaa003(), aa01.getAaa004());
				}
				httpsession.getServletContext().setAttribute("action6List",
						action6List);
				
				TreeMap<String, String> folwayList = new TreeMap<String, String>();
				for (Object f: folway){
					ClassHelper.copyProperties(f, aa01);
					folwayList.put(aa01.getAaa003(), aa01.getAaa004());
				}
				httpsession.getServletContext().setAttribute("folwayList", folwayList); //������ʽ
				
				
				
				
			}else{
				List shops = (ArrayList) map.get("shops"); // ����������
				
//				grCli=new ArrayList();
//				grCli.add(dto.getBsc011());
				GroupClient gc = new GroupClient();
				TreeMap<String, String> shoplist = new TreeMap<String, String>();
				for (Object g : shops) {
					ClassHelper.copyProperties(g, gc);
					shoplist.put(gc.getGctid(), gc.getGctnm());
				}
				shoplist.put("1501000000", "�ݶ������ܲ�");
				httpsession.getServletContext().setAttribute("shopList", shoplist);
			}
			
			
			
			if (vecsc03 != null) {
				TreeMap treemap = new TreeMap();
				for (int i = 0; i < vecsc03.size(); i++) {
					Sc03 sc03 = new Sc03();
					sc03 = (Sc03) vecsc03.get(i);
					treemap.put(sc03.getBsc007(), sc03.getBsc006());
				}
				request.getSession().getServletContext().setAttribute("BSC006",
						treemap);
			}
 
			httpsession.setAttribute("LoginDTO", dto);
			httpsession.setAttribute(GlobalNames.FUNCTION_LIST, list);
			a.a(request, (Collection) list);

			

			Product product = new Product();
			TreeMap<String, String> pdtlist = new TreeMap<String, String>();
			for (Object p : products) {
				ClassHelper.copyProperties(p, product);
				pdtlist.put(product.getPdtid(), product.getPdtnm() + ":"
						+ product.getPdtprc());
			}
			
//			ProClass pcl = new ProClass();
//			TreeMap<String, String> pcllist = new TreeMap<String, String>();
//			for (Object p : classes) {
//				ClassHelper.copyProperties(p, pcl);
//				pcllist.put(pcl.getPclid(),pcl.getPcllarge() + "-"+pcl.getPclmid()+"-"+pcl.getPclsmall()+"!");
//			}
			
//			Product nomProduct = new Product();
//			TreeMap<String, String> nomPdtList = new TreeMap<String, String>();
//			for (Object p : nomProducts) {
//				ClassHelper.copyProperties(p, nomProduct);
//				nomPdtList.put(nomProduct.getPdtid(), nomProduct.getPdtnm() + ":"
//						+ nomProduct.getPdtprc());
//			}
//			
//			
//			Product cusProduct = new Product();
//			TreeMap<String, String> cusPdtList = new TreeMap<String, String>();
//			for (Object p : cusProducts) {
//				ClassHelper.copyProperties(p, cusProduct);
//				cusPdtList.put(cusProduct.getPdtid(), cusProduct.getPdtnm() + ":"
//						+ cusProduct.getPdtprc());
//			}
			
			grCli = (ArrayList) map.get("grCli"); // ����������
			GroupClient gcli = new GroupClient();
			TreeMap<String, String> grCliList = new TreeMap<String, String>();
			for (Object g : grCli) {
				ClassHelper.copyProperties(g, gcli);
				grCliList.put(gcli.getGctid(), gcli.getGctnm());
			}
			
		    
			httpsession.getServletContext()
					.setAttribute("productList", pdtlist);
//			httpsession.getServletContext().setAttribute("classesList",pcllist);
			
//			httpsession.getServletContext().setAttribute("nomProductList", nomPdtList);
			
//			httpsession.getServletContext().setAttribute("cusProductList", cusPdtList);
			
			httpsession.getServletContext().setAttribute("grCliList",grCliList);
 
			
			
			
			//�������ʱ�õ���������ʾ
			/*Task task = new Task();
			TreeMap<String, String> panelList = new TreeMap<String, String>();
			for(Object p : panels){
				ClassHelper.copyProperties(p, task);
				panelList.put(task.getPnlnt(), task.getPnlnm());				
			}
			httpsession.getServletContext().setAttribute("panelList",panelList);*/
			
			
			

//			Aa01 aa01 = new Aa01();
//			TreeMap<String, String> action1List = new TreeMap<String, String>();
//			for (Object a : action1) {
//				ClassHelper.copyProperties(a, aa01);
//				action1List.put(aa01.getAaa003(), aa01.getAaa004() + ":" + aa01.getAaa006());
//			}
//			httpsession.getServletContext().setAttribute("action1List",
//					action1List);
//
//			TreeMap<String, String> action2List = new TreeMap<String, String>();
//			for (Object a : action2) {
//				ClassHelper.copyProperties(a, aa01);
//				action2List.put(aa01.getAaa003(), aa01.getAaa004() + ":" + aa01.getAaa006());
//			}
//			httpsession.getServletContext().setAttribute("action2List",
//					action2List);
//
//			TreeMap<String, String> action3List = new TreeMap<String, String>();
//			for (Object a : action3) {
//				ClassHelper.copyProperties(a, aa01);
//				action3List.put(aa01.getAaa003(), aa01.getAaa004() + ":" + aa01.getAaa006());
//			}
//			httpsession.getServletContext().setAttribute("action3List",
//					action3List);
//
//			TreeMap<String, String> action4List = new TreeMap<String, String>();
//			for (Object a : action4) {
//				ClassHelper.copyProperties(a, aa01);
//				action4List.put(aa01.getAaa003(), aa01.getAaa004() + ":" + aa01.getAaa006());
//			}
//			httpsession.getServletContext().setAttribute("action4List",
//					action4List);
//
//			TreeMap<String, String> action5List = new TreeMap<String, String>();
//			for (Object a : action5) {
//				ClassHelper.copyProperties(a, aa01);
//				action5List.put(aa01.getAaa003(), aa01.getAaa004() + ":" + aa01.getAaa006());
//			}
//			httpsession.getServletContext().setAttribute("action5List",
//					action5List);
//
//			TreeMap<String, String> action6List = new TreeMap<String, String>();
//			for (Object a : action6) {
//				ClassHelper.copyProperties(a, aa01);
//				action6List.put(aa01.getAaa003(), aa01.getAaa004() + ":" + aa01.getAaa006());
//			}
//			httpsession.getServletContext().setAttribute("action6List",
//					action6List);
			
			
			
			
			
		} else {
			String[] errorMsg = StringUtil.getAsStringArray(returnValue
					.getMsg(), "|");
			request.setAttribute("errorMsg", errorMsg[3]);
			return forward = mapping.findForward(FAILURE);
		}
		String dd = DateUtil.converToString(DateUtil.getSystemCurrentTime(),
				"yyyyMMddHHmmss");
		return new ActionForward("/Index.jsp?type=" + dd);
		// forward = mapping.findForward(SUCCESS);
		// return forward;
	}

	/**
	 * �˳�ϵͳ��ע���û�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward userLogout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ***********ע����¼��־***********
		// ����facade������
		Math.random();
		LoginFacade facade = (LoginFacade) getService("LoginFacade");
		HashMap mapRequest = new HashMap();
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = null;
		HttpSession session = request.getSession();
		if (session != null) {
			if (session.getAttribute("LoginDTO") != null) {

				mapRequest.put("dto", session.getAttribute("LoginDTO"));
				requestEnvelop.setBody(mapRequest);
				// ����facade�ӿڣ���¼ע��
				ResponseEnvelop resEnv = facade.logout(requestEnvelop);
				returnValue = processRevt(resEnv);
			}
			// ***********����Ự��Ϣ***********
			session.removeAttribute("LoginDTO");
			request.removeAttribute(GlobalNames.FUNCTION_LIST);
			request.removeAttribute("returnValue");
			session.invalidate();
			session = null;
		}
		// response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		// response.setHeader("Location",
		// "http://192.2.2.60:8081/lemis/LogonDialog.jsp");
		// return null;
		String dd = DateUtil.converToString(DateUtil.getSystemCurrentTime(),
				"yyyyMMddHHmmss");
		return new ActionForward("/LogonDialog.jsp?type=" + dd);
		// return mapping.findForward("logout");
	}

	/**
	 * �û�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward userLogoff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ***********ע����¼��־***********
		// ����facade������
		LoginFacade facade = (LoginFacade) getService("LoginFacade");
		HashMap mapRequest = new HashMap();
		RequestEnvelop requestEnvelop = new RequestEnvelop();
		EventResponse returnValue = null;
		HttpSession session = request.getSession();
		if (session != null) {
			if (session.getAttribute("LoginDTO") != null) {
				mapRequest.put("dto", session.getAttribute("LoginDTO"));
				requestEnvelop.setBody(mapRequest);
				// ����facade�ӿڣ���¼ע��
				ResponseEnvelop resEnv = facade.logout(requestEnvelop);
				returnValue = processRevt(resEnv);
			}
			// ***********����Ự��Ϣ***********
			session.removeAttribute("LoginDTO");
			request.removeAttribute(GlobalNames.FUNCTION_LIST);
			request.removeAttribute("returnValue");
			request.getSession().removeAttribute("logonForm");
			session.invalidate();
			session = null;
		}
		return mapping.findForward("logoff");
	}
}
