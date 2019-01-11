/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.order.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ��������
 */
public interface OrderFacade {

	/**
	 * ����������Ϣ����
	 */
	public ResponseEnvelop save(RequestEnvelop request);

	/**
	 * ����������Ϣ�޸�
	 */
	public ResponseEnvelop modify(RequestEnvelop request);

	/**
	 * ������ϸ��������
	 */
	public ResponseEnvelop saveDetail(RequestEnvelop request);

	/**
	 * ������ϸ�����޸�
	 */
	public ResponseEnvelop modifyDetail(RequestEnvelop request);

	/**
	 * ������Ϣɾ��
	 */
	public ResponseEnvelop delete(RequestEnvelop request);

	public ResponseEnvelop saveNew(RequestEnvelop request);
	/**
	 * �˻�
	 */
	public ResponseEnvelop recoil(RequestEnvelop request);
	
	/**
	 * ά�޼�¼��ѯ
	 */
	public ResponseEnvelop find(RequestEnvelop request);
	
	public ResponseEnvelop print(RequestEnvelop request);
	
	public ResponseEnvelop printCustomDetail(RequestEnvelop request);
	
	public ResponseEnvelop printClient(RequestEnvelop request);
	
	public ResponseEnvelop examine(RequestEnvelop request);
	
	public ResponseEnvelop rollBack(RequestEnvelop request);
	
	public ResponseEnvelop saveCustomOrder(RequestEnvelop request);
	
	public ResponseEnvelop commit(RequestEnvelop request);
	
	public ResponseEnvelop update(RequestEnvelop request);
	
	public ResponseEnvelop saveCusRep(RequestEnvelop request);
	
	//��ʾ��ģά�޶�������
	public ResponseEnvelop printEarRep(RequestEnvelop request);
	
	public ResponseEnvelop printCusRep(RequestEnvelop request);
	
	public ResponseEnvelop updateCusRep(RequestEnvelop request);
	
	public ResponseEnvelop updateEar(RequestEnvelop request);
	
	public ResponseEnvelop updateEarRep(RequestEnvelop request);
	
	public ResponseEnvelop commitCusRep(RequestEnvelop request);
	
	public ResponseEnvelop printEarDetail(RequestEnvelop request);
	
	public ResponseEnvelop printNomDetail(RequestEnvelop request);
	
	public ResponseEnvelop printCusRec(RequestEnvelop request);
	
	public ResponseEnvelop saveEar(RequestEnvelop request);
	
	public ResponseEnvelop saveNom(RequestEnvelop request);
	
	public ResponseEnvelop updateNom(RequestEnvelop request);
	
	public ResponseEnvelop saveDevRep(RequestEnvelop request);
	
	public ResponseEnvelop saveEarRep(RequestEnvelop request);
	
	public ResponseEnvelop queryFoldt(RequestEnvelop request);
	
	public ResponseEnvelop queryMinDis(RequestEnvelop request);
	
	public ResponseEnvelop examineDis(RequestEnvelop request);
	
	public ResponseEnvelop Disback(RequestEnvelop request);
	
	public ResponseEnvelop checkStoAmount(RequestEnvelop request);
	
	public ResponseEnvelop bill(RequestEnvelop request);
}
