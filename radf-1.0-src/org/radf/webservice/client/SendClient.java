/**
* <p>标题: WebService Client</p>
* <p>说明: 提供给用户的接口处理</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-11-289:01:36</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.webservice.client;


public class SendClient {
    private static SendServiceSoapBindingStub instance = null;
    private static String WEBSERVICE_URL = null;
    
    /**
     * 初始化WebService接口
     * @param url       远程服务器webservice服务地址，例如http://localhost:8080/plat/services/SendService
     * @param timeOut   超时时间，单位毫秒
     * @throws Exception
     */
    public static void init(String url,int timeOut)throws Exception{
        //设定服务器WebService位置
        if(url==null){
            throw new Exception("没有传递WebService服务的URL");
        }else{
            url = url.trim();
        }
        if(instance==null||WEBSERVICE_URL==null||!WEBSERVICE_URL.equalsIgnoreCase(url)){
            LogonURL.setURL(url);
            try{
                instance = null;
                instance = (SendServiceSoapBindingStub) new ServiceFacadeServiceLocator().getSendService();
                //超时时间，毫秒
                instance.setTimeout(timeOut);
            }catch (javax.xml.rpc.ServiceException jre) {
                if(jre.getLinkedCause()!=null)
                    jre.getLinkedCause().printStackTrace();
                throw jre;
            }
        }
    }
    

    /**
     * 发送信息，并接收返回信息
     * @param msg   要发送的内容字符串
     * @return      远程返回的结果字符串
     * @throws Exception
     */
    public static String Send(String msg)throws Exception{
        java.lang.String retValue = null;
        if(instance==null){
            throw new Exception("WebService 接口没有初始化，请通过init方法初始化");
        }
        try {
            //这一行是主要的业务调用
            retValue = instance.send(msg);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retValue;
    }

}
