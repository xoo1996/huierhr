package org.radf.plat.commons;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p>
 * Title: �Ͷ����г���Ϣϵͳ
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
 * @author llc
 * @version 1.0
 */

public class BigDecimalCalc {
	public BigDecimalCalc() {
	}

	/**
	 * ����BigDecimal������ӣ�����BigDecimal
	 * 
	 * @param paraA
	 *            Integer
	 * @param paraB
	 *            Integer
	 * @return Integer
	 */
	public static BigDecimal addBigDecimal(BigDecimal paraA, BigDecimal paraB) {
		if (paraA == null)
			paraA = new BigDecimal(0);
		if (paraB == null)
			paraB = new BigDecimal(0);
		int ret = paraA.intValue() + paraB.intValue();
		return new BigDecimal(ret);
	}

	/**
	 * ����BigDecimal�������(paraA - paraB),����BigDecimal
	 * 
	 * @param paraA
	 *            BigDecimal
	 * @param paraB
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal subInteger(BigDecimal paraA, BigDecimal paraB) {
		if (paraA == null)
			paraA = new BigDecimal(0);
		if (paraB == null)
			paraB = new BigDecimal(0);
		int ret = paraA.intValue() - paraB.intValue();
		return new BigDecimal(ret);
	}

	/**
	 * ����BigDecimal�������(paraA * paraB),����BigDecimal
	 * 
	 * @param paraA
	 *            BigDecimal
	 * @param paraB
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal multInteger(BigDecimal paraA, BigDecimal paraB) {
		if (paraA == null)
			paraA = new BigDecimal(0);
		if (paraB == null)
			paraB = new BigDecimal(0);
		int ret = paraA.intValue() * paraB.intValue();
		return new BigDecimal(ret);
	}

	/**
	 * ����BigDecimal�������(paraA / paraB),����BigDecimal
	 * 
	 * @param paraA
	 *            BigDecimal
	 * @param paraB
	 *            BigDecimal
	 * @return BigDecimal
	 */
	public static BigDecimal divInteger(BigDecimal paraA, BigDecimal paraB) {
		if (paraA == null)
			paraA = new BigDecimal(0);
		if (paraB == null)
			paraB = new BigDecimal(0);
		int ret = paraA.intValue() / paraB.intValue();
		return new BigDecimal(ret);
	}
//��������
	public static String round(String v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		DecimalFormat df1 = new DecimalFormat("0.##########");
		BigDecimal t = b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
		return df1.format(t).toString();
	}
}