package org.radf.manage.logMessage.dto;

import org.radf.plat.util.dto.DTOSupport;


/**
 * <p>Title: LogMessage</p>
 * <p>Description: This class encapsulate the log information, and saved to DB</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co.,Ltd</p>
 * <p>Company: LBS</p>
 * @author LBS Architect Team
 * @version 0.1
 */

public class LogMessageDTO extends DTOSupport{

    private org.radf.plat.sieaf.envelop.RequestEnvelopHead requestInfo;
    private String dateTime;
    private int code;
    private String message;
    public LogMessageDTO() {
    }

    public org.radf.plat.sieaf.envelop.RequestEnvelopHead getRequestInfo() {
        return requestInfo;
    }
    public void setRequestInfo(org.radf.plat.sieaf.envelop.RequestEnvelopHead requestInfo) {
        this.requestInfo = requestInfo;
    }
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    public String getDateTime() {
        return dateTime;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
