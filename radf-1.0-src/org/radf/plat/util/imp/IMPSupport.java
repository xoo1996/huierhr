/**
* <p>标题：IMP基础类，所有IMP继承的基础</p>
* <p>说明：</p>
* <p>(1) 类中提供了关于IMP层所有异常返回结果的处理方法：</p>
* <p>   拼装程序中控制抛出的AppException异常
*           ExceptionSupport(String method,String msg)</p>
* <p>   拼装程序中控制抛出的AppException异常
*           ExceptionSupport(int errCode,String method,String msg)</p>
* <p>   处理BPO或方法中控制抛出的AppException异常
*           ExceptionSupport(String className,AppException ae,RequestEnvelopHead head)</p>
* <p>   处理包括SqlException、Exceptio等非AppException类型的异常
*           ExceptionSupport(String className,String method,int errorCode,String message,RequestEnvelopHead head)</p>
* <p>(2) 类中提供了格式固定的几个基本业务的SIEAF和LEAF两种模式实现：
* <p>   增加：         create、store</p>
* <p>   修改：         modify</p>
* <p>   删除：         remove</p>
* <p>   查询所有：      getAll</p>
* <p>   主键查询：      find</p>
* <p>   条件查询带分页： findBySQL(查询Object=null就是所有查询带分页)</p>
* <p>(3) 如果上述处理业务需要特殊处理，需要自行重写</p>
* <p>(4) 基础类占用了错误码中前2０个，个人编写业务时错误码从21开始
* 
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-11 15:02:23</p>
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
     * 增加一条数据，传入的数据可以是一个对象，也可以是一个sql语句。
     * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
     * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。
     * 返回的数据也在key="beo"的HashMap中
     * 产生的错误码包括11，12，13,14
     * @param request   IMP的入口参数
     * @param bpo       处理此业务的BPO
     * @param method    调用的方法名
     * @param nErrorCode    错误码
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
     * 增加一条数据，传入的数据可以是一个对象，也可以是一个sql语句。
     * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
     * 如果传入是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。
     * 无传出HashMap的数据
     * 产生的错误码包括15，16，17,14
     * @param request   IMP的入口参数
     * @param bpo       处理此业务的BPO
     * @param method    调用的方法名
     * @param nErrorCode    错误码
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
     * 根据主键删除数据统一方法，传入的数据可以是一个对象，也可以是一个sql语句。
     * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
     * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。
     * 无传出HashMap的数据
     * 产生的错误码包括06，07
     * @param request   IMP的入口参数
     * @param bpo       处理此业务的BPO
     * @param method    调用的方法名
     * @param nErrorCode    错误码
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
     * 根据主键修改数据统一方法，传入的数据可以是一个对象，也可以是一个sql语句。
     * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
     * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。
     * 无传出HashMap的数据
     * @param request   IMP的入口参数
     * @param bpo       处理此业务的BPO
     * @param method    调用的方法名
     * @param nErrorCode    错误码
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
     * 无条件获取指定表中所有数据，无传入参数。
     * 返回数据在HashMap的key="resultset"中
     * 此方法仅对单表有效，一定需要在DAO层有具体实现类，不可被公共使用。
     * 产生的错误码包括19,20
     * @param request   IMP的入口参数
     * @param bpo       处理此业务的BPO
     * @param method    调用的方法名
     * @param nErrorCode    错误码
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
     * 根据条件查询数据的统一方法。
     * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
     * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。
     * 返回的数据也在key="beo"的HashMap中，可能是一个EntitySupport类型对象，也可能是一个数据集列表。
     * 产生的错误码包括01，02
     * @param request   IMP的入口参数
     * @param bpo       处理此业务的BPO
     * @param method    调用的方法名
     * @param nErrorCode    错误码
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
     * 根据条件，分页方式查询数据的统一方法。
     * 传入参数在HashMap中key="beo"（生成sql条件的对象)，可以是一个对象也可以是一个sql语句，
     * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。
     * 传入参数还包括key = count(显示条数)、key = offset(第一条记录偏移序号)
     * 返回的数据也在key="collection"的HashMap中，是一个数据集列表。
     * 产生的错误码包括03，04，05
     * @param request   IMP的入口参数
     * @param bpo       处理此业务的BPO
     * @param method    调用的方法名
     * @param nErrorCode    错误码
     * @return Obj      返回结果对象
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
     * 获取满足条件的记录数。
     * 传入参数在HashMap中key="beo"（生成sql条件的对象)，可以是一个对象也可以是一个sql语句，
     * (1)传递的对象是一个SQL语句，返回这个sql语句执行获取的记录条数；
     * (2)传递的对象是一个实体类，则对象必须是EntitySupport的继承类，并可以通过fileKey获取配置文件中的sql语句；
     * 返回的数据也在key="count"的HashMap中，是一个字符型数字。
     * 注意：不能传select count(*)之类的语句，否则返回结果只能是一条记录
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
            // 得到从request中的HashMap
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
     * 增加数据的统一方法，有返回结果。
     * 由外部进行数据库事物控制，自身不控制。
     * 产生的错误码包括12，13，14
     * @param con
     * @param obj   要增加的对象
     * @param bpo   增加处理类
     * @param code  错误码
     * @throws AppException
     */
    protected Object create(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        try {
            //查找是否存在记录
            if(bpo!=null&&getBPO(bpo).findKEYMembers(con, obj) != null){
                throw new AppException(nErrorCode + 12,GlobalErrorMsg.IMP_EXIST_MSG);
            }
            //增加前处理
            getBPO(bpo).createFirst(con,obj);
            //增加
            obj = getBPO(bpo).create(con, obj);
            //增加后处理
            getBPO(bpo).createLatter(con,obj);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 13, ex);
        }
        return obj;
    }

    /**
     * 增加数据的统一方法，无返回结果。
     * 由外部进行数据库事物控制，自身不控制。
     * 产生的错误码包括16，17,14。
     * @param con   数据库连接
     * @param obj   要增加的对象
     * @param bpo   增加处理类
     * @param code  错误码
     * @throws AppException
     */
    protected void store(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        try {
            if(obj instanceof Vector ){
                //列表方式添加
                Vector list = (Vector) obj; 
                //保存所有数据
                for (Iterator it = list.iterator(); it.hasNext();) {
                    Object dto = it.next();
                    //重复性校验
                    if(bpo!=null&&bpo.findKEYMembers(con, dto)!=null){
                        throw new AppException(nErrorCode + 16,GlobalErrorMsg.IMP_EXIST_MSG);
                    }
                    //增加前处理
                    getBPO(bpo).createFirst(con,obj);
                    //增加
                    getBPO(bpo).store(con, dto);
                    //增加后处理
                    getBPO(bpo).createLatter(con,dto);
                }
            }else{                //单条记录增加
                //重复性校验
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
     * 根据条件修改数据的统一方法，默认情况下根据主键
     * 产生的错误码包括09，10
     * @param con   数据库连接
     * @param obj   要修改的主键对象
     * @param bpo   修改处理类
     * @param code  错误码
     * @throws AppException
     */
    protected void modify(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        try {
            //修改前数据操作
            getBPO(bpo).modifyFirst(con,obj);
            //修改
            getBPO(bpo).modify(con, obj);
            //修改后处理
            getBPO(bpo).modifyLatter(con,obj);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 9, ex);
        }
    }
    /**
     * 根据主键删除数据的统一方法
     * 产生的错误码包括07
     * @param con
     * @param obj   要删除的主键对象
     * @param bpo   处理类
     * @param code  错误码
     * @throws AppException
     */
    protected void remove(Connection con, Object obj, BPOSupport bpo,int nErrorCode) throws AppException {
        try {
            //删除前数据处理
            getBPO(bpo).removeFirst(con,obj);
            //删除数据
            getBPO(bpo).remove(con, obj);
            //删除后处理
            getBPO(bpo).removeLatter(con,obj);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(nErrorCode + 7, ex);
        }
    }
    /**
     * 根据可以唯一确定一条记录的主健或其他条件查询数据。
     * 输入的对象可以是sql语句或一个实体类，
     * 如果输入的对象是实体类，则SQL配置文件中必须有对应的sql查询语句。
     * 返回结果可能是一条记录也可能是多条记录，由具体实现DAO类或者sql语句判断。
     * @param con   数据库连接
     * @param obj
     * @param bpo   处理类
     * @param code  错误码
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
     * 根据可以唯一确定一条记录的主健或其他条件查询数据。
     * 输入的对象可以是sql语句或一个实体类，
     * 如果输入的对象是实体类，则SQL配置文件中必须有对应的sql查询语句。
     * 返回结果可能是一条记录也可能是多条记录，由具体实现DAO类或者sql语句判断。
     * @param con   数据库连接
     * @param obj
     * @param bpo   处理类
     * @param code  错误码
     * @throws AppException
     */
    protected Collection findBySQL(Connection con, Object obj, int count, int offset, BPOSupport bpo,int nErrorCode) throws AppException {
        String sql = getBPO(bpo).toSQL(obj);
        return getBPO(bpo).findBySQL(con,sql,count,offset);
       
    }
    /**
     * 根据传递的对象获取数据库中满足条件的纪录条数。支持两种方式：
     * (1)传递的对象是一个SQL语句，返回这个sql语句执行获取的记录条数；
     * (2)传递的对象是一个实体类，并可以通过fileKey获取配置文件中的sql语句；
     * 注意：如果传select count(*)之类的语句，返回结果只能是一条记录
     * @param con   数据库连接
     * @param obj
     * @param bpo   处理类
     * @param code  错误码
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
     * 拼装程序控制抛出的异常信息，返回格式"错误详情|错误概述|"
     * @param method    执行的方法
     * @param msg       错误概述
     * @return
     */
    protected AppException ExceptionSupport(String method,String msg){
        return ExceptionSupport(GlobalErrorCode.IMPUSEREXCEPTIONCODE,method,msg);
    }
    /**
     * 拼装程序控制抛出的异常信息，返回格式"错误详情|错误概述|"
     * @param errCode   错误码
     * @param method    执行的方法
     * @param msg       错误概述
     * @return
     */
    protected AppException ExceptionSupport(int errCode,String method,String msg){
        //返回客户端的概要错误信息
        String SmaCusString = ExceptionUtil.buildCusMsg(msg,GlobalErrorMsg.EXCEPTIONMESSAGE);
        //错误详情(总控上下文信息)
        //String ZKContextString = method + "方法执行错误 ";
        //沈云刚改
        String ZKContextString = method;
        //生成客户端的详细错误信息
        String cusString = ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        //错误码
        errCode = ExceptionUtil.buildErrorCode(errCode,GlobalErrorCode.IMPUSEREXCEPTIONCODE);
        return new AppException(errCode,cusString);
    }
    /**
/**
     * SIEAF框架模式：标准异常错误封装，只处理BPO或程序控制抛出的AppException异常
     * @param className    处理类名
     * @param AppException BPO或软件自身抛出的AppException异常
     * @param requestHead  总控方法入参RequestEnvelop.getHead()
     * @return ResponseEnvelopHead
     */
    protected ResponseEnvelopHead ExceptionSupport(String className,AppException ae,RequestEnvelopHead requestHead){
        String cusString = ae.getMessage().trim();
        int errCode = ae.getErrorCode();    //最后生成的错误码
        //判断是否是IMP中new AppException方式获得的异常的处理，如果是重新组装异常，否则使用原来的异常
        if(!cusString.endsWith("|")&&!cusString.startsWith("|")){
            ae = ExceptionSupport(ae.getErrorCode(),"",cusString);
            errCode = ae.getErrorCode();
        }else{
            //判断错误码是否已经设置
            errCode = ExceptionUtil.buildErrorCode(errCode,GlobalErrorCode.IMPUSEREXCEPTIONCODE);
        }

        //生成客户端错误结构"错误码|详细信息|概述信息|"，getMessage格式为错误详情|错误概要信息|
        cusString = ExceptionUtil.formatAppExceptionMsg(errCode,"总控类" + className + "中：" + ae.getMessage());
        
        ResponseEnvelopHead head = new ResponseEnvelopHead();
        //标记错误号
        head.setCode(errCode);
        //标记返回客户端的错误信息
        head.setMessage(ExceptionUtil.processCusMsg(cusString));
        
        //记录日志
        ExceptionUtil.saveLog(requestHead, className, ae.getErrorCode(), cusString);
        
        return head;
    }
    
    /**
     * SIEAF框架模式：标准异常错误封装，处理包括SqlException、Exceptio等非AppException类型的异常
     * @param className    处理类名
     * @param method       总控方法名
     * @param errorCode    错误码
     * @param message      错误描述
     * @param requestHead  总控方法入参RequestEnvelop.getHead()
     * @return ResponseEnvelopHead
     */
    protected ResponseEnvelopHead ExceptionSupport(String className,String method,int errorCode,String msg,RequestEnvelopHead requestHead){

        //返回客户端的概要错误信息
        String SmaCusString = ExceptionUtil.buildCusMsg(msg,GlobalErrorMsg.EXCEPTIONMESSAGE);
        //错误详情(总控上下文信息)
        String ZKContextString = className + "的总控方法"+method+"调用非总控方法发生错误";
        //生成客户端的详细错误信息
        String cusString =ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        //错误码
        errorCode = ExceptionUtil.buildErrorCode(errorCode,GlobalErrorCode.IMPEXCEPTIONCODE);
        //生成客户端错误结构"错误码|详细信息|概述信息|"
        cusString = ExceptionUtil.formatAppExceptionMsg(errorCode,cusString);
        
        ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
        //标记错误号
        responseHead.setCode(errorCode); 
        //标记返回客户端的错误信息
        responseHead.setMessage(ExceptionUtil.processCusMsg(cusString));
        
        //记录日志
        ExceptionUtil.saveLog(requestHead, className, errorCode, cusString);

        return responseHead;
    }
    /**
     * SIEAF框架模式：标准异常错误封装，处理包括SqlException、Exceptio等非AppException类型的异常
     * @param className    处理类名
     * @param method       总控方法名
     * @param errorCode    错误码
     * @param message      错误描述
     * @param requestHead  总控方法入参RequestEnvelop.getHead()
     * @return ResponseEnvelopHead
     */
    protected ResponseEnvelopHead ExceptionSupport(String className,String method,int errorCode,Exception e,RequestEnvelopHead requestHead){

        //返回客户端的概要错误信息
        String SmaCusString = ExceptionUtil.buildCusMsg(e.toString(),GlobalErrorMsg.EXCEPTIONMESSAGE);
        //错误详情(总控上下文信息)
        String ZKContextString = className + "的总控方法"+method+"调用非总控方法发生错误";
        //生成客户端的详细错误信息
        String cusString =ExceptionUtil.formatContextMsg(ZKContextString,SmaCusString);
        //错误码
        errorCode = ExceptionUtil.buildErrorCode(errorCode,GlobalErrorCode.IMPEXCEPTIONCODE);
        //生成客户端错误结构"错误码|详细信息|概述信息|"
        cusString = ExceptionUtil.formatAppExceptionMsg(errorCode,cusString);
        
        ResponseEnvelopHead responseHead = new ResponseEnvelopHead();
        //标记错误号
        responseHead.setCode(errorCode); 
        //标记返回客户端的错误信息
        responseHead.setMessage(ExceptionUtil.processCusMsg(cusString));
        
        //记录日志
        ExceptionUtil.saveLog(requestHead, className, errorCode, cusString);

        return responseHead;
    }
    /**
     * 判断当前应该获取具体实例的BPO还是框架的BPO
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
