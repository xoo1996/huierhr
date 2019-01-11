package org.radf.apps.userinfo.form;

import java.util.ArrayList;

public class EduList extends ArrayList{
	 private Class itemClass;

	 public EduList(Class itemClass) {
	  this.itemClass = itemClass;
	 }

	 public Object get(int index) {
	  try {
	   while (index >= size()) {
	    add(itemClass.newInstance());
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  return super.get(index);
	 }
}
