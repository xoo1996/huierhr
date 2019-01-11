package org.radf.manage.role.entity;

import org.radf.plat.util.entity.EntitySupport;

/**
* ���׶���ʵ����
* ����ϵͳ�����д��ڵĹ��ܶ���(function)����������״�洢��
* (1)flag=0��ʾ�˵���Ŀ(�ڵ�)��ֻ���ǲ˵��е�һ��;
* (2)flag=1��ʾ��������(���սڵ�)��ֻ����һ����ť;
* (3)�κ�һ��parentID=0�ķ�֧������������һ��Ҷ��flag=1��¼;
* (4)����flag=1�����ݲ���ȱ�٣�����Ȩ���޷�����;
* (5)������localDB�е�SysFunction������һһ��Ӧ�����Զ����ص��ͻ���
* @author zqb
* @version 1.0
 */
public class SysFunction extends EntitySupport {

   private String functionId;       //����ģ�����
   private String title;            //�˵�����������(�ͻ���FunctionDesc)
   private String flag;             //���ܽ������     0-�˵���Ŀ��ģ���������ɲ˵�������ҵȫ���Ǵ����ͣ�
                                    //              1-����������ģ���Ӧҳ��Ķ������籣�ã�"
   private String parentId;         //�����Ĵ��루�˵��ĸ��ڵ㣩
   private String signatureType;    //����ǩ����֤��ʽ(SignID)��00-����Ҫ��֤
                                    //               10-�ͻ�����Ҫ��֤
                                    //               01-����������Ҫ��֤
                                    //               11-�ͻ��˺ͷ���������Ҫ��֤   

   private String type;             //�������ͣ�       0-�˵��ڵ�(����Ӧ��ҳ���ģ��)
                                    //              1-�˵�Ҷ��(һ����Ψһҳ����֮��Ӧ)
                                    //              3-��ť(Ŀǰ��Ч����������)"
   
   private String transFlag;        //�Ƿ���Ҫ���״���
   private String location;         //url����         �籣�ޣ���ҵ��type=1��Ч�Ҳ���Ϊ��
   private int orderno;             //��ͬһ���˵��е����,�˵�����ʹ��
   private String log;              //��action���Ƿ����־���籣�ޣ�1Ϊ�ǣ���������
   private String owner;            //�����ˣ���ģ�鿪��������
   private String functionDesc;     //����ģ�����ı�ע������
   private int imageID;             //���ͣ��˵�ͼ��ID
   private int helpID;              //����      �����ļ�CONTEXTid
   private int version;             //�汾��
   
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



