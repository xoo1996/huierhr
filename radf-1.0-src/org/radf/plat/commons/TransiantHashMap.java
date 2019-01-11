// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.commons;


import java.io.Serializable;
import java.util.*;

public class TransiantHashMap implements Serializable{

    int MAXLENGTH;
    List keys;
    List values;

    public TransiantHashMap()
    {
        this(20);
    }

    public TransiantHashMap(int i)
    {
        MAXLENGTH = 20;
        keys = null;
        values = null;
        MAXLENGTH = i;
        keys = new ArrayList(i);
        values = new ArrayList(i);
    }

    public void put(Object obj, Object obj1)
    {
        if(keys.size() >= MAXLENGTH)
            remove(0);
        remove(obj);
        keys.add(obj);
        values.add(obj1);
    }

    public Object get(Object obj)
    {
        if(obj == null)
            return null;
        Iterator iterator = values.iterator();
        Object obj1 = null;
        Object obj2 = null;
        for(Iterator iterator1 = keys.iterator(); iterator1.hasNext();)
        {
            Object obj3 = iterator1.next();
            Object obj4 = iterator.next();
            if(obj.equals(obj3))
            {
                obj1 = obj3;
                obj2 = obj4;
                break;
            }
        }

        remove(obj1);
        put(obj1, obj2);
        return obj2;
    }

    public List keySet()
    {
        return keys;
    }

    public List values()
    {
        return values;
    }

    public void remove(Object obj)
    {
        if(obj == null)
            return;
        Iterator iterator = values.iterator();
        Object obj1 = null;
        Object obj2 = null;
        for(Iterator iterator1 = keys.iterator(); iterator1.hasNext();)
        {
            Object obj3 = iterator1.next();
            Object obj4 = iterator.next();
            if(obj.equals(obj3))
            {
                obj1 = obj3;
                obj2 = obj4;
                break;
            }
        }

        keys.remove(obj1);
        values.remove(obj2);
    }

    public void remove(int i)
    {
        if(i < 0 || i > keys.size())
        {
            return;
        } else
        {
            keys.remove(i);
            values.remove(i);
            return;
        }
    }
}
