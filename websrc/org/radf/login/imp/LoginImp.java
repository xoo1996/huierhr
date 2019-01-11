package org.radf.login.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.apps.commons.entity.ProClass;
import org.radf.apps.commons.entity.Product;
import org.radf.login.dto.LoginDTO;
import org.radf.login.facade.LoginFacade;
import org.radf.manage.entity.Sc01;
import org.radf.manage.entity.Sc03;
import org.radf.manage.entity.Sc04;
import org.radf.manage.entity.Sc07;
import org.radf.manage.entity.Sc08;
import org.radf.manage.entity.Sc10;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.HMAC_MD5;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalNames;

public class LoginImp extends org.radf.plat.util.imp.IMPSupport implements
		LoginFacade {

	private String className = this.getClass().getName();

	/**
	 * 功能：用户登陆的用户密码验证，
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop userLogin(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		HashMap map = (HashMap) request.getBody();
		LoginDTO dto = (LoginDTO) map.get("dto");
		String bsc011 = dto.getBsc011();
		String aae101 = dto.getAae101();
		Connection con = null;
		ResultSet result = null;
		boolean flag;
		try {
			con = DBUtil.getConnection();
			dto.setFileKey("login_select_sc05");
			// 获取人员信息
			ArrayList list = (ArrayList) find(con, dto, null, 0);
			if (list != null && list.size() > 0) {
				ClassHelper.copyProperties(list.get(0), dto);
				if (dto.getAae100() == null || dto.getAae100().equals("")
						|| dto.getAae100().equals("0"))
					throw new AppException("|登陆失败，该用户已经注销");
			} else {
				throw new AppException("|登陆失败，无此用户或用户密码错误");
			}
			
			/*String sql = "select * from sc05 where bsc011='"+bsc011+"' and aae101='1'";
			PreparedStatement pst = con.prepareStatement(sql);
			result = pst.executeQuery();
			if(result.next()==false){
				//kong
				flag = false;
			}else{
				flag = true;
			}
			
			if (flag){
				// 验证ukey序列号有效性
				dto.setFileKey("ukey_findsn");
				ArrayList listKey = (ArrayList) find(con, dto, null, 0);
				if (listKey != null && listKey.size() > 0) {
				} else {
					throw new AppException("|登陆失败，请插入正确的ukey");
				}
				// 验证两个结果是否相等
				String key = bsc011;
				String Randata = dto.getRand();
				String ClientDigest = dto.getClientResult();
				HMAC_MD5 hm = new HMAC_MD5(key.getBytes());// 放入的是KEY！！！
				hm.addData(Randata.getBytes());// 放入随机数!!!

				byte digest[];
				digest = hm.sign();
				String ss = hm.toString();

				if (ClientDigest.equals(ss)) {
					System.out.println("ok,equal!");
				} else {
					throw new AppException("|登陆失败，Ukey验证失败，请插入正确的ukey");
				}
			}*/
			//得到即将到期合同数量
			String sqlNotice = "select count(*) from (select c.useremployid, useremployeestatus, username, conid,condatestart,c.contype,condatesign,c.condateend,userdepartmentid,u.userbirthday,u.usergender,row_number()over(partition by c.useremployid order by c.condatesign DESC NULLS LAST) as temp1 from tblcontract c left outer join tbluser u on c.useremployid = u.useremployid where substr(useridno,7,4)>1900 and substr(useridno,7,4)<2900 and substr(useridno,11,2)>0 and substr(useridno,11,2)<13 and substr(useridno,13,2)>0 and substr(useridno,13,2)<32 ) a left outer join sc04 s on s.bsc008  = a.userdepartmentid where USEREMPLOYEESTATUS <>'0' and temp1  =1 and (ceil(condateend - SYSDATE) <= 30 or ((sysdate-USERBIRTHDAY) >= 20055 and usergender = '1' and contype<>'rehiring') or ((sysdate-USERBIRTHDAY) >= 18229 and usergender = '0' and contype<>'rehiring')) ";
			List rs = (Vector) DBUtil.querySQL(con,sqlNotice);
			String noticeNumber = (String) ((HashMap) rs.get(0)).get("1").toString();
			dto.setNotinumber(noticeNumber);
			//得到待审批流程数量
			String id = bsc011;
			String entityid = null;
			String pronum,roleid = null;
			int condition=0;//
			//根据当前账号获取角色信息
			List result11 = (Vector) DBUtil.querySQL(con,
							"select a.userid,c.bsc014 from tbl_acc_user a left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010 where a.account='"
									+ id + "'");
			if (result11.size() > 0) {
				entityid = (String) ((HashMap) result11.get(0)).get("1");
				dto.setBsc014((String) ((HashMap) result11.get(0)).get("2"));
			}
			for(int i=0;i<result11.size();i++){
				roleid = (String) ((HashMap) result11.get(i)).get("2");
				if (roleid != null&& ( roleid.equals("921")|| roleid.equals("925"))){
					condition=1;break;
				}else if(roleid.equals("920")){
					break;
				}else if(roleid.equals("930")){
					condition=2;break;
				}
			}
			//List result2 = (Vector) DBUtil.querySQL(con,
			//		"select a.bsc011 from sc05 a left join sc07 b on b.bsc010=a.bsc010 where b.bsc014='923' and a.bsc008='1149'");
			//if(id.equals((String) ((HashMap) result2.get(0)).get("1"))){
			if("920".equals(roleid)){
				dto.setBsc014("920");
				pronum = "select count(*) from (select nemid from tblnem where 1=1 and (nemstate='spz' or nemstate='dsp') and nemsupid in ('930','920','"+ entityid + "') and hqz like '_0___' )";
			}else if(condition==1){
				pronum = "select count(*) from (select nemid from tblnem where 1=1 and (nemstate='spz' or nemstate='dsp') and nemsupid in ('"+ roleid + "','"+ entityid + "'))";
			}else if (condition==2) {
				List result2 = (Vector) DBUtil.querySQL(con,
						"select a.bsc008 from sc05 a where a.bsc011='"+id+"'");
				String bsc008=(String) ((HashMap) result2.get(0)).get("1");
				if("582".equals(bsc008)){
					pronum = "select count(*) from (select nemid from tblnem where 1=1 and (nemstate='spz' or nemstate='dsp') and nemsupid in ('930','"+ entityid + "') and hqz like '0____' )";
				}else if("1161".equals(bsc008)){
					pronum = "select count(*) from (select nemid from tblnem where 1=1 and (nemstate='spz' or nemstate='dsp') and nemsupid in ('930','"+ entityid + "') and hqz like '__0__' )";
				}else if("1151".equals(bsc008)){
					pronum = "select count(*) from (select nemid from tblnem where 1=1 and (nemstate='spz' or nemstate='dsp') and nemsupid in ('930','"+ entityid + "') and hqz like '___0_' )";
				}else if("620".equals(bsc008)){
					pronum = "select count(*) from (select nemid from tblnem where 1=1 and (nemstate='spz' or nemstate='dsp') and nemsupid in ('930','"+ entityid + "') and hqz like '____0' )";
				} else
					pronum = "select count(*) from (select nemid from tblnem where 1=1 and (nemstate='spz' or nemstate='dsp') and nemsupid in ('930','920','"+ entityid + "') and hqz like '_0___' )";
			}else{
				if("900".equals(roleid)){entityid=id;}
				pronum = "select count(*) from (select nemid from tblnem where 1=1 and (nemstate='spz' or nemstate='dsp') and nemsupid='"+ entityid + "')";
			}
			List rs2 = (Vector) DBUtil.querySQL(con,pronum);
			String proNumber = (String) ((HashMap) rs2.get(0)).get("1").toString();
			dto.setPronumber(proNumber);
			// 获取人员机构信息
			Sc01 sc01 = new Sc01();
			sc01.setBsc001(dto.getBsc001());
			sc01.setFileKey("login_select_sc06");
			ArrayList list1 = (ArrayList) find(con, sc01, null, 0);
			if (list1 != null && list1.size() > 0) {
				ClassHelper.copyProperties(list1.get(0), dto);
			}
			// 获取科室信息
			Sc04 sc04 = new Sc04();
			sc04.setBsc008(dto.getBsc008());
			sc04.setFileKey("SC04_select");
			ArrayList list2 = (ArrayList) find(con, sc04, null, 0);
			if (list2 != null && list2.size() > 0) {
				ClassHelper.copyProperties(list2.get(0), dto);
			}
			ArrayList list5 = new ArrayList();
			// 如果是超级用户获取所有菜单
			if (GlobalNames.SUPER_USER.equals(dto.getBsc011())) {
				Sc08 sc08 = new Sc08();
				sc08.setFileKey("login_select_Sc0801");
				ArrayList list4 = (ArrayList) find(con, sc08, null, 0);
				if (list4 != null && list4.size() > 0) {
					for (int j = 0; j < list4.size(); j++) {
						Sc08 sc081 = new Sc08();
						ClassHelper.copyProperties(list4.get(j), sc081);
						list5.add(sc081);
					}

				}
			} else {
				// 获取角色列表
				Sc07 sc07 = new Sc07();
				sc07.setBsc010(dto.getBsc010());
				sc07.setFileKey("login_select_sc06andsc07");
				ArrayList list3 = (ArrayList) find(con, sc07, null, 0);
				if (list3 != null && list3.size() > 0) {

					StringBuffer stringbuffer = new StringBuffer(
							" select distinct sc08.bsc016, sc08.* from sc08,sc09 where sc08.bsc016=sc09.bsc016 and sc09.bsc014 in  (");

					for (int i = 0; i < list3.size(); i++) {
						ClassHelper.copyProperties(list3.get(i), sc07);
						stringbuffer.append("'" + sc07.getBsc014() + "'");
						if (list3.size() == (i + 1)) {
							stringbuffer
									.append(") order by  sc08.bsc020,sc08.bsc016");
						} else {
							stringbuffer.append(",");
						}

					}

					// ArrayList list4 = (ArrayList) DBUtil.querySQL(con, hql,
					// "2");
					ArrayList list4 = (ArrayList) DBUtil.querySQL(con,
							stringbuffer.toString(), "2");
					if (list4 != null && list4.size() > 0) {
						for (int j = 0; j < list4.size(); j++) {
							Sc08 sc08 = new Sc08();
							ClassHelper.copyProperties(list4.get(j), sc08);
							list5.add(sc08);
						}

					}
				}

			}
			Sc03 sc03 = new Sc03();
			Vector vecsc03 = new Vector();
			sc03.setFileKey("sys01_016");
			sc03.setAae017(dto.getBsc001());
			ArrayList listsc03 = (ArrayList) find(con, sc03, null, 0);
			if (listsc03 != null && listsc03.size() > 0) {
				for (int j = 0; j < listsc03.size(); j++) {
					sc03 = new Sc03();
					ClassHelper.copyProperties(listsc03.get(j), sc03);
					vecsc03.add(sc03);
				}

			}
			dto.setFileKey("pdt02_000");// 所有商品代码
			Object products = find(con, dto, null, 0);

			// Product nomPdt=new Product();
			// nomPdt.setFileKey("pdt02_002");
			// Object nomProducts = find(con,nomPdt, null, 0);
			//
			// Product cusPdt=new Product();
			// nomPdt.setFileKey("pdt02_003");
			// Object cusProducts = find(con,cusPdt, null, 0);

			// 商品类别
			// ProClass pcl=new ProClass();
			// pcl.setFileKey("pdt06_000");
			// Object classes = find(con, pcl, null, 0);

			// 返回结果
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			Object grCli = null;

			if (dto.getBsc001().equals("1501000000")) {

				dto.setFileKey("clt04_001");// 所有连锁点代码
				Object shops = find(con, dto, null, 0);
				
				dto.setFileKey("clt04_004");
				Object zongbu = find(con, dto, null, 0);

				dto.setFileKey("clt04_002");
				grCli = find(con, dto, null, 0);

				dto.setFileKey("clt04_000");// 所有操作员代码
				Object users = find(con, dto, null, 0);

				dto.setFileKey("rep04_001");// 维修措施一
				Object action1 = find(con, dto, null, 0);
				dto.setFileKey("rep04_002");// 维修措施二
				Object action2 = find(con, dto, null, 0);
				dto.setFileKey("rep04_003");// 维修措施三
				Object action3 = find(con, dto, null, 0);
				dto.setFileKey("rep04_004");// 维修措施四
				Object action4 = find(con, dto, null, 0);
				dto.setFileKey("rep04_005");// 维修措施五
				Object action5 = find(con, dto, null, 0);
				dto.setFileKey("rep04_006");// 维修措施六
				Object action6 = find(con, dto, null, 0);

				dto.setFileKey("ord01_003");// 发货方式
				Object folway = find(con, dto, null, 0);

				// dto.setFileKey("tsk01_000");//面板代码|2012-4-14新增,tsk01_000在TZ_CUS_SQL.properties
				// Object panels = find(con, dto, null, 0);

				// dto.setFileKey("pdt05_002");//零件代码|2012-4-17新增,pdt05_002在TZ_PDT_SQL.properties
				// Object parts = find(con, dto, null, 0);

				// dto.setFileKey("pdt05_006");
				// Object pnlnm = find(con, dto, null, 0);//面板型号

				retmap.put("shops", shops);
				retmap.put("zongbu", zongbu);
				retmap.put("users", users);

				retmap.put("action1", action1);
				retmap.put("action2", action2);
				retmap.put("action3", action3);
				retmap.put("action4", action4);
				retmap.put("action5", action5);
				retmap.put("action6", action6);
				retmap.put("folway", folway);

				// retmap.put("panels", panels);
				// retmap.put("parts", parts);

				// retmap.put("pnlnm", pnlnm);
			} else {
				dto.setFileKey("clt04_001");// 所有连锁点代码
				Object shops = find(con, dto, null, 0);
				dto.setBsc011(bsc011);
				dto.setFileKey("clt04_003");
				grCli = find(con, dto, null, 0);
				retmap.put("shops", shops);
			}

			retmap.put("dto", dto);
			retmap.put("list", list5);
			retmap.put("vecsc03", vecsc03);
			retmap.put("grCli", grCli);
			retmap.put("products", products);
			// retmap.put("nomProducts", nomProducts);
			// retmap.put("cusProducts", cusProducts);
			// retmap.put("classes", classes);

			response.setBody(retmap);
			Sc10 sc10 = (Sc10) map.get("sc10");
			// 登陆成功写登陆日志
			sc10.setBsc025(DBUtil.getSequence(con, "SEQ_BSC025"));
			sc10.setBsc010(dto.getBsc010());
			sc10.setBsc011(dto.getBsc011());
			sc10.setBsc012(dto.getBsc012());
			sc10.setBsc027(DateUtil.getSystemCurrentTime());
			create(con, sc10, null, 0);
			DBUtil.commit(con);
			// 获取
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "userLogin",
					GlobalErrorCode.IMPEXCEPTIONCODE, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;

	}

	/**
	 * 登录注销
	 * 
	 * @param request
	 * @return
	 */
	public ResponseEnvelop logout(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop(); // 出口参数
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			LoginDTO dto = (LoginDTO) map.get("dto");
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			StringBuffer stringbuffer = new StringBuffer(
					"select * from sc10 where bsc025=(select max(bsc025) from sc10 where");
			if (dto.getBsc012() != null && !dto.getBsc012().equals("")) {
				stringbuffer.append(" bsc012='" + dto.getBsc012());
				stringbuffer.append("') and bsc012='" + dto.getBsc012() + "'");
			}
			ArrayList list = (ArrayList) DBUtil.querySQL(con,
					stringbuffer.toString(), "2");
			if (list != null && list.size() > 0) {
				Sc10 sc10 = new Sc10();
				ClassHelper.copyProperties(list.get(0), sc10);
				sc10.setBsc028(DateUtil.getSystemCurrentTime());
				modify(con, sc10, null, 0);
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "userLogin",
					GlobalErrorCode.IMPEXCEPTIONCODE, ex.getMessage(),
					request.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;

	}
}
