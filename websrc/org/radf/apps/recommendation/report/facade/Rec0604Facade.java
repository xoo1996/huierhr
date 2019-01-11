/**
 * Rec0604Facade.java 2008/04/28
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author sk
 * @version 1.0
 */
package org.radf.apps.recommendation.report.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 职业介绍综合月报
 */
public interface Rec0604Facade
{	
	/**
	 * 统计并生成月报 2008-4-28 by sk
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop reportCreate(RequestEnvelop request);
}
