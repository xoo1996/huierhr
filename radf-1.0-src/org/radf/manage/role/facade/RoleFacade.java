/**
 * <p>标题: </p>
 * <p>说明: </p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-5-1610:31:49</p>
 *
 * @author Administrator
 * @version 1.0
 */
package org.radf.manage.role.facade;

import java.util.HashMap;

import org.radf.manage.role.entity.SysUser;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;

public interface RoleFacade{
    /**************************************系统方法********************************/
    public HashMap userLogin(SysUser user) throws  AppException;

    
    /**************************************关于操作员SysUser的方法********************************/
    /**
     * 生成一条SYSUSER记录
    * @param request
     */
    public ResponseEnvelop createUser(RequestEnvelop request);
    
    /**
     * 根据USERID修改SYSUSER记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop modifyUser(RequestEnvelop request);
    /**
     * 根据UserID删除SYSUSER记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop removeUser(RequestEnvelop request);
    
    /**
     *查找所有的SYSUSER记录
     */
    public ResponseEnvelop findAllUsers(RequestEnvelop request);

    /**
     * 用单位编号DEPT_ID查询所有满足条件的SYSUSER记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findAllMembers(RequestEnvelop request);

    /**
     * 用主键UserID查询对应的SYSUSER记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findUserByPK(RequestEnvelop request);

    /**
     * 根据用户登录名修改用户密码
     * 传入的SysUser中，passWD存放原来密码，新密码临时存放在deptID字段中
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop changePWD(RequestEnvelop request);

    /**
     * 根据用户id，对用户密码重置，设置为初始化值
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop resetPWD(RequestEnvelop request);

    /**************************************关于系统角色SysRole的方法********************************/
    /**
     * 生成一条SYSROLE记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop createRole(RequestEnvelop request);

    /**
     * 根据RoleID，修改SYSROLE记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop modifyRole(RequestEnvelop request);

    /**
     * 根据RoleID，删除SYSROLE记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop removeRole(RequestEnvelop request);

        
    /**
     * 查找所有SYSROLE记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findAllRoles(RequestEnvelop request);

   /**
     * 用主键RoleID查询对应的SYSROLE记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findRoleByPK(RequestEnvelop request);

    /**************************************关于组织机构SysDept的方法********************************/
    /**
     * 生成一条SYSDEPT记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop createDept(RequestEnvelop request);

    /**
     * 根据机构编号（ORG)修改SYSDEPT记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop modifyDept(RequestEnvelop request);

    /**
     * 根据机构编号（ORG)删除SYSDEPT记录
     * @param request
     * @return ResponseEnvelop
     */ 
    public ResponseEnvelop removeDept(RequestEnvelop request);

    /**
     * 查询所有的SYSDEPT记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findAllDepts(RequestEnvelop request);

    /**
     * 用单位编号查询对应的SYSDEPT记录
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findDeptByPK(RequestEnvelop request);
    /**
     * 根据前台传过来的"userID"(用户登陆名),"Org"(机构编号)来更新系统用户信息的单位编号(或称机构编号)
     * @param user
     * @return void
     * @throws AppException
     */
    public ResponseEnvelop updateUserInfo(RequestEnvelop request); 
}
