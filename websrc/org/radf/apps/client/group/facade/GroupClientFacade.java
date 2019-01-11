/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 项目: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.client.group.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
/**
 * 团体客户管理
 */
public interface GroupClientFacade {	
	
	public ResponseEnvelop queryGCName(RequestEnvelop request);
	
    /**
     * 团体客户信息显示
     */
    public ResponseEnvelop print(RequestEnvelop request);	

    /**
     * 团体客户信息保存
     */
	public ResponseEnvelop save(RequestEnvelop request);

    /**
     * 团体客户信息变更
     */
	public ResponseEnvelop modify(RequestEnvelop request);
	
    /**
     * 团体客户信息删除
     */
	public ResponseEnvelop delete(RequestEnvelop request);
	
	public ResponseEnvelop query(RequestEnvelop request);
}
