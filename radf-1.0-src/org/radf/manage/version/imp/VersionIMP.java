/**
* <p>标题: 数据版本更新处理类</p>
* <p>说明: 此类为基础框架类，无需修改，可以满足全表更新和变动更新所有情况</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-8-2613:57:19</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.version.imp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.radf.manage.version.bpo.SysClientVersionBPO;
import org.radf.manage.version.bpo.SysRemoveVersionBPO;
import org.radf.manage.version.bpo.SysVersionBPO;
import org.radf.manage.version.entity.SysClientVersion;
import org.radf.manage.version.entity.SysVersion;
import org.radf.manage.version.facade.VersionFacade;
import org.radf.plat.commons.DBUtil;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.util.bpo.BPO;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;


public class VersionIMP extends org.radf.plat.util.imp.IMPSupport implements VersionFacade{
    private static final String className = VersionIMP.class.getName();
    private static SysVersionBPO verBPO = new SysVersionBPO();
    private static SysClientVersionBPO cliBPO = new SysClientVersionBPO();
    private static SysRemoveVersionBPO removeBPO = new SysRemoveVersionBPO();
    //采用全表更新的表信息列表，结果在TableCode范围内(type=1,type=2)
    private static HashMap fullTable = null;
    //采用全表或变动方式更新的表信息列表，结果每张表都独立(type=3)
    private static HashMap changeTable = null;
    private static List fullVerList = null;   //采用全表更新的表名列表
    
    protected static int nErrorCode = 109001000;
    
    public VersionIMP() {
        super(className);
        init();
    }
    
    /**
     * 功能：获取客户端Delphi程序当前的最新版本号，用于客户端验证是否需要更新版本
     * @param RequestEnvelop request
     * @return ResponseEnvelop
     */
    public ResponseEnvelop getApplicationVersion(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        HashMap req=(HashMap)request.getBody();
        String appName=(String)req.get("appName");

        Connection con=null;
        try{
            con = DBUtil.getConnection();
            Integer appVersion=new Integer(verBPO.getCurrentVersion(con,appName));
            HashMap rtnMap=new HashMap();
            rtnMap.put("appVersion",appVersion);
            response.setBody(rtnMap);
        } catch (AppException ae) {
            response.setHead(ExceptionSupport(className, ae, request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,
                    "updateVersion", nErrorCode,
                    GlobalErrorMsg.IMP_INDUSTRYCODE_VERSION_MSG + "："
                            + ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
    /**
     * <p>从服务器端获得数据表的最新版本，并将数据下载到客户端。</p>
     * <p>客户端传来本地的最新版本号，服务器端与之比较，如果客户端版本与服务器端不同，
     * 服务器将返回对应表的全部数据--data，以及当前最新版本号--version;
     * 否则返回的 data 为 null,version 是服务器端的最新版本号，而不是客户端传来的版本号。</p>
     * 
     * <p>支持全表更新或变动表更新方式，更新方式需要再具体数据的业务逻辑中进行控制</p>
     * <p>如果传递的表名为"ALL"，则表示更新所有采用“全表更新”方式的表数据，否则根据具体表定义更新方式</p>
     * @param RequestEnvelop
     * @return ResponseEnvelop
     */
    public ResponseEnvelop updateVersion(RequestEnvelop request){
        ResponseEnvelop response = new ResponseEnvelop();
        HashMap mapRequest = (HashMap) request.getBody();
        
        List versionList = (ArrayList)mapRequest.get("versionList");
        //判断是否需要全更新，如果是，重置versionList
        SysClientVersion dto = null;
        if(versionList.size()==1){
            dto = (SysClientVersion)versionList.get(0);
            if(dto.getTableName().equalsIgnoreCase("ALL")){
                versionList = getFullVerList();
            }
        }

        Connection con = null;
        Collection retRemove = new ArrayList();     //返回需要删除的SQL语句列表
        Collection retData = new ArrayList();       //返回需要更新的变动数据
        Collection retVersion = new ArrayList();    //返回服务端版本号
        try {
            con = DBUtil.getConnection();
            for(int i=0; i<versionList.size(); i++) {
                dto = (SysClientVersion)versionList.get(i); //客户端版本信息
                //获取服务器端当前表版本
                int iServerVersion = verBPO.getCurrentVersion(con, dto.getTableName());
                if(dto.getNowVersion() < iServerVersion){
                    Collection data = new ArrayList();
                    int updateVersion =0;  //本次更新到的版本号
                    //判断更新方式
                    if(tableVerify(dto.getTableName())){
                        //获取本次变动更新获取的数据，以及更新到的版本号
                        updateVersion = getVersionData(con,dto.getTableName(),dto.getNowVersion(),GlobalNames.UPDATE_SIZE,data);
                        if (updateVersion == 0){
                            updateVersion = iServerVersion;
                        }
                        //获取返回需要删除的数据
                        retRemove.addAll(getRemoveSQL(con,dto.getTableName(),dto.getNowVersion(),updateVersion));
                    }else{
                        //返回获取的更新数据，全表更新，版本号为最后版本号
                        getVersionData(con,dto.getTableName(),dto.getNowVersion(),0,data);
                        updateVersion = iServerVersion;
                    }
                    retData.addAll(data);
                    //记录版本更新记录
                    dto.setType("1");  //固定为中心更新
                    dto.setUpdateVersion(updateVersion);
                    dto.setIp(request.getHead().getIp());
                    if(dto.getIp()==null){
                        dto.setIp("未知IP");
                    }
                    //返回服务端最新版本
                    retVersion.add(dto);
                    if(cliBPO.findKEYMembers(con,dto)==null){
                        cliBPO.store(con,dto);
                    }else{
                        cliBPO.modify(con,dto);
                    }
                } else {                
                }
            }
            //返回Action的结果集
            HashMap rtnMap = new HashMap(3);
            rtnMap.put("remove",retRemove);
            rtnMap.put("data", retData);
            rtnMap.put("serverVersion", retVersion);
            // 返回处理结果
            response.setBody(rtnMap);
        } catch (AppException ae) {
            response.setHead(ExceptionSupport(className, ae, request.getHead()));
        } catch (Exception ex) {
            response.setHead(ExceptionSupport(className,
                    "updateVersion", nErrorCode,
                    GlobalErrorMsg.IMP_INDUSTRYCODE_VERSION_MSG + "："
                            + ex.getMessage(), request.getHead()));
        } finally {
            DBUtil.closeConnection(con);
        }
        return response;
    }
    
    /**
     * 获取被删除的数据列表
     * @param con
     * @param sTableName
     * @param starVersion
     * @param endVersion
     * @return
     * @throws AppException
     */
    private Collection getRemoveSQL(Connection con,String sTableName,int starVersion,int endVersion)throws AppException{
        return removeBPO.getVersionData(con,sTableName,starVersion,endVersion);
    }
    
    /**
     * 获取可用于更新的数据表列表
     */
    private void init(){
        if(GlobalNames.SERVICE_VERSION.equalsIgnoreCase("1")){
            Connection con = null;
            Collection verTable = null;
            try{
                con = DBUtil.getConnection();
                verTable = verBPO.findAllMembers(con);
            }catch(Exception e){
                System.out.println("更新服务启动失败");            
                e.printStackTrace();
            }finally{
                DBUtil.closeConnection(con);
            }
            if(verTable !=null){
                fullTable = null;
                fullTable = new HashMap();
                changeTable = null;
                changeTable = new HashMap();
                fullVerList = null;
                fullVerList = new ArrayList();
                Iterator it = verTable.iterator();
                while (it.hasNext()) {
                    SysVersion dto = (SysVersion)it.next();
                    if(dto.getType().equalsIgnoreCase("1")){
                        //生成全表更新的表名称列表
                        fullTable.put(dto.getTableName(),dto);
                        SysClientVersion sv = new SysClientVersion();
                        sv.setTableName(dto.getTableName());
                        sv.setNowVersion(0);
                        fullVerList.add(sv);
                        if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                            System.out.println("全表更新(1)："+dto.getTableName());
                        }
                    }else if (dto.getType().equalsIgnoreCase("2")){
                        //生成全表更新的表名称列表
                        fullTable.put(dto.getTableName(),dto);
                        SysClientVersion sv = new SysClientVersion();
                        sv.setTableName(dto.getTableName());
                        sv.setNowVersion(0);
                        fullVerList.add(sv);
                        if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                            System.out.println("全表更新(2)："+dto.getTableName());
                        }
                    }else{
                        //生成变动更新的表名称列表
                        changeTable.put(dto.getTableName(),dto);
                        if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                            System.out.println("变动更新(3)："+dto.getTableName());
                        }
                    }
                }
            }
        }
    }
    
    /**
     * 获取系统可采用全表更新的所有表列表
     * @return
     */
    private List getFullVerList(){
        return fullVerList;
    }
    
    /**
     * 判断传入的表是否是变动表
     * @param sTableName
     * @return
     */
    protected boolean tableVerify(String sTableName){
        if(changeTable!=null){
            if(changeTable.get(sTableName)!=null){
                return true;                
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    /**
     * 根据传入的表名称，获取相应表中需要更新的数据，将数据组装后返回客户端
     * @param con           数据库连接
     * @param sTableName    表名
     * @param nVersion      客户端版本号
     * @param count         获取记录条数
     * @return
     * @throws AppException
     */
    private int getVersionData(Connection con,String sTableName,int nVersion,int count,Collection data)
    throws AppException{
        SysVersion dto = (SysVersion)fullTable.get(sTableName);
        if(dto == null){
            dto = (SysVersion)changeTable.get(sTableName);
        }
        if(dto == null){
            throw new AppException(nErrorCode,"没有正确传入要更新的数据表标志，请与开发商联系");
        }else{
            if(dto.getBpo()==null){
                throw new AppException(nErrorCode,"表"+sTableName+"没有配置数据处理BPO类，请与开发商联系");
            }
            BPO bpo = null;
            try{
                bpo = (BPO)Class.forName(dto.getBpo()).newInstance();
            }catch(Exception e){
                throw new AppException(nErrorCode,"无法获取要更新数据的处理方法，请与开发商联系");
            }
            return bpo.getVersionData(con,nVersion,count,data);
        }
    }
    
//    /**
//     * 根据传入的表名称，获取相应表中需要更新的数据，将数据组装后返回客户端
//     * 此方法只针对变动表更新模式有效
//     * @param con
//     * @param sTableName
//     * @param nVersion
//     * @return
//     * @throws AppException
//     */
//    private Collection getChangeVersionData(Connection con,String sTableName,int nVersion)
//    throws AppException{
//        SysVersion dto = (SysVersion)changeTable.get(sTableName);
//        if(dto == null){
//            throw new AppException(nErrorCode,"没有正确传入要更新的数据表"+sTableName+"，请与开发商联系");
//        }else{
//            if(dto.getBpo()==null){
//                throw new AppException(nErrorCode,"表"+sTableName+"没有配置数据处理BPO类，请与开发商联系");
//            }
//            BPO bpo = null;
//            try{
//                bpo = (BPO)Class.forName(dto.getBpo()).newInstance();
//            }catch(Exception e){
//                throw new AppException(nErrorCode,"无法获取要更新数据"+sTableName+"的处理方法，请与开发商联系");
//            }
//            return bpo.getVersionData(con,nVersion);
//        }
//    }
}
