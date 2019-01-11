/**
* <p>标题: 版本更新借口类</p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-8-2613:57:54</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface VersionFacade {
    /**
     * 获取更新的数据
     * @param request
     * @return
     */
    public ResponseEnvelop updateVersion(RequestEnvelop request);
    /**
     * 获取客户端程序的最新版本号
     * @param request
     * @return
     */
    public ResponseEnvelop getApplicationVersion(RequestEnvelop request);    
}
