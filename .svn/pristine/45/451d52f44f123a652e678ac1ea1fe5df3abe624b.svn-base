package org.radf.plat.commons;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.sql.CLOB;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.ResultSetDynaClass;

import org.radf.plat.log.LogHelper;
import org.radf.plat.util.entity.EntitySupport;
import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;

/**
 * 数据库操作基础类，提供数据库操作的基本方法，本类全部是静态方法,不能实例化 主要提供三类操作： (1)根据传入的配置参数获取数据库连接；
 * (2)根据外部传入的数据库连接(包括Connection,PreparedStatement,ResultSet)进行关闭操作 (3)获取序列
 * (4)根据sql语句查询 (5)执行指定的sql语句
 * 
 * @author zqb
 * @version 1.1
 */
public abstract class DBUtil {
	private static LogHelper log;

	private static DataSource ds; // getConnection(int type,String dsName)建立

	private static Context ic;

	private static String dataSourceName; // 由getConnection(String dsName)建立

	private static DataSource specifiedDS; // 由getConnection(String dsName)建立

	private static DataSource logServiceDS;

	public DBUtil() {
		init();
	}

	/**
	 * 数据库连接初始化。 提供一次初始化连接，以加快第一次访问的速度
	 */
	public static void init() {
		Connection conn1 = null;
		Connection conn2 = null;
		log = new LogHelper("DBUtil.class");
		try {
			conn1 = getConnection();
			conn2 = getLogConnection();
		} catch (NoConnectionException nex) {
			log.log(null, GlobalNames.DEBUG_LOG, nex.toString());
		} finally {
			closeConnection(conn1);
			closeConnection(conn2);
		}
	}

	/**
	 * 获取数据库连接。
     * 根据系统参数DATABASE_CONNECT_TYPE判断是直接jdbc获取还是连结池获取。
	 * 
	 * @return java.sql.Connection
	 * @exception NoConnectionException
	 */
	public static Connection getConnection() throws NoConnectionException {
		if (GlobalNames.DATABASE_CONNECT_TYPE.equalsIgnoreCase("1")) {
			return getConnection(GlobalNames.DATA_SOURCE);
		} else {
			return getJDBCConnection();
		}
	}
    /**
     * 直接从系统参数中获取jdbc连结参数并创建连结。
     * 如果jdbc连结参数配置错误，此连结方式将不能正常使用。
     * @return
     * @throws NoConnectionException
     */
    public static Connection getJDBCConnection()throws NoConnectionException {
        return getConnection(GlobalNames.DATABASE_DRIVERNAME,
                GlobalNames.DATABASE_URL, GlobalNames.DATABASE_USER,
                GlobalNames.DATABASE_PASSWORD);
    }
	/**
	 * 获取日志数据库连接
	 * 
	 * @return
	 * @throws NoConnectionException
	 * @exception NoConnectionException
	 */
	public static Connection getLogConnection() throws NoConnectionException {
		if (GlobalNames.DATABASE_CONNECT_TYPE.equalsIgnoreCase("1")) {
			return getConnection(GlobalNames.DATA_SOURCE_LOG);
		} else {
			return getConnection(GlobalNames.DATABASE_DRIVERNAME,
					GlobalNames.DATABASE_URL, GlobalNames.DATABASE_USER,
					GlobalNames.DATABASE_PASSWORD);
		}
	}

	// /**
	// * 根据连接请求类型，连接数据库
	// * 如果连接方式为type=2，则参数dsNmae没有意义
	// * 此方法提供了数据库连接的底层参数调用，除非业务层需要跳过系统配置参数的连接模式，否则一般不需要使用此方法
	// * 正常情况下可以使用：
	// * getConnection()
	// * getConnection(String dsName)
	// * @param type type=1表示连接池方式，其他为jdbc直接连接
	// * @param dsName 连接的DataSoure名称
	// * @return
	// * @throws NoConnectionException
	// */
	// public static Connection getConnection(int type,String dsName)throws
	// NoConnectionException {
	// Connection conn = null;
	// if (type == 1) {
	// conn = getConnection(dsName);
	// } else { //type != 1
	// conn = getConnection(GlobalNames.DATABASE_DRIVERNAME,
	// GlobalNames.DATABASE_URL,
	// GlobalNames.DATABASE_USER,
	// GlobalNames.DATABASE_PASSWORD
	// );
	// }
	// return conn;
	// }

	/**
	 * 根据传递的参数创建连接
	 * 
	 * @param driverName
	 * @param url
	 * @param user
	 * @param password
	 */
	public static Connection getConnection(String driverName, String url,
			String user, String password) throws NoConnectionException {
		Connection conn = null;
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException ex) {
			throw new NoConnectionException("错误的数据库驱动程序名(" + driverName + "):"
					+ ex.getMessage());
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			beginTrans(conn);
		} catch (SQLException se) {
			System.out.println("出现SQLException错误：" + se.toString());
			throw new NoConnectionException(
					GlobalErrorCode.NOCONNECTEXCEPTIONCODE, "无法创建数据库连接("
							+ GlobalNames.DATABASE_URL + ","
							+ GlobalNames.DATABASE_USER + ")");
		} finally {
			//if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.out.println("Return connection = " + conn);
			//}
		}
		return conn;
	}
    
	// /**
	// * 获取指定DATASOURCE的连接
	// * 只有GlobalNames.DATABASE_CONNECT_TYPE = "1"有意义
	// * @param dsName
	// * @return
	// * @throws NoConnectionException
	// */
	// public static Connection getConnection(String dsName)
	// throws NoConnectionException {
	// if (GlobalNames.DATABASE_CONNECT_TYPE.equalsIgnoreCase("1")) {
	// return getConnection(1,dsName);
	// } else {
	// return getConnection(2,null);
	// }
	// }
	/**
	 * 获取指定DATASOURCE的连接。 如果连接名字不是配置文件中定义的DATA_SOURCE或DATA_SOURCE_LOG，
	 * 则创建相应的外部连接池，但此连接池会覆盖原来通过此方法创建的外部连接池。
	 * 
	 * @param dsName
	 * @return
	 * @throws NoConnectionException
	 */
	public static Connection getConnection(String dsName)
			throws NoConnectionException {
		Connection conn = null;
		String dss="java:comp/env/";
		dsName=dss+dsName;
		try {
			if (ic == null)
				ic = getInitialContext();
			if (dsName.equalsIgnoreCase(dss+GlobalNames.DATA_SOURCE)) {
				if (ds == null)
					ds = (DataSource) ic.lookup(dss+GlobalNames.DATA_SOURCE);
				conn = ds.getConnection();
			} else if (dsName.equalsIgnoreCase(dss+GlobalNames.DATA_SOURCE_LOG)) {
				// 日志连接
				if (logServiceDS == null) {
					logServiceDS = (DataSource) ic
							.lookup(dss+GlobalNames.DATA_SOURCE_LOG);
				}
				conn = logServiceDS.getConnection();
			} else {
				// 其他连接
				if (!dsName.equals(dataSourceName)) {
					dataSourceName = dsName;
					specifiedDS = null;
					specifiedDS = (DataSource) ic.lookup(dataSourceName);
				} else if (specifiedDS == null) {
					specifiedDS = (DataSource) ic.lookup(dataSourceName);
				}
				conn = specifiedDS.getConnection();
			}
			if (conn == null)
				throw new NoConnectionException();
			beginTrans(conn);
		} catch (NamingException ex) {
			System.out.println("出现NamingException错误：" + ex.toString());
			throw new NoConnectionException(
					GlobalErrorCode.NOCONNECTEXCEPTIONCODE,
					"NamingException错误：" + ex.getMessage());
		} catch (SQLException sql) {
			System.out.println("出现SQLException错误：" + sql.toString());
			throw new NoConnectionException(
					GlobalErrorCode.NOCONNECTEXCEPTIONCODE, "SQLException错误："
							+ sql.getMessage());
		} finally {
			//if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.out.println("Return connection = " + conn);
			//}
		}
		return conn;
	}

	/**
	 * 使用传递的数据库连接，获取指定名称的序列
	 * 
	 * @param con
	 * @param sequenceName
	 * @return java.lang.String
	 * @exception SQLException
	 */
	public static String getSequence(Connection conn, String sequenceName)
			throws SQLException {
		String sequenceID = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("Select ");
		buffer.append(sequenceName);
		buffer.append(".nextval from dual");
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement(buffer.toString());
			rs = pstm.executeQuery();
			if (rs.next())
				sequenceID = rs.getString(1);
		} catch (SQLException se) {
			throw se;
		} finally {
			closeResStat(rs, pstm);
			buffer = null;
		}
		return sequenceID;
	}
    /**
     * 自动创建数据库连结，获取指定名称的序列，然后再关闭数据库连结。
     * 一般情况下，业务中都不应该使用此方法获取序列，以免重复建立连结
     * @param sequenceName
     * @return java.lang.String
     * @exception SQLException
     * @throws NoConnectionException 
     */
    public static String getSequencevalue(String sequenceName)throws SQLException, NoConnectionException {
        Connection con = null;
        try{
            con = getConnection();
            return getSequence(con,sequenceName);
        }finally{
            closeConnection(con);
        }
    }
    
    /**
	 * 取数据库服务器中系统时间。 获取的数据库操作根据配置文件的SQL_SYSDATE语句得到，可以适用于所有数据库。
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static String getSysDate(Connection conn) throws SQLException {
		String result = "";
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(GlobalNames.SQL_SYSDATE);
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
				if (result == null)
					result = "";
			}
		} finally {
			closeResStat(rs, ps);
		}
		return result;
	}

	/**
	 * 取数据库服务器中系统时间，只适用于oracle数据库
	 * 
	 * @param conn
	 * @return java.sql.Date
	 * @throws SQLException
	 */
	public static java.sql.Date getSysDate2(Connection conn)
			throws SQLException {
		java.sql.Date result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("select sysdate from dual");
			rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getDate(1);
			}
		} finally {
			closeResStat(rs, ps);
		}
		return result;

	}

	/**
	 * 根据传入的SQL执行查询，并返回一个Collection的记录结果结合，
	 * 其中每条记录以HashMap格式存放，HashMap的key就是该值对应字段的顺序号（从1开始）
	 * 
	 * @param con
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static Collection querySQL(Connection conn, String sql)
			throws SQLException {
        return querySQL(conn,sql,"1");
	}
    /**
     * 根据传入的SQL执行查询，并返回一个Collection的记录结果结合，
     * 返回的类型根据参数type决定：
     * type=1时，每条记录以HashMap格式存放，HashMap的key就是该值对应字段的顺序号（从1开始）；
     * type=2时，每条纪录保存为DynaBean格式
     * @param conn
     * @param sql
     * @param type
     * @return
     * @throws SQLException
     */
  
    
    
    
    
    public static Collection querySQL(Connection conn, String sql,String type)throws SQLException
    {
    	 if(type==null||!type.equalsIgnoreCase("2")){
             type = "1";
         }
         // 入口参数合法性校验
         if (sql == null)
             throw new SQLException("要查询的SQL语句为空");
         if (conn == null || conn.isClosed()) {
             throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR);
         }

         PreparedStatement pstm = null;
         ResultSet res = null;
         try {
             pstm = conn.prepareStatement(sql);
             res = pstm.executeQuery();
             return ResultToCollection(res,type);
 		} catch (SQLException ex) {
 			log.debug(sql);
 			throw new SQLException(ex.getMessage());
         } finally {
             closeResStat(res, pstm);
         }
    }
	/**
	 * 根据传递的数据库连接，执行无参数保存操作 不会自动提交，也不会释放连接数据库Connection资源。
	 * 
	 * @param sql
	 *            要执行的数据操作语句。
	 * @throws SQLException
	 *             执行数据操作语句失败时，抛出异常。
	 */
	public static void execSQL(Connection conn, String sql) throws SQLException {
		if (sql == null) {
			throw new SQLException("要执行的SQL语句为空");
		}
		if (conn == null || conn.isClosed()) {
			throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR);
		}
		PreparedStatement ps = null;
		try {
			// 打开数据库连接并清楚原来记录集
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException ex) {
			log.debug(sql);
			throw new SQLException(ex.getMessage());
			
		} finally {
			closeStatement(ps);
		}
		return;
	}

	/**
	 * 执行存储过程并提交事务(方法准确性未验证),事务提交等通过外部执行 返回参数固定两个，第一个是int，第二个是string
	 * 
	 * @param ProName
	 *            过程名
	 * @param ParamValue
	 *            参数值数组
	 * @param ParamType
	 *            参数类型数组(目前只支持String,int,float,Date四类，注意大小写)
	 * @param ParamNum
	 *            存储过程需要的参数个数
	 * @return 返回字符串的前两位表示返回代码，2位后是存储过程返回的文本。
	 * @throws SQLException
	 *             执行存储过程出错抛出异常。
	 */
	public String exproAndCommit(Connection con, String ProName,
			String ParamValue[], String ParamType[], int ParamNum)
			throws SQLException {
		CallableStatement CallStmt = null;
		String strReturnTxt = null; // 返回的结果信心
		try {
			// 生成执行prepareCall所传递的语句
			String strCallSql = "{ call " + ProName + "(";
			for (int i = 0; i < ParamNum + 2; i++) {
				strCallSql += "?";
				if (i < ParamNum + 1) {
					strCallSql += ",";
				}
			}
			strCallSql += ") }";
			// 数据库操作
			CallStmt = con.prepareCall(strCallSql);
			// 参数分解
			for (int i = 0; i < ParamNum; i++) {
				if (ParamType[i].equalsIgnoreCase("String")) {
					CallStmt.setString(i + 1, ParamValue[i]);
				} else if (ParamType[i].equalsIgnoreCase("int")) {
					CallStmt.setInt(i + 1, Integer.parseInt(ParamValue[i]));
				} else if (ParamType[i].equalsIgnoreCase("float")) {
					CallStmt.setFloat(i + 1, Float.parseFloat(ParamValue[i]));
				} else if (ParamType[i].equalsIgnoreCase("Date")) {
					CallStmt.setDate(i + 1, Date.valueOf(ParamValue[i]));
				} else {
					CallStmt.setString(i + 1, ParamValue[i]);
				}
			}
			// 设置输出参数
			CallStmt.registerOutParameter(ParamNum + 1, Types.INTEGER);
			CallStmt.registerOutParameter(ParamNum + 2, Types.VARCHAR);

			CallStmt.execute();

			// 执行存储过程的结果
			String strReturnCode = String
					.valueOf(CallStmt.getInt(ParamNum + 1));
			strReturnTxt = CallStmt.getString(ParamNum + 2);
			for (int i = 0; i < 2 - strReturnCode.length(); i++) {
				strReturnCode = " " + strReturnCode;
			}
			strReturnTxt = strReturnCode + strReturnTxt;
		} catch (SQLException e) {
			throw new SQLException("执行存储过程出错:" + e.getMessage());
		} finally {
			try {
				CallStmt.close();
			} catch (SQLException se) {
			}
		}
		return strReturnTxt;
	}

	/**
	 * 根据sql语句获取数据条数，传递的sql语句必须是"select count(*)..."格式
	 * 
	 * @param con
	 * @param countSQL
	 * @return
	 * @throws SQLException
	 */
	public static int getRowCount(Connection con, String countSQL)
			throws SQLException {
		ResultSet rs = null;
		PreparedStatement pStatement = null;
		int count = 0;
		
		try {
				pStatement = con.prepareStatement(countSQL);
				pStatement.clearParameters();
				rs = pStatement.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				} else {
					count = 0;
				}
		} catch (SQLException ex) {
				// TODO Auto-generated catch block
			    log.debug(countSQL);
				throw new SQLException(ex.getMessage());
		
		} finally {
			closeResStat(rs, pStatement);
		}
		return count;
	}

	/**
	 * 释放ResultSet，自动忽略异常
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public static void closeResult(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
			rs = null;
		}
	}

	/**
	 * 释放Statement，自动忽略异常
	 * 
	 * @param statement
	 * @throws SQLException
	 */
	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Exception e) {
			}
			statement = null;
		}
	}

	/**
	 * 释放Connection，自动忽略异常
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public static void closeConnection(Connection con) {
		//if(GlobalNames.DEBUG_OUTPUT_FLAG)
		//{
			System.out.println("Close Connection = " + con);
		//}
		
		if (con != null) {
			try {
				rollback(con);
				con.close();
			} catch (Exception e) {
			} finally {
			}
			con = null;
		}
	}

	/**
	 * 释放ResultSet和Statement，自动忽略异常
	 * 
	 * @param rs
	 * @param ps
	 */
	public static void closeResStat(ResultSet rs, Statement ps) {
		closeResult(rs);
		closeStatement(ps);
	}

	/**
	 * This method is used to get an <code>javax.naming.InitialContext</code>
	 * object from JDNI tree.
	 * 
	 * @return javax.transaction.InitialContext
	 * @throws NamingException
	 */
	private static Context getInitialContext() throws NamingException {
		Properties properties = null;
		try {
			properties = new Properties();
			if (GlobalNames.SERVER_TYPE.equalsIgnoreCase("1")) {
				// weblogic下
				properties.put(Context.INITIAL_CONTEXT_FACTORY,
						GlobalNames.NAME_INITIAL_FACTORY);
				properties.put(Context.PROVIDER_URL,
						GlobalNames.NAME_URL_PROVIDER);
				return new InitialContext(properties);
			} else if (GlobalNames.SERVER_TYPE.equalsIgnoreCase("2")) {
				// tomcat下
			} else if (GlobalNames.SERVER_TYPE.equalsIgnoreCase("3")) {
				// oc4j下
			} else if (GlobalNames.SERVER_TYPE.equalsIgnoreCase("4")) {
				// jboss下
			} else {

			}
			return new InitialContext();
		} catch (Exception e) {
			System.out.println("Unable to connect to WebLogic server at "
					+ GlobalNames.NAME_URL_PROVIDER);
			System.out.println("Please make sure that the server is running.");
			return null;
		}
	}

	/**
	 * 将ResultSet中的返回数据组成Collection结构返回。
     * 返回的类型根据参数type决定：
	 * type=1时，每条记录以HashMap格式存放，HashMap的key就是该值对应字段的顺序号（从1开始）；
     * type=2时，每条纪录保存为DynaBean格式
	 * 
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public static Collection ResultToCollection(ResultSet rs, String type)
			throws SQLException {
		if (type == null||type.equals(""))
            type = "1";
		if (type.equals("1")) {
			Collection result = new Vector();
			while (rs.next()) {
				HashMap row = new HashMap();
				int i = 1;
//				for(int i=1;i<=rs.getMetaData().getColumnCount();i++)
//				{
				while (true) {
					try {
						Object fieldValue = rs.getObject(i);
						row.put(new Integer(i).toString(), fieldValue);
						i++;
					} catch (Exception e) {
						break;
					}
				}
				result.add(row);
				row = null;
			}
			return result;
		} else if (type.equals("2")) {
			ArrayList result = null;
			ResultSetDynaClass resultsetdynaclass = null;
			try {
				resultsetdynaclass = new ResultSetDynaClass(rs, true);
				BasicDynaClass basicdynaclass = new BasicDynaClass("test",
						org.apache.commons.beanutils.BasicDynaBean.class,
						resultsetdynaclass.getDynaProperties());
				Iterator iterator = resultsetdynaclass.iterator();
				if (iterator.hasNext())
					result = new ArrayList();
				else
					result = null;
				DynaBean dynabean1;
				for (; iterator.hasNext(); result.add(dynabean1)) {
					DynaBean dynabean = (DynaBean) iterator.next();
					dynabean1 = basicdynaclass.newInstance();
					ClassHelper.copyProperties(dynabean, dynabean1);
				}
			} catch (Exception exception) {
				throw new SQLException("数据转换出错！");
			}

			return result;
		}
		return null;
	}

	private static Context getInitialContext(String url) throws NamingException {
		Properties properties = null;
		try {
			properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					GlobalNames.NAME_INITIAL_FACTORY);
			properties.put(Context.PROVIDER_URL, url);
			return new InitialContext(properties);
		} catch (Exception e) {
			System.out.println("Unable to connect to WebLogic server at "
					+ GlobalNames.NAME_URL_PROVIDER);
			System.out.println("Please make sure that the server is running.");
			return null;
		}
	}

	/**
	 * 创建数据库的事务
	 * 
	 * @param con
	 * @throws SQLException
	 */
	public static void beginTrans(Connection con) throws SQLException {
		if (con != null) {
			con.setAutoCommit(false);
		}
	}

	/**
	 * 数据库连接事务回滚，无需考虑异常
	 * 
	 * @param con
	 */
	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * 数据库事务提交
	 * 
	 * @param con
	 * @exception SQLException
	 */
	public static void commit(Connection con) throws SQLException {
		if (con != null) {
			con.commit();
		}
	}

	/**
	 * 根据传入的SQL语句，获取动态的分页SQL语句，分页部分在语句最后增加两个参数：
	 * 第一个参数是获取需要小于的记录号(最大的记录号)，第二个参数是大于等于的记录号(最小的记录号)。
	 * 这两个参数需要通过ps.setInt(x,xx)方式来赋值
	 * 
	 * @param sql  传入的SQL语句
	 * @return
	 */
	public static String pageSQL(String sql) {
		StringBuffer sb = new StringBuffer(128 + sql.length());
		sb.append("select * from (select t.* ,rownum as raa from ( ");
		sb.append(sql);
		sb.append(") t where rownum < ?) where raa >= ?");
		return sb.toString();
	}

	/**
	 * 根据传入的SQL语句，获取分页SQL语句
	 * 
	 * @param sql  传入的SQL语句
	 * @param offset  第一条纪录的偏移量
	 * @param count  要显示的记录条数
	 * @return
	 */
	public static String pageSQL(String sql, int offset, int count) {
		if (offset < 0 || count <= 0) {
			return sql;
		} else {
			StringBuffer sb = new StringBuffer(512);
			sb.append("select * from (select t.* ,rownum as raa from ( ");
			sb.append(sql);
			sb.append(") t where rownum <");
			sb.append(offset + count);
			sb.append(") where raa >= ");
			sb.append(offset);
			return sb.toString();
		}
	}

	/**
	 * 将指定sql语句中能获取到的clob字段内容替换成传递的字符串内容。
     * 数据库事务由外部处理，且传递的连结不能是连接池。
	 * @param con  数据库连接
     * @param tableName
     * @param colName
     * @param key
	 * @param s    要替换的字符串，不管多少条记录都替换
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void saveClob(Connection conn, String tableName,String colName,String key, String s) throws SQLException, IOException {
        CallableStatement pstmt = null;
        ResultSet resultset = null;
        String sql = "select " + colName + " from " + tableName + " where " + key + " for update";
        String sql2 = "update " + tableName + " set "+ colName + " = EMPTY_CLOB()" + " where " + key;
		try {
			pstmt = conn.prepareCall(sql2);
			resultset = pstmt.executeQuery();
			DBUtil.closeStatement(pstmt);
			pstmt = conn.prepareCall(sql);
			resultset = pstmt.executeQuery();
			while (resultset.next()) {
				CLOB clob = (oracle.sql.CLOB) resultset.getClob(1);
                java.io.Writer pw = clob.getCharacterOutputStream();
                pw.write(s);
                pw.flush();
                pw.close();
			}
        }catch(SQLException ex){
			log.debug(sql);
			throw new SQLException(ex.getMessage());
		} finally {
			DBUtil.closeResStat(resultset, pstmt);
		}
	}
    
    /**
     * 将指定sql语句中能获取到的blob字段内容替换成传递的二进制数据。
     * 数据库事务由外部处理，且传递的连结不能是连接池。
     * @param con
     * @param tableName 表名
     * @param colName   字段
     * @param key       获取要保存记录的条件
     * @param bytes     要保存的数据
     * @throws SQLException
     * @throws IOException
     */
    public static void saveBlob(Connection con,String tableName,String colName,String key,byte[] bytes)throws SQLException, IOException{
//        oracle.jdbc.driver.OraclePreparedStatement  pstmt = null;
//        oracle.jdbc.OracleResultSet resultset = null;
        
        PreparedStatement  pstmt = null;
        ResultSet resultset = null;
        String sql = "select " + colName + " from " + tableName + " where " + key + " for update";
        String sql2 = "update " + tableName + " set "+ colName + " = EMPTY_BLOB()" + " where " + key;

        try {
//			pstmt = (oracle.jdbc.driver.OraclePreparedStatement)con.prepareStatement(sql2);
//            resultset = (oracle.jdbc.OracleResultSet)pstmt.executeQuery();
			pstmt = con.prepareStatement(sql2);
            resultset = pstmt.executeQuery();
			DBUtil.closeStatement(pstmt);

			pstmt = (oracle.jdbc.driver.OraclePreparedStatement)con.prepareStatement(sql);
            resultset = (oracle.jdbc.OracleResultSet)pstmt.executeQuery();
            while (resultset.next()) {
                oracle.sql.BLOB blob = ((oracle.jdbc.OracleResultSet)resultset).getBLOB(1);
                
                //weblogic
                //weblogic.jdbc.vendor.oracle.OracleThinBlob blob = (weblogic.jdbc.vendor.oracle.OracleThinBlob) rs.getBlob("BLOBATTR");
                //tomcat
                OutputStream ost = blob.getBinaryOutputStream();   
                ost.write(bytes);
                ost.flush();
                ost.close();
			  }
			
        }catch(SQLException ex){
					log.debug(sql);
					throw new SQLException(ex.getMessage());
        } finally {
            DBUtil.closeResStat(resultset, pstmt);
        }
    }
    /**
     * 这个转换方法主要用于apache.commons.dbcp连结转换成标准连结。
     * 需要dbcp包支持
     * @param con
     * @return
     * @throws SQLException
     */
    /*
    private Connection getNativeConnection(Connection con)
    throws SQLException {
        if (con instanceof DelegatingConnection) {
            Connection nativeCon = ((DelegatingConnection) con).getInnermostDelegate();
            return (nativeCon != null ? nativeCon : con.getMetaData().getConnection());
        }
        return con;
    }
    */
	// 为了使软件能够在Tomcat下正常运行，取消此方法
	// /**
	// * This method is used to get a
	// * <code>javax.transaction.UserTransaction</code> from JNDI tree.
	// *
	// * @return javax.transaction.UserTransaction
	// */
	// public static UserTransaction getUserTransaction() throws
	// NamingException,
	// Exception {
	// UserTransaction tran = null;
	// try {
	// Context ic = getInitialContext();
	// if (ic != null) {
	// tran = (UserTransaction) ic.lookup(GlobalNames.USER_TRANSACTION);
	// }
	// if (tran == null) {
	// throw new Exception("UserTransaction is null!");
	// }
	// } catch (NamingException ex) {
	// System.out
	// .println("catch NamingException when trying to lookup UserTransaction
	// name\n"
	// + ex.toString());
	// throw ex;
	// } finally {
	// }
	// return tran;
	// }

	/**
	 * <p>根据对象以及配置文件中内容生成SQL语句。 </p>
     * <p>(1）如果传入的本身就是SQL语句，则直接返回；</p>
	 * <p>(2)如果传入的不是EntitySupport或DTOSupport继承类，则报错</p>
     * <p>(3)否则，根据配置文件key值，拼装sql语句返回</p>
     * <p>(4)如果没有key值，根据对象类别obj和操作类型type，获取配置文件中与其同名的表的指定操作，自动拼装SQL语句。
     *       例如：类Entity对象增加，则找key=Entity_Isert的配置项</p> 
	 * @param obj  要保存的内容
     * @param type 数据操作类型(1-增加,2-修改，3-删除，4-查询)
	 * @return
	 * @throws SQLException
	 */
	public static String getSQLByObject(Object obj,int type) throws SQLException {
		String sql = null;
		String key = null;
		if (obj == null) {
			throw new SQLException("传入对象为空");
		} else if (obj instanceof String) {
			sql = (String) obj;
		} else {
			
			try {
                EntitySupport dto = (EntitySupport) obj;
                key = dto.getFileKey();
                if(key == null||key.equalsIgnoreCase("")){
                    String className = obj.getClass().getName();
                    String[] s = StringUtil.getAsStringArray(className,".");
                    className = s[s.length-1].toLowerCase();
                    switch(type){
                        case 1:
                            key = className+"_insert";
                            break;
                        case 2:
                            key = className+"_update";
                            break;
                        case 3:
                            key = className+"_delete";
                            break;
                        case 4:
                            key = className+"_select";
                            break;
                        default:{
                        }
                    }
                }
			} catch (Exception e) {
				throw new SQLException("传入对象" + obj + "不符合EntitySupport类型");
			}
			if (key == null||key.equalsIgnoreCase("")) {
				if(sql==null||sql.equalsIgnoreCase("")){
					throw new SQLException("传入对象中没有指定配置文件中SQL语句编号");
				}
			} else {
				String hql = PagerUtil.getFileSQL(key);
                if(hql==null||hql.equalsIgnoreCase("")){
                    throw new SQLException("配置文件中没有找到编号为 "+key+" 的sql语句");
                }
				if(GlobalNames.DEBUG_OUTPUT_FLAG && !"sc11_insert".equalsIgnoreCase(key)){
					System.out.println("调用QueryFunction.getHQL(hql, obj)生成编号为 "+key+" 的sql");
				}
				sql = QueryFunction.getHQL(hql, obj);
				if(GlobalNames.DEBUG_OUTPUT_FLAG  && !"sc11_insert".equalsIgnoreCase(key)){
					System.out.println("调用getSQLByObject生成编号为 "+key+" 的sql语句是："+sql);
				}
			}
		}
		
		return sql;
	}
}
