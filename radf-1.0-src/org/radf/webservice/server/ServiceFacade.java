/**
* <p>标题: WebService 服务端</p>
* <p>说明: 接口定义，无需修改，主要业务逻辑实现在WebServiceIMP.send方法中</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-11-2410:27:54</p>
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
