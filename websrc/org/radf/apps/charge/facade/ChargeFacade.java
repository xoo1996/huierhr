package org.radf.apps.charge.facade;


import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 收费管理
 */
public interface ChargeFacade {

	/**
	 * 收费基本信息保存
	 */
	public ResponseEnvelop save(RequestEnvelop request);
	
	/**
	 * 普通商品收费基本信息保存
	 */
	public ResponseEnvelop save2(RequestEnvelop request);
	/**
	 * 耳背机收费保存
	 */
	public ResponseEnvelop save3(RequestEnvelop request);
	
	public ResponseEnvelop checkNormalDisc(RequestEnvelop request);
	
	/**
	 * 普通商品收费
	 */
	public ResponseEnvelop charge1(RequestEnvelop request);

	/**
	 * 定制商品收费信息保存
	 */
	public ResponseEnvelop charge2(RequestEnvelop request);
	
	/**
	 * 维修商品收费信息保存
	 */
	public ResponseEnvelop charge3(RequestEnvelop request);
	
	/**
	 * 定制商品收费信息保存
	 */
	public ResponseEnvelop saveCustomizedRec(RequestEnvelop request);
	
	/**
	 * 普通商品收费信息保存
	 */
	public ResponseEnvelop saveNomRec(RequestEnvelop request);
	
	
	public ResponseEnvelop commitNomRec(RequestEnvelop request);
	
//	public ResponseEnvelop modifyNomRec(RequestEnvelop request);
	
	public ResponseEnvelop exaNomRec(RequestEnvelop request);
	
	public ResponseEnvelop exaCusRec(RequestEnvelop request);
	/**
	 * 定制商品收费信息保存
	 */
	public ResponseEnvelop commitCustomizedRec(RequestEnvelop request);
	/**
	 * 退机收费信息保存
	 */
	public ResponseEnvelop charge4(RequestEnvelop request);
	
	
	/**
	 * 定制商品收费点击收费后显示的页面信息
	 */
	public ResponseEnvelop show(RequestEnvelop request);
	
	/**
	 * 定制商品收费点击退机退费后显示的页面信息
	 */
	public ResponseEnvelop show1(RequestEnvelop request);
	
	/**
	 * 定制商品收费信息显示
	 */
	public ResponseEnvelop print(RequestEnvelop request);
	
	
	/**
	 * 维修收费信息显示
	 */
	public ResponseEnvelop print1(RequestEnvelop request);
	
	/**
	 * 普通收费信息显示
	 */
	public ResponseEnvelop print2(RequestEnvelop request);
	
	/**
	 * 退机收费信息显示
	 */
	public ResponseEnvelop print3(RequestEnvelop request);
	
	/**
	 * 普通收费时增加用户信息
	 * 
	 */
	public ResponseEnvelop addClient(RequestEnvelop request);
	
	
	public ResponseEnvelop norChgRecDetail(RequestEnvelop request);
	
	/**
	 * 收费基本信息修改
	 */
	/*public ResponseEnvelop modify(RequestEnvelop request);

	*//**
	 * 收费明细批量保存
	 *//*
	public ResponseEnvelop saveDetail(RequestEnvelop request);

	*//**
	 * 收费明细批量修改
	 *//*
	public ResponseEnvelop modifyDetail(RequestEnvelop request);

	*//**
	 * 收费信息删除
	 *//*
	public ResponseEnvelop delete(RequestEnvelop request);

	public ResponseEnvelop saveNew(RequestEnvelop request);
	*//**
	 * 退机
	 *//*
	public ResponseEnvelop recoil(RequestEnvelop request);*/
}