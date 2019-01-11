package org.radf.manage.role.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.radf.manage.role.entity.SysChange;
import org.radf.manage.util.Constants;
import org.radf.plat.commons.DBUtil;



/**
 *管理SYSCHANGE的DAO
 */
public class SysChangeDAO extends org.radf.plat.util.dao.DAOSupport{
    private static final String INSERT_SQL =
        "insert into SYSCHANGE (id,createTime,data ) values (?,?,?)";
    private static final String UPDATE_SQL =
        "update SYSCHANGE set createTime = ?,data = ? where id = ? ";
    private static final String DELETE_SQL =
        "delete from  SYSCHANGE  where id = ? ";
    private static final String SELECT_ID_SQL =
        "select * from  SYSCHANGE  where id = ? ";
    private static final String SELECT_BIGGERTHAN_SQL =
        "select * from  SYSCHANGE  where id > ? ";

    /**
     * 生成SYSCHANGE记录
     */
    public void doStore(Connection con,Object obj)
    throws java.sql.SQLException{
        PreparedStatement pstmt = null;
        SysChange sysChange = (SysChange)obj;

        String sqlCreate = INSERT_SQL;
        try{
            String id = DBUtil.getSequence(con,Constants.SQ_SYSCHANGEID);
            pstmt = con.prepareStatement(sqlCreate);
            pstmt.setLong(1 ,Long.parseLong(id));
            pstmt.setDate(2 ,new java.sql.Date((new java.util.Date()).getTime()));
            pstmt.setString(3 ,sysChange.getData());
            pstmt.executeUpdate();
        }finally{
            if(pstmt != null)
                pstmt.close();
        }
    }
    /**
     * 生成SYSCHANGE记录
     */
    public void createChange(Connection con,String data)
    throws SQLException{
         SysChange change = new SysChange();
         change.setData(data);
         doStore(con,change);
    }

    /**
     * 修改SYSCHANGE记录
     */
    public void doUpdate(Connection con,Object obj)
    throws java.sql.SQLException{

        PreparedStatement pstmt = null;
        SysChange sysChange = (SysChange)obj;

        String sqlModify = UPDATE_SQL;
        try{
           pstmt = con.prepareStatement(sqlModify);
           pstmt.setDate(1 ,sysChange.getCreateTime() );
           pstmt.setString(2 ,sysChange.getData() );
           pstmt.setLong(3 ,sysChange.getId() );
           pstmt.executeUpdate();
        }finally{
            if(pstmt != null)
                pstmt.close();
        }
    }

    /**
     * 删除SYSCHANGE记录
     */
    public void doDelete(Connection con, Object obj)
            throws java.sql.SQLException {
        PreparedStatement pstmt = null;
        SysChange sysChange = (SysChange) obj;

        String sqlDelete = DELETE_SQL;
        try {

            pstmt = con.prepareStatement(sqlDelete);
            pstmt.setLong(1, sysChange.getId());
            pstmt.executeUpdate();
        } finally {
            if (pstmt != null)
                pstmt.close();
        }
    }

    /**
     * 查询SYSCHANGE记录
     */
    public Object doFind(Connection con, Object obj)
            throws java.sql.SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SysChange sysChange = (SysChange) obj;
        String sqlSelect = SELECT_ID_SQL;
        try {
            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setLong(1, sysChange.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sysChange.setId(rs.getLong("id"));
                sysChange.setCreateTime(rs.getDate("createTime"));
                sysChange.setData(rs.getString("data"));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
        }
        return obj;
    }

     /**
      * 查询id大于指定值的SYSCHANGE记录
      */
     public ArrayList doFindByBiggerThan(Connection con,long id)
     throws java.sql.SQLException{
         PreparedStatement pstmt = null;
         ResultSet rs = null;

         SysChange sysChange = null;
         String sqlSelect = SELECT_BIGGERTHAN_SQL;
         ArrayList resultset = new ArrayList();
         try{

             pstmt = con.prepareStatement(sqlSelect);
             pstmt.setLong(1 ,id );
             rs = pstmt.executeQuery();
             while(rs.next()){
                 sysChange = new SysChange();
                 sysChange.setId (rs.getLong("id"));
                 //sysChange.setCreateTime (rs.getDate("createTime"));
                 sysChange.setData (rs.getString("data"));
                 resultset.add(sysChange);
             }
         }finally{
             if(rs != null)
                 rs.close();
             if(pstmt != null)
                 pstmt.close();
         }
         return resultset;
     }
 
}



