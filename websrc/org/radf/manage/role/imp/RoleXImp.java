package org.radf.manage.role.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.radf.login.dto.LoginDTO;
import org.radf.manage.entity.Sc08;
import org.radf.manage.entity.Sc06;
import org.radf.manage.entity.Sc07;
import org.radf.manage.entity.Sc09;
import org.radf.manage.role.facade.RoleXFacade;
import org.radf.manage.entity.Sc05;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.OptionDictSupport;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class RoleXImp extends org.radf.plat.util.imp.IMPSupport implements
		RoleXFacade {
	private String className = this.getClass().getName();

	/**
	 * 根据角色编号获取一级菜单信息
	 */
	public ResponseEnvelop findsc08bybsc016(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sc06 sc06 = (Sc06) map.get("beo");
			String qz = (String) map.get("qz");
			ArrayList list = (ArrayList) find(con, sc06, null, 0);
			ArrayList list1 = new ArrayList();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Sc08 sc08 = new Sc08();
					ClassHelper.copyProperties(list.get(i), sc08);
					if (qz.equals("0")) {
						list1.add(sc08);
					} else if (qz.equals("1")) {
						if (sc08.getBsc019() == null
								|| sc08.getBsc019().equals("")) {
							list1.add(sc08);
						}
					}
				}

			}
			DBUtil.commit(con);
			// 组装返回参数
			HashMap retmap = new HashMap();
			retmap.put("list", list1);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findsc08bybsc016",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 保存角色菜单权限
	 */
	public ResponseEnvelop saveFunctionList(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sc06 sc06 = (Sc06) map.get("beo");
			ArrayList list = (ArrayList) map.get("list");
			String as[] = (String[]) map.get("as");
			StringBuffer stringbuffer = (new StringBuffer(
					" delete  from sc09 sa where sa.bsc014 = ")).append(sc06
					.getBsc014());
			StringBuffer stringbuffer1 = (new StringBuffer(
					"delete  from sc09 sa where sa.bsc014 = ")).append(sc06
					.getBsc014());
			if (list != null) {
				stringbuffer.append(" and ( 1 != 1 ");
				stringbuffer1
						.append(" and ( 1 != 1 or sa.bsc016 in (select sf.bsc016 from sc08 sf where sf.bsc019 in (");
				for (Iterator iterator = list.iterator(); iterator.hasNext();) {
					Sc08 sc08 = (Sc08) iterator.next();
					stringbuffer.append("  or sa.bsc016 = '").append(
							sc08.getBsc016()).append("'");
					if (GlobalNames.RIGHT_LEVEL.equals(GlobalNames.MENU_LEAF)
							&& GlobalNames.MENU_LEAF.equals(sc08.getBsc021()))
						stringbuffer1.append("'").append(sc08.getBsc016())
								.append("',");
				}
				stringbuffer.append(" ) ");
				stringbuffer1.append(" 'nofunctionid'))) ");

			} else {
				stringbuffer.append(" and ( 1 != 1 )");
				stringbuffer1.append(" and ( 1 != 1 )");
			}
			StringBuffer stringbuffer2 = new StringBuffer(
					" select sf.bsc016 from sc08 sf where (1 != 1) ");
			Vector arraylist = new Vector();
			Object obj = null;
			if (as != null) {
				for (int i = 0; i < as.length; i++) {
					String as1[] = as[i].split(";");
					String s1 = as1[0];
					String s2 = as1[1];
					String s3 = as1[2];
					Sc09 sc09 = new Sc09();
					sc09.setBsc014(sc06.getBsc014());
					sc09.setBsc016(s1);
					sc09.setFileKey("SC09_insert");
					arraylist.add(sc09);
					if (GlobalNames.RIGHT_LEVEL.equals(GlobalNames.MENU_LEAF)
							&& GlobalNames.MENU_LEAF.equals(s2))
						stringbuffer2.append(" or (sf.bsc019 = '").append(s1)
								.append("')");
				}
			}
			int j = 0;
			List list1 = (List) DBUtil.querySQL(con, stringbuffer2.toString(),
					"2");
			if (list1 != null) {
				Sc09 sc09;
				for (Iterator iterator1 = list1.iterator(); iterator1.hasNext(); arraylist
						.add(sc09)) {
					String s4 = (String) iterator1.next();
					sc09 = new Sc09();
					sc09.setBsc014(sc06.getBsc014());
					sc09.setBsc016(s4);
					sc09.setFileKey("SC09_insert");
				}
			}
			DBUtil.execSQL(con, stringbuffer1.toString());
			DBUtil.execSQL(con, stringbuffer.toString());
			store(con, arraylist, null, 0);
			DBUtil.commit(con);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveFunctionList",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 根据角色编号人员
	 */
	public ResponseEnvelop findsc06bybsc014(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			Iterator iterator = null;
			Iterator iterator1 = null;
			Iterator iterator2 = null;
			Iterator iterator3 = null;
			HashMap map = (HashMap) request.getBody();
			Sc06 sc06 = (Sc06) map.get("beo");
			LoginDTO dto = (LoginDTO) map.get("dto");
			if (sc06.getBsc015()!=null&&sc06.getBsc015().equals("0")) {
				String userlist[] = (String[]) map.get("userlist");
				if (userlist != null && userlist.length > 0) {
					Sc07 sc07temp = new Sc07();
					sc07temp.setBsc014(sc06.getBsc014());
					sc07temp.setFileKey("sys03_005");
					remove(con, sc07temp, null, 0);
					for (int i = 0; i < userlist.length - 1; i++) {
						Sc07 sc07 = new Sc07();
						sc07.setBsc014(sc06.getBsc014());
						sc07.setBsc010(userlist[i]);
						sc07.setFileKey("SC07_insert");
						create(con, sc07, null, 0);
					}
				}
			}

			sc06.setFileKey("SC06_select");
			ArrayList list = (ArrayList) find(con, sc06, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), sc06);
			}
			Sc07 sc07 = new Sc07();
			sc07.setBsc014(sc06.getBsc014());
			sc07.setFileKey("sys03_003");
			ArrayList list1 = (ArrayList) find(con, sc07, null, 0);
			ArrayList list3 = new ArrayList();
			if (list1 != null && list1.size() > 0) {
				for (int i = 0; i < list1.size(); i++) {
					Sc05 aa = new Sc05();
					ClassHelper.copyProperties(list1.get(i), aa);
					list3.add(aa);
				}
			}
			Sc05 sc05 = new Sc05();
			String sql="";
			StringBuffer stringbuffer = (new StringBuffer("select sc05.* from sc05,sc01 " +
					"where sc01.bsc001=sc05.bsc001 "));
//					"and substr(sc01.aab003,1,length('"+dto.getAab003()+"')-2)=" +
//							"substr('"+dto.getAab003()+"',1,length('"+dto.getAab003()+"')-2) "));
			/*if (GlobalNames.SUPER_USER.equals(dto.getBsc011())) {
				
			} else {
				stringbuffer.append(" and bsc001='"+dto.getBsc001()+"'  ");
			}*/
			
			if(list3!=null&&list3.size()>0){
				stringbuffer.append(" and  bsc010 not in(");
				for (int j = 0; j < list3.size(); j++) {
					Sc05 aa =(Sc05)list3.get(j);
					stringbuffer.append("'"+aa.getBsc010()+"',");
				}
				sql=stringbuffer.toString().substring(0,stringbuffer.toString().lastIndexOf(","))+")";
			}else{
				sql=stringbuffer.toString();
			}
			
			ArrayList list2 = (ArrayList) DBUtil.querySQL(con, sql, "2");
			ArrayList list4 = new ArrayList();
			if (list2 != null && list2.size() > 0) {
				for (int i = 0; i < list2.size(); i++) {
					Sc05 bb = new Sc05();
					ClassHelper.copyProperties(list2.get(i), bb);
					
					list4.add(bb);
				}
			}
			if (list4 != null) {
				if (list3 != null) {
					list4.removeAll(list3);
					iterator = list3.iterator();
				}
				iterator2 = list4.iterator();
			}
			DBUtil.commit(con);
			// 组装返回参数
			HashMap retmap = new HashMap();
			retmap.put("iterator", iterator);
			retmap.put("iterator2", iterator2);
			retmap.put("sc06", sc06);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findsc08bybsc016",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 保存角色信息
	 */
	public ResponseEnvelop saveSc06(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sc06 sc06 = (Sc06) map.get("beo");
			String userlist[] = (String[]) map.get("userlist");
			if (sc06.getBsc014() != null && !sc06.getBsc014().equals("")) {
				sc06.setFileKey("SC06_update");
				modify(con, sc06, null, 0);
				if (userlist != null && userlist.length > 0) {
					Sc07 sc07temp = new Sc07();
					sc07temp.setBsc014(sc06.getBsc014());
					sc07temp.setFileKey("sys03_005");
					remove(con, sc07temp, null, 0);
					for (int i = 0; i < userlist.length - 1; i++) {
						Sc07 sc07 = new Sc07();
						sc07.setBsc014(sc06.getBsc014());
						sc07.setBsc010(userlist[i]);
						sc07.setFileKey("SC07_insert");
						create(con, sc07, null, 0);
					}
				}
			} else {
				sc06.setBsc014(DBUtil.getSequence(con, "SEQ_BSC014"));
				sc06.setFileKey("SC06_insert");
				create(con, sc06, null, 0);
				if (userlist != null && userlist.length > 0) {
					for (int i = 0; i < userlist.length - 1; i++) {
						Sc07 sc07 = new Sc07();
						sc07.setBsc014(sc06.getBsc014());
						sc07.setBsc010(userlist[i]);
						sc07.setFileKey("SC07_insert");
						create(con, sc07, null, 0);
					}
				}
			}
			DBUtil.commit(con);
			OptionDictSupport.add("SC06", sc06.getBsc014(),sc06.getBsc015(), false,null,true);
			// 组装返回参数
			HashMap retmap = new HashMap();

			retmap.put("sc06", sc06);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveSc06",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * 删除角色信息
	 */
	public ResponseEnvelop deleteSc06(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Sc06 sc06 = (Sc06) map.get("beo");
			sc06.setFileKey("SC06_delete");
			remove(con, sc06, null, 0);
			Sc07 sc07 = new Sc07();
			sc07.setBsc014(sc06.getBsc014());
			sc07.setFileKey("sys03_005");
			remove(con, sc07, null, 0);
			DBUtil.commit(con);
			OptionDictSupport.add("SC06", sc06.getBsc014(),sc06.getBsc015(), false,null,true);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "deleteSc06",// 注意提换当前方法名字queryEnterprise
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
