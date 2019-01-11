/**
 * CIFacade.java 2009/03/7
 * 
 * Copyright (c) 2009 ÏîÄ¿: ITSM
 * All rights reserved.<br>
 * @author Tony
 * @version 1.0
 */
package org.radf.apps.contract.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;


public interface ContractFacade {

	ResponseEnvelop addContract(RequestEnvelop request);

	ResponseEnvelop saveContract(RequestEnvelop request);

	ResponseEnvelop delContract(RequestEnvelop request);
	
}
