/**
 * <p>标题: 系统参数的自动加载</p>
 * <p>说明: 系统在加载时，自动调用SystemParameter.init()方法，加载init中所包含的内容。从而实现自动加载参数定义。业务系统的代码字典、权限菜单都可以通过此方式加载，运行中直接使用已经加载的数据内容。</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-12-6 9:31:13</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.plat.webcontroller;

import java.sql.Connection;
import org.radf.apps.commons.entity.Product;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.OptionDict;

public class SystemParameter {

	/**
	 * 初始化，加载数据
	 * 
	 * @author yepeng
	 * @date 2006-10-16 10:42:11
	 */
	public static void init() {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			OptionDict.init(con);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
	}

	public static void update() {
	}

}