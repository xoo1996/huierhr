package org.radf.manage.menu.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface MenuFacade {
    /**
     * ��ѯ
     * @author syy
     * @date 2007-10-22
     * @param request
     * @return
     */
    public ResponseEnvelop findSc08(RequestEnvelop request);
	
	/**
	 * �����޸�
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifySc08(RequestEnvelop request);
	
	/**
	 * ɾ��
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delSc08(RequestEnvelop request);
	public ResponseEnvelop orderSc08(RequestEnvelop request);
}
