/*
 * Created on 2005-5-29
 *
 */
package org.radf.plat.taglib.xtree;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.radf.login.dto.LoginDTO;

/**
 * @author Tony Yu
 *
 */
public class TreeServlet extends HttpServlet {

    private static final String a = "text/xml";
    private static final String _fldif = null;

    public TreeServlet(){
    }

    public void init() throws ServletException {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException {
		
        String s = httpservletrequest.getParameter("key");
        String s1 = httpservletrequest.getParameter("tree");
        String s2 = httpservletrequest.getParameter("condition");
        if(s == null)
            s = "";
        httpservletresponse.setContentType("text/xml");
        PrintWriter printwriter = httpservletresponse.getWriter();
        ServletContext servletcontext = getServletContext();
        if(s1.equals("region"))
        {
			RegionFactory regionfactory = (RegionFactory)servletcontext.getAttribute("RegionFactory");
            printwriter.println(regionfactory.getRegionListDocument(s, s2));
        } 
        else if(s1.equals("ssjd")) //判断所属街道，by李灵超 2004年4月26日
            {
				
				if(s.endsWith("root"))
				  {
					LoginDTO dto1 = (LoginDTO) httpservletrequest.getSession().getAttribute("LoginDTO");
					if(dto1.getAab003().length()>=8 && !"01".equals(dto1.getAab003().substring(dto1.getAab003().length()-2,dto1.getAab003().length())))
					{
						s="root"+dto1.getAab003();
					}else
					{

						s="root"+dto1.getBsc998();
					}
				  }
                SSJDRegionFactory ssjdregionfactory = (SSJDRegionFactory)servletcontext.getAttribute("SSJDRegionFactory");
                printwriter.println(ssjdregionfactory.getRegionListDocument(s, s2));
            }
        else if(s1.equals("ssjdQuery")) //判断所属街道，by李灵超 2004年4月26日
        {
            SSJDRegionQueryFactory ssjdregionqueryfactory = (SSJDRegionQueryFactory)servletcontext.getAttribute("SSJDRegionQueryFactory");
            printwriter.println(ssjdregionqueryfactory.getRegionListDocument(s, s2));
        }
        else if(s1.equals("workType"))
        {
            WorkTypeFactory worktypefactory = (WorkTypeFactory)servletcontext.getAttribute("WorkTypeFactory");
            printwriter.println(worktypefactory.getWorkTypeListDocument(s, s2));
        }
        else if(s1.equals("workTypeQuery"))
        {
            WorkTypeQueryFactory worktypequeryfactory = (WorkTypeQueryFactory)servletcontext.getAttribute("WorkTypeQueryFactory");
            printwriter.println(worktypequeryfactory.getWorkTypeListDocument(s, s2));
            
        }else if(s1.equals("cylb"))
        {
        	CYLBFactory cylbfactory = (CYLBFactory)servletcontext.getAttribute("CYLBFactory");
            printwriter.println(cylbfactory.getRegionListDocument(s, s2));
            
        }
        if(_fldif != null)
            printwriter.println(_fldif);
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException{
        doGet(httpservletrequest, httpservletresponse);
    }

    public void destroy(){
    }

}
