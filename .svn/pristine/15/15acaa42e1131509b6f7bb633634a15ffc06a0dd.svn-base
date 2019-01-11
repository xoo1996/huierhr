/**
* <p>标题: 删除数据的变动历史处理</p>
* <p>说明: 需要下载数据的表必须通过此功能增加删除变动历史</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-9-513:26:39</p>
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

import org.radf.manage.version.entity.SysRemoveVersion;
import org.radf.plat.util.dao.DAOSupport;

public class SysRemoveVersionDAO extends DAOSupport {
    private String SELECT_KEY_SQL =
        "SELECT * FROM SYSREMOVEVERSION WHERE tableName = ? and version = ?";
    private String DELETE_SQL =
        "delete from SYSREMOVEVERSION where tableName = ? and version = ?";
    private String INSERT_SQL =
        "insert INTO SYSREMOVEVERSION(tableName,version,Sql,UPDATEDATE)VALUES(?,?,?,sysdate)";
    private String SELECT_CHANGE_SQL =
        "SELECT * FROM SYSREMOVEVERSION WHERE tableName = ? and version > ? and version <= ?";
    /**
     * This method is used to save the current object into DB
     * @see org.radf.plat.util.dao.DAO#doStore(Connection, Object)
     * @param conn java.sql.Connection
     * @param obj an Object being to save
     * @throws SQLException
     */
    public void doStore(Connection con, Object obj) throws SQLException {
        SysRemoveVersion dto = (SysRemoveVersion)obj;
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(INSERT_SQL);
            pstmt.setString(1, dto.getTableName());
            pstmt.setInt(2, dto.getVersion());
            pstmt.setString(3, dto.getSql());
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
        SysRemoveVersion dto = (SysRemoveVersion) obj;
        SysRemoveVersion beo = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SELECT_KEY_SQL);
            pstmt.setString(1, dto.getTableName());
            pstmt.setInt(2, dto.getVersion());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                beo = new SysRemoveVersion();
                beo.setTableName(rs.getString("tableName"));
                beo.setVersion(rs.getInt("Version"));
                beo.setTableName(rs.getString("sql"));
                beo.setUpdate(rs.getDate("UPDATEDATE"));
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
     * 获取指定表指定版本以后的删除数据
     * @param conn
     * @param sTableName
     * @param starVersion   开始版本
     * @param eneVersion    结束版本
     * @return
     * @throws SQLException
     */
    public Collection getVersionData(Connection conn,String sTableName,int starVersion,int endVersion)throws SQLException {
        ArrayList rev = new ArrayList();
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {
            pStatement = conn.prepareStatement(SELECT_CHANGE_SQL);
            pStatement.clearParameters();
            pStatement.setString(1, sTableName);
            pStatement.setInt(2, starVersion);
            pStatement.setInt(3, endVersion);
            rs = pStatement.executeQuery();
            while (rs.next()) {
                SysRemoveVersion beo = new SysRemoveVersion();
                beo.setTableName(rs.getString("TableName"));
                beo.setVersion(rs.getInt("Version"));
                beo.setSql(rs.getString("sql"));
                beo.setUpdate(rs.getDate("UPDATEDATE"));
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
