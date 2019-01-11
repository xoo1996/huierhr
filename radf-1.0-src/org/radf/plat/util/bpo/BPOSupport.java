package org.radf.plat.util.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.plat.util.dao.DAOSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ExceptionUtil;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalErrorMsg;

/**
* <p>标题：所有BPO的基础类</p>
* <p>说明：</p>
* <p>1. 内部包含基本的BPO操作BPO层所有的异常处理，抛出的异常信息格式统一为"详细信息|概要信息|":</p>
* <p>   (1)由dao层抛出的异常：AppExceptionSupport(String className,String method,String msg,SQLException se)</p>
* <p>   (2)由dao层抛出的异常：AppExceptionSupport(String className,String method,NotFindException nfe)</p>
* <p>   (3)由BPO层意外执行抛出的异常：AppExceptionSupport(String className,String method,Exception ex)</p>
* <p>   (4)由BPO层程序控制抛出的异常：AppExceptionSupport(String className,String method,String msg)</p>
* <p>2.内部包含基本的业务处理方法：</p>
* <p>   (1)增加        create,store       涉及辅助方法：createFirst,createLatter</p>
* <p>   (2)修改        modify             涉及辅助方法：modifyFirst,modifyLatter</p>
* <p>   (3)删除        remove             涉及辅助方法：removeFirst,removeLatter</p>
* <p>   (4)主键查询     findKEYMembers</p>
* <p>   (5)全部查询     findAllMembers</p>
* <p>   (6)条件查询     findBySQL           涉及辅助方法：toSQL</p>
* <p>   (7)取版本数据    getVersionData</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-10-11 15:02:23</p>
* @author zqb
* @version 1.0
 */

public class BPOSupport implements BPO {
    private DAOSupport dao = null;
    private String className = null;
    public BPOSupport(){
        if(dao==null){
            this.dao = new DAOSupport();
            this.className = "BPOSupport";
        }
    }
    /**
     * 可以根据传输的dao自动执行对应的DAO处理方法：
     * doCreate,doStore,doDelete,doUpdate,doFind,getAllRecords
     * @param className
     * @param dao
     */
    public BPOSupport(String className,DAOSupport dao){
        if(dao==null){
            this.dao = new DAOSupport();
            this.className = "BPOSupport";
        }else{
            this.dao = dao;
            this.className = className;
        }
    }
    /**
     * 保存记录，并返回保存后数据的统一方法
     * @param Connection
     * @param Object
     * @return Object
     * @throws AppException
     */
    public Object create(Connection con,Object obj) throws AppException {
        if(className==null||dao==null)
            throw AppExceptionSupport(className,"create",GlobalErrorMsg.BPO_SUPER_ERROR);
        try{
            return dao.doCreate(con, obj);
        }catch(SQLException se){            
            throw AppExceptionSupport(className,"create",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }catch(Exception e){
            throw AppExceptionSupport(className,"create",e);            
        }
    }
    /**
     * 无返回保存记录的统一方法
     * @param Connection
     * @param Object
     * @throws AppException
     */
    public void store(Connection con,Object obj) throws AppException {
        if(className==null||dao==null)
            throw AppExceptionSupport(className,"store",GlobalErrorMsg.BPO_SUPER_ERROR);
        try{
            dao.doStore(con, obj);
        }catch(SQLException se){            
            throw AppExceptionSupport(className,"store",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }catch(Exception e){
            throw AppExceptionSupport(className,"store",e);            
        }
    }

    /**
     * 根据主键删除记录的统一方法
     * @param Connection
     * @param Object
     * @throws AppException
     */
    public void remove(Connection con, Object dto) throws AppException {
        if(className==null||dao==null)
            throw AppExceptionSupport(className,"remove",GlobalErrorMsg.BPO_SUPER_ERROR);
      try {
            dao.doDelete(con, dto);
        } catch (SQLException se) {
            throw AppExceptionSupport(className,"remove",GlobalErrorMsg.BPO_REMOVEKEY_ERROR, se);
        }catch(Exception e){
            throw AppExceptionSupport(className,"remove",e);            
        }
    }
    
    /**
     * 根据条件修改数据
     * @param Connection
     * @param Object
     * @throws AppException
     */
    public void modify(Connection con, Object dto) throws AppException {
        if(className==null||dao==null)
            throw AppExceptionSupport(className,"modify",GlobalErrorMsg.BPO_SUPER_ERROR);
        try {
            dao.doUpdate(con, dto);
        } catch (SQLException se) {
            throw AppExceptionSupport(className,"modify",GlobalErrorMsg.BPO_UPDATE_ERROR, se);
        }catch(Exception e){
            throw AppExceptionSupport(className,"modify",e);            
        }
    }
    
    /**
     * 根据可以唯一确定一条记录的主健或其他条件查询数据。
     * 输入的对象可以是sql语句或一个实体类，
     * 如果输入的对象是实体类，则SQL配置文件中必须有对应的sql查询语句。
     * 返回结果可能是一条记录也可能是多条记录，由具体实现DAO类或者sql语句判断。
     * @param Connection
     * @param Object
     * @return Object
     * @throws AppException
     */
    public Object findKEYMembers(Connection con, Object obj)throws AppException {
        try {
            return dao.doFind(con, obj);
        } catch (SQLException se) {
            throw AppExceptionSupport(className, "FindKEYMembers",
                    GlobalErrorMsg.BPO_FINDKEY_ERROR, se);
        } catch (Exception e) {
            throw AppExceptionSupport(className, "findKEYMembers", e);
        }
    }
  
    /**
     * 查询所有数据的统一方法
     * @param Connection
     * @return Collection
     * @throws AppException
     */
    public Collection findAllMembers(Connection con) throws AppException {
        if(className==null||dao==null)
            throw AppExceptionSupport(className,"findAllMembers",GlobalErrorMsg.BPO_SUPER_ERROR);
        try {
            return dao.getAllRecords(con);
        } catch (NotFindException nfe) {
            throw AppExceptionSupport(className,"FindAllMembers",nfe);
        } catch (SQLException se) {
            throw AppExceptionSupport(className,"FindAllMembers",GlobalErrorMsg.BPO_FIND_ERROR, se);
        } catch(Exception e){
            throw AppExceptionSupport(className,"findAllMembers",e);            
        }
    }
    /**
     * 
     * 自定义条件查询（外部传递WHERE开始的条件sql语句)
     * 注：如传递的sql语句完整的，则dao中doSelectSQL要return "";
     * @param con
     * @param sql
     * @param count
     * @param offset
     * @return
     * @throws AppException
     */
    public Collection findBySQL(Connection con, String sql,int count,int offset) throws AppException {
        if(className==null||dao==null)
            throw AppExceptionSupport(className,"findBySQL",GlobalErrorMsg.BPO_SUPER_ERROR);
        try {
            return dao.doFindBySQL(con, sql,count,offset);
        } catch (SQLException se) {
            throw AppExceptionSupport(className,"findBySQL",GlobalErrorMsg.BPO_FIND_ERROR, se);
        }catch(Exception e){
            throw AppExceptionSupport(className,"findBySQL",e);            
        }        
    }
//    /**
//     * 根据对象以及配置文件中内容生成SQL语句
//     * @param obj
//     * @return
//     * @throws AppException
//     */
//    public String buildSQLByObject(Object obj)throws AppException {
//        if(className==null||dao==null)
//            throw AppExceptionSupport(className,"buildSQLByObject",GlobalErrorMsg.BPO_SUPER_ERROR);
//        try{
//            return dao.buildSQLByObject(obj);
//        } catch (SQLException se) {
//            throw AppExceptionSupport(className,"buildSQLByObject",GlobalErrorMsg.BPO_SUPER_ERROR, se);
//        }catch(Exception e){
//            throw AppExceptionSupport(className,"buildSQLByObject",e);            
//        }
//    }
    
    
//    /**
//     * 根据传递的对象获取数据库中满足条件的纪录条数。支持两种方式：
//     * (1)传递的对象是一个SQL语句
//     * (2)传递的对象是一个实体类，并可以通过fileKey获取配置文件中的sql语句
//     * @param con
//     * @param obj
//     * @return
//     * @throws AppException
//     */
//    public int getRowCount(Connection con,Object obj)throws AppException {
//        try{
//            return DBUtil.getRowCount(con,obj);
//        }catch(SQLException se){            
//            throw AppExceptionSupport(className,"getRowCount",GlobalErrorMsg.BPO_SUPER_ERROR,se);            
//        }catch(Exception e){
//            throw AppExceptionSupport(className,"getRowCount",e);            
//        }
//    }
    /**
     * 获取版本更新数据
     * @param con
     * @param nVersion
     * @return Collection
     * @throws AppException
     */
    public int getVersionData(Connection con,int nVersion,int count,Collection data)throws AppException {
        if(className==null||dao==null)
            throw AppExceptionSupport(className,"getVersionData",GlobalErrorMsg.BPO_SUPER_ERROR);
        try {
            return dao.getVersionData(con,nVersion,count,data);
        } catch (SQLException se) {
            throw AppExceptionSupport(className,"getVersionData",GlobalErrorMsg.BPO_FIND_ERROR, se);
        }catch(Exception e){
            throw AppExceptionSupport(className,"getVersionData",e);            
        }
    }
    /**
     * 将指定的对象转化成sql语句的where条件部分
     * 此方法需要被具体业务BPO重载实现
     * @param obj
     * @return
     * @exception AppException
     */
    public String toSQL(Object obj) throws AppException{
        if(obj instanceof String){
            return (String)obj;
        }else{
            return "";
        }
        //throw new AppException("基类toSQL方法没有被具体业务BPO层实现，请与开发商联系");
    }

    /**
     * 增加记录前所需要进行的处理：
     * （1）数据校验，如果校验失败则抛出AppException
     * （2）其它数据操作
     * 此方法需要被继承类重载实现校验功能，不重载默认无需校验
     * 重载时如果不通过处理，需要通过AppException传递不通过校验的原因
     * @param con
     * @param obj
     * @exception AppException
     */
    public void createFirst(Connection con,Object obj)throws AppException{
    }
    /**
     * 增加记录的后续处理。
     * 此方法需要被继承类重载实现校验功能，不重载默认无后续处理
     * @param con
     * @param obj
     * @exception AppException
     */
    public void createLatter(Connection con,Object obj)throws AppException{
    }
    /**
     * 修改记录前所需要进行的处理
     * （1）数据校验，如果校验失败则抛出AppException
     * （2）其它数据操作
     * 此方法需要被继承类重载实现校验功能，不重载默认无需校验
     * 重载时如果不通过校验，需要通过AppException传递不通过校验的原因
     * @param con
     * @param obj
     * @exception AppException
     */
    public void modifyFirst(Connection con,Object obj)throws AppException{
    }
    /**
     * 修改记录的后续处理。
     * 此方法需要被继承类重载实现校验功能，不重载默认无后续处理
     * @param con
     * @param obj
     * @exception AppException
     */
    public void modifyLatter(Connection con,Object obj)throws AppException{
    }
    /**
     * 删除记录前所需要进行的处理
     * （1）数据校验，如果校验失败则抛出AppException
     * （2）其它数据操作
     * 此方法需要被继承类重载实现校验功能，不重载默认无需校验
     * 重载时如果不通过校验，需要通过AppException传递不通过校验的原因
     * @param con
     * @param obj
     * @exception AppException
     */
    public void removeFirst(Connection con,Object obj)throws AppException{
    }
    /**
     * 删除记录的后续处理。
     * 此方法需要被继承类重载实现校验功能，不重载默认无后续处理
     * @param con
     * @param obj
     * @exception AppException
     */
    public void removeLatter(Connection con,Object obj)throws AppException{
    }
//    /**
//     * 分页查询，根据偏移量进行
//     * 自定义条件查询（外部传递WHERE开始的条件sql语句)
//     * 注：如传递的sql语句完整的，则dao中doSelectSQL要return "";
//     * @param con
//     * @param sql
//     * @param count
//     * @param offset
//     * @return
//     * @throws AppException
//     */
//    public Collection pageBySQL(Connection con, String sql,int count,int page) throws AppException {
//        if(className==null||dao==null)
//            throw AppExceptionSupport(className,"pageBySQL",GlobalErrorMsg.BPO_SUPER_ERROR);
//        return findBySQL(con,sql,count,(page-1)*count + 1);
//    }

    /**
     * BPO层异常处理-接收的DAO层SQLException异常
     * @param className         //出错的类
     * @param method            //出错的方法
     * @param msg               //错误概要信息
     * @param SQLException
     * @return AppException
     */
    public AppException AppExceptionSupport(String className,String method,String msg,SQLException se){
        se = ExceptionUtil.OraBulidMsg(se);
        //返回客户端的概要错误信息
        String SmaCusString = ExceptionUtil.buildCusMsg(msg,se.getMessage());
        //错误详情(总控上下文信息)
        String ZKContextString = formatContextMsg(className,method,"SQLException");
        //生成客户端的详细错误信息
        String cusString = ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        AppException ae = new AppException(GlobalErrorCode.DATABASSQLEEXCEPTIONCODE,cusString,se); //SQLException的ErrorCode=0 
        return ae;
    }
    /**
     * BPO层异常处理-接收的DAO层NotFindException异常
     * @param className         //出错的类
     * @param method            //出错的方法
     * @param NotFindException
     * @return AppException
     */
    public AppException AppExceptionSupport(String className,String method,NotFindException nfe){
        //返回客户端的概要错误信息
        String SmaCusString = ExceptionUtil.buildCusMsg(nfe.getMessage(),GlobalErrorMsg.DAO_SQL_NOTFIND);
        //错误详情(总控上下文信息)
        String ZKContextString = formatContextMsg(className,method,"NotFindException");
        //生成客户端AppException错误信息
        String cusString = ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        return new AppException(GlobalErrorCode.DATABASSQLEEXCEPTIONCODE,cusString,nfe);
    }
    /**
     * BPO层自身产生的异常，或调用DAO层传递参数对象不匹配的错误
     * @param className         //出错的类
     * @param method            //出错的方法
     * @param Exception
     * @return AppException
     */
    public AppException AppExceptionSupport(String className,String method,Exception ex){
        //返回客户端的概要错误信息
        String SmaCusString = ExceptionUtil.buildCusMsg(ex.getMessage(),GlobalErrorMsg.EXCEPTIONMESSAGE);
        //错误详情(总控上下文信息)
        String ZKContextString = formatContextMsg(className,method,"Exception");
        //生成客户端AppException错误信息
        String cusString = ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        return new AppException(GlobalErrorCode.BPOEXCEPTIONCODE,cusString,ex);
    }
    
    /**
     * BPO层由用户业务人为抛出的异常
     * @param className         //出错的类
     * @param method            //出错的方法
     * @param msg               //异常说明
     * @return AppException
     */
    public AppException AppExceptionSupport(String className,String method,String msg){
        //返回客户端的概要错误信息
        String SmaCusString = ExceptionUtil.buildCusMsg(msg,GlobalErrorMsg.EXCEPTIONMESSAGE);
        //错误详情(总控上下文信息)
        String ZKContextString = formatContextMsg(className,method,"AppException");
        //生成客户端AppException错误信息
        String cusString = ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        return new AppException(GlobalErrorCode.BPOUSEREXCEPTIONCODE,cusString);
    }
    /**
     * 生成BPO层总控上下文信息
     * @param className
     * @param method
     * @param type
     * @return
     */
    private String formatContextMsg(String className,String method,String type){
        StringBuffer sb = new StringBuffer(256);
        sb.append(className);
        sb.append("的非总控方法");
        sb.append(method);
        sb.append("调用DAO时发生");
        sb.append(type);
        sb.append("错误 ");
        return sb.toString();
    }
}