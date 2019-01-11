package org.radf.apps.process.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ApaForm extends ActionForm {
	// ��Ա��¼����������
	private String nemid;// ��Ա��¼��id
	private String nemname;// ����
	private String nemsex;// �Ա�
	private String nembirthpl;// ����
	private Date nembirthdt;// ����
	private String nemeducation;// ���ѧ��
	private String nemmajor;// רҵ
	private String nemschool;// ��ҵѧУ
	private Date nemedudt;// ��ҵʱ��
	private String nempart;// ��¼�ò���
	private String nemjob;// ��¼�ø�λ
	private Date nemworkdt;// ����ְʱ��
	private String nemtype;// Ա������
	private String nempay;// н�����
	private String nemmon1;// ���ù���ÿ��˰ǰ
	private String nemmon2;// ת������ÿ��˰ǰ
	private String nemyear1;// ��н��׼Ϊ˰ǰ
	private String nemyear2;// ÿ�·���˰ǰ
	private String nemwelfare;// ����
	private String nemlimit;// ��ͬ����
	private String nemtry;// ������
	private String nemnote;// ��ע
	private String nemapplyid;// ������id
	private String nemstate;// ���״̬
	private String nemadv;// ��˽���
	private String nemverifyid;// �����id
	private String nemcard;//
	
	public String getNemcard() {
		return nemcard;
	}

	public void setNemcard(String nemcard) {
		this.nemcard = nemcard;
	}

	private String aparole;// ��˽�ɫ
	private String aparesult;// ��˽��
	private Date apaverifydt;//���ʱ��
	
	public Date getApaverifydt() {
		return apaverifydt;
	}

	public void setApaverifydt(Date apaverifydt) {
		this.apaverifydt = apaverifydt;
	}

	private String nemcondition;// �����ж�

	// �������
	private Date restsdt;// ��ʼʱ��
	private Date restedt;// ����ʱ��
	private String resttype;// �������
	private String restother;// ��������
	private String restreason;// �������
	private String restnote;// ��ע
	public String getRestnum() {
		return restnum;
	}

	public void setRestnum(String restnum) {
		this.restnum = restnum;
	}

	public String getCvtfee() {
		return cvtfee;
	}

	public void setCvtfee(String cvtfee) {
		this.cvtfee = cvtfee;
	}

	private String restnum;//
	// ת������
	private String cvtname;//
	private String cvtapt;// ���ڲ���
	private Date cvtbdt;//
	private String cvtedu;//
	private String cvtsch;//
	private Date cvtsdt;//
	private Date cvtedt;//
	public Date getCvtedt() {
		return cvtedt;
	}

	public void setCvtedt(Date cvtedt) {
		this.cvtedt = cvtedt;
	}
	private String cvtintro;//
	private String cvtfee;//
	// ��ְ����
	private String leaname;//
	private String leapart;//
	private Date leasdt;//
	private Date leaedt;//
	private String leatype;//
	private String leareason;//

	private String nemsupid;//
	private String apatype;//
	private Date nemappdt;//

	private String role;//
	private String nememployid;//
	private String nemyn;//
	
	private String nemad1;//
	private String nemad2;//
	private String nemad3;//
	private String nemad4;//
	private String nemad5;//
	private Date nemren1;//
	private Date nemren2;//
	private Date nemren3;//
	private Date nemren4;//
	private Date nemren5;//
	
	private String nemapa;
	private String nemif;//
	public Date getNemldt() {
		return nemldt;
	}

	public void setNemldt(Date nemldt) {
		this.nemldt = nemldt;
	}

	public Date getNemedt() {
		return nemedt;
	}

	public void setNemedt(Date nemedt) {
		this.nemedt = nemedt;
	}

	public String getNemday() {
		return nemday;
	}

	public void setNemday(String nemday) {
		this.nemday = nemday;
	}

	public String getNemsoc() {
		return nemsoc;
	}

	public void setNemsoc(String nemsoc) {
		this.nemsoc = nemsoc;
	}

	public String getNemsom() {
		return nemsom;
	}

	public void setNemsom(String nemsom) {
		this.nemsom = nemsom;
	}

	private Date nemldt;
	private Date nemedt;
	private String nemday;
	private String nemsoc;
	private String nemsom;
	public String getNemif() {
		return nemif;
	}

	public void setNemif(String nemif) {
		this.nemif = nemif;
	}
	public String getNemapa() {
		return nemapa;
	}

	public void setNemapa(String nemapa) {
		this.nemapa = nemapa;
	}

	public String getNemad1() {
		return nemad1;
	}

	public void setNemad1(String nemad1) {
		this.nemad1 = nemad1;
	}

	public String getNemad2() {
		return nemad2;
	}

	public void setNemad2(String nemad2) {
		this.nemad2 = nemad2;
	}

	public String getNemad3() {
		return nemad3;
	}

	public void setNemad3(String nemad3) {
		this.nemad3 = nemad3;
	}

	public String getNemad5() {
		return nemad5;
	}

	public void setNemad5(String nemad5) {
		this.nemad5 = nemad5;
	}
	
	public String getNemad4() {
		return nemad4;
	}

	public void setNemad4(String nemad4) {
		this.nemad4 = nemad4;
	}
	
	public Date getNemren1() {
		return nemren1;
	}

	public void setNemren1(Date nemren1) {
		this.nemren1 = nemren1;
	}

	public Date getNemren2() {
		return nemren2;
	}

	public void setNemren2(Date nemren2) {
		this.nemren2 = nemren2;
	}

	public Date getNemren3() {
		return nemren3;
	}

	public void setNemren3(Date nemren3) {
		this.nemren3 = nemren3;
	}

	public Date getNemren4() {
		return nemren4;
	}

	public void setNemren4(Date nemren4) {
		this.nemren4 = nemren4;
	}
	
	public Date getNemren5() {
		return nemren5;
	}

	public void setNemren5(Date nemren5) {
		this.nemren5 = nemren5;
	}
	
	public String getNemyn() {
		return nemyn;
	}

	public void setNemyn(String nemyn) {
		this.nemyn = nemyn;
	}

	public String getNememployid() {
		return nememployid;
	}

	public void setNememployid(String nememployid) {
		this.nememployid = nememployid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNemsupid() {
		return nemsupid;
	}

	public void setNemsupid(String nemsupid) {
		this.nemsupid = nemsupid;
	}

	public String getApatype() {
		return apatype;
	}

	public void setApatype(String apatype) {
		this.apatype = apatype;
	}

	public Date getNemappdt() {
		return nemappdt;
	}

	public void setNemappdt(Date nemappdt) {
		this.nemappdt = nemappdt;
	}

	public String getCvtname() {
		return cvtname;
	}

	public void setCvtname(String cvtname) {
		this.cvtname = cvtname;
	}

	public String getCvtapt() {
		return cvtapt;
	}

	public void setCvtapt(String cvtapt) {
		this.cvtapt = cvtapt;
	}
	
	public Date getCvtbdt() {
		return cvtbdt;
	}

	public void setCvtbdt(Date cvtbdt) {
		this.cvtbdt = cvtbdt;
	}

	public String getCvtedu() {
		return cvtedu;
	}

	public void setCvtedu(String cvtedu) {
		this.cvtedu = cvtedu;
	}

	public String getCvtsch() {
		return cvtsch;
	}

	public void setCvtsch(String cvtsch) {
		this.cvtsch = cvtsch;
	}

	public Date getCvtsdt() {
		return cvtsdt;
	}

	public void setCvtsdt(Date cvtsdt) {
		this.cvtsdt = cvtsdt;
	}

	public String getCvtintro() {
		return cvtintro;
	}

	public void setCvtintro(String cvtintro) {
		this.cvtintro = cvtintro;
	}

	public String getLeaname() {
		return leaname;
	}

	public void setLeaname(String leaname) {
		this.leaname = leaname;
	}

	public String getLeapart() {
		return leapart;
	}

	public void setLeapart(String leapart) {
		this.leapart = leapart;
	}

	public Date getLeasdt() {
		return leasdt;
	}

	public void setLeasdt(Date leasdt) {
		this.leasdt = leasdt;
	}

	public Date getLeaedt() {
		return leaedt;
	}

	public void setLeaedt(Date leaedt) {
		this.leaedt = leaedt;
	}

	public String getLeatype() {
		return leatype;
	}

	public void setLeatype(String leatype) {
		this.leatype = leatype;
	}

	public String getLeareason() {
		return leareason;
	}

	public void setLeareason(String leareason) {
		this.leareason = leareason;
	}

	public Date getRestsdt() {
		return restsdt;
	}

	public void setRestsdt(Date restsdt) {
		this.restsdt = restsdt;
	}

	public Date getRestedt() {
		return restedt;
	}

	public void setRestedt(Date restedt) {
		this.restedt = restedt;
	}

	public String getResttype() {
		return resttype;
	}

	public void setResttype(String resttype) {
		this.resttype = resttype;
	}

	public String getRestother() {
		return restother;
	}

	public void setRestother(String restother) {
		this.restother = restother;
	}

	public String getRestreason() {
		return restreason;
	}

	public void setRestreason(String restreason) {
		this.restreason = restreason;
	}

	public String getRestnote() {
		return restnote;
	}

	public void setRestnote(String restnote) {
		this.restnote = restnote;
	}

	public String getNemcondition() {
		return nemcondition;
	}

	public void setNemcondition(String nemcondition) {
		this.nemcondition = nemcondition;
	}

	public String getAparole() {
		return aparole;
	}

	public void setAparole(String aparole) {
		this.aparole = aparole;
	}

	public String getAparesult() {
		return aparesult;
	}

	public void setAparesult(String aparesult) {
		this.aparesult = aparesult;
	}

	public String getNemapplyid() {
		return nemapplyid;
	}

	public void setNemapplyid(String nemapplyid) {
		this.nemapplyid = nemapplyid;
	}

	public String getNemstate() {
		return nemstate;
	}

	public void setNemstate(String nemstate) {
		this.nemstate = nemstate;
	}

	public String getNemadv() {
		return nemadv;
	}

	public void setNemadv(String nemadv) {
		this.nemadv = nemadv;
	}

	public String getNemverifyid() {
		return nemverifyid;
	}

	public void setNemverifyid(String nemverifyid) {
		this.nemverifyid = nemverifyid;
	}

	public String getNemid() {
		return nemid;
	}

	public void setNemid(String nemid) {
		this.nemid = nemid;
	}

	public String getNemname() {
		return nemname;
	}

	public void setNemname(String nemname) {
		this.nemname = nemname;
	}

	public String getNemsex() {
		return nemsex;
	}

	public void setNemsex(String nemsex) {
		this.nemsex = nemsex;
	}

	public String getNembirthpl() {
		return nembirthpl;
	}

	public void setNembirthpl(String nembirthpl) {
		this.nembirthpl = nembirthpl;
	}

	public Date getNembirthdt() {
		return nembirthdt;
	}

	public void setNembirthdt(Date nembirthdt) {
		this.nembirthdt = nembirthdt;
	}

	public String getNemeducation() {
		return nemeducation;
	}

	public void setNemeducation(String nemeducation) {
		this.nemeducation = nemeducation;
	}

	public String getNemmajor() {
		return nemmajor;
	}

	public void setNemmajor(String nemmajor) {
		this.nemmajor = nemmajor;
	}

	public String getNemschool() {
		return nemschool;
	}

	public void setNemschool(String nemschool) {
		this.nemschool = nemschool;
	}

	public Date getNemedudt() {
		return nemedudt;
	}

	public void setNemedudt(Date nemedudt) {
		this.nemedudt = nemedudt;
	}

	public String getNempart() {
		return nempart;
	}

	public void setNempart(String nempart) {
		this.nempart = nempart;
	}

	public String getNemjob() {
		return nemjob;
	}

	public void setNemjob(String nemjob) {
		this.nemjob = nemjob;
	}

	public Date getNemworkdt() {
		return nemworkdt;
	}

	public void setNemworkdt(Date nemworkdt) {
		this.nemworkdt = nemworkdt;
	}

	public String getNemtype() {
		return nemtype;
	}

	public void setNemtype(String nemtype) {
		this.nemtype = nemtype;
	}

	public String getNempay() {
		return nempay;
	}

	public void setNempay(String nempay) {
		this.nempay = nempay;
	}

	public String getNemmon1() {
		return nemmon1;
	}

	public void setNemmon1(String nemmon1) {
		this.nemmon1 = nemmon1;
	}

	public String getNemmon2() {
		return nemmon2;
	}

	public void setNemmon2(String nemmon2) {
		this.nemmon2 = nemmon2;
	}

	public String getNemyear1() {
		return nemyear1;
	}

	public void setNemyear1(String nemyear1) {
		this.nemyear1 = nemyear1;
	}

	public String getNemyear2() {
		return nemyear2;
	}

	public void setNemyear2(String nemyear2) {
		this.nemyear2 = nemyear2;
	}

	public String getNemwelfare() {
		return nemwelfare;
	}

	public void setNemwelfare(String nemwelfare) {
		this.nemwelfare = nemwelfare;
	}

	public String getNemlimit() {
		return nemlimit;
	}

	public void setNemlimit(String nemlimit) {
		this.nemlimit = nemlimit;
	}

	public String getNemtry() {
		return nemtry;
	}

	public void setNemtry(String nemtry) {
		this.nemtry = nemtry;
	}

	public String getNemnote() {
		return nemnote;
	}

	public void setNemnote(String nemnote) {
		this.nemnote = nemnote;
	}

	private String name;
	private String id;
	private List<FormFile> myFiles = new ArrayList<FormFile>();

	public FormFile getFile(int i) // ��������
	{
		return myFiles.get(i);
	}

	public void setFile(int i, FormFile myFile) // ��������
	{
		if (myFile.getFileSize() > 0) // ֻ���ϴ��ļ����ֽ�������0�����ϴ�����ļ�
		{
			myFiles.add(myFile);
		}
	}

	public int getFileCount() // ����ϴ��ļ��ĸ���
	{
		return myFiles.size();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// �Ա�
		private String usergender;
		// ����
		private String usernation;
		// ��������
		private Date userbirthday;
		// ���֤����
		private String useridno;
		// ��ҵѧУ
		private String usergraduatefrom;
		// רҵ
		private String usermajor;
		// �ƶ��绰
		private String usermobilephone;
		// �̶��绰
		private String usertelephone;
		//������
		private String danganid;
		// Ա��id
		private String useremployid;
		// ����
		private String username;
		// ����
		private String userhometown;
		// ���״��
		private String userismarry;
		// ������ò
		private String userpolitical;
		// ��ҵʱ��
		private Date usergraduatedate;
		// ���ѧ��
		private String userheightestedu;
		// ����
		private String userforeignlangtype;
		// ����ˮƽ
		private String userforeignlanglevel;
		// ����ְ��
		private String userrankname;
		// ����ʱ��
		private Date userrankdate;
		// ��ס��ַ
		private String userresidence;
		// ��ס���ʱ�
		private String userpostcode1;
		// ������ַ
		private String userhousehold;
		// �����ʱ�
		private String userpostcode2;
		// ����
		private String useremail;
		// ��ְʱ��
		private Date userjoindate;
		// Ա��״̬
		private String useremployeestatus;
		// ����
		private String userbelong;
		// ���θ�λ
		private String userpositionnow;
		// ��������id
		private String userdepartmentid;
		// �Ͷ���ͬID

		private String usercontractid;
		// �����˺�
		private String userbankardno;
		// ��������
		private String userbanktype;
		// ��������
		private String userbankplace;
		// ��ע
		private String userremark;
		// ��������
		private String userworkperformence;
		// �ϼ�id
		private String usersuperiorid;
		//��ʼ��������
		private Date userworktime;

		private String departmentname;
		private String bsc009;

		private String departmenttype;

		private String contrctstartdate;

		private String contracttime;
		private String contrctenddate;
		private String worktime;

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

		public String getDanganid() {
			return danganid;
		}

		public void setDanganid(String danganid) {
			this.danganid = danganid;
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

		public void setUserforeignlangtype(String userforeignlangtype) {
			this.userforeignlangtype = userforeignlangtype;
		}

		public String getUserforeignlanglevel() {
			return userforeignlanglevel;
		}

		public void setUserforeignlanglevel(String userforeignlanglevel) {
			this.userforeignlanglevel = userforeignlanglevel;
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

		public String getUserbankardno() {
			return userbankardno;
		}

		public void setUserbankardno(String userbankardno) {
			this.userbankardno = userbankardno;
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

		public String getUsersuperiorid() {
			return usersuperiorid;
		}

		public void setUsersuperiorid(String usersuperiorid) {
			this.usersuperiorid = usersuperiorid;
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

		public String getBsc009() {
			return bsc009;
		}

		public void setBsc009(String bsc009) {
			this.bsc009 = bsc009;
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
	
		private String userworkold;
		public String getUserworkold() {
			return userworkold;
		}
		public void setUserworkold(String userworkold) {
			this.userworkold = userworkold;
		}
		public String usersocpro;
		public String getUsersocpro() {
			return usersocpro;
		}
		public void setUsersocpro(String usersocpro) {
			this.usersocpro = usersocpro;
		}
		
		private String shifa;
		public String getShifa() {
			return shifa;
		}

		public void setShifa(String shifa) {
			this.shifa = shifa;
		}

		public String getYingfa() {
			return yingfa;
		}

		public void setYingfa(String yingfa) {
			this.yingfa = yingfa;
		}

		public String getStore() {
			return store;
		}

		public void setStore(String store) {
			this.store = store;
		}

		private String yingfa;//Ӧ������
		private String store;//�ݶ���
		
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
		
		private String hqz;

		public String getHqz() {
			return hqz;
		}

		public void setHqz(String hqz) {
			this.hqz = hqz;
		}
}
