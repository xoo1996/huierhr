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
 * ��������
 */
public class OrderAction extends ActionLeafSupport {

	/**
	 * ��ѯ��ת
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
	 * ���涩��������Ϣ
	 */
	public ActionForward saveOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		try {
			ClassHelper.copyProperties(orderForm, order);
			if (order.getFolctid() == null || "".equals(order.getFolctid())) {
				super.saveSuccessfulMsg(req, "����ȷ¼������ͻ�");
				return mapping.findForward("backspace");
			}
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			order.setFolopr(dto.getBsc011());
			order.setFoldt(DateUtil.getDate());
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", order);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = orderFacade.save(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "��������������Ϣ�ɹ�!");
				// ��������Ķ���ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("folno");

				FindLog.insertLog(req, folno, "��������������Ϣ�ɹ���");

				Order order1 = (Order) ((HashMap) resEnv.getBody()).get("beo");
				// req.getSession().setAttribute("list", order1);
				ClassHelper.copyProperties(order1, orderForm);
				// ��һ��ҳ�棬��д������ϸ��Ϣ
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
	 * ɾ������(������ϸ)
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", order);
			mapRequest.put("grCli", grCli);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = orderFacade.delete(requestEnvelop);
			// �����ؽ��
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
						super.saveSuccessfulMsg(req, "�ö������ύ,�޷��޸ġ�ɾ����");
						return mapping.findForward("query");
					}
				}
				super.saveSuccessfulMsg(req, "ɾ�������ɹ�!");
				FindLog.insertLog(req, orderForm.getFolctid(), "ɾ������");
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
	 * ��ѯ����������Ϣ
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
			} else if ("delivery".equals(order)) { // Ϊ�˷�������ѯ
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
			// or.setIctgctid(dto.getBsc011());//ֱ��������ʾ����ͻ�������Ϊ��¼�˺�
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
		// } else if ("delivery".equals(order)) { // Ϊ�˷�������ѯ
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
			// or.setIctgctid(dto.getBsc011());//ֱ��������ʾ����ͻ�������Ϊ��¼�˺�
			// }

			die.setFileKey(fileKey);
			String hql = queryEnterprise(die);
			af = super.init(req, forward, hql);
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
	 * ����¼�붩����ϸ��Ϣ
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
	 * ��������ʱ��������ϸ��Ϣ
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
				super.saveSuccessfulMsg(req, "�ö�����ͨ�����,�޷�����ˣ�");
				return mapping.findForward("examine");
			}
			// order.setFolindctid(orderForm.getIctid());
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", order);
			mapRequest.put("grCli", grCli);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
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

						super.saveSuccessfulMsg(req, "�ö������ύ��û�б�����,�޷��޸ġ�ɾ����");
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
			// �����ؽ��

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap<String, Object> mapResponse = null;
				if (!order.getFoltype().equals("normal")) {
					mapResponse = (HashMap) resEnv.getBody();
				}
				// // Object dg = (Object) mapResponse.get("beo3");// �����Ϣ
				// List dg = (ArrayList) mapResponse.get("beo3");// �����Ϣ
				// List ord = (ArrayList) mapResponse.get("beo4");// ������ϸ��Ϣ
				// List od = (ArrayList) mapResponse.get("beo5");// ������Ϣ
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
								"�ö����е���Ʒ�������ȴ��ܲ���˻����ύ��û�б�����,�޷��޸ġ�ɾ����");
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
						 * super.saveSuccessfulMsg(req, "���ƶ�����ʼ������,�޷��޸ģ�");
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
							super.saveSuccessfulMsg(req, "���ƶ���������,�޷��޸ģ�");
							return mapping.findForward("query");
						}
					}
				}

				if (order.getFoltype().equals("make")
						|| order.getFoltype().equals("repair")
						|| order.getFoltype().equals("makeEar")
						|| order.getFoltype().equals("repairEar")) {
					List ci = (ArrayList) mapResponse.get("beo1");// ���˿ͻ���Ϣ
					if (null != ci) {
						ClassHelper.copyProperties(ci.get(0), orderForm);
					}
					List agList = (ArrayList) mapResponse.get("beo2");// ����ͼ��Ϣ
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
						// ����Ƿ���ڣ�
						if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
							String msg = "û�в�ѯ�����������ļ�¼��";
							super.saveSuccessfulMsg(req, msg);
						}
						ClassHelper.copyProperties(or, form); // �����뵥λ���������ڻش���mofiryEarRepair.jsp
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
			saveSuccessfulMsg(req, "����Ϊ�գ������²�ѯ");
		} else {
			// ClassHelper.copyProperties(scForm, sc);
			OrderFacade facade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", sc);
			// mapRequest.put("repid", repid);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = facade.printClient(requestEnvelop);
			// �����ؽ��

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				HashMap mapResponse = (HashMap) resEnv.getBody();
				List listci = (ArrayList) mapResponse.get("beo");// ���˿ͻ���Ϣ
				// List listci1 = (ArrayList) mapResponse.get("beo1");// ������Ϣ
				List listci2 = (ArrayList) mapResponse.get("beo2");// ����ͼ��Ϣ
				// String foldt = (String) mapResponse.get("foldt");// ����ͼ��Ϣ
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
	 * ��ѯ������ϸ��Ϣ
	 */
	public ActionForward queryDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ActionForward af = new ActionForward();
		String forward = "/order/customDetail.jsp"; // �鿴������ϸ
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
	 * �޸Ķ�����ϸ��Ϣ
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
	 * "/order/editDetail.jsp"; // �޸Ķ�����ϸ try { od.setFileKey("ord01_001");
	 * String hql = queryEnterprise(od); af = super.init(req, forward, hql); }
	 * catch (AppException ex) { this.saveErrors(req, ex); } catch (Exception e)
	 * { this.saveErrors(req, e); } return af; }
	 */

	/**
	 * ��Ӧ�����뷢������
	 */
	public ActionForward enterDelivery(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Order order = new Order();
		ActionForward af = new ActionForward();
		String forward = "/order/delivery.jsp"; // ��������
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
	 * �޸Ķ���������Ϣ(����)
	 */
	/*
	 * public ActionForward modifyOrder(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest req, HttpServletResponse res) throws Exception {
	 * SubmitDataMap data = new SubmitDataMap(req); OrderHeaderForm orderForm =
	 * (OrderHeaderForm) form; Order order = new Order(); try { String[] cltList
	 * = data.getParameterValues("fdtcltid"); // ���˴��� String[] pidList =
	 * data.getParameterValues("fdtpid"); // ��Ʒ(����)���� String[] numList =
	 * data.getParameterValues("fdtsqnt"); // �������� int size = pidList.length;
	 * List<OrderDetail> odList = new Vector<OrderDetail>(); for (int i = 0; i <
	 * size; i++) { OrderDetail od = new OrderDetail();
	 * od.setFdtfno(orderForm.getFolno()); od.setFdtcltid(cltList[i]);
	 * od.setFdtpid(pidList[i]); od.setFdtsqnt(Integer.parseInt(numList[i]));
	 * odList.add(od); } ClassHelper.copyProperties(orderForm, order); LoginDTO
	 * dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
	 * order.setFolsopr(dto.getBsc011());// ����Ա
	 * order.setFolsdt(DateUtil.getDate());// ����ʱ�� OrderFacade orderFacade =
	 * (OrderFacade) getService("OrderFacade");
	 * 
	 * RequestEnvelop requestEnvelop = new RequestEnvelop(); EventResponse
	 * returnValue = new EventResponse(); // ��Application�������HashMap
	 * HashMap<String, Object> mapRequest = new HashMap<String, Object>();
	 * mapRequest.put("beo", order); mapRequest.put("beo1", odList);
	 * requestEnvelop.setBody(mapRequest); // ���ö�Ӧ��Facadeҵ������ ResponseEnvelop
	 * resEnv = orderFacade.modifyDetail(requestEnvelop); // �����ؽ�� returnValue
	 * = processRevt(resEnv); if (returnValue.isSucessFlag()) {
	 * super.saveSuccessfulMsg(req, "�����ɹ�!"); // ��ô�ҵ��㷵�ص���־��Ϣ // Order order1 =
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
	 * ��ѯ������ϸ
	 */
	public ActionForward queryAllDetail(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String require = request.getParameter("require");

		// ��ȡҳ���ѯ����
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
			// ����ҳ������
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
				String msg = "û�в�ѯ��������������Ϣ��";
				super.saveSuccessfulMsg(request, msg);
			}
			 if ("check".equals(require)) {
			// ���������ϼ�
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
	 * ����
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
			String queryhql = "";// ��ҳ�����ѯ
			queryhql = "select d.*,h.*,c.ictnm,p.pdtnm from tblfoldetail d left outer join tblfolio h on h.folno = d.fdtfno left outer join tblindclient c on c.ictid = d.fdtcltid left outer join tblproduct p on p.pdtid = d.fdtpid where h.folsta = 'finish' and h.folctid = '"
					+ fm.getFolctid()
					+ "' and "
					+ "foldt between to_date('"
					+ fm.getStart()
					+ "','yyyy-mm-dd') and to_date('"
					+ fm.getEnd() + "','yyyy-mm-dd')";
			af = init(request, forward, queryhql, "1");
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ��������������Ϣ��";
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
	 * �в�����ϸ�Ķ���ÿ�ճ���ͳ�ƣ����ݼƻ��������ڣ�
	 */
	public ActionForward queryStat(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ��ȡҳ���ѯ����
		ActionForward af = new ActionForward();
		OrderHeaderForm fm = (OrderHeaderForm) actionForm;
		OrderDetail dto = new OrderDetail();

		// ArrayList statls = new ArrayList();
		try {

			String forward = "/order/stat.jsp";

			String queryhql = "";// ��ҳ�����ѯ

			queryhql = "select count(*) temp01, "
					+ "count(decode(h.folsta,'finish','1','charged','1')) temp02, "// ��һ��'2'��ʾ����״̬�ѷ���,temp02Ϊʵ�������
					+ "count(*)-count(decode(h.folsta,'finish','1','charged','1')) temp03, "// ��һ��'0'��ʾ����״̬δ����,temp03Ϊδ�����
					+ "trunc(count(decode(h.folsta,'finish','1','charged','1'))*100/decode(count(*),0,1,count(*)))||'%' temp04 "
					+ "from tblfolio h left outer join tblfoldetail d on h.folno=d.fdtfno where h.folpdt=to_date('"
					+ fm.getFolpdt()
					+ "','yyyy-MM-dd') and d.fdtcltnm is not null";

			af = init(request, forward, queryhql, "1");
			if (null == request.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ��������������Ϣ��";
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
	 * ��Ӧ����ӡ����
	 */
	public ActionForward print(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String type = req.getParameter("type");
		OrderHeaderForm header = (OrderHeaderForm) form;
		if (!(header.getFoltype().equals("normal"))) {
			super.saveSuccessfulMsg(req, "�ö���������ͨ���������ܴ�ӡ��");

			return mapping.findForward("backspace");
		}

		Connection conn = null;
		try {
			File reportFile = null;
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			if ("jiewen".equals(type)) {
				reportFile = new File(req.getSession().getServletContext()
						.getRealPath(
								"\\WEB-INF\\report\\report3_1_before.jasper"));
			} else {
				reportFile = new File(
						req.getSession().getServletContext().getRealPath(
								"\\WEB-INF\\report\\report3_before.jasper"));
			}
			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("fno", header.getFolno());
			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();

			byte[] bytes = JasperRunManager.runReportToPdf(
					reportFile.getPath(), parameters, conn);

			res.setContentType("application/pdf");
			// res.setContentLength(bytes.length);
			ServletOutputStream ouputStream = res.getOutputStream();
			// �Զ���ӡ
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
	 * ���ɳ����ͻ���
	 */
	public ActionForward print1(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm header = (OrderHeaderForm) form;
		Connection conn = null;
		try {
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\report3.jasper"));

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��
			parameters.put("code", header.getFolctid());
			parameters.put("date", header.getFolsdt());
			parameters.put("way", header.getFolway());
			parameters.put("sno", header.getFolsno());

			// ���ӵ����ݿ�
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
	 * �˻�
	 */
	public ActionForward recoil(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderHeaderForm orForm = (OrderHeaderForm) form;
		OrderDetail or = new OrderDetail();
		try {
			if (orForm.getFdtqnt() < 0) {
				super.saveSuccessfulMsg(request, "���Ѿ����˻�����!");
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
			// ��Application�������HashMap
			HashMap<String, OrderDetail> mapRequest = new HashMap<String, OrderDetail>();
			mapRequest.put("beo", or);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = czFacade.recoil(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(request, "�˻��ɹ�!");
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
	 * �ܲ���ѯ����������Ϣ���˻�ҳ�棩
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
			fileKey = "ord05_000"; // ��ѯҪ�˻��Ķ���
		}
		try {
			ClassHelper.copyProperties(orderForm, or);
			or.setFileKey(fileKey);
			String hql = queryEnterprise(or);
			System.out.println(hql);
			af = super.init(req, forward, hql);
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
	 * ֱ�����ѯ�˻�����������Ϣ
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
	 * ��ʾά�޼�¼��ʷ
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
	 * ά�޶������
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
			String opr = dto1.getBsc011();// ����˴���
			if (null == order.getFolno() || "".equals(order.getFolno())) {
				throw new AppException("ά�����ʱά�޶�����(������)Ϊ��");
			}
			if (null == rep.getRepfolid() || "".equals(rep.getRepfolid())) {
				throw new AppException("ά�����ʱά�޶����ţ�ά�ޱ�Ϊ��");
			}
			if (tp.equals("r")) {
				String sql0 = "update tblfolio l set l.folsta='back' where l.folno='"
						+ order.getFolno() + "' and l.foltype='repair'";
				System.out.println("OrderAction��examineRepair����sql0:" + sql0);
				pstm = con.prepareStatement(sql0);
				pstm.executeUpdate();
				super.saveSuccessfulMsg(req, "�ѻ��ˣ�");
			} else if (tp.equals("e")) {
				order.setFolsta("pass");
				order.setFolopr(opr);
				order.setFolexdt(DateUtil.getDate());
				if (null != order.getRepcpy() && order.getRepcpy().equals("�ݶ�")) {
					String sql1 = "update tblrep p set p.repsta='wait',p.repcpy='"
							+ rep.getRepcpy()
							+ "',p.repcls='"
							+ rep.getRepcls()
							+ "',p.reppdate=to_date('"
							+ rep.getReppdate()
							+ "','yyyy-MM-dd')"
							+ " where p.repfolid='" + rep.getRepfolid() + "'";
					System.out.println("OrderAction��examineRepair����sql1:"
							+ sql1);
					pstm = con.prepareStatement(sql1);
					pstm.executeUpdate();
				} else {
					// tblrep״̬��Ϊ����
					String sql2 = "update tblrep p set p.repsta='out',p.repcpy='"
							+ rep.getRepcpy()
							+ "',p.repcls='"
							+ rep.getRepcls()
							+ "',p.reppdate=to_date('"
							+ rep.getReppdate()
							+ "','yyyy-MM-dd')"
							+ " where p.repfolid='" + rep.getRepfolid() + "'";
					System.out.println("OrderAction��examineRepair����sql2:"
							+ sql2);
					pstm = con.prepareStatement(sql2);
					pstm.executeUpdate();
				}
				String sql3 = "update tblfolio set folsta='pass',folopr='"
						+ order.getFolopr() + "',folexdt=to_date('"
						+ order.getFolexdt() + "','yyyy-MM-dd') where folno='"
						+ order.getFolno() + "' and foltype='repair'";
				System.out.println("OrderAction��examineRepair����sql3:" + sql3);
				pstm = con.prepareStatement(sql3);
				pstm.executeUpdate();
				super.saveSuccessfulMsg(req, "���ͨ����");
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
	 * �������
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
			super.saveSuccessfulMsg(req, "��ѡ��������!");
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

			String opr = dto1.getBsc011();// ����˴���
			// String grCli="";
			// if(!"1501000000".equals(dto1.getBsc001())){
			// grCli = dto1.getBsc011();//�ͻ�����
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
				rep.setRepdate(DateUtil.getDate());// ��������
				// rep.setRepoprcd(dto1.getBsc011());
				// rep.setRepnote(od.getFdtinnt());// ��ע
				rep.setRepcltnm(orderForm.getIctnm());// �ͻ�����
				rep.setRepcltid(orderForm.getIctid());
				rep.setRepid(od.getFdtsid());// ��ģ������
				rep.setReppid(orderForm.getTmepid());// ��ģ�ͺ�
				rep.setRepgctid(order.getFolctid());// ����ͻ�
				rep.setReppdate(order.getReppdate());
				// rep.setRepreger(dto1.getBsc011());// ����˴���
				if (null != order.getRepcpy() && order.getRepcpy().equals("�ݶ�")) {
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
			// ��Application�������HashMap
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
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			if (tp1.equals("r")) {
				if (order.getFolsta().equals("pass")) {
					throw new AppException("���ͨ���Ķ��������ٻ��ˣ�");
				}
				resEnv = facade.rollBack(requestEnvelop);
			} else if (tp1.equals("e")) {
				if (order.getFolno() == null || "".equals(order.getFolno())) {
					throw new AppException("�����Ų���Ϊ��(��֪ͨ������Ա)��");
				}

				resEnv = facade.examine(requestEnvelop);
			}
			// �����ؽ��

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
							alert = "��" + minstoeq0 + "����Ʒ���ֿ����Ϊ0,���ܷ�����";
							// throw new AppException(alert);
						}
						if (!"".equals(minstoles0)) {
							minstoles0 = minstoles0.substring(0, minstoles0
									.length() - 1);
							alert = "��" + minstoles0 + "����Ʒ���ֿ����Ϊ" + stoAmo
									+ ",�������������ֿ������";
						}
						if (!"".equals(minstoi)) {
							minstoi = minstoi
									.substring(0, minstoi.length() - 1);
							minsto = minsto.substring(0, minsto.length() - 1);
							alert = "��" + minstoi
									+ "����Ʒ�������������������С�����!\\n����Ϳ����Ϊ" + minsto
									+ "���ֿ������Ϊ" + stoAmo;
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
						// // ����Ƿ���ڣ�
						// if (null == req.getAttribute(GlobalNames.QUERY_DATA))
						// {
						// String msg = "û�в�ѯ�����������ļ�¼��";
						// super.saveSuccessfulMsg(req, msg);
						// }
						// return mapping.findForward("nomDetail");
						// }

					}
				}

				if (tp1.equals("r")) {
					super.saveSuccessfulMsg(req, "�ѻ��ˣ�");

				} else if (tp1.equals("e")) {
					if ("Ear".equals(folType)) {
						HashMap<String, Object> mapResponse = (HashMap) resEnv
								.getBody();
						String tmesid = (String) mapResponse.get("tmesid");
						super.saveSuccessfulMsg(req, "���ͨ������ģ���Ϊ" + tmesid);
					} else {
						super.saveSuccessfulMsg(req, "���ͨ����");
						if ("nom".equals(folType)) {
							Connection conn = null;
							File reportFile = null;
							// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��

							reportFile = new File(
									req
											.getSession()
											.getServletContext()
											.getRealPath(
													"\\WEB-INF\\report\\report3_before.jasper"));

							// ���ݱ������õ��Ĳ���ֵ
							Map<String, Object> parameters = new HashMap<String, Object>();
							// "Name"�Ǳ����ж������һ����������,������ΪString ��
							parameters.put("fno", orderForm.getFolno());
							// ���ӵ����ݿ�
							conn = DBUtil.getConnection();

							byte[] bytes = JasperRunManager.runReportToPdf(
									reportFile.getPath(), parameters, conn);

							res.setContentType("application/pdf");
							// res.setContentLength(bytes.length);
							ServletOutputStream ouputStream = res
									.getOutputStream();
							// �Զ���ӡ
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
	 * ����������ά�޶���
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
					throw new AppException("����������ѻ��ˣ������ٶ�����ˣ�");
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
			 * AppException("����������ѻ��ˣ������ٶ�����ˣ�"); }
			 */
			// ClassHelper.copyProperties(orderForm, dg);
			// if (order.getFolctid() == null || "".equals(order.getFolctid()))
			// {
			// super.saveSuccessfulMsg(req, "����ȷ¼������ͻ�");
			// return mapping.findForward("backspace");
			// }
			// LoginDTO dto = (LoginDTO)
			// req.getSession().getAttribute("LoginDTO");
			// if(!"1501000000".equals(dto.getBsc001())){
			// grCli = dto.getBsc011();//�ͻ�����
			// }else{
			// grCli = orderForm.getFolctid();
			// }
			// rep.setRepgctid(dto.getBsc001());
			// rep.setRepreger(dto.getBsc011());
			// rep.setRepcltnm(orderForm.getIctnm());
			// rep.setRepcltid(orderForm.getIctid());
			// rep.setRepcusdt(Date.valueOf(repcusdt));
			// rep.setRepdate(DateUtil.getDate());// ��������
			// rep.setRepgctid(grCli);// ����ͻ�
			// rep.setRepsta("wait");
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			// mapRequest.put("die", die);
			mapRequest.put("collection", checked);
			requestEnvelop.setBody(mapRequest);
			resEnv = orderFacade.examineDis(requestEnvelop);
			// ���ö�Ӧ��Facadeҵ������
			// if(tp.equals("c"))
			// {
			// resEnv = orderFacade.examineDis(requestEnvelop);
			// }
			// else
			// {
			// resEnv = orderFacade.saveCusRep(requestEnvelop);
			// }
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "��������������Ϣ�ɹ�!");
				// ��������Ķ���ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");

				// FindLog.insertLog(req, folno, "����������ά�޶����ɹ���");
				// if(tp.equals("c"))
				// {
				super.saveSuccessfulMsg(req, "�ÿ��������ͨ����");
				// }
				// else
				// {
				// super.saveSuccessfulMsg(req, "������ά�޶���¼���ɹ��������ţ�" + folno);
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
	 * ������˻���
	 */
	public ActionForward Disback(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		DisExamine die = new DisExamine();
		try {
			ClassHelper.copyProperties(orderForm, die);
			if (null != die.getTdeisexa() && !((die.getTdeisexa().equals("0")))) {
				super.saveSuccessfulMsg(req, "�Ѿ�ͨ��������˵Ĳ��ܻ��ˣ�");
				return mapping.findForward("backspace");
			} // �Ƿ�����˿���
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("die", die);
			requestEnvelop.setBody(mapRequest);

			resEnv = orderFacade.Disback(requestEnvelop);

			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {

				super.saveSuccessfulMsg(req, "�ö���������˻��ˣ�");
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
	 * ����������ά�޶���
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
			// super.saveSuccessfulMsg(req, "����ȷ¼������ͻ�");
			// return mapping.findForward("backspace");
			// }
			if (orderForm.getReppid() == null
					|| "".equals(orderForm.getReppid())) {
				super.saveSuccessfulMsg(req, "��Ʒ����Ϊ�գ��������ͺ����������룡");
				return mapping.findForward("backspace");
			}
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			if (!"1501000000".equals(dto.getBsc001())) {
				grCli = dto.getBsc011();// �ͻ�����
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
			rep.setRepdate(DateUtil.getDate());// ��������
			rep.setRepgctid(grCli);// ����ͻ�
			// rep.setRepsta("wait");
			ResponseEnvelop resEnv = null;
			OrderFacade orderFacade = (OrderFacade) getService("OrderFacade");

			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("rep", rep);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveCusRep(requestEnvelop);
			}
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "��������������Ϣ�ɹ�!");
				// ��������Ķ���ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("repfno");

				// FindLog.insertLog(req, folno, "����������ά�޶����ɹ���");
				if (tp.equals("c")) {
					// throw new AppException("������ά�޶���¼�����ύ�ɹ��������ţ�"+ folno);
					super.saveSuccessfulMsg(req, "������ά�޶���¼�����ύ�ɹ��������ţ�" + folno);
				} else {
					super.saveSuccessfulMsg(req, "������ά�޶���¼���ɹ��������ţ�" + folno);
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
	 * ����������Ķ�ģ��Ϣ
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
			// super.saveSuccessfulMsg(req, "����ȷ¼������ͻ�");
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
				grCli = currentuser.getBsc011();// �ͻ�����
			} else {
				grCli = orderForm.getFolctid();
			}
			// �ж϶�ģ���
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
					// detail.setFdtfno(tmefno);// ������
					// detail.setFdtcltid(em.getTmecltid());//���˿ͻ�����
					// detail.setFdtcltnm(emList.get(0).getTmecltnm());// �ͻ�����
					// detail.setFdtprc(price);// ����
					// detail.setFdtdprc(price);
					// detail.setFdtpid(emList.get(0).getTmepid());
					// detail.setFdtqnt(earcount);// ��������
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
					// ����˫���е���
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
					// ����˫���е���
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
			// ���ö�Ӧ��Facadeҵ������
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveEar(requestEnvelop);
			}
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				String tmeno = (String) ((HashMap) resEnv.getBody())
						.get("tmeno");
				// ��ô�ҵ��㷵�ص���־��Ϣ
				String earworkString = (String) ((HashMap) resEnv.getBody())
						.get("earworkString");
				// FindLog.insertLog(req, tmeno, earworkString);
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "��ģ¼�����ύ�ɹ��������ţ�" + tmeno);
				} else {
					super.saveSuccessfulMsg(req, "��ģ¼���ɹ��������ţ�" + tmeno);
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
	 * ������ͨ����
	 */
	public ActionForward saveNomOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		// Repair rep=new Repair();
		String tp = req.getParameter("tp");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] idList = data.getParameterValues("fdtpid"); // ��Ʒ(����)����
		String[] dprcList = data.getParameterValues("fdtdprc");// �۸�
		// String[] discList = data.getParameterValues("fdtdisc"); // ����
		// String[] prcList = data.getParameterValues("fdtprc");// �۸�(�ۼ�)
		String[] numList = data.getParameterValues("fdtqnt"); // ����
		String[] ntList = data.getParameterValues("fdtinnt"); // ��ע
		// Diagnose dg=new Diagnose();
		try {
			// ClassHelper.copyProperties(orderForm, rep);
			// ClassHelper.copyProperties(orderForm, dg);
			// if (order.getFolctid() == null || "".equals(order.getFolctid()))
			// {
			// super.saveSuccessfulMsg(req, "����ȷ¼������ͻ�");
			// return mapping.findForward("backspace");
			// }
			String grCli = "";
			LoginDTO dto = (LoginDTO) req.getSession().getAttribute("LoginDTO");
			if (!"1501000000".equals(dto.getBsc001())) {
				grCli = dto.getBsc011();// �ͻ�����
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

				od.setFdtrqnt(Integer.parseInt(numList[i])); // ��ʼ����Ʒ��ʣ������Ϊ����ʱ������

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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("type", "nom");
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveNom(requestEnvelop);
			}
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "��������������Ϣ�ɹ�!");
				// ��������Ķ���ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("folno");

				// FindLog.insertLog(req, folno, "������ͨ�����ɹ���");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "��ͨ����¼�����ύ�ɹ��������ţ�" + folno);
				} else {
					super.saveSuccessfulMsg(req, "��ͨ����¼���ɹ��������ţ�" + folno);
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
	 * ������ͨ����
	 */
	public ActionForward saveDevRepOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		OrderHeaderForm orderForm = (OrderHeaderForm) form;
		Repair rep = new Repair();
		String tp = req.getParameter("tp");
		SubmitDataMap data = new SubmitDataMap(req);
		String[] idList = data.getParameterValues("fdtpid"); // ��Ʒ(����)����
		// String[] dprcList = data.getParameterValues("fdtdprc");// �۸�
		// String[] discList = data.getParameterValues("fdtdisc"); // ����
		// String[] prcList = data.getParameterValues("fdtprc");// �۸�(�ۼ�)
		String[] numList = data.getParameterValues("fdtqnt"); // ����
		String[] ntList = data.getParameterValues("fdtinnt"); // ��ע
		// Diagnose dg=new Diagnose();
		try {
			ClassHelper.copyProperties(orderForm, rep);
			// ClassHelper.copyProperties(orderForm, dg);
			// if (order.getFolctid() == null || "".equals(order.getFolctid()))
			// {
			// super.saveSuccessfulMsg(req, "����ȷ¼������ͻ�");
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

				od.setFdtrqnt(Integer.parseInt(numList[i])); // ��ʼ����Ʒ��ʣ������Ϊ����ʱ������

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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("rep", rep);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveDevRep(requestEnvelop);
			}
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "��������������Ϣ�ɹ�!");
				// ��������Ķ���ID
				String folno = (String) ((HashMap) resEnv.getBody())
						.get("folno");

				// FindLog.insertLog(req, folno, "������ͨ�����ɹ���");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "��ͨ����¼�����ύ�ɹ��������ţ�" + folno);
				} else {
					super.saveSuccessfulMsg(req, "��ͨ����¼���ɹ��������ţ�" + folno);
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
	 * ���������Ķ�ģά����Ϣ
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

			String opr = dto1.getBsc011();// ����˴���

			// ����tblearmaking���õ�������
			// ClassHelper.copyProperties(orderForm, emould);
			// emould.setTmecltid(orderForm.getIctid());//���˿ͻ�����
			// emould.setTmecls("repair");//�������
			// emould.setTmesta("2");//��ģ״̬��2��ʾ�ȴ��ʼ�
			// emould.setTmecltnm(orderForm.getIctnm());//���˿ͻ�����

			// rep.setRepdate(DateUtil.getDate());// ��������
			// rep.setRepoprcd(dto1.getBsc011());
			// rep.setRepnote(orderForm.getTment());// ��ע
			// rep.setRepcltnm(orderForm.getIctnm());// �ͻ�����
			// rep.setRepid(orderForm.getTmesid());// ��ģ������
			// rep.setReppid(orderForm.getTmepid());// ��ģ�ͺ�
			// rep.setRepgctid(orderForm.getIctgctid());// ����ͻ�
			// rep.setReppdate(orderForm.getTmepdt());//�ƻ��깤����
			// rep.setRepreger(dto1.getBsc011());//����˴���
			// rep.setRepsta("wait");
			// rep.setRepcpy("�ݶ�");

			// repList.add(rep);
			String grCli = "";
			if (!"1501000000".equals(dto1.getBsc001())) {
				grCli = dto1.getBsc011();// �ͻ�����
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("od", detail);
			mapRequest.put("type", "earRep");
			mapRequest.put("grCli", grCli);
			mapRequest.put("opr", opr);
			// ��HashMap�������requestEnvelop
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			// ResponseEnvelop resEnv = facade.saveRepair(requestEnvelop);
			ResponseEnvelop resEnv = null;
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.saveEarRep(requestEnvelop);
			}
			// �����ؽ��
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
					super.saveSuccessfulMsg(request, "�����ģά�޼�¼�ɹ�,������Ϊ" + repfno
							+ "!");
					return go2Page(request, mapping, "order");
					// return mapping.findForward("backspace");
				} else {
					super.saveSuccessfulMsg(request, "���沢�ύ��ģά�޼�¼�ɹ�,������Ϊ"
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
	 * ���ݶ�ģ�ͺŻ�ö�ģ�ۼ�
	 */
	public ActionForward queryEMPro(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {
			pdt.setPdtid(req.getParameter("EarId"));
			ProductFacade pdtFacade = (ProductFacade) getService("ProductFacade");
			RequestEnvelop requestEnvelop = new RequestEnvelop();
			EventResponse returnValue = new EventResponse();
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("beo", pdt);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = pdtFacade.query(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "��ѯ��Ʒ�۸�ɹ�!");
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
	 * ��������ͻ�����Ʒ��������С����
	 */
	public ActionForward queryDis(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		Product pdt = new Product();
		try {

			// pdt.setPdtid(req.getParameter("EarId"));
			String grid = req.getParameter("Grid");// ����ͻ�id
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("grid", grid);
			mapRequest.put("odList", odList);
			mapRequest.put("folno", folno);
			if (null != discount && !"".equals(discount)) {
				mapRequest.put("discount", discount);
			}
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = orderFacade.queryMinDis(requestEnvelop);
			// �����ؽ��
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
						String jsonStr = "[{tdspvo:" + tdspvo + ",lr:'6'}]"; // �ٴ�����Ŀ���С���ܲ�����˵Ŀ���
						res.getWriter().write(jsonStr);
						return null;
					} else if (null != alert3 && !"".equals(alert3)) {
						// throw new AppException("����������ѱ��ܲ����ˣ�����С�Ŀ���Ҳ�����ύ��");
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
					// throw new AppException("����������ѱ��ܲ����ˣ�����С�Ŀ���Ҳ�����ύ��");
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
	 * ���ݻ����Ż����������
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("cz", cz);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			ResponseEnvelop resEnv = orderFacade.queryFoldt(requestEnvelop);
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				super.saveSuccessfulMsg(req, "��ѯ��Ʒ�۸�ɹ�!");
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
	 * �޸Ķ�Ĥ����
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("type", "ear");
			mapRequest.put("folno", orderForm.getFolno());
			mapRequest.put("folurgent", orderForm.getFolurgent());
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.updateEar(requestEnvelop);
			}
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "��������������Ϣ�ɹ�!");
				// ��������Ķ���ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");
				// FindLog.insertLog(req, folno, "�޸�������ά�޶����ɹ���");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "�ύ��ģ�����ɹ���");
				} else {
					super.saveSuccessfulMsg(req, "�޸Ķ�ģ�����ɹ���");
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
	 * �޸Ķ�Ĥά�޶���
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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("od", od);
			// mapRequest.put("qnt", orderForm.getFdtqnt());
			// mapRequest.put("folno", orderForm.getFolno());
			// mapRequest.put("folurgent", orderForm.getFolurgent());
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.updateEarRep(requestEnvelop);
			}
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "��������������Ϣ�ɹ�!");
				// ��������Ķ���ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");
				// FindLog.insertLog(req, folno, "�޸�������ά�޶����ɹ���");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "�ύ��ģ�����ɹ���");
				} else {
					super.saveSuccessfulMsg(req, "�޸Ķ�ģ�����ɹ���");
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
		// String[] ordidList = data.getParameterValues("folno"); // ������
		String folno = req.getParameter("folno");
		String[] idList = data.getParameterValues("fdtpid"); // ��Ʒ(����)����
		String[] dprcList = data.getParameterValues("fdtdprc");// �۸�(�ۼ�)
		// String[] discList = data.getParameterValues("fdtdisc"); // ��Ʒ(����)����
		// String[] prcList = data.getParameterValues("fdtprc");// �۸�(�ۼ�)
		String[] numList = data.getParameterValues("fdtqnt"); // ����
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

				od.setFdtrqnt(Integer.parseInt(numList[i])); // ��ʼ����Ʒ��ʣ������Ϊ����ʱ������

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
			// ��Application�������HashMap
			HashMap<String, Object> mapRequest = new HashMap<String, Object>();
			mapRequest.put("odList", odList);
			mapRequest.put("type", "nom");
			mapRequest.put("chk", chk);
			requestEnvelop.setBody(mapRequest);
			// ���ö�Ӧ��Facadeҵ������
			if (tp.equals("c")) {
				resEnv = orderFacade.commit(requestEnvelop);
			} else {
				resEnv = orderFacade.updateNom(requestEnvelop);
			}
			// �����ؽ��
			returnValue = processRevt(resEnv);
			if (returnValue.isSucessFlag()) {
				// super.saveSuccessfulMsg(req, "��������������Ϣ�ɹ�!");
				// ��������Ķ���ID
				// String folno = (String) ((HashMap) resEnv.getBody())
				// .get("repfno");
				// FindLog.insertLog(req, folno, "�޸�������ά�޶����ɹ���");
				if (tp.equals("c")) {
					super.saveSuccessfulMsg(req, "�ύ��ͨ�����ɹ���");
				} else {
					super.saveSuccessfulMsg(req, "�޸���ͨ�����ɹ���");
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
	 * ��ͨ���������������
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
	 * ��ͨ���������������
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
	 * ���ݶ�ģ�ͺŻ�ö�ģ�ۼ�
	 */
	/*
	 * public ActionForward queryClass(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest req, HttpServletResponse res) throws Exception { //
	 * Product pdt = new Product(); Pro pro=new Pro(); try { //
	 * pdt.setPdtid(req.getParameter("EarId")); ProductFacade pdtFacade =
	 * (ProductFacade) getService("ProductFacade"); RequestEnvelop
	 * requestEnvelop = new RequestEnvelop(); EventResponse returnValue = new
	 * EventResponse(); // ��Application�������HashMap HashMap<String, Object>
	 * mapRequest = new HashMap<String, Object>(); // mapRequest.put("beo",
	 * pdt); requestEnvelop.setBody(mapRequest); // ���ö�Ӧ��Facadeҵ������
	 * ResponseEnvelop resEnv = pdtFacade.queryLarge(requestEnvelop); // �����ؽ��
	 * returnValue = processRevt(resEnv); if (returnValue.isSucessFlag()) {
	 * super.saveSuccessfulMsg(req, "��ѯ��Ʒ�۸�ɹ�!"); List list = (ArrayList)
	 * ((HashMap) resEnv.getBody()).get("beo"); if (list.size() > 1) throw new
	 * Exception(); ClassHelper.copyProperties(list.get(0), pro); String
	 * large=pro.getProlarge(); // double price = pdt.getPdtprc();
	 * res.setCharacterEncoding("GBK"); res.getWriter().write("[{large:" + large
	 * + "}]"); } } catch (Exception e) { super.saveErrors(req, e); } return
	 * null; }
	 */

	/**
	 * ���ɶ��ƻ�������
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
			super.saveSuccessfulMsg(req, "�ö�����������˵Ķ��ƶ��������ܴ�ӡ�����룡");
			String fileKey = "ord02_008";
			String forward = "examine";
			order.setFileKey(fileKey);
			String hql = queryEnterprise(order);
			af = super.init(req, forward, hql);
			// ����Ƿ���ڣ�
			// if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			// String msg = "û�в�ѯ�����������ļ�¼��";
			// super.saveSuccessfulMsg(req, msg);
			return actionMapping.findForward(forward);
		}
		// CustomizationForm cf = (CustomizationForm) actionForm;
		Connection conn = null;
		try {
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("\\WEB-INF\\report\\barcode.jasper"));

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();
			// System.out.println(cf.getTmkfno());
			// parameters.put("tmkfno", cf.getTmkfno());
			parameters.put("tmkfno", orderForm.getFolno());

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
	 * ���ɶ��ƻ�������
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
			super.saveSuccessfulMsg(req, "�ö������Ƕ��ƶ��������ܴ�ӡ��");

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
			// ����Ƿ���ڣ�
			// if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			// String msg = "û�в�ѯ�����������ļ�¼��";
			// super.saveSuccessfulMsg(req, msg);
			return actionMapping.findForward(forward);
		}
		Connection conn = null;
		try {
			// �������֮�����ɵ�.jasper �ļ��Ĵ��λ��
			File reportFile = new File(req.getSession().getServletContext()
					.getRealPath("/WEB-INF/report/order_cusOrder.jasper"));
			String webRootPath = req.getSession().getServletContext()
					.getRealPath("/");

			// ���ݱ������õ��Ĳ���ֵ
			Map<String, Object> parameters = new HashMap<String, Object>();
			// "Name"�Ǳ����ж������һ����������,������ΪString ��

			// ���ӵ����ݿ�
			conn = DBUtil.getConnection();
			// System.out.println(cf.getTmkfno());
			parameters.put("folno", orderForm.getFolno());
			parameters.put("ictid", orderForm.getFolindctid());
			parameters.put("SUBREPORT_DIR", webRootPath + "WEB-INF\\report\\");

			// �ڿ���̨��ʾһ�±����ļ�������·��
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
	 * ��ѯ���˿ͻ�ά�޼�¼
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "���û�����ά�޼�¼��";
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
	 * ��ѯ���ͨ���󶩵�������Ϣ
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
				String msg = "�޸Ŀ�Ʊ״̬�ɹ���";
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
	 * ����Ǩ��
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
			super.saveSuccessfulMsg(request, "����Ǩ��ʧ�ܣ�");
			return mapping.findForward("backspace");
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
			super.saveSuccessfulMsg(request, "����Ǩ�Ƴɹ���");
			return mapping.findForward("backspace");
		}
	/**
	 * ��ѯ����������Ϣ
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
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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



