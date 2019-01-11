package org.radf.manage.user.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.manage.user.dto.Sc05DTO;
import org.radf.manage.entity.Sc05;
import org.radf.manage.entity.Sc07;
import org.radf.manage.entity.Sc13;
import org.radf.manage.user.facade.UserFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class UserImp extends IMPSupport implements UserFacade {
	private String className = this.getClass().getName();

	/**
	 * 查找记录
	 * 
	 * @author syy
	 * @date 2007-10-22
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findSc05(RequestEnvelop request) {
		return find(request, null, "find", 0);
	}

	/**
	 * 查找表Sc04,Sc06
	 * 
	 * @author syy
	 * @date 2007-10-23
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findSc04NSc06(RequestEnvelop request) {

		ResponseEnvelop response = new ResponseEnvelop();
		HashMap retmap = new HashMap();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			String sc = (String) map.get("sc");
			String bsc010 = (String) map.get("bsc010");
			String aab003 = (String) map.get("aab003");
			String function = (String) map.get("function");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			if ("sc06".equals(sc)) {

				Sc05DTO sc05dto = new Sc05DTO();
				sc05dto.setBsc010(bsc010);
				sc05dto.setFileKey("sys02_002");
				if ("add".equals(function))
					sc05dto.setFileKey("SC06_select");
				ArrayList sc06List = (ArrayList) find(con, sc05dto, null, 0);
				retmap.put("sc06List", sc06List);

				if ("mod".equals(function)) {
					Sc05DTO sc05dto2 = new Sc05DTO();
					sc05dto2.setBsc010(bsc010);
					sc05dto2.setFileKey("sys02_001");
					ArrayList sc06List2 = (ArrayList) find(con, sc05dto2, null,
							0);
					retmap.put("sc06List2", sc06List2);
				}

			} else if ("sc04".equals(sc)) {

				Sc05DTO sc05dto = new Sc05DTO();
				sc05dto.setBsc010(bsc010);
				sc05dto.setAab003(aab003);
				sc05dto.setFileKey("sys02_004");
				if ("add".equals(function))
					sc05dto.setFileKey("sys02_011");
				ArrayList sc04List = (ArrayList) find(con, sc05dto, null, 0);
				retmap.put("sc04List", sc04List);

				if ("mod".equals(function)) {
					Sc05DTO sc05dto2 = new Sc05DTO();
					sc05dto2.setBsc010(bsc010);
					sc05dto2.setFileKey("sys02_005");
					ArrayList sc04List2 = (ArrayList) find(con, sc05dto2, null,
							0);
					retmap.put("sc04List2", sc04List2);
				}
			}
			DBUtil.commit(con);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findSc04NSc06",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 保存修改人员信息
	 * 
	 * @author syy
	 * @date 2007-10-22
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifySc05(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc05 sc05 = (Sc05) map.get("sc05");
			Sc07 sc07 = new Sc07();
			String function = (String) map.get("function");
			String[] roleList = (String[]) map.get("roleList");
			int size=0;
			String bsc008=sc05.getBsc008();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			List result = (Vector) DBUtil.querySQL(con,
					"select bsc001 from sc04 where bsc008='" + bsc008
							+ "'");
			String bsc001 = (String) ((HashMap) result.get(0)).get("1");
			// 是否存在相同代码的用户
			if (sc05.getBsc011() == null || sc05.getBsc011().equals("")
					|| sc05.getBsc011().trim().length() == 0) {
				throw new AppException("|保存失败！用户代码不能为空|");
			}
			if("mod".equals(function)&&sc05.getBsc011().equals((String) map.get("bsc011t"))){size=1;}
			sc05.setFileKey("sys02_010");
			ArrayList sc05List = (ArrayList) find(con, sc05, null, 0);
			if (sc05List != null && sc05List.size() > size) {
				throw new AppException("|保存失败！此用户代码已存在请重填|");
			}
			
			if ("add".equals(function)) {
				String bsc010 = DBUtil.getSequence(con, "SEQ_BSC010");
				sc05.setBsc010(bsc010);
				
				sc05.setBsc001(bsc001);
				sc05.setFileKey("sc05_insert");
				create(con, sc05, null, 0);
				sc05.setFileKey("tblgrp_insert");
				create(con, sc05, null, 0);
				if (roleList != null && roleList.length > 0) {
					for (int i = 0; i < roleList.length; i++) {
						if (!roleList[i].equals("") && roleList[i] != null) {
							sc07.setBsc010(bsc010);
							sc07.setBsc014(roleList[i]);
							sc07.setFileKey("SC07_insert");
							create(con, sc07, null, 0);
						}
					}
				}

			} else {

				// 人员机构科室变更记录 insert sc13
				Sc13 sc13 = new Sc13();
				String flag = new String();
				
				sc05.setBsc001(bsc001);
				if (flag != null && flag.equals("doLog")) {
					Sc05DTO sc05DTO = new Sc05DTO();
					sc05DTO.setBsc010(sc05.getBsc010());
					sc05DTO.setFileKey("sys02_009");
					ArrayList al = (ArrayList) find(con, sc05DTO, null, 0);
					if (al == null || al.size() == 0) {
						throw new AppException("|无此人！|");
					}
					ClassHelper.copyProperties(al.get(0), sc05DTO);
					String bsc046 = DBUtil.getSequence(con, "SEQ_BSC046");
					sc13.setBsc046(bsc046);
					sc13.setAae011((String)map.get("aae011"));
					sc13.setBsc045(DateUtil.getSystemCurrentTime());
					//原数据
					sc13.setBsc010(sc05DTO.getBsc010());
					sc13.setBsc011(sc05DTO.getBsc011());
					sc13.setBsc012(sc05DTO.getBsc012());
					sc13.setBsc037(sc05DTO.getBsc008());
					sc13.setBsc038(sc05DTO.getBsc009());
					sc13.setBsc039(sc05DTO.getBsc001());
					sc13.setBsc040(sc05DTO.getAab300());
					//目前数据
					sc13.setBsc041(sc05.getBsc008());
					sc13.setBsc042("");
					sc13.setBsc043(sc05.getBsc001());
					sc13.setBsc044("");
					sc13.setFileKey("SC13_insert");
					create(con, sc13, null, 0);
				}

				sc05.setFileKey("SC05_update");
				modify(con, sc05, null, 0);
				sc05.setFileKey("tblgrp_update");
				modify(con, sc05, null, 0);
				// 更新隶属角色表
				sc07.setBsc010(sc05.getBsc010());
				sc07.setFileKey("sys02_003");
				remove(con, sc07, null, 0);

				for (int i = 0; i < roleList.length; i++) {
					if (!roleList[i].equals("") && roleList[i] != null) {
						sc07.setBsc010(sc05.getBsc010());
						sc07.setBsc014(roleList[i]);
						sc07.setFileKey("SC07_insert");
						create(con, sc07, null, 0);
					}
				}

			}
			
			// 向外传递数据
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifySc05",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 删除记录
	 * 
	 * @author syy
	 * @date 2007-10-22
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delSc05(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc05 sc05 = new Sc05();
			Sc07 sc07 = new Sc07();
			String bsc010 = (String) map.get("bsc010");

			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			sc05.setBsc010(bsc010);
			if (!sc05.getBsc010().equals("") && sc05.getBsc010() != null) {
				sc05.setAae100("0");
				sc05.setFileKey("SC05_update");
				modify(con, sc05, null, 0);
			}

			sc07.setBsc010(bsc010);
			if (!sc07.getBsc010().equals("") && sc07.getBsc010() != null) {
				sc07.setFileKey("sys02_003");
				remove(con, sc07, null, 0);
			}

			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delSc05",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 密码重置
	 * 
	 * @author syy
	 * @date 2007-10-22
	 * @param request
	 * @return
	 */
	public ResponseEnvelop alterPwd(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc05DTO sc05Dto = new Sc05DTO();
			String bsc010 = (String) map.get("bsc010");
			String oldpwd = (String) map.get("oldpwd");
			String newpasswd = (String) map.get("newpasswd");
			String function = (String) map.get("function");

			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			sc05Dto.setBsc010(bsc010);
			sc05Dto.setBsc013(newpasswd);
			sc05Dto.setOldpwd(oldpwd);

			if (sc05Dto.getBsc010() != null && !sc05Dto.getBsc010().equals("")) {

				// 修改密码
				if (sc05Dto.getOldpwd() != null
						&& !sc05Dto.getOldpwd().equals("") && function != null
						&& function.equals("alter")) {
					sc05Dto.setFileKey("sys02_008");
					ArrayList al = (ArrayList) find(con, sc05Dto, null, 0);
					if (al == null || al.size() == 0) {
						throw new AppException("|更新失败旧密码错误！|");
					}

					sc05Dto.setFileKey("sys02_006");
					modify(con, sc05Dto, null, 0);
				}
				// 重置密码
				if (function != null && function.equals("reset")) {
					sc05Dto.setFileKey("sys02_007");
					modify(con, sc05Dto, null, 0);
				}
			}

			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "alterPwdSc05",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	/**
	 * 初始化Ukey
	 */
	public ResponseEnvelop initKey(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();

			String snID = (String) map.get("snID");
			String ugctid = (String) map.get("ugctid");

			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			List result1 = (Vector)DBUtil.querySQL(con, "select s_tblukey.NEXTVAL from dual");
			BigDecimal id = (BigDecimal)((HashMap)result1.get(0)).get("1");
			String sql2 = "delete from tblukey where ukeysn = ?";
			PreparedStatement pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, snID);
			pstmt2.execute();
			String sql1 = "insert into tblukey values (?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setString(1,ugctid);
			pstmt.setString(2,snID);
			pstmt.setString(3,ugctid);
			pstmt.setString(4,"1");
			pstmt.setBigDecimal(5,id);
			pstmt.setDate(6,DateUtil.getDate());
			pstmt.execute();

			DBUtil.commit(con);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "initKey",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	public ResponseEnvelop loss(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc05DTO sc05 = new Sc05DTO();
			sc05 = (Sc05DTO) map.get("beo");

			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			sc05.setUopdate(DateUtil.getDate());
			sc05.setFileKey("ukey01_01");
			modify(con, sc05, null, 0);
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "loss",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	public ResponseEnvelop ukeyOperate(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		int res;
		try {
			HashMap map = (HashMap) request.getBody();
			Sc05DTO sc05 = new Sc05DTO();
			sc05 = (Sc05DTO) map.get("beo");
			String flag = (String)map.get("flag");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql = "update sc05 set aae101='"+sc05.getAae101()+"'";
			if(flag.equals("open")||flag.equals("close")){
				sql = sql + "where bsc011='"+sc05.getBsc011()+"'";
			}
			PreparedStatement pst = con.prepareStatement(sql);
			res = pst.executeUpdate();
			
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			response.setBody(retmap);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "ukeyOperate",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
}
