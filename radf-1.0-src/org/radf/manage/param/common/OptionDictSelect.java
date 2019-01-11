/**
 * <p>标题: 初始化系统代码参数</p>
 * <p>说明: aa10表</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: </p>
 *
 * @author 沈云刚
 * @version 1.0
 */

package org.radf.manage.param.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.OptionDictSupport;
import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.global.GlobalNames;

/**
 *
 */
public class OptionDictSelect extends OptionDictSupport{

	/**
	 * 初始化所有选项字典
	 */
	public static void init() {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			// 初始化aa10参数
			initAa10(con);
		} catch (NoConnectionException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(con);
		}
	}

	/*
	 * 初始化
	 */
	private static void initAa10(Connection con) throws SQLException {
        int count = 0;      //记录数
        int typeCount = 0;  //总类数
        String typeTemp = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sSql = "SELECT AAA100,AAA102,AAA103,baa108,aae013,BAA109 FROM AA10 where 1=1 order by aaa100,aaa102";
			rs = stmt.executeQuery(sSql);
			while (rs.next()) {
				String type = rs.getString("AAA100");   //大类
                String code = rs.getString("AAA102");   //编码
                String name = rs.getString("AAA103");   //名称
                String defu = rs.getString("BAA108");   //是否默认
                String bz = rs.getString("AAE013");		//备注
                String view = rs.getString("BAA109");	//是否显示
                boolean isDefu = false;
                boolean isView = false;
                if (defu!=null&&defu.equals("1")) {
                	isDefu = true;
                }
                if (view!=null&&view.equals("1")) {
                	isView = true;
                }
                add(type,code,name,isDefu,bz,isView);
                if(!type.equalsIgnoreCase(typeTemp)){
                    typeCount++;
                    typeTemp = type;
                }
                count++;
            }
            if(GlobalNames.DEBUG_PERFERMANCE_FLAG){
                System.out.println("取到AA10数据：大类"+typeCount+"组,明细"+count+"条");
            }
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}
}
