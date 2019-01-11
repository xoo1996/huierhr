/**
 * 项目: Rapid Application Development Framework
 * 项目名称:     emis2006
 * 所在包名称:   org.radf.plat.commons
 * 类名称:      OptionDict.java
 * 创建者:      syg
 * 创建时间:     2006-10-17
 */
package org.radf.plat.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeMap;
import java.util.Vector;

import org.radf.manage.param.entity.Aa10;

/**
 * 创建者 syg
 */
public class OptionDict extends OptionDictSupport {

	/**
	 * 初始化所有选项字典
	 */
	public static void init(Connection con) throws SQLException {
		initSc06(con);// 初始化角色
	}

	/**
	 * 初始化管理对象和缴费项目的对应关系
	 * 
	 * @author Administrator
	 * @date 2006-10-16 10:47:34
	 */
	public static void initSc06(Connection con) {
		Statement stmt = null;
		ResultSet rs = null;
		try {

			stmt = con.createStatement();
			String sSql = "SELECT bsc014,bsc015 FROM sc06 where 1=1 order by bsc014";
			rs = stmt.executeQuery(sSql);

			while (rs.next()) {
				String s = rs.getString("bsc014");
				String s1 = rs.getString("bsc015");
				OptionDictSupport.add("SC06", s, s1, false, null, true);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}
}
