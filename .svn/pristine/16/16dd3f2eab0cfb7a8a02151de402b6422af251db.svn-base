package org.radf.plat.util.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.plat.util.exception.NotFindException;
/**
 * DAO的接口类
 * @author zqb
 * @version 1.0
 */
public  interface DAO {

    /**
     * This method is used to save the current object into DB
     * @param conn java.sql.Connection
     * @param obj an Object being to save
     * @throws SQLException
     */
    public void doStore(Connection conn,Object obj) throws SQLException;
    public Object doCreate(Connection conn, Object obj) throws SQLException;

    /**
     * This method is used to store the object from DB
     * @param conn java.sql.Connection
     * @param obj an Object being to delete
     * @throws SQLException
     */
    public void doDelete(Connection conn, Object obj) throws SQLException;

    /**
     * This method is used to update the current object in DB
     * @param conn java.sql.Connection
     * @param obj an object being to update
     * @throws SQLException
     */
    public void doUpdate(Connection conn, Object obj) throws SQLException;
    
    /**
     * This method used to find entity from Database.
     * @param conn java.sql.Connection
     * @param obj an object holds the input parameters and output attribute
     * @return Object return value
     * @throws SQLException
     */
    public  Object doFind(Connection conn, Object obj)
        throws NotFindException,SQLException;

    /**
     * This method used to find all entity from Database.
     * @param conn java.sql.Connection
     * @return Object Collection return value
     * @throws SQLException
     */
    public Collection getAllRecords(Connection con)
        throws SQLException, NotFindException;
}
