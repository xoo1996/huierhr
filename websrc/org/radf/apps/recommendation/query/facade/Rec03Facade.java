/**
 * Rec03Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.query.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 职业综合信息查询
 */
public interface Rec03Facade {
	/**
	 * 查询单位信息 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop queryEmployer(RequestEnvelop request);
}
