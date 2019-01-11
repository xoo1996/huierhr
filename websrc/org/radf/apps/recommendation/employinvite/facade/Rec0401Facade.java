/**
 * Rec0401Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */

package org.radf.apps.recommendation.employinvite.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface Rec0401Facade {
	/**
	 * ����ί����Ƹ��Ϣ 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop addEmployInvite(RequestEnvelop request);

	/**
	 * ��ʼ��ί����Ƹ��Ϣ�޸Ľ����ṩ���ݣ�����һ����λ��Ϣ 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop initaddEmployInvite(RequestEnvelop request);

}
