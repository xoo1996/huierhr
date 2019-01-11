/**
* <p>����: SIEAFģʽACTION��ͳһ�ӿ���</p>
* <p>˵��: </p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-5-2416:05:41</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.util.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.exception.AppException;

public interface Action {
    /**
     * @param context
     * @param session
     */
    public void init(ServletContext context, HttpSession session);
    
    /**
     * 
     * @param facade
     * @return
     * @throws AppException
     */
    public Object getService(String facade) throws AppException;
    
    /**
     * 
     * @param ev
     * @return
     */
    public EventResponse perform(Event ev);
}
