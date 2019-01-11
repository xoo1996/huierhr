/**
* <p>标题: 客户端版本信息表数据</p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-8-2613:39:45</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.bpo;

import org.radf.manage.version.dao.SysClientVersionDAO;

public class SysClientVersionBPO extends org.radf.plat.util.bpo.BPOSupport{
    public SysClientVersionBPO(){
        super("SysClientVersionBPO",new SysClientVersionDAO());
    }

}
