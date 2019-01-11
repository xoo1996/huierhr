package org.radf.apps.order.action;

import java.beans.Statement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
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
import org.radf.apps.commons.entity.Business;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.DisExamine;
import org.radf.apps.commons.entity.EarMould;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * 订单管理
 */
public class OrderAction extends ActionLeafSupport {

	/**
	 * 查询跳转
	 */
	public ActionForward enter(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String menuId = req.getParameter("menuId");
		String forward = menuId;
		OrderHeaderForm header = new OrderHeaderForm();
		header.setStart(DateUtil.getDate());
		header.setEnd(DateUtil.getDate());
		ClassHelper.copyProperties(header, form);
		return mapping.findForward(forward);
	}

	/**
	 * 保存订单基本信息
	 */
	public ActionForward saveOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		try {
			ClassHelper.copyProperties(orderForm, order);
			if (order.getFolctid() == null || "".equals(order.getFolctid())) {
				super.saveSuccessfulMsg(req, "请正确录入团体客户");
				return mapping.findForward("backspace");
			}
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			order.setFolopr(dto.getBsc011());
			order.setFoldt(DateUtil.getDate());
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", order);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = orderFacade.save(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("folno");

				FindLog.insertLog(req, folno, "新增订单基本信息成功！");

				Order order1 = (Order) ((HashMap) resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
				ClassHelper.copyProperties(order1, orderForm);
				// 下一个页面，填写订单详细信息
				return mapping.findForward("inputOrderDetail");
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
	 * 删除订单(包括明细)
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		String grCli = "";
		try {
			ClassHelper.copyProperties(orderForm, order);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			grCli = dto.getBsc001();
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", order);
			mapRequest.put("grCli", grCli);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = orderFacade.delete(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap<String, Object> mapResponse = (HashMap) resEnv
						.getBody();
				if (null != mapResponse) {
					for (Map.Entry<String, Object> entry : mapResponse
							.entrySet()) {
						ClassHelper.copyProperties(entry.getValue(), orderForm);
					}
					if (orderForm.getTp2().equals("false")) {
						super.saveSuccessfulMsg(req, "该订单已提交,无法修改、删除！");
						return mapping.findForward("query");
					}
				}
				super.saveSuccessfulMsg(req, "删除订单成功!");
				FindLog.insertLog(req, orderForm.getFolctid(), "删除订单");
				return go2Page(req, mapping, "order");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 查询订单基本信息
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order or = new Order();
		Charge chg = new Charge();
		String hql = null;
		String order = req.getParameter("order");
		String tp = req.getParameter("tp");
		String forward = null;
		String fileKey = null;

		try {

			ClassHelper.copyProperties(orderForm, or);
			ClassHelper.copyProperties(orderForm, chg);
			if ("del".equals(order)) {
				fileKey = "ord02_000";
				forward = "/order/del.jsp";
			} else if ("delivery".equals(order)) { // 为了发货而查询
				forward = "/order/query1.jsp";
				fileKey = "ord02_001";
			} else if ("nomRecExa".equals(order)) {
				fileKey = "chg04_003";
				forward = "/order/nomRecExa.jsp";
			} else if ("cusRecExa".equals(order)) {
				fileKey = "chg04_014";
				forward = "/order/cusRecExa.jsp";
			} else {
				if (tp.equals("q")) {
					fileKey = "ord02_004";
					forward = "/order/query.jsp";
				} else if (tp.equals("e")) {
					fileKey = "ord02_008";
					forward = "/order/examine.jsp";
				} else if (tp.equals("disExa")) {
					or.setTdeisexa("1");
					fileKey = "ord12_004";
					forward = "/order/disExa.jsp";
				}

			}

			// LoginDTO dto =
			// (LoginDTO)req.getSession().getAttribute("LoginDTO");

			// or.setIctgctid(or.getFolctid());
			// if(!"1501000000".equals(dto.getBsc001())){
			// or.setIctgctid(dto.getBsc011());//直属店则将所示团体客户代码置为登录账号
			// }
			if ("nomRecExa".equals(order) || "cusRecExa".equals(order)) {
				if ("nomRecExa".equals(order)) {
					chg.setFileKey(fileKey);
					hql = queryEnterprise(chg);
				} else if ("cusRecExa".equals(order)) {
					or.setFileKey(fileKey);
					hql = queryEnterprise(or);
				}
				if ((null != orderForm.getFoldtty() && !"".equals(orderForm
						.getFoldtty()))
						&& (null != orderForm.getStart() && !""
								.equals(orderForm.getStart()))) {
					hql += " and " + orderForm.getFoldtty() + ">=to_date('"
							+ orderForm.getStart() + "','yyyy-MM-DD')";
				}
				if ((null != orderForm.getFoldtty() && !"".equals(orderForm
						.getFoldtty()))
						&& (null != orderForm.getEnd() && !"".equals(orderForm
								.getEnd()))) {
					hql += " and " + orderForm.getFoldtty() + "<=to_date('"
							+ orderForm.getEnd() + "','yyyy-MM-DD')";
				}
			} else {
				or.setFileKey(fileKey);

				hql = queryEnterprise(or);
			}
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

	public ActionForward queryDisExa(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// String order = req.getParameter("order");
		// String tp = req.getParameter("tp");
		String forward = "/order/disExa.jsp";
		String fileKey = "disexamine_select";
		// if ("del".equals(order)) {
		// fileKey = "ord02_000";
		// forward = "/order/del.jsp";
		// } else if ("delivery".equals(order)) { // 为了发货而查询
		// forward = "/order/query1.jsp";
		// fileKey = "ord02_001";
		// } else {
		// if(tp.equals("q"))
		// {
		// fileKey = "ord02_004";
		// forward = "/order/query.jsp";
		// }
		// else if(tp.equals("e"))
		// {
		// fileKey = "ord02_008";
		// forward = "/order/examine.jsp";
		// }
		// else if(tp.equals("disExa"))
		// {
		// fileKey = "ord12_004";
		// forward = "/order/disExa.jsp";
		// }
		// }
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		// Order or = new Order();
		DisExamine die = new DisExamine();
		try {
			// LoginDTO dto =
			// (LoginDTO)req.getSession().getAttribute("LoginDTO");

			ClassHelper.copyProperties(orderForm, die);
			// or.setIctgctid(or.getFolctid());
			// if(!"1501000000".equals(dto.getBsc001())){
			// or.setIctgctid(dto.getBsc011());//直属店则将所示团体客户代码置为登录账号
			// }

			die.setFileKey(fileKey);
			String hql = queryEnterprise(die);
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
	 * 批量录入订单详细信息
	 */
	public ActionForward inputDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ActionForward af = new ActionForward();

		String forward = null;
		forward = "/order/inputDetail.jsp";
		try {
			ClassHelper.copyProperties(orderForm, order);
			order.setFileKey("ord01_001");
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 新增订单时，订单详细信息
	 */
	public ActionForward orderDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ResponseEnvelop resEnv = null;
		ActionForward af = new ActionForward();
		String grCli = "";
		try {
			String fileKey = "";
			String tp2 = req.getParameter("tp2");
			String forward = "";
			ClassHelper.copyProperties(orderForm, order);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			grCli = dto.getBsc001();
			if (tp2.equals("e") && order.getFolsta().equals("pass")) {
				super.saveSuccessfulMsg(req, "该订单已通过审核,无法再审核！");
				return mapping.findForward("examine");
			}
			// order.setFolindctid(orderForm.getIctid());
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", order);
			mapRequest.put("grCli", grCli);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (order.getFoltype().equals("make")) {
				resEnv = facade.printCustomDetail(requestEnvelop);
			} else if (order.getFoltype().equals("repair")) {
				resEnv = facade.printCusRep(requestEnvelop);
			} else if (order.getFoltype().equals("makeEar")) {
				resEnv = facade.printEarDetail(requestEnvelop);
			} else if (order.getFoltype().equals("repairEar")) {
				resEnv = facade.printEarRep(requestEnvelop);
			} else if (order.getFoltype().equals("makeRec")) {
				resEnv = facade.printCusRec(requestEnvelop);
			} else if (order.getFoltype().equals("normal")) {
				// if(order.getFolsta().equals("commited")||order.getFolsta().equals("pass"))
				if (!"1501000000".equals(grCli)) {

					if (!(order.getFolsta().equals("uncommited") || order
							.getFolsta().equals("back"))
							&& !tp2.equals("s") && !tp2.equals("e")) {

						super.saveSuccessfulMsg(req, "该订单已提交且没有被回退,无法修改、删除！");
						return mapping.findForward("query");
					}
				}
				// resEnv = facade.printNomDetail(requestEnvelop);

				fileKey = "ord11_025";
				if (tp2.equals("s")) {
					forward = "/order/seeNomDetail.jsp";
				} else if (tp2.equals("m")) {
					forward = "/order/modifyNom.jsp";
				} else if (tp2.equals("e")) {
					forward = "/order/nomDetail.jsp";
				}

			}
			// 处理返回结果

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap<String, Object> mapResponse = null;
				if (!order.getFoltype().equals("normal")) {
					mapResponse = (HashMap) resEnv.getBody();
				}
				// // Object dg = (Object) mapResponse.get("beo3");// 诊断信息
				// List dg = (ArrayList) mapResponse.get("beo3");// 诊断信息
				// List ord = (ArrayList) mapResponse.get("beo4");// 订单明细信息
				// List od = (ArrayList) mapResponse.get("beo5");// 订单信息
				if (order.getFoltype().equals("make")) {
					for (Map.Entry<String, Object> entry : mapResponse
							.entrySet()) {
						if (entry.getValue() == null) {
						} else {
							ClassHelper.copyProperties(entry.getValue(),
									orderForm);
						}
					}
				}
				// if(null!=orderForm.getTp2()&&!"".equals(orderForm.getTp2())&&orderForm.getTp2().equals("false"))
				// {

				if (!order.getFoltype().equals("normal")) {
					if (null != mapResponse.get("beo6")) {
						super.saveSuccessfulMsg(req,
								"该订单中的商品扣率正等待总部审核或已提交且没有被回退,无法修改、删除！");
						return mapping.findForward("query");
					}
					if (order.getFoltype().equals("make")) {
						/*
						 * if(tp2.equals("m")&&(order.getFolsta().equals("making"
						 * )
						 * ||order.getFolsta().equals("qcing")||order.getFolsta(
						 * )
						 * .equals("waiting")||order.getFolsta().equals("finish"
						 * )
						 * ||order.getFolsta().equals("charged")||order.getFolsta
						 * (
						 * ).equals("recback")||order.getFolsta().equals("recoiled"
						 * )||order.getFolsta().equals("recoiledhead")||order.
						 * getFolsta().equals("recpass"))) {
						 * super.saveSuccessfulMsg(req, "定制订单开始生产后,无法修改！");
						 * return mapping.findForward("query"); }
						 */
						if (tp2.equals("m")
								&& (order.getFolsta().equals("finish")
										|| order.getFolsta().equals("charged")
										|| order.getFolsta().equals("recback")
										|| order.getFolsta().equals("recoiled")
										|| order.getFolsta().equals(
												"recoiledhead") || order
										.getFolsta().equals("recpass"))) {
							super.saveSuccessfulMsg(req, "定制订单发货后,无法修改！");
							return mapping.findForward("query");
						}
					}
				}

				if (order.getFoltype().equals("make")
						|| order.getFoltype().equals("repair")
						|| order.getFoltype().equals("makeEar")
						|| order.getFoltype().equals("repairEar")) {
					List ci = (ArrayList) mapResponse.get("beo1");// 个人客户信息
					if (null != ci) {
						ClassHelper.copyProperties(ci.get(0), orderForm);
					}
					List agList = (ArrayList) mapResponse.get("beo2");// 听力图信息
					if (null != agList) {
						ClassHelper.copyProperties(agList.get(0), orderForm);
					}
					if (order.getFoltype().equals("repair")) {
						List repair = (ArrayList) mapResponse.get("beo3");// 
						if (null != repair) {
							ClassHelper
									.copyProperties(repair.get(0), orderForm);
						}
					}
					req.getSession()
							.setAttribute("ictid", orderForm.getIctid());
				}
				if (order.getFoltype().equals("makeEar")
						|| order.getFoltype().equals("repairEar")) {
					List od = (ArrayList) mapResponse.get("beo5");//	
					if (null != od) {
						ClassHelper.copyProperties(od.get(0), orderForm);
					}
				}
				if (order.getFoltype().equals("makeRec")) {
					List od = (ArrayList) mapResponse.get("beo1");
					ClassHelper.copyProperties(od.get(0), orderForm);
				}
				if (order.getFoltype().equals("normal")) {
					Order or = new Order();
					try {
						ClassHelper.copyProperties(orderForm, or);

						or.setFileKey(fileKey);
						String hql = queryEnterprise(or);
						af = super.init(req, forward, hql);
						// 检查是否存在？
						if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
							String msg = "没有查询到符合条件的记录！";
							super.saveSuccessfulMsg(req, msg);
						}
						ClassHelper.copyProperties(or, form); // 将申请单位，订货日期回传给mofiryEarRepair.jsp
					} catch (AppException ex) {
						this.saveErrors(req, ex);
					} catch (Exception e) {
						this.saveErrors(req, e);
					}
					return af;
				}
				if (tp2.equals("s")) {
					if (order.getFoltype().equals("make")) {
						// forward="customDetail";
						forward = "seeCustomDetail";
					} else if (order.getFoltype().equals("repair")) {
						// forward="cusRepDetail";
						forward = "seeCusRepDetail";
					} else if (order.getFoltype().equals("makeEar")) {
						// forward="cusRepDetail";
						forward = "seeEarDetail";
					} else if (order.getFoltype().equals("repairEar")) {
						forward = "seeEarRepair";
					} else if (order.getFoltype().equals("normal")) {
						forward = "seeNomDetail";
					}

				} else if (tp2.equals("m")) {
					if (order.getFoltype().equals("make")) {
						forward = "modifyCustom";
					} else if (order.getFoltype().equals("repair")) {
						forward = "modifyCusRep";
					} else if (order.getFoltype().equals("makeEar")) {
						// forward="cusRepDetail";
						forward = "modifyEar";
					} else if (order.getFoltype().equals("repairEar")) {
						forward = "modifyEarRepair";
					} else if (order.getFoltype().equals("normal")) {
						forward = "modifyNom";
					}
				}

				else if (tp2.equals("e")) {
					if (order.getFoltype().equals("make")) {
						forward = "customDetail";
					} else if (order.getFoltype().equals("repair")) {
						// forward="cusRepDetail";
						forward = "examineRepair";
					} else if (order.getFoltype().equals("makeEar")) {
						// forward="cusRepDetail";
						forward = "earDetail";
					} else if (order.getFoltype().equals("repairEar")) {
						forward = "earRepairDetail";
					} else if (order.getFoltype().equals("normal")) {
						forward = "nomDetail";
					} else if (order.getFoltype().equals("makeRec")) {
						forward = "cusRecDetail";
					}
				}
				return mapping.findForward(forward);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}

	}

	public ActionForward printClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String ictid = req.getParameter("ictid");
		String tp = req.getParameter("tp");
		String repid = req.getParameter("repid");
		String tmepid = req.getParameter("tmepid");
		String forward = "";
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		SingleClient sc = new SingleClient();
		sc.setIctid(ictid);
		if (null == ictid || "".equalsIgnoreCase(ictid)) {
			saveSuccessfulMsg(req, "主键为空，请重新查询");
		} else {
			// ClassHelper.copyProperties(scForm, sc);
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			// mapRequest.put("repid", repid);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = facade.printClient(requestEnvelop);
			// 处理返回结果

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// 个人客户信息
				// List listci1 = (ArrayList) mapResponse.get("beo1");// 门诊信息
				List listci2 = (ArrayList) mapResponse.get("beo2");// 听力图信息
				// String foldt = (String) mapResponse.get("foldt");// 听力图信息
				ClassHelper.copyProperties(listci.get(0), orderForm);
				ClassHelper.copyProperties(listci2.get(0), orderForm);
				// SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
				// sdf.parse(foldt);
				// if(!"".equals(foldt))
				// {
				// foldt=foldt.substring(0, 10);
				// orderForm.setRepcusdt(foldt);
				// }
				req.getSession().setAttribute("repid", repid);
				req.getSession().setAttribute("tmepid", tmepid);
				req.getSession().setAttribute("ictid", ictid);
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
			}
		}
		if (tp.equals("rep")) {
			forward = "confirmClientCusRep";
		} else if (tp.equals("cus")) {
			forward = "confirmClientCus";
		} else if (tp.equals("ear")) {
			forward = "confirmClientEar";
		} else if (tp.equals("earRep")) {
			forward = "confirmClientEarRep";
		}

		return mapping.findForward(forward);
	}

	/**
	 * 查询订单详细信息
	 */
	public ActionForward queryDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ActionForward af = new ActionForward();
		String forward = "/order/customDetail.jsp"; // 查看订单明细
		try {
			ClassHelper.copyProperties(orderForm, order);
			order.setFileKey("ord11_000");
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 修改订单详细信息
	 */
	/*
	 * public ActionForward editDetail(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest req, HttpServletResponse res) throws Exception {
	 * String folno = req.getParameter("folno"); String gctnm =
	 * req.getParameter("gctnm"); String oprnm = req.getParameter("bsc012");
	 * OrderHeaderForm orderForm = (OrderHeaderForm) form; OrderDetail od = new
	 * OrderDetail(); ActionForward af = new ActionForward();
	 * 
	 * od.setFdtfno(folno); orderForm.setFolctnm(gctnm); String forward =
	 * "/order/editDetail.jsp"; // 修改订单明细 try { od.setFileKey("ord01_001");
	 * String hql = queryEnterprise(od); af = super.init(req, forward, hql); }
	 * catch (AppException ex) { this.saveErrors(req, ex); } catch (Exception e)
	 * { this.saveErrors(req, e); } return af; }
	 */

	/**
	 * 供应部进入发货界面
	 */
	public ActionForward enterDelivery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ActionForward af = new ActionForward();
		String forward = "/order/delivery.jsp"; // 发货界面
		try {
			ClassHelper.copyProperties(orderForm, order);
			order.setFileKey("ord01_001");
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 修改订单基本信息(发货)
	 */
	/*
	 * public ActionForward modifyOrder(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest req, HttpServletResponse res) throws Exception {
	 * SubmitDataMap data = new SubmitDataMap(req); OrderHeaderForm orderForm =
	 * (OrderHeaderForm) form; Order order = new Order(); try { String[] cltList
	 * = data.getParameterValues("fdtcltid"); // 病人代码 String[] pidList =
	 * data.getParameterValues("fdtpid"); // 商品(耳机)代码 String[] numList =
	 * data.getParameterValues("fdtsqnt"); // 发货数量 int size = pidList.length;
	 * List<OrderDetail> odList = new Vector<OrderDetail>(); for (int i = 0; i <
	 * size; i++) { OrderDetail od = new OrderDetail();
	 * od.setFdtfno(orderForm.getFolno()); od.setFdtcltid(cltList[i]);
	 * od.setFdtpid(pidList[i]); od.setFdtsqnt(Integer.parseInt(numList[i]));
	 * odList.add(od); } ClassHelper.copyProperties(orderForm, order); LoginDTO
	 * dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
	 * order.setFolsopr(dto.getBsc011());// 发货员
	 * order.setFolsdt(DateUtil.getDate());// 发货时间 OrderFacade orderFacade =
	 * (OrderFacade) getService("OrderFacade");
	 * 
	 * RequestEnvelop requestEnvelop = new RequestEnvelop(); EventResponse
	 * returnValue = new EventResponse(); // 将Application对象放入HashMap
	 * HashMap<String, Object> mapRequest = new HashMap<String, Object>();
	 * mapRequest.put("beo", order); mapRequest.put("beo1", odList);
	 * requestEnvelop.setBody(mapRequest); // 调用对应的Facade业务处理方法 ResponseEnvelop
	 * resEnv = orderFacade.modifyDetail(requestEnvelop); // 处理返回结果 returnValue
	 * = processRevt(resEnv); if (returnValue.isSucessFlag()) {
	 * super.saveSuccessfulMsg(req, "发货成功!"); // 获得从业务层返回的日志信息 // Order order1 =
	 * (Order) ((HashMap) // resEnv.getBody()).get("beo"); //
	 * req.getSession().setAttribute("list", order1); //
	 * ClassHelper.copyProperties(order1, orderForm); return
	 * mapping.findForward("delivery"); } else { String[] aa =
	 * StringUtil.getAsStringArray(returnValue.getMsg(), "|");
	 * super.saveSuccessfulMsg(req, aa[3]); return
	 * mapping.findForward("backspace"); } } catch (Exception e) {
	 * super.saveErrors(req, e); return mapping.findForward("backspace"); } }
	 */

	/**
	 * 查询订单明细
	 */
	public ActionForward queryAllDetail(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String require = request.getParameter("require");

		// 获取页面查询条件
		ActionForward af = new ActionForward();
		OrderHeaderForm fm = (OrderHeaderForm) actionForm;
		Order od = new Order();
		String filekey = null;
		String forward = null;
		Connection conn = null;
		if ("delivery".equals(require)) {
			filekey = "ord02_003";
			forward = "/order/edit.jsp";
		} else if ("check".equals(require)) {
			filekey = "ord02_002";
			forward = "/order/check.jsp";
		}
		else if ("checkdetail".equals(require)) {
			filekey= "ord02_010";
			forward="/order/checkdetail.jsp";
		}
		try {
			// 处理页面数据
			ClassHelper.copyProperties(fm, od);
			if (od.getEnd() != null && !"".equals(od.getEnd())) {
				Calendar c = Calendar.getInstance();
				c.setTime(od.getEnd());
				c.add(Calendar.DAY_OF_MONTH, 1);
				od.setEnd(c.getTime());
			}
			od.setFileKey(filekey);
			String hql = queryEnterprise(od);
			af = init(request, forward, hql);

			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(request, msg);
			}
			 if ("check".equals(require)) {
			// 计算数量合计
				String[] str = hql.split("to_date");
				String start = str[1].substring(0, 47);
				String end = str[2].substring(0, 47);
				String total = "0";
				String deliveryTotal = "0";
				conn = DBUtil.getConnection();
				java.sql.Statement pstmt = conn.createStatement();
				String sql = "select sum(fdtqnt) as total,sum(fdtsqnt) as deliveryTotal from (select t.* ,rownum as raa from ( select d.*,h.*,o.bsc012 as folsoprnm,g.gctnm,p.pdtnm as pdtnm,p.pdtid as pdtid from tblfoldetail d left outer join tblfolio h on h.folno=d.fdtfno left outer join SC05 o on o.bsc011=h.folsopr left outer join tblgrpclient g on g.gctid=h.folctid left outer join tblproduct p on p.pdtid=d.fdtpid where h.folsta='finish' and h.folsdt>=to_date"
						+ start
						+ " and h.folctid like '%"
						+ od.getFolctid()
						+ "%' and h.folsdt<=to_date"
						+ end
						+ " and d.fdtpid like '%"
						+ od.getPdtid()
						+ "%' and h.foltype like '%"
						+ od.getFoltype()
						+ "%' order by  h.folsdt) t ) where raa >= 1";
				ResultSet rst = pstmt.executeQuery(sql);
				while (rst.next()) {
					total = rst.getString("total");
					deliveryTotal = rst.getString("deliveryTotal");
				}
				if (total == null) {
					total = "0";
				}
				if (deliveryTotal == null) {
					deliveryTotal = "0";
				}
				request.getSession().setAttribute("total", total);
				request.getSession().setAttribute("deliveryTotal", deliveryTotal);
				rst.close();
				pstmt.close();
			 }

		} catch (AppException e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		} finally {
			DBUtil.closeConnection(conn);
		}
		return af;
	}

	/**
	 * 销帐
	 */
	public ActionForward xiaozhang(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward af = new ActionForward();
		OrderHeaderForm fm = (OrderHeaderForm) actionForm;
		Order dto = new Order();
		try {
			ClassHelper.copyProperties(fm, dto);
			String forward = "/order/queryxz.jsp";
			String queryhql = "";// 分页组件查询
			queryhql = "select d.*,h.*,c.ictnm,p.pdtnm from tblfoldetail d left outer join tblfolio h on h.folno = d.fdtfno left outer join tblindclient c on c.ictid = d.fdtcltid left outer join tblproduct p on p.pdtid = d.fdtpid where h.folsta = 'finish' and h.folctid = '"
					+ fm.getFolctid()
					+ "' and "
					+ "foldt between to_date('"
					+ fm.getStart()
					+ "','yyyy-mm-dd') and to_date('"
					+ fm.getEnd() + "','yyyy-mm-dd')";
			af = init(request, forward, queryhql, "1");
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(request, msg);
			}
		} catch (AppException e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 有病人明细的订单每日出货统计（根据计划发货日期）
	 */
	public ActionForward queryStat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取页面查询条件
		ActionForward af = new ActionForward();
		OrderHeaderForm fm = (OrderHeaderForm) actionForm;
		OrderDetail dto = new OrderDetail();

		// ArrayList statls = new ArrayList();
		try {

			String forward = "/order/stat.jsp";

			String queryhql = "";// 分页组件查询

			queryhql = "select count(*) temp01, "
					+ "count(decode(h.folsta,'finish','1','charged','1')) temp02, "// 第一个'2'表示订单状态已发货,temp02为实际完成数
					+ "count(*)-count(decode(h.folsta,'finish','1','charged','1')) temp03, "// 第一个'0'表示订单状态未发货,temp03为未完成数
					+ "trunc(count(decode(h.folsta,'finish','1','charged','1'))*100/decode(count(*),0,1,count(*)))||'%' temp04 "
					+ "from tblfolio h left outer join tblfoldetail d on h.folno=d.fdtfno where h.folpdt=to_date('"
					+ fm.getFolpdt()
					+ "','yyyy-MM-dd') and d.fdtcltnm is not null";

			af = init(request, forward, queryhql, "1");
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的信息！";
				super.saveSuccessfulMsg(request, msg);
			}

		} catch (AppException e) {
			saveErrors(request, e);
			return mapping.findForward("backspace");
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
		return af;
	}

	/**
	 * 供应部打印订单
	 */
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String type = req.getParameter("type");
		OrderHeaderForm header = (OrderHeaderForm) form;
		if (!(header.getFoltype().equals("normal"))) {
			super.saveSuccessfulMsg(req, "该订单不是普通订单，不能打印！");

			return mapping.findForward("backspace");
		}

		Connection conn = null;
		try {
			File reportFile = null;
			// 报表编译之后生成的.jasper 文件的存放位置
			if ("jiewen".equals(type)) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath(
								"\\WEB-INF\\report\\report3_1_before.jasper"));
			} else {
				reportFile = new File(
						req.getSession().getServletContext().getRealPath(
								"\\WEB-INF\\report\\report3_before.jasper"));
			}
			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("fno", header.getFolno());
			// 连接到数据库
			conn = DBUtil.getConnection();

			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			// res.setContentLength(bytes.length);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 生成出库送货单
	 */
	public ActionForward print1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm header = (OrderHeaderForm) form;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\report3.jasper"));

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型
			parameters.put("code", header.getFolctid());
			parameters.put("date", header.getFolsdt());
			parameters.put("way", header.getFolway());
			parameters.put("sno", header.getFolsno());

			// 连接到数据库
			conn = DBUtil.getConnection();

			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 退机
	 */
	public ActionForward recoil(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderHeaderForm orForm = (OrderHeaderForm) form;
		OrderDetail or = new OrderDetail();
		try {
			if (orForm.getFdtqnt() < 0) {
				super.saveSuccessfulMsg(request, "这已经是退机订单!");
				return mapping.findForward("backspace");
			}
			ClassHelper.copyProperties(orForm, or);
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");
			or.setFdtcltnm(dto1.getBsc011());
			or.setFdtfno(orForm.getFolno());
			OrderFacade czFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, OrderDetail> mapRequest = new HashMap<String, OrderDetail>();
			mapRequest.put("beo", or);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = czFacade.recoil(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "退机成功!");
				return go2Page(request, mapping, "order");
			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(request, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (Exception e) {
			super.saveErrors(request, e);
			return mapping.findForward("backspace");
		}
	}

	/**
	 * 总部查询订单基本信息（退机页面）
	 */
	public ActionForward requery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = "/order/recoil.jsp";
		String fileKey = null;
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order or = new Order();
		String tmksid = orderForm.getSid();
		String cltnm = orderForm.getCltnm();
		if (!("".equals(tmksid) || tmksid == null)
				|| !(cltnm == null || "".equals(cltnm))) {
			fileKey = "ord05_001";
		} else {
			fileKey = "ord05_000"; // 查询要退机的订单
		}
		try {
			ClassHelper.copyProperties(orderForm, or);
			or.setFileKey(fileKey);
			String hql = queryEnterprise(or);
			System.out.println(hql);
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
	 * 直属店查询退机订单基本信息
	 */
	public ActionForward requery1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String forward = "/charge/recoilquery.jsp";
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order or = new Order();
		// String tmksid = orderForm.getSid();
		// String cltnm = orderForm.getCltnm();

		try {
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");

			ClassHelper.copyProperties(orderForm, or);
			or.setFolctid(dto.getBsc011());
			or.setFileKey("ord05_002");
			String hql = queryEnterprise(or);
			System.out.println(hql);
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
	 * 显示维修记录历史
	 */
	public ActionForward queryHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pdtid = req.getParameter("pdtid");
		String pdtnm = req.getParameter("pdtnm");

		String forward = "/order/cusRepHistory.jsp";
		String fileKey = "ord02_005";
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;

		Order or = new Order();
		or.setPdtid(pdtid);
		or.setPdtnm(pdtnm);
		try {
			ClassHelper.copyProperties(orderForm, or);
			or.setFileKey(fileKey);
			String hql = queryEnterprise(or);

			System.out.println("repairDetail: " + hql);

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

	/*
	 * 维修订单审核
	 */
	public ActionForward examineRepair(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		String tp = req.getParameter("tp");
		Order order = new Order();
		Repair rep = new Repair();
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			ClassHelper.copyProperties(orderForm, order);
			ClassHelper.copyProperties(orderForm, rep);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String opr = dto1.getBsc011();// 填表人代码
			if (null == order.getFolno() || "".equals(order.getFolno())) {
				throw new AppException("维修审核时维修订单号(订单表)为空");
			}
			if (null == rep.getRepfolid() || "".equals(rep.getRepfolid())) {
				throw new AppException("维修审核时维修订单号（维修表）为空");
			}
			if (tp.equals("r")) {
				String sql0 = "update tblfolio l set l.folsta='back' where l.folno='"
						+ order.getFolno() + "' and l.foltype='repair'";
				System.out.println("OrderAction中examineRepair方法sql0:" + sql0);
				pstm = con.prepareStatement(sql0);
				pstm.executeUpdate();
				super.saveSuccessfulMsg(req, "已回退！");
			} else if (tp.equals("e")) {
				order.setFolsta("pass");
				order.setFolopr(opr);
				order.setFolexdt(DateUtil.getDate());
				if (null != order.getRepcpy() && order.getRepcpy().equals("惠耳")) {
					String sql1 = "update tblrep p set p.repsta='wait',p.repcpy='"
							+ rep.getRepcpy()
							+ "',p.repcls='"
							+ rep.getRepcls()
							+ "',p.reppdate=to_date('"
							+ rep.getReppdate()
							+ "','yyyy-MM-dd')"
							+ " where p.repfolid='" + rep.getRepfolid() + "'";
					System.out.println("OrderAction中examineRepair方法sql1:"
							+ sql1);
					pstm = con.prepareStatement(sql1);
					pstm.executeUpdate();
				} else {
					// tblrep状态改为厂修
					String sql2 = "update tblrep p set p.repsta='out',p.repcpy='"
							+ rep.getRepcpy()
							+ "',p.repcls='"
							+ rep.getRepcls()
							+ "',p.reppdate=to_date('"
							+ rep.getReppdate()
							+ "','yyyy-MM-dd')"
							+ " where p.repfolid='" + rep.getRepfolid() + "'";
					System.out.println("OrderAction中examineRepair方法sql2:"
							+ sql2);
					pstm = con.prepareStatement(sql2);
					pstm.executeUpdate();
				}
				String sql3 = "update tblfolio set folsta='pass',folopr='"
						+ order.getFolopr() + "',folexdt=to_date('"
						+ order.getFolexdt() + "','yyyy-MM-dd') where folno='"
						+ order.getFolno() + "' and foltype='repair'";
				System.out.println("OrderAction中examineRepair方法sql3:" + sql3);
				pstm = con.prepareStatement(sql3);
				pstm.executeUpdate();
				super.saveSuccessfulMsg(req, "审核通过！");
			}
			DBUtil.commit(con);
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("examine");
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return mapping.findForward("examine");
	}

	/*
	 * 订单审核
	 */
	public ActionForward examineOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		SubmitDataMap data = new SubmitDataMap(req);
		String[] pidList = data.getParameterValues("fdtpid");
		String[] numList = data.getParameterValues("fdtqnt");
		List<OrderDetail> odList = new ArrayList<OrderDetail>();
		String tp1 = req.getParameter("tp1");
		if (tp1.equals("e") && null == orderForm.getType()
				|| "".equals(orderForm.getType())) {
			super.saveSuccessfulMsg(req, "请选择定制类型!");
			return mapping.findForward("customDetail");
		}
		Order order = new Order();
		OrderDetail od = new OrderDetail();
		if (null != orderForm.getDgnlnm() && !"".equals(orderForm.getDgnlnm())) {
			od.setFdtmklr("0");
		} else if (null != orderForm.getDgnrnm()
				&& !"".equals(orderForm.getDgnrnm())) {
			od.setFdtmklr("1");
		}
		EarMould em = new EarMould();
		ActionForward af = new ActionForward();
		// String repfolid=req.getParameter("repfolid");
		String folType = req.getParameter("folType");
		Repair rep = new Repair();
		ResponseEnvelop resEnv = null;
		try {
			ClassHelper.copyProperties(orderForm, order);
			ClassHelper.copyProperties(orderForm, od);
			ClassHelper.copyProperties(orderForm, em);
			ClassHelper.copyProperties(orderForm, rep);
			LoginDTO dto1 = (LoginDTO) req.getSession()
					.getAttribute("LoginDTO");

			String opr = dto1.getBsc011();// 填表人代码
			// String grCli="";
			// if(!"1501000000".equals(dto1.getBsc001())){
			// grCli = dto1.getBsc011();//客户代码
			// }else{
			// grCli = orderForm.getFolctid();
			// }
			od.setFdtcltnm(orderForm.getIctnm());
			if ("cusRep".equals(folType)) {
				// order.setFolno(repfolid);
				rep.setReppdate(order.getReppdate());
				order.setFoltype("repair");
			} else if ("Ear".equals(folType)) {
				// order.setFolno(em.getTmeno());
				order.setFoltype(folType);
				od.setFdtcltid(orderForm.getIctid());
			} else if ("repairEar".equals(folType)) {
				order.setFolno(orderForm.getFolno());
				order.setFoltype(folType);
				rep.setRepdate(DateUtil.getDate());// 送修日期
				// rep.setRepoprcd(dto1.getBsc011());
				// rep.setRepnote(od.getFdtinnt());// 备注
				rep.setRepcltnm(orderForm.getIctnm());// 客户姓名
				rep.setRepcltid(orderForm.getIctid());
				rep.setRepid(od.getFdtsid());// 耳模条形码
				rep.setReppid(orderForm.getTmepid());// 耳模型号
				rep.setRepgctid(order.getFolctid());// 团体客户
				rep.setReppdate(order.getReppdate());
				// rep.setRepreger(dto1.getBsc011());// 填表人代码
				if (null != order.getRepcpy() && order.getRepcpy().equals("惠耳")) {
					rep.setRepsta("wait");
				} else {
					rep.setRepsta("out");
				}
				rep.setRepcpy(order.getRepcpy());
			} else if ("nom".equals(folType) && tp1.equals("e")) {
				order.setFoltype("nom");
				for (int i = 0; i < pidList.length; i++) {
					OrderDetail odl = new OrderDetail();
					odl.setFdtpid(pidList[i]);
					odl.setFdtqnt(Integer.parseInt(numList[i]));
					odList.add(odl);
				}

			}
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("ord", order);
			mapRequest.put("od", od);
			mapRequest.put("rep", rep);
			mapRequest.put("opr", opr);
			mapRequest.put("em", em);
			// mapRequest.put("pidList", pidList);
			mapRequest.put("type", orderForm.getType());
			if ("nom".equals(folType)) {
				mapRequest.put("odList", odList);
			}
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp1.equals("r")) {
				if (order.getFolsta().equals("pass")) {
					throw new AppException("审核通过的订单不能再回退！");
				}
				resEnv = facade.rollBack(requestEnvelop);
			} else if (tp1.equals("e")) {
				if (order.getFolno() == null || "".equals(order.getFolno())) {
					throw new AppException("订单号不能为空(请通知开发人员)！");
				}

				resEnv = facade.examine(requestEnvelop);
			}
			// 处理返回结果

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				if ("nom".equals(folType)) {
					HashMap<String, Object> mapResponse = (HashMap) resEnv
							.getBody();
					if (null != mapResponse) {
						List<OrderDetail> odlList = null;
						for (Map.Entry<String, Object> entry : mapResponse
								.entrySet()) {
							odlList = (List<OrderDetail>) entry.getValue();
						}
						String minstoeq0 = "";
						String minstoles0 = "";
						String minstoi = "";
						String minsto = "";
						String stoAmo = "";
						for (OrderDetail odl : odList) {
							if (null != odl.getMinstoeq0()
									&& !"".equals(odl.getMinstoeq0())) {
								minstoeq0 += odl.getMinstoeq0() + ",";
							}
							if (null != odl.getMinstoles0()
									&& !"".equals(odl.getMinstoles0())) {
								minstoles0 += odl.getMinstoles0() + ",";
							}
							if (null != odl.getMinstoi()
									&& !"".equals(odl.getMinstoi())) {
								minstoi += odl.getMinstoi() + ",";
							}
							if (null != odl.getMinsto()
									&& !"".equals(odl.getMinsto())) {
								minsto += odl.getMinsto() + ",";

							}
							if (null != odl.getStoAmo()
									&& !"".equals(odl.getStoAmo())) {
								stoAmo += odl.getStoAmo() + ",";
							}

						}
						String alert = "";
						if (!"".equals(stoAmo)) {
							stoAmo = stoAmo.substring(0, stoAmo.length() - 1);
						}
						if (!"".equals(minstoeq0)) {
							minstoeq0 = minstoeq0.substring(0, minstoeq0
									.length() - 1);
							alert = "第" + minstoeq0 + "行商品的现库存量为0,不能发货！";
							// throw new AppException(alert);
						}
						if (!"".equals(minstoles0)) {
							minstoles0 = minstoles0.substring(0, minstoles0
									.length() - 1);
							alert = "第" + minstoles0 + "行商品的现库存量为" + stoAmo
									+ ",订货数量大于现库存量！";
						}
						if (!"".equals(minstoi)) {
							minstoi = minstoi
									.substring(0, minstoi.length() - 1);
							minsto = minsto.substring(0, minsto.length() - 1);
							alert = "第" + minstoi
									+ "行商品发货后其库存量会低于最小库存量!\\n现最低库存量为" + minsto
									+ "，现库存余量为" + stoAmo;
						}
						// if(!"".equals(minstoeq0))
						// {
						//
						// super.saveSuccessfulMsg(req, alert);
						// String fileKey = "ord11_025";
						// String forward="nomDetail";
						// order.setFileKey(fileKey);
						// String hql = queryEnterprise(order);
						// af = super.init(req, forward, hql);
						// // 检查是否存在？
						// if (null == req.getAttribute(GlobalNames.QUERY_DATA))
						// {
						// String msg = "没有查询到符合条件的记录！";
						// super.saveSuccessfulMsg(req, msg);
						// }
						// return mapping.findForward("nomDetail");
						// }

					}
				}

				if (tp1.equals("r")) {
					super.saveSuccessfulMsg(req, "已回退！");

				} else if (tp1.equals("e")) {
					if ("Ear".equals(folType)) {
						HashMap<String, Object> mapResponse = (HashMap) resEnv
								.getBody();
						String tmesid = (String) mapResponse.get("tmesid");
						super.saveSuccessfulMsg(req, "审核通过！耳模编号为" + tmesid);
					} else {
						super.saveSuccessfulMsg(req, "审核通过！");
						if ("nom".equals(folType)) {
							Connection conn = null;
							File reportFile = null;
							// 报表编译之后生成的.jasper 文件的存放位置

							reportFile = new File(
									req
											.getSession()
											.getServletContext()
											.getRealPath(
													"\\WEB-INF\\report\\report3_before.jasper"));

							// 传递报表中用到的参数值
							Map<String, Object> parameters = new HashMap<String, Object>();
							// "Name"是报表中定义过的一个参数名称,其类型为String 型
							parameters.put("fno", orderForm.getFolno());
							// 连接到数据库
							conn = DBUtil.getConnection();

							byte[] bytes = JasperRunManager.runReportToPdf(
									reportFile.getPath(), parameters, conn);

							res.setContentType("application/pdf");
							// res.setContentLength(bytes.length);
							ServletOutputStream ouputStream = res
									.getOutputStream();
							// 自动打印
							PdfReader reader = new PdfReader(bytes);
							StringBuffer script = new StringBuffer();
							script
									.append(
											"this.print({bUI: false,bSilent: true,bShrinkToFit: true});")
									.append("\r\nthis.closeDoc();");
							ByteArrayOutputStream bos = new ByteArrayOutputStream(
									bytes.length);
							PdfStamper stamp = new PdfStamper(reader, bos);
							stamp.setViewerPreferences(PdfWriter.HideMenubar
									| PdfWriter.HideToolbar
									| PdfWriter.HideWindowUI);
							stamp.addJavaScript(script.toString());
							stamp.close();
							ouputStream.write(bos.toByteArray());
							ouputStream.flush();
							ouputStream.close();

							return null;
						}
					}
				}

			} else {
				String[] aa = StringUtil.getAsStringArray(returnValue.getMsg(),
						"|");
				super.saveErrors(req, new AppException(aa[3]));
				return mapping.findForward("backspace");
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
			String fileKey = "ord11_025";
			String forward = "nomDetail";
			order.setFileKey(fileKey);
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
			return mapping.findForward(forward);
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return mapping.findForward("examine");

	}

	/*
	 * 保存助听器维修订单
	 */
	public ActionForward examineDis(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String[] chk = req.getParameterValues("chk");
		Collection<DisExamine> collection = null;
		Collection<DisExamine> checked = new LinkedList();
		try {
			collection = TypeCast.getEntities(new SubmitDataMap(req),
					DisExamine.class);
			for (int j = 0; j < chk.length; j++) {
				int i = 1;
				for (DisExamine dto : collection) {
					if (i == Integer.parseInt(chk[j])) {
						checked.add(dto);
					}
					i++;
				}

			}
			for (DisExamine dis : checked) {
				if (dis.getTdeisexa().equals("2")) {
					throw new AppException("该特殊扣率已回退，不能再对其审核！");
				}
			}
			// DisExamine die=new DisExamine();
			// Repair rep=new Repair();
			// String tp=req.getParameter("tp");
			// String repcusdt=req.getParameter("repcusdt");
			// String grCli="";
			/*
			 * try { ClassHelper.copyProperties(orderForm, die);
			 * if(die.getTdeisexa().equals("2")) { throw new
			 * AppException("该特殊扣率已回退，不能再对其审核！"); }
			 */
			// ClassHelper.copyProperties(orderForm, dg);
			// if (order.getFolctid() == null || "".equals(order.getFolctid()))
			// {
			// super.saveSuccessfulMsg(req, "请正确录入团体客户");
			// return mapping.findForward("backspace");
			// }
			// LoginDTO dto = (LoginDTO)
			// req.getSession().getAttribute("LoginDTO");
			// if(!"1501000000".equals(dto.getBsc001())){
			// grCli = dto.getBsc011();//客户代码
			// }else{
			// grCli = orderForm.getFolctid();
			// }
			// rep.setRepgctid(dto.getBsc001());
			// rep.setRepreger(dto.getBsc011());
			// rep.setRepcltnm(orderForm.getIctnm());
			// rep.setRepcltid(orderForm.getIctid());
			// rep.setRepcusdt(Date.valueOf(repcusdt));
			// rep.setRepdate(DateUtil.getDate());// 送修日期
			// rep.setRepgctid(grCli);// 团体客户
			// rep.setRepsta("wait");
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			// mapRequest.put("die", die);
			mapRequest.put("collection", checked);
			requestEnvelop.setBody(mapRequest);
			resEnv = orderFacade.examineDis(requestEnvelop);
			// 调用对应的Facade业务处理方法
			// if(tp.equals("c"))
			// {
			// resEnv = orderFacade.examineDis(requestEnvelop);
			// }
			// else
			// {
			// resEnv = orderFacade.saveCusRep(requestEnvelop);
			// }
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");

				// FindLog.insertLog(req, folno, "新增助听器维修订单成功！");
				// if(tp.equals("c"))
				// {
				super.saveSuccessfulMsg(req, "该扣率已审核通过！");
				// }
				// else
				// {
				// super.saveSuccessfulMsg(req, "助听器维修订单录单成功！订单号：" + folno);
				// }
				return go2Page(req, mapping, "order");
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

	/*
	 * 扣率审核回退
	 */
	public ActionForward Disback(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		DisExamine die = new DisExamine();
		try {
			ClassHelper.copyProperties(orderForm, die);
			if (null != die.getTdeisexa() && !((die.getTdeisexa().equals("0")))) {
				super.saveSuccessfulMsg(req, "已经通过扣率审核的不能回退！");
				return mapping.findForward("backspace");
			} // 是否已审核扣率
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("die", die);
			requestEnvelop.setBody(mapRequest);

			resEnv = orderFacade.Disback(requestEnvelop);

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {

				super.saveSuccessfulMsg(req, "该订单扣率审核回退！");
				//                
				return go2Page(req, mapping, "order");
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

	/*
	 * 保存助听器维修订单
	 */
	public ActionForward saveCusRepOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Repair rep = new Repair();
		String tp = req.getParameter("tp");
		// String repcusdt=req.getParameter("repcusdt");
		String grCli = "";
		try {
			ClassHelper.copyProperties(orderForm, rep);
			// ClassHelper.copyProperties(orderForm, dg);
			// if (order.getFolctid() == null || "".equals(order.getFolctid()))
			// {
			// super.saveSuccessfulMsg(req, "请正确录入团体客户");
			// return mapping.findForward("backspace");
			// }
			if (orderForm.getReppid() == null
					|| "".equals(orderForm.getReppid())) {
				super.saveSuccessfulMsg(req, "商品代码为空，助听器型号请重新输入！");
				return mapping.findForward("backspace");
			}
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			if (!"1501000000".equals(dto.getBsc001())) {
				grCli = dto.getBsc011();// 客户代码
			} else {
				grCli = orderForm.getFolctid();
			}
			rep.setRepgctid(dto.getBsc001());
			rep.setRepreger(dto.getBsc011());
			rep.setRepcltnm(orderForm.getIctnm());
			rep.setRepcltid(orderForm.getIctid());
			// SimpleDateFormat sdf=new SimpleDateFormat("");

			if (null != orderForm.getRepcusdt()
					&& !"".equals(orderForm.getRepcusdt())) {
				// rep.setRepcusdt(DateUtil.convertDateToYearMonthDay(Date.valueOf(orderForm.getRepcusdt())));
				rep.setRepcusdt(Date.valueOf(orderForm.getRepcusdt()));
			}
			rep.setRepdate(DateUtil.getDate());// 送修日期
			rep.setRepgctid(grCli);// 团体客户
			// rep.setRepsta("wait");
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("rep", rep);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveCusRep(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("repfno");

				// FindLog.insertLog(req, folno, "新增助听器维修订单成功！");
				if (tp.equals("c")) {
					// throw new AppException("助听器维修订单录单并提交成功！订单号："+ folno);
					super.saveSuccessfulMsg(req, "助听器维修订单录单并提交成功！订单号：" + folno);
				} else {
					super.saveSuccessfulMsg(req, "助听器维修订单录单成功！订单号：" + folno);
				}
				return mapping.findForward("customRepairSelect");
				// return go2Page(req, mapping, "order");
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
	 * 保存新增后的耳模信息
	 */
	public ActionForward saveEar(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		String tp = req.getParameter("tp");
		ResponseEnvelop resEnv = null;
		// EarMouldForm eform = (EarMouldForm) form;
		// EarMould emould = null;
		OrderDetail detail = null;
		String grCli = "";
		String num = req.getParameter("fdtqnt");
		Integer earnum = Integer.valueOf(num);
		// List<EarMould> emList = new Vector<EarMould>();
		List<OrderDetail> detailList = new Vector<OrderDetail>();
		try {
			// if (orderForm.getTmectid() == null ||
			// "".equals(orderForm.getTmectid())) {
			// super.saveSuccessfulMsg(req, "请正确录入团体客户");
			// return mapping.findForward("backspace");
			// }
			// LoginDTO dto1 = (LoginDTO)
			// req.getSession().getAttribute("LoginDTO");

			orderForm.setTmectid(orderForm.getIctgctid());
			orderForm.setTmecltid(orderForm.getIctid());
			String emtype = orderForm.getFdtmat();
			LoginDTO currentuser = (LoginDTO) req.getSession().getAttribute(
					"LoginDTO");
			if (!"1501000000".equals(currentuser.getBsc001())) {
				grCli = currentuser.getBsc011();// 客户代码
			} else {
				grCli = orderForm.getFolctid();
			}
			// 判断耳模类别
			if (emtype.equals("0")) {
				for (int i = 0; i < earnum; i++) {
					detail = new OrderDetail();
					ClassHelper.copyProperties(orderForm, detail);
					detail.setFdtcltid(orderForm.getIctid());
					detail.setFdtcltnm(orderForm.getIctnm());
					// detail.setFdtpid(orderForm.getTmepid());
					// detail.setFdtmat(orderForm.getTmemat());
					// detail.setFdtfree(orderForm.getTmefree());
					// detail.setFdtcls(orderForm.getTmecls());
					detail.setFdtpid(orderForm.getTmepid());
					detail.setFdtqnt(1);
					detail.setFdtmklr("0");
					detail.setFdtfree(orderForm.getFdtfree());
					detail.setFdtprc(orderForm.getFdtprc());
					detail.setFdtdprc(orderForm.getFdtprc());
					detailList.add(detail);
					// detail.setFdtfno(tmefno);// 订单号
					// detail.setFdtcltid(em.getTmecltid());//个人客户代码
					// detail.setFdtcltnm(emList.get(0).getTmecltnm());// 客户姓名
					// detail.setFdtprc(price);// 单价
					// detail.setFdtdprc(price);
					// detail.setFdtpid(emList.get(0).getTmepid());
					// detail.setFdtqnt(earcount);// 订购数量
					// emould = new EarMould();
					// ClassHelper.copyProperties(orderForm, emould);
					// emould.setTmecltnm(orderForm.getIctnm());
					// emList.add(emould);

				}
			} else if (emtype.equals("1")) {
				for (int i = 0; i < earnum; i++) {
					detail = new OrderDetail();
					ClassHelper.copyProperties(orderForm, detail);
					detail.setFdtcltid(orderForm.getIctid());
					detail.setFdtcltnm(orderForm.getIctnm());
					// detail.setFdtpid(orderForm.getTmepid());
					// detail.setFdtmat(orderForm.getTmemat());
					// detail.setFdtfree(orderForm.getTmefree());
					// detail.setFdtcls(orderForm.getTmecls());
					detail.setFdtpid(orderForm.getTmepid());
					detail.setFdtqnt(1);
					detail.setFdtmklr("1");
					detail.setFdtfree(orderForm.getFdtfree());
					detail.setFdtprc(orderForm.getFdtprc());
					detail.setFdtdprc(orderForm.getFdtprc());
					detailList.add(detail);
				}
			} else if (emtype.equals("2")) {
				for (int i = 0; i < earnum; i++) {
					// 生成双耳中的左
					detail = new OrderDetail();
					ClassHelper.copyProperties(orderForm, detail);
					detail.setFdtcltid(orderForm.getIctid());
					detail.setFdtcltnm(orderForm.getIctnm());
					// detail.setFdtpid(orderForm.getTmepid());
					// detail.setFdtmat(orderForm.getTmemat());
					// detail.setFdtfree(orderForm.getTmefree());
					// detail.setFdtcls(orderForm.getTmecls());
					detail.setFdtpid(orderForm.getTmepid());
					detail.setFdtprc(orderForm.getFdtprc());
					detail.setFdtdprc(orderForm.getFdtprc());
					detail.setFdtqnt(1);
					detail.setFdtmklr("0");
					detail.setFdtmat("0");
					detailList.add(detail);
					// emould = new EarMould();
					// ClassHelper.copyProperties(orderForm, emould);
					// emould.setTmemat("0");
					// emould.setTmecltnm(orderForm.getIctnm());
					// emList.add(emould);
					// 生成双耳中的右
					// emould = new EarMould();
					// ClassHelper.copyProperties(orderForm, emould);
					// emould.setTmemat("1");
					// emould.setTmecltnm(orderForm.getIctnm());
					// emList.add(emould);
					detail = new OrderDetail();
					ClassHelper.copyProperties(orderForm, detail);
					detail.setFdtcltid(orderForm.getIctid());
					detail.setFdtcltnm(orderForm.getIctnm());
					// detail.setFdtpid(orderForm.getTmepid());
					// detail.setFdtmat(orderForm.getTmemat());
					// detail.setFdtfree(orderForm.getTmefree());
					// detail.setFdtcls(orderForm.getTmecls());
					detail.setFdtpid(orderForm.getTmepid());
					detail.setFdtprc(orderForm.getFdtprc());
					detail.setFdtdprc(orderForm.getFdtprc());
					detail.setFdtqnt(1);
					detail.setFdtmklr("1");
					detail.setFdtmat("1");
					detailList.add(detail);
				}

			}
			// earnum = 1;

			// EarmouldFacade facade = (EarmouldFacade)
			// getService("EarmouldFacade");
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", detailList);
			mapRequest.put("opr", currentuser.getBsc011());
			mapRequest.put("type", "ear");
			mapRequest.put("grCli", grCli);
			mapRequest.put("urgent", orderForm.getFolurgent());
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveEar(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String tmeno = (String) ((HashMap) resEnv.getBody())
						.get("tmeno");
				// 获得从业务层返回的日志信息
				String earworkString = (String) ((HashMap) resEnv.getBody())
						.get("earworkString");
				// FindLog.insertLog(req, tmeno, earworkString);
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "耳模录单并提交成功！订单号：" + tmeno);
				} else {
					super.saveSuccessfulMsg(req, "耳模录单成功！订单号：" + tmeno);
				}

				String dd = DateUtil.converToString(DateUtil
						.getSystemCurrentTime(), "yyyyMMddHHmmss");
				// return new ActionForward("/Index.jsp?type=" + dd);
				// return mapping.findForward("success");
				return go2Page(req, mapping, "order");
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

	/*
	 * 保存普通订单
	 */
	public ActionForward saveNomOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		// Repair rep=new Repair();
		String tp = req.getParameter("tp");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] idList = data.getParameterValues("fdtpid"); // 商品(耳机)代码
		String[] dprcList = data.getParameterValues("fdtdprc");// 价格
		// String[] discList = data.getParameterValues("fdtdisc"); // 扣率
		// String[] prcList = data.getParameterValues("fdtprc");// 价格(售价)
		String[] numList = data.getParameterValues("fdtqnt"); // 数量
		String[] ntList = data.getParameterValues("fdtinnt"); // 备注
		// Diagnose dg=new Diagnose();
		try {
			// ClassHelper.copyProperties(orderForm, rep);
			// ClassHelper.copyProperties(orderForm, dg);
			// if (order.getFolctid() == null || "".equals(order.getFolctid()))
			// {
			// super.saveSuccessfulMsg(req, "请正确录入团体客户");
			// return mapping.findForward("backspace");
			// }
			String grCli = "";
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			if (!"1501000000".equals(dto.getBsc001())) {
				grCli = dto.getBsc011();// 客户代码
			} else {
				grCli = orderForm.getFolctid();
			}
			// String gctid=dto.getBsc011();
			// rep.setRepgctid(dto.getBsc001());
			// rep.setRepreger(dto.getBsc011());
			// rep.setRepcltnm(orderForm.getIctnm());
			// rep.setRepcltid(orderForm.getIctid());
			// order.setFoldt(DateUtil.getDate());
			int size = idList.length;
			List<OrderDetail> odList = new Vector<OrderDetail>();
			for (int i = 0; i < size; i++) {
				OrderDetail od = new OrderDetail();
				// od.setFdtfno(fno);
				od.setFdtpid(idList[i]);
				od.setFdtdprc(Double.parseDouble(dprcList[i]));
				// od.setFdtdisc(Double.parseDouble(discList[i]));
				// od.setFdtprc(Double.parseDouble(dprcList[i])*Double.parseDouble(discList[i])*Double.parseDouble(numList[i]));
				od.setFdtqnt(Integer.parseInt(numList[i]));

				od.setFdtrqnt(Integer.parseInt(numList[i])); // 初始化商品的剩余数量为订货时的数量

				if (Integer.parseInt(numList[i]) < 0) {
					od.setFdtodt(DateUtil.getSystemCurrentTime());
				}
				od.setFdtsqnt(Integer.parseInt(numList[i]));
				od.setFdtinnt(ntList[i]);
				od.setFdtcltid(grCli);
				odList.add(od);
			}
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("type", "nom");
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveNom(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("folno");

				// FindLog.insertLog(req, folno, "新增普通订单成功！");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "普通订单录单并提交成功！订单号：" + folno);
				} else {
					super.saveSuccessfulMsg(req, "普通订单录单成功！订单号：" + folno);
				}
				return go2Page(req, mapping, "order");
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

	/*
	 * 保存普通订单
	 */
	public ActionForward saveDevRepOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Repair rep = new Repair();
		String tp = req.getParameter("tp");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] idList = data.getParameterValues("fdtpid"); // 商品(耳机)代码
		// String[] dprcList = data.getParameterValues("fdtdprc");// 价格
		// String[] discList = data.getParameterValues("fdtdisc"); // 扣率
		// String[] prcList = data.getParameterValues("fdtprc");// 价格(售价)
		String[] numList = data.getParameterValues("fdtqnt"); // 数量
		String[] ntList = data.getParameterValues("fdtinnt"); // 备注
		// Diagnose dg=new Diagnose();
		try {
			ClassHelper.copyProperties(orderForm, rep);
			// ClassHelper.copyProperties(orderForm, dg);
			// if (order.getFolctid() == null || "".equals(order.getFolctid()))
			// {
			// super.saveSuccessfulMsg(req, "请正确录入团体客户");
			// return mapping.findForward("backspace");
			// }
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			String gctid = dto.getBsc011();
			// rep.setRepgctid(dto.getBsc001());
			// rep.setRepreger(dto.getBsc011());
			// rep.setRepcltnm(orderForm.getIctnm());
			// rep.setRepcltid(orderForm.getIctid());
			// order.setFoldt(DateUtil.getDate());
			int size = idList.length;
			List<OrderDetail> odList = new Vector<OrderDetail>();
			for (int i = 0; i < size; i++) {
				OrderDetail od = new OrderDetail();
				// od.setFdtfno(fno);
				od.setFdtpid(idList[i]);
				// od.setFdtdprc(Double.parseDouble(dprcList[i]));
				// od.setFdtdisc(Double.parseDouble(discList[i]));
				// od.setFdtprc(Double.parseDouble(dprcList[i])*Double.parseDouble(discList[i])*Double.parseDouble(numList[i]));
				od.setFdtqnt(Integer.parseInt(numList[i]));

				od.setFdtrqnt(Integer.parseInt(numList[i])); // 初始化商品的剩余数量为订货时的数量

				if (Integer.parseInt(numList[i]) < 0) {
					od.setFdtodt(DateUtil.getSystemCurrentTime());
				}
				od.setFdtsqnt(Integer.parseInt(numList[i]));
				od.setFdtinnt(ntList[i]);
				// od.setFolctid(gctid);
				odList.add(od);
			}
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("rep", rep);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveDevRep(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("folno");

				// FindLog.insertLog(req, folno, "新增普通订单成功！");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "普通订单录单并提交成功！订单号：" + folno);
				} else {
					super.saveSuccessfulMsg(req, "普通订单录单成功！订单号：" + folno);
				}
				return go2Page(req, mapping, "order");
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
	 * 保存新增的耳模维修信息
	 */
	public ActionForward saveEarRep(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tp = (String) request.getParameter("tp");
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		// EarMouldForm eform = (EarMouldForm) form;
		// EarMould emould = new EarMould();
		// Repair rep = new Repair();
		// String num = request.getParameter("fdtqnt");
		try {
			LoginDTO dto1 = (LoginDTO) request.getSession().getAttribute(
					"LoginDTO");

			String opr = dto1.getBsc011();// 填表人代码

			// 插入tblearmaking表用到的数据
			// ClassHelper.copyProperties(orderForm, emould);
			// emould.setTmecltid(orderForm.getIctid());//个人客户代码
			// emould.setTmecls("repair");//制作类别
			// emould.setTmesta("2");//耳模状态，2表示等待质检
			// emould.setTmecltnm(orderForm.getIctnm());//个人客户姓名

			// rep.setRepdate(DateUtil.getDate());// 送修日期
			// rep.setRepoprcd(dto1.getBsc011());
			// rep.setRepnote(orderForm.getTment());// 备注
			// rep.setRepcltnm(orderForm.getIctnm());// 客户姓名
			// rep.setRepid(orderForm.getTmesid());// 耳模条形码
			// rep.setReppid(orderForm.getTmepid());// 耳模型号
			// rep.setRepgctid(orderForm.getIctgctid());// 团体客户
			// rep.setReppdate(orderForm.getTmepdt());//计划完工日期
			// rep.setRepreger(dto1.getBsc011());//填表人代码
			// rep.setRepsta("wait");
			// rep.setRepcpy("惠耳");

			// repList.add(rep);
			String grCli = "";
			if (!"1501000000".equals(dto1.getBsc001())) {
				grCli = dto1.getBsc011();// 客户代码
			} else {
				grCli = orderForm.getFolctid();
			}
			OrderDetail detail = new OrderDetail();
			ClassHelper.copyProperties(orderForm, detail);
			detail.setFdtcltid(orderForm.getIctid());
			detail.setFdtcltnm(orderForm.getIctnm());
			detail.setFdtpid(orderForm.getTmepid());
			// detail.setFdtmat(orderForm.getTmemat());
			// detail.setFdtfree(orderForm.getTmefree());
			// detail.setFdtcls(orderForm.getTmecls());
			detail.setFdtcls("repair");
			detail.setFdtqnt(1);
			detail.setFdtmklr(orderForm.getFdtmat());

			// EarmouldFacade facade = (EarmouldFacade)
			// getService("EarmouldFacade");
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("od", detail);
			mapRequest.put("type", "earRep");
			mapRequest.put("grCli", grCli);
			mapRequest.put("opr", opr);
			// 将HashMap对象放入requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			// ResponseEnvelop resEnv = facade.saveRepair(requestEnvelop);
			ResponseEnvelop resEnv = null;
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveEarRep(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// HashMap<String,Object>
				// mapResponse=((HashMap)resEnv.getBody()).toString();
				String repfno = (String) ((HashMap) resEnv.getBody())
						.toString();
				repfno = repfno.substring(repfno.indexOf("=") + 1, repfno
						.indexOf("}"));
				// List repfno = (ArrayList) mapResponse.get("repfno");//
				if (tp.equals("b")) {
					super.saveSuccessfulMsg(request, "保存耳模维修记录成功,订单号为" + repfno
							+ "!");
					return go2Page(request, mapping, "order");
					// return mapping.findForward("backspace");
				} else {
					super.saveSuccessfulMsg(request, "保存并提交耳模维修记录成功,订单号为"
							+ repfno + "!");
					return go2Page(request, mapping, "order");
					// return mapping.findForward("backspace");
				}

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
	}

	/**
	 * 根据耳模型号获得耳模售价
	 */
	public ActionForward queryEMPro(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {
			pdt.setPdtid(req.getParameter("EarId"));
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
				super.saveSuccessfulMsg(req, "查询商品价格成功!");
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("beo");
				if (list.size() > 1)
					throw new Exception();
				ClassHelper.copyProperties(list.get(0), pdt);
				double price = pdt.getPdtprc();
				res.setCharacterEncoding("GBK");
				res.getWriter().write("[{price:" + price + "}]");
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}

	/**
	 * 根据团体客户、商品代码获得最小扣率
	 */
	public ActionForward queryDis(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {

			// pdt.setPdtid(req.getParameter("EarId"));
			String grid = req.getParameter("Grid");// 团体客户id
			String lpid = req.getParameter("Lpid");
			String rpid = req.getParameter("Rpid");

			String folno = req.getParameter("Folno");
			String discount = req.getParameter("Discount");
			// String dgnldct=req.getParameter("dgnldct");
			// String dgnrdct=req.getParameter("dgnrdct");
			// Double discount=null;
			// if(null!=dgnldct&&!"".equals(dgnldct))
			// {
			// discount=Double.parseDouble(dgnldct);
			// }
			// else
			// {
			// discount=Double.parseDouble(dgnrdct);
			// }
			String type = null;
			Connection con = null;
			try {

				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				String sql = "select t.GCTTYPE from TBLGRPCLIENT t where t.GCTID='"
						+ grid + "'";
				List result = (Vector) DBUtil.querySQL(con, sql);

				if (result.size() > 0) {
					// pamnt = Double.parseDouble(((HashMap)
					// result.get(0)).get("1").toString());
					type = ((HashMap) result.get(0)).get("1").toString();
				}
				System.out.println(type);
				DBUtil.commit(con);
			} catch (Exception ex) {

			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}

			List<OrderDetail> odList = new ArrayList<OrderDetail>();
			if ((null != lpid && !"".equals(lpid))
					&& (null == rpid || "".equals(rpid))) {
				OrderDetail od = new OrderDetail();
				od.setFdtpid(lpid);
				od.setFdtmklr("0");
				odList.add(od);
			} else if ((null != rpid && !"".equals(rpid))
					&& (null == lpid || "".equals(lpid))) {
				OrderDetail od = new OrderDetail();
				od.setFdtpid(rpid);
				od.setFdtmklr("1");
				odList.add(od);
			} else if ((null != rpid && !"".equals(rpid))
					&& (null != lpid && !"".equals(lpid))) {
				OrderDetail od1 = new OrderDetail();
				od1.setFdtpid(lpid);
				od1.setFdtmklr("0");
				odList.add(od1);
				OrderDetail od2 = new OrderDetail();
				od2.setFdtpid(rpid);
				od2.setFdtmklr("1");
				odList.add(od2);
			}
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("grid", grid);
			mapRequest.put("odList", odList);
			mapRequest.put("folno", folno);
			if (null != discount && !"".equals(discount)) {
				mapRequest.put("discount", discount);
			}
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = orderFacade.queryMinDis(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (!type.equals("A")) {
				String jsonStr = "{tdspvo:''}";
				res.getWriter().write(jsonStr);
				return null;

			} else {
				if (returnValue.isSucessFlag()) {

					HashMap<String, Object> mapResponse = (HashMap)resEnv.getBody();
					String alert = (String) mapResponse.get("alert");
					String alert1 = (String) mapResponse.get("alert1");
					String alert2 = (String) mapResponse.get("alert2");
					String alert3 = (String) mapResponse.get("alert3");

					if (null != alert && !"".equals(alert)) {
						super.saveSuccessfulMsg(req, alert);
						return null;
					} else if (null != alert1 && !"".equals(alert1)) {
						String jsonStr = "{tdspvo:''}";
						res.getWriter().write(jsonStr);
						return null;
					} else if (null != alert2 && !"".equals(alert2)) {
						alert2 = alert2.substring(alert2.indexOf("!") + 1,
								alert2.length());
						Double tdspvo = Double.parseDouble(alert2);
						String jsonStr = "[{tdspvo:" + tdspvo + ",lr:'6'}]"; // 再次申请的扣率小于总部已审核的扣率
						res.getWriter().write(jsonStr);
						return null;
					} else if (null != alert3 && !"".equals(alert3)) {
						// throw new AppException("该特殊扣率已被总部回退，比其小的扣率也不能提交！");
						String jsonStr = "[{lr:'7'}]";
						res.getWriter().write(jsonStr);
						return null;
					}
					List<OrderDetail> odlList = null;
					for (Map.Entry<String, Object> entry : mapResponse
							.entrySet()) {
						odlList = (List<OrderDetail>) entry.getValue();
					}
					res.setCharacterEncoding("GBK");
					String jsonStr = "[";
					// for(OrderDetail od:odlList)
					// {
					// jsonStr+="{tdspvo:"+od.getTdspvo()+"},";
					// }

					for (OrderDetail od : odlList) {
						jsonStr += "{tdspvo:" + od.getTdspvo() + ",lr:"
								+ od.getFdtmklr() + "},";
					}
					jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
					jsonStr += "]";
					res.getWriter().write(jsonStr);
				} else {
					String[] aa = StringUtil.getAsStringArray(returnValue
							.getMsg(), "|");
					super.saveSuccessfulMsg(req, aa[3]);
					// super.saveErrors(req, e);
					// throw new AppException("该特殊扣率已被总部回退，比其小的扣率也不能提交！");
					// return mapping.findForward("modifyCustom");
					// String jsonStr="[{lr:'7'}]";
					// res.getWriter().write(jsonStr);
					return mapping.findForward("backspace");
				}
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
			return mapping.findForward("backspace");
		}
		return null;
	}

	/**
	 * 根据机身编号获得验配日期
	 */
	public ActionForward queryFoldt(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// Product pdt = new Product();
		Customization cz = new Customization();
		Order order = new Order();
		try {
			// pdt.setPdtid(req.getParameter("RepId"));
			cz.setTmksid(req.getParameter("RepId"));
			// ProductFacade pdtFacade = (ProductFacade)
			// getService("ProductFacade");
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("cz", cz);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			ResponseEnvelop resEnv = orderFacade.queryFoldt(requestEnvelop);
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "查询商品价格成功!");
				List list = (ArrayList) ((HashMap) resEnv.getBody()).get("fol");
				if (list.size() > 1)
					throw new Exception();
				ClassHelper.copyProperties(list.get(0), order);
				// double price = pdt.getPdtprc();
				Calendar cal = Calendar.getInstance();
				cal.setTime(order.getFoldt());
				cal.get(Calendar.YEAR);
				cal.get(Calendar.MONTH);
				cal.get(Calendar.DATE);
				// DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				// DateFormat df1=new SimpleDateFormat("yyyy");
				// DateFormat df2=new SimpleDateFormat("MM");
				// DateFormat df3=new SimpleDateFormat("dd");
				// res.getWriter().write("[{foldt:" + order.getFoldt() + "}]");
				// res.getWriter().write("[{foldt1:" +
				// df1.format(order.getFoldt()) + ",foldt2:" +
				// df2.format(order.getFoldt()) + ",foldt3:" +
				// df3.format(order.getFoldt()) + "}]");
				String str = "[{foldt1:" + cal.get(Calendar.YEAR) + ",foldt2:"
						+ cal.get(Calendar.MONTH) + ",foldt3:"
						+ cal.get(Calendar.DATE) + "}]";
				res.getWriter().write(str);
			}
		} catch (Exception e) {
			super.saveErrors(req, e);
		}
		return null;
	}

	/*
	 * 修改耳膜订单
	 */
	public ActionForward modifyEar(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		// Repair rep=new Repair();
		List<OrderDetail> odList = new ArrayList<OrderDetail>();
		OrderDetail od = new OrderDetail();
		// EarMould em=new EarMould();
		String tp = req.getParameter("tp");
		try {
			ClassHelper.copyProperties(orderForm, od);
			od.setFdtpid(orderForm.getTmepid());
			// od.setFdtfno(orderForm.getFolno());
			odList.add(od);
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");

			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("type", "ear");
			mapRequest.put("folno", orderForm.getFolno());
			mapRequest.put("folurgent", orderForm.getFolurgent());
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.updateEar(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");
				// FindLog.insertLog(req, folno, "修改助听器维修订单成功！");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "提交耳模订单成功！");
				} else {
					super.saveSuccessfulMsg(req, "修改耳模订单成功！");
				}
				return go2Page(req, mapping, "order");
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

	/*
	 * 修改耳膜维修订单
	 */
	public ActionForward modifyEarRep(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		// Repair rep=new Repair();
		OrderDetail od = new OrderDetail();
		// EarMould em=new EarMould();
		String tp = req.getParameter("tp");
		try {
			ClassHelper.copyProperties(orderForm, od);
			od.setFdtpid(orderForm.getTmepid());
			od.setFdtfno(orderForm.getFolno());

			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");

			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("od", od);
			// mapRequest.put("qnt", orderForm.getFdtqnt());
			// mapRequest.put("folno", orderForm.getFolno());
			// mapRequest.put("folurgent", orderForm.getFolurgent());
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.updateEarRep(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");
				// FindLog.insertLog(req, folno, "修改助听器维修订单成功！");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "提交耳模订单成功！");
				} else {
					super.saveSuccessfulMsg(req, "修改耳模订单成功！");
				}
				return go2Page(req, mapping, "order");
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

	public ActionForward modifyNom(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		// Repair rep=new Repair();
		// EarMould em=new EarMould();
		// OrderDetail od=new OrderDetail();
		String tp = req.getParameter("tp");
		SubmitDataMap data = new SubmitDataMap(req);
		// String[] ordidList = data.getParameterValues("folno"); // 订单号
		String folno = req.getParameter("folno");
		String[] idList = data.getParameterValues("fdtpid"); // 商品(耳机)代码
		String[] dprcList = data.getParameterValues("fdtdprc");// 价格(售价)
		// String[] discList = data.getParameterValues("fdtdisc"); // 商品(耳机)代码
		// String[] prcList = data.getParameterValues("fdtprc");// 价格(售价)
		String[] numList = data.getParameterValues("fdtqnt"); // 数量
		String[] ntList = data.getParameterValues("fdtinnt"); // 
		String chk = req.getParameter("chk");
		try {
			// ClassHelper.copyProperties(orderForm, od);
			// LoginDTO dto = (LoginDTO)
			// req.getSession().getAttribute("LoginDTO");
			// rep.setRepgctid(dto.getBsc001());
			// rep.setRepreger(dto.getBsc011());
			// rep.setRepcltnm(orderForm.getIctnm());
			// rep.setRepcltid(orderForm.getIctid());
			// order.setFoldt(DateUtil.getDate());
			int size = idList.length;
			List<OrderDetail> odList = new Vector<OrderDetail>();
			for (int i = 0; i < size; i++) {
				OrderDetail od = new OrderDetail();
				// od.setOldpid(orderForm.getOldpid()[i]);
				od.setFdtfno(folno);
				od.setFdtpid(idList[i]);
				od.setFdtdprc(Double.parseDouble(dprcList[i]));
				// od.setFdtdisc(Double.parseDouble(discList[i]));
				// od.setFdtprc(Double.parseDouble(dprcList[i])*Double.parseDouble(discList[i])*Double.parseDouble(numList[i]));
				od.setFdtqnt(Integer.parseInt(numList[i]));

				od.setFdtrqnt(Integer.parseInt(numList[i])); // 初始化商品的剩余数量为订货时的数量

				if (Integer.parseInt(numList[i]) < 0) {
					od.setFdtodt(DateUtil.getSystemCurrentTime());
				}
				od.setFdtsqnt(Integer.parseInt(numList[i]));
				od.setFdtinnt(ntList[i]);
				od.setFileKey("ord11_026");
				odList.add(od);
			}
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// 将Application对象放入HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("type", "nom");
			mapRequest.put("chk", chk);
			requestEnvelop.setBody(mapRequest);
			// 调用对应的Facade业务处理方法
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.updateNom(requestEnvelop);
			}
			// 处理返回结果
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "新增订单基本信息成功!");
				// 获得新增的订单ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");
				// FindLog.insertLog(req, folno, "修改助听器维修订单成功！");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "提交普通订单成功！");
				} else {
					super.saveSuccessfulMsg(req, "修改普通订单成功！");
				}
				// return go2Page(req, mapping, "order");
				return mapping.findForward("query");
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

	/*
	 * 普通订单输入界面跳出
	 */
	public ActionForward inDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// InStoreForm storageForm = (InStoreForm ) form;
		// Store sto=new Store();
		Order ord = new Order();
		ActionForward af = new ActionForward();
		String forward = "/order/inputNomOrder.jsp";
		try {
			// ClassHelper.copyProperties(storageForm, sto);
			ord.setFolno("-1");
			ord.setFileKey("ord11_022");
			// sto.setStoid(-1);
			// sto.setFileKey("ord11_022");
			String hql = queryEnterprise(ord);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/*
	 * 普通订单输入界面跳出
	 */
	public ActionForward inDetailDevRep(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// InStoreForm storageForm = (InStoreForm ) form;
		// Store sto=new Store();
		Order ord = new Order();
		ActionForward af = new ActionForward();
		String forward = "/order/inputDevRepOrder.jsp";
		try {
			// ClassHelper.copyProperties(storageForm, sto);
			ord.setFolno("-1");
			ord.setFileKey("ord11_022");
			// sto.setStoid(-1);
			// sto.setFileKey("ord11_022");
			String hql = queryEnterprise(ord);
			af = super.init(req, forward, hql);
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

	/**
	 * 根据耳模型号获得耳模售价
	 */
	/*
	 * public ActionForward queryClass(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest req, HttpServletResponse res) throws Exception { //
	 * Product pdt = new Product(); Pro pro=new Pro(); try { //
	 * pdt.setPdtid(req.getParameter("EarId")); ProductFacade pdtFacade =
	 * (ProductFacade) getService("ProductFacade"); RequestEnvelop
	 * requestEnvelop = new RequestEnvelop(); EventResponse returnValue = new
	 * EventResponse(); // 将Application对象放入HashMap HashMap<String, Object>
	 * mapRequest = new HashMap<String, Object>(); // mapRequest.put("beo",
	 * pdt); requestEnvelop.setBody(mapRequest); // 调用对应的Facade业务处理方法
	 * ResponseEnvelop resEnv = pdtFacade.queryLarge(requestEnvelop); // 处理返回结果
	 * returnValue = processRevt(resEnv); if (returnValue.isSucessFlag()) {
	 * super.saveSuccessfulMsg(req, "查询商品价格成功!"); List list = (ArrayList)
	 * ((HashMap) resEnv.getBody()).get("beo"); if (list.size() > 1) throw new
	 * Exception(); ClassHelper.copyProperties(list.get(0), pro); String
	 * large=pro.getProlarge(); // double price = pdt.getPdtprc();
	 * res.setCharacterEncoding("GBK"); res.getWriter().write("[{large:" + large
	 * + "}]"); } } catch (Exception e) { super.saveErrors(req, e); } return
	 * null; }
	 */

	/**
	 * 生成定制机条形码
	 */
	public ActionForward barcode(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) actionForm;
		Order order = new Order();
		// ClassHelper.copyProperties(actionForm, order);
		ActionForward af = new ActionForward();
		if (!(orderForm.getFoltype().equals("make") && orderForm.getFolsta()
				.equals("pass"))) {
			super.saveSuccessfulMsg(req, "该订单不是已审核的定制订单，不能打印条形码！");
			String fileKey = "ord02_008";
			String forward = "examine";
			order.setFileKey(fileKey);
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			// if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			// String msg = "没有查询到符合条件的记录！";
			// super.saveSuccessfulMsg(req, msg);
			return actionMapping.findForward(forward);
		}
		// CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\barcode.jasper"));

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型

			// 连接到数据库
			conn = DBUtil.getConnection();
			// System.out.println(cf.getTmkfno());
			// parameters.put("tmkfno", cf.getTmkfno());
			parameters.put("tmkfno", orderForm.getFolno());

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			// res.setContentType("application/pdf");
			// res.setContentLength(bytes.length);
			// ServletOutputStream ouputStream = res.getOutputStream();
			// ouputStream.write(bytes, 0, bytes.length);
			// ouputStream.flush();
			// ouputStream.close();

			res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 生成定制机条形码
	 */
	public ActionForward cusOrderReport(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) actionForm;
		Order order = new Order();
		ClassHelper.copyProperties(actionForm, order);
		ActionForward af = new ActionForward();
		String tp = req.getParameter("tp");
		String forward = null;
		String fileKey = null;
		if (!(orderForm.getFoltype().equals("make"))) {
			super.saveSuccessfulMsg(req, "该订单不是定制订单，不能打印！");

			if (tp.equals("q")) {
				fileKey = "ord02_004";
				forward = "query";
			} else if (tp.equals("e")) {
				fileKey = "ord02_008";
				forward = "examine";
			}
			order.setFileKey(fileKey);
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			// if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			// String msg = "没有查询到符合条件的记录！";
			// super.saveSuccessfulMsg(req, msg);
			return actionMapping.findForward(forward);
		}
		Connection conn = null;
		try {
			// 报表编译之后生成的.jasper 文件的存放位置
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("/WEB-INF/report/order_cusOrder.jasper"));
			String webRootPath = req.getSession().getServletContext()
					.getRealPath("/");

			// 传递报表中用到的参数值
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"是报表中定义过的一个参数名称,其类型为String 型

			// 连接到数据库
			conn = DBUtil.getConnection();
			// System.out.println(cf.getTmkfno());
			parameters.put("folno", orderForm.getFolno());
			parameters.put("ictid", orderForm.getFolindctid());
			parameters.put("SUBREPORT_DIR", webRootPath + "WEB-INF\\report\\");

			// 在控制台显示一下报表文件的物理路径
			System.out.println(reportFile.getPath());
			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			ServletOutputStream ouputStream = res.getOutputStream();
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return null;
	}

	/**
	 * 查询个人客户维修记录
	 */
	public ActionForward queryRep(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Repair rep = new Repair();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		ActionForward af = new ActionForward();
		String forward = "/order/queryRep.jsp";
		try {
			rep.setRepcltid(orderForm.getIctid());
			rep.setReppid(orderForm.getFdtpid());
			rep.setFileKey("rep01_011");
			String hql = queryEnterprise(rep);
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "该用户暂无维修记录！";
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
	 * 查询审核通过后订单基本信息
	 */
	public ActionForward querycount(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order or = new Order();
		Charge chg = new Charge();
		String hql = null;
		String forward = null;
		String fileKey = null;

		try {

			ClassHelper.copyProperties(orderForm, or);
			ClassHelper.copyProperties(orderForm, chg);

			fileKey = "ord02_009";
			forward = "/order/querycount.jsp";

			// if((null!=orderForm.getFoldtty()&&!"".equals(orderForm.getFoldtty()))&&(null!=orderForm.getStart()&&!"".equals(orderForm.getStart())))
			// {
			// hql+=" and "+orderForm.getFoldtty()+">=to_date('"+orderForm.getStart()+"','yyyy-MM-DD')";
			// }
			// if((null!=orderForm.getFoldtty()&&!"".equals(orderForm.getFoldtty()))&&(null!=orderForm.getEnd()&&!"".equals(orderForm.getEnd())))
			// {
			// hql+=" and "+orderForm.getFoldtty()+"<=to_date('"
			// +orderForm.getEnd()+"','yyyy-MM-DD')";
			// }

			or.setFileKey(fileKey);
			hql = queryEnterprise(or);
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
	public ActionForward bill(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ResponseEnvelop resEnv = null;
		ActionForward af = new ActionForward();
		try {
			String forward = "";
			ClassHelper.copyProperties(orderForm, order);
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", order);
			requestEnvelop.setBody(mapRequest);
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			resEnv = orderFacade.bill(requestEnvelop);
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String msg = "修改开票状态成功！";
				super.saveSuccessfulMsg(req, msg);
				return go2Page(req, mapping, "order");
				}
			else{
				return mapping.findForward("backspace");
			}
	} catch (Exception e) {
		super.saveErrors(req, e);
		return mapping.findForward("backspace");
	}
	}
	/**
	 * 数据迁移
	 */
	public ActionForward trans(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Connection con = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm2 = null;
		PreparedStatement pstm3 = null;
		PreparedStatement pstm4 = null;
		String flag = request.getParameter("flag");
		try{
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql = "insert into tblfolio@mylinke select * from tblfolio where foldt<trunc(sysdate- interval '1' year,'yyyy') and foltype='normal'";
			pstm = con.prepareStatement(sql);
			pstm.execute();

			String sql3 = "insert into tblfoldetail@mylinke select d.* from tblfoldetail d left outer join tblfolio h on h.folno=d.fdtfno where h.foldt< trunc(sysdate- interval '1' year,'yyyy') and h.foltype ='normal'";
			pstm3 = con.prepareStatement(sql3);
			pstm3.execute();
			
			String sql4 = "delete from tblfoldetail where fdtfno in (select folno from tblfolio where foldt<trunc(sysdate- interval '1' year,'yyyy') and foltype='normal' )";
			pstm4 = con.prepareStatement(sql4);
			pstm4.execute();
			
			String sql2 = "delete from tblfolio where foldt<trunc(sysdate- interval '1' year,'yyyy') and foltype='normal'";
			pstm2 = con.prepareStatement(sql2);
			pstm2.execute();
			
			
			DBUtil.commit(con);
		} catch (Exception e) {
			super.saveSuccessfulMsg(request, "数据迁移失败！");
			return mapping.findForward("backspace");
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
			super.saveSuccessfulMsg(request, "数据迁移成功！");
			return mapping.findForward("backspace");
		}
	/**
	 * 查询订单基本信息
	 */
	public ActionForward queryHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		ActionForward af = new ActionForward();
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order or = new Order();
		Charge chg = new Charge();
		String hql = null;
		String forward = null;
		String fileKey = null;

		try {

			ClassHelper.copyProperties(orderForm, or);
			ClassHelper.copyProperties(orderForm, chg);
				fileKey = "ord02_004_his";
				forward = "/order/queryHis.jsp";
				or.setFileKey(fileKey);
				hql = queryEnterprise(or);

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
}



