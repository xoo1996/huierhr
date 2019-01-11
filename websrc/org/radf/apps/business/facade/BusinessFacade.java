/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.business.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 商务管理
 */
public interface BusinessFacade {

	/**
	 * 月结信息变更
	 */
	public ResponseEnvelop modify(RequestEnvelop request);
	
	/**
	 * 录入客户费用信息
	 * @param request
	 * @return
	 */
	public ResponseEnvelop insert(RequestEnvelop request);
	/**
	 * 查找客户费用信息
	 */
	public ResponseEnvelop query(RequestEnvelop request);
	
	/**
	 * 库存超期查询
	 */
	public ResponseEnvelop stoexpquery(RequestEnvelop request);
	
	/**
	 * 更新修改客户费用信息
	 */
	
	public ResponseEnvelop update(RequestEnvelop request);

	/**
	 * 修改报表信息
	 */
	public ResponseEnvelop find(RequestEnvelop request);
	/**
	 * 月结补充信息
	 * @param request
	 * @return
	 */
	public ResponseEnvelop save(RequestEnvelop request);
	
	/**
	 * 保存固定资产信息
	 */
	public ResponseEnvelop saveAsset(RequestEnvelop request);
	
	/**
	 * 显示固定资产修改页面
	 */
	public ResponseEnvelop showAsset(RequestEnvelop request);
	
	/**
	 * 保存修改后的固定资产信息
	 */
	public ResponseEnvelop modifyAsset(RequestEnvelop request);
	
	/**
	 * 删除固定资产信息
	 */
	public ResponseEnvelop deleteAsset(RequestEnvelop request);
	
	
	/**
	 * 保存摊销费用信息
	 */
	public ResponseEnvelop saveAmortize(RequestEnvelop request);
	
	
	/**
	 * 显示修改摊销管理信息页面
	 */
	public ResponseEnvelop showAmortize(RequestEnvelop request);
	
	/**
	 * 保存修改后的摊销信息
	 */
	public ResponseEnvelop modifyAmortize(RequestEnvelop request);
	
	/**
	 * 删除摊销管理信息
	 */
	public ResponseEnvelop deleteAmortize(RequestEnvelop request);
	
	/**
	 * 保存月度结账备注
	 */
	public ResponseEnvelop savent(RequestEnvelop request);
	
	public ResponseEnvelop edit(RequestEnvelop request);
	
	public ResponseEnvelop querysale(RequestEnvelop request);
	
	public ResponseEnvelop modifydis(RequestEnvelop request);
	
	public ResponseEnvelop lastMonth(RequestEnvelop request);
	
	/**
	 * 月度结账
	 */
	public ResponseEnvelop account(RequestEnvelop request);
	
	/**
	 * 显示修改库存期限页面
	 */
	public ResponseEnvelop print(RequestEnvelop request);
	
	
	/**
	 * 保存修改的库存期限
	 */
	public ResponseEnvelop saveExd(RequestEnvelop request);
	
	/**
	 * 查询是加盟店还是直属店
	
	 */
	public ResponseEnvelop insertAmortize(RequestEnvelop requestEnvelop);
	
	/**
	 * 查询是加盟店还是直属店
	
	 */
	public String queryStoreType(String Gctnm);


}
