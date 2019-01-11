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
 * 人员岗位推荐实现方法类
 */
public class Rec0103IMP extends IMPSupport implements Rec0103Facade {
	private String className = this.getClass().getName();

	/**
	 * 匹配推荐<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据cc20表中ACB208（求职状态）='0'的条件查询出具有有效求职信息的人员<br>
     * 2、根据cb20表中ACB208(有效标记)='0'和ACB211(委托可推荐人数=招聘人数x推荐比列)>0查询出岗位信息<br>
     * 3、如果求职信息有效,并且岗位信息也同时有效,则推荐并打印推荐信</tt>
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @param 数据层返回的处理结果
	 */
	public ResponseEnvelop Recommend(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Rec0103DTO output = new Rec0103DTO();
		Connection con = null;
		boolean acb208 = false; // 是否需要冻结招聘信息标志
		boolean aca111 = true;// 判断是否存在求职意向信息
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
			// 根据人员编号获取领取信息
			input.setFileKey("ac01_select");
			ArrayList list = (ArrayList) find(con, input, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), ac01);
			} else {
				throw new AppException("没有推荐信息");
			}
			input.setFileKey("cc20_select");
			ArrayList list1 = (ArrayList) find(con, input, null, 0);
			if (list1 != null && list1.size() > 0) {
				ClassHelper.copyProperties(list1.get(0), cc20);
			} else {
				throw new AppException("没有个人求职信息");
			}
			cb21 = getPost(con, input);

			ArrayList listcc21 = new ArrayList();
			listcc21 = getIntention(con, input);
			cc20.setFileKey("rec01_012");
			int bmcs = getCount(con, cc20, 0);
			ParaUtil pu = new ParaUtil();
			String qzbmcs = pu.getParaV("qzbmcs", "qzbmcs", "rec");
			if (bmcs + 1 >= Integer.parseInt(qzbmcs)) {
				cc20.setAcb208("1");// 冻结求职信息
			} else {
				cc20.setAcb208("0");// 不冻结求职信息
			}

			input.setFileKey("cb20_select");
			ArrayList list4 = (ArrayList) find(con, input, null, 0);
			if (list4 != null && list4.size() > 0) {
				ClassHelper.copyProperties(list4.get(0), cb20);
			} else {
				throw new AppException("没有单位招牌信息");
			}
			input.setAab001(cb20.getAab001());
			input.setFileKey("ab01_select");
			ArrayList list5 = (ArrayList) find(con, input, null, 0);
			if (list5 != null && list5.size() > 0) {
				ClassHelper.copyProperties(list5.get(0), ab01);
			} else {
				throw new AppException("没有该单位信息");
			}
			String acc220 = "0";
			if (input.getAcc220() != null) {
				acc220 = "update";
				input.setFileKey("rec01_014");
				ArrayList list6 = (ArrayList) find(con, input, null, 0);
				if (list6 != null && list6.size() > 0) {
					ClassHelper.copyProperties(list6.get(0), cc22);
				} else {
					throw new AppException("该推荐信息的推荐状态已经变化，不是联系成功状态，不能推荐。");
				}
				cc22.setFileKey("rec01_013");
				if (getCount(con, cc22, 0) > 0) {					
					throw new AppException("该人员在同一岗位下已经存在推荐信息，不能推荐。");
				}
				cc22
						.setAcc221(CommonDB.getSequence(con, "SEQ_ACC221", 10,
								"0")); // 介绍信编号
				cc22.setAcc223("0");
			} else {
				acc220 = "insert";
				ClassHelper.copyProperties(ac01, cc22);
				ClassHelper.copyProperties(cb21, cc22);
				cc22.setAab001(ab01.getAab001());
				cc22.setAcc200(cc20.getAcc200()); // 求职编号
				// theRecommend.setAae006(invite.getAcb20c());
				// //地址，即面试地点
				cc22.setAae011(input.getAae011()); // 经办人
				cc22.setAae036(input.getAae036()); // 经办时间，即推荐时间
				cc22.setAae017(input.getAae017()); // 经办机构
				cc22.setAcb201("1");
				cc22.setFileKey("rec01_013");
				if (getCount(con, cc22, 0) > 0) {					
					throw new AppException("改人员在同一岗位下已经存在推荐信息，不能推荐。");
				}
				int period = 7;
				// 推荐情况:推荐
				cc22.setAcc223("0");
				cc22
						.setAcc220(CommonDB.getSequence(con, "SEQ_ACC220", 10,
								"0")); // 推荐编号
				cc22
						.setAcc221(CommonDB.getSequence(con, "SEQ_ACC221", 10,
								"0")); // 介绍信编号
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
	 * 单位选人<br>
     * <tt><font color="#FF0000"><B>业务规则要求：</B></font><br>
     * 1、根据cc20表中ACB208（求职状态）='0'的条件查询出具有有效求职信息的人员<br>
     * 2、根据cb20表中ACB208(有效标记)='0'和ACB211(委托可推荐人数=招聘人数x推荐比列)>0查询出岗位信息<br>
     * 3、如果求职信息有效,并且岗位信息也同时有效,则确认该单位选择了此人<br>
     * 4、页面要支持多选,即同时选择多人<br>
     * 5、被选中人在推荐表cc22中显示为未联系状态</tt>
     * 
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @param 数据层返回的处理结果
	 */
	public ResponseEnvelop dwxr(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Rec0103DTO output = new Rec0103DTO();
		Connection con = null;
		boolean acb208 = false; // 是否需要冻结招聘信息标志
		boolean aca111 = true;// 判断是否存在求职意向信息
		HashMap map = (HashMap) request.getBody();
		Rec0103DTO input = (Rec0103DTO) map.get("input");
		Collection collection = (Collection) map.get("collection");

		try {

			con = DBUtil.getConnection();
			if (collection == null) {
				throw new AppException("请选择人员");
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
				// 根据人员编号获取领取信息
				input.setFileKey("ac01_select");
				ArrayList list = (ArrayList) find(con, input, null, 0);
				if (list != null && list.size() > 0) {
					ClassHelper.copyProperties(list.get(0), ac01);
				} else {
					throw new AppException("没有推荐信息");
				}
				input.setFileKey("cc20_select");
				ArrayList list1 = (ArrayList) find(con, input, null, 0);
				if (list1 != null && list1.size() > 0) {
					ClassHelper.copyProperties(list1.get(0), cc20);
				} else {
					throw new AppException("没有个人求职信息");
				}
				cb21 = getPost(con, input);

				/*
				 * ArrayList listcc21 = new ArrayList(); listcc21 =
				 * getIntention(con, input);
				 */
				cc20.setAcb208("1");// 冻结求职信息
				input.setFileKey("cb20_select");
				ArrayList list4 = (ArrayList) find(con, input, null, 0);
				if (list4 != null && list4.size() > 0) {
					ClassHelper.copyProperties(list4.get(0), cb20);
				} else {
					throw new AppException("没有单位招牌信息");
				}
				input.setAab001(cb20.getAab001());
				input.setFileKey("ab01_select");
				ArrayList list5 = (ArrayList) find(con, input, null, 0);
				if (list5 != null && list5.size() > 0) {
					ClassHelper.copyProperties(list5.get(0), ab01);
				} else {
					throw new AppException("没有该单位信息");
				}
				ClassHelper.copyProperties(ac01, cc22);
				ClassHelper.copyProperties(cb21, cc22);
				cc22.setAab001(ab01.getAab001());
				cc22.setAcc200(cc20.getAcc200()); // 求职编号
				// theRecommend.setAae006(invite.getAcb20c());
				// //地址，即面试地点
				cc22.setAae011(input.getAae011()); // 经办人
				cc22.setAae036(input.getAae036()); // 经办时间，即推荐时间
				cc22.setAae017(input.getAae017()); // 经办机构
				cc22.setAcb201("1");
				int period = 7;
				// 推荐情况:推荐
				cc22.setAcc223("6");
				cc22
						.setAcc220(CommonDB.getSequence(con, "SEQ_ACC220", 10,
								"0")); // 推荐编号
				cc22.setAcc221(null); // 介绍信编号

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
	 * 得到岗位信息
	 */
	private Cb21 getPost(Connection con, Rec0103DTO input) throws AppException {
		Cb21 cb21 = new Cb21();
		List list;
		Integer zero = new Integer(0);
		boolean acb208 = false; // 是否需要冻结招聘信息标志
		input.setFileKey("cb21_select");
		list = (ArrayList) find(con, input, null, 0);
		if (list != null || list.equals("")) {
			ClassHelper.copyProperties(list.get(0), cb21);

			if (cb21.getAcb218() == null) // 已推荐人数
				cb21.setAcb218(Short.valueOf("0"));
			if (cb21.getAcb211() == null) // 委托可推荐人数
				cb21.setAcb211(Short.valueOf("0"));
			if (cb21.getAcb21a() == null) // 已成功人数
				cb21.setAcb21a(Short.valueOf("0"));
			if (cb21.getAcb21d() == null) // 委托招聘人数
				cb21.setAcb21d(Short.valueOf("0"));
			if (cb21.getAca111() == null) // 招聘工种
				throw new AppException("没有招聘空位的工种信息，无法进行推荐。请与系统管理员联系。");
			if (cb21.getAcb208().compareTo("0") == 0)
				acb208 = true;
			if (!acb208)
				input.setInviteAcb208(true);
			if (cb21.getAcb210().compareTo(input.getAcb210()) == 0)
				chkPost(input, cb21);
		} else {
			throw new AppException("获取招聘编号为“" + input.getAcb200()
					+ "”的“空岗信息”时出现数据库错误。");
		}
		return cb21;
	}

	/**
	 * 检查到岗位信息状态
	 */
	private void chkPost(Rec0103DTO input, Cb21 cb21) throws AppException {
		int residualRecommand = 0;
		// 1.是否冻结
		if (cb21.getAcb208().compareTo("0") != 0)
			throw new AppException("该工种的空位信息已冻结。无法进行推荐。");

		// 2.剩余推荐数>0
		residualRecommand = (cb21.getAcb211().intValue() - cb21.getAcb218()
				.intValue());
		// 3.推荐数是否ok
		if (residualRecommand <= 0)
			throw new AppException("空位信息的岗位已到达推荐数。无推荐名额。");
		// 4.增加推荐数
		int acb218 = cb21.getAcb218().intValue() + 1;
		cb21.setAcb218(Short.valueOf(acb218 + ""));// 增加空位的推荐数

		// 6.判断本次推荐后，该空岗是否还有空位
		residualRecommand = (cb21.getAcb211().intValue() - cb21.getAcb218()
				.intValue());
		// 7.如果没有空位，冻结
		if (residualRecommand == 0)
			cb21.setAcb208("1");
		// 9.将工种信息放入inputdto中
		input.setAca111(cb21.getAca111());
	}

	/**
	 * 判断岗位与求职意向的匹配情况
	 */
	private ArrayList getIntention(Connection con, Rec0103DTO input)
			throws AppException {
		Cc21 cc21 = new Cc21();
		Object[] obj;
		Iterator it;
		ArrayList list;
		boolean aca111 = true; // 判断该人员有相应工种的求职意向的标志
		ArrayList result = new ArrayList();
		input.setFileKey("rec01_002");
		list = (ArrayList) find(con, input, null, 0);
		if (list != null || list.equals("")) {
			for (int j = 0; j < list.size(); j++) {
				ClassHelper.copyProperties(list.get(j), cc21);
				if (cc21.getAca111() != null) { // 专业工种
					aca111 = false;
				}
				result.add(cc21);
			}
			if (aca111)
				throw new AppException("没有该工种的求职意向，无法进行推荐。");
		} else {
			throw new AppException("获取招聘编号为“" + input.getAcb200()
					+ "”的“空岗信息”时出现数据库错误。");
		}
		return result;
	}

}