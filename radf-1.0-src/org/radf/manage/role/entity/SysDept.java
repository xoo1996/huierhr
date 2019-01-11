package org.radf.manage.role.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
* ��֯������Ϣ��
* @author zqb
* @version 1.0
 */
public class SysDept extends EntitySupport {

	private String groupID;			//��ˮ�ţ�seq����
	private String deptID;			//��λ��ţ����ݿ�ORG�ֶΣ�Ҳ��Ψһ��
	private String deptName;		//��������
	private String deptPrivilege;	//��������deptID
	private String comments;		//������Ϣ
	private String districtCode;   //������������
	private String license;        //���֤���
	private String principal;      //����������
	private String shortname;      //�������
	private String address;        //��ַ
	private String tel;            //�绰
	private String linkman;        //��ϵ��
	private String type;           //�������
	private String chargedept;     //���ܲ���
	private String frontfordept;   //���첿��

    public SysDept(){
    }
    /**
     * @param deptID
     */
    public SysDept(String deptID){
        this.deptID = deptID;
    }
 
	/**
	 * @return String
	 */
	public String getComments() {
		return comments;
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
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @return String
	 */
	public String getDeptPrivilege() {
		return deptPrivilege;
	}

	/**
	 * Sets the comments.
	 * @param comments The comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Sets the deptID.
	 * @param deptID The deptID to set
	 */
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	/**
	 * Sets the deptName.
	 * @param deptName The deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * Sets the deptPrivilege.
	 * @param deptPrivilege The deptPrivilege to set
	 */
	public void setDeptPrivilege(String deptPrivilege) {
		this.deptPrivilege = deptPrivilege;
	}

	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return Returns the chargedept.
	 */
	public String getChargedept() {
		return chargedept;
	}
	/**
	 * @param chargedept The chargedept to set.
	 */
	public void setChargedept(String chargedept) {
		this.chargedept = chargedept;
	}
	/**
	 * @return Returns the districtCode.
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * @param districtCode The districtCode to set.
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * @return Returns the frontfordept.
	 */
	public String getFrontfordept() {
		return frontfordept;
	}
	/**
	 * @param frontfordept The frontfordept to set.
	 */
	public void setFrontfordept(String frontfordept) {
		this.frontfordept = frontfordept;
	}
	/**
	 * @return Returns the groupID.
	 */
	public String getGroupID() {
		return groupID;
	}
	/**
	 * @param groupID The groupID to set.
	 */
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	/**
	 * @return Returns the license.
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * @param license The license to set.
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	/**
	 * @return Returns the linkman.
	 */
	public String getLinkman() {
		return linkman;
	}
	/**
	 * @param linkman The linkman to set.
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	/**
	 * @return Returns the principal.
	 */
	public String getPrincipal() {
		return principal;
	}
	/**
	 * @param principal The principal to set.
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	/**
	 * @return Returns the shortname.
	 */
	public String getShortname() {
		return shortname;
	}
	/**
	 * @param shortname The shortname to set.
	 */
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	/**
	 * @return Returns the tel.
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel The tel to set.
	 */
	public void setTel(String tel) {
		this.tel = tel;
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
}



