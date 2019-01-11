package org.radf.apps.userinfo.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;

import org.radf.apps.commons.entity.Fee;
import org.radf.apps.userinfo.facade.UserFamilyFacade;
import org.radf.apps.userinfo.form.UserFamilyForm;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.imp.IMPSupport;

public class UserFamilyImp extends IMPSupport implements UserFamilyFacade {

	private String className = this.getClass().getName();
	
	@Override
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<UserFamilyForm> famList = (List<UserFamilyForm>) map.get("famList");
			String employid =(String)map.get("useremployid");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (UserFamilyForm userF:famList) {
				String str = DBUtil.getSequence(con, "s_tblfeeamortize");
				int seq = Integer.parseInt(str)+1;
				String sql1 = "insert into tblfeeamortize (feegctid,feestart,feeend,amotype,money,note,operdate,feegctname,feeamoid,feelong,amomoney) values (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql1);
				pstmt.setString(1,userF.getFamilycall());
				pstmt.setString(2, userF.getFamilyname());
				pstmt.setString(3, userF.getFamilyphoneno());
				pstmt.setString(4, userF.getFamilyworkplace());
				pstmt.setString(5,employid);
				pstmt.execute();
			}
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
