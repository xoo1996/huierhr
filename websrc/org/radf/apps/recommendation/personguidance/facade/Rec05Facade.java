/**
 * Rec05Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personguidance.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 职业指导管理
 */
public interface Rec05Facade {
	/**
	 * 保存职业指导 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop save(RequestEnvelop request);

	/**
	 * 修改职业指导 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop edit(RequestEnvelop request);

	/**
	 * 查询职业指导 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findpersonguidance(RequestEnvelop request);

}
