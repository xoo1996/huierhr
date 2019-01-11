package org.radf.manage.role.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.radf.manage.role.entity.SysFunction;
import org.radf.manage.util.Constants;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;
/**
 * 管理交易定义SysFunction的DAO
 * @author zqb
 * @version 1.0
 */
public class SysFunctionDAO extends org.radf.plat.util.dao.DAOSupport{
    
    private SysChangeDAO changeDAO = new SysChangeDAO();

    
    private static final String INSERT_SQL =
        "insert into SYSFUNCTION (functionId,flag,type,transFlag,functionDesc,parentId,signatureType,title,location,orderno,log,owner ) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_ALL_SQL =
        "update SYSFUNCTION set flag = ?,type = ?,transFlag = ?,functionDesc = ?,parentId = ?,signatureType = ?,title = ?,location = ?,orderno = ?,log = ? where functionId = ? ";
    private static final String UPDATE_FLAG_SQL =
        "update sysfunction set flag = ? where functionId = ?";
    private static final String DELETE_SQL =
        "delete from  SYSFUNCTION  where functionId = ?";
    private static final String SELECT_KEY_SQL =
        "select * from  SYSFUNCTION  where functionId = ? order by orderno";
    private static final String SELECT_ENCRYPT_SQL =
        "select functionId,signatureType from  SYSFUNCTION  where signatureType <> '"
        + GlobalNames.NORMAL_FUNCTION + "' order by orderno";
    private static final String SELECT_TITLE_SQL =
        "select * from SYSFUNCTION where title like ? order by orderno";
    private static final String SELECT_PARENT_SQL =
        "select * from SYSFUNCTION where parentId = ? order by orderno";
    private static final String SELECT_ALL_SQL =
        "select * from sysfunction order by orderno";
    private static final String SELECT_VER_SQL =
        "select * from sysfunction where version > ? order by version";
    /**
     * 生成一条sysfunction记录
     * @param connection
     * @param Object SysFunction
     * @return Object SysFunction
     * @exception java.sql.SQLException
     */
    public Object doCreate(Connection con,Object obj)
    throws java.sql.SQLException{
        PreparedStatement pstmt = null;
        SysFunction sysFunction = (SysFunction)obj;

        String sqlCreate = INSERT_SQL;
        try{
           pstmt = con.prepareStatement(sqlCreate);
           pstmt.setString(1 ,sysFunction.getFunctionId() );
           pstmt.setString(2 ,sysFunction.getFlag() );
           pstmt.setString(3 ,sysFunction.getType() );
           pstmt.setString(4 ,sysFunction.getTransFlag() );
           pstmt.setString(5 ,sysFunction.getFunctionDesc() );
           pstmt.setString(6 ,sysFunction.getParentId() );
           pstmt.setString(7 ,sysFunction.getSignatureType() );
           pstmt.setString(8 ,sysFunction.getTitle() );
           pstmt.setString(9 ,sysFunction.getLocation() );
           pstmt.setInt   (10,sysFunction.getOrderno() );
           pstmt.setString(11,sysFunction.getLog() );
           pstmt.setString(12,sysFunction.getOwner() );
           pstmt.executeUpdate();
        }finally{
            if(pstmt != null)
                pstmt.close();
        }
        
        String sql = "insert into sysfunction(functionId,flag,type,transflag,functionDesc,parentId,signatureType) "
            + "values( '" + sysFunction.getFunctionId() + "', '" + sysFunction.getFlag() + "','"
            + sysFunction.getType() + "','" + sysFunction.getTransFlag() + "','"
            + sysFunction.getFunctionDesc() + "','" + sysFunction.getParentId() + "','"
           + sysFunction.getSignatureType() + ")";
        changeDAO.createChange(con,sql);

        return sysFunction;
    }

     /**
      * 修改sysfunction记录
      * @param connection
      * @param Object SysFunction
      * @exception java.sql.SQLException
      */
     public void doUpdate(Connection con,Object obj)
     throws java.sql.SQLException{

         PreparedStatement pstmt = null;
         SysFunction sysFunction = (SysFunction)obj;

         String sqlModify = UPDATE_ALL_SQL;
         try{

            pstmt = con.prepareStatement(sqlModify);
            pstmt.setString(1 ,sysFunction.getFlag() );
            pstmt.setString(2 ,sysFunction.getType() );
            pstmt.setString(3 ,sysFunction.getTransFlag() );
            pstmt.setString(4 ,sysFunction.getFunctionDesc() );
            pstmt.setString(5 ,sysFunction.getParentId() );
            pstmt.setString(6 ,sysFunction.getSignatureType() );
            pstmt.setString(7 ,sysFunction.getTitle() );
            pstmt.setString(8 ,sysFunction.getLocation() );
            pstmt.setInt   (9 ,sysFunction.getOrderno() );
            pstmt.setString(10,sysFunction.getLog() );
            pstmt.setString(11,sysFunction.getFunctionId() );
            pstmt.executeUpdate();
         }finally{
             if(pstmt != null)
                 pstmt.close();
         }
         String sql = "update sysfunction set functionId = '" + sysFunction.getFunctionId()
             + "', flag = '" + sysFunction.getFlag() + "', type = '"
             + sysFunction.getType() + "' , transflag = '"
             + sysFunction.getTransFlag() + "', functionDesc = '"
             + sysFunction.getFunctionDesc() + "' , parentId = '"
             + sysFunction.getParentId() + "',signatureType = '"
             + sysFunction.getSignatureType() + "' where functionId = '"
             + sysFunction.getFunctionId() + "'";
            String id = DBUtil.getSequence(con,Constants.SQ_SYSCHANGEID);
        changeDAO.createChange(con,sql);
     }

     /**
      * 删除sysFunction记录
      * @param con
      * @param Object SysFunction
      * @exception java.sql.SQLException
      */
     public void doDelete(Connection con , Object obj)
     throws java.sql.SQLException{
         PreparedStatement pstmt = null;
         String funId = ((SysFunction)obj).getFunctionId();

         String sqlDelete = DELETE_SQL;
         try{
             //check if the function-control mapping exists
             pstmt = con.prepareStatement(sqlDelete);
             pstmt.setString(1 ,funId );
             pstmt.executeUpdate();
         }finally{
             if(pstmt != null)
                 pstmt.close();
         }
         
         String sql = "delete from sysFunction where functionId = '" + funId + "'";
         changeDAO.createChange(con,sql);

     }

     /**
      * 用主键查询sysfunction记录
      * @param con
      * @param Object SysFunction
      * @return Object SysFunction
      * @exception java.sql.SQLException
      * @exception NotFindException
      */
     public Object doFind( Connection con, Object obj)
     throws java.sql.SQLException,NotFindException{

         PreparedStatement pstmt = null;
         ResultSet rs = null;
         SysFunction sysFunction = null;
         String sqlSelect = SELECT_KEY_SQL;
         try{
            pstmt = con.prepareStatement(sqlSelect);
            pstmt.setString(1 ,((SysFunction)obj).getFunctionId() );
            rs = pstmt.executeQuery();
            if (rs == null){
                throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
            }
            if(rs.next()){
                sysFunction = new SysFunction();
                sysFunction.setFunctionId (rs.getString("functionId"));
                sysFunction.setFlag (rs.getString("flag"));
                sysFunction.setType (rs.getString("type"));
                sysFunction.setTransFlag (rs.getString("transFlag"));
                sysFunction.setFunctionDesc (rs.getString("functionDesc"));
                sysFunction.setParentId (rs.getString("parentId"));
                sysFunction.setSignatureType(rs.getString("signatureType"));
                sysFunction.setLocation(rs.getString("Location"));
                sysFunction.setLog(rs.getString("Log"));
                sysFunction.setOrderno(rs.getInt("Orderno"));
                sysFunction.setOwner(rs.getString("Owner"));
                sysFunction.setTitle(rs.getString("Title"));
                sysFunction.setImageID(rs.getInt("imageID"));
                sysFunction.setHelpID(rs.getInt("helpID"));
            }
          }finally{
              DBUtil.closeResStat(rs,pstmt);
         }
         return sysFunction;
     }

     
     
     /* (non-Javadoc)
     * @see org.radf.plat.util.dao.DAOSupport#getAllRecords(java.sql.Connection)
     */
    public Collection getAllRecords(Connection con) throws SQLException, NotFindException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sqlSelect = SELECT_ALL_SQL;
        ArrayList result = new ArrayList();
        try {
             stmt = con.prepareStatement(sqlSelect);
             rs = stmt.executeQuery();
             if (rs == null){
                 throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
             }
             while(rs.next()){
               SysFunction sysFunction = new SysFunction();
               sysFunction.setFunctionId (rs.getString("functionId"));
               sysFunction.setTitle(rs.getString("Title"));
               sysFunction.setFlag (rs.getString("flag"));
               sysFunction.setParentId (rs.getString("parentId"));
               sysFunction.setSignatureType(rs.getString("signatureType"));

               sysFunction.setType (rs.getString("type"));
               sysFunction.setTransFlag (rs.getString("transFlag"));
               sysFunction.setFunctionDesc (rs.getString("functionDesc"));
               sysFunction.setLocation(rs.getString("Location"));
               sysFunction.setLog(rs.getString("Log"));
               sysFunction.setOrderno(rs.getInt("Orderno"));
               sysFunction.setOwner(rs.getString("Owner"));
               sysFunction.setVersion(rs.getInt("version"));
               sysFunction.setImageID(rs.getInt("imageID"));
               sysFunction.setHelpID(rs.getInt("helpID"));
               result.add(sysFunction);
             }
        }
        finally {
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
        return result;
    }

    /* (non-Javadoc)
     * @see org.radf.plat.util.dao.DAOSupport#getVersionData(java.sql.Connection, int,Collection)
     */
    public int getVersionData(Connection conn, int nVersion,int count,Collection data) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int version = 0;
        try {
            stmt = versionPage(conn,stmt,SELECT_VER_SQL,nVersion,count);
            rs = stmt.executeQuery();
            while(rs.next()){
               SysFunction sysFunction = new SysFunction();
               sysFunction.setFunctionId (rs.getString("functionId"));
               sysFunction.setTitle(rs.getString("Title"));
               sysFunction.setFlag (rs.getString("flag"));
               sysFunction.setParentId (rs.getString("parentId"));
               sysFunction.setSignatureType(rs.getString("signatureType"));

               sysFunction.setType (rs.getString("type"));
               sysFunction.setTransFlag (rs.getString("transFlag"));
               sysFunction.setFunctionDesc (rs.getString("functionDesc"));
               sysFunction.setLocation(rs.getString("Location"));
               sysFunction.setLog(rs.getString("Log"));
               sysFunction.setOrderno(rs.getInt("Orderno"));
               sysFunction.setOwner(rs.getString("Owner"));
               sysFunction.setVersion(rs.getInt("version"));
               sysFunction.setImageID(rs.getInt("imageID"));
               sysFunction.setHelpID(rs.getInt("helpID"));
               sysFunction.setVersion(rs.getInt("VERSION"));
               data.add(sysFunction);
               version = rs.getInt("VERSION");
            }
        }
        finally {
            DBUtil.closeResStat(rs,stmt);
        }
        return version;
    }

    /**
      * 获取所有需要认证的Functionid和对应签名类型
      * @param con
      * @return HashMap 数据put入格式为(functionID,signatureType)
      * @exception java.sql.SQLException
      */
     public HashMap doFindEncrypt( Connection con)
     throws java.sql.SQLException,NotFindException{

         Statement pstmt = null;
         ResultSet rs = null;
         HashMap result = new HashMap();
         String sqlSelect = SELECT_ENCRYPT_SQL;
         try{
            pstmt = con.createStatement();
            rs = pstmt.executeQuery(sqlSelect);
            if (rs == null){
                throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
            }
            while(rs.next()){
                result.put(rs.getString("functionId"),rs.getString("signatureType"));
            }
          }finally{
              DBUtil.closeResStat(rs,pstmt);
         }
         return result;
     }


     /**
      * 通过菜单标题title部分内容，模糊查询对应的SysFunction集合
      * @param con
      * @param String title
      * @return ArrayList SysFunction List
      * @exception java.sql.SQLException
      */
     public ArrayList doFindByTitle(Connection con , String title)
     throws java.sql.SQLException,NotFindException{
         PreparedStatement stmt = null;
         ResultSet rs = null;
         String sqlSelect = SELECT_TITLE_SQL;
         ArrayList result = new ArrayList();
         try {
              stmt = con.prepareStatement(sqlSelect);
              stmt.setString(1,'%'+title+'%');
              rs = stmt.executeQuery();
              if (rs == null){
                  throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
              }
              while(rs.next()){
                SysFunction sysFunction = new SysFunction();
                sysFunction.setFunctionId (rs.getString("functionId"));
                sysFunction.setFlag (rs.getString("flag"));
                sysFunction.setType (rs.getString("type"));
                sysFunction.setTransFlag (rs.getString("transFlag"));
                sysFunction.setFunctionDesc (rs.getString("functionDesc"));
                sysFunction.setParentId (rs.getString("parentId"));
                sysFunction.setSignatureType(rs.getString("signatureType"));
                sysFunction.setLocation(rs.getString("Location"));
                sysFunction.setLog(rs.getString("Log"));
                sysFunction.setOrderno(rs.getInt("Orderno"));
                sysFunction.setOwner(rs.getString("Owner"));
                sysFunction.setTitle(rs.getString("Title"));
                sysFunction.setImageID(rs.getInt("imageID"));
                sysFunction.setHelpID(rs.getInt("helpID"));
                result.add(sysFunction);
              }
         }
         finally {
             if(rs != null)
                 rs.close();
             if(stmt != null)
                 stmt.close();
         }
         return result;
     }

     /**
      * 获取指定SysFunction的子SysFunction集合
      * @param con
      * @param SysFunction 
      * @return ArrayList SysFunction List
      * @@exception java.sql.SQLException,NotFindException
      */
     public ArrayList getChildFunctions(Connection con ,SysFunction fun)
     throws java.sql.SQLException,NotFindException{
         PreparedStatement stmt = null;
         ResultSet rs = null;
         String sqlSelect = SELECT_PARENT_SQL;
         ArrayList result = new ArrayList();
         try {
              stmt = con.prepareStatement(sqlSelect);
              stmt.setString(1,fun.getFunctionId());
              rs = stmt.executeQuery(sqlSelect);
              if (rs == null){
                  throw new NotFindException(GlobalErrorMsg.DAO_SQL_NOTFIND);
              }
              while(rs.next()){
                SysFunction sysFunction = new SysFunction();
                sysFunction.setFunctionId (rs.getString("functionId"));
                sysFunction.setFlag (rs.getString("flag"));
                sysFunction.setType (rs.getString("type"));
                sysFunction.setTransFlag (rs.getString("transFlag"));
                sysFunction.setFunctionDesc (rs.getString("functionDesc"));
                sysFunction.setParentId (rs.getString("parentId"));
                sysFunction.setSignatureType(rs.getString("signatureType"));
                sysFunction.setLocation(rs.getString("Location"));
                sysFunction.setLog(rs.getString("Log"));
                sysFunction.setOrderno(rs.getInt("Orderno"));
                sysFunction.setOwner(rs.getString("Owner"));
                sysFunction.setTitle(rs.getString("Title"));
                sysFunction.setImageID(rs.getInt("imageID"));
                sysFunction.setHelpID(rs.getInt("helpID"));
                result.add(sysFunction);
              }
         }
         finally {
             if(rs != null)
                 rs.close();
             if(stmt != null)
                 stmt.close();
         }
         return result;
     }
     /**
      * 判断指定SysFunction是否有子function
      * @param con
      * @param SysFunction
      * @return boolean
      * @exception java.sql.SQLException
      */
     public boolean hasChildFunctions(Connection con ,SysFunction fun)
     throws java.sql.SQLException{
         PreparedStatement stmt = null;
         ResultSet rs = null;
         boolean result = false;
         String sqlSelect = SELECT_PARENT_SQL;
         try {
             stmt = con.prepareStatement(sqlSelect);
             stmt.setString(1,fun.getFunctionId());
             rs = stmt.executeQuery(sqlSelect);
             if(rs.next()){
                result = true;
             }
         }
         finally {
             if(rs != null)
                 rs.close();
             if(stmt != null)
                 stmt.close();
         }
         return result;
     }


     /**
      * 获取指定SysFunction的父SysFunction
      * @param con
      * @param SysFunction
      * @return SysFunction
      * @exception java.sql.SQLException
      * @exception NotFindException
      */
     public SysFunction getParentFunction(Connection con , SysFunction fun)
     throws java.sql.SQLException,NotFindException{
        Statement stmt = null;
        ResultSet rs = null;

        SysFunction father = null;
        try {
             SysFunction thisFunction = (SysFunction)doFind(con,fun);
             if(thisFunction!=null){
                 father = (SysFunction)doFind(con,thisFunction);
             }
        }
        finally {
            if(rs != null)
                rs.close();
            if(stmt != null)
                stmt.close();
        }
         return father;
     }

     /**
      * 修改指定SysFunction为节点
      * @param con
      * @param SysFunction
      * @exception java.sql.SQLException
      */
     public void changeToNode(Connection con ,SysFunction fun)
     throws java.sql.SQLException{
         PreparedStatement stmt = null;
         try {
                String sqlUpdate = UPDATE_FLAG_SQL;
                stmt = con.prepareStatement(sqlUpdate);
                stmt.setString(1,Constants.NODE_FUNCTION);
                stmt.setString(2,fun.getFunctionId());
                stmt.executeUpdate(sqlUpdate);
         }
         finally {
             if(stmt != null)
                 stmt.close();
         }
         String sql1 = "update sysfunction set flag = '" + Constants.NODE_FUNCTION + "' "
             + "wherer functionId = '" + fun.getParentId() + "'";
         changeDAO.createChange(con,sql1);

     }
     /**
      * 修改指定functionid为叶子节点
      * @param con
      * @param SysFunction
      * @exception java.sql.SQLException
      */
     public void changeToLeaf(Connection con ,SysFunction fun)
     throws java.sql.SQLException{
         PreparedStatement stmt = null;
         try {
             String sqlUpdate = UPDATE_FLAG_SQL;
             stmt = con.prepareStatement(sqlUpdate);
             stmt.setString(1,Constants.LEAF_FUNCTION);
             stmt.setString(2,fun.getFunctionId());
             stmt.executeUpdate(sqlUpdate);
         }
         finally {
             if(stmt != null)
                 stmt.close();
         }
         String sql = "update sysfunction set flag = '" + Constants.LEAF_FUNCTION + "' "
                     + "where functionId = '" + fun.getFunctionId() + "'";
         changeDAO.createChange(con,sql);

     }
}



