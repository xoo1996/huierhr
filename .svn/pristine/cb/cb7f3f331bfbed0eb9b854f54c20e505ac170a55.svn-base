/*
 * Created on 2004-8-17
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.radf.plat.commons;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.type.Type;
import net.sf.hibernate.util.StringHelper;

import org.apache.commons.beanutils.PropertyUtils;

import org.radf.plat.util.exception.AppException;

/**
 * <p>
 * Title: 劳动力市场信息系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: BJLBS
 * </p>
 * 
 * @author
 * @version 1.0
 */

public class QueryFunction {
	private static String IGNOREWORD = " '(%+"; // 忽略字符

	private static Map Entity = new HashMap();

	/**
	 * 返回查询的hql语句
	 * 
	 * @param String
	 *            hql
	 * @param Object
	 *            dto
	 * @param int i 1-查询语句（撤除语句） 2-增加语句 3-修改语句
	 * @return string
	 * @throws SQLException
	 */
	public static String getHQL(String hql, Object dto) throws SQLException {
		// 判断当前sql语句是否要重新组装
		int index = -1;
		index = hql.indexOf(":", index);
		// 如果不存在：直间返回当前sql语句
		if (index < 0) {
			return hql;
		}
		String para[] = getParaNames(hql);
		Type types[] = getTypes(dto, para);
		String hql1 = "";
		try {
			hql = hql.replaceAll("!", "");
			hql1 = QueryFactory.getHQL(hql.trim(), para, types, dto);
		} catch (Exception e) {
			throw new SQLException("生成语句时出错！");
		}
		return hql1;
	}

	/**
	 * 根据HQL语句信息，生成HQL中存在的参数信息。并与书写顺序一致
	 * 
	 * @param hql
	 * @return
	 */
	public static String[] getParaNames(String hql) {
		String prop = "";
		int index = -1;
		int endindex = -1;

		ArrayList al = new ArrayList();

		index = hql.indexOf(":", index);
		endindex = hql.indexOf("!", endindex);

		while (index > 0) {
			index = index + 1;
			endindex = endindex + 1;
			// 忽略尾随":"号的定义的忽略字符
			while (IGNOREWORD.indexOf(hql.substring(index, index + 1)) >= 0) {
				index++;
			}
			if (endindex > 0) {
				prop = hql.substring(index, endindex - 1);
			} else {
				prop = hql.substring(index, index + 6);
			}
			al.add(prop);
			endindex = hql.indexOf("!", endindex);

			index = hql.indexOf(":", index);
		}

		String paraNames[] = new String[al.size()];
		al.toArray(paraNames);

		return paraNames;
	}

	/**
	 * 书写参数的参数类型
	 * 
	 * @return Type[]
	 */
	private static Type getType(Class obj) {
		Type type = Hibernate.STRING;

		if (obj.equals(Date.class) || obj.equals(java.util.Date.class)) {
			type = Hibernate.DATE;
		} else if (obj.equals(String.class)) {
			type = Hibernate.STRING;
		} else if (obj.equals(Double.class)) {
			type = Hibernate.DOUBLE;
		} else if (obj.equals(Integer.class)) {
			type = Hibernate.INTEGER;
		} else if (obj.equals(BigDecimal.class)) {
			type = Hibernate.BIG_DECIMAL;
		} else if (obj.equals(Short.class)) {
			type = Hibernate.SHORT;
		} else {
			type = Hibernate.STRING;
		}

		return type;
	}

	/**
	 * 书写参数的参数类型
	 * 
	 * @param Object
	 *            entity
	 * @param String
	 *            [] props
	 * @return Type[]
	 * @throws SQLException
	 */
	public static Type[] getTypes(Object entity, String paras[])
			throws SQLException {
		if (paras == null || paras.length < 1)
			return null;

		ArrayList al = new ArrayList();

		for (int i = 0; i < paras.length; i++) {
			Class temp = null;
			try {
				temp = PropertyUtils.getPropertyType(entity, paras[i]);
			} catch (IllegalAccessException iae) {
				throw new SQLException("非法访问出错!");
			} catch (InvocationTargetException ite) {
				throw new SQLException("引用目标出错!");
			} catch (NoSuchMethodException nsme) {
				throw new SQLException("没有指定的方法出错!");
			}
			if (temp == null)
				throw new SQLException(":传入sql语句中的字段与对象类不符合");
			al.add(getType(temp));
		}

		Type[] types = new Type[al.size()];
		al.toArray(types);

		return types;
	}

	/**
	 * 获取HQL语句中select语句部分包含的所有实体
	 * 
	 * @param HQL
	 * @return
	 * @author 饶福华
	 */
	public static String getSelectEntitys(String HQL) {
		String sep = ",";
		StringBuffer rtn = new StringBuffer("");
		String hqlSelect = "";

		hqlSelect = getSelectHQL(HQL);

		if (NullFlag.isObjNull(hqlSelect)) {
			return hqlSelect;
		}

		String[] selects = hqlSelect.split(sep);
		for (int i = 0; i < selects.length; i++) {
			String select = selects[i];
			if (select.indexOf(StringHelper.DOT) < 0) {
				rtn.append(",").append(select);
			}
		}

		return rtn.substring(1);
	}

	/**
	 * 获取HQL语句中select语句部分包含的所有实体
	 * 
	 * @param HQL
	 * @return
	 * @throws AppException
	 * @author 饶福华
	 */
	public static String getFullSelect(String HQL) throws AppException {
		String sep = ",";
		StringBuffer hqlSelect = new StringBuffer("");
		String tmpSelect = "";

		tmpSelect = getSelectHQL(HQL);

		if (NullFlag.isObjNull(tmpSelect)) {
			return hqlSelect.toString();
		}

		String[] selects = tmpSelect.split(sep);
		for (int i = 0; i < selects.length; i++) {
			String select = selects[i];
			if (select.indexOf(StringHelper.DOT) < 0) {
				String left = "";
				String right = "";
				int indexBlank = 0;
				int indexSKh = 0;
				int indexEKh = 0;
				int max = 0;
				indexBlank = select.lastIndexOf(" ");
				indexSKh = select.lastIndexOf("(");
				if (indexBlank > indexSKh) {
					max = indexBlank;
				} else {
					max = indexSKh;
				}
				if (indexSKh > 0) {
					indexEKh = select.indexOf(")");
				}
				if (max > 0) {
					left = select.substring(0, max);
					if (indexEKh > 0) {
						right = select.substring(indexEKh);
						select = select.substring(max + 1, indexEKh).trim();
					} else {
						select = select.substring(max + 1).trim();
					}
				}
				hqlSelect.append(sep).append(left).append(
						getEntitySelect(TypeCast.objToString(Entity.get(select
								.trim())), select.trim())).append(right);
			} else {
				hqlSelect.append(sep).append(select);
			}
		}

		return hqlSelect.substring(1);
	}

	/**
	 * 读取实体的完整路径
	 * 
	 * @param entity
	 * @return
	 * @throws AppException
	 * @author 饶福华
	 */
	public static String getFullEntity(String entity) throws AppException {
		// Configuration conf = new Configuration();
		// String ent = "";
		// // try {
		// // SessionFactory sf =conf.configure().buildSessionFactory();
		// ent =
		// TypeCast.objToString(HibernateSession.getConfiguration().getImports().get(entity));
		// // } catch (HibernateException he) {
		// // throw new AppException("Hibernate配置时出错！",he);
		// // }
		//	    
		return null;
	}

	/**
	 * 获取实体的所有属性，以HQL中Select部分语句书写一致
	 * 
	 * @param entity
	 * @param aliasName
	 * @return
	 * @throws AppException
	 * @author 饶福华
	 */
	public static String getEntitySelect(String entity, String aliasName)
			throws AppException {
		Object obj;
		StringBuffer result = new StringBuffer("");

		try {
			obj = Class.forName(getFullEntity(entity)).newInstance();
			PropertyDescriptor[] pd = PropertyUtils.getPropertyDescriptors(obj);
			for (int i = 0; i < pd.length; i++) {
				if (!pd[i].getName().equals("class"))
					result.append(",").append(aliasName).append(
							StringHelper.DOT).append(pd[i].getName());
			}
		} catch (InstantiationException ie) {
			throw new AppException("实例化实体时出错！");
		} catch (IllegalAccessException iae) {
			throw new AppException("不合法的访问！");
		} catch (ClassNotFoundException cnfe) {
			throw new AppException("不存在的类型！");
		} catch (Exception ae) {

		}

		return result.substring(1);
	}

	/**
	 * 得到某属性在HQL查询语句中的属性索引
	 * 
	 * @param String
	 *            hql // 用于查询的HQL语句
	 * @param String
	 *            property // 需要索引的属性名
	 * @return int // 查询中第几个属性，如果没有则返回-1
	 * @author 饶福华
	 */
	public static int getIndex(String hql, String property) {
		String sep = ",";
		int rtn = -1;
		String hqlSelect = hql.toLowerCase();
		String selectBefore = "";

		if (NullFlag.isObjNull(hql)) {
			return rtn;
		}
		if (NullFlag.isObjNull(property)) {
			return rtn;
		}
		if (hqlSelect.indexOf("from") > 0) {
			hqlSelect = hqlSelect.substring(0, hqlSelect.indexOf("from"));
		} else {
			hqlSelect = "";
		}

		if (hqlSelect.indexOf(property.toLowerCase()) >= 0) {
			selectBefore = hqlSelect.substring(0, hqlSelect.indexOf(property
					.toLowerCase()) + 1);
			String[] seleBef = selectBefore.split(sep);
			rtn = seleBef.length - 1;
		}

		return rtn;
	}

	/**
	 * 获取HQL语句的Select部分的语句
	 * 
	 * @param hql
	 * @return
	 * @author 饶福华
	 */
	public static String getSelectHQL(String hql) {
		String hqlSelect = hql;
		int select = 0;
		int from = 0;

		if (NullFlag.isObjNull(hql)) {
			return null;
		}

		select = hqlSelect.toLowerCase().indexOf("select");
		from = hqlSelect.toLowerCase().indexOf("from");
		if (select < 0) {
			hqlSelect = getFromHQL(hql);
			String sep = " ";
			StringBuffer sb = new StringBuffer("");
			String[] entity = hqlSelect.split(",");
			for (int i = 0; i < entity.length; i++) {
				String[] alias = entity[i].split(sep);
				sb.append(",").append(alias[alias.length - 1]);
			}
			hqlSelect = sb.substring(1);
		} else {
			if (from > 0) {
				if (select < from) {
					hqlSelect = hqlSelect.substring(select + 6, from).trim();
				} else {
					hqlSelect = hqlSelect.substring(0, from).trim();
				}
			} else {
				return "";
			}
			getFromHQL(hql);
		}

		return hqlSelect;
	}

	/**
	 * 获取HQL语句的Select部分的语句
	 * 
	 * @param hql
	 * @return
	 * @author 饶福华
	 */
	public static String getFromHQL(String hql) {
		String hqlFrom = hql;
		int from = 0;
		int where = 0;

		if (NullFlag.isObjNull(hql)) {
			return null;
		}

		from = hqlFrom.toLowerCase().indexOf("from");
		where = hqlFrom.toLowerCase().indexOf("where");
		if (where > 0) {
			if (from < where) {
				hqlFrom = hqlFrom.substring(from + 4, where).trim();
			} else {
				hqlFrom = hqlFrom.substring(0, where).trim();
			}
		} else {
			if (from >= 0)
				hqlFrom = hqlFrom.substring(from + 4).trim();
		}

		if (hqlFrom.length() > 0) {
			setEntityMap(hqlFrom);
		}

		return hqlFrom;
	}

	/**
	 * 将所有的实体和与实体对应的别名保存到静态变量中
	 * 
	 * @param entitys
	 * @author 饶福华
	 */
	private static void setEntityMap(String entitys) {
		String sep = " ";
		String[] entity = entitys.split(",");
		for (int i = 0; i < entity.length; i++) {
			String[] alias = entity[i].split(sep);
			Entity.put(alias[alias.length - 1], alias[0]);
		}
	}

	/**
	 * 将HQL语句中的实体转换成Select实体的样式， 如From Employer ab01-->select
	 * ab01.aab001,ab01.aab002 ...... from Employer ab01
	 * 
	 * @param hql
	 * @return
	 * @throws AppException
	 * @author 饶福华
	 */
	public static String getFullHQL(String hql) throws AppException {
		String rtn = "";
		String afterFrom = "";
		String select = "";
		int indexFrom = 0;

		indexFrom = hql.toLowerCase().indexOf("from");
		if (indexFrom < 0) {
			throw new AppException("HQL语句不完整，不包含from子句");
		} else {
			afterFrom = hql.substring(indexFrom);
		}
		select = getFullSelect(hql);

		return "select " + select + " " + afterFrom;
	}

	private static String getEntity(String ent) {
		String entity = ent;
		String left = "";
		String right = "";
		int indexBlank = 0;
		int indexSKh = 0;
		int indexEKh = 0;
		int max = 0;
		indexBlank = entity.lastIndexOf(" ");
		indexSKh = entity.lastIndexOf("(");
		if (indexBlank > indexSKh) {
			max = indexBlank;
		} else {
			max = indexSKh;
		}
		if (indexSKh > 0) {
			indexEKh = entity.indexOf(")");
		}
		if (max > 0) {
			left = entity.substring(0, max);
			if (indexEKh > 0) {
				right = entity.substring(indexEKh);
				entity = entity.substring(max + 1, indexEKh).trim();
			} else {
				entity = entity.substring(max + 1).trim();
			}
		}
		return entity;
	}
}