package com.cm.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.client.group.facade.GroupClientFacade;
import org.radf.apps.client.group.form.GroupClientForm;
import org.radf.apps.commons.entity.Customization;
import org.radf.apps.commons.entity.GroupClient;
import org.radf.apps.commons.entity.QA;
import org.radf.apps.commons.entity.Repair;
import org.radf.apps.customization.facade.CustomizationFacade;
import org.radf.apps.customization.form.CustomizationForm;
import org.radf.apps.qa.facade.QAFacade;
import org.radf.apps.qa.form.QAForm;
import org.radf.apps.repair.facade.RepairFacade;
import org.radf.apps.repair.form.RepairForm;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.DateUtil;
import org.radf.plat.commons.FindLog;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.log.LogHelper;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class initCusService {
         public String initCustomer (String id) {
        	 Connection conn = null;
        	 String qatestft = null;
        	 String userinfo=null;
     		try {
     			conn = DBUtil.getConnection();
     			DBUtil.beginTrans(conn);
     			String sql = "SELECT i.ictid, i.ictnm, i.ictpnm, i.ictgender, i.ictbdt, i.ictaddr, i.ictpcd, i.icttel, i.ictage, i.ictpro, i.ictcity, i.ictcounty, i.ictphone from tblindclient i where i.ictgctid='" + id + "'";
     			List result = (Vector) DBUtil.querySQL(conn, sql);
     			List<LinkedHashMap<String, String>> userlist = new ArrayList<LinkedHashMap<String, String>>();
     			if (result.size() >= 1) {
/*     				String a=result.get(0).toString();
     				qatestft = ((HashMap) result.get(0)).get("1").toString();*/
//     				for(HashMap<K, V>)
     				for(int i=0;i<result.size();i++)
     				{
     					
     					LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
     					/*for(int j=1;j<13;j++){
     						String key=String.valueOf(j);
     						key=((HashMap)result.get(i)).get(key).toString();
     						map.put("ictid", result.get(i).get())*/
     						
     					
     					if(((HashMap)result.get(i)).get("1")==null){
     						map.put("ictid", "");
     					}
     					else {
     						map.put("ictid", ((HashMap)result.get(i)).get("1").toString());
						}
     					if(((HashMap)result.get(i)).get("2")==null){
     						map.put("ictnm", "");
     					}
     					else {
     						map.put("ictnm", ((HashMap)result.get(i)).get("2").toString());
						}
     					if(((HashMap)result.get(i)).get("3")==null){
     						map.put("ictpnm", "");
     					}
     					else {
     						map.put("ictpnm", ((HashMap)result.get(i)).get("3").toString());
						}
     					if(((HashMap)result.get(i)).get("4")==null){
     						map.put("ictgender", "");
     					}
     					else {
     						map.put("ictgender", ((HashMap)result.get(i)).get("4").toString());
						}
     					if(((HashMap)result.get(i)).get("5")==null){
     						map.put("ictbdt", "");
     					}
     					else {
     						map.put("ictbdt", ((HashMap)result.get(i)).get("5").toString());
						}
     					if(((HashMap)result.get(i)).get("6")==null){
     						map.put("ictaddr", "");
     					}
     					else {
     						map.put("ictaddr", ((HashMap)result.get(i)).get("6").toString());
						}
     					if(((HashMap)result.get(i)).get("7")==null){
     						map.put("ictpcd", "");
     					}
     					else {
     						map.put("ictpcd", ((HashMap)result.get(i)).get("7").toString());
						}
     					if(((HashMap)result.get(i)).get("8")==null){
     						map.put("icttel", "");
     					}
     					else {
     						map.put("icttel", ((HashMap)result.get(i)).get("8").toString());
						}
     					if(((HashMap)result.get(i)).get("9")==null){
     						map.put("ictage", "");
     					}
     					else {
     						map.put("ictage", ((HashMap)result.get(i)).get("9").toString());
						}
     					if(((HashMap)result.get(i)).get("10")==null){
     						map.put("ictpro", "");
     					}
     					else {
     						map.put("ictpro", ((HashMap)result.get(i)).get("10").toString());
						}
     					if(((HashMap)result.get(i)).get("11")==null){
     						map.put("ictcity", "");
     					}
     					else {
     						map.put("ictcity", ((HashMap)result.get(i)).get("11").toString());
						}
     					if(((HashMap)result.get(i)).get("12")==null){
     						map.put("ictcountry", "");
     					}
     					else {
     						map.put("ictcountry", ((HashMap)result.get(i)).get("12").toString());
						}
     					if(((HashMap)result.get(i)).get("13")==null){
     						map.put("ictphone", "");
     					}
     					else {
     						map.put("ictphone", ((HashMap)result.get(i)).get("13").toString());
						}
                       map.toString();
                       userlist.add(map);
               		  
     				}
     				 JSONArray jsonstr=JSONArray.fromObject(userlist);
             		   userinfo=jsonstr.toString();
     			}
     			else {
					userinfo="没有此门店的用户信息";
				}
     		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			DBUtil.closeConnection(conn);
    		}
        	 return userinfo;
		}
        public String queryUserById (String userid){
        	 Connection conn = null;
        	 String qatestft = null;
        	 String userinfo=null;
     		try {
     			conn = DBUtil.getConnection();
     			DBUtil.beginTrans(conn);
     			String sql = "SELECT i.ictid, i.ictnm, i.ictpnm, i.ictgender, i.ictbdt, i.ictaddr, i.ictpcd, i.icttel, i.ictage, i.ictpro, i.ictcity, i.ictcounty, i.ictphone from tblindclient i where i.ictid='" + userid + "'";
     			List result = (Vector) DBUtil.querySQL(conn, sql);
     			List<LinkedHashMap<String, String>> userlist = new ArrayList<LinkedHashMap<String, String>>();
     			if (result.size() >= 1) {
/*     				String a=result.get(0).toString();
     				qatestft = ((HashMap) result.get(0)).get("1").toString();*/
//     				for(HashMap<K, V>)
     				for(int i=0;i<result.size();i++)
     				{
     					
     					LinkedHashMap<String, String> map=new LinkedHashMap<String, String>();
     					/*for(int j=1;j<13;j++){
     						String key=String.valueOf(j);
     						key=((HashMap)result.get(i)).get(key).toString();
     						map.put("ictid", result.get(i).get())*/
     						
     					
     					if(((HashMap)result.get(i)).get("1")==null){
     						map.put("ictid", "");
     					}
     					else {
     						map.put("ictid", ((HashMap)result.get(i)).get("1").toString());
						}
     					if(((HashMap)result.get(i)).get("2")==null){
     						map.put("ictnm", "");
     					}
     					else {
     						map.put("ictnm", ((HashMap)result.get(i)).get("2").toString());
						}
     					if(((HashMap)result.get(i)).get("3")==null){
     						map.put("ictpnm", "");
     					}
     					else {
     						map.put("ictpnm", ((HashMap)result.get(i)).get("3").toString());
						}
     					if(((HashMap)result.get(i)).get("4")==null){
     						map.put("ictgender", "");
     					}
     					else {
     						map.put("ictgender", ((HashMap)result.get(i)).get("4").toString());
						}
     					if(((HashMap)result.get(i)).get("5")==null){
     						map.put("ictbdt", "");
     					}
     					else {
     						map.put("ictbdt", ((HashMap)result.get(i)).get("5").toString());
						}
     					if(((HashMap)result.get(i)).get("6")==null){
     						map.put("ictaddr", "");
     					}
     					else {
     						map.put("ictaddr", ((HashMap)result.get(i)).get("6").toString());
						}
     					if(((HashMap)result.get(i)).get("7")==null){
     						map.put("ictpcd", "");
     					}
     					else {
     						map.put("ictpcd", ((HashMap)result.get(i)).get("7").toString());
						}
     					if(((HashMap)result.get(i)).get("8")==null){
     						map.put("icttel", "");
     					}
     					else {
     						map.put("icttel", ((HashMap)result.get(i)).get("8").toString());
						}
     					if(((HashMap)result.get(i)).get("9")==null){
     						map.put("ictage", "");
     					}
     					else {
     						map.put("ictage", ((HashMap)result.get(i)).get("9").toString());
						}
     					if(((HashMap)result.get(i)).get("10")==null){
     						map.put("ictpro", "");
     					}
     					else {
     						map.put("ictpro", ((HashMap)result.get(i)).get("10").toString());
						}
     					if(((HashMap)result.get(i)).get("11")==null){
     						map.put("ictcity", "");
     					}
     					else {
     						map.put("ictcity", ((HashMap)result.get(i)).get("11").toString());
						}
     					if(((HashMap)result.get(i)).get("12")==null){
     						map.put("ictcountry", "");
     					}
     					else {
     						map.put("ictcountry", ((HashMap)result.get(i)).get("12").toString());
						}
     					if(((HashMap)result.get(i)).get("13")==null){
     						map.put("ictphone", "");
     					}
     					else {
     						map.put("ictphone", ((HashMap)result.get(i)).get("13").toString());
						}
                       map.toString();
                       userlist.add(map);
     				}
     				 JSONArray jsonstr=JSONArray.fromObject(userlist);
             		   userinfo=jsonstr.toString();
     			}
     			else {
					userinfo="没有此客户的信息";
				}
     		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			DBUtil.closeConnection(conn);
    		}
        	 return userinfo;
        }
        public static void main(String[] args) {
			initCusService init=new initCusService();
//			String qa=init.initCustomer("B0024");
			String userinfo=init.queryUserById("063972");
			System.out.println(userinfo);
		}
}
