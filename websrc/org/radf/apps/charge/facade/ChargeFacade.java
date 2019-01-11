package org.radf.apps.charge.facade;


import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * �շѹ���
 */
public interface ChargeFacade {

	/**
	 * �շѻ�����Ϣ����
	 */
	public ResponseEnvelop save(RequestEnvelop request);
	
	/**
	 * ��ͨ��Ʒ�շѻ�����Ϣ����
	 */
	public ResponseEnvelop save2(RequestEnvelop request);
	/**
	 * �������շѱ���
	 */
	public ResponseEnvelop save3(RequestEnvelop request);
	
	public ResponseEnvelop checkNormalDisc(RequestEnvelop request);
	
	/**
	 * ��ͨ��Ʒ�շ�
	 */
	public ResponseEnvelop charge1(RequestEnvelop request);

	/**
	 * ������Ʒ�շ���Ϣ����
	 */
	public ResponseEnvelop charge2(RequestEnvelop request);
	
	/**
	 * ά����Ʒ�շ���Ϣ����
	 */
	public ResponseEnvelop charge3(RequestEnvelop request);
	
	/**
	 * ������Ʒ�շ���Ϣ����
	 */
	public ResponseEnvelop saveCustomizedRec(RequestEnvelop request);
	
	/**
	 * ��ͨ��Ʒ�շ���Ϣ����
	 */
	public ResponseEnvelop saveNomRec(RequestEnvelop request);
	
	
	public ResponseEnvelop commitNomRec(RequestEnvelop request);
	
//	public ResponseEnvelop modifyNomRec(RequestEnvelop request);
	
	public ResponseEnvelop exaNomRec(RequestEnvelop request);
	
	public ResponseEnvelop exaCusRec(RequestEnvelop request);
	/**
	 * ������Ʒ�շ���Ϣ����
	 */
	public ResponseEnvelop commitCustomizedRec(RequestEnvelop request);
	/**
	 * �˻��շ���Ϣ����
	 */
	public ResponseEnvelop charge4(RequestEnvelop request);
	
	
	/**
	 * ������Ʒ�շѵ���շѺ���ʾ��ҳ����Ϣ
	 */
	public ResponseEnvelop show(RequestEnvelop request);
	
	/**
	 * ������Ʒ�շѵ���˻��˷Ѻ���ʾ��ҳ����Ϣ
	 */
	public ResponseEnvelop show1(RequestEnvelop request);
	
	/**
	 * ������Ʒ�շ���Ϣ��ʾ
	 */
	public ResponseEnvelop print(RequestEnvelop request);
	
	
	/**
	 * ά���շ���Ϣ��ʾ
	 */
	public ResponseEnvelop print1(RequestEnvelop request);
	
	/**
	 * ��ͨ�շ���Ϣ��ʾ
	 */
	public ResponseEnvelop print2(RequestEnvelop request);
	
	/**
	 * �˻��շ���Ϣ��ʾ
	 */
	public ResponseEnvelop print3(RequestEnvelop request);
	
	/**
	 * ��ͨ�շ�ʱ�����û���Ϣ
	 * 
	 */
	public ResponseEnvelop addClient(RequestEnvelop request);
	
	
	public ResponseEnvelop norChgRecDetail(RequestEnvelop request);
	
	/**
	 * �շѻ�����Ϣ�޸�
	 */
	/*public ResponseEnvelop modify(RequestEnvelop request);

	*//**
	 * �շ���ϸ��������
	 *//*
	public ResponseEnvelop saveDetail(RequestEnvelop request);

	*//**
	 * �շ���ϸ�����޸�
	 *//*
	public ResponseEnvelop modifyDetail(RequestEnvelop request);

	*//**
	 * �շ���Ϣɾ��
	 *//*
	public ResponseEnvelop delete(RequestEnvelop request);

	public ResponseEnvelop saveNew(RequestEnvelop request);
	*//**
	 * �˻�
	 *//*
	public ResponseEnvelop recoil(RequestEnvelop request);*/
}