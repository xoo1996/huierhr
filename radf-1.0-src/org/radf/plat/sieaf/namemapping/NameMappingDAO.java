package org.radf.plat.sieaf.namemapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import org.radf.plat.log.LogHelper;
import org.radf.plat.util.global.GlobalNames;

/**
 * ��������ӳ���ļ�NameMapping
 * @author zqb
 * @version 1.0
 */
public class NameMappingDAO extends DefaultHandler
{
   private HashMap nameMapping;

   /**
    * �ڿ�ʼ�����ĵ�ʱ����������ݵ�mapping
    * @throws org.xml.sax.SAXException
    * @roseuid 3E64A64A01EE
    */
   public void startDocument() throws SAXException
   {
       nameMapping = new HashMap();
   }

   /**
    * @param namespaceURI
    * @param localName
    * @param rawName
    * @param attrs
    * @throws org.xml.sax.SAXException
    * @roseuid 3E64A64B0109
    */
   public void startElement(String namespaceURI, String localName, String rawName, Attributes attrs) throws SAXException
   {

                if (rawName.equals("map")) {
                   nameMapping.put(attrs.getQName(0), attrs.getValue(0));
                }
   }

   /**
    * @throws org.xml.sax.SAXException
    * @roseuid 3E64A64C00BA
    */
   public void endDocument() throws SAXException
   {

   }

   /**
    * @roseuid 3E64A64C03B3
    */
   private void parse(InputStream is){
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        LogHelper log = new LogHelper("org.radf.plat.namemapping.NameMappingDAO.class");
        try {
            // ����SAXParser����������
            saxParser = spf.newSAXParser();
        } catch (Exception e) {
            log.log(null, 300010, e.getMessage());
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                    System.err.println(e);
            }
            return;
        }
    
        try {
            //GlobalNames�����������ԣ�NAME_MAPPINGS_URLָ������ӳ��XML�ļ�
            saxParser.parse(is, this);
        } catch (SAXException e) {
            log.log(null, 300011, e.getMessage());
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                    System.err.println(e.getMessage());
            }
        } catch (IOException e) {
            log.log(null, 300012, e.getMessage());
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                    System.err.println(e);
            }
        }
   }

   /**
    * @return org.radf.plat.namemapping.NameMapping
    * @roseuid 3E64A64C03DB
    */
   public NameMapping getNameMapping(InputStream is)
   {
                parse(is);
                HashMap tmpMap =new HashMap();
                Iterator it=nameMapping.entrySet().iterator();
                while(it.hasNext()){
                   Map.Entry entry = (Map.Entry)it.next();
                   tmpMap.put(entry.getValue(),entry.getKey()); //������ֵλ��
                }
                nameMapping.putAll(tmpMap);  //����������ָ����ӳ���������ӵ�nameMapping��
                return new NameMapping(nameMapping);
   }
}
