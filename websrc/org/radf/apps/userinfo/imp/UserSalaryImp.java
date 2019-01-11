package org.radf.apps.userinfo.imp;

import java.sql.Connection;
import java.util.HashMap;

import org.radf.apps.commons.entity.UserInfo;
import org.radf.apps.commons.entity.UserSalary;
import org.radf.apps.userinfo.facade.UserSalaryFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

public class UserSalaryImp extends IMPSupport implements UserSalaryFacade{

	private String className = this.getClass().getName();
	
	@Override
	public ResponseEnvelop addUserSalary(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			HashMap map = (HashMap) request.getBody();
			UserSalary userSalary = (UserSalary)map.get("userSalary");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			/*// 判断ID是否存在和重复
			if (null == userSalary.getUseremployid() || "".equals(userInfo.getUseremployid())) {
				throw new AppException("新加入的配置项ID号不能为空!");
			}*/
			
			
			userSalary.setFileKey("userSalary_insert");
			//插入新配置项记录
			store(con, userSalary, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "addSalary",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}


}
