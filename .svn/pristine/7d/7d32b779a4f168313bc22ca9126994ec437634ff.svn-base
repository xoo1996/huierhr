/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-6-315:24:27</p>
*
* @author Administrator
* @version 1.0
*/
package org.radf.manage.role.bpo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.radf.manage.role.dao.SysChangeDAO;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;

public class SysChangeBPO extends org.radf.plat.util.bpo.BPOSupport {
    private String className = this.getClass().getName();
    private SysChangeDAO dao = new SysChangeDAO();
   
    /**
     * 用主键id查询对应的SysChange记录
     * 返回的列表是syschange对象的集合
     * @param Connection
     * @param long id    
     * @return ArrayList Version List
     * @throws AppException
     */
    public ArrayList findNewVersion(Connection con,long id)
    throws AppException {
        ArrayList ver = null;
        try{
            ver = dao.doFindByBiggerThan(con, id);
        }catch(SQLException se){
            throw AppExceptionSupport(className,"findNewVersion",GlobalErrorMsg.BPO_FIND_ERROR,se);            
        }
        return ver;
    }

}
