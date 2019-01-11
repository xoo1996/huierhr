/**
* <p>标题: 关于客户端数据版本表的DAO</p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-8-2612:55:47</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.radf.manage.version.entity.SysClientVersion;
import org.radf.plat.util.dao.DAOSupport;

public class SysClientVersionDAO extends DAOSupport {
    private static String INSERT_SQL =
        "insert into SysClientVersion (ip,type,tablename,nowversion,updateversion,updateDate) " +
                   " values(?,?,?,?,?,sysdate)";
    private static String UPDATE_SQL =
        "update SysClientVersion set nowversion = ?,updateversion = ?,updateDate = sysdate" +
                    " where ip = ? and type = ? and tablename = ?";
    private static String SELECT_ALL_SQL =
        "select * from sysclientversion";
    private static String SELECT_KEY_SQL =
        "select * from sysclientversion where ip = ? and type = ? and tablename = ?";
    
    /**
     * This method is used to save the current object into DB
     * @see org.radf.plat.util.dao.DAO#doStore(Connection, Object)
     * @param conn java.sql.Connection
     * @param obj an Object being to save
     * @throws SQLException
     */
    public void doStore(Connection con, Object obj) throws SQLException {
        SysClientVersion dto = (SysClientVersion)obj;
        
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(INSERT_SQL);
            pstmt.setString(1, dto.getIp());
            pstmt.setString(2, dto.getType());
            pstmt.setString(3, dto.getTableName());
            pstmt.setInt(4, dto.getNowVersion());
            pstmt.setInt(5, dto.getUpdateVersion());
            pstmt.executeUpdate();

        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    /**
     * This method is used to update the current object in DB
     * @see org.radf.plat.util.dao.DAO#doUpdate(Connection, Object)
     * @param conn java.sql.Connection
     * @param obj an Object being to update
     * @throws SQLException
     */
    public void doUpdate(Connection con, Object obj)
            throws SQLException {
        SysClientVersion dto = (SysClientVersion) obj;
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(UPDATE_SQL);
            pstmt.setInt(1, dto.getNowVersion());
            pstmt.setInt(2, dto.getUpdateVersion());
            pstmt.setString(3, dto.getIp());
            pstmt.setString(4, dto.getType());
            pstmt.setString(5, dto.getTableName());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
    
    /**
     * This method used to find entity from Database.
     * @see org.radf.plat.util.dao.DAO#doFind(Connection, Object)
     * @param conn java.sql.Connection
     * @param obj an object holds the input parameters and output attribute
     * @return Object with value
     * @throws SQLException
     */
    public Object doFind(Connection con, Object obj)
            throws SQLException {
        SysClientVersion dto = (SysClientVersion) obj;
        SysClientVersion beo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SELECT_KEY_SQL);
            pstmt.setString(1, dto.getIp());
            pstmt.setString(2, dto.getType());
            pstmt.setString(3, dto.getTableName());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                beo = new SysClientVersion();
                beo.setIp(rs.getString("ip"));
                beo.setType(rs.getString("type"));
                beo.setTableName(rs.getString("tableName"));
                beo.setNowVersion(rs.getInt("NowVersion"));
                beo.setUpdateVersion(rs.getInt("updateVersion"));
                beo.setUpdateDate(rs.getDate("UpdateDate"));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return beo;
    }
    
    /**
     * 查询所有
     * @see org.radf.plat.util.dao.DAO#getAllRecords(Connection)
     * @param con    java.sql.Connection
     * @return Collection ChargeItemCode
     * @throws SQLException
     */
    public Collection getAllRecords(Connection con)throws SQLException {
        ArrayList rev = new ArrayList();
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {
            pStatement = con.prepareStatement(SELECT_ALL_SQL);
            pStatement.clearParameters();
            rs = pStatement.executeQuery();
            while (rs.next()) {
                SysClientVersion beo = new SysClientVersion();
                beo.setIp(rs.getString("ip"));
                beo.setType(rs.getString("type"));
                beo.setTableName(rs.getString("tableName"));
                beo.setNowVersion(rs.getInt("NowVersion"));
                beo.setUpdateVersion(rs.getInt("updateVersion"));
                beo.setUpdateDate(rs.getDate("UpdateDate"));
                rev.add(beo);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pStatement != null) {
                pStatement.close();
            }
        }
        return rev;
    }

}
