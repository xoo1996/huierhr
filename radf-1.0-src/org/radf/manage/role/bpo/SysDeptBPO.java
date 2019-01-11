/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-1115:12:32</p>
*
* @author Administrator
* @version 1.0
*/
package org.radf.manage.role.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.manage.role.dao.DeptDAO;
import org.radf.manage.role.entity.SysDept;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class SysDeptBPO  extends org.radf.plat.util.bpo.BPOSupport{
    private DeptDAO dao = new DeptDAO();
    private String className = this.getClass().getName();
   public SysDeptBPO(){
        super();
    }
    
    /**
     * 保存SYSDEPT记录，并返回带有id的保存后SYSDEPT数据
     * @param Connection
     * @param dept
     * @return SysDept
     * @throws AppExceptionSupport
     */
    public SysDept createDept(Connection con,SysDept dept)
    throws AppException {
        SysDept  org = null;
        try{
            org = (SysDept)dao.doCreate(con, dept);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createDept",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
        return org;
     }
    
    /**
     * 查询所有的SYSDEPT记录
     * @param Connection
     * @param dept
     * @return Collection       SysDeptList
     * @throws AppExceptionSupport
    */
    public Collection FindAllDepts(Connection con)
    throws AppException {
        try{
            return dao.doFindAllDepts(con);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"FindAllDepts",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"FindAllDepts",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
    }
    
    /**
     * 用DeptID查询对应的SYSDEPT记录
     * @param Connection
     * @param Obj               SysDept   
     * @return SysDept
     * @throws AppExceptionSupport
      */
    public SysDept findDeptByDeptID(Connection con,SysDept dept)
    throws AppException {
        try{
            return (SysDept) dao.doFindByDeptID(con, dept);     
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findDeptByDeptID",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findDeptByDeptID",GlobalErrorMsg.BPO_DEPT_FINDDEPTID,se);            
        }
    }
    /**
     * 修改SYSDEPT记录
     * @param Connection
     * @param Obj               SysDept   
     * @throws AppException
     */
    public void modifyDept(Connection con,SysDept dept)
    throws AppException {
        try{
            dao.doUpdate(con, dept);     
        }catch(SQLException se){
            throw AppExceptionSupport(className,"modifyDept",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
    }

    /**
     * 根据单位编号删除SYSDEPT记录
     * @param Connection
     * @param Obj               SysDept   
     * @throws java.sql.SQLException
     */
    public void removeDept(Connection con,SysDept dept)
    throws AppException {
        try{
            dao.doDeleteByDeptID(con, dept);     
        }catch(SQLException se){
            throw AppExceptionSupport(className,"removeDept",GlobalErrorMsg.BPO_REMOVE_ERROR,se);            
        }
    }
}
