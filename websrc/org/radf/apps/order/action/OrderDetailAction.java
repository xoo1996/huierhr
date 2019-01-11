package org.radf.apps.order.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderDetailForm;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 订单管理
 */
public class OrderDetailAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public OrderDetailAction() {
	}

	/**
	 * 供应部发货后查询发货详情
	 */
	public ActionForward queryDeliveried(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		OrderDetailForm odForm = (OrderDetailForm) form;
		OrderDetail od = new OrderDetail();
		ActionForward af = new ActionForward();
		String forward = "/order/deliveried.jsp";
		try {
			ClassHelper.copyProperties(odForm, od);
			od.setFileKey("ord01_002");
			String hql = queryEnterprise(od);
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
	 * 通过商品ID查询商品名称和价格
	 */
	public ActionForward queryPdtName(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderDetailForm odForm = new OrderDetailForm();
		Product pdt = new Product();
		try {
			pdt.setPdtid(req.getParameter("productID"));

			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pdt);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = pdtFacade.query(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询商品名称和价格成功!");
				// 获得商品名称和价格
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list.size() > 1)
					throw new Exception();
				ClassHelper.copyProperties(list.get(0), pdt);
				String name = pdt.getPdtnm();
				double price = pdt.getPdtprc();

				res.setCharacterEncoding("GBK");
				res.getWriter().write(
						"[{productName:'" + name + "',price:" + price + "}]");
			}
		} catch (Exception e) {
			// e.printStackTrace();
			super.saveErrors(req, e);
			// res.setCharacterEncoding("GBK");
			// res.getWriter().write("商品代码输入有误");
		}
		return null;
	}

	/**
	 * 批量修改账单明细
	 */
	public ActionForward batchModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fno = req.getParameter("fdtfno");
		SubmitDataMap data = new SubmitDataMap(req);
		try {
			// String[] cltList = data.getParameterValues("fdtcltid"); // 病人代码
			String[] idList = data.getParameterValues("fdtpid"); // 商品(耳机)代码
			String[] prcList = data.getParameterValues("fdtprc");// 价格(售价)
			String[] numList = data.getParameterValues("fdtqnt"); // 数量

			int size = idList.length;
			List<OrderDetail> odList = new Vector<OrderDetail>();
			for (int i = 0; i < size; i++) {
				OrderDetail od = new OrderDetail();
				od.setFdtfno(fno);
				od.setFdtpid(idList[i]);
				od.setFdtprc(Double.parseDouble(prcList[i]));
				od.setFdtqnt(Integer.parseInt(numList[i]));
				odList.add(od);
			}
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", odList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = orderFacade.modifyDetail(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "修改订单明细成功!");
				// 获得从业务层返回的日志信息
				// String workString = (String) ((HashMap)
				// resEnv.getBody()).get("workString");
				// FindLog.insertLog(req, fno, workString);
				// Order order1 = (Order) ((HashMap)
				// resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
				// ClassHelper.copyProperties(order1, orderForm);
				// 下一个页面，填写订单详细信息
				return mapping.findForward("query");
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
	 * 批量新增账单明细
	 */
	public ActionForward batchSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fno = req.getParameter("fdtfno");
		SubmitDataMap data = new SubmitDataMap(req);
		try {
			// String[] cltList = data.getParameterValues("fdtcltid"); // 病人代码
			String[] idList = data.getParameterValues("fdtpid"); // 商品(耳机)代码
			String[] prcList = data.getParameterValues("fdtprc");// 价格(售价)
			String[] numList = data.getParameterValues("fdtqnt"); // 数量
			

			int size = idList.length;
			List<OrderDetail> odList = new Vector<OrderDetail>();
			for (int i = 0; i < size; i++) {
				OrderDetail od = new OrderDetail();
				od.setFdtfno(fno);
				od.setFdtpid(idList[i]);
				od.setFdtprc(Double.parseDouble(prcList[i]));
				od.setFdtqnt(Integer.parseInt(numList[i]));
				
				od.setFdtrqnt(Integer.parseInt(numList[i]));	//初始化商品的剩余数量为订货时的数量
				
				if(Integer.parseInt(numList[i])<0){
					od.setFdtodt(DateUtil.getSystemCurrentTime());
				}
				od.setFdtsqnt(Integer.parseInt(numList[i]));
				odList.add(od);
			}
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", odList);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = orderFacade.saveDetail(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "新增订单明细成功!");
				// 获得从业务层返回的日志信息
				return go2Page(req, mapping, "order");
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
	 * 客服部进入发货界面
	 */
	public ActionForward enterDelivery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderDetailForm odf = (OrderDetailForm) form;
		String gctnm = req.getParameter("gctnm");
		String gctid = req.getParameter("folctid");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] folstaList = data.getParameterValues("folsta"); 
		ActionForward af = new ActionForward();
		String forward = "/order/delivery1.jsp"; // 发货界面
		Collection<OrderDetail> collection = null;
		List list = new ArrayList();
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),
					OrderDetail.class);
			Boolean flag=false;
			int j=0;
//			for(int i=0;i<folstaList.length;i++)
//			{
			for(OrderDetail od:collection)
			{
				if(!folstaList[j].equals("waiting"))
				{
					throw new AppException("订单号为：" + od.getFdtfno() + "的商品不能发货,只有等待发货状态的订单才能发货!");
				}
				j++;
			}
//			for(Order order:collection)
//			{
//				if(null!=order.getFolsta()&&(!(order.getFolsta().equals("waiting")))
//				{
//					super.saveSuccessfulMsg(req, "只有等待发货状态的订单才能发货！");
//					return mapping.findForward("edit");
//				}
//			}
//			if(null!=odf.getFolsta()&&(!(odf.getFolsta().equals("waiting"))))
//		    {
//		    	super.saveSuccessfulMsg(req, "只有等待发货状态的订单才能发货！");
//				return mapping.findForward("edit");
//		    }
			// order.setFileKey("ord01_001");
			String hql = "select d.*,h.*,g.gctnm,p.pdtnm from tblfoldetail d left outer join tblfolio h on h.folno=d.fdtfno left outer join tblgrpclient g on g.gctid=h.folctid left outer join tblproduct p on p.pdtid=d.fdtpid where ";
			for (OrderDetail od : collection) {
				list.add(od.getFdtfno());
			}
			for (int i = 0; i < list.size(); i++) {
				if (i != 0) {
					hql += " or ";
				}
				hql += "d.fdtfno='";
				hql += list.get(i);
				hql += "'";
			}
			System.out.println(hql);
			odf.setFoldesnm(gctnm);
			odf.setFoldes(gctid);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			this.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	
	/**
	 * 批量发货前判断是否已发货
	 */
	public ActionForward before_batchDelivery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String require = req.getParameter("require");
		String type = req.getParameter("type");
		String way = req.getParameter("way");
		String sno = req.getParameter("sno");
		String des = req.getParameter("des");
		String folctid = req.getParameter("folctid");
		Date time = DateUtil.getDate(); //不显示时分秒//DateUtil.getSystemCurrentTime();//显示时分秒
		Collection<OrderDetail> collection = null;
		Connection con = null;
		try {
			if ("kefu".equals(require)) {
				if ("".equals(des) || des == null) {
					super.saveSuccessfulMsg(req, "请正确录入发货地点!");
					return mapping.findForward("backspace");
				}
			}
			else if("gongying".equals(require)) {
				if ("".equals(des) || des == null) {
					super.saveSuccessfulMsg(req, "请正确录入发货地点!");
					return mapping.findForward("backspace");
				}
			}
			collection = TypeCast.getEntities(new SubmitDataMap(req),OrderDetail.class);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			String folgrctid=dto1.getBsc001();

			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			List result = null;
			List fno_sta = new ArrayList();
			String folsta_str = "";
			res.setCharacterEncoding("GBK");
			String jsonStr="[";
			for (OrderDetail od : collection) {
				result = (Vector) DBUtil.querySQL(con,
						"select f.folsta from  tblfolio f where f.folno = '"
								+ od.getFdtfno() + "'");
				if(((HashMap) result.get(0)).get("1") != null){
					folsta_str =  (String)((HashMap) result.get(0)).get("1");
				}
				if("waiting".equals(folsta_str)){
					continue;
				}else {
					//fno_sta.add(Integer.parseInt(od.getFdtfno()),folsta_str);
					jsonStr += "{folno:'" + od.getFdtfno() + "',folsta:'" + folsta_str + "'},";
				}
			}  
			if(jsonStr.length()>1){
				jsonStr=jsonStr.substring(0, jsonStr.length()-1);
			}
			jsonStr+="]";
			res.getWriter().write(jsonStr);
			DBUtil.commit(con);
			
			return null;
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		} finally {
			DBUtil.rollback(con);/////////add
			DBUtil.closeConnection(con);
		}
	}
	
	/**
	 * 批量发货_仅打印
	 */
	public ActionForward batchDelivery_print(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String require = req.getParameter("require");
		String type = req.getParameter("type");
		String way = req.getParameter("way");
		String sno = req.getParameter("sno");
		String des = req.getParameter("des");
		String folctid = req.getParameter("folctid");
		Date time = DateUtil.getDate(); //不显示时分秒//DateUtil.getSystemCurrentTime();//显示时分秒
		Collection<OrderDetail> collection = null;
		Connection conn = null;
		try {
			if ("kefu".equals(require)) {
				if ("".equals(des) || des == null) {
					super.saveSuccessfulMsg(req, "请正确录入发货地点!");
					return mapping.findForward("backspace");
				}
			}
			else if("gongying".equals(require)) {
				if ("".equals(des) || des == null) {
					super.saveSuccessfulMsg(req, "请正确录入发货地点!");
					return mapping.findForward("backspace");
				}
			}
			collection = TypeCast.getEntities(new SubmitDataMap(req),OrderDetail.class);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			String folgrctid=dto1.getBsc001();
			
				String folno_str="d.fdtfno in (";
				for (OrderDetail od : collection){
					folno_str+="'"+od.getFdtfno()+"'"+",";	
				}
				if(folno_str!=null){
					folno_str=folno_str.substring(0,folno_str.length()-1);
				}
				folno_str=folno_str+")";
				
					File reportFile = null;
					if ("kefu".equals(require)) {
						if ("jiewen".equals(type)) {
							// 报表编译之后生成的.jasper 文件的存放位置
							reportFile = new File(req.getSession()
									.getServletContext().getRealPath(
											"\\WEB-INF\\report\\report3_1.jasper"));
						} else {
							reportFile = new File(req.getSession()
									.getServletContext().getRealPath(
										"\\WEB-INF\\report\\report3.jasper"));
						}
					}
					else
					{
						return mapping.findForward("deliveried");
					}
					// 传递报表中用到的参数值
					Map<String, Object> parameters = new HashMap<String, Object>();
					// "Name"是报表中定义过的一个参数名称,其类型为String 型
//					parameters.put("code", des);
//					parameters.put("date", time.toLocaleString());
					parameters.put("way", way);
					parameters.put("sno", sno);
					parameters.put("folno_str",folno_str);
					// 连接到数据库
					conn = DBUtil.getConnection();

					//DBUtil.beginTrans(conn);
					
					byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
					//DBUtil.commit(conn);///////////add
					res.setContentType("application/pdf");
					//res.setContentLength(bytes.length);
					ServletOutputStream ouputStream = res.getOutputStream();
					// 自动打印
					PdfReader reader = new PdfReader(bytes);
					StringBuffer script = new StringBuffer();
					script
							.append(
									"this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
							.append("\r\nthis.closeDoc();");
					ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
					PdfStamper stamp = new PdfStamper(reader, bos);
					stamp.setViewerPreferences(PdfWriter.HideMenubar
							| PdfWriter.HideToolbar | PdfWriter.HideWindowUI);
					stamp.addJavaScript(script.toString());
					stamp.close();
					ouputStream.write(bos.toByteArray());
					ouputStream.flush();
					ouputStream.close();
					return null;
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		} finally {
			DBUtil.rollback(conn);/////////add
			DBUtil.closeConnection(conn);
		}
	}
	
	
	/**
	 * 批量发货
	 */
	public ActionForward batchDelivery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String require = req.getParameter("require");
		String type = req.getParameter("type");
		String way = req.getParameter("way");
		String sno = req.getParameter("sno");
		String des = req.getParameter("des");
		String folctid = req.getParameter("folctid");
		Date time = DateUtil.getDate(); //不显示时分秒//DateUtil.getSystemCurrentTime();//显示时分秒
		Collection<OrderDetail> collection = null;
		Connection conn = null;
		Connection con = null;
		try {
			if ("kefu".equals(require)) {
				if ("".equals(des) || des == null) {
					super.saveSuccessfulMsg(req, "请正确录入发货地点!");
					return mapping.findForward("backspace");
				}
			}
			else if("gongying".equals(require)) {
				if ("".equals(des) || des == null) {
					super.saveSuccessfulMsg(req, "请正确录入发货地点!");
					return mapping.findForward("backspace");
				}
			}
			collection = TypeCast.getEntities(new SubmitDataMap(req),OrderDetail.class);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			String folgrctid=dto1.getBsc001();
			
			
					con = DBUtil.getConnection();
					DBUtil.beginTrans(con);
					List result = null;
					ArrayList<OrderDetail> fol_sta_list=new ArrayList();
					String folsta_str = "";
					String jsonStr="";
					for (OrderDetail od : collection) {
						result = (Vector) DBUtil.querySQL(con,
								"select f.folsta from  tblfolio f where f.folno = '"
										+ od.getFdtfno() + "'");
						if(((HashMap) result.get(0)).get("1") != null){
							folsta_str =  (String)((HashMap) result.get(0)).get("1");
						}
						if("waiting".equals(folsta_str)){
							continue;
						}else {
							fol_sta_list.add(od);
							//fol_sta.add(od);
							//jsonStr += "{folno:'" + od.getFdtfno() + "',folsta:'" + folsta_str + "'}";
						}
					}
					DBUtil.commit(con);
					if(fol_sta_list.size()>0){
						String folno_str="d.fdtfno in (";
						for (int i=0;i<fol_sta_list.size();i++){
							folno_str+="'"+fol_sta_list.get(i).getFdtfno()+"'"+",";	
						}
						if(folno_str!=null){
							folno_str=folno_str.substring(0,folno_str.length()-1);
						}
						folno_str=folno_str+")";
						
						File reportFile = null;
						if ("kefu".equals(require)) {
							if ("jiewen".equals(type)) {
								// 报表编译之后生成的.jasper 文件的存放位置
								reportFile = new File(req.getSession()
										.getServletContext().getRealPath(
												"\\WEB-INF\\report\\report3_1.jasper"));
							} else {
								reportFile = new File(req.getSession()
										.getServletContext().getRealPath(
											"\\WEB-INF\\report\\report3.jasper"));
							}
						}
						else
						{
							super.saveSuccessfulMsg(req, "该订单已经发货，不能重复发货!");
							return mapping.findForward("deliveried");
						}
						// 传递报表中用到的参数值
						Map<String, Object> parameters = new HashMap<String, Object>();
						// "Name"是报表中定义过的一个参数名称,其类型为String 型
		//				parameters.put("code", des);
		//				parameters.put("date", time.toLocaleString());
						parameters.put("way", way);
						parameters.put("sno", sno);
						parameters.put("folno_str",folno_str);
						// 连接到数据库
						conn = DBUtil.getConnection();
		
						DBUtil.beginTrans(conn);
						
						byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
						DBUtil.commit(conn);///////////add
						DBUtil.closeConnection(conn);
						res.setContentType("application/pdf");
						//res.setContentLength(bytes.length);
						ServletOutputStream ouputStream = res.getOutputStream();
						// 自动打印
						PdfReader reader = new PdfReader(bytes);
						StringBuffer script = new StringBuffer();
						script
								.append(
										"this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
								.append("\r\nthis.closeDoc();");
						ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
						PdfStamper stamp = new PdfStamper(reader, bos);
						stamp.setViewerPreferences(PdfWriter.HideMenubar
								| PdfWriter.HideToolbar | PdfWriter.HideWindowUI);
						stamp.addJavaScript(script.toString());
						stamp.setFormFlattening(true);  
						stamp.close();
						ouputStream.write(bos.toByteArray());
						ouputStream.flush();
						ouputStream.close();
						return null;
					}
			
			
			
			// 获取接口
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String,Object> mapRequest = new HashMap<String,Object>();
			mapRequest.put("collection", collection);
			mapRequest.put("opr", dto1.getBsc011());
			mapRequest.put("way", way);
			mapRequest.put("des", des);
			mapRequest.put("sno", sno);
			mapRequest.put("time", time);
			mapRequest.put("folctid", folctid);
			mapRequest.put("folgrctid", folgrctid);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.modifyDetail(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String folno_str="d.fdtfno in (";
				for (OrderDetail od : collection){
					folno_str+="'"+od.getFdtfno()+"'"+",";	
				}
				if(folno_str!=null){
					folno_str=folno_str.substring(0,folno_str.length()-1);
				}
				folno_str=folno_str+")";
				
				
					
				 
					super.saveSuccessfulMsg(req, "批量发货成功!");
					File reportFile = null;
					if ("kefu".equals(require)) {
						if ("jiewen".equals(type)) {
							// 报表编译之后生成的.jasper 文件的存放位置
							reportFile = new File(req.getSession()
									.getServletContext().getRealPath(
											"\\WEB-INF\\report\\report3_1.jasper"));
						} else {
							reportFile = new File(req.getSession()
									.getServletContext().getRealPath(
										"\\WEB-INF\\report\\report3.jasper"));
						}
					}
					else
					{
						super.saveSuccessfulMsg(req, "发货成功!");
						return mapping.findForward("deliveried");
//						if ("jiewen".equals(type)) {
//							// 报表编译之后生成的.jasper 文件的存放位置
//							reportFile = new File(req.getSession()
//									.getServletContext().getRealPath(
//											"\\WEB-INF\\report\\report3_1_normal.jasper"));
//						} else {
//							reportFile = new File(req.getSession()
//									.getServletContext().getRealPath(
//										"\\WEB-INF\\report\\report3_normal.jasper"));
//					}
					}
					// 传递报表中用到的参数值
					Map<String, Object> parameters = new HashMap<String, Object>();
					// "Name"是报表中定义过的一个参数名称,其类型为String 型
//					parameters.put("code", des);
//					parameters.put("date", time.toLocaleString());
					parameters.put("way", way);
					parameters.put("sno", sno);
					parameters.put("folno_str",folno_str);
					// 连接到数据库
					conn = DBUtil.getConnection();

					byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);

					res.setContentType("application/pdf");
					//res.setContentLength(bytes.length);
					ServletOutputStream ouputStream = res.getOutputStream();
					// 自动打印
					PdfReader reader = new PdfReader(bytes);
					StringBuffer script = new StringBuffer();
					script
							.append(
									"this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
							.append("\r\nthis.closeDoc();");
					ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes.length);
					PdfStamper stamp = new PdfStamper(reader, bos);
					stamp.setViewerPreferences(PdfWriter.HideMenubar
							| PdfWriter.HideToolbar | PdfWriter.HideWindowUI);
					stamp.addJavaScript(script.toString());
					stamp.close();
					ouputStream.write(bos.toByteArray());
					ouputStream.flush();
					ouputStream.close();
					return null;
//				} else {
//					super.saveSuccessfulMsg(req, "发货成功!");
//					return mapping.findForward("deliveried");
//				}
//				super.saveSuccessfulMsg(req, "批量发货成功!");
//				return mapping.findForward("deliveried");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveSuccessfulMsg(req, aa[3]);
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	/*
	 * 发货前检查库存余
	 */
	public ActionForward checkStoAmount(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String gctid = req.getParameter("ictgctid");		
		
		SubmitDataMap data = new SubmitDataMap(req);
		//Charge charge = new Charge();
//		ChargeForm cf = (ChargeForm) form;	
		Collection<OrderDetail> collection = null;
		try {
			
			collection = TypeCast.getEntities(new SubmitDataMap(req),OrderDetail.class);
			//String[] idList = data.getParameterValues("pdtid"); // 商品(耳机)代码
//			String[] idList =req.getParameterValues("pdtid");
//			String[] sqntList = data.getParameterValues("fdtsqnt");// 商品扣率
//			int size = idList.length;
//			List<OrderDetail> odList = new ArrayList<OrderDetail>();		
//			for (int i = 0; i < idList.length; i++) {
////				Charge charge = new Charge();
////				charge.setStogrcliid(gctid);
////				charge.setNcdpid(idList[i]);
////				charge.setNcddis(Double.parseDouble(disList[i]));
////				chgList.add(charge);
//				OrderDetail od=new OrderDetail();
//				od.setFdtpid(idList[i]);
//				od.setFdtsqnt(Integer.parseInt(sqntList[i]));
//				odList.add(od);
////				
//			}
			
			
//			ChargeFacade facade = (ChargeFacade) getService("ChargeFacade");
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", collection);
//			mapRequest.put("beo", chgList);
//			mapRequest.put("req", req);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.checkStoAmount(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				Object body=resEnv.getBody();
				String jsonStr="";
				if(null!=body)
				{
					List<OrderDetail> list = (ArrayList<OrderDetail>)((HashMap)resEnv.getBody()).get("dto");
					res.setCharacterEncoding("GBK");
				
					
						Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().setPrettyPrinting().create();
						jsonStr=gson.toJson(list);

					}
//					String jsonStr="[";
////					boolean flag=false;
//					for(Charge chg:list)
//					{
//						if(null!=chg.getMindisi())
//						{
//							jsonStr+="{i:"+chg.getMindisi()+",mindis:"+chg.getTdspvo()+"},";
//							flag=true;
//						}
//					}
//					if(flag)
//					{
//						req.getSession().setAttribute("chgList",list);
//						jsonStr=jsonStr.substring(0, jsonStr.length()-1);
//						jsonStr+="]";
//						res.getWriter().write(jsonStr);
//						
//					}
					else
					{
						jsonStr="{tdspvo:''}";
//						res.getWriter().write(jsonStr);
					}
					res.getWriter().write(jsonStr);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			super.saveErrors(req, e);
//			return mapping.findForward("backspace");
		}
					return null;
		
	}
	
	
}
