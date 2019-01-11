// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.commons;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DOMUtil
{
    static class a
        implements ErrorHandler
    {

        public void warning(SAXParseException saxparseexception)
            throws SAXException
        {
        }

        public void error(SAXParseException saxparseexception)
            throws SAXException
        {
            throw saxparseexception;
        }

        public void fatalError(SAXParseException saxparseexception)
            throws SAXException
        {
            throw saxparseexception;
        }

        a()
        {
        }
    }


    public DOMUtil()
    {
    }

    public static Document createNewDocument()
    {
        Document document = null;
        try
        {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        }
        catch(ParserConfigurationException parserconfigurationexception) { }
        return document;
    }

    public static Document loadDocumentFromStr(String s)
    {
        Document document = null;
        try
        {
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(s.getBytes());
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(bytearrayinputstream));
        }
        catch(Exception exception) { }
        return document;
    }

    public static Document loadDocumentFromStr(String s, String s1)
    {
        Document document = null;
        try
        {
            ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(s.getBytes(s1));
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(bytearrayinputstream));
        }
        catch(Exception exception) { }
        return document;
    }

    public static Document loadDocumentFromFile(File file)
    {
        Document document = null;
        try
        {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        }
        catch(Exception exception) { }
        return document;
    }

    public static Document loadDocumentFromInputStream(InputStream inputstream)
    {
        Document document = null;
        try
        {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputstream);
        }
        catch(Exception exception) { }
        return document;
    }

    public static Document loadDocumentFromUri(String s)
    {
        Document document = null;
        try
        {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(s);
        }
        catch(Exception exception) { }
        return document;
    }

    public static void parse(Document document)
        throws Exception
    {
        DOMParser domparser = new DOMParser();
        domparser.setErrorHandler(new a());
        domparser.setFeature("http://xml.org/sax/features/validation", true);
        StringReader stringreader = new StringReader(domToString(document));
        InputSource inputsource = new InputSource(stringreader);
        domparser.parse(inputsource);
    }

    public static void appendChild(Node node, Node node1)
    {
        node.appendChild(node1);
    }

    public static boolean containsNode(Node node, String s)
    {
        return getSingleNode(node, s) != null;
    }

    public static Node getSingleNode(Node node, String s)
    {
        if(node == null || s == null)
            return null;
        Node node1 = null;
        NodeList nodelist = null;
        if(node instanceof Document)
            nodelist = ((Document)node).getElementsByTagName(s);
        else
            nodelist = ((Element)node).getElementsByTagName(s);
        if(nodelist != null && nodelist.getLength() > 0)
            node1 = nodelist.item(0);
        return node1;
    }

    public static Element getSingleElement(Node node, String s)
    {
        Element element = null;
        if(containsNode(node, s))
            element = (Element)getSingleNode(node, s);
        return element;
    }

    public static NodeList getMultiNodes(Node node, String s)
    {
        if(node == null || s == null)
            return null;
        NodeList nodelist = null;
        if(node instanceof Document)
            nodelist = ((Document)node).getElementsByTagName(s);
        else
            nodelist = ((Element)node).getElementsByTagName(s);
        return nodelist;
    }

    public static Node getNode(Node node, String s, int i)
    {
        if(node == null || s == null || i < 0)
            return null;
        Node node1 = null;
        NodeList nodelist = getMultiNodes(node, s);
        if(i < nodelist.getLength())
            node1 = nodelist.item(i);
        return node1;
    }

    public static Element getElement(Node node, String s, int i)
    {
        Node node1 = getNode(node, s, i);
        if(node1 == null)
            return null;
        else
            return (Element)node1;
    }

    public static String getNodeValue(Node node)
    {
        if(node == null || (node instanceof Document))
            return null;
        NodeList nodelist = node.getChildNodes();
        if(nodelist.getLength() <= 0)
            return null;
        StringBuffer stringbuffer = new StringBuffer();
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            Node node1 = nodelist.item(i);
            if(node1.getNodeType() == 3)
                stringbuffer.append(node1.getNodeValue());
        }

        return stringbuffer.toString();
    }

    public static String getSingleNodeValue(Node node, String s)
    {
        Node node1 = getSingleNode(node, s);
        return getNodeValue(node1);
    }

    public static String[] getMultiNodeValues(Node node, String s)
    {
        NodeList nodelist = getMultiNodes(node, s);
        if(nodelist == null)
            return new String[0];
        String as[] = new String[nodelist.getLength()];
        for(int i = 0; i < nodelist.getLength(); i++)
            as[i] = getNodeValue(nodelist.item(i));

        return as;
    }

    public static String getAttributeValue(Element element, String s)
    {
        if(element == null || s == null)
            return null;
        else
            return element.getAttribute(s);
    }

    public static HashMap getAttributeValues(Element element)
    {
        HashMap hashmap = new HashMap();
        NamedNodeMap namednodemap = element.getAttributes();
        for(int i = 0; i < namednodemap.getLength(); i++)
        {
            Attr attr = (Attr)namednodemap.item(i);
            hashmap.put(attr.getName(), attr.getValue());
        }

        return hashmap;
    }

    public static String getSingleAttributeValue(Node node, String s, String s1)
    {
        String s2 = null;
        Element element = getSingleElement(node, s);
        if(element != null)
            s2 = element.getAttribute(s1);
        return s2;
    }

    public static String[] getMultiAttributeValue(Node node, String s, String s1)
    {
        NodeList nodelist = getMultiNodes(node, s);
        if(nodelist == null)
            return new String[0];
        String as[] = new String[nodelist.getLength()];
        for(int i = 0; i < nodelist.getLength(); i++)
            as[i] = getAttributeValue((Element)nodelist.item(i), s1);

        return as;
    }

    public static Element createElement(Document document, String s, String s1)
    {
        if(document == null || s == null)
        {
            return null;
        } else
        {
            Element element = document.createElement(s);
            setNodeValue(element, s1);
            return element;
        }
    }

    public static Element createAndAppendRoot(Document document, String s, String s1)
    {
        if(document == null || s == null)
        {
            return null;
        } else
        {
            Element element = document.createElement(s);
            document.appendChild(element);
            setNodeValue(element, s1);
            return element;
        }
    }

    public static Element createAndAppendElement(Node node, String s, String s1)
    {
        if(node == null || s == null)
            return null;
        Document document = null;
        if(node instanceof Document)
            document = (Document)node;
        else
            document = node.getOwnerDocument();
        Element element = document.createElement(s);
        node.appendChild(element);
        setNodeValue(element, s1);
        return element;
    }

    public static void createAndAppendMultiElement(Node node, String s, String as[])
    {
        if(node == null || s == null || as == null || as.length == 0 || (node instanceof Document))
            return;
        Document document = node.getOwnerDocument();
        for(int i = 0; i < as.length; i++)
        {
            Element element = document.createElement(s);
            node.appendChild(element);
            setNodeValue(element, as[i]);
        }

    }

    public static void setNodeValue(Node node, String s)
    {
        if(node == null || (node instanceof Document))
            return;
        Document document = node.getOwnerDocument();
        NodeList nodelist = node.getChildNodes();
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            Node node1 = nodelist.item(i);
            if(node1.getNodeType() == 3)
                node.removeChild(node1);
        }

        if(s != null)
            node.appendChild(document.createTextNode(s));
    }

    public static void setSingleNodeValue(Node node, String s, String s1)
    {
        Node node1 = getSingleNode(node, s);
        if(node1 != null)
            setNodeValue(node1, s1);
    }

    public static void setAttribute(Element element, String s, String s1)
    {
        if(element != null && s != null)
            element.setAttribute(s, s1);
    }

    public static void setAttributes(Element element, HashMap hashmap)
    {
        if(element == null || hashmap == null)
            return;
        String s;
        for(Iterator iterator = hashmap.keySet().iterator(); iterator.hasNext(); element.setAttribute(s, hashmap.get(s).toString()))
            s = iterator.next().toString();

    }

    public static void setSingleNodeAttribute(Node node, String s, String s1, String s2)
    {
        Element element = getSingleElement(node, s);
        setAttribute(element, s1, s2);
    }

    public static String domToString(Node node)
    {
        StringWriter stringwriter = new StringWriter();
        XMLSerializer xmlserializer = new XMLSerializer(stringwriter, null);
        try
        {
            if(node instanceof Document)
                xmlserializer.serialize((Document)node);
            if(node instanceof Element)
                xmlserializer.serialize((Element)node);
        }
        catch(Exception exception)
        {
            return null;
        }
        return stringwriter.toString();
    }

    public static Node unionNodes(Node node, Node node1, boolean flag)
    {
        HashMap hashmap = getAttributeValues((Element)node);
        HashMap hashmap1 = getAttributeValues((Element)node1);
        String s;
        for(Iterator iterator = hashmap1.keySet().iterator(); iterator.hasNext(); hashmap.put(s, hashmap1.get(s)))
            s = iterator.next().toString();

        setAttributes((Element)node, hashmap);
        if(!flag)
            return node;
        NodeList nodelist = node1.getChildNodes();
        int i = nodelist.getLength();
        for(int j = 0; j < nodelist.getLength(); j++)
            if(nodelist.item(j).getNodeType() != 3)
                removeChildNodes(node, nodelist.item(j).getNodeName());

        for(int k = 0; k < nodelist.getLength(); k++)
            if(nodelist.item(k).getNodeType() != 3)
                appendChild(node, nodelist.item(k));

        return node;
    }

    public static void removeChildNodes(Node node, String s)
    {
        NodeList nodelist = node.getChildNodes();
        for(int i = 0; i < nodelist.getLength(); i++)
            if(nodelist.item(i).getNodeName().equals(s))
                node.removeChild(nodelist.item(i));

    }
}
