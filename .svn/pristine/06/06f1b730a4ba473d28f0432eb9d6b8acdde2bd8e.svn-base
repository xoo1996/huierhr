// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.b;

import java.io.*;

public abstract class a
{

    public a()
    {
    }

    protected abstract int _mthif();

    protected abstract int a();

    protected void _mthif(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
    }

    protected void a(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
    }

    protected int _mthdo(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        return a();
    }

    protected void _mthfor(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
    }

    protected void a(InputStream inputstream, OutputStream outputstream, int i)
        throws IOException
    {
        throw new IOException("StreamExhausted");
    }

    protected int a(InputStream inputstream, byte abyte0[], int i, int j)
        throws IOException
    {
        for(int k = 0; k < j; k++)
        {
            int l = inputstream.read();
            if(l == -1)
                return k == 0 ? -1 : k;
            abyte0[k + i] = (byte)l;
        }

        return j;
    }

    public void _mthint(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        int i = 0;
        _mthif(inputstream, outputstream);
        try
        {
            do
            {
                int j = _mthdo(inputstream, outputstream);
                int k;
                for(k = 0; k + _mthif() < j; k += _mthif())
                {
                    a(inputstream, outputstream, _mthif());
                    i += _mthif();
                }

                if(k + _mthif() == j)
                {
                    a(inputstream, outputstream, _mthif());
                    i += _mthif();
                } else
                {
                    a(inputstream, outputstream, j - k);
                    i += j - k;
                }
                _mthfor(inputstream, outputstream);
            } while(true);
        }
        catch(IOException ioexception)
        {
            if(ioexception.getMessage().equals("StreamExhausted"))
                a(inputstream, outputstream);
            else
                throw ioexception;
        }
    }

    public byte[] a(String s)
        throws IOException
    {
        byte abyte0[] = s.getBytes();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        _mthint(bytearrayinputstream, bytearrayoutputstream);
        return bytearrayoutputstream.toByteArray();
    }

    public byte[] a(InputStream inputstream)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        _mthint(inputstream, bytearrayoutputstream);
        return bytearrayoutputstream.toByteArray();
    }
}
