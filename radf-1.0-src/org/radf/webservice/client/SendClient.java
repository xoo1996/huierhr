/**
* <p>����: WebService Client</p>
* <p>˵��: �ṩ���û��Ľӿڴ���</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2006-11-289:01:36</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.webservice.client;


public class SendClient {
    private static SendServiceSoapBindingStub instance = null;
    private static String WEBSERVICE_URL = null;
    
    /**
     * ��ʼ��WebService�ӿ�
     * @param url       Զ�̷�����webservice�����ַ������http://localhost:8080/plat/services/SendService
     * @param timeOut   ��ʱʱ�䣬��λ����
     * @throws Exception
     */
    public static void init(String url,int timeOut)throws Exception{
        //�趨������WebServiceλ��
        if(url==null){
            throw new Exception("û�д���WebService�����URL");
        }else{
            url = url.trim();
        }
        if(instance==null||WEBSERVICE_URL==null||!WEBSERVICE_URL.equalsIgnoreCase(url)){
            LogonURL.setURL(url);
            try{
                instance = null;
                instance = (SendServiceSoapBindingStub) new ServiceFacadeServiceLocator().getSendService();
                //��ʱʱ�䣬����
                instance.setTimeout(timeOut);
            }catch (javax.xml.rpc.ServiceException jre) {
                if(jre.getLinkedCause()!=null)
                    jre.getLinkedCause().printStackTrace();
                throw jre;
            }
        }
    }
    

    /**
     * ������Ϣ�������շ�����Ϣ
     * @param msg   Ҫ���͵������ַ���
     * @return      Զ�̷��صĽ���ַ���
     * @throws Exception
     */
    public static String Send(String msg)throws Exception{
        java.lang.String retValue = null;
        if(instance==null){
            throw new Exception("WebService �ӿ�û�г�ʼ������ͨ��init������ʼ��");
        }
        try {
            //��һ������Ҫ��ҵ�����
            retValue = instance.send(msg);
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return retValue;
    }

}
