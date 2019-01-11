/**
 * ServiceFacadeServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis WSDL2Java emitter.
 */

package org.radf.webservice.client;

public class ServiceFacadeServiceTestCase extends junit.framework.TestCase {
    public ServiceFacadeServiceTestCase(java.lang.String name) {
        super(name);
    }
    public void test1SendServiceSend() throws Exception {
        try {
            //设定服务器WebService位置
            SendClient.init("http://localhost:8080/plat/services/SendService",60000);
            //发送到服务器端的内容
            String retValue = SendClient.Send("test......");
            //这就是返回结果
            System.out.println("From Server Msg :" + retValue);
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
    }

}
