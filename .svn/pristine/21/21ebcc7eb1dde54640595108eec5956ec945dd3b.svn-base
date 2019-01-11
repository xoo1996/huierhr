/**
* <p>标题: 系统版本表</p>
* <p>说明: 记录系统参数表或其他需要管理版本的表的版本标记</p>
* <p>  目前：需要记录版本的表数据发生变动时，此表对应记录版本标记+1，</p>
* <p>       客户端检验版本后，需要重新下载对应表所有数据；</p>
* <p>  以后考虑：原始表中增加一个版本字段。数据增加和修改时，修改版本表同时修改原始表版本字段，
* <p>       删除记录时，被删除数据记录到删除历史表中。
* <p>       通过只下载上次变动以后数据，减少客户端下载数据量，加快访问速度。
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-6-1316:40:07</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class SysVersion extends EntitySupport{
    private String tableName;       //要记录版本的表名
    private int version;            //当前版本号，采用流水+1方式变动
    private Date createtime;        //此版本数据创建时间
    private String note;            //关于此版本的备注说明，一般为"增加"、"修改"、"删除"
    private String type;            //表更新方式,1-全表更新，2-变动更新
    private String bpo;             //对应的BPO类名
    private String bz;              //标志：1-有效，其他-出错
    /**
     * @return Returns the createtime.
     */
    public Date getCreatetime() {
        return createtime;
    }
    /**
     * @param createtime The createtime to set.
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    /**
     * @return Returns the note.
     */
    public String getNote() {
        return note;
    }
    /**
     * @param note The note to set.
     */
    public void setNote(String note) {
        this.note = note;
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
     * @return Returns the version.
     */
    public int getVersion() {
        return version;
    }
    /**
     * @param version The version to set.
     */
    public void setVersion(int version) {
        this.version = version;
    }
    /**
     * @return Returns the bpo.
     */
    public String getBpo() {
        return bpo;
    }
    /**
     * @param bpo The bpo to set.
     */
    public void setBpo(String bpo) {
        this.bpo = bpo;
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
     * @return Returns the bz.
     */
    public String getBz() {
        return bz;
    }
    /**
     * @param bz The bz to set.
     */
    public void setBz(String bz) {
        this.bz = bz;
    }
    
}
