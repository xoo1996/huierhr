package org.radf.manage.log.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.radf.manage.entity.Sc10;
import org.radf.manage.entity.Sc11;
import org.radf.manage.entity.Sc12;
import org.radf.manage.entity.Sc13;
import org.radf.manage.log.form.Sc10Form;
import org.radf.manage.log.form.Sc11Form;
import org.radf.manage.log.form.Sc12Form;
import org.radf.manage.log.form.Sc13Form;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.util.action.ActionLeafSupport;

public class LogAction extends ActionLeafSupport {
	/**
	 * 获取登陆日志信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward FindSc10List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/log/Sc10List.jsp";
		ActionForward af = new ActionForward(forward);
		Sc10Form sc10Form = (Sc10Form) form;
		Sc10 sc10 = new Sc10();
		ClassHelper.copyProperties(sc10Form, sc10);
		if("".equals(sc10Form.getBsc027())||sc10Form.getBsc027()==null)
		{
			sc10.setFileKey("sys07_000");
		}
		else
		{
			sc10.setFileKey("sys07_007");
		}
		//sc10.setFileKey("sys07_000");
		String hql = queryEnterprise(sc10);
		af = super.init(request, forward, hql);
		return af;
	}
	/**
	 * 获取操作日志信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward FindSc11List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/log/Sc11List.jsp";
		ActionForward af = new ActionForward(forward);
		Sc11Form sc11Form = (Sc11Form) form;
		Sc11 sc11 = new Sc11();
		ClassHelper.copyProperties(sc11Form, sc11);
		if("".equals(sc11Form.getBsc027())||sc11Form.getBsc027()==null)
		{
			   sc11.setFileKey("sys07_001");
		}
		else
		{
			   sc11.setFileKey("sys07_004");
		}
		String hql = queryEnterprise(sc11);
		af = super.init(request, forward, hql);
		return af;
	}
	/**
	 * 获取错误日志信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward FindSc12List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/log/Sc12List.jsp";
		ActionForward af = new ActionForward(forward);
		Sc12Form sc12Form = (Sc12Form) form;
		Sc12 sc12 = new Sc12();
		ClassHelper.copyProperties(sc12Form, sc12);
		//sc12.setFileKey("sys07_002");
		if("".equals(sc12Form.getBsc027())||sc12Form.getBsc027()==null)
		{
			sc12.setFileKey("sys07_002");
		}
		else
		{
			sc12.setFileKey("sys07_005");
		}
		String hql = queryEnterprise(sc12);
		af = super.init(request, forward, hql);
		return af;
	}
	/**
	 * 获取变更日志信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward FindSc13List(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/log/Sc13List.jsp";
		ActionForward af = new ActionForward(forward);
		Sc13Form sc13Form = (Sc13Form) form;
		Sc13 sc13 = new Sc13();
		ClassHelper.copyProperties(sc13Form, sc13);
		if("".equals(sc13Form.getBsc045())||sc13Form.getBsc045()==null)
		{
			sc13.setFileKey("sys07_003");
		}
		else
		{
			sc13.setFileKey("sys07_006");
		}
		//sc13.setFileKey("sys07_003");
		String hql = queryEnterprise(sc13);
		af = super.init(request, forward, hql);
		return af;
	}
	/**
	 * 数据迁移
	 */
	public ActionForward trans(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Connection con = null;
		PreparedStatement pstm = null;
		PreparedStatement pstm2 = null;
		String sql="";
		String sql2 = "";
		String flag = request.getParameter("flag");
		try{
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if(flag.equals("oper")){
				sql = "insert into sc11@mylinke select * from sc11 where bsc027<trunc(sysdate- interval '1' year,'yyyy')";
			}else{
				sql = "insert into sc10@mylinke select * from sc10 where bsc027<trunc(sysdate- interval '1' year,'yyyy')";
			}
			pstm = con.prepareStatement(sql);
			pstm.execute();
			
			if(flag.equals("oper")){
				sql2 = "delete from sc11 where bsc027<trunc(sysdate- interval '1' year,'yyyy')";
			}else{
				sql2 = "delete from sc10 where bsc027<trunc(sysdate- interval '1' year,'yyyy')";
			}
			pstm2 = con.prepareStatement(sql2);
			pstm2.execute();
			
			DBUtil.commit(con);
		} catch (Exception e) {
			super.saveSuccessfulMsg(request, "数据迁移失败！");
			return mapping.findForward("backspace");
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
			super.saveSuccessfulMsg(request, "数据迁移成功！");
			return mapping.findForward("backspace");
		}
	/**
	 * 获取历史操作日志信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward FindSc11ListHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/log/Sc11ListHis.jsp";
		ActionForward af = new ActionForward(forward);
		Sc11Form sc11Form = (Sc11Form) form;
		Sc11 sc11 = new Sc11();
		ClassHelper.copyProperties(sc11Form, sc11);
		if("".equals(sc11Form.getBsc027())||sc11Form.getBsc027()==null)
		{
			   sc11.setFileKey("sys07_001_his");
		}
		else
		{
			   sc11.setFileKey("sys07_004_his");
		}
		String hql = queryEnterprise(sc11);
		af = super.init(request, forward, hql);
		return af;
	}
	/**
	 * 获取历史登陆日志信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward FindSc10ListHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = "/sysmanager/log/Sc10ListHis.jsp";
		ActionForward af = new ActionForward(forward);
		Sc10Form sc10Form = (Sc10Form) form;
		Sc10 sc10 = new Sc10();
		ClassHelper.copyProperties(sc10Form, sc10);
		if("".equals(sc10Form.getBsc027())||sc10Form.getBsc027()==null)
		{
			sc10.setFileKey("sys07_000_his");
		}
		else
		{
			sc10.setFileKey("sys07_007_his");
		}
		//sc10.setFileKey("sys07_000");
		String hql = queryEnterprise(sc10);
		af = super.init(request, forward, hql);
		return af;
	}
}
