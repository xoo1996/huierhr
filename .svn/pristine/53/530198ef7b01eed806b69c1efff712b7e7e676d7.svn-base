/**
* <p>项目名称: emistzsw</p>
* <p>包名称: org.radf.apps.commons</p>
* <p>标题: NumberUtil.java</p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2007-3-13</p>
*
* @author yp
* @version 1.0
*/
package org.radf.plat.commons;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil
{
    public static String round(String v, int scale) {
        if (scale < 0) {
        throw new IllegalArgumentException(
        "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        DecimalFormat df1 = new DecimalFormat("0.##########");
        BigDecimal t=b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
        return df1.format(t).toString();
        }  
}
