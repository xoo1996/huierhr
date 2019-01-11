package com.cm.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.radf.apps.charge.form.ChargeForm;
import org.radf.apps.commons.entity.Applyprocess;
import org.radf.apps.commons.entity.Charge;
import org.radf.apps.test.entity.TestEntity;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.util.imp.IMPSupport;

import com.google.gson.JsonArray;

public class mobileVerify extends IMPSupport {

	// static String
	// json_str="[{\"ID\":\"634\",\"Name\":\"于东\"},{\"ID\":\"822\",\"Name\":\"于祎\"},{\"ID\":\"782\",\"Name\":\"于燕\"},{\"ID\":\"636\",\"Name\":\"于玲\"},{\"ID\":\"841\",\"Name\":\"于浩\"},{\"ID\":\"383\",\"Name\":\"于娟\"}]";
	public static void main(String[] args) throws JsonGenerationException,
			JsonMappingException, IOException {
		mobileVerify mv = new mobileVerify();
	}

	/*
	 * JSONObject jsonObject=JSONObject.fromObject(json_str);
	 * System.out.println(jsonpl.getInt("ID"));
	 */
	// 手机端发送请求和登录账号参数，返回当前账号待审批流程
	public String reWaitVerify(String account, String password) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			// DBUtil.beginTrans(conn);
			String pw = DigestUtils.md5Hex(password);
			String sql0 = "select * from sc05 where bsc011='" + account
					+ "' and bsc013='" + pw + "'";
			List result0 = (Vector) DBUtil.querySQL(conn, sql0);
			if (result0.size() < 1) {
				return "error";
			}
			String entityid = null;
			String roleid = null;
			int condition = 0;//
			String sql = "select a.userid,c.bsc014 from tbl_acc_user a left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010 where a.account='"
					+ account + "'";
			List result = (Vector) DBUtil.querySQL(conn, sql);
			// if (result.size() > 0) {
			entityid = (String) ((HashMap) result.get(0)).get("1");
			// }else{
			//
			// }
			String sql2 = "select a.nemid,a.apatype,d.bsc012 as nemname,a.nemappdt,a.nemstate from tblnem a LEFT JOIN tbl_acc_user c ON c.userid =a.nemapplyid LEFT JOIN sc05 d ON d.bsc011=c.account where 1=1 and a.nemsupid='"
					+ entityid
					+ "' and a.nemstate in ('dsp', 'spz' ) order by a.nemappdt DESC";
			List<HashMap> result2 = (Vector) DBUtil.querySQL(conn, sql2);
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			// JSONArray ja = new JSONArray();
			for (HashMap acc : result2) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("nemid", acc.get("1"));
				row.put("apatype", acc.get("2"));
				row.put("nemname", acc.get("3"));
				row.put("nemappdt", acc.get("4"));
				row.put("nemstate", acc.get("5"));
				rows.add(row);
				// ja.add(row);
			}
			JSONArray arr = JSONArray.fromObject(rows);
			// System.out.println(ja.toString());
			System.out.println(arr.toString());

			return arr.toString();
		} catch (Exception e) {
			return "exception";
		}
	}

	// 手机端选择流程号发送请求 ，返回该流程的详细信息
	public String reInfo(String nemid) {
		return "";
	}

	// 手机端填入相应数据发送请求，获得数据，并处理审批
	// 请假流程返回：日期开始时间，结束时间，流程id，审批通过与否，审批意见
	public String verify(String json) {
		//System.out.print(json);
		JSONObject jsonObject = JSONObject.fromObject(json);
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Connection con = DBUtil.getConnection();
			Applyprocess ap = new Applyprocess();
			String nemid =jsonObject.getString("nemid");
			String id = jsonObject.getString("account");
			List result = (Vector) DBUtil
					.querySQL(
							con,
							"select a.userid,d.bsc015,e.superiorid,b.bsc008 from tbl_acc_user a left join sc05 b on b.bsc011=a.account left join sc07 c on c.bsc010=b.bsc010 left join sc06 d on d.bsc014=c.bsc014 left join tbl_sup_jun e on e.juniorid=a.userid where a.account='"
									+ id + "'");
			String entityid = (String) ((HashMap) result.get(0)).get("1");
			ap.setNemverifyid(entityid);
			ap.setAparole((String) ((HashMap) result.get(0)).get("2"));
			String nemsupid = (String) ((HashMap) result.get(0)).get("3");
			ap.setNemsupid(nemsupid);
			ap.setApaverifydt(DateUtil.getSystemCurrentTime());
			if (nemsupid == null) {
				return "n";
			} else {
				ap.setAparesult("通过");
				// 流程判断 流程条件
				List result2 = (Vector) DBUtil.querySQL(con,
						"select nemcondition from tblnem where nemid='" + nemid
								+ "'");
				String condition = (String) ((HashMap) result2.get(0)).get("1");
				//查找下一级审批人的账号
				List result3 = (Vector) DBUtil.querySQL(con,
								"select account from tbl_acc_user where userid='" + nemsupid + "'");
				String acc = (String) ((HashMap) result3.get(0)).get("1");
				java.util.Date dt1 = sim.parse(jsonObject.getString("restsdt"));
				java.util.Date dt2 = sim.parse(jsonObject.getString("restedt"));
				long dt = dt2.getTime() - dt1.getTime();
				long day = dt / (1000 * 3600 * 24)+1;
				if ((condition.equals("0") && (day < 4) && checkrole(con,acc,"924"))||(condition.equals("2") && (day < 4) && checkrole(con,acc,"919"))){				
					ap.setNemsupid("920");
					ap.setNemcondition("4");
					condition="4";
				}
				if (checkrole(con,id,"919") || checkrole(con,id,"924")) {
					ap.setNemsupid("920");
					ap.setNemcondition("4");
				}
				if (checkrole(con,id,"920")&&condition.equals("4")) {// rsbm
					ap.setNemstate("tg");
					ap.setNemsupid(" ");
				} else
					ap.setNemstate("spz");
				DBUtil.beginTrans(con);
				PreparedStatement ps = null;
				try {
					// 打开数据库连接并清楚原来记录集
					String sql="update tblnem set nemverifyid='"+ap.getNemverifyid()+"',nemstate='"+ap.getNemstate()+"',nemsupid='"+nemsupid+"',nemcondition='"+ap.getNemcondition()+"',nemadv='"+jsonObject.getString("nemadv")+"' where nemid='"+nemid+"'";
					ps = con.prepareStatement(sql);
					ps.execute();
					sql="insert into tblapa(apaid,aparole,apaadv,aparesult,apaverifyid,apaproid,apaverifydt)values(SEQ_APA.NEXTVAL,'"+ap.getAparole()+"','"+jsonObject.getString("nemadv")+"','"+ap.getAparesult()+"','"+ap.getNemverifyid()+"','"+nemid+"',to_date('"+ap.getApaverifydt()+"','yyyy-MM-dd HH24:mi:ss'))";
					ps = con.prepareStatement(sql);
					ps.execute();
					if (ap.getNemstate().equals("tg")) {
						sql="insert into tblnen(select * from tblnem where nemid='"+nemid+"')";
						ps = con.prepareStatement(sql);
						ps.execute();
					}
					DBUtil.commit(con);
					con.close();
				} catch (SQLException ex) {
					return "e";
				}
//			ap.setFileKey("ver_update");
//			modify(con, ap, null, 0);
//			ap.setFileKey("ver_insert");
//			store(con, ap, null, 0);
//			if (ap.getNemstate().equals("tg")) {
			}
			return "s";
			// }catch (SQLException ex) {
			// throw new SQLException(ex.getMessage());
		} catch (Exception e) {
			return "e";
		}

	}

	public boolean checkrole(Connection con,String acc,String role) throws Exception{
		String roleid = null;
		List result = (Vector) DBUtil.querySQL(con,
				"select c.bsc014 from sc05 b left join sc07 c on c.bsc010=b.bsc010 where b.bsc011='"
						+ acc + "'");
		for(int i=0;i<result.size();i++){
			roleid = (String) ((HashMap) result.get(i)).get("1");
			if (roleid != null&& roleid.equals(role)){
				return true;
			}
		}
		return false;
	}
}
