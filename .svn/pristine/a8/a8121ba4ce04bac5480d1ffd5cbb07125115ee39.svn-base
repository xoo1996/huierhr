package org.radf.plat.sieaf.soap.encoding.util;

import java.util.ArrayList;
import java.util.Iterator;


/**<p>Description:soap消息信封</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co., Ltd.</p>
 * <p>Company: LBS</p>
 * @author chenshuichao
 * @version 1.0
*/
public class MessageEnvelop {
	private static final String ENVELOP_END = "</soap:Envelope>";
	private static final String BODY_END = "</soap:Body>";
	private static final String BODY_START = "<soap:Body>";
	private static final String HEAD_END = "</soap:Header>";
	private static final String HEAD_START = "<soap:Header>";
	private static final String ENVELOP_START =
		"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" soap:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">";
	private static final String XML_PI =
		"<?xml version=\"1.0\" encoding=\"GBK\"?>";
	private ArrayList headEntry;
	private ArrayList bodyEntry;
	/**
	 * Method init.
	 */
	public void init()
	{
		headEntry=new ArrayList();
		bodyEntry=new ArrayList();
	}
	/**
	 * 添加head条目.
	 * @param entry
	 */
	public void addHeadEntry(String entry) {
		headEntry.add(entry);
	}
	/**
	 * 添加body条目.
	 * @param entry
	 */
	public void addBodyEntry(String entry) {
		bodyEntry.add(entry);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		Iterator iter =null;
		strBuf.append(XML_PI);
		strBuf.append(ENVELOP_START);
		strBuf.append(HEAD_START);
		iter = headEntry.iterator();
		while (iter.hasNext()) {
			strBuf.append((String) iter.next());
		}
		strBuf.append(HEAD_END);
		strBuf.append(BODY_START);
		iter = bodyEntry.iterator();
		while (iter.hasNext()) {
			strBuf.append((String) iter.next());
		}
		strBuf.append(BODY_END);
		strBuf.append(ENVELOP_END);
		return strBuf.toString();
	}

}
