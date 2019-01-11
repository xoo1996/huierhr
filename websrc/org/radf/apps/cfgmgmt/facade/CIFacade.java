/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 ��Ŀ: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.cfgmgmt.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
/**
 * ���������
 */
public interface CIFacade {	
    /**
     * ��������Ϣ��ʾ
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop printCI(RequestEnvelop request);	

    /**
     * ��������Ϣ����
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop saveCI(RequestEnvelop request);

    /**
     * ��������Ϣ���
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop modifyCI(RequestEnvelop request);
	
    /**
     * ��������Ϣɾ��
	 * @param requestEnvelop
	 * @return
     */
	public ResponseEnvelop deleteCI(RequestEnvelop request);
}
