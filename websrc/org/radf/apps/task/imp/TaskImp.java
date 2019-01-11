package org.radf.apps.task.imp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.commons.entity.Order;
import org.radf.apps.commons.entity.OrderDetail;
import org.radf.apps.commons.entity.OrderState;
import org.radf.apps.commons.entity.Product;
import org.radf.apps.commons.entity.Task;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.order.form.OrderStateForm;
import org.radf.apps.repair.facade.RepairFacade;
import org.radf.apps.repair.form.RepairForm;
import org.radf.apps.task.facade.TaskFacade;
import org.radf.login.dto.LoginDTO;
import org.radf.manage.util.ManageErrorCode;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.NoConnectionException;
import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.util.imp.IMPSupport;

import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * �������
 */
public class TaskImp extends IMPSupport implements TaskFacade {

	
	private String className = this.getClass().getName();
	/**
	 * ����������
	 */
	public ResponseEnvelop configure(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Task dto = (Task) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			// ��ȡ�������񵥺�
			List result = (Vector) DBUtil.querySQL(con,
					"select SEQ_TSKID.NEXTVAL from dual");
			BigDecimal stkid = (BigDecimal)((HashMap)result.get(0)).get("1");
			String id = stkid.toString();
			dto.setTskid(id);//�����ݿ���ȡ���������񵥺�
			dto.setTsksta("0");//�����״̬����Ϊ�����δȷ�ϡ�
			
			dto.setFileKey("task_insert");
			// ����������
			store(con, dto, null, 0);
			
			String sql =  "select cfgacyid from tblpnlcfg where cfgpnlnm='" + dto.getTskpnlnm() + "'";
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
			retmap.put("tskid", dto.getTskid());
			retmap.put("beo", dto);
			response.setBody(retmap);

			
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "configure",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	
	/**
	 * �޸�����
	 */
	public ResponseEnvelop modify(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			Task dto = (Task)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			//dto.setFileKey("tsk01_003");
			modify(con, dto, null, 0);
			
			DBUtil.commit(con);
			
		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae, request.getHead()));
		}catch (Exception ex) {
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
	 * ����ɾ��
	 */
	public ResponseEnvelop delete(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			Task dto = (Task)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			dto.setFileKey("task_delete");
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
	
	/**
	 * �������ȷ��
	 */
	public ResponseEnvelop confirm(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Task task = (Task) map.get("beo");
			List<Task> taskList = (List<Task>)map.get("taskList");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			
			// �жϿ������Ӧ�Ŀ���Ƿ��㹻
			String sql1 = "select f.tcfacy,y.pdtnm,y.pdtmod from tbltskcfg f left outer join tbltsk k on k.tskid=f.tcfid left outer join tblproduct y on y.pdtid=f.tcfacy  where k.tskid='" + task.getTskid() + "'";
			List result = (Vector)DBUtil.querySQL(con, sql1);
			if(result.size() > 0){//result.size()Ϊһ�������е��������������
				
				//��ѭ����������е�����Ƿ����㹻�Ŀ��
				for(int i = 0; i < result.size(); i++){
					String acyid = ((HashMap)result.get(i)).get("1").toString();//�������
					String acypdtnm = ((HashMap)result.get(i)).get("2").toString();//�������
					String sql2 = "select nvl(sum(stoamount*stoactype),0) as temp from tblsto  where stoproid = '" + acyid + "'";//ĳ�����������Ӧ����Ʒʣ�������
					List result1 = (Vector)DBUtil.querySQL(con, sql2);
					Integer amount = Integer.parseInt(((HashMap)result1.get(0)).get("1").toString());//ĳ�����������Ӧ����Ʒʣ����
					if(amount < task.getTskpnlqnt()){
						String msg = acypdtnm + "û���㹻�Ŀ������";
						throw new AppException(msg);
					}
				}
				for(int i = 0; i < result.size(); i++){
					String acyid = ((HashMap)result.get(i)).get("1").toString();
					String acypdtnm = ((HashMap)result.get(i)).get("2").toString();
					//String acytyp = ((HashMap)result.get(i)).get("3").toString();//����ͺ�
					List result2 = (Vector)DBUtil.querySQL(con, "select SEQ_STOID.NEXTVAL from dual");
					BigDecimal stoid = (BigDecimal)((HashMap)result2.get(0)).get("1");//ĳ�����������Ӧ�Ŀ����
					String id = stoid.toString();
					
//					List result1 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall from  tblproduct t left join tblproclass c on t.pdtclid=c.pclid where t.pdtid='" + acyid +"'");
//					String pclid = null;
//					String pcllar = null;
//					String pclmid = null;
//					String pclsam = null;
//					if(result.size() > 0){
//						pclid = ((HashMap)result1.get(0)).get("1").toString();	
//						pcllar = ((HashMap)result1.get(0)).get("2").toString();						
//						pclmid = ((HashMap)result1.get(0)).get("3").toString();						
//						pclsam = ((HashMap)result1.get(0)).get("4").toString();
//					}
//					
//					
//					task.setStoprotype(pclid);
//					task.setStocllar(pcllar);
//					task.setStoclmid(pclmid);
//					task.setStoclsam(pclsam);
					
					task.setStoid(id);
					task.setStogrcliid("1501000000");
					task.setStoproid(acyid);
					task.setStoproname(acypdtnm);
					task.setStoamount(task.getTskpnlqnt());
					task.setStoactype(-1);//����涯�����óɳ��⣨-1Ϊ���⣩
					task.setStoremark("����������");
					task.setStooprcd(task.getTskadtopr());
					task.setStodisc("0");//�Ƿ��ѱ���
					task.setStodate(DateUtil.getDate());
					
					task.setFileKey("store_insert");
					store(con, task, null, 0);
					
					
					
				}
			}else{
				String msg = task.getTskid() + "����Ӧ�������������Ϣ��";
				throw new AppException(msg);
			}
			task.setTsksta("1");//1��ʾ�����ȷ��
			//task.setFileKey("tsk02_003");
			task.setFileKey("task_update");
			modify(con, task, null, 0);
			//����tbltskdetail����
			for(int i = 1; i <= task.getTskpnlqnt(); i ++){
				task.setTsdtskid(task.getTskid());
				if(i>=10){
					task.setTsdsid(task.getTskid() + "-" + i);
				}
				else {
					task.setTsdsid(task.getTskid() + "-0" + i);
				}
				//task.setTsdsid(task.getTskid() + "-" + i);
				task.setTsdsta("1");
				task.setFileKey("tsk02_004");
				store(con, task, null, 0);
			}
			
			for (Task tsk : taskList) {
				Task ts = new Task();
				ts.setTcfid(task.getTskid());
				// ts.setTcfsid(task.getTskid() + "-" + i);
				ts.setTcfacy(tsk.getTcfacy());
				ts.setTcfbth(tsk.getTcfbth());
				ts.setTcfnum(tsk.getTcfnum());
				ts.setTcfnt(tsk.getTcfnt());

				ts.setFileKey("tsk02_006");
				// ����tbltskcfg����
				modify(con, ts, null, 0);
			}
			
			
			DBUtil.commit(con);
/*			HashMap<String, String> retmap = new HashMap<String, String>();
//			retmap.put("pdtid", task.getPdtid());
			retmap.put("workString", "���ȷ��");
			response.setBody(retmap);*/
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "confirm",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * �������
	 */
	public ResponseEnvelop produce(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			Task dto = (Task)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			//dto.setFileKey("tsk06_002");
			dto.setFileKey("task_update");
			modify(con, dto, null, 0);
			
			for(int i = 1; i <= dto.getTskpnlqnt(); i ++){
				dto.setTsdtskid(dto.getTskid());
				if(i>=10){
					dto.setTsdsid(dto.getTskid() + "-" + i);
				}
				else {
					dto.setTsdsid(dto.getTskid() + "-0" + i);
				}
				//dto.setTsdsid(dto.getTskid() + "-" + i);
				dto.setTsdsta("2");
				dto.setFileKey("tsk06_003");
				modify(con, dto, null, 0);
			}
			
			DBUtil.commit(con);

		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "produce",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * ������
	 */
	public ResponseEnvelop finish(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			Task dto = (Task)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);

			dto.setFileKey("tsk03_002");
			modify(con, dto, null, 0);
			
			for(int i = 1; i <= dto.getTskpnlqnt(); i ++){
				dto.setTsdtskid(dto.getTskid());
				dto.setTsdsid(dto.getTskid() + "-" + i);
				dto.setTsdsta("3");
				dto.setFileKey("tsk06_003");
				modify(con, dto, null, 0);
			}
			
			DBUtil.commit(con);

		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "finish",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * ����ʼ���Ϣ����
	 */
	public ResponseEnvelop saveQA(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			Task dto = (Task)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			/*String pnlid = dto.getPnlqapnl();//�����ʼ����
			String pnlid1 = null;//�����һ��������������������
			String pnlid2 = null;//���һ��������
			
			String prefix = pnlid.substring(0, 2);//�����ʼ�����ǰ������ĸ��JB�� 
			Integer pnlnum = Integer.parseInt(pnlid.substring(2));//�����ʼ����JB���9λ����
			Integer num = dto.getTskpnlqnt();//�����е��������
			for(int i = 0; i < num; i ++){
				if(pnlnum % 10 == 4){//���ݿͻ�Ҫ������ŵ����һλ������Ϊ4
					pnlnum ++;
				}
				if(i == num-1){//��ȡ���һ��������
					pnlid2 = prefix + pnlnum.toString();
					dto.setPnlid(pnlid2);
					dto.setPnlnm(dto.getTskpnlnm());
					dto.setFileKey("tsk04_007");
					dto.setPnlnt("�������������");
					//����tblpnl����
					store(con, dto, null, 0);
				}
				else{
					pnlid1 = prefix + pnlnum.toString();
					dto.setPnlid(pnlid1);
					dto.setPnlnm(dto.getTskpnlnm());
					dto.setFileKey("tsk04_007");
					dto.setPnlnt("�������������");
					//����tblpnl����
					store(con, dto, null, 0);
				}
				pnlnum ++;
				
			}*/
			
		/*	// ��ȡ�����ʼ���ˮ��
			List result = (Vector)DBUtil.querySQL(con, "select SEQ_PQAID.NEXTVAL from dual");
			BigDecimal pqaid = (BigDecimal)((HashMap)result.get(0)).get("1");
			
			String id = pqaid.toString();
			dto.setPnlqaid(id);//�ʼ���ˮ��
*/			
			//String pnlidRange = pnlid + "-" + pnlid2;
			//dto.setPnlqapnl(pnlidRange);//��������ŷ�Χ
			String sql2 = "select * from tblpnlqa where pnlqatskid = '" + dto.getTskid() + "' and pnlqapnl='" + dto.getPnlqapnl() + "'";
			List result2 = (Vector)DBUtil.querySQL(con, sql2);
			if(result2.size() < 1){
				dto.setFileKey("tsk04_003");
				//���tblpnlqa�����޼�¼�򽫼�¼����tblpnlqa��
				store(con, dto, null, 0);
			}else{
				dto.setFileKey("tsk04_009");
				modify(con, dto, null, 0);
			}
			/*if(null !=dto.getTsksta() && !dto.getTsksta().equals("4")){
				dto.setTsksta("4");//�ı����״̬��4��ʾ����ʼ���
				dto.setFileKey("tsk04_004");
				//����tbltsk����Ӧ��״̬
				modify(con, dto, null, 0);
				
			}*/
			
			//�������еĵ�ǰ���״̬����Ϊ�������ʼ�
			dto.setTsdsta("5");
			dto.setTsdpnlid(dto.getPnlqapnl());
			dto.setFileKey("tsk06_003");
			dto.setTsdtskid(dto.getTskid());
			modify(con, dto, null, 0);
			
		/*	//�����ǰ���״̬Ϊ��5�������ʼ���Ϊ���ϸ񡱣������ͬһ�����е����״̬�Ƿ�Ϊ������ʼ���ɡ������ʼ�����Ϊ���ϸ�
			if("5".equals(dto.getTsdsta()) && "yes".equals(dto.getPnlqarsta())){
				Integer j = 0;
				String sql = "select d.tsdsta,q.pnlqarsta from tbltskdetail d left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdtskid ='" + dto.getTskid() + "'";
				List result1 = (Vector)DBUtil.querySQL(con, sql);
				for(int i = 0; i <= dto.getTskpnlqnt()-1; i ++)
				{
					//String sql = "select d.tsdsta,q.pnlqarsta from tbltskdetail d left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdtskid ='" + dto.getTskid() + "'";
					//List result1 = (Vector)DBUtil.querySQL(con, sql);
					String tsdsta = ((HashMap)result1.get(i)).get("1").toString();
					if(null != ((HashMap)result1.get(i)).get("2")){
						String qarst = ((HashMap)result1.get(i)).get("2").toString();
						if("5".equals(tsdsta) && "yes".equals(qarst)){
							j ++;
							if(dto.getTskpnlqnt().intValue() == j){//���j�������������˵��������嶼�Ѿ��ϸ����������ʼ�
								dto.setTsksta("5");//�ı����״̬��5��ʾ����ʼ����
								dto.setFileKey("tsk04_004");
								//����tbltsk����Ӧ��״̬
								modify(con, dto, null, 0);
							}
						}
						
					}
				}
			}*/
			
			DBUtil.commit(con);
		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "saveQA",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * �������ʼ���Ϣ����
	 */
	public ResponseEnvelop saveQAF(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			Task dto = (Task)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			
			String pnlid = dto.getPnlqapnl();//�����뷶Χ
			String pnlid1 = null;//�����һ��������������������
			String pnlid2 = null;//���һ��������
			
			String prefix = pnlid.substring(0, 2);//�����ʼ�����ǰ������ĸ��JB��
			String subprefix = pnlid.substring(2,11);//�����ʼ����JB���9λ����
			Integer pnlnum = Integer.parseInt(subprefix);//�����ʼ����JB���9λ����
			Integer num = dto.getTskpnlqnt();//�����е��������
			for(int i = 0; i < num; i ++){
				if(pnlnum % 10 == 4){//���ݿͻ�Ҫ������ŵ����һλ������Ϊ4
					pnlnum ++;
				}
				if(i == num-1){//��ȡ���һ��������
					pnlid2 = prefix + pnlnum.toString();
					dto.setPnlid(pnlid2);
					dto.setPnlnm(dto.getTskpnlnm());
					dto.setFileKey("tsk04_007");
					dto.setPnlnt("�������������");
					//����tblpnl����
					store(con, dto, null, 0);
				}
				else{
					pnlid1 = prefix + pnlnum.toString();
					dto.setPnlid(pnlid1);
					dto.setPnlnm(dto.getTskpnlnm());
					dto.setFileKey("tsk04_007");
					dto.setPnlnt("�������������");
					//����tblpnl����
					store(con, dto, null, 0);
				}
				pnlnum ++;
				
			}
			
			dto.setFileKey("tsk07_003");
			modify(con, dto, null, 0);
			
			dto.setTsksta("5");//�ı����״̬��5��ʾ����ʼ����
			dto.setFileKey("tsk07_004");
			modify(con, dto, null, 0);
			
			DBUtil.commit(con);
		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "saveQAF",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	/**
	 * �˻�����ʼ���Ϣ����
	 */
	public ResponseEnvelop saveRecoilQA(RequestEnvelop request){
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			HashMap map = (HashMap)request.getBody();
			Task dto = (Task)map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			List result1 = (Vector)DBUtil.querySQL(con, "select SEQ_TSKID.NEXTVAL from dual");
			BigDecimal id = (BigDecimal)((HashMap)result1.get(0)).get("1");
			String tskid = id.toString();
			dto.setTskid(tskid);
			dto.setTskpnlnm(dto.getPnlnm());
			dto.setTskpnlqnt(1);
			dto.setTsksta("5");
			dto.setTskbilopr(dto.getPnlqaopra());//�Ƶ���
			dto.setTskadtopr(dto.getPnlqaopra());//�����
			dto.setTsknt("�˻��ʼ�");
			dto.setFileKey("tsk04_005");
			//����tbltsk����
			store(con, dto, null, 0);
			
			List result2 = (Vector)DBUtil.querySQL(con, "select SEQ_PQAID.NEXTVAL from dual");
			BigDecimal id2 = (BigDecimal)((HashMap)result2.get(0)).get("1");
			String qaid = id2.toString();
			dto.setPnlqaid(qaid);
			dto.setPnlqatskid(tskid);
			dto.setPnlqapnl(dto.getPnlid());
			dto.setPnlqant(dto.getPnlnt());
			dto.setFileKey("tsk04_006");
			//����tblpnlqa����
			store(con, dto, null, 0);
			
			dto.setPnlnt("�˻����");
			dto.setFileKey("tsk04_007");
			//����tblpnl����
			store(con, dto, null, 0);
			
			
			DBUtil.commit(con);
		}catch(AppException ae){
			response.setHead(ExceptionSupport(className, ae,request.getHead()));
		} catch(Exception ex){
			response.setHead(ExceptionSupport(className, "saveRecoilQA",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		}finally{
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * �����Ϣ����
	 */
	public ResponseEnvelop store(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Task task = (Task) map.get("beo");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			
			//��ѯ����ͺ�����Ӧ����Ʒ�����롢���ࡢ�����С��
			List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid from  tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid where t.pdtnm='" + task.getTskpnlnm() + "'");
			String pclid=(String)(((HashMap)result2.get(0)).get("1"));
			if(null!=pclid && !"".equals(pclid))
			{
				String pcllar = ((HashMap)result2.get(0)).get("2").toString();
				String pclmid = ((HashMap)result2.get(0)).get("3").toString();
				String pclsam = ((HashMap)result2.get(0)).get("4").toString();
				String pdtid = ((HashMap)result2.get(0)).get("5").toString();
				task.setStoprotype(pclid);
				task.setStocllar(pcllar);
				task.setStoclmid(pclmid);
				task.setStoclsam(pclsam);
				task.setStoproid(pdtid);
			}
			
			
			// �������񵥲�ѯ��Ӧ�������
			String sql1 = "select q.pnlqapnl,q.pnlqant,k.tskpnlnm from tblpnlqa q left outer join tbltsk k on k.tskid=q.pnlqatskid where pnlqatskid='" + task.getTskid() + "'";
			List result = (Vector)DBUtil.querySQL(con, sql1);
			String pnlid =null;
			String pnlqant = null;
			if(result.size() > 0){
	
				String mark = "���񵥺�Ϊ" + task.getTskid() + "����������ţ�";
				for(int i = 0; i < result.size(); i ++){
					if(i == result.size()-1){
						mark += ((HashMap)result.get(i)).get("1").toString();//�����
					}
					else{
						mark += ((HashMap)result.get(i)).get("1").toString() + "," ;
					}
				}
				/*if(null != ((HashMap)result.get(0)).get("2"))
					pnlqant = ((HashMap)result.get(0)).get("2").toString();//��ע
				else
					pnlqant = " ";*/
					
					/*List result1 = (Vector)DBUtil.querySQL(con, "select SEQ_STOID.NEXTVAL from dual");
					BigDecimal id = (BigDecimal)((HashMap)result1.get(0)).get("1");
					String stoid = id.toString();
					task.setStoid(stoid);*/
					
				task.setStoremark(mark);//��ע
				
				task.setFileKey("store_insert");
				store(con, task, null, 0);

			}else{
				String msg = task.getTskid() + "����Ӧ�������������Ϣ��";
				throw new AppException(msg);
			}
			
			//�޸���������Ӧ�����״̬
			task.setFileKey("tsk05_003");
			modify(con, task, null, 0);
			
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "store",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * ��������������Ϣ
	 */
	public ResponseEnvelop batchSave(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Task dto = (Task) map.get("beo");
			List<Task> taskList = (List<Task>)map.get("taskList");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			
			for (Task tsk : taskList) {
	
				Task task = new Task();
				task = dto;
				task.setTsdsid(tsk.getTsdsid());
				task.setTsdsta(tsk.getTsdsta());
				task.setTsdtskid(task.getTskid());
				if(!task.getTsdsta().equals("2")){
					throw new AppException("������Ϊ��" + task.getTsdsid() + "����������������\\n�������ظ�������");
				}else{
					task.setTsdsta("3");
					task.setTsdfinish(DateUtil.getDate());
					task.setFileKey("tsk06_003");
					modify(con, task, null, 0);
				}
				
			}
			
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
	 * ���������ʼ���Ϣ
	 */
	public ResponseEnvelop batchQA(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Task dto = (Task) map.get("beo");
			List<Task> taskList = (List<Task>)map.get("taskList");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			
			
			for (Task tsk : taskList) {
				
				/*Task ts = new Task();
				ts.setTsdpnlid(tsk.getTsdpnlid());
				ts.setPnlqarsta(tsk.getPnlqarsta());
				ts.setPnlqant(tsk.getPnlqant());
				ts.setPnlqaopra(tsk.getPnlqaopra());
				ts.setPnlqadt(tsk.getPnlqadt());*/
				
				Task task = new Task();
				task = dto;
				task.setTsdpnlid(tsk.getTsdpnlid());
				task.setPnlqarsta(tsk.getPnlqarsta());
				task.setPnlqant(tsk.getPnlqant());
				task.setPnlqaopra(tsk.getPnlqaopra());
				task.setPnlqadt(tsk.getPnlqadt());
				task.setTsdsid(tsk.getTsdsid());
				task.setPnlqatskid(task.getTskid());
				task.setPnlqapnl(tsk.getTsdpnlid());
				
				//String sql2 = "select pnlqaid from tblpnlqa where pnlqatskid = '" + task.getTskid() + "' and pnlqapnl='" + tsk.getTsdpnlid() + "'";
				String sql2 = "select pnlqaid from tblpnlqa where pnlqapnl =(select tsdpnlid from tbltskdetail where tsdtskid = '" 
						+ task.getTskid() + "' and tsdsid='" + task.getTsdsid() + "')";
				List result2 = (Vector)DBUtil.querySQL(con, sql2);
				if(result2.size() < 1){
					task.setFileKey("tsk04_003");
					//���tblpnlqa�����޼�¼�򽫼�¼����tblpnlqa��
					store(con, task, null, 0);
				}else{
					String qaid = ((HashMap)result2.get(0)).get("1").toString();
					task.setPnlqaid(qaid);
					task.setPnlqadt(DateUtil.getDate());
					task.setFileKey("tsk04_009");
					modify(con, task, null, 0);
				}/*
				if(null != task.getTsksta() && !task.getTsksta().equals("4")){
					task.setTsksta("4");//�ı����״̬��4��ʾ����ʼ���
					task.setFileKey("tsk04_004");
					//����tbltsk����Ӧ��״̬
					modify(con, task, null, 0);
					
				}*/
				
				//�������е�ĳһ���������״̬����Ϊ�������ʼ�
				task.setTsdsta("5");
				//task.setTsdpnlid(task.getPnlqapnl());
				task.setFileKey("tsk06_003");
				task.setTsdtskid(task.getTskid());
				modify(con, task, null, 0);
				
				/*//�����ǰ���״̬Ϊ��5�������ʼ���Ϊ���ϸ񡱣������ͬһ�����е����״̬�Ƿ�Ϊ������ʼ���ɡ������ʼ�����Ϊ���ϸ�
				if("5".equals(task.getTsdsta()) && "yes".equals(task.getPnlqarsta())){
					Integer j = 0;
					String sql = "select d.tsdsta,q.pnlqarsta from tbltskdetail d left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdtskid ='" + task.getTskid() + "'";
					List result1 = (Vector)DBUtil.querySQL(con, sql);
					for(int i = 0; i <= task.getTskpnlqnt()-1; i ++)
					{
						//String sql = "select d.tsdsta,q.pnlqarsta from tbltskdetail d left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdtskid ='" + dto.getTskid() + "'";
						//List result1 = (Vector)DBUtil.querySQL(con, sql);
						String tsdsta = ((HashMap)result1.get(i)).get("1").toString();
						if(null != ((HashMap)result1.get(i)).get("2")){
							String qarst = ((HashMap)result1.get(i)).get("2").toString();
							if("5".equals(tsdsta) && "yes".equals(qarst)){
								j ++;
								if(task.getTskpnlqnt().intValue() == j){//���j�������������˵��������嶼�Ѿ��ϸ����������ʼ�
									task.setTsksta("5");//�ı����״̬��5��ʾ����ʼ����
									task.setFileKey("tsk04_004");
									//����tbltsk����Ӧ��״̬
									modify(con, task, null, 0);
								}
							}
							
						}
					}
				}*/
				
			}
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "batchQA",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	/**
	 * �����޸��ʼ���Ϣ
	 */
	public ResponseEnvelop batchQaUpdate(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Task dto = (Task) map.get("beo");
			List<Task> taskList = (List<Task>)map.get("taskList");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
			
			
			for (Task tsk : taskList) {
				
				/*Task ts = new Task();
				ts.setTsdpnlid(tsk.getTsdpnlid());
				ts.setPnlqarsta(tsk.getPnlqarsta());
				ts.setPnlqant(tsk.getPnlqant());
				ts.setPnlqaopra(tsk.getPnlqaopra());
				ts.setPnlqadt(tsk.getPnlqadt());*/
				
				Task task = new Task();
				task = dto;
				task.setTsdpnlid(tsk.getTsdpnlid());
//				task.setPnlqarsta(tsk.getPnlqarsta());
//				task.setPnlqant(tsk.getPnlqant());
//				task.setPnlqaopra(tsk.getPnlqaopra());
//				task.setPnlqadt(tsk.getPnlqadt());
				task.setTsdsid(tsk.getTsdsid());
				task.setPnlqatskid(task.getTskid());
				task.setPnlqapnl(tsk.getTsdpnlid());
				
				//String sql2 = "select pnlqaid from tblpnlqa where pnlqatskid = '" + task.getTskid() + "' and pnlqapnl='" + tsk.getTsdpnlid() + "'";
				String sql2 = "select pnlqaid��PNLQAPNL from tblpnlqa where pnlqapnl =(select tsdpnlid from tbltskdetail where tsdtskid = '" 
						+ task.getTskid() + "' and tsdsid='" + task.getTsdsid() + "')";
				List result2 = (Vector)DBUtil.querySQL(con, sql2);
				if(result2.size() < 1){
					task.setFileKey("tsk04_003");
					//���tblpnlqa�����޼�¼�򽫼�¼����tblpnlqa��
					store(con, task, null, 0);
				}else{
					String qaid = ((HashMap)result2.get(0)).get("1").toString();
					String oldqapnl = ((HashMap)result2.get(0)).get("2").toString();
					String newqapnl = task.getPnlqapnl();

					task.setPnlqaid(qaid);
//					task.setPnlqadt(DateUtil.getDate());
					task.setFileKey("tsk04_009");
					modify(con, task, null, 0);
				
				
				//�������е�ĳһ�������������޸�
//				task.setTsdsta("5");
//              task.setTsdpnlid(task.getPnlqapnl());
				task.setFileKey("tsk06_003");
				task.setTsdtskid(task.getTskid());
				modify(con, task, null, 0);
				
				String sql3 = "select TSKSTA from tbltsk where TSKID='"+task.getTskid()+"'"; 
				List result3 = (Vector)DBUtil.querySQL(con, sql3);
				String type=((HashMap)result3.get(0)).get("1").toString();
				if(type.equals("8"))
				{
					String sq14="select STOREMARK,STOID from tblsto where STOREMARK like '%"+task.getTskid()+"��%'";
					System.out.println(sq14);
					List result4=(Vector)DBUtil.querySQL(con,sq14);
					if(result4.size()>=1)
					{
						String stomark=((HashMap)result4.get(0)).get("1").toString();
						String stoid=((HashMap)result4.get(0)).get("2").toString();

						  int   pos=0;   
				          int   findPos;   
				          if((findPos=stomark.indexOf(oldqapnl,pos))!=-1){   
				        	  stomark=stomark.substring(0,findPos)+newqapnl+stomark.substring(findPos+oldqapnl.length());   
				          }   
                         String sql5="update tblsto set storemark='"+stomark+"' where stoid='"+stoid+"'";
                         System.out.println(sql5);
                         Statement stmt=con.createStatement(); 
         			     stmt.execute(sql5);
         			    
					}
                 					
				}
				/*//�����ǰ���״̬Ϊ��5�������ʼ���Ϊ���ϸ񡱣������ͬһ�����е����״̬�Ƿ�Ϊ������ʼ���ɡ������ʼ�����Ϊ���ϸ�
				if("5".equals(task.getTsdsta()) && "yes".equals(task.getPnlqarsta())){
					Integer j = 0;
					String sql = "select d.tsdsta,q.pnlqarsta from tbltskdetail d left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdtskid ='" + task.getTskid() + "'";
					List result1 = (Vector)DBUtil.querySQL(con, sql);
					for(int i = 0; i <= task.getTskpnlqnt()-1; i ++)
					{
						//String sql = "select d.tsdsta,q.pnlqarsta from tbltskdetail d left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdtskid ='" + dto.getTskid() + "'";
						//List result1 = (Vector)DBUtil.querySQL(con, sql);
						String tsdsta = ((HashMap)result1.get(i)).get("1").toString();
						if(null != ((HashMap)result1.get(i)).get("2")){
							String qarst = ((HashMap)result1.get(i)).get("2").toString();
							if("5".equals(tsdsta) && "yes".equals(qarst)){
								j ++;
								if(task.getTskpnlqnt().intValue() == j){//���j�������������˵��������嶼�Ѿ��ϸ����������ʼ�
									task.setTsksta("5");//�ı����״̬��5��ʾ����ʼ����
									task.setFileKey("tsk04_004");
									//����tbltsk����Ӧ��״̬
									modify(con, task, null, 0);
								}
							}
							
						}
					}
				}*/
				
				}
			}
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "batchQA",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * �������
	 */
	public ResponseEnvelop batchStore(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Task dto = (Task) map.get("beo");
			List<Task> taskList = (List<Task>)map.get("taskList");
			//int length = (Integer)map.get("length");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
	
			String mark = "���񵥺�Ϊ" + dto.getTskid() + "�Ĳ�������ţ�";;
			int m = 0;//���㱣���������
			for (Task tsk : taskList) {
				
				
				Task task = new Task();
				task = dto;
				task.setTsdpnlid(tsk.getTsdpnlid());
				task.setTsdsid(tsk.getTsdsid());
				

				//�������е�ĳһ���������״̬����Ϊ��������
				task.setTsdsta("6");
				task.setTsdtskid(task.getTskid());
				task.setFileKey("tsk06_003");
				modify(con, task, null, 0);
				
				List result1 = (Vector)DBUtil.querySQL(con, "select k.tskpnlproid " +
						"from tbltsk k" +
						" where k.tskid='" + dto.getTskid() + "'");
				String pnlproid=null;
				if(result1.size() > 0){
					pnlproid=(String)(((HashMap)result1.get(0)).get("1"));
					dto.setPnlproid(pnlproid);
				}
				
				//��ѯ����ͺ�����Ӧ����Ʒ�����롢���ࡢ�����С��
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
						"from tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid" +
						" where t.pdtid='" + dto.getPnlproid() + "'");
				
				String pclid = null;
				if(result2.size() > 0){
					pclid=(String)(((HashMap)result2.get(0)).get("1"));
					String pdtid = ((HashMap)result2.get(0)).get("5").toString();
					dto.setStoproid(pdtid);
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
				if(m == taskList.size() -1){
					mark += tsk.getTsdpnlid();
				}else{
					mark += tsk.getTsdpnlid()  + "," ;
				}
				m ++;
			}
			Integer j = 0;
			String sql = "select d.tsdsta,q.pnlqarsta from tbltskdetail d left outer join tblpnlqa q on q.pnlqapnl=d.tsdpnlid where d.tsdtskid ='"
					+ dto.getTskid() + "'";
			List result1 = (Vector) DBUtil.querySQL(con, sql);
			for (int i = 0; i <= dto.getTskpnlqnt() - 1; i++) {
				String tsdsta = ((HashMap) result1.get(i)).get("1").toString();
				if ("6".equals(tsdsta)) {
					j++;
					if (dto.getTskpnlqnt().intValue() == j) {// ���j�������������˵��������嶼�Ѿ����
						dto.setTsksta("8");// �ı����״̬��8��ʾһ�������е����ȫ�����
						dto.setFileKey("task_update");
						// ����tbltsk����Ӧ��״̬
						modify(con, dto, null, 0);
					}
				}

			}

			dto.setStoremark(mark);//��ע
			
			dto.setFileKey("store_insert");
			store(con, dto, null, 0);
				
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "batchStore",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
	
	
	/**
	 * �������
	 */
	public ResponseEnvelop batchStorere(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try {
			HashMap map = (HashMap) request.getBody();
			Task dto = (Task) map.get("beo");
			List<Task> taskList = (List<Task>)map.get("taskList");
			//int length = (Integer)map.get("length");
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			
	
			String mark = "�������������Ϊ��";;
			int m = 0;//���㱣���������
			for (Task tsk : taskList) {
				
				
				
				Task task = new Task();
				task = dto;
				List result0 = (Vector)DBUtil.querySQL(con, "select SEQ_PQAID.NEXTVAL from dual");
				BigDecimal id2 = (BigDecimal)((HashMap)result0.get(0)).get("1");
				String qaid = id2.toString();
			    task.setPnlqaid(qaid);
				task.setPnlqapnl(tsk.getPnlqapnl());
				task.setPnlqant(tsk.getPnlqant());
				
				task.setFileKey("tsk05_006");
				store(con, dto, null, 0);
				
				
				List result1 = (Vector)DBUtil.querySQL(con, "select p.pdtnm " +
						"from tblproduct p" +
						" where p.pdtid='" + dto.getPnlproid() + "'");
				String stoproname=null;
				if(result1.size() > 0){
					stoproname=(String)(((HashMap)result1.get(0)).get("1"));
					dto.setStoproname(stoproname);
				}
				
				//��ѯ����ͺ�����Ӧ����Ʒ�����롢���ࡢ�����С��
				List result2 = (Vector)DBUtil.querySQL(con, "select t.pdtclid,c.pcllarge,c.pclmid,c.pclsmall,t.pdtid " +
						"from tblproduct t left outer join tblproclass c on t.pdtclid=c.pclid" +
						" where t.pdtid='" + dto.getPnlproid() + "'");
				
				String pclid = null;
				if(result2.size() > 0){
					pclid=(String)(((HashMap)result2.get(0)).get("1"));
					String pdtid = ((HashMap)result2.get(0)).get("5").toString();
					dto.setStoproid(pdtid);
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
				if(m == taskList.size() -1){
					mark += tsk.getPnlqapnl();
				}else{
					mark += tsk.getPnlqapnl()  + "," ;
				}
				m ++;
			}
			

			dto.setStoremark(mark);//��ע
			
			dto.setFileKey("store_insert");
			store(con, dto, null, 0);
				
			
			DBUtil.commit(con);
		} catch (AppException ae) {
			response
					.setHead(ExceptionSupport(className, ae, request.getHead()));
		} catch (Exception ex) {
			response.setHead(ExceptionSupport(className, "batchStore",
					ManageErrorCode.SQLERROR, ex.getMessage(), request
							.getHead()));
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}

	public ResponseEnvelop getBonus(RequestEnvelop request) {
		ResponseEnvelop response = new ResponseEnvelop();
		Connection con = null;
		try{
			con = DBUtil.getConnection();
			DBUtil.beginTrans(con);
			String sql = "SELECT SUM(DECODE(AAA003,'earmodel',AAA005,NULL)) AS earmodel, " +
					"  SUM(DECODE(AAA003,'shell',AAA005,NULL))         AS shell, " +
					"  SUM(DECODE(AAA003,'shellsetupite',AAA005,NULL)) AS shellsetupite, " +
					"  SUM(DECODE(AAA003,'shellsetupcic',AAA005,NULL)) AS shellsetupcic, " +
					"  SUM(DECODE(AAA003,'repairmaster',AAA005,NULL))  AS repairmaster, " +
					"  SUM(DECODE(AAA003,'repairshell',AAA005,NULL))   AS repairshell, " +
					"  SUM(DECODE(AAA003,'shellsetup',AAA005,NULL))    AS shellsetup, " +
					"  SUM(DECODE(AAA003,'board',AAA005,NULL))         AS board, " +
					"  SUM(DECODE(AAA003,'ite',AAA005,NULL))           AS ite " +
					"FROM " +
					"  ( SELECT AAA003,AAA005 FROM aa01 WHERE AAA001 = 'BONUS' " +
					"  )";
			Statement pstmt = con.createStatement();
			ResultSet rst = null;
			rst = (ResultSet)pstmt.executeQuery(sql);
			String earmodel = null;
			String shell= null;
			String shellsetupite= null;
			String shellsetupcic= null ;
			String repairmaster= null;
			String repairshell= null;
			String shellsetup= null;
			String board= null;
			String ite = null;
			while(rst.next()){
				earmodel = rst.getString(1);
				shell = rst.getString(2);
				shellsetupite = rst.getString(3);
				shellsetupcic = rst.getString(4);
				repairmaster = rst.getString(5);
				repairshell = rst.getString(6);
				shellsetup = rst.getString(7);
				board = rst.getString(8);
				ite = rst.getString(9);
			}
			DBUtil.commit(con);
			HashMap<String, String> retmap = new HashMap<String, String>();
			retmap.put("earmodel", earmodel);
			retmap.put("shell", shell);
			retmap.put("shellsetupite", shellsetupite);
			retmap.put("shellsetupcic", shellsetupcic);
			retmap.put("repairmaster", repairmaster);
			retmap.put("repairshell", repairshell);
			retmap.put("shellsetup", shellsetup);
			retmap.put("board", board);
			retmap.put("ite", ite);
			response.setBody(retmap);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoConnectionException e) {
			e.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		return response;
	}
}
