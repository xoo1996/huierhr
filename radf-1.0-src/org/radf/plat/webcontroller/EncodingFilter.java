package org.radf.plat.webcontroller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.radf.plat.util.global.GlobalNames;

/**
 * EncodeingFilter
 * ±àÂë¹ýÂËÆ÷
 * @author zqb
 * @version 1.0
 */
public class EncodingFilter implements Filter {

    protected String encoding;

    protected FilterConfig filterConfig;

    public EncodingFilter() {
        encoding = null;
        filterConfig = null;
    }

    public void destroy() {
        encoding = null;
        filterConfig = null;
    }

    public void doFilter(ServletRequest servletrequest,
            ServletResponse servletresponse, FilterChain filterchain)
            throws IOException, ServletException {
        if (encoding != null)
            servletrequest.setCharacterEncoding(encoding);
        else
            servletrequest.setCharacterEncoding(GlobalNames.DEFAULT_ENCODING);
        filterchain.doFilter(servletrequest, servletresponse);
    }

    public void init(FilterConfig filterconfig) throws ServletException {
        filterConfig = filterconfig;
        encoding = filterconfig.getInitParameter(GlobalNames.SERVICE_ENCODING);
    }
}
