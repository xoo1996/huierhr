package org.radf.plat.sieaf.event;

/**
 * <p>Title: Event</p>
 * <p>Description: this class represents the input parameter of all <code>EJBAction</code></p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co.,Ltd</p>
 * <p>Company: LBS</p>
 * @author LBS Architect Team
 * @version 0.1
 */
import java.io.Serializable;

public class Event implements Serializable{

    private java.util.HashMap body; //
    private String loginName;       //用户登录名
    private String DEPTID;          //单位编号
    private String functionID;      //交易ID
    private String productType;     //add at 2002-1-7
    private String XQBM;            //
    private String XZBM;            //
    private String CBM;             //
    
    public Event() {
    }

    public java.util.HashMap getBody() {
        return body;
    }

    public void setBody(java.util.HashMap body) {
        this.body = body;
    }

    /**
     * Returns the functionID.
     * 
     * @return String
     */
    public String getFunctionID() {
        return functionID;
    }

    /**
     * Returns the userID.
     * 
     * @return String
     */
    public String getLoginName() {
        return loginName;
    }

    public String getXQBM() {
        return XQBM;
    }

    public String getXZBM() {
        return XZBM;
    }

    public String getCBM() {
        return CBM;
    }

    public String getDEPTID() {
        return DEPTID;
    }

    public void setXQBM(String str) {
        this.XQBM = str;
    }

    public void setXZBM(String str) {
        this.XZBM = str;
    }

    public void setCBM(String str) {
        this.CBM = str;
    }

    public void setDEPTID(String str) {
        this.DEPTID = str;
    }

    /**
     * Sets the functionID.
     * 
     * @param functionID
     *            The functionID to set
     */
    public void setFunctionID(String functionID) {
        this.functionID = functionID;
    }

    /**
     * Sets the userID.
     * 
     * @param userID
     *            The userID to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    /**
     * 此方法没有实际作用，只是为了兼容原来已有程序保留
     * @param loginName
     */
    public void setUserID(String loginName){
        setLoginName(loginName);
    }
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
