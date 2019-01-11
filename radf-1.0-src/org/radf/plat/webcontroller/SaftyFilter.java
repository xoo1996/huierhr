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
 * ��ȫУ�����
 * ����ͨ���ع�LEAFFilter��SIEAFFilterʵ�ֲ�ͬ�Ĺ��˹���
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
        // ���ǰһ��Filter��������, ��Filter�������κδ���, ���ص�������
        if (hreq.getAttribute(RequestNames.ERROR) != null) {
            fc.doFilter(request, response);
            return;
        }

        RequestEnvelopHead head = (RequestEnvelopHead) hreq
        .getAttribute(RequestNames.ENV_HEAD);
        boolean ret = true;
        if(head!=null){ //sieafģʽ
            ret = sieaf.filter(config,hreq,hres,head);
        }else{  //leafģʽ
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
