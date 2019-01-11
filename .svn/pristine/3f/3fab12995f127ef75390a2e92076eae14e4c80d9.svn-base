/**
* <p>标题: 系统全局变量定义基础类</p>
* <p>说明: 此类必须被GlobalNames继承，绝大部分的变量可以通过SystemConfigLoad.java从配置文件获取</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-11-1711:56:21</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.util.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class GlobalNamesSupport {

    //*********************************************************//
    //                  全局环境变量定义                             //
    //*********************************************************//
    /**应用框架模型0-双模式，1-SOAP，2-WEB*/
    public static String SERVICE_FRAME = "0";
    /**应用服务器类型，1-WebLogic，2-Tomcat，3-Oc4j，4-JBOSS*/
    public static String SERVER_TYPE = "1"; 
    /**版本数据更新服务是否启动:1-启动，2-不启动*/
    public static String SERVICE_VERSION = "1";
    /**从配置文件中获取的字符集*/
    public static String SERVICE_ENCODING = "GBK";
    /**从配置文件无法获取字符集时默认使用的字符集*/
    public static String DEFAULT_ENCODING = "GBK";
    /**是否获取Action和Function之间的对应关系：0不启用，1-启用*/
    public static String SERVICE_ACTION_MAPING = "1";
    /** 当前应用系统的地区性主版本号，此号码由公司内部统一分配，不能修改 **/
    public static String SERVICE_ZONE = "";
    //*********************************************************//
    //                   地区性差异接口的定义                         //
    //*********************************************************//
    public static HashMap ZONE_MAP = null;
    
    //*********************************************************//
    //                      关于配置文件的定义                       //
    //*********************************************************//
    /**全局文件位置定义配置，位置固定名称不能修改*/
    public static String FILE_INIT_URL = "/init.properties";
    public static HashMap INIT_CONFIG = new HashMap();
    /**定义SQL语句文件的文件名，位置固定*/
    public static String FILE_SQL_URL = "/TZ_SQL.properties";
    /**定义全局配置文件所处路径，在init文件的PATH_CONFIG_URL中配置*/
    public static String PATH_CONFIG_URL = "";
    /**定义全局系统配置文件的文件名，在init文件的NAME_CONFIG_URL中配置*/
    public static String FILE_CONFIG_URL = "TZSW_PLATConfig.xml";
    /**定义本应用在全局配置XML文件中的节点，在init文件的PATH_CONFIG_INDEX中配置*/
    public static String PATH_CONFIG_INDEX = "";
    /**定义全局Facade和IMP对应关系的文件名*/
    public static String FILE_FACADE_URL = "TZSW_FacadeConfig.xml";
    /**定义全局FunctionID和Action对应关系的文件名*/
    public static String FILE_MAPPINGS_URL = "TZSW_mappings.xml";
    //\u00B9\u00A6\u00C4\u00DC\u00BA\u00C5\u00BA\u00CDAction\u00B5\u00C4\u00D3\u00B3\u00C9\u00E4±í\u00CE\u00C4\u00BC\u00FE
    public static String FILE_NAMEMAP_URL = "TZSW_namemappings.xml";
    //*********************************************************//
    //                  关于数据库、事务处理的定义                    //
    //*********************************************************//
    /**数据库连接类型：１-DATA_SOURCE,   2-JDBC直接连接 */
    public static String DATABASE_CONNECT_TYPE = "2";
    /**数据库驱动名称，DATABASE_CONNECT_TYPE = "2"有效*/
    public static String DATABASE_DRIVERNAME = "oracle.jdbc.driver.OracleDriver";
    /**数据库连接URL，DATABASE_CONNECT_TYPE = "2"有效*/
    public static String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    /**数据库连接用户，DATABASE_CONNECT_TYPE = "2"有效*/
    public static String DATABASE_USER = "huier";
    /**数据库连接用户密码，DATABASE_CONNECT_TYPE = "2" 有效*/
    public static String DATABASE_PASSWORD = "oracle";
    
    /** 业务系统数据库连接池 */
    public static String DATA_SOURCE = "jdbc/SIMISdatasource";
    /**TX数据库连接池 */
    public static String DATA_SOURCE_TX = "jdbc/SIMIStxdatasource";
    /** 日志系统数据库连接池 */
    public static String DATA_SOURCE_LOG = "jdbc/SIMISlogservice";
    /** 用户事务处理类 */
    public static String USER_TRANSACTION = "javax.transaction.UserTransaction";
    /**命名的定义，datasource和JMS使用*/
    public static String NAME_INITIAL_FACTORY = "weblogic.jndi.WLInitialContextFactory";
    /**命名的定义，datasource和JMS使用*/
    public static String NAME_URL_PROVIDER = "t3://127.0.0.1:7001";
    
    //*******************************************************//
    //                  关于JMS相关的定义                          //
    //*******************************************************//
    public static String JMS_CONTECTION_FACTROY = "weblogic.jms.ConnectionFactory";
    public static String JMS_QUEUE_NAME = "LogMessageQueue";
    public static String JMS_USER_NAME = "weblogic";
    public static String JMS_PASSWORD = "weblogic";

    //*********************************************************//
    //                      关于日志处理的定义                       //
    //*********************************************************//
    /** 调试日志的默认编号 */
    public static int DEBUG_LOG = 99999;
    /** 调试输出日志是否打开 */
    public static boolean DEBUG_OUTPUT_FLAG = true;
    /** 系统调试日志是否打开 */
    public static boolean DEBUG_PERFERMANCE_FLAG = true;
    /** 系统操作日志是否保存 */
    public static boolean INFO_OUTPUT_FLAG = true;
     /** 系统是否加载参数 */
    public static boolean PARAM_FLAG = true;
    
    public static String LOG_INFO_TABLE_NAME = "loginfo";
    public static String LOG_WARN_TABLE_NAME = "logwarn";
    public static String LOG_ERROR_TABLE_NAME = "logerror";
    /** 操作日志在数据库中的序列名 */
    public static String LOG_MESSAGE_SEQNAME = "SEQ_LOGMESSAGE";
    /** 事务日志在数据库中的序列名 */
    public static String LOG_TRANS_SEQNAME = "SEQ_LOGTRANSACTION";


    //*********************************************************//
    //               关于交易、服务、角色、事务的定义                  //
    //*********************************************************//
    public static String XML_STRING = "xmlString";
    public static List FACADE_LIST = new ArrayList();
    public static HashMap FACADE_CACHE = null;//Facade to IMP Cache
    public static String MAPPING_DAO =
        "org.radf.plat.actionmapping.dao.ActionmappingDAOImpl";
    //FuntionMapping\u00B5\u00C4\u00CA\u00B5\u00C0\u00FD\u00C3\u00FB\u00B3\u00C6
    public static Vector ROLE_PUBLIC_ALL = new Vector();


    public static String TRANS_TYPE_VARCHAR = "00";
    public static String TRANS_TYPE_BLOB = "01";
    public static String ACTION_MAPPINGS = "ACTION_MAPPINGS";
    public static String TRANSACTION_DEF_CACHE = "TRANS_DEF_CACHE";
    public static String TRANSFORMER =
        "org.radf.plat.transformer.XmlTransformerImpl";
    
    //*********************************************************//
    //                      关于系统安全的定义                       //
    //*********************************************************//
    public static boolean ENCRYPT_FLAG = true;
    public static String FUNCTION_CACHE = "FUNCTION_CACHE";
    public static String CURRENT_CERT_FILE_PATH = "certs";
    public static String SERVER_KEY = "ServerKey.key";
    public static String SERVER_CERT = "ServerCert.der";
    /**不需要认证的Function*/
    public static String NORMAL_FUNCTION = "00";
    /**客户端需要认证的Function*/
    public static String CLIENT_CERT_FUNCTION = "10";
    /**服务器端需要认证的Function*/
    public static String SERVER_CERT_FUNCTION = "01";
    /**客户端和服务器都需要认证的Function*/
    public static String BOTH_CERT_FUNCTION = "11";
    
    //*********************************************************//
    //                   关于网站的配置定义                           //
    //*********************************************************//
    /**web应用的相对根路径*/
    public static String WEB_APP = "/plat";
    /**当前发布的应用所在的文件路径 */
    public static String WEB_PATH = "";

    public static String LOGON_ACTION = "/logonAction.do";
    public static String LOGON_PAGE = "/logonAction.do";

    public static String LOGOFF_PAGE = "/Main.htm";
    public static String LOGOFF_ACTION = "/logoffAction.do";

    public static String MENU_ACTION = "/menuAction.do";
    public static String MENU_PAGE = "/Left.jsp";
	public static String TOP_PAGE = "/Top.jsp";

    public static String INDEX_PAGE = "/Index.jsp";
    public static String MAIN_PAGE = "/Main.jsp";
    public static String MAIN_PAGEWB = "/WbMain.jsp"; 
    //*********************************************************//
    //                   关于系统部分功能的操作定义                   //
    //*********************************************************//
    /**分页组件默认的分页大小*/
    public static int PAGE_SIZE = 10;
    /**客户端版本更新时每次下载数据的条数**/
    public static int UPDATE_SIZE = 500;
    /** 用户所属组织 */
    public static String USER_ORG = null;
    /** 从sql配置文件中获取的外部定义sql语句信息*/
    public static HashMap SQL_MAP = new HashMap();
    /**获取数据库系统时间的SQL语句*/
    public static String SQL_SYSDATE = "select to_char(sysdate,'yyyy-mm-dd') now from dual";
    
    
    public static String QUERY_INFO = "queryinfo";
    public static String ORDER_BY = "order by";
    public static String PAGE_QUERY_ACTION = "pageQueryAction";
    public static String QUERY_DATA = "querydata";
    public static String HQL_MAP = "hqlmap";
    public static String FUNCTION_LIST = "function_list";
    public static String NAVIGATION = "navigation";
    public static String MENU_BUTTON = "2";
    /**
     * 获取全局初始化配置文件中指定key的内容
     * @param key
     * @return
     */
    public static String get_INITCONFIG(String key){
        return (String)GlobalNames.INIT_CONFIG.get(key);
    }


}
