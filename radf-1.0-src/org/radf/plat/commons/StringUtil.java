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
 * String����ش�����
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
	 * ���ڴ��ݵ�XML�ļ��б�ʶ��"< ,/, > "��ת��������ֹxml��ʽ��������
	 * 
	 * @param val
	 * @return
	 */
	public static String deal(String val) {

		val = StringReplace("\"", "��", val);
		val = StringReplace(">", "��", val);
		val = StringReplace("<", "��", val);
		return val;
	}

	/**
	 * ������ַ�����<��>��&ת����html�ı����ַ���־ ���磺A&B<C>Dת���Ժ��ΪA&amp;B&lt;C&gt;D
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
     * ���ɱ�׼·����ʽ��
     * ��·���е�"\\"�Լ�"//"�滻��"/"��
     * @param path
     * @return
     */
    public static String getFormatPath(String path) {
        path = path.replaceAll("\\\\", "/");
        path = path.replaceAll("//", "/");
        return path;
    }

	/**
	 * describe:��ȡ���������ݽ���ת����ת����html�ļ���ʽ�ַ���
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
	 * ɾ�������ַ�����html���"<>"֮����������ݡ� ע�ⲻ֧��Ƕ�ף������Ƕ�׵����ţ��Ե�һ�����صġ�>��Ϊ׼�� ���磺abc<html
	 * x=<yyy>D>efgת����ΪabcDefg
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
	 * ɾ��html��ǲ���ȡһ���ַ����������ɲ�������
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
     * @���ܣ���ȡ�ַ���strָ������len1����������"."���������󳤶�Ϊlen2��
     *      ���str����С��len1��len2=0��ֱ�ӷ���ԭʼstr�ַ���
	 * @param  str
     * @param  len1 ��ȡ���ֳ��ȣ����ֻ�Ӣ���ַ���ֻ��һ����
     * @param  len2 �������󳤶ȣ����Ϊ0��ʾ�̶�����"����"
	 * @return str
	 */
	public static String toOmit(String str, int len1,int len2) {
		if (len1 == 0)
            len1 = 112;
		if (str.length() >= len1){
            if(len2==0){
                str = str.substring(0, len1) + "����";
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
	 * ��ָ���ַ���to�滻ԭʼ�ַ���source�е�from�ַ���
	 * �����õ������滻˫���ţ�StringReplace("\"","\'",oldString)
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

		int i = -1;//shenyg�޸ļ���Ҫ�滻��λ��������0�����������˵� �����޸ĵ�ǰ����
		i = source.indexOf(from);
		while (i != -1) {
			source = source.substring(0, i).concat(to).concat(
					source.substring(i + from.length()));
			i = source.indexOf(from);
		}
		return (source);
	}

	/**
	 * ���ݷָ��delim�����ַ���s_valueת��Ϊ��������
	 * 
	 * @param s_value
	 *            �ַ���
	 * @param delim
	 *            �ָ�����Ϊ��Ĭ��ʹ�á�,���ָ�
	 * @return Collection �ַ����б�
	 */
	public static String[] getAsStringArray(String s_value, String delim) {
		if (delim == null || delim.equalsIgnoreCase("")) {
			delim = ",";
		}
		String[] s = new String[getSubtringCount(s_value, delim) + 1];
        int firstindex = s_value.indexOf(delim); // ��һ��λ��
        if (firstindex == -1) {
            //������û��ָ���ķָ����
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
	 * ���������ַ���sourceʹ��chr(ֻ���ǵ��ַ�)����Ϊ�������顣
	 * 
	 * @param source
	 *            ��Ҫ���л��ֵ�ԭ�ַ���
	 * @param delim
	 *            ���ʵķָ��ַ���Ϊ����Ĭ����","�ָ�
	 * @return �����Ժ�����飬���sourceΪnull��ʱ�򷵻���sourceΪΨһԪ�ص����飬
	 *         ���delimΪnull��ʹ�ö�����Ϊ�ָ��ַ�����
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
	 * ���������ַ���sourceʹ��delim����Ϊ�������顣
	 * 
	 * @param source
	 *            ��Ҫ���л��ֵ�ԭ�ַ���
	 * @param delim
	 *            ���ʵķָ��ַ�
	 * @return �����Ժ�����飬���sourceΪnull��ʱ�򷵻���sourceΪΨһԪ�ص����顣
	 */
	public static String[] split(String source, char chr) {
		return split(source, String.valueOf(chr));
	}

	/**
	 * ��null����""�ַ�ת����"&nbsp;"����Ҫ����htmlҳ������Ҫ�Ŀո���ʾ��
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
	 * �����ַ���ָ���ַ��Ժ�����ݵ��ַ�������
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
	 * ���ַ����еı���ʹ��values�����е����ݽ����滻�� �滻�Ĺ����ǲ�����Ƕ�׵ģ�������滻�������а����������ʽʱ�����滻��
	 * 
	 * @param prefix
	 *            ����ǰ׺�ַ���
	 * @param source
	 *            ��������ԭ�ַ���
	 * @param values
	 *            �滻�õ��ַ�������
	 * @return �滻����ַ����� ���ǰ׺Ϊnull��ʹ�á�%����Ϊǰ׺��
	 *         ���source����valuesΪnull����values�ĳ���Ϊ0�򷵻�source��
	 *         ���values�ĳ��ȴ��ڲ����ĸ����������ֵ�������ԣ�
	 *         ���values�ĳ���С�ڲ����ĸ��������������в�����ʹ�����һ��ֵ�����滻��
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
	 * ���ַ����еı������ԡ�%��Ϊǰ��������֣�ʹ��values�����е����ݽ����滻��
	 * �滻�Ĺ����ǲ�����Ƕ�׵ģ�������滻�������а����������ʽʱ�����滻��
	 * 
	 * @param source
	 *            ��������ԭ�ַ���
	 * @param values
	 *            �滻�õ��ַ�������
	 * @return �滻����ַ���
	 */
	public static String getReplaceString(String source, String[] values) {
		return getReplaceString("%", source, values);
	}

	/**
	 * �ַ����������Ƿ����ָ�����ַ�����
	 * 
	 * @param strings
	 *            �ַ�������
	 * @param string
	 *            �ַ���
	 * @param caseSensitive
	 *            �Ƿ��Сд����
	 * @return ����ʱ����true�����򷵻�false
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
	 * ���ַ�������ʹ��ָ���ķָ����ϲ���һ���ַ�����
	 * 
	 * @param array
	 *            �ַ�������
	 * @param delim
	 *            �ָ�����Ϊnull��ʱ��ʹ��""��Ϊ�ָ�������û�зָ�����
	 * @return �ϲ�����ַ���
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
	 * ��ָ�����ַ��ͳ�������һ�����ַ���ָ�����ȵ��ַ�����
	 * 
	 * @param c
	 *            ָ�����ַ�
	 * @param length
	 *            ָ���ĳ���
	 * @return �������ɵ��ַ���
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
     * ��ָ���ַ���sourceͷ����β�����ָ���ַ�c��ʹ�ܳ��ȴﵽlen�����source�����Ѿ�����len���򲻱�
     * @param source    ԭ���ַ���
     * @param c         Ҫ��ӵ��ַ�
     * @param len       �����ܳ���
     * @param type      ��ӷ�ʽ1-�����ԭ���ַ���ǰ�棬����-����
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
	 * �õ��ַ������ֽڳ��ȡ�
	 * 
	 * @param source
	 *            �ַ���
	 * @return �ַ������ֽڳ���
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
	 * �õ��ַ����е��Ӵ��ĸ�����
	 * 
	 * @param source
	 *            �ַ���
	 * @param sub
	 *            �Ӵ�
	 * @return �ַ����е��Ӵ��ĸ���
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
	 * ���Ƹռ��� �õ��ַ���:ƴ���ַ���������� ���� ����˭ ���� wss
	 * 
	 * @param s
	 *            �ַ���
	 * @return ƴ���ַ���
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
     * �ַ���ת��ͳһ��������fromEncoding����ת����toEncoding�����ַ�
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
	 * ���Ƹռ��� �õ�ascii�ַ�
	 * 
	 * @param s
	 *            �ַ���
	 * @return ascii�ַ���
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
	 * ���Ƹռ��� �õ�utf�ַ���
	 * 
	 * @param s    �ַ���
	 * @return utf �ַ���
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
	 * ���Ƹռ��� �õ��ַ���
	 * 
	 * @param s
	 *            ascii�ַ���
	 * @return String�ַ���
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
	 * ���Ƹռ��� �ַ��滻
	 * 
	 * @param s
	 *            "[(]?([a-zA-Z]+[a-zA-Z0-9]*)[.]?" + "([a-zA-Z]+[a-zA-Z0-9]*)" +
	 *            \\s*((like)|=|(>)|(<)|(>=)|(<=)|(!=))\\s*: + s2 + "[)]?" +
	 *            \\s*(,)?\\s*
	 * @param s1
	 *            �ַ���
	 * @param s2
	 *            �ַ���
	 * @return �ַ���
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
	 * ���Ƹռ��� �õ��ַ��� ���� "111" 5�õ� 00111 ������ַ�С�ڳ������ַ���ǰ��0
	 * 
	 * @param s
	 *            �ַ���
	 * @param i
	 *            ����
	 * @return �ַ���
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
	 * LWD ���� "11100" �õ� 111 
	 * 
	 * @param s
	 *            �ַ���
	 * @param i
	 *            ����
	 * @return �ַ���
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
