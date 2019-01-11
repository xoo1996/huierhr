/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.cfgmgmt.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
/**
 * 配置项管理
 */
public interface CIFacade {	
    /**
     * 配置项信息显示
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop printCI(RequestEnvelop request);	

    /**
     * 配置项信息保存
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop saveCI(RequestEnvelop request);

    /**
     * 配置项信息变更
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop modifyCI(RequestEnvelop request);
	
    /**
     * 配置项信息删除
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop deleteCI(RequestEnvelop request);
}
