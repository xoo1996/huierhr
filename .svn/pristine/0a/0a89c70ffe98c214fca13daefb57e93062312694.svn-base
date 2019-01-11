/**
 * <p>标题: 日志处理接口类</p>
 * <p>说明: Facade</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-5-2911:05:57</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.manage.logMessage.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface LogFacade {
    
    /**
     * 按FunctionID查询日志信息总控方法 <br>
     * @param request HashMap  key： functionid
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findByFunctionid(RequestEnvelop request);

    /**
     * 按Message模糊查询日志信息总控方法
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findByMessage(RequestEnvelop request);

    /**
     * 按Msgdate时间段查询日志信息总控方法
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findByMsgdate(RequestEnvelop request);

    /**
     * 按UserID查询日志信息总控方法
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findByUser(RequestEnvelop request);

    /**
     * 查询单个日志信息总控方法 <br>
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop findLogMessage(RequestEnvelop request);

    /**
     * 删除日志信息总控方法 <br>
     * @param request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop removeLogMessage(RequestEnvelop request);

}
