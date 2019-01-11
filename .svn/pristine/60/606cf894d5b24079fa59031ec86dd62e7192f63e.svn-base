package org.radf.plat.sieaf.trans;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public interface TransAction {

	public void init(ServletContext context, HttpServletRequest request);

	public void preExec() throws PreExecException;

	public EventResponse exec(Event obj);

	public void postExec() throws PostExecException;

}
