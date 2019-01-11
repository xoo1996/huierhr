// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart.dataproducer;


import java.util.List;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;
import org.radf.commons.chart.BaseDataProducer;
import org.radf.commons.chart.ChartQueryDAO;

import de.laures.cewolf.DatasetProduceException;

public class CategoryDataProducer extends BaseDataProducer
{

    public CategoryDataProducer()
    {
        producerId = "CategoryDataProducer";
    }

    public Object produceDataset(Map map) throws DatasetProduceException
    {
        if(map.containsKey("data"))
        {
            Object obj = map.get("data");
            if(obj != null)
                return obj;
        }
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        try
        {
            if(map.containsKey("hqlmap"))
            {
                Map map1 = (Map)map.get("hqlmap");
                if(map1 != null)
                    a(defaultcategorydataset, map1, 0);
            }
            if(map.containsKey("sqlmap"))
            {
                Map map2 = (Map)map.get("sqlmap");
                if(map2 != null)
                    a(defaultcategorydataset, map2, 1);
            }
        }
        catch(DatasetProduceException opexception)
        {
            throw new DatasetProduceException(opexception.getMessage());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return defaultcategorydataset;
    }

    private void a(DefaultCategoryDataset defaultcategorydataset, Map map, int i)
        throws  DatasetProduceException,Exception
    {
        Comparable acomparable[] = new Comparable[map.size()];
        map.keySet().toArray(acomparable);
        Object obj = null;
        for(int j = 0; j < acomparable.length; j++)
        {
            List list = ChartQueryDAO.list((String)map.get(acomparable[j]), i);
            a(defaultcategorydataset, list, acomparable[j]);
        }

    }

    private void a(DefaultCategoryDataset defaultcategorydataset, List list, Comparable comparable)
        throws DatasetProduceException,Exception
    {
        if(list != null)
        {
            Object aobj[] = new Object[list.size()];
            list.toArray(aobj);
            for(int i = 0; i < aobj.length; i++)
            {
                Object aobj1[] = (Object[])aobj[i];
                if(aobj1.length >= 3)
                    defaultcategorydataset.addValue((Number)aobj1[2], (Comparable)aobj1[1], (Comparable)aobj1[0]);
                else
                    defaultcategorydataset.addValue((Number)aobj1[1], comparable, (Comparable)aobj1[0]);
            }

        }
    }
}
