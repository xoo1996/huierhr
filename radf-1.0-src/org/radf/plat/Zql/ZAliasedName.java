// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 
// Source File Name:   ZAliasedName.java

package org.radf.plat.Zql;

import java.io.Serializable;
import java.util.StringTokenizer;

public class ZAliasedName
    implements Serializable
{

    String strform_;
    String schema_;
    String table_;
    String column_;
    String alias_;
    public static int FORM_TABLE = 1;
    public static int FORM_COLUMN = 2;
    int form_;

    public ZAliasedName()
    {
        strform_ = "";
        schema_ = null;
        table_ = null;
        column_ = null;
        alias_ = null;
        form_ = FORM_COLUMN;
    }

    public ZAliasedName(String s, int i)
    {
        strform_ = "";
        schema_ = null;
        table_ = null;
        column_ = null;
        alias_ = null;
        form_ = FORM_COLUMN;
        form_ = i;
        strform_ = new String(s);
        StringTokenizer stringtokenizer = new StringTokenizer(s, ".");
        switch(stringtokenizer.countTokens())
        {
        case 1: // '\001'
            if(i == FORM_TABLE)
                table_ = new String(stringtokenizer.nextToken());
            else
                column_ = new String(stringtokenizer.nextToken());
            break;

        case 2: // '\002'
            if(i == FORM_TABLE)
            {
                schema_ = new String(stringtokenizer.nextToken());
                table_ = new String(stringtokenizer.nextToken());
            } else
            {
                table_ = new String(stringtokenizer.nextToken());
                column_ = new String(stringtokenizer.nextToken());
            }
            break;

        case 3: // '\003'
        default:
            schema_ = new String(stringtokenizer.nextToken());
            table_ = new String(stringtokenizer.nextToken());
            column_ = new String(stringtokenizer.nextToken());
            break;
        }
    }

    public String toString()
    {
        if(alias_ == null)
            return strform_;
        else
            return strform_ + " " + alias_;
    }

    public String getSchema()
    {
        return schema_;
    }

    public String getTable()
    {
        return table_;
    }

    public String getColumn()
    {
        return column_;
    }

    public boolean isWildcard()
    {
        if(form_ == FORM_TABLE)
            return table_ != null && table_.equals("*");
        else
            return column_ != null && column_.indexOf(42) >= 0;
    }

    public String getAlias()
    {
        return alias_;
    }

    public void setAlias(String s)
    {
        alias_ = new String(s);
    }

}
