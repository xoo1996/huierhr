/**
* <p>标题: 关于删除记录版本历史SQL的处理类</p>
* <p>说明: BPO</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-9-513:42:23</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.manage.version.dao.SysRemoveVersionDAO;
import org.radf.manage.version.entity.SysRemoveVersion;
import org.radf.plat.util.dao.DAOSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class SysRemoveVersionBPO extends org.radf.plat.util.bpo.BPOSupport{
    private static final String className = SysRemoveVersionBPO.class.getName();
    private static final SysRemoveVersionDAO dao = new SysRemoveVersionDAO();
    public SysRemoveVersionBPO(){
        super(className,dao);
    }
    /**
     * 获取指定表被删除的SQL语句
     * @param con
     * @param sTableName
     * @param starVersion   客户端版本号
     * @param endVersion    更新到的版本号
     * @return
     * @throws AppException
     */
    public Collection getVersionData(Connection con,String sTableName,int starVersion,int endVersion)throws AppException {
        try{
            return dao.getVersionData(con,sTableName,starVersion,endVersion);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"getVersionData",GlobalErrorMsg.SYS_VERSION_UPDATE,se);            
        }
    }
    /**
     * 根据传递的表、版本、和删除的SQL语句，增加一条删除纪录的日志
     * 被删除的对象必须在业务实现DAO中重构了getRemoveSQL方法
     * @param con
     * @param sTableName    删除纪录的表
     * @param ver           删除纪录的版本流水
     * @param dao           删除纪录的业务dao
     * @param obj           被删除的对象
     * @throws AppException
     */
    public void addRemove(Connection con,String sTableName,int ver,DAOSupport dao,Object obj)throws AppException {
        SysRemoveVersion dto = new SysRemoveVersion();
        dto.setTableName(sTableName);
        dto.setVersion(ver);
        dto.setSql(dao.getRemoveSQL(obj));
        store(con,dto);
        dto = null;
    }
}
