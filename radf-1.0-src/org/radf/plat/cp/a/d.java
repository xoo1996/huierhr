// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.a;

import java.io.Writer;
import java.sql.Clob;

import org.radf.plat.util.exception.AppException;

public class d
{

    public d()
    {
    }

    public static void a(Clob clob, String s)
        throws AppException
    {
        if(clob != null)
        {
            Object obj = null;
            try
            {
                Writer writer = (Writer)clob.getClass().getMethod("getCharacterOutputStream", null).invoke(clob, null);
                writer.write(s);
                writer.flush();
                writer.close();
            }
            catch(Exception exception)
            {
                throw new AppException(exception);
            }
        }
    }
}
