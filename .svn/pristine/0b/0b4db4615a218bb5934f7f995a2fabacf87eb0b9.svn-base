/**
* <p>标题: 关于delphi客户端ParamDictionary表的数据处理类</p>
* <p>说明: 只用在版本更新中</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-4-1822:43:01</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.manage.role.entity.ParamDictionary;
import org.radf.plat.commons.DBUtil;

public class ParamDictionaryDAO extends org.radf.plat.util.dao.DAOSupport{
    private static String SELECT_VER_SQL =
        "select * from ParamDictionary where version > ? order by version";

    /* (non-Javadoc)
     * @see org.radf.plat.util.dao.DAOSupport#getVersionData(java.sql.Connection, int,Collection)
     */
    public int getVersionData(Connection conn, int nVersion,int count,Collection data) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int version = 0;
        try {
            stmt = versionPage(conn,stmt,SELECT_VER_SQL,nVersion,count);
            rs = stmt.executeQuery();
            while(rs.next()){
                 ParamDictionary dto = new ParamDictionary();
                 dto.setFunctionID(rs.getString("FunctionID"));
                 dto.setCodeFlag(rs.getString("CodeFlag"));
                 dto.setCodeType(rs.getString("CodeType"));
                 dto.setDataSetName(rs.getString("DataSetName"));
                 dto.setDisPlayText(rs.getString("DisPlayText"));
                 dto.setFieldName(rs.getString("FieldName"));
                 dto.setParamType(rs.getInt("ParamType"));
                 dto.setRequiredEncrypt(rs.getInt("RequiredEncrypt"));
                 dto.setSize(rs.getInt("iSize"));
                 dto.setTabOrder(rs.getInt("TabOrder"));
                 dto.setVersion(rs.getInt("VERSION"));
                 data.add(dto);
                 version = rs.getInt("VERSION"); 
            }
        }
        finally {
            DBUtil.closeResStat(rs,stmt);
        }
        return version;
    }
}
