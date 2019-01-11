/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.store.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ������
 */
public interface StoreFacade {

	/**
	 * ��������Ϣ����
	 */
	public ResponseEnvelop saveStore(RequestEnvelop request);

//	/**
//	 * ����������Ϣ�޸�
//	 */
//	public ResponseEnvelop modify(RequestEnvelop request);
//
//	/**
//	 * ������ϸ��������
//	 */
//	public ResponseEnvelop saveDetail(RequestEnvelop request);
//
//	/**
//	 * ������ϸ�����޸�
//	 */
	public ResponseEnvelop modifyState(RequestEnvelop request,int sta);

//	/**
//	 * ������Ϣɾ��
//	 */
//	public ResponseEnvelop delete(RequestEnvelop request);
//
//	public ResponseEnvelop saveNew(RequestEnvelop request);
//	/**
//	 * �˻�
//	 */
//	public ResponseEnvelop recoil(RequestEnvelop request);
//	
//	/**
//	 * ά�޼�¼��ѯ
//	 */
//	public ResponseEnvelop find(RequestEnvelop request);
	
	/**
	 * ���
	 */
	public ResponseEnvelop allocate(RequestEnvelop request);
	/**
	 * �̿�
	 */
	public ResponseEnvelop panku(RequestEnvelop request);
	
}
