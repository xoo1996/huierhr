/**
* <p>����: </p>
* <p>˵��: </p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2006-4-1822:53:21</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.manage.role.bpo;

import org.radf.manage.role.dao.ParamDictionaryDAO;

public class ParamDictionaryBPO extends org.radf.plat.util.bpo.BPOSupport{
    private static final String className = ParamDictionaryBPO.class.getName();
    private static final ParamDictionaryDAO dao = new ParamDictionaryDAO();
    public ParamDictionaryBPO(){
        super(className,dao);
    }

}
