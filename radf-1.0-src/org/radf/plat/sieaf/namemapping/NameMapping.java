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
    * �ɴ�����(ָ����ϵ��)�õ�������,�ڹ���������ݵĸ�ʽʱʹ�á�
    * ����ֵת��ΪСд��ʽ
    * @param codedName
    * @return String
    * @roseuid 3E64A64D0314
    */
   public String getName(String codedName)
   {
        return  nameMapping.get(codedName.toLowerCase()).toString().toLowerCase();
   }

   /**
    * �������õ��������(ָ����ϵ��)���ڸ����������ݹ���SQL���ʱʹ�á�
    * ��ʱ�����õ����������������ݹ���SQL���ķ�����á�
    * ����ֵת��Ϊ��д��ʽ
    * @param name
    * @return String
    * @roseuid 3E64A64D035A
    */
   public String getCodedName(String name)
   {
        return  nameMapping.get(name.toLowerCase()).toString().toUpperCase();
   }
}
