/**
* <p>����: ϵͳ�汾��</p>
* <p>˵��: ��¼ϵͳ�������������Ҫ����汾�ı�İ汾���</p>
* <p>  Ŀǰ����Ҫ��¼�汾�ı����ݷ����䶯ʱ���˱��Ӧ��¼�汾���+1��</p>
* <p>       �ͻ��˼���汾����Ҫ�������ض�Ӧ���������ݣ�</p>
* <p>  �Ժ��ǣ�ԭʼ��������һ���汾�ֶΡ��������Ӻ��޸�ʱ���޸İ汾��ͬʱ�޸�ԭʼ��汾�ֶΣ�
* <p>       ɾ����¼ʱ����ɾ�����ݼ�¼��ɾ����ʷ���С�
* <p>       ͨ��ֻ�����ϴα䶯�Ժ����ݣ����ٿͻ����������������ӿ�����ٶȡ�
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-6-1316:40:07</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.entity;

import java.util.Date;

import org.radf.plat.util.entity.EntitySupport;

public class SysVersion extends EntitySupport{
    private String tableName;       //Ҫ��¼�汾�ı���
    private int version;            //��ǰ�汾�ţ�������ˮ+1��ʽ�䶯
    private Date createtime;        //�˰汾���ݴ���ʱ��
    private String note;            //���ڴ˰汾�ı�ע˵����һ��Ϊ"����"��"�޸�"��"ɾ��"
    private String type;            //����·�ʽ,1-ȫ����£�2-�䶯����
    private String bpo;             //��Ӧ��BPO����
    private String bz;              //��־��1-��Ч������-����
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
