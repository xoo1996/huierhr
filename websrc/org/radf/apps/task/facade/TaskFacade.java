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
 * 面板制作
 */
public interface TaskFacade  {

	/**
	 * 面板零件配置
	 */
	public ResponseEnvelop configure(RequestEnvelop request);
	
	/**
	 * 任务单修改页面显示
	 */
	public ResponseEnvelop find(RequestEnvelop request);
	
	/**
	 * 任务单修改
	 */
	public ResponseEnvelop modify(RequestEnvelop request);
	
	/**
	 * 任务单删除
	 */
	public ResponseEnvelop delete(RequestEnvelop request);
	
	/**
	 * 任务单配件确认
	 */
	public ResponseEnvelop confirm(RequestEnvelop request);
	
	
	public ResponseEnvelop batchStorere(RequestEnvelop request);
	
	/**

	/**
	 * 面板生产
	 */
	public ResponseEnvelop produce(RequestEnvelop request);
	
	
	/**
	 * 面板完成确认
	 */
	public ResponseEnvelop finish(RequestEnvelop request);
	
	/**
	 * 批量保存生产信息
	 */
	public ResponseEnvelop batchSave(RequestEnvelop request);
	
	
	/**
	 * 批量保存质检信息
	 */
	public ResponseEnvelop batchQA(RequestEnvelop request);
	
	/**
	 * 批量修改质检信息
	 */
	public ResponseEnvelop batchQaUpdate(RequestEnvelop request);
	
	
	/**
	 * 批量入库
	 */
	public ResponseEnvelop batchStore(RequestEnvelop request);
	
	/**
	 * 面板开始质检信息保存
	 */
	public ResponseEnvelop saveQA(RequestEnvelop request);
	
	/**
	 * 面板完成质检信息保存
	 */
	public ResponseEnvelop saveQAF(RequestEnvelop request);
	
	/**
	 * 退机面板质检信息保存
	 */
	public ResponseEnvelop saveRecoilQA(RequestEnvelop request);
	
	/**
	 * 入库信息保存
	 */
	public ResponseEnvelop store(RequestEnvelop request);
	/**
	 * 得到奖金单价
	 */
	public ResponseEnvelop getBonus(RequestEnvelop requestEnvelop);

}
