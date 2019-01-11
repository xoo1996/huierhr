package org.radf.plat.sieaf.trans;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import org.radf.manage.trans.dao.SysTranseDefDAO;
import org.radf.manage.trans.entity.SysTranseDef;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.global.GlobalNames;

public class TransDefCache {

    private HashMap transDefMap;


    /**
     * Initialize,gets all SysTranseDefs from DB and caches in memory.
     */
    public void init(){
        transDefMap = new HashMap();
        SysTranseDefDAO dao = new SysTranseDefDAO();
        Connection conn = null;
        try{
            conn = DBUtil.getConnection();
            Collection collection = dao.doFindAll(conn);
            SysTranseDef obj = null;
            String transID = null;
            java.util.Iterator iter = collection.iterator();
            while(iter.hasNext()){
                obj = (SysTranseDef)iter.next();
                transID = obj.getTransId();
                transDefMap.put(transID,obj);
            }
        }catch(SQLException sqle){
            LogHelper log = new LogHelper("org.radf.plat.trans.TransDefCache.class");
            log.log(null,300002,sqle.toString());
        }
        catch(NoConnectionException exx){
            if(GlobalNames.DEBUG_OUTPUT_FLAG)
                System.out.println("Can not get DataBase connection from DB!!!"+exx);
        }finally{
            try{
                if(conn != null)
                    conn.close();
            }catch(Exception exx){
                exx.printStackTrace();
            }
        }

    }

    /**
     * Get SysTranseDef from cache by transId.
     * @param transID
     * @return
     */
    public SysTranseDef getSysTranseDef(String transID){
        SysTranseDef returnValue = null;
        if(transID == null || transDefMap == null){
            return null;
        }
        returnValue = (SysTranseDef)transDefMap.get(transID);
        return returnValue;
    }
}