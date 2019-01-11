package org.radf.manage.logMessage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import org.radf.manage.logMessage.dto.LogMessageDTO;
import org.radf.manage.logMessage.entity.LogMessage;
import org.radf.manage.role.entity.SysUser;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.util.dao.DAOSupport;
import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalNames;

/**
 * <p>
 * Title: 日志管理
 * </p>
 * <p>
 * Description:DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: LBS
 * </p>
 * 
 * @author <a href=mailto:qin_yalan@sina.com>Qin Yalan </a>
 * @version 1.0
 */

public class LogMessageDAO extends DAOSupport {
    private final static String tableName = "sys_logmessage";

    public LogMessageDAO() {

    }

    /**
     * save the log information into the storage.
     * 
     * @param log
     *            an instance of <code>LogMessage</code>
     * @return true if saving operation successful else return false
     */
    public boolean insertLogMessage(LogMessageDTO log) {
        boolean returnFlag = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 取日志连接池特有连接
            conn = DBUtil.getLogConnection();

            // 生成SQL语句
            String sql = constructInsertSql(log, conn);

            // 保存
            ps = conn.prepareStatement(sql);
            returnFlag = ps.execute();
            conn.commit();
        } catch (NoConnectionException ex) {
            ex.printStackTrace();
        } catch (SQLException sqle) {
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                System.out.println("catch SQLException when insert log info");
            }
            sqle.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (conn != null) {
                    conn.rollback();
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return returnFlag;
    }

    /**
     * 根据LogMessageDTO生成日志SQL语句，其中id从数据库中生成流水
     * 
     * @param log
     *            LogMessageDTO
     * @param con
     *            Connection
     * @return String SQL语句
     * @throws SQLException
     */
    private String constructInsertSql(LogMessageDTO log, Connection con)
            throws SQLException {
        if (log == null || con == null) {
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                System.out.println("Can not log null information!!!");
            }

            return null;
        }

        StringBuffer buffer = new StringBuffer();
        String loginName = null;
        String sessionID = null;
        String functionID = null;
        if (log.getRequestInfo() == null) {
            loginName = "";
            sessionID = "";
            functionID = "";

        } else {
            loginName = log.getRequestInfo().getLoginName();
            sessionID = log.getRequestInfo().getSessionID();
            functionID = log.getRequestInfo().getFunctionID();
        }

        String dateTime = log.getDateTime();
        String message = log.getMessage();
        String sequenceID = getSequence(con);
        int errorCode = log.getCode();

        // 对超过500位长的message进行截除超长部分的处理 CFY2003-03-24 增加
        // 因一个汉字占两位，所以必须以byte长为准
        byte[] bytes = message.getBytes();
        if (bytes.length > 500) {
            byte[] msgBytes = new byte[500];
            System.arraycopy(bytes, 0, msgBytes, 0, 500);
            message = new String(msgBytes);
            msgBytes = null;
        }
        bytes = null;

        buffer.append("insert into " + tableName
                + " (id,code,message,msgdate,sessionID,loginName,functionID) ");
        buffer.append(" values( '" + sequenceID + "'," + errorCode + ",'"
                + message + "','" + dateTime + "','" + sessionID + "',");
        buffer.append(" '" + loginName + "','" + functionID + "')");
        return buffer.toString();
    }

    /**
     * This method is used to retrive the <code>LogMessage</code> from storage
     * according to the query info
     * 
     * @return a collection of LogMessage instances
     */
    public Collection getLogMessageList(String loginName, String sessionID,
            String functionID, int code, String startDate, String endDate) {
        Vector returnValue = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (loginName == null && sessionID == null && functionID == null
                && code == 0 && startDate == null && endDate == null) {
            return null;
        }
        String sqlStr = constructQuerySQL(loginName, sessionID, functionID,
                code, startDate, endDate);

        if (GlobalNames.DEBUG_OUTPUT_FLAG) {
            System.out.println("sqlStr = \n" + sqlStr);
        }

        try {
            try {
                conn = DBUtil.getConnection();
            } catch (NoConnectionException ex) {
            }
            ps = conn.prepareStatement(sqlStr);
            rs = ps.executeQuery();
            LogMessageDTO obj = new LogMessageDTO();
            returnValue = new Vector();
            while (rs.next()) {
                obj.setCode(rs.getInt(1));
                obj.setMessage(rs.getString(2));
                obj.setDateTime(rs.getString(3));
                RequestEnvelopHead head = new RequestEnvelopHead();
                head.setSessionID(rs.getString(4));
                head.setLoginName(rs.getString(5));
                head.setFunctionID(rs.getString(6));
                obj.setRequestInfo(head);
                returnValue.add(obj);
            }
        } catch (SQLException sqle) {
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                System.out
                        .println("Catch Exception when trying to query log info");
            }
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return returnValue;
    }

    private String constructQuerySQL(String loginName, String sessionID,
            String functionID, int code, String startDate, String endDate) {
        Properties prop = new Properties();
        if (loginName != null)
            prop.put("userID", loginName);
        if (sessionID != null)
            prop.put("sessionID", sessionID);
        if (functionID != null)
            prop.put("functionID", functionID);
        if (0 < code && code < 30000)
            prop.put("code", new Integer(code));
        if (startDate != null)
            prop.put("startDate", startDate);
        if (endDate != null)
            prop.put("endDate", endDate);

        StringBuffer buffer = new StringBuffer();
        buffer.append("select code,message,msgdate,sessionID,loginName,functionID from "
                        + tableName + " where");
        int count = 0;
        Enumeration enu = prop.keys();
        while (enu.hasMoreElements()) {
            if (count != 0)
                buffer.append(" and ");
            String keyName = (String) enu.nextElement();
            Object value = prop.get(keyName);
            if (keyName.equals("startDate"))
                buffer.append(" '" + value + "' < msgdate ");
            else if (keyName.equals("endDate"))
                buffer.append(" '" + value + "' > msgdate ");
            else if (keyName.equals("code"))
                buffer.append(" code = " + value);
            else
                buffer.append(" " + keyName + "='" + value + "' ");
            count++;
        }
        buffer.append(" order by msgdate");
        return buffer.toString();
    }

    /**
     * 获取日志需要的SEQUENCE
     * 
     * @param con
     * @return
     * @throws SQLException
     */
    private String getSequence(Connection con) throws SQLException {
        return DBUtil.getSequence(con, GlobalNames.LOG_MESSAGE_SEQNAME);
    }

    /**
     * 查询 order by
     */
    final static String ORDERBY = " ORDER BY ID DESC ";

    /**
     * 日志分页查询
     * 
     * @param conn
     * @param sql
     *            查询条件，where开始
     * @param count
     *            每页显示记录数
     * @param offset
     *            开始显示偏移
     * @return collection of LogMessage
     * @throws SQLException
     */
    public Collection doFindBySql(Connection conn, String sql, int count,
            int offset) throws SQLException {

        ArrayList arrayList = new ArrayList();

        StringBuffer sbSQL = new StringBuffer(256);
        sbSQL.append(" SELECT ");
        sbSQL.append(" ID, ");
        sbSQL.append(" CODE, ");
        sbSQL.append(" MESSAGE, ");
        sbSQL.append(" MSGDATE, ");
        sbSQL.append(" SESSIONID, ");
        sbSQL.append(" loginName, ");
        sbSQL.append(" FUNCTIONID, ");
        sbSQL.append(" IP  ");
        sbSQL.append(" FROM LOGMESSAGE");
        sbSQL.append(sql);
        sbSQL.append(ORDERBY);
        
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
                LogMessage dto = new LogMessage();

                dto.setId(rs.getString("ID")); // ID
                dto.setCode(rs.getInt("CODE")); // CODE
                dto.setMessage(rs.getString("MESSAGE")); // MESSAGE
                dto.setMsgdate(rs.getString("MSGDATE")); // MSGDATE
                dto.setSessionid(rs.getString("SESSIONID")); // SESSIONID
                dto.setLoginName(rs.getString("loginName")); // loginName
                dto.setFunctionid(rs.getString("FUNCTIONID")); // FUNCTIONID
                dto.setIp(rs.getString("IP")); // IP

                arrayList.add(dto);
            }

            return arrayList;

        } catch (SQLException sqle) {
            throw sqle;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pStatement != null) {
                pStatement.close();
            }
            sbSQL = null;
        }
    }

    // ******************** Select Code Begin ********************

    /**
     * This method used to find entity from Database.
     * 
     * @see org.radf.plat.util.dao.DAO#doFind(Connection, Object)
     * @param conn
     *            java.sql.Connection
     * @param obj
     *            an object holds the input parameters and output attribute
     * @return Object with value
     * @throws NotFindException,SQLException
     */
    public Object doFind(Connection conn, Object obj) throws NotFindException,
            SQLException {

        LogMessage dto = (LogMessage) obj;

        StringBuffer sbSQL = new StringBuffer(128);
        sbSQL.append(" SELECT ");
        sbSQL.append(" ID, ");
        sbSQL.append(" CODE, ");
        sbSQL.append(" MESSAGE, ");
        sbSQL.append(" MSGDATE, ");
        sbSQL.append(" SESSIONID, ");
        sbSQL.append(" loginName, ");
        sbSQL.append(" IP, ");
        sbSQL.append(" FUNCTIONID  ");
        sbSQL.append(" FROM LOGMESSAGE");
        sbSQL.append(" WHERE ID= ? ");

        ResultSet rs = null;
        PreparedStatement pStatement = null;

        try {
            pStatement = conn.prepareStatement(sbSQL.toString());
            pStatement.clearParameters();

            pStatement.setString(1, dto.getId());

            rs = pStatement.executeQuery();

            if (!rs.next()) {
                String notFindMsg = "未查找到日志编码为: " + dto.getId() + " 的日志信息！";
                throw new NotFindException(notFindMsg);
            }

            dto.setId(rs.getString("ID")); // ID
            dto.setCode(rs.getInt("CODE")); // CODE
            dto.setMessage(rs.getString("MESSAGE")); // MESSAGE
            dto.setMsgdate(rs.getString("MSGDATE")); // MSGDATE
            dto.setSessionid(rs.getString("SESSIONID")); // SESSIONID
            dto.setLoginName(rs.getString("loginName")); // loginName
            dto.setFunctionid(rs.getString("FUNCTIONID")); // FUNCTIONID
            dto.setIp(rs.getString("IP"));

            return dto;

        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pStatement != null) {
                pStatement.close();
            }

            sbSQL = null;
        }
    }

    // ******************** Select Code End ********************

    // ******************** Delete Code Begin ********************

    /**
     * This method is used to delete the object from DB
     * 
     * @see org.radf.plat.util.dao.DAO#doDelete(Connection, Object)
     * @param conn
     *            java.sql.Connection
     * @param obj
     *            an Object being to delete
     * @param conn
     *            java.sql.Connection
     * @throws SQLException
     */
    public void doDelete(Connection conn, Object obj) throws SQLException {

        LogMessage dto = (LogMessage) obj;

        StringBuffer sbSQL = new StringBuffer();
        sbSQL.append(" DELETE FROM LOGMESSAGE ");
        sbSQL.append(" WHERE ID= ?");

        PreparedStatement pStatement = null;
        try {
            pStatement = conn.prepareStatement(sbSQL.toString());
            pStatement.clearParameters();

            pStatement.setString(1, dto.getId()); // ID

            pStatement.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (pStatement != null) {
                pStatement.close();
            }

            sbSQL = null;
        }
    }

    // ******************** Delete Code End ********************

    /**
     * This method used to find entity from Database.
     * 
     * @see org.radf.plat.util.dao.DAO#doFind(Connection, Object)
     * @param conn
     *            java.sql.Connection
     * @param message
     *            select condition, mapping to MESSAGE in DB
     * @param count
     *            每页显示记录数
     * @param offset
     *            开始显示偏移
     * @return Collection of LogMessage with value
     * @throws SQLException
     */
    public Collection doFindByMessage(Connection conn, String message,
            int count, int offset) throws SQLException {

        String strSql = " WHERE upper(MESSAGE) LIKE '%" + message.toUpperCase()
                + "%' ";

        try {
            return this.doFindBySql(conn, strSql, count, offset);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * This method used to find entity from Database.
     * 
     * @see org.radf.plat.util.dao.DAO#doFind(Connection, Object)
     * @param conn
     *            java.sql.Connection
     * @param beginMsgDate
     *            select condition,format "2002-12-10 21:27:27"
     * @param endMsgDate
     *            select condition:[beginMsgDate,endMsgDate]
     * @param count
     *            每页显示记录数
     * @param offset
     *            开始显示偏移
     * @return Collection of LogMessage with value
     * @throws SQLException
     */
    public Collection doFindByMsgdate(Connection conn, String beginMsgDate,
            String endMsgDate, int count, int offset) throws SQLException {

        String strSql = " WHERE TO_DATE(MSGDATE,'yyyy-mm-dd hh24-mi-ss') >= TO_DATE('"
                + beginMsgDate + "', 'yyyy-mm-dd hh24-mi-ss') ";

        if (!endMsgDate.equals("")) {
            strSql += (" AND TO_DATE(MSGDATE,'yyyy-mm-dd hh24-mi-ss') <= TO_DATE('"
                    + endMsgDate + "', 'yyyy-mm-dd hh24-mi-ss') ");
        }

        try {
            return this.doFindBySql(conn, strSql, count, offset);
        } catch (SQLException e) {
            throw e;
        }

    }

    /**
     * This method used to find entity from Database.
     * 
     * @see org.radf.plat.util.dao.DAO#doFind(Connection, Object)
     * @param conn
     *            java.sql.Connection
     * @param loginName
     *            select condition,Mapping loginName in DB
     * @param count
     *            每页显示记录数
     * @param offset
     *            开始显示偏移
     * @return Collection of LogMessage with value
     * @throws SQLException
     */
    public Collection doFindByUserid(Connection conn, SysUser user, int count,
            int offset) throws SQLException {

        String strSql = " WHERE loginName = '" + user.getLoginName() + "'";

        try {
            return this.doFindBySql(conn, strSql, count, offset);
        } catch (SQLException e) {
            throw e;
        }
    }

    /**
     * This method used to find entity from Database.
     * 
     * @see org.radf.plat.util.dao.DAO#doFind(Connection, Object)
     * @param conn
     *            java.sql.Connection
     * @param functionid
     *            select condition,Mapping FUNCTIONID in DB
     * @param count
     *            每页显示记录数
     * @param offset
     *            开始显示偏移
     * @return Collection of LogMessage with value
     * @throws SQLException
     */
    public Collection doFindByFunctionid(Connection conn, String functionid,
            int count, int offset) throws SQLException {

        String strSql = " WHERE FUNCTIONID = '" + functionid + "'";

        try {
            return this.doFindBySql(conn, strSql, count, offset);
        } catch (SQLException e) {
            throw e;
        }
    }

    public String doSelectSQL() throws SQLException {
        return "SELECT * FROM SYS_LOGMESSAGE  ";
    }

    public Collection doBuildPage(ResultSet rs) throws SQLException {
        Collection aclList = new ArrayList();
        LogMessage dto = null;
        while (rs.next()) {

            dto = new LogMessage();

            dto.setId(rs.getString("ID")); // ID
            dto.setCode(rs.getInt("CODE")); // CODE
            dto.setMessage(rs.getString("MESSAGE")); // MESSAGE
            dto.setMsgdate(rs.getString("MSGDATE")); // MSGDATE
            dto.setSessionid(rs.getString("SESSIONID")); // SESSIONID
            dto.setLoginName(rs.getString("loginName")); // loginName
            dto.setFunctionid(rs.getString("FUNCTIONID")); // FUNCTIONID
            dto.setIp(rs.getString("IP")); // IP
            aclList.add(dto);
        }
        return aclList;
    }
}
