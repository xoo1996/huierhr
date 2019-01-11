package org.radf.plat.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.global.GlobalErrorMsg;

/**
 * 数据库操作扩充类，提供几种操作：
 * <p>(1)使用DBBase的所有功能,以静态方式执行数据操作</p>
 * <p>(2)构造实例,创建内部数据连接</p>
 * <p>(3)使用内部数据库连接执行getSequence，querySQL，ExecSQL方法，完成数据库操作</p>
 * <p>(4)对内部数据库连接，通过beginTrans，commitTrans，rollbackTrans控制事务处理</p>
 *    
 * @author zqb
 * @version 1.1
 */
public class DBExtend extends DBUtil{
    private  Connection con = null;
    private  PreparedStatement ps = null;
    private  ResultSet rs = null;
    
    public DBExtend() throws SQLException{
        try{
            openConnection();
        }catch(SQLException e){
            throw new SQLException("[DBExtend]构造失败："+GlobalErrorMsg.DB_CONNECTION_ERROR+e.getMessage());
        }
    }
    
    /**
     * 释放内部数据库连接资源
     * @param con
     * @param rs
     * @param statement
     * @throws SQLException
     */
    public void destroy(){
        closeResult();
        closeStatement();
        closeConnection();
    }
    
    /**
     * 使用内部自动创建的数据库连接，获取指定名称的序列
     * @param sequenceName  序列名称
     * @return java.lang.String
     * @exception SQLException
     */
    public String getSequence(String sequenceName) throws SQLException {
        return getSequence(con,sequenceName);
    }
    
    /**
     * 获取数据库系统当前时间，针对不同的数据库，SQL语句由外部传入
     * @return
     * @throws SQLException
     */
    public String getSysDate()throws SQLException {
        return getSysDate(con);
    }
    /**
     * 获取数据库系统当前时间
     * @return java.sql.Date
     * @throws SQLException
     */
    public java.sql.Date getSysDate2() throws SQLException {
        return getSysDate2(con);
    }

    /**
     *使用内部数据库连接，查询数据并返回结果集合
     *@param String sql：要查询的SQL语句
     *@return ResultSet rs：查询的结果集
     * @throws SQLException
     **/
    public ResultSet query(String sql) throws SQLException{
        if(sql==null)
            throw new SQLException("要查询的SQL语句为空");
        try{
            closeResStat();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch(SQLException se){
            System.out.println("数据query错误(" + String.valueOf(se.getErrorCode()) + ")：" + se.getMessage());
        }
        return rs;
    }
    /**
     * 使用内部数据库连接，根据传入的SQL执行查询，并返回一个Collection的记录结果结合
     * 其中每条记录以HashMap格式存放，HashMap的key就是该值对应字段的顺序号（从1开始）
     * 注意：本方法不适合读取clob或blob等大数据类型
     * @param sql
     * @return
     * @throws SQLException
     */
    public Collection querySQL(String sql) throws SQLException{
        try{
            query(sql);
            return ResultToCollection(rs,"1");
        }finally{
            closeResStat();
        }
    }
    /**
     * 使用内部数据库连接，执行无参数保存操作
     * 不会自动提交，也不会释放连接数据库Connection资源。
     * @param sql  要执行的数据操作语句。
     * @throws SQLException   执行数据操作语句失败时，抛出异常。
     */
    public void execSQL(String sql) throws SQLException {
        //打开数据库连接并清楚原来记录集
        execSQL(con,sql);
    }


    /**
     * 执行多条SQL语句更新操作，内部独立事务控制
     * @param sqlList(List)
     * @return void
     * @exception SQLException
     */
    public void execSQL(List sqlList) throws SQLException {
        if (sqlList == null || sqlList.size() == 0)
            return;
        try {
            closeResStat();
            beginTrans();
            Statement stmt = null;
            try{
                stmt = con.createStatement();
                for (int i = 0; i < sqlList.size(); i++) {
                    stmt.executeUpdate((String) sqlList.get(i));
                }
            }finally{
                stmt.close();
            }
            commitTrans();
        } catch (SQLException ex) {
            rollbackTrans();
            throw new SQLException("数据更新失败,错误码" + ex.getCause() + ",错误信息" + ex.getMessage());
        }finally{
        }
    }
    /**
     * 创建独立的数据库事务，执行存储过程并提交事务(方法准确性未验证)
     * @param ProName     过程名
     * @param ParamValue  参数值数组
     * @param ParamType   参数类型数组
     * @param ParamNum    存储过程需要的参数个数
     * @return 返回字符串的前两位表示返回代码，2位后是存储过程返回的文本。
     * @throws SQLException  执行存储过程出错抛出异常。
     */
    public String exproAndCommit(String ProName, String ParamValue[],
            String ParamType[], int ParamNum) throws SQLException {
        return exproAndCommit(con,ProName,ParamValue,ParamType,ParamNum);
    }
    /**
     * 根据sql语句获取数据条数，传递的sql语句必须是"select count(*)..."格式
     * @param sql
     * @return
     * @throws SQLException
     */
    public int getRowCount(String sql) throws SQLException{
        return getRowCount(con,sql);
    }
    
    /**
     * 判断内部ResultSet数据集中下一条记录是否存在
     * @return boolean 下一条记录是否存在
     * @exception SQLException
     */
    public boolean nextRow() throws SQLException {
        if (rs == null)
            throw new SQLException("ResultSet is null");
        return rs.next();
    }
    /**
     * 获取内部ResultSet数据集当前所在行号
     * @return
     * @throws SQLException
     */
    public int getRow() throws SQLException {
        if (rs == null)
            throw new SQLException("ResultSet is null");
        return rs.getRow();
    }


    /**
     * 开始事务
     * @throws SQLException  事务开始失败时，抛出异常。
     */
    public void beginTrans() throws SQLException {
        try {
            verifyConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new SQLException("数据库事务创建失败：" + e.getMessage());
        }
    }

    /**
     * 提交事务，释放ps和rs，但数据库连接不释放
     * @throws SQLException 提交失败时，抛出异常。
     */
    public void commitTrans() throws SQLException {
        try {
            verifyConnection();
            con.commit();
            con.setAutoCommit(true);
            closeResStat();
        } catch (SQLException e) {
            throw new SQLException("数据库提交出错: " + e.getMessage());
        }
    }

    /**
     * 回滚事务，释放ps和rs，但数据库连接不释放
     * @throws SQLException 回滚失败时，抛出异常。
     */
    public void rollbackTrans() throws SQLException {
        try {
            verifyConnection();
            con.rollback();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            throw new SQLException("数据库回滚出错: " + e.getMessage());
        } finally {
            closeResStat();
        }
    }

    
    /**
     * 校验数据库连接是否存在，如果不存在则抛出异常
     * @throws SQLException
     */
    private void verifyConnection()throws SQLException {
        if(con==null||con.isClosed()){
            throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR);
        }
    }
    
    /**
     * 关闭ResultSet
     */
    private void closeResult(){
        closeResult(rs);
    }
    /**
     * 关闭Statement
     */
    private void closeStatement(){
        closeStatement(ps);
    }
    /**
     * 关闭数据库连接
     */
    private void closeConnection(){
        closeConnection(con);
    }
    /**
     * 释放ResultSet和Statement
     * @throws SQLException
     */
    private void closeResStat(){
        closeResult();
        closeStatement();
    }
    
    /**
     * 创建传递SQL语句的PreparedStatement 以单引号引用的内容将组装成参数形式传递并执行
     * 例如：select * from A where B = 'a'
     *     则改为select * from A where B = ? ,并传递参数"a"
     * @param Connect con 数据库连接
     * @param String queryy 数据库语句
     * @return PreparedStatement
     */
    private PreparedStatement createPreparedStatement(String querry) throws SQLException {
        ArrayList targetStrings = new ArrayList();
        String processedQuerry = "";
        int startIndex = -1; // 开始时，此参数控制是执行分解参数还是不执行分解参数
        if (startIndex != -1) {
            int index = startIndex;
            int literalStart = -1;
            while (index < querry.length()) {
                // 查找字符串中存在的特殊符号：'
                if (querry.charAt(index) == '\'') {
                    if (literalStart == -1 && index + 1 < querry.length()) {
                        literalStart = index + 1; // 符号" ' "出现在字符串中的位置
                    } else {
                        String targetString = querry.substring(literalStart,
                                index);// 连续两个引号之间的内容
                        targetStrings.add(targetString);
                        literalStart = -1;
                        processedQuerry += "?";
                        index++;
                    }
                }
                if (index < querry.length() && literalStart == -1) {
                    processedQuerry += querry.charAt(index);
                }
                index++;
            }
            ps = con.prepareStatement(processedQuerry + " ");
            Iterator it = targetStrings.iterator();
            int counter = 1;
            while (it.hasNext()) {
                String arg = (String) it.next();
                ps.setString(counter++, arg);
            }
        } else {
            ps = con.prepareStatement(querry);
        }
        return ps;
    }
    /**
     * 创建内部数据库连接
     * 此连接是本类内部默认连接，也可以返回调用者使用 
     * @return  Connection 创建的连接
     * @throws SQLException
     */
    private Connection openConnection()throws SQLException {
        //创建连接
        try{
            if(con==null||con.isClosed()){
                destroy();
                con = getConnection();
            }else{
                closeResStat();
            }
        }catch(SQLException se){
            throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR + se.getMessage());
        }catch(NoConnectionException se){
            throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR + se.getMessage());
        }
        return con;
    }
    protected void finalize()throws Throwable{
        destroy();
    }
}
