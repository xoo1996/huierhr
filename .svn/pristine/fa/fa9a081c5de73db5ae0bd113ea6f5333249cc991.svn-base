/**
* <p>标题: 关于交易定义的相关处理类</p>
* <p>说明: BPO</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-269:45:29</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.role.bpo;

import java.sql.Connection;
import java.sql.SQLException;

import org.radf.manage.role.dao.SysFunctionDAO;
import org.radf.manage.role.entity.SysFunction;
import org.radf.manage.util.Constants;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class SysFunctionBPO extends org.radf.plat.util.bpo.BPOSupport{
    private static final String className = SysFunctionBPO.class.getName();
    private static final SysFunctionDAO dao = new SysFunctionDAO();
    
    public SysFunctionBPO(){
        super(className,dao);
    }
    
    /**
     * 保存SysFunction记录，并返回带有id的保存后SysFunction数据
     * 如果传入的上级节点类型为“叶子(flag=1)”，则修改上级节点类型，如果传入为空或其他，则不修改
     * @param con
     * @param SysFunction function
     * @param 
     * @return SysFunction
     * @throws AppException
     */
    public SysFunction createFunction(Connection con,SysFunction function,String parentFlag)
    throws AppException {
        SysFunction fun = null;
        try{
            dao.doStore(con, function);
            //如果上级function原来是叶子，则改为节点
            if(parentFlag.equals(Constants.LEAF_FUNCTION)){
                dao.changeToNode(con,new SysFunction(function.getParentId()));
            }
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createFunction",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
        return fun;
    }
    /**
     * 修改sysfunction记录
     * @param con
     * @param SysFunction
     * @throws AppException
     */
    public void modifyFunction(Connection con,SysFunction fun)
    throws AppException {
        try{
            Object o = dao.doFind(con,fun);
            if(o == null)
                throw new SQLException(GlobalErrorMsg.BPO_FIND_ERROR);
            dao.doUpdate(con,fun);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"modifyFunction",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
    }
    /**
     * 删除指定交易定义
     * 如果此节点有子节点（即不是叶子节点），则不可删除
     * 如果此节点是其父节点唯一的叶子，则删除此节点时同时修改父节点类型为“叶子节点”
     * @param con
     * @param SysFunction
     * @throws AppException
     */
    public void removeFunction(Connection con,SysFunction fun)
    throws AppException {
        try{
            SysFunction sysFunction = (SysFunction)dao.doFind(con,fun);
            if(sysFunction != null){
                //检查是否存在子节点，如果存在则需要先删除子功能才能删除
                boolean hasChildren = dao.hasChildFunctions(con,sysFunction);
                if(hasChildren)
                    throw new AppException(ManageErrorCode.HASCHILDFUNCTIONS,GlobalErrorMsg.BPO_FUNCTION_HASCHILD);
                dao.doDelete(con,fun);

                //检查此节点的父节点是否还有其他叶子节点，如果没有则修改父节点类型为“叶子节点”
                try{
                    String parentId  = sysFunction.getParentId();
                    hasChildren = false;
                    hasChildren = dao.hasChildFunctions(con,new SysFunction(parentId));
    
                    if(!hasChildren){
                        //change the parent function to leaf
                        dao.changeToLeaf(con,new SysFunction(parentId));
                    }
                }catch(SQLException se){
                    throw new AppException(GlobalErrorMsg.BPO_FUNCTION_LEAF,se);            
                }
            }
        }catch(SQLException se){
            throw AppExceptionSupport(className,"removeFunction",GlobalErrorMsg.BPO_REMOVEKEY_ERROR,se);            
        }      
    }
    /**
     * 根据主键查询sysfunction记录
     * @param con
     * @param SysFunction
     * @return SysFunction
     * @throws AppException
     */
    public SysFunction findFunctionByPK(Connection con,SysFunction fun)
    throws AppException {
        SysFunction sysFunction = null;
        try{
            sysFunction = (SysFunction)dao.doFind(con,fun);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findFunctionByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }      
        
        return sysFunction;
    }
    /**
     * 获取指定SysFunction的父SysFunction
     * @param con
     * @param SysFunction
     * @return
     * @throws AppException
     */
    public SysFunction findParentFunction(Connection con,SysFunction fun)
    throws AppException {
        SysFunction sysFunction = null;
        try{
            sysFunction = (SysFunction)dao.getParentFunction(con,fun);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findParentFunction",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
        return sysFunction;       
    }
}
