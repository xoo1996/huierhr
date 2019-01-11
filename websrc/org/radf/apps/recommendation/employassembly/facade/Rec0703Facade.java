/**
 * Rec0703Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employassembly.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface Rec0703Facade {

	/**
	 * 初始化大会招聘期数信息
	 * 
	 * @author Y.J
	 * @date 2008-2-20 18:25:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop initddqs(RequestEnvelop request);

	/**
	 * 保存 大会招聘期数信息
	 * 
	 * @author Y.J
	 * @date 2008-3-19 12:50:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop saveqs(RequestEnvelop request);

	/**
	 * 所有的查询
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:13:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop search(RequestEnvelop request);

	/**
	 * 修改
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:22:08
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifyCommon(RequestEnvelop request);

	/**
	 * 删除
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:22:15
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delete(RequestEnvelop request);
}
