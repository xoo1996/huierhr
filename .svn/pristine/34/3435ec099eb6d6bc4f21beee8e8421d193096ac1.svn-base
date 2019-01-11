/*
 * Created on 2003-6-18
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.radf.plat.commons;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.io.ByteToCharConverter;
import sun.io.CharToByteConverter;

import org.radf.plat.util.global.GlobalNames;

/**
 * String的相关处理类
 * 
 * @author zqb
 */
public abstract class StringUtil {

//	private static Pattern collectionPattern = null;

	private static final int li_SecPosValue[] = { 1601, 1637, 1833, 2078, 2274,
			2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858,
			4027, 4086, 4390, 4558, 4684, 4925, 5249 };

	private static final String lc_FirstLetter[] = { "A", "B", "C", "D", "E",
			"F", "G", "H", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "W", "X", "Y", "Z" };

	private static final String ls_SecondSecTable = 4096
			+ "CJWGNSPGCGNE[Y[BTYYZDXYKYGT[JNNJQMBSGZSCYJSYY[PGKBZGY[YWJKGKLJYWKPJQHY[W[DZLSGMRYPYWWCCKZNKYYGTT"
			+ "NJJNYKKZYTCJNMCYLQLYPYQFQRPZSLWBTGKJFYXJWZLTBNCXJJJJTXDTTSQZYCDXXHGCK[PHFFSS[YBGXLPPBYLL[HLXS[ZM"
			+ "[JHSOJNGHDZQYKLGJHSGQZHXQGKEZZWYSCSCJXYEYXADZPMDSSMZJZQJYZC[J[WQJBYZPXGZNZCPWHKXHQKMWFBPBYDTJZZK"
			+ "QHYLYGXFPTYJYYZPSZLFCHMQSHGMXXSXJ[[DCSBBQBEFSJYHXWGZKPYLQBGLDLCCTNMAYDDKSSNGYCSGXLYZAYBNPTSDKDYLH"
			+ "GYMYLCXPY[JNDQJWXQXFYYFJLEJPZRXCCQWQQSBNKYMGPLBMJRQCFLNYMYQMSQYRBCJTHZTQFRXQHXMJJCJLXQGJMSHZKBSWYE"
			+ "MYLTXFSYDSWLYCJQXSJNQBSCTYHBFTDCYZDJWYGHQFRXWCKQKXEBPTLPXJZSRMEBWHJLBJSLYYSMDXLCLQKXLHXJRZJMFQHXHW"
			+ "YWSBHTRXXGLHQHFNM[YKLDYXZPYLGG[MTCFPAJJZYLJTYANJGBJPLQGDZYQYAXBKYSECJSZNSLYZHSXLZCGHPXZHZNYTDSBCJK"
			+ "DLZAYFMYDLEBBGQYZKXGLDNDNYSKJSHDLYXBCGHXYPKDJMMZNGMMCLGWZSZXZJFZNMLZZTHCSYDBDLLSCDDNLKJYKJSYCJLKWH"
			+ "QASDKNHCSGANHDAASHTCPLCPQYBSDMPJLPZJOQLCDHJJYSPRCHN[NNLHLYYQYHWZPTCZGWWMZFFJQQQQYXACLBHKDJXDGMMYDJ"
			+ "XZLLSYGXGKJRYWZWYCLZMSSJZLDBYD[FCXYHLXCHYZJQ[[QAGMNYXPFRKSSBJLYXYSYGLNSCMHZWWMNZJJLXXHCHSY[[TTXRYC"
			+ "YXBYHCSMXJSZNPWGPXXTAYBGAJCXLY[DCCWZOCWKCCSBNHCPDYZNFCYYTYCKXKYBSQKKYTQQXFCWCHCYKELZQBSQYJQCCLMTHS"
			+ "YWHMKTLKJLYCXWHEQQHTQH[PQ[QSCFYMNDMGBWHWLGSLLYSDLMLXPTHMJHWLJZYHZJXHTXJLHXRSWLWZJCBXMHZQXSDZPMGFCS"
			+ "GLSXYMJSHXPJXWMYQKSMYPLRTHBXFTPMHYXLCHLHLZYLXGSSSSTCLSLDCLRPBHZHXYYFHB[GDMYCNQQWLQHJJ[YWJZYEJJDHPB"
			+ "LQXTQKWHLCHQXAGTLXLJXMSL[HTZKZJECXJCJNMFBY[SFYWYBJZGNYSDZSQYRSLJPCLPWXSDWEJBJCBCNAYTWGMPAPCLYQPCLZ"
			+ "XSBNMSGGFNZJJBZSFZYNDXHPLQKZCZWALSBCCJX[YZGWKYPSGXFZFCDKHJGXDLQFSGDSLQWZKXTMHSBGZMJZRGLYJBPMLMSXLZ"
			+ "JQQHZYJCZYDJWBMYKLDDPMJEGXYHYLXHLQYQHKYCWCJMYYXNATJHYCCXZPCQLBZWWYTWBQCMLPMYRJCCCXFPZNZZLJPLXXYZTZ"
			+ "LGDLDCKLYRZZGQTGJHHGJLJAXFGFJZSLCFDQZLCLGJDJCSNZLLJPJQDCCLCJXMYZFTSXGCGSBRZXJQQCTZHGYQTJQQLZXJYLYL"
			+ "BCYAMCSTYLPDJBYREGKLZYZHLYSZQLZNWCZCLLWJQJJJKDGJZOLBBZPPGLGHTGZXYGHZMYCNQSYCYHBHGXKAMTXYXNBSKYZZGJ"
			+ "ZLQJDFCJXDYGJQJJPMGWGJJJPKQSBGBMMCJSSCLPQPDXCDYYKY[CJDDYYGYWRHJRTGZNYQLDKLJSZZGZQZJGDYKSHPZMTLCPWN"
			+ "JAFYZDJCNMWESCYGLBTZCGMSSLLYXQSXSBSJSBBSGGHFJLYPMZJNLYYWDQSHZXTYYWHMZYHYWDBXBTLMSYYYFSXJC[DXXLHJHF"
			+ "[SXZQHFZMZCZTQCXZXRTTDJHNNYZQQMNQDMMG[YDXMJGDHCDYZBFFALLZTDLTFXMXQZDNGWQDBDCZJDXBZGSQQDDJCMBKZFFXM"
			+ "KDMDSYYSZCMLJDSYNSBRSKMKMPCKLGDBQTFZSWTFGGLYPLLJZHGJ[GYPZLTCSMCNBTJBQFKTHBYZGKPBBYMTDSSXTBNPDKLEYC"
			+ "JNYDDYKZDDHQHSDZSCTARLLTKZLGECLLKJLQJAQNBDKKGHPJTZQKSECSHALQFMMGJNLYJBBTMLYZXDCJPLDLPCQDHZYCBZSCZB"
			+ "ZMSLJFLKRZJSNFRGJHXPDHYJYBZGDLQCSEZGXLBLGYXTWMABCHECMWYJYZLLJJYHLG[DJLSLYGKDZPZXJYYZLWCXSZFGWYYDLY"
			+ "HCLJSCMBJHBLYZLYCBLYDPDQYSXQZBYTDKYXJY[CNRJMPDJGKLCLJBCTBJDDBBLBLCZQRPPXJCJLZCSHLTOLJNMDDDLNGKAQHQ"
			+ "HJGYKHEZNMSHRP[QQJCHGMFPRXHJGDYCHGHLYRZQLCYQJNZSQTKQJYMSZSWLCFQQQXYFGGYPTQWLMCRNFKKFSYYLQBMQAMMMYX"
			+ "CTPSHCPTXXZZSMPHPSHMCLMLDQFYQXSZYYDYJZZHQPDSZGLSTJBCKBXYQZJSGPSXQZQZRQTBDKYXZKHHGFLBCSMDLDGDZDBLZY"
			+ "YCXNNCSYBZBFGLZZXSWMSCCMQNJQSBDQSJTXXMBLTXZCLZSHZCXRQJGJYLXZFJPHYMZQQYDFQJJLZZNZJCDGZYGCTXMZYSCTLK"
			+ "PHTXHTLBJXJLXSCDQXCBBTJFQZFSLTJBTKQBXXJJLJCHCZDBZJDCZJDCPRNPQCJPFCZLCLZXZDMXMPHJSGZGSZZQLYLWTJPFSY"
			+ "ASMCJBTZKYCWMYTCSJJLJCQLWZMALBXYFBPNLSFHTGJWEJJXXGLLJSTGSHJQLZFKCGNNNSZFDEQFHBSAQTGYLBXMMYGSZLDYDQ"
			+ "MJJRGBJTKGDHGKBLQKBDMBYLXWCXYTTYBKMRTJZXQJBHLMHMJJZMQASLDCYXYQDLQCAFYWYXQHZ";

	/**
	 * 关于传递的XML文件中标识符"< ,/, > "的转换处理，防止xml格式解析错误
	 * 
	 * @param val
	 * @return
	 */
	public static String deal(String val) {

		val = StringReplace("\"", "’", val);
		val = StringReplace(">", "’", val);
		val = StringReplace("<", "’", val);
		return val;
	}

	/**
	 * 传入的字符串中<、>、&转换成html文本中字符标志 例如：A&B<C>D转换以后成为A&amp;B&lt;C&gt;D
	 * 
	 * @param s
	 * @return
	 */
	public static String toHtmlInput(String s) {
		if (s == null) {
			return null;
		} else {
			String s1 = new String(s);
			s1 = StringReplace("&", "&amp;", s1);
			s1 = StringReplace("<", "&lt;", s1);
			s1 = StringReplace(">", "&gt;", s1);
			return s1;
		}
	}
    /**
     * 生成标准路径格式。
     * 将路径中的"\\"以及"//"替换成"/"。
     * @param path
     * @return
     */
    public static String getFormatPath(String path) {
        path = path.replaceAll("\\\\", "/");
        path = path.replaceAll("//", "/");
        return path;
    }

	/**
	 * describe:对取出来的数据进行转换，转换成html文件格式字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String toHtml(String s) {
		if (s == null) {
			return null;
		} else {
			String s1 = new String(s);
			s1 = toHtmlInput(s1);
			s1 = StringReplace("\r\n", "\n", s);
			s1 = StringReplace("\n", "<br>\n", s);
			s1 = StringReplace("\t", "    ", s);
			s1 = StringReplace("  ", " &nbsp;", s);
			return s1;
		}
	}

	/**
	 * 删除传入字符串中html标记"<>"之间的所有内容。 注意不支持嵌套，如果有嵌套的括号，以第一个返回的“>“为准。 例如：abc<html
	 * x=<yyy>D>efg转换后为abcDefg
	 * 
	 * @param htmStr
	 * @return
	 */
	public static String delete_htm(String htmStr) {
		boolean boo = true;
		StringBuffer retStrB = new StringBuffer();
		if (htmStr == null || htmStr.length() == 0) {
			return "";
		}
		for (int i = 0; i < htmStr.length(); i++) {
			if (htmStr.substring(i, i + 1).equals("<")) {
				boo = false;
			}
			if (htmStr.substring(i, i + 1).equals(">")) {
				boo = true;
			}
			if (boo)
				retStrB.append(htmStr.substring(i, i + 1));
		}
		String retStr = retStrB.toString();
		retStr = StringReplace(">", "", retStr);
		// retStr.replaceAll(">","");
		return retStr;
	}

	/**
	 * 删除html标记并截取一段字符串，长度由参数控制
	 * 
	 * @param htmStr
	 * @param length
	 * @return
	 */
	public static String delete_htmAndTrim(String htmStr, int length) {
		String s = toOmit(delete_htm(htmStr), length,0);
		return s;
	}

	/**
     * @功能：截取字符串str指定长度len1，后面填满"."，补充的最后长度为len2。
     *      如果str长度小于len1且len2=0则直接返回原始str字符串
	 * @param  str
     * @param  len1 截取的字长度，汉字或英文字符都只算一个字
     * @param  len2 补充的最后长度，如果为0表示固定补充"……"
	 * @return str
	 */
	public static String toOmit(String str, int len1,int len2) {
		if (len1 == 0)
            len1 = 112;
		if (str.length() >= len1){
            if(len2==0){
                str = str.substring(0, len1) + "……";
            }else{
                str = fillString(str,'.',len2,2);
            }
        }else{
            if(len2>0){
                str = fillString(str,'.',len2,2);
            }
        }
		return str;
	}

	/**
	 * 以指定字符串to替换原始字符串source中的from字符串
	 * 例如用单引号替换双引号：StringReplace("\"","\'",oldString)
	 * 
	 * @param from
	 * @param to
	 * @param source
	 * @return
	 */
	public static String StringReplace(String from, String to, String source) {
		if (source == null) {
			return null;
		}
//		String temp1, temp2;

		int i = -1;//shenyg修改假如要替换的位置正好是0这样不被过滤的 所以修改当前方法
		i = source.indexOf(from);
		while (i != -1) {
			source = source.substring(0, i).concat(to).concat(
					source.substring(i + from.length()));
			i = source.indexOf(from);
		}
		return (source);
	}

	/**
	 * 根据分割符delim，将字符串s_value转化为单词数组
	 * 
	 * @param s_value
	 *            字符串
	 * @param delim
	 *            分隔符，为空默认使用“,”分割
	 * @return Collection 字符串列表
	 */
	public static String[] getAsStringArray(String s_value, String delim) {
		if (delim == null || delim.equalsIgnoreCase("")) {
			delim = ",";
		}
		String[] s = new String[getSubtringCount(s_value, delim) + 1];
        int firstindex = s_value.indexOf(delim); // 第一个位置
        if (firstindex == -1) {
            //整串中没有指定的分割符号
            s[0] = s_value;
        }else{
            int i = 0;
            while (firstindex != -1) {
                s[i] = s_value.substring(0, firstindex);
                s_value = s_value.substring(firstindex + delim.length(), s_value
                        .length());
                firstindex = s_value.indexOf(delim);
                i++;
            }
            s[i] = s_value;
        }
        return s;
	}

	/**
	 * 将给出的字符串source使用chr(只能是单字符)划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符，为空则默认用","分割
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
	 *         如果delim为null则使用逗号作为分隔字符串。
	 */
	public static String[] split(String source, String chr) {
		String[] wordLists;
		if (source == null) {
			wordLists = new String[1];
			wordLists[0] = source;
			return wordLists;
		}
		if (chr == null) {
			chr = ",";
		}
		StringTokenizer st = new StringTokenizer(source, chr);
		int total = st.countTokens();
		wordLists = new String[total];
		for (int i = 0; i < total; i++) {
			wordLists[i] = st.nextToken();
		}
		return wordLists;
	}

	/**
	 * 将给出的字符串source使用delim划分为单词数组。
	 * 
	 * @param source
	 *            需要进行划分的原字符串
	 * @param delim
	 *            单词的分隔字符
	 * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
	 */
	public static String[] split(String source, char chr) {
		return split(source, String.valueOf(chr));
	}

	/**
	 * 将null或者""字符转换成"&nbsp;"，主要用于html页面中需要的空格显示。
	 * 
	 * @param s
	 * @return
	 */
	public static String spaceToNBSP(String s) {
		if (s == null || s.equals("")) {
			return "&nbsp;";
		} else
			return s;
	}

	/**
	 * 返回字符串指定字符以后的内容到字符串结束
	 * 
	 * @param s_value
	 * @param code
	 * @return
	 */
	public static String getSubstring(String s_value, String code) {
		int id = s_value.indexOf(code) + 1;
		return s_value.substring(id);
	}

	/**
	 * 将字符串中的变量使用values数组中的内容进行替换。 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 * 
	 * @param prefix
	 *            变量前缀字符串
	 * @param source
	 *            带参数的原字符串
	 * @param values
	 *            替换用的字符串数组
	 * @return 替换后的字符串。 如果前缀为null则使用“%”作为前缀；
	 *         如果source或者values为null或者values的长度为0则返回source；
	 *         如果values的长度大于参数的个数，多余的值将被忽略；
	 *         如果values的长度小于参数的个数，则后面的所有参数都使用最后一个值进行替换。
	 */
	public static String getReplaceString(String prefix, String source,
			String[] values) {
		String result = source;
		if (source == null || values == null || values.length < 1) {
			return source;
		}
		if (prefix == null) {
			prefix = "%";
		}

		for (int i = 0; i < values.length; i++) {
			String argument = prefix + Integer.toString(i + 1);
			int index = result.indexOf(argument);
			if (index != -1) {
				String temp = result.substring(0, index);
				if (i < values.length) {
					temp += values[i];
				} else {
					temp += values[values.length - 1];
				}
				temp += result.substring(index + 2);
				result = temp;
			}
		}
		return result;
	}

	/**
	 * 将字符串中的变量（以“%”为前导后接数字）使用values数组中的内容进行替换。
	 * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
	 * 
	 * @param source
	 *            带参数的原字符串
	 * @param values
	 *            替换用的字符串数组
	 * @return 替换后的字符串
	 */
	public static String getReplaceString(String source, String[] values) {
		return getReplaceString("%", source, values);
	}

	/**
	 * 字符串数组中是否包含指定的字符串。
	 * 
	 * @param strings
	 *            字符串数组
	 * @param string
	 *            字符串
	 * @param caseSensitive
	 *            是否大小写敏感
	 * @return 包含时返回true，否则返回false
	 */
	public static boolean contains(String[] strings, String string,
			boolean caseSensitive) {
		for (int i = 0; i < strings.length; i++) {
			if (caseSensitive == true) {
				if (strings[i].equals(string)) {
					return true;
				}
			} else {
				if (strings[i].equalsIgnoreCase(string)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 将字符串数组使用指定的分隔符合并成一个字符串。
	 * 
	 * @param array
	 *            字符串数组
	 * @param delim
	 *            分隔符，为null的时候使用""作为分隔符（即没有分隔符）
	 * @return 合并后的字符串
	 */
	public static String combineStringArray(String[] array, String delim) {
		int length = array.length - 1;
		if (delim == null) {
			delim = "";
		}
		StringBuffer result = new StringBuffer(length * 8);
		for (int i = 0; i < length; i++) {
			result.append(array[i]);
			result.append(delim);
		}
		result.append(array[length]);
		return result.toString();
	}

	/**
	 * 以指定的字符和长度生成一个该字符的指定长度的字符串。
	 * 
	 * @param c
	 *            指定的字符
	 * @param length
	 *            指定的长度
	 * @return 最终生成的字符串
	 * @since 0.6
	 */
	public static String fillString(char c, int length) {
		String ret = "";
		for (int i = 0; i < length; i++) {
			ret += c;
		}
		return ret;
	}
    /**
     * 在指定字符串source头或者尾部填充指定字符c，使总长度达到len，如果source长度已经超过len，则不变
     * @param source    原是字符串
     * @param c         要添加的字符
     * @param len       最后的总长度
     * @param type      添加方式1-添加在原来字符串前面，其他-后面
     * @return
     */
    public static String fillString(String source,char c,int len,int type){
        if(source==null){
            source = "";
        }
        if(source.length()<len){
            String temp = fillString(c,len - source.length());
            if(type==1){
                source = temp + source;
            }else{
                source = source + temp;
            }
        }
        return source;
    }
    
	/**
	 * 得到字符串的字节长度。
	 * 
	 * @param source
	 *            字符串
	 * @return 字符串的字节长度
	 * @since 0.6
	 */
	public static int getByteLength(String source) {
		int len = 0;
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			int highByte = c >>> 8;
			len += highByte == 0 ? 1 : 2;
		}
		return len;
	}

	/**
	 * 得到字符串中的子串的个数。
	 * 
	 * @param source
	 *            字符串
	 * @param sub
	 *            子串
	 * @return 字符串中的子串的个数
	 * @since 0.6
	 */
	public static int getSubtringCount(String source, String sub) {
		if (source == null || source.length() == 0) {
			return 0;
		}
		int count = 0;
        for(int index = source.indexOf(sub); index >= 0; index = source.indexOf(sub, index + 1))
            count++;
		return count;
	}

	/**
	 * 沈云刚加入 得到字符串:拼音字符码或者数字 例如 我是谁 返回 wss
	 * 
	 * @param s
	 *            字符串
	 * @return 拼音字符码
	 * @throws Exception
	 */
	public static String MnemonicWords(String s) throws Exception {
		String s1 = "";
		try {
			for (int i = 0; i < s.length(); i++) {
				String s2 = s.substring(i, i + 1);
				byte abyte0[] = s2.getBytes("gbk");
				if (abyte0.length == 1) {
					s1 = s1 + s.substring(i, i + 1).toUpperCase();
				} else {
					int j = (256 + abyte0[0]) - 160;
					int k = (256 + abyte0[1]) - 160;
					int l = j * 100 + k;
					if (l > 1600 && l < 5590) {
						for (int i1 = 22; i1 >= 0; i1--) {
							if (l < li_SecPosValue[i1])
								continue;
							s1 = s1 + lc_FirstLetter[i1];
							break;
						}

					} else {
						int j1 = ((j - 56) * 94 + k) - 1;
						if (j1 >= 0 && j1 <= 3007)
							s1 = s1 + ls_SecondSecTable.substring(j1, j1 + 1);
						else
							s1 = s1 + s.charAt(i);
					}
				}
			}

		} catch (Exception exception) {

			exception.printStackTrace();
		}
		return s1.toLowerCase();
	}
    /**
     * 字符集转换统一方法，将fromEncoding类型转换成toEncoding类型字符
     * @param s
     * @param fromEncoding
     * @param toEncoding
     * @return
     */
    public static String encoding(String s,String fromEncoding,String toEncoding){
        try{
            s = (s == null) ? "" : new String(s.getBytes(fromEncoding), toEncoding);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return s;
    }
    
	/**
	 * 沈云刚加入 得到ascii字符
	 * 
	 * @param s
	 *            字符串
	 * @return ascii字符串
	 */
	public static String ChineseStringToAscii(String s) {
		try {
			CharToByteConverter chartobyteconverter = CharToByteConverter
					.getConverter("GBK");
			byte abyte0[] = chartobyteconverter.convertAll(s.toCharArray());
			char ac[] = new char[abyte0.length];
			for (int i = 0; i < abyte0.length; i++)
				ac[i] = (char) (abyte0[i] & 0xff);

			return new String(ac);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return s;
	}

	/**
	 * 沈云刚加入 得到utf字符串
	 * 
	 * @param s    字符串
	 * @return utf 字符串
	 */

	public static String ChineseStringToUTF(String s) {
		try {
			CharToByteConverter chartobyteconverter = CharToByteConverter
					.getConverter("UTF-8");
			byte abyte0[] = chartobyteconverter.convertAll(s.toCharArray());
			char ac[] = new char[abyte0.length];
			for (int i = 0; i < abyte0.length; i++)
				ac[i] = (char) (abyte0[i] & 0xff);
			return new String(ac);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return s;
	}

	/**
	 * 沈云刚加入 得到字符串
	 * 
	 * @param s
	 *            ascii字符串
	 * @return String字符串
	 */
	public static String AsciiToChineseString(String s) {
		char ac[] = s.toCharArray();
		byte abyte0[] = new byte[ac.length];
		for (int i = 0; i < ac.length; i++)
			abyte0[i] = (byte) (ac[i] & 0xff);

		try {
			ByteToCharConverter bytetocharconverter = ByteToCharConverter
					.getConverter("GBK");
			return new String(bytetocharconverter.convertAll(abyte0));
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return s;
	}

	/**
	 * 沈云刚加入 字符替换
	 * 
	 * @param s
	 *            "[(]?([a-zA-Z]+[a-zA-Z0-9]*)[.]?" + "([a-zA-Z]+[a-zA-Z0-9]*)" +
	 *            \\s*((like)|=|(>)|(<)|(>=)|(<=)|(!=))\\s*: + s2 + "[)]?" +
	 *            \\s*(,)?\\s*
	 * @param s1
	 *            字符串
	 * @param s2
	 *            字符串
	 * @return 字符串
	 */
	public static synchronized String regexReplace(String s, String s1,
			String s2) {
		Pattern pattern = PatternFactory.getPattern(s);
		Matcher matcher = pattern.matcher(s2);
		StringBuffer stringbuffer = new StringBuffer();
		for (; matcher.find(); matcher.appendReplacement(stringbuffer, s1))
			;
		matcher.appendTail(stringbuffer);
		return stringbuffer.toString();
	}

	/**
	 * 沈云刚加入 得到字符串 例如 "111" 5得到 00111 传入的字符小于长度在字符串前加0
	 * 
	 * @param s
	 *            字符串
	 * @param i
	 *            长度
	 * @return 字符串
	 */
	public static String fillZero(String s, int i) {
		String s1;
		if (s.length() < i) {
			char ac[] = new char[i - s.length()];
			for (int j = 0; j < i - s.length(); j++)
				ac[j] = '0';

			s1 = new String(ac) + s;
		} else {
			s1 = s;
		}
		return s1;
	}
	/**
	 * LWD 例如 "11100" 得到 111 
	 * 
	 * @param s
	 *            字符串
	 * @param i
	 *            长度
	 * @return 字符串
	 */
	public static String dealZero(String s) {
		String s1="";
		if (s.length() >0) {
			for (int j = s.length()-1; j >=0; j--)
				{
				  if(!"0".equals(s.substring(j,j+1)))
				  {					 
					  s1=s.substring(0,j+1);
					  break;
				  }
				  
				}

		} else {
			s1 = s;
		}
		return s1;
	}
	/**
	 * @param s
	 * @param s1
	 * @return
	 */

	public static synchronized boolean exist(String s, String s1) {
		Pattern pattern = PatternFactory.getPattern(s);
		Matcher matcher = pattern.matcher(s1);
		return matcher.find();
	}

	/**
	 * @param s
	 * @return
	 */
	public static synchronized String dealOrderBy(String s) {
		String s1 = "order\\s+by";
		String s2 = "\\s+";
		s = regexReplace(s2, " ", s);
		String s3 = s;
		if (exist(s1, s3.toLowerCase())) {
			s3 = regexReplace(s1, GlobalNames.ORDER_BY, s3.toLowerCase());
			int i = s3.lastIndexOf(GlobalNames.ORDER_BY);
			s = s.substring(0, i) + GlobalNames.ORDER_BY + " "
					+ s.substring(i + 8);
		}
		return s;
	}

	public static void main(String[] args) {
		/*String s = "select NWID,NWSTIME,NWCONTENT,NWCOUNTS "
				+ "from news where nwid > ? and nwid < ?  order by nwid"
				+ "_&ST_nwid,ST_nwid"
				+ "_&ST_NWID,DA_NWSTIME,BL_NWCONTENT,IN_NWCOUNTS" + "_&3";
		System.out.println(s);
		String[] ret = getAsStringArray(s, "_&");
		System.out.println(ret.toString());

		String s2 = StringReplace("_&", "%%", s);
		System.out.println(s2);

		s = "aaaaaaaa _1 _2 _3 bbbbbbbbb";
		String s3 = getReplaceString("_", s, ret);
		System.out.println(s3);
        
        s = "c";
        System.out.println(fillString(s,'$',10,2));*/
		System.out.println(dealZero("456004304000"));
		
	}
}
