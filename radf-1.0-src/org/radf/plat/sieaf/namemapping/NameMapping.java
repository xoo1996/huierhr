package org.radf.plat.sieaf.namemapping;

import java.util.HashMap;

/**
 * @author chenfeiyan
 * @version 1.0
 * @since 2003
 */
public class NameMapping implements java.io.Serializable
{
   private HashMap nameMapping;

   /**
    * @param mapping
    * @roseuid 3E64A64D02CE
    */
   public NameMapping(java.util.HashMap mapping)
   {
        nameMapping = mapping;
   }

   /**
    * 由代码名(指标体系名)得到其义名,在构造输出数据的格式时使用。
    * 返回值转换为小写形式
    * @param codedName
    * @return String
    * @roseuid 3E64A64D0314
    */
   public String getName(String codedName)
   {
        return  nameMapping.get(codedName.toLowerCase()).toString().toLowerCase();
   }

   /**
    * 由义名得到其代码名(指标体系名)，在根据输入数据构造SQL语句时使用。
    * 暂时不会用到，供根据输入数据构造SQL语句的服务调用。
    * 返回值转换为大写形式
    * @param name
    * @return String
    * @roseuid 3E64A64D035A
    */
   public String getCodedName(String name)
   {
        return  nameMapping.get(name.toLowerCase()).toString().toUpperCase();
   }
}
