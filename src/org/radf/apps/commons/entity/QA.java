package org.radf.apps.commons.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

//质检记录表
public class QA extends EntitySupport {
	private BigDecimal qaid; // 质检流水号
	private String qafno; // 对应订单号
	private String qasid; // 对应机身编号
	private String qapid; // 对应产品代码
	private String qatype; // 质检类型(定制机、维修机)
	private String qastatus; // 质检状态
	private String qatest1; // 外观
	private Double qatest2; // 饱和声输出
	private Double qatest3; // 最大值增益
	private Double qatest4; // 1600Hz增益
	private String qatest5; // 频率响应范围
	private Double qatest6; // 等效输入噪声
	private Double qatest7; // 电池电流
	private Double qatest8; // 总谐波失真(500Hz)
	private Double qatest9; // 总谐波失真(800Hz)
	private Double qatest10; // 总谐波失真(1600Hz)
	private String qachk; // 质检结果
	private String qachkopr; // 质检员代码
	private Date qachkdt; // 质检日期
	private String qachkopr2; // 质检员代码
	private Date qachkdt2; // 质检日期
	private String qacltnm; // 病人姓名
	
	//2012-2-25增加质量检测2
	private Double qatest11;//高频平均值
	private String qatestft;//频率响应范围2
	
	// 商品表
	private String qapnm; // 产品名称
	private String pdtcls;//耳机类别
    private String pdttype;//商品品牌类别
	
    //质检
   private Date start;
   private Date end;
	

	

	
	public Date getStart() {
	return start;
}

public void setStart(Date start) {
	this.start = start;
}

public Date getEnd() {
	return end;
}

public void setEnd(Date end) {
	this.end = end;
}

	public String getQachkopr2() {
		return qachkopr2;
	}

	public void setQachkopr2(String qachkopr2) {
		this.qachkopr2 = qachkopr2;
	}

	public Date getQachkdt2() {
		return qachkdt2;
	}

	public void setQachkdt2(Date qachkdt2) {
		this.qachkdt2 = qachkdt2;
	}

	public String getPdttype()
	{
		return pdttype;
	}
	
	public void setPdttype(String pdttype)
	{
		this.pdttype=pdttype;
	}

	public BigDecimal getQaid() {
		return qaid;
	}

	public void setQaid(BigDecimal qaid) {
		this.qaid = qaid;
	}

	public String getQafno() {
		return qafno;
	}

	public void setQafno(String qafno) {
		this.qafno = qafno;
	}

	public String getQasid() {
		return qasid;
	}

	public void setQasid(String qasid) {
		this.qasid = qasid;
	}

	public String getQapid() {
		return qapid;
	}

	public void setQapid(String qapid) {
		this.qapid = qapid;
	}

	public String getQatype() {
		return qatype;
	}

	public void setQatype(String qatype) {
		this.qatype = qatype;
	}

	public String getQatest1() {
		return qatest1;
	}

	public void setQatest1(String qatest1) {
		this.qatest1 = qatest1;
	}

	public Double getQatest2() {
		return qatest2;
	}

	public void setQatest2(Double qatest2) {
		this.qatest2 = qatest2;
	}

	public Double getQatest3() {
		return qatest3;
	}

	public void setQatest3(Double qatest3) {
		this.qatest3 = qatest3;
	}

	public Double getQatest4() {
		return qatest4;
	}

	public void setQatest4(Double qatest4) {
		this.qatest4 = qatest4;
	}

	public String getQatest5() {
		return qatest5;
	}

	public void setQatest5(String qatest5) {
		this.qatest5 = qatest5;
	}

	public Double getQatest6() {
		return qatest6;
	}

	public void setQatest6(Double qatest6) {
		this.qatest6 = qatest6;
	}

	public Double getQatest7() {
		return qatest7;
	}

	public void setQatest7(Double qatest7) {
		this.qatest7 = qatest7;
	}

	public Double getQatest8() {
		return qatest8;
	}

	public void setQatest8(Double qatest8) {
		this.qatest8 = qatest8;
	}

	public Double getQatest9() {
		return qatest9;
	}

	public void setQatest9(Double qatest9) {
		this.qatest9 = qatest9;
	}

	public Double getQatest10() {
		return qatest10;
	}

	public void setQatest10(Double qatest10) {
		this.qatest10 = qatest10;
	}

	public String getQachk() {
		return qachk;
	}

	public void setQachk(String qachk) {
		this.qachk = qachk;
	}

	public String getQachkopr() {
		return qachkopr;
	}

	public void setQachkopr(String qachkopr) {
		this.qachkopr = qachkopr;
	}

	public Date getQachkdt() {
		return qachkdt;
	}

	public void setQachkdt(Date qachkdt) {
		this.qachkdt = qachkdt;
	}

	public String getQastatus() {
		return qastatus;
	}

	public void setQastatus(String qastatus) {
		this.qastatus = qastatus;
	}

	public String getQacltnm() {
		return qacltnm;
	}

	public void setQacltnm(String qacltnm) {
		this.qacltnm = qacltnm;
	}

	public Double getQatest11() {
		return qatest11;
	}

	public void setQatest11(Double qatest11) {
		this.qatest11 = qatest11;
	}

	public String getQatestft() {
		return qatestft;
	}

	public void setQatestft(String qatestft) {
		this.qatestft = qatestft;
	}

	public String getQapnm() {
		return qapnm;
	}

	public void setQapnm(String qapnm) {
		this.qapnm = qapnm;
	}

	public String getPdtcls() {
		return pdtcls;
	}

	public void setPdtcls(String pdtcls) {
		this.pdtcls = pdtcls;
	}
	
	
}
