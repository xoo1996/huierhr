package org.radf.manage.trans.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
/**
* 交易日志记录Facade
* @author zqb
* @version 1.0
 */
public interface TransFacade{
    /**
     *生成SYSTRANSEDEF记录
     */
    ResponseEnvelop createTrans(RequestEnvelop request);
    /**
     *修改SYSTRANSEDEF记录
     */
    ResponseEnvelop modifyTrans(RequestEnvelop request);
    /**
     * 删除SYSTRANSEDEF记录
     */
    ResponseEnvelop removeTrans(RequestEnvelop request);
    /**
     * 查询SYSTRANSEDEF记录
     */
    ResponseEnvelop findTransByPK(RequestEnvelop request);
    /**
     *查询所有SYSTRANSEDEF记录
     */
    ResponseEnvelop findAllTrans(RequestEnvelop request);
    
    
    
    /**
     * 查询SYSTRANSVASTINPUT、SYSTRANSVASTOUT记录
     */
    ResponseEnvelop findInOutputByPK(RequestEnvelop request);
    
    
    /**
     * 查询logid小于指定值的SYSTRANSLOG记录
     */
    ResponseEnvelop findTransLogBack(RequestEnvelop request);
    /**
     * 查询logid大于指定值的SYSTRANSLOG记录
     */
    ResponseEnvelop findTransLogForward(RequestEnvelop request);
    /**
     * 查询SYSTRANSLOG记录
     */
    ResponseEnvelop findTransLogByPK(RequestEnvelop request);
    /**
     * 根据Time查询SYSTRANSLOG记录,分页
     */
    ResponseEnvelop findTransLogByTime(RequestEnvelop request);
    /**
     * 根据TransName查询SYSTRANSLOG记录,分页
     */
    ResponseEnvelop findTransLogByTransName(RequestEnvelop request);
    /**
     * 根据登录名LoginName查询SYSTRANSLOG记录,分页
     */
    ResponseEnvelop findTransLogByLoginName(RequestEnvelop request);
    /**
     * 根据Status查询SYSTRANSLOG记录,分页
     */
    ResponseEnvelop findTransLogByStatus(RequestEnvelop request);
}