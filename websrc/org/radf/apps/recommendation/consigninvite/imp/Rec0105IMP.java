/**
 * Rec0105IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.consigninvite.imp;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.radf.apps.commons.entity.Cb21;
import org.radf.apps.commons.entity.Cc20;
import org.radf.apps.commons.entity.Cc21;
import org.radf.apps.commons.entity.Cc22;
import org.radf.apps.recommendation.consigninvite.dto.Rec0103DTO;
import org.radf.apps.recommendation.consigninvite.dto.Rec0105DTO;
import org.radf.apps.recommendation.consigninvite.facade.Rec0105Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * ��Ա��λ�Ƽ���Ϣ����ʵ�ַ�����
 */
public class Rec0105IMP extends IMPSupport implements Rec0105Facade {
	private String className = this.getClass().getName();

	/**
	 * �Ƽ��ɹ�
	 * 
	 * @param request
	 *            ҵ���߼���Ĳ�����װ����
	 * @param ���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop RecommendFBSuccess(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		Iterator it;
		int i = 0;
		// ArrayList list1 = null;
		HashMap map = (HashMap) request.getBody();
		Rec0105DTO dto = (Rec0105DTO) map.get("input");
		Cc22 cc22 = new Cc22();
		Cb21 cb21 = new Cb21();
		try {
			con = DBUtil.getConnection();
			HashMap retmap = new HashMap();
			// 1���жϸ�����ְ�Ƽ���Ϣ���¼�������Ƽ���Ż�ȡ��Ϣ
			dto.setFileKey("cc22_select");
			ArrayList list = (ArrayList) find(con, dto, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc22);
				if (cc22.getAcc223() == null)// �ж��Ƽ����
					cc22.setAcc223("0");
				if (cc22.getAcc223().compareTo("0") != 0) {
					throw new AppException("�Ƽ����Ϊ��" + cc22.getAcc220()
							+ "�����Ƽ���Ϣ�ѷ����Ƽ�����������������Ϊ��" + dto.getAae017() + "");
				}
				cc22.setAcc223("1");// �ɹ��Ƽ�

			} else {
				throw new AppException("û���Ƽ���Ϣ");
			}

			// 2���жϵ�λ��Ƹ��λ��Ϣ
			dto.setAcb200(cc22.getAcb200());// ��Ƹ���
			dto.setAcb210(cc22.getAcb210());// ��λ���
			dto.setFileKey("rec01_006");
			ArrayList list2 = (ArrayList) find(con, dto, null, 0);
			if (list2 != null && list2.size() > 0) {
				ClassHelper.copyProperties(list2.get(0), cb21);
				if (cb21.getAcb208() != null) { // �����־
					cb21.setAcb208(cb21.getAcb208());
				} else {
					cb21.setAcb208("0");
				}

				if (cb21.getAcb21a().intValue() >= cb21.getAcb21d().intValue())
					throw new AppException("��Ƹ����������");
				cb21.setAcb21a(Short.valueOf((cb21.getAcb21a().intValue() + 1)
						+ "")); // �ɹ���+1

				if (cb21.getAcb21a().intValue() >= cb21.getAcb21d().intValue()) {
					cb21.setAcb208("1");
				}
				// 3���������λ���ᣬ������Ƹ��Ϣ���������и�λ��Ϣ�Ƿ��Ѷ��᣿������ᣬ�򶳽���Ƹ��Ϣ��
				if (cb21.getAcb208().compareTo("1") == 0) {
					// ��λ��Ϣ�Ķ�����Ϣ
					if (cb21.getAcb20d() == null)
						cb21.setAcb20d(dto.getAae011()); // ������Ա
					if (cb21.getAcb20e() == null)
						cb21.setAcb20e(dto.getAae036()); // ��������
				}
			} else {
				throw new AppException("û���Ƽ���Ϣ");
			}
			// update
			// �޸ĸ�����ְ�Ƽ���Ϣ��
			cc22.setAcc226(dto.getAcc226());// ������
			cc22.setAcc229(dto.getAae036());// ����ʱ��
			cc22.setFileKey("cc22_update");
			modify(con, cc22, null, 0);
			// �޸ĵ�λ��Ƹ��λ��Ϣ��
			cb21.setFileKey("cb21_update");
			modify(con, cb21, null, 0);
			/*
			 * // �޸ĵ�λ��Ƹ��Ϣ�� if (list1 != null) { // �ύ��Ƹ��Ϣ
			 * cb20.setFileKey("cb20_update"); modify(con, cb20, null, 0); }
			 */
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "RecommendFBSuccess",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �ָ����Ƽ���
	 * 
	 * @param request
	 *            ҵ���߼���Ĳ�����װ����
	 * @param ���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop RecommendFBhftj(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		Iterator it;
		int i = 0;
		// ArrayList list1 = null;
		HashMap map = (HashMap) request.getBody();
		Rec0105DTO dto = (Rec0105DTO) map.get("input");
		Cc22 cc22 = new Cc22();
		Cb21 cb21 = new Cb21();
		Cc20 cc20 = new Cc20();
		try {
			con = DBUtil.getConnection();
			HashMap retmap = new HashMap();
			// 1���жϸ�����ְ�Ƽ���Ϣ���¼�������Ƽ���Ż�ȡ��Ϣ
			dto.setFileKey("cc22_select");
			ArrayList list = (ArrayList) find(con, dto, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc22);
				if (!"1".equals(cc22.getAcc223())
						&& !"2".equals(cc22.getAcc223())) {
					throw new AppException("�Ƽ����Ϊ��" + cc22.getAcc220()
							+ "�����Ƽ���Ϣδ���Ƽ�����������ָ�����");
				}

			} else {
				throw new AppException("û���Ƽ���Ϣ");
			}
			if ("1".equals(cc22.getAcc223())) {
				cc22.setAcc223("0");
				dto.setAcb200(cc22.getAcb200());// ��Ƹ���
				dto.setAcb210(cc22.getAcb210());// ��λ���
				dto.setFileKey("rec01_006");
				ArrayList list2 = (ArrayList) find(con, dto, null, 0);
				if (list2 != null && list2.size() > 0) {
					ClassHelper.copyProperties(list2.get(0), cb21);
					if (cb21.getAcb208() != null) { // �����־
						cb21.setAcb208(cb21.getAcb208());
					} else {
						cb21.setAcb208("0");
					}

					cb21.setAcb21a(Short
							.valueOf((cb21.getAcb21a().intValue() - 1) + ""));
					if (cb21.getAcb21a().intValue() < 0) {
						cb21.setAcb21a(Short.valueOf("0"));
					}
					if (cb21.getAcb21a().intValue() >= cb21.getAcb21d()
							.intValue()) {
						cb21.setAcb208("1");
					} else {
						cb21.setAcb208("0");
					}
					// 3���������λ���ᣬ������Ƹ��Ϣ���������и�λ��Ϣ�Ƿ��Ѷ��᣿������ᣬ�򶳽���Ƹ��Ϣ��
					if (cb21.getAcb208().compareTo("1") == 0) {
						// ��λ��Ϣ�Ķ�����Ϣ
						if (cb21.getAcb20d() == null)
							cb21.setAcb20d(dto.getAae011()); // ������Ա
						if (cb21.getAcb20e() == null)
							cb21.setAcb20e(dto.getAae036()); // ��������
					} else {
						cb21.setAcb20d(null);
						cb21.setAcb20e(null);
					}
				} else {
					throw new AppException("û���Ƽ���Ϣ");
				}

				cc22.setAcc226(null);// ������
				cc22.setAcc229(null);// ����ʱ��
				cc22.setFileKey("cc22_update");
				modify(con, cc22, null, 0);
				// �޸ĵ�λ��Ƹ��λ��Ϣ��
				cb21.setFileKey("cb21_update");
				modify(con, cb21, null, 0);
				DBUtil.commit(con);
			} else {
				dto.setAcc200(cc22.getAcc200());
				dto.setFileKey("cc20_select");
				ArrayList list1 = (ArrayList) find(con, dto, null, 0);
				if (list1 != null && list1.size() > 0) {
					ClassHelper.copyProperties(list.get(0), cc20);
					cc20.setAcb208("1");
				} else {
					throw new AppException("û�и���Ա����ְ��Ϣ");
				}

				cc22.setAcc223("0"); // ʧ���Ƽ�
				cc22.setAcc226(null);// ������
				cc22.setAcc22e(null);// ʧ��ԭ��
				cc22.setAcc229(null);// ����ʱ��
				// update
				cc22.setFileKey("cc22_update");
				modify(con, cc22, null, 0);

				// ���ע����ְ��Ϣ�����ύ
				if (cc20.getAcb208().compareTo("1") == 0) {
					// �ⶳ��ְ��Ϣ
					cc20.setFileKey("rec01_007");
					modify(con, cc20, null, 0);
				}

				DBUtil.commit(con);
			}

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "RecommendFBSuccess",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �Ƽ�ʧ��
	 * 
	 * @param request
	 *            ҵ���߼���Ĳ�����װ����
	 * @param ���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop RecommendFBLost(RequestEnvelop request) {

		ResponseEnvelop response = new ResponseEnvelop();
		HashMap map = (HashMap) request.getBody();
		Rec0105DTO dto = (Rec0105DTO) map.get("input");
		Connection con = null;
		String acc223 = dto.getAcc223();
		Cc22 cc22 = new Cc22();
		Cc20 cc20 = new Cc20();
		Cc21 cc21 = new Cc21();
		Cb21 cb21 = new Cb21();
		try {
			con = DBUtil.getConnection();
			dto.setFileKey("cc22_select");
			ArrayList list = (ArrayList) find(con, dto, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc22);
				if (cc22.getAcc223() == null)
					cc22.setAcc223("0");
				if ("0".equals(acc223)) {
					if (cc22.getAcc223().compareTo("0") != 0) {
						throw new AppException("�Ƽ����Ϊ��" + cc22.getAcc220()
								+ "�����Ƽ���Ϣ�ѷ����Ƽ�����������������Ϊ��" + dto.getAae017()
								+ "");
					}

				} else if ("6".equals(acc223)) {
					if (cc22.getAcc223().compareTo("6") != 0) {
						throw new AppException("��λ��ϵ���Ϊ��" + cc22.getAcc220()
								+ "������ϵ��Ϣ�ѷ������������������Ϊ��" + dto.getAae017() + "");
					}
				}

			} else {
				throw new AppException("û���Ƽ���Ϣ");
			}
			dto.setAcc200(cc22.getAcc200());
			dto.setFileKey("cc20_select");
			ArrayList list1 = (ArrayList) find(con, dto, null, 0);
			if (list1 != null && list1.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc20);
				cc20.setAcb208("0");
			} else {
				throw new AppException("û�и���Ա����ְ��Ϣ");
			}

			Date d = new Date(System.currentTimeMillis());

			// 3.��¼����ְ��ʧ��
			if ("7".equals(dto.getFlag()) || "8".equals(dto.getFlag())) {
				cc22.setAcc223(dto.getFlag()); // ʧ���Ƽ�
				cc22.setAcc22e(null);// ʧ��ԭ��
			} else {
				cc22.setAcc223("2"); // ʧ���Ƽ�
				cc22.setAcc22e(dto.getAcc22e());// ʧ��ԭ��
			}
			cc22.setAcc226(dto.getAcc226());// ������
		
			cc22.setAcc229(d);// ����ʱ��
			// update
			cc22.setFileKey("cc22_update");
			modify(con, cc22, null, 0);
			if (!"7".equals(dto.getFlag()) && !"8".equals(dto.getFlag())) {
				// ����ⶳ��ְ��Ϣ�����ύ
				if (cc20.getAcb208().compareTo("0") == 0) {
					// �ⶳ��ְ��Ϣ
					cc20.setFileKey("rec01_007");
					modify(con, cc20, null, 0);
				}
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "RecommendFBLost",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}

		return response;
	}

	/**
	 * �Ƽ�ʧ��
	 * 
	 * @param request
	 *            ҵ���߼���Ĳ�����װ����
	 * @param ���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop RecommendFBdel(RequestEnvelop request) {

		ResponseEnvelop response = new ResponseEnvelop();
		HashMap map = (HashMap) request.getBody();
		Rec0105DTO dto = (Rec0105DTO) map.get("input");
		Connection con = null;
		Cc22 cc22 = new Cc22();
		Cc20 cc20 = new Cc20();
		Cc21 cc21 = new Cc21();
		Cb21 cb21 = new Cb21();
		try {
			con = DBUtil.getConnection();
			dto.setFileKey("cc22_select");
			ArrayList list = (ArrayList) find(con, dto, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc22);
				if (cc22.getAcc223() == null)
					cc22.setAcc223("0");
				if (cc22.getAcc223().compareTo("0") != 0
						&& cc22.getAcc223().compareTo("6") != 0
						&& cc22.getAcc223().compareTo("7") != 0
						&& cc22.getAcc223().compareTo("8") != 0) {
					throw new AppException("�Ƽ����Ϊ��" + cc22.getAcc220()
							+ "�����Ƽ���Ϣ�ѷ����Ƽ����������ɾ��");
				}
			} else {
				throw new AppException("û���Ƽ���Ϣ");
			}
			dto.setAcc200(cc22.getAcc200());
			dto.setFileKey("cc20_select");
			ArrayList list1 = (ArrayList) find(con, dto, null, 0);
			if (list1 != null && list1.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc20);
				cc20.setAcb208("0");
			} else {
				throw new AppException("û�и���Ա����ְ��Ϣ");
			}
			dto.setAcb210(cc22.getAcb210());
			dto.setFileKey("cb21_select");
			ArrayList listcb21 = (ArrayList) find(con, dto, null, 0);
			if (listcb21 != null && listcb21.size() > 0) {
				ClassHelper.copyProperties(listcb21.get(0), cb21);
			} else {
				throw new AppException("û�иø�λ��Ϣ");
			}
			cb21.setAcb218(Short
					.valueOf((cb21.getAcb218().intValue() - 1) + ""));
			if (cb21.getAcb218().intValue() < 0) {
				cb21.setAcb218(Short.valueOf("0"));
			}
			cc22.setFileKey("cc22_delete");
			remove(con, cc22, null, 0);
			if (cc22.getAcc223().compareTo("0") == 0) {
				// ����ⶳ��ְ��Ϣ�����ύ
				if (cc20.getAcb208().compareTo("0") == 0) {
					// �ⶳ��ְ��Ϣ
					cc20.setFileKey("rec01_007");
					modify(con, cc20, null, 0);
				}
				cb21.setFileKey("rec01_011");
				modify(con, cb21, null, 0);
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "RecommendFBLost",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}

		return response;
	}

	/**
	 * ��ѯ�����Ƽ���ϸ��Ϣ
	 * 
	 * @param request
	 *            ҵ���߼���Ĳ�����װ����
	 * @param ���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop viewRec(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Rec0105DTO input = (Rec0105DTO) map.get("input");
		Rec0103DTO output = new Rec0103DTO();
		try {
			con = DBUtil.getConnection();
			HashMap retmap = new HashMap();
			input.setFileKey("rec01_004");
			ArrayList list = (ArrayList) find(con, input, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), output);
				retmap.put("output", output);
				response.setBody(retmap);
			} else {
				throw new AppException("û���Ƽ���Ϣ");
			}

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "viewRec",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �Ƽ�������ѯ
	 * 
	 * @param request
	 *            ҵ���߼���Ĳ�����װ����
	 * @param ���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop findRecommendHistory(RequestEnvelop request) {
		return find(request, null, "find", 0);
	}
}