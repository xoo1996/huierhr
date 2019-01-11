/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.store.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 库存管理
 */
public interface StoreFacade {

	/**
	 * 库存基本信息保存
	 */
	public ResponseEnvelop saveStore(RequestEnvelop request);

//	/**
//	 * 订单基本信息修改
//	 */
//	public ResponseEnvelop modify(RequestEnvelop request);
//
//	/**
//	 * 订单明细批量保存
//	 */
//	public ResponseEnvelop saveDetail(RequestEnvelop request);
//
//	/**
//	 * 订单明细批量修改
//	 */
	public ResponseEnvelop modifyState(RequestEnvelop request,int sta);

//	/**
//	 * 订单信息删除
//	 */
//	public ResponseEnvelop delete(RequestEnvelop request);
//
//	public ResponseEnvelop saveNew(RequestEnvelop request);
//	/**
//	 * 退机
//	 */
//	public ResponseEnvelop recoil(RequestEnvelop request);
//	
//	/**
//	 * 维修记录查询
//	 */
//	public ResponseEnvelop find(RequestEnvelop request);
	
	/**
	 * 配货
	 */
	public ResponseEnvelop allocate(RequestEnvelop request);
	/**
	 * 盘库
	 */
	public ResponseEnvelop panku(RequestEnvelop request);
	
}
