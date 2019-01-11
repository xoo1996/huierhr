package org.radf.apps.client.single.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Date;

import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
import org.radf.apps.client.single.facade.SingleClientFacade;
import org.radf.apps.client.single.facade.ReservationFacade;
import org.radf.apps.commons.entity.Audiogram;
import org.radf.apps.commons.entity.Diagnose;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.ReDiagnose;
import org.radf.apps.commons.entity.Reservation;
import org.radf.apps.commons.entity.SingleClient;
import org.radf.apps.commons.entity.Task;
import org.radf.apps.commons.entity.Tblitsmci;

public class ReservationImp extends IMPSupport implements ReservationFacade {
	private String className = this.getClass().getName();
	
	//客户预约
		public ResponseEnvelop yuyueSave(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				HashMap map = (HashMap) request.getBody();
				Reservation dto = (Reservation) map.get("beo");
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				Date today=DateUtil.getDate();
				// 获取最新任预约号
				List result = (Vector) DBUtil.querySQL(con,
						"select SEQ_RSVID.NEXTVAL from dual");
				BigDecimal rsvid = (BigDecimal)((HashMap)result.get(0)).get("1");
				String id = rsvid.toString();
				dto.setRsvdjdate(today);
				dto.setRsvid(id);//从数据库中取得最大的任务单号
				dto.setRsvsta("reservating");//将面板状态设置为“配件未确认”
				
				dto.setFileKey("reservate_insert");
				// 插入新任务单
				store(con, dto, null, 0);
				
				/*String sql =  "select cfgacyid from tblpnlcfg where cfgpnlnm='" + dto.getTskpnlnm() + "'";
				List result1 = (Vector)DBUtil.querySQL(con,sql);
				if(null != result1 && result1.size() >0){
					for(int i = 0; i < result1.size(); i ++)
					{
						dto.setTcfid(dto.getTskid());
						dto.setTcfacy(((HashMap)result1.get(i)).get("1").toString());
						dto.setFileKey("tsk01_004");
						store(con,dto,null,0);
					}
				}
				
				/*dto.setFileKey("pnl01_002");
				store(con, dto, null, 0);*/
				
				DBUtil.commit(con);
				HashMap<String, Object> retmap = new HashMap<String, Object>();
				retmap.put("rsvid", dto.getRsvid());
				retmap.put("beo", dto);
				response.setBody(retmap);
			} catch (Exception ex) {
				response.setHead(ExceptionSupport(className, "yuyueSave",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			} finally {
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		public ResponseEnvelop yuyueqiandao(RequestEnvelop request) {
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try {
				HashMap map = (HashMap)request.getBody();
				Reservation dto = (Reservation)map.get("beo");
				String grCli = (String) map.get("grCli");
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				Date today=DateUtil.getDate();
				dto.setRsvqddate(today);
				dto.setFileKey("reservate_update0");
				modify(con, dto, null, 0);
				String sta="reservated";
				List result = (Vector) DBUtil.querySQL(con,
						"select rsvid from tblreservation where rsvsta='" 
							+ sta
							+"' and rsvqddate=to_date('"
							+ today
							+"','yyyy-MM-dd') and rsvgcltid='"
							+ grCli
							+ "' ");
				Integer num=result.size()+1;
				if(num<10)
				{
					dto.setRsvtodayid("000"+num);
				}
				else if(num>=10&&num<100)
				{
					dto.setRsvtodayid("00"+num);
				}
				else if(num>=100&&num<1000)
				{
					dto.setRsvtodayid("0"+num);
				}
				else
				{
					dto.setRsvtodayid(""+num);
				}
				dto.setRsvsta("reservated");
				
				dto.setFileKey("reservate_update");
				modify(con, dto, null, 0);
				DBUtil.commit(con);
				HashMap<String, Object> retmap = new HashMap<String, Object>();
				retmap.put("rsvtodayid", dto.getRsvtodayid());
				retmap.put("beo", dto);
				response.setBody(retmap);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "yuyueqiandao",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
		public ResponseEnvelop delete(RequestEnvelop request){
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try{
				HashMap map = (HashMap)request.getBody();
				Reservation dto = (Reservation)map.get("beo");
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);

				dto.setFileKey("reservate_delete");
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
		
		public ResponseEnvelop menzhen(RequestEnvelop request){
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			try{
				HashMap map = (HashMap)request.getBody();
				Reservation dto = (Reservation)map.get("beo");
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				dto.setRsvsta("menzhened");
				
				dto.setFileKey("reservate_update");
				modify(con, dto, null, 0);
				DBUtil.commit(con);
				
			}catch(AppException ae){
				response.setHead(ExceptionSupport(className, ae,request.getHead()));
			} catch(Exception ex){
				response.setHead(ExceptionSupport(className, "menzhen",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			}finally{
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		public ResponseEnvelop menzhen1(RequestEnvelop request){
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			try{
				HashMap map = (HashMap)request.getBody();
				Reservation dto = (Reservation)map.get("beo");
				//SingleClient sc=new SingleClient();
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				dto.setRsvsta("menzhened");
				
				dto.setFileKey("reservate_update");
				modify(con, dto, null, 0);
				
				PreparedStatement pstm = null;
		        ResultSet res = null;
		        String sql0="select r.rsvnm,r.rsvphone,r.rsvgcltid from tblreservation r where r.rsvid='"+dto.getRsvid()+"'";
        		
		        pstm = con.prepareStatement(sql0);
        		res = pstm.executeQuery();
        		while(res.next())
		        {
        			dto.setIctnm(res.getString("rsvnm"));
        			dto.setIcttel(res.getString("rsvphone"));
        			dto.setIctgctid(res.getString("rsvgcltid"));

	            }
				//sc.setRsvid(dto.getRsvid());
//				dto.setFileKey("yuyue02_000");
//				Object ci = find(con, dto, null, 0);
				
				DBUtil.commit(con);
				retmap.put("beo1", dto);
        		response.setBody(retmap);
			}catch(AppException ae){
				response.setHead(ExceptionSupport(className, ae,request.getHead()));
			} catch(Exception ex){
				response.setHead(ExceptionSupport(className, "menzhen1",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			}finally{
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}
		
		
		public ResponseEnvelop chuanzhi(RequestEnvelop request){
			ResponseEnvelop response = new ResponseEnvelop();
			Connection con = null;
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			try {
				HashMap map = (HashMap) request.getBody();
				//String grCli=(String)map.get("grCli");
				Reservation  reservation=(Reservation)map.get("beo") ;
				SingleClient sc=new SingleClient();
				con = DBUtil.getConnection();
				DBUtil.beginTrans(con);
				PreparedStatement pstm = null;
		        ResultSet res = null;
		        String sql0="select r.rsvnm,r.rsvphone,r.rsvgcltid from tblreservation r where r.rsvid='"+reservation.getRsvid()+"'";
        		
		        pstm = con.prepareStatement(sql0);
        		res = pstm.executeQuery();
        		while(res.next())
		        {
        			sc.setIctnm(res.getString("rsvnm"));
        			sc.setIcttel(res.getString("rsvphone"));
        			sc.setIctgctid(res.getString("rsvgcltid"));

	            }
        		
        		DBUtil.commit(con);
        		retmap.put("beo1", sc);
        		response.setBody(retmap);
			
			}catch(Exception ex){
				response.setHead(ExceptionSupport(className, "chuanzhi",
						ManageErrorCode.SQLERROR, ex.getMessage(), request
								.getHead()));
			}finally{
				DBUtil.rollback(con);
				DBUtil.closeConnection(con);
			}
			return response;
		}

		
}