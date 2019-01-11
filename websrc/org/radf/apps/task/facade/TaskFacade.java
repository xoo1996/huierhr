package org.radf.apps.task.facade;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.order.form.OrderStateForm;
import org.radf.apps.repair.facade.RepairFacade;
import org.radf.apps.repair.form.RepairForm;
import org.radf.login.dto.LoginDTO;
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
import org.radf.plat.util.global.GlobalNames;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

/**
 * �������
 */
public interface TaskFacade  {

	/**
	 * ����������
	 */
	public ResponseEnvelop configure(RequestEnvelop request);
	
	/**
	 * �����޸�ҳ����ʾ
	 */
	public ResponseEnvelop find(RequestEnvelop request);
	
	/**
	 * �����޸�
	 */
	public ResponseEnvelop modify(RequestEnvelop request);
	
	/**
	 * ����ɾ��
	 */
	public ResponseEnvelop delete(RequestEnvelop request);
	
	/**
	 * �������ȷ��
	 */
	public ResponseEnvelop confirm(RequestEnvelop request);
	
	
	public ResponseEnvelop batchStorere(RequestEnvelop request);
	
	/**

	/**
	 * �������
	 */
	public ResponseEnvelop produce(RequestEnvelop request);
	
	
	/**
	 * ������ȷ��
	 */
	public ResponseEnvelop finish(RequestEnvelop request);
	
	/**
	 * ��������������Ϣ
	 */
	public ResponseEnvelop batchSave(RequestEnvelop request);
	
	
	/**
	 * ���������ʼ���Ϣ
	 */
	public ResponseEnvelop batchQA(RequestEnvelop request);
	
	/**
	 * �����޸��ʼ���Ϣ
	 */
	public ResponseEnvelop batchQaUpdate(RequestEnvelop request);
	
	
	/**
	 * �������
	 */
	public ResponseEnvelop batchStore(RequestEnvelop request);
	
	/**
	 * ��忪ʼ�ʼ���Ϣ����
	 */
	public ResponseEnvelop saveQA(RequestEnvelop request);
	
	/**
	 * �������ʼ���Ϣ����
	 */
	public ResponseEnvelop saveQAF(RequestEnvelop request);
	
	/**
	 * �˻�����ʼ���Ϣ����
	 */
	public ResponseEnvelop saveRecoilQA(RequestEnvelop request);
	
	/**
	 * �����Ϣ����
	 */
	public ResponseEnvelop store(RequestEnvelop request);
	/**
	 * �õ����𵥼�
	 */
	public ResponseEnvelop getBonus(RequestEnvelop requestEnvelop);

}
