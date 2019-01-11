// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart.dataproducer;


import java.util.List;
import java.util.Map;

import org.jfree.data.general.DefaultPieDataset;
import org.radf.commons.chart.BaseDataProducer;
import org.radf.commons.chart.ChartQueryDAO;

import de.laures.cewolf.DatasetProduceException;

public class PieDataProducer extends BaseDataProducer
{

    public PieDataProducer()
    {
        producerId = "PieDataProducer";
    }

    public Object produceDataset(Map map)
        throws DatasetProduceException
    {
        if(map.containsKey("data"))
        {
            Object obj = map.get("data");
            if(obj != null)
                return obj;
        }
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        try
        {
            if(map.containsKey("hqlmap"))
            {
                Map map1 = (Map)map.get("hqlmap");
                if(map1 != null)
                    a(defaultpiedataset, map1, 0);
            }
            if(map.containsKey("sqlmap"))
            {
                Map map2 = (Map)map.get("sqlmap");
                if(map2 != null)
                    a(defaultpiedataset, map2, 1);
            }
        }
        catch(DatasetProduceException opexception)
        {
            throw new DatasetProduceException(opexception.getMessage());
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return defaultpiedataset;
    }

    private void a(DefaultPieDataset defaultpiedataset, Map map, int i)
        throws Exception, DatasetProduceException
    {
        Object aobj[] = new Object[map.size()];
        map.values().toArray(aobj);
        Object obj = null;
        for(int j = 0; j < aobj.length; j++)
        {
            List list = ChartQueryDAO.list((String)aobj[j], i);
            a(defaultpiedataset, list);
        }

    }

    private void a(DefaultPieDataset defaultpiedataset, List list)
        throws DatasetProduceException
    {
        if(list != null)
        {
            Object aobj[] = new Object[list.size()];
            list.toArray(aobj);
            for(int i = 0; i < aobj.length; i++)
            {
                Object aobj1[] = (Object[])aobj[i];
                defaultpiedataset.setValue((Comparable)aobj1[0], (Number)aobj1[1]);
            }

        }
    }
}
