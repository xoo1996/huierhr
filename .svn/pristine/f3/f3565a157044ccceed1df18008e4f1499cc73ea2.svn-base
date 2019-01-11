
function selectall(obj)
{
	if(null ==obj) return false;
 var num=obj.length;
  if(document.all("checkall").checked){
    if(num==null){obj.checked=true;}
	else{
       for (var i=0;i<num;i++)
	     obj[i].checked=true;
	}
	}
  else
  {
   if(num==null){obj.checked=false;}
   else{
     for (var i=0;i<num;i++)
      obj[i].checked=false;
  }
  }
}

//判断是否全选，如果下面的所有checkbox全部选中，则修改checkall的状态
function checkItem(checkListName, controlCheck)
{
 var aryChecked = document.all(checkListName);
 if (aryChecked != null)
 {
  if (aryChecked.length != null && aryChecked.length > 0)
  {
   var nCount = 0;
   for (var i = 0; i < aryChecked.length; i ++)
   {
    if (aryChecked[i].checked)
        nCount ++;
   }
   if (nCount == aryChecked.length) {


       controlCheck.checked = true;
	}  else{
       controlCheck.checked = false;
 	}
  }
  else
  {
   controlCheck.checked = aryChecked.checked;
  }
 }
}
//只能选择一个
function editObj(name) {
    var checkObj = document.all(name);
    if (checkObj) {
      if (checkObj.length) {
        for (var i=0,j=0;i<checkObj.length && j<=2;i++) {
          if (checkObj[i].checked) {
            j++;
          }
        }
        if (j > 1) {
          alert("只能选择一个对象");
          return false;
        } else if (j == 0) {
          alert("请选择对象");
          return false;
        }
      } else {
        if (! checkObj.checked) {
          alert("请选择对象");
          return false;
        }
      }
    } else {
      alert("当前没有选中的对象");
      return false;
    }

	return true;
  }

//必须有选中
function delObj(name) {
    var checkObj = document.all(name);   //复选框对象
    if (checkObj) {
      if (checkObj.length) {
        for (var i=0,j=0;i<checkObj.length;i++) {
          if (checkObj[i].checked) {
            j++;
            break;
          }
        }
        if (j == 0) {
          alert("请选择要删除的对象");
          return false;
        }
      } else {
        if (!checkObj.checked) {
          alert("请选择要删除的对象");
          return false;
        }
      }
    } else {
      alert("当前没有可删除的对象");
      return false;
    }
     return true;
  }
//回车--提交
function aaa() {
    if (event.keyCode == 13) {
        gotoPage();
		event.keyCode = 9;
    }
}
//设置页面元素的样式
function setClass(eleName, clsName) {
    document.all(eleName).className = clsName;
}


//得到表单上的所有元素的值
function getAlldata(obj){
    var data = "";
    for (i=0; i<obj.length; i++ ){
        if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button"){
            if( obj(i).type == "select-multiple"){
                for(j=0; j<obj(i).length;j++){
                    if (obj(i).options[j].selected ){
                        data = data+obj(i).id+"="+replaceStr(obj(i).options[j].value)+"&";
                    }
                }
            }else if(obj(i).type =="radio" || obj(i).type =="checkbox"){
                if (obj(i).checked){
                    data=data+obj(i).id+"="+replaceStr(obj(i).value)+"&";
                }
            }else{
                data=data+obj(i).id+"="+replaceStr(obj(i).value)+"&";
            }
        }
    }
    return data;
}

function replaceStr(str)
{
	str = str.replace(/%/g,"%25");
	str = str.replace(/&/g,"%26");
    str = str.replace(/\\/g,"&#92;");
    str = str.replace(/</g,"&#60;");
    str = str.replace(/>/g,"&#62;");
    str = str.replace(/\"/g,"&#34;");
    str = str.replace(/ /g,"&nbsp;");

    return str;
}

/**得到当前行的数据
*@param obj object
*/
function getRowData(obj){
	//get the parent element(TD)
	var obj_col=obj.parentElement;
	if (obj_col==null)
	{
		return;
	}
	//get the parent element again(TR)
	var obj_row=obj_col.parentElement;
	if (obj_row==null)
	{
		return;
	}
	//keep the data of the current line
	var rowData=new Array();
	var iLength=obj_row.childNodes.length;
	var iNum=0;
	for (var i=0;i<iLength;i++)
	{
		//rowData[iNum]=obj_row.childNodes(i).childNodes(0).value;
		rowData[iNum]=obj_row.childNodes(i).innerText;
		iNum++;
	}
	return rowData;
}

//得到要编辑的数据（1条）name:checkbox的name
function getEditData(name){
  var obj=document.all.tags("input");
  var rowdata = null;
	var str="";
	for(var i=0;i<obj.length;i++)
	{
      if(obj[i].type == "checkbox"){

        if( obj[i].name == name && obj[i].checked){
      		rowdata = getRowData(obj[i]);
              break;
        }
      }
	}
  return rowdata;
}


//弹出模式对话框并与后台交互 add by chenkl
function MM_openModalWindow(requestURL,winName,width,height) { //v2.0
  newwin = window.showModalDialog(requestURL,winName,'dialogWidth:'+width+'px;dialogHeight:'+height+'px;help:no;status:no');
  //newwin.moveTo((screen.width-width)/2,(screen.height-height)/2);

}

function executeReqAction(requestAction){
if((null != requestAction) && ("" != requestAction )){
 		var reqAction = "/" + unieap.WEB_APP_NAME + requestAction
  		var XMLReq = new ActiveXObject("Microsoft.XMLHTTP");
  		XMLReq.open("POST", reqAction, false);
  		XMLReq.send("");
  	}
}

function openModalWindow(requestAction,requestURL,winName,width,height) { //v2.0

	executeReqAction(requestAction);
  MM_openModalWindow(requestURL,winName,width,height);
  //newwin.moveTo((screen.width-width)/2,(screen.height-height)/2);

}

//去掉字符串的前后空格
function trim(str){
	var temp = str;
	var exit = false;
	while(!exit){
		if(" " == temp.charAt(0)){
			temp = temp.substring(1,temp.length);
		}else{
			exit = true;
		}
	}
	exit = false;
	while(!exit){
		if(" " == temp.charAt(temp.length - 1 )){
			temp = temp.substring(0,temp.length - 1);
		}else{
			exit = true;
		}
	}
	return temp;
}
//得到输入框的值，并拼装成name=value&name1=value1的形式
function getInputData(){
	 var obj=document.all.tags("input");
	var str="";
	for(var i=0;i<obj.length;i++)
	{
      if(obj[i].type == "text"){
		str = str + "&" + replaceStr(obj[i].name) + "=" + replaceStr(obj[i].value);
      }
	}
	return str;
}

