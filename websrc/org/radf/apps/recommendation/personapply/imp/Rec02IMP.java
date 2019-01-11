/**
 * Rec02IMP.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */

package org.radf.apps.recommendation.personapply.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.radf.apps.commons.entity.Ac01;
import org.radf.apps.commons.entity.Cc20;
import org.radf.apps.commons.entity.Cc21;
import org.radf.apps.commons.entity.Cc22;
import org.radf.apps.recommendation.personapply.dto.Rec0201DTO;
import org.radf.apps.recommendation.personapply.facade.Rec02Facade;
import org.radf.apps.recommendation.personapply.form.Rec0201Form;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * ��ְ�Ǽǹ���
 */
public class Rec02IMP extends IMPSupport implements Rec02Facade {
	private String className = this.getClass().getName();

	/**
	 * ������ְ��Ըҳ�棬��ѯ��ĳһ��Ա�������Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1��������ְ��Ϣ�Ƿ���Ч,��ѯ����</tt>
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findPersonInfo(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		boolean acb208 = true;
		boolean acc223 = true;
		boolean aae017 = true;
		HashMap map = (HashMap) request.getBody();
		Rec0201DTO dto = (Rec0201DTO) map.get("beo");
		Rec0201DTO dto2 = new Rec0201DTO();
		Cc20 cc20 = new Cc20();
		Cc22 cc22 = new Cc22();
		try {
			con = DBUtil.getConnection();
			cc20.setAcb208(null);
			dto.setFileKey("rec02_002");
			List list1 = (ArrayList) find(con, dto, null, 0);
			if (list1 != null) {
				for (int i = 0; i < list1.size(); i++) {
					ClassHelper.copyProperties(list1.get(i), cc20);
					//�ַ���ȣ�����0
					if (cc20.getAcb208()!=null&&cc20.getAcb208().compareTo("0") == 0) {
						dto2.setAae017(cc20.getAae017());
						dto2.setAce014(cc20.getAce014());
						dto2.setAcc200(cc20.getAcc200());
						acb208 = false;
					}
				}
				if (!acb208) {// δ����
					dto2.setFileKey("rec02_003");
					List lss = (ArrayList) find(con, dto2, null, 0);
					if (lss != null) {
						ClassHelper.copyProperties(lss.get(0), dto2);
						if (!(dto2.getAae017().equals(dto.getAae017()))) {
							throw new AppException(
									"����Ա�������Ǽǻ����Ǽǹ������һ�û���Ƽ������ܱ༭��");
						}
						dto.setFileKey("rec02_004");
						List list = (ArrayList) find(con, dto, null, 0);
						if (list != null) {
							for (int i = 0; i < list.size(); i++) {
								if (i == 0) {
									ClassHelper
											.copyProperties(list.get(i), dto);
								}
								if (i == 1) {
									ClassHelper.copyProperties(list.get(i),
											dto2);
									dto.setAca111a(dto2.getAca111());
									dto.setAca112a(dto2.getAca112());
									dto.setAcc210a(dto2.getAcc210());
								}
								if (i == 2) {
									ClassHelper.copyProperties(list.get(i),
											dto2);
									dto.setAca111b(dto2.getAca111());
									dto.setAca112b(dto2.getAca112());
									dto.setAcc210b(dto2.getAcc210());
								}
							}
						}
					} else {
						dto.setFileKey("ac01_select");
						List list = (ArrayList) find(con, dto, null, 0);
						ClassHelper.copyProperties(list.get(0), dto);
					}
				} else {// ����
					dto.setFileKey("rec02_005");
					List ls = (ArrayList) find(con, dto, null, 0);
					if (ls != null) {
						for (int i = 0; i < ls.size(); i++) {
							ClassHelper.copyProperties(ls.get(i), cc22);
							if (cc22.getAcc223().equals("0")) {// ��������Ƽ��еġ�����
								cc22.setFileKey("rec02_006");
								List list = (ArrayList) find(con, cc22, null, 0);
								ClassHelper.copyProperties(list.get(0), cc20);
								if (!(cc20.getAae017().equals(dto.getAae017()))) {
									aae017 = false;
								}
								acc223 = false;
							}
						}
						if (!acc223) {
							if (!aae017) {
								throw new AppException(
										"����ְ��Ϣ�����������Ǽǹ����Ҵ����Ƽ�״̬�����ܱ༭��");
							} else {
								throw new AppException("����Ա�����Ƽ��У��������ӣ�");
							}
						}
					}
					dto.setFileKey("rec02_007");
					List list = (ArrayList) find(con, dto, null, 0);
					ClassHelper.copyProperties(list.get(0), dto);
				}
			} else {
				dto.setFileKey("rec02_007");
				List list = (ArrayList) find(con, dto, null, 0);
				ClassHelper.copyProperties(list.get(0), dto);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findPersonInfo",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.closeConnection(con);
		}
		HashMap retmap = new HashMap();
		retmap.put("beo", dto);
		response.setBody(retmap);
		return response;
	}

	/**
	 * �鿴������ְ��Ϣ��ʼ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1����ʾ��ǰ��Ч����ְ��Ϣ</tt>
     * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewPersonApply(RequestEnvelop request) {
		return find(request);
	}

	/**
	 * �޸ĸ�����ְ��Ϣ��ʼ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1���޸ĵ�ǰ��Ч����ְ��Ϣ</br>
     * 2��������Ƽ��в����޸�</tt>
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modPersonApply(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Rec0201Form form = (Rec0201Form) map.get("beo");
		Ac01 ac01 = new Ac01();
		Cc20 cc20 = new Cc20();
		try {
			ClassHelper.copyProperties(form, ac01);
			ClassHelper.copyProperties(form, cc20);
			ac01.setFileKey("ac01_update");
			cc20.setFileKey("cc20_update");
			con = DBUtil.getConnection();
			modify(con, ac01, null, 0);
			modify(con, cc20, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modPersonApply",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �����¼ӵĻ�����Ϣ����ӣ�������ְ��Ϣ����ӣ�������ְ��������<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1�����������Ϣ����ְ��Ϣ�������ڵĸ�����ְ</tt>
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop savePerson(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Rec0201DTO dto = (Rec0201DTO) map.get("beo");
		String type = (String) map.get("type");
		Ac01 ac01 = new Ac01();
		Cc20 cc20 = new Cc20();
		Cc21 cc21 = new Cc21();
		try {
			int period = 7;
			if (null == dto.getAce014()) {
				dto.setAce014(DateUtil.getStepDay(DateUtil
						.getSystemCurrentTime(), period));// ��Ч����
			}
			ClassHelper.copyProperties(dto, ac01);
			ac01.setFileKey("bas02_000");
			ClassHelper.copyProperties(dto, cc20);
			cc20.setAcb208("0");
			ClassHelper.copyProperties(dto, cc21);

			// cc20.setAce014(DateUtil.getStepDay(DateUtil.getSystemCurrentTime(),
			// period));// ��Ч����
			// cc21.setAce014(DateUtil.getStepDay(DateUtil.getSystemCurrentTime(),
			// period));// ��Ч����
			con = DBUtil.getConnection();
			// �ж����֤���Ƿ��ظ�
			if (getCount(con, ac01, 0) > 0) {
				throw new AppException("�����֤���Ѵ���");
			}
			// �趨ũ�񹤱�ʶ
			if (!(ac01.getAac009() == null || "".equals(ac01.getAac009()))) {
				if ((ac01.getAac009()).substring(0, 1).equals("2")) {
					ac01.setAac028("1");
				} else {
					ac01.setAac028("0");
				}
			}
			int j;
			if (dto.getAca111a() != null && dto.getAca111b() != null) {
				j = 3;
			} else if ((dto.getAca111a() == null && dto.getAca111b() != null)
					|| (dto.getAca111a() != null && dto.getAca111b() == null)) {
				j = 2;
			} else {
				j = 1;
			}
			cc21.setFileKey("rec02_011");
			int i = getCount(con, cc21, 0);

			ac01.setAac001(CommonDB.getSequence(con, "SEQ_AAC001", 10, "0"));// �õ���Ա����
			ac01.setAae100("1");// ������ԱΪ��Ч
			ac01.setFileKey("ac01_insert");
			store(con, ac01, null, 0);// ������Ա��������Ϣ
			cc20.setAac001(ac01.getAac001());
			cc21.setAac001(ac01.getAac001());
			cc20.setAcc200(CommonDB.getSequence(con, "SEQ_ACC200", 10, "0"));
			cc20.setFileKey("cc20_insert");
			store(con, cc20, null, 0);// ������ְ��Ϣ
			cc21.setAcc200(cc20.getAcc200());

			cc21.setAcc210(CommonDB.getSequence(con, "SEQ_ACC210", 10, "0"));
			cc21.setFileKey("cc21_insert");
			store(con, cc21, null, 0);
			// dto.setAcc210(cc21.getAcc210());
			// �ڶ���Ը
			cc21.setAca111(dto.getAca111a());
			cc21.setAca112(dto.getAca112a());
			cc21.setAcc210(CommonDB.getSequence(con, "SEQ_ACC210", 10, "0"));
			cc21.setFileKey("cc21_insert");
			store(con, cc21, null, 0);
			// ������Ը
			cc21.setAca111(dto.getAca111b());
			cc21.setAca112(dto.getAca112b());
			cc21.setAcc210(CommonDB.getSequence(con, "SEQ_ACC210", 10, "0"));
			cc21.setFileKey("cc21_insert");
			store(con, cc21, null, 0);

			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			dto.setAac001(ac01.getAac001());
			dto.setAcc200(cc20.getAcc200());
			retmap.put("beo", dto);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �޸ĸ�����ְ��Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1���޸ĵ�ǰ��Ч����ְ��Ϣ</br>
     * 2��������Ƽ��в����޸�</tt>
     * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modPerson(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		Ac01 ac01 = new Ac01();
		Cc20 cc20 = new Cc20();
		Cc21 cc21 = new Cc21();

		try {
			HashMap map = (HashMap) request.getBody();
			Rec0201DTO dto = (Rec0201DTO) map.get("beo");
			String type = (String) map.get("type");
			int period = 7;
			if (null == dto.getAce014()) {
				dto.setAce014(DateUtil.getStepDay(DateUtil
						.getSystemCurrentTime(), period));// ��Ч����
			}
			ClassHelper.copyProperties(dto, ac01);
			ac01.setFileKey("bas02_001");
			ClassHelper.copyProperties(dto, cc20);
			cc20.setAcb208("0");
			ClassHelper.copyProperties(dto, cc21);

			con = DBUtil.getConnection();
			// �ж����֤���Ƿ��ظ�
			if (getCount(con, ac01, 0) > 0) {
				throw new AppException("�����֤���Ѵ���");
			}
			// �趨ũ�񹤱�ʶ
			if (!(ac01.getAac009() == null || "".equals(ac01.getAac009()))) {
				if ((ac01.getAac009()).substring(0, 1).equals("2")) {
					ac01.setAac028("1");
				} else {
					ac01.setAac028("0");
				}
			}

			ac01.setFileKey("ac01_update");
			modify(con, ac01, null, 0);// �޸���Ա��������Ϣ
			int j;
			if (!("".equals(dto.getAca111a()))
					&& !("".equals(dto.getAca111b()))) {
				j = 3;
			} else if (("".equals(dto.getAca111a()) && !("".equals(dto
					.getAca111b())))
					|| (!("".equals(dto.getAca111a())) && "".equals(dto
							.getAca111b()))) {
				j = 2;
			} else {
				j = 1;
			}
			cc21.setFileKey("rec02_011");
			int i = getCount(con, cc21, 0);
			/*
			 * if(cc20.getBcb991().equals("1")&&cc21.getBcb992().equals("0")){
			 * if(i>=count||(i+j)>count){ throw new
			 * AppException("����췢������Ϣ�Ѿ��ﵽ����,����췢����"); } }
			 */
			// �ж���ְ��Ϣ��������Ƿ�Ϊ�գ�����ִ�в��룬�ǿ���ִ���޸Ĳ���
			if (cc20.getAcc200() == null || "".equals(cc20.getAcc200())) {
				cc20
						.setAcc200(CommonDB.getSequence(con, "SEQ_ACC200", 10,
								"0"));
				cc20.setFileKey("cc20_insert");
				store(con, cc20, null, 0);// ������ְ��Ϣ
			} else {
				cc20.setFileKey("cc20_update");
				modify(con, cc20, null, 0);// ������ְ��Ϣ
			}
			cc21.setAcc200(cc20.getAcc200());

			if (dto.getAcc210() == null || "".equals(dto.getAcc210())) {
				cc21
						.setAcc210(CommonDB.getSequence(con, "SEQ_ACC210", 10,
								"0"));
				cc21.setFileKey("cc21_insert");
				store(con, cc21, null, 0);
			} else {
				cc21.setFileKey("cc21_update");
				modify(con, cc21, null, 0);
			}
			dto.setAcc210(cc21.getAcc210());
			// �ڶ���Ը
			cc21.setAca111(dto.getAca111a());
			cc21.setAca112(dto.getAca112a());
			if (dto.getAcc210a() == null || "".equals(dto.getAcc210a())) {
				cc21
						.setAcc210(CommonDB.getSequence(con, "SEQ_ACC210", 10,
								"0"));
				cc21.setFileKey("cc21_insert");
				store(con, cc21, null, 0);
			} else {
				cc21.setAcc210(dto.getAcc210a());
				cc21.setFileKey("cc21_update");
				modify(con, cc21, null, 0);
			}
			// ������Ը
			cc21.setAca111(dto.getAca111b());
			cc21.setAca112(dto.getAca112b());
			if (dto.getAcc210b() == null || "".equals(dto.getAcc210b())) {
				cc21
						.setAcc210(CommonDB.getSequence(con, "SEQ_ACC210", 10,
								"0"));
				cc21.setFileKey("cc21_insert");
				store(con, cc21, null, 0);
			} else {
				cc21.setAcc210(dto.getAcc210b());
				cc21.setFileKey("cc21_update");
				modify(con, cc21, null, 0);
			}

			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			dto.setAcc200(cc20.getAcc200());
			retmap.put("beo", dto);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modPerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}

		return response;
	}

	/**
	 * �޸ĸ�����ְ��Ϣ��ʼ��<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1���޸ĵ�ǰ��Ч����ְ��Ϣ</br>
     * 2��������Ƽ��в����޸�</tt>
     * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findPersonApply(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Rec0201Form dto = (Rec0201Form) map.get("beo");
		Cc20 cc20 = new Cc20();
		boolean acb208 = true;
		try {
			ClassHelper.copyProperties(dto, cc20);
			con = DBUtil.getConnection();
			cc20.setAcb208("0");
			cc20.setFileKey("rec02_002");
			List list = (ArrayList) find(con, cc20, null, 0);
			if (list != null) {
				ClassHelper.copyProperties(list.get(0), cc20);
				if (cc20.getAcb208().compareTo("0") == 0) {
					cc20.setAae017(cc20.getAae017());
					cc20.setAce014(cc20.getAce014());
					cc20.setAcc200(cc20.getAcc200());
					acb208 = false;
				}
				cc20.setFileKey("rec02_003");
				List lss = (ArrayList) find(con, cc20, null, 0);
				if (lss == null) {
					throw new AppException("����Ա���Ǽǵ���ְ��Ϣ�ѹ��ڲ��Զ�ע���������޸ģ������µǼǣ�");
				} else {
					ClassHelper.copyProperties(lss.get(0), cc20);
					if (!(cc20.getAae017().equals(dto.getAae017()))) {
						throw new AppException("����ְ��Ϣ�������ṹ�Ǽǵ���Ϣ�������޸ģ�");
					} else {
						cc20.setFileKey("rec02_012");
						List ls = (ArrayList) find(con, cc20, null, 0);
						ClassHelper.copyProperties(ls.get(0), dto);
						HashMap retmap = new HashMap();
						retmap.put("beo", dto);
						response.setBody(retmap);
					}
				}

			} else {
				throw new AppException("����Ա����ְ��Ϣ������ְ��Ϣ�Ѿ�ע���������޸ģ������µǼǣ�");
			}

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findPersonApply",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ɾ��������ְ��Ϣ<br>
     * <tt><font color="#FF0000"><B>ҵ�����Ҫ��</B></font><br>
     * 1��ɾ����ְ��Ϣ</br>
     * 2��������Ƽ��в���ɾ��</tt>
     * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delPersonApply(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		HashMap map = (HashMap) request.getBody();
		Cc20 cc20 = (Cc20) map.get("beo");
		Cc21 cc21 = new Cc21();
		try {
			cc20.setAcb208("0");
			cc20.setFileKey("rec02_002");
			con = DBUtil.getConnection();
			List list = (ArrayList) find(con, cc20, null, 0);
			if (list == null) {
				throw new AppException("����Ա����Ч����ְ��Ϣ���޷�ɾ����");
			} else {
				ClassHelper.copyProperties(list.get(0), cc20);
			}
			cc20.setFileKey("rec02_013");
			if (getCount(con, cc20, 0) > 0) {
				throw new AppException("����Ա�Ѿ����Ƽ�����������ɾ��");
			}
			cc20.setFileKey("rec02_014");
			remove(con, cc20, null, 0);
			cc20.setFileKey("cc20_delete");
			remove(con, cc20, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
