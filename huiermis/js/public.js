/**
 * �ýű��ļ��ṩһЩ���õĺ���
 * @copyright bjlbs 2004
 * @author zhouzm
 * @date 2004-3-10

*/


/**ȥ���ַ����е����пո�
 * @param str Ҫȥ���ո���ַ���
 */
function replaceBlankAll(str){
	str=str.toString();
	if (str=="")
	{
		return;
	}
	var reg=/ /gi;
	return str.replace(reg, "");
   
}

/**ȥ���ַ����е�ͷβ�ո�
 * @param str Ҫȥ���ո���ַ���
 */
function replaceBlank(str){
	str=str.toString();
	if (str=="")
	{
		return;
	}
	var reg=/(^\s*|\s*$)/g;
	return str.replace(reg, "");
   
}