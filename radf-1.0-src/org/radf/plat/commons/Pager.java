/**
* <p>����: ͨ�ò�ѯ���</p>
* <p>˵��: ����ͨ�������ļ��Լ����ݲ����ȷ�ʽ���з�ҳ��ѯ,���Է��ط�ҳ�������</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-11-3014:58:42</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.commons;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.SQLParam;

public class Pager extends PagerUtil{
    //��ҳ����
    private SQLParam para = null;

    
    /**
     * ���캯��,��ʼ�������ļ�
     */
    public Pager(){
        super.init();
        this.para = new SQLParam();

    }
    
    /**
     * ���ն��¼���б�ʽ����ȡָ��ҳ����Ϣ
     * @param page      Ҫ��ʾ��ҳ��
     * @param pageSize  ��ҳ��ʾ������
     * @return
     * @throws AppException
     */
    public Collection getPageData(int page,int pageSize) throws AppException{
        this.para.setPageSize(pageSize);
        return getPageData(page);
    }
    /**
     * ��ȡҳ�����ݼ�������
     * @param page      Ҫ��ʾ��ҳ��
     * @param count     ÿҳ��ʾ�ļ�¼��
     * @return
     * @throws AppException
     */
    public ResultSet getPageData(Connection con,int page,int pageSize) throws AppException{
        this.para.setPageSize(pageSize);
        this.para.setPage(page);
        return getResultData(con,this.para);
    }
    /**
     * ���ն��¼���б�ʽ����ȡָ��ҳ����Ϣ
     * @param page         Ҫ��ʾ��ҳ��
     * @return
     * @throws AppException
     */
    public Collection getPageData(int page) throws AppException{
        this.para.setPage(page); //��ǰҪ��ʾ��ҳ��
        return getPageData(this.para);
    }
    
    /**
     * ���ݴ��ݵĻ�ȡ��¼��Ŀ��SQL��䣬��ȡ���������ļ�¼��Ŀ
     * @return
     * @throws AppException
     */
    public int getRowCount() throws AppException{
        
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            int recordCount = getMaxRowCount(con,para);
            this.para.setRecordCount(recordCount);
            return recordCount;
        } catch (Exception ex) {
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+31, ex);
        }finally{
            DBUtil.closeConnection(con);
        }
    }
    public int getPageSize(){
        return getPageSize(para);
    }
    /**
     * ���ݴ��ݵĻ�ȡ��¼��Ŀ��SQL��䣬��ȡ���������ļ�¼��Ŀ
     * @param con �ⲿ���ݵ����ݿ�����
     * @return
     * @throws AppException
     */
    public int getRowCount(Connection con) throws AppException{
        
        try {
            int recordCount = getMaxRowCount(con,para);
            this.para.setRecordCount(recordCount);
            return recordCount;
        } catch (Exception ex) {
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+31, ex);
        }
    }
    /**
     * ��ȡ�������ݽ�����ֶ����ƶ����б�
     * @param key
     * @return
     */
    public String getCols(String key){
        return para.getOutPara(key);
    }
    /**
     * ��ȡ�������ݽ�����ֶ����ƶ����б�
     * @param key
     * @return
     */
    public String getCols(int key){
    	return para.getOutPara(key+"");
    }
    /**
     * ��ȡҪ��ʾ��������
     * @return
     */
    public int getColsSize(){
    	return para.getOutParaSize();
    }
    /**
     * ����ָ��������Ӧ���ֵ�������
     * @param key
     * @return
     */
    public String getColsDict(String key){
    	return para.getOutDict(key);
    }
    /**
     * ����ָ��������Ӧ���ֵ�������
     * @param key
     * @return
     */
    public String getColsDict(int key){
    	return para.getOutDict(key+"");
    }
    /**
     * �������ļ��л�ȡָ���������õ�SQL����,�������ݰ������5��������:
     * (1)������SQL���,����ͨ��setSQL����ʵ��
     * (2)SQL����в�ѯ������з����ֶ��б�,����ͨ��addOutValue��addOutType����ʵ��
     * (3)SQL��ѯ����,��Ҫ�ⲿ���ݲ���ֵ���ֶ��б�,����ͨ��addInValue��addInType����ʵ��
     * (4)ÿҳ��ʾ������,û����ȡϵͳĬ��ֵ
     * (5)��ȡ��ҳ��ѯ�ܼ�¼����SQL���
     * @param key
     * @throws AppException
     */
    public void setParaByFileKey(String key)throws AppException{
        this.para = getSQLParam(key);
    }
    
    /**
     * ���ݲ�ѯ���ݵ�SQL���
     * @param sql
     */
    public void setSQL(String sql){
        this.para.setSql(sql);
    }
    /**
     * ���ݻ�ȡ���ݼ�¼������sql��䡣
     * ��������ļ����Ѿ�������ȡ��¼��sql��䣬��������ûḲ�������ļ��е�����
     * @param countSql
     */
    public void setCountSql(String countSql) {
        this.para.setCountSQL(countSql);
    }
    /**
     * ���ݲ�ѯ�����������������װ�����򲿷ֵ�SQL��䡣�����п����Ƕ���ֶ�
     * @param orderCols �ֶ����б�
     * @param orderType ����ʽ
     */
    public void setOrder(String orderCols,String orderType){
        this.para.setOrder(" Order by " + orderCols + " " + orderType);
    }
    
    
    
    /**
     * <p>���SQL�����where����������Ҫ���ݵĲ���ֵ�Ͳ�������</p>
     * <p>����:select a,b from t where c = ? and d = ?;
     * ��?���ֵı���c����ΪaddInValue(1,c,PagerUtil.TYPE_IN_),����d����ΪaddInValue(2,d,PagerUtil.TYPE_ST_)</p>
     * @param key ������˳����,��һ����1��ʼ
     * @param obj ����ֵ
     * @param type �������ͣ����ͨ�����ô������ͣ��˲�������Ϊ0������Ḳ�������ļ�������
     */
    public void addInValue(String key,Object obj,int type){
        addInValue(key,obj);
        if(type!=0){
            addInType(key,type);
        }
    }
    /**
     * ���ؽ�������У���ָ����key�е�ֵ���ձ�׼�����ֵ���ȡ��Ӧname��
     * @see OptionDictSupport#getCodeName(String, String)
     * @param key
     * @param code
     */
    public void addOutDict(String key,String code){
    	this.para.addOutDict(key, code);
    }
    
    /**
     * <p>���SQL�����where����������Ҫ���ݵĲ���ֵ</p>
     * <p>����ֻ����String��Date���ͣ�int�����Ͷ�Ҫת����String</p>
     * @param key
     * @param v
     */
    public void addInValue(Vector v){
        if (v != null) {
            for (int row = 0; row < v.size(); row++) {
                Object obj = v.elementAt(row);
                if(obj instanceof java.sql.Date || obj instanceof java.util.Date){
                    addInType(row+1+"",TYPE_DA_);
                }else{
                    addInType(row+1+"",TYPE_ST_);
                }
                addInValue(row+1+"",obj);
            }
        }
    }
    /**
     * <p>���SQL�����where����������Ҫ���ݵĲ���ֵ</p>
     * <p>����:select a,b from t where c = ? and d = ?
     * ��?���ֵı���c����ΪaddInValue(1,c),����d����ΪaddInValue(2,d)</p>
     * @param key   ������˳����,��һ����1��ʼ
     * @param obj   ����ֵ
     */
    protected void addInValue(String key,Object obj){
        this.para.addInPara(key,obj);
    }
    /**
     * ɾ��ָ����SQL�����where�������ֲ���ֵ
     * @inParam key ������˳����,��һ����1��ʼ
     */
    protected void removeInValue(String key){
        this.para.removeInPara(key);
    }
    /**
     * <p>���SQL�����where����������Ҫ���ݵĲ�������</p>
     * @param key       ������˳����,��һ����1��ʼ
     * @param type      ��������
     */
    protected void addInType(String key,int type){
        this.para.addInType(key,type);
    }
    /**
     * ɾ��ָ����SQL�����where�������ֲ�������
     * @param key   ������˳����,��һ����1��ʼ
     */
    protected void removeInType(String key){
        this.para.removeInType(key);
    }
    /**
     * ���ָ���ķ��������ƣ�keyֵ�����addColsType�ж�Ӧ��
     * select����п����кܶ෵��ֵ������ֻ��ͨ���˷���
     * @param key   ������˳����,��һ����1��ʼ
     * @param name  ���ص����ݵ��ֶ���
     */
    protected void addColsValue(String key,String name){
        this.para.addOutPara(key,name);
    }
    /**
     * ���ӷ��ز������ͣ����ݵ���������SQL�����ļ��ж�����ͬ���ַ���
     * @param key
     * @param type
     */
    protected void addColsType(String key,String type){
        this.para.addOutType(key,type);
    }
     
    /**
     * ���ӷ��ز������ͣ����ݵ�������ͨ��PageUtil.TYPE_***�õ��ľ�̬����
     * @param key
     * @param type
     */
    protected void addColsType(String key,int type){
        this.para.addOutType(key,type);
    }
//    /**
//     * <p>���SQL�����select���ؽ�������ֶ������б�</p>
//     * <p>����:select a,b from t where c = ? and d = ?,  ������Ϊ["a","b"]</p>
//     * @param value   �ֶ������б�
//     */
//    protected void setColsValue(HashMap value){
//        this.para.setOutPara(value);
//    }
//    /**
//     * ���SQL�����select���ؽ�������ֶ�����
//     * @param type  �ֶ���������
//     */
//    protected void setColsType(HashMap type){
//        this.para.setOutType(type);
//    }

}
