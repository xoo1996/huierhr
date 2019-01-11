/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.qa.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 质量管理
 */
public interface QAFacade {

	/**
	 * 质检记录保存
	 */
	public ResponseEnvelop create(RequestEnvelop request);

	/**
	 * 质检记录查询
	 */
	public ResponseEnvelop find(RequestEnvelop request);

	/**
	 * 质检记录修改
	 */
	public ResponseEnvelop modify(RequestEnvelop request);

	/**
	 * 质检记录删除
	 */
	public ResponseEnvelop remove(RequestEnvelop request);

	public ResponseEnvelop finish1before(RequestEnvelop request);
	
	public ResponseEnvelop finish2before(RequestEnvelop request);
	
	public ResponseEnvelop finish(RequestEnvelop request);
}
