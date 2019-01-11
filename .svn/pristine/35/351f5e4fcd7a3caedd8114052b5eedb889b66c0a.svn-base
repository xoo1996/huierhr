package org.radf.manage.role.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
* 交易定义实体类
* 定义系统中所有存在的功能定义(function)，并按照树状存储。
* (1)flag=0表示菜单项目(节点)，只能是菜单中的一级;
* (2)flag=1表示交互动作(最终节点)，只能是一个按钮;
* (3)任何一个parentID=0的分支，最终至少有一个叶子flag=1记录;
* (4)所有flag=1的数据不能缺少，否则权限无法分配;
* (5)数据与localDB中的SysFunction表数据一一对应，并自动下载到客户端
* @author zqb
* @version 1.0
 */
public class SysFunction extends EntitySupport {

   private String functionId;       //功能模块代码
   private String title;            //菜单的中文描述(客户端FunctionDesc)
   private String flag;             //功能结点类型     0-菜单项目：模块用于生成菜单树（就业全部是此类型）
                                    //              1-交互动作：模块对应页面的动作（社保用）"
   private String parentId;         //父结点的代码（菜单的父节点）
   private String signatureType;    //数字签名验证方式(SignID)：00-不需要验证
                                    //               10-客户端需要认证
                                    //               01-服务器端需要认证
                                    //               11-客户端和服务器都需要认证   

   private String type;             //功能类型：       0-菜单节点(不对应到页面的模块)
                                    //              1-菜单叶子(一定有唯一页面与之对应)
                                    //              3-按钮(目前无效，无需配置)"
   
   private String transFlag;        //是否需要交易处理
   private String location;         //url连接         社保无，就业中type=1有效且不能为空
   private int orderno;             //在同一级菜单中的序号,菜单排序使用
   private String log;              //在action中是否记日志，社保无，1为记，其它不记
   private String owner;            //开发人，此模块开发人姓名
   private String functionDesc;     //功能模块中文备注性描述
   private int imageID;             //整型，菜单图标ID
   private int helpID;              //整型      帮助文件CONTEXTid
   private int version;             //版本号
   
   public SysFunction(){
   }
   public SysFunction(String functionID){
       this.functionId = functionID;
   }
   
   public String getFunctionId(){
        return functionId ;
   }
   
   public void setFunctionId(String functionId){
       this.functionId = functionId;
   }
   public String getFlag(){
        return flag ;
   }
   
   public void setFlag(String flag){
       this.flag = flag;
   }
   public String getType(){
        return type ;
   }
   
   public void setType(String type){
       this.type = type;
   }
   public String getTransFlag(){
        return transFlag ;
   }
   
   public void setTransFlag(String transFlag){
       this.transFlag = transFlag;
   }
   public String getFunctionDesc(){
        return functionDesc ;
   }
   
   public void setFunctionDesc(String functionDesc){
       this.functionDesc = functionDesc;
   }
   public String getParentId(){
        return parentId ;
   }
   
   public void setParentId(String parentId){
       this.parentId = parentId;
   }
   public String getSignatureType(){
        return signatureType ;
   }
   
   public void setSignatureType(String signatureType){
       this.signatureType = signatureType;
   }

    /**
     * @return Returns the location.
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /**
     * @return Returns the log.
     */
    public String getLog() {
        return log;
    }
    
    /**
     * @param log The log to set.
     */
    public void setLog(String log) {
        this.log = log;
    }
    
    /**
     * @return Returns the orderno.
     */
    public int getOrderno() {
        return orderno;
    }
    
    /**
     * @param orderno The orderno to set.
     */
    public void setOrderno(int orderno) {
        this.orderno = orderno;
    }
    
    /**
     * @return Returns the owner.
     */
    public String getOwner() {
        return owner;
    }
    
    /**
     * @param owner The owner to set.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return Returns the helpID.
     */
    public int getHelpID() {
        return helpID;
    }
    /**
     * @param helpID The helpID to set.
     */
    public void setHelpID(int helpID) {
        this.helpID = helpID;
    }
    /**
     * @return Returns the imageID.
     */
    public int getImageID() {
        return imageID;
    }
    /**
     * @param imageID The imageID to set.
     */
    public void setImageID(int imageID) {
        this.imageID = imageID;
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

}



