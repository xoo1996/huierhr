/**
* <p>标题: 实体类的超类</p>
* <p>说明: 定义了实体类的基本操作</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-10-811:19:30</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.util.entity;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public class EntitySupport implements Serializable{
    /**
	 * 
	 */

	//传递对象当前所要进行操作使用的配置文件中sql语句编号
    private String fileKey = null;
    //存放对象将要操作的一个sql语句，只能针对每次操作临时存放
//	private String sql = null;
    
    /**
     * 将实体类的每个字段值都显示出来
     */
    public String toString() {
        StringBuffer results = new StringBuffer();
        Class clazz = getClass();

        results.append(getClass().getName() + "\n");

        Field[] fields = clazz.getDeclaredFields();

        try {
            AccessibleObject.setAccessible(fields, true);
            for (int i = 0; i < fields.length; i++) {
                results.append("\t" + fields[i].getName() + "=" +
                               fields[i].get(this) + "\n");
            }
        } catch (Exception e) {
            // ignored!
        }

        return results.toString();
    }
    /**
     * 对实体类每个字段都赋值null
     *
     */
    public void clear(){
        Class clazz = getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            AccessibleObject.setAccessible(fields, true);
            for (int i = 0; i < fields.length; i++) {
                fields[i].set(this,null);
            }
        } catch (Exception e) {
        }
    }
    /**
     * 获取实体类当前进行的操作的sql语句key值。
     * @return Returns the fileKey.
     */
    public String getFileKey() {
        return fileKey;
    }
    /**
     * 设定实体类当前进行的操作的sql语句的key值
     * @param fileKey The fileKey to set.
     */
    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

	
}
