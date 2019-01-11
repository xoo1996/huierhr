/**
* <p>����: ʵ����ĳ���</p>
* <p>˵��: ������ʵ����Ļ�������</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-10-811:19:30</p>
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

	//���ݶ���ǰ��Ҫ���в���ʹ�õ������ļ���sql�����
    private String fileKey = null;
    //��Ŷ���Ҫ������һ��sql��䣬ֻ�����ÿ�β�����ʱ���
//	private String sql = null;
    
    /**
     * ��ʵ�����ÿ���ֶ�ֵ����ʾ����
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
     * ��ʵ����ÿ���ֶζ���ֵnull
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
     * ��ȡʵ���൱ǰ���еĲ�����sql���keyֵ��
     * @return Returns the fileKey.
     */
    public String getFileKey() {
        return fileKey;
    }
    /**
     * �趨ʵ���൱ǰ���еĲ�����sql����keyֵ
     * @param fileKey The fileKey to set.
     */
    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

	
}
