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
 * 人员岗位推荐信息反馈实现方法类
 */
public class Rec0105IMP extends IMPSupport implements Rec0105Facade {
	private String className = this.getClass().getName();

	/**
	 * 推荐成功
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @param 数据层返回的处理结果
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
			// 1，判断个人求职推荐信息表记录。根据推荐编号获取信息
			dto.setFileKey("cc22_select");
			ArrayList list = (ArrayList) find(con, dto, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc22);
				if (cc22.getAcc223() == null)// 判断推荐情况
					cc22.setAcc223("0");
				if (cc22.getAcc223().compareTo("0") != 0) {
					throw new AppException("推荐编号为“" + cc22.getAcc220()
							+ "”的推荐信息已反馈推荐情况，反馈机构编号为“" + dto.getAae017() + "");
				}
				cc22.setAcc223("1");// 成功推荐

			} else {
				throw new AppException("没有推荐信息");
			}

			// 2。判断单位招聘空位信息
			dto.setAcb200(cc22.getAcb200());// 招聘编号
			dto.setAcb210(cc22.getAcb210());// 空位编号
			dto.setFileKey("rec01_006");
			ArrayList list2 = (ArrayList) find(con, dto, null, 0);
			if (list2 != null && list2.size() > 0) {
				ClassHelper.copyProperties(list2.get(0), cb21);
				if (cb21.getAcb208() != null) { // 冻结标志
					cb21.setAcb208(cb21.getAcb208());
				} else {
					cb21.setAcb208("0");
				}

				if (cb21.getAcb21a().intValue() >= cb21.getAcb21d().intValue())
					throw new AppException("招聘人数已满！");
				cb21.setAcb21a(Short.valueOf((cb21.getAcb21a().intValue() + 1)
						+ "")); // 成功数+1

				if (cb21.getAcb21a().intValue() >= cb21.getAcb21d().intValue()) {
					cb21.setAcb208("1");
				}
				// 3。如果本岗位冻结，则检查招聘信息所属的所有岗位信息是否已冻结？如果冻结，则冻结招聘信息。
				if (cb21.getAcb208().compareTo("1") == 0) {
					// 岗位信息的冻结信息
					if (cb21.getAcb20d() == null)
						cb21.setAcb20d(dto.getAae011()); // 冻结人员
					if (cb21.getAcb20e() == null)
						cb21.setAcb20e(dto.getAae036()); // 冻结日期
				}
			} else {
				throw new AppException("没有推荐信息");
			}
			// update
			// 修改个人求职推荐信息表
			cc22.setAcc226(dto.getAcc226());// 反馈人
			cc22.setAcc229(dto.getAae036());// 反馈时间
			cc22.setFileKey("cc22_update");
			modify(con, cc22, null, 0);
			// 修改单位招聘空位信息表
			cb21.setFileKey("cb21_update");
			modify(con, cb21, null, 0);
			/*
			 * // 修改单位招聘信息表 if (list1 != null) { // 提交招聘信息
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
	 * 恢复到推荐中
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @param 数据层返回的处理结果
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
			// 1，判断个人求职推荐信息表记录。根据推荐编号获取信息
			dto.setFileKey("cc22_select");
			ArrayList list = (ArrayList) find(con, dto, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc22);
				if (!"1".equals(cc22.getAcc223())
						&& !"2".equals(cc22.getAcc223())) {
					throw new AppException("推荐编号为“" + cc22.getAcc220()
							+ "”的推荐信息未做推荐反馈，无须恢复处理");
				}

			} else {
				throw new AppException("没有推荐信息");
			}
			if ("1".equals(cc22.getAcc223())) {
				cc22.setAcc223("0");
				dto.setAcb200(cc22.getAcb200());// 招聘编号
				dto.setAcb210(cc22.getAcb210());// 空位编号
				dto.setFileKey("rec01_006");
				ArrayList list2 = (ArrayList) find(con, dto, null, 0);
				if (list2 != null && list2.size() > 0) {
					ClassHelper.copyProperties(list2.get(0), cb21);
					if (cb21.getAcb208() != null) { // 冻结标志
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
					// 3。如果本岗位冻结，则检查招聘信息所属的所有岗位信息是否已冻结？如果冻结，则冻结招聘信息。
					if (cb21.getAcb208().compareTo("1") == 0) {
						// 岗位信息的冻结信息
						if (cb21.getAcb20d() == null)
							cb21.setAcb20d(dto.getAae011()); // 冻结人员
						if (cb21.getAcb20e() == null)
							cb21.setAcb20e(dto.getAae036()); // 冻结日期
					} else {
						cb21.setAcb20d(null);
						cb21.setAcb20e(null);
					}
				} else {
					throw new AppException("没有推荐信息");
				}

				cc22.setAcc226(null);// 反馈人
				cc22.setAcc229(null);// 反馈时间
				cc22.setFileKey("cc22_update");
				modify(con, cc22, null, 0);
				// 修改单位招聘空位信息表
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
					throw new AppException("没有该人员的求职信息");
				}

				cc22.setAcc223("0"); // 失败推荐
				cc22.setAcc226(null);// 反馈人
				cc22.setAcc22e(null);// 失败原因
				cc22.setAcc229(null);// 反馈时间
				// update
				cc22.setFileKey("cc22_update");
				modify(con, cc22, null, 0);

				// 如果注销求职信息，则提交
				if (cc20.getAcb208().compareTo("1") == 0) {
					// 解冻求职信息
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
	 * 推荐失败
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @param 数据层返回的处理结果
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
						throw new AppException("推荐编号为“" + cc22.getAcc220()
								+ "”的推荐信息已反馈推荐情况，反馈机构编号为“" + dto.getAae017()
								+ "");
					}

				} else if ("6".equals(acc223)) {
					if (cc22.getAcc223().compareTo("6") != 0) {
						throw new AppException("单位联系编号为“" + cc22.getAcc220()
								+ "”的联系信息已反馈处理，反馈机构编号为“" + dto.getAae017() + "");
					}
				}

			} else {
				throw new AppException("没有推荐信息");
			}
			dto.setAcc200(cc22.getAcc200());
			dto.setFileKey("cc20_select");
			ArrayList list1 = (ArrayList) find(con, dto, null, 0);
			if (list1 != null && list1.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc20);
				cc20.setAcb208("0");
			} else {
				throw new AppException("没有该人员的求职信息");
			}

			Date d = new Date(System.currentTimeMillis());

			// 3.记录本次职介失败
			if ("7".equals(dto.getFlag()) || "8".equals(dto.getFlag())) {
				cc22.setAcc223(dto.getFlag()); // 失败推荐
				cc22.setAcc22e(null);// 失败原因
			} else {
				cc22.setAcc223("2"); // 失败推荐
				cc22.setAcc22e(dto.getAcc22e());// 失败原因
			}
			cc22.setAcc226(dto.getAcc226());// 反馈人
		
			cc22.setAcc229(d);// 反馈时间
			// update
			cc22.setFileKey("cc22_update");
			modify(con, cc22, null, 0);
			if (!"7".equals(dto.getFlag()) && !"8".equals(dto.getFlag())) {
				// 如果解冻求职信息，则提交
				if (cc20.getAcb208().compareTo("0") == 0) {
					// 解冻求职信息
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
	 * 推荐失败
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @param 数据层返回的处理结果
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
					throw new AppException("推荐编号为“" + cc22.getAcc220()
							+ "”的推荐信息已反馈推荐情况，不能删除");
				}
			} else {
				throw new AppException("没有推荐信息");
			}
			dto.setAcc200(cc22.getAcc200());
			dto.setFileKey("cc20_select");
			ArrayList list1 = (ArrayList) find(con, dto, null, 0);
			if (list1 != null && list1.size() > 0) {
				ClassHelper.copyProperties(list.get(0), cc20);
				cc20.setAcb208("0");
			} else {
				throw new AppException("没有该人员的求职信息");
			}
			dto.setAcb210(cc22.getAcb210());
			dto.setFileKey("cb21_select");
			ArrayList listcb21 = (ArrayList) find(con, dto, null, 0);
			if (listcb21 != null && listcb21.size() > 0) {
				ClassHelper.copyProperties(listcb21.get(0), cb21);
			} else {
				throw new AppException("没有该岗位信息");
			}
			cb21.setAcb218(Short
					.valueOf((cb21.getAcb218().intValue() - 1) + ""));
			if (cb21.getAcb218().intValue() < 0) {
				cb21.setAcb218(Short.valueOf("0"));
			}
			cc22.setFileKey("cc22_delete");
			remove(con, cc22, null, 0);
			if (cc22.getAcc223().compareTo("0") == 0) {
				// 如果解冻求职信息，则提交
				if (cc20.getAcb208().compareTo("0") == 0) {
					// 解冻求职信息
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
	 * 查询个人推荐详细信息
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @param 数据层返回的处理结果
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
				throw new AppException("没有推荐信息");
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
	 * 推荐反馈查询
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @param 数据层返回的处理结果
	 */
	public ResponseEnvelop findRecommendHistory(RequestEnvelop request) {
		return find(request, null, "find", 0);
	}
}