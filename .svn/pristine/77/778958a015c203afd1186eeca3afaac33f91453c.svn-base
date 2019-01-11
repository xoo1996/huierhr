// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class BaseDataProducer
    implements DatasetProducer, Serializable
{

    protected String producerId;

    public BaseDataProducer()
    {
        producerId = "BaseDataProducer";
    }

    public Object produceDataset(Map map)
        throws DatasetProduceException
    {
        throw new DatasetProduceException("BaseDataProducer类不能产生图表数据，请覆盖produceDataset方法或使用DatasetProducer接口的其它实现类！");
    }

    public boolean hasExpired(Map map, Date date)
    {
        return System.currentTimeMillis() - date.getTime() > 5000L;
    }

    public String getProducerId()
    {
        return producerId;
    }
}
