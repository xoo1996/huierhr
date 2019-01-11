/**
* <p>����: </p>
* <p>˵��: </p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-5-1115:12:32</p>
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
     * ����SYSDEPT��¼�������ش���id�ı����SYSDEPT����
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
     * ��ѯ���е�SYSDEPT��¼
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
     * ��DeptID��ѯ��Ӧ��SYSDEPT��¼
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
     * �޸�SYSDEPT��¼
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
     * ���ݵ�λ���ɾ��SYSDEPT��¼
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
