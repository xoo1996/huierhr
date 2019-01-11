/**
 * Rec0703Facade.java 2008/03/27
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author lwd
 * @version 1.0
 */
package org.radf.apps.recommendation.employassembly.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface Rec0703Facade {

	/**
	 * ��ʼ�������Ƹ������Ϣ
	 * 
	 * @author Y.J
	 * @date 2008-2-20 18:25:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop initddqs(RequestEnvelop request);

	/**
	 * ���� �����Ƹ������Ϣ
	 * 
	 * @author Y.J
	 * @date 2008-3-19 12:50:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop saveqs(RequestEnvelop request);

	/**
	 * ���еĲ�ѯ
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:13:07
	 * @param request
	 * @return
	 */
	public ResponseEnvelop search(RequestEnvelop request);

	/**
	 * �޸�
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:22:08
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifyCommon(RequestEnvelop request);

	/**
	 * ɾ��
	 * 
	 * @author Y.J
	 * @date 2008-3-19 13:22:15
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delete(RequestEnvelop request);
}
