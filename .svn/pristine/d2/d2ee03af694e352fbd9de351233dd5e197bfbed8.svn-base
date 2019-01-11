package org.radf.apps.process.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.apps.process.facade.ApaFacade;////
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
import org.radf.apps.commons.entity.Applyprocess;////

public class ApaImp extends IMPSupport implements ApaFacade {
	private String className = this.getClass().getName();

	/**
	 * 流程信息审核
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @return 数据层返回的处理结果
	 */
	public ResponseEnvelop verifyApa(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Applyprocess dto = (Applyprocess) map.get("beo");
			String type = (String) map.get("beo3");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getNemid() == null || dto.getNemid().equals("")) {
				throw new AppException("该流程信息未登记");
			} else {
				dto.setFileKey("ver_update");
				modify(con, dto, null, 0);
				if (!(type.equals("leave"))) {
					dto.setFileKey("ver_insert");
					store(con, dto, null, 0);
				}
				if(type.equals("nem")){
					dto.setFileKey("nem_update");
					modify(con, dto, null, 0);
				}else if(type.equals("cvt")){
					dto.setFileKey("cvt_update");
					modify(con, dto, null, 0);
				}else if(type.equals("lea")){
					if("4".equals(dto.getNemcondition())){
						dto.setUseremployeestatus("0");
						dto.setNememployid(dto.getNemapplyid());
						dto.setFileKey("user_update");
						modify(con, dto, null, 0);
					}
				}
				if (dto.getNemstate().equals("tg")) {
					dto.setFileKey("che_insert");
					store(con, dto, null, 0);
					if(dto.getNememployid()!=null){
						dto.setFileKey("user_update");
						modify(con, dto, null, 0);
					}
					if(type.equals("leave")){
						dto.setFileKey("accuser_delete");
						remove(con, dto, null, 0);
						dto.setFileKey("supjun_delete");
						remove(con, dto, null, 0);
					}
					if (type.equals("user")) {
						List result8 = (Vector) DBUtil.querySQL(con,
								"select max(conid) as conid from tblcontract");
						HashMap<String, String> map1 = (HashMap<String, String>) result8.get(0);
						String conid = map1.get("1");
						String a = conid.split("HT")[1];
						int b = Integer.parseInt(a) + 1;
						String c = String.valueOf(b);
						int length = c.length();
						for(int i = 0; i < 5-length; i ++){
							c = "0" + c;
						}
						dto.setCondateend(dto.getUsercondateend());
						dto.setContype(dto.getUsercontype());
						dto.setConid("HT" + c);
						dto.setCondatestart(dto.getUserjoindate());
						dto.setFileKey("con_000002");
						store(con, dto, null, 0);
						dto.setFileKey("update_con");
						modify(con, dto, null, 0);
					}
				}
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "verifyApa",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 新增流程信息
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @return 数据层返回的处理结果
	 */
	public ResponseEnvelop saveApa(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;

		try {
			HashMap map = (HashMap) request.getBody();
			Applyprocess Applyprocess = (Applyprocess) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String type = (String) map.get("beo3");
			if (type.equals("leave")) {
				Applyprocess.setFileKey("leave_update");
				modify(con, Applyprocess, null, 0);
				DBUtil.commit(con);
			} else if (type.equals("user")){
				Applyprocess.setFileKey("userinfo_insert");
				store(con, Applyprocess, null, 0);
				Applyprocess.setApatype("user");
				Applyprocess.setFileKey("user_insert");
				store(con, Applyprocess, null, 0);
				//根据所属部门查出是否是总部及对应账号
				List result3 = (Vector) DBUtil.querySQL(con,
								"select a.bsc001,a.bsc011 from sc05 a where a.bsc008='"
										+ Applyprocess.getUserdepartmentid() + "'");
				//成立一个新的部门，没有对应的账号；原来总部某部门员工换到新部门，先更新sc05表
				if (result3.size()<1) {
					throw new AppException("该部门或门店没有对应账号");
				}
				String part = (String) ((HashMap) result3.get(0)).get("1");
				if (part.equals("1501000000")){
					Applyprocess.setFileKey("accuser_insert");
					store(con, Applyprocess, null, 0);
				}else{
					Applyprocess.setAccount((String) ((HashMap) result3.get(0)).get("2"));
					Applyprocess.setFileKey("accuser_insert");
					store(con, Applyprocess, null, 0);
				}
				//直接查找账号-上级工号表，查出对应账号的上级工号
				List result4 = (Vector) DBUtil.querySQL(con,
						"select superiorid from tbl_acc_sup where account='"+ Applyprocess.getAccount() + "'");
				if(result4.size()>0){
					Applyprocess.setUsersuperiorid((String) ((HashMap) result4.get(0)).get("1"));
					Applyprocess.setFileKey("supjun_insert");
					store(con, Applyprocess, null, 0);
				}else{
					throw new AppException("该账号没有配置上级工号，请前往员工档案信息管理菜单下配置！");
				}
				//当新入职的员工所在部门相同职位的已经有上级，将会自动对应
//				List result4 = (Vector) DBUtil.querySQL(con,
//						"select a.superiorid from tbl_sup_jun a left join tbluser b on a.juniorid=b.useremployid where b.userdepartmentid='"+ Applyprocess.getUserdepartmentid() + "' and positionname='"
//								+ Applyprocess.getPositionname() + "' and b.useremployeestatus <> '0'");
//				if(result4.size()>0){
//					Applyprocess.setUsersuperiorid((String) ((HashMap) result4.get(0)).get("1"));
//					Applyprocess.setFileKey("supjun_insert");
//					store(con, Applyprocess, null, 0);
//				}
				DBUtil.commit(con);
			}else {
				if (type.equals("nem")) {
					Applyprocess.setApatype("nem");
					Applyprocess.setFileKey("nem_insert");
				} else if (type.equals("cvt")) {
					Applyprocess.setApatype("cvt");
					Applyprocess.setFileKey("cvt_insert");
				} else if (type.equals("rest")) {
					Applyprocess.setApatype("rest");
					Applyprocess.setFileKey("rest_insert");
				} else if (type.equals("lea")) {
					Applyprocess.setApatype("lea");
					Applyprocess.setFileKey("lea_insert");
				} else if (type.equals("con")) {
					Applyprocess.setApatype("con");
					Applyprocess.setFileKey("con_insert");
				}
				// 插入新流程记录
				store(con, Applyprocess, null, 0);
				DBUtil.commit(con);
			}
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("nemid", Applyprocess.getNemid());
			retmap.put("workString", "新增流程信息");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 流程信息修改
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @return 数据层返回的处理结果
	 */
	public ResponseEnvelop modifyApa(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Applyprocess dto = (Applyprocess) map.get("beo");
			String type = (String) map.get("beo3");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			List result = (Vector) DBUtil.querySQL(con,
					"select nemverifyid,nemstate,nemsupid from tblnem where nemid='" + dto.getNemid() + "'");
			if(result.size()>0){
				String nemstate=((HashMap) result.get(0)).get("2").toString();
				if("ht".equals(nemstate))
					dto.setNemsupid((String) ((HashMap) result.get(0)).get("1"));
				else dto.setNemsupid((String) ((HashMap) result.get(0)).get("3"));
			}
			dto.setNemstate("dsp");
			if (dto.getNemid() == null || dto.getNemid().equals("")) {
				throw new AppException("该流程信息未登记");
			} else {
				if (type.equals("nem")) {
					dto.setFileKey("nem_update");
				} else if (type.equals("cvt")) {
					dto.setFileKey("cvt_update");
				} else if (type.equals("rest")) {
					dto.setFileKey("rest_update");
				} else if (type.equals("lea")) {
					dto.setFileKey("lea_update");
				} else if (type.equals("con")) {
					dto.setFileKey("con_update");
				}else if (type.equals("user")) {
					dto.setFileKey("userinfo_update");
				}
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyApa",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 流程信息删除
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @return 数据层返回的处理结果
	 */
	public ResponseEnvelop deleteApa(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Applyprocess dto = (Applyprocess) map.get("beo");
			String apatype = (String) map.get("beo3");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getNemid() == null || dto.getNemid().equals("")) {
				throw new AppException("该流程信息未登记");
			} else {
				if(apatype.equals("user")){
					String nemid=dto.getNemid();
					List result = (Vector) DBUtil.querySQL(con,
							"select a.useremployid from tbluser a left join tblnem b on b.nememployid=a.useremployid where b.nemid='" + nemid + "'");
					dto.setUseremployid((String) ((HashMap) result.get(0)).get("1"));
					dto.setFileKey("nem_delete");
					remove(con, dto, null, 0);
					dto.setFileKey("user_delete");
					remove(con, dto, null, 0);
					DBUtil.commit(con);
				}else{
					dto.setFileKey("nem_delete");
					remove(con, dto, null, 0);
					dto.setFileKey("apa_delete");
					remove(con, dto, null, 0);
					DBUtil.commit(con);
				}
			}
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteApa",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 流程信息显示
	 * 
	 * @param request
	 *            业务逻辑层的参数封装请求
	 * @return 数据层返回的处理结果
	 */
	public ResponseEnvelop printApa(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Applyprocess dto = (Applyprocess) map.get("beo");
			String apatype = (String) map.get("beo3");
			if (apatype.equals("nem")) {
				dto.setFileKey("nem_select");// 流程详细信息
			} else if (apatype.equals("cvt")) {
				dto.setFileKey("cvt_select");//
			} else if (apatype.equals("rest")) {
				dto.setFileKey("rest_select");//
			} else if (apatype.equals("lea")) {
				dto.setFileKey("lea_select");//
			} else if (apatype.equals("con")) {
				dto.setFileKey("con_select");//
			} else {
				dto.setFileKey("user_select");//
			}
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "printApa",
					ManageErrorCode.SQLERROR, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

}
