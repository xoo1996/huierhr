package org.radf.manage.trans.dao;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.radf.manage.trans.entity.SysTransVastInput;

/**
 * 管理SYSTRANSVASTINPUT 的DAO
 */
public class SysTransVastInputDAO extends org.radf.plat.util.dao.DAOSupport{

     /**
      * 查询SYSTRANSVASTINPUT记录
      */
     public Object doFind( Connection con, Object obj)
     throws java.sql.SQLException{

         Statement stmt = null;
         ResultSet rs = null;
         SysTransVastInput sysTransVastInput = null;
         String id = (String)obj;
         String sqlSelect = "select * from  SYSTRANSVASTINPUT  where logId = " + id;
         try{
            stmt = con.createStatement();

            rs = stmt.executeQuery(sqlSelect);
            if(rs.next()){
                Blob blob = rs.getBlob("data");
                InputStream instream = blob.getBinaryStream();
                byte[] buffer = new byte[(int)blob.length()];
                instream.read(buffer);
                instream.close();
                sysTransVastInput = new SysTransVastInput();
                sysTransVastInput.setLogId(rs.getLong("logid"));
                sysTransVastInput.setData(buffer);
            }
         }catch(Exception e){
             e.printStackTrace();
         }finally{
             if(rs != null)
                 rs.close();
             if(stmt != null)
                 stmt.close();

         }
         return sysTransVastInput;
     }

     /**
      * 生成SYSTRANSVASTINPUT记录
      */
     public void doStore(Connection conn, Object obj) throws SQLException{
        PreparedStatement ps = null;
        SysTransVastInput input = (SysTransVastInput)obj;

        try{
            String sqlInsert = "insert into SYSTRANSVASTINPUT(logID,data) values(?,EMPTY_BLOB())";

            ps = conn.prepareStatement(sqlInsert);

            ps.setLong(1,input.getLogId());
            ps.executeUpdate();

            ps.close();

            String sqlUpdate = "update SYSTRANSVASTINPUT set data = ? where logid = ?";
            ps = conn.prepareStatement(sqlUpdate);

            byte[] tmp = input.getData();
            ps.setBytes(1,tmp);
            ps.setLong(2,input.getLogId());

            ps.executeUpdate();

        }catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(ps != null)
                ps.close();
        }
    }

    /**
     * 删除SYSTRANSVASTINPUT记录
     */
    public void doDelete(Connection conn,Object obj) throws SQLException{
        PreparedStatement ps = null;
        String stLogId = (String)obj;
        long logId = Long.parseLong(stLogId);

        String sqlDelete = "delete from SYSTRANSVASTINPUT where logID = ?";
        try{

            ps = conn.prepareStatement(sqlDelete);
            ps.setLong(1,logId);

            ps.executeUpdate();
        }catch(SQLException sqle){
            throw sqle;
        }
        finally{
            if(ps != null)
                ps.close();
        }
    }
    /**
     * 修改SYSTRANSVASTINPUT记录
     */
    public void doUpdate(Connection conn,Object obj) throws SQLException{
        throw new UnsupportedOperationException("This Operation is forbiden.");
    }



}



