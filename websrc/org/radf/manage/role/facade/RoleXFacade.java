package org.radf.manage.role.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface RoleXFacade {
	/**
	 *���ݽ�ɫ��Ż�ȡһ���˵���Ϣ
	 */
	public ResponseEnvelop findsc08bybsc016(RequestEnvelop request);
	/**
	 *�����ɫȨ����Ϣ
	 */
	public ResponseEnvelop saveFunctionList(RequestEnvelop request);
	/**
	 *���ݽ�ɫ�����Ա
	 */
	public ResponseEnvelop findsc06bybsc014(RequestEnvelop request);
	/**
	 *�����ɫ��Ϣ
	 */
	public ResponseEnvelop saveSc06(RequestEnvelop request);
	/**
	 *ɾ����ɫ��Ϣ
	 */
	public ResponseEnvelop deleteSc06(RequestEnvelop request);
	
}
