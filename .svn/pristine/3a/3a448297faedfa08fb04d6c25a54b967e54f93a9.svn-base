/**
 * PersonFacade.java 2008/03/24
 * 
 * Copyright (c) 2008 项目: Rapid Application Development Framework
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.basicinfo.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
/**
 * 人员管理
 */
public interface PersonFacade
{
	/**
	 * 人员查询
	 * @param requestEnvelop
	 * @return
	 */
    public ResponseEnvelop findPerson(RequestEnvelop request);
    
    /**
     * 保存个人基本信息
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop savePerson(RequestEnvelop request);
    
    /**
     * 人员注销
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop writeOff(RequestEnvelop request);
    /**
     * 人员合并
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop callPro(RequestEnvelop request);
	
    /**
     * 人员删除
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop delPerson(RequestEnvelop request);
    
    /**
     * 人员信息打印
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop printPerson(RequestEnvelop request);
    /**
     * 人员合并验证
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop validateUnitePerson(RequestEnvelop request);
	
	
    

}
