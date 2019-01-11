/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.client.single.facade;

import org.apache.struts.action.ActionForward;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
/**
 * 个人客户管理
 */
public interface SingleClientFacade {	
    /**
     * 个人客户信息显示
     */
    public ResponseEnvelop print(RequestEnvelop request);	
    /**
     * 个人客户复诊听力显示
     */
    public ResponseEnvelop printSCTL(RequestEnvelop request);	
    /**
     * 个人售后服务调查表显示
     */
    public ResponseEnvelop printBV(RequestEnvelop request);	
    /**
     * 个人客户复诊听力显示
     */
    public ResponseEnvelop printSCSC(RequestEnvelop request);	

    
    
    /**
     * 个人客户信息保存
     */
	public ResponseEnvelop save(RequestEnvelop request);

    /**
     * 个人客户信息变更
     */
	public ResponseEnvelop modify(RequestEnvelop request);
	
    /**
     * 个人客户信息删除
     */
	public ResponseEnvelop delete(RequestEnvelop request);
	
    /**
     * 个人客户复诊信息保存
     */
	public ResponseEnvelop saveSC(RequestEnvelop request);
	
    /**
     * 个人客户复诊信息删除
     */
	public ResponseEnvelop deleteSCTL(RequestEnvelop request);
	/**
     * 个人客户售后服务调查表删除
     */
	public ResponseEnvelop deleteBV(RequestEnvelop request);
	/**
     * 个人客户复诊信息删除
     */
	public ResponseEnvelop deleteSCYYPG(RequestEnvelop request);
	
	/**
     * 个人客户复诊信息删除
     */
	public ResponseEnvelop deleteSCSC(RequestEnvelop request);
	/**
     * 个人客户复诊信息删除
     */
	public ResponseEnvelop deleteSC(RequestEnvelop request);
	
	
	/**
	 * 
	 * 个人客户重命名检查
	 */
	public ResponseEnvelop checkSingleName(RequestEnvelop request);

	/**
	 * 
	 *个人复诊听力保存
	 */
	public ResponseEnvelop saveSCSC(RequestEnvelop request);
	
	/**
	 * 
	 *个人复诊听力保存
	 */
	public ResponseEnvelop saveSCTL(RequestEnvelop request);
	/**
	 * 
	 *个人回访记录保存
	 */
	public ResponseEnvelop saveBV(RequestEnvelop request);
	/**
	 * PG
	 *个人复诊言语评估保存
	 */
	public ResponseEnvelop saveSCYYPG(RequestEnvelop request);
	
}
