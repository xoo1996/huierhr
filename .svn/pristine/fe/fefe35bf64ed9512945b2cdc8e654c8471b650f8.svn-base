/**
* <p>标题: 动态SQL语句传递到入口参数集合BEO</p>
* <p>说明: 当通过PagerUtil获取此参数时自动获得sql,inType,iytPara,outType，只要再传入inPara部分即可</p>
* <p>     使用本类作为DAO通用数据查询或修改的参数定义，需要传递以下参数：</p>
* <p>     (1)通过setSql传递完整的执行sql语句，可以是静态也可以是动态(以?标志变量)，语句以select开头</p>
* <p>     (2)如果是动态sql语句,通过setInPara保存传递的参数，setInType保存传递的参数类型，key以1开始</p>
* <p>        例：sql = select a,b,c,d from tab where d = ? and e = ? and f = ?</p>
* <p>        则：d = getInPara.get("1")</p>
* <p>     (3)如果是静态sql语句，则入口inPara和inType为null</p>
* <p>     (4)返回的记录每行是一个hashmap表，字段名按照outPara的顺序put到hashmap表，outPara下标从0开始</p>
* <p>     (5)返回的数据类型通过入口参数outType定义说明，类型用GlobalNames.DATA_TYPE_****设置</p>
* <p>     (6)通过setCount传递每页记录数，不传递或传递的参数<=0则默认取GlobalNames.PAGE_SIZE</p>
* <p>     (7)通过setPage传递要分页查询的页码，不传递或传递的参数<=0则表示不分页</p>
* <p>     (8)通过setInPara和setInType传递入口参数和入口参数类型
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-10-27 17:03:32</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.util.global;

import java.util.HashMap;

import org.radf.plat.commons.PagerUtil;

public class SQLParam {
    private String sql;     //传递到完整sql语句，可以是静态度也可以是动态的，可以包括也可以不包括排序部分
    private String countSQL;    //分页查询获取总记录数的SQL语句
    private String order="";   //sql的排序部分，如果sql已经包含排序部分，则此部分为空
    private HashMap inPara=null;   //where后面传递的入口参数变量值列表
    private HashMap inType=null;   //where后面传递的入口参数类型列表
    private HashMap outPara=null;   //出口参数字段名称列表，key从1开始表示返回顺序
    private HashMap outType=null;   //出口参数字段类型列表，key从1开始表示返回顺序
    private HashMap outTitle=null;  //出口参数字段标题列表
    private HashMap outDict=null;	//出口参数代码字段转换类型用OptionDictSupport.getCodeName取值
    private int pageSize=GlobalNames.PAGE_SIZE;        //每页记录数
    private int maxPage=0;      //页面总数
    private int page = 0;       //当前查询的页面，<=0则不分页
    private int recordCount = 0;    //查询获得的记录总数

    /**
     * 添加一个参数
     * @inParam key
     * @inParam obj
     */
    public void addInPara(String key,Object obj){
        if(inPara==null){
            inPara = new HashMap();
        }
        this.inPara.put(key,obj);
    }
    /**
     * 查找指定key的参数
     * @inParam key
     * @return
     */
    public Object findInPara(String key){
        if(this.inPara!=null){
            return this.inPara.get(key);
        }else{
            return null;
        }
        
    }
    /**
     * 删除指定key的参数
     * @inParam key
     * @inParam obj
     */
    public void removeInPara(String key){
        if(this.inPara!=null){
            this.inPara.remove(key);
        }
    }
    /**
     * @return Returns the para.
     */
    public Object getInPara(String key) {
    	if(inPara==null){
    		return null;
    	}else{
    		return inPara.get(key);
    	}
    }
    public int getInParaSize(){
    	if(inPara==null){
    		return 0;
    	}else{
    		return inPara.size();
    	}
    }
    /**
     * 添加一个参数类型定义
     * @param key
     * @param inType
     */
    public void addInType(int key,String inType){
        String s = key + "";
        addInType(s,inType);
    }
    /**
     * 添加一个参数类型定义
     * @param key
     * @param inType
     */
    public void addInType(int key,int inType){
        String s = key + "";
        addInType(s,inType);
    }
    /**
     * 添加一个参数类型定义
     * @param key
     * @param inType，传递的类型字符串定义
     */
    public void addInType(String key,String inType){
        addInType(key,buildType(inType));
    }
    /**
     * 添加一个参数类型定义
     * @param key
     * @param inType
     */
    public void addInType(String key,int inType){
        if(this.inType==null){
            this.inType = new HashMap();
        }
        this.inType.put(key,new Integer(inType));
    }
    /**
     * 查找指定的参数类型
     * @inParam key
     * @return
     * @throws Exception
     */
    public int findInType(String key){
        if(this.inType==null){
            return PagerUtil.TYPE_NULL_;
        }else{
            return ((Integer)this.inType.get(key)).intValue();
        }
    }
    /**
     * 删除指定的参数类型
     * @inParam key
     */
    public void removeInType(String key){
        if(this.inType!=null){
            this.inType.remove(key);
        }
    }
    /**
     * @return Returns the inType.
     */
    public Integer getInType(String key) {
    	if(inType==null){
    		return null;
    	}else{
    		return (Integer)inType.get(key);
    	}
    }
    public int getInTypeSize(){
    	if(inType==null){
    		return 0;
    	}else{
    		return inType.size();
    	}
    }
    public void addOutPara(String key,String code){
        if(outPara==null){
            outPara = new HashMap();
        }
        this.outPara.put(key,code);
    }
    public String getOutPara(String key){
    	if(outPara==null){
    		return null;
    	}else{
    		return (String)outPara.get(key);
    	}
    }
    public int getOutParaSize(){
    	if(outPara==null){
    		return 0;
    	}else{
    		return outPara.size();
    	}
    }
    /**
     * 增加指定下标的数据
     * @param key
     * @param type
     */
    public void addOutType(String key,int type){
        if(outType==null){
            outType = new HashMap();
        }
        this.outType.put(key,type+"");
    }
    /**
     * 增加指定下标的数据
     * @param key
     * @param type
     */
    public void addOutType(String key,String type){
        if(outType==null){
            outType = new HashMap(10);
        }
        this.outType.put(key,buildType(type)+"");
    }
    /**
     * @return Returns the outType.
     */
    public String getOutType(String key) {
    	if(outType==null){
    		return null;
    	}else{
    		return (String)outType.get(key);
    	}
    }
    public int getOutTypeSize(){
    	if(outType==null){
    		return 0;
    	}else{
    		return outType.size();
    	}
    }
    public void addOutTitle(String key,String title){
        if(outTitle==null){
            outTitle = new HashMap(10);
        }
        this.outTitle.put(key,title);
    }
    /**
     * @return Returns the outTitle.
     */
    public String getOutTitle(String key) {
    	if(outTitle==null){
    		return null;
    	}else{
    		return (String)outTitle.get(key);
    	}
    }
    public int getOutTitleSize(){
    	if(outTitle==null){
    		return 0;
    	}else{
    		return outTitle.size();
    	}
    }
    public void addOutDict(String key,String code){
    	if(outDict==null){
    		outDict = new HashMap(10);
    	}
    	this.outDict.put(key, code);
    }
    public String getOutDict(String key){
    	if(outDict==null){
    		return null;
    	}else{
    		return (String)outDict.get(key);
    	}
    }
    public int getOutDictSize(){
    	if(outDict==null){
    		return 0;
    	}else{
    		return outDict.size();
    	}
    }

    
    
    /**
     * @return Returns the count.
     */
    public int getPageSize() {
        return pageSize;
    }
    /**
     * @param count The count to set.
     */
    public void setPageSize(int pageSize) {
        if(pageSize>0){
            this.pageSize = pageSize;
        }else if(this.pageSize<=0){
        	this.pageSize=GlobalNames.PAGE_SIZE<=0?10:GlobalNames.PAGE_SIZE;
        }
    }
    /**
     * @return Returns the maxPage.
     */
    public int getMaxPage() {
        return maxPage;
    }
    /**
     * @param maxPage The maxPage to set.
     */
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    /**
     * @return Returns the sql.
     */
    public String getSql() {
        return sql;
    }
    /**
     * 传递的分页SQL语句，可以含有order部分也可以不含。
     * 如果含则setOrder不能传递参数
     * @inParam sql The sql to set.
     */
    public void setSql(String sql) {
        if(sql!=null){
//            this.sql = sql.trim().toUpperCase();
            this.sql = sql.trim();
        }else{
            this.sql = null;
        }
    }
    /**
     * @return Returns the countSQL.
     */
    public String getCountSQL() {
        return countSQL;
    }
    /**
     * @param countSQL The countSQL to set.
     */
    public void setCountSQL(String countSQL) {
        if(countSQL!=null){
//            this.countSQL = countSQL.trim().toUpperCase();
            this.countSQL = countSQL.trim();
        }else{
            this.countSQL = null;
        }
    }
    /**
     * @return Returns the recordCount.
     */
    public int getRecordCount() {
        return recordCount;
    }
    /**
     * @param recordCount The recordCount to set.
     */
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
    /**
     * @return Returns the page.
     */
    public int getPage() {
        return page;
    }
    /**
     * @param page The page to set.
     */
    public void setPage(int page) {
        if(page>0){
            this.page = page;
        }
    }
    /**
     * @return Returns the order.
     */
    public String getOrder() {
        return order;
    }
    /**
     * @param order The order to set.
     */
    public void setOrder(String order) {
        this.order = order;
    }
    /**
     * 清除所有内部变量
     */
    public void clear(){
        this.sql = null;
        this.inPara = null;
        this.inType = null;
        this.outPara = null;
        this.outType = null;
        this.outTitle = null;
        this.outDict = null;
        this.pageSize=GlobalNames.PAGE_SIZE;
        this.maxPage=0;
        this.page = 0;
    }

    
    
    
    
    
    
    /**
     * 将字符串中的类型转换成数字的类型
     * @param type
     * @return
     */
    private int buildType(String type){
        type = type.trim().toUpperCase();
        int i = PagerUtil.TYPE_NULL_;
        if(!type.startsWith("NULL_")){
            if(type.startsWith("ST_")){
                i = PagerUtil.TYPE_ST_;
            }else if(type.startsWith("IN_")){
                i = PagerUtil.TYPE_IN_;
            }else if(type.startsWith("LO_")){
                i = PagerUtil.TYPE_LO_;
            }else if(type.startsWith("SH_")){
                i = PagerUtil.TYPE_SH_;
            }else if(type.startsWith("FL_")){
                i = PagerUtil.TYPE_FL_;
            }else if(type.startsWith("DO_")){
                i = PagerUtil.TYPE_DO_;
            }else if(type.startsWith("DA_")){
                i = PagerUtil.TYPE_DA_;
            }else if(type.startsWith("BL_")){
                i = PagerUtil.TYPE_BL_;
            }else if(type.startsWith("CL_")){
                i = PagerUtil.TYPE_CL_;
            }
        }
        return i;
    }
    /**
     * 将指定下标和值的数据增加到数组中，如果数组已满则自动扩充
     * @param in
     * @param key
     * @param value
     * @return
     */
    private int[] arrayAdd(int[] in,int key,int value){
        if(in==null){
            in = new int[key+1];
            in[key] = value;
            return in;
        }else if(in.length<=key){
            int[] out = new int[key+1];
            System.arraycopy(in,0,out,0,in.length);
            out[key]= value;
            return out;
//          System.arraycopy(starData, 0, data_All, 0, size_count);
        }else{
            in[key]=value;
            return in;
        }
    }
    /**
     * 将指定下标和值的数据增加到数组中，如果数组已满则自动扩充
     * @param in
     * @param key
     * @param value
     * @return
     */
    private String[] arrayAdd(String[] in,int key,String value){
        if(in==null){
            in = new String[key+1];
            in[key] = value;
            return in;
        }else if(in.length<=key){
            String[] out = new String[key+1];
            System.arraycopy(in,0,out,0,in.length);
            out[key]= value;
            return out;
//          System.arraycopy(starData, 0, data_All, 0, size_count);
        }else{
            in[key]=value;
            return in;
        }
    }
//    /**
//     * @inParam inPara The inPara to set.
//     */
//    public void setInPara(HashMap inPara) {
//        this.inPara = inPara;
//    }
    
//    /**
//     * @inParam inType The inType to set.
//     */
//    public void setInType(HashMap inType) {
//        this.inType = inType;
//    }
    
//    /**
//     * @param outPara The outPara to set.
//     */
//    public void setOutPara(HashMap outPara) {
//        this.outPara = outPara;
//    }
    
//    /**
//     * @param outType The outType to set.
//     */
//    public void setOutType(HashMap outType) {
//        this.outType = outType;
//    }
//    /**
//     * @param outTitle The outTitle to set.
//     */
//    public void setOutTitle(HashMap outTitle) {
//        this.outTitle = outTitle;
//    }
    public void finalize(){
        clear();
    }
}
