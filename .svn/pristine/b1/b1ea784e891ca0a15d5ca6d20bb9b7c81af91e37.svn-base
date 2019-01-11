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
	 * 新增定制机信息
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
			// 生成订单号
			List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_FOLNO.NEXTVAL from dual");
			BigDecimal id = (BigDecimal) ((HashMap) result.get(0)).get("1");
			String tmkfno1 = id.toString();

			// 生成定制机条形码
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
				// 录入售价
				result = (Vector) DBUtil.querySQL(con,
						"select pdtprc from tblproduct where pdtid='"
								+ czList.get(i).getTmkpid() + "'");
				price[i] = (BigDecimal) ((HashMap) result.get(0)).get("1");
			}
			header.setFolctid(gid); // 团体客户
			header.setFoldt(DateUtil.getDate()); // 订单创建日期
			header.setFolno(tmkfno1); // 订单号
			header.setFolsta("uncommited"); // 定制机订单生成时状态为"未提交"
			header.setFoltype("make");// 订单类别为"定制机订单"
			header.setFolopr(opr);
//			header.setFolurgent(dg);
			if (num == 1) {
				detail.setFdtfno(tmkfno1);// 订单号
				detail.setFdtcltid(cid);//个人编号
				detail.setFdtcltnm(cnm); // 个人用户
				detail.setFdtpid(czList.get(0).getTmkpid()); // 耳机代码
				detail.setFdtprc(price[0].doubleValue());
				detail.setFdtqnt(1);// 数量为1
				detail.setFdtsqnt(1);
				detail.setFdtdprc(price[0].doubleValue());
				store(con, header, null, 0);
				store(con, detail, null, 0);
			} else if (num == 2) {
				if (czList.get(0).getTmkpid().equals(czList.get(1).getTmkpid()) && czList.get(0).getTmkplr().equals(czList.get(1).getTmkplr()) ) {
					detail.setFdtfno(tmkfno1);// 订单号
					detail.setFdtcltid(cid);//个人编号
					detail.setFdtcltnm(cnm); // 个人用户
					detail.setFdtpid(czList.get(0).getTmkpid()); // 耳机代码
					detail.setFdtprc(price[0].doubleValue());
					detail.setFdtqnt(2);// 数量为2
					detail.setFdtsqnt(2);  //发货数量
					detail.setFdtdprc(price[0].doubleValue());  //?
					store(con, header, null, 0);
					store(con, detail, null, 0);
				} else {
					OrderDetail d0 = new OrderDetail();
					d0.setFdtfno(tmkfno1);// 订单号
					d0.setFdtcltid(cid);//个人编号
					d0.setFdtcltnm(cnm); // 个人用户
					d0.setFdtpid(czList.get(0).getTmkpid()); // 左耳机代码
					d0.setFdtprc(price[0].doubleValue());
					d0.setFdtqnt(1);// 数量为1
					d0.setFdtsqnt(1);
					d0.setFdtdprc(price[0].doubleValue());
					store(con, header, null, 0);
					store(con, d0, null, 0);
					OrderDetail d1 = new OrderDetail();

					// 生成订单号
					result = (Vector) DBUtil.querySQL(con,
							"select SEQ_FOLNO.NEXTVAL from dual");
					id = (BigDecimal) ((HashMap) result.get(0)).get("1");
					String tmkfno2 = id.toString();

					d1.setFdtfno(tmkfno2);// 订单号
					d0.setFdtcltid(cid);//个人编号
					d1.setFdtcltnm(cnm); // 个人用户
					d1.setFdtpid(czList.get(1).getTmkpid()); // 右耳机代码
					d1.setFdtprc(price[1].doubleValue());
					d1.setFdtqnt(1);// 数量为1
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
	 * 定制机信息修改
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
				throw new AppException("该定制机信息未登记");
			} else {
				//查看是否已经配过面板
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
				if(!"000000".equals(dto.getTmkpnl()) && !tmkpnl.equals(dto.getTmkpnl()) && !"唯听".equals(pdttype))
				{
					String cql = "select count(*) from tblpnlqa  where pnlqapnl='" + dto.getTmkpnl() + "'";
					List result4 = (Vector)DBUtil.querySQL(con, cql);
					if(result4.size() < 1){
						throw new AppException("该面板编码未生产，请生产后继续操作！");
					}
					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
						throw new AppException("该面板编码未生产，请生产后继续操作！");
					}
					
					//查询库存中是否有该面板编号所对应的面板型号存在。
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
							throw new AppException("库存中无此面板信息，请入库后操作！");
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
								throw new AppException("库存中无此面板信息，请入库后操作！");
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
						throw new AppException("库存中无此面板信息，请入库后操作！");
					}
					
					String sql1 ="select nvl(sum(stoamount*stoactype),0)  总数 from tblsto  where stoproid='" + pnlproid + "'";
					//String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
					if(result1.size() < 1){
						throw new AppException("该面板编码所对应的面板无库存，请入库后操作！");
					}
					if (null != ((HashMap)result1.get(0)).get("1") && Integer.parseInt(((HashMap)result1.get(0)).get("1").toString()) < 1) {// 如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
						throw new AppException("该面板编码所对应的面板无库存，请入库后操作！");
					}
					
					String sqlpnl="select m.tmkpnl from tblmaking m where m.tmkpnl='" + dto.getTmkpnl() + "'";
					List resultpnl = (Vector)DBUtil.querySQL(con,sqlpnl) ;
					
					if (resultpnl.size() > 0) {// 如果该面板编码的面板存在tblmaking表中，说明该面板已经配置，不能再次配置。
						throw new AppException("该面板编码所对应的面板已出库，请选择别的面板操作！");
					}
					
					//查询面板型号所对应的商品类别代码、大类、中类和小类
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
						dto.setStoamountun("块");
						dto.setStodate(DateUtil.getDate());
						dto.setStoactype(-1);
						dto.setStodisc("0");
						dto.setStoremark("配料面板出库");
						dto.setFileKey("store_insert");
						store(con, dto, null, 0);
					}

					
				}
//				else  20130426
//				{
//					String cql = "select count(*) from tblpnlqa  where pnlqapnl='" + dto.getTmkpnl() + "'";
//					List result4 = (Vector)DBUtil.querySQL(con, cql);
//					if(result4.size() < 1){
//						throw new AppException("该面板编码未生产，请生产后继续操作！");
//					}
//					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
//						throw new AppException("该面板编码未生产，请生产后继续操作！");
//					}
//					
//					//查询库存中是否有该面板编号所对应的面板型号存在。
//					String sql = "select k.tskpnlproid,k.tskpnlnm from tbltsk k left outer join tbltskdetail d on d.tsdtskid=k.tskid "
//							+ "left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdpnlid='" +  dto.getTmkpnl() + "'";
//					List result0 = (Vector)DBUtil.querySQL(con, sql);
//					if(result0.size() < 1){
//						throw new AppException("库存中无此面板信息，请入库后操作！");
//					}
//					String pnlproid = (String)((HashMap)result0.get(0)).get("1") ;
//					String pnlnm = (String)((HashMap)result0.get(0)).get("2") ;
//					if(null == pnlproid || "".equals(pnlproid) ){
//						throw new AppException("库存中无此面板信息，请入库后操作！");
//					}
//					
//					String sql1 ="select nvl(sum(stoamount*stoactype),0)  总数 from tblsto  where stoproid='" + pnlproid + "'";
//					//String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
//					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
//					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
//					if(result1.size() < 1){
//						throw new AppException("该面板编码所对应的面板无库存，请入库后操作！");
//					}
//					if (null != ((HashMap)result1.get(0)).get("1") && Integer.parseInt(((HashMap)result1.get(0)).get("1").toString()) < 1) {// 如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
//						throw new AppException("该面板编码所对应的面板无库存，请入库后操作！");
//					}
//					
//					String sqlpnl="select m.tmkpnl from tblmaking m where m.tmkpnl='" + dto.getTmkpnl() + "'";
//					List resultpnl = (Vector)DBUtil.querySQL(con,sqlpnl) ;
//					
//					if (resultpnl.size() > 0) {// 如果该面板编码的面板存在tblmaking表中，说明该面板已经配置，不能再次配置。
//						throw new AppException("该面板编码所对应的面板已出库，请选择别的面板操作！");
//					}
//				}   20130426
				
				
				
//				if(!"000000".equals(dto.getTmkpnl()) && !tmkpnl.equals(dto.getTmkpnl())){//如果面板编码不为000000，也不和原来的面板编码一样，则将新的面板编码所对应的面板出库
//					
//					//查看面板编号是否存在，面板编号在tblpnlqa表中查询
//					String cql = "select count(*) from tblpnlqa  where pnlqapnl='" + dto.getTmkpnl() + "'";
//					List result4 = (Vector)DBUtil.querySQL(con, cql);
//					if(result4.size() < 1){
//						throw new AppException("该面板编码未生产，请生产后继续操作！");
//					}
//					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
//						throw new AppException("该面板编码未生产，请生产后继续操作！");
//					}
//					
//					//查询库存中是否有该面板编号所对应的面板型号存在。
//					String sql = "select k.tskpnlnm from tbltsk k left outer join tbltskdetail d on d.tsdtskid=k.tskid "
//							+ "left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdpnlid='" +  dto.getTmkpnl() + "'";
//					List result0 = (Vector)DBUtil.querySQL(con, sql);
//					if(result0.size() < 1){
//						throw new AppException("库存中无此面板信息，请入库后操作！");
//					}
//					String pnlnm = (String)((HashMap)result0.get(0)).get("1") ;
//					if(null == pnlnm || "".equals(pnlnm) ){
//						throw new AppException("库存中无此面板信息，请入库后操作！");
//					}
//					
//					//查看面板编号所对应的面板型号是否有足够的库存
//					String sql1 ="select nvl(sum(stoamount*stoactype),0)  总数 from tblsto  where stoproname='" + pnlnm + "'";
//					//String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
//					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
//					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
//					if(result1.size() < 1){
//						throw new AppException("该面板编码所对应的面板无库存，请入库后操作！");
//					}
//					if (null != ((HashMap)result1.get(0)).get("1") && Integer.parseInt(((HashMap)result1.get(0)).get("1").toString()) < 1) {// 如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
//						throw new AppException("该面板编码所对应的面板无库存，请入库后操作！");
//					}
//					
//					
//					
//					//查询面板型号所对应的商品类别代码、大类、中类和小类
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
//					//dto.setStoprotype("3");//3表示面板
//					dto.setStoproname(pnlnm);
//					dto.setStoamount(new Integer("1"));
//					dto.setStoamountun("块");
//					dto.setStodate(DateUtil.getDate());
//					dto.setStoactype(-1);
//					dto.setStoremark("配料面板出库");
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
	 * 定制机信息删除
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
	 * 定制机信息显示
	 */
	public ResponseEnvelop printCI(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();

		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Customization dto = (Customization) map.get("beo");
			dto.setFileKey("customization_select");// 定制机详细信息
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
	 * 配料－更新定制机信息
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
				throw new AppException("该定制机信息未登记");
			} else {
				//查看是否已经配过面板
				String tql = "select tmkpnl from tblmaking where tmkfno='" + dto.getTmkfno() + "' and tmkcltid='" + dto.getTmkcltid() + "' and tmkpid='" + dto.getTmkpid() + "'";
				List result5 = (Vector)DBUtil.querySQL(con, tql);
				String tmkpnl = null;
				if(null != ((HashMap)result5.get(0)).get("1")){
					tmkpnl = ((HashMap)result5.get(0)).get("1").toString();
				}
				if(null == tmkpnl || "".equals(tmkpnl)){
					tmkpnl = " ";
				}
				if(!"000000".equals(dto.getTmkpnl()) && !tmkpnl.equals(dto.getTmkpnl())){//如果面板编码不为“000000”，也不和原来的面板编码一样，则将相应的面板编码出库
					
					String cql = "select count(*) from tblpnlqa  where pnlqapnl='" + dto.getTmkpnl() + "'";
					List result4 = (Vector)DBUtil.querySQL(con, cql);
					if(result4.size() < 1){
						throw new AppException("该面板编码未生产，请生产后继续操作！");
					}
					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
						throw new AppException("该面板编码未生产，请生产后继续操作！");
					}
					
					//查询库存中是否有该面板编号所对应的面板型号存在。
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
							throw new AppException("库存中无此面板信息，请入库后操作！");
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
								throw new AppException("库存中无此面板信息，请入库后操作！");
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
						throw new AppException("库存中无此面板信息，请入库后操作！");
					}
					
					String sql1 ="select nvl(sum(stoamount*stoactype),0)  总数 from tblsto  where stoproid='" + pnlproid + "'";
					//String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
					if(result1.size() < 1){
						throw new AppException("该面板编码所对应的面板无库存，请入库后操作！");
					}
					if (null != ((HashMap)result1.get(0)).get("1") && Integer.parseInt(((HashMap)result1.get(0)).get("1").toString()) < 1) {// 如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
						throw new AppException("该面板编码所对应的面板无库存，请入库后操作！");
					}
					
					String sqlpnl="select m.tmkpnl from tblmaking m where m.tmkpnl='" + dto.getTmkpnl() + "'";
					List resultpnl = (Vector)DBUtil.querySQL(con,sqlpnl) ;
					
					if (resultpnl.size() > 0) {// 如果该面板编码的面板存在tblmaking表中，说明该面板已经配置，不能再次配置。
						throw new AppException("该面板编码所对应的面板已出库，请选择别的面板操作！");
					}
					
					//查询面板型号所对应的商品类别代码、大类、中类和小类
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
					dto.setStoamountun("块");
					dto.setStodate(DateUtil.getDate());
					dto.setStoactype(-1);
					dto.setStodisc("0");
					dto.setStoremark("配料面板出库");
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
				
				String delay = null;//延保年限
				String limit = null;//保修年限
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
				
				//保修日期不合法问题
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
					retmap.put("isDelay", "(续)");
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
	 * 质检－更新定制机信息
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
				throw new AppException("该定制机信息未登记");
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
	 * 批量更新定制机状态
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
				retmap.put("workString", "批量确认配料成功！");
			} else if ("refuse".equals(require)) {
				fileKey = "cus01_007";
				retmap.put("workString", "批量回退配料成功！");
			} else if ("startMake".equals(require)) {
				fileKey = "cus01_008";
				retmap.put("workString", "批量开始制作外壳成功！");
			} else if ("makeEnd".equals(require)) {
			} else if ("startInstall".equals(require)) {
				fileKey = "cus01_009";
				retmap.put("workString", "批量开始安装外壳成功！");
			}
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			response.setBody(retmap);
			for (Customization dto : dtoList) {
				if (dto.getTmksid() == null || dto.getTmksid().equals("")) {
					throw new AppException("该定制机信息未登记");
				} else {
					dto.setFileKey(fileKey);
					modify(con, dto, null, 0);
				}
			}
			
			/**
			 * 配料确认后面板出库
			 */
/*			if("confirm".equals(require)){
				for(Customization dto : dtoList){
					String cql = "select count(*) as num from (select pnlnm from tblpnl  where pnlid='" + dto.getTmkpnl() + "')";
					List result4 = (Vector)DBUtil.querySQL(con, cql);
					if(Integer.parseInt(((HashMap)result4.get(0)).get("1").toString()) < 1){
						throw new AppException("该面板未登记，请在商品管理中登记后操作！");
					}
					String sql = "select pnlnm from tblpnl  where pnlid='" + dto.getTmkpnl() + "'";
					List result0 = (Vector)DBUtil.querySQL(con,sql);
					String pnlnm = ((HashMap)result0.get(0)).get("1").toString();
					if(null == ((HashMap)result0.get(0)).get("1").toString()){
						throw new AppException("库存中无此面板信息，请入库后操作！");
					}
					
					
					String sql1 = "select nvl(sum(stoamount*stoactype),0) from tblsto where stoproname='" + pnlnm + "'";
					List result1 = (Vector)DBUtil.querySQL(con,sql1) ;
					//if(Integer.parseInt(((BigDecimal)((HashMap)result1.get(0)).get("1")).toString()) < 1){//如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
					if(Integer.parseInt((((HashMap)result1.get(0)).get("1")).toString()) < 1){//如果库存中面板编码所对应的面板个数少于1，则提示库存不足信息。
						String msg = pnlnm + "无库存，请入库后操作！";
						throw new AppException(msg);
					}
					
					List result2 = (Vector)DBUtil.querySQL(con,"select SEQ_STOID.NEXTVAL from dual");
					String stoid = (((HashMap)result2.get(0)).get("1")).toString();
					dto.setStoid(stoid);
					dto.setStoproid(dto.getTmkpnl());
					dto.setStogrcliid("1501000000");
					dto.setStoprotype("3");//3表示面板
					dto.setStoproname(pnlnm);
					dto.setStoamount(new Integer("1"));
					dto.setStoamountun("块");
					dto.setStodate(DateUtil.getDate());
					dto.setStoactype(-1);
					dto.setStoremark("配料出库");
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
	 * 生产完成确认
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
					throw new AppException("该定制机信息未登记");
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