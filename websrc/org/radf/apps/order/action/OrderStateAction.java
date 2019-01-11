package org.radf.apps.order.action;

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
import org.radf.apps.order.facade.OrderFacade;
import org.radf.apps.order.form.OrderHeaderForm;
import org.radf.apps.order.form.OrderStateForm;
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


public class OrderStateAction extends ActionLeafSupport {
	
/**
 * 订单状态查询
 */
public ActionForward stateQuery(ActionMapping mapping, ActionForm form,
		HttpServletRequest req, HttpServletResponse res) throws Exception {
	OrderStateForm sForm = (OrderStateForm)form;
	String forward = "/order/statequery.jsp";
	String fileKey ="sta01_000";
	
	System.out.println("StateAction.java ");
	
	ActionForward af = new ActionForward();
	OrderState os = new OrderState();
	try {
		ClassHelper.copyProperties(sForm, os);
		os.setFileKey(fileKey);
		String hql = queryEnterprise(os);
		af = super.init(req, forward, hql);
		// 检查是否存在？
		if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
			String msg = "没有查询到符合条件的记录！";
			super.saveSuccessfulMsg(req, msg);
		}
	} catch (AppException ex) {
		this.saveErrors(req, ex);
	} catch (Exception e) {
		this.saveErrors(req, e);
	}
	return af;
}

}

