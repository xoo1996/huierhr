package org.radf.manage.trans.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.radf.manage.trans.bpo.TransDefBPO;
import org.radf.manage.trans.bpo.TransLogBPO;
import org.radf.manage.trans.entity.SysTransLog;
import org.radf.manage.trans.entity.SysTransVastInput;
import org.radf.manage.trans.entity.SysTransVastOutput;
import org.radf.manage.trans.entity.SysTranseDef;
import org.radf.manage.trans.facade.TransFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;
/**
 * 交易管理
 * 包括SYSTRANSLOG、SYSTRANSEDEF、SYSTRANSVASTINPUT、SYSTRANSVASTOUTPUT的处理
 * @author zqb
 * @version 1.0
 */

public class TransIMP extends org.radf.plat.util.imp.IMPSupport implements TransFacade{
    private String className = this.getClass().getName();
    private TransDefBPO defBPO = new TransDefBPO();
    private TransLogBPO logBPO = new TransLogBPO();

    public TransIMP(){
   }
    /**
     *生成SYSTRANSEDEF记录
     */
    public ResponseEnvelop createTrans(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        SysTranseDef trans = (SysTranseDef)request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            trans = defBPO.createTrans(con,trans);
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"createTrans",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null){
                    con.rollback();
                    con.close();
                }
            } catch (Exception ex) {
            }
        }
       return response;
    }

    
    /**
     *修改SYSTRANSEDEF记录
     */
    public ResponseEnvelop modifyTrans(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        SysTranseDef trans = (SysTranseDef)request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            defBPO.modifyTrans(con,trans);
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"modifyTrans",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            try {
                if(con != null){
                    con.rollback();
                    con.close();
                }
            }catch (Exception ex) {
            }
        }
        return response;
    }
    /**
     * 删除SYSTRANSEDEF记录
     */
    public ResponseEnvelop removeTrans(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        SysTranseDef dto = (SysTranseDef)request.getBody();

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            defBPO.removeTrans(con,dto);
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"removeTrans",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            try {
                if(con != null){
                    con.rollback();
                    con.close();
                }
            }catch (Exception ex) {
            }
        }
        return response;
    }
    /**
     * 查询SYSTRANSEDEF记录
     */
    public ResponseEnvelop findTransByPK(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        SysTranseDef dto = (SysTranseDef)request.getBody();

        Connection con = null;
        try {
            SysTranseDef trans = null;
            con = DBUtil.getConnection();
            trans = defBPO.findTransByPK(con,dto);
            if(trans == null){
                response.setHead(ExceptionSupport(className,"findTransByPK",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));
            }else{
                response.setBody(trans);
            }
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findTransByPK",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }
    
    /**
     *查询所有SYSTRANSEDEF记录
     */
    public ResponseEnvelop findAllTrans(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();

        Connection con = null;
        try {
            ArrayList result = null;
            con = DBUtil.getConnection();
            result = defBPO.findAllTrans(con);
            response.setBody(result);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findAllTrans",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }
    
    /****************************以下关于SystransLog的操作**************************/
    
    /**
     * 创建交易日志，同时增加输入输出日志
     * 本交易共产生2个日志：SysTransLog、SYSTRANSVASTINPUT
     * @param SysTransLog 保存的交易日志信息（日志ｉｄ为空）
     * @param String inputString 交易输入参数
     * @return  SysTransLog 带有logId的日志信息
     */
    public SysTransLog createTransLog(SysTransLog dto,String inputString) throws AppException{
        SysTransLog translog = null;
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            con = DBUtil.getConnection();
            translog = logBPO.createTransLog(con,dto);
            
            //create TransInputDTO
            SysTransVastInput inDTO = new SysTransVastInput();
            inDTO.setLogId(translog.getLogId());
            inDTO.setData(inputString.getBytes());
            logBPO.createSysTransVastInput(con,inDTO);
            con.commit();
        } catch (AppException ae){
            throw new AppException(ae);
        } catch (Exception ex) {
            throw new AppException(ManageErrorCode.SQLERROR,className + "的总控方法createTrans调用非总控方法发生错误:"+ex.getMessage());
         } finally {
            try {
                if (con != null){
                    con.rollback();
                    con.close();
                }
            } catch (Exception ex) {
            }
        }
        return translog;
    }
    
    /**
     * 创建交易日志，同时增加输入输出日志
     * 本交易共产生2个日志：SysTransLog、SYSTRANSVASTINPUT
     * @param SysTransLog 保存的交易日志信息（日志ｉｄ为空）
     * @param String inputString 交易输入参数
     * @return  SysTransLog 带有logId的日志信息
     */
    public SysTransLog modifyTransLog(SysTransLog dto,String outString) throws AppException{
        SysTransLog translog = null;
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            con = DBUtil.getConnection();
            translog = logBPO.createTransLog(con,dto);
            
            //create TransInputDTO
            SysTransVastOutput outDTO = new SysTransVastOutput();
            outDTO.setLogId(translog.getLogId());
            outDTO.setData(outString.getBytes());
            logBPO.createSysTransVastOutput(con,outDTO);
            con.commit();
        } catch (AppException ae){
            throw new AppException(ae);
        } catch (Exception ex) {
            throw new AppException(ManageErrorCode.SQLERROR,className + "的总控方法modifyTransLog调用非总控方法发生错误:"+ex.getMessage());
         } finally {
            try {
                if (con != null){
                    con.rollback();
                    con.close();
                }
            } catch (Exception ex) {
            }
        }
        return translog;
    }    
    
    /**
     * 查询logid大于指定值的SYSTRANSLOG记录
     */
    public ResponseEnvelop findTransLogForward(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        String id = (String)request.getBody();

        Connection con = null;
        try {
            ArrayList result = null;
            con = DBUtil.getConnection();
            result = (ArrayList)logBPO.findTransLogForward(con,id);
            response.setBody(result);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findTransLogForward",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }

    /**
     * 查询logid小于指定值的SYSTRANSLOG记录
     */
    public ResponseEnvelop findTransLogBack(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        String id = (String)request.getBody();

        Connection con = null;
        try {
            ArrayList result = null;
            con = DBUtil.getConnection();
            result = logBPO.findTransLogBack(con,id);
            response.setBody(result);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findTransLogBack",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }

    /**
     * 查询SYSTRANSLOG记录
     */
    public ResponseEnvelop findTransLogByPK(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        String id = (String)request.getBody();

        Connection con = null;
        try {
            SysTransLog logg = null;
            con = DBUtil.getConnection();
            logg = logBPO.findTransLogByPK(con,id);
            if(logg == null){
                response.setHead(ExceptionSupport(className,"findTransLogByPK",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));
            }else{
                response.setBody(logg);
            }
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findTransLogBack",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
         return response;
    }

    /**
     * 根据Time查询SYSTRANSLOG记录,分页
     */
    public ResponseEnvelop findTransLogByTime(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();

        HashMap body = (HashMap)request.getBody();
        String beginTime = (String)body.get("beginTime");
        String endTime = (String)body.get("endTime");
        Integer count = (Integer)body.get("count");
        Integer offset = (Integer)body.get("offset");

        Connection con = null;
        ArrayList result = null;
        try {
            con = DBUtil.getConnection();
            result = (ArrayList)logBPO.findTransLogByTime(con,beginTime,endTime,count.intValue(),offset.intValue());
            response.setBody(result);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findTransLogBack",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }


    /**
     * 根据TransName查询SYSTRANSLOG记录,分页
     */
    public ResponseEnvelop findTransLogByTransName(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        HashMap body = (HashMap)request.getBody();
        String transName = (String)body.get("transName");
        Integer count = (Integer)body.get("count");
        Integer offset = (Integer)body.get("offset");

        Connection con = null;
        try {
            ArrayList result = null;
            con = DBUtil.getConnection();
            result = (ArrayList)logBPO.findTransLogByTransName(con,transName,count.intValue(),offset.intValue());
            response.setBody(result);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findTransLogByTransName",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }

    /**
     * 根据登录名LoginName查询SYSTRANSLOG记录,分页
     */
    public ResponseEnvelop findTransLogByLoginName(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();

        HashMap body = (HashMap)request.getBody();
        String loginName = (String)body.get("loginName");

        Integer count = (Integer)body.get("count");
        Integer offset = (Integer)body.get("offset");

        Connection con = null;
        try {
            ArrayList result = null;
            con = DBUtil.getConnection();
            result = (ArrayList)logBPO.findTransLogByLoginName(con,loginName,count.intValue(),offset.intValue());
            if(result==null){
                result = new ArrayList();
            }
            response.setBody(result);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findTransLogByTransName",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }


    /**
     * 根据Status查询SYSTRANSLOG记录,分页
     */
    public ResponseEnvelop findTransLogByStatus(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        HashMap body = (HashMap)request.getBody();
        String status = (String)body.get("status");
        Integer count = (Integer)body.get("count");
        Integer offset = (Integer)body.get("offset");

        Connection con = null;
        try {
            ArrayList result = null;
            con = DBUtil.getConnection();
            result = (ArrayList)logBPO.findTransLogByStatus(con,status,count.intValue(),offset.intValue());
            response.setBody(result);
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findTransLogByStatus",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }
    
    /**
     * 查询SYSTRANSVASTINPUT、SYSTRANSVASTOUT记录
     */
    public ResponseEnvelop findInOutputByPK(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        String id = (String)request.getBody();
        
        Connection con = null;
        try {
            SysTransVastInput input = null;
            SysTransVastOutput output = null;
            HashMap body = null;

            con = DBUtil.getConnection();
            input = logBPO.findInLogByPK(con,id);
            output = logBPO.findOutLogByPK(con,id);
            
            if(input == null){
                response.setHead(ExceptionSupport(className,"findInOutputByPK",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));               
            }else{
                body = new HashMap();
                body.put("inputData",input);
                if(output != null)
                    body.put("outputData",output);
                response.setBody(body);
            }
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findInOutputByPK",ManageErrorCode.NOSUCHRECORD,GlobalErrorMsg.IMP_NODATA_ERROR,request.getHead()));
        } finally {
            try {
                if(con != null)
                     con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }

}