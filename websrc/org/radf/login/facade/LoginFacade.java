package org.radf.login.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface LoginFacade {
	/**
	 * 用户登录验证
	 * 
	 * @param requeset
	 * @return
	 */
	public ResponseEnvelop userLogin(RequestEnvelop request);

	/**
	 * 用户登录注销
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop logout(RequestEnvelop request);
}
