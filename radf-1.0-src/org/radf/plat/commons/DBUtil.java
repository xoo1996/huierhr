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
 * ���ݿ���������࣬�ṩ���ݿ�����Ļ�������������ȫ���Ǿ�̬����,����ʵ���� ��Ҫ�ṩ��������� (1)���ݴ�������ò�����ȡ���ݿ����ӣ�
 * (2)�����ⲿ��������ݿ�����(����Connection,PreparedStatement,ResultSet)���йرղ��� (3)��ȡ����
 * (4)����sql����ѯ (5)ִ��ָ����sql���
 * 
 * @author zqb
 * @version 1.1
 */
public abstract class DBUtil {
	private static LogHelper log;

	private static DataSource ds; // getConnection(int type,String dsName)����

	private static Context ic;

	private static String dataSourceName; // ��getConnection(String dsName)����

	private static DataSource specifiedDS; // ��getConnection(String dsName)����

	private static DataSource logServiceDS;

	public DBUtil() {
		init();
	}

	/**
	 * ���ݿ����ӳ�ʼ���� �ṩһ�γ�ʼ�����ӣ��Լӿ��һ�η��ʵ��ٶ�
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
	 * ��ȡ���ݿ����ӡ�
     * ����ϵͳ����DATABASE_CONNECT_TYPE�ж���ֱ��jdbc��ȡ��������ػ�ȡ��
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
     * ֱ�Ӵ�ϵͳ�����л�ȡjdbc����������������ᡣ
     * ���jdbc����������ô��󣬴����᷽ʽ����������ʹ�á�
     * @return
     * @throws NoConnectionException
     */
    public static Connection getJDBCConnection()throws NoConnectionException {
        return getConnection(GlobalNames.DATABASE_DRIVERNAME,
                GlobalNames.DATABASE_URL, GlobalNames.DATABASE_USER,
                GlobalNames.DATABASE_PASSWORD);
    }
	/**
	 * ��ȡ��־���ݿ�����
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
	// * ���������������ͣ��������ݿ�
	// * ������ӷ�ʽΪtype=2�������dsNmaeû������
	// * �˷����ṩ�����ݿ����ӵĵײ�������ã�����ҵ�����Ҫ����ϵͳ���ò���������ģʽ������һ�㲻��Ҫʹ�ô˷���
	// * ��������¿���ʹ�ã�
	// * getConnection()
	// * getConnection(String dsName)
	// * @param type type=1��ʾ���ӳط�ʽ������Ϊjdbcֱ������
	// * @param dsName ���ӵ�DataSoure����
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
	 * ���ݴ��ݵĲ�����������
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
			throw new NoConnectionException("��������ݿ�����������(" + driverName + "):"
					+ ex.getMessage());
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			beginTrans(conn);
		} catch (SQLException se) {
			System.out.println("����SQLException����" + se.toString());
			throw new NoConnectionException(
					GlobalErrorCode.NOCONNECTEXCEPTIONCODE, "�޷��������ݿ�����("
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
	// * ��ȡָ��DATASOURCE������
	// * ֻ��GlobalNames.DATABASE_CONNECT_TYPE = "1"������
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
	 * ��ȡָ��DATASOURCE�����ӡ� ����������ֲ��������ļ��ж����DATA_SOURCE��DATA_SOURCE_LOG��
	 * �򴴽���Ӧ���ⲿ���ӳأ��������ӳػḲ��ԭ��ͨ���˷����������ⲿ���ӳء�
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
				// ��־����
				if (logServiceDS == null) {
					logServiceDS = (DataSource) ic
							.lookup(dss+GlobalNames.DATA_SOURCE_LOG);
				}
				conn = logServiceDS.getConnection();
			} else {
				// ��������
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
			System.out.println("����NamingException����" + ex.toString());
			throw new NoConnectionException(
					GlobalErrorCode.NOCONNECTEXCEPTIONCODE,
					"NamingException����" + ex.getMessage());
		} catch (SQLException sql) {
			System.out.println("����SQLException����" + sql.toString());
			throw new NoConnectionException(
					GlobalErrorCode.NOCONNECTEXCEPTIONCODE, "SQLException����"
							+ sql.getMessage());
		} finally {
			//if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.out.println("Return connection = " + conn);
			//}
		}
		return conn;
	}

	/**
	 * ʹ�ô��ݵ����ݿ����ӣ���ȡָ�����Ƶ�����
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
     * �Զ��������ݿ����ᣬ��ȡָ�����Ƶ����У�Ȼ���ٹر����ݿ����ᡣ
     * һ������£�ҵ���ж���Ӧ��ʹ�ô˷�����ȡ���У������ظ���������
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
	 * ȡ���ݿ��������ϵͳʱ�䡣 ��ȡ�����ݿ�������������ļ���SQL_SYSDATE���õ��������������������ݿ⡣
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
	 * ȡ���ݿ��������ϵͳʱ�䣬ֻ������oracle���ݿ�
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
	 * ���ݴ����SQLִ�в�ѯ��������һ��Collection�ļ�¼�����ϣ�
	 * ����ÿ����¼��HashMap��ʽ��ţ�HashMap��key���Ǹ�ֵ��Ӧ�ֶε�˳��ţ���1��ʼ��
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
     * ���ݴ����SQLִ�в�ѯ��������һ��Collection�ļ�¼�����ϣ�
     * ���ص����͸��ݲ���type������
     * type=1ʱ��ÿ����¼��HashMap��ʽ��ţ�HashMap��key���Ǹ�ֵ��Ӧ�ֶε�˳��ţ���1��ʼ����
     * type=2ʱ��ÿ����¼����ΪDynaBean��ʽ
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
         // ��ڲ����Ϸ���У��
         if (sql == null)
             throw new SQLException("Ҫ��ѯ��SQL���Ϊ��");
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
	 * ���ݴ��ݵ����ݿ����ӣ�ִ���޲���������� �����Զ��ύ��Ҳ�����ͷ��������ݿ�Connection��Դ��
	 * 
	 * @param sql
	 *            Ҫִ�е����ݲ�����䡣
	 * @throws SQLException
	 *             ִ�����ݲ������ʧ��ʱ���׳��쳣��
	 */
	public static void execSQL(Connection conn, String sql) throws SQLException {
		if (sql == null) {
			throw new SQLException("Ҫִ�е�SQL���Ϊ��");
		}
		if (conn == null || conn.isClosed()) {
			throw new SQLException(GlobalErrorMsg.DB_CONNECTION_ERROR);
		}
		PreparedStatement ps = null;
		try {
			// �����ݿ����Ӳ����ԭ����¼��
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
	 * ִ�д洢���̲��ύ����(����׼ȷ��δ��֤),�����ύ��ͨ���ⲿִ�� ���ز����̶���������һ����int���ڶ�����string
	 * 
	 * @param ProName
	 *            ������
	 * @param ParamValue
	 *            ����ֵ����
	 * @param ParamType
	 *            ������������(Ŀǰֻ֧��String,int,float,Date���࣬ע���Сд)
	 * @param ParamNum
	 *            �洢������Ҫ�Ĳ�������
	 * @return �����ַ�����ǰ��λ��ʾ���ش��룬2λ���Ǵ洢���̷��ص��ı���
	 * @throws SQLException
	 *             ִ�д洢���̳����׳��쳣��
	 */
	public String exproAndCommit(Connection con, String ProName,
			String ParamValue[], String ParamType[], int ParamNum)
			throws SQLException {
		CallableStatement CallStmt = null;
		String strReturnTxt = null; // ���صĽ������
		try {
			// ����ִ��prepareCall�����ݵ����
			String strCallSql = "{ call " + ProName + "(";
			for (int i = 0; i < ParamNum + 2; i++) {
				strCallSql += "?";
				if (i < ParamNum + 1) {
					strCallSql += ",";
				}
			}
			strCallSql += ") }";
			// ���ݿ����
			CallStmt = con.prepareCall(strCallSql);
			// �����ֽ�
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
			// �����������
			CallStmt.registerOutParameter(ParamNum + 1, Types.INTEGER);
			CallStmt.registerOutParameter(ParamNum + 2, Types.VARCHAR);

			CallStmt.execute();

			// ִ�д洢���̵Ľ��
			String strReturnCode = String
					.valueOf(CallStmt.getInt(ParamNum + 1));
			strReturnTxt = CallStmt.getString(ParamNum + 2);
			for (int i = 0; i < 2 - strReturnCode.length(); i++) {
				strReturnCode = " " + strReturnCode;
			}
			strReturnTxt = strReturnCode + strReturnTxt;
		} catch (SQLException e) {
			throw new SQLException("ִ�д洢���̳���:" + e.getMessage());
		} finally {
			try {
				CallStmt.close();
			} catch (SQLException se) {
			}
		}
		return strReturnTxt;
	}

	/**
	 * ����sql����ȡ�������������ݵ�sql��������"select count(*)..."��ʽ
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
	 * �ͷ�ResultSet���Զ������쳣
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
	 * �ͷ�Statement���Զ������쳣
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
	 * �ͷ�Connection���Զ������쳣
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
	 * �ͷ�ResultSet��Statement���Զ������쳣
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
				// weblogic��
				properties.put(Context.INITIAL_CONTEXT_FACTORY,
						GlobalNames.NAME_INITIAL_FACTORY);
				properties.put(Context.PROVIDER_URL,
						GlobalNames.NAME_URL_PROVIDER);
				return new InitialContext(properties);
			} else if (GlobalNames.SERVER_TYPE.equalsIgnoreCase("2")) {
				// tomcat��
			} else if (GlobalNames.SERVER_TYPE.equalsIgnoreCase("3")) {
				// oc4j��
			} else if (GlobalNames.SERVER_TYPE.equalsIgnoreCase("4")) {
				// jboss��
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
	 * ��ResultSet�еķ����������Collection�ṹ���ء�
     * ���ص����͸��ݲ���type������
	 * type=1ʱ��ÿ����¼��HashMap��ʽ��ţ�HashMap��key���Ǹ�ֵ��Ӧ�ֶε�˳��ţ���1��ʼ����
     * type=2ʱ��ÿ����¼����ΪDynaBean��ʽ
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
				throw new SQLException("����ת������");
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
	 * �������ݿ������
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
	 * ���ݿ���������ع������迼���쳣
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
	 * ���ݿ������ύ
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
	 * ���ݴ����SQL��䣬��ȡ��̬�ķ�ҳSQL��䣬��ҳ��������������������������
	 * ��һ�������ǻ�ȡ��ҪС�ڵļ�¼��(���ļ�¼��)���ڶ��������Ǵ��ڵ��ڵļ�¼��(��С�ļ�¼��)��
	 * ������������Ҫͨ��ps.setInt(x,xx)��ʽ����ֵ
	 * 
	 * @param sql  �����SQL���
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
	 * ���ݴ����SQL��䣬��ȡ��ҳSQL���
	 * 
	 * @param sql  �����SQL���
	 * @param offset  ��һ����¼��ƫ����
	 * @param count  Ҫ��ʾ�ļ�¼����
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
	 * ��ָ��sql������ܻ�ȡ����clob�ֶ������滻�ɴ��ݵ��ַ������ݡ�
     * ���ݿ��������ⲿ�����Ҵ��ݵ����᲻�������ӳء�
	 * @param con  ���ݿ�����
     * @param tableName
     * @param colName
     * @param key
	 * @param s    Ҫ�滻���ַ��������ܶ�������¼���滻
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
     * ��ָ��sql������ܻ�ȡ����blob�ֶ������滻�ɴ��ݵĶ��������ݡ�
     * ���ݿ��������ⲿ�����Ҵ��ݵ����᲻�������ӳء�
     * @param con
     * @param tableName ����
     * @param colName   �ֶ�
     * @param key       ��ȡҪ�����¼������
     * @param bytes     Ҫ���������
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
     * ���ת��������Ҫ����apache.commons.dbcp����ת���ɱ�׼���ᡣ
     * ��Ҫdbcp��֧��
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
	// Ϊ��ʹ����ܹ���Tomcat���������У�ȡ���˷���
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
	 * <p>���ݶ����Լ������ļ�����������SQL��䡣 </p>
     * <p>(1���������ı������SQL��䣬��ֱ�ӷ��أ�</p>
	 * <p>(2)�������Ĳ���EntitySupport��DTOSupport�̳��࣬�򱨴�</p>
     * <p>(3)���򣬸��������ļ�keyֵ��ƴװsql��䷵��</p>
     * <p>(4)���û��keyֵ�����ݶ������obj�Ͳ�������type����ȡ�����ļ�������ͬ���ı��ָ���������Զ�ƴװSQL��䡣
     *       ���磺��Entity�������ӣ�����key=Entity_Isert��������</p> 
	 * @param obj  Ҫ���������
     * @param type ���ݲ�������(1-����,2-�޸ģ�3-ɾ����4-��ѯ)
	 * @return
	 * @throws SQLException
	 */
	public static String getSQLByObject(Object obj,int type) throws SQLException {
		String sql = null;
		String key = null;
		if (obj == null) {
			throw new SQLException("�������Ϊ��");
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
				throw new SQLException("�������" + obj + "������EntitySupport����");
			}
			if (key == null||key.equalsIgnoreCase("")) {
				if(sql==null||sql.equalsIgnoreCase("")){
					throw new SQLException("���������û��ָ�������ļ���SQL�����");
				}
			} else {
				String hql = PagerUtil.getFileSQL(key);
                if(hql==null||hql.equalsIgnoreCase("")){
                    throw new SQLException("�����ļ���û���ҵ����Ϊ "+key+" ��sql���");
                }
				if(GlobalNames.DEBUG_OUTPUT_FLAG && !"sc11_insert".equalsIgnoreCase(key)){
					System.out.println("����QueryFunction.getHQL(hql, obj)���ɱ��Ϊ "+key+" ��sql");
				}
				sql = QueryFunction.getHQL(hql, obj);
				if(GlobalNames.DEBUG_OUTPUT_FLAG  && !"sc11_insert".equalsIgnoreCase(key)){
					System.out.println("����getSQLByObject���ɱ��Ϊ "+key+" ��sql����ǣ�"+sql);
				}
			}
		}
		
		return sql;
	}
}
