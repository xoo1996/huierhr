package org.radf.plat.commons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;

public class ClassHelper {
	private static Log a;

	public ClassHelper() {
        
	}

	public static Object getClassInstance(String s) {
		try {
			return Thread.currentThread().getContextClassLoader().loadClass(s).newInstance();
		} catch (Exception exception) {
		}
		try {
			return Class.forName(s).newInstance();
		} catch (ClassNotFoundException classnotfoundexception) {
			a.error(classnotfoundexception.toString());
			return null;
		} catch (IllegalAccessException illegalaccessexception) {
			a.error(illegalaccessexception.toString());
			return null;
		} catch (InstantiationException instantiationexception) {
			a.error(instantiationexception.toString());
		}
		return null;
	}

	public static void copyProperties(Object obj, Object obj1) {
		try {
			BeanUtils.copyProperties(obj1, obj);
		} catch (IllegalAccessException illegalaccessexception) {
			a.error(illegalaccessexception.toString());
		} catch (InvocationTargetException invocationtargetexception) {
			a.error(invocationtargetexception.toString());
		}
	}

	public static void setProperty(Object bean, String name, Object value) {
		try {
			BeanUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException illegalaccessexception) {
			a.error(illegalaccessexception.toString());
		} catch (InvocationTargetException invocationtargetexception) {
			a.error(invocationtargetexception.toString());
		}
	}

	

	public static String getProperty(Object bean, String name) {

		String Property = "";
		try {
			Property = BeanUtils.getProperty(bean, name);
		} catch (IllegalAccessException illegalaccessexception) {
			a.error(illegalaccessexception.toString());
		} catch (InvocationTargetException invocationtargetexception) {
			a.error(invocationtargetexception.toString());

		} catch (NoSuchMethodException nosuchmethodexception) {
			a.error(nosuchmethodexception.toString());
		}
		return Property;
	}

	public static synchronized String getProperties(Object obj) {
		StringBuffer stringbuffer = new StringBuffer(512);
		int i = 0;
		Method amethod[] = obj.getClass().getMethods();
		Object obj1 = null;
		Object obj2 = null;
		Object obj3 = null;
		Object obj4 = null;
		try {
			for (int j = 0; j < amethod.length; j++) {
				String s2 = amethod[j].getName().substring(0, 3);
				String s3 = amethod[j].getName().substring(3);
				if ("get".equalsIgnoreCase(s2)
						&& !"class".equalsIgnoreCase(s3)
						&& !"SERVLETWRAPPER".equalsIgnoreCase(s3.toUpperCase())
						&& !"MULTIPARTREQUESTHANDLER".equalsIgnoreCase(s3
								.toUpperCase())) {
					Object obj5 = amethod[j].invoke(obj, null);
					String s1;
					if (obj5 != null)
						s1 = obj5.toString();
					else
						s1 = "null";
					String s = a(obj5, s1);
					if (i == 0) {
						stringbuffer.append(s3.toUpperCase() + "=" + s);
						i++;
					} else {
						stringbuffer.append("," + s3.toUpperCase() + "=" + s);
					}
				}
			}

		} catch (InvocationTargetException invocationtargetexception) {
			a.error(invocationtargetexception.toString());
		} catch (IllegalAccessException illegalaccessexception) {
			a.error(illegalaccessexception.toString());
		} catch (IllegalArgumentException illegalargumentexception) {
			a.error(illegalargumentexception.toString());
		}
		return stringbuffer.toString();
	}

	private static String a(Object aobj[]) {
		StringBuffer stringbuffer = new StringBuffer(512);
		Object obj = null;
		for (int i = 0; i < aobj.length; i++) {
			String s1 = aobj[i].toString();
			String s = a(aobj[i], s1);
			if (i == 0)
				stringbuffer.append("[" + i + "]=" + s);
			else
				stringbuffer.append(",[" + i + "]=" + s);
		}

		return stringbuffer.toString();
	}

	private static String a(Map map) {
		return a(map.values().iterator());
	}

	private static String a(Iterator iterator) {
		StringBuffer stringbuffer = new StringBuffer(512);
		Object obj = null;
		Object obj1 = null;
		int i = 0;
		for (Iterator iterator1 = iterator; iterator1.hasNext();) {
			Object obj2 = iterator1.next();
			String s1 = obj2.toString();
			String s = a(obj2, s1);
			if (i == 0) {
				stringbuffer.append(s);
				i++;
			} else {
				stringbuffer.append("," + s);
			}
		}

		return stringbuffer.toString();
	}

	private static String a(Object obj, String s) {
		if (s.indexOf(".") != -1 && s.indexOf("@") != -1 && !s.startsWith("[")
				&& !s.startsWith("{"))
			s = "<" + getProperties(obj) + ">";
		if (s.startsWith("[") && s.endsWith("]")) {
			Collection collection = (Collection) obj;
			s = "<coll: " + a(collection.iterator()) + " coll>";
		}
		if (s.startsWith("{") && s.endsWith("}")) {
			Map map = (Map) obj;
			s = "<map: " + a(map) + " map>";
		}
		if (s.startsWith("[") && !s.endsWith("]")) {
			Object aobj[] = (Object[]) obj;
			s = "<arr: " + a(aobj) + " arr>";
		}
		return s;
	}

}
