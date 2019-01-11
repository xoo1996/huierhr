package org.radf.apps.client.single.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.client.single.form.TjForm;
import org.radf.apps.commons.entity.Tj;
import org.radf.login.dto.LoginDTO;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.log.LogHelper;
import org.radf.plat.util.action.ActionLeafSupport;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class TjAction extends ActionLeafSupport {
	LogHelper log = new LogHelper(this.getClass().getName());

	public TjAction() {
	}
	public ActionForward tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String forward = null;
		
		ActionForward af = new ActionForward();
		
		forward = "/client/queryF.jsp";
		Tj reservation = new Tj();
		TjForm rf = (TjForm)form;
		try {
			LoginDTO dto = (LoginDTO)req.getSession().getAttribute("LoginDTO");
			ClassHelper.copyProperties(rf, reservation);
			
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date myDate = dateFormat1.parse("2013-04-25");
			String hql="";
			if(rf.getStart()!=null && rf.getEnd()!=null ){
				if(rf.getStart().after(rf.getEnd())==true){
					hql="select * from tbltj where 1=2";
					af = super.init(req, forward, hql);
					String msg = "起始时间 大于终止时间，没有查询到符合条件的记录！";
					super.saveSuccessfulMsg(req, msg);
				}else{
					boolean b1=myDate.before(rf.getStart());//起始时间是否大于20130425
					boolean b2=myDate.before(rf.getEnd());//终止时间是否大于20130425
					if(b1==true){//起始时间在25之后
						reservation.setFileKey("yuyue04_000");
						hql = queryEnterprise(reservation);
					}
					if(b2==false){//终止时间在25之前
						//reservation.setFileKey("yuyue04_001");
						hql="select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt," +
								"pdtprc,foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
								"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c " +
								"where c.ictid=f.fdtcltid) as ictaddr,f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm," +
								"(case when (o.folsta='recpass' and o.foltype='make') then -1 when (o.folsta!='recpass' and o.foltype='make') then 1 when (o.folsta='finish' and o.foltype='normal') then f.fdtqnt end) as fdtqnt," +
								"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tbltj f left join tblfolio o on f.fdtfno=o.folno " +
								"where o.foldt >= to_date('" + rf.getStart().toString() +"','yyyy-mm-dd') " +
										"and o.foldt < to_date('" + rf.getEnd().toString() +"','yyyy-mm-dd') and 1=1)";
					}
					if(b1==false && b2==true){//起始时间在25之前，终止时间在25之后
						//reservation.setFileKey("yuyue04_002");
						hql="select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc," +
								"foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
								"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c " +
								"where c.ictid=f.fdtcltid) as ictaddr,f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm," +
								"(case when (o.folsta='recpass' and o.foltype='make') then -1 when (o.folsta!='recpass' and o.foltype='make') then 1 when (o.folsta='finish' and o.foltype='normal') then f.fdtqnt end) as fdtqnt," +
								"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tbltj f left join tblfolio o on f.fdtfno=o.folno " +
								"where o.foldt >= to_date('" + rf.getStart().toString() +"','yyyy-mm-dd') and o.foldt < to_date('2013-04-26','yyyy-mm-dd')) " +
								"union all " +
								"select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc," +
								"foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
								"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c where c.ictid=f.fdtcltid) as ictaddr," +
								"f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm,(case when o.folsta='recpass' then -1 else 1 end) as fdtqnt," +
								"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tblfoldetail f left join tblfolio o on f.fdtfno=o.folno " +
								"where o.foltype='make' and o.folsta not in ('uncommited','commited','back','disback','wtdis') " +
								"and o.foldt >= to_date('2013-04-26','yyyy-mm-dd') and o.foldt < to_date('" +rf.getEnd().toString()+"','yyyy-mm-dd'))" +
								" union all " +
								"select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc,foldt " +
								"from (select (select c.ictnm from tblindclient c where c.ictid=g.chgcltid) as ictnm,(select c.icttel from tblindclient c where c.ictid=g.chgcltid) as icttel," +
								"(select c.ictaddr from tblindclient c where c.ictid=g.chgcltid) as ictaddr,i.ncdpid as fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=i.ncdpid) as pdtnm," +
								"i.ncdqnt as fdtqnt,(select p.pdtprc from tblproduct p where p.pdtid=i.ncdpid) as pdtprc,g.chgdt as foldt from tblnorchgdetail i, tblnorchg g " +
								"where ((i.ncdsta in ('finish') and g.chgdt >= to_date('2013-04-26','YYYY-MM-DD') " +
								"and g.chgdt < to_date('" +rf.getEnd().toString()+"','yyyy-mm-dd') and i.ncdid = g.chgid) or (i.ncdsta in ('nomrecoiled', 'commited', 'pass','back') " +
								"and i.ncdrecdt >= to_date('2013-04-26','YYYY-MM-DD') " +
								"and i.ncdrecdt < to_date('" +rf.getEnd().toString()+"','yyyy-mm-dd') and i.ncdoid = g.chgid)))";
					}
					
					//String hql = queryEnterprise(reservation);
					af = super.init(req, forward, hql);
					// 检查是否存在？
					if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
						String msg = "没有查询到符合条件的记录！";
						super.saveSuccessfulMsg(req, msg);
					}
				}
			}else{
				if(rf.getStart()==null && rf.getEnd()==null){
					//reservation.setFileKey("yuyue04_002");
					hql="select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc," +
							"foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
							"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c " +
							"where c.ictid=f.fdtcltid) as ictaddr,f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm," +
							"(case when (o.folsta='recpass' and o.foltype='make') then -1 when (o.folsta!='recpass' and o.foltype='make') then 1 when (o.folsta='finish' and o.foltype='normal') then f.fdtqnt end) as fdtqnt," +
							"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tbltj f left join tblfolio o on f.fdtfno=o.folno " +
							"where o.foldt < to_date('2013-04-26','yyyy-mm-dd')) " +
							"union all " +
							"select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc," +
							"foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
							"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c where c.ictid=f.fdtcltid) as ictaddr," +
							"f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm,(case when o.folsta='recpass' then -1 else 1 end) as fdtqnt," +
							"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tblfoldetail f left join tblfolio o on f.fdtfno=o.folno " +
							"where o.foltype='make' and o.folsta not in ('uncommited','commited','back','disback','wtdis') " +
							"and o.foldt >= to_date('2013-04-26','yyyy-mm-dd') )" +
							" union all " +
							"select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc,foldt " +
							"from (select (select c.ictnm from tblindclient c where c.ictid=g.chgcltid) as ictnm,(select c.icttel from tblindclient c where c.ictid=g.chgcltid) as icttel," +
							"(select c.ictaddr from tblindclient c where c.ictid=g.chgcltid) as ictaddr,i.ncdpid as fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=i.ncdpid) as pdtnm," +
							"i.ncdqnt as fdtqnt,(select p.pdtprc from tblproduct p where p.pdtid=i.ncdpid) as pdtprc,g.chgdt as foldt from tblnorchgdetail i, tblnorchg g " +
							"where ((i.ncdsta in ('finish') and g.chgdt >= to_date('2013-04-26','YYYY-MM-DD') " +
							"and i.ncdid = g.chgid) or (i.ncdsta in ('nomrecoiled', 'commited', 'pass','back') " +
							"and i.ncdrecdt >= to_date('2013-04-26','YYYY-MM-DD') " +
							"and i.ncdoid = g.chgid)))";
				}else{
					if(rf.getStart()==null && rf.getEnd()!=null){
						if(myDate.before(rf.getEnd()) ==true ){//终止时间25之后
							//reservation.setFileKey("yuyue04_002");
							hql="select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc," +
									"foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
									"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c " +
									"where c.ictid=f.fdtcltid) as ictaddr,f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm," +
									"(case when (o.folsta='recpass' and o.foltype='make') then -1 when (o.folsta!='recpass' and o.foltype='make') then 1 when (o.folsta='finish' and o.foltype='normal') then f.fdtqnt end) as fdtqnt," +
									"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tbltj f left join tblfolio o on f.fdtfno=o.folno " +
									"where o.foldt < to_date('2013-04-26','yyyy-mm-dd')) " +
									"union all " +
									"select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc," +
									"foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
									"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c where c.ictid=f.fdtcltid) as ictaddr," +
									"f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm,(case when o.folsta='recpass' then -1 else 1 end) as fdtqnt," +
									"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tblfoldetail f left join tblfolio o on f.fdtfno=o.folno " +
									"where o.foltype='make' and o.folsta not in ('uncommited','commited','back','disback','wtdis') " +
									"and o.foldt >= to_date('2013-04-26','yyyy-mm-dd') and o.foldt < to_date('" +rf.getEnd().toString()+"','yyyy-mm-dd'))" +
									" union all " +
									"select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc,foldt " +
									"from (select (select c.ictnm from tblindclient c where c.ictid=g.chgcltid) as ictnm,(select c.icttel from tblindclient c where c.ictid=g.chgcltid) as icttel," +
									"(select c.ictaddr from tblindclient c where c.ictid=g.chgcltid) as ictaddr,i.ncdpid as fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=i.ncdpid) as pdtnm," +
									"i.ncdqnt as fdtqnt,(select p.pdtprc from tblproduct p where p.pdtid=i.ncdpid) as pdtprc,g.chgdt as foldt from tblnorchgdetail i, tblnorchg g " +
									"where ((i.ncdsta in ('finish') and g.chgdt >= to_date('2013-04-26','YYYY-MM-DD') " +
									"and g.chgdt < to_date('" +rf.getEnd().toString()+"','yyyy-mm-dd') and i.ncdid = g.chgid) or (i.ncdsta in ('nomrecoiled', 'commited', 'pass','back') " +
									"and i.ncdrecdt >= to_date('2013-04-26','YYYY-MM-DD') " +
									"and i.ncdrecdt < to_date('" +rf.getEnd().toString()+"','yyyy-mm-dd') and i.ncdoid = g.chgid)))";
						}else{
							//reservation.setFileKey("yuyue04_001");
							hql="select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt," +
									"pdtprc,foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
									"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c " +
									"where c.ictid=f.fdtcltid) as ictaddr,f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm," +
									"(case when (o.folsta='recpass' and o.foltype='make') then -1 when (o.folsta!='recpass' and o.foltype='make') then 1 when (o.folsta='finish' and o.foltype='normal') then f.fdtqnt end) as fdtqnt," +
									"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tbltj f left join tblfolio o on f.fdtfno=o.folno " +
									"where o.foldt < to_date('" + rf.getEnd().toString() +"','yyyy-mm-dd') and 1=1)";
						}
					}else{
						if(rf.getStart()!=null && rf.getEnd()==null){
							if(myDate.before(rf.getStart()) ==true ){//起始时间25之后
								reservation.setFileKey("yuyue04_000");
								hql = queryEnterprise(reservation);
							}else{
								//reservation.setFileKey("yuyue04_002");
								hql="select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc," +
										"foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
										"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c " +
										"where c.ictid=f.fdtcltid) as ictaddr,f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm," +
										"(case when (o.folsta='recpass' and o.foltype='make') then -1 when (o.folsta!='recpass' and o.foltype='make') then 1 when (o.folsta='finish' and o.foltype='normal') then f.fdtqnt end) as fdtqnt," +
										"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tbltj f left join tblfolio o on f.fdtfno=o.folno " +
										"where o.foldt >= to_date('" + rf.getStart().toString() +"','yyyy-mm-dd') and o.foldt < to_date('2013-04-26','yyyy-mm-dd')) " +
										"union all " +
										"select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc," +
										"foldt from (select nvl(f.fdtcltnm,nvl((select c.ictnm from tblindclient c where c.ictid=f.fdtcltid),'000000')) as ictnm," +
										"(select c.icttel from tblindclient c where c.ictid=f.fdtcltid) as icttel,(select c.ictaddr from tblindclient c where c.ictid=f.fdtcltid) as ictaddr," +
										"f.fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=f.fdtpid) as pdtnm,(case when o.folsta='recpass' then -1 else 1 end) as fdtqnt," +
										"(select p.pdtprc from tblproduct p where p.pdtid=f.fdtpid) as pdtprc,o.foldt from tblfoldetail f left join tblfolio o on f.fdtfno=o.folno " +
										"where o.foltype='make' and o.folsta not in ('uncommited','commited','back','disback','wtdis') " +
										"and o.foldt >= to_date('2013-04-26','yyyy-mm-dd') )" +
										" union all " +
										"select distinct ictnm,icttel,ictaddr,fdtpid,pdtnm,sum(fdtqnt)over(partition by ictnm,fdtpid,foldt) as fdtqnt,pdtprc,foldt " +
										"from (select (select c.ictnm from tblindclient c where c.ictid=g.chgcltid) as ictnm,(select c.icttel from tblindclient c where c.ictid=g.chgcltid) as icttel," +
										"(select c.ictaddr from tblindclient c where c.ictid=g.chgcltid) as ictaddr,i.ncdpid as fdtpid,(select p.pdtnm from tblproduct p where p.pdtid=i.ncdpid) as pdtnm," +
										"i.ncdqnt as fdtqnt,(select p.pdtprc from tblproduct p where p.pdtid=i.ncdpid) as pdtprc,g.chgdt as foldt from tblnorchgdetail i, tblnorchg g " +
										"where ((i.ncdsta in ('finish') and g.chgdt >= to_date('2013-04-26','YYYY-MM-DD') " +
										"and i.ncdid = g.chgid) or (i.ncdsta in ('nomrecoiled', 'commited', 'pass','back') " +
										"and i.ncdrecdt >= to_date('2013-04-26','YYYY-MM-DD') " +
										"and i.ncdoid = g.chgid)))";
							}
						}
					}
				}
				//String hql = queryEnterprise(reservation);
				af = super.init(req, forward, hql);
				// 检查是否存在？
				if (null == req.getAttribute(GlobalNames.QUERY_DATA)) {
					String msg = "没有查询到符合条件的记录！";
					super.saveSuccessfulMsg(req, msg);
				}
			}
			
		} catch (AppException ex) {
			this.saveErrors(req, ex);
		} catch (Exception e) {
			this.saveErrors(req, e);
		}
		return af;
	}

}
