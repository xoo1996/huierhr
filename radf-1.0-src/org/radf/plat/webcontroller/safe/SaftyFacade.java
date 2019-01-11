/**
 * <p>标题: 安全过滤器接口</p>
 * <p>说明: SaftyFilter调用的过滤器接口</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-11-1111:03:23</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.plat.webcontroller.safe;

import java.io.IOException;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radf.plat.sieaf.envelop.RequestEnvelopHead;

public interface SaftyFacade {
    /**
     * 安全过滤器过滤接口
     * 
     * @param hreq
     * @param hres
     * @param head
     */
    public boolean filter(FilterConfig config,HttpServletRequest hreq, HttpServletResponse hres,
            RequestEnvelopHead head) throws IOException, ServletException;
}
