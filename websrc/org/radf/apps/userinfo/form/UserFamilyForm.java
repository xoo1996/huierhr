package org.radf.apps.userinfo.form;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class UserFamilyForm {


    private String familycall;

    private String familyworkplace;

    private String familyphoneno;

    private String familyname;

    private String useremployid;
    
 private Date familybirthday;
 private String familybirthdayStr;
 
 
    

    public String getFamilybirthdayStr() {
	return familybirthdayStr;
}

public void setFamilybirthdayStr(String familybirthdayStr) {
	this.familybirthdayStr = familybirthdayStr;
}



    public Date getFamilybirthday() {
	return familybirthday;
}

public void setFamilybirthday(Date familybirthday) {
	this.familybirthday = familybirthday;
}

	public String getFamilycall() {
        return familycall;
    }

    public void setFamilycall(String familycall) {
        this.familycall = familycall == null ? null : familycall.trim();
    }

    public String getFamilyworkplace() {
        return familyworkplace;
    }

    public void setFamilyworkplace(String familyworkplace) {
        this.familyworkplace = familyworkplace == null ? null : familyworkplace.trim();
    }

    public String getFamilyphoneno() {
        return familyphoneno;
    }

    public void setFamilyphoneno(String familyphoneno) {
        this.familyphoneno = familyphoneno == null ? null : familyphoneno.trim();
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname == null ? null : familyname.trim();
    }

    public String getUseremployid() {
        return useremployid;
    }

    public void setUseremployid(String useremployid) {
        this.useremployid = useremployid == null ? null : useremployid.trim();
    }

}
