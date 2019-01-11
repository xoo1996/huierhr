package org.radf.manage.logMessage.imp;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;

import org.radf.manage.logMessage.bpo.LogMessageBPO;
import org.radf.manage.logMessage.entity.LogMessage;
import org.radf.manage.logMessage.facade.LogFacade;
import org.radf.manage.role.bpo.SysUserBPO;
import org.radf.manage.role.entity.SysUser;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelopHead;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

/**
 * <p>Title: ��־����</p>
 * <p>Description:BPO </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: LBS</p>
 * @author <a href=mailto:qin_yalan@sina.com>Qin Yalan</a>
 * @version 1.0
 */

public class LogMessageIMP extends IMPSupport implements LogFacade{
    private String className = this.getClass().getName();
    private LogMessageBPO bpo = new LogMessageBPO();
    private SysUserBPO userBPO = new SysUserBPO();

	public LogMessageIMP() {
	}

	/**
	 * ɾ����־��Ϣ�ܿط��� <br>
	 * @param request
	 * @return ResponseEnvelop
	 */
	public ResponseEnvelop removeLogMessage(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        HashMap hashMap = (HashMap) request.getBody();
		LogMessage dto = (LogMessage) hashMap.get("dto");

        Connection con = null;
		try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);          
            bpo.removeLogMessage(con,dto);        
            con.commit();
            
            //�ɹ���Ϣ
            ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
            responseHead.setCode(0);
            responseHead.setMessage("ɾ����־��Ϣ�ɹ�");
            response.setHead(responseHead);
            response.setBody("ɾ����־��Ϣ�ɹ�");
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"removeLogMessage",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null){
                    con.rollback();
                    con.close();
                }
            } catch (Exception ex) {
            }
        }
		return response;
	}

	/**
	 * ��ѯ������־��Ϣ�ܿط��� <br>
	 * @param request
	 * @return ResponseEnvelop
	 */
	public ResponseEnvelop findLogMessage(RequestEnvelop request) {

		ResponseEnvelop response = new ResponseEnvelop();
		HashMap hashMap = (HashMap) request.getBody();
        LogMessage dto = (LogMessage) hashMap.get("dto");

        Connection con = null;
		try {
            con = DBUtil.getConnection();
            dto = (LogMessage) bpo.findLogMessage(con,dto);
            
            //�ɹ���Ϣ
            ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
            RequestEnvelopHead requestHead = request.getHead();
            responseHead.setCode(0);
            responseHead.setMessage("��ѯ������־��Ϣ�ɹ�");
            response.setHead(responseHead);
            HashMap hashM = new HashMap();
            hashM.put("dto", dto);
            response.setBody(hashM);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findLogMessage",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
		return response;
	}


	/**
	 * ��FunctionID��ѯ��־��Ϣ�ܿط��� <br>
	 * @param request HashMap  key�� functionid
	 * @return ResponseEnvelop
	 */
	public ResponseEnvelop findByFunctionid(RequestEnvelop request) {

		ResponseEnvelop response = new ResponseEnvelop();
		HashMap hashMap = (HashMap) request.getBody();

        Connection con = null;

        try {
			String functionid = (String) hashMap.get("functionid");
			int count = Integer.parseInt((String) hashMap.get("count"));
			int offset = Integer.parseInt((String) hashMap.get("offset"));

            con = DBUtil.getConnection();
            Collection collect = bpo.findByFunctionid(con,functionid, count, offset);

            //�ɹ���Ϣ
            ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
            RequestEnvelopHead requestHead = request.getHead();
            responseHead.setCode(0);
            responseHead.setMessage("��FunctionID��ѯ��־��Ϣ�ɹ�");
            HashMap hashM = new HashMap();
            hashM.put("collection", collect);
            response.setHead(responseHead);
            response.setBody(hashM);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findByFunctionid",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
		return response;
	}


	/**
	 * ��Messageģ����ѯ��־��Ϣ�ܿط���
	 * @param request
	 * @return ResponseEnvelop
	 */
	public ResponseEnvelop findByMessage(RequestEnvelop request) {

		ResponseEnvelop response = new ResponseEnvelop();
		HashMap hashMap = (HashMap) request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
			String message = (String) hashMap.get("message");
			int count = Integer.parseInt((String) hashMap.get("count"));
			int offset = Integer.parseInt((String) hashMap.get("offset"));

            Collection collect = bpo.findByMessage(con,message, count, offset);
            
            //�ɹ���Ϣ
            ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
            RequestEnvelopHead requestHead = request.getHead();

            responseHead.setCode(0);
            responseHead.setMessage("��Message��ѯ��־��Ϣ�ɹ�");
            HashMap hashM = new HashMap();
            hashM.put("collection", collect);
            response.setHead(responseHead);
            response.setBody(hashM);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findByMessage",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
		return response;
	}


	/**
	 * ��Msgdateʱ��β�ѯ��־��Ϣ�ܿط���
	 * @param request
	 * @return ResponseEnvelop
	 */
	public ResponseEnvelop findByMsgdate(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap hashMap = (HashMap) request.getBody();

        Connection con = null;

        try {
            con = DBUtil.getConnection();
			String beginMsgDate = (String) hashMap.get("beginmsgdate");
			String endMsgDate = (String) hashMap.get("endmsgdate");
			int count = Integer.parseInt((String) hashMap.get("count"));
			int offset = Integer.parseInt((String) hashMap.get("offset"));

            Collection collect =
				bpo.findByMsgdate(con,beginMsgDate, endMsgDate, count, offset);

            //�ɹ���Ϣ
            ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
            RequestEnvelopHead requestHead = request.getHead();
            responseHead.setCode(0);
            responseHead.setMessage("��MsgDate��ѯ��־��Ϣ�ɹ�");
            HashMap hashM = new HashMap();
            hashM.put("collection", collect);
            response.setHead(responseHead);
            response.setBody(hashM);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findByMsgdate",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
		return response;
	}


	/**
	 * ���û���¼����ѯ��־��Ϣ�ܿط���
	 * @param request
	 * @return ResponseEnvelop
	 */
	public ResponseEnvelop findByUser(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap hashMap = (HashMap) request.getBody();

        Connection con = null;
        try {
			String loginName = (String) hashMap.get("loginName");
			int count = Integer.parseInt((String) hashMap.get("count"));
			int offset = Integer.parseInt((String) hashMap.get("offset"));
            SysUser dto = new SysUser();
            dto.setLoginName(loginName);;

            con = DBUtil.getConnection();
            
            //�����û���¼����ѯ��־
            Collection collect = bpo.findByLoginName(con,dto, count, offset);
            //�ɹ���Ϣ
            ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
            RequestEnvelopHead requestHead = request.getHead();
            responseHead.setCode(0);
            responseHead.setMessage("��LoginName��ѯ��־��Ϣ�ɹ�");
            HashMap hashM = new HashMap();
            hashM.put("collection", collect);
            response.setHead(responseHead);
            response.setBody(hashM);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findByUser",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
		return response;
	}
}