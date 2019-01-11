/**
 * Rec0105Facade.java 2008/03/27
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
 * ��Ա��λ�Ƽ������ӿ�
 */
public interface Rec0105Facade {
	/**
	 * �Ƽ��ɹ� 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop RecommendFBSuccess(RequestEnvelop request);

	/**
	 * �Ƽ��ɹ� 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop RecommendFBhftj(RequestEnvelop request);

	/**
	 * �Ƽ�ʧ�� 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop RecommendFBLost(RequestEnvelop request);

	/**
	 * �Ƽ�ɾ�� 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop RecommendFBdel(RequestEnvelop request);

	/**
	 * ��ѯ�����Ƽ���ϸ��Ϣ 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewRec(RequestEnvelop request);

	/**
	 * �Ƽ�������ѯ 2008-2-28 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop findRecommendHistory(RequestEnvelop request);
}