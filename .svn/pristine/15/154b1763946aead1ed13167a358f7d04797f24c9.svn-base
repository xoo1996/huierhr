/**
 * 该脚本文件提供一些公用的函数
 * @copyright bjlbs 2004
 * @author zhouzm
 * @date 2004-3-10

*/


/**去掉字符串中的所有空格
 * @param str 要去掉空格的字符串
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

/**去掉字符串中的头尾空格
 * @param str 要去掉空格的字符串
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