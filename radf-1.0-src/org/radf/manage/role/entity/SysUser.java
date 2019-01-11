
package org.radf.manage.role.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
* ����Ա������Ϣ
* @author zqb
* @version 1.0
 */
public class SysUser extends EntitySupport {

	private String userID;         //�û�����
	private String passWD;          //�û�����
	private String loginName;       //��½��
	private String operatorName;    //����������
    private String type;            //�û����ͣ�Ĭ��"IU"
	private String deptID;          //�����������
    private String aab034;          //�����籣����
    private String certName;        //֤������
    private String XQBM;            //Ͻ��
    private String XZBM;            //����
    private String CBM;             //?
    private String DESCRIPTION;     //�û�����
    //����Ϊzqb�¼��룺
    private String isleader;        //�Ƿ����쵼
    private String pinid;           //���֤���� 

    public SysUser(){
    }
    
    public SysUser(String userID){
        this.userID = userID;
    }


	/**
	 * @return String
	 */
	public String getDeptID() {
		return deptID;
	}

	/**
	 * @return String
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @return String
	 */
	public String getOperatorName() {
		return operatorName;
	}

	/**
	 * @return String
	 */
	public String getPassWD() {
		return passWD;
	}

	/**
	 * @return String
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Sets the deptID.
	 * @param deptID The deptID to set
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * Sets the loginName.
	 * @param loginName The loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * Sets the operatorName.
	 * @param operatorName The operatorName to set
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	/**
	 * Sets the passWD.
	 * @param passWD The passWD to set
	 */
	public void setPassWD(String passWD) {
		this.passWD = passWD;
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
    public String getDESCRIPTION() {
            return DESCRIPTION;
    }

    public void setXQBM(String string) {
            this.XQBM = string;
    }
    public void setXZBM(String string) {
            this.XZBM = string;
    }
    public void setCBM(String string) {
            this.CBM = string;
    }
    public void setDESCRIPTION(String string) {
            this.DESCRIPTION = string;
    }

        
        
	/**
	 * Sets the userID.
	 * @param userID The userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
    public String getCertName() {
        return certName;
    }
    public void setCertName(String certName) {
        this.certName = certName;
    }


	/**
	 * @return Returns the isleader.
	 */
	public String getIsleader() {
		return isleader;
	}
	/**
	 * @param isleader The isleader to set.
	 */
	public void setIsleader(String isleader) {
		this.isleader = isleader;
	}
	/**
	 * @return Returns the pinid.
	 */
	public String getPinid() {
		return pinid;
	}
	/**
	 * @param pinid The pinid to set.
	 */
	public void setPinid(String pinid) {
		this.pinid = pinid;
	}
	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

    /**
     * @return Returns the aab034.
     */
    public String getAab034() {
        return aab034;
    }

    /**
     * @param aab034 The aab034 to set.
     */
    public void setAab034(String aab034) {
        this.aab034 = aab034;
    }

    
}



