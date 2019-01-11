package org.radf.login.dto;


import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class LoginDTO extends EntitySupport{
	private String bsc010;//	  用户内码（公司）  
	private String bsc011;//	  用户代码（公司）  
	private String bsc012;//	  用户名称（公司）
	private String bsc013;//	  密码  
	private String aae005;//	  联系电话  
	private String bsc001;//	  机构内码  
	private String bsc008;//	  科室内码  
	private String aae100;//	  有效标记  
	private Date   aae036;//  操作时间  
	private String aae011;//	  操作人  
	private String aab003;//	  机构编码      
	private String aab300;//	  机构名称      
	private String aae006;//	  机构地址          
	private String aab304;//	  联系人        
	private String aae007;//	  邮编          
	private String bsc002;//	  机构简称
	private String bsc009;//	  科室名称
	private String bsc998;//	  父id
	private String ukeysn;//     Ukey序列号
	private String clientResult; // 客户端计算结果
	private String rand;           //随机数
	private String aae101;//是否启用
	private String notinumber;//通知数量
	private String pronumber;//通知待审批流程数量
	private String bsc014;//角色
	
	
	
	public String getBsc014() {
		return bsc014;
	}
	public void setBsc014(String bsc014) {
		this.bsc014 = bsc014;
	}
	public String getPronumber() {
		return pronumber;
	}
	public void setPronumber(String pronumber) {
		this.pronumber = pronumber;
	}
	public String getNotinumber() {
		return notinumber;
	}
	public void setNotinumber(String notinumber) {
		this.notinumber = notinumber;
	}
	public String getAae101() {
		return aae101;
	}
	public void setAae101(String aae101) {
		this.aae101 = aae101;
	}
	public String getUkeysn() {
		return ukeysn;
	}
	public void setUkeysn(String ukeysn) {
		this.ukeysn = ukeysn;
	}
	public String getClientResult() {
		return clientResult;
	}
	public void setClientResult(String clientResult) {
		this.clientResult = clientResult;
	}
	public String getRand() {
		return rand;
	}
	public void setRand(String rand) {
		this.rand = rand;
	}
	public String getBsc998() {
		return bsc998;
	}
	public void setBsc998(String bsc998) {
		this.bsc998 = bsc998;
	}
	public String getAab003() {
		return aab003;
	}
	public void setAab003(String aab003) {
		this.aab003 = aab003;
	}
	public String getAab300() {
		return aab300;
	}
	public void setAab300(String aab300) {
		this.aab300 = aab300;
	}
	public String getAab304() {
		return aab304;
	}
	public void setAab304(String aab304) {
		this.aab304 = aab304;
	}
	public String getAae005() {
		return aae005;
	}
	public void setAae005(String aae005) {
		this.aae005 = aae005;
	}
	public String getAae006() {
		return aae006;
	}
	public void setAae006(String aae006) {
		this.aae006 = aae006;
	}
	public String getAae007() {
		return aae007;
	}
	public void setAae007(String aae007) {
		this.aae007 = aae007;
	}
	public String getAae011() {
		return aae011;
	}
	public void setAae011(String aae011) {
		this.aae011 = aae011;
	}
	public Date getAae036() {
		return aae036;
	}
	public void setAae036(Date aae036) {
		this.aae036 = aae036;
	}
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	public String getBsc001() {
		return bsc001;
	}
	public void setBsc001(String bsc001) {
		this.bsc001 = bsc001;
	}
	public String getBsc002() {
		return bsc002;
	}
	public void setBsc002(String bsc002) {
		this.bsc002 = bsc002;
	}
	public String getBsc008() {
		return bsc008;
	}
	public void setBsc008(String bsc008) {
		this.bsc008 = bsc008;
	}
	public String getBsc009() {
		return bsc009;
	}
	public void setBsc009(String bsc009) {
		this.bsc009 = bsc009;
	}
	public String getBsc010() {
		return bsc010;
	}
	public void setBsc010(String bsc010) {
		this.bsc010 = bsc010;
	}
	public String getBsc011() {
		return bsc011;
	}
	public void setBsc011(String bsc011) {
		this.bsc011 = bsc011;
	}
	public String getBsc012() {
		return bsc012;
	}
	public void setBsc012(String bsc012) {
		this.bsc012 = bsc012;
	}
	public String getBsc013() {
		return bsc013;
	}
	public void setBsc013(String bsc013) {
		this.bsc013 = bsc013;
	}
	
}
