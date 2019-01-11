package org.radf.login.common;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import org.radf.login.dto.LoginDTO;
import org.radf.manage.entity.Sc11;
import org.radf.manage.entity.Sc12;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;

public class LoginUtil {
	/**
	 * 记录操作员操作信息
	 * @param hreq
	 * @param s
	 */
	public static void Sc01logAdd(HttpServletRequest hreq,String s) {
		LoginDTO dto = (LoginDTO) hreq.getSession().getAttribute("LoginDTO");
		Sc11 sc11 = new Sc11();
		Connection con = null;
		try {
			if (dto != null) {
				con = DBUtil.getConnection();
				sc11.setBsc030(DBUtil.getSequence(con, "SEQ_BSC030"));
				sc11.setBsc010(dto.getBsc010());
				sc11.setBsc011(dto.getBsc011());
				sc11.setBsc012(dto.getBsc012());
				sc11.setBsc026(hreq.getRemoteAddr());
				sc11.setBsc027(DateUtil.getSystemCurrentTime());
				sc11.setBsc031(s);
				/*ArrayList list = (ArrayList) DBUtil.querySQL(con,
						"select * from sc08 where bsc017 like '%"
								+ s + "%'", "2");
				if (list != null && list.size() > 0) {
					Sc08 sc08 = new Sc08();
					ClassHelper.copyProperties(list.get(0), sc08);
					sc11.setBsc047(sc08.getBsc016());
					sc11.setBsc033(sc08.getBsc018());
				}*/
				sc11.setBsc029(hreq.getSession().getId());
				sc11.setFileKey("SC11_insert");
				String hql = DBUtil.getSQLByObject(sc11, 2);
				DBUtil.execSQL(con, hql);
				DBUtil.commit(con);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
	}
	/**
	 * 记录错误记录
	 * @param head
	 * @param code
	 * @param msg
	 */
	public static void Sc012ogAdd(RequestEnvelopHead head, int code, String msg) {
		Sc12 sc12 = new Sc12();
		Connection con = null;
		try {
				con = DBUtil.getConnection();
				sc12.setBsc036(DBUtil.getSequence(con, "SEQ_BSC036"));
				sc12.setBsc027(DateUtil.getSystemCurrentTime());
				sc12.setBsc034(String.valueOf(code));
				sc12.setBsc035(msg);
				sc12.setFileKey("SC12_insert");
				String hql = DBUtil.getSQLByObject(sc12, 2);
				DBUtil.execSQL(con, hql);
				DBUtil.commit(con);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
	}
	/**
	 * 是否需要回退处理 增加记录
	 * @param modid 按妞编号
	 * @param dto   人员信息
	 * @param con   当前数据连接
	 * 
	 */
//	public static void Ywclht(LoginDTO dto,Connection conn,String modid) throws Exception {
//	
//		Sc14 sc14=new Sc14();
//		sc14.setBsc048(conn.toString());
//		sc14.setBsc010(dto.getBsc010());
//		sc14.setBsc011(dto.getBsc011());
//		sc14.setBsc012(dto.getBsc012());
//		sc14.setBsc027(DateUtil.getSystemCurrentTime());
//	
//		sc14.setFileKey("SC14_insert");
//		String hql = DBUtil.getSQLByObject(sc14,1);
//		if (hql == null) {
//			throw new SQLException("要执行的SQL语句为空");
//		}
//		if (conn == null || conn.isClosed()) {
//			throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR);
//		}
//		PreparedStatement ps = null;
//		try {
//			// 打开数据库连接并清楚原来记录集
//			ps = conn.prepareStatement(hql);
//			ps.executeUpdate();
//		} catch (SQLException ex) {
//			throw new SQLException(ex.getMessage());
//			
//		} finally {
//			DBUtil.closeStatement(ps);
//		}
//	}
//	/**
//	 * 保存操作的sql语句处理
//	 * @param falge
//	 * @param dto
//	 * @param conn
//	 * @throws Exception
//	 */
//	public static void Ywclhtaddsql(Connection conn,String sql) throws SQLException {
//		Sc14 sc14=new Sc14();
//		sc14.setBsc048(conn.toString());
//		sc14.setFileKey("SC14_select");
//		String hql = DBUtil.getSQLByObject(sc14,4);
//		ArrayList list=(ArrayList)DBUtil.querySQL(conn, hql, "2");
//		if(list!=null&&list.size()>0){
////			ClassHelper.copyProperties(list.get(0),sc14);
////			if (sc14.getBsc050() != null && sc14.getBsc050().equals("1")) {
////				String aa = "";
////				if (sql.substring(0, 6).toLowerCase().equals("insert")) {
////					aa = "insert=" + sql
////							+ "|";
////				} else if (sql.substring(0, 6).toLowerCase().equals("delete")) {
////					aa = "delete=" + sql
////							+ "|";
////				} else if (sql.substring(0, 6).toLowerCase().equals("update")) {
////					aa = "update=" +sql
////							+ "|";
////				}
////				if (sc14.getBsc032() == null)
////					sc14.setBsc032("");
////				sc14.setBsc032(StringUtil.StringReplace("\"","\''",StringUtil.StringReplace("\'", "\"", sc14.getBsc032() + aa)));
////				sc14.setFileKey("SC14_update");
////				hql = DBUtil.getSQLByObject(sc14, 1);
////				PreparedStatement ps = null;
////				try {
////					// 打开数据库连接并清楚原来记录集
////					ps = conn.prepareStatement(hql);
////					ps.executeUpdate();
////				} catch (SQLException ex) {
////					throw new SQLException(ex.getMessage());
////
////				} finally {
////					DBUtil.closeStatement(ps);
////				}
////			}
//		}
//		
//		
//	}
}
