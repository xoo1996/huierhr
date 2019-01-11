// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.AppException;

public class ChartQueryDAO
    implements Serializable
{

//    private static OPManager a = new OPManager();

    public ChartQueryDAO()
    {
    }

    public static List list(String s, int i)
        throws SQLException,Exception
    {
        switch(i)
        {
        case 0: // '\0'
            return listTimeData(s);

        case 1: // '\001'
            return list(s);
        }
        return null;
    }

    public static List list(String s)
        throws SQLException,Exception
    {
        ArrayList arraylist;
        arraylist = new ArrayList();
        ResultSet resultset = null;
        PreparedStatement preparedstatement = null;
        Connection connection=null;
        try
        {
            connection = DBUtil.getConnection();
            preparedstatement = connection.prepareStatement(s);
            resultset = preparedstatement.executeQuery();
            ResultSetMetaData resultsetmetadata = resultset.getMetaData();
            int i = resultsetmetadata.getColumnCount();
            Object aobj[];
            for(; resultset.next(); arraylist.add(((Object) (aobj))))
            {
                aobj = new Object[i];
                for(int j = 0; j < i; j++)
                    aobj[j] = resultset.getObject(j + 1);

            }

        }
        catch(Exception exception)
        {
        	exception.printStackTrace();
        }
        finally
        {
    		DBUtil.rollback(connection);
    		DBUtil.closeConnection(connection);
        	try
            {
                if(resultset != null)
                    resultset.close();
                if(preparedstatement != null)
                    preparedstatement.close();
            }
            catch(SQLException sqlexception1)
            {
                sqlexception1.printStackTrace();
            }
        }
        return arraylist;
    }
    
    public static List listTimeData(String s)
    throws Exception
{
    ArrayList arraylist;
    arraylist = new ArrayList();
    Connection connection=null;
    try
    {
    	connection = DBUtil.getConnection();
        PreparedStatement preparedstatement = connection.prepareStatement(s);
        ResultSet resultset = preparedstatement.executeQuery();
        ResultSetMetaData resultsetmetadata = resultset.getMetaData();
        int i = resultsetmetadata.getColumnCount();
        String s1 = "获取TimeData图表数据出错：";
        String s2 = null;
        if(i < 2)
            s2 = "数据不能少于两个字段！";
        if(s2 != null)
            throw new AppException(s1 + s2);
        Object aobj[];
        for(; resultset.next(); arraylist.add(((Object) (aobj))))
        {
            aobj = new Object[i];
            aobj[0] = resultset.getTimestamp(1);
            aobj[1] = resultset.getObject(2);
        }

    }
    catch(SQLException sqlexception)
    {
        throw new AppException(sqlexception);
    }
 
    finally
    {
    	DBUtil.rollback(connection);
		DBUtil.closeConnection(connection);
    }
    return arraylist;
}

  

}
