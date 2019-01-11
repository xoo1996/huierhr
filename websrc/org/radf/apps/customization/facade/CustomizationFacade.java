/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.customization.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ���������
 */
public interface CustomizationFacade {
	/**
	 * ��������Ϣ��ʾ
	 * 
	 * @param requestEnvelop
	 * @return
	 */
	public ResponseEnvelop printCI(RequestEnvelop request);

	/**
	 * ���ƻ���Ϣ����
	 */
	public ResponseEnvelop save(RequestEnvelop request);

	/**
	 * ��������Ϣ���
	 * 
	 * @param requestEnvelop
	 * @return
	 */
	public ResponseEnvelop modifyCI(RequestEnvelop request);

	/**
	 * ���ƻ���Ϣɾ��
	 */
	public ResponseEnvelop delete(RequestEnvelop request);

	/**
	 * ����
	 */
	public ResponseEnvelop updateArrangement(RequestEnvelop request);

	/**
	 * �ʼ�
	 */
	public ResponseEnvelop updateTest(RequestEnvelop request);

	/**
	 * ȷ���������
	 */
	public ResponseEnvelop complete(RequestEnvelop request);

	public ResponseEnvelop changeStatus(RequestEnvelop request);

	/**
	 * ���ƻ���Ϣ��ѯ
	 */
	public ResponseEnvelop find(RequestEnvelop request);

	public ResponseEnvelop batchChange(RequestEnvelop request);
}
