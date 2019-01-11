/**
 * ResumesFacade.java 2008/03/24
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
 * 简历管理
 */
public interface ResumesFacade
{
    /**
     * 保存个人简历
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop saveResumes(RequestEnvelop request);
    
    /**
     * 删除个人简历
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop removeResumes(RequestEnvelop request);
    
    /**
     * 查询个人简历详细信息
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop findResumes(RequestEnvelop request);
}
