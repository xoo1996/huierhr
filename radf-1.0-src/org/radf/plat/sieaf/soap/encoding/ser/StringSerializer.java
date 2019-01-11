/* Generated by Together */

package org.radf.plat.sieaf.soap.encoding.ser;

/**<p>Description:string������</p>
 * <p>Copyright: Copyright (c) 2002 by LBS Co., Ltd.</p>
 * <p>Company: LBS</p>
 * @author chenshuichao
 * @version 1.0
*/

public class StringSerializer implements Serializer {
	/**
	 * @see org.radf.plat.soap.encoding.Serializer#serialize(java.lang.StringBuffer, java.lang.Object, org.radf.plat.soap.encoding.SerializationContext)
	 */
	public void serialize(
		String Qname,
		StringBuffer out,
		Object value,
		SerializationContext ctx) {
		out.append(Qname);
		out.append("=\"");
		String val = null;
		if (value != null) {
			val = (String) value;
			val = StringReplace("\"", "��", val);
			val = StringReplace(">", "��", val);
			val = StringReplace("<", "��", val);
			out.append(val);
		}

		out.append("\" ");

	}
	private static String StringReplace(
		String from,
		String to,
		String source) {
		String temp1, temp2;
		int i = source.indexOf(from);
		while (i > 0) {
			source =
				source.substring(0, i).concat(to).concat(
					source.substring(i + from.length()));
			i = source.indexOf(from);
		}
		return (source);

	}

}