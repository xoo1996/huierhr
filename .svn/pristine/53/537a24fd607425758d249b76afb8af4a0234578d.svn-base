/**
 * <p>标题: </p>
 * <p>说明: </p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-5-2611:52:41</p>
 *
 * @author Administrator
 * @version 1.0
 */
package org.radf.manage.role.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface FunctionFacade {
    /**
     * 生成功能变动日志
     * @param request
     * @return
     */
    public ResponseEnvelop findNewVersion(RequestEnvelop request);
    /**
     * 生成一条sysfunction记录
     */
    public ResponseEnvelop createFunction(RequestEnvelop request);

    /**
     * 修改sysfunction记录
     */
    public ResponseEnvelop modifyFunction(RequestEnvelop request);

    /**
     * 删除sysFunction及关联的sysFuncControlMap记录
     */
    public ResponseEnvelop removeFunction(RequestEnvelop request);

    /**
     * 根据主键查询sysfunction记录
     */
    public ResponseEnvelop findFunctionByPK(RequestEnvelop request);

    /**
     * 获取指定SysFunction的父SysFunction
     */
    public ResponseEnvelop getChildPairs(RequestEnvelop request);

    // public ResponseEnvelop createControl(RequestEnvelop request)
    // throws RemoteException;

    // public ResponseEnvelop modifyControl(RequestEnvelop request)
    // throws RemoteException;

    // public ResponseEnvelop removeControl(RequestEnvelop request)
    // throws RemoteException;

    // public ResponseEnvelop createMap(RequestEnvelop request)
    // throws RemoteException;

    // public ResponseEnvelop removeMap(RequestEnvelop request)
    // throws RemoteException;

    // public ResponseEnvelop findControlByPK(RequestEnvelop request)
    // throws RemoteException;
}
