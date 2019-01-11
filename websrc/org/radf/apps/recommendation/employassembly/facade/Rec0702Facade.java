/**
 * Rec0702Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employassembly.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface Rec0702Facade {
	/**
	 * ��ѯ��λ������Ϣ����Ƹ��Ϣ 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop toNext(RequestEnvelop request);

	/**
	 * Ϊ��ʼ����Ƹ��λ�޸Ľ����ѯ���� 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modEmptyPost(RequestEnvelop request);

	/**
	 * Ϊ��Ƹ��λ�޸� 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modsaveEmptyPost(RequestEnvelop request);

	/**
	 * ���ӿո���Ϣ���ո�������Ϣ 2008-2-21 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop addEmptyPost(RequestEnvelop request);

	/**
	 * ɾ��һ����λ��Ϣ 2008-2-22 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delEmptyPost(RequestEnvelop request);

	/**
	 * ɾ��һ����λ��Ϣ 2008-2-22 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop deleteEmptyPost(RequestEnvelop request);

	/**
	 * �鿴ĳһ����λ��Ƹ��Ϣ 2008-2-22 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewEmployInvite(RequestEnvelop request);
	/**
	 * �鿴�����Ƹ��Ϣ 2008-2-22 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewEmployInviteDhzp(RequestEnvelop request);
	/**
	 * �޸�ί����Ƹ��Ϣ 2008-2-22 by lwd
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modEmployInvite(RequestEnvelop request);
}
