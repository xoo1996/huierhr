package org.radf.apps.cfgmgmt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.apps.basicinfo.dto.PersonMergeDTO;
import org.radf.apps.basicinfo.facade.PersonFacade;
import org.radf.apps.basicinfo.form.PersonForm;
import org.radf.apps.cfgmgmt.form.CIForm;
import org.radf.apps.basicinfo.form.UnitePersonForm;
import org.radf.apps.commons.entity.Ac01;
import org.radf.apps.commons.entity.Tblitsmci;
import org.radf.plat.commons.ActionUtil;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;
/**
 * ��������Ϣ��ѯ
 */
public class QueryCIAction extends ActionLeafSupport
{
    LogHelper log = new LogHelper(this.getClass().getName());

    public QueryCIAction()
    {
    }

    /**
     * �����ͬ�˵����벻ͬҳ��
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��Ա������Ϣ�б�ҳ��
     * @throws	��ת����
     */
    public ActionForward enterPerson(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        String menuid = req.getParameter("menuId");
		req.getSession().setAttribute("menuId",menuid);
        String forward = "personList";
        if ("alter".equalsIgnoreCase(menuid))
        {
            forward = "alterPerson";
        }
        else if ("reg".equalsIgnoreCase(menuid))
        {
            forward = "newPerson";
        }
        else if ("writeOff".equalsIgnoreCase(menuid))
        {
            forward = "writeoffperson";
        }
		else if ("unitePeople".equalsIgnoreCase(menuid)){//��Ա�ϲ�
			req.getSession().setAttribute("UnitePersonForm",new UnitePersonForm());
			forward = "uniteperson";
        }
		 else if ("delPerson".equalsIgnoreCase(menuid))
	        {
	            forward = "delPerson";
	        }
	        else if ("allPeople".equalsIgnoreCase(menuid))
	        {
	            forward = "allPeople";
	        }
        return mapping.findForward(forward);
    }

    /**
     * ��ѯ������Ϣ(������֧��ģ����ѯ)
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��Ա������Ϣ�б�ҳ��
     * @throws	��ѯ����
     */
    public ActionForward query(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {

		req.getSession().setAttribute("mod","cfgmgmt");
        String menuid = req.getParameter("menuId");
        String forward = "/cfgmgmt/queryCI.jsp";
        if ("alter".equalsIgnoreCase(menuid))
        {
            forward = "/basicinfo/alterperson.jsp";
        }
        else if ("writeOff".equalsIgnoreCase(menuid))
        {
            forward = "/basicinfo/writeoffperson.jsp";
        }
        else if ("delPerson".equalsIgnoreCase(menuid))
        {
            forward = "/basicinfo/delPerson.jsp";
        }
        else if ("allPeople".equalsIgnoreCase(menuid))
        {
            forward = "/basicinfo/querypersonall.jsp";
        }
        CIForm ciform = (CIForm) form;
        Tblitsmci ci = new Tblitsmci();
        ActionForward af = new ActionForward(forward);
        try
        {
            ClassHelper.copyProperties(ciform, ci);
            ci.setFileKey("cfg02_003");
            String hql = queryEnterprise(ci);
            af = super.init(req, forward, hql);
            // ����Ƿ���ڣ�
            if (null == req.getAttribute(GlobalNames.QUERY_DATA))
            {
                String msg = "û�в�ѯ�����������ļ�¼��";
                super.saveSuccessfulMsg(req, msg);
            }
        }
        catch (AppException ex)
        {
            this.saveErrors(req, ex);
        }
        catch (Exception e)
        {
            this.saveErrors(req, e);
        }
        return af;
    }

    /**
     * ��ѯ������Ϣ(����ģ����); �����ѯ������ֱ��ת����������
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��Ա������Ϣ�б�ҳ��
     * @throws	��ѯ����
     */
    public ActionForward findPerson(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        PersonForm pf = (PersonForm) form;
        PersonFacade facade = (PersonFacade) getService("PersonFacade");
        String fg = request.getParameter("fg");
        String aac002 = pf.getAac002();
        Ac01 person = new Ac01();
        ClassHelper.copyProperties(pf, person);
        String forward = "/basicinfo/personReg.jsp";
        ActionForward af = new ActionForward();
        // �����������Ա������Ϣ����ֱ��ת�Ƶ���Ա��������
        if ("add".equalsIgnoreCase(fg))
        {
            request.getSession().setAttribute(
                                              "OperPersonForm",
                                              new PersonForm());
            af = new ActionForward("/modiperson.jsp");
            return af;
        }
        person.setFileKey("bas02_003");
        String hql = queryEnterprise(person);
        
        
        
        af = super.init(request, forward, hql);
        // ����Ƿ���ڣ������������Ա��Ϣ����ֱ�ӽ�����Ա��������
        if (null == request.getAttribute(GlobalNames.QUERY_DATA))
        {
            if ((null != aac002) && (!"".equals(aac002)))
            {
                pf.setAac005("01");// ��������Ĭ����Ϊ����
                pf.setAac006(DateUtil.getBirtday(aac002));
                pf.setAac004(DateUtil.getGender(aac002));
				if(DateUtil.checkDateString(pf.getAac006())==false){
                    pf.setAac006("");
                } 
            }
            request.getSession().setAttribute("OperPersonForm", pf);
            af = new ActionForward("/modiperson.jsp");
        }
        return af;
    }

    /**
     * ������ϸ
     * 
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��Ա������Ϣҳ��
     * @throws	��ѯ����
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse res)
            throws Exception
    {
        PersonForm pf = (PersonForm) form;
        String menuid = request.getParameter("menuId");
        String forward = "/personinfo.jsp";
        Ac01 person = new Ac01();
        person.setAac001(pf.getAac001());
        person.setFileKey("ac01_select");
        try
        {
            // ��ȡ�ӿ�
            PersonFacade facade = (PersonFacade) getService("PersonFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap mapRequest = new HashMap();
            mapRequest.put("beo", person);
            // ��HashMap�������requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.findPerson(requestEnvelop);
            // �����ؽ��
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
                List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
                ClassHelper.copyProperties(list.get(0), pf);
                //Ϊ�˵õ��ֵ�����
                pf.setSsjqnm(getidtoname(pf.getCce001(), "CCE001"));
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                super.saveSuccessfulMsg(request,aa[3]);
            }
        }
        catch (Exception e)
        {
            super.saveErrors(request, e);
        }
        if ("query".equalsIgnoreCase(menuid))
        {
            forward = "/viewperson.jsp";
        }
        else if ("writeOff".equalsIgnoreCase(menuid))
        {
            forward = "/viewperson.jsp";
        }
        return new ActionForward(forward);
    }

    /**
     * �����޸ĸ�����Ϣ
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��Ա������Ϣҳ��
     * @throws	��ѯ����
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception
    {
        PersonForm pf = (PersonForm) form;
        String menuid = req.getParameter("menuId");
		System.out.println(req.getParameter("uu"));
        String forward = "/alterperinfo.jsp";
        Ac01 person = new Ac01();
        if ("alter".equalsIgnoreCase(menuid))
        {
            forward = "/modiperson.jsp";
        }
        try
        {
            person.setAac001(pf.getAac001());
            person.setFileKey("ac01_select");
            // ��ȡ�ӿ�
            PersonFacade facade = (PersonFacade) getService("PersonFacade");
            RequestEnvelop requestEnvelop = new RequestEnvelop();
            EventResponse returnValue = new EventResponse();
            // ��Application�������HashMap
            HashMap mapRequest = new HashMap();
            mapRequest.put("beo", person);
            // ��HashMap�������requestEnvelop
            requestEnvelop.setBody(mapRequest);
            // ���ö�Ӧ��Facadeҵ������
            ResponseEnvelop resEnv = facade.findPerson(requestEnvelop);
            // �����ؽ��
            returnValue = processRevt(resEnv);
            if (returnValue.isSucessFlag())
            {
                List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
                ClassHelper.copyProperties(list.get(0), pf);
                //Ϊ�˵õ��ֵ�����
                pf.setSsjqnm(getidtoname(pf.getCce001(), "CCE001"));
            }
            else
            {
                String[] aa = StringUtil.getAsStringArray(
                                                          returnValue.getMsg(),
                                                          "|");
                super.saveSuccessfulMsg(req,aa[3]);
            }
        }
        catch (Exception e)
        {
            super.saveErrors(req, e);
        }
        return new ActionForward(forward);
    }


    /**
     * �õ���������������
     * @param	id		�ֵ�����
     * @param	field	��������
     * 
     * @return	��������������
     */
    public String getidtoname(String id, String field)
            throws java.lang.Exception
    {

        javax.servlet.ServletContext servletcontext = servlet
                .getServletContext();
        java.util.TreeMap treemap = (java.util.TreeMap) servletcontext
                .getAttribute(field.toUpperCase());
        Object obj = id;
        if (obj == null)
            obj = "";
        if (treemap != null)
            obj = treemap.get(obj);
        if (obj == null)
            obj = "";

        return obj.toString();
    }
	
    /**
     * ��Ա�ϲ��Ĳ�ѯ
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��Ա������Ϣҳ��
     * @throws	��ѯ����
     */
	 public ActionForward queryUnitePerson(ActionMapping mapping, ActionForm form,
	            HttpServletRequest req, HttpServletResponse res) throws Exception
	    {
	        String forward = "/basicinfo/uniteperson.jsp";
	       
			UnitePersonForm pf = (UnitePersonForm) form;
	        Ac01 person = new Ac01();
	        ActionForward af = new ActionForward(forward);
	        try
	        {
	            ClassHelper.copyProperties(pf, person);
	            person.setFileKey("bas02_026");
	            String hql = queryEnterprise(person);
	            af = super.init(req, forward, hql,"1");
	            // ����Ƿ���ڣ�
	            if (null == req.getAttribute(GlobalNames.QUERY_DATA))
	            {
	                String msg = "û�в�ѯ�����������ļ�¼��";
	                super.saveSuccessfulMsg(req, msg);
	            }
	        }	        
	        catch (Exception e)
	        {
	            this.saveErrors(req, e);
				return mapping.findForward("backspace");
	        }
	        return af;
	    }
	 
	 /**
	  * ��Ա�ϲ��ڶ�����ѯ
     * @param	mapping		��ӳ����
     * @param	actionForm	������ʵ��
     * @param	request	    �ͻ��˵�HTTP��������
     * @param	response   	�ͻ��˵�HTTP��������
     * 
     * @return	��Ա������Ϣҳ��
     * @throws	��ѯ����
	 */
		public ActionForward initFindOtherPerson(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception
	    {
			UnitePersonForm pf = (UnitePersonForm) form;		
			PersonMergeDTO dto = new PersonMergeDTO();
			ActionForward af = new ActionForward();
			af = mapping.findForward("uniteotherperson");
			try {
				ClassHelper.copyProperties(pf, dto);
				dto.setFileKey("bas02_027");
				
				PersonFacade facade = (PersonFacade) getService("PersonFacade");
			      RequestEnvelop requestEnvelop = new RequestEnvelop();
			      EventResponse returnValue = new EventResponse();
			      HashMap mapRequest = new HashMap();
			      mapRequest.put("beo", dto);
			      //mapRequest.put("cu", userform);
			      requestEnvelop.setBody(mapRequest);
			      // ���ýӿڵ�ʵ�������в�ѯ
			      ResponseEnvelop resEnv = facade.findPerson(requestEnvelop);
			      returnValue = processRevt(resEnv);
			      if(returnValue.isSucessFlag()){
			          HashMap map = (HashMap) resEnv.getBody();
			          ArrayList list = (ArrayList) map.get("beo");
					  ClassHelper.copyProperties(list.get(0),pf);
					  
			      }else{
			          String[] errorMsg = StringUtil.getAsStringArray(returnValue
			                        .getMsg(), "|");
			                request.setAttribute("errorMsg", errorMsg[3]);
			                super.saveSuccessfulMsg(request, errorMsg[3]);
			                return mapping.findForward("backspace");
			      }
			      
			    }
			    catch (AppException ae) {
			      saveErrors(request, ae);
			      return mapping.findForward("backspace");
			    }
			    catch (Exception e) {
			      saveErrors(request, e);
			      return mapping.findForward("backspace");
			    }
			
			return af;		
	    }
		
		
		public ActionForward queryUniteOtherPerson(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception
	    {
	        UnitePersonForm pf = (UnitePersonForm) form;
			PersonMergeDTO person = new PersonMergeDTO();
			ActionForward af = new ActionForward();    
			
			try{
	        ClassHelper.copyProperties(pf, person);
			
	        String forward = "/basicinfo/uniteotherperson.jsp";
	        
	       
	        person.setFileKey("bas02_028");		
	        String hql = queryEnterprise(person);
	        af = super.init(request, forward, hql,"2");
	        if (null == request.getAttribute(GlobalNames.QUERY_DATA))
	        {
	           super.saveSuccessfulMsg(request,"û�ҵ�����Ա��Ϣ!");
	        }
		 }catch (Exception e) {
		        super.saveErrors(request, e);
		        return mapping.findForward("backspace");
		    }
	        return af;
	    }
		
		
		/**
		 * �ϲ���Ա
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��Ա������Ϣҳ��
	     * @throws	��ѯ����
		 */
		public ActionForward saveUnitePerson(ActionMapping mapping,
				ActionForm actionForm, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ActionForward af = new ActionForward();
			af = mapping.findForward("uniteperson");
			UnitePersonForm pf = (UnitePersonForm) actionForm;

			PersonMergeDTO dto = new PersonMergeDTO(); // ʵ��
			ClassHelper.copyProperties(pf, dto);

			try {
				
				RequestEnvelop requestEnvelop = new RequestEnvelop();
				EventResponse returnValue = new EventResponse();
				HashMap mapRequest = new HashMap();
				mapRequest.put("beo", dto);
				requestEnvelop.setBody(mapRequest);
				PersonFacade facade = (PersonFacade) getService("PersonFacade");
				// ���ö�Ӧ��Facadeҵ������
				ResponseEnvelop resEnv = facade.callPro(requestEnvelop);
				// �����ؽ��
				returnValue = processRevt(resEnv);
				if (returnValue.isSucessFlag()) {
					super.saveSuccessfulMsg(request, "��Ա�ϲ��ɹ�!");
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
							"|");
					super.saveSuccessfulMsg(request, aa[3]);				
			        return mapping.findForward("backspace");
				}

			} catch (Exception e) {
				super.saveErrors(request, e);
				return mapping.findForward("backspace");
			}
	    
			return go2Page(request, mapping, "basicinfo", "1");
		}
		
		/**
		 * ��Ա�ϲ�  ��֤
	     * @param	mapping		��ӳ����
	     * @param	actionForm	������ʵ��
	     * @param	request	    �ͻ��˵�HTTP��������
	     * @param	response   	�ͻ��˵�HTTP��������
	     * 
	     * @return	��Ա������Ϣҳ��
	     * @throws	��ѯ����
		 */
		public ActionForward validateUnitePerson(ActionMapping mapping,
				ActionForm form, HttpServletRequest req,
				HttpServletResponse response) throws Exception {

			UnitePersonForm pf = (UnitePersonForm) form;
			PersonMergeDTO dto = new PersonMergeDTO(); // ʵ��
			ClassHelper.copyProperties(pf, dto);
			
			String newaac001 = dto.getNewaac001();
			String oldaac001 = dto.getOldaac001();
			
			StringBuffer sb = new StringBuffer("");		
			
			if (newaac001 == null || oldaac001 == null || newaac001.equals("")
					|| "".equals(oldaac001)) {
				dto.setResult("alert('�������δ�õ���Ա����,�����²�ѯ!');v=false;");
				sb.append(dto.getResult()).append("$");
			} else {
				dto.setResult("v=true;");		
				String retstr = "v=true;$";

				try {
					HashMap mapRequest = new HashMap();
					mapRequest.put("beo", dto);				
					
					RequestEnvelop requestEnvelop = new RequestEnvelop();
					requestEnvelop.setBody(mapRequest);
					PersonFacade facade = (PersonFacade) getService("PersonFacade");
					ResponseEnvelop resEnv = facade.validateUnitePerson(requestEnvelop);			
					EventResponse returnValue = new EventResponse();
					// �����ؽ��
					returnValue = processRevt(resEnv);
					if (returnValue.isSucessFlag()) {
						
						retstr = (String) ((HashMap) resEnv.getBody()).get("retstr");					

					} else {
						dto.setResult("alert('�������δ֪����');v=false;");
					}
				} catch (Exception e) {
					dto.setResult("alert('�������δ֪����');v=false;");
				}
				sb.append(dto.getResult()).append("$")
				  .append(retstr);
			}

			ActionUtil.handleProxyRequest(response, sb.toString());
			return null;
		}
		
}
