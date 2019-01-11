package org.radf.apps.product.action;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.business.facade.BusinessFacade;
import org.radf.apps.business.form.BusinessForm;
import org.radf.apps.client.group.facade.GroupClientFacade;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.client.single.form.SingleClientForm;
import org.radf.apps.commons.entity.Business;
import org.radf.apps.commons.entity.Configuration;
import org.radf.apps.commons.entity.Discount;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderDetailForm;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.apps.product.form.ConfigurationForm;
import org.radf.apps.product.form.ProductForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

/**
 * 面板配置信息
 */
public class ConfigurationAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public ConfigurationAction() {
	}

	/**
	 * 查询配置基本信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = "/product/configuration/query.jsp";
		String fileKey = "pdt05_003";
		ActionForward af = new ActionForward();
		ConfigurationForm configurationForm = (ConfigurationForm) form;
		Configuration configuration = new Configuration();
		try {
			ClassHelper.copyProperties(configurationForm, configuration);
			configuration.setFileKey(fileKey);
			String hql = queryEnterprise(configuration);
			af = super.init(req, forward, hql);
			
			
			
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	/**
	 * 新增面板配置信息
	 */
	public ActionForward saveConfiguration(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ConfigurationForm configurationForm = (ConfigurationForm) form;
		Configuration configuration = new Configuration();
		try {
			ClassHelper.copyProperties(configurationForm, configuration);
			if (configuration.getCfgpnlnm() == null || "".equals(configuration.getCfgpnlnm())) {
				super.saveSuccessfulMsg(req, "请正确录入面板型号");
				return mapping.findForward("backspace");
			}
			
			ProductFacade productFacade = (ProductFacade) getService("ProductFacade");

			//configuration.setCfgacyid("100000");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", configuration);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = productFacade.save2(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				
				String cfgpnlnm = (String) ((HashMap) resEnv.getBody())
						.get("cfgpnlnm");
				FindLog.insertLog(req, cfgpnlnm, "新增面板配置成功！");
				
				Configuration configuration1 = (Configuration) ((HashMap) resEnv.getBody()).get("beo");
				ClassHelper.copyProperties(configuration1, configurationForm);
				
				// 下一个页面，填写面板配置详细信息
				return mapping.findForward("inputConfigurationDetail");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	

	/**
	 * 批量录入面板配置详细信息
	 */
	public ActionForward inputDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ConfigurationForm configurationForm = (ConfigurationForm) form;
		Configuration configuration = new Configuration();
		ActionForward af = new ActionForward();
		String forward = "/product/configuration/inputDetail.jsp";
		try {
			ClassHelper.copyProperties(configurationForm, configuration);
			configuration.setFileKey("pdt05_001");
			String hql = queryEnterprise(configuration);
			af = super.init(req, forward, hql,0);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	/**
	 * 批量新增面板配置信息
	 */
	public ActionForward batchSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String cfgpnlnm = req.getParameter("cfgpnlnm");
		
		//req.getSession().setAttribute("cfgpnlnm", cfgpnlnm);
		ConfigurationForm cf = (ConfigurationForm)form;
		
		SubmitDataMap data = new SubmitDataMap(req);
		try {

			String[] acyidList = data.getParameterValues("pdtid");//零件代码,若在inputDetail.jsp页面加上hidden后，acyidList.length的值为原来的两倍
			/*String[] acypdtidList = data.getParameterValues("acypdtid");//零件货号
*/			String[] acypdtnmList = data.getParameterValues("pdtnm");//零件名称
			String[] acytypList = data.getParameterValues("pdtmod");//零件型号
			String[] cfgntList = data.getParameterValues("cfgnt");//备注
			
			int size = acyidList.length;
			
			List<Configuration> configList = new Vector<Configuration>();
			for (int i = 0; i < size; i++) {
				Configuration configuration = new Configuration();
				configuration.setCfgpnlnm(cfgpnlnm);
				configuration.setCfgacyid(acyidList[i]);
				configuration.setCfgnt(cfgntList[i]);

				configuration.setAcyid(acyidList[i]);
				//configuration.setAcypdtid(acypdtidList[i]);
				configuration.setAcypdtnm(acypdtnmList[i]);
				configuration.setAcytyp(acytypList[i]);
				
				configList.add(configuration);
			}
			ProductFacade productFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", configList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = productFacade.saveDetail(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "新增面板零件配置信息成功!");
				// 获得从业务层返回的日志信息
				
				
				return mapping.findForward("inputConfigurationDetail");
				
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}
	
	
	/**
	 * 修改面板配置信息
	 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ConfigurationForm configurationForm = (ConfigurationForm)form;
		Configuration configuration = new Configuration();
		ActionForward af = new ActionForward();
		String pnlnm = req.getParameter("pnlnm");
		String forward = "/product/configuration/modify.jsp"; // 面板配置信息修改界面
		try {
			configurationForm.setPnlnm(pnlnm);
			ClassHelper.copyProperties(configurationForm, configuration);
			configuration.setFileKey("pdt05_004");
			
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", configuration);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.query(requestEnvelop);
			//req.getSession().setAttribute("configuration", configuration);
			
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				//super.saveSuccessfulMsg(req, "查询商品价格成功!");
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
//				if (list.size() > 1)
//					throw new Exception();
				ClassHelper.copyProperties(list, form);
//				double price = pdt.getPdtprc();
//				res.setCharacterEncoding("GBK");
//				res.getWriter().write("[{price:" + price + "}]");
				
			}
			
			String hql = queryEnterprise(configuration);

			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	/**
	 * 删除面板型号
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pnlnm = req.getParameter("pnlnm");
		Configuration configuration = new Configuration();
		ConfigurationForm configurationForm = (ConfigurationForm)form;
		if (null == pnlnm || "".equalsIgnoreCase(pnlnm)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			ClassHelper.copyProperties(configurationForm, configuration);
			ProductFacade facade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Configuration> mapRequest = new HashMap<String,Configuration>();
			mapRequest.put("beo", configuration);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.delete2(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "删除面板配置信息成功!");
				FindLog.insertLog(req, pnlnm, "删除面板配置信息");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		}
		return go2Page(req, mapping, "product");
	}

	
	/**
	 * 批量保存更改后的面板配置信息
	 */
	@SuppressWarnings("unchecked")
	public ActionForward batchSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String[] chk = req.getParameterValues("chk");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] idList = data.getParameterValues("pdtid"); // 零件代码
		String[] tempList = data.getParameterValues("cfgtemperature"); // 温度
	
		//String[] ntList = data.getParameterValues("cfgnt");//配置备注
		String pnlnm = req.getParameter("pnlnm");
		List<Configuration> dtoList = new LinkedList<Configuration>();

  	    ConfigurationForm configForm = (ConfigurationForm)form;
  	   
  	   
  	    //cfig.setCfgacyid(configForm.getCfgacyid());
  	    
		//Collection<Configuration> collection = null;
		//Collection<Configuration> checked = new LinkedList();
		try {
			//collection = TypeCast.getEntities(new SubmitDataMap(req),Configuration.class);
			
			for (int j = 0; j < chk.length; j++) {
				if(null == tempList[j] || "".equals(tempList[j])){
					tempList[j]="0";
				}
				
			}
			
			for (int j = 0; j < chk.length; j++) {
				//int i = 1;
				//for (Configuration dto : collection) {
					//if (i == Integer.parseInt(chk[j])) {
				 Configuration cfig=new Configuration();
				 cfig.setPdtid(idList[Integer.parseInt(chk[j])-1]);
				 cfig.setCfgtemperature(Integer.parseInt(tempList[j]));
				 dtoList.add(cfig);
					//}
					//i++;
				//}
			}
			LoginDTO dto1 = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			ProductFacade productFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("dtoList", dtoList);
			mapRequest.put("pnlnm", pnlnm);
			mapRequest.put("idList", idList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = productFacade.batchSave(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "批量修改信息成功!");
				
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
			return mapping.findForward("query");
		} catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}

	
	
	/**
	 * 根据耳模型号获得耳模售价
	 */
	public ActionForward queryParts(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {
//			pdt.setPdtclid(req.getParameter("PclId"));pdtcls
//			pdt.setPdtcls(req.getParameter("pdtcls"));
//			String proType=req.getParameter("proType");
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("pdt", pdt);
//			mapRequest.put("proType", proType);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.queryParts(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询商品价格成功!");
//				Collection<Product> collection = (Collection<Product>) map
//						.get("collection");
				HashMap<String,Object> mapResponse = (HashMap) resEnv.getBody();
				List<Product> proList=null;
				 for(Map.Entry<String, Object> entry:mapResponse.entrySet()){  
					  proList=(List<Product>)entry.getValue();
					    }
//				List proList=(ArrayList)mapResponse.get("pdt");
				res.setCharacterEncoding("GBK");
				String jsonStr="[";
				for(Product pro:proList)
				{
					jsonStr+="{proid:'"+pro.getPdtid()+"',proname:'" + pro.getPdtnm() + "',promod:'"+pro.getPdtmod()+"'},";
				}
				jsonStr=jsonStr.substring(0, jsonStr.length()-1);
				jsonStr+="]";
				res.getWriter().write(jsonStr);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}

}
