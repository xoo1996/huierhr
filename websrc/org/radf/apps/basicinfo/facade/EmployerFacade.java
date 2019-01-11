/**
 * EmployerFacade.java 2008/03/24
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
 * 单位管理
 */
public interface EmployerFacade {
	/**
	 * 公共查询
	 * @param requestEnvelop
	 * @return
	 */
	 public ResponseEnvelop findCommon(RequestEnvelop request);
	/**
	 *  增加单位基本信息
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop addEmp(RequestEnvelop request);
	/**
	 * 查看单位基本信息
	 * @param requestEnvelop
	 * @return
	 */
	public ResponseEnvelop retriveEmp(RequestEnvelop request);
	/**
	 * 更新单位基本信息
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop updateEmp(RequestEnvelop request);
	/**
	 * 变更单位基本信息
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop alterEmp(RequestEnvelop request);
	/**
	 * 注销单位
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop writeOff(RequestEnvelop request);
	
	/**
	 * 合并单位
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop callPro(RequestEnvelop request);
	
	
	/**
	 * 单位删除
	 * @param requestEnvelop
	 * @return  
	 */
	public ResponseEnvelop delEmployer(RequestEnvelop request);
	/**
	 * 验证单位合并
	 * @param requestEnvelop
	 * @return  
	 */	
	public ResponseEnvelop validateUniteEmploy(RequestEnvelop request);
	
	
	
}
