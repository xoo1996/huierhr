// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   SimpleCharStream.java
package org.radf.plat.Zql;

import java.io.*;

public class SimpleCharStream
{

    public static final boolean staticFlag = false;
    int bufsize;
    int available;
    int tokenBegin;
    public int bufpos;
    protected int bufline[];
    protected int bufcolumn[];
    protected int column;
    protected int line;
    protected boolean prevCharIsCR;
    protected boolean prevCharIsLF;
    protected Reader inputStream;
    protected char buffer[];
    protected int maxNextCharInd;
    protected int inBuf;

    protected void ExpandBuff(boolean flag)
    {
        char ac[] = new char[bufsize + 2048];
        int ai[] = new int[bufsize + 2048];
        int ai1[] = new int[bufsize + 2048];
        try
        {
            if(flag)
            {
                System.arraycopy(buffer, tokenBegin, ac, 0, bufsize - tokenBegin);
                System.arraycopy(buffer, 0, ac, bufsize - tokenBegin, bufpos);
                buffer = ac;
                System.arraycopy(bufline, tokenBegin, ai, 0, bufsize - tokenBegin);
                System.arraycopy(bufline, 0, ai, bufsize - tokenBegin, bufpos);
                bufline = ai;
                System.arraycopy(bufcolumn, tokenBegin, ai1, 0, bufsize - tokenBegin);
                System.arraycopy(bufcolumn, 0, ai1, bufsize - tokenBegin, bufpos);
                bufcolumn = ai1;
                maxNextCharInd = bufpos += bufsize - tokenBegin;
            } else
            {
                System.arraycopy(buffer, tokenBegin, ac, 0, bufsize - tokenBegin);
                buffer = ac;
                System.arraycopy(bufline, tokenBegin, ai, 0, bufsize - tokenBegin);
                bufline = ai;
                System.arraycopy(bufcolumn, tokenBegin, ai1, 0, bufsize - tokenBegin);
                bufcolumn = ai1;
                maxNextCharInd = bufpos -= tokenBegin;
            }
        }
        catch(Throwable throwable)
        {
            throw new Error(throwable.getMessage());
        }
        bufsize += 2048;
        available = bufsize;
        tokenBegin = 0;
    }

    protected void FillBuff()
        throws IOException
    {
        if(maxNextCharInd == available)
            if(available == bufsize)
            {
                if(tokenBegin > 2048)
                {
                    bufpos = maxNextCharInd = 0;
                    available = tokenBegin;
                } else
                if(tokenBegin < 0)
                    bufpos = maxNextCharInd = 0;
                else
                    ExpandBuff(false);
            } else
            if(available > tokenBegin)
                available = bufsize;
            else
            if(tokenBegin - available < 2048)
                ExpandBuff(true);
            else
                available = tokenBegin;
        int i;
        try
        {
            if((i = inputStream.read(buffer, maxNextCharInd, available - maxNextCharInd)) == -1)
            {
                inputStream.close();
                throw new IOException();
            } else
            {
                maxNextCharInd += i;
                return;
            }
        }
        catch(IOException ioexception)
        {
            bufpos--;
            backup(0);
            if(tokenBegin == -1)
                tokenBegin = bufpos;
            throw ioexception;
        }
    }

    public char BeginToken()
        throws IOException
    {
        tokenBegin = -1;
        char c = readChar();
        tokenBegin = bufpos;
        return c;
    }

    protected void UpdateLineColumn(char c)
    {
        column++;
        if(prevCharIsLF)
        {
            prevCharIsLF = false;
            line += column = 1;
        } else
        if(prevCharIsCR)
        {
            prevCharIsCR = false;
            if(c == '\n')
                prevCharIsLF = true;
            else
                line += column = 1;
        }
        switch(c)
        {
        case 13: // '\r'
            prevCharIsCR = true;
            break;

        case 10: // '\n'
            prevCharIsLF = true;
            break;

        case 9: // '\t'
            column--;
            column += 8 - (column & 0x7);
            break;
        }
        bufline[bufpos] = line;
        bufcolumn[bufpos] = column;
    }

    public char readChar()
        throws IOException
    {
        if(inBuf > 0)
        {
            inBuf--;
            if(++bufpos == bufsize)
                bufpos = 0;
            return buffer[bufpos];
        }
        if(++bufpos >= maxNextCharInd)
            FillBuff();
        char c = buffer[bufpos];
        UpdateLineColumn(c);
        return c;
    }

    public int getColumn()
    {
        return bufcolumn[bufpos];
    }

    public int getLine()
    {
        return bufline[bufpos];
    }

    public int getEndColumn()
    {
        return bufcolumn[bufpos];
    }

    public int getEndLine()
    {
        return bufline[bufpos];
    }

    public int getBeginColumn()
    {
        return bufcolumn[tokenBegin];
    }

    public int getBeginLine()
    {
        return bufline[tokenBegin];
    }

    public void backup(int i)
    {
        inBuf += i;
        if((bufpos -= i) < 0)
            bufpos += bufsize;
    }

    public SimpleCharStream(Reader reader, int i, int j, int k)
    {
        bufpos = -1;
        column = 0;
        line = 1;
        prevCharIsCR = false;
        prevCharIsLF = false;
        maxNextCharInd = 0;
        inBuf = 0;
        inputStream = reader;
        line = i;
        column = j - 1;
        available = bufsize = k;
        buffer = new char[k];
        bufline = new int[k];
        bufcolumn = new int[k];
    }

    public SimpleCharStream(Reader reader, int i, int j)
    {
        this(reader, i, j, 4096);
    }

    public SimpleCharStream(Reader reader)
    {
        this(reader, 1, 1, 4096);
    }

    public void ReInit(Reader reader, int i, int j, int k)
    {
        inputStream = reader;
        line = i;
        column = j - 1;
        if(buffer == null || k != buffer.length)
        {
            available = bufsize = k;
            buffer = new char[k];
            bufline = new int[k];
            bufcolumn = new int[k];
        }
        prevCharIsLF = prevCharIsCR = false;
        tokenBegin = inBuf = maxNextCharInd = 0;
        bufpos = -1;
    }

    public void ReInit(Reader reader, int i, int j)
    {
        ReInit(reader, i, j, 4096);
    }

    public void ReInit(Reader reader)
    {
        ReInit(reader, 1, 1, 4096);
    }

    public SimpleCharStream(InputStream inputstream, int i, int j, int k)
    {
        this(((Reader) (new InputStreamReader(inputstream))), i, j, 4096);
    }

    public SimpleCharStream(InputStream inputstream, int i, int j)
    {
        this(inputstream, i, j, 4096);
    }

    public SimpleCharStream(InputStream inputstream)
    {
        this(inputstream, 1, 1, 4096);
    }

    public void ReInit(InputStream inputstream, int i, int j, int k)
    {
        ReInit(((Reader) (new InputStreamReader(inputstream))), i, j, 4096);
    }

    public void ReInit(InputStream inputstream)
    {
        ReInit(inputstream, 1, 1, 4096);
    }

    public void ReInit(InputStream inputstream, int i, int j)
    {
        ReInit(inputstream, i, j, 4096);
    }

    public String GetImage()
    {
        if(bufpos >= tokenBegin)
            return new String(buffer, tokenBegin, (bufpos - tokenBegin) + 1);
        else
            return new String(buffer, tokenBegin, bufsize - tokenBegin) + new String(buffer, 0, bufpos + 1);
    }

    public char[] GetSuffix(int i)
    {
        char ac[] = new char[i];
        if(bufpos + 1 >= i)
        {
            System.arraycopy(buffer, (bufpos - i) + 1, ac, 0, i);
        } else
        {
            System.arraycopy(buffer, bufsize - (i - bufpos - 1), ac, 0, i - bufpos - 1);
            System.arraycopy(buffer, 0, ac, i - bufpos - 1, bufpos + 1);
        }
        return ac;
    }

    public void Done()
    {
        buffer = null;
        bufline = null;
        bufcolumn = null;
    }

    public void adjustBeginLineColumn(int i, int j)
    {
        int k = tokenBegin;
        int l;
        if(bufpos >= tokenBegin)
            l = (bufpos - tokenBegin) + inBuf + 1;
        else
            l = (bufsize - tokenBegin) + bufpos + 1 + inBuf;
        int i1 = 0;
        int j1 = 0;
        boolean flag = false;
        boolean flag1 = false;
        int i2 = 0;
        int k1;
        for(; i1 < l && bufline[j1 = k % bufsize] == bufline[k1 = ++k % bufsize]; i1++)
        {
            bufline[j1] = i;
            int l1 = (i2 + bufcolumn[k1]) - bufcolumn[j1];
            bufcolumn[j1] = j + i2;
            i2 = l1;
        }

        if(i1 < l)
        {
            bufline[j1] = i++;
            bufcolumn[j1] = j + i2;
            while(i1++ < l) 
                if(bufline[j1 = k % bufsize] != bufline[++k % bufsize])
                    bufline[j1] = i++;
                else
                    bufline[j1] = i;
        }
        line = bufline[j1];
        column = bufcolumn[j1];
    }
}
