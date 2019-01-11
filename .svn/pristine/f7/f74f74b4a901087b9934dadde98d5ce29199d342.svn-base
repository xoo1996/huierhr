package org.radf.commons.chart.dataproducer;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.radf.commons.chart.ChartQueryDAO;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.exception.NoConnectionException;

import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import de.laures.cewolf.links.CategoryItemLinkGenerator;
import de.laures.cewolf.tooltips.CategoryToolTipGenerator;

/**
 * An example data producer.
 * 
 * @author Guido Laures
 */
public class PageViewCountData implements DatasetProducer,
		CategoryToolTipGenerator, CategoryItemLinkGenerator {

	// These values would normally not be hard coded but produced by
	// some kind of data source like a database or a file
	private final String[] categories = { "125", "250", "500", "1K", "4K", "8K" };
	private final String[] seriesNames = { "¹Çµ¼", "Æøµ¼" };

	/**
	 * Produces some random data.
	 */
	public Object produceDataset(Map params) throws DatasetProduceException {
		DefaultCategoryDataset dataset = null;
		try {
			dataset = new DefaultCategoryDataset();
			for (int series = 0; series < seriesNames.length; series++) {
				List list = (ArrayList) ChartQueryDAO
						.list(
								"select t.adgfq,t.adgadt from tblaudgraph t where t.adgctid = 015516 and t.adglre = 'L' and t.adgtp = 'Q'",
								1);
				for (int i = 0; i < categories.length; i++) {
					dataset.addValue((Number) list.get(i), seriesNames[series],
							categories[i]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataset;
	}

	/**
	 * This producer's data is invalidated after 5 seconds. By this method the
	 * producer can influence Cewolf's caching behaviour the way it wants to.
	 */
	public boolean hasExpired(Map params, Date since) {
		return (System.currentTimeMillis() - since.getTime()) > 5000;
	}

	/**
	 * Returns a unique ID for this DatasetProducer
	 */
	public String getProducerId() {
		return "PageViewCountData DatasetProducer";
	}

	/**
	 * Returns a link target for a special data item.
	 */
	public String generateLink(Object data, int series, Object category) {
		return seriesNames[series];
	}

	/**
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * @see org.jfree.chart.tooltips.CategoryToolTipGenerator#generateToolTip(CategoryDataset,
	 *      int, int)
	 */
	public String generateToolTip(CategoryDataset arg0, int series, int arg2) {
		return seriesNames[series];
	}
}
