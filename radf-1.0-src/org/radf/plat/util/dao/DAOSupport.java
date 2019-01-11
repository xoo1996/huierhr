package org.radf.plat.util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NotFindException;
import org.radf.plat.util.global.GlobalNames;

/**
 * DAO的超类，定义了DAO的几个基本操作方法，这些方法必须被业务DAO实现
 * 
 * @author zqb
 * @version 1.0
 */
public class DAOSupport implements DAO {
	/**
	 * 增加数据，返回增加后变更的内容，需要重构
	 * 
	 * @param conn
	 *            数据库连接
	 * @param obj
	 *            要保存的对象
	 * @return obj 保存后返回的对象
	 * @exception SQLException
	 */
	public Object doCreate(Connection conn, Object obj) throws SQLException {
		if (obj == null) {
			throw new SQLException("基类doCreate或doStore方法没有被具体业务层实现，请与开发商联系");
		} else {
			String hql = DBUtil.getSQLByObject(obj,1);
			DBUtil.execSQL(conn, hql);
		}
		return obj;
	}

	/**
	 * 增加数据，无返回结果，需要重构
	 * 
	 * @param conn
	 *            数据库连接
	 * @param obj
	 *            要保存的对象
	 * @exception SQLException
	 */
	public void doStore(Connection conn, Object obj) throws SQLException {
		doCreate(conn, obj);
	}

	/**
	 * 删除数据，需要重构
	 * 
	 * @param conn
	 *            数据库连接
	 * @param obj
	 *            要保存的对象
	 * @exception SQLException
	 */
	public void doDelete(Connection conn, Object obj) throws SQLException {
		if (obj == null) {
			throw new SQLException(
					"基类doDelete方法没有被具体业务层实现，或文件中没有正确的配置SQL语句，请与开发商联系");
		} else {
			String hql = DBUtil.getSQLByObject(obj,3);
            DBUtil.execSQL(conn, hql);
		}
	}

	/**
	 * 修改数据，需要重构
	 * 
	 * @param conn
	 *            数据库连接
	 * @param obj
	 *            要保存的对象
	 * @exception SQLException
	 */
	public void doUpdate(Connection conn, Object obj) throws SQLException {
		if (obj == null) {
			throw new SQLException(
					"基类doUpdate方法没有被具体业务层实现，或文件中没有正确的配置SQL语句，请与开发商联系");
		} else {
			String hql = DBUtil.getSQLByObject(obj,2);
			System.out.println(hql);
            DBUtil.execSQL(conn, hql);
		}
	}

	/**
	 * 无条件，不分页，查询所有数据，需要重构
	 * 
	 * @param conn
	 *            数据库连接
	 * @param obj
	 *            要保存的对象
	 * @return Collection 查询对象结果集
	 * @exception SQLException
	 */
	public Collection getAllRecords(Connection con) throws SQLException,
			NotFindException {
		throw new SQLException(
				"基类getAllRecords方法没有被具体业务层实现，或文件中没有正确的配置SQL语句，请与开发商联系");
	}

	/**
	 * 根据可以唯一确定一条记录的主健或其他条件查询数据。
	 * 输入的对象可以是sql语句或一个实体类，
     * 如果输入的对象是实体类，则SQL配置文件中必须有对应的sql查询语句。
     * 返回结果可能是一条记录也可能是多条记录，由具体实现类或者sql语句判断。
     * 本方法可以被重构，自行控制实现方式，没有重构时，返回的是一个结果集。
	 * @param con 数据库连接
	 * @param obj 要保存的对象
	 * @return obj 查询到的结果数据，可能是一条记录的对象，也可能是列表集合
	 * @exception SQLException
	 */
	public Object doFind(Connection con, Object obj) throws NotFindException,
			SQLException {
        if (obj == null) {
            throw new SQLException("基类doFind方法没有被具体业务层实现，请与开发商联系");
        } else {
            String hql = DBUtil.getSQLByObject(obj,4);
            return DBUtil.querySQL(con, hql, "2");
        }
	}
    /**
     * 分页查询基础方法，根据传入的SQL语句品装完整分页语句，并获取指定分页数据。
     * count或offset为0表示不分页查询所有满足条件记录。
     * @param conn
     * @param sql   数据查询语句部分，可以是完整的sql语句，也可以是where开始条件部分(需要重构doSelect获取select部分)
     * @param count 每页记录数
     * @param offset    偏移开始量 =(页数-1)*每页记录数+1
     * @return Collection 结果集合
     */
    public Collection doFindBySQL(Connection conn, String sql) throws SQLException {
        if(sql.trim().toUpperCase().startsWith("WHERE")){
            sql = doSelectSQL() + sql;
        }
        String pageSQL = DBUtil.pageSQL(sql,0,0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(pageSQL);
            ps.clearParameters();
            rs = ps.executeQuery();
            return doBuildPage(rs);
        } finally {
            DBUtil.closeResStat(rs, ps);
        }
    }
    /**
     * 分页查询基础方法，根据传入的SQL语句品装完整分页语句，并获取指定分页数据。
     * count或offset为0表示不分页查询所有满足条件记录。
     * @param conn
     * @param sql
     *            数据查询语句部分，可以是完整的sql语句，也可以是where开始条件部分(需要重构doSelect获取select部分)
     * @param count
     *            每页记录数
     * @param offset
     *            偏移开始量，=(页数-1)*每页记录数+1
     * @return Collection 结果集合
     */
    public Collection doFindBySQL(Connection conn, String sql,
            int count, int offset) throws SQLException {
        if(sql.trim().toUpperCase().startsWith("WHERE")){
            sql = doSelectSQL() + sql;
        }
        String pageSQL = DBUtil.pageSQL(sql,offset,count);
        PreparedStatement ps = null;
        ResultSet rs = null;
		if(GlobalNames.DEBUG_OUTPUT_FLAG)
		{
			System.out.println("doFindBySQL==="+pageSQL);
		}
		
        try {
            ps = conn.prepareStatement(pageSQL);
            ps.clearParameters();
            rs = ps.executeQuery();
            return doBuildPage(rs);
        } finally {
            DBUtil.closeResStat(rs, ps);
        }
    }

//    /**
//     * 根据指定sql语句或对象,获取满足条件的记录数目，
//     * 如果输入的对象是实体类，则SQL配置文件中必须有对应的sql查询语句。
//     * 此方法用下面的：DBUtil.getRowCount(conn, obj);
//     * @param conn
//     * @param obj
//     * @return
//     * @throws SQLException
//     */
//    public static int doExistBySQL(Connection conn, Object obj)
//            throws SQLException {
//        return DBUtil.getRowCount(conn, obj);
//    }

    /**
     * 根据指定条件，从指定表中获取满足条件的记录个数
     * 
     * @param conn
     * @param table
     * @param whereSQL
     * @return
     * @throws SQLException
     * @throws NotFindException
     */
    public static int doExistBySQL(Connection conn, String table,
            String whereSQL) throws SQLException {
        StringBuffer sbSQL = new StringBuffer(512);
        sbSQL.append("SELECT COUNT(1) FROM ");
        sbSQL.append(table);
        sbSQL.append(" ");
        sbSQL.append(whereSQL);
        return DBUtil.getRowCount(conn, sbSQL.toString());
    }

	/**
	 * 传递分页查询的select语句部分，需要重构 如果外部计划传递完整SQL语句，不进行品装，可以retun ""，通过BPO传递完整的SQL语句
	 * 
	 * @return
	 */
	protected String doSelectSQL() throws SQLException {
		throw new SQLException("基类doSelectSQL方法没有被具体业务层实现，请与开发商联系");
		// return "";
	}

	/**
	 * 生成分页查询结果集，需要重构
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	protected Collection doBuildPage(ResultSet rs) throws SQLException {
        return DBUtil.ResultToCollection(rs,"2");
	}

	/**
	 * 根据传递的SQL语句以及分页信息获取分页结果 本信息只为了版本更新等固定位置使用
	 * 
	 * @param conn
	 *            数据库
	 * @param pStatement
	 *            传递的stmt
	 * @param sql
	 *            要分页的sql语句
	 * @param ver
	 *            客户端的版本
	 * @param count
	 *            要获取的记录数
	 * @return
	 * @throws SQLException
	 */
    public PreparedStatement versionPage(Connection conn,
			PreparedStatement pStatement, String sql, int ver, int count)
			throws SQLException {
		if (count == 0) {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, ver);
		} else {
			pStatement = conn.prepareStatement(DBUtil.pageSQL(sql));
			pStatement.setInt(1, ver);
			pStatement.setInt(2, 1 + count);
			pStatement.setInt(3, 1);
		}
		return pStatement;
	}


    /**
     * 获取指定版本开始的更新数据，并且每次获得指定条数。需要重构
     * 
     * @param conn
     *            数据库连接
     * @param nVersion
     *            当前客户端版本号，大于此版本的数据才更新
     * @param count
     *            获取纪录的条数，按照版本号顺序获取指定纪录数的数据，0表示全部
     * @param data
     *            返回的数据结果
     * @return int 获取的数据的最大版本号
     * @throws SQLException
     */
    public int getVersionData(Connection conn, int nVersion, int count,
            Collection data) throws SQLException {
        return getVersionData(conn, nVersion, data);
    }

    /**
     * 获取指定版本开始的更新数据，需要重构
     * 
     * @param conn
     *            数据库连接
     * @param nVersion
     *            当前客户端版本号，大于此版本的数据才更新
     * @param data
     *            返回的数据结果
     * @return int 获取的数据的最大版本号
     * @throws SQLException
     */
    public int getVersionData(Connection conn, int nVersion, Collection data)
            throws SQLException {
        return getVersionData(conn, data);
    }

    /**
     * 获取所有的更新数据，需要重构
     * 
     * @param conn
     *            数据库连接
     * @param data
     *            返回的数据结果
     * @return int 获取的数据的最大版本号
     * @throws SQLException
     */
    public int getVersionData(Connection conn, Collection data)
            throws SQLException {
        throw new SQLException("基类getVersionData方法没有被具体业务层实现，请与开发商联系");
    }

    /**
     * 根据传入的对象，获取删除数据的SQL语句，用于版本控制
     * 
     * @param dto
     * @return 删除的完整SQL语句
     */
    public String getRemoveSQL(Object obj) {
        return null;
    }
}
