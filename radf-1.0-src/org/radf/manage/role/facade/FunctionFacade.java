/**
 * <p>����: </p>
 * <p>˵��: </p>
 * <p>��Ŀ: Rapid Application Development Framework</p>
 * <p>��Ȩ: Copyright 2008 - 2017</p>
 * <p>ʱ��: 2005-5-2611:52:41</p>
 *
 * @author Administrator
 * @version 1.0
 */
package org.radf.manage.role.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface FunctionFacade {
    /**
     * ���ɹ��ܱ䶯��־
     * @param request
     * @return
     */
    public ResponseEnvelop findNewVersion(RequestEnvelop request);
    /**
     * ����һ��sysfunction��¼
     */
    public ResponseEnvelop createFunction(RequestEnvelop request);

    /**
     * �޸�sysfunction��¼
     */
    public ResponseEnvelop modifyFunction(RequestEnvelop request);

    /**
     * ɾ��sysFunction��������sysFuncControlMap��¼
     */
    public ResponseEnvelop removeFunction(RequestEnvelop request);

    /**
     * ����������ѯsysfunction��¼
     */
    public ResponseEnvelop findFunctionByPK(RequestEnvelop request);

    /**
     * ��ȡָ��SysFunction�ĸ�SysFunction
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
