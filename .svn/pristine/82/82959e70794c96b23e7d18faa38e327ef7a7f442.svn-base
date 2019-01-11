
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
* <p>标题: 分页组件PagerUtil</p>
* <p>说明: 提供分页查询后台逻辑相关处理,此类中均为静态方法,不能被实例化</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-10-31 13:08:18</p>
*
* @author zqb
* @version 1.0
*/
public abstract class PagerUtil {
    private static boolean _INIT_ = false;  //是否已经初始化，防止重复的初始化用
    /**数据类型定义: null*/
    public final static int TYPE_NULL_=0;   //null
    /**数据类型定义: long*/
    public final static int TYPE_LO_=2;     //long
    /**数据类型定义: int*/
    public final static int TYPE_IN_=4;     //int
    /**数据类型定义: short*/
    public final static int TYPE_SH_=5;     //short
    /**数据类型定义: folat*/
    public final static int TYPE_FL_=6;     //folat
    /**数据类型定义: double*/
    public final static int TYPE_DO_=8;     //double
    /**数据类型定义: string*/
    public final static int TYPE_ST_=12;    //string
    /**数据类型定义: blob*/
    public final static int TYPE_BL_=2004;  //blob
    /**数据类型定义: clob*/
    public final static int TYPE_CL_=2005;  //clob
    /**数据类型定义: date*/
    public final static int TYPE_DA_=91;    //date
    
    /**
     * 初始化配置参数
     * 产生的错误码包括0x
     */
    public static void init(){
        if(_INIT_){
            //已经初始化
        }else{
            //取得sql配置文件的存放位置
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
						//lwd20070926添加为了一个模块一个sql文件
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
                        System.out.println("从文件"+GlobalNames.FILE_SQL_URL+"加载SQL语句完成，共"+GlobalNames.SQL_MAP.size()+"条记录");
                    }
                    _INIT_ = true;
                }catch (Exception ex){           
                    System.out.println("读取配置文件出错:" + GlobalNames.FILE_SQL_URL);
                }
            }
        }
    }
    public static String getFileSQL(String key)throws SQLException{
        SQLParam para = (SQLParam)GlobalNames.SQL_MAP.get(key.trim().toLowerCase());
        if(para==null){
            throw new SQLException("配置文件中没有找到编号为 "+key+" 的sql语句");
        }
        return para.getSql();
    }
    /**
     * 获取页面大小（每页最大显示的数据条数
     * @return
     */
    protected static int getPageSize(SQLParam para){
        return para.getPageSize();
    }

    /**
     * 通用查询组件（仅适用于静态无入口参数的SQL语句）
     * @param sqlid   查询语句的编号
     * @return Collection  查询的结果，是object的列表
     * @throws AppException 异常, 产生的错误码包括3x
     */
    protected static Collection getPageData(String  sqlID) throws AppException {
        
        try {
            //根据传递到sqlid获取SQLParam参数
            SQLParam para = getSQLParam(sqlID);
            return getPageData(para);
        } catch (AppException ae) {
            throw ae;
        } catch (Exception ex) {
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+31, ex);
        }
    }
    
    /**
     * <p>根据传入的para参数获取查询结果集合，并按照指定分页条件显示</p>
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
     * <p>根据传入的para参数获取查询结果集合，并按照指定分页条件显示</p>
     * <p>(1)传入的sql语句必须是完整的，可以是静态也可以使动态(以?标志变量);</p>
     * <p>(2)如果是动态sql语句,入口的para.inPara中保存传递的参数，para.inType保存传递的参数类型。
     *       key以1开始，个数和sql中变量相同</p>
     * <p>  例：sql = select a,b,c,d from tab where d = ? and e = ? and f = ?</p>
     * <p>  则：d = inMap.get("1")，e = inMap.get("2"), f=inMap.get(3)</p>
     * <p>(3)如果是静态sql语句，则para.inMap = null;</p>
     * <p>(4)返回的记录每行是一个hashmap表，字段名按照para.outPara的顺序put到hashmap表</p>
     * <p>  如上例：则put("1",a),put("2",b),put("3",c),put("4",d)</p>
     * <p>(5)返回的数据类型通过入口参数outType定义说明</p>
     * <p>(6)传入的sql语句必须以SELECT开头的查询语句</p>
     * <p>(7)传递的page<=0 表示不分页 </p>
     * <p>(8)page>0时，count<=0表示使用系统默认页大小(GlobalNames.PAGE_SIZE)返回结果集</p>
     * 
     * @param con
     * @param para
     * @return
     * @throws AppException,产生的错误码包括4x
     */
    protected static Collection getPageData(Connection con,SQLParam para)
    throws AppException{
        ResultSet rs = null;
        try{
            //获取分页结果数据集
            rs = getResultData(con,para);
            // 组装返回结果
            Collection retList = new Vector();
            while (rs.next()) {
                HashMap map = CreateRsetMap(para,rs);
                retList.add(map);
            }
            return retList;
         }catch(SQLException se){
             throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+43,se.getMessage()+"。查询取数据出错，请检查返回数据参数类型个数定义是否正确");
         }finally{
             DBUtil.closeResult(rs);
         }
    }
    /**
     * 根据传递到sql语句以及参数，获取返回的记录条数
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
            throw new AppException("数据非法：传入的SQL语句不是以SELECT开始的查询语句");
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(para.getCountSQL());
            ps.clearParameters();
            int i = 0;
           // 如果是动态度SQL语句，则传递参数
           if(para.getInParaSize()>0){
               //校验参数个数和参数类型个数是否相同，不相同则出错
               if(para.getInTypeSize()!=para.getInParaSize()){
                   throw new SQLException("数据非法：入口参数的个数和入口参数类型的个数不等");
               }
               for(i=0;i<para.getInParaSize();){
                   i++;
                   String key = (new Integer(i)).toString();
                   // 判断传递的参数是否为空
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
           // 执行查询
           long inTime = System.currentTimeMillis();
           rs = ps.executeQuery();
           long outTime = System.currentTimeMillis();
           if(rs.next()){
               reCount =rs.getInt(1);
           }
           if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
               System.out.println("获取总行数执行的SQL语句消耗时间为："+(outTime-inTime));
           }
        }catch(SQLException se){
            DBUtil.closeResStat(rs,ps);            
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+49,"执行查询出错，请检查SQL语句或传入的数据及类型定义是否正确："+se.getMessage());
        }finally{
            DBUtil.closeResStat(rs,ps);
        }
        return reCount;
    }
    /**
     * 根据指定的sql语句编号，从配置文件中查询对应的sql参数定义
     * @param key   sql语句编号
     * @return      sql参数定义
     * @throws AppException 异常,产生的错误码包括2x
     */
    protected static SQLParam getSQLParam(String key) throws AppException{
        if(key == null){
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION + 21,"传入的要查询sql语句编号为null");
        }
        if(GlobalNames.SQL_MAP == null){
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION + 22,"没有初始化获取配置文件信息,或者初始化失败");
        }
        SQLParam para = (SQLParam)GlobalNames.SQL_MAP.get(key.toLowerCase());
        if(para==null){
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION + 23,"无法从文件"+GlobalNames.FILE_SQL_URL+"中获取标号为"+key+"的SQL语句定义");
        }
        return para;
    }
    /**
     * <p>根据传入的para参数，获取查询结果数据集合</p>
     * <p>(1)传入的sql语句必须以SELECT开头的查询语句</p>
     * <p>(2)传入的sql语句必须是完整的，可以是静态也可以使动态(以?标志变量);</p>
     * <p>(3)如果是动态sql语句,入口的para.inPara中保存传递的参数，para.inType保存传递的参数类型。
     *       key以1开始，个数和sql中变量相同</p>
     * <p>  例：sql = select a,b,c,d from tab where d = ? and e = ? and f = ?</p>
     * <p>  则：d = inMap.get("1")，e = inMap.get("2"), f=inMap.get(3)</p>
     * <p>(4)如果是静态sql语句，则para.inMap = null;</p>
     * 
     * @param con
     * @param para
     * @return  获取的结果数据集合ResultSet
     * @throws AppException
     */
    protected static ResultSet getResultData(Connection con,SQLParam para)throws AppException{
        String sql = null;
        try {
            // 参数校验
            sql = para.getSql() + para.getOrder();
            if(sql==null){
                throw new SQLException("数据非法：传入的SQL语句为空");
            }else if(!sql.substring(0,6).toUpperCase().equalsIgnoreCase("SELECT")){
                throw new SQLException("数据非法：传入的SQL语句不是以SELECT开始的查询语句");
            }
            if(para.getOutParaSize()==0||para.getOutTypeSize()==0){
                throw new SQLException("数据非法：查询结果返回参数的结构未定义1");
            }
            if(para.getOutParaSize()!=para.getOutTypeSize()){
                throw new SQLException("数据非法：查询结果返回参数的结构和类型不匹配");
            }
            if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
                System.out.println("分页组件执行的SQL语句为："+sql);
            }
            // 分页的SQL语句
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
           // 如果是动态度SQL语句，则传递参数
           if(para.getInParaSize()>0){
               //校验参数个数和参数类型个数是否相同，不相同则出错
               if(para.getInTypeSize()!=para.getInParaSize()){
                   throw new SQLException("数据非法：入口参数的个数和入口参数类型的个数不等");
               }
               for(i=0;i<para.getInParaSize();){
                   i++;
                   String key = (new Integer(i)).toString();
                   // 判断传递的参数是否为空
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
           // 分页参数
           if(para.getPage()>0){
               ps.setInt(i+1, para.getPage()*para.getPageSize()+1);
               ps.setInt(i+2, (para.getPage()-1)*para.getPageSize()+1);
           }
           // 执行查询
           long inTime = System.currentTimeMillis();
           rs = ps.executeQuery();
           long outTime = System.currentTimeMillis();
           if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
               System.out.println("分页组件执行的SQL语句消耗时间为："+(outTime-inTime));
           }
        }catch(SQLException se){
            DBUtil.closeResStat(rs,ps);            
            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+42,"执行查询出错，请检查SQL语句或传入的数据及类型定义是否正确："+se.getMessage());
        }
        return rs;
    }
    /**
     * 将指定列进行代码字段转换
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
     * 将指定列进行代码字段转换
     * @param dictType	代码类别
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
     * 对查询结果集进行解析，并返回一个散列表HashMap。返回到key为字段排列的顺序。
     * 如果某列要编码转换，则根据转换规则将数据库中编码转换为定义的内容，转化后统一为字符串
     * @param para  要返回到数据类型的定义
     * @param rset  查询获取的数据结果集合
     * @return
     * @exception SQLException
     */
    private static HashMap CreateRsetMap(SQLParam para,ResultSet rset)
    throws SQLException,ManageInputException {
        HashMap hm=new HashMap();
        for(int i = 1;i<=para.getOutParaSize();i++){
            String s = (String)para.getOutPara(i+"");
            //判断输出列是否需要转换
            int type = TypeCast.stringToInt((String)para.getOutType(i+""),"输出数据类型",false);
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
     * 格式化配置文件中的SQL参数串，转换成SQLParam类型并保存在HashMap中
     * 文件中每条SQL参数格式为A_&B_&C_&D_&E_&F，含义如下：
     *  (1)_&表示分割符，SQL参数被分解成ABCDE五部分，其中ABC部分不能缺少(为空保留分割符)
     *  (2)A部分为完整的SQL语句，可以是静态也可以是动态，不能为空
     *  (3)B为sql中where语句之后所需要的或者返回的字段名（由字段类型+下划线+字段名组成），
     *     此格式是为了兼容tbase，也可以不包括字段名称，不能为空
     *  (4)C为返回结果集合所需要的字段名（由字段类型+下划线+字段名组成），不能为空
     *  (5)D部分为每页显示的行数，如果没有此参数则默认取系统分页参数
     *  (6)E部分为获取分页查询总记录数的SQL语句，有E则必须添加D段(可以为空）
     *  (7)F部分是分页显示时页面的标题部分，可以为空，如果不为空必须和C配套
     * @param sql   从SQL配置文件中获取的sql语句
     * @return
     * @exception AppException 异常处理,产生的错误码包括1x
     */
    private static SQLParam buildSQLParam(String sql)throws AppException{
        if(sql==null){
            return null;
        }else{
            //分解配置文件中sql语句参数定义
            String[] s = StringUtil.getAsStringArray(sql,"_&");
            SQLParam para = new SQLParam();
            if(s.length==1){
                para.setSql(s[0]);
            }else if(s.length<3){
                throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+11,"SQL参数分解错误");
            }else{
                //--------------------位置s[0]：A:sql语句--------------------
                para.setSql(s[0]);
                //--------------------位置s[1]：B:入口参数--------------------
                String[] in = StringUtil.getAsStringArray(s[1],",");
                for(int i = 0;i<in.length;i++){
                    para.addInType(i+1,in[i]);
                }
                //--------------------位置s[2]：C:出口参数--------------------
                String[] out = StringUtil.getAsStringArray(s[2],",");
                for(int i = 0;i<out.length;i++){
                    para.addOutType(i+1+"",out[i]);
                    para.addOutPara(i+1+"",StringUtil.getSubstring(out[i],"_"));
                }
                try{
                    //---------------位置s[3]:D:页面大小--------------------
                    if(s.length>3){
                        try{
                            para.setPageSize(Integer.parseInt(s[3]));
                        }catch(NumberFormatException e){
                            para.setPageSize(GlobalNames.PAGE_SIZE);
                        }
                    }else{
                        para.setPageSize(GlobalNames.PAGE_SIZE);
                    }
                    //--------------位置s[4]：E:查询总记录的语句--------------------
                    if(s.length>4){
                        para.setCountSQL(s[4]);
                    }else{
                        
                    }
                    //--------------位置s[5]：F:标题显示内容-----------------------
//                    if(s.length>5){
//                        String[] title = StringUtil.getAsStringArray(s[5],",");
//                        if(title.length!=out.length){
//                            throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+12,"SQL定义文件格式错误："+sql);
//                        }
//                        for(int i = 1;i<=title.length;i++){
//                            para.addOutTitle(i+"",title[i]);
//                        }
//                    }
                }catch(Exception e){
                    //第四段参数错误时，忽略，自动使用全局变量
                    System.out.println("SQL定义文件格式错误："+sql);
                    throw new AppException(GlobalErrorCode.PAGERUTILEXCEPTION+12,"SQL定义文件格式错误："+sql);
                }
            }
            return para;
        }
    }
}
