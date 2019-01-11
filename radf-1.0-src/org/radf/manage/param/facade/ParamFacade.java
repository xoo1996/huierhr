/**
 * 项目: Rapid Application Development Framework
 * 所在包名称:   org.radf.manage.param.facade
 * 类名称:      paramfacade.java
 * 创建者:      syg
 * 创建时间:     2006-11-14
 */
package org.radf.manage.param.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 创建者 syg
 *
 */
public interface ParamFacade {
	 /**
     * 选择代码增加
     * @param requeset
     * @return
     */
    public ResponseEnvelop AddAa01(RequestEnvelop request);
	 /**
     * 选择代码增加
     * @param requeset
     * @return
     */
    public ResponseEnvelop AddAa10(RequestEnvelop request);
	 /**
     * 选择代码修改
     * @param requeset
     * @return
     */
    public ResponseEnvelop modifAa10(RequestEnvelop request);
    
    public ResponseEnvelop find(RequestEnvelop request);
}
