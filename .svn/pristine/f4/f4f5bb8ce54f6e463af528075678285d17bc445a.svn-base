/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.product.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 商品代码管理
 */
public interface ProductFacade {

	/**
	 * 查询商品代码
	 */
	public ResponseEnvelop query(RequestEnvelop request);
	
	/**
	 * 查询商品代码list
	 */
	public ResponseEnvelop findBySQL(RequestEnvelop request);

	/**
	 * 商品代码显示
	 */
	public ResponseEnvelop print(RequestEnvelop request);
	
	public ResponseEnvelop printPcl(RequestEnvelop request);
	
	/**
	 * 零件面板显示
	 */
	public ResponseEnvelop print1(RequestEnvelop request);

	/**
	 * 商品代码保存
	 */
	public ResponseEnvelop save(RequestEnvelop request);
	
	/**
	 * 面板零件保存
	 */
	public ResponseEnvelop save1(RequestEnvelop request);
	
	/**
	 * 面板配置信息保存
	 */
	public ResponseEnvelop save2(RequestEnvelop request);
	
	/**
	 * 批量录入面板零件配置信息
	 */
	public ResponseEnvelop saveDetail(RequestEnvelop request);
	
	public ResponseEnvelop savePcl(RequestEnvelop request);
	
	
	/**
	 * 商品代码修改
	 */
	public ResponseEnvelop modify(RequestEnvelop request);
	
	/**
	 * 面板零件修改
	 */
	public ResponseEnvelop modify1(RequestEnvelop request);
	
	/**
	 * 商品代码修改
	 */
	public ResponseEnvelop modifyPcl(RequestEnvelop request);
	/**
	 * 面板零件配置批量修改保存
	 * @param request
	 * @return
	 */
	public ResponseEnvelop batchSave(RequestEnvelop request);

	/**
	 * 商品代码删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request);
	
	public ResponseEnvelop deletePcl(RequestEnvelop request);
	
	/**
	 * 删除面板零件
	 */
	public ResponseEnvelop delete1(RequestEnvelop request);
	
	/**
	 * 删除面板型号
	 */
	public ResponseEnvelop delete2(RequestEnvelop request);
	
	/**
	 * 更改扣率
	 * @param request
	 * @return
	 */
	public ResponseEnvelop update(RequestEnvelop request);
	
	/**
	 * 扣率保存
	 */
	public ResponseEnvelop savedis(RequestEnvelop request);
	
	/**
	 * 批量修改扣率
	 */
	public ResponseEnvelop disupdate(RequestEnvelop request);
	
	
	
	public ResponseEnvelop queryProidByClass(RequestEnvelop request);
	public ResponseEnvelop queryProidByClass1(RequestEnvelop request);//查询面板型号
	
	
	public ResponseEnvelop queryParts(RequestEnvelop request);
	
	public ResponseEnvelop queryClasses(RequestEnvelop request);
	
	
	public ResponseEnvelop queryPanels(RequestEnvelop request);
	
	
	public ResponseEnvelop modTypDis(RequestEnvelop request);
	
	public ResponseEnvelop modProDis(RequestEnvelop request);
	
}
