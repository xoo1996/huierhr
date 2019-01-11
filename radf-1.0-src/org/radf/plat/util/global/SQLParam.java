/**
* <p>����: ��̬SQL��䴫�ݵ���ڲ�������BEO</p>
* <p>˵��: ��ͨ��PagerUtil��ȡ�˲���ʱ�Զ����sql,inType,iytPara,outType��ֻҪ�ٴ���inPara���ּ���</p>
* <p>     ʹ�ñ�����ΪDAOͨ�����ݲ�ѯ���޸ĵĲ������壬��Ҫ�������²�����</p>
* <p>     (1)ͨ��setSql����������ִ��sql��䣬�����Ǿ�̬Ҳ�����Ƕ�̬(��?��־����)�������select��ͷ</p>
* <p>     (2)����Ƕ�̬sql���,ͨ��setInPara���洫�ݵĲ�����setInType���洫�ݵĲ������ͣ�key��1��ʼ</p>
* <p>        ����sql = select a,b,c,d from tab where d = ? and e = ? and f = ?</p>
* <p>        ��d = getInPara.get("1")</p>
* <p>     (3)����Ǿ�̬sql��䣬�����inPara��inTypeΪnull</p>
* <p>     (4)���صļ�¼ÿ����һ��hashmap���ֶ�������outPara��˳��put��hashmap��outPara�±��0��ʼ</p>
* <p>     (5)���ص���������ͨ����ڲ���outType����˵����������GlobalNames.DATA_TYPE_****����</p>
* <p>     (6)ͨ��setCount����ÿҳ��¼���������ݻ򴫵ݵĲ���<=0��Ĭ��ȡGlobalNames.PAGE_SIZE</p>
* <p>     (7)ͨ��setPage����Ҫ��ҳ��ѯ��ҳ�룬�����ݻ򴫵ݵĲ���<=0���ʾ����ҳ</p>
* <p>     (8)ͨ��setInPara��setInType������ڲ�������ڲ�������
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-10-27 17:03:32</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.util.global;

import java.util.HashMap;

import org.radf.plat.commons.PagerUtil;

public class SQLParam {
    private String sql;     //���ݵ�����sql��䣬�����Ǿ�̬��Ҳ�����Ƕ�̬�ģ����԰���Ҳ���Բ��������򲿷�
    private String countSQL;    //��ҳ��ѯ��ȡ�ܼ�¼����SQL���
    private String order="";   //sql�����򲿷֣����sql�Ѿ��������򲿷֣���˲���Ϊ��
    private HashMap inPara=null;   //where���洫�ݵ���ڲ�������ֵ�б�
    private HashMap inType=null;   //where���洫�ݵ���ڲ��������б�
    private HashMap outPara=null;   //���ڲ����ֶ������б�key��1��ʼ��ʾ����˳��
    private HashMap outType=null;   //���ڲ����ֶ������б�key��1��ʼ��ʾ����˳��
    private HashMap outTitle=null;  //���ڲ����ֶα����б�
    private HashMap outDict=null;	//���ڲ��������ֶ�ת��������OptionDictSupport.getCodeNameȡֵ
    private int pageSize=GlobalNames.PAGE_SIZE;        //ÿҳ��¼��
    private int maxPage=0;      //ҳ������
    private int page = 0;       //��ǰ��ѯ��ҳ�棬<=0�򲻷�ҳ
    private int recordCount = 0;    //��ѯ��õļ�¼����

    /**
     * ���һ������
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
     * ����ָ��key�Ĳ���
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
     * ɾ��ָ��key�Ĳ���
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
     * ���һ���������Ͷ���
     * @param key
     * @param inType
     */
    public void addInType(int key,String inType){
        String s = key + "";
        addInType(s,inType);
    }
    /**
     * ���һ���������Ͷ���
     * @param key
     * @param inType
     */
    public void addInType(int key,int inType){
        String s = key + "";
        addInType(s,inType);
    }
    /**
     * ���һ���������Ͷ���
     * @param key
     * @param inType�����ݵ������ַ�������
     */
    public void addInType(String key,String inType){
        addInType(key,buildType(inType));
    }
    /**
     * ���һ���������Ͷ���
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
     * ����ָ���Ĳ�������
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
     * ɾ��ָ���Ĳ�������
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
     * ����ָ���±������
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
     * ����ָ���±������
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
     * ���ݵķ�ҳSQL��䣬���Ժ���order����Ҳ���Բ�����
     * �������setOrder���ܴ��ݲ���
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
     * ��������ڲ�����
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
     * ���ַ����е�����ת�������ֵ�����
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
     * ��ָ���±��ֵ���������ӵ������У���������������Զ�����
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
     * ��ָ���±��ֵ���������ӵ������У���������������Զ�����
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
