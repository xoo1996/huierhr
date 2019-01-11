package org.radf.plat.sieaf.actionmapping;

import java.util.HashMap;

/**
 * <p>Title:FunctionMapping </p>
 * <p>Description: this class supply the Entity Object which contain
 *  the corresponding relationship between FunctionId and EjbAction</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co., Ltd.</p>
 * <p>Company: LBS</p>
 * @author LBS Architect Team
 * @version 0.1
 */

public class ActionMapping implements java.io.Serializable{
    private HashMap functionMappings;   //业务交易
    private HashMap transMappings;      //事务交易

    public ActionMapping(HashMap functionMappings,HashMap transMappings) {
        this.functionMappings = functionMappings;
        this.transMappings = transMappings;
    }

    public String getAction(String functionID) {
        if(this.functionMappings == null)
            return null;
        return (String)functionMappings.get(functionID);
    }

    public String getTransAction(String functionID){
        if(this.transMappings == null)
            return null;
        return (String)this.transMappings.get(functionID);
    }

    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.getClass());
        buffer.append("["+this.hashCode()+"] @");
        buffer.append(" FunctionMapping ={");
        java.util.Iterator key = functionMappings.keySet().iterator();
        while(key.hasNext()){
            Object name = key.next();
            Object value = functionMappings.get(name);
            buffer.append(name+":"+value+" ");
        }
        buffer.append("} ActionMapping ={");
        java.util.Iterator key2 = transMappings.keySet().iterator();
        while(key.hasNext()){
            Object name = key2.next();
            Object value = transMappings.get(name);
            buffer.append(name+":"+value+" ");
        }
        buffer.append("}");
        return buffer.toString();
    }
}

