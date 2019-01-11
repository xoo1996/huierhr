/**
 * <p>标题: 业务处理对象基础类</p>
 * <p>说明: 提供常规操作的几个接口，并提供SIEAF和LEAF两种模式实现：</p>
 * <p>       增加：create、store</p>
 * <p>       修改：modify</p>
 * <p>       删除：remove</p>
 * <p>       查询所有：getAll</p>
 * <p>       条件查询：find</p>
 * <p>       条件查询带分页：findBySQL</p>
 * <p>       获取总记录数：getCount</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-9-1 13:30:54</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.plat.util.facade;

import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;

/**
 * 此类是公共的IMP接口类，通过此接口可以在开发人员仅实现ACTION部分同时， 按照标准方式调用IMP层提供的增删改查等一系列工作。
 * 由于此类仅是一个公共模型，因此不涉及业务判断，仅仅根据提供数据做出操作。 对于具有条件校验、计算等业务逻辑的数据进行处理时，仍需要开发人员写具体IMP。
 * 
 * @author zqb
 * @version 1.0
 */
public interface FACADESupport {

	/**
	 * 增加一条数据，传入的数据可以是一个对象，也可以是一个sql语句。
	 * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
	 * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。
	 * 返回的数据也在key="beo"的HashMap中 产生的错误码包括11，12，13,14
	 * 
	 * @param request
	 *            IMP的入口参数
	 * @param bpo
	 *            处理此业务的BPO
	 * @param method
	 *            调用的方法名
	 * @param nErrorCode
	 *            错误码
	 * @return
	 */
	public ResponseEnvelop create(RequestEnvelop request);

	/**
	 * 增加一条数据，传入的数据可以是一个对象，也可以是一个sql语句。
	 * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
	 * 如果传入是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。 无传出HashMap的数据
	 * 产生的错误码包括15，16，17,14
	 * 
	 * @param request
	 *            IMP的入口参数
	 * @param bpo
	 *            处理此业务的BPO
	 * @param method
	 *            调用的方法名
	 * @param nErrorCode
	 *            错误码
	 * @return
	 */
	public ResponseEnvelop store(RequestEnvelop request);

	/**
	 * 根据主键删除数据统一方法，传入的数据可以是一个对象，也可以是一个sql语句。
	 * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
	 * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。 无传出HashMap的数据
	 * 产生的错误码包括06，07
	 * 
	 * @param request
	 *            IMP的入口参数
	 * @param bpo
	 *            处理此业务的BPO
	 * @param method
	 *            调用的方法名
	 * @param nErrorCode
	 *            错误码
	 * @return
	 */
	public ResponseEnvelop remove(RequestEnvelop request);

	/**
	 * 根据主键修改数据统一方法，传入的数据可以是一个对象，也可以是一个sql语句。
	 * 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
	 * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。 无传出HashMap的数据
	 * 
	 * @param request
	 *            IMP的入口参数
	 * @param bpo
	 *            处理此业务的BPO
	 * @param method
	 *            调用的方法名
	 * @param nErrorCode
	 *            错误码
	 * @return
	 */
	public ResponseEnvelop modify(RequestEnvelop request);

	/**
	 * 无条件获取指定表中所有数据，无传入参数。 返回数据在HashMap的key="resultset"中
	 * 此方法仅对单表有效，一定需要在DAO层有具体实现类，不可能被公共使用。 产生的错误码包括19,20
	 * 
	 * @param request
	 *            IMP的入口参数
	 * @param bpo
	 *            处理此业务的BPO
	 * @param method
	 *            调用的方法名
	 * @param nErrorCode
	 *            错误码
	 * @return
	 */
	public ResponseEnvelop getAll(RequestEnvelop request);

	/**
	 * 根据条件查询数据的统一方法。 传入参数在HashMap中key="beo"，可以是一个对象也可以是一个sql语句，
	 * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。
	 * 返回的数据也在key="beo"的HashMap中，可能是一个EntitySupport类型对象，也可能是一个数据集列表。
	 * 产生的错误码包括01，02
	 * 
	 * @param request
	 *            IMP的入口参数
	 * @param bpo
	 *            处理此业务的BPO
	 * @param method
	 *            调用的方法名
	 * @param nErrorCode
	 *            错误码
	 * @return
	 */
	public ResponseEnvelop find(RequestEnvelop request);

	/**
	 * 根据条件，分页方式查询数据的统一方法。 传入参数包括beo、
	 * 传入参数在HashMap中key="beo"（生成sql条件的对象)，可以是一个对象也可以是一个sql语句，
	 * 如果是一个对象，则对象必须是EntitySupport的继承类，且fileKey在配置文件中能找到SQL语句。 传入参数还包括key =
	 * count(显示条数)、key = offset(第一条记录偏移序号)
	 * 返回的数据也在key="collection"的HashMap中，是一个数据集列表。 产生的错误码包括03，04，05
	 * 
	 * @param request
	 *            IMP的入口参数
	 * @param bpo
	 *            处理此业务的BPO
	 * @param method
	 *            调用的方法名
	 * @param nErrorCode
	 *            错误码
	 * @return Obj 返回结果对象
	 */
	public ResponseEnvelop findBySQL(RequestEnvelop request);

	/**
	 * 获取满足条件的记录数。 传入参数在HashMap中key="beo"（生成sql条件的对象)，可以是一个对象也可以是一个sql语句，
	 * (1)传递的对象是一个SQL语句，返回这个sql语句执行获取的记录条数；
	 * (2)传递的对象是一个实体类，则对象必须是EntitySupport的继承类，并可以通过fileKey获取配置文件中的sql语句；
	 * 返回的数据也在key="count"的HashMap中，是一个字符型数字。 注意：不能传select
	 * count(*)之类的语句，否则返回结果只能是一条记录
	 * 
	 * @param request
	 * @param bpo
	 * @param method
	 * @param nErrorCode
	 * @return
	 */
	public ResponseEnvelop getCount(RequestEnvelop request);
}
