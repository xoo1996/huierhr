// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.plugin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DateConvert;
import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

import org.radf.plat.commons.DBUtil;
import org.radf.plat.taglib.xtree.CYLBFactory;
import org.radf.plat.taglib.xtree.RegionFactory;
import org.radf.plat.taglib.xtree.SSJDRegionFactory;
import org.radf.plat.taglib.xtree.SSJDRegionQueryFactory;
import org.radf.plat.taglib.xtree.WorkTypeFactory;
import org.radf.plat.taglib.xtree.WorkTypeQueryFactory;

public class CodeListPlugIn implements PlugIn {

	private static Log _fldif;

	private ModuleConfig a;

	private ActionServlet _flddo;

	protected Collection resources;

	public CodeListPlugIn() {
		a = null;
		_flddo = null;
		resources = null;
	}

	public void init(ActionServlet actionservlet, ModuleConfig moduleconfig)
			throws ServletException {
		a = moduleconfig;
		_flddo = actionservlet;
		ServletContext servletcontext = actionservlet.getServletContext();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			// 选择代码
			initAa10(con, servletcontext);
			// 工作类型
			initCa11(con, servletcontext);
			// 地区代码
			initAa12(con, servletcontext);
			// 产业类别
			initAa15(con, servletcontext);
			//机构
			initSc01(con, servletcontext);
			//机构
			initSc05(con, servletcontext);
            //科室
			initSc04(con, servletcontext);
            //虚拟机构
			initSc03(con, servletcontext);
			a(servletcontext);
			ConvertUtils.register(new DateConvert(), Date.class);
		} catch (Exception exception) {
			_fldif.error("=============## CodeList 加载错误！");
			exception.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}

		TreeMap treemap3 = new TreeMap();
		treemap3.put("0", "否");
		treemap3.put("1", "是");
		TreeMap treemap4 = new TreeMap();
		treemap4.put("0", "叶子");
		treemap4.put("1", "节点");
		treemap4.put("2", "按钮");
		servletcontext.setAttribute("LOG", treemap3);
		servletcontext.setAttribute("TYPE", treemap4);
		servletcontext.setAttribute("ISLEADER", treemap3);
	}

	public void destroy() {
		if (_fldif.isDebugEnabled())
			_fldif.debug("Destroying CodeListPlugin");
		_flddo = null;
		a = null;
	}

	/*
	 * 初始化
	 */
	private static void initAa10(Connection con, ServletContext servletcontext)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sSql = "SELECT AAA100,AAA102,AAA103 FROM AA10 where 1=1 order by aaa100,aaa102";
			rs = stmt.executeQuery(sSql);
			while (rs.next()) {
				String s = rs.getString("AAA100");
				if (servletcontext.getAttribute(s) == null) {
					TreeMap treemap5 = new TreeMap();
					treemap5
							.put(rs.getString("AAA102"), rs.getString("AAA103"));
					servletcontext.setAttribute(s, treemap5);
				} else {
					TreeMap treemap6 = (TreeMap) servletcontext.getAttribute(s);
					treemap6
							.put(rs.getString("AAA102"), rs.getString("AAA103"));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}

	/*
	 * 初始化
	 */
	private static void initAa12(Connection con, ServletContext servletcontext)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {

			stmt = con.createStatement();
			String sSql = "select aaa020,aaa021 from AA12 order by aaa020";
			rs = stmt.executeQuery(sSql);
			TreeMap treemap = new TreeMap();
			while (rs.next()) {
				treemap.put(rs.getString("aaa020"), rs.getString("aaa021"));
			}
			servletcontext.setAttribute("AAB301", treemap);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}

	/*
	 * 初始化
	 */
	private static void initCa11(Connection con, ServletContext servletcontext)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {

			stmt = con.createStatement();
			String sSql = "select aca111,aca112 from CA11 order by  aca111";
			rs = stmt.executeQuery(sSql);
			TreeMap treemap = new TreeMap();
			while (rs.next()) {
				treemap.put(rs.getString("aca111"), rs.getString("aca112"));
			}
			servletcontext.setAttribute("ACA111", treemap);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}

	/*
	 * 初始化
	 */
	private static void initAa15(Connection con, ServletContext servletcontext)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sSql = "select bab055,aab054 from AA15 order by  BAB055";
			rs = stmt.executeQuery(sSql);
			TreeMap treemap = new TreeMap();
			while (rs.next()) {
				treemap.put(rs.getString("bab055"), rs.getString("aab054"));
			}
			servletcontext.setAttribute("AAB054", treemap);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}
	/*
	 * 初始化
	 */
	private static void initSc01(Connection con, ServletContext servletcontext)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sSql = "select bsc001,aab300 from sc01 order by  bsc001";
			rs = stmt.executeQuery(sSql);
			TreeMap treemap = new TreeMap();
			while (rs.next()) {
				treemap.put(rs.getString("bsc001"), rs.getString("aab300"));
			}
			servletcontext.setAttribute("AAE017", treemap);
			servletcontext.setAttribute("CCE001", treemap);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}
	/*
	 * 初始化
	 */
	private static void initSc05(Connection con, ServletContext servletcontext)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sSql = "select bsc010,bsc012 from sc05 order by  bsc010";
			rs = stmt.executeQuery(sSql);
			TreeMap treemap = new TreeMap();
			while (rs.next()) {
				treemap.put(rs.getString("bsc010"), rs.getString("bsc012"));
			}
			servletcontext.setAttribute("AAE011", treemap);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}
	/*
	 * 初始化
	 */
	private static void initSc04(Connection con, ServletContext servletcontext)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sSql = "select BSC008,BSC009 from sc04 order by  BSC008";
			rs = stmt.executeQuery(sSql);
			TreeMap treemap = new TreeMap();
			while (rs.next()) {
				treemap.put(rs.getString("BSC008"), rs.getString("BSC009"));
			}
			servletcontext.setAttribute("AAE019", treemap);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}
	/*
	 * 初始化
	 */
	private static void initSc03(Connection con, ServletContext servletcontext)
			throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sSql = "select BSC006,BSC007 from sc03 order by  BSC006";
			rs = stmt.executeQuery(sSql);
			TreeMap treemap = new TreeMap();
			while (rs.next()) {
				treemap.put(rs.getString("BSC007"), rs.getString("BSC006"));
			}
			servletcontext.setAttribute("BSC006", treemap);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeResStat(rs, stmt);
		}
	}
	private void a(ServletContext servletcontext) {
		// _fldif.info("加载RegionFactory");
		RegionFactory regionfactory = new RegionFactory();
		servletcontext.setAttribute("RegionFactory", regionfactory);
		// _fldif.info("加载WorkTypeFactory");
		// by 李灵超 2006年4月26日
		SSJDRegionFactory ssjdregionfactory = new SSJDRegionFactory();
		servletcontext.setAttribute("SSJDRegionFactory", ssjdregionfactory);
		
		SSJDRegionQueryFactory ssjdregionqueryfactory = new SSJDRegionQueryFactory();
		servletcontext.setAttribute("SSJDRegionQueryFactory", ssjdregionqueryfactory);
		
		WorkTypeFactory worktypefactory = new WorkTypeFactory();
		servletcontext.setAttribute("WorkTypeFactory", worktypefactory);

		// 叶鹏 2007。1。8
		WorkTypeQueryFactory worktypequeryfactory = new WorkTypeQueryFactory();
		servletcontext.setAttribute("WorkTypeQueryFactory",
				worktypequeryfactory);
		// 林科 20070201
		CYLBFactory cylbfactory = new CYLBFactory();
		servletcontext.setAttribute("CYLBFactory", cylbfactory);

	}

}
