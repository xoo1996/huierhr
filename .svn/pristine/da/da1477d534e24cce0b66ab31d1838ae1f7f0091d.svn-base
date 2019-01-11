package org.radf.apps.earmould.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface EarmouldFacade {

	/**
	 * 耳模信息保存
	 */
	public ResponseEnvelop save(RequestEnvelop request);
	
	/**
	 * 耳模制作
	 */
	public ResponseEnvelop updateProduce(RequestEnvelop request);
	
	/**
	 * 耳模制作完成确认
	 */
	public ResponseEnvelop complete(RequestEnvelop request);
	
	
	/**
	 * 根据用户姓名查询该用户的详细信息
	 */
	public ResponseEnvelop find(RequestEnvelop request);
	
	/**
	 * 耳模信息显示
	 */
    public ResponseEnvelop printCI(RequestEnvelop request);
    
    /**
	 * 耳模信息删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request);
	
	/**
	 * 保存耳模质检信息
	 */
	public ResponseEnvelop updateCheckup(RequestEnvelop request);
	
	/**
	 * 保存修改后的耳模信息
	 */
	public ResponseEnvelop savemodify(RequestEnvelop request);
	
	/**
	 * 添加耳模维修记录
	 */
	public ResponseEnvelop saveRepair(RequestEnvelop request);
	
}
