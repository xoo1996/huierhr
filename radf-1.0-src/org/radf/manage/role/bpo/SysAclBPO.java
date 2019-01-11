package org.radf.manage.role.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.manage.role.dao.AclDAO;
import org.radf.manage.role.entity.SysAcl;
import org.radf.manage.role.entity.SysRole;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;

/**
* 管理ACL的BPO
* @author zqb
* @version 1.0
 */
public class SysAclBPO extends org.radf.plat.util.bpo.BPOSupport {
    private String className = this.getClass().getName();
    private AclDAO dao = new AclDAO();
	public SysAclBPO() {
	}
    /**
     * 保存SysAct记录，并返回带有id的保存后SysAcl数据
     * @param con
     * @param SysAcl acl
     * @return SysAcl acl
     * @throws AppException
     */
    public SysAcl createAcl(Connection con,SysAcl obj)
    throws AppException {
        SysAcl acl = null;
        try{
            acl = (SysAcl)dao.doCreate(con, obj);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createAcl",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
        return acl;
    }
    
    /**
     * 用主键AclID查询对应的SysAcl记录
     * @param Connection
     * @param Obj               SysAcl    
     * @return SysAcl
     * @throws AppException
     */
    public SysAcl findAclByPK(Connection con,SysAcl obj)
    throws AppException {
        SysAcl acl = null;
        try{
            acl = (SysAcl)dao.doFind(con, obj);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findAclByPK",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findAclByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return acl;
    }

    /**
     *查找roleid对应的Acl记录集
     * @param Connection
     * @param Object        SysRole
     * @return Collection   SysAclList
     */
    public Collection findByRole(Connection con,SysRole obj)
    throws AppException {
        Collection list = null;
        try{
            list = dao.doFindByRole(con, obj);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findByRole",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findByRole",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
        return list;
    }
    
    /**
     * 查找roleID对应的FunctionID记录集
     * @param con
     * @param Object SysRole
     * @return Collection  String格式functionIdList
    */
    public Collection findFunctionIDByRole(Connection con,SysRole obj)
    throws AppException {
        Collection list = null;
        try{
            list = dao.doFindFunctionIDByRole(con, obj);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findFunctionIDByRole",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findFunctionIDByRole",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
        return list;
    }
    
    /**
     * 根据aclID修改SysAcl记录
     * @param Connection
     * @param Obj               SysAcl   
     * @throws AppException
     */
    public void modifyAcl(Connection con,SysAcl acl)
    throws AppException {
        try{
            dao.doUpdate(con, acl);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"modifyAcl",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
    }
    
    /**
     * 根据aclID删除SysAcl记录
     * @param Connection
     * @param Obj               SysAcl   
     * @throws AppException
     */
    public void removeAcl(Connection con,SysAcl acl)
    throws AppException {
        try{
            dao.doDelete(con, acl);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"removeAcl",GlobalErrorMsg.BPO_REMOVEKEY_ERROR,se);            
        }
    }
    
    
}
