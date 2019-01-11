/**
* <p>标题: 通用查询组件</p>
* <p>说明: 可以通过配置文件以及传递参数等方式进行分页查询,可以返回分页结果集合</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-11-3014:58:42</p>
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
    //分页参数
    private SQLParam para = null;

    
    /**
     * 构造函数,初始化配置文件
     */
    public Pager(){
        super.init();
        this.para = new SQLParam();

    }
    
    /**
     * 按照多记录表单列表方式，获取指定页面信息
     * @param page      要显示的页码
     * @param pageSize  本页显示的行数
     * @return
     * @throws AppException
     */
    public Collection getPageData(int page,int pageSize) throws AppException{
        this.para.setPageSize(pageSize);
        return getPageData(page);
    }
    /**
     * 获取页面数据集，慎用
     * @param page      要显示的页码
     * @param count     每页显示的记录数
     * @return
     * @throws AppException
     */
    public ResultSet getPageData(Connection con,int page,int pageSize) throws AppException{
        this.para.setPageSize(pageSize);
        this.para.setPage(page);
        return getResultData(con,this.para);
    }
    /**
     * 按照多记录表单列表方式，获取指定页面信息
     * @param page         要显示的页码
     * @return
     * @throws AppException
     */
    public Collection getPageData(int page) throws AppException{
        this.para.setPage(page); //当前要显示的页面
        return getPageData(this.para);
    }
    
    /**
     * 根据传递的获取记录数目的SQL语句，获取满足条件的记录数目
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
     * 根据传递的获取记录数目的SQL语句，获取满足条件的记录数目
     * @param con 外部传递的数据库链接
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
     * 获取返回数据结果集字段名称定义列表
     * @param key
     * @return
     */
    public String getCols(String key){
        return para.getOutPara(key);
    }
    /**
     * 获取返回数据结果集字段名称定义列表
     * @param key
     * @return
     */
    public String getCols(int key){
    	return para.getOutPara(key+"");
    }
    /**
     * 获取要显示的总列数
     * @return
     */
    public int getColsSize(){
    	return para.getOutParaSize();
    }
    /**
     * 返回指定列所对应的字典类别代码
     * @param key
     * @return
     */
    public String getColsDict(String key){
    	return para.getOutDict(key);
    }
    /**
     * 返回指定列所对应的字典类别代码
     * @param key
     * @return
     */
    public String getColsDict(int key){
    	return para.getOutDict(key+"");
    }
    /**
     * 从配置文件中获取指定行所配置的SQL参数,配置内容包括最多5部分描述:
     * (1)完整的SQL语句,可以通过setSQL方法实现
     * (2)SQL语句中查询结果集中返回字段列表,可以通过addOutValue和addOutType方法实现
     * (3)SQL查询条件,需要外部传递参数值的字段列表,可以通过addInValue和addInType方法实现
     * (4)每页显示的行数,没有则取系统默认值
     * (5)获取分页查询总记录数的SQL语句
     * @param key
     * @throws AppException
     */
    public void setParaByFileKey(String key)throws AppException{
        this.para = getSQLParam(key);
    }
    
    /**
     * 传递查询数据的SQL语句
     * @param sql
     */
    public void setSQL(String sql){
        this.para.setSql(sql);
    }
    /**
     * 传递获取数据记录条数的sql语句。
     * 如果配置文件中已经包含获取记录的sql语句，这里的设置会覆盖配置文件中的设置
     * @param countSql
     */
    public void setCountSql(String countSql) {
        this.para.setCountSQL(countSql);
    }
    /**
     * 传递查询语句的排序参数，并组装成排序部分的SQL语句。排序列可以是多个字段
     * @param orderCols 字段名列表
     * @param orderType 排序方式
     */
    public void setOrder(String orderCols,String orderType){
        this.para.setOrder(" Order by " + orderCols + " " + orderType);
    }
    
    
    
    /**
     * <p>添加SQL语句中where条件部分需要传递的参数值和参数类型</p>
     * <p>例如:select a,b from t where c = ? and d = ?;
     * 则?部分的变量c传递为addInValue(1,c,PagerUtil.TYPE_IN_),变量d传递为addInValue(2,d,PagerUtil.TYPE_ST_)</p>
     * @param key 参数的顺序编号,第一个从1开始
     * @param obj 参数值
     * @param type 参数类型，如果通过配置传递类型，此参数设置为0，否则会覆盖配置文件中类型
     */
    public void addInValue(String key,Object obj,int type){
        addInValue(key,obj);
        if(type!=0){
            addInType(key,type);
        }
    }
    /**
     * 返回结果数据中，将指定列key中的值按照标准代码字典表获取对应name。
     * @see OptionDictSupport#getCodeName(String, String)
     * @param key
     * @param code
     */
    public void addOutDict(String key,String code){
    	this.para.addOutDict(key, code);
    }
    
    /**
     * <p>添加SQL语句中where条件部分需要传递的参数值</p>
     * <p>参数只能是String或Date类型，int等类型都要转换成String</p>
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
     * <p>添加SQL语句中where条件部分需要传递的参数值</p>
     * <p>例如:select a,b from t where c = ? and d = ?
     * 则?部分的变量c传递为addInValue(1,c),变量d传递为addInValue(2,d)</p>
     * @param key   参数的顺序编号,第一个从1开始
     * @param obj   参数值
     */
    protected void addInValue(String key,Object obj){
        this.para.addInPara(key,obj);
    }
    /**
     * 删除指定的SQL语句中where条件部分参数值
     * @inParam key 参数的顺序编号,第一个从1开始
     */
    protected void removeInValue(String key){
        this.para.removeInPara(key);
    }
    /**
     * <p>添加SQL语句中where条件部分需要传递的参数类型</p>
     * @param key       参数的顺序编号,第一个从1开始
     * @param type      参数类型
     */
    protected void addInType(String key,int type){
        this.para.addInType(key,type);
    }
    /**
     * 删除指定的SQL语句中where条件部分参数类型
     * @param key   参数的顺序编号,第一个从1开始
     */
    protected void removeInType(String key){
        this.para.removeInType(key);
    }
    /**
     * 添加指定的返回列名称，key值必须和addColsType中对应。
     * select语句中可能有很多返回值，但是只有通过此方法
     * @param key   参数的顺序编号,第一个从1开始
     * @param name  返回的数据的字段名
     */
    protected void addColsValue(String key,String name){
        this.para.addOutPara(key,name);
    }
    /**
     * 增加返回参数类型，传递到类型是与SQL配置文件中定义相同的字符串
     * @param key
     * @param type
     */
    protected void addColsType(String key,String type){
        this.para.addOutType(key,type);
    }
     
    /**
     * 增加返回参数类型，传递的类型是通过PageUtil.TYPE_***得到的静态变量
     * @param key
     * @param type
     */
    protected void addColsType(String key,int type){
        this.para.addOutType(key,type);
    }
//    /**
//     * <p>添加SQL语句中select返回结果部分字段名称列表</p>
//     * <p>例如:select a,b from t where c = ? and d = ?,  则结果集为["a","b"]</p>
//     * @param value   字段名称列表
//     */
//    protected void setColsValue(HashMap value){
//        this.para.setOutPara(value);
//    }
//    /**
//     * 添加SQL语句中select返回结果部分字段类型
//     * @param type  字段类型数组
//     */
//    protected void setColsType(HashMap type){
//        this.para.setOutType(type);
//    }

}
