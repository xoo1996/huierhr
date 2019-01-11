package org.radf.plat.sieaf.transformer;

import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.xml.sax.helpers.DefaultHandler;

import org.radf.manage.version.entity.SysVersion;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.commons.safe.SecurityUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.event.Event;
import org.radf.plat.sieaf.event.EventError;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.sieaf.soap.encoding.deser.DeserializationContext;
import org.radf.plat.sieaf.soap.encoding.deser.DeserializationContextImpl;
import org.radf.plat.sieaf.soap.encoding.ser.SerializationContext;
import org.radf.plat.sieaf.soap.encoding.ser.SerializationContextImpl;
import org.radf.plat.sieaf.soap.encoding.util.MessageEnvelop;
import org.radf.plat.util.exception.SafeException;
import org.radf.plat.util.exception.TransException;
import org.radf.plat.util.exception.WebException;
import org.radf.plat.util.global.GlobalNames;
/**
 * <p>Description:soap��Ϣת����</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co., Ltd.</p>
 * <p>Company: LBS</p>
 * @author chenshuichao
 * @version 1.0
*/

public class XmlTransformerImpl extends DefaultHandler implements Transformer {

	private RequestEnvelopHead head;

	/**
	 * ��Collection��ʽ��funList����ɹ淶xml��ʽString
	 * @see org.radf.plat.sieaf.transformer.Transformer#doACL(java.util.Collection)
	 */
	public String doLogin(Collection funList,Collection versionList) {
		StringBuffer strBuf = new StringBuffer(12800);
		String tmp = null;
		strBuf.append(
			"<?xml version=\"1.0\" encoding=\"GBK\"?> <soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><soap:Header><out:system xmlns:out=\"http://www.molss.gov.cn/\"><result sessionID=\"");
		strBuf.append(head.getSessionID() + "\"/>");
		strBuf.append("<result username=\"");      //�û���
		strBuf.append(head.getUsrName() + "\"/>");
		strBuf.append("<result producttype=\"");  //
		strBuf.append(head.getProductType());
		strBuf.append(
			"\"/></out:system></soap:Header><soap:Body><out:business xmlns:out=\"http://www.molss.gov.cn/\">");
        // ������ȱ���
        strBuf.append("<result XQBM=\"");
        strBuf.append(head.getXQBM() + "\"/>");     //Ͻ��
        strBuf.append("<result XZBM=\"");
        strBuf.append(head.getXZBM() + "\"/>");     //����
        strBuf.append("<result CBM=\"");
        strBuf.append(head.getCBM() + "\"/>");      //
        strBuf.append("<result DEPTID=\"");
        strBuf.append(head.getDEPTID() + "\"/>");   //����
        strBuf.append("<result AAB034=\"");
        strBuf.append(head.getAab034() + "\"/>");   //�����籣����
        strBuf.append("<result TYPE=\"");
        strBuf.append(head.getType() + "\"/>");     //�û�����
        
        //�����û���ɫ�б�
        strBuf.append("<resultset name=\"ACL\">");
        Iterator iter = funList.iterator();
		while (iter.hasNext()) {
			tmp = (String) iter.next();
			strBuf.append("<row id=\"");
			strBuf.append(tmp);
			strBuf.append("\"/>");
		}
		strBuf.append("</resultset>");
        
        //�����������ݰ汾�б�
        strBuf.append("<resultset name=\"Version\">");
        SysVersion version = null;
        Iterator it = versionList.iterator();
        while(it.hasNext()){
            version = (SysVersion) it.next();
            strBuf.append("<row tableName=\"");
            strBuf.append(version.getTableName());
            strBuf.append("\" version=\"");
            strBuf.append(""+version.getVersion());
            strBuf.append("\"/>");
        }
        strBuf.append("</resultset>");
        
        
        strBuf.append("</out:business></soap:Body></soap:Envelope>");
        
        System.out.println("���ز����ĳ��ȣ�"+strBuf.length());
		return strBuf.toString();
	}

	/**
	 * ����sessionid��Ŀ.
	 * @return String
	 */
	private String createSessionIDEntry() {
		StringBuffer strBuf = new StringBuffer("<result sessionID=\"");
		strBuf.append(head.getSessionID());
		strBuf.append("\"/>");
		return strBuf.toString();
	}

	/**
	 * ��error�����һ����ʽ��xml String
	 * @see org.radf.plat.sieaf.transformer.Transformer#doError(org.radf.plat.sieaf.event.EventError)
	 */
	public String doError(EventError error) {
		//-----------��������Ϣ�ĳ��ȼ��ٵ�500 add by chenkl 20030408---------------
		if (error.getErrorMsg() != null) {
			if (error.getErrorMsg().length() > 500) {
				String err = error.getErrorMsg().substring(0, 500);
				error.setErrorMsg(err);
			}
		}
		MessageEnvelop message = new MessageEnvelop();
		message.init();
		message.addHeadEntry(createSessionIDEntry());
		message.addBodyEntry(createFaultEntry(error));

		return message.toString();
	}

	/**
	 * ������������Ŀ.
	 * @param error
	 * @return String
	 */
	private String createFaultEntry(EventError error) {
		StringBuffer strBuf = new StringBuffer("<soap:Fault><faultcode>");
		strBuf.append(error.getErrorCode());
		strBuf.append("</faultcode><faultstring><error msg=\"");
		if (error.getErrorCode() == 2003) {
			strBuf.append(StringUtil.deal(error.getErrorMsg()));
		} else {
			Date date = new Date(System.currentTimeMillis());
			java.text.SimpleDateFormat format =
				new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strBuf.append(format.format(date));
			strBuf.append("|");
			strBuf.append(error.getErrorCode());
			strBuf.append("|");
			strBuf.append("ϵͳ���󣨷�����ϵͳ��ܲ��֣�������ϵ����֧�ֲ��ţ�");
			strBuf.append("|");
			strBuf.append(StringUtil.deal(error.getErrorMsg()));
		}

		strBuf.append("\"/></faultstring></soap:Fault>");
		return strBuf.toString();
	}

	/**
	 * ����xmlString���ѽ���ŵ�hashMap�У�������
	 * @see org.radf.plat.sieaf.transformer.Transformer#doRequest(java.lang.String)
	 */
	public Event doRequest(String xmlString) throws TransException {
		Event requestEvent = new Event();
		requestEvent.setFunctionID(head.getFunctionID());
		requestEvent.setLoginName(head.getLoginName());
                requestEvent.setXQBM(head.getXQBM());
                requestEvent.setXZBM(head.getXZBM());
                requestEvent.setCBM(head.getCBM());
                requestEvent.setDEPTID(head.getDEPTID());
		requestEvent.setProductType(head.getProductType());
		DeserializationContext tran = null;
		try {
			tran = new DeserializationContextImpl();
			tran.init();
			xmlString =
				xmlString.substring(
					xmlString.indexOf("<soap:Body>"),
					xmlString.indexOf("</soap:Envelope>"));
			tran.deserialize(
				"<?xml version=\"1.0\" encoding=\"GBK\"?>" + xmlString);
			requestEvent.setBody((HashMap) tran.getValue());

		} catch (WebException we) {
			we.printStackTrace();
			throw new TransException("xml parser error" + we.getMessage());
		}
		return requestEvent;

	}

	/**
	 * ��response�����xml String
	 * @see org.radf.plat.sieaf.transformer.Transformer#doResponse(org.radf.plat.sieaf.event.EventResponse)
	 */
	public String doResponse(EventResponse response) throws WebException {
		if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
			System.out.println(
				"response from Action-->" + response.getBody());
		}
		MessageEnvelop message = new MessageEnvelop();
		message.init();
		message.addHeadEntry(createSessionIDEntry());

		StringBuffer out = null;
		if (response.getBody() != null) {
			out = new StringBuffer("");
			SerializationContext ctx = new SerializationContextImpl();
			ctx.init();
			ctx.serialize(null, out, response);
			message.addBodyEntry(out.toString());
		}
		if (GlobalNames.ENCRYPT_FLAG) {
			String type = head.getSignatureType();
			if (type != null
				&& (type.equals(GlobalNames.SERVER_CERT_FUNCTION) //01
					|| type.equals(GlobalNames.BOTH_CERT_FUNCTION))) { //11
				if (out != null) {
					String body = out.toString();
					String sign = null;
					try {
						SecurityUtil util = new SecurityUtil();
						sign = util.doSignature(body);
						if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
							System.out.println("body is: {" + body + "}");
							System.out.println("sign is: {" + sign + "}");
						}
					} catch (SafeException se) {
						se.printStackTrace();
						throw new WebException("����ǩ��ʧ��");
					}
					//��Ӵ��ǩ�����ݵ�Ԫ��
					message.addHeadEntry(
						"<result signature=\"" + sign + "\"/>");

				}
			}
		}
		return message.toString();

	}

	/** ��ʼ������
	 * @see org.radf.plat.sieaf.transformer.Transformer#init(org.radf.plat.sieaf.envelop.RequestEnvelopHead)
	 */
	public void init(RequestEnvelopHead head) {
		this.head = head;
	}

}
