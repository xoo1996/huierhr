package org.radf.manage.user.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface UserFacade {
    /**
     * 查询
     * @author syy
     * @date 2007-10-22
     * @param request
     * @return
     */
    public ResponseEnvelop findSc05(RequestEnvelop request);
    
	/**
	 * 查找表Sc04,Sc06
	 * 
	 * @author syy
	 * @date 2007-10-23
	 * @param request
	 * @return
	 */
    public ResponseEnvelop findSc04NSc06(RequestEnvelop request);
	
	/**
	 * 保存修改
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifySc05(RequestEnvelop request);
	
	/**
	 * 删除
	 * @param request
	 * @return
	 */
	public ResponseEnvelop delSc05(RequestEnvelop request);
	
	/**
	 * 密码重置
	 * @param request
	 * @return
	 */
	public ResponseEnvelop alterPwd(RequestEnvelop request);
	/**
	 * 初始化Ukey
	 */
	public ResponseEnvelop initKey(RequestEnvelop request);
	/**
	 * 挂失
	 */
	public ResponseEnvelop loss(RequestEnvelop request);

	public ResponseEnvelop ukeyOperate(RequestEnvelop request);

}
