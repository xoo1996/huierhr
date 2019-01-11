package org.radf.manage.trans.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.radf.manage.trans.entity.SysTransLog;
import org.radf.manage.util.Constants;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.global.GlobalNames;

/**
 *管理SYSTRANSLOG 的DAO
 */
public class SysTransLogDAO extends org.radf.plat.util.dao.DAOSupport {
    
    public static String INSERT_SQL = "insert into SYSTRANSLOG (logId,startTime,endTime,transId,transName,origLogId,loginName,sessionId,timeoutTime,status,ip ) values (?,?,?,?,?,?,?,?,?,?,?)";
    public static String UPDATE_SQL = "update SYSTRANSLOG set logId = ?,startTime = ?,endTime = ?,transId = ?,transName = ?,origLogId = ?,loginName = ?,sessionId = ?,timeoutTime = ?,status = ? where logId = ? ";
	/**
	 * 查询 order by
	 */
	final static String ORDERBY = " ORDER BY LOGID DESC ";
    
    public Object doCreate(Connection con, Object obj)
    throws java.sql.SQLException {
        PreparedStatement pstmt = null;
        SysTransLog sysTransLog = (SysTransLog) obj;

        String sqlCreate = INSERT_SQL;
        
        try {
            sysTransLog.setLogId(Long.parseLong(DBUtil.getSequence(con, GlobalNames.LOG_TRANS_SEQNAME)));

            pstmt = con.prepareStatement(sqlCreate);
            pstmt.setLong(1, sysTransLog.getLogId());
            pstmt.setString(2, sysTransLog.getStartTime());
            pstmt.setString(3, sysTransLog.getEndTime());
            pstmt.setString(4, sysTransLog.getTransId());
            pstmt.setString(5, sysTransLog.getTransName());
            pstmt.setLong(6, sysTransLog.getOrigLogId());
            pstmt.setString(7, sysTransLog.getLoginName());
            pstmt.setString(8, sysTransLog.getSessionId());
            pstmt.setInt(9, sysTransLog.getTimeoutTime());
            pstmt.setString(10, sysTransLog.getStatus());
            pstmt.setString(11,sysTransLog.getIp());

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
        }
        return sysTransLog;
    }
	/**
	 * 生成SYSTRANSLOG记录
     * @throws java.sql.SQLException
	 */
	public void doStore(Connection con, Object obj)
		throws java.sql.SQLException {
        doCreate(con,obj);
	}

	/**
	 * 修改SYSTRANSLOG记录
     * @throws java.sql.SQLException
	 */
	public void doUpdate(Connection con, Object obj)
		throws java.sql.SQLException {

		PreparedStatement pstmt = null;
		SysTransLog sysTransLog = (SysTransLog) obj;

		String sqlModify = UPDATE_SQL;
			;
		try {

			pstmt = con.prepareStatement(sqlModify);
			pstmt.setLong(1, sysTransLog.getLogId());
			pstmt.setString(2, sysTransLog.getStartTime());
			pstmt.setString(3, sysTransLog.getEndTime());
			pstmt.setString(4, sysTransLog.getTransId());
			pstmt.setString(5, sysTransLog.getTransName());
			pstmt.setLong(6, sysTransLog.getOrigLogId());
			pstmt.setString(7, sysTransLog.getLoginName());
			pstmt.setString(8, sysTransLog.getSessionId());
			pstmt.setInt(9, sysTransLog.getTimeoutTime());
			pstmt.setString(10, sysTransLog.getStatus());

			pstmt.setLong(11, sysTransLog.getLogId());
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	/**
	 * 删除SYSTRANSLOG记录
     * @throws java.sql.SQLException
	 */
	public void doDelete(Connection con, Object obj)
		throws java.sql.SQLException {
		PreparedStatement pstmt = null;
		SysTransLog sysTransLog = (SysTransLog) obj;

		String sqlDelete = "delete from  SYSTRANSLOG  where logId = ? ";
		try {

			pstmt = con.prepareStatement(sqlDelete);
			pstmt.setLong(1, sysTransLog.getLogId());
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
		}
	}

	/**
	 * 查询SYSTRANSLOG记录
     * @throws java.sql.SQLException
	 */
	public Object doFind(Connection con, Object obj)
		throws java.sql.SQLException {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SysTransLog sysTransLog = null;
		String id = (String) obj;
		String sqlSelect = "select * from  SYSTRANSLOG  where logId = ? ";
		try {
			pstmt = con.prepareStatement(sqlSelect);
			pstmt.setLong(1, Long.parseLong(id));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sysTransLog = new SysTransLog();
				sysTransLog.setLogId(rs.getLong("logId"));
				sysTransLog.setStartTime(rs.getString("startTime"));
				sysTransLog.setEndTime(rs.getString("endTime"));
				sysTransLog.setTransId(rs.getString("transId"));
				sysTransLog.setTransName(rs.getString("transName"));
				sysTransLog.setOrigLogId(rs.getLong("origLogId"));
				sysTransLog.setLoginName(rs.getString("LoginName"));
				sysTransLog.setSessionId(rs.getString("sessionId"));
				sysTransLog.setTimeoutTime(rs.getInt("timeoutTime"));
				sysTransLog.setStatus(rs.getString("status"));
			}
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();

		}
		return sysTransLog;
	}

	/**
	 * 查询logid大于指定值的SYSTRANSLOG记录
     * @throws java.sql.SQLException
	 */
	public ArrayList doFindForward(Connection con, Object obj)
		throws java.sql.SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		SysTransLog sysTransLog = null;
		String id = (String) obj;
		String sqlSelect =
			"select * from  SYSTRANSLOG  where logId > "
				+ id
				+ " order by logId ";
		ArrayList result = new ArrayList();
		try {
			stmt = con.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);
			int i = 0;
			while (rs.next() && i < Constants.PAGE_ROWS + 1) {
				sysTransLog = new SysTransLog();
				sysTransLog.setLogId(rs.getLong("logId"));
				sysTransLog.setStartTime(rs.getString("startTime"));
				sysTransLog.setEndTime(rs.getString("endTime"));
				sysTransLog.setTransId(rs.getString("transId"));
				sysTransLog.setTransName(rs.getString("transName"));
				sysTransLog.setOrigLogId(rs.getLong("origLogId"));
				sysTransLog.setLoginName(rs.getString("LoginName"));
				sysTransLog.setSessionId(rs.getString("sessionId"));
				sysTransLog.setTimeoutTime(rs.getInt("timeoutTime"));
				sysTransLog.setStatus(rs.getString("status"));
				result.add(sysTransLog);
				i++;
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();

		}
		return result;
	}

	/**
	 * 查询logid小于指定值的SYSTRANSLOG记录
     * @throws java.sql.SQLException
	 */
	public ArrayList doFindBack(Connection con, Object obj)
		throws java.sql.SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		SysTransLog sysTransLog = null;
		String id = (String) obj;
		String sqlSelect =
			"select * from  SYSTRANSLOG  where logId < "
				+ id
				+ " order by logId desc";
		ArrayList result = new ArrayList();
		try {
			stmt = con.prepareStatement(sqlSelect);
			rs = stmt.executeQuery(sqlSelect);
			int i = 0;
			ArrayList tmp = new ArrayList();
			while (rs.next() && i < Constants.PAGE_ROWS + 1) {
				sysTransLog = new SysTransLog();
				sysTransLog.setLogId(rs.getLong("logId"));
				sysTransLog.setStartTime(rs.getString("startTime"));
				sysTransLog.setEndTime(rs.getString("endTime"));
				sysTransLog.setTransId(rs.getString("transId"));
				sysTransLog.setTransName(rs.getString("transName"));
				sysTransLog.setOrigLogId(rs.getLong("origLogId"));
				sysTransLog.setLoginName(rs.getString("LoginName"));
				sysTransLog.setSessionId(rs.getString("sessionId"));
				sysTransLog.setTimeoutTime(rs.getInt("timeoutTime"));
				sysTransLog.setStatus(rs.getString("status"));
				tmp.add(sysTransLog);
				i++;
			}

			for (int j = tmp.size(); j > 0; j--) {
				result.add(tmp.get(j - 1));
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();

		}
		return result;
	}

	/**
	 * 分页查询
	 * @param conn
	 * @param sql
	 * @param count 每页显示记录数
	 * @param offset 开始显示偏移
	 * @return collection of LogMessage
	 * @throws java.sql.SQLException
	 */
	public ArrayList doFindBySql(
		Connection conn,
		String sql,
		int count,
		int offset)
		throws java.sql.SQLException {

		ArrayList arrayList = new ArrayList();

		StringBuffer sbSQL = new StringBuffer();
		sbSQL.append(" SELECT * from systranslog ");
		sbSQL.append(sql);
		sbSQL.append(ORDERBY);
		System.out.print(sbSQL + "\n");

        DBUtil.pageSQL(sbSQL.toString());
		ResultSet rs = null;
		PreparedStatement pStatement = null;
		try {

			pStatement = conn.prepareStatement(sbSQL.toString());
			pStatement.clearParameters();
			pStatement.setInt(1, offset + count);
            pStatement.setInt(2, offset);

			rs = pStatement.executeQuery();

			while (rs.next()) {
				SysTransLog sysTransLog = new SysTransLog();
				sysTransLog.setLogId(rs.getLong("logId"));
				sysTransLog.setStartTime(rs.getString("startTime"));
				sysTransLog.setEndTime(rs.getString("endTime"));
				sysTransLog.setTransId(rs.getString("transId"));
				sysTransLog.setTransName(rs.getString("transName"));
				sysTransLog.setOrigLogId(rs.getLong("origLogId"));
				sysTransLog.setLoginName(rs.getString("LoginName"));
				sysTransLog.setSessionId(rs.getString("sessionId"));
				sysTransLog.setTimeoutTime(rs.getInt("timeoutTime"));
				sysTransLog.setStatus(rs.getString("status"));
				arrayList.add(sysTransLog);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pStatement != null) {
				pStatement.close();
			}
		}
		sbSQL = null;
		System.out.println(arrayList);
		return arrayList;
	}

	public ArrayList doFindByTime(
		Connection con,
		String beginTime,
		String endTime,
		int count,
		int offset)
		throws java.sql.SQLException {
		String strSql =
			" WHERE starttime >= '"
				+ beginTime
				+ "'  and endtime <= '"
				+ endTime
				+ "' ";

		return this.doFindBySql(con, strSql, count, offset);

	}

	public ArrayList doFindByTransName(
		Connection con,
		String transName,
		int count,
		int offset)
		throws java.sql.SQLException {
		String strSql = " WHERE transName = '" + transName + "' ";

		return this.doFindBySql(con, strSql, count, offset);

	}

	public ArrayList doFindByLoginName(
		Connection con,
		String loginName,
		int count,
		int offset)
		throws java.sql.SQLException {
		String strSql = " WHERE LoginName = '" + loginName + "' ";

		return this.doFindBySql(con, strSql, count, offset);

	}

	public ArrayList doFindByStatus(
		Connection con,
		String status,
		int count,
		int offset)
		throws java.sql.SQLException {
		String strSql = " WHERE status = '" + status + "' ";

		return this.doFindBySql(con, strSql, count, offset);

	}

}
