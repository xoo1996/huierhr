package org.radf.plat.commons;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.FastHashMap;
import org.apache.commons.logging.Log;

public class Convert {

	private static FastHashMap _fldif;
	private static Log a;
	
    static Class class$0; /* synthetic field */
    static Class class$1; /* synthetic field */
    static Class class$2; /* synthetic field */
    static Class class$3; /* synthetic field */
    static Class class$4; /* synthetic field */
    static Class class$5; /* synthetic field */
    static Class class$6; /* synthetic field */
    static Class class$7; /* synthetic field */
    static Class class$8; /* synthetic field */
    static Class class$9; /* synthetic field */
    static Class class$10; /* synthetic field */
    static Class class$11; /* synthetic field */
    static Class class$12; /* synthetic field */

    public Convert()
    {
    }

    public static synchronized Collection getCollection(SubmitDataMap submitdatamap, Class class1)
        throws InvocationTargetException, InstantiationException, IllegalAccessException
    {
        Object obj = class1.newInstance();
        PropertyDescriptor apropertydescriptor[] = PropertyUtils.getPropertyDescriptors(obj);
        Object obj1 = null;
        int i1 = 0;
        String as[] = (String[])null;
        for(int j1 = 0; j1 < apropertydescriptor.length; j1++)
        {
            String s = apropertydescriptor[j1].getName();
            if("class".equals(s))
                continue;
            String as1[] = submitdatamap.getParameterValues(s);
            if(as1 == null)
                continue;
            i1 = as1.length;
            break;
        }

        ArrayList arraylist = new ArrayList();
        for(int k1 = 0; k1 < i1; k1++)
        {
            Object obj2 = class1.newInstance();
            for(int l1 = 0; l1 < apropertydescriptor.length; l1++)
                if(apropertydescriptor[l1].getReadMethod() == null)
                {
                    if(a.isTraceEnabled())
                        a.trace("-->No getter on JavaBean for " + apropertydescriptor[l1].getName() + ", skipping");
                } else
                {
                    String s1 = apropertydescriptor[l1].getName();
                    if(!"class".equals(s1))
                    {
                        String s2 = null;
                        String as2[] = submitdatamap.getParameterValues(s1);
                        if(as2 != null)
                            s2 = as2[k1];
                        BeanUtils.copyProperty(obj2, s1, s2);
                    }
                }

            arraylist.add(obj2);
        }

        return arraylist;
    }

    /*public static Object convert(String s, Class class1)
    {
        Converter converter = (Converter)_fldif.get(class1);
        if(converter == null)
            converter = (Converter)_fldif.get(java.lang.String.class);
        return converter.convert(class1, s);
    }

    public static Object convert(String as[], Class class1)
    {
        Class class2 = class1;
        if(class1.isArray())
            class2 = class1.getComponentType();
        Converter converter = (Converter)_fldif.get(class2);
        if(converter == null)
            converter = (Converter)_fldif.get(java.lang.String.class);
        Object obj = Array.newInstance(class2, as.length);
        for(int i1 = 0; i1 < as.length; i1++)
            Array.set(obj, i1, converter.convert(class2, as[i1]));

        return obj;
    }

    private static void a()
    {
        _fldif.put(java.math.BigDecimal.class, new k(null));
        _fldif.put(java.math.BigInteger.class, new m(null));
        _fldif.put(Boolean.TYPE, new g(null));
        _fldif.put(java.lang.Boolean.class, new g(null));
        _fldif.put(Byte.TYPE, new l(null));
        _fldif.put(java.lang.Byte.class, new l(null));
        _fldif.put(Character.TYPE, new b(null));
        _fldif.put(java.lang.Character.class, new b(null));
        _fldif.put(Double.TYPE, new e(null));
        _fldif.put(java.lang.Double.class, new e(null));
        _fldif.put(Float.TYPE, new a(null));
        _fldif.put(java.lang.Float.class, new a(null));
        _fldif.put(Integer.TYPE, new h(null));
        _fldif.put(java.lang.Integer.class, new h(null));
        _fldif.put(Long.TYPE, new j(null));
        _fldif.put(java.lang.Long.class, new j(null));
        _fldif.put(Short.TYPE, new c(null));
        _fldif.put(java.lang.Short.class, new c(null));
        _fldif.put(java.lang.String.class, new f());
        _fldif.put(java.sql.Date.class, new i(null));
    }

    static 
    {
        _fldif = new FastHashMap();
        _fldif.setFast(false);
        a();
        _fldif.setFast(true);
        a = LogFactory.getLog(com.lbs.commons.converter.Convert.class);
    }*/
	
}
