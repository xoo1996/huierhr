package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

public class AccUser extends EntitySupport{
		//�¼�id
		private String useremployid;
		//userid���󶨵��˺�
		private String account;
		

		
		public String getUseremployid() {
			return useremployid;
		}
		public void setUseremployid(String useremployid) {
			this.useremployid = useremployid;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
}
