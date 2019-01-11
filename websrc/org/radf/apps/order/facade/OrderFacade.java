/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.order.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 订单管理
 */
public interface OrderFacade {

	/**
	 * 订单基本信息保存
	 */
	public ResponseEnvelop save(RequestEnvelop request);

	/**
	 * 订单基本信息修改
	 */
	public ResponseEnvelop modify(RequestEnvelop request);

	/**
	 * 订单明细批量保存
	 */
	public ResponseEnvelop saveDetail(RequestEnvelop request);

	/**
	 * 订单明细批量修改
	 */
	public ResponseEnvelop modifyDetail(RequestEnvelop request);

	/**
	 * 订单信息删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request);

	public ResponseEnvelop saveNew(RequestEnvelop request);
	/**
	 * 退机
	 */
	public ResponseEnvelop recoil(RequestEnvelop request);
	
	/**
	 * 维修记录查询
	 */
	public ResponseEnvelop find(RequestEnvelop request);
	
	public ResponseEnvelop print(RequestEnvelop request);
	
	public ResponseEnvelop printCustomDetail(RequestEnvelop request);
	
	public ResponseEnvelop printClient(RequestEnvelop request);
	
	public ResponseEnvelop examine(RequestEnvelop request);
	
	public ResponseEnvelop rollBack(RequestEnvelop request);
	
	public ResponseEnvelop saveCustomOrder(RequestEnvelop request);
	
	public ResponseEnvelop commit(RequestEnvelop request);
	
	public ResponseEnvelop update(RequestEnvelop request);
	
	public ResponseEnvelop saveCusRep(RequestEnvelop request);
	
	//显示耳模维修订单详情
	public ResponseEnvelop printEarRep(RequestEnvelop request);
	
	public ResponseEnvelop printCusRep(RequestEnvelop request);
	
	public ResponseEnvelop updateCusRep(RequestEnvelop request);
	
	public ResponseEnvelop updateEar(RequestEnvelop request);
	
	public ResponseEnvelop updateEarRep(RequestEnvelop request);
	
	public ResponseEnvelop commitCusRep(RequestEnvelop request);
	
	public ResponseEnvelop printEarDetail(RequestEnvelop request);
	
	public ResponseEnvelop printNomDetail(RequestEnvelop request);
	
	public ResponseEnvelop printCusRec(RequestEnvelop request);
	
	public ResponseEnvelop saveEar(RequestEnvelop request);
	
	public ResponseEnvelop saveNom(RequestEnvelop request);
	
	public ResponseEnvelop updateNom(RequestEnvelop request);
	
	public ResponseEnvelop saveDevRep(RequestEnvelop request);
	
	public ResponseEnvelop saveEarRep(RequestEnvelop request);
	
	public ResponseEnvelop queryFoldt(RequestEnvelop request);
	
	public ResponseEnvelop queryMinDis(RequestEnvelop request);
	
	public ResponseEnvelop examineDis(RequestEnvelop request);
	
	public ResponseEnvelop Disback(RequestEnvelop request);
	
	public ResponseEnvelop checkStoAmount(RequestEnvelop request);
	
	public ResponseEnvelop bill(RequestEnvelop request);
}
