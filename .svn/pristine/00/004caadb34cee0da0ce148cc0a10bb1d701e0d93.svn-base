package org.radf.plat.webcontroller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.util.FacadeFactory;
import org.radf.plat.util.global.RequestNames;
import org.radf.plat.webcontroller.safe.SIEAFFilter;
import org.radf.plat.webcontroller.safe.SaftyFacade;

/**
 * SafltFilter
 * 安全校验组件
 * 可以通过重构LEAFFilter或SIEAFFilter实现不同的过滤功能
 * @author zqb
 * @version 1.0
 */

public class SaftyFilter implements Filter {

    private FilterConfig config;
    private SIEAFFilter sieaf = new SIEAFFilter();

    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    public void destroy() {
        this.config = null;
    }

    /**
     * @param ServletRequest
     * @param ServletResponse
     * @param FilterChain
     * @exception IOException
     * @exception ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain fc) throws IOException, ServletException {

        HttpServletRequest hreq = (HttpServletRequest) request;
        HttpServletResponse hres = (HttpServletResponse)response;
        // 如果前一个Filter发生错误, 本Filter不进行任何处理, 返回到调用者
        if (hreq.getAttribute(RequestNames.ERROR) != null) {
            fc.doFilter(request, response);
            return;
        }

        RequestEnvelopHead head = (RequestEnvelopHead) hreq
        .getAttribute(RequestNames.ENV_HEAD);
        boolean ret = true;
        if(head!=null){ //sieaf模式
            ret = sieaf.filter(config,hreq,hres,head);
        }else{  //leaf模式
            try{
                SaftyFacade facade = (SaftyFacade)FacadeFactory.getService("Filter"); 
                ret = facade.filter(config,hreq,hres,head);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        if(ret){
            fc.doFilter(request, response);
        }
    }
}
