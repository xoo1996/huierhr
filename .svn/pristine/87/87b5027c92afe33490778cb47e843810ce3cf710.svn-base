// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.b;

import java.io.*;

// Referenced classes of package com.lbs.cp.b:
//            a

public class d extends a
{

    private static final char a[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
        '8', '9', '+', '/'
    };
    private static final byte _fldif[];
    byte _flddo[];

    public d()
    {
        _flddo = new byte[4];
    }

    protected int _mthif()
    {
        return 4;
    }

    protected int a()
    {
        return 72;
    }

    protected void a(InputStream inputstream, OutputStream outputstream, int i)
        throws IOException
    {
        byte byte0 = -1;
        byte byte1 = -1;
        byte byte2 = -1;
        byte byte3 = -1;
        if(i < 2)
            throw new IOException("BASE64Decoder: Not enough bytes for an atom.");
        int j;
        do
        {
            j = inputstream.read();
            if(j == -1)
                throw new IOException("StreamExhausted");
        } while(j == 10 || j == 13);
        _flddo[0] = (byte)j;
        j = a(inputstream, _flddo, 1, i - 1);
        if(j == -1)
            throw new IOException("StreamExhausted");
        if(i > 3 && _flddo[3] == 61)
            i = 3;
        if(i > 2 && _flddo[2] == 61)
            i = 2;
        switch(i)
        {
        case 4: // '\004'
            byte3 = _fldif[_flddo[3] & 0xff];
            // fall through

        case 3: // '\003'
            byte2 = _fldif[_flddo[2] & 0xff];
            // fall through

        case 2: // '\002'
            byte1 = _fldif[_flddo[1] & 0xff];
            byte0 = _fldif[_flddo[0] & 0xff];
            // fall through

        default:
            switch(i)
            {
            case 2: // '\002'
                outputstream.write((byte)(byte0 << 2 & 0xfc | byte1 >>> 4 & 0x3));
                break;

            case 3: // '\003'
                outputstream.write((byte)(byte0 << 2 & 0xfc | byte1 >>> 4 & 0x3));
                outputstream.write((byte)(byte1 << 4 & 0xf0 | byte2 >>> 2 & 0xf));
                break;

            case 4: // '\004'
                outputstream.write((byte)(byte0 << 2 & 0xfc | byte1 >>> 4 & 0x3));
                outputstream.write((byte)(byte1 << 4 & 0xf0 | byte2 >>> 2 & 0xf));
                outputstream.write((byte)(byte2 << 6 & 0xc0 | byte3 & 0x3f));
                break;
            }
            break;
        }
    }

    static 
    {
        _fldif = new byte[256];
        for(int i = 0; i < 255; i++)
            _fldif[i] = -1;

        for(int j = 0; j < a.length; j++)
            _fldif[a[j]] = (byte)j;

    }
}
