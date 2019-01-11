package org.radf.manage.trans.dao;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.radf.manage.trans.entity.SysTransVastOutput;


/**
 * 管理SYSTRANSVASTOUTPUT的DAO
 */
public class SysTransVastOutputDAO extends org.radf.plat.util.dao.DAOSupport{

  /**
   * 查询SYSTRANSVASTOUTPUT记录
   */
    public Object doFind( Connection con, Object obj)
    throws java.sql.SQLException{

        Statement stmt = null;
        ResultSet rs = null;
        SysTransVastOutput sysTransVastOutput = null;
        String id = (String)obj;
        String sqlSelect = "select * from  SYSTRANSVASTOUTPUT  where logId = " + id;
        try{
           stmt = con.createStatement();

           rs = stmt.executeQuery(sqlSelect);
           if(rs.next()){
               Blob blob = rs.getBlob("data");
               InputStream instream = blob.getBinaryStream();
               byte[] buffer = new byte[(int)blob.length()];
               instream.read(buffer);
               instream.close();
               sysTransVastOutput = new SysTransVastOutput();
               sysTransVastOutput.setLogId(rs.getLong("logid"));
               sysTransVastOutput.setData(buffer);
           }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();

        }
        return sysTransVastOutput;
     }
     /**
      * 生成SYSTRANSVASTOUTPUT记录
      */
     public void doStore(Connection conn, Object obj) throws SQLException{
        PreparedStatement ps = null;

        SysTransVastOutput input = (SysTransVastOutput)obj;

        try{

            String sqlInsert = "insert into SYSTRANSVASTOUTPUT(logID,data) values(?,EMPTY_BLOB())";

             ps = conn.prepareStatement(sqlInsert);

             ps.setLong(1,input.getLogId());
             ps.executeUpdate();

             ps.close();

             String sqlUpdate = "update SYSTRANSVASTOUTPUT set data = ? where logid = ?";
             ps = conn.prepareStatement(sqlUpdate);

             byte[] tmp = input.getData();
             ps.setBytes(1,tmp);
             ps.setLong(2,input.getLogId());

            ps.executeUpdate();
        }catch(SQLException sqle){
            //log("Catch SQLException when trying to find Transaction Info from DB!!!"+sqle);
            throw sqle;
        }
        finally{
            if(ps != null)
                ps.close();
        }
    }

    /**
     * 删除SYSTRANSVASTOUTPUT记录
     */
    public void doDelete(Connection conn,Object obj) throws SQLException{
        PreparedStatement ps = null;
        String stLogId = (String)obj;
        long logId = Long.parseLong(stLogId);
        String sqlDelete = "delete from systransvastoutput where logid = ?";
        try{

            ps = conn.prepareStatement(sqlDelete);
            ps.setLong(1,logId);
            ps.executeUpdate();
        }catch(SQLException sqle){
            //log("Catch SQLException when trying to find Transaction Info from DB!!!"+sqle);
            throw sqle;
        }
        finally{
            if(ps != null)
                ps.close();
        }
    }
    /**
     * 修改SYSTRANSVASTOUTPUT记录
     */
    public void doUpdate(Connection conn,Object obj) throws SQLException{
        throw new UnsupportedOperationException("This Operation is forbiden.");
    }



}



