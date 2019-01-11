package org.radf.apps.customization.imp;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.Diagnose;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.QA;
import org.radf.apps.customization.facade.CustomizationFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

public class CustomizationImp extends IMPSupport implements CustomizationFacade {
	private String className = this.getClass().getName();

	/**
	 * �������ƻ���Ϣ
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		CallableStatement proc = null;
		String retTmksid = null;
		BigDecimal[] price = new BigDecimal[2];
		try {
			HashMap map = (HashMap) request.getBody();
			HashMap<String, String> retmap = new HashMap<String, String>();
			List<Customization> czList = (Vector<Customization>) map.get("beo");
			String gid = (String) map.get("gid");
			String cid = (String) map.get("cid");
			String opr = (String) map.get("opr");
			String cnm = (String) map.get("cnm");
//			String company = (String) map.get("company");
			String tye = (String)map.get("tye");
			Diagnose dg=(Diagnose)map.get("diagnose");
//			Double rdiscount=Double.parseDouble((String)map.get("rdiscount"));
			Integer num = czList.size();
			Order header = new Order();
			OrderDetail detail = new OrderDetail();
			// List<OrderDetail> detailList = new Vector<OrderDetail>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// ���ɶ�����
			List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_FOLNO.NEXTVAL from dual");
			BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			String tmkfno1 = id.toString();

			// ���ɶ��ƻ�������
			for (Customization cz : czList) {
				proc = con.prepareCall("{ call PRC_CREATE_TMKSID(?, ?, ?) }");
				if (cz.getTmkplr() == "0") {
					proc.setInt(1, 0);
				} else if (cz.getTmkplr() == "1") {
					proc.setInt(1, 1);
				}
				proc.registerOutParameter(2, Types.VARCHAR);
				proc.setString(3, tye);
				proc.execute();
				retTmksid = proc.getString(2);
				cz.setTmksid(retTmksid);
				cz.setTmkfno(tmkfno1);
			}
			for (int i = 0; i < num; i++) {
				// ¼���ۼ�
				result = (Vector) DBUtil.querySQL(con,
						"select pdtprc from tblproduct where pdtid='"
								+ czList.get(i).getTmkpid() + "'");
				price[i] = (BigDecimal) ((HashMap) result.get(0)).get("1");
			}
			header.setFolctid(gid); // ����ͻ�
			header.setFoldt(DateUtil.getDate()); // ������������
			header.setFolno(tmkfno1); // ������
			header.setFolsta("uncommited"); // ���ƻ���������ʱ״̬Ϊ"δ�ύ"
			header.setFoltype("make");// �������Ϊ"���ƻ�����"
			header.setFolopr(opr);
//			header.setFolurgent(dg);
			if (num == 1) {
				detail.setFdtfno(tmkfno1);// ������
				detail.setFdtcltid(cid);//���˱��
				detail.setFdtcltnm(cnm); // �����û�
				detail.setFdtpid(czList.get(0).getTmkpid()); // ��������
				detail.setFdtprc(price[0].doubleValue());
				detail.setFdtqnt(1);// ����Ϊ1
				detail.setFdtsqnt(1);
				detail.setFdtdprc(price[0].doubleValue());
				store(con, header, null, 0);
				store(con, detail, null, 0);
			} else if (num == 2) {
				if (czList.get(0).getTmkpid().equals(czList.get(1).getTmkpid()) && czList.get(0).getTmkplr().equals(czList.get(1).getTmkplr()) ) {
					detail.setFdtfno(tmkfno1);// ������
					detail.setFdtcltid(cid);//���˱��
					detail.setFdtcltnm(cnm); // �����û�
					detail.setFdtpid(czList.get(0).getTmkpid()); // ��������
					detail.setFdtprc(price[0].doubleValue());
					detail.setFdtqnt(2);// ����Ϊ2
					detail.setFdtsqnt(2);  //��������
					detail.setFdtdprc(price[0].doubleValue());  //?
					store(con, header, null, 0);
					store(con, detail, null, 0);
				} else {
					OrderDetail d0 = new OrderDetail();
					d0.setFdtfno(tmkfno1);// ������
					d0.setFdtcltid(cid);//���˱��
					d0.setFdtcltnm(cnm); // �����û�
					d0.setFdtpid(czList.get(0).getTmkpid()); // ���������
					d0.setFdtprc(price[0].doubleValue());
					d0.setFdtqnt(1);// ����Ϊ1
					d0.setFdtsqnt(1);
					d0.setFdtdprc(price[0].doubleValue());
					store(con, header, null, 0);
					store(con, d0, null, 0);
					OrderDetail d1 = new OrderDetail();

					// ���ɶ�����
					result = (Vector) DBUtil.querySQL(con,
							"select SEQ_FOLNO.NEXTVAL from dual");
					id = (BigDecimal) ((HashMap) result.get(0)).get("1");
					String tmkfno2 = id.toString();

					d1.setFdtfno(tmkfno2);// ������
					d0.setFdtcltid(cid);//���˱��
					d1.setFdtcltnm(cnm); // �����û�
					d1.setFdtpid(czList.get(1).getTmkpid()); // �Ҷ�������
					d1.setFdtprc(price[1].doubleValue());
					d1.setFdtqnt(1);// ����Ϊ1
					d1.setFdtsqnt(1);
					d1.setFdtdprc(price[1].doubleValue());
					header.setFolno(tmkfno2);
					czList.get(1).setTmkfno(tmkfno2);
					store(con, header, null, 0);
					store(con, d1, null, 0);
					retmap.put("tmkfno2", tmkfno2);
				}
			}
			store(con, czList, null, 0);
			DBUtil.commit(con);
			retmap.put("tmkfno1", tmkfno1);
			response.setBody(retmap);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setHead(ExceptionSupport(className, "save",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ���ƻ���Ϣ�޸�
	 */
	public ResponseEnvelop modifyCI(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Customization dto = (Customization) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getTmksid() == null || "".equals(dto.getTmksid())) {
				throw new AppException("�ö��ƻ���Ϣδ�Ǽ�");
			} else {
				//�鿴�Ƿ��Ѿ�������
				String tql = "select tmkpnl from tblmaking where tmkfno='" + dto.getTmkfno() + "' and tmkcltid='" + dto.getTmkcltid() + "' and tmkpid='" + dto.getTmkpid() + "'";
				List result3 = (Vector)DBUtil.querySQL(con, tql);
				String tmkpnl = null;
				if(null != ((HashMap)result3.get(0)).get("1")){
					tmkpnl = ((HashMap)result3.get(0)).get("1").toString();
				}
				if(null == tmkpnl || "".equals(tmkpnl)){
					tmkpnl = " ";
				}
				String pql = "select pdttype from tblproduct where pdtid ='" + dto.getTmkpid() + "'";
				List resultp = (Vector)DBUtil.querySQL(con, pql);
				String pdttype = null;
				if(null != ((HashMap)resultp.get(0)).get("1")){
					pdttype = ((HashMap)resultp.get(0)).get("1").toString();
				}
				if(null == pdttype || "".equals(pdttype)){
					pdttype = " ";
				}
				//if(("000000".equals(tmkpnl)&&null!=dto.getTmkpnl())||(" ".equals(tmkpnl))&&null!=dto.getTmkpnl()||null!=dto.getTmkpnl()&&(!(" ".equals(tmkpnl)))&&(!(tmkpnl.equals(dto.getTmkpnl()))))
				if(!"000000".equals(dto.getTmkpnl()) && !tmkpnl.equals(dto.getTmkpnl()) && !"Ψ��".equals(pdttype))
				{
					String cql = "select count(*) from tblpnlqa  where pnlqapnl='" + dto.getTmkpnl() + "'";
					List result4 = (Vector)DBUtil.querySQL(con, cql);
					if(result4.size() < 1){
						throw new AppException("��������δ�����������������������");
					}
					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
						throw new AppException("��������δ�����������������������");
					}
					
					//��ѯ������Ƿ��и����������Ӧ������ͺŴ��ڡ�
					String sql = "select k.tskpnlproid,k.tskpnlnm from tbltsk k left outer join tbltskdetail d on d.tsdtskid=k.tskid "
							+ "left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdpnlid='" +  dto.getTmkpnl() + "'";
					List result0 = (Vector)DBUtil.querySQL(con, sql);
					String pnlpdtid=null;
					String pnlpdtnm=null;
					String flag="0";
					if(result0.size() < 1){
						String sqlqaproid="select q.pnlqapdtid from tblpnlqa q where q.pnlqapnl='" + dto.getTmkpnl() + "'";
						List resultqaproid= (Vector)DBUtil.querySQL(con, sqlqaproid);
						if(resultqaproid.size()<1)
						{
							throw new AppException("������޴������Ϣ�������������");
						}
						else
						{
							String pnlqapdtid = null;
							if(null != ((HashMap)resultqaproid.get(0)).get("1")){
								pnlqapdtid = ((HashMap)resultqaproid.get(0)).get("1").toString();
							}
							if(null == pnlqapdtid || "".equals(pnlqapdtid)){
								pnlqapdtid = " ";
							}
							String sqlpdtid="select p.pdtid,p.pdtnm from tblproduct p where p.pdtid='" + pnlqapdtid + "'";
							List resultpdtid=(Vector)DBUtil.querySQL(con, sqlpdtid);
							
							if(resultpdtid.size()<1)
							{
								throw new AppException("������޴������Ϣ�������������");
							}
							pnlpdtid = (String)((HashMap)resultpdtid.get(0)).get("1") ;
							pnlpdtnm = (String)((HashMap)resultpdtid.get(0)).get("2") ;
							flag="1";
							
						}
					}
					String pnlproid = null;
					String pnlnm = null;
					if("0".equals(flag))
					{
						pnlproid = (String)((HashMap)result0.get(0)).get("1") ;
						pnlnm = (String)((HashMap)result0.get(0)).get("2") ;
					}
					if("1".equals(flag))
					{
						pnlproid=pnlpdtid;
						pnlnm=pnlpdtnm;
					}
					if(null == pnlproid || "".equals(pnlproid) ){
						throw new AppException("������޴������Ϣ�������������");
					}
					
					String sql1 ="select nvl(sum(stoamount*stoactype),0)  ���� from tblsto  where stoproid='" + pnlproid + "'";
					//String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//������������������Ӧ������������1������ʾ��治����Ϣ��
					if(result1.size() < 1){
						throw new AppException("������������Ӧ������޿�棬�����������");
					}
					if (null != ((HashMap)result1.get(0)).get("1") && Integer.parseInt(((HashMap)result1.get(0)).get("1").toString()) < 1) {// ������������������Ӧ������������1������ʾ��治����Ϣ��
						throw new AppException("������������Ӧ������޿�棬�����������");
					}
					
					String sqlpnl="select m.tmkpnl from tblmaking m where m.tmkpnl='" + dto.getTmkpnl() + "'";
					List resultpnl = (Vector)DBUtil.querySQL(con,sqlpnl) ;
					
					if (resultpnl.size() > 0) {// ������������������tblmaking���У�˵��������Ѿ����ã������ٴ����á�
						throw new AppException("������������Ӧ������ѳ��⣬��ѡ������������");
					}
					
					//��ѯ����ͺ�����Ӧ����Ʒ�����롢���ࡢ�����С��
					List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtid='"+pnlproid+"'");
					String pclid = null;
					String pdtid = null;
					if(result2.size() != 0){
						pclid=(String)(((HashMap)result2.get(0)).get("1"));
						pdtid = ((HashMap)result2.get(0)).get("5").toString();
					}
					if(null!=pclid && !"".equals(pclid))
					{
						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
						
						dto.setStoprotype(pclid);
						dto.setStocllar(pcllar);
						dto.setStoclmid(pclmid);
						dto.setStoclsam(pclsam);
						
					}
					
					/*List result2 = (Vector)DBUtil.querySQL(con,"select SEQ_STOID.NEXTVAL from dual");
					String stoid = (((HashMap)result2.get(0)).get("1")).toString();
					dto.setStoid(stoid);*/
					String tmkpnlql = "select pnlqapnl from tblpnlqa  where pnlqapnl='" + tmkpnl + "'";
					List resulttmkpnl = (Vector)DBUtil.querySQL(con, tmkpnlql);
					if(resulttmkpnl.size() < 1){
						List resultdual = (Vector)DBUtil.querySQL(con,"select SEQ_STOID.NEXTVAL from dual");
						String stoid = (((HashMap)resultdual.get(0)).get("1")).toString();
						dto.setStoid(stoid);
						dto.setStoproid(pdtid);
						dto.setStogrcliid("1501000000");
						
						dto.setStoproname(pnlnm);
						dto.setStoamount(new Integer("1"));
						dto.setStoamountun("��");
						dto.setStodate(DateUtil.getDate());
						dto.setStoactype(-1);
						dto.setStodisc("0");
						dto.setStoremark("����������");
						dto.setFileKey("store_insert");
						store(con, dto, null, 0);
					}

					
				}
//				else  20130426
//				{
//					String cql = "select count(*) from tblpnlqa  where pnlqapnl='" + dto.getTmkpnl() + "'";
//					List result4 = (Vector)DBUtil.querySQL(con, cql);
//					if(result4.size() < 1){
//						throw new AppException("��������δ�����������������������");
//					}
//					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
//						throw new AppException("��������δ�����������������������");
//					}
//					
//					//��ѯ������Ƿ��и����������Ӧ������ͺŴ��ڡ�
//					String sql = "select k.tskpnlproid,k.tskpnlnm from tbltsk k left outer join tbltskdetail d on d.tsdtskid=k.tskid "
//							+ "left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdpnlid='" +  dto.getTmkpnl() + "'";
//					List result0 = (Vector)DBUtil.querySQL(con, sql);
//					if(result0.size() < 1){
//						throw new AppException("������޴������Ϣ�������������");
//					}
//					String pnlproid = (String)((HashMap)result0.get(0)).get("1") ;
//					String pnlnm = (String)((HashMap)result0.get(0)).get("2") ;
//					if(null == pnlproid || "".equals(pnlproid) ){
//						throw new AppException("������޴������Ϣ�������������");
//					}
//					
//					String sql1 ="select nvl(sum(stoamount*stoactype),0)  ���� from tblsto  where stoproid='" + pnlproid + "'";
//					//String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
//					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
//					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//������������������Ӧ������������1������ʾ��治����Ϣ��
//					if(result1.size() < 1){
//						throw new AppException("������������Ӧ������޿�棬�����������");
//					}
//					if (null != ((HashMap)result1.get(0)).get("1") && Integer.parseInt(((HashMap)result1.get(0)).get("1").toString()) < 1) {// ������������������Ӧ������������1������ʾ��治����Ϣ��
//						throw new AppException("������������Ӧ������޿�棬�����������");
//					}
//					
//					String sqlpnl="select m.tmkpnl from tblmaking m where m.tmkpnl='" + dto.getTmkpnl() + "'";
//					List resultpnl = (Vector)DBUtil.querySQL(con,sqlpnl) ;
//					
//					if (resultpnl.size() > 0) {// ������������������tblmaking���У�˵��������Ѿ����ã������ٴ����á�
//						throw new AppException("������������Ӧ������ѳ��⣬��ѡ������������");
//					}
//				}   20130426
				
				
				
//				if(!"000000".equals(dto.getTmkpnl()) && !tmkpnl.equals(dto.getTmkpnl())){//��������벻Ϊ000000��Ҳ����ԭ����������һ�������µ�����������Ӧ��������
//					
//					//�鿴������Ƿ���ڣ��������tblpnlqa���в�ѯ
//					String cql = "select count(*) from tblpnlqa  where pnlqapnl='" + dto.getTmkpnl() + "'";
//					List result4 = (Vector)DBUtil.querySQL(con, cql);
//					if(result4.size() < 1){
//						throw new AppException("��������δ�����������������������");
//					}
//					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
//						throw new AppException("��������δ�����������������������");
//					}
//					
//					//��ѯ������Ƿ��и����������Ӧ������ͺŴ��ڡ�
//					String sql = "select k.tskpnlnm from tbltsk k left outer join tbltskdetail d on d.tsdtskid=k.tskid "
//							+ "left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdpnlid='" +  dto.getTmkpnl() + "'";
//					List result0 = (Vector)DBUtil.querySQL(con, sql);
//					if(result0.size() < 1){
//						throw new AppException("������޴������Ϣ�������������");
//					}
//					String pnlnm = (String)((HashMap)result0.get(0)).get("1") ;
//					if(null == pnlnm || "".equals(pnlnm) ){
//						throw new AppException("������޴������Ϣ�������������");
//					}
//					
//					//�鿴���������Ӧ������ͺ��Ƿ����㹻�Ŀ��
//					String sql1 ="select nvl(sum(stoamount*stoactype),0)  ���� from tblsto  where stoproname='" + pnlnm + "'";
//					//String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
//					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
//					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//������������������Ӧ������������1������ʾ��治����Ϣ��
//					if(result1.size() < 1){
//						throw new AppException("������������Ӧ������޿�棬�����������");
//					}
//					if (null != ((HashMap)result1.get(0)).get("1") && Integer.parseInt(((HashMap)result1.get(0)).get("1").toString()) < 1) {// ������������������Ӧ������������1������ʾ��治����Ϣ��
//						throw new AppException("������������Ӧ������޿�棬�����������");
//					}
//					
//					
//					
//					//��ѯ����ͺ�����Ӧ����Ʒ�����롢���ࡢ�����С��
//					List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtnm='"+pnlnm+"'");
//					String pclid = null;
//					if(result2.size() != 0){
//						pclid=(String)(((HashMap)result2.get(0)).get("1"));
//					} 
//					if(null!=pclid && !"".equals(pclid))
//					{
//						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
//						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
//						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
//						String pdtid = ((HashMap)result2.get(0)).get("5").toString();
//						dto.setStoprotype(pclid);
//						dto.setStocllar(pcllar);
//						dto.setStoclmid(pclmid);
//						dto.setStoclsam(pclsam);
//						dto.setStoproid(pdtid);
//					}
//					
//					/*List result2 = (Vector)DBUtil.querySQL(con,"select SEQ_STOID.NEXTVAL from dual");
//					String stoid = (((HashMap)result2.get(0)).get("1")).toString();
//					dto.setStoid(stoid);
//					dto.setStoproid(dto.getTmkpnl());*/
//					dto.setStogrcliid("1501000000");
//					//dto.setStoprotype("3");//3��ʾ���
//					dto.setStoproname(pnlnm);
//					dto.setStoamount(new Integer("1"));
//					dto.setStoamountun("��");
//					dto.setStodate(DateUtil.getDate());
//					dto.setStoactype(-1);
//					dto.setStoremark("����������");
//					dto.setFileKey("store_insert");
//					store(con, dto, null, 0);
//					
//
//					/*dto.setFileKey("cus01_002");
//					modify(con, dto, null, 0);*/
//					
//					
//					
//				}
				
//				dto.setFileKey("ord03_000");
//				modify(con, dto, null, 0);
				Order ord=new Order();
				ord.setFolsta(dto.getFolsta());
				ord.setFolpdt(dto.getTmkpdt());
				ord.setFolno(dto.getTmkfno());
				ord.setFileKey("ord03_000");
				modify(con, ord, null, 0);

				dto.setFileKey("customization_update");
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modifyCI",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ���ƻ���Ϣɾ��
	 */
	public ResponseEnvelop delete(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Customization dto = (Customization) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			dto.setFileKey("Customization_delete");
			remove(con, dto, null, 0);
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ���ƻ���Ϣ��ʾ
	 */
	public ResponseEnvelop printCI(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Customization dto = (Customization) map.get("beo");
			dto.setFileKey("customization_select");// ���ƻ���ϸ��Ϣ
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "printCI",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ���ϣ����¶��ƻ���Ϣ
	 */
	public ResponseEnvelop updateArrangement(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Customization dto = (Customization) map.get("beo");
			
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getTmksid() == null || dto.getTmksid().equals("")) {
				throw new AppException("�ö��ƻ���Ϣδ�Ǽ�");
			} else {
				//�鿴�Ƿ��Ѿ�������
				String tql = "select tmkpnl from tblmaking where tmkfno='" + dto.getTmkfno() + "' and tmkcltid='" + dto.getTmkcltid() + "' and tmkpid='" + dto.getTmkpid() + "'";
				List result5 = (Vector)DBUtil.querySQL(con, tql);
				String tmkpnl = null;
				if(null != ((HashMap)result5.get(0)).get("1")){
					tmkpnl = ((HashMap)result5.get(0)).get("1").toString();
				}
				if(null == tmkpnl || "".equals(tmkpnl)){
					tmkpnl = " ";
				}
				if(!"000000".equals(dto.getTmkpnl()) && !tmkpnl.equals(dto.getTmkpnl())){//��������벻Ϊ��000000����Ҳ����ԭ����������һ��������Ӧ�����������
					
					String cql = "select count(*) from tblpnlqa  where pnlqapnl='" + dto.getTmkpnl() + "'";
					List result4 = (Vector)DBUtil.querySQL(con, cql);
					if(result4.size() < 1){
						throw new AppException("��������δ�����������������������");
					}
					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
						throw new AppException("��������δ�����������������������");
					}
					
					//��ѯ������Ƿ��и����������Ӧ������ͺŴ��ڡ�
					String sql = "select k.tskpnlproid,k.tskpnlnm from tbltsk k left outer join tbltskdetail d on d.tsdtskid=k.tskid "
							+ "left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdsta='6' and d.tsdpnlid='" +  dto.getTmkpnl() + "'";
					List result0 = (Vector)DBUtil.querySQL(con, sql);
					
					String pnlpdtid=null;
					String pnlpdtnm=null;
					String flag="0";
					if(result0.size() < 1){
						String sqlqaproid="select q.pnlqapdtid from tblpnlqa q where q.pnlqapnl='" + dto.getTmkpnl() + "'";
						List resultqaproid= (Vector)DBUtil.querySQL(con, sqlqaproid);
						if(resultqaproid.size()<1)
						{
							throw new AppException("������޴������Ϣ�������������");
						}
						else
						{
							String pnlqapdtid = null;
							if(null != ((HashMap)resultqaproid.get(0)).get("1")){
								pnlqapdtid = ((HashMap)resultqaproid.get(0)).get("1").toString();
							}
							if(null == pnlqapdtid || "".equals(pnlqapdtid)){
								pnlqapdtid = " ";
							}
							String sqlpdtid="select p.pdtid,p.pdtnm from tblproduct p where p.pdtid='" + pnlqapdtid + "'";
							List resultpdtid=(Vector)DBUtil.querySQL(con, sqlpdtid);
							
							if(resultpdtid.size()<1)
							{
								throw new AppException("������޴������Ϣ�������������");
							}
							pnlpdtid = (String)((HashMap)resultpdtid.get(0)).get("1") ;
							pnlpdtnm = (String)((HashMap)resultpdtid.get(0)).get("2") ;
							flag="1";
							
						}
					}
					String pnlproid = null;
					String pnlnm = null;
					if("0".equals(flag))
					{
						pnlproid = (String)((HashMap)result0.get(0)).get("1") ;
						pnlnm = (String)((HashMap)result0.get(0)).get("2") ;
					}
					if("1".equals(flag))
					{
						pnlproid=pnlpdtid;
						pnlnm=pnlpdtnm;
					}
					if(null == pnlproid || "".equals(pnlproid) ){
						throw new AppException("������޴������Ϣ�������������");
					}
					
					String sql1 ="select nvl(sum(stoamount*stoactype),0)  ���� from tblsto  where stoproid='" + pnlproid + "'";
					//String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//������������������Ӧ������������1������ʾ��治����Ϣ��
					if(result1.size() < 1){
						throw new AppException("������������Ӧ������޿�棬�����������");
					}
					if (null != ((HashMap)result1.get(0)).get("1") && Integer.parseInt(((HashMap)result1.get(0)).get("1").toString()) < 1) {// ������������������Ӧ������������1������ʾ��治����Ϣ��
						throw new AppException("������������Ӧ������޿�棬�����������");
					}
					
					String sqlpnl="select m.tmkpnl from tblmaking m where m.tmkpnl='" + dto.getTmkpnl() + "'";
					List resultpnl = (Vector)DBUtil.querySQL(con,sqlpnl) ;
					
					if (resultpnl.size() > 0) {// ������������������tblmaking���У�˵��������Ѿ����ã������ٴ����á�
						throw new AppException("������������Ӧ������ѳ��⣬��ѡ������������");
					}
					
					//��ѯ����ͺ�����Ӧ����Ʒ�����롢���ࡢ�����С��
					List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtid='"+pnlproid+"'");
					String pclid = null;
					String pdtid = null;
					if(result2.size() != 0){
						pclid=(String)(((HashMap)result2.get(0)).get("1"));
						pdtid = ((HashMap)result2.get(0)).get("5").toString();
					}
					if(null!=pclid && !"".equals(pclid))
					{
						String pcllar = ((HashMap)result2.get(0)).get("2").toString();
						String pclmid = ((HashMap)result2.get(0)).get("3").toString();
						String pclsam = ((HashMap)result2.get(0)).get("4").toString();
						
						dto.setStoprotype(pclid);
						dto.setStocllar(pcllar);
						dto.setStoclmid(pclmid);
						dto.setStoclsam(pclsam);
						
					}
					
					/*List result2 = (Vector)DBUtil.querySQL(con,"select SEQ_STOID.NEXTVAL from dual");
					String stoid = (((HashMap)result2.get(0)).get("1")).toString();
					dto.setStoid(stoid);*/
					dto.setStoproid(pdtid);
					dto.setStogrcliid("1501000000");
					
					dto.setStoproname(pnlnm);
					dto.setStoamount(new Integer("1"));
					dto.setStoamountun("��");
					dto.setStodate(DateUtil.getDate());
					dto.setStoactype(-1);
					dto.setStodisc("0");
					dto.setStoremark("����������");
					dto.setFileKey("store_insert");
					store(con, dto, null, 0);
					
					
					
				}
				
				dto.setTmkpst("1");
				dto.setFileKey("cus01_002");
				modify(con, dto, null, 0);
//				dto.setFileKey("ord03_000");
//				modify(con, dto, null, 0);
				Order ord=new Order();
				ord.setFolsta("making");
				ord.setFolpdt(dto.getTmkpdt());
				ord.setFolno(dto.getTmkfno());
				ord.setFileKey("ord03_000");  //   ord11_027
				modify(con, ord, null, 0);
				
				List result = (Vector) DBUtil.querySQL(con,"select gctnm from tblgrpclient where gctid=(select ictgctid from tblindclient where ictid='"
										+ dto.getTmkcltid() + "')");
				String gctnm = (String) ((HashMap) result.get(0)).get("1");
				
				String delay = null;//�ӱ�����
				String limit = null;//��������
				String foldt = null;//
				List result3 = (Vector)DBUtil.querySQL(con, "select h.foldelay,p.pdtlmt,h.foldt,h.delayleft,h.delayright from tblfolio h left join tblfoldetail d on d.fdtfno=h.folno " +
						"left join tblproduct p on p.pdtid=d.fdtpid where h.folno ='" + dto.getTmkfno() + "'");	
				String delayleft;
				String delayright;
				if(null != ((HashMap)result3.get(0)).get("4")){
					delayleft = ((HashMap)result3.get(0)).get("4").toString().trim();
				}else{
					delayleft = "0";
				}
				if(null != ((HashMap)result3.get(0)).get("5")){
					delayright = ((HashMap)result3.get(0)).get("5").toString().trim();
				}else{
					delayright = "0";
				}
				if(delayleft.equals("0")){
					delay = delayright;
				}else{
					delay = delayleft;
				}
				if(null != ((HashMap)result3.get(0)).get("2")){
					limit = ((HashMap)result3.get(0)).get("2").toString().trim();
				}else{
					limit = "0";
				}
				if(null != ((HashMap)result3.get(0)).get("3")){
					foldt = ((HashMap)result3.get(0)).get("3").toString().trim();
				}else{
					foldt = "0";
				}
				Date tmkpdt = dto.getTmkpdt();
				String year = tmkpdt.toString().substring(0,4);
				String month = tmkpdt.toString().substring(5, 7);
				String day = tmkpdt.toString().substring(8);
				Integer months = Integer.parseInt(year) * 12 + Integer.parseInt(delay) +  Integer.parseInt(limit) + Integer.parseInt(month);
				Integer years;
				if(months % 12 == 0){
					years = months / 12 - 1;
					months = 12;
				}else{
					years = months / 12;
					months = months % 12; 
				}
				
				//�������ڲ��Ϸ�����
				if (months >= 1 && months <= 12 && Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31) {
					if (months == 4 || months == 6 || months == 9 || months == 11) {
						if (Integer.parseInt(day) >= 31)
							day="30";
					} else {
						if (months == 2) {
						   if (years % 100 != 0 && years % 4 == 0 || years % 400 == 0) {
						      if (Integer.parseInt(day) >= 29){
							     day="29";
						      }
					       }
						   else{
							   if (Integer.parseInt(day) >= 28){
								     day="28";
							      }
						   }
						}
					}
				}
				
				Date expire = DateUtil.getDate(years, months, Integer.parseInt(day));
				dto.setTmkrependdt(expire);
				dto.setFileKey("cus01_015");
				modify(con, dto, null, 0);
				
				DBUtil.commit(con);
				HashMap<String,Object> retmap = new HashMap<String,Object>();
				retmap.put("gctnm", gctnm);
				retmap.put("expire", expire);
				retmap.put("foldt",foldt);
				if(Integer.parseInt(delay)!=0){
					retmap.put("isDelay", "(��)");
				}else{
					retmap.put("isDelay", "");
				}
				response.setBody(retmap);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateArrangement",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �ʼ죭���¶��ƻ���Ϣ
	 */
	public ResponseEnvelop updateTest(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Customization dto = (Customization) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getTmksid() == null || dto.getTmksid().equals("")) {
				throw new AppException("�ö��ƻ���Ϣδ�Ǽ�");
			} else {
				dto.setTmkpst("6");
				dto.setFileKey("cus01_011");
				modify(con, dto, null, 0);
				dto.setFileKey("ord03_001");
				modify(con, dto, null, 0);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateArrangement",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	public ResponseEnvelop changeStatus(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Customization cz = (Customization) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if ("4".equals(cz.getTmkpst())) {
				cz.setFileKey("cus01_008");
				modify(con, cz, null, 0);
			} else if ("5".equals(cz.getTmkpst())) {
				cz.setFileKey("cus01_009");
				modify(con, cz, null, 0);
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
	 * �������¶��ƻ�״̬
	 */
	public ResponseEnvelop batchChange(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<Customization> dtoList = (Vector<Customization>) map
					.get("beo");
			String require = (String) map.get("require");
			String fileKey = null;
			HashMap<String, String> retmap = new HashMap<String, String>();
			if ("confirm".equals(require)) {
				fileKey = "cus01_006";
				retmap.put("workString", "����ȷ�����ϳɹ���");
			} else if ("refuse".equals(require)) {
				fileKey = "cus01_007";
				retmap.put("workString", "�����������ϳɹ���");
			} else if ("startMake".equals(require)) {
				fileKey = "cus01_008";
				retmap.put("workString", "������ʼ������ǳɹ���");
			} else if ("makeEnd".equals(require)) {
			} else if ("startInstall".equals(require)) {
				fileKey = "cus01_009";
				retmap.put("workString", "������ʼ��װ��ǳɹ���");
			}
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			response.setBody(retmap);
			for (Customization dto : dtoList) {
				if (dto.getTmksid() == null || dto.getTmksid().equals("")) {
					throw new AppException("�ö��ƻ���Ϣδ�Ǽ�");
				} else {
					dto.setFileKey(fileKey);
					modify(con, dto, null, 0);
				}
			}
			
			/**
			 * ����ȷ�Ϻ�������
			 */
/*			if("confirm".equals(require)){
				for(Customization dto : dtoList){
					String cql = "select count(*) as num from (select pnlnm from tblpnl  where pnlid='" + dto.getTmkpnl() + "')";
					List result4 = (Vector)DBUtil.querySQL(con, cql);
					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
						throw new AppException("�����δ�Ǽǣ�������Ʒ�����еǼǺ������");
					}
					String sql = "select pnlnm from tblpnl  where pnlid='" + dto.getTmkpnl() + "'";
					List result0 = (Vector)DBUtil.querySQL(con,sql);
					String pnlnm = ((HashMap)result0.get(0)).get("1").toString();
					if(null == ((HashMap)result0.get(0)).get("1").toString()){
						throw new AppException("������޴������Ϣ�������������");
					}
					
					
					String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//������������������Ӧ������������1������ʾ��治����Ϣ��
					if(Integer.parseInt((((HashMap)result1.get(0)).get("1")).toString()) < 1){//������������������Ӧ������������1������ʾ��治����Ϣ��
						String msg = pnlnm + "�޿�棬�����������";
						throw new AppException(msg);
					}
					
					List result2 = (Vector)DBUtil.querySQL(con,"select SEQ_STOID.NEXTVAL from dual");
					String stoid = (((HashMap)result2.get(0)).get("1")).toString();
					dto.setStoid(stoid);
					dto.setStoproid(dto.getTmkpnl());
					dto.setStogrcliid("1501000000");
					dto.setStoprotype("3");//3��ʾ���
					dto.setStoproname(pnlnm);
					dto.setStoamount(new Integer("1"));
					dto.setStoamountun("��");
					dto.setStodate(DateUtil.getDate());
					dto.setStoactype(-1);
					dto.setStoremark("���ϳ���");
					dto.setFileKey("sto02_000");
					store(con, dto, null, 0);
					
				}
			}*/
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateArrangement",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * �������ȷ��
	 */
	public ResponseEnvelop complete(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<Customization> dtoList = (Vector<Customization>) map
					.get("beo");
			String fileKey = "cus01_012";
			HashMap<String, String> retmap = new HashMap<String, String>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			response.setBody(retmap);
			for (Customization dto : dtoList) {
				if (dto.getTmksid() == null || dto.getTmksid().equals("")) {
					throw new AppException("�ö��ƻ���Ϣδ�Ǽ�");
				} else {
					//if(!dto.getTmkpnl().equals("000000"))
					//{
						dto.setFileKey(fileKey);
						modify(con, dto, null, 0);
						QA qa = new QA();
						qa.setQafno(dto.getTmkfno());
						qa.setQasid(dto.getTmksid());
						qa.setQapid(dto.getTmkpid());
						qa.setQacltnm(dto.getTmkcltnm());
						qa.setQatype("make");
						qa.setQastatus("wait");
						qa.setFileKey("qa_insert");
						store(con, qa, null, 0);
						dto.setFileKey("ord03_003");
						modify(con, dto, null, 0);
					//}
				}
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "updateComplete",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}