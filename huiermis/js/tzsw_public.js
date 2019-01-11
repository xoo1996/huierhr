//输入一个‘yyyy-mm-dd’格式的str和增加的天数addt
//使用方法（下例中，输入aa中的日期，格式为YYYY-MM-DD，鼠标点击aa外区域，bb会显示15天后的日期：
//<input name="aa" onBlur="document.all.bb.value=addDateTime(this.value,15)">
//<br>
//<input name="bb">
function addDateTime(str,addt){
  var reg1 = /^(\d{4,4})-(\d{2,2})-(\d{2,2})$/; 
  var r = str.match(reg1); 
  d= new Date(r[1], --r[2],r[3]*1+addt); 
  var iFullYear=d.getFullYear()
  var iMonth=parseInt(d.getMonth())+1;
  var iDate=parseInt(d.getDate());
  if(iMonth<10){
  	iMonth="0"+iMonth;
  }
  if(iDate<10){
  	iDate="0"+iDate;
  }
  var FullDate=iFullYear+"-"+iMonth+"-"+iDate;
  return FullDate;
}