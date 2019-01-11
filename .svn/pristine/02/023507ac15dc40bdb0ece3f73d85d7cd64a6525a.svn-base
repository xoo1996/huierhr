package org.radf.plat.sieaf;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.util.exception.WebException;
import org.radf.plat.util.global.GlobalNames;

/**
 * 标题: HeadParser(SIEAF)
 * 说明：分解xml文件头部分，生成RequestEnvelopHead，由HeadFilter调用
 * @author zqb
 * @version 1.0
 */

public class HeadParser extends DefaultHandler {

	private RequestEnvelopHead head = null;
	/*
	public static void main(String[] args)
	{
		HeadParser head=new HeadParser();
		head.parserHead("<xml><para name=\"usr\" value=\"jed\"/><para name=\"pwd\" value=\"jed\"/><para name=\"funid\" value=\"100\"/></xml>");
		System.out.println(head.getUsr());
	}
	*/
	// 处理文档前的工作
	public void startDocument() throws SAXException {

	}
	//对每一个开始元属进行处理
	public void startElement(
		String namespaceURI,
		String localName,
		String rawName,
		Attributes attrs)
		throws SAXException {

		if (rawName.equals("para")) {
			String name = attrs.getQName(0);
			String value = attrs.getValue(0);
			if (name.equals("usr")) {
				head.setLoginName(value);
			} else if (name.equals("funid")) {
				head.setFunctionID(value);
			} else if (name.equals("pwd")) {
				head.setPassword(value);
			} else if (name.equals("signature")) {
				head.setSignature(value);
			} else if (name.equals("producttype")) {
				head.setProductType(value);
			}
		}
	}

	public void endDocument() throws SAXException {

	}

	//完成解析工作
	public RequestEnvelopHead parserHead(String xmlString)
		throws WebException {

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
			// 创建一个解析器SAXParser对象
			saxParser = spf.newSAXParser();

		} catch (Exception ex) {
			LogHelper log = new LogHelper(this.getClass().getName());
			log.log(null, 300004, ex.getMessage());
			throw new WebException("sax parser load error");
		}
		try {
			//注册handler，并进行解析
			StringReader sr = new StringReader(xmlString);
			head = new RequestEnvelopHead();
			saxParser.parse(new org.xml.sax.InputSource(sr), this);
			return head;

		} catch (SAXException se) {
			LogHelper log = new LogHelper(this.getClass().getName());
			log.log(null, 300005, se.getMessage());
			if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.err.println(se.getMessage());
			}
			throw new WebException("xml format invalid");
		} catch (IOException ioe) {
			LogHelper log = new LogHelper(this.getClass().getName());
			log.log(null, 300006, ioe.getMessage());
			if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.err.println(ioe);
			}
			throw new WebException("io error");

		}
	}

}
