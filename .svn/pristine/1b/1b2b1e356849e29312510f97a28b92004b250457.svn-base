package org.radf.manage.department.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface DepartmentFacade {

	/**
	 * 获取所有机构和科室信息
	 */
	public ResponseEnvelop findsc01andsc05(RequestEnvelop request);
	/**
	 * 增加机构信息
	 */
	public ResponseEnvelop addSc01(RequestEnvelop request);
	/**
	 * 增加科室信息
	 */
	public ResponseEnvelop addSc04(RequestEnvelop request);
	/**
	 * 获取所有机构和科室信息
	 */
	public ResponseEnvelop findsc01andsc04(RequestEnvelop request);
	/**
	 * 获取科室信息
	 */
	public ResponseEnvelop findsc04(RequestEnvelop request);
	
	/**
	 * 为机构统计获取机构信息
	 */
	public ResponseEnvelop findSc01(RequestEnvelop request);
	/**
	 * 保存修改sc01并新增sc03
	 * @param request
	 * @return
	 */
	public ResponseEnvelop modifySc01NSc03(RequestEnvelop request);
}
