/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.repair.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 维修管理
 */
public interface RepairFacade {

	/**
	 * 维修机保存
	 */
	public ResponseEnvelop save(RequestEnvelop request);

	/**
	 * 维修机查询
	 */
	public ResponseEnvelop find(RequestEnvelop request);

	/**
	 * 维修机删除
	 */
	public ResponseEnvelop remove(RequestEnvelop request);

	/**
	 * 维修机修改
	 */
	public ResponseEnvelop modify(RequestEnvelop request);
	
	public ResponseEnvelop changeStatus(RequestEnvelop request);
	
	public ResponseEnvelop finish(RequestEnvelop request);
	
	public ResponseEnvelop batchChange(RequestEnvelop request);
	
	public ResponseEnvelop batchMakeshell(RequestEnvelop request);
	
	//开票
	public ResponseEnvelop kaip(RequestEnvelop request);
	public ResponseEnvelop getRepair(RequestEnvelop request);
	
}
