/**
 * Rec0401Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */

package org.radf.apps.recommendation.employinvite.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface Rec0401Facade {
	/**
	 * 保存委托招聘信息 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop addEmployInvite(RequestEnvelop request);

	/**
	 * 初始化委托招聘信息修改界面提供数据，查找一条单位信息 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop initaddEmployInvite(RequestEnvelop request);

}
