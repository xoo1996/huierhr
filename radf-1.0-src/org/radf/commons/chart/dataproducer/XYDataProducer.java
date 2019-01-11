// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart.dataproducer;

import java.util.List;
import java.util.Map;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.radf.commons.chart.BaseDataProducer;
import org.radf.commons.chart.ChartQueryDAO;

import de.laures.cewolf.DatasetProduceException;

public class XYDataProducer extends BaseDataProducer {

	public XYDataProducer() {
		producerId = "XYDataProducer";
	}

	public Object produceDataset(Map map) throws DatasetProduceException {
		if (map.containsKey("data")) {
			Object obj = map.get("data");
			if (obj != null)
				return obj;
		}
		XYSeriesCollection xyseriescollection = new XYSeriesCollection();
		try {
			if (map.containsKey("hqlmap")) {
				Map map1 = (Map) map.get("hqlmap");
				if (map1 != null)
					a(xyseriescollection, map1, 0);
			}
			if (map.containsKey("sqlmap")) {
				Map map2 = (Map) map.get("sqlmap");
				if (map2 != null)
					a(xyseriescollection, map2, 1);
			}
		} catch (Exception opexception) {
			throw new DatasetProduceException(opexception.getMessage());
		}
		return xyseriescollection;
	}

	private void a(XYSeriesCollection xyseriescollection, Map map, int i)
			throws Exception, DatasetProduceException {
		if (map != null) {
			String as[] = new String[map.size()];
			map.keySet().toArray(as);
			Object obj = null;
			for (int j = 0; j < as.length; j++) {
				List list = ChartQueryDAO.list((String) map.get(as[j]), i);
				for (Object a : list)
					System.out.println(a);
				a(xyseriescollection, list, as[j]);
			}

		}
	}

	private void a(XYSeriesCollection xyseriescollection, List list, String s)
			throws DatasetProduceException {
		if (list != null) {
			XYSeries xyseries = new XYSeries(s);
			Object aobj[] = new Object[list.size()];
			list.toArray(aobj);
			for (int i = 0; i < aobj.length; i++) {
				Object aobj1[] = (Object[]) aobj[i];
				System.out.println((Number) aobj1[0] + "," + (Number) aobj1[1]);
				xyseries.add((Number) aobj1[0], (Number) aobj1[1]);
			}

			xyseriescollection.addSeries(xyseries);
			xyseriescollection.setAutoWidth(false);
		}
	}
}
