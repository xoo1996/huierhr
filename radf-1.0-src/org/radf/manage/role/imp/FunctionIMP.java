package org.radf.manage.role.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.radf.manage.role.bpo.SysChangeBPO;
import org.radf.manage.role.bpo.SysFunctionBPO;
import org.radf.manage.role.dao.SysFunctionDAO;
import org.radf.manage.role.entity.SysFunction;
import org.radf.manage.role.facade.FunctionFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelopHead;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;
/**
 * 管理交易定义SYSFUNCTION的IMP
 * @author zqb
 * @version 1.0
 */
public class FunctionIMP extends org.radf.plat.util.imp.IMPSupport implements FunctionFacade{
    private static final String className = FunctionIMP.class.getName();
    public FunctionIMP(){
        super(className);
    }
    
    private SysFunctionBPO bpo = new SysFunctionBPO();
    private SysChangeBPO changeBPO = new SysChangeBPO();

    /**
     * 生成一条sysfunction记录
     */
    public ResponseEnvelop createFunction(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        HashMap body = (HashMap)request.getBody();

        SysFunction sysFunction = (SysFunction)body.get("SysFunction");
        String parentFlag = (String)body.get("parentFlag");

        SysFunctionDAO dao = new SysFunctionDAO();
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);

            sysFunction = bpo.createFunction(con,sysFunction,parentFlag);
            
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"addChildFunction",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
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
     * 修改sysfunction记录
     */
    public ResponseEnvelop modifyFunction(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        SysFunction sysFunction = (SysFunction)request.getBody();

        SysFunctionDAO dao = new SysFunctionDAO();
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            con.setAutoCommit(false);
            bpo.modifyFunction(con,sysFunction);
            con.commit();
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"modifyFunction",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
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
     * 删除sysFunction及关联的sysFuncControlMap记录
     */
    public ResponseEnvelop removeFunction(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        SysFunction fun = (SysFunction)request.getBody();

        Connection con = null;
         try {
             con = DBUtil.getConnection();
             con.setAutoCommit(false);
             bpo.removeFunction(con,fun);
//             logger.debug("Delete : [" + fun.getFunctionId() + "]");
             con.commit();
         } catch (AppException ae){
             response.setHead(ExceptionSupport(className,ae,request.getHead()));
         } catch (Exception ex) {
             response.setHead(ExceptionSupport(className,"removeFunction",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
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
     * 根据主键查询sysfunction记录
     */
    public ResponseEnvelop findFunctionByPK(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        SysFunction fun = (SysFunction)request.getBody();
        SysFunction sysFunction = null;

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            sysFunction = bpo.findFunctionByPK(con,fun);
            if(sysFunction == null){
                ResponseEnvelopHead head=new ResponseEnvelopHead();
                head.setCode(ManageErrorCode.FINDFUNCTIONBYPK_N);
                head.setMessage(GlobalErrorMsg.DAO_SQL_NOTFIND);
                response.setHead(head);
            }else{
                response.setBody(sysFunction);
            }
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"findFunctionByPK",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }

    /**
     * 获取指定SysFunction的父SysFunction
     */
    public ResponseEnvelop getChildPairs(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        SysFunction fun = (SysFunction)request.getBody();
        SysFunction sysFunction = null;

        Connection con = null;
        try {
            con = DBUtil.getConnection();
            sysFunction = bpo.findParentFunction(con,fun);
            if(sysFunction == null){
                ResponseEnvelopHead head=new ResponseEnvelopHead();
                head.setCode(ManageErrorCode.FINDFUNCTIONBYPK_N);
                head.setMessage(GlobalErrorMsg.DAO_SQL_NOTFIND);
                response.setHead(head);
            }else{
                response.setBody(sysFunction);
            }
        } catch (AppException ae){
            response.setHead(ExceptionSupport(className,ae,request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,"getChildPairs",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
         } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception ex) {
            }
        }
        return response;
    }
    
    /**
     *查询id大于指定值的SYSCHANGE记录
     */
      public ResponseEnvelop findNewVersion(RequestEnvelop request){
          ResponseEnvelop response = new ResponseEnvelop();
          String id = (String)request.getBody();

          Connection con = null;
          try {
              ArrayList result = null;

              con = DBUtil.getConnection();
              result = changeBPO.findNewVersion(con,Long.parseLong(id));
              response.setBody(result);
          } catch (AppException ae){
              response.setHead(ExceptionSupport(className,ae,request.getHead()));
          } catch (Exception ex) {
              response.setHead(ExceptionSupport(className,"findNewVersion",ManageErrorCode.SQLERROR,ex.getMessage(),request.getHead()));
           } finally {
              try {
                  if (con != null)
                      con.close();
              } catch (Exception ex) {
              }
          }
          return response;
      }
}
