/**
* <p>包名称: org.radf.plat.commons</p>
* <p>标题: FindLog.java</p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-10-25</p>
* 
* @author linke
* @version 1.0
*/

package org.radf.plat.commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.radf.login.dto.LoginDTO;
/**
 * @author linke
 * @date 2006-10-25 16:20:36
 * @
 */
public class FindLog {
    
    public FindLog(){
        }        
    //登记操作员记录
    public static void insertLog(HttpServletRequest request,String args1,String args2){      
        Connection con = null;
		LoginDTO loginform = (LoginDTO) request.getSession().getAttribute(
        "LoginDTO");
        try {
            
            
			//String menuid=request.getParameter("menuId");
			//String action=request.getParameter("method");
            
            String bze101=loginform.getBsc012();
            //String aze100=loginform.getLoginName();
            //String bze103=mf.getAac001();
            //String bze105="该人员在"+menuid+"的操作是:"+action+"";
            String bze102=loginform.getAab300();
            Date bze104=DateUtil.getSystemCurrentTime();
            
//          获取连接
            con = DBUtil.getConnection();    
//			获取序列号
            String bze001=CommonDB.getSequence(con,"SEQ_BZE001",10,"0");
            String sql="insert into log0(bze001,bze101,bze102,bze103,bze104,bze105) values('"+bze001+"','"+bze101+"','"+bze102+"','"+args1+"',sysdate,'"+args2+"')";
            DBUtil.execSQL(con,sql);
            DBUtil.commit(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }       
    }
    
    

}
