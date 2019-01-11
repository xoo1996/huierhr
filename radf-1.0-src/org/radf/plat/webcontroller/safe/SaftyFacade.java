/**
 * <p>����: ��ȫ�������ӿ�</p>
 * <p>˵��: SaftyFilter���õĹ������ӿ�</p>
 * <p>��Ŀ: Rapid Application Development Framework</p>
 * <p>��Ȩ: Copyright 2008 - 2017</p>
 * <p>ʱ��: 2005-11-1111:03:23</p>
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
     * ��ȫ���������˽ӿ�
     * 
     * @param hreq
     * @param hres
     * @param head
     */
    public boolean filter(FilterConfig config,HttpServletRequest hreq, HttpServletResponse hres,
            RequestEnvelopHead head) throws IOException, ServletException;
}
