/**
* <p>����: WebService �����</p>
* <p>˵��: �ӿڶ��壬�����޸ģ���Ҫҵ���߼�ʵ����WebServiceIMP.send������</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2006-11-2410:27:54</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.webservice.server;

public class ServiceFacade {
    private static WebServiceIMP instance = null;
    private WebServiceIMP getInstance() {
        if (instance == null) {
            instance= new WebServiceIMP();
        }
        return instance;
    }
    
    public String send(String msg){
        return getInstance().send(msg);
    }
}
