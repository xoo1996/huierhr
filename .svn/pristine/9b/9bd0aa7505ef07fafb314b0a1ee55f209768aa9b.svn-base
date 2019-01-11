/**
 * SkillIMP.java 2008/03/24
 * 
 * Copyright (c) 2008 - 2017
 * All rights reserved.<br>
 * @author yulei
 * @version 1.0
 */
package org.radf.apps.basicinfo.imp;

import java.sql.Connection;
import java.util.HashMap;


import org.radf.apps.commons.entity.Cc0d;
import org.radf.apps.basicinfo.facade.SkillFacade;

import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.CommonDB;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
/**
 * 个人技能管理数据访问层
 */
public class SkillIMP extends IMPSupport implements SkillFacade
{
    private String className = this.getClass().getName();

    /**
     * 保存个人技能
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
    public ResponseEnvelop saveSkill(RequestEnvelop request)
    {
        ResponseEnvelop response = new ResponseEnvelop();
        Connection con = null;
        String workString="";//所做操作的语句，用于LOG
        try
        {
            HashMap map = (HashMap) request.getBody();
            Cc0d cc0d = (Cc0d) map.get("beo");
            con = DBUtil.getConnection();
            DBUtil.beginTrans(con);
            //判断主键是否为空，空就执行增加，否则执行修改
            if ((null == cc0d.getAcc0d0()) || ("".equals(cc0d.getAcc0d0())))
            {
                // insert
                cc0d.setAcc0d0(CommonDB.getSequence(con,"SEQ_ACC0D0",10,"0"));// 得到技能编码
                cc0d.setFileKey("cc0d_insert");
                store(con, cc0d, null, 0);
                workString="增加个人技能信息";
            }
            else
            {
                //update
                cc0d.setFileKey("cc0d_update");
                modify(con, cc0d, null, 0);
                workString="修改个人技能信息";
            }
            DBUtil.commit(con);
            HashMap retmap = new HashMap();
            retmap.put("workString",workString);
            response.setBody(retmap);
        }
        catch (AppException ae)
        {
            response
                    .setHead(ExceptionSupport(className, ae, request.getHead()));
        }
        catch (Exception ex)
        {
            response.setHead(ExceptionSupport(
                                              className,
                                              "saveSkill",
                                              ManageErrorCode.SQLERROR,
                                              ex.getMessage(),
                                              request.getHead()));
        }
        finally
        {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        return response;
    }

    /**
     * 删除个人技能
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
    public ResponseEnvelop removeSkill(RequestEnvelop request){
        return remove(request,null,"remove",0);
    }
    
    /**
     * 查询个人技能详细信息
     * @param	request	 业务逻辑层的参数封装请求
     * @return	数据层返回的处理结果
	 */
    public ResponseEnvelop findSkill(RequestEnvelop request){
        return find(request,null,"find",0);
    }

}
