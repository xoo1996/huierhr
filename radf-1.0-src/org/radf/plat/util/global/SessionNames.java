package org.radf.plat.util.global;

/**
 * 登陆用户的基本信息类
 * 保存在用户登录的会话中
 * @author zqb
 * @version 1.0
 */

public interface SessionNames {

	String PWD = "pwd";
	String USER_ID = "userID";
    String LOGIN_NAME = "loginName";
    String USER_NAME = "operatorName";
    String USER_AAB034 = "aab034";
    String USER_DEPTID = "deptid";
	String CERT = "ClientCert";
    String IP = "ip";
    
    String HEAD = "head";
    
    
    
    String FUNCTION_LIST = "functionList";
    String VERSION_LIST = "versionList";    //此信息不保存，使用后立即释放

}
