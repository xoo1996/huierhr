// Decompiled by Jad v1.5.7f. Copyright 2000 Pavel Kouznetsov.
//Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
//Decompiler options: packimports(3)
//Source File Name: BeanUtils.java

package org.apache.commons.beanutils;

import java.beans.IndexedPropertyDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.FastHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//Referenced classes of package org.apache.commons.beanutils:
//         DynaBean, MappedPropertyDescriptor, PropertyUtils, DynaClass,
//         DynaProperty, ConvertUtils, Converter

public class BeanUtils {

	public BeanUtils() {
	}

	public static int getDebug() {
		return debug;
	}

	public static void setDebug(int newDebug) {
		debug = newDebug;
	}

	public static Object cloneBean(Object bean) throws IllegalAccessException,
			InstantiationException, InvocationTargetException,
			NoSuchMethodException {
		if (log.isDebugEnabled())
			log.debug("Cloning bean: " + bean.getClass().getName());
		Class clazz = bean.getClass();
		Object newBean = clazz.newInstance();
		PropertyUtils.copyProperties(newBean, bean);
		return newBean;
	}

	public static void copyProperties(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException {
		if (dest == null)
			throw new IllegalArgumentException("No destination bean specified");
		if (orig == null)
			throw new IllegalArgumentException("No origin bean specified");
		if (log.isDebugEnabled())
			log.debug("BeanUtils.copyProperties(" + dest + ", " + orig + ")");
		if (orig instanceof DynaBean) {
			DynaProperty origDescriptors[] = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if (PropertyUtils.isWriteable(dest, name)) {
					Object value = ((DynaBean) orig).get(name);
//					if (value != null && value.equals(""))
//						value = null;
					copyProperty(dest, name, value);
				}
			}

		} else if (orig instanceof Map) {
			for (Iterator names = ((Map) orig).keySet().iterator(); names
					.hasNext();) {
				String name = (String) names.next();
				if (PropertyUtils.isWriteable(dest, name)) {
					Object value = ((Map) orig).get(name);
//					if (value != null && value.equals(""))
//						value = null;
					copyProperty(dest, name, value);
				}
			}

		} else {
			PropertyDescriptor origDescriptors[] = PropertyUtils
					.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if (!"class".equals(name)
						&& PropertyUtils.isReadable(orig, name)
						&& PropertyUtils.isWriteable(dest, name))
					try {
						Object value = PropertyUtils.getSimpleProperty(orig,
								name);
//						if (value != null && value.equals(""))
//							value = null;
						copyProperty(dest, name, value);
					} catch (NoSuchMethodException e) {
					}
			}

		}
	}

	public static void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		if (log.isTraceEnabled()) {
			StringBuffer sb = new StringBuffer("  copyProperty(");
			sb.append(bean);
			sb.append(", ");
			sb.append(name);
			sb.append(", ");
			if (value == null)
				sb.append("<NULL>");
			else if (value instanceof String)
				sb.append((String) value);
			else if (value instanceof String[]) {
				String values[] = (String[]) value;
				sb.append('[');
				for (int i = 0; i < values.length; i++) {
					if (i > 0)
						sb.append(',');
					sb.append(values[i]);
				}

				sb.append(']');
			} else {
				sb.append(value.toString());
			}
			sb.append(')');
			log.trace(sb.toString());
		}
		Object target = bean;
		int delim = name.lastIndexOf(46);
		if (delim >= 0) {
			try {
				target = PropertyUtils.getProperty(bean, name.substring(0,
						delim));
			} catch (NoSuchMethodException e) {
				return;
			}
			name = name.substring(delim + 1);
			if (log.isTraceEnabled()) {
				log.trace("    Target bean = " + target);
				log.trace("    Target name = " + name);
			}
		}
		String propName = null;
		Class type = null;
		int index = -1;
		String key = null;
		propName = name;
		int i = propName.indexOf(91);
		if (i >= 0) {
			int k = propName.indexOf(93);
			try {
				index = Integer.parseInt(propName.substring(i + 1, k));
			} catch (NumberFormatException e) {
			}
			propName = propName.substring(0, i);
		}
		int j = propName.indexOf(40);
		if (j >= 0) {
			int k = propName.indexOf(41);
			try {
				key = propName.substring(j + 1, k);
			} catch (IndexOutOfBoundsException e) {
			}
			propName = propName.substring(0, j);
		}
		if (target instanceof DynaBean) {
			DynaClass dynaClass = ((DynaBean) target).getDynaClass();
			DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
			if (dynaProperty == null)
				return;
			type = dynaProperty.getType();
		} else {
			PropertyDescriptor descriptor = null;
			try {
				descriptor = PropertyUtils.getPropertyDescriptor(target, name);
				if (descriptor == null)
					return;
			} catch (NoSuchMethodException e) {
				return;
			}
			type = descriptor.getPropertyType();
			if (type == null) {
				if (log.isTraceEnabled())
					log.trace("    target type for property '" + propName
							+ "' is null, so skipping ths setter");
				return;
			}
		}
		if (value == null)
			if (type.getName().equals("int")) {
				value ="-2147483648";
			} else if (type.getName().equals("double")) {
				value ="-1.0E-10";
			} else if (type.getName().equals("long")) {
				value ="-9223372036854775808L";
			}else if (type.getName().equals("short")) {
				value ="-32768";
			}
        if(type.getName().equals("java.math.BigDecimal")){
            if(value!=null&&value.equals("")) {
                value="-2147483648";
            }
         }
        if(type.getName().equals("java.util.Date")){
            if(value!=null&&value.equals("")) {
                value="1900-01-01";
            }
         }
		if (log.isTraceEnabled())
			log.trace("    target propName=" + propName + ", type=" + type
					+ ", index=" + index + ", key=" + key);
		if (index >= 0) {
			Converter converter = ConvertUtils.lookup(type.getComponentType());
			if (converter != null) {
				log.trace("        USING CONVERTER " + converter);
				value = converter.convert(type, value);
			}
			try {
				PropertyUtils
						.setIndexedProperty(target, propName, index, value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		} else if (key != null) {
			try {
				PropertyUtils.setMappedProperty(target, propName, key, value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		} else {
			//Converter converter1=new Converter();
			if(type.getName().equals("java.util.Date"))
			ConvertUtils.register( new DateConvert(),type); 
			Converter converter = ConvertUtils.lookup(type);
			if (converter != null) {
				log.trace("        USING CONVERTER " + converter);
				if (value != null)
					value = converter.convert(type, value);
			}
			try {
				PropertyUtils.setSimpleProperty(target, propName, value);
			} catch (NoSuchMethodException e) {
				throw new InvocationTargetException(e, "Cannot set " + propName);
			}
		}
	}

	public static Map describe(Object bean) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		if (bean == null)
			return new HashMap();
		if (log.isDebugEnabled())
			log.debug("Describing bean: " + bean.getClass().getName());
		Map description = new HashMap();
		if (bean instanceof DynaBean) {
			DynaProperty descriptors[] = ((DynaBean) bean).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				description.put(name, getProperty(bean, name));
			}

		} else {
			PropertyDescriptor descriptors[] = PropertyUtils
					.getPropertyDescriptors(bean);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (descriptors[i].getReadMethod() != null)
					description.put(name, getProperty(bean, name));
			}

		}
		return description;
	}

	public static String[] getArrayProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object value = PropertyUtils.getProperty(bean, name);
		if (value == null)
			return null;
		if (value instanceof Collection) {
			ArrayList values = new ArrayList();
			for (Iterator items = ((Collection) value).iterator(); items
					.hasNext();) {
				Object item = items.next();
				if (item == null)
					values.add((String) null);
				else
					values.add(item.toString());
			}

			return (String[]) values.toArray(new String[values.size()]);
		}
		if (value.getClass().isArray()) {
			int n = Array.getLength(value);
			String results[] = new String[n];
			for (int i = 0; i < n; i++) {
				Object item = Array.get(value, i);
				if (item == null)
					results[i] = null;
				else
					results[i] = item.toString();
			}

			return results;
		} else {
			String results[] = new String[1];
			results[0] = value.toString();
			return results;
		}
	}

	public static String getIndexedProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object value = PropertyUtils.getIndexedProperty(bean, name);
		return ConvertUtils.convert(value);
	}

	public static String getIndexedProperty(Object bean, String name, int index)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object value = PropertyUtils.getIndexedProperty(bean, name, index);
		return ConvertUtils.convert(value);
	}

	public static String getMappedProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object value = PropertyUtils.getMappedProperty(bean, name);
		return ConvertUtils.convert(value);
	}

	public static String getMappedProperty(Object bean, String name, String key)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object value = PropertyUtils.getMappedProperty(bean, name, key);
		return ConvertUtils.convert(value);
	}

	public static String getNestedProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object value = PropertyUtils.getNestedProperty(bean, name);
		return ConvertUtils.convert(value);
	}

	public static String getProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return getNestedProperty(bean, name);
	}

	public static String getSimpleProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Object value = PropertyUtils.getSimpleProperty(bean, name);
		return ConvertUtils.convert(value);
	}

	public static void populate(Object bean, Map properties)
			throws IllegalAccessException, InvocationTargetException {
		if (bean == null || properties == null)
			return;
		if (log.isDebugEnabled())
			log.debug("BeanUtils.populate(" + bean + ", " + properties + ")");
		for (Iterator names = properties.keySet().iterator(); names.hasNext();) {
			String name = (String) names.next();
			if (name != null) {
				Object value = properties.get(name);
				setProperty(bean, name, value);
			}
		}

	}

	public static void setProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		if (log.isTraceEnabled()) {
			StringBuffer sb = new StringBuffer("  setProperty(");
			sb.append(bean);
			sb.append(", ");
			sb.append(name);
			sb.append(", ");
			if (value == null)
				sb.append("<NULL>");
			else if (value instanceof String)
				sb.append((String) value);
			else if (value instanceof String[]) {
				String values[] = (String[]) value;
				sb.append('[');
				for (int i = 0; i < values.length; i++) {
					if (i > 0)
						sb.append(',');
					sb.append(values[i]);
				}

				sb.append(']');
			} else {
				sb.append(value.toString());
			}
			sb.append(')');
			log.trace(sb.toString());
		}
		Object target = bean;
		int delim = name.lastIndexOf(46);
		if (delim >= 0) {
			try {
				target = PropertyUtils.getProperty(bean, name.substring(0,
						delim));
			} catch (NoSuchMethodException e) {
				return;
			}
			name = name.substring(delim + 1);
			if (log.isTraceEnabled()) {
				log.trace("    Target bean = " + target);
				log.trace("    Target name = " + name);
			}
		}
		String propName = null;
		Class type = null;
		int index = -1;
		String key = null;
		propName = name;
		int i = propName.indexOf(91);
		if (i >= 0) {
			int k = propName.indexOf(93);
			try {
				index = Integer.parseInt(propName.substring(i + 1, k));
			} catch (NumberFormatException e) {
			}
			propName = propName.substring(0, i);
		}
		int j = propName.indexOf(40);
		if (j >= 0) {
			int k = propName.indexOf(41);
			try {
				key = propName.substring(j + 1, k);
			} catch (IndexOutOfBoundsException e) {
			}
			propName = propName.substring(0, j);
		}
		if (target instanceof DynaBean) {
			DynaClass dynaClass = ((DynaBean) target).getDynaClass();
			DynaProperty dynaProperty = dynaClass.getDynaProperty(propName);
			if (dynaProperty == null)
				return;
			type = dynaProperty.getType();
		} else {
			PropertyDescriptor descriptor = null;
			try {
				descriptor = PropertyUtils.getPropertyDescriptor(target, name);
				if (descriptor == null)
					return;
			} catch (NoSuchMethodException e) {
				return;
			}
			if (descriptor instanceof MappedPropertyDescriptor) {
				if (((MappedPropertyDescriptor) descriptor)
						.getMappedWriteMethod() == null) {
					if (log.isDebugEnabled())
						log.debug("Skipping read-only property");
					return;
				}
				type = ((MappedPropertyDescriptor) descriptor)
						.getMappedPropertyType();
			} else if (descriptor instanceof IndexedPropertyDescriptor) {
				if (((IndexedPropertyDescriptor) descriptor)
						.getIndexedWriteMethod() == null) {
					if (log.isDebugEnabled())
						log.debug("Skipping read-only property");
					return;
				}
				type = ((IndexedPropertyDescriptor) descriptor)
						.getIndexedPropertyType();
			} else {
				if (descriptor.getWriteMethod() == null) {
					if (log.isDebugEnabled())
						log.debug("Skipping read-only property");
					return;
				}
				type = descriptor.getPropertyType();
			}
		}
		Object newValue = null;
		if (type.isArray() && index < 0) {
			if (value == null) {
				String values[] = new String[1];
				values[0] = (String) value;
				newValue = ConvertUtils.convert((String[]) values, type);
			} else if (value instanceof String) {
				String values[] = new String[1];
				values[0] = (String) value;
				newValue = ConvertUtils.convert((String[]) values, type);
			} else if (value instanceof String[])
				newValue = ConvertUtils.convert((String[]) value, type);
			else
				newValue = value;
		} else if (type.isArray()) {
			if (value instanceof String)
				newValue = ConvertUtils.convert((String) value, type
						.getComponentType());
			else if (value instanceof String[])
				newValue = ConvertUtils.convert(((String[]) value)[0], type
						.getComponentType());
			else
				newValue = value;
		} else if ((value instanceof String) || value == null)
			newValue = ConvertUtils.convert((String) value, type);
		else if (value instanceof String[])
			newValue = ConvertUtils.convert(((String[]) value)[0], type);
		else if (ConvertUtils.lookup(value.getClass()) != null)
			newValue = ConvertUtils.convert(value.toString(), type);
		else
			newValue = value;
		try {
			if (index >= 0)
				PropertyUtils.setIndexedProperty(target, propName, index,
						newValue);
			else if (key != null)
				PropertyUtils
						.setMappedProperty(target, propName, key, newValue);
			else
				PropertyUtils.setProperty(target, propName, newValue);
		} catch (NoSuchMethodException e) {
			throw new InvocationTargetException(e, "Cannot set " + propName);
		}
	}

	static Class class$(String x0) {
		java.lang.Class a = null;
		try {
			a = Class.forName(x0);
			// ClassNotFoundException x1;
			// x1;
			// throw new NoClassDefFoundError(x1.getMessage());
		} catch (NoClassDefFoundError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	private static FastHashMap dummy = new FastHashMap();

	private static Log log;

	private static int debug = 0;

	static Class class$org$apache$commons$beanutils$BeanUtils; /*
																 * synthetic
																 * field
																 */

	static {
		log = LogFactory
				.getLog(class$org$apache$commons$beanutils$BeanUtils != null ? class$org$apache$commons$beanutils$BeanUtils
						: (class$org$apache$commons$beanutils$BeanUtils = class$("org.apache.commons.beanutils.BeanUtils")));
	}
}