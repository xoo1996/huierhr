package org.radf.manage.role.facade;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

public interface AclFacade{
    /**
     * 生成一条ACL记录
     */
	public ResponseEnvelop createAcl(RequestEnvelop request);
    /**
     * 用主键查找指定ACL记录
     */
    public ResponseEnvelop findAclByPK(RequestEnvelop request);
    /**
     *查找roleID对应的ACL记录集
    */
    public ResponseEnvelop findAclByRole(RequestEnvelop request);
    /**
     * 更新指定ACL记录
     */
    public ResponseEnvelop modifyAcl(RequestEnvelop request);
    /**
     * 删除指定ACL记录
     */
    public ResponseEnvelop removeAcl(RequestEnvelop request);

    /**************************************关于SysACT的方法********************************/
    /**
     * 生成一条Act记录
     */
    public ResponseEnvelop createAct(RequestEnvelop request);
    /**
     *查找roleid对应的Act记录集
    */
    public ResponseEnvelop findAllActByRole(RequestEnvelop request);
    /**
     *查找userID对应的Act记录集
    */
    public ResponseEnvelop findAllActByUser(RequestEnvelop request);
    /**
     * 用主键查找指定Act记录
     */
    public ResponseEnvelop findActByPK(RequestEnvelop request);
    /**
     * 更新指定Act记录
     */
    public ResponseEnvelop modifyAct(RequestEnvelop request);
    /**
     * 删除指定Act记录
     */
    public ResponseEnvelop removeAct(RequestEnvelop request);
}
