/**
 * Rec03Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.query.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ְҵ�ۺ���Ϣ��ѯ
 */
public interface Rec03Facade {
	/**
	 * ��ѯ��λ��Ϣ 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop queryEmployer(RequestEnvelop request);
}
