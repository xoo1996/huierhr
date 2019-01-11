package org.radf.plat.sieaf.transformer;

import java.util.Collection;

import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventError;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.exception.TransException;
import org.radf.plat.util.exception.WebException;

/**
 * <p>Title: Transformer</p>
 * <p>Description: this interface regulates the behavior of all Transformer instances </p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co., Ltd.</p>
 * <p>Company: LBS</p>
 * @author chenshuichao
 * @version 0.1
 */
public interface Transformer {
    /**
     * 解析xmlString，把结果放到hashMap中，并返回
     */
	Event doRequest(String xmlString) throws TransException;
	
    /**
     * 把response翻译成xml String
     */
    String doResponse(EventResponse eventResponse) throws WebException;
    
    /**
     * 把error翻译成一定格式的xml String
     */    
	String doError(EventError error);
    
    /**
     * 把Collection格式下funList翻译成规范xml格式String
     * @param funList       角色列表
     * @param versionList   服务端数据版本列表
     */
	String doLogin(Collection funList,Collection versionList);
    
    /** 初始化方法
     */
	void init(RequestEnvelopHead head);
}
