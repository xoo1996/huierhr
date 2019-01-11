package org.radf.manage.trans.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.radf.manage.trans.entity.SysTranseDef;


/**
 *����SYSTRANSEDEF��DAO
 */
public class SysTranseDefDAO extends org.radf.plat.util.dao.DAOSupport{

    
     /**
      *����SYSTRANSEDEF��¼
      */
     public Object doCreate(Connection con,Object obj)
     throws java.sql.SQLException{
         PreparedStatement pstmt = null;
         SysTranseDef sysTranseDef = (SysTranseDef)obj;

         String sqlCreate = "insert into SYSTRANSEDEF (transId,transName,transDesc,timeOut,transType,undoTransId,redoTransId,inputDataType,outputDataType ) values (?,?,?,?,?,?,?,'01','01')";
         try{
            pstmt = con.prepareStatement(sqlCreate);
            pstmt.setString(1 ,sysTranseDef.getTransId() );
            pstmt.setString(2 ,sysTranseDef.getTransName() );
            pstmt.setString(3 ,sysTranseDef.getTransDesc() );
            pstmt.setInt(4 ,sysTranseDef.getTimeOut() );
            pstmt.setString(5 ,sysTranseDef.getTransType() );
            pstmt.setString(6 ,sysTranseDef.getUndoTransId() );
            pstmt.setString(7 ,sysTranseDef.getRedoTransId() );
            //pstmt.setString(8 ,sysTranseDef.getInputDataType() );
            //pstmt.setString(9 ,sysTranseDef.getOutputDataType() );

            pstmt.executeUpdate();
         }finally{
             if(pstmt != null)
                 pstmt.close();
         }
         return sysTranseDef;
     }
     
     public void doStore(Connection con,Object obj)
     throws java.sql.SQLException{
         doCreate(con,obj);
     }
     /**
      *�޸�SYSTRANSEDEF��¼
      */
     public void doUpdate(Connection con,Object obj)
     throws java.sql.SQLException{

         PreparedStatement pstmt = null;
         SysTranseDef sysTranseDef = (SysTranseDef)obj;

         String sqlModify = "update SYSTRANSEDEF set transId = ?,transName = ?,transDesc = ?,timeOut = ?,transType = ?,undoTransId = ?,redoTransId = ?,inputDataType = ?,outputDataType = ? where transId = ? ";
         try{

            pstmt = con.prepareStatement(sqlModify);
            pstmt.setString(1 ,sysTranseDef.getTransId() );
            pstmt.setString(2 ,sysTranseDef.getTransName() );
            pstmt.setString(3 ,sysTranseDef.getTransDesc() );
            pstmt.setInt(4 ,sysTranseDef.getTimeOut() );
            pstmt.setString(5 ,sysTranseDef.getTransType() );
            pstmt.setString(6 ,sysTranseDef.getUndoTransId() );
            pstmt.setString(7 ,sysTranseDef.getRedoTransId() );
            pstmt.setString(8 ,"01" );
            pstmt.setString(9 ,"01" );

           pstmt.setString(10 ,sysTranseDef.getTransId() );
            pstmt.executeUpdate();
         }finally{
             if(pstmt != null)
                 pstmt.close();
         }
     }

     /**
      *ɾ��SYSTRANSEDEF��¼
      *@param con
      *@param Object SysTranseDef
      */
     public void doDelete(Connection con , Object obj)
     throws java.sql.SQLException{
         PreparedStatement pstmt = null;
        String id = ((SysTranseDef)obj).getTransId();

         String sqlDelete = "delete from  SYSTRANSEDEF  where transId = ? ";
         try{

            pstmt = con.prepareStatement(sqlDelete);
           pstmt.setString(1 ,id );
            pstmt.executeUpdate();
         }finally{
             if(pstmt != null)
                 pstmt.close();
         }
     }

     /**
      * ��ѯSYSTRANSEDEF��¼
      */
     public Object doFind(Connection con, Object obj)
            throws java.sql.SQLException {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SysTranseDef sysTranseDef = null;
        String id = ((SysTranseDef) obj).getTransId();
        String sqlSelect = "select * from  SYSTRANSEDEF  where transId = ? ";
        try {
            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sysTranseDef = new SysTranseDef();
                sysTranseDef.setTransId(rs.getString("transId"));
                sysTranseDef.setTransName(rs.getString("transName"));
                sysTranseDef.setTransDesc(rs.getString("transDesc"));
                sysTranseDef.setTimeOut(rs.getInt("timeOut"));
                sysTranseDef.setTransType(rs.getString("transType"));
                sysTranseDef.setUndoTransId(rs.getString("undoTransId"));
                sysTranseDef.setRedoTransId(rs.getString("redoTransId"));
                sysTranseDef.setInputDataType(rs.getString("inputDataType"));
                sysTranseDef.setOutputDataType(rs.getString("outputDataType"));
            }
        } finally {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();

        }
        return sysTranseDef;
    }

     /**
      *��ѯ����SYSTRANSEDEF��¼
      */
     public ArrayList doFindAll( Connection con)
     throws java.sql.SQLException{

         Statement stmt = null;
         ResultSet rs = null;
         SysTranseDef sysTranseDef = null;
         String sqlSelect = "select * from  SYSTRANSEDEF order by transId";
         ArrayList result = new ArrayList();
         try{
             stmt = con.createStatement();
             rs = stmt.executeQuery(sqlSelect);
             while(rs.next()){
                 sysTranseDef = new SysTranseDef();
                 sysTranseDef.setTransId (rs.getString("transId"));
                 sysTranseDef.setTransName (rs.getString("transName"));
                 sysTranseDef.setTransDesc (rs.getString("transDesc"));
                 sysTranseDef.setTimeOut (rs.getInt("timeOut"));
                 sysTranseDef.setTransType (rs.getString("transType"));
                 sysTranseDef.setUndoTransId (rs.getString("undoTransId"));
                 sysTranseDef.setRedoTransId (rs.getString("redoTransId"));
                 sysTranseDef.setInputDataType (rs.getString("inputDataType"));
                 sysTranseDef.setOutputDataType (rs.getString("outputDataType"));
                 result.add(sysTranseDef);
             }
         }finally{
             if(rs != null)
                 rs.close();
             if(stmt != null)
                 stmt.close();

         }
         return result;
     }


}



