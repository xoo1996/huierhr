/**
 * EmployerFacade.java 2008/03/24
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
 * ��λ����
 */
public interface EmployerFacade {
	/**
	 * ������ѯ
	 * @param requestEnvelop
	 * @return
	 */
	 public ResponseEnvelop findCommon(RequestEnvelop request);
	/**
	 *  ���ӵ�λ������Ϣ
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop addEmp(RequestEnvelop request);
	/**
	 * �鿴��λ������Ϣ
	 * @param requestEnvelop
	 * @return
	 */
	public ResponseEnvelop retriveEmp(RequestEnvelop request);
	/**
	 * ���µ�λ������Ϣ
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop updateEmp(RequestEnvelop request);
	/**
	 * �����λ������Ϣ
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop alterEmp(RequestEnvelop request);
	/**
	 * ע����λ
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop writeOff(RequestEnvelop request);
	
	/**
	 * �ϲ���λ
	 * @param requestEnvelop
	 * @return 
	 */
	public ResponseEnvelop callPro(RequestEnvelop request);
	
	
	/**
	 * ��λɾ��
	 * @param requestEnvelop
	 * @return  
	 */
	public ResponseEnvelop delEmployer(RequestEnvelop request);
	/**
	 * ��֤��λ�ϲ�
	 * @param requestEnvelop
	 * @return  
	 */	
	public ResponseEnvelop validateUniteEmploy(RequestEnvelop request);
	
	
	
}
