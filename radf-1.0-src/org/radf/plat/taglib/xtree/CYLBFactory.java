package org.radf.plat.taglib.xtree;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.FastArrayList;
import org.apache.commons.collections.FastHashMap;

import org.radf.plat.entity.Aa15;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.util.global.GlobalNames;

public class CYLBFactory implements Serializable{

    private Map a;

    public CYLBFactory()
    {
        a = null;
        a = new FastHashMap();
    }

    public void clearCache()
    {
        synchronized(a)
        {
            a.clear();
        }
    }

    public String getRegionListDocument(String s, String s1)
    {
        String s2 = s.toLowerCase();
        if(a.containsKey(s2))
            return (String)a.get(s2);
        else
            return a(s2, s1);
    }

    private String a(String s, String s1)
    {
        Object obj = null;
        FastArrayList fastarraylist = new FastArrayList();
        FastArrayList fastarraylist1 = new FastArrayList();
        Object obj1 = null;
        StringBuffer stringbuffer = new StringBuffer(1024);
        int i = getLevel(s);
        stringbuffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
        stringbuffer.append("<tree>");
		Connection con = null;
        try
        {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
            StringBuffer stringbuffer1 = (new StringBuffer(128)).append(" select * from  aa15  where 1 = 1 ");
            if(s1 != null)
                stringbuffer1.append(" and (").append(s1).append(")");
            List list = (List)DBUtil.querySQL(con,stringbuffer1.append(" and aa15.bab055 like '").append(generateKey(s, i)).append("'").toString(),"2");
            System.out.println("@@@@@"+stringbuffer1);
			Iterator iterator = list.iterator();
            i++;
            while(iterator.hasNext()) 
            {
            	Aa15 aa15 = new Aa15();
                ClassHelper.copyProperties(iterator.next(),aa15);
                if(i == getLevel(aa15.getBab055()))
                    fastarraylist.add(aa15);
                else
                    fastarraylist1.add(aa15);
            }
            list = null;
			Collection collection = a(fastarraylist1);
            for(Iterator iterator1 = fastarraylist.iterator(); iterator1.hasNext(); stringbuffer.append("/>"))
            {
            	Aa15 aa15 = (Aa15)iterator1.next();
                String s2 = aa15.getBab055();
                String s3 = StringUtil.ChineseStringToAscii(aa15.getAab054());
                stringbuffer.append("<tree text=\"").append(s3).append("\" action=\"javascript:cylb_setValue('").append(s3).append("','").append(s2).append("')\"");
                if(collection.contains(s2))
                    stringbuffer.append(" src='").append(GlobalNames.WEB_APP).append("/treeServlet?key=").append(s2).append("&amp;tree=cylb'");
            }

        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }finally {
			try {
				if (con != null) {
					con.rollback();
					con.close();
				}
			} catch (Exception ex) {
			}
		}
        stringbuffer.append("</tree>");
        String s4 = stringbuffer.toString();
        a.put(s, s4);
        return s4;
    }

    private Collection a(List list)
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = list.iterator();
        String s1 = null;
        for(; iterator.hasNext(); arraylist.add(s1))
        {
            StringBuffer stringbuffer = new StringBuffer();
            Aa15 aa15 = (Aa15)iterator.next();
            String s = aa15.getBab055();
            int i = getLevel(s);
            switch(i)
            {
            case 2: // '\002'
                s1 = stringbuffer.append(s.substring(0, 3)).append("000000").toString();
                break;

            case 3: // '\003'
                s1 = stringbuffer.append(s.substring(0, 5)).append("0000").toString();
                break;

            case 4: // '\004'
                s1 = stringbuffer.append(s.substring(0, 7)).append("00").toString();
                break;
            }
        }

        return arraylist;
    }

    String generateKey(String s, int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s1 = s;
        switch(i)
        {
        case 0: // '\0'
            stringbuffer.append("_____0000");
            break;

        case 1: // '\001'
            stringbuffer.append(s1.substring(0, 3)).append("____00");
            break;

        case 2: // '\002'
            stringbuffer.append(s1.substring(0, 5)).append("____");
            break;

        case 3: // '\003'
            stringbuffer.append(s1.substring(0, 7)).append("__");
            break;

        case 4: // '\004'
            stringbuffer.append(s1);
            break;
        }
        return stringbuffer.toString();
    }

    int getLevel(String s)
    {
        byte byte0;
        if(s.endsWith("root"))
            byte0 = 0;
        else
        if(s.endsWith("000000"))
            byte0 = 1;
        else
        if(s.endsWith("0000"))
            byte0 = 2;
        else
        if(s.endsWith("00"))
            byte0 = 3;
        else
            byte0 = 4;
        return byte0;
    }
}
