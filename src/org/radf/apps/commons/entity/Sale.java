package org.radf.apps.commons.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class Sale extends EntitySupport {

		private String mgctid;//�ͻ�����
		private Integer myear;//��
		private Integer mmonth;//��
		private Double mad; // ����
		private Double mback;// ����
		private Double msales;// ��������
		private Double mpamnt;//ʵ�ʻؿ�
		private Double marrears;//����Ƿ��
		private Double mothers;//��������
		private String mnote;//��ע
		private Double mnsales;//��������
		
		private Date mopdt;//ʱ��
		private String mop;//������Ա
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
