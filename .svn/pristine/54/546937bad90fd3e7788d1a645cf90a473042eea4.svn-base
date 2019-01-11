/**
* <p>标题: 用户、角色、单位管理</p>
* <p>说明: 管理SYSUSER、SYSROLE、SYSDEPT的BPO</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-1114:57:02</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.role.imp;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.radf.manage.role.bpo.SysDeptBPO;
import org.radf.manage.role.bpo.SysRoleBPO;
import org.radf.manage.role.bpo.SysUserBPO;
import org.radf.manage.role.dao.AclDAO;
import org.radf.manage.role.dao.ActDAO;
import org.radf.manage.role.entity.SysAct;
import org.radf.manage.role.entity.SysDept;
import org.radf.manage.role.entity.SysRole;
import org.radf.manage.role.entity.SysUser;
import org.radf.manage.role.facade.RoleFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.manage.version.bpo.SysVersionBPO;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.util.global.SessionNames;

public class RoleIMP extends org.radf.plat.util.imp.IMPSupport implements RoleFacade{
    private static final String className = RoleIMP.class.getName();
    public RoleIMP(){
        super(className);
    }
    private SysRoleBPO roleBPO = new SysRoleBPO();
    private SysUserBPO userBPO = new SysUserBPO();
    private SysDeptBPO deptBPO = new SysDeptBPO();
    
//    private static RoleIMP  imp = null; 
//    public static RoleIMP getInstance(){
//        if( imp == null){
//            imp = new RoleIMP();
//        }       
//        return imp;
//    }

    
    /**************************************关于SysUser的方法********************************/
    /**
     * 生成一条SYSUSER记录
     */
    public ResponseEnvelop createUser(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        SysUser sysUser = (SysUser) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            response.setBody(userBPO.createUser(con, sysUser).getUserID());

            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"createUser",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
             DBUtil.rollback(con);
             DBUtil.closeConnection(con);
        }
        return response;
    }
 
    /**
     * 根据USERID修改SYSUSER记录
     */
    public ResponseEnvelop modifyUser(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        SysUser sysUser = (SysUser) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            userBPO.modifyUser(con, sysUser);

            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"modifyUser",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
    /**
     * 根据用户id(UserID)删除SYSUSER记录
     */
    public ResponseEnvelop removeUser(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        String PK = (String) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            userBPO.removeUser(con, new SysUser(PK));
            
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
             response.setHead(ExceptionSupport(className,"removeUser",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }

    /**
     *查找所有的SYSUSER记录
     */
    public ResponseEnvelop findAllUsers(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            response.setBody(userBPO.findAllUsers(con));
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findAllUsers",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
             DBUtil.closeConnection(con);
        }
        return response;
    }

    /**
     * 用主键UserID查询对应的SYSUSER记录
     */
    public ResponseEnvelop findUserByPK(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        SysUser sysUser = (SysUser) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            sysUser = userBPO.findUserByPK(con, sysUser);
            response.setBody(sysUser);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findUserByPK",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }

    /**
     * 用DEPT_ID查询所有满足条件的SYSUSER记录
     */
    public ResponseEnvelop findAllMembers(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        String PK = (String) request.getBody();
        
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            response.setBody(userBPO.findAllMembers(con,PK));
        } catch (AppException ae) {
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"FindAllMembers",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
    /**
     * 修改密码
     * 传入的SysUser中，passWD存放原来密码，新密码临时存放在deptID字段中
     */
    public ResponseEnvelop changePWD(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        SysUser user = (SysUser) request.getBody();
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            userBPO.changePWD(con,user);
            
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"changePWD",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**
     * 根据用户id，对用户密码重置，设置为初始化值
     * @param request
     * @return
     */
    public ResponseEnvelop resetPWD(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        SysUser pk = (SysUser) request.getBody();

        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            userBPO.resetPWD(con, pk);
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"resetPWD",GlobalErrorCode.EXCEPTIONCODE,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }

    /**************************************关于SysRole的方法********************************/

    /**
     * 生成一条SYSROLE记录
     */
    public ResponseEnvelop createRole(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        SysRole map = (SysRole) request.getBody();
        
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            SysRole role = roleBPO.createRole(con,map);
            
            con.commit();
           if(role==null){
                throw new Exception(GlobalErrorMsg.IMP_ROLE_EXIST);
            }else{
                response.setBody(role.getRoleID());
            }
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"createRole",ManageErrorCode.SQLERROR, ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
    /**
     * 根据RoleID，修改SYSROLE记录
     */
    public ResponseEnvelop modifyRole(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        SysRole sysRole = (SysRole) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            roleBPO.modifyRole(con, sysRole);

            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
         } catch (Exception ex) {
             response.setHead(ExceptionSupport(className,"modifyRole",ManageErrorCode.SQLERROR, ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
    /**
     * 根据RoleID，删除SYSROLE记录
     */
    public ResponseEnvelop removeRole(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        String PK = (String) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            roleBPO.removeRole(con, new SysRole(PK));
            
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
         } catch (Exception ex) {
             response.setHead(ExceptionSupport(className,"removeRole",ManageErrorCode.SQLERROR, ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
 
    /**
     * 用主键RoleID查询对应的SYSROLE记录
     */
    public ResponseEnvelop findRoleByPK(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        SysRole sysRole = (SysRole) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            sysRole = roleBPO.findRoleByPK(con, sysRole);
            response.setBody(sysRole);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
         } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findRoleByPK",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
    /**
     * 查找所有SYSROLE记录
     */
    public ResponseEnvelop findAllRoles(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            response.setBody(roleBPO.findAllRoles(con));
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findAllRoles",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
             DBUtil.closeConnection(con);
        }
        return response;
    }

    
    /**************************************关于SysDept的方法********************************/
    /**
     * 生成一条SYSDEPT记录
     */
    public ResponseEnvelop createDept(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        SysDept sysDept = (SysDept) request.getBody();
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            response.setBody(deptBPO.createDept(con,sysDept).getDeptID());
             
            con.commit(); 
            
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
         } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"createDept",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }

    /**
     * 根据机构编号（ORG)修改SYSDEPT记录
     */
    public ResponseEnvelop modifyDept(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        SysDept sysDept = (SysDept) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            deptBPO.modifyDept(con,sysDept);
            
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"modifyDept",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
 
    /**
     * 根据单位编号删除SYSDEPT记录
     */
    public ResponseEnvelop removeDept(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        String PK = (String) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            deptBPO.removeDept(con, new SysDept(PK));
            
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"removeDept",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }

        return response;
    }
 
    /**
     * 查询所有的SYSDEPT记录
     */
    public ResponseEnvelop findAllDepts(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            response.setBody(deptBPO.FindAllDepts(con));
           
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findAllDepts",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    

    /**
     * 用单位编号查询对应的SYSDEPT记录
     */
    public ResponseEnvelop findDeptByPK(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        String deptID = (String) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            SysDept sysDept = (SysDept)deptBPO.findDeptByDeptID(con, new SysDept(deptID));
            response.setBody(sysDept);
            
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findDeptByPK",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**************************************系统交易********************************/
    /**
     * 用户登录处理交易，交易ID=F00.00.00.00
     * 此方法为系统级交易，不能被外部定义的交易访问
     * @param user
     * @return Collection   返回对象列表中第一个是SysUser，第二个是角色列表Collection
     * @throws AppException
     */
    public HashMap userLogin(SysUser user) throws  AppException{
        Connection con = null;
        HashMap map = null;
        try {
            SysUserBPO bpo = new SysUserBPO();
            String pwd = user.getPassWD();
            if (pwd == null)
                pwd = "";

            con = DBUtil.getConnection();
            //获取用户
            SysUser usr = (SysUser) bpo.findUserByLoginName(con, user);
            if(usr==null){
                throw new AppException(GlobalErrorMsg.IMP_USER_NOEXIST);
            }
            //校验密码
            if (!pwd.equals(usr.getPassWD())) {
                throw new AppException(1405,GlobalErrorMsg.IMP_ROLE_LOGIN);
            }
            //判断用户是否能访问当前应用
            String usr_org = null;
            if(usr.getType()==null){
                if(GlobalNames.SERVICE_ZONE.startsWith("01.01")){
                    throw new AppException(1405,"系统配置错误1，你没有权利访问此系统");
                }
            }else{
                if(usr.getType().equalsIgnoreCase("EC")){
                    //外部用户：九江取配置文件中"EC"项目
                    if(GlobalNames.USER_ORG==null){
                        if(GlobalNames.SERVICE_ZONE.startsWith("01.01")){
                            throw new AppException(1405,"系统配置错误1，你没有权利访问此系统");
                        }
                    }else{
                        if(!GlobalNames.USER_ORG.equalsIgnoreCase("EC")){
                            throw new AppException(1405,"你没有权利访问此系统1，请选择与你身份匹配的客户端登录");
                        }
                    }
                }else if(usr.getType().equalsIgnoreCase("SP")){
                    //超级用户
                }else{
                    //其他用户，认为都是内部用户
                    if(GlobalNames.USER_ORG==null||usr.getAab034()==null){
                        if(GlobalNames.SERVICE_ZONE.startsWith("01.01")){
                            throw new AppException(1405,"系统配置错误1，你没有权利访问此系统");
                        }
                    }else{
                        if(!GlobalNames.USER_ORG.equalsIgnoreCase(usr.getAab034())){
                            throw new AppException(1405,"你没有权利访问此系统2，请选择与你身份匹配的客户端登录");
                        }
                    }
                }
            }
                
            
            //返回的用户信息部分
            map = new HashMap();
            map.put("SysUser",usr);
            
            //获取用户角色列表
            ActDAO actDao = new ActDAO();
            AclDAO aclDao = new AclDAO();
            
            //获取act列表
            Iterator iter  = actDao.doFindByUser(con, usr).iterator();
            
            //根据act获取acl中functionID列表
            Collection acl = new Vector();
            while (iter.hasNext()) {
                SysAct act = (SysAct)iter.next();
                Collection list = aclDao.doFindFunctionIDByRole(con,new SysRole(act.getRoleID()));
                acl.addAll(list);
            }
            map.put(SessionNames.FUNCTION_LIST,acl);
            //获取数据版本列表
            SysVersionBPO verBPO = new SysVersionBPO();
            Collection ver = verBPO.findAllMembers(con);
            map.put(SessionNames.VERSION_LIST,ver);
        } catch (AppException ae){
            throw ae;
        } catch (Exception ex) {
            throw new AppException(GlobalErrorCode.EXCEPTIONCODE,ex.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
        return map;
    }
    
   
    /**
     * 根据前台传过来的"userID"(用户ID),"Org"(机构编号)来更新系统用户的单位编号
     * @param user
     * @return void
     * @throws AppException
     */
    public ResponseEnvelop updateUserInfo(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        Vector bjbm = (Vector) request.getBody();
        
        HashMap map =null;
        Connection con = null;
        SysUser sysUser =new SysUser();
        try {
            con = DBUtil.getConnection();
           
           if (bjbm!=null){
               for(int i=0; i<bjbm.size(); i++){
                   map =(HashMap)bjbm.get(i);
                   String loginId =(String)map.get("userID");//用户代码
                   String Org=(String)map.get("Org");//机构编号
                   sysUser.setUserID(loginId);
                   sysUser.setDeptID(Org);
                   userBPO.modifyUserByUserID(con,sysUser);
               }
           }
           con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findAllDepts",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }
    
   
}
