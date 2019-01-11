package org.radf.plat.sieaf.actionmapping.dao;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.actionmapping.ActionMapping;
import org.radf.plat.util.global.GlobalNames;

/**
 * 关于maping文件XML的操作处理类
 * @author zqb
 * @version 1.0
 */

public class ActionmappingDAOImpl
	extends DefaultHandler
	implements ActionmappingDAO {

	private final static String ACTION_MAPPING_NAME = "map";
	private final static String TRANS_MAPPING_NAME = "trans";
	private HashMap actionMappings;
	private HashMap transMappings;

	public ActionmappingDAOImpl() {
	}

	public void startDocument() throws SAXException {
		actionMappings = new HashMap();
		transMappings = new HashMap();
	}

	public void startElement(
		String namespaceURI,
		String localName,
		String rawName,
		Attributes attrs)
		throws SAXException {
		//*************** not implement!!!!
		if (rawName.equals(ACTION_MAPPING_NAME)) {
			actionMappings.put(attrs.getQName(0), attrs.getValue(0));
			if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
				System.out.println(
					"actionMapping name = "
						+ attrs.getQName(0)
						+ " value = "
						+ attrs.getValue(0));
			}
		} else if (rawName.equals(TRANS_MAPPING_NAME)) {
			transMappings.put(attrs.getQName(0), attrs.getValue(0));
			if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
				System.out.println(
					"transMapping name = "
						+ attrs.getQName(0)
						+ " value = "
						+ attrs.getValue(0));
			}
		}

	}

	public void endDocument() throws SAXException {

	}

	public ActionMapping getActionMapping(InputStream is) {
        parse(is);
        return new ActionMapping(this.actionMappings, this.transMappings);
	}

	private void parse(InputStream is) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
			// 创建SAXParser解析器对象
			saxParser = spf.newSAXParser();
		} catch (Exception ex) {
			//log操作
			LogHelper log =
				new LogHelper("org.radf.plat.actionmapping.dao.ActionmappingDAOImpl.class");
			log.log(null, 300004, ex.getMessage());
			if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.err.println(ex);
			}
			return;
		}

		try {
			//为解析器注册handler处理器，并指定要解析的对象
            saxParser.parse(is, this);
		} catch (SAXException se) {
			LogHelper log =
				new LogHelper("org.radf.plat.actionmapping.dao.ActionmappingDAOImpl.class");
			log.log(null, 300005, se.getMessage());
			if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.err.println(se.getMessage());
			}
		} catch (IOException ioe) {
			LogHelper log =
				new LogHelper("org.radf.plat.actionmapping.dao.ActionmappingDAOImpl.class");
			log.log(null, 300006, ioe.getMessage());
			if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.err.println(ioe);
			}
		}
	}
}
