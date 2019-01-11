package org.radf.apps.commons.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class Sale extends EntitySupport {

		private String mgctid;//客户代码
		private Integer myear;//年
		private Integer mmonth;//月
		private Double mad; // 广告费
		private Double mback;// 返利
		private Double msales;// 本月销售
		private Double mpamnt;//实际回款
		private Double marrears;//本月欠款
		private Double mothers;//其他费用
		private String mnote;//备注
		private Double mnsales;//不计销售
		
		private Date mopdt;//时间
		private String mop;//操作人员
		public String getMgctid() {
			return mgctid;
		}
		public void setMgctid(String mgctid) {
			this.mgctid = mgctid;
		}
		public Integer getMyear() {
			return myear;
		}
		public void setMyear(Integer myear) {
			this.myear = myear;
		}
		public Integer getMmonth() {
			return mmonth;
		}
		public void setMmonth(Integer mmonth) {
			this.mmonth = mmonth;
		}
		public Double getMad() {
			return mad;
		}
		public void setMad(Double mad) {
			this.mad = mad;
		}
		public Double getMback() {
			return mback;
		}
		public void setMback(Double mback) {
			this.mback = mback;
		}
		public Double getMsales() {
			return msales;
		}
		public void setMsales(Double msales) {
			this.msales = msales;
		}
		public Double getMpamnt() {
			return mpamnt;
		}
		public void setMpamnt(Double mpamnt) {
			this.mpamnt = mpamnt;
		}
		public Double getMarrears() {
			return marrears;
		}
		public void setMarrears(Double marrears) {
			this.marrears = marrears;
		}
		public Double getMothers() {
			return mothers;
		}
		public void setMothers(Double mothers) {
			this.mothers = mothers;
		}
		public String getMnote() {
			return mnote;
		}
		public void setMnote(String mnote) {
			this.mnote = mnote;
		}
		public Double getMnsales() {
			return mnsales;
		}
		public void setMnsales(Double mnsales) {
			this.mnsales = mnsales;
		}
		public Date getMopdt() {
			return mopdt;
		}
		public void setMopdt(Date mopdt) {
			this.mopdt = mopdt;
		}
		public String getMop() {
			return mop;
		}
		public void setMop(String mop) {
			this.mop = mop;
		}
		
}
