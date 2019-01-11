/**
* <p>标题: 系统版本表数据操作类</p>
* <p>说明: DAO</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-6-1317:01:08</p>
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

import org.radf.manage.version.entity.SysVersion;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.dao.DAOSupport;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class SysVersionDAO extends DAOSupport {
    private static final String INSERT_SQL =
        "insert into SysVersion (tablename,version,createtime,note,type,bpo,bz) " +
                   "values(?,?,sysdate,?,?,?,?)";
    private static final String UPDATE_SQL =
        "update SysVersion set version =?,createtime=sysdate,note=? where tablename = ?";
    //update sysVersion set version = (1+select max(version) from SysVersion),createtime=sysdate);
    private static final String SELECT_KEY_SQL =
        "select * from SysVersion where tablename = ?";
    private static final String SELECT_ALL_SQL =
        "select * from sysVersion where bz = '1' order by tablename";
    /**
     * 增加一条版本记录
     * 实际中不会单独使用，仅在修改时如果发现没有原始记录时自动增加
     * @param Connection con
     * @param SysVersion ver
     * @return int version
     * @throws java.sql.SQLException
     */
    public int doCreate(Connection con, SysVersion ver)
            throws java.sql.SQLException {
        PreparedStatement pstmt = null;
        ver.setVersion(getSEQUE(con));
        pstmt = con.prepareStatement(INSERT_SQL);
        pstmt.setString(1, ver.getTableName());
        pstmt.setInt(2, ver.getVersion());
        pstmt.setString(3, ver.getNote());
        pstmt.setString(4, ver.getType());
        pstmt.setString(5, ver.getBpo());
        pstmt.setString(6, ver.getBz());
        pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
        return ver.getVersion();
    }
    /**
     * 修改版本记录
     * @param con
     * @param obj
     * @return
     * @throws java.sql.SQLException
     */
    public int doUpdate(Connection con,SysVersion ver)
        throws java.sql.SQLException {
//        SysVersion ver = (SysVersion)obj;
        PreparedStatement pstmt = null;

        ver.setVersion(getSEQUE(con));
        pstmt = con.prepareStatement(UPDATE_SQL);
        pstmt.setInt(1, ver.getVersion());
        pstmt.setString(2, ver.getNote());
        pstmt.setString(3, ver.getTableName());
        pstmt.executeUpdate();

        if (pstmt != null)
            pstmt.close();
        
        return ver.getVersion();
    }
    /**
     * 获取指定表的当前版本号
     * @param con
     * @param tableName
     * @return
     */
    public int getCurrentVersion(Connection con,String tableName)
    throws java.sql.SQLException,NotFindException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int iVersion = 0;
        try {
            pstmt = con.prepareStatement(SELECT_KEY_SQL);
            pstmt.setString(1, tableName);
            rs = pstmt.executeQuery();
            if (rs == null)
                throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
            if (rs.next()) {
                iVersion = rs.getInt("version");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return iVersion;
    }
    /**
     * 获取所有有效的版本信息
     * @see org.radf.plat.util.dao.DAOSupport#getAllRecords(Connection);
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
                SysVersion beo = new SysVersion();
                beo.setTableName(rs.getString("TableName"));
                beo.setVersion(rs.getInt("Version"));
                beo.setCreatetime(rs.getDate("Createtime"));
                beo.setNote(rs.getString("Note"));
                beo.setType(rs.getString("type"));
                beo.setBpo(rs.getString("bpo"));
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
    
    private int getSEQUE(Connection con)throws java.sql.SQLException{
        String seq = DBUtil.getSequence(con,"\"SEQ_VERSION\"");
        return new Integer(seq).intValue();
    }
    
}
