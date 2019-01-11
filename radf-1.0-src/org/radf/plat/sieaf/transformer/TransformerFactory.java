package org.radf.plat.sieaf.transformer;

import org.radf.plat.log.LogHelper;
import org.radf.plat.util.global.GlobalNames;

/**
 * <p>Title:TransformerFactory </p>
 * <p>Description: this class provides the different methods to transform the message
 *    between client and server</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co., Ltd.</p>
 * <p>Company: LBS</p>
 * @author chenshuichao
 * @version 0.1
 */

public class TransformerFactory {

	public static Transformer getTransformer() {
		Transformer dao = null;
		try {
			//根据指定的类名动态加载类，并实例化一个对象
			//			Dao =
			//				(Transformer) Class
			//					.forName(GlobalNames.TRANSFORMER)
			//					.newInstance();
			dao = new XmlTransformerImpl();

		} catch (Exception se) {

			LogHelper log =
				new LogHelper(TransformerFactory.class.getName());
			log.log(null, 300006, se.getMessage());
			if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
				System.err.println(
					"<TransformerFactory.class>Exception while getting TransformerFactory.class");
				se.printStackTrace();
			}
		}
		return dao;

	}

}
