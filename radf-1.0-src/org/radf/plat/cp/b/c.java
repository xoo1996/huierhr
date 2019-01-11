// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.b;

import java.io.*;

public abstract class c
{

    protected PrintStream a;

    public c()
    {
    }

    protected abstract int _mthif();

    protected abstract int a();

    protected void _mthdo(OutputStream outputstream)
        throws IOException
    {
        a = new PrintStream(outputstream);
    }

    protected void a(OutputStream outputstream)
        throws IOException
    {
    }

    protected void a(OutputStream outputstream, int i)
        throws IOException
    {
    }

    protected void _mthif(OutputStream outputstream)
        throws IOException
    {
    }

    protected abstract void a(OutputStream outputstream, byte abyte0[], int i, int j)
        throws IOException;

    protected int a(InputStream inputstream, byte abyte0[])
        throws IOException
    {
        for(int i = 0; i < abyte0.length; i++)
        {
            int j = inputstream.read();
            if(j == -1)
                return i;
            abyte0[i] = (byte)j;
        }

        return abyte0.length;
    }

    public void a(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        byte abyte0[] = new byte[a()];
        _mthdo(outputstream);
        do
        {
            int i = a(inputstream, abyte0);
            if(i == 0)
                break;
            a(outputstream, i);
            for(int j = 0; j < i; j += _mthif())
                if(j + _mthif() <= i)
                    a(outputstream, abyte0, j, _mthif());
                else
                    a(outputstream, abyte0, j, i - j);

            if(i < a())
                break;
            _mthif(outputstream);
        } while(true);
        a(outputstream);
    }

    public void _mthif(byte abyte0[], OutputStream outputstream)
        throws IOException
    {
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        a(bytearrayinputstream, outputstream);
    }

    public String _mthif(byte abyte0[])
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        String s = null;
        try
        {
            a(bytearrayinputstream, bytearrayoutputstream);
            s = bytearrayoutputstream.toString("8859_1");
        }
        catch(Exception exception)
        {
            throw new Error("ChracterEncoder::encodeBuffer internal error");
        }
        return s;
    }

    public void _mthif(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        byte abyte0[] = new byte[a()];
        _mthdo(outputstream);
        int i;
        do
        {
            i = a(inputstream, abyte0);
            if(i == 0)
                break;
            a(outputstream, i);
            for(int j = 0; j < i; j += _mthif())
                if(j + _mthif() <= i)
                    a(outputstream, abyte0, j, _mthif());
                else
                    a(outputstream, abyte0, j, i - j);

            _mthif(outputstream);
        } while(i >= a());
        a(outputstream);
    }

    public void a(byte abyte0[], OutputStream outputstream)
        throws IOException
    {
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        _mthif(bytearrayinputstream, outputstream);
    }

    public String a(byte abyte0[])
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        try
        {
            _mthif(bytearrayinputstream, bytearrayoutputstream);
        }
        catch(Exception exception)
        {
            throw new Error("ChracterEncoder::encodeBuffer internal error");
        }
        return bytearrayoutputstream.toString();
    }
}
