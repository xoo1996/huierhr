/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-1115:12:19</p>
*
* @author Administrator
* @version 1.0
*/
package org.radf.manage.role.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.manage.role.dao.RoleDAO;
import org.radf.manage.role.entity.SysRole;
import org.radf.manage.role.entity.SysUser;
import org.radf.plat.util.bpo.BPOSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class SysRoleBPO extends BPOSupport{
    private String className = this.getClass().getName();
    private RoleDAO dao = new RoleDAO();
    
    public SysRoleBPO(){
        super();
    }
    /**
     * 保存SYSROLE记录，并返回带有id的保存后SYSROLE数据
     * 如果要保存的角色描述信息重复，则不保存直接返回null
     * @param Connection
     * @param role
     * @return　SysRole
     */
    public SysRole createRole(Connection con,SysRole role)
    throws AppException {
        try{
            if(dao.doFindByDesc(con,role)!=null){
                return null;
            }else{
                return (SysRole)dao.doCreate(con, role);
            }
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"createRole",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createRole",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
    }
    /**
     * 查询所有SYSROLE记录
     * @param con
     * @return Collection  SYSROLE List
     * @throws AppException
     */
    public Collection findAllRoles(Connection con)
    throws AppException {
        Collection list = null;
        try{
            list = dao.doFindAllRoles(con);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findAllRoles",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findAllRoles",GlobalErrorMsg.BPO_USER_FINDLOGINNAME,se);            
        }catch(Exception e){
            throw AppExceptionSupport(className,"findAllRoles",e);
        }
        return list;
    }
    
    public Collection findAllRoles(Connection con,SysUser user)
    throws AppException {
        Collection list = null;
        try{
            if(user==null||user.getUserID()==null){
                throw new AppException(GlobalErrorMsg.IMP_USER_NOEXIST);
            }
            list = dao.doFindAllLeafRoles(con,user);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findAllRoles",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findAllRoles",GlobalErrorMsg.BPO_USER_FINDLOGINNAME,se);            
        }
        return list;
    }

    
    /**
     * 用主键ROLEID查询对应的SYSROLE记录
     * @param Connection
     * @param Obj               SysRole    
     * @return SysRole
     * @throws java.sql.SQLException
     * @throws NotFindException
     */
    public SysRole findRoleByPK(Connection con,SysRole role)
    throws AppException {
        try{
            return (SysRole)dao.doFind(con, role);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findRoleByPK",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findRoleByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
    }
    
    /**
     * 根据ROLEID修改SYSROLE记录
     * @param Connection
     * @param Obj               SysRole   
     * @throws java.sql.SQLException
     */
    public void modifyRole(Connection con,SysRole role)
    throws AppException {
        try{
            dao.doUpdate(con, role);   
        }catch(SQLException se){
            throw AppExceptionSupport(className,"modifyRole",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
    }
    
    /**
     * 根据RoleID删除SYSROLE记录
     * @param Connection
     * @param Obj               SysRole   
     * @throws java.sql.SQLException
     */
    public void removeRole(Connection con,SysRole role)
    throws AppException {
        try{
            dao.doDelete(con, role);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"removeRole",GlobalErrorMsg.BPO_REMOVEKEY_ERROR,se);            
        }
    }
}
