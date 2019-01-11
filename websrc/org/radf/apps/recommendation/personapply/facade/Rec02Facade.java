/**
 /**
 * Rec02Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personapply.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 求职登记接口
 */
public interface Rec02Facade {
	/**
	 * 查询人员求职详细信息 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findPersonInfo(RequestEnvelop request);

	/**
	 * 查看人员求职信息 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewPersonApply(RequestEnvelop request);

	/**
	 * 保存人员信息，求职信息，求职意愿 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modPerson(RequestEnvelop request);

	/**
	 * 获取求职信心 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findPersonApply(RequestEnvelop request);

	/**
	 * 修改人员求职信息 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modPersonApply(RequestEnvelop request);

	/**
	 * 删除人员求职信息 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delPersonApply(RequestEnvelop request);

	/**
	 * 新增人员信息，求职信息，求职意愿 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop savePerson(RequestEnvelop request);
}
