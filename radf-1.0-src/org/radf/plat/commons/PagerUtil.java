
package org.radf.plat.commons;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.exception.ManageInputException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalNames;
import org.radf.plat.util.global.SQLParam;
/**
* <p>����: ��ҳ���PagerUtil</p>
* <p>˵��: �ṩ��ҳ��ѯ��̨�߼���ش���,�����о�Ϊ��̬����,���ܱ�ʵ����</p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-10-31 13:08:18</p>
*
* @author zqb
* @version 1.0
*/
public abstract class PagerUtil {
    private static boolean _INIT_ = false;  //�Ƿ��Ѿ���ʼ������ֹ�ظ��ĳ�ʼ����
    /**�������Ͷ���: null*/
    public final static int TYPE_NULL_=0;   //null
    /**�������Ͷ���: long*/
    public final static int TYPE_LO_=2;     //long
    /**�������Ͷ���: int*/
    public final static int TYPE_IN_=4;     //int
    /**�������Ͷ���: short*/
    public final static int TYPE_SH_=5;     //short
    /**�������Ͷ���: folat*/
    public final static int TYPE_FL_=6;     //folat
    /**�������Ͷ���: double*/
    public final static int TYPE_DO_=8;     //double
    /**�������Ͷ���: string*/
    public final static int TYPE_ST_=12;    //string
    /**�������Ͷ���: blob*/
    public final static int TYPE_BL_=2004;  //blob
    /**�������Ͷ���: clob*/
    public final static int TYPE_CL_=2005;  //clob
    /**�������Ͷ���: date*/
    public final static int TYPE_DA_=91;    //date
    
    /**
     * ��ʼ�����ò���
     * �����Ĵ��������0x
     */
    public static void init(){
        if(_INIT_){
            //�Ѿ���ʼ��
        }else{
            //ȡ��sql�����ļ��Ĵ��λ��
            InputStream is = PagerUtil.class.getResourceAsStream(GlobalNames.FILE_SQL_URL);
            if(is!=null){
                GlobalNames.SQL_MAP = null;
                GlobalNames.SQL_MAP = new HashMap();
                try {
                    Properties properties = new Properties();
                    properties.load(is);
                    Enumeration en = properties.propertyNames();
                    while( en.hasMoreElements()){
                        String key=(String)en.nextElement();
                        String code = properties.getProperty(key);
						//lwd20070926���Ϊ��һ��ģ��һ��sql�ļ�
						Properties properties1 = new Properties();
						InputStream is1 = PagerUtil.class.getResourceAsStream(code);
	                    properties1.load(is1);
	                    Enumeration en1 = properties1.propertyNames();
	                    while( en1.hasMoreElements()){
	                        String key1=(String)en1.nextElement();
	                        String code1 = properties1.getProperty(key1);
	                        key1 = key1.trim().toLowerCase();
	                        GlobalNames.SQL_MAP.put(key1,buildSQLParam(code1));
	                    }
						
						//end
                        //key = key.trim().toLowerCase();
                        //GlobalNames.SQL_MAP.put(key,buildSQLParam(code));
                    }
                    if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
                        System.out.println("���ļ�"+GlobalNames.FILE_SQL_URL+"����SQL�����ɣ���"+GlobalNames.SQL_MAP.size()+"����¼");
                    }
                    _INIT_ = true;
                }catch (Exception ex){           
                    System.out.println("��ȡ�����ļ�����:" + GlobalNames.FILE_SQL_URL);
                }
            }
        }
    }
    public static String getFileSQL(String key)throws SQLException{
        SQLParam para = (SQLParam)GlobalNames.SQL_MAP.get(key.trim().toLowerCase());
        if(para==null){
            throw new SQLException("�����ļ���û���ҵ����Ϊ "+key+" ��sql���");
        }
        return para.getSql();
    }
    /**
     * ��ȡҳ���С��ÿҳ�����ʾ����������
     * @return
     */
    protected static int getPageSize(SQLParam para){
        return para.getPageSize();
    }

    /**
     * ͨ�ò�ѯ������������ھ�̬����ڲ�����SQL��䣩
     * @param sqlid   ��ѯ���ı��
     * @return Collection  ��ѯ�Ľ������object���б�
     * @throws AppException �쳣, �����Ĵ��������3x
     */
    protected static Collection getPageData(String  sqlID) throws AppException {
        
        try {
            //���ݴ��ݵ�sqlid��ȡSQLParam����
            SQLParam para = getSQLParam(sqlID);
            return getPageData(para);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+31, ex);
        }
    }
    
    /**
     * <p>���ݴ����para������ȡ��ѯ������ϣ�������ָ����ҳ������ʾ</p>
     * @see org.radf.plat.commons.PagerUtil#doQueryBySQL(Connection, SQLParam)
     * @param para
     * @return
     * @throws AppException
     */
    protected static Collection getPageData(SQLParam para)throws AppException{
        Connection con = null;
        try {
            con = DBUtil.getConnection();
            return getPageData(con,para);           
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+31, ex);
        } finally {
            DBUtil.closeConnection(con);
        }
    }
    /**
     * <p>���ݴ����para������ȡ��ѯ������ϣ�������ָ����ҳ������ʾ</p>
     * <p>(1)�����sql�������������ģ������Ǿ�̬Ҳ����ʹ��̬(��?��־����);</p>
     * <p>(2)����Ƕ�̬sql���,��ڵ�para.inPara�б��洫�ݵĲ�����para.inType���洫�ݵĲ������͡�
     *       key��1��ʼ��������sql�б�����ͬ</p>
     * <p>  ����sql = select a,b,c,d from tab where d = ? and e = ? and f = ?</p>
     * <p>  ��d = inMap.get("1")��e = inMap.get("2"), f=inMap.get(3)</p>
     * <p>(3)����Ǿ�̬sql��䣬��para.inMap = null;</p>
     * <p>(4)���صļ�¼ÿ����һ��hashmap���ֶ�������para.outPara��˳��put��hashmap��</p>
     * <p>  ����������put("1",a),put("2",b),put("3",c),put("4",d)</p>
     * <p>(5)���ص���������ͨ����ڲ���outType����˵��</p>
     * <p>(6)�����sql��������SELECT��ͷ�Ĳ�ѯ���</p>
     * <p>(7)���ݵ�page<=0 ��ʾ����ҳ </p>
     * <p>(8)page>0ʱ��count<=0��ʾʹ��ϵͳĬ��ҳ��С(GlobalNames.PAGE_SIZE)���ؽ����</p>
     * 
     * @param con
     * @param para
     * @return
     * @throws AppException,�����Ĵ��������4x
     */
    protected static Collection getPageData(Connection con,SQLParam para)
    throws AppException{
        ResultSet rs = null;
        try{
            //��ȡ��ҳ������ݼ�
            rs = getResultData(con,para);
            // ��װ���ؽ��
            Collection retList = new Vector();
            while (rs.next()) {
                HashMap map = CreateRsetMap(para,rs);
                retList.add(map);
            }
            return retList;
         }catch(SQLException se){
             throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+43,se.getMessage()+"����ѯȡ���ݳ������鷵�����ݲ������͸��������Ƿ���ȷ");
         }finally{
             DBUtil.closeResult(rs);
         }
    }
    /**
     * ���ݴ��ݵ�sql����Լ���������ȡ���صļ�¼����
     * @param con
     * @param sql
     * @param inPara
     * @param inType
     * @return
     * @throws AppException
     */
    protected static int getMaxRowCount(Connection con,SQLParam para) throws AppException{
        int reCount = 0;
        if(para.getCountSQL()==null){
            para.setCountSQL("select count(*) from (" + para.getSql() + ")");
        }else if(!para.getCountSQL().substring(0,6).toUpperCase().equalsIgnoreCase("SELECT")){
            throw new AppException("���ݷǷ��������SQL��䲻����SELECT��ʼ�Ĳ�ѯ���");
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(para.getCountSQL());
            ps.clearParameters();
            int i = 0;
           // ����Ƕ�̬��SQL��䣬�򴫵ݲ���
           if(para.getInParaSize()>0){
               //У����������Ͳ������͸����Ƿ���ͬ������ͬ�����
               if(para.getInTypeSize()!=para.getInParaSize()){
                   throw new SQLException("���ݷǷ�����ڲ����ĸ�������ڲ������͵ĸ�������");
               }
               for(i=0;i<para.getInParaSize();){
                   i++;
                   String key = (new Integer(i)).toString();
                   // �жϴ��ݵĲ����Ƿ�Ϊ��
                   if(para.getInPara(key)==null||para.getInPara(key).equals("")){
                	   ps.setString(i,(String)para.getInPara(key));
                   }else{
                       switch(((Integer)para.getInType(key)).intValue()){
                           case TYPE_LO_:{
                               ps.setLong(i,((Long)para.getInPara(key)).longValue());
                               break;
                           }case TYPE_DO_:{
                               ps.setDouble(i,((Double)para.getInPara(key)).doubleValue());
                               break;
                           }case TYPE_IN_:{
                               ps.setInt(i,((Integer)para.getInPara(key)).intValue());
                               break;
                           }case TYPE_SH_:{
                               ps.setShort(i,((Short)para.getInPara(key)).shortValue());
                               break;
                           }case TYPE_ST_:{
                               ps.setString(i,(String)para.getInPara(key));
                               break;
                           }case TYPE_DA_:{
                               ps.setDate(i,(java.sql.Date)para.getInPara(key));
                               break;
                           }case TYPE_BL_:{
                               ps.setBlob(i,(Blob)para.getInPara(key));
                               break;
                           }case TYPE_CL_:{
                               ps.setClob(i,(Clob)para.getInPara(key));
                               break;
                           }case TYPE_NULL_:{
                               ps.setNull(i,TYPE_NULL_);
                               break;
                           }default:{
                               ps.setNull(i,TYPE_NULL_);
                           }
                       }
                   }             
               }
           }
           // ִ�в�ѯ
           long inTime = System.currentTimeMillis();
           rs = ps.executeQuery();
           long outTime = System.currentTimeMillis();
           if(rs.next()){
               reCount =rs.getInt(1);
           }
           if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
               System.out.println("��ȡ������ִ�е�SQL�������ʱ��Ϊ��"+(outTime-inTime));
           }
        }catch(SQLException se){
            DBUtil.closeResStat(rs,ps);            
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+49,"ִ�в�ѯ��������SQL����������ݼ����Ͷ����Ƿ���ȷ��"+se.getMessage());
        }finally{
            DBUtil.closeResStat(rs,ps);
        }
        return reCount;
    }
    /**
     * ����ָ����sql����ţ��������ļ��в�ѯ��Ӧ��sql��������
     * @param key   sql�����
     * @return      sql��������
     * @throws AppException �쳣,�����Ĵ��������2x
     */
    protected static SQLParam getSQLParam(String key) throws AppException{
        if(key == null){
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION + 21,"�����Ҫ��ѯsql�����Ϊnull");
        }
        if(GlobalNames.SQL_MAP == null){
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION + 22,"û�г�ʼ����ȡ�����ļ���Ϣ,���߳�ʼ��ʧ��");
        }
        SQLParam para = (SQLParam)GlobalNames.SQL_MAP.get(key.toLowerCase());
        if(para==null){
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION + 23,"�޷����ļ�"+GlobalNames.FILE_SQL_URL+"�л�ȡ���Ϊ"+key+"��SQL��䶨��");
        }
        return para;
    }
    /**
     * <p>���ݴ����para��������ȡ��ѯ������ݼ���</p>
     * <p>(1)�����sql��������SELECT��ͷ�Ĳ�ѯ���</p>
     * <p>(2)�����sql�������������ģ������Ǿ�̬Ҳ����ʹ��̬(��?��־����);</p>
     * <p>(3)����Ƕ�̬sql���,��ڵ�para.inPara�б��洫�ݵĲ�����para.inType���洫�ݵĲ������͡�
     *       key��1��ʼ��������sql�б�����ͬ</p>
     * <p>  ����sql = select a,b,c,d from tab where d = ? and e = ? and f = ?</p>
     * <p>  ��d = inMap.get("1")��e = inMap.get("2"), f=inMap.get(3)</p>
     * <p>(4)����Ǿ�̬sql��䣬��para.inMap = null;</p>
     * 
     * @param con
     * @param para
     * @return  ��ȡ�Ľ�����ݼ���ResultSet
     * @throws AppException
     */
    protected static ResultSet getResultData(Connection con,SQLParam para)throws AppException{
        String sql = null;
        try {
            // ����У��
            sql = para.getSql() + para.getOrder();
            if(sql==null){
                throw new SQLException("���ݷǷ��������SQL���Ϊ��");
            }else if(!sql.substring(0,6).toUpperCase().equalsIgnoreCase("SELECT")){
                throw new SQLException("���ݷǷ��������SQL��䲻����SELECT��ʼ�Ĳ�ѯ���");
            }
            if(para.getOutParaSize()==0||para.getOutTypeSize()==0){
                throw new SQLException("���ݷǷ�����ѯ������ز����Ľṹδ����1");
            }
            if(para.getOutParaSize()!=para.getOutTypeSize()){
                throw new SQLException("���ݷǷ�����ѯ������ز����Ľṹ�����Ͳ�ƥ��");
            }
            if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
                System.out.println("��ҳ���ִ�е�SQL���Ϊ��"+sql);
            }
            // ��ҳ��SQL���
            if(para.getPage()>0){
                sql = DBUtil.pageSQL(sql);
           }
        }catch(SQLException se){
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+41,se);
        }
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(sql);
            ps.clearParameters();
            int i = 0;
           // ����Ƕ�̬��SQL��䣬�򴫵ݲ���
           if(para.getInParaSize()>0){
               //У����������Ͳ������͸����Ƿ���ͬ������ͬ�����
               if(para.getInTypeSize()!=para.getInParaSize()){
                   throw new SQLException("���ݷǷ�����ڲ����ĸ�������ڲ������͵ĸ�������");
               }
               for(i=0;i<para.getInParaSize();){
                   i++;
                   String key = (new Integer(i)).toString();
                   // �жϴ��ݵĲ����Ƿ�Ϊ��
                   if(para.findInPara(key)==null||para.findInPara(key).equals("")){
                       ps.setNull(i,para.findInType(key));
                   }else{
                       switch(para.findInType(key)){
                           case TYPE_LO_:{
                               ps.setLong(i,((Long)para.findInPara(key)).longValue());
                               break;
                           }case TYPE_DO_:{
                               ps.setDouble(i,((Double)para.findInPara(key)).doubleValue());
                               break;
                           }case TYPE_IN_:{
                               ps.setInt(i,((Integer)para.findInPara(key)).intValue());
                               break;
                           }case TYPE_SH_:{
                               ps.setShort(i,((Short)para.findInPara(key)).shortValue());
                               break;
                           }case TYPE_ST_:{
                               ps.setString(i,(String)para.findInPara(key));
                               break;
                           }case TYPE_DA_:{
                               ps.setDate(i,(java.sql.Date)para.findInPara(key));
                               break;
                           }case TYPE_BL_:{
                               ps.setBlob(i,(Blob)para.findInPara(key));
                               break;
                           }case TYPE_CL_:{
                               ps.setClob(i,(Clob)para.findInPara(key));
                               break;
                           }case TYPE_NULL_:{
                               ps.setNull(i,TYPE_NULL_);
                               break;
                           }default:{
                               ps.setNull(i,TYPE_NULL_);
                           }
                       }
                   }             
               }
           }
           // ��ҳ����
           if(para.getPage()>0){
               ps.setInt(i+1, para.getPage()*para.getPageSize()+1);
               ps.setInt(i+2, (para.getPage()-1)*para.getPageSize()+1);
           }
           // ִ�в�ѯ
           long inTime = System.currentTimeMillis();
           rs = ps.executeQuery();
           long outTime = System.currentTimeMillis();
           if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
               System.out.println("��ҳ���ִ�е�SQL�������ʱ��Ϊ��"+(outTime-inTime));
           }
        }catch(SQLException se){
            DBUtil.closeResStat(rs,ps);            
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+42,"ִ�в�ѯ��������SQL����������ݼ����Ͷ����Ƿ���ȷ��"+se.getMessage());
        }
        return rs;
    }
    /**
     * ��ָ���н��д����ֶ�ת��
     * @param para
     * @param colsKey
     * @param code
     * @return
     */
    protected static String DataDictConvert(SQLParam para,String colsKey,String code){
        String dictType = (String)para.getOutDict(colsKey);
        return DataDictConvert(dictType,code);
    }
    /**
     * ��ָ���н��д����ֶ�ת��
     * @param dictType	�������
     * @param code
     * @return
     */
    protected static String DataDictConvert(String dictType,String code){
        if(dictType!=null){
            return OptionDictSupport.getCodeName(dictType, code);
        }else{
        	return code;
        }
    }
    /**
     * �Բ�ѯ��������н�����������һ��ɢ�б�HashMap�����ص�keyΪ�ֶ����е�˳��
     * ���ĳ��Ҫ����ת���������ת���������ݿ��б���ת��Ϊ��������ݣ�ת����ͳһΪ�ַ���
     * @param para  Ҫ���ص��������͵Ķ���
     * @param rset  ��ѯ��ȡ�����ݽ������
     * @return
     * @exception SQLException
     */
    private static HashMap CreateRsetMap(SQLParam para,ResultSet rset)
    throws SQLException,ManageInputException {
        HashMap hm=new HashMap();
        for(int i = 1;i<=para.getOutParaSize();i++){
            String s = (String)para.getOutPara(i+"");
            //�ж�������Ƿ���Ҫת��
            int type = TypeCast.stringToInt((String)para.getOutType(i+""),"�����������",false);
            switch(type){
                case TYPE_LO_:{
                    long l = rset.getLong(s);
                    hm.put(i+"",DataDictConvert(para,i+"",l+""));
                    break;
                }case TYPE_DO_:{
                    double d = rset.getDouble(s);
                    hm.put(i+"",DataDictConvert(para,i+"",d+""));
                    break;
                }case TYPE_IN_:{
                    int in = rset.getInt(s);
                    hm.put(i+"",DataDictConvert(para,i+"",in+""));
                    break;
                }case TYPE_SH_:{
                    short sh = rset.getShort(s);
                    hm.put(i+"",DataDictConvert(para,i+"",sh+""));
                    break;
                }case TYPE_ST_:{
                	String str = rset.getString(s);
                    hm.put(i+"",DataDictConvert(para,i+"",str));
                    break;
                }case TYPE_DA_:{
                	hm.put(i+"",rset.getDate(s));
                    break;
                }case TYPE_BL_:{
                    byte[] b = TypeCast.blobToBytes(rset.getBlob(s));
                    hm.put(i+"",b);
                    break;
                }case TYPE_CL_:{
                    hm.put(i+"",TypeCast.clobToString(rset.getClob(s)));
                    break;
                }case TYPE_NULL_:{
                    hm.put(i+"",null);
                    break;
                }default:{
                    hm.put(i+"",rset.getObject(s));
                }
            }
        }
        return hm;
    }
    /**
     * ��ʽ�������ļ��е�SQL��������ת����SQLParam���Ͳ�������HashMap��
     * �ļ���ÿ��SQL������ʽΪA_&B_&C_&D_&E_&F���������£�
     *  (1)_&��ʾ�ָ����SQL�������ֽ��ABCDE�岿�֣�����ABC���ֲ���ȱ��(Ϊ�ձ����ָ��)
     *  (2)A����Ϊ������SQL��䣬�����Ǿ�̬Ҳ�����Ƕ�̬������Ϊ��
     *  (3)BΪsql��where���֮������Ҫ�Ļ��߷��ص��ֶ��������ֶ�����+�»���+�ֶ�����ɣ���
     *     �˸�ʽ��Ϊ�˼���tbase��Ҳ���Բ������ֶ����ƣ�����Ϊ��
     *  (4)CΪ���ؽ����������Ҫ���ֶ��������ֶ�����+�»���+�ֶ�����ɣ�������Ϊ��
     *  (5)D����Ϊÿҳ��ʾ�����������û�д˲�����Ĭ��ȡϵͳ��ҳ����
     *  (6)E����Ϊ��ȡ��ҳ��ѯ�ܼ�¼����SQL��䣬��E��������D��(����Ϊ�գ�
     *  (7)F�����Ƿ�ҳ��ʾʱҳ��ı��ⲿ�֣�����Ϊ�գ������Ϊ�ձ����C����
     * @param sql   ��SQL�����ļ��л�ȡ��sql���
     * @return
     * @exception AppException �쳣����,�����Ĵ��������1x
     */
    private static SQLParam buildSQLParam(String sql)throws AppException{
        if(sql==null){
            return null;
        }else{
            //�ֽ������ļ���sql����������
            String[] s = StringUtil.getAsStringArray(sql,"_&");
            SQLParam para = new SQLParam();
            if(s.length==1){
                para.setSql(s[0]);
            }else if(s.length<3){
                throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+11,"SQL�����ֽ����");
            }else{
                //--------------------λ��s[0]��A:sql���--------------------
                para.setSql(s[0]);
                //--------------------λ��s[1]��B:��ڲ���--------------------
                String[] in = StringUtil.getAsStringArray(s[1],",");
                for(int i = 0;i<in.length;i++){
                    para.addInType(i+1,in[i]);
                }
                //--------------------λ��s[2]��C:���ڲ���--------------------
                String[] out = StringUtil.getAsStringArray(s[2],",");
                for(int i = 0;i<out.length;i++){
                    para.addOutType(i+1+"",out[i]);
                    para.addOutPara(i+1+"",StringUtil.getSubstring(out[i],"_"));
                }
                try{
                    //---------------λ��s[3]:D:ҳ���С--------------------
                    if(s.length>3){
                        try{
                            para.setPageSize(Integer.parseInt(s[3]));
                        }catch(NumberFormatException e){
                            para.setPageSize(GlobalNames.PAGE_SIZE);
                        }
                    }else{
                        para.setPageSize(GlobalNames.PAGE_SIZE);
                    }
                    //--------------λ��s[4]��E:��ѯ�ܼ�¼�����--------------------
                    if(s.length>4){
                        para.setCountSQL(s[4]);
                    }else{
                        
                    }
                    //--------------λ��s[5]��F:������ʾ����-----------------------
//                    if(s.length>5){
//                        String[] title = StringUtil.getAsStringArray(s[5],",");
//                        if(title.length!=out.length){
//                            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+12,"SQL�����ļ���ʽ����"+sql);
//                        }
//                        for(int i = 1;i<=title.length;i++){
//                            para.addOutTitle(i+"",title[i]);
//                        }
//                    }
                }catch(Exception e){
                    //���Ķβ�������ʱ�����ԣ��Զ�ʹ��ȫ�ֱ���
                    System.out.println("SQL�����ļ���ʽ����"+sql);
                    throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+12,"SQL�����ļ���ʽ����"+sql);
                }
            }
            return para;
        }
    }
}
