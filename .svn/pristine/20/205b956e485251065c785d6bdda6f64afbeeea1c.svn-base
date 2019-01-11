// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.commons;

import java.util.Random;

import org.radf.plat.util.global.GlobalNames;

// Referenced classes of package com.lbs.commons:
//            GlobalNames

public class FileNameGenerator
{

    protected static Random r = new Random();

    public FileNameGenerator()
    {
    }

    public static String nextFileName()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; i < Integer.valueOf(GlobalNames.TEMP_FILE_NAME_LENGTH).intValue(); i++)
            stringbuffer.append(r.nextInt(10));

        return stringbuffer.toString();
    }

    public static void main(String args[])
    {
    }

}
