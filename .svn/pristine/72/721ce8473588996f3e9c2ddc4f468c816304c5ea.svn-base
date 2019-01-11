/**
 * EmployerIMP.java 2008/03/24
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

import org.apache.log4j.Logger;

import org.radf.apps.basicinfo.dto.EmployMergeDTO;
import org.radf.apps.basicinfo.facade.EmployerFacade;
import org.radf.apps.commons.entity.Ab01;
import org.radf.apps.commons.entity.Cb02;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;

/**
 * 单位管理数据访问层
 */
public class EmployerIMP extends org.radf.plat.util.imp.IMPSupport implements
		EmployerFacade {

	private String className = this.getClass().getName();

	private Logger a;

	/**
	 * 公共查询
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop findCommon(RequestEnvelop request) {
		return find(request);
	}
	
	/**
	 * 增加单位信息
	 * 
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop addEmp(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Ab01 dto = (Ab01) map.get("empdto");

			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			// 根据单位名称或取单位信息，判断是否重名的单位名称
			dto.setFileKey("bas01_006");
			if (getCount(con, dto, 0) > 0) {
				throw new AppException("存在单位名称");
			}
			// 获取单位编号
			dto.setAab001(CommonDB.getSequence(con, "SEQ_AAB001", 10, "0"));
			// 若组织机构代码输入为空，则自动生成，否则先判断是否存在
			if (dto.getAab003() == null || dto.getAab003().equals(""))
				dto.setAab003(CommonDB.dogetinvcmpcd(con, "SEQ_AAB003"));
			else {
				// 根据组织机构代码获取单位信息，判断是否存在组织机构代码
				dto.setFileKey("bas01_007");
				if (getCount(con, dto, 0) > 0) {
					throw new AppException("存在组织机构代码");
				}
			}
			// 假如单位名称不为空生成拼音码
			if (dto.getAab004() != null) {
				dto.setAab043(StringUtil.MnemonicWords(dto.getAab004()));
			}
			// (6)
			if (!"20".equals(dto.getAab019())) {
				dto.setAab057(null);
			}
			dto.setAae119("1");

			// 保存单位信息
			dto.setFileKey("ab01_insert");
			store(con, dto, null, 0);// 增加ab01记录
			DBUtil.commit(con);
			// 组装返回参数
			HashMap retmap = new HashMap();
			retmap.put("AB01", dto);
			response.setBody(retmap);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			// 注意提换当前方法名字queryEnterprise
			response.setHead(ExceptionSupport(className, "addEmp",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 查看单位信息
	 * 
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop retriveEmp(RequestEnvelop request) {
		//return find(request, null, "find", 0);
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Ab01 dto = (Ab01) map.get("beo");
			EmployMergeDTO ldywdto=new EmployMergeDTO();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getAab001() == null || dto.getAab001().equals("")) {
				throw new AppException("该单位未登记");
			} else {
				// update
				Object obj = find(con, dto, null, 0);
				ldywdto.setAab001(dto.getAab001());
				ldywdto.setFileKey("bas02_013");
				List list = (ArrayList)find(con, ldywdto, null, 0);
				if (list != null && list.size() > 0) {
					ClassHelper.copyProperties(list.get(0), ldywdto);

				}
				HashMap retmap = new HashMap();
				retmap.put("beo", obj);
				retmap.put("ldywbeo",ldywdto.getDwldyw());
				response.setBody(retmap);


			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
			System.out.println(ae.toString());
			ae.printStackTrace();
			System.out.println(ae);
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "retriveEmp",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
		
	}

	

	/**
	 * 更新单位基本信息
	 * 
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop updateEmp(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Ab01 dto = (Ab01) map.get("emp");
			Cb02 cb02 = new Cb02();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getAab001() == null || dto.getAab001().equals("")) {
				throw new AppException("该单位未登记");
			} else {
				// 根据单位名称或取单位信息，判断是否重名的单位名称
				dto.setFileKey("bas01_005");
				if (getCount(con, dto, 0) > 0) {
					throw new AppException("存在单位名称");
				}
				// 根据单位组织机构代码，判断是否存在重名的组织机构代码
				dto.setFileKey("bas01_004");
				if (getCount(con, dto, 0) > 0) {
					throw new AppException("存在组织机构代码");
				}
				// 增加单位变更信息
				dto.setFileKey("cb02_select");
				ClassHelper.copyProperties(dto, cb02);
				cb02
						.setAcb020(CommonDB.getSequence(con, "SEQ_ACB020", 10,
								"0"));// 得到单位变更信息表序号
				cb02.setAae035(DateUtil.getSystemCurrentTime());// 得到变更日期
				cb02.setFileKey("cb02_insert");
				store(con, cb02, null, 0);
				// update
				dto.setFileKey("ab01_update");
				// 执行修改
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateEmp",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 单位信息修改
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
	public ResponseEnvelop alterEmp(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Ab01 dto = (Ab01) map.get("emp");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getAab001() == null || dto.getAab001().equals("")) {
				throw new AppException("该单位未登记");
			} else {
				// 根据单位名称或取单位信息，判断是否重名的单位名称
				dto.setFileKey("bas01_005");
				if (getCount(con, dto, 0) > 1) {
					throw new AppException("存在单位名称");
				}
				// 根据单位组织机构代码，判断是否存在重名的组织机构代码
			//dto.setFileKey("bas01_004");
			//	if (getCount(con, dto, 0) > 0) {
			//		throw new AppException("存在组织机构代码");
			//	}
				// update
				dto.setFileKey("ab01_update");
				// 执行修改
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "alterEmp",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return null;
	}

	public ResponseEnvelop writeOff(RequestEnvelop request) {
		return modify(request);
	}
	
	/**
     * 调用存储过程,单位合并
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
     */
    public ResponseEnvelop callPro(RequestEnvelop request)
    {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        CallableStatement proc = null;
        String returnStr1=null;
        String returnStr=null;
        try
        {
            HashMap map = (HashMap) request.getBody();
			EmployMergeDTO dto = (EmployMergeDTO) map.get("beo");
            
            con = DBUtil.getConnection();

            proc = con.prepareCall("{ call cop_merge2(?,?,?,?,?) }");
                // proc.registerOutParameter(6,Types.VARCHAR);
            proc.setString(1, dto.getOldaab001());
            proc.setString(2, dto.getNewaab001());
            proc.setString(3, dto.getAae011());
           // proc.setString(4,OptionDict.getBaa110("BCCD61","1"));            
            
            proc.registerOutParameter(4,Types.VARCHAR);
            proc.registerOutParameter(5,Types.VARCHAR);
           

            proc.execute();//执行
            returnStr1=proc.getString(4);
            System.out.println(returnStr1);
            returnStr=proc.getString(5);
            System.out.println(returnStr);
            proc.close();
            
            if("-1".equals(returnStr1)){
                throw new Exception(returnStr);
            }
            DBUtil.commit(con);
        }
        catch (Exception ex)
        {
            response.setHead(ExceptionSupport(
                                              className,
                                              "callPro",
                                              ManageErrorCode.SQLERROR,
                                              ex.getMessage(),
                                              request.getHead()));
        }
        finally
        {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
	/**
     * 删除单位,如果有业务信息则删除出错
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
     */
	public ResponseEnvelop delEmployer(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		
		Connection con = null;
		String newdate = DateUtil.getSystemCurrentTime().toString();
		CallableStatement proc = null;
        String returnStr1=null;
        String returnStr=null;
		try {
		con = DBUtil.getConnection();
		HashMap map = (HashMap) request.getBody();
		Ab01 dto = (Ab01)map.get("beo");
		proc = con.prepareCall("{ call del_company(?,?,?) }");
		
        proc.setString(1, dto.getAab001());//单位编号
 
        proc.registerOutParameter(2,Types.VARCHAR);
        proc.registerOutParameter(3,Types.VARCHAR);
        proc.execute();//执行
        returnStr1=proc.getString(2);
        returnStr=proc.getString(3);
        System.out.println(returnStr1);

        if("2".equals(returnStr1)){
            throw new Exception(returnStr);
        }else if("0".equals(returnStr1)){
        	throw new AppException("单位有业务信息不能删除！");
        }
        DBUtil.commit(con);

	} catch (AppException ae) {
		response
				.setHead(ExceptionSupport(className, ae, request.getHead()));
	} catch (Exception ex) {
		response.setHead(ExceptionSupport(className, "saveLabconfer",
				ManageErrorCode.SQLERROR, ex.getMessage(), request
						.getHead()));
	} finally {
		DBUtil.rollback(con);
		DBUtil.closeConnection(con);
	}
	return response;	
	}
	
	
	/**
     * 验证单位合并
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
     */
    public ResponseEnvelop validateUniteEmploy(RequestEnvelop request)
    {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        CallableStatement proc = null;
        String returnStr1=null;
        String returnStr=null;
        try
        {
            HashMap map = (HashMap) request.getBody();
			EmployMergeDTO dto = (EmployMergeDTO) map.get("beo");
            
            con = DBUtil.getConnection();

            proc = con.prepareCall("{ call cop_merge1(?,?,?,?,?) }");
                // proc.registerOutParameter(6,Types.VARCHAR);
            proc.setString(1, dto.getOldaab001());
            proc.setString(2, dto.getNewaab001());
            proc.setString(3, dto.getAae011());
           // proc.setString(4,OptionDict.getBaa110("BCCD61","1"));            
            
            proc.registerOutParameter(4,Types.VARCHAR);
            proc.registerOutParameter(5,Types.VARCHAR);
           

            proc.execute();//执行
            returnStr1=proc.getString(4);
            System.out.println(returnStr1);
            returnStr=proc.getString(5);
            System.out.println(returnStr);
            proc.close();
            
            if("-1".equals(returnStr1)){
                throw new AppException("单位验证调用过程出错!");
            }			
            DBUtil.commit(con);
			
			Map retMap = new HashMap();
			retMap.put("retstr",returnStr);
			response.setBody(retMap);
        }
		catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		}
        catch (Exception ex)
        {
            response.setHead(ExceptionSupport(
                                              className,
                                              "validateUniteEmploy",
                                              ManageErrorCode.SQLERROR,
                                              ex.getMessage(),
                                              request.getHead()));
        }
        finally
        {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
	
}
