/**
* <p>标题: 关于字典类型数据的内存加载基类</p>
* <p>说明: 此方式定义了一个通用的字典加载类</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-11-2914:45:28</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.commons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.radf.manage.param.entity.Aa10;

public abstract class OptionDictSupport {
    private static TreeMap nameMap = new TreeMap();		//可以显示的列表
    private static TreeMap defaultMap = new TreeMap();  //字典默认选项定义，没有则代表无默认值
    private static TreeMap remarkMap = new TreeMap();	//备注列表
    private static TreeMap noViewMap = new TreeMap();	//不可以显示的列表
    /**
     * 将给定的字典内容插入到字典列表中
     * @param type          大类标志
     * @param code          某个大类下选项编码
     * @param name          选项名称
     * @param selected      当前的选项是否是此大类下的默认选中项目
     * @param remark		备注
     * @param view			是否显示在下拉列表中
     */
    public static synchronized void add(String type,String code,String name,boolean selected,String remark,boolean view){
    	if(view){
        	//name
            if (nameMap.get(type) == null) {
                TreeMap temp = new TreeMap();
                temp.put(code, name);
                nameMap.put(type, temp);
            } else {
                TreeMap temp = (TreeMap) nameMap.get(type);
                temp.put(code,name);
            }
    	}else{
        	//name
            if (noViewMap.get(type) == null) {
                TreeMap temp = new TreeMap();
                temp.put(code, name);
                noViewMap.put(type, temp);
            } else {
                TreeMap temp = (TreeMap) noViewMap.get(type);
                temp.put(code,name);
            }
    	}
        //remark
        if (remarkMap.get(type) == null) {
            TreeMap temp = new TreeMap();
            temp.put(code, remark);
            remarkMap.put(type, temp);
        } else {
            TreeMap temp = (TreeMap) remarkMap.get(type);
            temp.put(code,remark);
        }
        
        if(selected&&view){
            defaultMap.put(type, code);
        }
    }
    /**
     * 将给定的字典内容修改到字典列表中
     * @param type          大类标志
     * @param code          某个大类下选项编码
     * @param name          选项名称
     * @param selected      当前的选项是否是此大类下的默认选中项目
     */
    public final static synchronized void update(String type,String code,String name,boolean selected,String remark,boolean view){
        add(type,code,name,selected,remark,view);
    }
    /**
     * 删除指定代码类表下某个具体选项，如果选项编号为空，则删除整个代码类
     * @param type          大类标志
     * @param code          某个大类下选项编码
     */
    public final static synchronized void delete(String type, String code) {
        if(code==null){
        	nameMap.remove(type);
        	remarkMap.remove(type);
        	noViewMap.remove(type);
            defaultMap.remove(type);
        }else if (type != null || !type.equals("")) {
            TreeMap temp1 = getNameMap(type);
            if(temp1!=null){
            	temp1.remove(code);
                defaultMap.remove(type);
            }
            TreeMap temp2= getRemarkMap(type);
            if(temp2!=null){
            	temp2.remove(code);
            }
            TreeMap temp3= getNoViewMap(type);
            if(temp3!=null){
            	temp3.remove(code);
            }
        }
    }

    /**
     * 获取某种类型下的代码字典列表
     * @return Returns the map(code,name)
     */
    public final static TreeMap getNameMap(String type) {
        return (TreeMap)nameMap.get(type);
    }
    private final static TreeMap getRemarkMap(String type){
    	return (TreeMap)remarkMap.get(type);
    }
    private final static TreeMap getNoViewMap(String type){
    	return (TreeMap)noViewMap.get(type);
    }
    /**
     * 返回某种类别下的代码默认值
     * @return Returns the code.
     */
    public final static String getDefaultMap(String type) {
        return (String)defaultMap.get(type);
    }
    /**
     * 根据字段名字和小类编号获取小类名称
     * @param type 大类编号
     * @param code 小类编号
     * @return 小类名称
     */
    
    public final static String getCodeName(String type, String code) {
        if (type == null || type.equals("")||code==null||code.equals("")){
            return "";
        }else{
            // 获取指定大类type的数据
        	String name = null;
            TreeMap temp1 = getNameMap(type);
            if(temp1!=null){
                name = (String)temp1.get(code);
            }
            if(name==null||name.equalsIgnoreCase("")){
            	TreeMap temp2 = getNoViewMap(type);
            	if(temp2!=null){
            		name = (String)temp2.get(code);
            	}
            }
            if(name==null)
            	name = "";
            return name;
        }
    }
    /**
     * 根据字段名字和小类编号获取小类备注
     * @param type 大类编号
     * @param code 小类编号
     * @return 小类备注
     */
    public final static String getCodeRemark(String type,String code){
        if (type == null || type.equals("")||code==null&&code.equals("")){
            return "";
        }else{
            TreeMap temp = getRemarkMap(type);
            if(temp!=null){
            	return (String)temp.get(code);
            }else{
            	return "";
            }
        }
    }
    /**
     * 根据当前大类编号获取下拉列表，并使用系统默认选中编码
     * @param type  大类编号
     * @return
     */
    public final static String getComboBoxString(String type) {
        return getComboBoxString(type,null);
    }
    /**
     * 根据当前大类编号获取下拉列表，并用指定编码code数据作为默认选择。
     * 如果code=null则使用数据库中的选项为默认值。
     * @param type          大类编号
     * @param defaultCode   作为默认选项的项目编号
     * @return
     */
    public final static String getComboBoxString(String type,String defaultCode) {
        StringBuffer stringbuffer = new StringBuffer(128);
        if (type == null || type.equals("")) {

        } else {
            //如果传递参数没有指定默认选中编号，则取系统的默认选中项
            if(defaultCode==null||defaultCode.equals("")){
                defaultCode = getDefaultMap(type);
            }
            if(defaultCode==null||defaultCode.equals("")){
                stringbuffer.append("<option value='' selected>--请选择--</option>");
            }else{
                stringbuffer.append("<option value='' >--请选择--</option>");
            }
            
            // 获取当前type的数据
            TreeMap treemap = getNameMap(type);
			if(treemap==null)
				return stringbuffer.toString();
			
            Iterator iterator = treemap.keySet().iterator();
            Iterator iterator1 = treemap.values().iterator();

            for (; iterator.hasNext(); stringbuffer.append("</option>")) {
                String code = (String) iterator.next();
                String name = (String) iterator1.next();
                stringbuffer.append("<option value='");
                stringbuffer.append(code);
                if (code.equals(defaultCode))
                    stringbuffer.append("' selected>");
                else
                    stringbuffer.append("' >");
                stringbuffer.append(name);
            }

        }
        return stringbuffer.toString();
    }
    /**
     * 获取某种类型下的代码字典列表
     * @return Returns the map(code,name)
     */
	 public final static TreeMap getNameMap(HttpServletRequest request,String type) {

		 return (TreeMap)request.getSession().getServletContext().getAttribute(type);
		 //return (TreeMap)pageContext.getAttribute(type);
	 }
	 /**
	     * 根据字段名字和小类编号获取小类名称
	     * @param type 大类编号
	     * @param code 小类编号
	     * @return 小类名称
	     */
	    
	    public final static String getCodeName(HttpServletRequest request,String type, String code) {
	        if (type == null || type.equals("")||code==null||code.equals("")){
	            return "";
	        }else{
	            // 获取指定大类type的数据
	        	String name = null;
	            TreeMap temp1 = getNameMap(request,type);
	            if(temp1!=null){
	                name = (String)temp1.get(code);
	            }	           
	            if(name==null)
	            	name = "";
	            return name;
	        }
	    }
	    /**
	     * 获取某种类型下的代码字典列表,
	     * @return Returns the ArrayList(code,name)
	     */
		 public final static ArrayList getNameAray(HttpServletRequest request,String type) {
			 ArrayList list =new ArrayList();
			 TreeMap map=(TreeMap)request.getSession().getServletContext().getAttribute(type);
		
			 Iterator it = map.entrySet().iterator();
			  while (it.hasNext()) {
			   // entry的输出结果如key0=value0等
			   Map.Entry entry =(Map.Entry) it.next();
			   Object key = entry.getKey();
			   Object value=entry.getValue();
			   if(key==null)
			   {
				   key="";
			   }
			   if(value==null)
			   {
				   value="";
			   }
			   Aa10 aa10 =new Aa10();
			   aa10.setAaa100(type);
			   aa10.setAaa102(key.toString());
			   aa10.setAaa103(value.toString());
			   list.add(aa10);
			  }
			 return list;
		 }
}
