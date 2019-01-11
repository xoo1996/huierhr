/**
 * Rec06Facade.java 2008/04/08
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.recommendation.statistic.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * ְҵ���ܹ����������
 */
public interface Rec06Facade {
	/**
	 * ��������
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop born(RequestEnvelop request);

	/**
	 * ������ʾ
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewReport(RequestEnvelop request);
	
	/**
	 * �ϱ�������ʾ
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop viewRt98(RequestEnvelop request);

	/**
	 * �ϱ������޸�
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop saveRt98(RequestEnvelop request);

	/**
	 * ������
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop saveReport(RequestEnvelop request);
	
	/**
	 * �����ϱ�
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop upcastReport(RequestEnvelop request);
	/**
	 * �������
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop born_hz(RequestEnvelop request);
	

}
