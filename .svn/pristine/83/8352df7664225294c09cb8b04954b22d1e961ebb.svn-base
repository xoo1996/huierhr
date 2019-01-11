/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-8-3118:02:06</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.apps.test.imp;

import java.sql.Connection;
import java.util.HashMap;

import org.radf.apps.test.bpo.TestBPO;
import org.radf.apps.test.entity.TestEntity;
import org.radf.apps.test.facade.TestFacade;

import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;

public class TestIMP extends org.radf.plat.util.imp.IMPSupport implements TestFacade{
    // 下面一段是固定的，每个类都一样的格式定义
    private static final String className = TestIMP.class.getName();
    private static int errorCode = 8888888;
    public TestIMP() {
        super(className);
    }

    // 下面定义imp中需要的所有bpo，任何bpo都不应该在方法内部new方式获取
    private TestBPO testBPO = new TestBPO();

    // 定义不同功能模块的错误编码，对于简单系统，也可以不定义而统一设置成0
    private int userErrorCode = 14020400;

    // 定义公共的基本操作接口，目前包括增加(store、create)、修改(modify)、删除(remove)、查所有(getAll)、查主键(find)、分页条件查询(findBySQL)六类，每个都可以按照如下格式定义但不具体编码实现相应功能
    public ResponseEnvelop findUserBySQL(RequestEnvelop request) {
        return modify(request, testBPO, " findUserBySQL", userErrorCode);
    }
 

    // 业务所需要的功能的定义，自行编写的业务逻辑，整个格式基本如下
    public ResponseEnvelop userLogin(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            // 入口map中数据的获取，出错则直接抛出错误到Exception中
            HashMap mapRequest = (HashMap) request.getBody();
            TestEntity test = (TestEntity)mapRequest.get("dto");
            // 如果有数据库操作，可以使用多种方式创建数据库链接，具体参考DBUtil定义
            con = DBUtil.getConnection();
            // 如果有数据库读写操作则创建数据库事务，保证后面1或多个操作都在一个事物中
            DBUtil.beginTrans(con);
            // 数据处理、计算等业务操作，如果需要不同表的处理，必须通过不同bpo实现，注意事物一致性
            TestEntity obj = testBPO.login(con,test);
            // logBPO.stopLog(con,obj);
            // 组装并返回传出的参数，极少发生错误，放在提交前面只是为了防止编码人员处理不当造成拼装错误
            HashMap map = new HashMap();
            map.put("dto",obj);
            response.setBody(map);
            // 一定要在处理的最后提交数据库事务，避免中间数据整理的异常抛出错误却提交了数据
            DBUtil.commit(con);
        } catch (AppException ae) {
            response
                    .setHead(ExceptionSupport(className, ae, request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, "userLogin",
                    errorCode, ex.getMessage(), request.getHead()));
        } finally {
            // 如果有数据库事务，则此处rollback
            DBUtil.rollback(con);
            // 有数据库操作，则此处一定close
            DBUtil.closeConnection(con);
        }
        return response;
    }
}
