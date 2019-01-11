package org.radf.plat.sieaf.trans;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.radf.manage.trans.entity.SysTransLog;
import org.radf.manage.trans.entity.SysTranseDef;
import org.radf.manage.trans.imp.TransIMP;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.util.global.RequestNames;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author KentKong
 * @version 1.0
 */

public abstract class TransActionSupport
	implements java.io.Serializable, TransAction {
	protected ServletContext context;
	protected HttpServletRequest request;
	private LogHelper logger = null;
    private TransIMP transIMP = new TransIMP();

	public TransActionSupport() {
		logger = new LogHelper(this.getClass().getName());
	}

	public void init(ServletContext context, HttpServletRequest request) {
		this.request = request;
		this.context = context;
	}

	/**
	 * This method will record the transaction 's input data into db , and
	 * create a TransLog to track the transaction's status.
	 * @throws PreExecException
	 */
	public void preExec() throws PreExecException {
		SysTranseDef transDef = null;
		RequestEnvelopHead requestHead = null;
		SysTransLog transLog = null;

		String transID = null;
		String transName = null;
		String loginName = null;
		String startTime = null;
		String sessionID = null;
		String transType = GlobalNames.TRANS_TYPE_BLOB;
		String status = null;
		try {

			requestHead =
				(RequestEnvelopHead) request.getAttribute(
					RequestNames.ENV_HEAD);


			transID = requestHead.getFunctionID();

			TransDefCache cache =
				(TransDefCache) context.getAttribute(
					GlobalNames.TRANSACTION_DEF_CACHE);

			transDef = cache.getSysTranseDef(transID);
			if (transDef == null)
				throw new PreExecException("没有此交易");

            loginName = requestHead.getLoginName();
			sessionID = requestHead.getSessionID();

			startTime = DateUtil.converToString(DateUtil.getSystemCurrentTime(),null);

            transLog = new SysTransLog();
			transLog.setTransId(transID);
			transLog.setTransName(transDef.getTransName());
			transLog.setLoginName(loginName);
			transLog.setStartTime(startTime);
			transLog.setSessionId(sessionID);
			transLog.setStatus(TransUtil.BEGIN);
			transLog.setTimeoutTime(transDef.getTimeOut());
            
            String inputString =
                (String) request.getAttribute(RequestNames.XML_STRING);
            
            transLog = transIMP.createTransLog(transLog,inputString);

			request.setAttribute(RequestNames.TRANS_LOG, transLog);
		} catch (AppException ae) {
			if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				ae.printStackTrace();
			}
			throw new PreExecException(ae.getMessage());
		} catch (Exception exx) {
			if (GlobalNames.DEBUG_OUTPUT_FLAG)
				exx.printStackTrace();
             String s = exx.getMessage();
             if(s == null)
               s = "不知名异常";
             throw new PreExecException(exx.getMessage());
        } finally {
		}
	}

	/**
	 * The method is the business logic the developer should implement.
	 * @param obj
	 * @return
	 */
	public abstract EventResponse exec(Event obj);

	/**
	 * This method will record the transaction's output data and change the TransLog according to
	 * the transaction result.
	 * @throws PostExecException
	 */
	public void postExec() throws PostExecException {
		try {

			SysTransLog transLog =
				(SysTransLog) request.getAttribute(RequestNames.TRANS_LOG);
			transLog.setEndTime(DateUtil.converToString(DateUtil.getSystemCurrentTime(),null));
            String outString = null;
            if (transLog.getStatus().equals(TransUtil.RESULT_RETURN)) {
                outString = (String) this.request.getAttribute(RequestNames.XML_STRING);
            }
            transIMP.modifyTransLog(transLog,outString);
            
		} catch (AppException ae) {
			ae.printStackTrace();
			throw new PostExecException(ae.getMessage());
		} catch (Exception exx) {
			exx.printStackTrace();
			throw new PostExecException(exx.getMessage());
		} finally {
		}
	}

}
