package org.radf.plat.sieaf.soap.encoding.deser;


/**<p>Description:DefaultHandlerµÄ·â×°</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co., Ltd.</p>
 * <p>Company: LBS</p>
 * @author chenshuichao
 * @version 1.0
*/
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SOAPHandler extends DefaultHandler
{

	public SOAPHandler()
	{
		
	}

	public void startElement(String namespace, String localName, String prefix, Attributes attributes)
		throws SAXException
	{

	}




	public SOAPHandler startChild(String namespace, String localName, String prefix, Attributes attributes,DeserializationContext ctx)
		throws SAXException
	{
		SOAPHandler handler = new SOAPHandler();
		return handler;
	}

	public void endChild(String s, String s1)
		throws SAXException
	{
	}


	/**
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void endElement(String namespaceURI, String localName, String qName,DeserializationContext ctx)
		throws SAXException {
		super.endElement(namespaceURI, localName, qName);
	}

}
