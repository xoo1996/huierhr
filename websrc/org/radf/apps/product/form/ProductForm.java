package org.radf.apps.product.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class ProductForm extends ActionForm {
	private String pdtid; // 商品（耳机）代码
	private String pdtnm; // 商品（耳机）名称
	private String pdttype; // 产品分类
	private String pdtcls; // 商品（耳机）分类(耳内式:ITE;耳道式:ITC;完全耳道式:CIC;耳背式:BTE)
	private String pdtut; // 商品（耳机）单位
	private Double pdtprc;// //商品（耳机）单价
	private Double pdtdiscount;//商品扣率
	private String pdtnt; // 备注
	private String pdtisd;//是否添加扣率
	private String pdtclid; //商品类别代码
	private String pdtp;//产品类别
	private String pdtclnm;//商品类别名称
	private String pdtmod;//零件型号
	private String pdtispnl;//是否为面板零件
	
	
	private String pclid;
	private String pcllarge;
	private String pclmid;
	private String pclsmall;
	private String pdtminsto;//最小库存量
	private Integer pdtlmt;//保修年限（月）

	private String dispdtid; //产品代码
	private String disgctid;//客户代码
	private Double disprice;//打折后的价格
	private Double discount;//扣率
	private String disnote;//备注
	private String disop;//操作人员
	private Date   disoptime;//操作时间
	
	private String disgctnm;//客户名称
	private String gcttype;
	
	/**
	 * 面板零件字段
	 * @return
	 */
	private String acyid; //零件代码
	private String acystoid; //库存编号
	private String acypdtid; //零件货号
	private String acypdtnm; //零件名称
	private String acytyp; //零件型号
	private String acybthnum; //零件批号
	private String acynt; //备注
	
	public Double getPdtdiscount() {
		return pdtdiscount;
	}

	public void setPdtdiscount(Double pdtdiscount) {
		this.pdtdiscount = pdtdiscount;
	}

	public String getPdtid() {
		return pdtid;
	}

	public void setPdtid(String pdtid) {
		this.pdtid = pdtid;
	}

	public String getPdtnm() {
		return pdtnm;
	}

	public void setPdtnm(String pdtnm) {
		this.pdtnm = pdtnm;
	}

	public String getPdtcls() {
		return pdtcls;
	}

	public void setPdtcls(String pdtcls) {
		this.pdtcls = pdtcls;
	}

	public String getPdtut() {
		return pdtut;
	}

	public void setPdtut(String pdtut) {
		this.pdtut = pdtut;
	}

	public Double getPdtprc() {
		return pdtprc;
	}

	public void setPdtprc(Double pdtprc) {
		this.pdtprc = pdtprc;
	}

	public String getPdtnt() {
		return pdtnt;
	}

	public void setPdtnt(String pdtnt) {
		this.pdtnt = pdtnt;
	}

	public String getPdtisd() {
		return pdtisd;
	}

	public void setPdtisd(String pdtisd) {
		this.pdtisd = pdtisd;
	}

	public String getPdttype() {
		return pdttype;
	}

	public void setPdttype(String pdttype) {
		this.pdttype = pdttype;
	}

	public String getPdtp() {
		return pdtp;
	}

	public void setPdtp(String pdtp) {
		this.pdtp = pdtp;
	}

	public String getDispdtid() {
		return dispdtid;
	}

	public void setDispdtid(String dispdtid) {
		this.dispdtid = dispdtid;
	}

	public String getDisgctid() {
		return disgctid;
	}

	public void setDisgctid(String disgctid) {
		this.disgctid = disgctid;
	}

	public Double getDisprice() {
		return disprice;
	}

	public void setDisprice(Double disprice) {
		this.disprice = disprice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDisnote() {
		return disnote;
	}

	public void setDisnote(String disnote) {
		this.disnote = disnote;
	}

	public String getDisop() {
		return disop;
	}

	public void setDisop(String disop) {
		this.disop = disop;
	}

	public Date getDisoptime() {
		return disoptime;
	}

	public void setDisoptime(Date disoptime) {
		this.disoptime = disoptime;
	}

	public String getDisgctnm() {
		return disgctnm;
	}

	public void setDisgctnm(String disgctnm) {
		this.disgctnm = disgctnm;
	}

	public String getGcttype() {
		return gcttype;
	}

	public void setGcttype(String gcttype) {
		this.gcttype = gcttype;
	}

	public String getAcyid() {
		return acyid;
	}

	public void setAcyid(String acyid) {
		this.acyid = acyid;
	}

	public String getAcystoid() {
		return acystoid;
	}

	public void setAcystoid(String acystoid) {
		this.acystoid = acystoid;
	}

	public String getAcypdtid() {
		return acypdtid;
	}

	public void setAcypdtid(String acypdtid) {
		this.acypdtid = acypdtid;
	}

	public String getAcypdtnm() {
		return acypdtnm;
	}

	public void setAcypdtnm(String acypdtnm) {
		this.acypdtnm = acypdtnm;
	}

	public String getAcytyp() {
		return acytyp;
	}

	public void setAcytyp(String acytyp) {
		this.acytyp = acytyp;
	}

	public String getAcybthnum() {
		return acybthnum;
	}

	public void setAcybthnum(String acybthnum) {
		this.acybthnum = acybthnum;
	}

	public String getAcynt() {
		return acynt;
	}

	public void setAcynt(String acynt) {
		this.acynt = acynt;
	}

	public String getPdtclid() {
		return pdtclid;
	}

	public void setPdtclid(String pdtclid) {
		this.pdtclid = pdtclid;
	}

	public String getPdtclnm() {
		return pdtclnm;
	}

	public void setPdtclnm(String pdtclnm) {
		this.pdtclnm = pdtclnm;
	}

	public String getPclid() {
		return pclid;
	}

	public void setPclid(String pclid) {
		this.pclid = pclid;
	}

	public String getPcllarge() {
		return pcllarge;
	}

	public void setPcllarge(String pcllarge) {
		this.pcllarge = pcllarge;
	}

	public String getPclmid() {
		return pclmid;
	}

	public void setPclmid(String pclmid) {
		this.pclmid = pclmid;
	}

	public String getPclsmall() {
		return pclsmall;
	}

	public void setPclsmall(String pclsmall) {
		this.pclsmall = pclsmall;
	}

	public String getPdtminsto() {
		return pdtminsto;
	}

	public void setPdtminsto(String pdtminsto) {
		this.pdtminsto = pdtminsto;
	}

	public Integer getPdtlmt() {
		return pdtlmt;
	}

	public void setPdtlmt(Integer pdtlmt) {
		this.pdtlmt = pdtlmt;
	}

	public String getPdtmod() {
		return pdtmod;
	}

	public void setPdtmod(String pdtmod) {
		this.pdtmod = pdtmod;
	}

	public String getPdtispnl() {
		return pdtispnl;
	}

	public void setPdtispnl(String pdtispnl) {
		this.pdtispnl = pdtispnl;
	}
	
	
	
}
