package org.radf.apps.commons.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class UserInfo extends EntitySupport{
	
	//��������
			private String bsc009;
	//�Ա�
		private String usergender;
		//����
		private String usernation;
		//��������
		private Date userbirthday;
		//���֤����
		private String useridno;
		//��ҵѧУ
		private String usergraduatefrom;
		//רҵ
		private String usermajor;
		//�ƶ��绰
		private String usermobilephone;
		//�̶��绰
		private String usertelephone;
		//Ա��id
		private String useremployid;
		//����
		private String username;
		//����
		private String userhometown;
		//���״��
		private String userismarry;
		//������ò
		private String userpolitical;
		//��ҵʱ��
		private Date usergraduatedate;
		//���ѧ��
		private String userheightestedu;
		//����
		private String userforeignlangtype;
		//����ˮƽ
		private String userforeignlanglevel;
		//����ְ��
		private String userrankname;
		//����ʱ��
		private Date userrankdate;
		//��ס��ַ
		private String userresidence;
		//��ס���ʱ�
		private String userpostcode1;
		//������ַ
		private String userhousehold;
		//�����ʱ�
		private String userpostcode2;
		//����
		private String useremail;
		//��ְʱ��
		private Date userjoindate;
		//Ա��״̬
		private String useremployeestatus;
		//����
		private String userbelong;
		//���θ�λ
		private String userpositionnow;
		//��������id
		private String userdepartmentid;
		//�Ͷ���ͬID
		
		private String usercontractid;
		//�����˺�
		private String userbankcardno;
		//��������
		private String userbanktype;
		//��������
		private String userbankplace;
		//��ע
		private String userremark;
		//��������
		private String userworkperformence;
		//�ϼ�id
		private String usersuperiorid;
		
		//��Ů����
		private String userchildno;
		
		//�����ˮƽ
		private String usercomputerlevel;
		
		//��������
		private String userhukoutype;
		//emeper  emergencyperson ������ϵ��
		//������ϵ��
		private String emepername; 
		//��ϵ
		private String emeperrelation;
		//��ϵ��ʽ
		private String emeperphone;
		//��ϵ��ַ
		private String emeperaddress;
		//���ʱ��
		private Date writedate;
		//����id
		private String departmentid;
		//��λid
		private String positionid;
	
		//��λ����
		private String positionname;
		//�Ƿ��������ڱ���˾����
		private String investigate1;
		//�Ƿ���������ҵǩ�����ܡ���ҵ����Э��
		private String investigate2;
		//�Ƿ���������λ��δ����������
		private String investigate3;
		//�Ƿ��ܽ��ܹ����ص������Ƶ������
		private String investigate4;
		//�Ƿ��������ְ
		private String investigate5;
		//��������
		private String jianshebank;
		//ũҵ����
		private String nongyebank;
		//������
		private String danganid;
		
		//�μӹ���ʱ��
		private Date userworktime;
		
		private String userworkold;//���ݿ��еĹ�������
		
		private String workage;//sql������Ĺ���
		
		private String departmentname;
		
		private String departmenttype;
		
		private String contrctstartdate;
		
		private String contracttime;
		private String contrctenddate;
		private String worktime;
		
		
		
		
		
		
		public String getWorkage() {
			return workage;
		}
		public void setWorkage(String workage) {
			this.workage = workage;
		}
		public String getDanganid() {
			return danganid;
		}
		public void setDanganid(String danganid) {
			this.danganid = danganid;
		}
		public String getJianshebank() {
			return jianshebank;
		}
		public void setJianshebank(String jianshebank) {
			this.jianshebank = jianshebank;
		}
		public String getNongyebank() {
			return nongyebank;
		}
		public void setNongyebank(String nongyebank) {
			this.nongyebank = nongyebank;
		}
		public String getUserchildno() {
			return userchildno;
		}
		public void setUserchildno(String userchildno) {
			this.userchildno = userchildno;
		}
		public String getUsercomputerlevel() {
			return usercomputerlevel;
		}
		public void setUsercomputerlevel(String usercomputerlevel) {
			this.usercomputerlevel = usercomputerlevel;
		}
		public String getUserhukoutype() {
			return userhukoutype;
		}
		public void setUserhukoutype(String userhukoutype) {
			this.userhukoutype = userhukoutype;
		}
		public String getEmepername() {
			return emepername;
		}
		public void setEmepername(String emepername) {
			this.emepername = emepername;
		}
		public String getEmeperrelation() {
			return emeperrelation;
		}
		public void setEmeperrelation(String emeperrelation) {
			this.emeperrelation = emeperrelation;
		}
		public String getEmeperphone() {
			return emeperphone;
		}
		public void setEmeperphone(String emeperphone) {
			this.emeperphone = emeperphone;
		}
		public String getEmeperaddress() {
			return emeperaddress;
		}
		public void setEmeperaddress(String emeperaddress) {
			this.emeperaddress = emeperaddress;
		}
		
		public Date getWritedate() {
			return writedate;
		}
		public void setWritedate(Date writedate) {
			this.writedate = writedate;
		}
		public String getDepartmentid() {
			return departmentid;
		}
		public void setDepartmentid(String departmentid) {
			this.departmentid = departmentid;
		}
		public String getPositionid() {
			return positionid;
		}
		public void setPositionid(String positionid) {
			this.positionid = positionid;
		}
		public String getPositionname() {
			return positionname;
		}
		public void setPositionname(String positionname) {
			this.positionname = positionname;
		}
		public String getInvestigate1() {
			return investigate1;
		}
		public void setInvestigate1(String investigate1) {
			this.investigate1 = investigate1;
		}
		public String getInvestigate2() {
			return investigate2;
		}
		public void setInvestigate2(String investigate2) {
			this.investigate2 = investigate2;
		}
		public String getInvestigate3() {
			return investigate3;
		}
		public void setInvestigate3(String investigate3) {
			this.investigate3 = investigate3;
		}
		public String getInvestigate4() {
			return investigate4;
		}
		public void setInvestigate4(String investigate4) {
			this.investigate4 = investigate4;
		}
		public String getInvestigate5() {
			return investigate5;
		}
		public void setInvestigate5(String investigate5) {
			this.investigate5 = investigate5;
		}
	
		public Date getUserworktime() {
			return userworktime;
		}
		public void setUserworktime(Date userworktime) {
			this.userworktime = userworktime;
		}
		public String getDepartmentname() {
			return departmentname;
		}
		public void setDepartmentname(String departmentname) {
			this.departmentname = departmentname;
		}
		public String getDepartmenttype() {
			return departmenttype;
		}
		public void setDepartmenttype(String departmenttype) {
			this.departmenttype = departmenttype;
		}
		public String getContrctstartdate() {
			return contrctstartdate;
		}
		public void setContrctstartdate(String contrctstartdate) {
			this.contrctstartdate = contrctstartdate;
		}
		public String getContracttime() {
			return contracttime;
		}
		public void setContracttime(String contracttime) {
			this.contracttime = contracttime;
		}
		public String getContrctenddate() {
			return contrctenddate;
		}
		public void setContrctenddate(String contrctenddate) {
			this.contrctenddate = contrctenddate;
		}
		public String getWorktime() {
			return worktime;
		}
		public void setWorktime(String worktime) {
			this.worktime = worktime;
		}
		
		
		
		
		public String getUsersuperiorid() {
			return usersuperiorid;
		}
		public void setUsersuperiorid(String usersuperiorid) {
			this.usersuperiorid = usersuperiorid;
		}
		public String getUsergender() {
			return usergender;
		}
		public void setUsergender(String usergender) {
			this.usergender = usergender;
		}
		public String getUsernation() {
			return usernation;
		}
		public void setUsernation(String usernation) {
			this.usernation = usernation;
		}
		public Date getUserbirthday() {
			return userbirthday;
		}
		public void setUserbirthday(Date userbirthday) {
			this.userbirthday = userbirthday;
		}
		public String getUseridno() {
			return useridno;
		}
		public void setUseridno(String useridno) {
			this.useridno = useridno;
		}
		public String getUsergraduatefrom() {
			return usergraduatefrom;
		}
		public void setUsergraduatefrom(String usergraduatefrom) {
			this.usergraduatefrom = usergraduatefrom;
		}
		public String getUsermajor() {
			return usermajor;
		}
		public void setUsermajor(String usermajor) {
			this.usermajor = usermajor;
		}
		public String getUsermobilephone() {
			return usermobilephone;
		}
		public void setUsermobilephone(String usermobilephone) {
			this.usermobilephone = usermobilephone;
		}
		public String getUsertelephone() {
			return usertelephone;
		}
		public void setUsertelephone(String usertelephone) {
			this.usertelephone = usertelephone;
		}
		public String getUseremployid() {
			return useremployid;
		}
		public void setUseremployid(String useremployid) {
			this.useremployid = useremployid;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUserhometown() {
			return userhometown;
		}
		public void setUserhometown(String userhometown) {
			this.userhometown = userhometown;
		}
		public String getUserismarry() {
			return userismarry;
		}
		public void setUserismarry(String userismarry) {
			this.userismarry = userismarry;
		}
		
		public String getUserpolitical() {
			return userpolitical;
		}
		public void setUserpolitical(String userpolitical) {
			this.userpolitical = userpolitical;
		}
		public Date getUsergraduatedate() {
			return usergraduatedate;
		}
		public void setUsergraduatedate(Date usergraduatedate) {
			this.usergraduatedate = usergraduatedate;
		}
		public String getUserheightestedu() {
			return userheightestedu;
		}
		public void setUserheightestedu(String userheightestedu) {
			this.userheightestedu = userheightestedu;
		}
		public String getUserforeignlangtype() {
			return userforeignlangtype;
		}
		public void setUserforeignlangtype(String userforeigntype) {
			this.userforeignlangtype = userforeigntype;
		}
		public String getUserforeignlanglevel() {
			return userforeignlanglevel;
		}
		public void setUserforeignlanglevel(String userforeignlevel) {
			this.userforeignlanglevel = userforeignlevel;
		}
		
		public String getUserrankname() {
			return userrankname;
		}
		public void setUserrankname(String userrankname) {
			this.userrankname = userrankname;
		}
		public Date getUserrankdate() {
			return userrankdate;
		}
		public void setUserrankdate(Date userrankdate) {
			this.userrankdate = userrankdate;
		}
		public String getUserresidence() {
			return userresidence;
		}
		public void setUserresidence(String userresidence) {
			this.userresidence = userresidence;
		}
		public String getUserpostcode1() {
			return userpostcode1;
		}
		public void setUserpostcode1(String userpostcode1) {
			this.userpostcode1 = userpostcode1;
		}
		public String getUserhousehold() {
			return userhousehold;
		}
		public void setUserhousehold(String userhousehold) {
			this.userhousehold = userhousehold;
		}
		public String getUserpostcode2() {
			return userpostcode2;
		}
		public void setUserpostcode2(String userpostcode2) {
			this.userpostcode2 = userpostcode2;
		}
		public String getUseremail() {
			return useremail;
		}
		public void setUseremail(String useremail) {
			this.useremail = useremail;
		}
		public Date getUserjoindate() {
			return userjoindate;
		}
		public void setUserjoindate(Date userjoindate) {
			this.userjoindate = userjoindate;
		}
		public String getUseremployeestatus() {
			return useremployeestatus;
		}
		public void setUseremployeestatus(String useremployeestatus) {
			this.useremployeestatus = useremployeestatus;
		}
		public String getUserbelong() {
			return userbelong;
		}
		public void setUserbelong(String userbelong) {
			this.userbelong = userbelong;
		}
		public String getUserpositionnow() {
			return userpositionnow;
		}
		public void setUserpositionnow(String userpositionnow) {
			this.userpositionnow = userpositionnow;
		}
		public String getUserdepartmentid() {
			return userdepartmentid;
		}
		public void setUserdepartmentid(String userdepartmentid) {
			this.userdepartmentid = userdepartmentid;
		}
		public String getUsercontractid() {
			return usercontractid;
		}
		public void setUsercontractid(String usercontractid) {
			this.usercontractid = usercontractid;
		}
		
		public String getUserbankcardno() {
			return userbankcardno;
		}
		public void setUserbankcardno(String userbankcardno) {
			this.userbankcardno = userbankcardno;
		}
		public String getUserbanktype() {
			return userbanktype;
		}
		public void setUserbanktype(String userbanktype) {
			this.userbanktype = userbanktype;
		}
		public String getUserbankplace() {
			return userbankplace;
		}
		public void setUserbankplace(String userbankplace) {
			this.userbankplace = userbankplace;
		}
		public String getUserremark() {
			return userremark;
		}
		public void setUserremark(String userremark) {
			this.userremark = userremark;
		}
		public String getUserworkperformence() {
			return userworkperformence;
		}
		public void setUserworkperformence(String userworkperformence) {
			this.userworkperformence = userworkperformence;
		}

		public String getUserworkold() {
			return userworkold;
		}
		public void setUserworkold(String userworkold) {
			this.userworkold = userworkold;
		}
		public String getBsc009() {
			return bsc009;
		}
		public void setBsc009(String bsc009) {
			this.bsc009 = bsc009;
		}
		public String usersocpro;
		public String getUsersocpro() {
			return usersocpro;
		}
		public void setUsersocpro(String usersocpro) {
			this.usersocpro = usersocpro;
		}
		
		private String account;//�˺�

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}
		
		private String conid;//��ͬid
		public String getConid() {
			return conid;
		}

		public void setConid(String conid) {
			this.conid = conid;
		}

		public Date getCondatestart() {
			return condatestart;
		}

		public void setCondatestart(Date condatestart) {
			this.condatestart = condatestart;
		}

		public Date getCondateend() {
			return condateend;
		}

		public void setCondateend(Date condateend) {
			this.condateend = condateend;
		}

		public Date getCondatesign() {
			return condatesign;
		}

		public void setCondatesign(Date condatesign) {
			this.condatesign = condatesign;
		}

		public String getContype() {
			return contype;
		}

		public void setContype(String contype) {
			this.contype = contype;
		}

		private Date condatestart;//��ͬ��ʼ����
		private Date condateend;//��ͬ��������
		private Date condatesign;//��ͬǩԼ����
		private String contype;//��ͬ����
		
		private Date usercondateend;//��ͬ��������
		private String usercontype;//��ͬ����

		public Date getUsercondateend() {
			return usercondateend;
		}

		public void setUsercondateend(Date usercondateend) {
			this.usercondateend = usercondateend;
		}

		public String getUsercontype() {
			return usercontype;
		}

		public void setUsercontype(String usercontype) {
			this.usercontype = usercontype;
		}
}
