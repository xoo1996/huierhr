/**
* <p>标题: </p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-8-3118:04:30</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.apps.test.bpo;

import java.sql.Connection;
import java.sql.SQLException;

import org.radf.apps.test.dao.TestDAO;
import org.radf.apps.test.entity.TestEntity;

import org.radf.plat.util.exception.AppException;

public class TestBPO extends org.radf.plat.util.bpo.BPOSupport{
    private static final String className = TestBPO.class.getName();
    private static final TestDAO dao = new TestDAO();
    public TestBPO(){
        super(className,dao);
    }
    
    public TestEntity login(Connection con,TestEntity obj) throws AppException {
        TestEntity dto = null;
        try{
            dto = dao.login(con,obj);
        }catch(SQLException se){                                                     //DAO层错误捕捉
            throw AppExceptionSupport(className,"findByRole","根据角色查找失败",se);
        }catch(Exception e){
            throw AppExceptionSupport(className,"findByRole",e);                     //BPO层错误捕捉
        }
        return dto;
    }
    
}
