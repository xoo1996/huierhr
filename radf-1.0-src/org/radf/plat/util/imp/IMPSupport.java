/**
* <p>���⣺IMP�����࣬����IMP�̳еĻ���</p>
* <p>˵����</p>
* <p>(1) �����ṩ�˹���IMP�������쳣���ؽ���Ĵ�������</p>
* <p>   ƴװ�����п����׳���AppException�쳣
*           ExceptionSupport(String method,String msg)</p>
* <p>   ƴװ�����п����׳���AppException�쳣
*           ExceptionSupport(int errCode,String method,String msg)</p>
* <p>   ����BPO�򷽷��п����׳���AppException�쳣
*           ExceptionSupport(String className,AppException ae,RequestEnvelopHead head)</p>
* <p>   �������SqlException��Exceptio�ȷ�AppException���͵��쳣
*           ExceptionSupport(String className,String method,int errorCode,String message,RequestEnvelopHead head)</p>
* <p>(2) �����ṩ�˸�ʽ�̶��ļ�������ҵ���SIEAF��LEAF����ģʽʵ�֣�
* <p>   ���ӣ�         create��store</p>
* <p>   �޸ģ�         modify</p>
* <p>   ɾ����         remove</p>
* <p>   ��ѯ���У�      getAll</p>
* <p>   ������ѯ��      find</p>
* <p>   ������ѯ����ҳ�� findBySQL(��ѯObject=null�������в�ѯ����ҳ)</p>
* <p>(3) �����������ҵ����Ҫ���⴦����Ҫ������д</p>
* <p>(4) ������ռ���˴�������ǰ2���������˱�дҵ��ʱ�������21��ʼ
* 
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-5-11 15:02:23</p>
* @author zqb
* @version 1.0
*/
package org.radf.plat.util.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.radf.apps.commons.entity.Ac01;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.RequestEnvelopHead;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelopHead;
import org.radf.plat.util.bpo.BPOSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ExceptionUtil;
import org.radf.plat.util.facade.FACADESupport;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalErrorMsg;

public class IMPSupport implements FACADESupport{
    private String className = "IMPSupport";
    private static BPOSupport BPO = new BPOSupport("DAOSupport",null);
    public IMPSupport(){
    }
    public IMPSupport(String className){
        this.className = className;
    }
    
    public ResponseEnvelop create(RequestEnvelop request) {
        return create(request,null,"create",0);
    }
    public ResponseEnvelop store(RequestEnvelop request) {
        return store(request,null,"store",0);
    }
    public ResponseEnvelop remove(RequestEnvelop request) {
        return remove(request,null,"remove",0);
    }
    public ResponseEnvelop modify(RequestEnvelop request) {
        return modify(request,null,"modify",0);
    }
    public ResponseEnvelop getAll(RequestEnvelop request) {
        return getAll(request,null,"getAll",0);
    }
    public ResponseEnvelop find(RequestEnvelop request) {
        return find(request,null,"find",0);
    }
    public ResponseEnvelop findBySQL(RequestEnvelop request) {
        return findBySQL(request,null,"findBySQL",0);
    }
    public ResponseEnvelop getCount(RequestEnvelop request) {
        return getCount(request,null,"getCount",0);
    }
    
    
    /**
     * ����һ�����ݣ���������ݿ�����һ������Ҳ������һ��sql��䡣
     * ���������HashMap��key="beo"��������һ������Ҳ������һ��sql��䣬
     * �����һ����������������EntitySupport�ļ̳��࣬��fileKey�������ļ������ҵ�SQL��䡣
     * ���ص�����Ҳ��key="beo"��HashMap��
     * �����Ĵ��������11��12��13,14
     * @param request   IMP����ڲ���
     * @param bpo       �����ҵ���BPO
     * @param method    ���õķ�����
     * @param nErrorCode    ������
     * @return
     */
    protected ResponseEnvelop create(RequestEnvelop request,BPOSupport bpo,String method,int nErrorCode) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            HashMap mapRequest = (HashMap) request.getBody();
            Object obj = mapRequest.get("beo");
            con = DBUtil.getConnection();
            DBUtil.beginTrans(con);
            Object retObj = create(con,obj,bpo,nErrorCode);
            DBUtil.commit(con);
            HashMap mapResponse = new HashMap(1);
            mapResponse.put("beo",retObj);
            response.setBody(mapResponse);
        } catch (AppException ae) {
            response.setHead(ExceptionSupport(className, ae, request
                    .getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, method,
                    nErrorCode + 11, ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**
     * ����һ�����ݣ���������ݿ�����һ������Ҳ������һ��sql��䡣
     * ���������HashMap��key="beo"��������һ������Ҳ������һ��sql��䣬
     * ���������һ����������������EntitySupport�ļ̳��࣬��fileKey�������ļ������ҵ�SQL��䡣
     * �޴���HashMap������
     * �����Ĵ��������15��16��17,14
     * @param request   IMP����ڲ���
     * @param bpo       �����ҵ���BPO
     * @param method    ���õķ�����
     * @param nErrorCode    ������
     * @return
     */
    protected ResponseEnvelop store(RequestEnvelop request,BPOSupport bpo,String method,int nErrorCode) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            HashMap mapRequest = (HashMap) request.getBody();
            Object obj = mapRequest.get("beo");
            con = DBUtil.getConnection();
            DBUtil.beginTrans(con);
            store(con,obj,bpo,nErrorCode);
            DBUtil.commit(con);
        } catch (AppException ae) {
            response.setHead(ExceptionSupport(className, ae, request
                    .getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, method,
                    nErrorCode + 15, ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**
     * ��������ɾ������ͳһ��������������ݿ�����һ������Ҳ������һ��sql��䡣
     * ���������HashMap��key="beo"��������һ������Ҳ������һ��sql��䣬
     * �����һ����������������EntitySupport�ļ̳��࣬��fileKey�������ļ������ҵ�SQL��䡣
     * �޴���HashMap������
     * �����Ĵ��������06��07
     * @param request   IMP����ڲ���
     * @param bpo       �����ҵ���BPO
     * @param method    ���õķ�����
     * @param nErrorCode    ������
     * @return
     */
    protected ResponseEnvelop remove(RequestEnvelop request,BPOSupport bpo,String method,int nErrorCode) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            HashMap mapRequest = (HashMap) request.getBody();
            Object obj = mapRequest.get("beo");
            con = DBUtil.getConnection();
            DBUtil.beginTrans(con);
            remove(con,obj,bpo,nErrorCode);
            DBUtil.commit(con);
        } catch (AppException ae) {
            response.setHead(ExceptionSupport(className, ae, request
                    .getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, method,
                    nErrorCode + 6, ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
    /**
     * ���������޸�����ͳһ��������������ݿ�����һ������Ҳ������һ��sql��䡣
     * ���������HashMap��key="beo"��������һ������Ҳ������һ��sql��䣬
     * �����һ����������������EntitySupport�ļ̳��࣬��fileKey�������ļ������ҵ�SQL��䡣
     * �޴���HashMap������
     * @param request   IMP����ڲ���
     * @param bpo       �����ҵ���BPO
     * @param method    ���õķ�����
     * @param nErrorCode    ������
     * @return
     */
    protected ResponseEnvelop modify(RequestEnvelop request,BPOSupport bpo,String method,int nErrorCode) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            HashMap mapRequest = (HashMap) request.getBody();
            Object obj = mapRequest.get("beo");
			Ac01 ac01 = (Ac01) mapRequest.get("ac01");
            con = DBUtil.getConnection();
            DBUtil.beginTrans(con);
            modify(con,obj,bpo,nErrorCode);
			if(ac01!=null)
			modify(con,ac01,bpo,nErrorCode);
            DBUtil.commit(con);
        } catch (AppException ae) {
            response.setHead(ExceptionSupport(className, ae, request
                    .getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, method,
                    nErrorCode + 8, ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**
     * ��������ȡָ�������������ݣ��޴��������
     * ����������HashMap��key="resultset"��
     * �˷������Ե�����Ч��һ����Ҫ��DAO���о���ʵ���࣬���ɱ�����ʹ�á�
     * �����Ĵ��������19,20
     * @param request   IMP����ڲ���
     * @param bpo       �����ҵ���BPO
     * @param method    ���õķ�����
     * @param nErrorCode    ������
     * @return
     */
    protected ResponseEnvelop getAll(RequestEnvelop request,BPOSupport bpo,String method,int nErrorCode) {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            Collection retObj = bpo.findAllMembers(con);
            HashMap mapResponse = new HashMap(1);
            mapResponse.put("resultset",retObj);
            response.setBody(mapResponse);
        } catch (AppException ae) {
            response.setHead(ExceptionSupport(className, ae, request
                    .getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, method,
                    nErrorCode + 19, ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**
     * ����������ѯ���ݵ�ͳһ������
     * ���������HashMap��key="beo"��������һ������Ҳ������һ��sql��䣬
     * �����һ����������������EntitySupport�ļ̳��࣬��fileKey�������ļ������ҵ�SQL��䡣
     * ���ص�����Ҳ��key="beo"��HashMap�У�������һ��EntitySupport���Ͷ���Ҳ������һ�����ݼ��б�
     * �����Ĵ��������01��02
     * @param request   IMP����ڲ���
     * @param bpo       �����ҵ���BPO
     * @param method    ���õķ�����
     * @param nErrorCode    ������
     * @return
     */
    protected ResponseEnvelop find(RequestEnvelop request,BPOSupport bpo,String method,int nErrorCode){
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            HashMap mapRequest=(HashMap)request.getBody();
            Object obj = mapRequest.get("beo");
            con = DBUtil.getConnection();
            Object retObj = find(con,obj,bpo,nErrorCode);
            if(retObj!=null){
                HashMap mapResponse = new HashMap(1);
                mapResponse.put("beo",retObj);
                response.setBody(mapResponse);
            }else{
				HashMap mapResponse = new HashMap(1);
				 response.setBody(mapResponse);
            }
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, method,
                    nErrorCode + 1, ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**
     * ������������ҳ��ʽ��ѯ���ݵ�ͳһ������
     * ���������HashMap��key="beo"������sql�����Ķ���)��������һ������Ҳ������һ��sql��䣬
     * �����һ����������������EntitySupport�ļ̳��࣬��fileKey�������ļ������ҵ�SQL��䡣
     * �������������key = count(��ʾ����)��key = offset(��һ����¼ƫ�����)
     * ���ص�����Ҳ��key="collection"��HashMap�У���һ�����ݼ��б�
     * �����Ĵ��������03��04��05
     * @param request   IMP����ڲ���
     * @param bpo       �����ҵ���BPO
     * @param method    ���õķ�����
     * @param nErrorCode    ������
     * @return Obj      ���ؽ������
     */
    protected ResponseEnvelop findBySQL(RequestEnvelop request,BPOSupport bpo,String method,int nErrorCode){
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            HashMap mapRequest=(HashMap)request.getBody();
            Object obj = mapRequest.get("beo");
            int count  = (new Integer((String)mapRequest.get("count"))).intValue();
            int offset = (new Integer((String)mapRequest.get("offset"))).intValue();
            con = DBUtil.getConnection();
            Collection list = findBySQL(con,obj,count,offset,bpo,nErrorCode);
            HashMap mapResponse = new HashMap(1);
            mapResponse.put("collection",list);
            response.setBody(mapResponse);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, method,
                    nErrorCode + 3, ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**
     * ��ȡ���������ļ�¼����
     * ���������HashMap��key="beo"������sql�����Ķ���)��������һ������Ҳ������һ��sql��䣬
     * (1)���ݵĶ�����һ��SQL��䣬�������sql���ִ�л�ȡ�ļ�¼������
     * (2)���ݵĶ�����һ��ʵ���࣬����������EntitySupport�ļ̳��࣬������ͨ��fileKey��ȡ�����ļ��е�sql��䣻
     * ���ص�����Ҳ��key="count"��HashMap�У���һ���ַ������֡�
     * ע�⣺���ܴ�select count(*)֮�����䣬���򷵻ؽ��ֻ����һ����¼
     * @param request
     * @param bpo
     * @param method
     * @param nErrorCode
     * @return
     */
    protected ResponseEnvelop getCount(RequestEnvelop request,BPOSupport bpo,String method,int nErrorCode){
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        try {
            // �õ���request�е�HashMap
            HashMap map = (HashMap) request.getBody();
            Object obj = map.get("beo");
            con = DBUtil.getConnection();
            int count = getCount(con, obj,0);
            HashMap retmap = new HashMap();
            retmap.put("count", String.valueOf(count));
            response.setBody(retmap);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className, method,
                    nErrorCode, ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    /**
     * �������ݵ�ͳһ�������з��ؽ����
     * ���ⲿ�������ݿ�������ƣ��������ơ�
     * �����Ĵ��������12��13��14
     * @param con
     * @param obj   Ҫ���ӵĶ���
     * @param bpo   ���Ӵ�����
     * @param code  ������
     * @throws AppException
     */
    protected Object create(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        try {
            //�����Ƿ���ڼ�¼
            if(bpo!=null&&getBPO(bpo).findKEYMembers(con, obj) != null){
                throw new AppException(nErrorCode + 12,GlobalErrorMsg.IMP_EXIST_MSG);
            }
            //����ǰ����
            getBPO(bpo).createFirst(con,obj);
            //����
            obj = getBPO(bpo).create(con, obj);
            //���Ӻ���
            getBPO(bpo).createLatter(con,obj);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 13, ex);
        }
        return obj;
    }

    /**
     * �������ݵ�ͳһ�������޷��ؽ����
     * ���ⲿ�������ݿ�������ƣ��������ơ�
     * �����Ĵ��������16��17,14��
     * @param con   ���ݿ�����
     * @param obj   Ҫ���ӵĶ���
     * @param bpo   ���Ӵ�����
     * @param code  ������
     * @throws AppException
     */
    protected void store(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        try {
            if(obj instanceof Vector ){
                //�б�ʽ���
                Vector list = (Vector) obj; 
                //������������
                for (Iterator it = list.iterator(); it.hasNext();) {
                    Object dto = it.next();
                    //�ظ���У��
                    if(bpo!=null&&bpo.findKEYMembers(con, dto)!=null){
                        throw new AppException(nErrorCode + 16,GlobalErrorMsg.IMP_EXIST_MSG);
                    }
                    //����ǰ����
                    getBPO(bpo).createFirst(con,obj);
                    //����
                    getBPO(bpo).store(con, dto);
                    //���Ӻ���
                    getBPO(bpo).createLatter(con,dto);
                }
            }else{                //������¼����
                //�ظ���У��
                if(bpo!=null&&bpo.findKEYMembers(con, obj)!=null){
                    throw new AppException(nErrorCode + 16,GlobalErrorMsg.IMP_EXIST_MSG);
                }
                getBPO(bpo).createFirst(con,obj);
                getBPO(bpo).store(con, obj);
                getBPO(bpo).createLatter(con,obj);
            }
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 17, ex);
        }
    }

    /**
     * ���������޸����ݵ�ͳһ������Ĭ������¸�������
     * �����Ĵ��������09��10
     * @param con   ���ݿ�����
     * @param obj   Ҫ�޸ĵ���������
     * @param bpo   �޸Ĵ�����
     * @param code  ������
     * @throws AppException
     */
    protected void modify(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        try {
            //�޸�ǰ���ݲ���
            getBPO(bpo).modifyFirst(con,obj);
            //�޸�
            getBPO(bpo).modify(con, obj);
            //�޸ĺ���
            getBPO(bpo).modifyLatter(con,obj);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 9, ex);
        }
    }
    /**
     * ��������ɾ�����ݵ�ͳһ����
     * �����Ĵ��������07
     * @param con
     * @param obj   Ҫɾ������������
     * @param bpo   ������
     * @param code  ������
     * @throws AppException
     */
    protected void remove(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        try {
            //ɾ��ǰ���ݴ���
            getBPO(bpo).removeFirst(con,obj);
            //ɾ������
            getBPO(bpo).remove(con, obj);
            //ɾ������
            getBPO(bpo).removeLatter(con,obj);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 7, ex);
        }
    }
    /**
     * ���ݿ���Ψһȷ��һ����¼������������������ѯ���ݡ�
     * ����Ķ��������sql����һ��ʵ���࣬
     * �������Ķ�����ʵ���࣬��SQL�����ļ��б����ж�Ӧ��sql��ѯ��䡣
     * ���ؽ��������һ����¼Ҳ�����Ƕ�����¼���ɾ���ʵ��DAO�����sql����жϡ�
     * @param con   ���ݿ�����
     * @param obj
     * @param bpo   ������
     * @param code  ������
     * @throws AppException
     */
    protected Object find(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        Object retObj = null;
        try{
            retObj = getBPO(bpo).findKEYMembers(con,obj);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 7, ex);
        }
        return retObj;
    }
    /**
     * ���ݿ���Ψһȷ��һ����¼������������������ѯ���ݡ�
     * ����Ķ��������sql����һ��ʵ���࣬
     * �������Ķ�����ʵ���࣬��SQL�����ļ��б����ж�Ӧ��sql��ѯ��䡣
     * ���ؽ��������һ����¼Ҳ�����Ƕ�����¼���ɾ���ʵ��DAO�����sql����жϡ�
     * @param con   ���ݿ�����
     * @param obj
     * @param bpo   ������
     * @param code  ������
     * @throws AppException
     */
    protected Collection findBySQL(Connection con, Object obj, int count, int offset, BPOSupport bpo,int nErrorCode) throws AppException {
        String sql = getBPO(bpo).toSQL(obj);
        return getBPO(bpo).findBySQL(con,sql,count,offset);
       
    }
    /**
     * ���ݴ��ݵĶ����ȡ���ݿ������������ļ�¼������֧�����ַ�ʽ��
     * (1)���ݵĶ�����һ��SQL��䣬�������sql���ִ�л�ȡ�ļ�¼������
     * (2)���ݵĶ�����һ��ʵ���࣬������ͨ��fileKey��ȡ�����ļ��е�sql��䣻
     * ע�⣺�����select count(*)֮�����䣬���ؽ��ֻ����һ����¼
     * @param con   ���ݿ�����
     * @param obj
     * @param bpo   ������
     * @param code  ������
     * @throws AppException
     */
    protected int getCount(Connection con, Object obj,int nErrorCode) throws AppException {
        try{
            String sql = DBUtil.getSQLByObject(obj,4);
            sql = "select count(*) from ("+sql+")";
            return DBUtil.getRowCount(con,sql);
        } catch (SQLException ae) {
            throw new AppException(ae);
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 7, ex);
        }
    }

    /**
     * ƴװ��������׳����쳣��Ϣ�����ظ�ʽ"��������|�������|"
     * @param method    ִ�еķ���
     * @param msg       �������
     * @return
     */
    protected AppException ExceptionSupport(String method,String msg){
        return ExceptionSupport(GlobalErrorCode.IMPUSEREXCEPTIONCODE,method,msg);
    }
    /**
     * ƴװ��������׳����쳣��Ϣ�����ظ�ʽ"��������|�������|"
     * @param errCode   ������
     * @param method    ִ�еķ���
     * @param msg       �������
     * @return
     */
    protected AppException ExceptionSupport(int errCode,String method,String msg){
        //���ؿͻ��˵ĸ�Ҫ������Ϣ
        String SmaCusString = ExceptionUtil.buildCusMsg(msg,GlobalErrorMsg.EXCEPTIONMESSAGE);
        //��������(�ܿ���������Ϣ)
        //String ZKContextString = method + "����ִ�д��� ";
        //���Ƹո�
        String ZKContextString = method;
        //���ɿͻ��˵���ϸ������Ϣ
        String cusString = ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        //������
        errCode = ExceptionUtil.buildErrorCode(errCode,GlobalErrorCode.IMPUSEREXCEPTIONCODE);
        return new AppException(errCode,cusString);
    }
    /**
/**
     * SIEAF���ģʽ����׼�쳣�����װ��ֻ����BPO���������׳���AppException�쳣
     * @param className    ��������
     * @param AppException BPO����������׳���AppException�쳣
     * @param requestHead  �ܿط������RequestEnvelop.getHead()
     * @return ResponseEnvelopHead
     */
    protected ResponseEnvelopHead ExceptionSupport(String className,AppException ae,RequestEnvelopHead requestHead){
        String cusString = ae.getMessage().trim();
        int errCode = ae.getErrorCode();    //������ɵĴ�����
        //�ж��Ƿ���IMP��new AppException��ʽ��õ��쳣�Ĵ��������������װ�쳣������ʹ��ԭ�����쳣
        if(!cusString.endsWith("|")&&!cusString.startsWith("|")){
            ae = ExceptionSupport(ae.getErrorCode(),"",cusString);
            errCode = ae.getErrorCode();
        }else{
            //�жϴ������Ƿ��Ѿ�����
            errCode = ExceptionUtil.buildErrorCode(errCode,GlobalErrorCode.IMPUSEREXCEPTIONCODE);
        }

        //���ɿͻ��˴���ṹ"������|��ϸ��Ϣ|������Ϣ|"��getMessage��ʽΪ��������|�����Ҫ��Ϣ|
        cusString = ExceptionUtil.formatAppExceptionMsg(errCode,"�ܿ���" + className + "�У�" + ae.getMessage());
        
        ResponseEnvelopHead head = new ResponseEnvelopHead();
        //��Ǵ����
        head.setCode(errCode);
        //��Ƿ��ؿͻ��˵Ĵ�����Ϣ
        head.setMessage(ExceptionUtil.processCusMsg(cusString));
        
        //��¼��־
        ExceptionUtil.saveLog(requestHead, className, ae.getErrorCode(), cusString);
        
        return head;
    }
    
    /**
     * SIEAF���ģʽ����׼�쳣�����װ���������SqlException��Exceptio�ȷ�AppException���͵��쳣
     * @param className    ��������
     * @param method       �ܿط�����
     * @param errorCode    ������
     * @param message      ��������
     * @param requestHead  �ܿط������RequestEnvelop.getHead()
     * @return ResponseEnvelopHead
     */
    protected ResponseEnvelopHead ExceptionSupport(String className,String method,int errorCode,String msg,RequestEnvelopHead requestHead){

        //���ؿͻ��˵ĸ�Ҫ������Ϣ
        String SmaCusString = ExceptionUtil.buildCusMsg(msg,GlobalErrorMsg.EXCEPTIONMESSAGE);
        //��������(�ܿ���������Ϣ)
        String ZKContextString = className + "���ܿط���"+method+"���÷��ܿط�����������";
        //���ɿͻ��˵���ϸ������Ϣ
        String cusString =ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        //������
        errorCode = ExceptionUtil.buildErrorCode(errorCode,GlobalErrorCode.IMPEXCEPTIONCODE);
        //���ɿͻ��˴���ṹ"������|��ϸ��Ϣ|������Ϣ|"
        cusString = ExceptionUtil.formatAppExceptionMsg(errorCode,cusString);
        
        ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
        //��Ǵ����
        responseHead.setCode(errorCode); 
        //��Ƿ��ؿͻ��˵Ĵ�����Ϣ
        responseHead.setMessage(ExceptionUtil.processCusMsg(cusString));
        
        //��¼��־
        ExceptionUtil.saveLog(requestHead, className, errorCode, cusString);

        return responseHead;
    }
    /**
     * SIEAF���ģʽ����׼�쳣�����װ���������SqlException��Exceptio�ȷ�AppException���͵��쳣
     * @param className    ��������
     * @param method       �ܿط�����
     * @param errorCode    ������
     * @param message      ��������
     * @param requestHead  �ܿط������RequestEnvelop.getHead()
     * @return ResponseEnvelopHead
     */
    protected ResponseEnvelopHead ExceptionSupport(String className,String method,int errorCode,Exception e,RequestEnvelopHead requestHead){

        //���ؿͻ��˵ĸ�Ҫ������Ϣ
        String SmaCusString = ExceptionUtil.buildCusMsg(e.toString(),GlobalErrorMsg.EXCEPTIONMESSAGE);
        //��������(�ܿ���������Ϣ)
        String ZKContextString = className + "���ܿط���"+method+"���÷��ܿط�����������";
        //���ɿͻ��˵���ϸ������Ϣ
        String cusString =ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        //������
        errorCode = ExceptionUtil.buildErrorCode(errorCode,GlobalErrorCode.IMPEXCEPTIONCODE);
        //���ɿͻ��˴���ṹ"������|��ϸ��Ϣ|������Ϣ|"
        cusString = ExceptionUtil.formatAppExceptionMsg(errorCode,cusString);
        
        ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
        //��Ǵ����
        responseHead.setCode(errorCode); 
        //��Ƿ��ؿͻ��˵Ĵ�����Ϣ
        responseHead.setMessage(ExceptionUtil.processCusMsg(cusString));
        
        //��¼��־
        ExceptionUtil.saveLog(requestHead, className, errorCode, cusString);

        return responseHead;
    }
    /**
     * �жϵ�ǰӦ�û�ȡ����ʵ����BPO���ǿ�ܵ�BPO
     * @param bpo
     * @return
     */
    private BPOSupport getBPO(BPOSupport bpo){
        if(bpo==null){
            return BPO;
        }else{
            return bpo;
        }
    }
}
