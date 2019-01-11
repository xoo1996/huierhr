
package org.radf.plat.commons;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


/**
 * ͨ��У�鴦����
 * @author zqb
 * @version 1.0
 */
public class CommonVerify {

	/**
	 * @������
	 */
	public CommonVerify() {

	}

	/**
	 * �ж�������ַ����Ƿ���ָ���ĳ������
	 * @param name
	 * @param length
	 * @return boolean
	 * @roseuid 3E486AD4014E
	 */
	public static boolean stringLenthVerify(String name, int length) {
		if (name.length() == length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �ж�������ַ����׻�β�Ƿ��пո�,���пո���ȥ��
	 * @param name
	 * @return java.lang.String
	 * @roseuid 3E486BFE034B
	 */
	public static String stringTrimVerify(String name) {
		return name.trim();
	}

	/**
     * �жϴ�����ַ����Ƿ��ǣ�����������
	 * ����ȡ���ַ�����ÿһλ,�ж�ȡ�����ַ���ascii���Ƿ���'0'��'9'��Ӧascii֮��
	 * @param name
	 * @return boolean
	 * @roseuid 3E486D4E009B
	 */
     public static boolean numberVerify(String name) {
        boolean isNumberChar = true;
        int i = 0;
        char x;
       // while (isNumberChar || i+1 >= name.length()) {���Ƹ��޸� �����ǰ�����ַ�������С�ڵ���i+1�Ļ� �ǻػ�ȡһ�����ַ�
	   // ���統ǰ��������ĳ���Ϊ8 ѭ���Ľ��iΪ7 i+1һ�����ڵ���8 
		while (isNumberChar && i < name.length()) {
            x = name.charAt(i);
            if (x < '0' || x > '9')
                isNumberChar = false;
            i++;
        }
        return isNumberChar;
    }

	/**
     * �жϴ�����ַ����Ƿ��Ǵ�Ӣ���ַ�����a-z��A-Z) ����ȡ���ַ�����ÿһλ,�ж�ȡ�����ַ���ascii���Ƿ���'a'��'Z'��Ӧascii֮��
     * 
     * @param name
     * @return boolean
     * @roseuid 3E486D590051
     */
	public static boolean charVerify(String name) {
		char achar[];
		achar=name.toCharArray();
		for (int i=0;i<achar.length;i++){
			if (achar[i]>='A' && achar[i]<='z'){
			}
			else{
				return false;
			}

		}
		return true;
	}

	/**
	 * �ж�ָ�����ַ����Ƿ�Ϊ�գ�����ֵ1�����գ�0��Ϊ��
	 * @param name
	 * @return int
	 * @roseuid 3E486D810199
	 */
	public static int nullVerify(String name) {
		if (name==null){
			return 0;
		}else{
			return 1;
		}
	}

    /**
     * ���ָ���Ľ���id�Ƿ��������Ȩ�޵Ľ����б���
     * @param functionList��ֻ��FunctionID�ֶε��б�
     * @param functionID
     * @return boolean
     */
    public boolean postValidate(Collection functionList, String functionID) {
        boolean isValid = false;
        if (functionList != null) {
            String function;
            Iterator ite = functionList.iterator();
            while (ite.hasNext()) {
                function = (String) ite.next();
                if (function.equals(functionID)) {
                    isValid = true;
                    break;
                }
            }
        }
        return isValid;
    }
    
    /**
     * �������֤�Ϸ��Ե�У��
     * @param identityCode
     * @return
     */
    public static boolean ckeckIndentityCode(String identityCode) {
        if (identityCode == null || identityCode.equalsIgnoreCase(""))
            return false;
        boolean valid = false;
        identityCode = identityCode.trim();
        if (identityCode.length() != 18 && identityCode.length() != 15) {
            return false;
        } else if (identityCode.length() == 15) {
            try{
                int y,m,d;
                Date temp;
                y = new Integer(identityCode.substring(6,8)).intValue();
                m = new Integer(identityCode.substring(8,10)).intValue();
                d = new Integer(identityCode.substring(10,12)).intValue();
                if(m==1||m==3||m==5||m==7||m==8||m==10||m==12){
                    if(d>31){
                        return false;
                    }
                }else if(m==2){
                    //TODO �˴��Ƿ�������֤Ŀǰû��
                    if(d>29){
                        return false;
                    }
                }else if(m<=12 && m>0){
                    if(d>30){
                        return false;
                    }
                }else{
                    return  false;
                }

            }catch(Exception e){
                return false;
            }
            return true;
        } else {
            int[] w = new int[19];
            w[2] = 2;
            w[3] = 4;
            w[4] = 8;
            w[5] = 5;
            w[6] = 10;
            w[7] = 9;
            w[8] = 7;
            w[9] = 3;
            w[10] = 6;
            w[11] = 1;
            w[12] = 2;
            w[13] = 4;
            w[14] = 8;
            w[15] = 5;
            w[16] = 10;
            w[17] = 9;
            w[18] = 7;
            int i = 0, sum = 0;
            for (i = 1; i <= 17; i++) {
                int temp =
                    new Integer(identityCode.substring(i-1, i )).intValue();
                sum = sum + temp * w[19 - i];
            }
            sum=sum%11;
            String a18;
            switch (sum){
                case 0:
                case 1:     a18=(1-sum)+"";
                            break;
                case 2:     a18="X";
                            break;
                default:    a18=(12-sum)+"";
            }
            a18=a18.substring(0,1);
            String id=identityCode.substring(17,18);
            if(a18.equalsIgnoreCase(id)){
                return true;
            }else{
                return false;
            }
        }
    }
    
    /**
	 * 6λ����ʽ��yyyymm��,��������, yyyy��1980��2050֮��,mm��1��12֮��
	 * 
	 * @param costDate
	 * @return boolean
	 * @roseuid 3E48867101D5
	 */
	public static boolean costDateVerify(String costDate) {
		int yyyy, MM, dd;
		boolean flag = false;

		if (!CommonVerify.numberVerify(costDate) || costDate.length() != 6) {
			return false;
		}
		yyyy = Integer.parseInt(costDate.substring(0, 4));
		MM = Integer.parseInt(costDate.substring(4, 6));
		if (yyyy >= 1980 && yyyy <= 2050 && MM >= 1 && MM <= 12)
			flag = true;

		return flag;
	}
}
