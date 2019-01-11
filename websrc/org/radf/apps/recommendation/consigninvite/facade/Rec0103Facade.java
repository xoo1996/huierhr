/**
 * Rec0103Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.consigninvite.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ��Ա��λ�Ƽ��ӿ�
 */
public interface Rec0103Facade {

	/**
	 * ƥ���Ƽ�
	 * 
	 * @param requestEnvelop
	 * @return
	 */
	public ResponseEnvelop Recommend(RequestEnvelop request);

	/**
	 * ��λѡ��
	 * 
	 * @param requestEnvelop
	 * @return
	 */
	public ResponseEnvelop dwxr(RequestEnvelop request);

}
