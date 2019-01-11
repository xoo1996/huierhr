/**
* <p>����: </p>
* <p>˵��: </p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2006-8-3118:02:06</p>
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
    // ����һ���ǹ̶��ģ�ÿ���඼һ���ĸ�ʽ����
    private static final String className = TestIMP.class.getName();
    private static int errorCode = 8888888;
    public TestIMP() {
        super(className);
    }

    // ���涨��imp����Ҫ������bpo���κ�bpo����Ӧ���ڷ����ڲ�new��ʽ��ȡ
    private TestBPO testBPO = new TestBPO();

    // ���岻ͬ����ģ��Ĵ�����룬���ڼ�ϵͳ��Ҳ���Բ������ͳһ���ó�0
    private int userErrorCode = 14020400;

    // ���幫���Ļ��������ӿڣ�Ŀǰ��������(store��create)���޸�(modify)��ɾ��(remove)��������(getAll)��������(find)����ҳ������ѯ(findBySQL)���࣬ÿ�������԰������¸�ʽ���嵫���������ʵ����Ӧ����
    public ResponseEnvelop findUserBySQL(RequestEnvelop request) {
        return modify(request, testBPO, " findUserBySQL", userErrorCode);
    }
 

    // ҵ������Ҫ�Ĺ��ܵĶ��壬���б�д��ҵ���߼���������ʽ��������
    public ResponseEnvelop userLogin(RequestEnvelop request) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            // ���map�����ݵĻ�ȡ��������ֱ���׳�����Exception��
            HashMap mapRequest = (HashMap) request.getBody();
            TestEntity test = (TestEntity)mapRequest.get("dto");
            // ��������ݿ����������ʹ�ö��ַ�ʽ�������ݿ����ӣ�����ο�DBUtil����
            con = DBUtil.getConnection();
            // ��������ݿ��д�����򴴽����ݿ����񣬱�֤����1������������һ��������
            DBUtil.beginTrans(con);
            // ���ݴ��������ҵ������������Ҫ��ͬ��Ĵ�������ͨ����ͬbpoʵ�֣�ע������һ����
            TestEntity obj = testBPO.login(con,test);
            // logBPO.stopLog(con,obj);
            // ��װ�����ش����Ĳ��������ٷ������󣬷����ύǰ��ֻ��Ϊ�˷�ֹ������Ա���������ƴװ����
            HashMap map = new HashMap();
            map.put("dto",obj);
            response.setBody(map);
            // һ��Ҫ�ڴ��������ύ���ݿ����񣬱����м�����������쳣�׳�����ȴ�ύ������
            DBUtil.commit(con);
        } catch (AppException ae) {
            response
                    .setHead(ExceptionSupport(className, ae, request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, "userLogin",
                    errorCode, ex.getMessage(), request.getHead()));
        } finally {
            // ��������ݿ�������˴�rollback
            DBUtil.rollback(con);
            // �����ݿ��������˴�һ��close
            DBUtil.closeConnection(con);
        }
        return response;
    }
}
