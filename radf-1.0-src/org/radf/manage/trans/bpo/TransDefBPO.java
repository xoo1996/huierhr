/**
* <p>����: ���׹���</p>
* <p>˵��: BPO</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-6-216:56:48</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.trans.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.radf.manage.trans.dao.SysTranseDefDAO;
import org.radf.manage.trans.entity.SysTranseDef;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class TransDefBPO extends org.radf.plat.util.bpo.BPOSupport{
    private String className = this.getClass().getName();
    private SysTranseDefDAO transDAO = new SysTranseDefDAO();

    /**
     * ����SYSTRANSEDEF��¼
     * @param con
     * @param SysTranseDef dto
     * @return SysTranseDef
     * @throws AppException
     */
    public SysTranseDef createTrans(Connection con,SysTranseDef dto)
    throws AppException {
        SysTranseDef trans = null;
        try{
            trans = (SysTranseDef)transDAO.doCreate(con, dto);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createTrans",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
        return trans;
    }
    
    /**
     * �޸�SYSTRANSEDEF��¼
     * @param con
     * @param SysTranseDef dto
     * @throws AppException
     */
    public SysTranseDef modifyTrans(Connection con,SysTranseDef dto)
    throws AppException {
        SysTranseDef trans = null;
        try{
            transDAO.doUpdate(con, dto);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"modifyTrans",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
        return trans;
    }
    
    /**
     * ɾ��SYSTRANSEDEF��¼
     * @param con
     * @param SysTranseDef dto
     * @throws AppException
     */
    public void removeTrans(Connection con,SysTranseDef dto)
    throws AppException {
        try{
            transDAO.doDelete(con, dto);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"removeTrans",GlobalErrorMsg.BPO_REMOVEKEY_ERROR,se);            
        }
    }
   
    /**
     * ����������ѯSYSTRANSEDEF��¼
     * @param con
     * @param SysTranseDef dto
     * @throws AppException
     */
    public SysTranseDef findTransByPK(Connection con,SysTranseDef dto)
    throws AppException {
        SysTranseDef trans = null;
        try{
            trans = (SysTranseDef)transDAO.doFind(con, dto);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findTransByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return trans;
    }
    
    /**
     * ��ѯ����SYSTRANSEDEF��¼
     * @param con
     * @param SysTranseDef dto
     * @throws AppException
     */
    public ArrayList findAllTrans(Connection con)
    throws AppException {
        ArrayList result = null;
        try{
            result = (ArrayList)transDAO.doFindAll(con);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findAllTrans",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
        return result;
    }
}
