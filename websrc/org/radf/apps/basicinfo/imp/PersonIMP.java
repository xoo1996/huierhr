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
 * 个人信息管理数据访问层
 */
public class PersonIMP extends IMPSupport implements PersonFacade {
	private String className = this.getClass().getName();

	/**
	 * 查找个人详细信息
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop findPerson(RequestEnvelop request) {
		return find(request, null, "find", 0);
	}

	/**
	 * 保存个人基本信息
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop savePerson(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		String workString;// 所做操作的语句，用于LOG
		try {
			HashMap map = (HashMap) request.getBody();
			Ac01 person = (Ac01) map.get("person");
			person.setAac022(StringUtil.MnemonicWords(person.getAac003()));// 得到人员拼音码
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// 判断人员内码是否存在，不存在执行新增操作
			if (null == person.getAac001() || "".equals(person.getAac001())) {
				person.setFileKey("bas02_000");
				// 判断身份证号是否重复
				if (getCount(con, person, 0) > 0) {
					throw new AppException("该身份证号已存在");
				}
				person.setAac001(CommonDB.getSequence(con, "SEQ_AAC001", 10,
						"0"));// 得到人员内码
				person.setAae100("1");// 设置人员为有效
				person.setFileKey("ac01_insert");
				// 执行增加
				store(con, person, null, 0);
				workString = "新增人员基本信息";
			} else {
				String menuid = (String) map.get("menuid");
				if ("alter".equals(menuid))// 判断是否为变更
				{
					person.setFileKey("bas02_001");
					// 判断身份证号是否重复
					if (getCount(con, person, 0) > 0) {
						throw new AppException("该身份证号已存在");
					}
					person.setFileKey("ac01_select");
					Cc05 cc05 = new Cc05();
					List list = (ArrayList) find(con, person, null, 0);
					ClassHelper.copyProperties(list.get(0), cc05);// 查询出人员表信息并复制到变更表
					cc05.setAae035(DateUtil.getSystemCurrentTime());// 得到变更日期
					cc05.setFileKey("cc05_insert");
					cc05.setAcc050(CommonDB.getSequence(con, "SEQ_ACC050", 10,
							"0"));// 得到人员变更码
					store(con, cc05, null, 0);
					workString = "变更人员基本信息";
				} else {
					// 把不能修改的信息置空，以免更新时冲掉数据
					person.setAac002(null);// 身份证
					person.setAac003(null);// 姓名
					person.setAac004(null);// 性别
					person.setAac005(null);// 民族
					person.setAac006(null);// 出生日期
					person.setAac022(null);// 人员拼音码
					workString = "修改人员基本信息";
				}
				person.setFileKey("ac01_update");
				// 执行修改
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
	 * 人员注销
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop writeOff(RequestEnvelop request) {
		return modify(request);
	}

	/**
	 * 人员合并
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
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

			proc.execute();// 执行
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
	 * 验证人员合并
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
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

			proc.execute();// 执行
			returnStr1 = proc.getString(4);
			System.out.println(returnStr1);
			returnStr = proc.getString(5);
			System.out.println(returnStr);
			proc.close();

			
			DBUtil.commit(con);

			if ("-1".equals(returnStr1)) {
				throw new AppException("人员合并验证调用过程出错!");
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
	 * 人员删除
	 * 
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
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

			proc.setString(1, dto.getAac001());// 人员编号

			proc.registerOutParameter(2, Types.VARCHAR);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();// 执行
			returnStr1 = proc.getString(2);
			returnStr = proc.getString(3);
			System.out.println(returnStr1);

			if ("2".equals(returnStr1)) {
				throw new Exception(returnStr);
			} else if ("0".equals(returnStr1)) {
				throw new AppException("人员有业务信息不能删除！");
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
	 * 打印人员的所有信息
	 * 
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop printPerson(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();

			Ac01 dto = (Ac01) map.get("beo");
			dto.setFileKey("bas02_006");// 合同信息
			Object objcc59 = find(con, dto, null, 0);

			dto.setFileKey("ac01_select");// 人员基本信息
			Object objac01 = find(con, dto, null, 0);

			dto.setFileKey("bas02_007");// 失业登记信息
			Object objcc02 = find(con, dto, null, 0);

			dto.setFileKey("bas02_008");// 失业金审核信息
			Object objjc10 = find(con, dto, null, 0);

			dto.setFileKey("bas02_009");// 失业金发放信息
			Object objjc22 = find(con, dto, null, 0);

			dto.setFileKey("bas02_010");// 农合工补助信息
			Object objjc40 = find(con, dto, null, 0);

			dto.setFileKey("bas02_011");// 个人参保
			Object objac02 = find(con, dto, null, 0);
			
			dto.setFileKey("bas02_014");// 就业登记信息
			Object objcc03 = find(con, dto, null, 0);

			dto.setFileKey("bas02_012");// 个人参保
			Object objjc01 = find(con, dto, null, 0);
			
			dto.setFileKey("bas02_089");// 灵活就业补贴
			Object objcc9a = find(con, dto, null, 0);
			
			dto.setFileKey("bas02_099");// 一次性再就业优惠补助
			Object objcc9d = find(con, dto, null, 0);
			
			dto.setFileKey("bas02_020");// 职业介绍
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
			retmap.put("objrec", objrec);//职业介绍
			
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
