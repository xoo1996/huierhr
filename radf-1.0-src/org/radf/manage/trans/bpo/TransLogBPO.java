/**
* <p>标题: 交易日志管理</p>
* <p>说明: BPO</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-6-39:40:52</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.trans.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.radf.manage.trans.dao.SysTransLogDAO;
import org.radf.manage.trans.dao.SysTransVastInputDAO;
import org.radf.manage.trans.dao.SysTransVastOutputDAO;
import org.radf.manage.trans.entity.SysTransLog;
import org.radf.manage.trans.entity.SysTransVastInput;
import org.radf.manage.trans.entity.SysTransVastOutput;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class TransLogBPO extends org.radf.plat.util.bpo.BPOSupport{
    private String className = this.getClass().getName();
    private SysTransLogDAO dao = new SysTransLogDAO();
    private SysTransVastInputDAO inputDAO = new SysTransVastInputDAO();
    private SysTransVastOutputDAO outputDAO = new SysTransVastOutputDAO();

    /**
     * 增加新的交易日志记录
     * @param con
     * @param SysTransLog dto
     * @return
     * @throws AppException
     */
    public SysTransLog createTransLog(Connection con,SysTransLog dto)
    throws AppException {
        SysTransLog log = null;
        try{
            log = (SysTransLog)dao.doCreate(con, dto);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createTransLog",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
        return log;       
    }
    /**
     * 修改交易日志
     * @param con
     * @param dto
     * @throws AppException
     */
    public void updateTransLog(Connection con,SysTransLog dto)
    throws AppException {
        SysTransLog log = null;
        try{
            dao.doUpdate(con, dto);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"updateTransLog",GlobalErrorMsg.BPO_UPDATE_ERROR,se);            
        }
    }
    
    
    /**
     * 根据主键查询SYSTRANSEDEF记录
     * @param con
     * @param SysTransLog dto
     * @throws AppException
     */
    public SysTransLog findTransLogByPK(Connection con,String id)
    throws AppException {
        SysTransLog log = null;
        try{
            log = (SysTransLog)dao.doFind(con, id);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findTransLogByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return log;
    }
    /**
     * 根据时间查询SYSTRANSLOG记录,分页显示
     * @param con
     * @param String start 开始时间
     * @param String end 结束时间
     * @param int count 每页显示行数
     * @param int offset    开始偏移
     * @return ArrayList LogList
     * @throws AppException
     */
    public ArrayList findTransLogByTime(Connection con,String start,String end,int count,int offset)
    throws AppException {
        ArrayList logList = null;
        try{
            logList = (ArrayList)dao.doFindByTime(con, start,end,count,offset);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findTransLogByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return logList;
    }

    /**
     * 根据TransName查询SYSTRANSLOG记录,分页
     * @param con
     * @param String transName
     * @param int count 每页显示行数
     * @param int offset    开始偏移
     * @return ArrayList LogList
     * @throws AppException
     */
    public ArrayList findTransLogByTransName(Connection con,String transName,int count,int offset)
    throws AppException {
        ArrayList logList = null;
        try{
            logList = (ArrayList)dao.doFindByTransName(con, transName,count,offset);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findTransLogByTransName",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return logList;
    }
    /**
     * 根据登录名（userid）查询SYSTRANSLOG记录,分页
     * @param con
     * @param String loginName  登录名
     * @param int count 每页显示行数
     * @param int offset    开始偏移
     * @return ArrayList LogList
     * @throws AppException
     */
    public ArrayList findTransLogByLoginName(Connection con,String loginName,int count,int offset)
    throws AppException {
        ArrayList logList = null;
        try{
            logList = (ArrayList)dao.doFindByLoginName(con, loginName,count,offset);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findTransLogByLoginName",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return logList;
    }
    /**
     * 根据登录名（status）查询SYSTRANSLOG记录,分页
     * @param con
     * @param String status  状态
     * @param int count 每页显示行数
     * @param int offset    开始偏移
     * @return ArrayList LogList
     * @throws AppException
     */
    public ArrayList findTransLogByStatus(Connection con,String status,int count,int offset)
    throws AppException {
        ArrayList logList = null;
        try{
            logList = (ArrayList)dao.doFindByStatus(con, status,count,offset);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findTransLogByStatus",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return logList;
    }
    
    /**
     * 查询logid大于指定值的SYSTRANSLOG记录
     * @param con
     * @param String id
     * @throws AppException
     */
    public ArrayList findTransLogForward(Connection con,String id)
    throws AppException {
        ArrayList result = null;
        try{
            result = (ArrayList)dao.doFindForward(con,id);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findTransLogForward",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
        return result;
    }
    
    /**
     * 查询logid小于指定值的SYSTRANSLOG记录
     * @param con
     * @param String id
     * @throws AppException
     */
    public ArrayList findTransLogBack(Connection con,String id)
    throws AppException {
        ArrayList result = null;
        try{
            result = (ArrayList)dao.doFindBack(con,id);          
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findTransLogBack",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
        return result;
    }
    
    /**
     * 根据主键查询SYSTRANSVASTINPUT记录
     * @param con
     * @param String id
     * @return SysTransVastInput
     * @throws AppException
     */
    public SysTransVastInput findInLogByPK(Connection con,String id)
    throws AppException {
        SysTransVastInput input = null;
        try{
            input = (SysTransVastInput)inputDAO.doFind(con,id);        
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findInLogByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return input;
    }
    /**
     * 增加输入交易日志
     * @param Connection con
     * @param SysTransVastInput obj
     * @throws AppException
     */
    public void createSysTransVastInput(Connection con,SysTransVastInput obj)
    throws AppException {
        try{
            inputDAO.doStore(con,obj);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createSysTransVastInput",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
    }
    
    /**
     * 增加输出交易日志
     * @param Connection con
     * @param SysTransVastOutput obj
     * @throws AppException
     */
    public void createSysTransVastOutput(Connection con,SysTransVastOutput obj)
    throws AppException {
        try{
            outputDAO.doStore(con,obj);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"createSysTransVastOutput",GlobalErrorMsg.BPO_CREATE_ERROR,se);            
        }
    }
    /**
     * 根据主键查询SysTransVastOutput记录
     * @param con
     * @param String id
     * @return SysTransVastOutput
     * @throws AppException
     */
    public SysTransVastOutput findOutLogByPK(Connection con,String id)
    throws AppException {
        SysTransVastOutput input = null;
        try{
            input = (SysTransVastOutput)outputDAO.doFind(con,id);        
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findOutLogByPK",GlobalErrorMsg.BPO_FINDKEY_ERROR,se);            
        }
        return input;
    }

}
