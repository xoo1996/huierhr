/**
 * Rec05Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.personguidance.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ְҵָ������
 */
public interface Rec05Facade {
	/**
	 * ����ְҵָ�� 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop save(RequestEnvelop request);

	/**
	 * �޸�ְҵָ�� 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop edit(RequestEnvelop request);

	/**
	 * ��ѯְҵָ�� 2008-3-30 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findpersonguidance(RequestEnvelop request);

}
