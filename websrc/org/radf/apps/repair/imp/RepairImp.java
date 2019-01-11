package org.radf.apps.repair.imp;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;
import org.radf.apps.commons.entity.Business;
import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.QA;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.repair.facade.RepairFacade;

public class RepairImp extends IMPSupport implements RepairFacade {
	private String className = this.getClass().getName();

	/**
	 * ����ά�޻���Ϣ
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Repair rep = (Repair) map.get("beo");
			Order header = new Order();
			OrderDetail detail = new OrderDetail();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// ���ɶ�����
			List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_FOLNO.NEXTVAL from dual");
			BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			String repfno = id.toString();

			// ����������Ϣ
			header.setFolctid(rep.getRepgctid()); // ����ͻ�
			header.setFoldt(DateUtil.getDate()); // ������������
			header.setFolno(repfno); // ������
			header.setFolsta("repairing"); // ���ƻ���������ʱ״̬Ϊ"ά����"
			header.setFoltype("repair");// �������Ϊ"ά�޻�����"
			header.setFolopr(rep.getRepreger());
			// ������ϸ
			detail.setFdtfno(repfno);// ������
			detail.setFdtcltnm(rep.getRepcltnm()); // �����û�
			detail.setFdtpid(rep.getReppid()); // ��������
			detail.setFdtqnt(1);// ����Ϊ1
			detail.setFdtsqnt(1);

			rep.setRepfolid(repfno);
			rep.setFileKey("repair_insert");
			store(con, rep, null, 0);
			header.setFileKey("order_insert");
			store(con, header, null, 0);
			detail.setFileKey("orderDetail_insert");
			store(con, detail, null, 0);
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			retmap.put("beo", rep);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	public ResponseEnvelop modify(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Repair rep = (Repair) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			OrderDetail od = new OrderDetail();
			od.setFdtfno(rep.getRepfolid());
			od.setFdtprc(rep.getRepamt());
			od.setFileKey("rep03_006");
			modify(con, od, null, 0);
			rep.setFileKey("repair_update");
			modify(con, rep, null, 0);
			
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modify",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	@Override
	public ResponseEnvelop changeStatus(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Repair rep = (Repair) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if ("process".equals(rep.getRepsta())) {
				rep.setFileKey("rep02_000");
				modify(con, rep, null, 0);
			} else if ("stop".equals(rep.getRepsta())) {
				rep.setFileKey("rep03_001");
				remove(con, rep, null, 0);
				rep.setFileKey("rep03_002");
				remove(con, rep, null, 0);
				rep.setRepfdate(DateUtil.getDate());
				rep.setFileKey("rep02_001");
				modify(con, rep, null, 0);
			} else if ("out".equals(rep.getRepsta())) {
				rep.setFileKey("rep02_002");
				modify(con, rep, null, 0);
			} else if ("finish".equals(rep.getRepsta())) {
				rep.setFileKey("rep03_003");
				modify(con, rep, null, 0);
				rep.setFileKey("rep03_005");
				modify(con, rep, null, 0);
				rep.setRepfdate(DateUtil.getDate());
				rep.setFileKey("rep02_001");
				modify(con, rep, null, 0);
				if ("�ݶ�".equals(rep.getRepcpy())) {
					QA qa = new QA();
					qa.setQafno(rep.getRepfolid());
					qa.setQasid(rep.getRepid());
					qa.setQapid(rep.getReppid());
					qa.setQacltnm(rep.getRepcltnm());
					qa.setQatype("repair");
					qa.setQastatus("wait");
					qa.setFileKey("qa_insert");
					store(con, qa, null, 0);
				}
			}
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "changeStatus",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ������ʼά��
	 */
	public ResponseEnvelop batchChange(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<Repair> collection = (Collection<Repair>) map
					.get("collection");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Repair rep : collection) {
				rep.setFileKey("rep02_000");
				modify(con, rep, null, 0);
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "batchChange",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	public ResponseEnvelop batchMakeshell(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<Repair> collection = (Collection<Repair>) map
					.get("collection");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Repair rep : collection) {
				rep.setFileKey("rep02_004");
				modify(con, rep, null, 0);
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "batchMakeshell",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �޸�ά�޼�¼��ȷ��ά�����
	 */
	public ResponseEnvelop finish(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Repair rep = (Repair) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			rep.setFileKey("rep03_003");
			modify(con, rep, null, 0);
			if ("�ݶ�".equals(rep.getRepcpy())) {
				QA qa = new QA();
				qa.setQafno(rep.getRepfolid());
				qa.setQasid(rep.getRepid());
				qa.setQapid(rep.getReppid());
				qa.setQacltnm(rep.getRepcltnm());
				qa.setQatype("repair");
				qa.setQastatus("wait");
				qa.setFileKey("qa_insert");
				store(con, qa, null, 0);
				rep.setFileKey("rep03_005");
				modify(con, rep, null, 0);
			} else {
				rep.setFileKey("rep03_004");
				modify(con, rep, null, 0);
			}
			rep.setRepfdate(DateUtil.getDate());
			rep.setFileKey("repair_update");
			modify(con, rep, null, 0);
			rep.setFileKey("rep02_001");
			modify(con, rep, null, 0);
			DBUtil.commit(con);
			HashMap retmap = new HashMap();
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "changeStatus",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	//��Ʊ
	public ResponseEnvelop kaip(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<Repair> collection = (Collection<Repair>) map
					.get("collection");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Repair dto : collection) {
				dto.setFileKey("rep02_003");
				modify(con, dto, null, 0);
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "kaip",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	public ResponseEnvelop getRepair(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<Object> repList = new ArrayList<Object>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql="select AAA003 as repactionCode, AAA004 as repaction,AAA006 as repactionprc,AAA001 as repactiontype from AA01 where 1=1 and AAA001 in ('REPACTION1','REPACTION2','REPACTION3','REPACTION4','REPACTION5','REPACTION6')";
			pstm = con.prepareStatement(sql);
            res = pstm.executeQuery();
            while(res.next())
            {
            	Repair rep = new Repair();
            	rep.setRepactionCode(res.getString("repactionCode"));
            	rep.setRepaction(res.getString("repaction"));
            	rep.setRepactionPrc(res.getString("repactionprc"));
            	rep.setRepactionType(res.getString("repactiontype"));
            	repList.add(rep);
            }
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("rep", repList);
			response.setBody(retmap);
	}  catch (Exception ex) {
		response.setHead(ExceptionSupport(className, "getRepair",
				ManageErrorCode.SQLERROR, ex.getMessage(), request
						.getHead()));
	} finally {
		DBUtil.rollback(con);
		DBUtil.closeConnection(con);
	}
	return response;
	}

}
