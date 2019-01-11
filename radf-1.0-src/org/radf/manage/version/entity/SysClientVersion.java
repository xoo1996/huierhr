/**
* <p>标题: 客户端版本号</p>
* <p>说明: entity</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-8-2612:51:06</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class SysClientVersion extends EntitySupport{
    private String ip;              //客户端IP地址
    private String type;            //客户端类型 1-中心客户端，2-定点医疗机构
    private String tableName;       //要记录版本的表名
    private int nowVersion;         //客户端当前的版本
    private int updateVersion;      //客户端更新后版本
    private Date updateDate;        //最后更新时间
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
