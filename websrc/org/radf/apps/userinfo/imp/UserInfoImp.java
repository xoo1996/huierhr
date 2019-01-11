package org.radf.apps.userinfo.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.apps.commons.entity.AccUser;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.commons.entity.Task;
import org.radf.apps.commons.entity.Tblitsmci;
import org.radf.apps.commons.entity.UserEducate;
import org.radf.apps.commons.entity.UserFamily;
import org.radf.apps.commons.entity.UserInfo;
import org.radf.apps.commons.entity.UserSup;
import org.radf.apps.commons.entity.UserTrain;
import org.radf.apps.commons.entity.UserWork;
import org.radf.apps.userinfo.facade.UserInfoFacade;
import org.radf.apps.userinfo.form.AccUserForm;
import org.radf.apps.userinfo.form.UserEducateForm;
import org.radf.apps.userinfo.form.UserFamilyForm;
import org.radf.apps.userinfo.form.UserSupForm;
import org.radf.apps.userinfo.form.UserTrainForm;
import org.radf.apps.userinfo.form.UserWorkForm;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.imp.IMPSupport;

public class UserInfoImp extends IMPSupport implements UserInfoFacade {

	private String className = this.getClass().getName();
	
	@Override
	public ResponseEnvelop addUser(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			HashMap map = (HashMap) request.getBody();
			UserInfo userInfo = (UserInfo)map.get("userInfo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// �ж�ID�Ƿ���ں��ظ�
			if (null == userInfo.getUseremployid() || "".equals(userInfo.getUseremployid())) {
				throw new AppException("�¼����������ID�Ų���Ϊ��!");
			}
			//�ж�������ID���Ƿ��ظ�
			userInfo.setFileKey("userinfo_select");
			if (getCount(con, userInfo, 0) > 0) {
				throw new AppException("ID=" + userInfo.getUseremployid() + "��Ա����Ϣ�Ѵ���!");
			}
			userInfo.setFileKey("userinfo_insert");
			//�������������¼
			store(con, userInfo, null, 0);
			//�����������Ų���Ƿ����ܲ�����Ӧ�˺�
			List result3 = (Vector) DBUtil.querySQL(con,
							"select a.bsc001,a.bsc011 from sc05 a where a.bsc008='"
									+ userInfo.getUserdepartmentid() + "'");
			String part = (String) ((HashMap) result3.get(0)).get("1");
			userInfo.setAccount((String) ((HashMap) result3.get(0)).get("2"));
			if (!part.equals("1501000000")){
				userInfo.setFileKey("accuser_insert");
				store(con, userInfo, null, 0);
			}
			List result4 = (Vector) DBUtil.querySQL(con,
					"select superiorid from tbl_acc_sup where account='"+ userInfo.getAccount() + "'");
			if(result4.size()>0){
				userInfo.setUsersuperiorid((String) ((HashMap) result4.get(0)).get("1"));
				userInfo.setFileKey("supjun_insert");
				store(con, userInfo, null, 0);
			}
			List result8 = (Vector) DBUtil.querySQL(con,
					"select max(conid) as conid from tblcontract");
			HashMap<String, String> map1 = (HashMap<String, String>) result8.get(0);
			String conid = map1.get("1");
			String a = conid.split("HT")[1];
			int b = Integer.parseInt(a) + 1;
			String c = String.valueOf(b);
			int length = c.length();
			for(int i = 0; i < 5-length; i ++){
				c = "0" + c;
			}
			userInfo.setConid("HT" + c);
			userInfo.setCondatestart(userInfo.getUserjoindate());
			userInfo.setFileKey("con_000002");
			store(con, userInfo, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("employId", userInfo.getUseremployid());
			retmap.put("workString", "������������Ϣ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savePerson",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop showDetail(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			UserInfo dto = (UserInfo) map.get("beo");
			String tp = (String) map.get("tp");
			dto.setFileKey("userinfo_select");// ���˿ͻ���ϸ��Ϣ
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "showDetail",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop saveUserFamily(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			List<UserFamilyForm> famList = (List<UserFamilyForm>)map.get("famList");
			String employid =(String)map.get("useremployid");
			for(UserFamilyForm fam:famList){
				UserFamily userFam = new UserFamily();
				ClassHelper.copyProperties(fam, userFam);
				if(userFam.getUseremployid() == null || userFam.getUseremployid().equals(""))
					userFam.setUseremployid(employid);

				userFam.setFileKey("family_insert");
				store(con, userFam, null, 0);
				DBUtil.commit(con);
			}
			
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("employId", employid);
			retmap.put("workString", "������������Ϣ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveFam",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;

	}

	@Override
	public ResponseEnvelop addUserEducate(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			List<UserEducateForm> eduList = (List<UserEducateForm>)map.get("eduList");
			String employid =(String)map.get("useremployid");
			for(UserEducateForm edu:eduList){
				UserEducate userEdu = new UserEducate();
				ClassHelper.copyProperties(edu, userEdu);
				if(userEdu.getUseremployid() == null || userEdu.getUseremployid().equals(""))
					userEdu.setUseremployid(employid);
				
				userEdu.setFileKey("useredu_insert");
				store(con, userEdu, null, 0);
				DBUtil.commit(con);
			}
			
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("employId", employid);
			retmap.put("workString", "������������Ϣ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveEdu",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop addUserWork(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			HashMap map = (HashMap) request.getBody();
			List<UserWorkForm> workList = (List<UserWorkForm>)map.get("workList");
			String employid =(String)map.get("useremployid");
			for(UserWorkForm work:workList){
				UserWork userWork = new UserWork();
				ClassHelper.copyProperties(work,userWork);
				if(work.getEmployid() == null || work.getEmployid().equals(""))
					userWork.setEmployid(employid);
				
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);

				userWork.setFileKey("userwork_insert");
				store(con, userWork, null, 0);
				DBUtil.commit(con);
			}
			
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("employId", employid);
			retmap.put("workString", "������������Ϣ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveWork",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop modifyUserInfo(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			HashMap map = (HashMap) request.getBody();
			UserInfo userInfo = (UserInfo)map.get("userInfo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// �ж�ID�Ƿ���ں��ظ�
			if (null == userInfo.getUseremployid() || "".equals(userInfo.getUseremployid())) {
				throw new AppException("�¼����������ID�Ų���Ϊ��!");
			}
			List result1 = (Vector) DBUtil.querySQL(con,
					"select userdepartmentid from tbluser where useremployid='"
							+ userInfo.getUseremployid() + "'");
			String depart = (String) ((HashMap) result1.get(0)).get("1");
			userInfo.setFileKey("userinfo_update");
			//�������������¼
			modify(con, userInfo, null, 0);
			//�����б�
			if(!(depart.equals(userInfo.getUserdepartmentid()))){
			//���ݻ����²��Ŵ������Ƿ����ŵ꼰��Ӧ�˺�
			//�����ܲ������ܲ����ȸ����˺���Ϣ���ٸ���Ա����Ϣ
			List result3 = (Vector) DBUtil.querySQL(con,
							"select a.bsc001,a.bsc011 from sc05 a where a.bsc008='"
									+ userInfo.getUserdepartmentid() + "'");
			if(result3.size()>0){
				String part = (String) ((HashMap) result3.get(0)).get("1");
				//������ŵ��˺�����и����˺Ź��ű��ܲ�һ�����Ŷ�Ӧ����˺ţ��ŵ껻���ܲ�����Ҫ���˺ţ��޸��˺�-��������;�ܲ�-�ܲ�����Ҫ�޸�sc05��,�˺�-���Ų���
				if (!part.equals("1501000000")){
					userInfo.setAccount((String) ((HashMap) result3.get(0)).get("2"));
					userInfo.setFileKey("useracc_update");
					modify(con, userInfo, null, 0);
				}
			}else{
				throw new AppException("���벿�Ŵ��������ò���û���˺Ŷ�Ӧ!");
			}
			//ֱ�Ӳ����˺�-�ϼ����ű������Ӧ�˺ŵ��ϼ����ţ��ŵ��Զ����룬�ܲ��ֶ�����
			List result4 = (Vector) DBUtil.querySQL(con,
					"select superiorid from tbl_acc_sup where account='"+ userInfo.getAccount() + "'");
			userInfo.setFileKey("tblsj_delete");
			//�ܲ���Ա������Ϣ��Ҫ�ֶ��������¼�������
			remove(con, userInfo, null, 0);
			String part = (String) ((HashMap) result3.get(0)).get("1");
			if (!part.equals("1501000000")){
				if(result4.size()>0){
					userInfo.setUsersuperiorid((String) ((HashMap) result4.get(0)).get("1"));
					userInfo.setFileKey("tblsj_insert");
					store(con, userInfo, null, 0);
				}else{
					throw new AppException("�����ŵ��˺�û�������ϼ����ţ���ǰ��Ա��������Ϣ����˵������ã�");
				}
			}
			//�������Ա�����ڲ�����ְͬλ���Ѿ����ϼ��������Զ���Ӧ��ת�����ŵ꣬û���ϼ�
//			List result4 = (Vector) DBUtil.querySQL(con,
//					"select a.superiorid from tbl_sup_jun a left join tbluser b on a.juniorid=b.useremployid where b.userdepartmentid='"+ userInfo.getUserdepartmentid() + "' and positionname='"
//							+ userInfo.getPositionname() + "' and b.useremployeestatus <> '0'");
//			remove(con, userInfo, null, 0);
//			if(result4.size()>0){
//				userInfo.setUsersuperiorid((String) ((HashMap) result4.get(0)).get("1"));
//				userInfo.setFileKey("tblsj_insert");
//				modify(con, userInfo, null, 0);
//			}
			}
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("employId", userInfo.getUseremployid());
			retmap.put("workString", "������������Ϣ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyUserInfo",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop deleteUserEducate(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			UserInfo dto = (UserInfo)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			dto.setFileKey("useredu_delete");
			remove(con, dto, null, 0);
			DBUtil.commit(con);

		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop deleteUserWork(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			UserInfo dto = (UserInfo)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			dto.setFileKey("userwork_delete");
			remove(con, dto, null, 0);
			DBUtil.commit(con);

		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop deleteUserFamily(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			UserInfo dto = (UserInfo)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			dto.setFileKey("family_delete");
			remove(con, dto, null, 0);
			DBUtil.commit(con);

		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop saveUserTrain(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			HashMap map = (HashMap) request.getBody();
			List<UserTrainForm> trainList = (List<UserTrainForm>)map.get("trainList");
			String employid =(String)map.get("useremployid");
			for(UserTrainForm train:trainList){
				UserTrain userTrain = new UserTrain();
				ClassHelper.copyProperties(train,userTrain);
				if(userTrain.getUseremployid() == null || userTrain.getUseremployid().equals(""))
					userTrain.setUseremployid(employid);
				
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);

				userTrain.setFileKey("userTrain_insert");
				store(con, userTrain, null, 0);
				DBUtil.commit(con);
			}
			
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("employId", employid);
			retmap.put("workString", "������������Ϣ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "saveWork",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop deleteUserTrain(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			UserInfo dto = (UserInfo)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			dto.setFileKey("userTrain_delete");
			remove(con, dto, null, 0);
			DBUtil.commit(con);

		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop addUserSup(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			List<UserSupForm> workList = (List<UserSupForm>)map.get("workList");
			for(UserSupForm work:workList){
				UserSup userSup = new UserSup();
				ClassHelper.copyProperties(work,userSup);
				userSup.setFileKey("usersup_insert");
				store(con, userSup, null, 0);
				DBUtil.commit(con);
			}	
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("workString", "������������Ϣ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "addUserSup",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop addAccUser(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;		
		try {
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			HashMap map = (HashMap) request.getBody();
			List<AccUserForm> workList = (List<AccUserForm>)map.get("workList");
			for(AccUserForm work:workList){
				AccUser accUser = new AccUser();
				ClassHelper.copyProperties(work,accUser);
				accUser.setFileKey("useracc_insert");
				store(con, accUser, null, 0);
				DBUtil.commit(con);
			}	
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("workString", "������������Ϣ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "addAccUser",
					ManageErrorCode.SQLERROR, ex.getMessage(), request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	@Override
	public ResponseEnvelop addAccSup(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			UserInfo dto = (UserInfo)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			//��ɾ��������
			dto.setFileKey("sj_del");
			remove(con, dto, null, 0);
			dto.setFileKey("accsup_insert");
			store(con, dto, null, 0);
			dto.setFileKey("sj_insert");
			store(con, dto, null, 0);
			DBUtil.commit(con);

		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "addAccSup",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	@Override
	public ResponseEnvelop delAccSup(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			UserInfo dto = (UserInfo)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			dto.setFileKey("accsup_del");
			remove(con, dto, null, 0);
			dto.setFileKey("sj_del");
			remove(con, dto, null, 0);
			DBUtil.commit(con);

		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "addAccSup",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

}
