package org.radf.apps.product.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.radf.apps.commons.entity.Configuration;
import org.radf.apps.commons.entity.ProClass;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.Task;
import org.radf.apps.commons.entity.TypDiscount;
import org.radf.apps.product.facade.ProductFacade;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;

import com.lowagie.text.pdf.TSAClientBouncyCastle;

public class ProductImp extends IMPSupport implements ProductFacade {
	private String className = this.getClass().getName();

	/**
	 * ������Ʒ����
	 */
	public ResponseEnvelop save(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Product pdt = (Product) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// �ж�ID�Ƿ���ں��ظ�
			if (null == pdt.getPdtid() || "".equals(pdt.getPdtid())) {
				throw new AppException("������Ʒ���벻��Ϊ��!");
			}
			// �ж�ID���Ƿ��ظ�
			pdt.setFileKey("product_select");
			if (getCount(con, pdt, 0) > 0) {
				throw new AppException("����Ʒ�����Ѵ���!");
			}
			pdt.setFileKey("product_insert");
			// �����¼
			//System.out.println(pdt);
			store(con, pdt, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("pdtid", pdt.getPdtid());
			retmap.put("workString", "������Ʒ����");
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
	
	
	
	/*public	Collection getListConfiguration(String sql)
	{
	    
	}*/
	/**
	 * ����������
	 */
	public ResponseEnvelop save1(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Product pdt = (Product) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (null == pdt.getPdtnm() || "".equals(pdt.getPdtnm()) || null == pdt.getPdtmod() || "".equals(pdt.getPdtmod())) {
				throw new AppException("����������ƻ�����ͺŲ���Ϊ��!");
			}
			/*List result = (Vector)DBUtil.querySQL(con, "select SEQ_ACYID.NEXTVAL from dual");
			BigDecimal id = (BigDecimal)((HashMap)result.get(0)).get("1");
			String acyid = "A" + id.toString();
			pdt.setAcyid(acyid);*/
			
			pdt.setFileKey("product_select");
			if (getCount(con, pdt, 0) > 0) {
				throw new AppException("������Ѵ���!");
			}
			
			/*pdt.setFileKey("component_insert");
			// ����tblacy����
			store(con, pdt, null, 0);*/
			
			//String pdtnm = pdt.getAcypdtnm() + " " +  pdt.getAcytyp();
			//String pdtnm = pdt.getAcypdtnm() + pdt.getAcytyp();
			//pdt.setPdtid(acyid);
			//pdt.setPdtnm(pdtnm);
			pdt.setPdtispnl("��");
			pdt.setPdtcls("OTH");
			pdt.setFileKey("product_insert");
			// ����tblproduct����
			store(con,pdt,null,0);
			
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("pdtid",pdt.getPdtid());
			retmap.put("workString", "����������");
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save1",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * �����������
	 */
	public ResponseEnvelop save2(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Configuration configuration = (Configuration) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (null == configuration.getCfgpnlnm() || "".equals(configuration.getCfgpnlnm())) {
				throw new AppException("��������ͺŲ���Ϊ��!");
			}
			configuration.setFileKey("configuration_select");
			if (getCount(con, configuration, 0) > 0) {
				throw new AppException("������ͺŵ�������Ϣ�Ѵ���,�������ҳ������޸�!");
			}
			//configuration.setFileKey("configuration_insert");
			// �����¼
		
			//store(con, configuration, null, 0);
			//DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("cfgpnlnm", configuration.getCfgpnlnm());
			retmap.put("beo",configuration);//��configuration���󷵻أ��Ա���Action�н��д�ֵ
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "save2",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * ����¼��������������Ϣ
	 */
	public ResponseEnvelop saveDetail(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<Configuration> dtoList = (Vector<Configuration>) map.get("beo");
			
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			
			// �������������ϸ
			store(con, dtoList, null, 0);
			DBUtil.commit(con);

			HashMap<String, Object> retmap = new HashMap<String, Object>();
			

			retmap.put("workString", "����������������ϸ");
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			ex.printStackTrace();
			response.setHead(ExceptionSupport(className, "saveDetail",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	

	/**
	 * ��Ʒ�����޸�
	 */
	public ResponseEnvelop modify(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Product dto = (Product) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getPdtid() == null || dto.getPdtid().equals("")) {
				throw new AppException("����Ʒ����δ�Ǽ�");
			} else {
				dto.setFileKey("product_update");
				modify(con, dto, null, 0);
				dto.setFileKey("product_update_sto");
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}
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
	
	
	/**
	 * ��Ʒ�����޸�
	 */
	public ResponseEnvelop modifyPcl(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			ProClass dto = (ProClass) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getPclid() == null || dto.getPclid().equals("")) {
				throw new AppException("����Ʒ������δ�Ǽ�");
			} else {
				dto.setFileKey("pcl_update");
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}
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
	
	/**
	 * �������޸�
	 */
	public ResponseEnvelop modify1(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Product dto = (Product) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (null == dto.getPdtid() || "".equals(dto.getPdtid())) {
				throw new AppException("����Ʒ����δ�Ǽ�");
			}
			/*if (dto.getAcyid() == null || dto.getAcyid() .equals("")) {
				throw new AppException("��������δ�Ǽ�");
			} else {
				dto.setFileKey("component_update");
				modify(con, dto, null, 0);
				
				dto.setFileKey("product_update");
				dto.setPdtid(dto.getAcyid());
				modify(con, dto, null, 0);
				DBUtil.commit(con);
			}*/
			//dto.setFileKey("component_update");
			//modify(con, dto, null, 0);
			else {
			dto.setFileKey("product_update");
			//dto.setPdtid(dto.getAcyid());
			modify(con, dto, null, 0);
			dto.setFileKey("product_update_sto");
			modify(con, dto, null, 0);
			DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "modify1",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	
	/**
	 * ������Ʒ����
	 */
	public ResponseEnvelop savePcl(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			ProClass pcl = (ProClass) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// �ж�ID�Ƿ���ں��ظ�
			if (null == pcl.getPclid() || "".equals(pcl.getPclid())) {
				throw new AppException("������Ʒ�����벻��Ϊ��!");
			}
			// �ж�ID���Ƿ��ظ�
			pcl.setFileKey("pcl_select");
			if (getCount(con, pcl, 0) > 0) {
				throw new AppException("����Ʒ�������Ѵ���!");
			}
			pcl.setFileKey("pcl_insert");
			// �����¼
			//System.out.println(pdt);
			store(con, pcl, null, 0);
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("pclid", pcl.getPclid());
			retmap.put("workString", "������Ʒ������");
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
	
	/**
	 * ���������������޸ı���
	 * @param request
	 * @return
	 */
	public ResponseEnvelop batchSave(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			String pnlnm = (String)map.get("pnlnm");
			String[] idList = (String[])map.get("idList");
			List<Configuration> dtoList = (List<Configuration>) map.get("dtoList");
			
			
			//Collection<Configuration> collection = (Collection<Configuration>) map.get("collection");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			
			PreparedStatement pstm = null;
	        ResultSet res = null;
			
	        
	        String sql0="select cfgacyid,cfgpnlnm from tblpnlcfg where cfgpnlnm='" + pnlnm + "' order by cfgacyid asc";
			pstm = con.prepareStatement(sql0);
	        res = pstm.executeQuery();
	        for(int i = 0,j = 0; i< idList.length; i++){
	        	res.next();
	        	String oldpid=res.getString("cfgacyid");
//	        	if(idList[i].equals(oldpid)){
//	        		continue;
//	        	}
	        	dtoList.get(j).setCfgacyid(oldpid);
            	dtoList.get(j).setCfgpnlnm(pnlnm);
            	dtoList.get(j).setFileKey("pdt05_005");
            	Configuration configuration = dtoList.get(j);
				if(!(idList[i].equals(oldpid))||configuration.getCfgtemperature()!=0)
				{
					if(configuration.getCfgtemperature()==0)
					{
						configuration.setCfgtemperature(null);
					}
					modify(con, configuration, null, 0);
				}
            	j++;
	        }  
	       /* for(int  i = 0;i < dtoList.size(); i++)
            {
            	res.next();
            	String oldpid=res.getString("cfgacyid");
            	dtoList.get(i).setCfgacyid(oldpid);
            	dtoList.get(i).setCfgpnlnm(pnlnm);
            	dtoList.get(i).setFileKey("pdt05_005");
				modify(con, dtoList.get(i), null, 0);
            	//odList.get(i).setOldpid(oldpid);
            	//modify(con, odList.get(i), null, 0); 
            } */
			/*for (Configuration dto : collection) {
				dto.setFileKey("pdt05_005");
				modify(con, dto, null, 0);
			}*/
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "batchSave",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	

	
	
	/**
	 * ��Ʒ����ɾ��
	 */
	public ResponseEnvelop deletePcl(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			ProClass dto = (ProClass) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getPclid() == null || dto.getPclid().equals("")) {
				throw new AppException("����Ʒ����δ�Ǽ�");
			} else {
				dto.setFileKey("pcl_delete");
				remove(con, dto, null, 0);
				DBUtil.commit(con);
			}
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
	 * ��Ʒ����ɾ��
	 */
	public ResponseEnvelop delete(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Product dto = (Product) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getPdtid() == null || dto.getPdtid().equals("")) {
				throw new AppException("����Ʒ����δ�Ǽ�");
			} else {
				dto.setFileKey("Product_delete");
				remove(con, dto, null, 0);
				DBUtil.commit(con);
			}
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
	 * �������ɾ��
	 */
	public ResponseEnvelop delete1(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Product dto = (Product) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getAcyid() == null || dto.getAcyid().equals("")) {
				throw new AppException("���������δ�Ǽ�");
			} else {
				dto.setFileKey("component_delete");
				remove(con, dto, null, 0);
				
				dto.setPdtid(dto.getAcyid());
				dto.setFileKey("product_delete");
				remove(con, dto, null, 0);
				
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delete1",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * �������ɾ��
	 */
	public ResponseEnvelop delete2(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Configuration dto = (Configuration) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			if (dto.getPnlnm() == null || dto.getPnlnm().equals("")) {
				throw new AppException("������ͺ�δ�Ǽ�");
			} else {
				dto.setFileKey("configuration_delete");
				remove(con, dto, null, 0);
				DBUtil.commit(con);
			}
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "delete2",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	

	/**
	 * ��Ʒ��Ϣ��ʾ
	 */
	public ResponseEnvelop print(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Product dto = (Product) map.get("beo");
			dto.setFileKey("Product_select");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	
	/**
	 * ��Ʒ��Ϣ��ʾ
	 */
	public ResponseEnvelop printPcl(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			ProClass dto = (ProClass) map.get("beo");
			dto.setFileKey("pcl_select");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * ��������ʾ
	 */
	public ResponseEnvelop print1(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			HashMap map = (HashMap) request.getBody();
			Product dto = (Product) map.get("beo");
			dto.setFileKey("product_select");
			Object ci = find(con, dto, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("beo", ci);
			response.setBody(retmap);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "print1",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	/**
	 * ��ѯ��Ʒ��Ϣ
	 */
	public ResponseEnvelop query(RequestEnvelop request) {
		return find(request);
	}

	
	public ResponseEnvelop queryProidByClass(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			HashMap map = (HashMap) request.getBody();
//			Collection<OrderDetail> collection = (Collection<OrderDetail>) map
//					.get("collection");
//			Product pdt = (Product) map.get("pdt");
			String proType=(String)map.get("proType");
			List<Object> proList = new ArrayList<Object>();
//			Order header = new Order();
//			Store store = new Store();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql="select p.pdtid,p.pdtnm,p.pdtprc from tblproduct p where 1=1";
			if(null!=proType && proType.equals("nom"))
			{
			   sql+="and p.pdtcls not in('CIC','ITC','HS','ITE') and p.pdtispnl is null";
			}
			else if(null!=proType && proType.equals("cus"))
			{
		       sql+="and p.pdtcls in('CIC','ITC','HS','ITE')";
			 
			}
			else if(null!=proType && proType.equals("oth"))   //���������ͺ�
			{
		       sql+="and p.pdtcls in('OTH','BTE')";
			 
			}
			else if(null!=proType && proType.equals("rep"))
			{
				sql+="and p.pdtcls in('CIC','ITC','HS','ITE','BTE','OTH','BOX')";
			}
			else if(null!=proType && proType.equals("store"))
			{
				sql+="and p.pdtcls not in('CIC','ITC','HS','ITE') or p.pdtcls is null";
			}
			pstm = con.prepareStatement(sql);
            res = pstm.executeQuery();
            while(res.next())
            {
            	Product pro=new Product();
            	pro.setPdtid(res.getString("pdtid"));
            	pro.setPdtnm(res.getString("pdtnm"));
            	pro.setPdtprc(res.getDouble("pdtprc"));
            	proList.add(pro);
            }
//			List result = null;
//			result = (Vector) DBUtil.querySQL(con,"select p.pdtid from tblproduct p where p.pdtclid='"+ pdt.getPdtclid() + "'");					
//				price = (BigDecimal) ((HashMap) result.get(0)).get("1");
//			if(proType.equals("nom"))
//			{
//				pdt.setFileKey("pdt02_002");
//			}
//			else if(proType.equals("cus"))
//			{
//				pdt.setFileKey("pdt02_003");
//			}
//			else if(proType.equals("all"))
//			{
//				pdt.setFileKey("pdt02_001");
//			}
//			Object product = find(con, pdt, null, 0);
//			proList.add(product);
			DBUtil.commit(con);
//			HashMap<String, Object> retmap = new HashMap<String, Object>();
//			retmap.put("beo", ci);
//			response.setBody(retmap);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("pdt", proList);
			response.setBody(retmap);
	}  catch (Exception ex) {
		response.setHead(ExceptionSupport(className, "queryProidByClass",
				ManageErrorCode.SQLERROR, ex.getMessage(), request
						.getHead()));
	} finally {
		DBUtil.rollback(con);
		DBUtil.closeConnection(con);
	}
	return response;
	}
	
	
	public ResponseEnvelop queryProidByClass1(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			HashMap map = (HashMap) request.getBody();
			String proType=(String)map.get("proType");
			List<Object> proList = new ArrayList<Object>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql="select distinct t.pdtid,p.cfgpnlnm from tblpnlcfg p,tblproduct t where p.cfgpnlnm=t.pdtnm and (t.pdtcls='BTE' or t.pdtcls='OTH')";
			pstm = con.prepareStatement(sql);
            res = pstm.executeQuery();
            while(res.next())
            {
            	Task pro=new Task();
            	pro.setPnlproid(res.getString("pdtid"));
            	pro.setCfgpnlnm(res.getString("cfgpnlnm"));
            	proList.add(pro);
            }
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("pdt", proList);
			response.setBody(retmap);
	}  catch (Exception ex) {
		response.setHead(ExceptionSupport(className, "queryProidByClass",
				ManageErrorCode.SQLERROR, ex.getMessage(), request
						.getHead()));
	} finally {
		DBUtil.rollback(con);
		DBUtil.closeConnection(con);
	}
	return response;
	}
	

	public ResponseEnvelop queryParts(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			HashMap map = (HashMap) request.getBody();
//			Collection<OrderDetail> collection = (Collection<OrderDetail>) map
//					.get("collection");
//			Product pdt = (Product) map.get("pdt");
//			String proType=(String)map.get("proType");
			List<Object> proList = new ArrayList<Object>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			String sql="select p.pdtid,p.pdtnm,p.pdtmod from tblproduct p where p.pdtispnl='��'";
			pstm = con.prepareStatement(sql);
            res = pstm.executeQuery();
            while(res.next())
            {
            	Product pro=new Product();
            	pro.setPdtid(res.getString("pdtid"));
            	pro.setPdtnm(res.getString("pdtnm"));
//            	pro.setPdtprc(res.getDouble("pdtprc"));
            	pro.setPdtmod(res.getString("pdtmod"));
            	proList.add(pro);
            }

//			pdt.setFileKey("pdt05_002");//�������|2012-4-17����,pdt05_002��TZ_PDT_SQL.properties
//			Object parts = find(con, pdt, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("pdt", proList);
			response.setBody(retmap);
	}  catch (Exception ex) {
		response.setHead(ExceptionSupport(className, "queryParts",
				ManageErrorCode.SQLERROR, ex.getMessage(), request
						.getHead()));
	} finally {
		DBUtil.rollback(con);
		DBUtil.closeConnection(con);
	}
	return response;
	}
	
	
	public ResponseEnvelop queryPanels(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			HashMap map = (HashMap) request.getBody();
//			Collection<OrderDetail> collection = (Collection<OrderDetail>) map
//					.get("collection");
//			Configuration cfg = (Configuration) map.get("cfg");
//			String proType=(String)map.get("proType");
			List<Object> cfgList = new ArrayList<Object>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			String sql="select distinct cfgpnlnm from tblpnlcfg";
			pstm = con.prepareStatement(sql);
            res = pstm.executeQuery();
            while(res.next())
            {
            	Configuration cfg=new Configuration();
            	cfg.setCfgpnlnm(res.getString("cfgpnlnm"));
            	cfgList.add(cfg);
            }
//			cfg.setFileKey("pdt05_006");//�������|2012-4-17����,pdt05_002��TZ_PDT_SQL.properties
//			Object pnlnm = find(con, cfg, null, 0);
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("cfg", cfgList);
			response.setBody(retmap);
	}  catch (Exception ex) {
		response.setHead(ExceptionSupport(className, "queryParts",
				ManageErrorCode.SQLERROR, ex.getMessage(), request
						.getHead()));
	} finally {
		DBUtil.rollback(con);
		DBUtil.closeConnection(con);
	}
	return response;
	}
	
	
	
	public ResponseEnvelop queryClasses(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			HashMap map = (HashMap) request.getBody();
			ProClass pcl = (ProClass) map.get("pcl");
			List<Object> pclList = new ArrayList<Object>();
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
//			pcl.setFileKey("pdt06_000");
//			Object classes = find(con, pcl, null, 0);
			String sql="select c.pclid,c.pcllarge,c.pclmid,c.pclsmall from tblproclass c " ;
					//"where c.pclid='"+pcl.getPclid()+"'";
			pstm = con.prepareStatement(sql);
            res = pstm.executeQuery();
            while(res.next())
            {
            	ProClass pcls=new ProClass();
            	pcls.setPclid(res.getString("pclid"));
            	pcls.setPcllarge(res.getString("pcllarge"));
            	pcls.setPclmid(res.getString("pclmid"));
            	pcls.setPclsmall(res.getString("pclsmall"));
            	pclList.add(pcls);
            }
			DBUtil.commit(con);
			HashMap<String, Object> retmap = new HashMap<String, Object>();
			retmap.put("pcl", pclList);
			response.setBody(retmap);
	}  catch (Exception ex) {
		response.setHead(ExceptionSupport(className, "queryParts",
				ManageErrorCode.SQLERROR, ex.getMessage(), request
						.getHead()));
	} finally {
		DBUtil.rollback(con);
		DBUtil.closeConnection(con);
	}
	return response;
	}
	
	/**
	 * ��ѯ��Ʒ��Ϣlist
	 */
	public ResponseEnvelop findBySQL(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap mapRequest = (HashMap) request.getBody();
			Product pdt = (Product) mapRequest.get("beo");
			con = DBUtil.getConnection();
			List result = (Vector) DBUtil.querySQL(con,
					"select pdtid,pdtnm from tblproduct where pdttype='"
							+ pdt.getPdttype() + "' and pdtcls='"
							+ pdt.getPdtcls()+"'");
			HashMap mapResponse = new HashMap();
			mapResponse.put("result", result);
			response.setBody(mapResponse);
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "findBySQL",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.closeConnection(con);
		}
		return response;
	}
	/**
	 * ���Ŀ�����Ϣ����
	 * @param request
	 * @return
	 */
	public ResponseEnvelop update(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<Product> collection = (Collection<Product>) map
					.get("collection");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Product dto : collection) {
				dto.setFileKey("product_update");
				modify(con, dto, null, 0);
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "update",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * �����������
	 */
	public ResponseEnvelop savedis(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<Product> collection = (Collection<Product>) map
					.get("collection");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Product dto : collection) {
				dto.setFileKey("pdt03_001");
				modify(con, dto, null, 0);
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savedis",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	
	public ResponseEnvelop disupdate(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Collection<Product> collection = (Collection<Product>) map
					.get("collection");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Product dto : collection) {
				dto.setFileKey("pdt04_000");
				modify(con, dto, null, 0);
			}
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "disupdate",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * ������ƷƷ�Ʊ������
	 */
	public ResponseEnvelop modTypDis(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<TypDiscount> tpdList = (ArrayList<TypDiscount>) map
					.get("tpdList");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (TypDiscount tpd:tpdList) {
				modify(con, tpd, null, 0);
			}
//			modify(con, tpdList, null, 0);
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savedis",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * ����������Ʒ�������
	 */
	public ResponseEnvelop modProDis(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			List<Product> pdtList = (ArrayList<Product>) map
					.get("pdtList");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			for (Product pdt:pdtList) {
				pdt.setFileKey("pdt06_003");
				modify(con, pdt, null, 0);
			}
//			modify(con, pdtList, null, 0);
			DBUtil.commit(con);

		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "savedis",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
