/**
* <p>����: �汾���½����</p>
* <p>˵��: </p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-8-2613:57:54</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface VersionFacade {
    /**
     * ��ȡ���µ�����
     * @param request
     * @return
     */
    public ResponseEnvelop updateVersion(RequestEnvelop request);
    /**
     * ��ȡ�ͻ��˳�������°汾��
     * @param request
     * @return
     */
    public ResponseEnvelop getApplicationVersion(RequestEnvelop request);    
}
