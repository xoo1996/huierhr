// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.commons.chart.dataproducer;

import de.laures.cewolf.DatasetProduceException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.*;
import org.jfree.data.time.*;
import org.radf.commons.chart.BaseDataProducer;
import org.radf.commons.chart.ChartQueryDAO;

public class TimeDataProducer extends BaseDataProducer {

	private Class _fldif;

	// static Class class$0; /* synthetic field */

	public TimeDataProducer() {

		_fldif = org.jfree.data.time.Month.class;
		producerId = "TimeDataProducer";
	}

	public Object produceDataset(Map map) throws DatasetProduceException {
		if (map.containsKey("data")) {
			Object obj = map.get("data");
			if (obj != null)
				return obj;
		}
		if (map.containsKey("timeClass")) {
			Object obj1 = map.get("timeClass");
			if (obj1 != null)
				_fldif = (Class) obj1;
		}
		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		try {
			System.out.println(map.get("sqlmap"));
			if (map.containsKey("hqlmap"))
				a(timeseriescollection, (Map) map.get("hqlmap"), 0);
			if (map.containsKey("sqlmap"))
				a(timeseriescollection, (Map) map.get("sqlmap"), 1);
		} catch (Exception opexception) {
			throw new DatasetProduceException(opexception.getMessage());
		}
		return timeseriescollection;
	}

	private void a(TimeSeriesCollection timeseriescollection, Map map, int i)
			throws DatasetProduceException {
		if (map == null)
			return;
		String as[] = new String[map.size()];
		map.keySet().toArray(as);
		Object obj = null;
		List list = null;
		System.out.println(as.length);
		for (int j = 0; j < as.length; j++) {

			try {
				list = ChartQueryDAO.list((String) map.get(as[j]), i);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (list != null) {
				a(timeseriescollection, list, as[j], _fldif);
				list = null;
			}
		}

	}

	private void a(TimeSeriesCollection timeseriescollection, List list,
			String s, Class class1) throws DatasetProduceException {
		if (list != null) {
			for(Object a : list)
				System.out.println(a);
			TimeSeries timeseries = new TimeSeries(s, class1);
			Class aclass[] = new Class[1];
			try {
				aclass[0] = Class.forName("java.util.Date");
				Constructor constructor = class1.getConstructor(aclass);
				int i = list.size();
				Object aobj[] = (Object[]) null;
				Object aobj2[] = new Object[1];
				for (int j = 0; j < i; j++) {
					Object aobj1[] = (Object[]) list.get(j);
					aobj2[0] = aobj1[0];
					timeseries.add((RegularTimePeriod) constructor
							.newInstance(aobj2), (Number) aobj1[1]);
				}

			} catch (Exception exception) {
				exception.printStackTrace();
				throw new DatasetProduceException(exception.getMessage());
			}
			timeseriescollection.addSeries(timeseries);
		}
	}

	public Class getTimeClass() {
		return _fldif;
	}

	public void setTimeClass(Class class1) {
		_fldif = class1;
	}
}
