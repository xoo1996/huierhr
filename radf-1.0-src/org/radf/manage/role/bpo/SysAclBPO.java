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
* ����ACL��BPO
* @author zqb
* @version 1.0
 */
public class SysAclBPO extends org.radf.plat.util.bpo.BPOSupport {
    private String className = this.getClass().getName();
    private AclDAO dao = new AclDAO();
	public SysAclBPO() {
	}
    /**
     * ����SysAct��¼�������ش���id�ı����SysAcl����
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
     * ������AclID��ѯ��Ӧ��SysAcl��¼
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
     *����roleid��Ӧ��Acl��¼��
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
     * ����roleID��Ӧ��FunctionID��¼��
     * @param con
     * @param Object SysRole
     * @return Collection  String��ʽfunctionIdList
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
     * ����aclID�޸�SysAcl��¼
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
     * ����aclIDɾ��SysAcl��¼
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
