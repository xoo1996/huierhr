function url_change(){
    this.deleURL  = "deleURL";
}
var url_change = new url_change();
var deleURL = "deleURL";

// 得到删除返回的URL
function getDeleteURL(obj) {
  var url = "&"+deleURL+"="+obj.replace(/\&/g,";"+deleURL+";");
  return url;
}