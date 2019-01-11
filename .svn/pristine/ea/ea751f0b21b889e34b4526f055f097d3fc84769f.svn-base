/**
* <p>标题: 获取webservice的部署位置信息</p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-1-4 10:50:56</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.webservice.client;


public class LogonURL {
    private static String WEBSERVICE_URL = "http://localhost:8080/plat/services/SendService";
    
    private static void getPropertiesURL(){
//        try {
//            InputStream is = LogonURL.class
//                    .getResourceAsStream(GlobalNames.FILE_INIT_URL);
//            Properties properties = new Properties();
//            properties.load(is);
//            WEBSERVICE_URL = properties.getProperty("WEBSERVICE_URL");
//        } catch (Exception e) {
//            System.out.println("取初始化配置文件" + GlobalNames.FILE_INIT_URL + "数据出错："
//                    + e.getMessage());
//        }
    }
    public static void setURL(String url){
        WEBSERVICE_URL = url;
    }
    public static String getUrl(){
        if(WEBSERVICE_URL==null){
            getPropertiesURL();
        }
        return WEBSERVICE_URL;
    }
}
