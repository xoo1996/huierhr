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
 * 求职登记管理
 */
public class Rec02IMP extends IMPSupport implements Rec02Facade {
	private String className = this.getClass().getName();

	/**
	 * 进入求职意愿页面，查询出某一人员的相关信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据求职信息是否有效,查询数据</tt>
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
					//字符相等，返回0
					if (cc20.getAcb208()!=null&&cc20.getAcb208().compareTo("0") == 0) {
						dto2.setAae017(cc20.getAae017());
						dto2.setAce014(cc20.getAce014());
						dto2.setAcc200(cc20.getAcc200());
						acb208 = false;
					}
				}
				if (!acb208) {// 未冻结
					dto2.setFileKey("rec02_003");
					List lss = (ArrayList) find(con, dto2, null, 0);
					if (lss != null) {
						ClassHelper.copyProperties(lss.get(0), dto2);
						if (!(dto2.getAae017().equals(dto.getAae017()))) {
							throw new AppException(
									"该人员在其他登记机构登记过，并且还没有推荐，不能编辑！");
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
				} else {// 冻结
					dto.setFileKey("rec02_005");
					List ls = (ArrayList) find(con, dto, null, 0);
					if (ls != null) {
						for (int i = 0; i < ls.size(); i++) {
							ClassHelper.copyProperties(ls.get(i), cc22);
							if (cc22.getAcc223().equals("0")) {// 如果有在推荐中的。。。
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
										"该求职信息在其他机构登记过，且处于推荐状态，不能编辑！");
							} else {
								throw new AppException("该人员处于推荐中，不能增加！");
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
	 * 查看个人求职信息初始化<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、显示当前有效的求职信息</tt>
     * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewPersonApply(RequestEnvelop request) {
		return find(request);
	}

	/**
	 * 修改个人求职信息初始化<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、修改当前有效的求职信息</br>
     * 2、如果在推荐中不能修改</tt>
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
	 * 个人新加的基本信息的添加，个人求职信息的添加，个人求职意向的添加<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、保存基本信息和求职信息都不存在的个人求职</tt>
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
						.getSystemCurrentTime(), period));// 有效日期
			}
			ClassHelper.copyProperties(dto, ac01);
			ac01.setFileKey("bas02_000");
			ClassHelper.copyProperties(dto, cc20);
			cc20.setAcb208("0");
			ClassHelper.copyProperties(dto, cc21);

			// cc20.setAce014(DateUtil.getStepDay(DateUtil.getSystemCurrentTime(),
			// period));// 有效日期
			// cc21.setAce014(DateUtil.getStepDay(DateUtil.getSystemCurrentTime(),
			// period));// 有效日期
			con = DBUtil.getConnection();
			// 判断身份证号是否重复
			if (getCount(con, ac01, 0) > 0) {
				throw new AppException("该身份证号已存在");
			}
			// 设定农民工标识
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

			ac01.setAac001(CommonDB.getSequence(con, "SEQ_AAC001", 10, "0"));// 得到人员内码
			ac01.setAae100("1");// 设置人员为有效
			ac01.setFileKey("ac01_insert");
			store(con, ac01, null, 0);// 新增人员基本表信息
			cc20.setAac001(ac01.getAac001());
			cc21.setAac001(ac01.getAac001());
			cc20.setAcc200(CommonDB.getSequence(con, "SEQ_ACC200", 10, "0"));
			cc20.setFileKey("cc20_insert");
			store(con, cc20, null, 0);// 新增求职信息
			cc21.setAcc200(cc20.getAcc200());

			cc21.setAcc210(CommonDB.getSequence(con, "SEQ_ACC210", 10, "0"));
			cc21.setFileKey("cc21_insert");
			store(con, cc21, null, 0);
			// dto.setAcc210(cc21.getAcc210());
			// 第二意愿
			cc21.setAca111(dto.getAca111a());
			cc21.setAca112(dto.getAca112a());
			cc21.setAcc210(CommonDB.getSequence(con, "SEQ_ACC210", 10, "0"));
			cc21.setFileKey("cc21_insert");
			store(con, cc21, null, 0);
			// 第三意愿
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
	 * 修改个人求职信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、修改当前有效的求职信息</br>
     * 2、如果在推荐中不能修改</tt>
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
						.getSystemCurrentTime(), period));// 有效日期
			}
			ClassHelper.copyProperties(dto, ac01);
			ac01.setFileKey("bas02_001");
			ClassHelper.copyProperties(dto, cc20);
			cc20.setAcb208("0");
			ClassHelper.copyProperties(dto, cc21);

			con = DBUtil.getConnection();
			// 判断身份证号是否重复
			if (getCount(con, ac01, 0) > 0) {
				throw new AppException("该身份证号已存在");
			}
			// 设定农民工标识
			if (!(ac01.getAac009() == null || "".equals(ac01.getAac009()))) {
				if ((ac01.getAac009()).substring(0, 1).equals("2")) {
					ac01.setAac028("1");
				} else {
					ac01.setAac028("0");
				}
			}

			ac01.setFileKey("ac01_update");
			modify(con, ac01, null, 0);// 修改人员基本表信息
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
			 * AppException("你今天发布的信息已经达到上限,请改天发布！"); } }
			 */
			// 判断求职信息表的主键是否为空，空则执行插入，非空则执行修改操作
			if (cc20.getAcc200() == null || "".equals(cc20.getAcc200())) {
				cc20
						.setAcc200(CommonDB.getSequence(con, "SEQ_ACC200", 10,
								"0"));
				cc20.setFileKey("cc20_insert");
				store(con, cc20, null, 0);// 新增求职信息
			} else {
				cc20.setFileKey("cc20_update");
				modify(con, cc20, null, 0);// 更改求职信息
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
			// 第二意愿
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
			// 第三意愿
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
	 * 修改个人求职信息初始化<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、修改当前有效的求职信息</br>
     * 2、如果在推荐中不能修改</tt>
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
					throw new AppException("该人员所登记的求职信息已过期并自动注销，不能修改，请重新登记！");
				} else {
					ClassHelper.copyProperties(lss.get(0), cc20);
					if (!(cc20.getAae017().equals(dto.getAae017()))) {
						throw new AppException("该求职信息属其他结构登记的信息，不能修改！");
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
				throw new AppException("该人员无求职信息或者求职信息已经注销，不能修改，请重新登记！");
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
	 * 删除个人求职信息<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、删除求职信息</br>
     * 2、如果在推荐中不能删除</tt>
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
				throw new AppException("该人员无有效的求职信息，无法删除！");
			} else {
				ClassHelper.copyProperties(list.get(0), cc20);
			}
			cc20.setFileKey("rec02_013");
			if (getCount(con, cc20, 0) > 0) {
				throw new AppException("该人员已经被推荐报名，不能删除");
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
