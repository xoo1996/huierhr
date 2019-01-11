/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.product.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ��Ʒ�������
 */
public interface ProductFacade {

	/**
	 * ��ѯ��Ʒ����
	 */
	public ResponseEnvelop query(RequestEnvelop request);
	
	/**
	 * ��ѯ��Ʒ����list
	 */
	public ResponseEnvelop findBySQL(RequestEnvelop request);

	/**
	 * ��Ʒ������ʾ
	 */
	public ResponseEnvelop print(RequestEnvelop request);
	
	public ResponseEnvelop printPcl(RequestEnvelop request);
	
	/**
	 * ��������ʾ
	 */
	public ResponseEnvelop print1(RequestEnvelop request);

	/**
	 * ��Ʒ���뱣��
	 */
	public ResponseEnvelop save(RequestEnvelop request);
	
	/**
	 * ����������
	 */
	public ResponseEnvelop save1(RequestEnvelop request);
	
	/**
	 * ���������Ϣ����
	 */
	public ResponseEnvelop save2(RequestEnvelop request);
	
	/**
	 * ����¼��������������Ϣ
	 */
	public ResponseEnvelop saveDetail(RequestEnvelop request);
	
	public ResponseEnvelop savePcl(RequestEnvelop request);
	
	
	/**
	 * ��Ʒ�����޸�
	 */
	public ResponseEnvelop modify(RequestEnvelop request);
	
	/**
	 * �������޸�
	 */
	public ResponseEnvelop modify1(RequestEnvelop request);
	
	/**
	 * ��Ʒ�����޸�
	 */
	public ResponseEnvelop modifyPcl(RequestEnvelop request);
	/**
	 * ���������������޸ı���
	 * @param request
	 * @return
	 */
	public ResponseEnvelop batchSave(RequestEnvelop request);

	/**
	 * ��Ʒ����ɾ��
	 */
	public ResponseEnvelop delete(RequestEnvelop request);
	
	public ResponseEnvelop deletePcl(RequestEnvelop request);
	
	/**
	 * ɾ��������
	 */
	public ResponseEnvelop delete1(RequestEnvelop request);
	
	/**
	 * ɾ������ͺ�
	 */
	public ResponseEnvelop delete2(RequestEnvelop request);
	
	/**
	 * ���Ŀ���
	 * @param request
	 * @return
	 */
	public ResponseEnvelop update(RequestEnvelop request);
	
	/**
	 * ���ʱ���
	 */
	public ResponseEnvelop savedis(RequestEnvelop request);
	
	/**
	 * �����޸Ŀ���
	 */
	public ResponseEnvelop disupdate(RequestEnvelop request);
	
	
	
	public ResponseEnvelop queryProidByClass(RequestEnvelop request);
	public ResponseEnvelop queryProidByClass1(RequestEnvelop request);//��ѯ����ͺ�
	
	
	public ResponseEnvelop queryParts(RequestEnvelop request);
	
	public ResponseEnvelop queryClasses(RequestEnvelop request);
	
	
	public ResponseEnvelop queryPanels(RequestEnvelop request);
	
	
	public ResponseEnvelop modTypDis(RequestEnvelop request);
	
	public ResponseEnvelop modProDis(RequestEnvelop request);
	
}
