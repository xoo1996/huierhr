package org.radf.apps.userinfo.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;

import org.radf.apps.userinfo.facade.TblLoginFacade;
import org.radf.apps.userinfo.form.UserFamilyForm;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.imp.IMPSupport;

public class TblLoginImp extends IMPSupport implements TblLoginFacade{

	private String className = this.getClass().getName();
		
	@Override
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			String entityid =(String)map.get("entityid");
			String superiorid = (String)map.get("superiorid");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			String sqlbind = "insert into tbllogin(entityid,superiorid) values(?,?)";
			PreparedStatement pstmt = con.prepareStatement(sqlbind);
			pstmt.setString(1,entityid);
			pstmt.setString(2, superiorid);
			pstmt.execute();
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			response.setBody(retmap);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "insert",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	


	@Override
	public ResponseEnvelop delete(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			String entityid =(String)map.get("entityid");
			String superiorid = (String)map.get("superiorid");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			String sqlbind = "delete from tbllogin where entityid = ? and superiorid = ?";
			PreparedStatement pstmt = con.prepareStatement(sqlbind);
			pstmt.setString(1,entityid);
			pstmt.setString(2, superiorid);
			pstmt.execute();
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			response.setBody(retmap);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "insert",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

}
