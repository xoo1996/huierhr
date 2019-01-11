/**
 * Rec06IMP.java 2008/04/07
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.recommendation.statistic.imp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.HashMap;
import java.util.Vector;

import org.radf.apps.commons.ParaUtil;
import org.radf.apps.commons.entity.Rt98;
import org.radf.apps.recommendation.statistic.dto.Rec06DTO;
import org.radf.apps.recommendation.statistic.facade.Rec06Facade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
/**
 *  ְҵ���ܹ�������������ݷ��ʲ�
 */
public class Rec06IMP extends IMPSupport implements Rec06Facade {

	private String className = this.getClass().getName();

	/**
	 * �������
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop born(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		CallableStatement proc = null;
		String returnStr1 = null;
		String returnStr = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Rec06DTO dto = (Rec06DTO) map.get("beo");
			if(dto.getBsc006()==null||"".equals(dto.getBsc006())){
				proc = con.prepareCall("{ call zyjs_year(?,?,?,?,?,?) }");
	
				proc.setString(1, dto.getBrt996());// ��������
				proc.setString(2, dto.getAae011());// ������
				proc.setString(3, dto.getAae017());// ���첿��
				proc.setString(4, dto.getCce001());// ͳ�ƻ���
				
				proc.registerOutParameter(5, Types.VARCHAR);
				proc.registerOutParameter(6, Types.VARCHAR);
			}else{
				proc = con.prepareCall("{ call zyjs_year_xn(?,?,?,?,?,?) }");
				
				proc.setString(1, dto.getBrt996());// ��������
				proc.setString(2, dto.getAae011());// ������
				proc.setString(3, dto.getAae017());// ���첿��
				proc.setString(4, dto.getBsc006());// �������
				
				proc.registerOutParameter(5, Types.VARCHAR);
				proc.registerOutParameter(6, Types.VARCHAR);
			}
			proc.execute();// ִ��
			returnStr1 = proc.getString(5);
			System.out.println(returnStr1);
			returnStr = proc.getString(6);
			System.out.println(returnStr);
			proc.close();
			if ("-1".equals(returnStr1)) {
				throw new Exception(returnStr);
			}
			//���������
			if(dto.getBsc006()==null||"".equals(dto.getBsc006())){
				dto.setFileKey("rec06_002");
			//�������
			}else{
				dto.setFileKey("rec06_005");
			}
			Object obj = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("beo", obj);
			response.setBody(retmap);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "born",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	/**
	 * �������
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop born_hz(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		CallableStatement proc = null;
		String returnStr1 = null;
		String returnStr = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Rec06DTO dto = (Rec06DTO) map.get("beo");
			proc = con.prepareCall("{ call zyjs_year_hz(?,?,?,?,?,?) }");

			proc.setString(1, dto.getBrt996());// ��������
			proc.setString(2, dto.getAae011());// ������
			proc.setString(3, dto.getAae017());// ���첿��
			proc.setString(4, dto.getCce001());// ͳ�ƻ���
			
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();// ִ��
			returnStr1 = proc.getString(5);
			System.out.println(returnStr1);
			returnStr = proc.getString(6);
			System.out.println(returnStr);
			proc.close();
			if ("-1".equals(returnStr1)) {
				throw new Exception(returnStr);
			}
			//���������
			if(dto.getBsc006()==null||"".equals(dto.getBsc006())){
				dto.setFileKey("rec06_002");
			//�������
			}else{
				dto.setFileKey("rec06_005");
			}
			Object obj = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("beo", obj);
			response.setBody(retmap);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "born_hz",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * ������ʾ
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop viewReport(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Rec06DTO dto = (Rec06DTO) map.get("beo");
			//���������
			if(dto.getBsc006()==null||"".equals(dto.getBsc006())){
				dto.setFileKey("rec06_002");
			//�������
			}else{
				dto.setFileKey("rec06_005");
			}
			Object obj = find(con, dto, null, 0);
			
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("beo", obj);
			response.setBody(retmap);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "viewReport",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	/**
	 * ������ʾ
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop viewRt98(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Rt98 dto = (Rt98) map.get("beo");
			
			dto.setFileKey("rec06_004");// ������Ϣ���
			Object obj = find(con, dto, null, 0);
            String cce001 = (String)map.get("cce001");//ͳ�ƻ���
            String brt996 = (String)map.get("brt996");//�õ�ͳ�����
            ParaUtil para=new ParaUtil();
            String aab03=para.getAab003(cce001);
            //�õ��ϼ������Ĳ�α���
            cce001=aab03.substring(0,aab03.length()-2);
            Rt98 rt98=new Rt98();
            rt98.setCce001(cce001);
            rt98.setBrt996(brt996);
            rt98.setFileKey("rec06_006");
            //����ܷ��޸ĵı�־λ
            String exist="0";
            if( getCount(con, rt98, 0) > 0){
            	exist="1";
            }
            if(aab03.length()==2){
            	exist="0";
            }
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			
			retmap.put("beo", obj);
			retmap.put("exist", exist);
			response.setBody(retmap);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "viewRt98",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * ������
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop saveReport(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Vector v99 = (Vector) map.get("beo");
             
			store(con, v99, null, 0);

			DBUtil.commit(con);
		

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveReport",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * ���ϱ�����ı���
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop saveRt98(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
            String cce001 = (String)map.get("cce001");//ͳ�ƻ���
            String brt996 = (String)map.get("brt996");//�õ�ͳ�����
            ParaUtil para=new ParaUtil();
            String aab03=para.getAab003(cce001);
            //�õ��ϼ������Ĳ�α���
            cce001=aab03.substring(0,aab03.length()-2);
            Rt98 rt98=new Rt98();
            rt98.setCce001(cce001);
            rt98.setBrt996(brt996);
            rt98.setFileKey("rec06_006");
 
            if( getCount(con, rt98, 0) > 0&&aab03.length()!=2){
            	throw new Exception("�ϼ����ϱ����޷��޸ģ�");
            }
			Vector v98 = (Vector) map.get("beo");
			store(con, v98, null, 0);
			DBUtil.commit(con);
		

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveRt98",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * �����ϱ�
     * @param	request	 ҵ���߼���Ĳ�����װ����
     * @return	���ݲ㷵�صĴ�����
	 */
	public ResponseEnvelop upcastReport(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		CallableStatement proc = null;
		String returnStr1 = null;
		String returnStr = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Rec06DTO dto = (Rec06DTO) map.get("beo");
			proc = con.prepareCall("{ call zyjs_year_upcast(?,?,?,?,?,?) }");

			proc.setString(1, dto.getBrt996());// ��������
			proc.setString(2, dto.getAae011());// ������
			proc.setString(3, dto.getAae017());// ���첿��
			proc.setString(4, dto.getCce001());// ͳ�ƻ���
			
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();// ִ��
			returnStr1 = proc.getString(5);
			System.out.println(returnStr1);
			returnStr = proc.getString(6);
			System.out.println(returnStr);
			proc.close();
			if ("-1".equals(returnStr1)) {
				throw new Exception(returnStr);
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "upcastReport",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

}