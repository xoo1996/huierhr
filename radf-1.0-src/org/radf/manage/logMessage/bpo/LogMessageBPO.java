/**
 * <p>标题: </p>
 * <p>说明: </p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-5-2911:41:34</p>
 *
 * @author Administrator
 * @version 1.0
 */
package org.radf.manage.logMessage.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.manage.logMessage.dao.LogMessageDAO;
import org.radf.manage.logMessage.entity.LogMessage;
import org.radf.manage.role.entity.SysUser;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class LogMessageBPO extends org.radf.plat.util.bpo.BPOSupport {
    private static final String className = LogMessageBPO.class.getName();

    private   static final LogMessageDAO dao = new LogMessageDAO();
    public LogMessageBPO(){
        super(className,dao);
    }
    /**
     * 删除日志信息非总控方法 <br>
     * 
     * @param Connection
     * @param LogMessageDTO
     * @return void
     * @throws AppException
     */
    public void removeLogMessage(Connection con, LogMessage dto)
            throws AppException {
        // 执行删除, 调用DAO.doDelete
        try {
            dao.doDelete(con, dto);
        } catch (SQLException se) {
            throw AppExceptionSupport(className, "removeLogMessage",
                    GlobalErrorMsg.BPO_REMOVE_ERROR, se);
        }
    }

    /**
     * 查询单个日志信息非总控方法 <br>
     * 
     * @param Connection
     * @param object
     *            LogMessage
     * @return object LogMessage with values
     * @throws AppException
     */
    public Object findLogMessage(Connection con, LogMessage dto)
            throws AppException {
        // 执行查询, 调用DAO.doFind
        try {
            return dao.doFind(con, dto);
        } catch (SQLException se) {
            throw AppExceptionSupport(className, "findLogMessage",
                    GlobalErrorMsg.BPO_FIND_ERROR, se);
        }
    }

    /**
     * 按FunctionID查询日志信息非总控方法
     * 
     * @param Connection
     * @param functionid
     * @param count
     * @param offset
     * @return Collection of LogMessage
     * @throws AppException
     */
    public Collection findByFunctionid(Connection con, String functionid,
            int count, int offset) throws AppException {
        Collection collect = null;
        // 按条件查询, 调用DAO.doFindBySql
        try {
            collect = dao.doFindByFunctionid(con, functionid, count, offset);
        } catch (SQLException se) {
            throw AppExceptionSupport(className, "findByFunctionid",
                    GlobalErrorMsg.BPO_FIND_ERROR, se);
        }
        return collect;
    }

    /**
     * 按Message模糊查询日志信息非总控方法
     * 
     * @param Connection
     * @param message
     * @param count
     * @param offset
     * @return Collection of LogMessage
     * @throws AppException
     */
    public Collection findByMessage(Connection con, String message, int count,
            int offset) throws AppException {
        Collection collect = null;
        // 按条件查询, 调用DAO.doFindBySql
        try {
            collect = dao.doFindByMessage(con, message, count, offset);
        } catch (SQLException se) {
            throw AppExceptionSupport(className, "findByMessage",
                    GlobalErrorMsg.BPO_FIND_ERROR, se);
        }
        return collect;
    }

    /**
     * 按Msgdate时间段查询日志信息非总控方法
     * 
     * @param Connection
     * @param beginMsgDate
     * @param endMsgDate
     * @param count
     * @param offset
     * @return Collection of LogMessage
     * @throws AppException
     */
    public Collection findByMsgdate(Connection con, String beginMsgDate,
            String endMsgDate, int count, int offset) throws AppException {

        Collection collect = null;
        // 按条件查询, 调用DAO.doFindBySql
        try {
            collect = dao.doFindByMsgdate(con, beginMsgDate, endMsgDate, count,
                    offset);
        } catch (SQLException se) {
            throw AppExceptionSupport(className, "findByMsgdate",
                    GlobalErrorMsg.BPO_FIND_ERROR, se);
        }
        return collect;

    }

    /**
     * 按loginName查询日志信息非总控方法
     * 
     * @param Connection
     * @param SysUser
     * @param count
     * @param offset
     * @return Collection of LogMessage
     * @throws AppException
     */
    public Collection findByLoginName(Connection con, SysUser user, int count,
            int offset) throws AppException {

        Collection collect = null;
        // 按条件查询, 调用DAO.doFindBySql
        try {
            collect = dao.doFindByUserid(con, user, count, offset);
        } catch (SQLException se) {
            throw AppExceptionSupport(className, "findByLoginName",
                    GlobalErrorMsg.BPO_FIND_ERROR, se);
        }
        return collect;
    }

    /**
     * 查询指定条件数据前生成的SQL语句
     * 
     */
    public String toSQL(Object beoObj) {
        StringBuffer sql = new StringBuffer(256);
        sql.append(" where 1=1 ");
        LogMessage beo = (LogMessage) beoObj;

		 if (String.valueOf(beo.getCode())!=null&&!String.valueOf(beo.getCode()).equals("")){
	            sql.append(" and  CODE=");
	            sql.append(beo.getCode()+"");  
	        }
		 if (beo.getLoginName()!=null&&!beo.getLoginName().equals("")){
	            sql.append(" and  LOGINNAME='");
	            sql.append(beo.getLoginName()+"'");  
	        }
		 if (beo.getMsgdate()!=null){
	            sql.append(" and  to_char(to_date(MSGDATE),'yyyy-mm-dd') like '%");
	            sql.append(beo.getMsgdate()+"%'");  
	        }
        if (beo.getPaixu() == null || beo.getPaixu().equals("")) {
            sql.append(" order by ID");
        } else {

            String aa = "  order by  ";
            sql.append(aa);

            sql.append("" + beo.getPaixu() + "");
            String bb = "";
            if (beo.getDianji() != null && beo.getDianji().equals("false")) {
                bb = " desc  ";
                sql.append(bb);
            } else {
                bb = " asc  ";
                sql.append(bb);
            }
        }

        System.out.println(sql);
        return sql.toString();
    }
}
