/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-1912:07:24</p>
*
* @author Administrator
* @version 1.0
*/
package org.radf.manage.role.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.manage.role.dao.ActDAO;
import org.radf.manage.role.entity.SysAct;
import org.radf.manage.role.entity.SysRole;
import org.radf.manage.role.entity.SysUser;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;

/**
* 管理ACT的BPO
* @author zqb
* @version 1.0
 */
public class SysActBPO extends org.radf.plat.util.bpo.BPOSupport {
    private String className = this.getClass().getName();
    private ActDAO dao = new ActDAO();
    public SysActBPO() {
    }

    /**
     * 保存SysAct记录，并返回带有id的保存后SysAct数据
     * @param con
     * @param SysAct act
     * @return SysAct act
     * @throws AppException
     */
    public SysAct createAct(Connection con,SysAct obj)
    throws AppException {
        SysAct act = null;
        try{
            act = (SysAct)dao.doCreate(con, obj);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createAct",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
        return act;
    }
    
    /**
     * 用主键ActID查询对应的SysAct记录
     * @param Connection
     * @param Obj               SysAct    
     * @return SysAct
     * @throws AppException
     */
    public SysAct findActByPK(Connection con,SysAct obj)
    throws AppException {
        SysAct act = null;
        try{
            act = (SysAct)dao.doFind(con, obj);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findAclByPK",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findAclByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return act;
    }

    /**
     *查找roleid对应的Act记录集
     * @param Connection
     * @param Object        SysRole
     * @return Collection   SysActList
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
     *查找userid对应的Act记录集
     * @param Connection
     * @param Object        SysUser
     * @return Collection   SysActList
     */
    public Collection findByUser(Connection con,SysUser obj)
    throws AppException {
        Collection list = null;
        try{
            list = dao.doFindByUser(con, obj);
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"findByUser",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findByUser",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
        return list;
    }
    
    /**
     * 根据actID修改SysAct记录
     * @param Connection
     * @param Obj               SysAct   
     * @throws AppException
     */
    public void modifyAct(Connection con,SysAct act)
    throws AppException {
        try{
            dao.doUpdate(con, act);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"modifyAct",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
    }
    /**
     * 根据actID删除SysAct记录
     * @param Connection
     * @param Obj               SysAclt   
     * @throws AppException
     */
    public void removeAct(Connection con,SysAct act)
    throws AppException {
        try{
            dao.doDelete(con, act);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"removeAct",GlobalErrorMsg.BPO_REMOVEKEY_ERROR,se);            
        }
    }
}
