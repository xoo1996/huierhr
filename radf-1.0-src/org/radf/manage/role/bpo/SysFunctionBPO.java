/**
* <p>����: ���ڽ��׶������ش�����</p>
* <p>˵��: BPO</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-5-269:45:29</p>
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
     * ����SysFunction��¼�������ش���id�ı����SysFunction����
     * ���������ϼ��ڵ�����Ϊ��Ҷ��(flag=1)�������޸��ϼ��ڵ����ͣ��������Ϊ�ջ����������޸�
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
            //����ϼ�functionԭ����Ҷ�ӣ����Ϊ�ڵ�
            if(parentFlag.equals(Constants.LEAF_FUNCTION)){
                dao.changeToNode(con,new SysFunction(function.getParentId()));
            }
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createFunction",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
        return fun;
    }
    /**
     * �޸�sysfunction��¼
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
     * ɾ��ָ�����׶���
     * ����˽ڵ����ӽڵ㣨������Ҷ�ӽڵ㣩���򲻿�ɾ��
     * ����˽ڵ����丸�ڵ�Ψһ��Ҷ�ӣ���ɾ���˽ڵ�ʱͬʱ�޸ĸ��ڵ�����Ϊ��Ҷ�ӽڵ㡱
     * @param con
     * @param SysFunction
     * @throws AppException
     */
    public void removeFunction(Connection con,SysFunction fun)
    throws AppException {
        try{
            SysFunction sysFunction = (SysFunction)dao.doFind(con,fun);
            if(sysFunction != null){
                //����Ƿ�����ӽڵ㣬�����������Ҫ��ɾ���ӹ��ܲ���ɾ��
                boolean hasChildren = dao.hasChildFunctions(con,sysFunction);
                if(hasChildren)
                    throw new AppException(ManageErrorCode.HASCHILDFUNCTIONS,GlobalErrorMsg.BPO_FUNCTION_HASCHILD);
                dao.doDelete(con,fun);

                //���˽ڵ�ĸ��ڵ��Ƿ�������Ҷ�ӽڵ㣬���û�����޸ĸ��ڵ�����Ϊ��Ҷ�ӽڵ㡱
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
     * ����������ѯsysfunction��¼
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
     * ��ȡָ��SysFunction�ĸ�SysFunction
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
