/**
 * ResumesFacade.java 2008/03/24
 * 
 * Copyright (c) 2008 ��Ŀ: Rapid Application Development Framework
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.basicinfo.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
/**
 * ��������
 */
public interface ResumesFacade
{
    /**
     * ������˼���
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop saveResumes(RequestEnvelop request);
    
    /**
     * ɾ�����˼���
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop removeResumes(RequestEnvelop request);
    
    /**
     * ��ѯ���˼�����ϸ��Ϣ
	 * @param requestEnvelop
	 * @return
     */
    public ResponseEnvelop findResumes(RequestEnvelop request);
}
