/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.process.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
/**
 * ���������
 */
public interface ApaFacade {	
	/**
     * ��������Ϣ���
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop verifyApa(RequestEnvelop request);	
    /**
     * ��������Ϣ��ʾ
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop printApa(RequestEnvelop request);	

    /**
     * ��������Ϣ����
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop saveApa(RequestEnvelop request);

    /**
     * ��������Ϣ���
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop modifyApa(RequestEnvelop request);
	
    /**
     * ��������Ϣɾ��
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop deleteApa(RequestEnvelop request);
}
