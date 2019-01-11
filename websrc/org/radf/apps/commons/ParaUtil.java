package org.radf.apps.commons;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.radf.manage.entity.Sc01;
import org.radf.manage.param.entity.Aa01;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NoConnectionException;


public class ParaUtil extends org.radf.plat.util.imp.IMPSupport {
	public ParaUtil() {
	}
	
	/**
	 * 根据机构内码获取机构名称
	 * @param bsc001			机构编号
	 * @return
	 * @throws AppException
	 */
	public String getAab300(String bsc001) throws AppException {
		Connection	con  = null;
		Sc01		sc01 = new Sc01();
		List		list = null;
		
		if(null == bsc001 || "" == bsc001)
			return "";
		try
		{
			sc01.setBsc001(bsc001);
			sc01.setFileKey("sc01_select");
			con = DBUtil.getConnection();
			list = (List) find(con, sc01, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), sc01);
			}
			// 提交事务
			DBUtil.commit(con);
		} catch (AppException oe) {
			throw new AppException("查询参数时出错！", oe);
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		
		return sc01.getAab300();
	}

	/**
	 * 根据机构内码获取机构编码
	 * @param bsc001			机构编号
	 * @return
	 * @throws AppException
	 */
	public String getAab003(String bsc001) throws AppException {
		Connection	con  = null;
		Sc01		sc01 = new Sc01();
		List		list = null;
		
		if(null == bsc001 || "" == bsc001)
			return "";
		try
		{
			sc01.setBsc001(bsc001);
			sc01.setFileKey("sc01_select");
			con = DBUtil.getConnection();
			list = (List) find(con, sc01, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), sc01);
			}
			// 提交事务
			DBUtil.commit(con);
		} catch (AppException oe) {
			throw new AppException("查询参数时出错！", oe);
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		
		return sc01.getAab003();
	}

	/**
	 * 2008-3-11 by lwd
	 * 
	 * @param aaa001
	 * @param aaa003
	 * @param aae140
	 * @return
	 * @throws AppException
	 */
	public String getParaV(String aaa001, String aaa003, String aae140)
			throws AppException {
		Connection con = null;
		String aaa005 = "";
		Aa01 aa01 = new Aa01();
		aa01.setAaa001(aaa001);
		aa01.setAaa003(aaa003);
		aa01.setAae140(aae140);
		aa01.setFileKey("aa01_select01");
		List list = null;
		try {
			con = DBUtil.getConnection();
			list = (List) find(con, aa01, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), aa01);
			}
			// 提交事务
			DBUtil.commit(con);
		} catch (AppException oe) {
			throw new AppException("查询参数时出错！", oe);
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		if (list == null) {
			aaa005 = "";
		} else {
			aaa005 = aa01.getAaa005();
		}

		return aaa005;
	}

	/**
	 * 2008-3-11 by lwd
	 * 
	 * @param aaa001
	 * @param aaa003
	 * @param aae140
	 * @return
	 * @throws AppException
	 */
	public Aa01 getParaAa01(String aaa001, String aaa003, String aae140)
			throws AppException {
		Connection con = null;
		Aa01 aa01 = new Aa01();
		aa01.setAaa001(aaa001);
		aa01.setAaa003(aaa003);
		aa01.setAae140(aae140);
		aa01.setFileKey("aa01_select01");
		List list = null;
		try {
			con = DBUtil.getConnection();
			list = (List) find(con, aa01, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), aa01);
			}
			// 提交事务
			DBUtil.commit(con);
		} catch (AppException oe) {
			throw new AppException("查询参数时出错！", oe);
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}

		return aa01;
	}

	/**
	 * 2008-3-11 by lwd
	 * 
	 * @param aaa001
	 * @param aae140
	 * @return
	 * @throws AppException
	 */
	public Vector getParaV(String aaa001, String aae140) throws AppException {
		Connection con = null;
		Vector aaa005 = new Vector();
		Aa01 aa01 = new Aa01();
		aa01.setAaa001(aaa001);
		aa01.setAaa003(null);
		aa01.setAae140(aae140);
		aa01.setFileKey("aa01_select01");
		List list = null;
		try {
			con = DBUtil.getConnection();
			list = (List) find(con, aa01, null, 0);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					aa01 = new Aa01();
					ClassHelper.copyProperties(list.get(i), aa01);
					aaa005.add(aa01);
				}

			}
			// 提交事务
			DBUtil.commit(con);
		} catch (AppException oe) {
			throw new AppException("查询参数时出错！", oe);
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}

		return aaa005;
	}

	/**
	 * 2008-3-11 by lwd
	 * 
	 * @param aaa001
	 * @param aaa003
	 * @param aae140
	 * @param aae030
	 * @param aae031
	 * @return
	 * @throws AppException
	 */
	public String getParadate(String aaa001, String aaa003, String aae140,
			Date aae030, Date aae031) throws AppException {
		Connection con = null;
		String aaa005 = "";
		Aa01 aa01 = new Aa01();
		aa01.setAaa001(aaa001);
		aa01.setAaa003(aaa003);
		aa01.setAae140(aae140);
		aa01.setAae030(aae030);
		aa01.setAae031(aae031);
		aa01.setFileKey("aa01_select02");
		List list = null;
		try {
			con = DBUtil.getConnection();
			list = (List) find(con, aa01, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), aa01);
			}
			// 提交事务
			DBUtil.commit(con);
		} catch (AppException oe) {
			throw new AppException("查询参数时出错！", oe);
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		if (list == null) {
			aaa005 = "";
		} else {
			aaa005 = aa01.getAaa005();
		}

		return aaa005;
	}

	/**
	 * 2008-3-11 by lwd
	 * 
	 * @param aaa001
	 * @param aaa003
	 * @param aae140
	 * @param aae030
	 * @param aae031
	 * @return
	 * @throws AppException
	 */
	public Aa01 getParadateAa01(String aaa001, String aaa003, String aae140,
			Date aae030, Date aae031) throws AppException {
		Connection con = null;

		Aa01 aa01 = new Aa01();
		aa01.setAaa001(aaa001);
		aa01.setAaa003(aaa003);
		aa01.setAae140(aae140);
		aa01.setAae030(aae030);
		aa01.setAae031(aae031);
		aa01.setFileKey("aa01_select02");
		List list = null;
		try {
			con = DBUtil.getConnection();
			list = (List) find(con, aa01, null, 0);
			if (list != null && list.size() > 0) {

				ClassHelper.copyProperties(list.get(0), aa01);
			}
			// 提交事务
			DBUtil.commit(con);
		} catch (AppException oe) {
			throw new AppException("查询参数时出错！", oe);
		} catch (NoConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}

		return aa01;
	}
	
}