package org.radf.plat.commons;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SubmitDataMap implements Serializable {

	private LinkedHashMap a;

    public SubmitDataMap(String s)
    {
        a = new LinkedHashMap();
        a(s);
    }

    public SubmitDataMap()
    {
        a = new LinkedHashMap();
        a(((String) (null)));
    }

    public SubmitDataMap(HttpServletRequest httpservletrequest)
    {
        a = new LinkedHashMap();
        a(httpservletrequest);
    }

    private void a(HttpServletRequest httpservletrequest)
    {
        String s;
        String as[];
//        try {
        for(Enumeration enumeration = httpservletrequest.getParameterNames(); enumeration.hasMoreElements(); a.put(s, as))
        {
            s = (String)enumeration.nextElement();
           
//				httpservletrequest.setCharacterEncoding("BGK");
				as = httpservletrequest.getParameterValues(s);
			
		 }
//        } 
//         catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//            
//        }
        
    }

    private void a(String s)
    {
        if(s != null)
        {
            String as[] = s.split("&");
            for(int i = 0; i < as.length; i++)
            {
                String as1[] = as[i].split("=");
                Object obj = (List)a.get(as1[0]);
                if(obj == null)
                {
                    obj = new ArrayList();
                    if(as1.length > 1)
                        ((List) (obj)).add(as1[1]);
                    else
                        ((List) (obj)).add(null);
                    a.put(as1[0], obj);
                } else
                if(as1.length > 1)
                    ((List) (obj)).add(as1[1]);
                else
                    ((List) (obj)).add(null);
            }

        }
    }

    public String[] getParameterValues(String s)
    {
        Object obj = a.get(s);
        if(obj == null)
            return null;
        if(obj instanceof String[])
        {
            return (String[])obj;
        } else
        {
            List list = (List)obj;
            return (String[])list.toArray(new String[list.size()]);
        }
    }

    /*public String getParameter(String s)
    {
        List list = (List)a.get(s);
        if(list == null)
            return null;
        else
            return (String)list.get(0);
    }*/
}
