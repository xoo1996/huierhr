package org.radf.plat.sieaf;

/**
 * 查询并获取所有的Functionid和认证证书列表
 * 此对象用于系统中构建functionid认证的缓冲，在系统启动时加载
 * 此对象由MainServlet加载使用
 * @author zqb
 * @version 1.0
 */
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;

import org.radf.manage.role.dao.SysFunctionDAO;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.WebException;

public class FunctionCache implements Serializable {

	private HashMap signType = null;

	public FunctionCache() throws WebException {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			SysFunctionDAO dao = new SysFunctionDAO();
			signType = dao.doFindEncrypt(con);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new WebException(ex.getMessage());
		} finally {
            DBUtil.closeConnection(con);
		}
	}

	/**
	 * 获取签名类型
	 * @param functionID
	 * @return
	 */
	public String getSignatureType(String functionID) {
		return (String) signType.get(functionID);
	}
}