package org.radf.plat.commons;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.radf.plat.log.LogHelper;
import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.imp.IMPSupport;



/**
 * <p>
 * Title: 劳动力市场信息系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Project: Rapid Application Development Framework
 * </p>
 * 
 * @author llchao
 * @version 1.0
 */

public class CommonSum  extends IMPSupport{
	private LogHelper log = new LogHelper(this.getClass());
	public CommonSum() {
	}

	

	public static Map getSummary(String[] viewCols, String[] sum) {
		Map summary = new LinkedHashMap();
		String col = "";
		int index = -1;

		//添加合计信息，key值 GlobalNames.TOTAL_SUM 是合计

		for (int i = 0; i < sum.length; i++) {
			col = viewCols[i];
			index = col.indexOf(".");
			if (index > 0) {
				col = col.substring(index + 1);
			}
			summary.put(col, sum[i]);
		}

		return summary;
	}
	/**
	 * 求数字型字符串的和
	 * 
	 * @param String
	 *            total 数字字符串
	 * @param String
	 *            separator 分隔符
	 * @return
	 */
	public static BigDecimal getPageSum(String total, String separator) {
		BigDecimal sum = new BigDecimal(0);

		if (NullFlag.isObjNull(total))
			return sum;
		String[] tot = total.split(separator);

		for (int i = 0; i < tot.length; i++) {
			sum = sum.add(new BigDecimal(tot[i]));
		}

		return sum;
	}

	/**
	 * 求数字型字符串的和，默认分隔符为：“;”
	 * 
	 * @param String
	 *            total 数字字符串
	 * @return
	 */
	public static BigDecimal getPageSum(String total) {
		return getPageSum(total, ";");
	}
    /**
     * 将List中的值读取出来并组成一个指定分隔符分隔的字符
     * 
     * @param list
     *            List 读取值的List对象
     * @param prop
     *            String 属性名
     * @throws ApplicationException
     * @return String
     */
    public static String[] getPageSum(List list, String[] prop, QueryInfo qi) {
        String total = "";
        if (prop == null || prop.length < 1)
            return null;
        String[] pageSum = new String[prop.length];

        try {
            for (int i = 0; i < prop.length; i++) {
                total = ListUtil.listToString(list, prop[i], ";", qi);
                BigDecimal sum = getPageSum(total, ";");
                pageSum[i] = sum.toString();
            }
        } catch (AppException e) {
            return null;
        }

        return pageSum;
    }
	/**
	 * 对指定HQL的指定列进行求和处理
	 * 
	 * @param hql
	 * @param sumCols
	 * @return @throws
	 *         ApplicationException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String[] querySum(Connection con,String hql, String sumCols[])
			throws AppException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		int index = hql.toLowerCase().indexOf(" from ");
		StringBuffer hqlf = new StringBuffer("");
		List list = null;

		hqlf.append(" SELECT ").append(TypeCast.array2String(sumCols)).append(hql.substring(index));

		log.info("HQL is:" + hqlf);

		try {
			//list = op.retrieveObjs(hqlf.toString());
			list=(ArrayList)findBySQL(con,hqlf.toString(),-1,-1,null,0);
		} catch (Exception oe) {
			throw new AppException("读取总数时出错！", oe);
		}

		String totalSum[] = new String[sumCols.length];
		if (list != null) {
			/*if (sumCols.length == 1) {
				//totalSum[0] = TypeCast.objToString(list.get(0));
				totalSum[0] = getSimpleProperty(list.get(0),TypeCast.getname(sumCols[0])+"_0");
			} else {*/
				//Object obj[] = (Object[]) list.get(0);
			Object obj=(Object)list.get(0);
				for (int i = 0; i < sumCols.length; i++) {
					//totalSum[i] = TypeCast.objToString(obj[i]);
					totalSum[i] = getSimpleProperty(obj,TypeCast.getname(sumCols[i])+"_"+i);
				}
			//}
		} else {
			for (int i = 0; i < totalSum.length; i++) {
				totalSum[i] = "0";
			}
		}

		//	  	totalSum = TypeCast.array2String(temp,";");

		return totalSum;
	}
	public static String getSimpleProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		
		Object value = PropertyUtils.getSimpleProperty(bean, name);
		if(value==null)
			return "";
		else
          return ConvertUtils.convert(value);
	}
	
}