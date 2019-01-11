// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.cp.a;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.radf.manage.department.dto.DeptDTO;
import org.radf.manage.entity.Sc08;
import org.radf.plat.commons.ClassHelper;
import org.radf.plat.util.global.GlobalNames;

public class c {

	private static String a[] = { "functionid", "location", "title", "parent",
			"type", "description", "log", "orderno", "checktype" };

	public c() {
	}

	public static int a(String s) {
		for (int i = 0; i < a.length; i++)
			if (a[i].equals(s))
				return i;

		return -1;
	}

	public static Sc08 _mthif(Collection collection, String s) {
		if (s == null || collection == null)
			return null;
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			Sc08 functiondto = (Sc08) iterator.next();
			if (s.equals(functiondto.getBsc016()))
				return functiondto;
		}

		return null;
	}

	public static List a(Collection collection, String s) {
		if (s == null || collection == null)
			return null;
		ArrayList arraylist = new ArrayList();
		for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
			Sc08 functiondto = (Sc08) iterator.next();
			if (s.equals(functiondto.getBsc019()))
				if (GlobalNames.RIGHT_LEVEL.equals(GlobalNames.MENU_BUTTON))
					arraylist.add(functiondto);
				else if (GlobalNames.RIGHT_LEVEL.equals(GlobalNames.MENU_LEAF)
						&& !GlobalNames.MENU_BUTTON.equals(functiondto
								.getBsc021()))
					arraylist.add(functiondto);
		}

		if (arraylist.iterator().hasNext())
			return arraylist;
		else
			return null;
	}

	// 获取二级菜单
	public static List a1(Collection collection,String bsc001) {
		if (bsc001 == null || collection == null)
			return null;
		ArrayList arraylist = new ArrayList();
		ArrayList list = (ArrayList) collection;
		for (int i = 0; i < list.size(); i++) {
			DeptDTO dto = new DeptDTO();
			ClassHelper.copyProperties(list.get(i), dto);
	            if(bsc001.equals(dto.getBsc999()))
	                arraylist.add(dto);
		}
		if (arraylist.size() > 0)
			return arraylist;
		else
			return null;
	}
	public static List _mthdo(Collection collection, String s) {
		if (s == null || collection == null)
			return null;
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		Sc08 functiondto = _mthif(collection, s);
		if (functiondto == null)
			return null;
		arraylist.add(functiondto);
		arraylist1.addAll(arraylist);
		Object obj = null;
		boolean flag = true;
		while (flag) {
			ArrayList arraylist2 = new ArrayList();
			for (Iterator iterator = arraylist1.iterator(); iterator.hasNext();) {
				Sc08 functiondto1 = (Sc08) iterator.next();
				List list = a(collection, functiondto1.getBsc016());
				if (list != null) {
					arraylist.removeAll(list);
					arraylist.addAll(list);
					arraylist2.removeAll(list);
					arraylist2.addAll(list);
				}
			}

			if (arraylist2.iterator().hasNext())
				arraylist1 = arraylist2;
			else
				flag = false;
		}
		return arraylist;
	}

	public static String _mthfor(Collection collection, String s) {
		if (collection != null && s != null) {
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				Sc08 functiondto = (Sc08) iterator.next();
				if (s.equals(functiondto.getBsc016()))
					return "2";
			}

		}
		return null;
	}

	public static List a(List list) {
		if (list == null)
			return null;
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Object aobj[] = (Object[]) list.get(i);
			Sc08 functiondto = new Sc08();
			functiondto.setBsc016((String) aobj[a("bsc016")]);
			functiondto.setBsc017((String) aobj[a("bsc017")]);
			functiondto.setBsc018((String) aobj[a("bsc018")]);
			functiondto.setBsc019((String) aobj[a("bsc019")]);
			functiondto.setBsc021((String) aobj[a("bsc021")]);
			functiondto.setBsc022((String) aobj[a("bsc022")]);
			functiondto.setBsc024((String) aobj[a("bsc024")]);
			functiondto.setBsc020((BigDecimal) aobj[a("bsc020")]);
			arraylist.add(functiondto);
		}

		return arraylist;
	}

}
