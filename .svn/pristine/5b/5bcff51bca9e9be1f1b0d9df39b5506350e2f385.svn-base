// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.plugin;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.DateConvert;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.CharacterConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.converters.StringConverter;

public class ConvertRegister
{

    public ConvertRegister()
    {
    }
    public static void register()
    {
        ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);
        ConvertUtils.register(new BigIntegerConverter(null), java.math.BigInteger.class);
        ConvertUtils.register(new BooleanConverter(null), Boolean.TYPE);
        ConvertUtils.register(new BooleanConverter(null), java.lang.Boolean.class);
        ConvertUtils.register(new ByteConverter(null), Byte.TYPE);
        ConvertUtils.register(new ByteConverter(null), java.lang.Byte.class);
        ConvertUtils.register(new CharacterConverter(null), Character.TYPE);
        ConvertUtils.register(new CharacterConverter(null), java.lang.Character.class);
        ConvertUtils.register(new DoubleConverter(null), Double.TYPE);
        ConvertUtils.register(new DoubleConverter(null), java.lang.Double.class);
        ConvertUtils.register(new FloatConverter(null), Float.TYPE);
        ConvertUtils.register(new FloatConverter(null), java.lang.Float.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.TYPE);
        ConvertUtils.register(new IntegerConverter(null), java.lang.Integer.class);
        ConvertUtils.register(new LongConverter(null), Long.TYPE);
        ConvertUtils.register(new LongConverter(null), java.lang.Long.class);
        ConvertUtils.register(new ShortConverter(null), Short.TYPE);
        ConvertUtils.register(new ShortConverter(null), java.lang.Short.class);
        ConvertUtils.register(new StringConverter(), java.lang.String.class);
       // ConvertUtils.register(new DateConvert(null), java.sql.Date.class);
		//ConvertUtils.register(new DateConvert(null), java.util.Date.class);
        ConvertUtils.register(new SqlTimeConverter(null), java.sql.Time.class);
        ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
    }
}
