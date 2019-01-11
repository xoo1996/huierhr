// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.commons.download;


import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.radf.plat.commons.StringUtil;
import org.radf.plat.util.global.GlobalNames;

public class DownloadServlet extends HttpServlet
{

    public DownloadServlet()
    {
    }

    public void init()
        throws ServletException
    {
    }

    public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        javax.servlet.http.HttpSession httpsession = httpservletrequest.getSession(false);
        if(httpsession == null)
        {
            httpservletresponse.getWriter().println(StringUtil.ChineseStringToAscii("有以下错误：没有权限！"));
            return;
        }
        try
        {
            String s = httpservletrequest.getAttribute("type") != null ? (String)httpservletrequest.getAttribute("type") : httpservletrequest.getParameter("type");
            String s1 = httpservletrequest.getAttribute("name") != null ? (String)httpservletrequest.getAttribute("name") : httpservletrequest.getParameter("name");
            if(s1 == null || s == null)
                return;
            if(s.equals("excel"))
            {
                HSSFWorkbook hssfworkbook = (HSSFWorkbook)httpservletrequest.getAttribute("excelData");
                httpservletresponse.setContentType("application/vnd.ms-excel");
                StringBuffer stringbuffer1 = new StringBuffer("\"attachment; filename=\"");
                stringbuffer1.append(s1).append(".xls");
                httpservletresponse.setHeader("Content-disposition", stringbuffer1.toString());
                ServletOutputStream servletoutputstream1 = httpservletresponse.getOutputStream();
                hssfworkbook.write(servletoutputstream1);
                servletoutputstream1.flush();
                servletoutputstream1.close();
                return;
            }
            if(s.equals("txt"))
            {
                String s2 = (String)httpservletrequest.getAttribute(GlobalNames.LOG_DATA);
                a(httpservletresponse, s1, "");
                ServletOutputStream servletoutputstream = httpservletresponse.getOutputStream();
                servletoutputstream.write(s2.getBytes(GlobalNames.DEFAULT_ENCODING));
                servletoutputstream.flush();
                servletoutputstream.close();
                return;
            }
            StringBuffer stringbuffer = new StringBuffer(256);
            if(s.equals("report"))
            {
                stringbuffer.append(getServletContext().getRealPath(""));
                stringbuffer.append(File.separatorChar);
                stringbuffer.append("reports");
                stringbuffer.append(File.separatorChar);
                stringbuffer.append(s1);
                stringbuffer.append(".xml");
                a(httpservletresponse, s1, ".xml");
            }
            if(s.equals("photo"))
            {
                stringbuffer.append(getServletContext().getRealPath(GlobalNames.TEMP_PHOTO_PATH));
                stringbuffer.append(File.separatorChar);
                stringbuffer.append(s1);
                a(httpservletresponse, null, null);
            }
            FileInputStream fileinputstream = new FileInputStream(stringbuffer.toString());
            ServletOutputStream servletoutputstream2 = httpservletresponse.getOutputStream();
            int i;
            while((i = fileinputstream.read()) != -1) 
                servletoutputstream2.write(i);
            fileinputstream.close();
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public void destroy()
    {
    }

    private void a(HttpServletResponse httpservletresponse, String s, String s1)
    {
        if(s == null || s1 == null)
        {
            httpservletresponse.setContentType("text/html; charset=GBK");
        } else
        {
            httpservletresponse.setContentType("application/octet-stream; charset=GBK");
            StringBuffer stringbuffer = new StringBuffer("\"attachment; filename=\"");
            stringbuffer.append(s).append(s1);
            httpservletresponse.setHeader("Content-disposition", stringbuffer.toString());
        }
    }
}
