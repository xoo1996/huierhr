package org.radf.plat.log;

import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.radf.manage.logMessage.dto.LogMessageDTO;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.util.global.GlobalNames;
//import java.util.Locale;

/**
 * <p>Title: LogHelper</p>
 * <p>Description: This class provides some method to help to save log information into storage</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co.,Ltd</p>
 * <p>Company: LBS</p>
 * @author LBS Architect Team
 * @version 0.1
 */

public class LogHelper implements java.io.Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 3257853194460477750L;
    private Logger log;
	private String className;

	public LogHelper(String className) {
		this.className = className;
	}
    public LogHelper(Class cls) {
        this.className = cls.getName();
    }
    
	/**
	 * help to classify the log infomation into deferent log appendor
	 * @param head an instance of <code>RequestEnvelopHead</code> which
	 * @param code
     *       0-10000:error
     *       10001-20000:warn
     *       20001-30000:info
     *       99999      :debug
     *       >=999999   :error
	 * @param msg
	 */
	public void log(RequestEnvelopHead head, int code, String msg) {
		LogMessageDTO myMsg = new LogMessageDTO();
		myMsg.setCode(code);
		myMsg.setRequestInfo(head);
		myMsg.setMessage(msg);
        
		Date date = DateUtil.getSystemCurrentTime();
        String dateStr = DateUtil.converToString(date,"yyyy-MM-dd HH:mm:ss");
		myMsg.setDateTime(dateStr);
        
        if (GlobalNames.DEBUG_OUTPUT_FLAG)
            System.out.println(className + " [DEBUG]:" + myMsg);
        
        log = Logger.getLogger(className);
		if (code == GlobalNames.DEBUG_LOG) {
            //仅是debug显示用，不保存
            log.debug(myMsg);
		} else if (0 < code	&& code <= 10000) {
			//系统错误日志，保存
			log.error(myMsg);
		} else if (999999 <= code) {
			//业务错误日志，保存
			log.error(myMsg);
		} else if (10001 <= code && code <= 20000) {
            //警告日志，保存
			log.warn(myMsg);
		} else if (20001 <= code && code <= 30000) {
            //操作日志，是否保存根据全局参数定义
            log.info(myMsg);
		}
	}
    /**
     * 调试日志，不保存，等同于debug(msg)
     * @see log(RequestEnvelopHead head, int code, String msg)
     * @param msg
     */
    public void log(String msg){
        log(null,GlobalNames.DEBUG_LOG,msg);
    }
    /**
     * 调试日志，不保存
     * @see log(RequestEnvelopHead head, int code, String msg)
     * @param msg
     */
    public void debug(String msg){
        log(null,GlobalNames.DEBUG_LOG,msg);
    }
    /**
     * 错误日志，保存
     * @param msg
     */
    public void error(String msg){
        log(null,999999,msg);
    }
    /**
     * 操作日志，保存
     * @param msg
     */
    public void info(String msg){
        log(null,30000,msg);
    }
    public static Logger getLogger(String name){

        Logger  logger = Logger.getLogger(name);
        logger.setLevel(Level.DEBUG);
        return logger;
    }
}
