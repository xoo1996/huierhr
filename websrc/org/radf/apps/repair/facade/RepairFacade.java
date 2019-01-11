/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.repair.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ά�޹���
 */
public interface RepairFacade {

	/**
	 * ά�޻�����
	 */
	public ResponseEnvelop save(RequestEnvelop request);

	/**
	 * ά�޻���ѯ
	 */
	public ResponseEnvelop find(RequestEnvelop request);

	/**
	 * ά�޻�ɾ��
	 */
	public ResponseEnvelop remove(RequestEnvelop request);

	/**
	 * ά�޻��޸�
	 */
	public ResponseEnvelop modify(RequestEnvelop request);
	
	public ResponseEnvelop changeStatus(RequestEnvelop request);
	
	public ResponseEnvelop finish(RequestEnvelop request);
	
	public ResponseEnvelop batchChange(RequestEnvelop request);
	
	public ResponseEnvelop batchMakeshell(RequestEnvelop request);
	
	//��Ʊ
	public ResponseEnvelop kaip(RequestEnvelop request);
	public ResponseEnvelop getRepair(RequestEnvelop request);
	
}
