/**
 * <p>����: ��ʼ��ϵͳ�������</p>
 * <p>˵��: aa10��</p>
 * <p>��Ŀ: Rapid Application Development Framework</p>
 * <p>��Ȩ: Copyright 2008 - 2017</p>
 * <p>ʱ��: </p>
 *
 * @author ���Ƹ�
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
	 * ��ʼ������ѡ���ֵ�
	 */
	public static void init() {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			// ��ʼ��aa10����
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
	 * ��ʼ��
	 */
	private static void initAa10(Connection con) throws SQLException {
        int count = 0;      //��¼��
        int typeCount = 0;  //������
        String typeTemp = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sSql = "SELECT AAA100,AAA102,AAA103,baa108,aae013,BAA109 FROM AA10 where 1=1 order by aaa100,aaa102";
			rs = stmt.executeQuery(sSql);
			while (rs.next()) {
				String type = rs.getString("AAA100");   //����
                String code = rs.getString("AAA102");   //����
                String name = rs.getString("AAA103");   //����
                String defu = rs.getString("BAA108");   //�Ƿ�Ĭ��
                String bz = rs.getString("AAE013");		//��ע
                String view = rs.getString("BAA109");	//�Ƿ���ʾ
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
                System.out.println("ȡ��AA10���ݣ�����"+typeCount+"��,��ϸ"+count+"��");
            }
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}
}
