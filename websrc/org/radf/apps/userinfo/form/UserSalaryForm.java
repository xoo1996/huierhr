package org.radf.apps.userinfo.form;

import org.apache.struts.action.ActionForm;

public class UserSalaryForm extends ActionForm {

		//年
		private String year;
		//月
	//工号
		private String useremployid;
		//手机号
		private String phonenumber;
		//惠耳点
		private String store;
		//序号
		private String indexx;
		//月
		private String month;
		//姓名
		private String name;
		//基本工资
		private Double jiben;
		//岗位工资
		private Double gangwei;
		//绩效工资
		private Double jixiao;
		//销售奖10
		private Double xiaoshou;
		//惠耳产品奖
		private Double chanpin;
		//特殊机型奖
		private Double teshu1;
		//节约奖
		private Double jieyue;
		//促销
		private Double cuxiao;
		//新合作点奖励
		private Double xinhezuo;
		//额外奖金
		private Double ewai;
		//特殊津贴
		private Double teshu2;
		//缺勤扣款
		private Double queqin;
		//其他扣款
		private Double qita;
		//工资调整20
		private Double tiaozheng;
		//应发工资
		private Double yingfa;
		//五险补贴
		private Double wxbt;
		//公积金补贴
		private Double gjj;
		//节日费
		private Double jieri;
		//五险扣款
		private Double wxkk;
		//公积金扣款
		private Double gkkjj;
		//实发工资
		private Double shifa;
		//农行
		private String nongye;
		//建行30
		private String jianshe;
		//计奖销售
		private Double jjxs;
		//实际销售额
		private Double sjxse;
		//惠耳产品销售额
		private Double hecpxse;
		//可控费用34
		private Double kkfy;
		
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getUseremployid() {
			return useremployid;
		}
		public void setUseremployid(String useremployid) {
			this.useremployid = useremployid;
		}
		public String getPhonenumber() {
			return phonenumber;
		}
		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}
		public String getStore() {
			return store;
		}
		public void setStore(String store) {
			this.store = store;
		}

		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getJiben() {
			return jiben;
		}
		public void setJiben(Double jiben) {
			this.jiben = jiben;
		}
		public Double getGangwei() {
			return gangwei;
		}
		public void setGangwei(Double gangwei) {
			this.gangwei = gangwei;
		}
		public Double getJixiao() {
			return jixiao;
		}
		public void setJixiao(Double jixiao) {
			this.jixiao = jixiao;
		}
		public Double getXiaoshou() {
			return xiaoshou;
		}
		public void setXiaoshou(Double xiaoshou) {
			this.xiaoshou = xiaoshou;
		}
		public Double getChanpin() {
			return chanpin;
		}
		public void setChanpin(Double chanpin) {
			this.chanpin = chanpin;
		}
		public Double getTeshu1() {
			return teshu1;
		}
		public void setTeshu1(Double teshu1) {
			this.teshu1 = teshu1;
		}
		public Double getJieyue() {
			return jieyue;
		}
		public void setJieyue(Double jieyue) {
			this.jieyue = jieyue;
		}
		public Double getCuxiao() {
			return cuxiao;
		}
		public void setCuxiao(Double cuxiao) {
			this.cuxiao = cuxiao;
		}
		
		public Double getEwai() {
			return ewai;
		}
		public void setEwai(Double ewai) {
			this.ewai = ewai;
		}
		public Double getTeshu2() {
			return teshu2;
		}
		public void setTeshu2(Double teshu2) {
			this.teshu2 = teshu2;
		}
		public Double getQueqin() {
			return queqin;
		}
		public void setQueqin(Double queqin) {
			this.queqin = queqin;
		}
		public Double getQita() {
			return qita;
		}
		public void setQita(Double qita) {
			this.qita = qita;
		}
		public Double getTiaozheng() {
			return tiaozheng;
		}
		public void setTiaozheng(Double tiaozheng) {
			this.tiaozheng = tiaozheng;
		}
		public Double getYingfa() {
			return yingfa;
		}
		public void setYingfa(Double yingfa) {
			this.yingfa = yingfa;
		}
		
		public String getIndexx() {
			return indexx;
		}
		public void setIndexx(String indexx) {
			this.indexx = indexx;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public Double getXinhezuo() {
			return xinhezuo;
		}
		public void setXinhezuo(Double xinhezuo) {
			this.xinhezuo = xinhezuo;
		}
		public Double getWxbt() {
			return wxbt;
		}
		public void setWxbt(Double wxbt) {
			this.wxbt = wxbt;
		}
		public Double getGjj() {
			return gjj;
		}
		public void setGjj(Double gjj) {
			this.gjj = gjj;
		}
		public Double getJieri() {
			return jieri;
		}
		public void setJieri(Double jieri) {
			this.jieri = jieri;
		}
		public Double getWxkk() {
			return wxkk;
		}
		public void setWxkk(Double wxkk) {
			this.wxkk = wxkk;
		}
		
		public Double getGkkjj() {
			return gkkjj;
		}
		public void setGkkjj(Double gkkjj) {
			this.gkkjj = gkkjj;
		}
		public Double getShifa() {
			return shifa;
		}
		public void setShifa(Double shifa) {
			this.shifa = shifa;
		}
	
		public String getNongye() {
			return nongye;
		}
		public void setNongye(String nongye) {
			this.nongye = nongye;
		}
		public String getJianshe() {
			return jianshe;
		}
		public void setJianshe(String jianshe) {
			this.jianshe = jianshe;
		}
		public Double getJjxs() {
			return jjxs;
		}
		public void setJjxs(Double jjxs) {
			this.jjxs = jjxs;
		}
	
		public Double getSjxse() {
			return sjxse;
		}
		public void setSjxse(Double sjxse) {
			this.sjxse = sjxse;
		}
		public Double getHecpxse() {
			return hecpxse;
		}
		public void setHecpxse(Double hecpxse) {
			this.hecpxse = hecpxse;
		}
		public Double getKkfy() {
			return kkfy;
		}
		public void setKkfy(Double kkfy) {
			this.kkfy = kkfy;
		}
}
