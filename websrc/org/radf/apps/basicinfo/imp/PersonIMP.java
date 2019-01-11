/**
 * PersonIMP.java 2008/03/24
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.basicinfo.imp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.radf.apps.commons.entity.Ac01;
import org.radf.apps.commons.entity.Cc05;
import org.radf.apps.basicinfo.dto.PersonMergeDTO;
import org.radf.apps.basicinfo.facade.PersonFacade;

import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
/**
 * ������Ϣ�������ݷ��ʲ�
 */
public class PersonIMP extends IMPSupport implements PersonFacade {
	private String className = this.getClass().getName();

	/**
	 * ���Ҹ�����ϸ��Ϣ
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop findPerson(RequestEnvelop request) {
		return find(request, null, "find", 0);
	}

	/**
	 * ������˻�����Ϣ
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop savePerson(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		String workString;// ������������䣬����LOG
		try {
			HashMap map = (HashMap) request.getBody();
			Ac01 person = (Ac01) map.get("person");
			person.setAac022(StringUtil.MnemonicWords(person.getAac003()));// �õ���Աƴ����
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// �ж���Ա�����Ƿ���ڣ�������ִ����������
			if (null == person.getAac001() || "".equals(person.getAac001())) {
				person.setFileKey("bas02_000");
				// �ж����֤���Ƿ��ظ�
				if (getCount(con, person, 0) > 0) {
					throw new AppException("�����֤���Ѵ���");
				}
				person.setAac001(CommonDB.getSequence(con, "SEQ_AAC001", 10,
						"0"));// �õ���Ա����
				person.setAae100("1");// ������ԱΪ��Ч
				person.setFileKey("ac01_insert");
				// ִ������
				store(con, person, null, 0);
				workString = "������Ա������Ϣ";
			} else {
				String menuid = (String) map.get("menuid");
				if ("alter".equals(menuid))// �ж��Ƿ�Ϊ���
				{
					person.setFileKey("bas02_001");
					// �ж����֤���Ƿ��ظ�
					if (getCount(con, person, 0) > 0) {
						throw new AppException("�����֤���Ѵ���");
					}
					person.setFileKey("ac01_select");
					Cc05 cc05 = new Cc05();
					List list = (ArrayList) find(con, person, null, 0);
					ClassHelper.copyProperties(list.get(0), cc05);// ��ѯ����Ա����Ϣ�����Ƶ������
					cc05.setAae035(DateUtil.getSystemCurrentTime());// �õ��������
					cc05.setFileKey("cc05_insert");
					cc05.setAcc050(CommonDB.getSequence(con, "SEQ_ACC050", 10,
							"0"));// �õ���Ա�����
					store(con, cc05, null, 0);
					workString = "�����Ա������Ϣ";
				} else {
					// �Ѳ����޸ĵ���Ϣ�ÿգ��������ʱ�������
					person.setAac002(null);// ���֤
					person.setAac003(null);// ����
					person.setAac004(null);// �Ա�
					person.setAac005(null);// ����
					person.setAac006(null);// ��������
					person.setAac022(null);// ��Աƴ����
					workString = "�޸���Ա������Ϣ";
				}
				person.setFileKey("ac01_update");
				// ִ���޸�
				modify(con, person, null, 0);
			}
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("aac001", person.getAac001());
			retmap.put("workString", workString);
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
	 * ��Աע��
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop writeOff(RequestEnvelop request) {
		return modify(request);
	}

	/**
	 * ��Ա�ϲ�
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop callPro(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		CallableStatement proc = null;
		String returnStr1 = null;
		String returnStr = null;
		try {
			HashMap map = (HashMap) request.getBody();
			PersonMergeDTO dto = (PersonMergeDTO) map.get("beo");

			con = DBUtil.getConnection();

			proc = con.prepareCall("{ call peoplemerge2(?,?,?,?,?) }");
			// proc.registerOutParameter(6,Types.VARCHAR);
			proc.setString(1, dto.getOldaac001());
			proc.setString(2, dto.getNewaac001());
			proc.setString(3, dto.getAae011());
			// proc.setString(4,OptionDict.getBaa110("BCCD61","1"));

			proc.registerOutParameter(4, Types.VARCHAR);
			proc.registerOutParameter(5, Types.VARCHAR);

			proc.execute();// ִ��
			returnStr1 = proc.getString(4);
			System.out.println(returnStr1);
			returnStr = proc.getString(5);
			System.out.println(returnStr);
			proc.close();

			if ("-1".equals(returnStr1)) {
				throw new Exception(returnStr);
			}
			DBUtil.commit(con);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "callPro",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ��֤��Ա�ϲ�
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop validateUnitePerson(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		CallableStatement proc = null;
		String returnStr1 = null;
		String returnStr = null;
		try {
			HashMap map = (HashMap) request.getBody();
			PersonMergeDTO dto = (PersonMergeDTO) map.get("beo");

			con = DBUtil.getConnection();

			proc = con.prepareCall("{ call peoplemerge1(?,?,?,?,?) }");
			// proc.registerOutParameter(6,Types.VARCHAR);
			proc.setString(1, dto.getOldaac001());
			proc.setString(2, dto.getNewaac001());
			proc.setString(3, dto.getAae011());
			// proc.setString(4,OptionDict.getBaa110("BCCD61","1"));

			proc.registerOutParameter(4, Types.VARCHAR);
			proc.registerOutParameter(5, Types.VARCHAR);

			proc.execute();// ִ��
			returnStr1 = proc.getString(4);
			System.out.println(returnStr1);
			returnStr = proc.getString(5);
			System.out.println(returnStr);
			proc.close();

			
			DBUtil.commit(con);

			if ("-1".equals(returnStr1)) {
				throw new AppException("��Ա�ϲ���֤���ù��̳���!");
			}
			DBUtil.commit(con);

			Map retMap = new HashMap();
			retMap.put("retstr", returnStr);
			response.setBody(retMap);
			
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "validateUnitePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ��Աɾ��
	 * 
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop delPerson(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;

		CallableStatement proc = null;
		String returnStr1 = null;
		String returnStr = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Ac01 dto = (Ac01) map.get("beo");
			proc = con.prepareCall("{ call del_people(?,?,?) }");

			proc.setString(1, dto.getAac001());// ��Ա���

			proc.registerOutParameter(2, Types.VARCHAR);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();// ִ��
			returnStr1 = proc.getString(2);
			returnStr = proc.getString(3);
			System.out.println(returnStr1);

			if ("2".equals(returnStr1)) {
				throw new Exception(returnStr);
			} else if ("0".equals(returnStr1)) {
				throw new AppException("��Ա��ҵ����Ϣ����ɾ����");
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delPerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}


	/**
	 * ��ӡ��Ա��������Ϣ
	 * 
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop printPerson(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();

			Ac01 dto = (Ac01) map.get("beo");
			dto.setFileKey("bas02_006");// ��ͬ��Ϣ
			Object objcc59 = find(con, dto, null, 0);

			dto.setFileKey("ac01_select");// ��Ա������Ϣ
			Object objac01 = find(con, dto, null, 0);

			dto.setFileKey("bas02_007");// ʧҵ�Ǽ���Ϣ
			Object objcc02 = find(con, dto, null, 0);

			dto.setFileKey("bas02_008");// ʧҵ�������Ϣ
			Object objjc10 = find(con, dto, null, 0);

			dto.setFileKey("bas02_009");// ʧҵ�𷢷���Ϣ
			Object objjc22 = find(con, dto, null, 0);

			dto.setFileKey("bas02_010");// ũ�Ϲ�������Ϣ
			Object objjc40 = find(con, dto, null, 0);

			dto.setFileKey("bas02_011");// ���˲α�
			Object objac02 = find(con, dto, null, 0);
			
			dto.setFileKey("bas02_014");// ��ҵ�Ǽ���Ϣ
			Object objcc03 = find(con, dto, null, 0);

			dto.setFileKey("bas02_012");// ���˲α�
			Object objjc01 = find(con, dto, null, 0);
			
			dto.setFileKey("bas02_089");// ����ҵ����
			Object objcc9a = find(con, dto, null, 0);
			
			dto.setFileKey("bas02_099");// һ�����پ�ҵ�Żݲ���
			Object objcc9d = find(con, dto, null, 0);
			
			dto.setFileKey("bas02_020");// ְҵ����
			Object objrec = find(con, dto, null, 0);

			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("beocc59", objcc59);
			retmap.put("beoac01", objac01);
			retmap.put("beocc02", objcc02);
			retmap.put("beojc10", objjc10);
			retmap.put("beojc22", objjc22);
			retmap.put("beojc40", objjc40);
			retmap.put("beoac02", objac02);
			retmap.put("beojc01", objjc01);
			retmap.put("beocc03", objcc03);
			retmap.put("beocc9a", objcc9a);
			retmap.put("beocc9d", objcc9d);
			retmap.put("objrec", objrec);//ְҵ����
			
			response.setBody(retmap);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "printPerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

}
