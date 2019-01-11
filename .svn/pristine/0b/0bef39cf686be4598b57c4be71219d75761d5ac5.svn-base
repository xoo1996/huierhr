/**
* <p>标题: 字符串发送接收的通用WebService</p>
* <p>说明: 接收客户端传递的字符串，并进行处理</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-11-2410:27:38</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.webservice.server;

import org.radf.plat.commons.StringUtil;

public class WebServiceIMP{

    public String send(String msg) {
        System.out.println("From Client Msg :" + msg);
        return "RetunrMsg------" + msg;
    }
    //以下几个方法可能会对数据处理有用：
    /**
     * 将给出的字符串source使用chr划分为单词数组
     */
    private String[] split(String source, String chr) {
        //这种格式效率稍高，但是只对单个字符做分割符有效，例如用","
        //return StringUtil.split(source,chr);
        //这种方式效率略低，可以使用复合字符做分割符，例如"||"
        return StringUtil.getAsStringArray(source,chr);
    }
}
