/*
 * Created on 2006年4月26日 by 李灵超
 *
 */
package org.radf.plat.taglib.xtree;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.hibernate.util.FastHashMap;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.collections.FastArrayList;

import org.radf.manage.entity.Sc01;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.StringUtil;
import org.radf.plat.util.global.GlobalNames;

/**
 * @author Tony Yu
 */
public class SSJDRegionFactory implements Serializable {

	private Map a;

	public SSJDRegionFactory() {
		a = null;
		a = new FastHashMap();
	}

	public String getRegionListDocument(String s, String s1) {
		String s2 = s.toLowerCase();
		if (a.containsKey(s2))
			return (String) a.get(s2);
		else
			return getRegionListDocumentFromDB(s2, s1);
	}

	String getRegionListDocumentFromDB(String s, String s1) {
		Object obj = null;
		FastArrayList fastarraylist = new FastArrayList();
		FastArrayList fastarraylist1 = new FastArrayList();
		Object obj1 = null;
		StringBuffer stringbuffer = new StringBuffer(1024);
		int type=0;
        if(s.length()>4)
		{
			String root=s.substring(0,4);
			if("root".endsWith(root))
			{
				type=1;
				s=s.substring(4,s.length());
			}
		}
		stringbuffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
		stringbuffer.append("<tree>");
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			if (!s.endsWith("root")) {
				String sql = "select * from sc01 where bsc001='" + s + "'";
				List list = (List) DBUtil.querySQL(con, sql, "2");
				if (list != null && list.size() > 0) {
					BasicDynaBean sc01=(BasicDynaBean)list.get(0);
					s=(String)sc01.get("aab003");
				}
			}
			int i = getLevel(s);
			StringBuffer stringbuffer2 = (new StringBuffer(128))
					.append("select * from  sc01 where 1 = 1 ");
			if (s1 != null)
				stringbuffer2.append(" and (").append(s1).append(")");
			if (i == 0) {
				stringbuffer2.append("");
			} else {
				if (type == 1) {
					stringbuffer2.append(" and aab003='" + s + "' or length(sc01.aab003)<=2 ");
				}else
				{
					stringbuffer2.append(" and length(sc01.aab003)>="
							+ generateKey(s, i) + " and sc01.aab003 like  '" + s
							+ "%' ");
				}
				
			}
			String hql1 = stringbuffer2.append(" order by sc01.aab003")
					.toString();
			List list = (List) DBUtil.querySQL(con, hql1, "2");
			if (list != null) {
				Iterator iterator = list.iterator();

				while (iterator.hasNext()) {
					Sc01 sc01 = new Sc01();
					ClassHelper.copyProperties(iterator.next(), sc01);
					fastarraylist1.add(sc01);
					if (type == 1) {
						if (i== getLevel(sc01.getAab003()))
							fastarraylist.add(sc01);
					}else{
						if (i + 1 == getLevel(sc01.getAab003()))
							fastarraylist.add(sc01);
					}
					

				}
				list = null;
			}
			Collection collection = a(fastarraylist1);
			for (Iterator iterator2 = fastarraylist.iterator(); iterator2
					.hasNext(); stringbuffer.append("/>")) {
				Sc01 sc01 = (Sc01) iterator2.next();
				String s2 = sc01.getBsc001();
				String s4 = sc01.getAab003();
				String s3 = StringUtil.ChineseStringToAscii(sc01.getAab300());
				stringbuffer.append("<tree text=\"").append(s3).append(
						"\" action=\"javascript:ssjd_setValue('").append(s3)
						.append("','").append(s2).append("')\"");
				if (collection.contains(s4))
					stringbuffer.append(" src='").append(GlobalNames.WEB_APP)
							.append("/treeServlet?key=").append(s2).append(
									"&amp;tree=ssjd'");

			}

		} catch (Exception exception) {
			exception.getMessage();
			exception.printStackTrace();
		} finally {
			DBUtil.rollback(con);
			DBUtil.closeConnection(con);
		}
		stringbuffer.append("</tree>");
		String s4 = stringbuffer.toString();
		a.put(s, s4);
		return s4;
	}

	private Collection a(List list) {
		Vector vector = new Vector();
		Iterator iterator = list.iterator();
		String s1 = null;
		for (; iterator.hasNext(); vector.add(s1)) {
			StringBuffer stringbuffer = new StringBuffer();
			Sc01 sc01 = (Sc01) iterator.next();
			s1 = sc01.getAab003();
		}
		return vector;
	}

	String generateKey(String s, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s1 = s;
		switch (i) {
		case 0:
			break;
		case 1:
			stringbuffer.append("4");
			break;
		case 2:
			stringbuffer.append("6");
			break;
		case 3: // '\005'
			stringbuffer.append("8");
			break;
		case 4: // '\005'
			stringbuffer.append("10");
			break;
		case 5: // '\005'
			stringbuffer.append("12");
			break;
		case 6: // '\005'
			stringbuffer.append("14");
			break;
		}
		return stringbuffer.toString();
	}

	int getLevel(String s) {
		byte byte0;
		if (s.endsWith("root"))
			byte0 = 0;
		else if (s.length() == 2)
			byte0 = 1;
		else if (s.length() == 4)
			byte0 = 2;
		else if (s.length() == 6)
			byte0 = 3;
		else if (s.length() == 8)
			byte0 = 4;
		else if (s.length() == 10)
			byte0 = 5;
		else if (s.length() == 12)
			byte0 = 6;
		else
			byte0 = 7;
		return byte0;
	}
}
