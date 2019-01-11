package org.radf.apps.userinfo.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.TblLogin;
import org.radf.apps.commons.entity.UserInfo;
import org.radf.apps.userinfo.form.AccUserForm;
import org.radf.apps.userinfo.form.TblLoginForm;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.SubmitDataMap;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class BindSXJAction extends ActionLeafSupport {

	
	/**
	 * ���ݲ����е�Ա��id��ȡ����˺Ű󶨵���Ա��Ϣ
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//��ѯ�����е���Ҫ���¼���id
		String qsuperiorid = req.getParameter("qsuperiorid");
		System.out.println(qsuperiorid);
		String forward = "/userinfo/querySXJ.jsp";
		ActionForward af = new ActionForward();
		if(qsuperiorid == null || qsuperiorid.equals("")){	
			super.saveSuccessfulMsg(req, "�˺Ų���Ϊ�գ�");
			return mapping.findForward("toQuerySXJ");
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from tbluser where useremployid = ?";	
		try {
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,qsuperiorid);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "����Ϊ " +  qsuperiorid + " ��Ա�������ڣ�");
            	return mapping.findForward("toQuerySXJ");
            }
            
            
			String hql = "select u.username, u.useremployid,sj.superiorid  from tbluser u left join tbl_sup_jun sj on sj.juniorid = u.useremployid where sj.superiorid IN (SELECT au.userid FROM tbl_acc_user au WHERE u.useremployeestatus <> '0' AND  sj.superiorid = '" + qsuperiorid + "')";
			af = super.init(req, forward, hql);
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "��Ա����û���¼���";
				super.saveSuccessfulMsg(req, msg);
			}
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}
	
	
	/**
	 * ��ת���������¼��󶨵�jspҳ��
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 *//*
	public ActionForward toBindJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		TblLoginForm loginForm = (TblLoginForm)form;
		//��ѯ�����е���Ҫ���¼���id
		req.setAttribute("qentityid", loginForm.getQentityid());
		//��ѯ�����е���Ҫ��Ϊ�¼����¼�id
		req.setAttribute("xentityid", loginForm.getXentityid());
		return mapping.findForward("toBindJsp");
	}
	
	*/
	/**
	 * ��bindҳ���Ͻ�����Ա��ѯ���ҳ���Ҫ����Ϊ�¼�����Ա��Ϣ
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryEmployee(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//�ϼ�id
		String qsuperiorid = req.getParameter("qsuperiorid");
		//�¼�id
		String qjuniorid = req.getParameter("qjuniorid");
		String username = req.getParameter("username");
		String forward = "/userinfo/bindSXJ.jsp";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		ActionForward af = new ActionForward();
		String sql = "select * from tbluser where useremployid like ?";	

		try {
			
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"%" + qjuniorid + "%");
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "��Ա����Ϣ�����ڣ�");
            	return mapping.findForward("toBindSXJ");
            }
			String hql = "select distinct u.username,u.useremployid,sj.superiorid from tbluser u left join tbl_sup_jun sj  on u.useremployid = sj.juniorid where u.useremployid like '%" + qjuniorid + "%' and u.username like '%"+username+"%'";
			af = super.init(req, forward, hql);
			// ����Ƿ���ڣ�
			if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
				String msg = "û�в�ѯ�����������ļ�¼��";
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
	
	public ActionForward toBindJsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//��ѯ�����е���Ҫ���¼���id
		String qsuperiorid = req.getParameter("qsuperiorid");
		System.out.println(qsuperiorid);
		String forward = "/userinfo/querySXJ.jsp";
		ActionForward af = new ActionForward();
		if(qsuperiorid == null || qsuperiorid.equals("")){	
			super.saveSuccessfulMsg(req, "�˺Ų���Ϊ�գ�");
			return mapping.findForward("toQuerySXJ");
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from tbluser where useremployid = ?";	
		try {
			con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,qsuperiorid);
            resultset = pstmt.executeQuery();
            DBUtil.commit(con);
            if (!resultset.next()) {
            	super.saveSuccessfulMsg(req, "����Ϊ " +  qsuperiorid + " ��Ա�������ڣ�");
            	return mapping.findForward("toQuerySXJ");
            }else
            {
            	return mapping.findForward("toBindSXJ");
            }
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return mapping.findForward("toQuerySXJ");
	}
	
	
	/**
	 * �ڻ����Ҫ�󶨵����¼�id֮����а󶨲���
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward bindSXJ(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//�ϼ�id
		String qsuperiorid = req.getParameter("qsuperiorid");
		//�¼�id
		String juniorid = req.getParameter("useremployid");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from tbl_sup_jun where juniorid = ?";	
        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,juniorid);
            resultset = pstmt.executeQuery();
            
            if (resultset.next()) {
            	super.saveSuccessfulMsg(req, "����Ա�Ѱ󶨣����Ƚ��");
            	return mapping.findForward("toBindSXJ");
            }else{
	        	String sql_bind2 = "insert into tbl_sup_jun(juniorid,superiorid) values(?,?)";
	        	pstmt = con.prepareStatement(sql_bind2);
	        	pstmt.setString(1,juniorid);
	            pstmt.setString(2,qsuperiorid);
	            pstmt.executeUpdate();
	            DBUtil.commit(con);
            }
        }catch(Exception e){
            e.printStackTrace();
            super.saveSuccessfulMsg(req, "ϵͳ����");
            return mapping.findForward("toBindSXJ");
        }finally{
        	DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        super.saveSuccessfulMsg(req, "�󶨳ɹ���");
        ActionForward actionForward = new ActionForward();
		actionForward.setPath("");
		actionForward.setRedirect(true);
    	return mapping.findForward("toBindSXJ");
	}
	
	/**
	 * ��������˺���Ա������
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward unWrapSXJ(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		//�¼�����
		String juniorid = req.getParameter("useremployid");
		//�ϼ�����
		String superiorid = req.getParameter("superiorid");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String sql = "select * from tbl_sup_jun where juniorid = ? and superiorid = ?";	

        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,juniorid);
            pstmt.setString(2,superiorid);
            resultset = pstmt.executeQuery();
            
            if (resultset.next()) {
            	String sql_delete = "delete from tbl_sup_jun where juniorid = ?";
            	pstmt = con.prepareStatement(sql_delete);
                pstmt.setString(1,juniorid);
                pstmt.executeUpdate();
                DBUtil.commit(con);
            }else{ 
                super.saveSuccessfulMsg(req, "����Աδ�󶨣�");
            	return mapping.findForward("toBindSXJ");
            }
        }catch(Exception e){
            e.printStackTrace();
            super.saveSuccessfulMsg(req, "ϵͳ����");
            return mapping.findForward("toBindSXJ");
        }finally{
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        super.saveSuccessfulMsg(req, "���ɹ���");
    	return mapping.findForward("toBindSXJ");
	}
	
	
	/**
	 * ������������˺���Ա������
	 * @param mapping
	 * @param form
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public ActionForward batchUnwrapSXJ(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		SubmitDataMap data = new SubmitDataMap(req);
		String[] chk = req.getParameterValues("chk");
		String[] juniorids = data.getParameterValues("useremployid");
		String[] superiorids = data.getParameterValues("superiorid");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		String msg = null;
        try{
        	con = DBUtil.getJDBCConnection();
        	DBUtil.beginTrans(con);
        	for(int t= 0;t<chk.length;t++){
        		int i = Integer.parseInt(chk[t]) - 1;
        		String juniorid = juniorids[i];
        		String superiorid = superiorids[i];
        		if(juniorid != null && !juniorid.equals("")){
        			String sql = "select * from tbl_sup_jun where juniorid = ?";	
        			pstmt = con.prepareStatement(sql);
					pstmt.setString(1,juniorid);
				    resultset = pstmt.executeQuery();
				    if (resultset.next()) {
				    	String sql_delete = "delete from tbl_sup_jun where juniorid = ? and superiorid = ?";
						pstmt = con.prepareStatement(sql_delete);
					    pstmt.setString(1,juniorid);
					    pstmt.setString(2,superiorid);
					    pstmt.executeUpdate();
					    DBUtil.commit(con);
					    resultset.close();
					    pstmt.close();
				    }else{  
				    	msg = " " + juniorid +",";
					    resultset.close();
					    pstmt.close();
					    break;
					}
				    
        		}
        	}
        }catch(Exception e){
            e.printStackTrace();
            super.saveSuccessfulMsg(req, "ϵͳ����");
            return mapping.findForward("toQuerySXJ");
        }finally{
        	DBUtil.closeResult(resultset);
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
        if(msg == null){
        	super.saveSuccessfulMsg(req, "���ɹ���");
        	return mapping.findForward("toQuerySXJ");
        }
        else{
        	super.saveSuccessfulMsg(req, "����Ϊ" +msg + "��Ա�������ʧ�ܣ�");
			return mapping.findForward("toQuerySXJ");
        }
        
	}
	
}
