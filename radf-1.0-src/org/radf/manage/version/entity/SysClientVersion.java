/**
* <p>����: �ͻ��˰汾��</p>
* <p>˵��: entity</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-8-2612:51:06</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class SysClientVersion extends EntitySupport{
    private String ip;              //�ͻ���IP��ַ
    private String type;            //�ͻ������� 1-���Ŀͻ��ˣ�2-����ҽ�ƻ���
    private String tableName;       //Ҫ��¼�汾�ı���
    private int nowVersion;         //�ͻ��˵�ǰ�İ汾
    private int updateVersion;      //�ͻ��˸��º�汾
    private Date updateDate;        //������ʱ��
    /**
     * @return Returns the ip.
     */
    public String getIp() {
        return ip;
    }
    /**
     * @param ip The ip to set.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    /**
     * @return Returns the nowVersion.
     */
    public int getNowVersion() {
        return nowVersion;
    }
    /**
     * @param nowVersion The nowVersion to set.
     */
    public void setNowVersion(int nowVersion) {
        this.nowVersion = nowVersion;
    }
    /**
     * @return Returns the tableName.
     */
    public String getTableName() {
        return tableName;
    }
    /**
     * @param tableName The tableName to set.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**
     * @return Returns the type.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * @return Returns the updateDate.
     */
    public Date getUpdateDate() {
        return updateDate;
    }
    /**
     * @param updateDate The updateDate to set.
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * @return Returns the updateVersion.
     */
    public int getUpdateVersion() {
        return updateVersion;
    }
    /**
     * @param updateVersion The updateVersion to set.
     */
    public void setUpdateVersion(int updateVersion) {
        this.updateVersion = updateVersion;
    }

}
