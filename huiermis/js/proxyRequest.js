//通过XMLHTTP请求后台
function proxyRequest(action) {
  	var requestURL = "/" + lemis.WEB_APP_NAME + action  	
    var XMLHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    XMLHTTP.open("POST", requestURL, false);
    XMLHTTP.send("");
    var result = "";
    result = XMLHTTP.responseText;
    var begin = result.indexOf("#");
    var end = result.lastIndexOf("#");
    if(-1 != begin){
    	return  result.substring(begin + 1, end - 1);
    }else{
      return "";
    }
}
