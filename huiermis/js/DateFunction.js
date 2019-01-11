// 创建人： 饶福华
// 创建时间：20050120
// 检查日前的先后顺序 form的字符串和日期的先后顺序字符串，从大到小排列
function checkDates(obj,obj2) {
	if (obj2==null||obj2=="") {
		alert("需要比较的日期不足，不能比较！");
  	return 	false;
	}
	var cDate = obj2.split(";");
	if (obj2.length < 2) {
		alert("需要比较的日期不足，不能比较！");
  	return 	false;
	}
	var oDate	=	new Array();
	for (var i=0;i<cDate.length;i++) {
		oDate[i]	=	eval(obj+"."+cDate[i]);
	}
	for (var i=1;i<oDate.length;i++) {
		if (checkDate(oDate[i-1],oDate[i])) {
		} else {
			return false;
		}
	}
	return true;
}
// 比较2个日期字段值的大小，前大后小 true
function checkDate(obj,obj2){
	if (compareDate(obj.value,obj2.value)) {
		return true;
	} else {
		alert(obj.label+"应该晚于"+obj2.label+"！");
		obj.focus();
		return false;
	}
}

/**比较两个日期大小,2004-12-01 > 2004-11-08会返回true
 * @param max 两个日期中大的日期
 * @param min 两个日期中小的日期
 * @return true/false max大于min/max小于min
 */
function compareDate(max,min){
    var newMax = strToDate(max);
    var newMin = strToDate(min);
    if (newMax >= newMin){
        return true;
    }else{
        return false;
    }
}

/**用于把一个日期string 转换成一个 Date 类型的值
*  @param strDate 要转换的字符串 ‘20020303’ 或 ‘2002-3-3’ 或 ‘2002.3.3’
*  @return 8位字符型日期
*/
function strToDate(strDate) {
    var tempDate = strDate;
    var index1 = tempDate.indexOf(".");
    if(-1 == index1)
        index1 = tempDate.indexOf("-");
        
    var index2 = tempDate.lastIndexOf(".");
    if(-1 == index2)
        index2 = tempDate.lastIndexOf("-");

    //形式如2002-2-2,2002.2.3的解析
    if ((-1 != index1) || (-1 != index2)) {
        var year = tempDate.substring(0, index1);
        
        var m = parseInt(tempDate.substring(index1 + 1, index2),10);
        var month = "" + m;
        if(m < 10)
            month = "0" + m;
            
        var d = parseInt(tempDate.substring(index2 + 1, tempDate.length),10);             
        var day = "" + d;
        if(d < 10)
            day = "0" + d;
            
        tempDate = year + month + day;
    } 
    return tempDate;
}

/**得到一个字符串的长度(中英数混合)
 * @param str 字符串
 * return 字符串长度
 */
function getStrLength(str){
    var num = str.length;
    var arr = str.match(/[^\x00-\x80]/ig)
    if(arr!=null)num+=arr.length;
    return num
}
