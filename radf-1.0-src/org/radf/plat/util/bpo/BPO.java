/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-9-113:18:52</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.util.bpo;

import java.sql.Connection;
import java.util.Collection;

import org.radf.plat.util.exception.AppException;

public interface BPO {
    
    //---------------------------增加数据的处理------------------------------
    /**
     * 保存记录，并返回保存后数据的统一方法
     * @param Connection
     * @param Object
     * @return Object
     * @throws AppException
     */
    public Object create(Connection con,Object obj) throws AppException;
    
    /**
     * 无返回保存记录的统一方法
     * @param Connection
     * @param Object
     * @throws AppException
     */
    public void store(Connection con,Object obj) throws AppException;
    
    //---------------------------删除数据的处理------------------------------
    /**
     * 根据主键删除记录的统一方法
     * @param Connection
     * @param Object
     * @throws AppException
     */
    public void remove(Connection con, Object dto) throws AppException;
    //---------------------------修改数据的处理------------------------------
    /**
     * 根据主键修改数据
     * @param Connection
     * @param Object
     * @throws AppException
     */
    public void modify(Connection con, Object dto) throws AppException ;
    //---------------------------查询数据的处理------------------------------
    /**
     * 查询主键查询数据的统一方法
     * @param Connection
     * @param Object
     * @return Object
     * @throws AppException
     */
    public Object findKEYMembers(Connection con, Object obj) throws AppException;
    /**
     * 查询所有数据的统一方法
     * @param Connection
     * @return Collection
     * @throws AppException
     */
    public Collection findAllMembers(Connection con) throws AppException;
    /**
     * 根据传递的对象获取数据库中满足条件的纪录条数。支持两种方式：
     * (1)传递的对象是一个SQL语句
     * (2)传递的对象是一个实体类，并可以通过fileKey获取配置文件中的sql语句
     * @param con
     * @param obj
     * @return
     * @throws AppException
     */
//    public int getRowCount(Connection con,Object obj)throws AppException;
    
    /**
     * 根据对象以及配置文件中内容生成SQL语句
     * @param obj
     * @return
     * @throws AppException
     */
//    public String buildSQLByObject(Object obj)throws AppException;
    /**
     * 获取版本更新数据。按照版本顺序。
     * @param con       数据库连接
     * @param nVersion  客户端版本号
     * @param count     获取的记录条数
     * @return
     * @throws AppException
     */
    public int getVersionData(Connection con,int nVersion,int count,Collection data)throws AppException;
    /**
     * 分页查询
     * 自定义条件查询（外部传递WHERE开始的条件sql语句)
     * @param con
     * @param sql
     * @param count
     * @param offset
     * @return
     * @throws AppException
     */
//    public Collection findBySQL(Connection con, String sql,int count,int offset) throws AppException;
}
