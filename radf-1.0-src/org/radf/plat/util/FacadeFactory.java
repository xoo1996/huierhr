/**
* <p>标题: Facade Cache</p>
* <p>说明: 缓冲xml配置文件中的业务处理Facade，是Action中关于Facade调用的基础</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-5-1611:17:01</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.radf.plat.commons.SystemConfigLoad;
import org.radf.plat.log.LogHelper;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;

/**
 * 缓冲xml配置文件中的业务处理Facade，此类不能被实例化。
 * @author zqb
 * @version 1.0
 */
public abstract class FacadeFactory implements Serializable {
	
    /**
     * Method init.
     * 从映射文件获取所有业务Facade类列表
     * 如果系统自动加载的路径中没有对应的配置文件，则配置文件存放在"c:\"即可
     * 并保存到facadeStubList中
     * @throws AppException
     */
    public static void init() throws AppException {
        GlobalNames.FACADE_CACHE = new HashMap();
        String facadeName = null;
        String className = null;
        
        if(GlobalNames.FACADE_LIST.size()<1){
            SystemConfigLoad.load("");
        }
        List facadeList = GlobalNames.FACADE_LIST;
        for (int i = 0; i < facadeList.size(); i++) {
            String [] temp = (String[])facadeList.get(i);
            facadeName = temp[0];
            className = temp[1];
            //---------------------------非EJB模式---------------------------
            try {
                Object obj = lookupFacade(className);
                GlobalNames.FACADE_CACHE.put(facadeName,obj);
                if (GlobalNames.DEBUG_PERFERMANCE_FLAG){
                    System.out.println("[" + i + "]   facade name = " + facadeName+"; obj = " + obj);
                }
            } catch (AppException ae) {
                LogHelper log =
                new LogHelper(FacadeFactory.class.getName());
                log.log(null, 300002, ae.getMessage());
                throw ae;
            }
        }
    }

    /**
     * 获取指定名称的Facade
     * @param FacadeName
     * @return Object Facade
     * @throws AppException
     */
    public static Object getService(String name) throws AppException{
        if(GlobalNames.FACADE_CACHE==null){
            throw new AppException("没有初始化Facade接口，请通过init绑定");
        }
        Object obj=GlobalNames.FACADE_CACHE.get(name);
        if(obj==null){
            try {
                obj = lookupFacade(name);
                if(obj==null){
                    throw new AppException(GlobalErrorCode.CREATEEXCEPTIONCODE,GlobalErrorMsg.CREATEEXCEPTIONMESSAGE);
                }
                GlobalNames.FACADE_CACHE.put(name,obj);
            }
            catch (AppException ne) {
                LogHelper log =
                    new LogHelper(FacadeFactory.class.getName());
                log.log(null, 300002, ne.getMessage());
                if (GlobalNames.DEBUG_OUTPUT_FLAG){
                    System.out.println(
                            "无法获取正确的Facade接口交易类："+name);
                }
                throw ne;
            }
        }
        return obj;       
    }
    

    
    /**
     * 根据facade类名绑定对应的IMP类，此类有两种用法：
     * (1)如果传递的类名在系统列表中，只要传递IMP对应的实现类即可
     * 　　例如配置文件中存在：RoleFacade 对应org.radf.plat.manage.role.bp.imp.RoleIMP
     *    则只要传递RoleFacade即可
     * (2)如果传递的类名不在系统列表中，则直接以传入的名称作为类名，
     *    此时需要传递完整的IMP类路径才能正常访问，不能直接传递Facade名称
     *    例如：传递org.radf.plat.manage.role.bp.imp.RoleIMP
     *    
     * @param name Facade类名称
     * @return Object
     * @throws AppException
     */
    private static Object lookupFacade(String facadeName) throws AppException {
        Object obj = null;
        if(facadeName==null){
            return null;
        }
        try{
            obj = GlobalNames.FACADE_CACHE.get(facadeName);
            if(obj==null){
                obj = Class.forName(facadeName).newInstance();
            }
        }catch(InstantiationException e){
            e.printStackTrace();
            throw new AppException("Facade绑定失败（InstantiationException）："+e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new AppException("Facade绑定失败（IllegalAccessException）："+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new AppException("Facade绑定失败（ClassNotFoundException）："+e.getMessage());
        }
        return obj;
    }
}
