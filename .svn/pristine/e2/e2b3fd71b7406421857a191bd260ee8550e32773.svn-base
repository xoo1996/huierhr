/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.customization.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 配置项管理
 */
public interface CustomizationFacade {
	/**
	 * 配置项信息显示
	 * 
	 * @param requestEnvelop
	 * @return
	 */
	public ResponseEnvelop printCI(RequestEnvelop request);

	/**
	 * 定制机信息保存
	 */
	public ResponseEnvelop save(RequestEnvelop request);

	/**
	 * 配置项信息变更
	 * 
	 * @param requestEnvelop
	 * @return
	 */
	public ResponseEnvelop modifyCI(RequestEnvelop request);

	/**
	 * 定制机信息删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request);

	/**
	 * 配料
	 */
	public ResponseEnvelop updateArrangement(RequestEnvelop request);

	/**
	 * 质检
	 */
	public ResponseEnvelop updateTest(RequestEnvelop request);

	/**
	 * 确认生产完成
	 */
	public ResponseEnvelop complete(RequestEnvelop request);

	public ResponseEnvelop changeStatus(RequestEnvelop request);

	/**
	 * 定制机信息查询
	 */
	public ResponseEnvelop find(RequestEnvelop request);

	public ResponseEnvelop batchChange(RequestEnvelop request);
}
