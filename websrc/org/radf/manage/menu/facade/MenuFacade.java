package org.radf.manage.menu.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface MenuFacade {
    /**
     * ²éÑ¯
     * @author syy
     * @date 2007-10-22
     * @param request
     * @return
     */
    public ResponseEnvelop findSc08(RequestEnvelop request);
	
	/**
	 * ±£´æÐÞ¸Ä
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifySc08(RequestEnvelop request);
	
	/**
	 * É¾³ý
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delSc08(RequestEnvelop request);
	public ResponseEnvelop orderSc08(RequestEnvelop request);
}
