/* Generated by Together */

package org.radf.plat.sieaf.soap.encoding.deser;


/**<p>Description:vector解码子</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co., Ltd.</p>
 * <p>Company: LBS</p>
 * @author chenshuichao
 * @version 1.0
*/

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class VectorDeserializer extends DeserializerImpl {
	/**
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String namespaceURI, String localName, String qName,DeserializationContext ctx)
		throws SAXException {
		getTarget().setValue(key, value);

	}

	/**
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */
	public void startElement(
		String namespaceURI,
		String localName,
		String qName,
		Attributes atts)
		throws SAXException {

		key = atts.getValue(0);
		Vector resultset = new Vector();
		value = resultset;

	}

	/**
	 * @see org.radf.plat.soap.encoding.Deserializer#setValue(java.lang.Object)
	 */
	public void setValue(Object val) {
		((Vector) value).add(val);
	}

	/**
	 * 处理子标签
	 * @see org.radf.plat.soap.encoding.SOAPHandler#startChild(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes, org.radf.plat.soap.encoding.DeserializationContext)
	 */
	public SOAPHandler startChild(
		String namespace,
		String localName,
		String prefix,
		Attributes attributes,
		DeserializationContext ctx)
		throws SAXException {

		Deserializer deser = ctx.getDeserializer(localName);
		deser.registTarget(this);

		return (SOAPHandler) deser;
	}
	private String key;

}
