// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi

package org.radf.plat.commons;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import net.sf.hibernate.type.Type;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.FastArrayList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.commons:
// StringHelper, DateUtil, GlobalNames

public class QueryFactory {

	private Log a;

	static String baseExp = "\\s*((like)|=|(>)|(<)|(>=)|(<=)|(!=)|(<>))\\s*:";

	static String baseAnd = "\\s*(and)?\\s*";// and替换

	static String baseDot = "\\s*(,)?\\s*";// 逗号替换

	public QueryFactory() {
		a = LogFactory.getLog(org.radf.plat.commons.QueryFactory.class);
	}

	private static String a(String s, Object obj, String as[]) throws Exception {
		String s1 = s;
		String base = baseAnd;
		String sqlto = s1.substring(0, 6);
		boolean update = sqlto.toUpperCase().equals("UPDATE");
		if (update) {
			base = baseDot;
		}
		if (as == null)
			return s;
		for (int i = 0; i < as.length; i++) {

			String param = as[i];
			String s3 = "[(]?([a-zA-Z]+[a-zA-Z0-9]*)[.]?"
					+ "([a-zA-Z]+[a-zA-Z0-9]*)" + baseExp + param + "[)]?"
					+ base;
			try {
				if (update) {
					if (BeanUtils.getProperty(obj, param) == null)
						s1 = StringUtil.regexReplace(s3, "", s1);
				} else {
					if (BeanUtils.getProperty(obj, param) == null
							|| "".equals(BeanUtils.getProperty(obj, param))) {// double)
						s1 = StringUtil.regexReplace(s3, "", s1);
					} else {
						if ("-2147483648".equals(BeanUtils.getProperty(obj,
								param))
								|| "1900-01-01".equals(BeanUtils.getProperty(
										obj, param))
								|| "-1.0E-10".equals(BeanUtils.getProperty(obj,
										param))// double型
								|| "-9223372036854775808L".equals(BeanUtils
										.getProperty(obj, param))// long型
								|| "-32768".equals(BeanUtils.getProperty(obj,
										param))) {
							s1 = StringUtil.regexReplace(s3, "", s1);
						}
					}
				}

			} catch (NoSuchMethodException nosuchmethodexception) {
				nosuchmethodexception.printStackTrace();
				throw new Exception(nosuchmethodexception);
			} catch (InvocationTargetException invocationtargetexception) {
				invocationtargetexception.printStackTrace();
				throw new Exception(invocationtargetexception);
			} catch (IllegalAccessException illegalaccessexception) {
				illegalaccessexception.printStackTrace();
				throw new Exception(illegalaccessexception);
			}
		}

		boolean flag = true;
		if (update) {
			flag = true;
			String s5 = s1.substring(0, s1.toUpperCase().indexOf("WHERE"));
			String s6 = s1.substring(s1.toUpperCase().indexOf("WHERE"));
			while (flag)
				if (s5.trim().endsWith(","))
					s5 = s5.substring(0, s5.lastIndexOf(","));
				else
					flag = false;
			s1 = s5 + " " + s6;
		}

		flag = true;
		while (flag)

			if (s1.trim().endsWith("and"))
				s1 = s1.substring(0, s1.lastIndexOf("and"));
			else
				flag = false;
		flag = true;
		while (flag)
			if (s1.trim().endsWith("where"))
				s1 = s1.substring(0, s1.lastIndexOf("where"));
			else
				flag = false;
		flag = true;
		while (flag)
			if (s1.trim().endsWith("or"))
				s1 = s1.substring(0, s1.lastIndexOf("or"));
			else
				flag = false;
		flag = true;
		while (flag)
			if (s1.trim().endsWith("having"))
				s1 = s1.substring(0, s1.lastIndexOf("having"));
			else
				flag = false;
		return s1;
	}

	private static String[] a(Object obj, String as[], String type)
			throws Exception {
		FastArrayList fastarraylist = new FastArrayList();
		if (as == null)
			return null;
		for (int i = 0; i < as.length; i++) {
			String s = as[i];
			try {
				if (type.toUpperCase().equals("UPDATE")) {
					if (BeanUtils.getProperty(obj, s) != null) {

						fastarraylist.add(s);
					}
				} else {
					if (BeanUtils.getProperty(obj, s) != null
							&& !"".equals(BeanUtils.getProperty(obj, s)))
						if (type.toUpperCase().equals("SELECT")) {
							if (!"-2147483648".equals(BeanUtils.getProperty(
									obj, s))
									&& !"1900-01-01".equals(BeanUtils
											.getProperty(obj, s))) {
								fastarraylist.add(s);
							}
						} else {
							fastarraylist.add(s);
						}

					//								
					// &&!"-2147483648".equals(BeanUtils.getProperty(obj, s))//
					// int型
					// && "-1.0E-10".equals(BeanUtils.getProperty(obj, s))//
					// double型
					// &&
					// "-9223372036854775808L".equals(BeanUtils.getProperty(obj,
					// s))// long型
					// && "-32768".equals(BeanUtils.getProperty(obj,
					// s))&&!"1900-01-01".equals(BeanUtils.getProperty(obj, s))

				}

			} catch (NoSuchMethodException nosuchmethodexception) {
				nosuchmethodexception.printStackTrace();
				throw new Exception(nosuchmethodexception);
			} catch (InvocationTargetException invocationtargetexception) {
				invocationtargetexception.printStackTrace();
				throw new Exception(invocationtargetexception);
			} catch (IllegalAccessException illegalaccessexception) {
				illegalaccessexception.printStackTrace();
				throw new Exception(illegalaccessexception);
			}
		}

		String as1[] = new String[fastarraylist.size()];
		fastarraylist.toArray(as1);
		return as1;
	}

	private static String[] a(String s, Object obj, String as[], Type atype[])
			throws Exception {
		FastArrayList fastarraylist = new FastArrayList();
		if (as == null)
			return null;
		for (int i = 0; i < as.length; i++) {
			String s1 = as[i];
			Type type = atype[i];
			try {
				Object obj1 = PropertyUtils.getProperty(obj, s1);
				if (obj1 == null) {
					obj1 = "";
				}
				if (type.getName().equals("string")) {

					StringBuffer stringbuffer = new StringBuffer((String) obj1);

					String s2 = "like\\s*:" + s1 + "(\\s*[+]+\\s*['%']+)+";
					String s3 = "like\\s*(['%']+\\s*[+]+\\s*)+:" + s1;
					String s4 = "like\\s*:" + s1;
					if (StringUtil.exist(s2, s)) {
						stringbuffer.insert(0, "'").append("%'");
						fastarraylist.add(stringbuffer.toString());
					} else if (StringUtil.exist(s3, s)) {
						stringbuffer.insert(0, "'%").append("'");
						fastarraylist.add(stringbuffer.toString());
					} else if (StringUtil.exist(s4, s)) {
						stringbuffer.insert(0, "'%").append("%'");
						fastarraylist.add(stringbuffer.toString());
					} else {
						stringbuffer.insert(0, "'").append("'");
						fastarraylist.add(stringbuffer.toString());
					}
				} else if (type.getName().equals("date")
						|| type.getName().equals("java.util.Date")) {
					if (obj1 != "") {

						if (String.valueOf("1900-01-01")
								.equals(obj1.toString())) {
							fastarraylist.add(i, "''");
						} else {
							StringBuffer stringbuffer1 = new StringBuffer(
									"to_date('");
							stringbuffer1.append(DateUtil
									.converToString(new Date(
											((java.util.Date) obj1).getTime()),
											"yyyy-MM-dd HH24:mi:ss"));
							stringbuffer1.append("','yyyy-MM-dd HH24:mi:ss')");
							fastarraylist.add(i, stringbuffer1.toString());
						}
					} else {
						fastarraylist.add(i, "''");
					}
				} else {
					if ("-2147483648".equals(obj1.toString())// int型
							|| "-1.0E-10".equals(obj1.toString())// double型
							|| "-9223372036854775808L".equals(obj1.toString())// long型
							|| "-32768".equals(obj1.toString())) {// short型
						fastarraylist.add("''");
					} else {
						fastarraylist.add("'" + obj1.toString() + "'");
					}
				}
			} catch (NoSuchMethodException nosuchmethodexception) {
				nosuchmethodexception.printStackTrace();
				throw new Exception(nosuchmethodexception);
			} catch (InvocationTargetException invocationtargetexception) {
				invocationtargetexception.printStackTrace();
				throw new Exception(invocationtargetexception);
			} catch (IllegalAccessException illegalaccessexception) {
				illegalaccessexception.printStackTrace();
				throw new Exception(illegalaccessexception);
			}
		}

		String as1[] = new String[fastarraylist.size()];
		fastarraylist.toArray(as1);
		return as1;
	}

	private static Type[] a(String as[], String as1[], Type atype[])
			throws Exception {
		FastArrayList fastarraylist = new FastArrayList();
		if (as1 == null)
			return null;
		for (int i = 0; i < as.length; i++) {
			String s = as[i];
			for (int j = 0; j < as1.length; j++) {
				if (!s.equals(as1[j]))
					continue;
				fastarraylist.add(atype[j]);
				break;
			}

		}

		Type atype1[] = new Type[fastarraylist.size()];
		fastarraylist.toArray(atype1);
		return atype1;
	}

	private static String a(String s, String as[], String as1[])
			throws Exception {
		Object obj = null;
		Object obj1 = null;
		String s3 = s;
		String s4 = "(['%']+\\s*[+]+\\s*)?:";
		String s5 = "(\\s*[+]+\\s*['%']+)?";
		for (int i = 0; i < as.length; i++) {
			String s1 = as[i];
			String s2 = as1[i];
			try {
				s3 = StringUtil.regexReplace(s4 + s1 + s5, s2, s3);
			} catch (Exception exception) {
				exception.printStackTrace();
				throw new Exception(exception);
			}
		}

		return s3;
	}

	public static synchronized String getHQL(String s, String as[],
			Type atype[], Object obj) throws Exception {
		if (as.length != atype.length)
			throw new Exception("参数名称数组的长度与参数类型数组的长度不匹配！");
		s = StringUtil.dealOrderBy(s);
		String s1 = "";
		if (s.indexOf(GlobalNames.ORDER_BY) > -1) {
			s1 = s.substring(s.lastIndexOf(GlobalNames.ORDER_BY));
			s = s.substring(0, s.lastIndexOf(GlobalNames.ORDER_BY));
		}
		String sqlto = s.substring(0, 6);
		String s2 = s;
		String as1[] = as;
		Type atype1[] = atype;
		if (!sqlto.toUpperCase().equals("INSERT")
				&& !sqlto.toUpperCase().equals("DELETE")) {

			as1 = a(obj, as, sqlto);
			atype1 = a(as1, as, atype);

			s2 = a(s, obj, as);
		}

		String as2[] = a(s2, obj, as1, atype1);
		String s3 = a(s2, as1, as2);

		return s3 + s1;
	}

}
