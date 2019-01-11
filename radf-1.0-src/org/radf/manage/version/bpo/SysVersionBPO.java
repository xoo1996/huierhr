/**
* <p>标题: 关于客户端数据版本处理的BPO</p>
* <p>说明: 包括SysVersion和SysClientVersion的处理</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-6-1318:25:40</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.bpo;

import java.sql.Connection;
import java.sql.SQLException;

import org.radf.manage.version.dao.SysVersionDAO;
import org.radf.manage.version.entity.SysVersion;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class SysVersionBPO extends org.radf.plat.util.bpo.BPOSupport {
    private static final String className = SysVersionBPO.class.getName();
    private static final SysVersionDAO dao = new SysVersionDAO();
    public SysVersionBPO(){
        super(className,dao);
    }
    /**
     * 修改指定表的版本号，如果此表没有版本信息，则自动增加一条
     * @param tableName 需要修改版本的表
     * @param note      版本变动说明
     * @return version  版本号
     * @exception AppException
     */
    public int incCurrentVersion(Connection con,String tableName,String note)
    throws AppException {
        int iVersion = 0;
        try{
            SysVersion ver = new SysVersion();
            ver.setTableName(tableName);
            ver.setNote(note);

            //获取指定表的版本信息
            iVersion = dao.getCurrentVersion(con,tableName);
            //如果修改返回的版本编号为0，则表示没有此表的版本记录
            
            if(iVersion==0){
                //增加版本
                iVersion = dao.doCreate(con,ver);
            }else{
                //修改版本
                iVersion = dao.doUpdate(con,ver);
            }
            
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"incCurrentVersion",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"incCurrentVersion",GlobalErrorMsg.SYS_VERSION_UPDATE,se);            
        }
        return iVersion;
    }
    /**
     * 获取指定表当前最新版本号
     * @param con
     * @param tableName
     * @return
     * @throws AppException
     */
    public int getCurrentVersion(Connection con,String tableName)
    throws AppException {
        try{
            int iVersion = 0;
            iVersion = dao.getCurrentVersion(con,tableName);
            return iVersion;
        }catch(NotFindException nfe){
            throw AppExceptionSupport(className,"getCurrentVersion",nfe);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"getCurrentVersion",GlobalErrorMsg.SYS_VERSION_VERIFY,se);            
        }
    }
    /**
     * 检验版本是否需要更新
     * @param tableName
     * @param version
     * @return boolean
     * @exception AppException
     */
    public boolean verifyVersion(Connection con,String tableName,int version)
        throws AppException {
        int iVersion = 0;
        iVersion = getCurrentVersion(con,tableName);
        
        if(iVersion>version){
            return true;
        }else{
            return false;
        }
     }

}
