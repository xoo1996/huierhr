package org.radf.apps.userinfo.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.userinfo.form.AccUserForm;
import org.radf.apps.userinfo.form.TblLoginForm;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class BindAccUserAction extends ActionLeafSupport {

	/**
	 * 根据参数中的账号获取与该账号绑定的人员信息
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryAccountInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String taraccount = req.getParameter("taraccount");
		String forward = "/userinfo/queryAccount.jsp";
		ActionForward af = new ActionForward();
		if(taraccount == null || taraccount.equals("")){
			super.saveSuccessfulMsg(req, "账号不能为空！");
			return mapping.findForward("toQueryAccount");
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from sc05 where bsc011 = ?";	
		try {
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,taraccount);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "该账号不存在！");
            	return mapping.findForward("toQueryAccount");
            }
            
            
			String hql = "select u.username,u.useremployid,au.account from tbluser u left join tbl_acc_user au on u.useremployid = au.userid where au.account = '" + taraccount + "'";
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				/*String msg = "该账号未绑定人员信息！";*/
				/*super.saveSuccessfulMsg(req, msg);*/
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}finally{
		 	DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
		}
		return af;
	}
	
	public ActionForward toQueryAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {

		String taraccount = req.getParameter("taraccount");
		String forward = "/userinfo/queryAccount.jsp";
		ActionForward af = new ActionForward();
		if(taraccount == null || taraccount.equals("")){
			super.saveSuccessfulMsg(req, "账号不能为空！");
			return mapping.findForward("toQueryAccount");
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from sc05 where bsc011 = ?";	
		try {
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,taraccount);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "该账号不存在！");
            	return mapping.findForward("toQueryAccount");
            }else{
            	return mapping.findForward("toBindAccount");
            }
		} catch (Exception e) {
			this.saveErrors(req, e);
		}finally{
		 	DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
		}
		return mapping.findForward("toQueryAccount");
	}
	
	/**
	 * 账号与员工绑定。账号先在页面上输入，人员经过该方法获得
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		AccUserForm accuserForm = (AccUserForm)form;
		String taraccount = req.getParameter("taraccount");
		String useremployid = req.getParameter("quseremployid");
		String qusername = req.getParameter("qusername");
		req.setAttribute("taraccount", taraccount);
		String forward = "/userinfo/bindAccount.jsp";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		ActionForward af = new ActionForward();
		String sql = "select * from sc05 where bsc011 = ?";	

		try {
			
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            //查找账号是否存在
        	pstmt = con.prepareStatement(sql);
            pstmt.setString(1,taraccount);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "该账号不存在！");
            	return mapping.findForward("toQueryAccount");
            }
            
            //查询当前输入的工号是否有绑定账号
			String hql = "select u.username,u.useremployid,au.account from tbluser u left join tbl_acc_user au on u.useremployid = au.userid";
			String where = " where";
			//前面有多少判断条件

			int count = 0;
			if(qusername !=null && !qusername.equals("")){
				if(count > 0)where += " and ";
				where += " u.username = '" + accuserForm.getQusername() + "'";
				count++;
			}
			if(useremployid !=null && !useremployid.equals("")){
				if(count > 0)where += " and ";
				where += " u.useremployid='" + useremployid + "'";
				count++;
			}
			if(count > 0)
				hql += where;
			af = super.init(req, forward, hql);
			// 检查是否存在？
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "没有查询到符合条件的记录！";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}finally{
		 	DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
		}
		return af;
	}
	
	/**
	 * 账号人员绑定
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward bindAccUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String useremployid = req.getParameter("useremployid");
		String taraccount = req.getParameter("taraccount");
		String qusername = req.getParameter("qusername");
		String qusername1 = req.getParameter("username1");
		req.setAttribute("useremployid", useremployid);
		req.setAttribute("taraccount", taraccount);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from tbl_acc_user where userid = ?";	
        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,useremployid);
            resultset = pstmt.executeQuery();
            
            if (resultset.next()) {
            	super.saveSuccessfulMsg(req, "该人员已绑定账号，请先解绑！");
            	return mapping.findForward("toBindAccount");
            }else{
            	String sql_bind = "insert into tbl_acc_user(account,userid) values(?,?)";
            	pstmt = con.prepareStatement(sql_bind);
            	pstmt.setString(1,taraccount);
                pstmt.setString(2,useremployid);
                pstmt.executeUpdate();
                DBUtil.commit(con);
            }
        }catch(Exception e){
            e.printStackTrace();
            super.saveSuccessfulMsg(req, "系统出错！");
            return mapping.findForward("toBindAccount");
        }finally{
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        super.saveSuccessfulMsg(req, "绑定成功！");
        ActionForward actionForward = new ActionForward();
		actionForward.setPath("");
		actionForward.setRedirect(true);
    	return mapping.findForward("toBindAccount");
	}
	
	
	/**
	 * 批量解绑已有账号与员工关联
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward batchUnwrapAccUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SubmitDataMap data = new SubmitDataMap(req);
		String[] chk = req.getParameterValues("chk");
		String[] useremployids = data.getParameterValues("useremployid");
		String[] accounts = data.getParameterValues("account");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String msg = null;
        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
        	for(int t= 0;t<chk.length;t++){
        		int i = Integer.parseInt(chk[t]) - 1;
        		String useremployid = useremployids[i];
        		String account = accounts[i];
        		if(useremployids[i] != null && !useremployids[i].equals("")){
        			String sql = "select * from tbl_acc_user where userid = ?";	
        			pstmt = con.prepareStatement(sql);
					pstmt.setString(1,useremployid);
				    resultset = pstmt.executeQuery();
				    if (resultset.next()) {
				    	String sql_delete = "delete from tbl_acc_user where userid = ? and account = ?";
						pstmt = con.prepareStatement(sql_delete);
					    pstmt.setString(1,useremployid);
					    pstmt.setString(2,account);
					    pstmt.executeUpdate();
					    DBUtil.commit(con);
					    resultset.close();
					    pstmt.close();
				    }else{  
				    	msg = " " + useremployid +",";
					    resultset.close();
					    pstmt.close();
					    break;
					}
				    
        		}
        	}
        }catch(Exception e){
            e.printStackTrace();
            super.saveSuccessfulMsg(req, "系统出错！");
            return mapping.findForward("toQueryAccount");
        }finally{
        	DBUtil.closeResult(resultset);
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        if(msg == null){
        	super.saveSuccessfulMsg(req, "解绑成功！");
        	return mapping.findForward("toQueryAccount");
        }
        else{
        	super.saveSuccessfulMsg(req, "工号为" +msg + "的员工解除绑定失败！");
			return mapping.findForward("toQueryAccount");
        }
        
	}
	
	
	/**
	 * 解绑已有账号与员工关联
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward unWrapAccUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		String useremployid = req.getParameter("useremployid");
		String account = req.getParameter("account");
		String username = req.getParameter("username");
		req.setAttribute("uername", username);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from tbl_acc_user where userid = ?";	

        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,useremployid);
            resultset = pstmt.executeQuery();
            
            if (resultset.next()) {
            	String sql_delete = "delete from tbl_acc_user where userid = ?";
            	pstmt = con.prepareStatement(sql_delete);
                pstmt.setString(1,useremployid);
                pstmt.executeUpdate();
                DBUtil.commit(con);
            }else{ 
                super.saveSuccessfulMsg(req, "该人员未绑定！");
            	return mapping.findForward("toBindAccount");
            }
        }catch(Exception e){
            e.printStackTrace();
            super.saveSuccessfulMsg(req, "系统出错！");
            return mapping.findForward("bindAccount");
        }finally{
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        super.saveSuccessfulMsg(req, "解绑成功！");
    	return mapping.findForward("toBindAccount");
	}
	
	
}
