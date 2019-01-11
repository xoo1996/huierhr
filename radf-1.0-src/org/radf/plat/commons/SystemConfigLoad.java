package org.radf.plat.commons;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;

/**
* 从指定的配置XML文件获取系统配置信息，配置文件名称从init.properties获取并传入
* @author zqb
* @version 1.0
 */

public class SystemConfigLoad extends DefaultHandler {
	HashMap configMap = new HashMap();     //配置文件的完整信息
	String facadeMapName;                  //Facade和IMP映射关系标识名
    List facadeList = null;
    String sieafNode = null;                //当前从配置文件中正在解析的节点 
	public SystemConfigLoad() {
	}
    
    /**
     * 根据系统初始化配置文件，自动获取配置文件路径和名称。
     * 配置文件中路径为空时，默认为入口参数所指定的路径
     * @param path 无路径时默认的XML配置文件路径
     */
    public static void load(String path) {
        try {
            GlobalNames.INIT_CONFIG = FileUtil.readPropertyFile(GlobalNames.FILE_INIT_URL);
            GlobalNames.PATH_CONFIG_URL = GlobalNames.get_INITCONFIG("PATH_CONFIG_URL");
            if(GlobalNames.PATH_CONFIG_URL==null||GlobalNames.PATH_CONFIG_URL.equals("")){
                GlobalNames.PATH_CONFIG_URL = path;
            }
            GlobalNames.FILE_CONFIG_URL = GlobalNames.get_INITCONFIG("NAME_CONFIG_URL");
            GlobalNames.PATH_CONFIG_INDEX = GlobalNames.get_INITCONFIG("PATH_CONFIG_INDEX");
            GlobalNames.USER_ORG = GlobalNames.get_INITCONFIG("USER_CONFIG_ORG");
        } catch (Exception e) {
            System.out.println("取初始化配置文件" + GlobalNames.FILE_INIT_URL + "数据出错："
                    + e.getMessage());
        }
        SystemConfigLoad load = new SystemConfigLoad();
        load.loadConfig(GlobalNames.PATH_CONFIG_URL
                + GlobalNames.FILE_CONFIG_URL);
        load.loadFacadeList(GlobalNames.PATH_CONFIG_URL
                + GlobalNames.FILE_FACADE_URL);
    }
    
    
    /**
     * 获取系统运行参数配置信息
     * 配置文件根据参数GlobalNames.NAME_CONFIG_URL获取
     * @param strFilePath 文件所在路径，空时默认为系统应用路径
     */
    private void loadConfig(String strFilePath) {
        this.parser(strFilePath);
        this.loadConfig(this.configMap);
    }
    
    /**
     * 获取系统运行的Facade和IMP映射关系定义
     * 配置文件根据参数GlobalNames.NAME_FACADE_URL获取
     * @param strFilePath 文件所在路径，空时默认为系统应用路径
     * @author zqb
     */
    private void loadFacadeList(String strFilePath) {
        this.parser(strFilePath);
        try {
            //Facade和IMP映射关系定义
            if(facadeMapName.equalsIgnoreCase("FACADE_LIST")){
                GlobalNames.FACADE_LIST = this.facadeList;
				String [] ss={"FACADESupport","org.radf.plat.util.imp.IMPSupport"};
				GlobalNames.FACADE_LIST.add(ss);
				
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	//在元属开始事件中进行的工作
	public void startElement(
		String namespaceURI,
		String localName,
		String rawName,
		Attributes attrs)
		throws SAXException {
        //系统配置信息部分
        if(rawName.equals("sieaf-config")){
            sieafNode = attrs.getValue("name");
        }
        
		if (GlobalNames.PATH_CONFIG_INDEX==null||sieafNode==null||GlobalNames.PATH_CONFIG_INDEX.equalsIgnoreCase(sieafNode)){
            if(rawName.equals("config")) {
                String name = attrs.getValue("name");
                //System.out.println(name);
                if (name == null) {
                    throw new SAXException(GlobalErrorMsg.SYS_XML_FORMAT);
                }
                String value = attrs.getValue("value");
                //System.out.println(value);
                if (value == null) {
                    throw new SAXException(GlobalErrorMsg.SYS_XML_FORMAT);
                }
                configMap.put(name, value);
            }
            //Facade和IMP映射关系定义
            if (rawName.equals("configlist")) {
                facadeMapName = attrs.getValue("name");
                facadeList = new ArrayList();
            }
            //具体的Facade和IMP映射关系列表
            if (rawName.equals("index")) {
                String [] row = new String[2];
                row[0] = attrs.getValue("name");
                
                if (row[0] == null) {
                    throw new SAXException(GlobalErrorMsg.SYS_XML_FORMAT);
                }
                row[1] = attrs.getValue("value");
                if (row[1] == null) {
                    throw new SAXException(GlobalErrorMsg.SYS_XML_FORMAT);
                }
                facadeList.add(row);
//              for (int i = 0; i < attrs.getLength(); i++) {
//              String paraName = attrs.getQName(i);
//              curVector.add(attrs.getValue(paraName));
            }
		}
	}

	//在元属结束事件中进行的工作
	public void endElement(String uri, String local, String rawName)
		throws SAXException {
        if(rawName.equals("sieaf-config")){
            sieafNode = null;
        }

		if (rawName.equals("configlist")) {
			configMap.put(facadeMapName, facadeList);
		}
	}

	//xmlString的解析工作
	private void parser(String strFilePath) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		XMLReader reader = null;
		try {
			// 创建一个SAXParser解析器对象
			saxParser = spf.newSAXParser();
			reader = saxParser.getXMLReader();
		} catch (Exception ex) {
		}
        System.out.println("XML文件：" + strFilePath);
		try {
            InputStream is = SystemConfigLoad.class.getResourceAsStream(strFilePath);
			saxParser.parse(is, this);
		} catch (Exception ioe) {
			if (GlobalNames.DEBUG_OUTPUT_FLAG) {
				System.out.println(
					"Load "	+ strFilePath+ " failed! Using default configuration.");
                ioe.printStackTrace();
			}

		}
	}
    
    private void loadConfig(HashMap map){
        try {
            GlobalNames.DEBUG_LOG =
                Integer.parseInt((String) map.get("DEBUG_LOG"));
            GlobalNames.DEBUG_OUTPUT_FLAG =
                Boolean.valueOf((String) map.get("DEBUG_OUTPUT_FLAG")).booleanValue();
            GlobalNames.DEBUG_PERFERMANCE_FLAG =
                Boolean.valueOf((String) map.get("DEBUG_PERFERMANCE_FLAG")).booleanValue();
            GlobalNames.LOG_INFO_TABLE_NAME =
                (String) map.get("LOG_INFO_TABLE_NAME");
            GlobalNames.LOG_WARN_TABLE_NAME =
                (String) map.get("LOG_WARN_TABLE_NAME");
            GlobalNames.LOG_ERROR_TABLE_NAME =
                (String) map.get("LOG_ERROR_TABLE_NAME");
            GlobalNames.LOG_MESSAGE_SEQNAME =
                (String) map.get("LOG_MESSAGE_SEQNAME");
            GlobalNames.LOG_TRANS_SEQNAME = 
                (String) map.get("LOG_TRANS_SEQNAME");
            if(map.get("INFO_OUTPUT_FLAG")!=null){
                GlobalNames.INFO_OUTPUT_FLAG = Boolean.valueOf((String) map.get("INFO_OUTPUT_FLAG")).booleanValue();
            }

            //定义服务应用的类型
            if(map.get("SERVICE_FRAME")!=null){
                GlobalNames.SERVICE_FRAME = (String) map.get("SERVICE_FRAME");
            }
            //获取应用的地区版本类型
            if(map.get("SERVICE_ZONE")!=null){
                GlobalNames.SERVICE_ZONE = (String) map.get("SERVICE_ZONE");
            }
            //定义是否启动数据更新服务
            if(map.get("SERVICE_VERSION")!=null){
                GlobalNames.SERVICE_VERSION = (String) map.get("SERVICE_VERSION");
            }
			//定义是否启动数据参数加载
            if(map.get("PARAM_FLAG")!=null){
                GlobalNames.PARAM_FLAG = Boolean.valueOf((String) map.get("PARAM_FLAG")).booleanValue();
            }
            //默认字符集定义
            if(map.get("SERVICE_ENCODING")!=null){
                GlobalNames.SERVICE_ENCODING = (String) map.get("SERVICE_ENCODING");
            }
            //是否获取Action和Function之间的对应关系
            if(map.get("SERVICE_ACTION_MAPING")!=null){
                GlobalNames.SERVICE_ACTION_MAPING = (String)map.get("SERVICE_ACTION_MAPING");
            }
            //定义FunctionID和Action对应关系
            if(map.get("FILE_MAPPINGS_URL")!=null){
                GlobalNames.FILE_MAPPINGS_URL = (String) map.get("FILE_MAPPINGS_URL");
            }
            //Facade和IMP对应关系
            if(map.get("FILE_FACADE_URL")!=null){
                GlobalNames.FILE_FACADE_URL = (String) map.get("FILE_FACADE_URL");
            }            
            //指标体系名和义名映射文件
            if(map.get("FILE_NAMEMAP_URL")!=null){
                GlobalNames.FILE_NAMEMAP_URL = (String) map.get("FILE_NAMEMAP_URL");
            }
            //SQL语句映射文件位置定义
            if(map.get("FILE_SQL_URL")!=null){
                GlobalNames.FILE_SQL_URL = (String) map.get("FILE_SQL_URL");
            }
            //应用服务器类型定义
            GlobalNames.SERVER_TYPE = (String) map.get("SERVER_TYPE");
            
            GlobalNames.MAPPING_DAO = (String) map.get("MAPPING_DAO");
            
            //数据库相关
            GlobalNames.NAME_INITIAL_FACTORY =
                (String) map.get("NAME_INITIAL_FACTORY");
            GlobalNames.NAME_URL_PROVIDER = (String) map.get("NAME_URL_PROVIDER");
            GlobalNames.DATA_SOURCE = (String) map.get("DATA_SOURCE");
            GlobalNames.DATA_SOURCE_TX = (String) map.get("DATA_SOURCE_TX");
            GlobalNames.DATA_SOURCE_LOG = (String) map.get("DATA_SOURCE_LOG");
            GlobalNames.USER_TRANSACTION = (String) map.get("USER_TRANSACTION");
            
            GlobalNames.DATABASE_CONNECT_TYPE = (String) map.get("DATABASE_CONNECT_TYPE");
            GlobalNames.DATABASE_DRIVERNAME = (String) map.get("DATABASE_DRIVERNAME");
            GlobalNames.DATABASE_URL = (String) map.get("DATABASE_URL");
            GlobalNames.DATABASE_USER = (String) map.get("DATABASE_USER");
            GlobalNames.DATABASE_PASSWORD = (String) map.get("DATABASE_PASSWORD");
           
            //JMS相关
            GlobalNames.JMS_CONTECTION_FACTROY =
                (String) map.get("JMS_CONTECTION_FACTROY");
            GlobalNames.JMS_QUEUE_NAME = (String) map.get("JMS_QUEUE_NAME");
            GlobalNames.JMS_USER_NAME = (String) map.get("JMS_USER_NAME");
            GlobalNames.JMS_PASSWORD = (String) map.get("JMS_PASSWORD");
            
            //安全认证相关
            GlobalNames.ENCRYPT_FLAG =
                Boolean.valueOf((String) map.get("ENCRYPT_FLAG")).booleanValue();
            GlobalNames.NORMAL_FUNCTION =
                (String) map.get("NORMAL_FUNCTION");
            GlobalNames.CLIENT_CERT_FUNCTION =
                (String) map.get("CLIENT_CERT_FUNCTION");
            GlobalNames.SERVER_CERT_FUNCTION =
                (String) map.get("SERVER_CERT_FUNCTION");
            GlobalNames.BOTH_CERT_FUNCTION =
                (String) map.get("BOTH_CERT_FUNCTION");
            
            //应用的根路径
//            if(map.get("LEAF_FILTER_URL")!=null&&!map.get("LEAF_FILTER_URL").equals("")){
//                GlobalNames.LEAF_FILTER_URL = (String)map.get("LEAF_FILTER_URL");
//            }
            if(map.get("WEB_APP")!=null){
                GlobalNames.WEB_APP = (String)map.get("WEB_APP");
            }
            //索引页面
            if(map.get("INDEX_PAGE")!=null&&!map.get("INDEX_PAGE").equals("")){
                GlobalNames.INDEX_PAGE = (String)map.get("INDEX_PAGE");
            }
            //默认登录页面
            if(map.get("LOGON_PAGE")!=null&&!map.get("LOGON_PAGE").equals("")){
                GlobalNames.LOGON_PAGE = (String)map.get("LOGON_PAGE");
            }
            //默认得登录action
            if(map.get("LOGON_ACTION")!=null&&!map.get("LOGON_ACTION").equals("")){
                GlobalNames.LOGON_ACTION = (String)map.get("LOGON_ACTION");
            }
            //登录成功后默认进入的页面
            if(map.get("MAIN_PAGE")!=null&&!map.get("MAIN_PAGE").equals("")){
                GlobalNames.MAIN_PAGE = (String)map.get("MAIN_PAGE");
            }
            
            //分页组件默认分页大小
            if(map.get("PAGE_SIZE")!=null&&!map.get("PAGE_SIZE").equals("")){
                GlobalNames.PAGE_SIZE = Integer.parseInt((String)map.get("PAGE_SIZE"));
            }
            //分页组件默认分页大小
            if(map.get("UPDATE_SIZE")!=null&&!map.get("UPDATE_SIZE").equals("")){
                GlobalNames.UPDATE_SIZE = Integer.parseInt((String)map.get("UPDATE_SIZE"));
            }
            //系统登录页面
            //获取数据库系统时间的SQL语句
            if(map.get("SQL_SYSDATE")!=null&&!map.get("SQL_SYSDATE").equals("")){
                GlobalNames.SQL_SYSDATE = (String)map.get("SQL_SYSDATE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) {
	    SystemConfigLoad u=new SystemConfigLoad();
        load("\\");
	}
}
