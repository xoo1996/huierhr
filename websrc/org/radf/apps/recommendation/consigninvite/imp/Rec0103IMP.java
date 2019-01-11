/**
 * Rec0103IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */

package org.radf.apps.recommendation.consigninvite.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.radf.apps.commons.ParaUtil;
import org.radf.apps.commons.entity.Ab01;
import org.radf.apps.commons.entity.Ac01;
import org.radf.apps.commons.entity.Cb20;
import org.radf.apps.commons.entity.Cb21;
import org.radf.apps.commons.entity.Cc20;
import org.radf.apps.commons.entity.Cc21;
import org.radf.apps.commons.entity.Cc22;
import org.radf.apps.recommendation.consigninvite.dto.Rec0103DTO;
import org.radf.apps.recommendation.consigninvite.dto.Rec010404DTO;
import org.radf.apps.recommendation.consigninvite.facade.Rec0103Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * ��Ա��λ�Ƽ�ʵ�ַ�����
 */
public class Rec0103IMP extends IMPSupport implements Rec0103Facade {
	private String className = this.getClass().getName();

	/**
	 * ƥ���Ƽ�<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1������cc20����ACB208����ְ״̬��='0'��������ѯ��������Ч��ְ��Ϣ����Ա<br>
     * 2������cb20����ACB208(��Ч���)='0'��ACB211(ί�п��Ƽ�����=��Ƹ����x�Ƽ�����)>0��ѯ����λ��Ϣ<br>
     * 3�������ְ��Ϣ��Ч,���Ҹ�λ��ϢҲͬʱ��Ч,���Ƽ�����ӡ�Ƽ���</tt>
	 * 
	 * @param request
	 *            ҵ���߼���Ĳ�����װ����
	 * @param ���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop Recommend(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Rec0103DTO output = new Rec0103DTO();
		Connection con = null;
		boolean acb208 = false; // �Ƿ���Ҫ������Ƹ��Ϣ��־
		boolean aca111 = true;// �ж��Ƿ������ְ������Ϣ
		HashMap map = (HashMap) request.getBody();
		Rec0103DTO input = (Rec0103DTO) map.get("input");
		Cc20 cc20 = new Cc20();
		Cb20 cb20 = new Cb20();
		Cb21 cb21 = new Cb21();
		Ac01 ac01 = new Ac01();
		Ab01 ab01 = new Ab01();
		Cc22 cc22 = new Cc22();
		Cc21 cc21 = new Cc21();
		try {
			con = DBUtil.getConnection();
			HashMap retmap = new HashMap();
			// ������Ա��Ż�ȡ��ȡ��Ϣ
			input.setFileKey("ac01_select");
			ArrayList list = (ArrayList) find(con, input, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), ac01);
			} else {
				throw new AppException("û���Ƽ���Ϣ");
			}
			input.setFileKey("cc20_select");
			ArrayList list1 = (ArrayList) find(con, input, null, 0);
			if (list1 != null && list1.size() > 0) {
				ClassHelper.copyProperties(list1.get(0), cc20);
			} else {
				throw new AppException("û�и�����ְ��Ϣ");
			}
			cb21 = getPost(con, input);

			ArrayList listcc21 = new ArrayList();
			listcc21 = getIntention(con, input);
			cc20.setFileKey("rec01_012");
			int bmcs = getCount(con, cc20, 0);
			ParaUtil pu = new ParaUtil();
			String qzbmcs = pu.getParaV("qzbmcs", "qzbmcs", "rec");
			if (bmcs + 1 >= Integer.parseInt(qzbmcs)) {
				cc20.setAcb208("1");// ������ְ��Ϣ
			} else {
				cc20.setAcb208("0");// ��������ְ��Ϣ
			}

			input.setFileKey("cb20_select");
			ArrayList list4 = (ArrayList) find(con, input, null, 0);
			if (list4 != null && list4.size() > 0) {
				ClassHelper.copyProperties(list4.get(0), cb20);
			} else {
				throw new AppException("û�е�λ������Ϣ");
			}
			input.setAab001(cb20.getAab001());
			input.setFileKey("ab01_select");
			ArrayList list5 = (ArrayList) find(con, input, null, 0);
			if (list5 != null && list5.size() > 0) {
				ClassHelper.copyProperties(list5.get(0), ab01);
			} else {
				throw new AppException("û�иõ�λ��Ϣ");
			}
			String acc220 = "0";
			if (input.getAcc220() != null) {
				acc220 = "update";
				input.setFileKey("rec01_014");
				ArrayList list6 = (ArrayList) find(con, input, null, 0);
				if (list6 != null && list6.size() > 0) {
					ClassHelper.copyProperties(list6.get(0), cc22);
				} else {
					throw new AppException("���Ƽ���Ϣ���Ƽ�״̬�Ѿ��仯��������ϵ�ɹ�״̬�������Ƽ���");
				}
				cc22.setFileKey("rec01_013");
				if (getCount(con, cc22, 0) > 0) {					
					throw new AppException("����Ա��ͬһ��λ���Ѿ������Ƽ���Ϣ�������Ƽ���");
				}
				cc22
						.setAcc221(CommonDB.getSequence(con, "SEQ_ACC221", 10,
								"0")); // �����ű��
				cc22.setAcc223("0");
			} else {
				acc220 = "insert";
				ClassHelper.copyProperties(ac01, cc22);
				ClassHelper.copyProperties(cb21, cc22);
				cc22.setAab001(ab01.getAab001());
				cc22.setAcc200(cc20.getAcc200()); // ��ְ���
				// theRecommend.setAae006(invite.getAcb20c());
				// //��ַ�������Եص�
				cc22.setAae011(input.getAae011()); // ������
				cc22.setAae036(input.getAae036()); // ����ʱ�䣬���Ƽ�ʱ��
				cc22.setAae017(input.getAae017()); // �������
				cc22.setAcb201("1");
				cc22.setFileKey("rec01_013");
				if (getCount(con, cc22, 0) > 0) {					
					throw new AppException("����Ա��ͬһ��λ���Ѿ������Ƽ���Ϣ�������Ƽ���");
				}
				int period = 7;
				// �Ƽ����:�Ƽ�
				cc22.setAcc223("0");
				cc22
						.setAcc220(CommonDB.getSequence(con, "SEQ_ACC220", 10,
								"0")); // �Ƽ����
				cc22
						.setAcc221(CommonDB.getSequence(con, "SEQ_ACC221", 10,
								"0")); // �����ű��
			}

			// update
			cc20.setFileKey("cc20_update");
			modify(con, cc20, null, 0);

			for (int i = 0; i < listcc21.size(); i++) {
				ClassHelper.copyProperties(listcc21.get(i), cc21);
				cc21.setFileKey("cc21_update");
				modify(con, cc21, null, 0);
			}

			cb21.setFileKey("cb21_update");
			modify(con, cb21, null, 0);

			if ("update".equals(acc220)) {
				cc22.setFileKey("cc22_update");
				store(con, cc22, null, 0);
			} else {
				cc22.setFileKey("cc22_insert");
				store(con, cc22, null, 0);

			}

			cc22.setFileKey("rec01_004");
			ArrayList ls = (ArrayList) find(con, cc22, null, 0);
			DBUtil.commit(con);
			ClassHelper.copyProperties(ls.get(0), output);
			output.setAca111(cb21.getAca111());
			retmap.put("output", output);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "Recommend",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ��λѡ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1������cc20����ACB208����ְ״̬��='0'��������ѯ��������Ч��ְ��Ϣ����Ա<br>
     * 2������cb20����ACB208(��Ч���)='0'��ACB211(ί�п��Ƽ�����=��Ƹ����x�Ƽ�����)>0��ѯ����λ��Ϣ<br>
     * 3�������ְ��Ϣ��Ч,���Ҹ�λ��ϢҲͬʱ��Ч,��ȷ�ϸõ�λѡ���˴���<br>
     * 4��ҳ��Ҫ֧�ֶ�ѡ,��ͬʱѡ�����<br>
     * 5����ѡ�������Ƽ���cc22����ʾΪδ��ϵ״̬</tt>
     * 
	 * 
	 * @param request
	 *            ҵ���߼���Ĳ�����װ����
	 * @param ���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop dwxr(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Rec0103DTO output = new Rec0103DTO();
		Connection con = null;
		boolean acb208 = false; // �Ƿ���Ҫ������Ƹ��Ϣ��־
		boolean aca111 = true;// �ж��Ƿ������ְ������Ϣ
		HashMap map = (HashMap) request.getBody();
		Rec0103DTO input = (Rec0103DTO) map.get("input");
		Collection collection = (Collection) map.get("collection");

		try {

			con = DBUtil.getConnection();
			if (collection == null) {
				throw new AppException("��ѡ����Ա");
			}

			Iterator it = collection.iterator();
			Vector vec = new Vector();
			while (it.hasNext()) {
				Rec010404DTO rec010404dto = new Rec010404DTO();
				ClassHelper.copyProperties(it.next(), rec010404dto);
				input.setAac001(rec010404dto.getAac001());
				input.setAcc200(rec010404dto.getAcc200());
				input.setAcc210(rec010404dto.getAcc210());
				Cc20 cc20 = new Cc20();
				Cb20 cb20 = new Cb20();
				Cb21 cb21 = new Cb21();
				Ac01 ac01 = new Ac01();
				Ab01 ab01 = new Ab01();
				Cc22 cc22 = new Cc22();
				Cc21 cc21 = new Cc21();
				// ������Ա��Ż�ȡ��ȡ��Ϣ
				input.setFileKey("ac01_select");
				ArrayList list = (ArrayList) find(con, input, null, 0);
				if (list != null && list.size() > 0) {
					ClassHelper.copyProperties(list.get(0), ac01);
				} else {
					throw new AppException("û���Ƽ���Ϣ");
				}
				input.setFileKey("cc20_select");
				ArrayList list1 = (ArrayList) find(con, input, null, 0);
				if (list1 != null && list1.size() > 0) {
					ClassHelper.copyProperties(list1.get(0), cc20);
				} else {
					throw new AppException("û�и�����ְ��Ϣ");
				}
				cb21 = getPost(con, input);

				/*
				 * ArrayList listcc21 = new ArrayList(); listcc21 =
				 * getIntention(con, input);
				 */
				cc20.setAcb208("1");// ������ְ��Ϣ
				input.setFileKey("cb20_select");
				ArrayList list4 = (ArrayList) find(con, input, null, 0);
				if (list4 != null && list4.size() > 0) {
					ClassHelper.copyProperties(list4.get(0), cb20);
				} else {
					throw new AppException("û�е�λ������Ϣ");
				}
				input.setAab001(cb20.getAab001());
				input.setFileKey("ab01_select");
				ArrayList list5 = (ArrayList) find(con, input, null, 0);
				if (list5 != null && list5.size() > 0) {
					ClassHelper.copyProperties(list5.get(0), ab01);
				} else {
					throw new AppException("û�иõ�λ��Ϣ");
				}
				ClassHelper.copyProperties(ac01, cc22);
				ClassHelper.copyProperties(cb21, cc22);
				cc22.setAab001(ab01.getAab001());
				cc22.setAcc200(cc20.getAcc200()); // ��ְ���
				// theRecommend.setAae006(invite.getAcb20c());
				// //��ַ�������Եص�
				cc22.setAae011(input.getAae011()); // ������
				cc22.setAae036(input.getAae036()); // ����ʱ�䣬���Ƽ�ʱ��
				cc22.setAae017(input.getAae017()); // �������
				cc22.setAcb201("1");
				int period = 7;
				// �Ƽ����:�Ƽ�
				cc22.setAcc223("6");
				cc22
						.setAcc220(CommonDB.getSequence(con, "SEQ_ACC220", 10,
								"0")); // �Ƽ����
				cc22.setAcc221(null); // �����ű��

				cc22.setFileKey("cc22_insert");
				vec.add(cc22);

			}
			if (vec.size() > 0) {
				store(con, vec, null, 0);
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "Recommend",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �õ���λ��Ϣ
	 */
	private Cb21 getPost(Connection con, Rec0103DTO input) throws AppException {
		Cb21 cb21 = new Cb21();
		List list;
		Integer zero = new Integer(0);
		boolean acb208 = false; // �Ƿ���Ҫ������Ƹ��Ϣ��־
		input.setFileKey("cb21_select");
		list = (ArrayList) find(con, input, null, 0);
		if (list != null || list.equals("")) {
			ClassHelper.copyProperties(list.get(0), cb21);

			if (cb21.getAcb218() == null) // ���Ƽ�����
				cb21.setAcb218(Short.valueOf("0"));
			if (cb21.getAcb211() == null) // ί�п��Ƽ�����
				cb21.setAcb211(Short.valueOf("0"));
			if (cb21.getAcb21a() == null) // �ѳɹ�����
				cb21.setAcb21a(Short.valueOf("0"));
			if (cb21.getAcb21d() == null) // ί����Ƹ����
				cb21.setAcb21d(Short.valueOf("0"));
			if (cb21.getAca111() == null) // ��Ƹ����
				throw new AppException("û����Ƹ��λ�Ĺ�����Ϣ���޷������Ƽ�������ϵͳ����Ա��ϵ��");
			if (cb21.getAcb208().compareTo("0") == 0)
				acb208 = true;
			if (!acb208)
				input.setInviteAcb208(true);
			if (cb21.getAcb210().compareTo(input.getAcb210()) == 0)
				chkPost(input, cb21);
		} else {
			throw new AppException("��ȡ��Ƹ���Ϊ��" + input.getAcb200()
					+ "���ġ��ո���Ϣ��ʱ�������ݿ����");
		}
		return cb21;
	}

	/**
	 * ��鵽��λ��Ϣ״̬
	 */
	private void chkPost(Rec0103DTO input, Cb21 cb21) throws AppException {
		int residualRecommand = 0;
		// 1.�Ƿ񶳽�
		if (cb21.getAcb208().compareTo("0") != 0)
			throw new AppException("�ù��ֵĿ�λ��Ϣ�Ѷ��ᡣ�޷������Ƽ���");

		// 2.ʣ���Ƽ���>0
		residualRecommand = (cb21.getAcb211().intValue() - cb21.getAcb218()
				.intValue());
		// 3.�Ƽ����Ƿ�ok
		if (residualRecommand <= 0)
			throw new AppException("��λ��Ϣ�ĸ�λ�ѵ����Ƽ��������Ƽ����");
		// 4.�����Ƽ���
		int acb218 = cb21.getAcb218().intValue() + 1;
		cb21.setAcb218(Short.valueOf(acb218 + ""));// ���ӿ�λ���Ƽ���

		// 6.�жϱ����Ƽ��󣬸ÿո��Ƿ��п�λ
		residualRecommand = (cb21.getAcb211().intValue() - cb21.getAcb218()
				.intValue());
		// 7.���û�п�λ������
		if (residualRecommand == 0)
			cb21.setAcb208("1");
		// 9.��������Ϣ����inputdto��
		input.setAca111(cb21.getAca111());
	}

	/**
	 * �жϸ�λ����ְ�����ƥ�����
	 */
	private ArrayList getIntention(Connection con, Rec0103DTO input)
			throws AppException {
		Cc21 cc21 = new Cc21();
		Object[] obj;
		Iterator it;
		ArrayList list;
		boolean aca111 = true; // �жϸ���Ա����Ӧ���ֵ���ְ����ı�־
		ArrayList result = new ArrayList();
		input.setFileKey("rec01_002");
		list = (ArrayList) find(con, input, null, 0);
		if (list != null || list.equals("")) {
			for (int j = 0; j < list.size(); j++) {
				ClassHelper.copyProperties(list.get(j), cc21);
				if (cc21.getAca111() != null) { // רҵ����
					aca111 = false;
				}
				result.add(cc21);
			}
			if (aca111)
				throw new AppException("û�иù��ֵ���ְ�����޷������Ƽ���");
		} else {
			throw new AppException("��ȡ��Ƹ���Ϊ��" + input.getAcb200()
					+ "���ġ��ո���Ϣ��ʱ�������ݿ����");
		}
		return result;
	}

}