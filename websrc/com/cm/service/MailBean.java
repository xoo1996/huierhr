package com.cm.service;

import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailBean {
	static String sourceAdd="jyk0406@126.com";
	static String serveAdd="smtp.126.com";
	static int port=25;
	static String name="jyk0406";
	static String password="jyk199346";
	
//	public static void main(String args[]) throws Exception{
//		MailBean mb=new MailBean();
//		//mb.password="jyk";
//		mb.SendMail("jyk0406@126.com");
//	}
	
	public static void SendMail(String targetAdd) throws Exception{  
		try{
	        Properties props = new Properties();  
	        props.setProperty("mail.smtp.auth", "true");  
	        props.setProperty("mail.transport.protocol", "smtp");  
	          
	        Session session = Session.getInstance(props);  
	        session.setDebug(true);  
	          
	        Message msg = new MimeMessage(session);  
	        msg.setSubject("�ݶ�����ϵͳ");  
	        msg.setText("����!�ݶ�����ϵͳ����������������������뾡�촦��лл~");  
	        msg.setFrom(new InternetAddress(sourceAdd));  
	  
	        Transport transport = session.getTransport();  
	        transport.connect(serveAdd,port ,name , password);  
	        transport.sendMessage(msg,new Address[]{new InternetAddress(targetAdd)});  
	  
	        System.out.println("�ʼ����ͳɹ�...");  
	        transport.close();  
		}catch(Exception e){
			System.out.println("�˺��������");  
			throw new Exception(e.getMessage());
		}
    }  
}
