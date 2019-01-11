package org.radf.manage.department.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface DepartmentFacade {

	/**
	 * ��ȡ���л����Ϳ�����Ϣ
	 */
	public ResponseEnvelop findsc01andsc05(RequestEnvelop request);
	/**
	 * ���ӻ�����Ϣ
	 */
	public ResponseEnvelop addSc01(RequestEnvelop request);
	/**
	 * ���ӿ�����Ϣ
	 */
	public ResponseEnvelop addSc04(RequestEnvelop request);
	/**
	 * ��ȡ���л����Ϳ�����Ϣ
	 */
	public ResponseEnvelop findsc01andsc04(RequestEnvelop request);
	/**
	 * ��ȡ������Ϣ
	 */
	public ResponseEnvelop findsc04(RequestEnvelop request);
	
	/**
	 * Ϊ����ͳ�ƻ�ȡ������Ϣ
	 */
	public ResponseEnvelop findSc01(RequestEnvelop request);
	/**
	 * �����޸�sc01������sc03
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifySc01NSc03(RequestEnvelop request);
}
