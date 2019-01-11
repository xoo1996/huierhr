package org.radf.plat.webcontroller;

import java.io.BufferedReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.HeadParser;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.event.EventError;
import org.radf.plat.util.exception.WebException;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.util.global.RequestNames;
/**
 * 入口数据解析器
 * 如果传入的是XML格式的社保交易，采用XML解析，否则web模式原来格式传递
 * @author zqb
 * @version 1.0
 */
public class HeadFilter implements Filter {

	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
		throws java.io.IOException, javax.servlet.ServletException {
        
        if(!GlobalNames.SERVICE_FRAME.equalsIgnoreCase("2")){
            req.getParameterNames();    //zqb注：此行没有任何意义，但是不加会导致getReader后所有入口参数丢失，原因不明
            BufferedReader curReader = req.getReader();
            String strtmp = curReader.readLine();
            
            if(strtmp!=null&&strtmp.trim().startsWith("<?xml")){//sieaf模式
                StringBuffer strBuf = new StringBuffer();
                while (strtmp != null) {
                    strBuf.append(strtmp);
                    strtmp = curReader.readLine();
                }
                String str = strBuf.toString().trim();
                strBuf = null;
                //str=new String(str.getBytes("ISO8859_1"),"GBK");
                sieaf(req,str);
            }
        }
        fc.doFilter(req, res);
	}
    
    private void sieaf(ServletRequest req,String str){
        if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
            System.out.println("===== Beging Input String from request in doFilter <HeadFilter.class>=====");
            System.out.println(str);
            System.out.println("===== End Input String from request in doFilter <HeadFilter.class> ===== ");
        }
        HttpServletRequest hreq = (HttpServletRequest) req;
        RequestEnvelopHead head = null;
        try {
            HeadParser parser = new HeadParser();
            head = parser.parserHead(str);
        } catch (WebException ex) {
            EventError eventError = new EventError();
            eventError.setErrorCode(5402);
            eventError.setErrorMsg(GlobalErrorMsg.SYS_XML_FORMAT);
            req.setAttribute(RequestNames.ERROR, eventError);
            LogHelper log = new LogHelper(this.getClass().getName());
            log.log(null, GlobalNames.DEBUG_LOG, "5402:"+GlobalErrorMsg.SYS_XML_FORMAT);
            log.log(null, 5402, GlobalErrorMsg.SYS_XML_FORMAT);
            log = null;
        }

        req.setAttribute(RequestNames.XML_STRING, str);
        req.setAttribute(RequestNames.ENV_HEAD, head);        
    }
}