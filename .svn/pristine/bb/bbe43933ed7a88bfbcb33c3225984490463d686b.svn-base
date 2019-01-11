/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-1115:47:49</p>
*
* @author Administrator
* @version 1.0
*/
package org.radf.manage.role.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.manage.role.dao.UserDAO;
import org.radf.manage.role.entity.SysUser;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class SysUserBPO extends org.radf.plat.util.bpo.BPOSupport{
    private UserDAO dao = new UserDAO();
    private String className = this.getClass().getName();
    public SysUserBPO(){
        super();
    }

    /**
     * 保存SysUser记录，并返回带有id的保存后SysUser数据
     * @param con
     * @param SysUser user
     * @return SysUser user
     * @throws AppException
     */
    public SysUser createUser(Connection con,SysUser user)
    throws AppException {
        SysUser usr = null;
        try{
            usr = (SysUser)dao.doCreate(con, user);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createUser",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
        return usr;
    }
    /**
     * 用单位编号查询指定单位下所有SysUser
     * @param Connection
     * @param String deptid
     * @return SysUserList
     * @throws AppException
     */
    public Collection findAllMembers(Connection con,String deptID)
        throws AppException {
        Collection usrList = null;
        try{
            usrList = dao.doFindAllMembers(con, deptID);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"FindAllMembers",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"FindAllMembers",GlobalErrorMsg.BPO_USER_FINDALLMEMBERS,se);            
        }
        return usrList;
    }
    
    /**
     * 用主键UserID查询对应的SysUser记录
     * @param Connection
     * @param Obj               SysUser    
     * @return SysUser
     * @throws AppException
     */
    public SysUser findUserByPK(Connection con,SysUser user)
    throws AppException {
        SysUser usr = null;
        try{
            usr = (SysUser)dao.doFind(con, user);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findUserByPK",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findUserByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return usr;
    }
    
    /**
     * 根据用户名LoginName查询对应的SysUser记录
     * @param Connection
     * @param Obj               SysUser    
     * @return SysUser
     * @throws AppException
     */
    public SysUser findUserByLoginName(Connection con,SysUser user)
    throws AppException {
        SysUser usr = null;
        try{
            usr = (SysUser)dao.doFindByLoginName(con, user);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findUserByLoginName",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findUserByLoginName",GlobalErrorMsg.BPO_USER_FINDLOGINNAME,se);            
        }
        return usr;
    }
    /**
     * 查询所有SYSUSER记录
     * @param con
     * @return Collection UserList
     * @throws AppException
     */
    public Collection findAllUsers(Connection con)
    throws AppException {
        Collection list =null;
        try{
            list = dao.doFindAllUsers(con);
            
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findAllUsers",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findAllUsers",GlobalErrorMsg.BPO_USER_FINDLOGINNAME,se);            
        }
        return list;
    }
   
    /**
     * 根据USERID修改SysUser记录
     * @param Connection
     * @param Obj               SysUser   
     * @throws AppException
     */
    public void modifyUser(Connection con,SysUser user)
    throws AppException {
        try{
            dao.doUpdate(con, user);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"modifyUser",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
    }
    
 
    /**
     * 根据USERID修改SysUser中DeptId(机构编号)
     * @param Connection
     * @param Obj               SysUser   
     * @throws AppException
     */
    public void modifyUserByUserID(Connection con,SysUser  sysUser)
    throws AppException {
        try{
                    dao.doUpdateDeptIDByUserID(con,sysUser);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"modifyUser",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
    }
  
    /**
     * 根据UserID删除SysUser记录
     * @param Connection
     * @param Obj               SysUser   
     * @throws AppException
     */
    public void removeUser(Connection con,SysUser user)
    throws AppException {
        try{
            dao.doDelete(con, user);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"removeUser",GlobalErrorMsg.BPO_REMOVEKEY_ERROR,se);            
        }
    }
    
  
    /**
     * 校验原来用户和密码，相同时修改密码
     * 传入的SysUser中，passWD存放原来密码，新密码临时存放在deptID字段中
     * @param con
     * @param user
     * @throws AppException
     */
    public void changePWD(Connection con,SysUser user)
    throws AppException {
        try{
            SysUser usr = (SysUser) dao.doFindByLoginName(con, user);
            //比较密码是否相同
            if (usr.getPassWD().equals(user.getPassWD())) {
                //将新密码放回passWD字段
                user.setPassWD(user.getDeptID());
                dao.doUpdatePWD(con, user);
            } else {
                throw new NotFindException(GlobalErrorMsg.BPO_USER_PWDERROR);
            }
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"changePWD",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"changePWD",GlobalErrorMsg.BPO_USER_PWDERROR,se);            
        }catch(Exception ex){
            throw AppExceptionSupport(className,"changePWD",ex);
        }
    }
    
    /**
     * 根据用户id，重置密码
     * @param con
     * @param user
     * @throws AppException
     */
    public void resetPWD(Connection con,SysUser user)
    throws AppException {
        try{
            dao.doResetPWD(con, user);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"resetPWD",GlobalErrorMsg.BPO_USER_RESETPWD,se);            
        }
    } 
}
