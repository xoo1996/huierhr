// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.b;

import java.security.MessageDigest;

public class b
{

    public b()
    {
    }

    public static String a(String s)
    {
        try
        {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.update(s.getBytes("UTF8"));
            byte abyte0[] = messagedigest.digest();
            String s1 = "";
            for(int i = 0; i < abyte0.length; i++)
                s1 = s1 + Integer.toHexString(0xff & abyte0[i] | 0xffffff00).substring(6);

            return s1;
        }
        catch(Exception exception)
        {
            return null;
        }
    }
}
