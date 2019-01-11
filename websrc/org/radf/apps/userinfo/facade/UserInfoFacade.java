package org.radf.apps.userinfo.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface UserInfoFacade{
	/**
	 * 展示用户具体信息
	 * @param request
	 * @return
	 */
	public ResponseEnvelop addUser(RequestEnvelop request);	
	
	public ResponseEnvelop addUserEducate(RequestEnvelop request);	
	public ResponseEnvelop deleteUserEducate(RequestEnvelop request);	
	public ResponseEnvelop addUserSup(RequestEnvelop request);	
	public ResponseEnvelop addUserWork(RequestEnvelop request);	
	public ResponseEnvelop deleteUserWork(RequestEnvelop request);	
	public ResponseEnvelop addAccUser(RequestEnvelop request);	
	public ResponseEnvelop saveUserFamily(RequestEnvelop request);	
	public ResponseEnvelop deleteUserFamily(RequestEnvelop request);
	
	public ResponseEnvelop saveUserTrain(RequestEnvelop request);	
	public ResponseEnvelop deleteUserTrain(RequestEnvelop request);
	
	public ResponseEnvelop showDetail(RequestEnvelop request);	
	
	public ResponseEnvelop modifyUserInfo(RequestEnvelop request);	
	public ResponseEnvelop addAccSup(RequestEnvelop request);		
	public ResponseEnvelop delAccSup(RequestEnvelop request);	
}
